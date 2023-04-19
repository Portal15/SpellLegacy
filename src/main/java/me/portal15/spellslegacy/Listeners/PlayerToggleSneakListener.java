package me.portal15.spellslegacy.Listeners;

import me.portal15.spellslegacy.SpellsLegacy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class PlayerToggleSneakListener implements Listener {
    private final SpellsLegacy plugin;

    public PlayerToggleSneakListener(SpellsLegacy plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent e){
        Player p = e.getPlayer();
        if(plugin.sneak.get(p.getUniqueId())){
           plugin.sneak.put(p.getUniqueId(),false);
        } else {
            plugin.sneak.put(p.getUniqueId(),true);
        }
    }
}
