package portal15.spelllegacy.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spellcaster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import portal15.spelllegacy.SpellLegacy;

public class PlayerJoinListener implements Listener {
    private final SpellLegacy plugin;
    public PlayerJoinListener(SpellLegacy plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void playerJoinListener(PlayerJoinEvent e){
        SpellLegacy.currentSpellSlot.put(e.getPlayer().getUniqueId(), 0);
        if(!(e.getPlayer().getGameMode().equals(GameMode.CREATIVE) || e.getPlayer().getGameMode().equals(GameMode.SPECTATOR))){
            e.getPlayer().setAllowFlight(false);
        }
    }
}
