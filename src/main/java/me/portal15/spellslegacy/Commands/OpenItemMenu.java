package me.portal15.spellslegacy.Commands;

import me.portal15.spellslegacy.MenuSystem.Menus.AdminMenu;
import me.portal15.spellslegacy.SpellsLegacy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenItemMenu implements CommandExecutor {

    SpellsLegacy plugin;

    public OpenItemMenu(SpellsLegacy plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player p){
            if(p.hasPermission("spellslegacy.admin")){
                new AdminMenu(p, plugin).open();
            }
        }
        return true;
    }
}
