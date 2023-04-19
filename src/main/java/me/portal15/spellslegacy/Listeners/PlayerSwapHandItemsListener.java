package me.portal15.spellslegacy.Listeners;

import me.portal15.spellslegacy.SpellsLegacy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.net.http.WebSocket;
import java.util.ArrayList;

public class PlayerSwapHandItemsListener implements Listener {

    private final SpellsLegacy plugin;

    public PlayerSwapHandItemsListener(SpellsLegacy plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerSwapHandItemEvent(PlayerSwapHandItemsEvent e) {
        Player p = e.getPlayer();
        ItemStack wand = new ItemStack(Material.STICK, 1);
        ItemMeta wandMeta = wand.getItemMeta();
        wandMeta.setDisplayName(ChatColor.DARK_PURPLE + "Magic Wand");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.MAGIC + "asdfghjk");
        wandMeta.setLore(lore);
        wand.setItemMeta(wandMeta);
        if (e.getOffHandItem().equals(wand)) {
            e.setCancelled(true);
            if (plugin.spellSlot.get(p.getUniqueId()) == 3) {
                plugin.spellSlot.put(p.getUniqueId(), 1);
                p.sendMessage(ChatColor.GRAY + "Main Spell: " + plugin.spellType1.get(p.getUniqueId()));
            } else {
                plugin.spellSlot.put(p.getUniqueId(), plugin.spellSlot.get(p.getUniqueId()) + 1);
                if (plugin.spellSlot.get(p.getUniqueId()) == 2) {
                    p.sendMessage(ChatColor.GRAY + "Main Spell: " + plugin.spellType2.get(p.getUniqueId()));
                } else {
                    p.sendMessage(ChatColor.GRAY + "Main Spell: " + plugin.spellType3.get(p.getUniqueId()));
                }
            }
        }
    }
}
