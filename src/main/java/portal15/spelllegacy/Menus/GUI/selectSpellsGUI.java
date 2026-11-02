package portal15.spelllegacy.Menus.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import portal15.spelllegacy.Menus.Menu;
import portal15.spelllegacy.SpellLegacy;

import java.util.Objects;

public class selectSpellsGUI extends Menu {
    public selectSpellsGUI(Player p, SpellLegacy plugin) {
        super(p, plugin);
    }

    @Override
    public String getTitle() {
        return "&8Select a spell.";
    }

    @Override
    public int getSlots() {
        return 27;
    }
    Material unLearned = Material.BARRIER;
    Material learned = Material.ENCHANTED_BOOK;

    @Override
    public void handleMenu(InventoryClickEvent e) {
        if(Objects.requireNonNull(Objects.requireNonNull(e.getCurrentItem()).getItemMeta()).getDisplayName().isEmpty()){
            return;
        }
        String[] currentSpells = SpellLegacy.spellsFile.getConfig().getStringList(p.getUniqueId().toString() + ".spells").toArray(new String[0]);
        if(e.getCurrentItem().getType().equals(unLearned)){
            e.getWhoClicked().sendMessage(SpellLegacy.prefix + ChatColor.RED + "You haven't learned this spell yet.");
            return;
        }
        if(!(SpellLegacy.spellsFile.getConfig().getStringList(p.getUniqueId().toString() + ".selectedSpells").get(2).isEmpty())){
            if(Objects.requireNonNull(e.getCurrentItem()).getItemMeta().getDisplayName().contains("Confirm")){
                p.closeInventory();
                return;
            } else{
                e.getWhoClicked().sendMessage(SpellLegacy.prefix + ChatColor.RED + "You have already selected 3 spells.");
                return;

            }
        }
        if(e.getCurrentItem().getType().equals(learned)) {
            for (int i = 0; i < 3; i++) {
                if (SpellLegacy.spellsFile.getConfig().getStringList(p.getUniqueId().toString() + ".selectedSpells").get(i).isEmpty()) {
                    String[] selectedSpells = SpellLegacy.spellsFile.getConfig().getStringList(p.getUniqueId().toString() + ".selectedSpells").toArray(new String[0]);
                    selectedSpells[i] = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().toString().toLowerCase());
                    SpellLegacy.spellsFile.getConfig().set(p.getUniqueId().toString() + ".selectedSpells", selectedSpells);
                    SpellLegacy.spellsFile.update();
                    p.sendMessage(SpellLegacy.prefix + "You have selected " + Objects.requireNonNull(e.getCurrentItem()).getItemMeta().getDisplayName());
                    new selectSpellsGUI(p, plugin).open();
                    return;
                }
            }
        }
    }

    @Override
    public void setMenuItems() {
        for(int i = 0; i < SpellLegacy.spells.length; i++){
            String[] currentSpells = SpellLegacy.spellsFile.getConfig().getStringList(p.getUniqueId().toString() + ".spells").toArray(new String[0]);
            if(SpellLegacy.containsSpell(currentSpells, SpellLegacy.spells[i])){
                createItem(learned, 1, i, ChatColor.LIGHT_PURPLE + SpellLegacy.spells[i].toUpperCase(), SpellLegacy.descriptions[i]);
            } else{
                createItem(unLearned, 1, i, ChatColor.LIGHT_PURPLE + SpellLegacy.spells[i].toUpperCase(), "&cYou haven't learned this spell yet.");
            }
        }
        for(int i = 0; i < 3; i++){
            if(!(SpellLegacy.spellsFile.getConfig().getStringList(p.getUniqueId().toString() + ".selectedSpells").get(i).isEmpty())){
                createItem(Material.LIME_DYE, 1, 21 + i, "&a" + SpellLegacy.spellsFile.getConfig().getStringList(p.getUniqueId().toString() + ".selectedSpells").get(i), "&7Click to unselect this spell.");
            } else{
                createItem(Material.GRAY_DYE, 1, 21 + i, "&7Empty", "&7Click to select a spell.");
            }
        }

        if(!(SpellLegacy.spellsFile.getConfig().getStringList(p.getUniqueId().toString() + ".selectedSpells").get(2).isEmpty())){
            createItem(Material.GREEN_CONCRETE, 1, 26, "&aConfirm", "&7Click to confirm your selection.");
        } else{
            createItem(Material.RED_CONCRETE, 1, 26, "&7Confirm", "&7You must select 3 spells.");
        }
        setFillerGlass();

    }
}
