package me.portal15.spellslegacy.Commands.AdminCommands;

import me.portal15.spellslegacy.SpellsLegacy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetPlayer implements CommandExecutor {
    private final SpellsLegacy plugin;

    public ResetPlayer(SpellsLegacy plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // CPSL = Config Player Spell List
        if (sender instanceof Player p) {
            if (p.hasPermission("sl.reset")) {
                if (args.length == 0) {
                    SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".lvl", 1);
                    SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".maxMana", 100);
                    SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".accio", 0);
                    SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".depulso", 0);
                    SpellsLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".spuda", 0);
                    SpellsLegacy.spellsFile.update();
                } else {
                    if (Bukkit.getPlayerExact(args[0]) == null) {
                        p.sendMessage(ChatColor.RED + "The Player " + ChatColor.YELLOW + args[0] + ChatColor.RED + "Not Online!");
                    } else {
                        Player target = Bukkit.getPlayerExact(args[0]);
                            SpellsLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".lvl", 1);
                        SpellsLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".maxMana", 100);
                        SpellsLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".accio", 0);
                        SpellsLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".depulso", 0);
                        SpellsLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".spuda", 0);
                        SpellsLegacy.spellsFile.update();
                    }
                }

            }
        } else {
            if (!(Bukkit.getPlayerExact(args[0]) == null)) {
                Player target = Bukkit.getPlayerExact(args[0]);
                SpellsLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".lvl", 1);
                SpellsLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".maxMana", 100);
                SpellsLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".accio", 0);
                SpellsLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".depulso", 0);
                SpellsLegacy.spellsFile.getConfig().set(target.getUniqueId().toString() + ".spuda", 0);
                SpellsLegacy.spellsFile.update();
            }
        }
        return true;
    }
}