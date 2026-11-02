package portal15.spelllegacy.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import portal15.spelllegacy.SpellLegacy;

import java.util.logging.Level;

public class mySpells implements CommandExecutor {
    private final SpellLegacy plugin;
    public mySpells(SpellLegacy plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(sender instanceof Player p){
            if(SpellLegacy.spellsFile.getConfig().getStringList(p.getUniqueId().toString() + ".spells").isEmpty()){
                p.sendMessage(SpellLegacy.prefix + ChatColor.GRAY + "You have not learned any spells yet!");
                return true;
            }
            p.sendMessage(SpellLegacy.prefix + ChatColor.GRAY + "You have the following spells:");
            int i = 1;
            for (String spell : SpellLegacy.spellsFile.getConfig().getStringList(p.getUniqueId().toString() + ".spells")){
                p.sendMessage(ChatColor.GRAY + String.valueOf(i) + ". " + ChatColor.LIGHT_PURPLE + spell.toUpperCase());
                i++;
            }
        } else{
            if(args.length != 1) plugin.getLogger().log(Level.INFO, "Usage: /me <player>");
            else{
                Player target = plugin.getServer().getPlayer(args[0]);
                if(target == null) plugin.getLogger().log(Level.INFO, "Player not found!");
                else{
                    if(SpellLegacy.spellsFile.getConfig().getStringList(target.getUniqueId().toString() + ".spells").isEmpty()){
                        plugin.getLogger().log(Level.INFO, target.getName() + " have not learned any spells yet!");
                        return true;
                    }
                    plugin.getLogger().log(Level.INFO, target.getName() + " have the following spells:");
                    for (String spell : SpellLegacy.spellsFile.getConfig().getStringList(target.getUniqueId().toString() + ".spells")){
                        sender.sendMessage(spell.toUpperCase() + "\t");
                    }
                }
            }
        }

        return true;
    }
}
