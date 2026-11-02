package portal15.spelllegacy.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class artOfVoid {
    public static ItemStack getItem(){
        ItemStack artOfVoid = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta bookMeta = artOfVoid.getItemMeta();
        bookMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "The Art of the Void");
        String lore = ChatColor.GRAY + "This book allows you to learn an ancient void arts.";
        bookMeta.setLore(Collections.singletonList(lore));
        artOfVoid.setItemMeta(bookMeta);
        return artOfVoid;
    }
}
