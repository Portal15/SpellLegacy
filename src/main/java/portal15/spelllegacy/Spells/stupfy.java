package portal15.spelllegacy.Spells;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import portal15.spelllegacy.Listeners.PlayerInteractListener;
import portal15.spelllegacy.Listeners.PlayerMoveListener;
import portal15.spelllegacy.SpellLegacy;

import java.util.HashMap;
import java.util.UUID;

public class stupfy extends BaseSpell{
    private final SpellLegacy plugin;
    public stupfy(SpellLegacy plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @Override
    public String SpellName() {
        return "stupfy";
    }

    private HashMap<UUID,Integer> cooldownPlayers = new HashMap<>();

    @Override
    public void onRightClick(PlayerInteractEvent e, SpellLegacy plugin) {
        // Checks if the player in cooldown.
        if(BaseSpell.isInCooldown(cooldownPlayers, e.getPlayer())) return;
        // Add cooldown to the player.
        setCooldown(cooldownPlayers, e.getPlayer().getUniqueId(), 25);
        // Add shield to the player.
        e.getPlayer().sendMessage(SpellLegacy.prefix + ChatColor.GREEN + "You have casted the " + ChatColor.LIGHT_PURPLE + SpellName() + " spell.");
        // Show Particles to the player.
        Entity entity = BaseSpell.getTargetEntity(e.getPlayer(), 5, Particle.FIREWORKS_SPARK);
        if(entity == null) return;

        e.getPlayer().sendMessage(ChatColor.GRAY + "Stupefyed!");

        if(entity instanceof LivingEntity){
            if(entity instanceof Player target ){
                if(BaseSpell.checkShield(e.getPlayer(), target)) return;
                PlayerMoveListener.freezePlayers.add(target.getUniqueId());
                PlayerInteractListener.cantAttack.add(target.getUniqueId());
                target.sendMessage(ChatColor.GRAY + "You have been Stupefyed!");
                particles(target);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        PlayerMoveListener.freezePlayers.remove(target.getUniqueId());
                        PlayerInteractListener.cantAttack.remove(target.getUniqueId());
                    }
                }.runTaskLater(plugin, 10);
            }else{
                ((LivingEntity) entity).setAI(false);
                particles((LivingEntity) entity);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        ((LivingEntity) entity).setAI(true);
                    }
                }.runTaskLater(plugin, 10);
            }
        }
    }
    private void particles(LivingEntity player){
        particles(player,5,1.5);
    }
    private void particles(LivingEntity player, int amountOfParticles, double time){
        new BukkitRunnable() {
            int counter = 0; // Counter to track the number of times the task has run
            @Override
            public void run() {
                if (counter < time * 20) { // Run the task for 60 ticks (3 seconds)
                    for (int i = 0; i < 360; i += 5) { // Adjust the step for more or less density
                        for (int j = -90; j < 90; j += 5) {
                            double angle1 = i * Math.PI / 180;
                            double angle2 = j * Math.PI / 180;
                            double x = 0.5 * Math.cos(angle1) * Math.cos(angle2);
                            double y = 0.5 * Math.sin(angle2);
                            double z = 0.5 * Math.sin(angle1) * Math.cos(angle2);
                            //Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 10);
                            //player.spawnParticle(Particle.REDSTONE, x, y + 1, z, 1, 1, 1, 1, 1, new Particle.DustOptions(Color.RED, 10));
                            //player.getWorld().spawnParticle(Particle.DRAGON_BREATH, player.getLocation().clone().add(x, y + 1, z), 0);
                            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.YELLOW,0.3F); // Change color to YELLOW
                            player.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().clone().add(x, y + 1, z), amountOfParticles, dustOptions);

                        }
                    }
                    counter += 2; // Increment the counter by 10 each time the task runs
                } else {
                    cancel(); // Cancel the task after 3 seconds
                }
            }
        }.runTaskTimer(plugin, 0, 2);
    }
}
