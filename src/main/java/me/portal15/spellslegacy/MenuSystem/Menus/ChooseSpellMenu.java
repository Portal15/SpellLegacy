package me.portal15.spellslegacy.MenuSystem.Menus;

import me.portal15.spellslegacy.MenuSystem.Menu;
import me.portal15.spellslegacy.Spells.Accio;
import me.portal15.spellslegacy.SpellsLegacy;
import me.portal15.spellslegacy.Utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spellcaster;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ChooseSpellMenu extends Menu {

    public ChooseSpellMenu(Player p, SpellsLegacy plugin) {
        super(p, plugin);
    }

    @Override
    public String getTitle() {
        return "&8Select Spell";
    }

    @Override
    public int getSlots() {
        return 45;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7??????"));
        if(!e.getCurrentItem().getItemMeta().getLore().equals(lore)){
            if (plugin.spellType1.get(p.getUniqueId()) == null) {
                switchSpell(e, 1, plugin.spellType1);
            } else if (plugin.spellType2.get(p.getUniqueId()) == null) {
                switchSpell(e, 2, plugin.spellType2);
            } else if (plugin.spellType3.get(p.getUniqueId()) == null) {
                switchSpell(e, 3, plugin.spellType3);
            }
            SpellsLegacy.spellsFile.saveConfig();
        }
    }

    @Override
    public void setMenuItems() {
        if (SpellsLegacy.spellsFile.getConfig().getInt(p.getUniqueId().toString() + ".accio") == 2){createItem(Material.BOOK,1,10,"&eAccio", "&6Makes an object at a distance from the caster to fly into his arms", "&5Accio Mystery: if the object is a player &7-->&5 the accio spell blind him for 3s");} else if((SpellsLegacy.spellsFile.getConfig().getInt(p.getUniqueId().toString() + ".accio") == 1)){createItem(Material.BOOK,1,10,"&eAccio", "&6Makes an object at a distance from the caster to fly into his arms","&7Mystery Accio: ????????");} else {createItem(Material.BOOK,1,10,"&7Accio", "&7??????");}
        if (SpellsLegacy.spellsFile.getConfig().getInt(p.getUniqueId().toString() + ".depulso") == 2){createItem(Material.BOOK,1,10,"&eDepulso", "&6Sent the target away from the caster", "&5Depulso Mystery: if you send the target to block &7--> &5 the target take some damage.");} else if((SpellsLegacy.spellsFile.getConfig().getInt(p.getUniqueId().toString() + ".depulso") == 1)){createItem(Material.BOOK,1,10,"&eDepulso", "&6Sent the target away from the caster","&7Mystery Depulso: ????????");} else {createItem(Material.BOOK,1,10,"&7Depulso", "&7??????");}
        if (SpellsLegacy.spellsFile.getConfig().getInt(p.getUniqueId().toString() + ".spuda") == 2){createItem(Material.BOOK,1,10,"&eSpuda", "&6Makes the caster dash few blocks away", "&5Spuda Mystery: if you send the target to block &7--> &5 the target take some damage.");} else if((SpellsLegacy.spellsFile.getConfig().getInt(p.getUniqueId().toString() + ".depulso") == 1)){createItem(Material.BOOK,1,10,"&eDepulso", "&6Sent the target away from the caster","&7Mystery Depulso: ????????");} else {createItem(Material.BOOK,1,10,"&7Depulso", "&7??????");}
        setFillerGlass();
    }

    private void switchSpell(InventoryClickEvent e, int slot, HashMap<UUID, String> map){
        Player p = (Player) e.getWhoClicked();
        for (int i = 10; i < 17; i++) {
            if(e.getSlot() == i){
                String spell = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
                map.put(p.getUniqueId(),spell);
                p.sendMessage(ChatColor.GRAY + "Spell Slot" + slot + " Successfully Chanced to " + spell);
            }
        }
    }
}
