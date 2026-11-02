package portal15.spelllegacy.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class magicWand {
    public static ItemStack getItem(){
        ItemStack magicWand = new ItemStack(Material.STICK);
        ItemMeta wandMeta = magicWand.getItemMeta();
        wandMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Magic Wand");
        wandMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        wandMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        String lore = ChatColor.GRAY + "\"a small magic wand with a big effect.\"";
        wandMeta.setLore(Collections.singletonList(lore));
        magicWand.setItemMeta(wandMeta);
        return magicWand;
    }
}
