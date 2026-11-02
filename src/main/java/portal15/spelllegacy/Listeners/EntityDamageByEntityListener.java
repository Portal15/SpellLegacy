package portal15.spelllegacy.Listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import portal15.spelllegacy.SpellLegacy;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class EntityDamageByEntityListener implements Listener {
    private final SpellLegacy plugin;
    public EntityDamageByEntityListener(SpellLegacy plugin) {
        this.plugin = plugin;
    }
    public static ArrayList<UUID> doomtoPlayers = new ArrayList<>();

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e){
        // for doomto spell
        if(doomtoPlayers.contains(e.getEntity().getUniqueId())){
            if(e.getDamager() instanceof Projectile projectile){
                if(projectile.getShooter() instanceof  LivingEntity shooter){
                    Objects.requireNonNull(shooter.getLocation().getWorld()).strikeLightningEffect(shooter.getLocation());
                    shooter.damage(15);
                    e.setCancelled(true);
                }
            }else{
                if(e.getDamager() instanceof LivingEntity damager){
                    Objects.requireNonNull(damager.getLocation().getWorld()).strikeLightningEffect(damager.getLocation());
                    damager.damage(15);
                    e.setCancelled(true);
                }
            }
        }
    }

}
