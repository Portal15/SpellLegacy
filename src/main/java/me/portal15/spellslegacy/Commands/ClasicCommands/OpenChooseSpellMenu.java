package me.portal15.spellslegacy.Commands.ClasicCommands;

import me.portal15.spellslegacy.MenuSystem.Menus.ChooseSpellMenu;
import me.portal15.spellslegacy.SpellsLegacy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenChooseSpellMenu implements CommandExecutor {
    private final SpellsLegacy plugin;
    public OpenChooseSpellMenu(SpellsLegacy plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player p){
            plugin.spellType1.put(p.getUniqueId(),null);
            plugin.spellType2.put(p.getUniqueId(),null);
            plugin.spellType3.put(p.getUniqueId(),null);
            new ChooseSpellMenu(p,plugin).open();
        }
        return true;
    }
}
