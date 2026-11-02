package portal15.spelllegacy.Spells;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import portal15.spelllegacy.Listeners.EntityDamageByEntityListener;
import portal15.spelllegacy.Listeners.EntityDamageListener;
import portal15.spelllegacy.SpellLegacy;

import java.util.HashMap;
import java.util.UUID;

public class accio extends BaseSpell{
    public accio(SpellLegacy plugin) {
        super(plugin);
    }

    @Override
    public String SpellName() {
        return "accio";
    }
    public HashMap<UUID,Integer> cooldownPlayers = new HashMap<>();

    @Override
    public void onRightClick(PlayerInteractEvent e, SpellLegacy plugin) {
        // Checks if the player in cooldown.
        if(BaseSpell.isInCooldown(cooldownPlayers, e.getPlayer())) return;

        // Add cooldown to the player.
        setCooldown(cooldownPlayers, e.getPlayer().getUniqueId(), 15);

        e.getPlayer().sendMessage(SpellLegacy.prefix + ChatColor.GREEN + "You have casted the " + ChatColor.LIGHT_PURPLE + SpellName() + " spell.");

        Entity target = BaseSpell.getTargetEntity(e.getPlayer(), 15, Particle.EGG_CRACK);
        if(target == null) return;
        e.getPlayer().sendMessage(ChatColor.GRAY + "Accio!");
        if(target instanceof Player){
            if(BaseSpell.checkShield(e.getPlayer(), (Player) target)) return;
            target.sendMessage(ChatColor.GRAY + "You have been Accioed!");
        }
        Location loc = target.getLocation();
        loc.setY(loc.getY()+1);
        target.teleport(loc);

        new BukkitRunnable() {
            @Override
            public void run() {
                Location playerLocation = e.getPlayer().getLocation();
                Location targetLocation = target.getLocation();
                double distance = playerLocation.distance(targetLocation);
                if (distance > 2) {
                    Vector toPlayer = playerLocation.toVector().subtract(targetLocation.toVector());
                    toPlayer.normalize();
                    double speed = 0.5;
                    Vector velocity = toPlayer.multiply(speed);
                    target.setVelocity(velocity);
                } else {
                    cancel();
                }

            }
        }.runTaskTimer(plugin,0,1);
    }
}
