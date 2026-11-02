package portal15.spelllegacy.Spells;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import portal15.spelllegacy.Listeners.EntityDamageByEntityListener;
import portal15.spelllegacy.SpellLegacy;

import java.util.HashMap;
import java.util.UUID;

public class doomto extends BaseSpell{
    private final SpellLegacy plugin;

    public doomto(SpellLegacy plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    private HashMap<UUID,Integer> cooldownPlayers = new HashMap<>();

    @Override
    public String SpellName() {
        return "doomto";
    }

    @Override
    public void onRightClick(PlayerInteractEvent e, SpellLegacy plugin) {
        // Checks if the player in cooldown.
        if(BaseSpell.isInCooldown(cooldownPlayers, e.getPlayer())) return;
        // Add cooldown to the player.
        setCooldown(cooldownPlayers, e.getPlayer().getUniqueId(), 180);
        // Add shield to the player.
        EntityDamageByEntityListener.doomtoPlayers.add(e.getPlayer().getUniqueId());
        e.getPlayer().sendMessage(SpellLegacy.prefix + ChatColor.GREEN + "You have casted the " + ChatColor.LIGHT_PURPLE + SpellName() + " spell.");
        // Show Particles to the player.
        particles(e.getPlayer());
        // Remove shield from the player after 10 seconds.
        new BukkitRunnable(){
            @Override
            public void run() {
                EntityDamageByEntityListener.doomtoPlayers.remove(e.getPlayer().getUniqueId());

            }
        }.runTaskLater(plugin, 200);
    }

    private void particles(Player player){
        particles(player,4,7);
    }
    private void particles(Player player, int amountOfParticles, int time){
        new BukkitRunnable() {
            int counter = 0; // Counter to track the number of times the task has run
            @Override
            public void run() {
                if (counter < time * 20) { // Run the task for 60 ticks (3 seconds)
                    for (int i = 0; i < 360; i += 10) { // Adjust the step for more or less density
                        for (int j = -90; j < 90; j += 10) {
                            double angle1 = i * Math.PI / 180;
                            double angle2 = j * Math.PI / 180;
                            double x = 1 * Math.cos(angle1) * Math.cos(angle2);
                            double y = 1 * Math.sin(angle2);
                            double z = 1 * Math.sin(angle1) * Math.cos(angle2);
                            //Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 10);
                            //player.spawnParticle(Particle.REDSTONE, x, y + 1, z, 1, 1, 1, 1, 1, new Particle.DustOptions(Color.RED, 10));
                            //player.getWorld().spawnParticle(Particle.DRAGON_BREATH, player.getLocation().clone().add(x, y + 1, z), 0);
                            player.getWorld().spawnParticle(Particle.DRAGON_BREATH, player.getLocation().clone().add(x, y + 1, z), 0);

                        }
                    }
                    counter += 5; // Increment the counter by 10 each time the task runs
                } else {
                    cancel(); // Cancel the task after 3 seconds
                }
            }
        }.runTaskTimer(plugin, 0, 5);
    }
}
