package portal15.spelllegacy.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import portal15.spelllegacy.Items.magicWand;
import portal15.spelllegacy.SpellLegacy;
import portal15.spelllegacy.Spells.BaseSpell;

import java.util.ArrayList;
import java.util.UUID;

import static portal15.spelllegacy.SpellLegacy.spellIndexes;

public class PlayerInteractListener implements Listener{
    private final SpellLegacy plugin;

    public PlayerInteractListener(SpellLegacy plugin) {
        this.plugin = plugin;
    }
    public static ArrayList<UUID> cantAttack = new ArrayList<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        if(cantAttack.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            return;
        }
        if(e.getPlayer().getInventory().getItemInMainHand().equals(magicWand.getItem())){
            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if(e.getHand() == EquipmentSlot.OFF_HAND) return;
                String[] selectedSpells = plugin.spellsFile.getConfig().getStringList(e.getPlayer().getUniqueId().toString() + ".selectedSpells").toArray(new String[0]);
                BaseSpell spell = spellIndexes.getOrDefault(selectedSpells[plugin.currentSpellSlot.getOrDefault(e.getPlayer().getUniqueId(), 0)], null);
                spell.onRightClick(e, plugin);
            }
        }
    }
}
