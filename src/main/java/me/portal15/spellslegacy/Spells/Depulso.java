package me.portal15.spellslegacy.Spells;

import me.portal15.spellslegacy.SpellsLegacy;
import me.portal15.spellslegacy.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
        Location targetLocation = target.getLocation();
        for (int i = 0; i < 10; i++) {
            Location loc = p.getLocation().add(p.getLocation().getDirection().normalize().multiply(i + p.getLocation().distance(target.getLocation())));
            loc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, loc, 100,0,0,0,0.001);
            int locY = loc.getWorld().getHighestBlockYAt(loc) + 1;
            if(loc.getY()+3 >= locY){
                loc.setY(locY);
                new BukkitRunnable() {
                    @Override
                    public void run() {double distance = loc.distance(targetLocation);
                        Vector toPlayer = loc.toVector().subtract(targetLocation.toVector());
                        toPlayer.normalize();
                        double speed = 2;
                        Vector velocity = toPlayer.multiply(speed);
                        target.setVelocity(velocity);

                    }
                }.runTaskTimer(plugin,0,1);
            }
        }
        p.sendMessage(Utils.chat("&eDepulso!"));
        if(target instanceof Player){
            target.sendMessage(Utils.chat("&7Somebody used the&e Depulso &7spell on you!"));
            if(SpellsLegacy.spellsFile.getConfig().getInt(p.getUniqueId() + ".depulso") == 2){
                if(!(targetLocation.distance(target.getLocation()) == 10)) {
                    ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60, 2));
                    ((Player) target).damage(2);
                }
            }
        }
    }

    @Override
    public void onRightClickMystery(PlayerInteractEvent e, SpellsLegacy plugin) {
    }
}