package portal15.spelllegacy.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spellcaster;
import portal15.spelllegacy.SpellLegacy;

public class unLearn implements CommandExecutor {
    private final SpellLegacy plugin;

    public unLearn(SpellLegacy plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p){
            if(!(p.hasPermission("spelllegacy.unlearn"))){
                p.sendMessage("You do not have permission to use this command.");
                return true;
            }
            if(strings.length == 1){
                // unLearn to himself. - BY PLAYER
                if(!(SpellLegacy.containsSpell(SpellLegacy.spells, strings[0]))){
                    p.sendMessage(SpellLegacy.prefix + ChatColor.RED + "The spell \"" + strings[0] + "\" does not exist.");
                    return true;
                }
                String[] currentLearnedSpells = SpellLegacy.spellsFile.getConfig().getStringList(p.getUniqueId().toString() + ".spells").toArray(new String[0]);
                if(!SpellLegacy.containsSpell(currentLearnedSpells, strings[0])){
                    p.sendMessage(SpellLegacy.prefix + ChatColor.RED + "You have not learned the spell " + ChatColor.LIGHT_PURPLE + strings[0].toUpperCase() + ChatColor.RED + ".");
                    return true;
                }
                SpellLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".spells", SpellLegacy.removeSpell(currentLearnedSpells, strings[0]));
                SpellLegacy.spellsFile.update();
                p.sendMessage(SpellLegacy.prefix + ChatColor.GRAY + "You have unlearned the spell " + ChatColor.LIGHT_PURPLE + strings[0].toUpperCase() + ChatColor.GRAY + ".");
            }else if(strings.length == 2){
                // unLearn spell for someone else. - BY PLAYER
                if(!(SpellLegacy.containsSpell(SpellLegacy.spells, strings[0]))){
                    p.sendMessage(SpellLegacy.prefix + ChatColor.RED + "The spell \"" + strings[0] + "\" does not exist.");
                    return true;
                }
                Player target = plugin.getServer().getPlayer(strings[1]);
                if(target == null){
                    p.sendMessage(SpellLegacy.prefix + ChatColor.RED + "Player not found!");
                    return true;
                }
                String[] currentLearnedSpells = SpellLegacy.spellsFile.getConfig().getStringList(target.getUniqueId().toString() + ".spells").toArray(new String[0]);
                if(!SpellLegacy.containsSpell(currentLearnedSpells, strings[0])){
                    p.sendMessage(SpellLegacy.prefix + ChatColor.RED + target.getName() + " has not learned the spell " + ChatColor.LIGHT_PURPLE + strings[0].toUpperCase() + ChatColor.RED + ".");
                    return true;
                }
                SpellLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".spells", SpellLegacy.removeSpell(currentLearnedSpells, strings[0]));
                SpellLegacy.spellsFile.update();
                p.sendMessage(SpellLegacy.prefix + ChatColor.GRAY + target.getName() + " has unlearned the spell " + ChatColor.LIGHT_PURPLE + strings[0].toUpperCase() + ChatColor.GRAY + ".");
                return true;
            }else{
                p.sendMessage("Usage: /unlearn <Spell> [Player]");
                return true;
            }
        }else{
            if(strings.length != 2){
                plugin.getLogger().info("Usage: /unlearn <Spell> <Player>");
                return true;
            }
            if(!(SpellLegacy.containsSpell(SpellLegacy.spells, strings[0]))){
                plugin.getLogger().info("The spell \"" + strings[0] + "\" does not exist.");
                return true;
            }
            Player target = plugin.getServer().getPlayer(strings[1]);
            if(target == null){
                plugin.getLogger().info("Player not found!");
                return true;
            }
            String[] currentLearnedSpells = SpellLegacy.spellsFile.getConfig().getStringList(target.getUniqueId().toString() + ".spells").toArray(new String[0]);
            if(!SpellLegacy.containsSpell(currentLearnedSpells, strings[0])){
                plugin.getLogger().info(target.getName() + " has not learned the spell " + strings[0].toUpperCase() + ".");
                return true;
            }
            SpellLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".spells", SpellLegacy.removeSpell(currentLearnedSpells, strings[0]));
            SpellLegacy.spellsFile.update();
            plugin.getLogger().info(target.getName() + " has unlearned the spell " + strings[0].toUpperCase() + ".");

        }
        return true;
    }
}
