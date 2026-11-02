package portal15.spelllegacy.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import portal15.spelllegacy.Menus.GUI.selectSpellsGUI;
import portal15.spelllegacy.SpellLegacy;

import java.util.logging.Level;

public class selectSpells implements CommandExecutor {
    private final SpellLegacy plugin;

    public selectSpells(SpellLegacy plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p){
            if(strings.length == 3){
                for(int i = 0; i < 3; i++) {
                    if (!(SpellLegacy.containsSpell(SpellLegacy.spells, strings[i]))) {
                        p.sendMessage(SpellLegacy.prefix + ChatColor.RED + "The spell \"" + strings[i] + "\" does not exist.");
                        return true;
                    }
                }
                String[] currentLearnedSpells = SpellLegacy.spellsFile.getConfig().getStringList(p.getUniqueId().toString() + ".spells").toArray(new String[0]);
                for(int i = 0; i < 3; i++) {
                    if(!(SpellLegacy.containsSpell(currentLearnedSpells, strings[i]))){
                        p.sendMessage(SpellLegacy.prefix + ChatColor.RED + "You have not learned the spell " + ChatColor.LIGHT_PURPLE + strings[i].toUpperCase() + ChatColor.RED + " yet.");
                        return true;
                    }
                }
                SpellLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".selectedSpells", strings);
                SpellLegacy.spellsFile.update();
                p.sendMessage(SpellLegacy.prefix + ChatColor.GRAY + "You have selected the following spells:");
                for(int i = 0; i < 3; i++){
                    p.sendMessage(ChatColor.LIGHT_PURPLE + strings[i].toUpperCase());
                }
            }else{
                String[] clearSelectedSpells = {"", "", ""};
                String[] currentSelected = SpellLegacy.spellsFile.getConfig().getStringList(p.getUniqueId().toString() + ".selectedSpells").toArray(new String[0]);
                SpellLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".selectedSpells", clearSelectedSpells);
                SpellLegacy.spellsFile.update();
                new selectSpellsGUI(p, plugin).open();
            }
        }else{
            plugin.getLogger().log(Level.INFO, "The console cannot use this command.");
        }
        return true;
    }
}
