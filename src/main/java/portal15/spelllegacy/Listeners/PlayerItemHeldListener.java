package portal15.spelllegacy.Listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import portal15.spelllegacy.Items.magicWand;
import portal15.spelllegacy.SpellLegacy;

import java.awt.*;

public class PlayerItemHeldListener implements Listener {
    private final SpellLegacy plugin;
    public PlayerItemHeldListener(SpellLegacy plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent e) {
//        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
//            @Override
//            public void run() {
//                if(e.getPlayer().getInventory().getItemInMainHand().equals(magicWand.getItem())){
//                    int currentSlot = SpellLegacy.currentSpellSlot.getOrDefault(e.getPlayer().getUniqueId(), 0);
//                    String currentSpell = SpellLegacy.spellsFile.getConfig().getStringList(e.getPlayer().getUniqueId().toString() + ".selectedSpells").toArray(new String[0])[currentSlot];
//                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Current Spell: " + currentSpell));
//                }
//            }
//        }, 2);
        new BukkitRunnable(){
            @Override
            public void run() {
                if(e.getPlayer().getInventory().getItemInMainHand().equals(magicWand.getItem())){
                    int currentSlot = SpellLegacy.currentSpellSlot.getOrDefault(e.getPlayer().getUniqueId(), 0);
                    String currentSpell = SpellLegacy.spellsFile.getConfig().getStringList(e.getPlayer().getUniqueId().toString() + ".selectedSpells").toArray(new String[0])[currentSlot];
                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Current Spell: " + ChatColor.LIGHT_PURPLE + currentSpell.toUpperCase()));
                }else{
                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(""));
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 2, 40);
    }

//        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
//            @Override
//            public void run() {
//                if(e.getPlayer().getInventory().getItemInMainHand().equals(magicWand.getItem())){
//                    int currentSlot = SpellLegacy.currentSpellSlot.getOrDefault(e.getPlayer().getUniqueId(), 0);
//                    String currentSpell = SpellLegacy.spellsFile.getConfig().getStringList(e.getPlayer().getUniqueId().toString() + ".selectedSpells").toArray(new String[0])[currentSlot];
//                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Current Spell: " + currentSpell));
//                }
//            }
//        }, 2);

}
