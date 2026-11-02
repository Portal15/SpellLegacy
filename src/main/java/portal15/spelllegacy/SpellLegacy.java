package portal15.spelllegacy;


import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import portal15.spelllegacy.Commands.*;
import portal15.spelllegacy.Files.SpellsFile;
import portal15.spelllegacy.Items.magicWand;
import portal15.spelllegacy.Items.spellsBook;
import portal15.spelllegacy.Listeners.*;
import portal15.spelllegacy.Spells.*;
import portal15.spelllegacy.Utils.Utils;

import java.util.HashMap;
import java.util.UUID;

public final class SpellLegacy extends JavaPlugin {

    public static SpellsFile spellsFile;
    public ProtocolManager protocolManager;

    public static String prefix = Utils.chat("&8[&5SpellLegacy&8] &r");
    public static String[] spells = {"protego","doomto","expelliarmus", "lumos","stupfy", "accio", "defusion", "depulso", "incendio", "revelio", "spuda", "diffindo"};
    public static String[] descriptions = {"Defensive Charm","Defensive Charm","Defensive Charm", "Light Charm", "Stun Charm", "Summoning Charm", "Hell Curse", "Banishing Charm", "Fire-Making Charm", "Revealing Charm", "Dash Charm", "Sword Charm"};
    public static HashMap<String, BaseSpell> spellIndexes = new HashMap<>();

    public static HashMap<UUID,Integer> currentSpellSlot = new HashMap<>();
    @Override
    public void onEnable() {

        // Plugin startup logic
        getConfig().options().copyDefaults(true);
        saveConfig();
        spellsFile = new SpellsFile(this);
        spellsFile.saveDefaultConfig();
        protocolManager = ProtocolLibrary.getProtocolManager();
        System.out.println("SpellLegacy has been enabled!");
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new portal15.spelllegacy.Menus.MenuListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerSwapHandItemListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerItemHeldListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(this), this);

        getCommand("mySpells").setExecutor((CommandExecutor) new mySpells(this));
        getCommand("learn").setExecutor((CommandExecutor) new learn(this));
        getCommand("unlearn").setExecutor((CommandExecutor) new unLearn(this));
        getCommand("selectSpells").setExecutor((CommandExecutor) new selectSpells(this));
        getCommand("adminMenu").setExecutor((CommandExecutor) new adminMenu(this));
        getCommand("test").setExecutor((CommandExecutor) new test(this));
        getCommand("safeReload").setExecutor((CommandExecutor) new safeReload(this));
        //Spells
        spellIndexes.put("protego", new protego(this));
        spellIndexes.put("doomto", new doomto(this));
        spellIndexes.put("lumos", new lumos(this));
        spellIndexes.put("stupfy", new stupfy(this));
        spellIndexes.put("expelliarmus", new expelliarmus(this));
        spellIndexes.put("accio", new accio(this));
        spellIndexes.put("depulso", new depulso(this));
        spellIndexes.put("diffindo", new diffindo(this));
        spellIndexes.put("revelio", new revelio(this));
        // Recipes
        wandRecipe();
        spellsBookRecipe();
    }

    private void wandRecipe(){
        ItemStack wand = magicWand.getItem();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "wand"), wand);
        recipe.shape(" AE", " SA", "G  ");

        recipe.setIngredient('A', Material.AMETHYST_SHARD);
        recipe.setIngredient('E', Material.ENDER_PEARL);
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('G', Material.GOLD_INGOT);
        Bukkit.addRecipe(recipe);
    }
    private void spellsBookRecipe(){
        ItemStack book = spellsBook.getItem();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "book"), book);
        recipe.shape("AGA", "GBG", "AGA");
        recipe.setIngredient('A', Material.AMETHYST_SHARD);
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('B', Material.BOOK);
        Bukkit.addRecipe(recipe);
    }

    public static boolean containsSpell(String[] spells, String spell){
        for (String s : spells){
            if (spell.equalsIgnoreCase(s)) return true;
        }
        return false;
    }
    public static String[] addSpell(String[] spells, String spell){
        String[] newSpells = new String[spells.length + 1];
        for (int i = 0; i < spells.length; i++){
            newSpells[i] = spells[i];
        }
        newSpells[spells.length] = spell;
        return newSpells;
    }
    public static String[] removeSpell(String[] spells, String spell){
        String[] newSpells = new String[spells.length - 1];
        int i = 0;
        for (String s : spells){
            if (s.equalsIgnoreCase(spell)) continue;
            newSpells[i] = s;
            i++;
        }
        return newSpells;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("SpellLegacy has been disabled!");
    }

    @Override
    public void onLoad() {
        protocolManager = ProtocolLibrary.getProtocolManager();
    }
}
