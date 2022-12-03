package mrstreeet.simplesetspawn.commands;
import mrstreeet.simplesetspawn.SimpleSetSpawn;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

    private SimpleSetSpawn plugin;

    public Spawn(SimpleSetSpawn plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        Player p = (Player) sender;

        FileConfiguration config = plugin.getConfig();

        if(config.contains("Spawn.X")){
            double x = Double.valueOf(config.getString("Spawn.X"));
            double y = Double.valueOf(config.getString("Spawn.Y"));
            double z = Double.valueOf(config.getString("Spawn.Z"));
            float yaw = Float.valueOf(config.getString("Spawn.Yaw"));
            float pitch = Float.valueOf(config.getString("Spawn.Pitch"));
            World world = plugin.getServer().getWorld(config.getString("Spawn.World"));
            Location l = new Location(world, x, y, z, yaw, pitch);
            p.teleport(l);

            return true;

        }else {
            p.sendMessage(ChatColor.RED + "The spawn doesn't exist!");

        }

        return true;
    }
}
