package mrstreeet.simplesetspawn.commands;

import mrstreeet.simplesetspawn.SimpleSetSpawn;
import mrstreeet.simplesetspawn.utils.Settings;
import mrstreeet.simplesetspawn.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    private SimpleSetSpawn plugin;

    public MainCommand(SimpleSetSpawn plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(plugin.name + ChatColor.RED + " You can perfom this command only in game!");
            return false;
        }else{
            Player p = (Player) sender;
            if(args.length > 0){
                if (args[0].equalsIgnoreCase("info")){

                    p.sendMessage(plugin.name + ChatColor.GREEN + " You are using SimpleSetSpawn version " + ChatColor.WHITE + plugin.version);

                    new UpdateChecker(SimpleSetSpawn.getPlugin(), 106488).getLatestVersion(version -> {
                        if (!SimpleSetSpawn.getInstance().getDescription().getVersion().equalsIgnoreCase(version)) {
                            p.sendMessage(ChatColor.RED + "You are runinng an outdated version!");
                            p.sendMessage(ChatColor.RED + "Update the version " + ChatColor.WHITE + Settings.VERSION + ChatColor.YELLOW + " -> " + ChatColor.WHITE + version);
                            p.sendMessage(ChatColor.GREEN + "Update it here! " + ChatColor.GRAY + ChatColor.ITALIC + Settings.RESOURCE_LINK);
                        }

                    });

                    return true;

                }else if(args[0].equalsIgnoreCase("reload")){

                    plugin.reloadConfig();
                    p.sendMessage(plugin.name + ChatColor.GREEN + " Plugin reloaded correctly!");
                    return true;

                }else if(args[0].equalsIgnoreCase("donate")) {

                    p.sendMessage(plugin.name + ChatColor.GREEN + " Thanks for using my plugin! Consider donating ");
                    p.sendMessage(ChatColor.GREEN + "here:" + ChatColor.WHITE + " ko-fi.com/mrstreeet");
                    return true;

                }else if(args[0].equalsIgnoreCase("help")){

                    p.sendMessage(plugin.name + ChatColor.GREEN + " These are all the available commands:");
                    p.sendMessage(ChatColor.WHITE + "/simplesetspawn or /sss " + ChatColor.YELLOW + "->" + ChatColor.GREEN + " Main command.");
                    p.sendMessage(ChatColor.WHITE + "/simplesetspawn info " + ChatColor.YELLOW + "->" + ChatColor.GREEN + " Information about the plugin.");
                    p.sendMessage(ChatColor.WHITE + "/simplesetspawn donate " + ChatColor.YELLOW + "->" + ChatColor.GREEN + " To help me with future projects :D.");
                    p.sendMessage(ChatColor.WHITE + "/simplesetspawn reload " + ChatColor.YELLOW + "->" + ChatColor.GREEN + " Reaload config.yml.");
                    p.sendMessage(ChatColor.WHITE + "/setspawn " + ChatColor.YELLOW + "->" + ChatColor.GREEN + " Sets world spawnpoint.");
                    p.sendMessage(ChatColor.WHITE + "/spawn " + ChatColor.YELLOW + "->" + ChatColor.GREEN + " Teleports to spawn point.");


                }else{

                    p.sendMessage(plugin.name + ChatColor.RED + " That command doesn't exist!");
                    return true;

                }
            }else{

                p.sendMessage(plugin.name + ChatColor.GREEN + " Do you need help? Use " );
                p.sendMessage(plugin.name + ChatColor.GREEN + "Use " + ChatColor.WHITE + "/simplesetspawn help" + ChatColor.GREEN + " to see all available commands!" );

            }
        }
        return false;
    }
}
