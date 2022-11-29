package mrstreeet.simplesetspawn;

import mrstreeet.simplesetspawn.commands.MainCommand;
import mrstreeet.simplesetspawn.commands.SetSpawn;
import mrstreeet.simplesetspawn.commands.Spawn;
import mrstreeet.simplesetspawn.events.AlwaysTP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class SimpleSetSpawn extends JavaPlugin {

    PluginDescriptionFile pdffile = getDescription(); // Gets description of the plugin
    public String version = pdffile.getVersion(); // Gets version of the plugin
    public String name = ChatColor.GOLD + "[" + pdffile.getName() + "]"; // Gets name of the plugin


    public String configPath;


    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(name + ChatColor.GREEN + " It's now working correctly!.");
        Bukkit.getConsoleSender().sendMessage(name + ChatColor.GREEN + " Do you like this plugin? Help me with a donation here!: " + ChatColor.WHITE + "ko-fi.com/mrstreeet");

        registerCommand();
        registerEvents();
        saveDefaultConfig();

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
