package portal15.spelllegacy.Menus.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import portal15.spelllegacy.Items.*;
import portal15.spelllegacy.Menus.Menu;
import portal15.spelllegacy.SpellLegacy;

public class adminGUI extends Menu {
    public adminGUI(Player p, SpellLegacy plugin) {
        super(p, plugin);
    }

    @Override
    public String getTitle() {
        return "&8Admin GUI";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        if(e.getCurrentItem().equals(magicWand.getItem())){
            p.getInventory().addItem(magicWand.getItem());
        }
        if(e.getCurrentItem().equals(spellsBook.getItem())){
            p.getInventory().addItem(spellsBook.getItem());
        }
        if (e.getCurrentItem().equals(artOfDarkness.getItem())){
            p.getInventory().addItem(artOfDarkness.getItem());
        }
        if (e.getCurrentItem().equals(artOfScience.getItem())){
            p.getInventory().addItem(artOfScience.getItem());
        }
        if (e.getCurrentItem().equals(artOfVoid.getItem())){
            p.getInventory().addItem(artOfVoid.getItem());
        }
        if (e.getCurrentItem().equals(artOfArts.getItem())){
            p.getInventory().addItem(artOfArts.getItem());
        }

    }

    @Override
    public void setMenuItems() {
        setBorder();
        createItem(Material.WOODEN_AXE, 1, 4, "SpellLegacy Admin Menu", "L click on any item to take it.");
        inventory.setItem(10, magicWand.getItem());
        inventory.setItem(11, spellsBook.getItem());
        inventory.setItem(12, artOfDarkness.getItem());
        inventory.setItem(13, artOfScience.getItem());
        inventory.setItem(14, artOfVoid.getItem());
        inventory.setItem(15, artOfArts.getItem());
    }
}
