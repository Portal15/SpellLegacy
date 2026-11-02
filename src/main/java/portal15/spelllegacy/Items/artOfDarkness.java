package portal15.spelllegacy.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class artOfDarkness {
    public static ItemStack getItem(){
        ItemStack artOfDarkness = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta bookMeta = artOfDarkness.getItemMeta();
        bookMeta.setDisplayName(ChatColor.BLACK + "" + ChatColor.BOLD + "The Art of the Darkness");
        String lore = ChatColor.GRAY + "This book allows you to learn an ancient dark arts.";
        bookMeta.setLore(Collections.singletonList(lore));
        artOfDarkness.setItemMeta(bookMeta);
        return artOfDarkness;
    }
}
