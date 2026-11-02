package portal15.spelllegacy.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitScheduler;
import portal15.spelllegacy.SpellLegacy;

public class safeReload implements CommandExecutor {
    private final SpellLegacy plugin;

    public safeReload(SpellLegacy plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("spelllegacy.reload")) {
            plugin.getServer().getOnlinePlayers().forEach(player -> player.kickPlayer("Server is reloading. Please rejoin in a few seconds."));
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                plugin.getServer().reload();
            }, 6);
        }
        return true;
    }
}
