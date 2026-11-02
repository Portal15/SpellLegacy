//package portal15.spelllegacy.Spells;
//
//import org.bukkit.ChatColor;
//import org.bukkit.Location;
//import org.bukkit.Material;
//import org.bukkit.entity.ArmorStand;
//import org.bukkit.entity.EntityType;
//import org.bukkit.event.player.PlayerInteractEvent;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.util.EulerAngle;
//import portal15.spelllegacy.SpellLegacy;
//
//import java.util.HashMap;
//import java.util.UUID;
//
//public class diffindo extends BaseSpell{
//    public diffindo(SpellLegacy plugin) {
//        super(plugin);
//    }
//
//    @Override
//    public String SpellName() {
//        return "diffindo";
//    }
//
//    HashMap<UUID,Integer> cooldownPlayers = new HashMap<>();
//    @Override
//    public void onRightClick(PlayerInteractEvent e, SpellLegacy plugin) {
//        if(BaseSpell.isInCooldown(cooldownPlayers, e.getPlayer())) return;
//        setCooldown(cooldownPlayers, e.getPlayer().getUniqueId(), 6);
//        e.getPlayer().sendMessage(SpellLegacy.prefix + ChatColor.GREEN + "You have casted the " + ChatColor.LIGHT_PURPLE + SpellName() + " spell.");
//        e.getPlayer().sendMessage(ChatColor.GRAY + "Diffindo!");
//        Location swordLoc = e.getPlayer().getLocation().add(e.getPlayer().getLocation().getDirection().normalize().multiply(2));
//        ArmorStand armorStand = (ArmorStand) swordLoc.getWorld().spawnEntity(swordLoc, EntityType.ARMOR_STAND);
//        armorStand.setVisible(false); // Make the ArmorStand invisible
//        armorStand.setArms(true); // Enable arms on the ArmorStand
//        armorStand.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD)); // Give the ArmorStand a diamond sword
//        armorStand.setRightArmPose(new EulerAngle(Math.toRadians(e.getPlayer().getLocation().getPitch()), Math.toRadians(e.getPlayer().getLocation().getYaw()), Math.toRadians(e.getPlayer().getLocation().getZ()))); // Set the pose of the right arm to match the player's direction
//    }
//}
package portal15.spelllegacy.Spells;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import portal15.spelllegacy.SpellLegacy;

import java.util.HashMap;
import java.util.UUID;

public class diffindo extends BaseSpell {

    private final SpellLegacy plugin;

    public diffindo(SpellLegacy plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @Override
    public String SpellName() {
        return "diffindo";
    }

    private final HashMap<UUID, Integer> cooldownPlayers = new HashMap<>();

    @Override
    public void onRightClick(PlayerInteractEvent e, SpellLegacy plugin) {
        Player player = e.getPlayer();

        if (BaseSpell.isInCooldown(cooldownPlayers, player)) return;
        setCooldown(cooldownPlayers, player.getUniqueId(), 15);

        player.sendMessage(SpellLegacy.prefix + ChatColor.GREEN + "You have casted the " + ChatColor.LIGHT_PURPLE + SpellName() + " spell.");
        player.sendMessage(ChatColor.GRAY + "Diffindo!");
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 1.8f);

        // --- CALCULATION FOR PARALLEL KNIVES ---

        // 1. Get the direction the player is looking (The "Forward" vector)
        Vector forwardDir = player.getLocation().getDirection().normalize();

        // 2. Calculate the "Right" vector relative to the player
        // We do this by crossing the Forward vector with the Up vector (Y axis).
        // This gives us a vector pointing 90 degrees to the right of the player.
        Vector rightDir = forwardDir.getCrossProduct(new Vector(0, 1, 0)).normalize();

        // 3. Define the gap between knives (0.5 blocks)
        Vector offset = rightDir.multiply(1);

        // 4. Starting point (Eye location, slightly lowered so it doesn't block view)
        Location startLoc = player.getEyeLocation().subtract(0, 0.4, 0);

        // FIRE CENTER
        fireKnife(player, startLoc, forwardDir);

        // FIRE RIGHT (Add the offset)
        fireKnife(player, startLoc.clone().add(offset), forwardDir);

        // FIRE LEFT (Subtract the offset)
        fireKnife(player, startLoc.clone().subtract(offset), forwardDir);
    }

    private void fireKnife(Player caster, Location startLoc, Vector direction) {
        ArmorStand as = (ArmorStand) startLoc.getWorld().spawnEntity(startLoc, EntityType.ARMOR_STAND);

        as.setVisible(false);
        as.setGravity(false);
        as.setSmall(true);
        as.setMarker(true);
        as.setArms(true);
        as.setBasePlate(false);
        as.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));

        // Start pose
        as.setRightArmPose(new EulerAngle(Math.toRadians(-90), 0, 0));

        new BukkitRunnable() {
            int ticks = 0;
            double speed = 1; // Speed of the knife
            double spinAngle = 0; // Rotation tracking

            @Override
            public void run() {
                if (ticks > 40 || !caster.isOnline() || as.isDead()) {
                    as.remove();
                    this.cancel();
                    return;
                }

                // MOVEMENT
                Location currentLoc = as.getLocation();
                Location nextLoc = currentLoc.add(direction.clone().multiply(speed));

                // Keep looking forward
                nextLoc.setDirection(direction);
                as.teleport(nextLoc);

//                // SPINNING ANIMATION (Drill effect)
//                spinAngle += 45;
//                as.setRightArmPose(new EulerAngle(Math.toRadians(-90), 0, Math.toRadians(spinAngle)));

                // PARTICLE TRAIL
                as.getWorld().spawnParticle(Particle.CRIT, as.getLocation().add(0, 0.3, 0), 1, 0, 0, 0, 0);

                // BLOCK COLLISION
                if (nextLoc.getBlock().getType().isSolid()) {
                    as.getWorld().playSound(nextLoc, Sound.BLOCK_ANVIL_LAND, 0.5f, 2f);
                    as.getWorld().spawnParticle(Particle.BLOCK_CRACK, nextLoc, 5, 0.1, 0.1, 0.1, nextLoc.getBlock().getBlockData());
                    as.remove();
                    this.cancel();
                    return;
                }

                // ENTITY COLLISION
                for (Entity target : as.getWorld().getNearbyEntities(as.getLocation().add(0, 0.5, 0), 0.6, 0.6, 0.6)) {
                    if (target == caster) continue;
                    if (target == as) continue;
                    if (target instanceof LivingEntity) {
                        LivingEntity livingTarget = (LivingEntity) target;
                        livingTarget.damage(9.0, caster);
                        livingTarget.getWorld().playSound(livingTarget.getLocation(), Sound.ENTITY_PLAYER_HURT, 1f, 1f);
                        as.remove();
                        this.cancel();
                        return;
                    }
                }
                ticks++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }
}