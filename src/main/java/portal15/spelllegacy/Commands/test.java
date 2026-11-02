package portal15.spelllegacy.Commands;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;
import portal15.spelllegacy.Items.magicWand;
import portal15.spelllegacy.Listeners.EntityDamageByEntityListener;
import portal15.spelllegacy.Listeners.PlayerInteractListener;
import portal15.spelllegacy.Listeners.PlayerMoveListener;
import portal15.spelllegacy.SpellLegacy;
import portal15.spelllegacy.Spells.BaseSpell;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class test implements CommandExecutor {
    private final SpellLegacy plugin;

    public test(SpellLegacy plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage("Test command");
        Player player = (Player) commandSender;
        Zombie zombie = (Zombie) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
        zombie.setCustomName("Zombie Bodyguard");
        zombie.setCustomNameVisible(true);
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();

        Team team = board.getTeam("playerTeam");
        if (team == null) {
            team = board.registerNewTeam("playerTeam");
        }

        team.addEntry(player.getName());
        team.addEntry(zombie.getUniqueId().toString());
        player.sendMessage("A zombie bodyguard has been summoned to aid you!");


//        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
//
//        // Create a new packet
//        PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.LIGHT_UPDATE);
//        // Set the packet contents
//        packet.getIntegers()
//                .write(0, location.getBlockX() >> 4)  // Chunk X
//                .write(1, location.getBlockZ() >> 4); // Chunk Z
//
//        packet.getBooleans()
//                .write(0, true)  // Trust edges
//                .write(1, false); // Sky light array suppressed
//
//        // Note: The contents of the packet will depend on the type of packet and what you want to do with it
//
//        // Send the packet to the player
//        System.out.println("Packet fields: " + packet.getModifier().getFields());
//        try {
//            protocolManager.sendServerPacket(player, packet);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Create a new packet container for the block change packet
////        PacketContainer packet = manager.createPacket(PacketType.Play.Server.EXPLOSION);
////        p.sendMessage("Test command");
////        packet.getDoubles().write(0, playerLocation.getX());
////        packet.getDoubles().write(1, playerLocation.getY());
////        packet.getDoubles().write(2, playerLocation.getZ()  );
////        packet.getFloat().write(0, 5.0f);
//
//        player.sendMessage("0");
////        try {
////            manager.sendServerPacket(p, packet);
////        } catch (InvocationTargetException e) {
////            throw new RuntimeException(e);
////        }
//        player.sendMessage("1");
            return true;
    }

}
