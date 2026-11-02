package portal15.spelllegacy.Listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import portal15.spelllegacy.Items.magicWand;
import portal15.spelllegacy.SpellLegacy;

import java.net.http.WebSocket;

public class PlayerSwapHandItemListener implements Listener {
    private final SpellLegacy plugin;

    public PlayerSwapHandItemListener(SpellLegacy plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerSwapHandItem(PlayerSwapHandItemsEvent e) {
        if(e.getOffHandItem().equals(magicWand.getItem())){
            e.setCancelled(true);
            int currentSlot = SpellLegacy.currentSpellSlot.getOrDefault(e.getPlayer().getUniqueId(), 0);
            currentSlot = currentSlot + 1;
            if(currentSlot > 2) {
                currentSlot = 0;
            }
            SpellLegacy.currentSpellSlot.put(e.getPlayer().getUniqueId(), currentSlot);
            String currentSpell = SpellLegacy.spellsFile.getConfig().getStringList(e.getPlayer().getUniqueId().toString() + ".selectedSpells").toArray(new String[0])[currentSlot];
            e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Current Spell: " + ChatColor.LIGHT_PURPLE + currentSpell.toUpperCase()));
        }
    }
}
