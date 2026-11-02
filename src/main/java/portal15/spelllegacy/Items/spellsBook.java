package portal15.spelllegacy.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class spellsBook {
    public static ItemStack getItem(){
        ItemStack spellsBook = new ItemStack(Material.BOOK);
        ItemMeta bookMeta = spellsBook.getItemMeta();
        bookMeta.setDisplayName(ChatColor.GOLD + "Spells Book");
        String lore = ChatColor.GRAY + "this is not a regular book...";
        bookMeta.setLore(Collections.singletonList(lore));
        spellsBook.setItemMeta(bookMeta);
        return spellsBook;
    }
}
