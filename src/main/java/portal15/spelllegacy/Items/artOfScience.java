package portal15.spelllegacy.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class artOfScience {
    public static ItemStack getItem(){
        ItemStack artOfScience = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta bookMeta = artOfScience.getItemMeta();
        bookMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "The Art of the Science");
        String lore = ChatColor.GRAY + "This book allows you to learn an ancient science arts.";
        bookMeta.setLore(Collections.singletonList(lore));
        artOfScience.setItemMeta(bookMeta);
        return artOfScience;
    }
}
