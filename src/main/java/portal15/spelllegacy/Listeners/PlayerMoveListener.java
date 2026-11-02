package portal15.spelllegacy.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import portal15.spelllegacy.SpellLegacy;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerMoveListener implements Listener {
    private final SpellLegacy plugin;

    public PlayerMoveListener(SpellLegacy plugin) {
        this.plugin = plugin;
    }
    public static ArrayList<UUID> freezePlayers = new ArrayList<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        if((e.getFrom().getX() != e.getTo().getX()) || (e.getFrom().getZ() != e.getTo().getZ()) || (e.getFrom().getY() != e.getTo().getY())) {
            if(freezePlayers.contains(e.getPlayer().getUniqueId())){
                e.setCancelled(true);}
            if(EntityDamageByEntityListener.doomtoPlayers.contains(e.getPlayer().getUniqueId())) {
                e.setCancelled(true);
            }
        }
        return;
    }
}
