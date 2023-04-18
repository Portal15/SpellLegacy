package me.portal15.spellslegacy.MenuSystem;

import me.portal15.spellslegacy.SpellsLegacy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public abstract class Menu implements InventoryHolder {

    protected Inventory inventory;
    protected ItemStack FILLER_GLASS = makeItem(Material.GRAY_STAINED_GLASS_PANE, 1, " ");
    protected Player p;
    protected SpellsLegacy plugin;

    public Menu(Player p, SpellsLegacy plugin) {
        this.p = p;
        this.plugin = plugin;
    }

    public abstract String getTitle();

    public abstract int getSlots();

    public abstract void handleMenu(InventoryClickEvent e);

    public abstract void setMenuItems();

    public void onClose(InventoryCloseEvent e) {

    }

    public void open() {
        inventory = Bukkit.createInventory(this, getSlots(), ChatColor.translateAlternateColorCodes('&', getTitle()));

        this.setMenuItems();

        p.openInventory(inventory);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void setFillerGlass() {
        for (int i = 0; i < getSlots(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, FILLER_GLASS);
            }
        }
    }

    public void setBorder() {
        int lines = inventory.getSize() / 9;
        ArrayList<Integer> slotsToFill = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            slotsToFill.add(i);
            slotsToFill.add(i + 9 * (lines - 1));
        }
        for (int i = 1; i < lines - 1; i++) {
            slotsToFill.add(i * 9);
            slotsToFill.add(17 + ((i - 1) * 9));
        }
        for (int slot : slotsToFill) {
            inventory.setItem(slot, FILLER_GLASS);
        }
    }

    public ItemStack createItem(Material material, int amount, int invSlot, String displayName, String... loreString) {
        ItemStack item = makeItem(material, amount, displayName, loreString);
        inventory.setItem(invSlot, item);

        return item;
    }

    public ItemStack createItem(Material material, int amount, int invSlot, String displayName,
                                ArrayList<String> loreString) {
        ItemStack item = makeItem(material, amount, displayName, loreString);
        inventory.setItem(invSlot, item);

        return item;
    }

    public static ItemStack makeItem(Material material, int amount, String displayName, ArrayList<String> loreString) {
        ArrayList<String> lore = new ArrayList();
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        String[] loreStr = loreString.toArray(new String[0]);
        int loreLength = (int) loreString.stream().count();

        for (int i = 0; i < loreLength; ++i) {
            String chat = loreStr[i];
            lore.add(ChatColor.translateAlternateColorCodes('&', chat));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack makeItem(Material material, int amount, String displayName, String... loreString) {
        ArrayList<String> lore = new ArrayList();
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        String[] loreStr = loreString;
        int loreLength = loreString.length;

        for (int i = 0; i < loreLength; ++i) {
            String chat = loreStr[i];
            lore.add(ChatColor.translateAlternateColorCodes('&', chat));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
}
