package me.portal15.spellslegacy.Spells;

import me.portal15.spellslegacy.SpellsLegacy;
import me.portal15.spellslegacy.Utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spellcaster;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import static me.portal15.spellslegacy.Utils.Utils.getTargetEntity;

public class Accio extends BaseSpell{

    @Override
    public String getName() {
        return "Accio";
    }

    @Override
    public String getDescription() {
        return "Charm that caused an object at a distance from the caster to fly into his arms";
    }

    @Override
    public Integer getManaCost() {
        return null;
    }

    @Override
    public void onRightClick(PlayerInteractEvent e,SpellsLegacy plugin) {
        Player p = e.getPlayer();
        Entity target = Utils.getTargetEntity(p,15, Particle.SPELL_INSTANT);
        Location loc = target.getLocation();
        loc.setY(loc.getY()+1);
        target.teleport(loc);

        new BukkitRunnable() {
            @Override
            public void run() {
                Location playerLocation = p.getLocation();
                Location targetLocation = target.getLocation();
                double distance = playerLocation.distance(targetLocation);
                if (distance > 2) {
                    Vector toPlayer = playerLocation.toVector().subtract(targetLocation.toVector());
                    toPlayer.normalize();
                    double speed = 1;
                    Vector velocity = toPlayer.multiply(speed);
                    target.setVelocity(velocity);
                } else {
                    cancel();
                }

            }
        }.runTaskTimer(plugin,0,1);

        p.sendMessage(Utils.chat("&eAccio!"));
        if(target instanceof Player){
            target.sendMessage(Utils.chat("&7Somebody used the&e accio &7spell on you!"));
            if (SpellsLegacy.spellsFile.getConfig().getInt(p.getUniqueId() + ".accio") == 2) {
                ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 60, 1));
            }
        }
    }
}
