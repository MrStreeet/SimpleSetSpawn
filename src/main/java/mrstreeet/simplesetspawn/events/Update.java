package mrstreeet.simplesetspawn.events;

import mrstreeet.simplesetspawn.SimpleSetSpawn;
import mrstreeet.simplesetspawn.utils.Settings;
import mrstreeet.simplesetspawn.utils.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.swing.*;
import java.awt.*;

public class Update implements Listener {

    private SimpleSetSpawn plugin;

    public Update(SimpleSetSpawn plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event)  {
        Player p = event.getPlayer();
        FileConfiguration config = plugin.getConfig();

        String pathtp = "Config.Check-For-Updates";
        if (config.getString(pathtp).equals("true")){
            if (p.hasPermission("simplesetspawn.admin")) {

                new UpdateChecker(SimpleSetSpawn.getPlugin(), 106488).getLatestVersion(version -> {
                    if (!SimpleSetSpawn.getInstance().getDescription().getVersion().equalsIgnoreCase(version)) {
                        p.sendMessage(plugin.name + ChatColor.RED + " You are runinng an outdated version!");
                        p.sendMessage(ChatColor.RED + "Update the version " + ChatColor.WHITE + Settings.VERSION + ChatColor.YELLOW + " -> " + ChatColor.WHITE + version);
                        p.sendMessage(ChatColor.GREEN + "Update it here! " + ChatColor.GRAY + ChatColor.ITALIC + Settings.RESOURCE_LINK);
                    }

                });
            }
        }

    }
}
