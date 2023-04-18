package me.portal15.spellslegacy.Spells;

import me.portal15.spellslegacy.SpellsLegacy;
import me.portal15.spellslegacy.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Depulso extends BaseSpell{
    @Override
    public String getName() {
        return "Depulso";
    }

    @Override
    public String getDescription() {
        return "Sent the target away from the caster";
    }

    @Override
    public Integer getManaCost() {
        return null;
    }

    @Override
    public void onRightClick(PlayerInteractEvent e, SpellsLegacy plugin) {
        Player p = e.getPlayer();
        Entity target = Utils.getTargetEntity(p,15, Particle.WHITE_ASH);
        for (int i = 0; i < 10; i++) {
            Location loc = p.getLocation().add(p.getLocation().getDirection().normalize().multiply(i + 5));
            loc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, loc, 100,0,0,0,0.001);
            int locY = loc.getWorld().getHighestBlockYAt(loc) + 1;
            if(loc.getY()+3 >= locY){
                loc.setY(locY);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Location targetLocation = target.getLocation();
                        double distance = loc.distance(targetLocation);
                        if (distance > 2) {
                            Vector toPlayer = loc.toVector().subtract(targetLocation.toVector());
                            toPlayer.normalize();
                            double speed = 1;
                            Vector velocity = toPlayer.multiply(speed);
                            target.setVelocity(velocity);
                        } else {
                            cancel();
                        }

                    }
                }.runTaskTimer(plugin,0,1);
            }
        }
    }
}
