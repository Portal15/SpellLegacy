package me.portal15.spellslegacy.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collection;

public class Utils {
    public static String chat(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static Entity getTargetEntity(Player p, Integer range, Particle particleType){
        for (int i = 0; i < range; i++){
            Location loc = p.getLocation().add(p.getLocation().getDirection().normalize().multiply(i + 1));
            Collection<Entity> found = p.getWorld().getNearbyEntities(loc, 1, 1, 1);
            loc.getWorld().spawnParticle(particleType, loc, 100,0,0,0,0.001);
            loc = loc.subtract(p.getLocation().getDirection().normalize().multiply(-0.5));
            loc.getWorld().spawnParticle(particleType, loc, 100,0,0,0,0.001);
            loc = loc.subtract(p.getLocation().getDirection().normalize().multiply(1));
            if (found.contains(p)) {
                found.remove(p);
            }
            if (found.size() > 0) {
                Entity target = found.stream().toList().get(0);
                return target;
            }
        }
        return null;
    }

    public static Player getTargetPlayer(Player p, Integer range, Particle particleType){
        for (int i = 0; i < range; i++){
            Location loc = p.getLocation().add(p.getLocation().getDirection().normalize().multiply(i + 1));
            Collection<Entity> found = p.getWorld().getNearbyEntities(loc, 1, 1, 1, entity -> entity instanceof Player);
            loc.getWorld().spawnParticle(particleType, loc, 100,0,0,0,0.001);
            loc = loc.subtract(p.getLocation().getDirection().normalize().multiply(-0.5));
            loc.getWorld().spawnParticle(particleType, loc, 100,0,0,0,0.001);
            loc = loc.subtract(p.getLocation().getDirection().normalize().multiply(1));
            if (found.contains(p)) {
                found.remove(p);
            }
            if (found.size() > 0) {
                Player target = (Player) found.stream().toList().get(0);
                return target;
            }
        }
        return null;
    }
}
