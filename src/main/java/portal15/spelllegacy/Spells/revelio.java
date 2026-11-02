package portal15.spelllegacy.Spells;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import portal15.spelllegacy.SpellLegacy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class revelio extends BaseSpell {

    private final SpellLegacy plugin;

    public revelio(SpellLegacy plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @Override
    public String SpellName() {
        return "revelio";
    }

    private final HashMap<UUID, Integer> cooldownPlayers = new HashMap<>();

    @Override
    public void onRightClick(PlayerInteractEvent e, SpellLegacy plugin) {
        Player player = e.getPlayer();

        if (BaseSpell.isInCooldown(cooldownPlayers, player)) return;
        setCooldown(cooldownPlayers, player.getUniqueId(), 10);

        player.sendMessage(SpellLegacy.prefix + ChatColor.GREEN + "You have casted the " + ChatColor.LIGHT_PURPLE + SpellName() + " spell.");
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 2f, 0.5f);

        startRevelioWave(player);
    }

    private void startRevelioWave(Player player) {
        final Location center = player.getLocation();
        final List<UUID> revealedEntities = new ArrayList<>();

        // Thick, Light Blue Dust
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(200, 240, 255), 2.0f);

        new BukkitRunnable() {
            double currentRadius = 2;
            final double maxRadius = 50;
            final double expansionSpeed = 1.5;

            @Override
            public void run() {
                if (currentRadius > maxRadius) {
                    this.cancel();
                    return;
                }

                // --- VISUALS ---
                int particlesCount = (int) (currentRadius * 25);
                for (int i = 0; i < particlesCount; i++) {
                    double offset = 2.0 / particlesCount;
                    double increment = Math.PI * (3.0 - Math.sqrt(5.0));

                    double y = ((i * offset) - 1) + (offset / 2);
                    double r = Math.sqrt(1 - Math.pow(y, 2));
                    double phi = ((i + 1) % particlesCount) * increment;

                    double x = Math.cos(phi) * r;
                    double z = Math.sin(phi) * r;

                    Vector pos = new Vector(x, y, z).multiply(currentRadius);
                    Location particleLoc = center.clone().add(pos);
                    player.spawnParticle(Particle.REDSTONE, particleLoc, 1, 0, 0, 0, 0, dustOptions);
                }

                // --- LOGIC ---
                for (Entity entity : player.getWorld().getNearbyEntities(center, currentRadius + 1, currentRadius + 1, currentRadius + 1)) {
                    if (entity == player) continue;
                    if (!(entity instanceof LivingEntity)) continue;

                    double distance = entity.getLocation().distance(center);
                    if (distance <= currentRadius && distance > currentRadius - expansionSpeed) {
                        if (!revealedEntities.contains(entity.getUniqueId())) {
                            revealEntityForPlayer(player, (LivingEntity) entity);
                            revealedEntities.add(entity.getUniqueId());
                        }
                    }
                }
                currentRadius += expansionSpeed;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    private void revealEntityForPlayer(Player observer, LivingEntity target) {
        observer.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1.5f);

        new BukkitRunnable() {
            int ticks = 0;
            final int duration = 100; // 5 Seconds

            @Override
            public void run() {
                // Stop if entity died or player left
                if (!target.isValid() || !observer.isOnline() || ticks >= duration) {
                    if (target.isValid() && observer.isOnline()) {
                        sendGlowPacket(observer, target, false); // Turn off
                    }
                    this.cancel();
                    return;
                }

                // Re-send packet every second to ensure it stays glowing
                if (ticks % 20 == 0) {
                    sendGlowPacket(observer, target, true);
                }
                ticks++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    // --- UPDATED FOR 1.20+ ---
    private void sendGlowPacket(Player observer, LivingEntity target, boolean glow) {
        try {
            ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
            PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
            packet.getIntegers().write(0, target.getEntityId());

            // On older ProtocolLib, we use getWatchableCollectionModifier
            WrappedDataWatcher watcher = WrappedDataWatcher.getEntityWatcher(target).deepClone();

            // Set the Glowing Flag (Index 0, Byte)
            byte current = 0;
            if (watcher.hasIndex(0)) {
                current = (byte) watcher.getObject(0);
            }

            byte newFlags = glow ? (byte) (current | 0x40) : (byte) (current & ~0x40);

            // Update the watcher
            WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
            watcher.setObject(0, serializer, newFlags);

            // Write to packet
            packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());

            protocolManager.sendServerPacket(observer, packet);
        } catch (Exception e) {
            // If this fails, it confirms you MUST update your pom.xml using the instruction at the top.
            System.out.println("Error sending packet: " + e.getMessage());
        }
    }
}