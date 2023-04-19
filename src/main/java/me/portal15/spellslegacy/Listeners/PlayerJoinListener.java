package me.portal15.spellslegacy.Listeners;

import me.portal15.spellslegacy.SpellsLegacy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerJoinListener implements Listener {
    private final SpellsLegacy plugin;

    public PlayerJoinListener(SpellsLegacy plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        plugin.spellSlot.put(p.getUniqueId(),1);
        plugin.sneak.put(p.getUniqueId(),false);
        p.sendMessage(ChatColor.MAGIC + "&&&&" + ChatColor.DARK_PURPLE + "Welcome " + ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.DARK_PURPLE + "To" + ChatColor.BOLD + ChatColor.DARK_PURPLE + "SpellsLegacy!");
        if(!p.hasPlayedBefore()){
            SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".lvl", 1);
            SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".maxMana", 100);
            SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".accio", 0);
            SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".depulso", 0);
            SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".spuda", 0);
            SpellsLegacy.spellsFile.update();
        }
    }
}
