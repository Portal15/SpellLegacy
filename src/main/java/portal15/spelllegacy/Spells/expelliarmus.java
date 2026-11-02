package portal15.spelllegacy.Spells;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import portal15.spelllegacy.Listeners.EntityDamageByEntityListener;
import portal15.spelllegacy.Listeners.EntityDamageListener;
import portal15.spelllegacy.SpellLegacy;

import java.util.HashMap;
import java.util.UUID;

public class expelliarmus extends BaseSpell{
    private final SpellLegacy plugin;
    public expelliarmus(SpellLegacy plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @Override
    public String SpellName() {
        return "expelliarmus";
    }

    HashMap<UUID,Integer> cooldownPlayers = new HashMap<>();
    @Override
    public void onRightClick(PlayerInteractEvent e, SpellLegacy plugin) {
        // Checks if the player in cooldown.
        if(BaseSpell.isInCooldown(cooldownPlayers, e.getPlayer())) return;
        // Add cooldown to the player.
        setCooldown(cooldownPlayers, e.getPlayer().getUniqueId(), 35);
        // Add shield to the player.
        e.getPlayer().sendMessage(SpellLegacy.prefix + ChatColor.GREEN + "You have casted the " + ChatColor.LIGHT_PURPLE + SpellName() + " spell.");

        Entity target = BaseSpell.getTargetEntity(e.getPlayer(), 15, Particle.DRAGON_BREATH);
        if(target == null) return;

        e.getPlayer().sendMessage("Expelliarmus!");

        // Remove the item from the target's hand.
        if(target instanceof LivingEntity){
            if(target instanceof Player){
                if(BaseSpell.checkShield(e.getPlayer(), (Player) target)) return;
                target.sendMessage(ChatColor.GRAY + "You have been Expelliarmused!");
            }
            if(((LivingEntity) target).getEquipment().getItemInMainHand().getType().equals(Material.AIR)) {
                if (((LivingEntity) target).getEquipment().getItemInOffHand().getType().equals(Material.AIR)) {
                    return;
                }
                target.getLocation().getWorld().dropItemNaturally(target.getLocation(), ((LivingEntity) target).getEquipment().getItemInOffHand());
                ((LivingEntity) target).getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
                return;
            }
            target.getLocation().getWorld().dropItemNaturally(target.getLocation(), ((LivingEntity) target).getEquipment().getItemInMainHand());
            ((LivingEntity) target).getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
        }
        // Show Particles to the player.
    }
}
