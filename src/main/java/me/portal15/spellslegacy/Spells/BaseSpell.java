package me.portal15.spellslegacy.Spells;

import me.portal15.spellslegacy.SpellsLegacy;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public abstract class BaseSpell {

    protected SpellsLegacy plugin;



    public abstract String getName();

    public abstract String getDescription();

    public abstract Integer getManaCost();

    public abstract void onRightClick(PlayerInteractEvent e, SpellsLegacy plugin);
}
