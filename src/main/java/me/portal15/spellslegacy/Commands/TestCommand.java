package me.portal15.spellslegacy.Commands;


import me.portal15.spellslegacy.SpellsLegacy;
import me.portal15.spellslegacy.Utils.Targeter;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class TestCommand implements CommandExecutor {
    private final SpellsLegacy plugin;

    public TestCommand(SpellsLegacy plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player p)) return true;

        Player target = Targeter.getTargetPlayer(p);


        new BukkitRunnable() {
            @Override
            public void run() {
                Location playerLocation = p.getLocation();
                Location targetLocation = target.getLocation();
                double distance = playerLocation.distance(targetLocation);
                if (distance > 2) {
                    Vector toPlayer = playerLocation.toVector().subtract(targetLocation.toVector());
                    toPlayer.normalize();
                    double speed = 1;
                    Vector velocity = toPlayer.multiply(speed);
                    target.setVelocity(velocity);
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 1);


        return true;
    }
}
