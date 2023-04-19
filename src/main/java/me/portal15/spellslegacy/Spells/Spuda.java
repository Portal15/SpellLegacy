package me.portal15.spellslegacy.Spells;

import me.portal15.spellslegacy.SpellsLegacy;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Spuda extends BaseSpell{
    @Override
    public String getName() {
        return "Spuda";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Integer getManaCost() {
        return null;
    }

    @Override
    public void onRightClick(PlayerInteractEvent e, SpellsLegacy plugin) {
        Player p = e.getPlayer();
        for (int i = 0; i < 7; i++) {
            Location loc = p.getLocation().add(p.getLocation().getDirection().normalize().multiply(i));
            loc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, loc, 100, 0, 0, 0, 0.001);
            int locY = loc.getWorld().getHighestBlockYAt(loc) + 1;
            if (loc.getY() + 3 >= locY) {
                loc.setY(locY);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        double distance = loc.distance(p.getLocation());
                        Vector toPlayer = loc.toVector().subtract(p.getLocation().toVector());
                        toPlayer.normalize();
                        double speed = 2;
                        Vector velocity = toPlayer.multiply(speed);
                        p.setVelocity(velocity);

                    }
                }.runTaskTimer(plugin, 0, 1);
            }
        }
    }

    @Override
    public void onRightClickMystery(PlayerInteractEvent e, SpellsLegacy plugin) {
        Player p = e.getPlayer();
        p.sendMessage("Spuda Mystery");
    }
}
