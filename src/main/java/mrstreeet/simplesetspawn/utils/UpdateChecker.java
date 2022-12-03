package mrstreeet.simplesetspawn.utils;

import mrstreeet.simplesetspawn.SimpleSetSpawn;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {
    private SimpleSetSpawn plugin;
    private int resourceId;

    public UpdateChecker(SimpleSetSpawn plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getLatestVersion(Consumer<String> consumer) {


        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            Bukkit.getConsoleSender().sendMessage(plugin.name + ChatColor.YELLOW + " Checking for updates...");
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream();
            Scanner scanner = new Scanner(inputStream)) {
            if (scanner.hasNext()) {
                consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                Bukkit.getConsoleSender().sendMessage(plugin.name + ChatColor.RED + " Update checker it's broken! " + exception.getMessage());
            }
        });
    }
}
