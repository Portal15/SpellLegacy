package me.portal15.spellslegacy.Listeners;

import me.portal15.spellslegacy.Spells.Accio;
import me.portal15.spellslegacy.Spells.BaseSpell;
import me.portal15.spellslegacy.SpellsLegacy;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayerInteractListener implements Listener {
    private final SpellsLegacy plugin;

    public PlayerInteractListener(SpellsLegacy plugin) {
        this.plugin = plugin;
        spells.put("accio", new Accio());
    }

    private final HashMap<String, BaseSpell> spells = new HashMap<>();

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack wand = new ItemStack(Material.STICK, 1);
            ItemMeta wandMeta = wand.getItemMeta();
            wandMeta.setDisplayName(ChatColor.DARK_PURPLE + "Magic Wand");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.MAGIC + "asdfghjk");
            wandMeta.setLore(lore);
            wand.setItemMeta(wandMeta);
            if (p.getInventory().getItemInMainHand().equals(wand)) {
                if (plugin.spellSlot.get(p.getUniqueId()) == 1) {
                    if (plugin.spellType1.get(p.getUniqueId()).equals("Accio")) {BaseSpell spell = spells.getOrDefault("accio", null);spell.onRightClick(e,plugin);}
                }
            }
        }

        if(!p.getInventory().getItemInMainHand().getType().equals(Material.COMMAND_BLOCK_MINECART)) return;
        e.setCancelled(true);
        String spellScroll = ChatColor.stripColor(e.getItem().getItemMeta().getDisplayName());
        if(spellScroll.equals("Accio Scroll")){
            if(SpellsLegacy.spellsFile.getConfig().getInt(p.getUniqueId().toString() + ".accio") == 0){
                SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".accio", 1);
                p.sendMessage(ChatColor.GRAY + "You successfully learned the " + ChatColor.YELLOW + ChatColor.BOLD + "Accio Spell" + ChatColor.GRAY + "!");
                ItemStack scroll = p.getInventory().getItemInMainHand();
                p.getInventory().removeItem(scroll);
            } else{
                p.sendMessage(ChatColor.YELLOW + "You have been already learned this spell!");
            }
        }
        if(spellScroll.equals("Depulso Scroll")){
            if(SpellsLegacy.spellsFile.getConfig().getInt(p.getUniqueId().toString() + ".depulso") == 0){
                SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".depulso", 1);
                p.sendMessage(ChatColor.GRAY + "You successfully learned the " + ChatColor.YELLOW + ChatColor.BOLD + "Depulso Spell" + ChatColor.GRAY + "!");
                ItemStack scroll = p.getInventory().getItemInMainHand();
                p.getInventory().removeItem(scroll);
            } else{
                p.sendMessage(ChatColor.YELLOW + "You have been already learned this spell!");
            }
        }
        if(spellScroll.equals("Spuda Scroll")){
            if(SpellsLegacy.spellsFile.getConfig().getInt(p.getUniqueId().toString() + ".spuda") == 0){
                SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".spuda", 1);
                p.sendMessage(ChatColor.GRAY + "You successfully learned the " + ChatColor.YELLOW + ChatColor.BOLD + "Spuda Spell" + ChatColor.GRAY + "!");
                ItemStack scroll = p.getInventory().getItemInMainHand();
                p.getInventory().removeItem(scroll);
            } else{
                p.sendMessage(ChatColor.YELLOW + "You have been already learned this spell!");
            }
        }

        SpellsLegacy.spellsFile.update();
    }
}
