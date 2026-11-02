package portal15.spelllegacy.Spells;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;
import portal15.spelllegacy.Items.magicWand;
import portal15.spelllegacy.SpellLegacy;

import java.util.HashMap;
import java.util.UUID;

public class lumos extends BaseSpell{
    private HashMap<UUID,Integer> cooldownPlayers = new HashMap<>();

    public lumos(SpellLegacy plugin) {
        super(plugin);
    }

    @Override
    public String SpellName() {
        return "lumos";
    }

    @Override
    public void onRightClick(PlayerInteractEvent e, SpellLegacy plugin) {

        // Checks if the player in cooldown.
        if(cooldownPlayers.containsKey(e.getPlayer().getUniqueId())){
            if(cooldownPlayers.get(e.getPlayer().getUniqueId()) > 90){
                e.getPlayer().sendMessage(SpellLegacy.prefix + ChatColor.GRAY + "To disable this spell, switch spell slot or switch item in your hand.");
                return;
            }
            e.getPlayer().sendMessage(SpellLegacy.prefix + ChatColor.RED + "You can't cast this spell yet. Cooldown for: " + cooldownPlayers.get(e.getPlayer().getUniqueId()).toString() + " seconds.");
            return;
        }
        setCooldown(cooldownPlayers, e.getPlayer().getUniqueId(), 999999);
        e.getPlayer().sendMessage(SpellLegacy.prefix + ChatColor.GREEN + "You have casted the " + ChatColor.LIGHT_PURPLE + SpellName() + " spell.");
        // Put Light.
        new BukkitRunnable() {
            Location last1 = e.getPlayer().getLocation().add(0, 1, 0);
            @Override
            public void run() {
                if(plugin.getServer().getOnlinePlayers().contains(e.getPlayer())) {
                    last1.getBlock().setType(Material.AIR);
                    if (e.getPlayer().getLocation().add(0, 1, 0).getBlock().getType().equals(Material.AIR)) {
                        if (e.getPlayer().getInventory().getItemInMainHand().equals(magicWand.getItem()) && SpellLegacy.spellsFile.getConfig().getStringList(e.getPlayer().getUniqueId().toString() + ".selectedSpells").toArray(new String[0])[SpellLegacy.currentSpellSlot.getOrDefault(e.getPlayer().getUniqueId(), 0)].equalsIgnoreCase("lumos")){
                            e.getPlayer().getLocation().add(0, 1, 0).getBlock().setType(Material.LIGHT);
                            last1 = e.getPlayer().getLocation().add(0, 1, 0);
                        }else {
                            last1.getBlock().setType(Material.AIR);
                            setCooldown(cooldownPlayers, e.getPlayer().getUniqueId(), 3);
                            cancel();
                        }
                    }
                }else{
                    last1.getBlock().setType(Material.AIR);
                    setCooldown(cooldownPlayers, e.getPlayer().getUniqueId(), 3);
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 5);
        // Add cooldown to the player.
    }
}
