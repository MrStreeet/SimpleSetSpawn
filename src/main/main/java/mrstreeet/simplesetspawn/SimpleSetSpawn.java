package mrstreeet.simplesetspawn;

import mrstreeet.simplesetspawn.commands.MainCommand;
import mrstreeet.simplesetspawn.commands.SetSpawn;
import mrstreeet.simplesetspawn.commands.Spawn;
import mrstreeet.simplesetspawn.events.AlwaysTP;
import mrstreeet.simplesetspawn.events.Update;
import mrstreeet.simplesetspawn.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class SimpleSetSpawn extends JavaPlugin {

    PluginDescriptionFile pdffile = getDescription(); // Gets description of the plugin
    public String version = pdffile.getVersion(); // Gets version of the plugin
    public String name = ChatColor.GOLD + "[" + pdffile.getName() + "]"; // Gets name of the plugin

    public String configPath;

    public static SimpleSetSpawn plugin;

    private static Plugin instance;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(name + ChatColor.YELLOW + " Plugin it's loading...");

        plugin = this;
        instance = this;

        registerCommand();
        registerEvents();
        saveDefaultConfig();
        updateChecker();

        Bukkit.getConsoleSender().sendMessage(name + ChatColor.GREEN + " It's now working correctly!.");
        Bukkit.getConsoleSender().sendMessage(name + ChatColor.GREEN + " Do you like this plugin? Help me with a donation here!: " + ChatColor.WHITE + "ko-fi.com/mrstreeet");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(name + ChatColor.RED + " Session closed correctly. See you!");
    }

    public void registerCommand(){
        this.getCommand("simplesetspawn").setExecutor(new MainCommand(this));
        this.getCommand("spawn").setExecutor(new Spawn(this));
        this.getCommand("setspawn").setExecutor(new SetSpawn(this));
    }

    public void registerEvents(){
        PluginManager pm =getServer().getPluginManager();
        pm.registerEvents(new AlwaysTP(this), this);
        pm.registerEvents(new Update(this), this);
    }

    public void updateChecker(){
        new UpdateChecker(this, 106488).getLatestVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                Bukkit.getConsoleSender().sendMessage(name + ChatColor.GREEN + " It's up to date (Running version: " + ChatColor.WHITE +  version + ")");
            }else{
                Bukkit.getConsoleSender().sendMessage(name + ChatColor.RED + " You are running an old version of the plugin, please update to the latest version.");
            }
        });
    }

    public static SimpleSetSpawn getPlugin() {
        return plugin;
    }

    public static Plugin getInstance() {
        return instance;
    }

    //Creation of config.yml
    public void registerConfig(){
        File config = new File(getDataFolder(), "config.yml");
        configPath = config.getPath();

        if (!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }
}
