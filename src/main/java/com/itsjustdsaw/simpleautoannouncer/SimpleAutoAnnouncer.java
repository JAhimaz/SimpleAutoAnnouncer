package com.itsjustdsaw.simpleautoannouncer;

import com.itsjustdsaw.simpleautoannouncer.announcer.Announcer;
import com.itsjustdsaw.simpleautoannouncer.announcer.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class SimpleAutoAnnouncer extends JavaPlugin {

    private List<Message> messages = new ArrayList<Message>();
    private Announcer announcer;

    @Override
    public void onEnable() {
        loadConfig();
        if(getConfig().getBoolean("plugin-enabled")){
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
                @Override
                public void run(){
                    setupAnnouncer();
                    startupPrompt();
                    announcer.startAnnouncer();
                }
            });
        }
    }

    @Override
    public void onDisable() {
        System.out.println("Thanks For Using Simple Auto-Announcer");
    }

    private void setupAnnouncer(){
        for(String key : getConfig().getConfigurationSection("Messages").getKeys(false)){
            messages.add(new Message(key, getConfig().getStringList("Messages." + key)));
        }
        //Create The Announcer Object
        announcer = new Announcer(this, messages);
    }

    private void startupPrompt(){
        System.out.println("=======================================================");
        System.out.print("Fetched ");
        if(messages.size() > 0){
            System.out.print(ChatColor.GREEN + "" + messages.size() + ChatColor.RESET);
        }else{
            System.out.print(ChatColor.RED + "" + messages.size() + ChatColor.RESET);
        }
        System.out.print(" Messages From The Config\n");
        System.out.println("=======================================================");
    }

    private void loadConfig(){
        final FileConfiguration config = this.getConfig();
        getConfig().options().copyDefaults(true);
        //Placeholders
        saveDefaultConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("simpleautoannouncer")){

            //CONSOLE COMMANDS

            if(sender instanceof ConsoleCommandSender){
                if(args.length == 0){
                    System.out.println("Invalid Arguments! Do saa help");
                }else if (args.length == 1){
                    if (args[0].equalsIgnoreCase("reload")) {
                        reloadPlugin();
                        System.out.println(ChatColor.translateAlternateColorCodes('&', getConfig().getString("announcer-prefix")) + " Reloaded The Config!");
                    }else if(args[0].equalsIgnoreCase("toggle")){
                        if(announcer.curr != null){
                            announcer.stopAnnouncer();
                            System.out.println("Announcements Disabled");
                        }else if(announcer.curr == null){
                            announcer.startAnnouncer();
                            System.out.println("Announcements Enabled");
                        }
                    }else if(args[0].equalsIgnoreCase("aliases")){
                        System.out.println(ChatColor.AQUA + "\n==========================================");
                        System.out.println(ChatColor.AQUA + "\nALIASES:");
                        System.out.println(ChatColor.AQUA + "\nSAA | SimpleAutoAnnouncer | Announcer");
                        System.out.println(ChatColor.AQUA + "\n==========================================");
                    } else if (args[0].equalsIgnoreCase("help")) {
                        System.out.println(ChatColor.AQUA + "\n==========================================");
                        System.out.println(ChatColor.AQUA + "/saa start");
                        System.out.println(ChatColor.AQUA + "/saa stop");
                        System.out.println(ChatColor.AQUA + "/saa reload");
                        System.out.println(ChatColor.AQUA + "/saa help");
                        System.out.println(ChatColor.AQUA + "/saa aliases");
                        System.out.println(ChatColor.AQUA + "==========================================\n");
                    }
                }
            }

            //PLAYER COMMANDS

            if(sender instanceof Player){
                Player player = (Player) sender;
                if(args.length == 0){
                    player.sendMessage("Invalid Arguments, Try Doing /saa help");
                }
                else if(args.length == 1){
                    if (args[0].equalsIgnoreCase("reload") && player.hasPermission("saa.reload")) {
                        reloadPlugin();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("announcer-prefix")) + " Reloaded The Config!");
                    }else if(args[0].equalsIgnoreCase("reload") && !(player.hasPermission("saa.reload"))){
                        player.sendMessage(ChatColor.RED + "Sorry, You Don't Have Permission To Access That Command!");
                    }else if(args[0].equalsIgnoreCase("toggle")){
                        if(announcer.curr != null){
                            announcer.stopAnnouncer();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("announcer-prefix")) + " Announcements Disabled");
                        }else if(announcer.curr == null){
                            announcer.startAnnouncer();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("announcer-prefix")) + " Announcements Enabled");
                        }
                    }else if(args[0].equalsIgnoreCase("aliases")){
                        player.sendMessage(ChatColor.AQUA + "\n==========================================");
                        player.sendMessage(ChatColor.AQUA + "\nALIASES:");
                        player.sendMessage(ChatColor.AQUA + "\nSAA | SimpleAutoAnnouncer | Announcer");
                        player.sendMessage(ChatColor.AQUA + "\n==========================================");
                    }else if (args[0].equalsIgnoreCase("help")) {
                        player.sendMessage(ChatColor.AQUA + "\n==========================================\n");
                        player.sendMessage(ChatColor.AQUA + "SimpleAutoAnnouncer v1.0.0 by JAhimaz\n");
                        player.sendMessage(ChatColor.AQUA + "/saa start");
                        player.sendMessage(ChatColor.AQUA + "/saa stop");
                        player.sendMessage(ChatColor.AQUA + "/saa reload");
                        player.sendMessage(ChatColor.AQUA + "/saa help");
                        player.sendMessage(ChatColor.AQUA + "/saa aliases");
                        player.sendMessage(ChatColor.AQUA + "==========================================\n");
                    }
                }else{
                    player.sendMessage("Invalid Command? Do /saa help for Commands");
                }
            }
        }
        return true;
    }

    private void reloadPlugin(){
        if(announcer != null){
            announcer.stopAnnouncer();
            announcer = null;
        }
        reloadConfig();
        if(getConfig().getBoolean("plugin-enabled")){
            setupAnnouncer();
            startupPrompt();
            announcer.startAnnouncer();
        }
    }
}
