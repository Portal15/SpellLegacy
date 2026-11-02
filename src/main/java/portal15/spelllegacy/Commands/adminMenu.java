package portal15.spelllegacy.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import portal15.spelllegacy.Menus.GUI.adminGUI;
import portal15.spelllegacy.Menus.GUI.selectSpellsGUI;
import portal15.spelllegacy.SpellLegacy;

public class adminMenu implements CommandExecutor {
    private final SpellLegacy plugin;

    public adminMenu(SpellLegacy plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p){
            if(!(p.hasPermission("spelllegacy.admin"))) {
                p.sendMessage("You do not have permission to use this command.");
                return true;
            }
            p.sendMessage("Loading Admin Menu...");
            new adminGUI(p, plugin).open();
        }
        return  true;
    }
}
