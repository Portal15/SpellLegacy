package portal15.spelllegacy.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import portal15.spelllegacy.SpellLegacy;
import portal15.spelllegacy.Spells.BaseSpell;
import portal15.spelllegacy.Spells.protego;

import java.util.ArrayList;
import java.util.UUID;

import static portal15.spelllegacy.Listeners.EntityDamageByEntityListener.doomtoPlayers;

public class EntityDamageListener implements Listener {
    private final SpellLegacy plugin;
    public EntityDamageListener(SpellLegacy plugin) {
        this.plugin = plugin;
    }
    public static ArrayList<UUID> shieldPlayers = new ArrayList<>();
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player p){
            // for protego spell
            if(shieldPlayers.contains(p.getUniqueId())){
                e.setCancelled(true);
            }
            // for doomto spell
            if (EntityDamageByEntityListener.doomtoPlayers.contains(p.getUniqueId())){
                e.setCancelled(true);
            }
        }
    }
}
