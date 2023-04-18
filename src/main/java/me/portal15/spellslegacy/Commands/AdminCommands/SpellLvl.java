package me.portal15.spellslegacy.Commands.AdminCommands;

import me.portal15.spellslegacy.SpellsLegacy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpellLvl implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 0) { p.sendMessage(ChatColor.RED + "You must to type the name of the spell/maxMana/magicLvl you want to change.");}
            else if (args.length == 1) { p.sendMessage(ChatColor.RED + "You must to put lvl (num)");}
            else if (args.length == 2) {
                int lvl = Integer.valueOf(args[1]);
                if (args[0].equals("magicLvl")) {SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".lvl", lvl);}
                if (args[0].equals("maxMana")) {SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".maxMana", lvl);}
                if (args[0].equals("accio")) {SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".accio", lvl);}
                if (args[0].equals("depulso")) {SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".depulso", lvl);}
                if (args[0].equals("spuda")) {SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".spuda", lvl);}
                SpellsLegacy.spellsFile.update();
            } else if(args.length == 3) {
                Player target = Bukkit.getPlayerExact(args[2]);
                if (target == null) {
                    p.sendMessage(ChatColor.RED + "The Player " + ChatColor.YELLOW + args[2] + ChatColor.RED + "Is Not Online!");
                } else {
                    int lvl = Integer.valueOf(args[1]);
                    if (args[0].equals("lvl")) {SpellsLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".lvl", lvl);}
                    if (args[0].equals("maxMana")) {SpellsLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".maxMana", lvl);}
                    if (args[0].equals("accio")) {SpellsLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".accio", lvl);}
                    if (args[0].equals("depulso")) {SpellsLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".depulso", lvl);}
                    if (args[0].equals("spuda")) {SpellsLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".spuda", lvl);}
                    SpellsLegacy.spellsFile.update();
                }
            }
        }
        return true;
    }
}
