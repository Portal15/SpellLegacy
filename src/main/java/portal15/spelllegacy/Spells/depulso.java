package portal15.spelllegacy.Spells;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import portal15.spelllegacy.SpellLegacy;

import java.util.HashMap;
import java.util.UUID;

public class depulso extends BaseSpell{
    public depulso(SpellLegacy plugin) {
        super(plugin);
    }

    @Override
    public String SpellName() {
        return "depulso";
    }

    public HashMap<UUID, Integer> cooldownPlayers = new HashMap<>();

    @Override
    public void onRightClick(PlayerInteractEvent e, SpellLegacy plugin) {
        if(BaseSpell.isInCooldown(cooldownPlayers, e.getPlayer())) return;
        setCooldown(cooldownPlayers, e.getPlayer().getUniqueId(), 20);
        e.getPlayer().sendMessage(SpellLegacy.prefix + ChatColor.GREEN + "You have casted the " + ChatColor.LIGHT_PURPLE + SpellName() + " spell.");

        Entity entity = BaseSpell.getTargetEntity(e.getPlayer(), 15, Particle.CAMPFIRE_COSY_SMOKE);
        if(entity == null) return;
        e.getPlayer().sendMessage(ChatColor.GRAY + "Depulso!");
        double distance = e.getPlayer().getLocation().distance(entity.getLocation());
        for (int i = (int) distance; i < 12 + (int) distance; i++) {
            Location loc = e.getPlayer().getLocation().add(e.getPlayer().getLocation().getDirection().normalize().multiply(i));
            int locY = loc.getWorld().getHighestBlockYAt(loc) + 1;
            if (loc.getY() + 3 >= locY) {
                loc.setY(locY);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        double distance = loc.distance(entity.getLocation());
                        Vector toPlayer = loc.toVector().subtract(entity.getLocation().toVector());
                        toPlayer.normalize();
                        double speed = 2;
                        Vector velocity = toPlayer.multiply(speed);
                        entity.setVelocity(velocity);

                    }
                }.runTaskLater(plugin, 5);
            }
        }
        //entity.setVelocity(entity.getLocation().getDirection().multiply(-1));

    }
}
