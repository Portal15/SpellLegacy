package portal15.spelllegacy.Spells;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import portal15.spelllegacy.Items.magicWand;
import portal15.spelllegacy.Listeners.EntityDamageByEntityListener;
import portal15.spelllegacy.Listeners.EntityDamageListener;
import portal15.spelllegacy.SpellLegacy;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public abstract class BaseSpell {
    private final SpellLegacy plugin;
    public BaseSpell(SpellLegacy plugin) {
        this.plugin = plugin;
    }
    public abstract String SpellName();
    public abstract void onRightClick(PlayerInteractEvent e, SpellLegacy plugin);
    public void setCooldown(HashMap<UUID, Integer> map, UUID p, Integer seconds){
        map.put(p, seconds);
        new BukkitRunnable(){
            @Override
            public void run() {
                if(map.get(p) <= 0){
                    map.remove(p);
                    cancel();
                }else{
                    map.replace(p, map.get(p) - 1);
                }
            }
        }.runTaskTimer(plugin, 20, 20);
    }
    public static boolean isInCooldown(HashMap<UUID, Integer> map, Player p){
        if(map.containsKey(p.getUniqueId())) {
            p.sendMessage(SpellLegacy.prefix + ChatColor.GRAY + "You can't cast this spell yet. Cooldown for: " + map.get(p.getUniqueId()).toString() + " seconds.");
            return true;
        }
        return false;
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

    public static boolean checkShield(Player caster, Player target){
        if(EntityDamageListener.shieldPlayers.contains(target.getUniqueId())) {
            caster.sendMessage(ChatColor.GRAY + "The target has a shield!");
            return true;
        }else if(EntityDamageByEntityListener.doomtoPlayers.contains(target.getUniqueId())){
            caster.sendMessage(ChatColor.GRAY + "The target has a shield!");
            caster.getLocation().getWorld().strikeLightningEffect(caster.getLocation());
            caster.damage(15);
            return true;
        }
        return false;
    }



}
