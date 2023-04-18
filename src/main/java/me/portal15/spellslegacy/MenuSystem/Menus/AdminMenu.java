package me.portal15.spellslegacy.MenuSystem.Menus;

import me.portal15.spellslegacy.MenuSystem.Menu;
import me.portal15.spellslegacy.SpellsLegacy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AdminMenu extends Menu {

    public AdminMenu(Player p, SpellsLegacy plugin) {

        super(p, plugin);
    }

    @Override
    public String getTitle() {
        return "&c&lAdmin Menu";
    }

    @Override
    public int getSlots() {
        return 45;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        for (int i = 10; i < 15; i++) {
            if(e.getSlot() == i){
                p.closeInventory();
                p.getInventory().addItem(e.getCurrentItem());
            }

        }
        if(e.getSlot() == 40){
            p.closeInventory();
        }
    }

    @Override
    public void setMenuItems() {

        // Background Glass
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "$");
        glass.setItemMeta(glassMeta);

        ItemStack axe = new ItemStack(Material.PURPLE_WOOL);
        ItemMeta axeMeta = axe.getItemMeta();
        axeMeta.setDisplayName(ChatColor.DARK_PURPLE + "Spells Legacy Items!");
        axe.setItemMeta(axeMeta);

        // Magic Wand
        ItemStack wand = new ItemStack(Material.STICK, 1);
        ItemMeta wandMeta = wand.getItemMeta();
        wandMeta.setDisplayName(ChatColor.DARK_PURPLE + "Magic Wand");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.MAGIC + "asdfghjk");
        wandMeta.setLore(lore);
        wand.setItemMeta(wandMeta);

        // Empty Scroll
        ItemStack emptyScroll = new ItemStack(Material.KNOWLEDGE_BOOK);
        ItemMeta emptyScrollMeta = emptyScroll.getItemMeta();
        emptyScrollMeta.setDisplayName(ChatColor.DARK_PURPLE + "Empty Spell Scroll");
        ArrayList<String> emptyScrollLore = new ArrayList<>();
        emptyScrollLore.add(ChatColor.YELLOW + "Open the magic crafting table by Rclick");
        emptyScrollLore.add(ChatColor.YELLOW + "On enchanting table with crafting table in your hand!");
        emptyScrollMeta.setLore(emptyScrollLore);
        emptyScroll.setItemMeta(emptyScrollMeta);

        // Accio Scroll
        ItemStack accioScroll = new ItemStack(Material.COMMAND_BLOCK_MINECART);
        ItemMeta accioScrollMeta = accioScroll.getItemMeta();
        accioScrollMeta.setDisplayName(ChatColor.YELLOW + "Accio Scroll");
        ArrayList<String> accioScrollLore = new ArrayList<>();
        accioScrollLore.add(ChatColor.WHITE + "Accio Scroll! Rclick to learn the spell!.");
        accioScrollLore.add(ChatColor.GRAY + "/spellinfo accio - for more info about the spell");
        accioScrollMeta.setLore(accioScrollLore);
        accioScroll.setItemMeta(accioScrollMeta);

        // Spuda Scroll
        ItemStack spudaScroll = new ItemStack(Material.COMMAND_BLOCK_MINECART);
        ItemMeta spudaScrollMeta = spudaScroll.getItemMeta();
        spudaScrollMeta.setDisplayName(ChatColor.YELLOW + "Spuda Scroll");
        ArrayList<String> spudaScrollLore = new ArrayList<>();
        spudaScrollLore.add(ChatColor.WHITE + "Spuda Scroll! Rclick to learn the spell!.");
        spudaScrollLore.add(ChatColor.GRAY + "/spellinfo spuda - for more info about the spell");
        spudaScrollMeta.setLore(spudaScrollLore);
        spudaScroll.setItemMeta(spudaScrollMeta);

        // Depulso Scroll
        ItemStack depulsoScroll = new ItemStack(Material.COMMAND_BLOCK_MINECART);
        ItemMeta depulsoScrollMeta = depulsoScroll.getItemMeta();
        depulsoScrollMeta.setDisplayName(ChatColor.YELLOW + "Depulso Scroll");
        ArrayList<String> depulsoScrollLore = new ArrayList<>();
        depulsoScrollLore.add(ChatColor.WHITE + "Depulso Scroll! Rclick to learn the spell!.");
        depulsoScrollLore.add(ChatColor.GRAY + "/spellinfo depulso - for more info about the spell");
        depulsoScrollMeta.setLore(depulsoScrollLore);
        depulsoScroll.setItemMeta(depulsoScrollMeta);

        // Close Button
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName(ChatColor.RED + "Close");
        close.setItemMeta(closeMeta);

        inventory.setItem(40,close);
        inventory.setItem(10,wand);
        inventory.setItem(11,emptyScroll);
        inventory.setItem(12,accioScroll);
        inventory.setItem(13,depulsoScroll);
        inventory.setItem(14,spudaScroll);
        inventory.setItem(4,axe);

        setFillerGlass();
    }
}
