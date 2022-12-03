package mrstreeet.simplesetspawn.events;

import mrstreeet.simplesetspawn.SimpleSetSpawn;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AlwaysTP implements Listener {

    private SimpleSetSpawn plugin;

    public  AlwaysTP(SimpleSetSpawn plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player p = event.getPlayer();
        FileConfiguration config = plugin.getConfig();

        String pathtp = "Config.Teleport-On-Join";
        if (config.getString(pathtp).equals("true")){
            if (config.contains("Spawn.X")){
                double x = Double.valueOf(config.getString("Spawn.X"));
                double y = Double.valueOf(config.getString("Spawn.Y"));
                double z = Double.valueOf(config.getString("Spawn.Z"));
                float yaw = Float.valueOf(config.getString("Spawn.Yaw"));
                float pitch = Float.valueOf(config.getString("Spawn.Pitch"));
                World world = plugin.getServer().getWorld(config.getString("Spawn.World"));

                Location l = new Location(world, x, y, z, yaw, pitch);
                p.teleport(l);
            }else{
                Bukkit.getConsoleSender().sendMessage(plugin.name + ChatColor.RED + " Player cannot be teleported because there's no SpawnPoint setted!");

                if (p.hasPermission("simplesetspawn.admin")){
                    p.sendMessage(plugin.name + ChatColor.RED + " Player cannot be teleported, check terminal for more info.");
                }
            }
        }
    }
}
