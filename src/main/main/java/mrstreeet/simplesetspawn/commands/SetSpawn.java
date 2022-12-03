package mrstreeet.simplesetspawn.commands;

import mrstreeet.simplesetspawn.SimpleSetSpawn;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class SetSpawn implements CommandExecutor {

    private SimpleSetSpawn plugin;

    public SetSpawn(SimpleSetSpawn plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        Player p = (Player) sender;

        FileConfiguration config = plugin.getConfig();

        if(sender instanceof Player){

            if (p.hasPermission("simplesetspawn.admin")){

                Location location = p.getLocation();
                double x = location.getX();
                double y = location.getY();
                double z = location.getZ();
                String world = location.getWorld().getName();
                float yaw = location.getYaw();
                float pitch = location.getPitch();

                config.set("Spawn.X", x);
                config.set("Spawn.Y", y);
                config.set("Spawn.Z", z);
                config.set("Spawn.Pitch", pitch);
                config.set("Spawn.Yaw", yaw);
                config.set("Spawn.World", world);

                p.sendMessage(plugin.name + ChatColor.GREEN + " Spawnpoint set!");
                plugin.saveConfig();
                return true;

            }else{
                p.sendMessage(plugin.name + ChatColor.RED + " You don't have permission to perform this command!");
            }

        }else{
            sender.sendMessage(plugin.name + ChatColor.RED + " Only players can perform this command!");
        }
        return true;
    }
}