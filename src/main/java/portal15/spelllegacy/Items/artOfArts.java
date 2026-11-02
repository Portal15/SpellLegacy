package portal15.spelllegacy.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class artOfArts {
    public static ItemStack getItem(){
        ItemStack artOfArts = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta bookMeta = artOfArts.getItemMeta();
        bookMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "The ART OF THE ARTS");
        String lore = ChatColor.GRAY + "This book allows you to learn all of the ancient arts.";
        bookMeta.setLore(Collections.singletonList(lore));
        artOfArts.setItemMeta(bookMeta);
        return artOfArts;
    }
}
