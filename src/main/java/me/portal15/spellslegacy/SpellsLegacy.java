package me.portal15.spellslegacy;

import me.portal15.spellslegacy.Commands.AdminCommands.ResetPlayer;
import me.portal15.spellslegacy.Commands.AdminCommands.SpellLvl;
import me.portal15.spellslegacy.Commands.ClasicCommands.OpenChooseSpellMenu;
import me.portal15.spellslegacy.Commands.OpenItemMenu;
import me.portal15.spellslegacy.Commands.TestCommand;
import me.portal15.spellslegacy.Files.SpellsFile;
import me.portal15.spellslegacy.Listeners.PlayerInteractListener;
import me.portal15.spellslegacy.Listeners.PlayerJoinListener;
import me.portal15.spellslegacy.Listeners.PlayerSwapHandItemsListener;
import me.portal15.spellslegacy.MenuSystem.MenuListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class SpellsLegacy extends JavaPlugin {
    public HashMap<UUID,String> spellType1 = new HashMap<>();
    public HashMap<UUID,String> spellType2 = new HashMap<>();
    public HashMap<UUID,String> spellType3 = new HashMap<>();

    public HashMap<UUID,Integer> spellSlot = new HashMap<>();
    public HashMap<UUID,Boolean> sneak = new HashMap<>();

    public static SpellsFile spellsFile;

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults(true);
        saveConfig();
        spellsFile = new SpellsFile(this);
        spellsFile.saveDefaultConfig();

        // Register All The Events!
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this),this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this),this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerSwapHandItemsListener(this),this);

        // Register All The Commands
        getCommand("adminItemMenu").setExecutor(new OpenItemMenu(this));
        getCommand("resetplayer").setExecutor(new ResetPlayer(this));
        getCommand("setPlayerDataLvl").setExecutor(new SpellLvl());
        getCommand("selectSpell").setExecutor(new OpenChooseSpellMenu(this));
        getCommand("test").setExecutor(new TestCommand(this));


    }

}
