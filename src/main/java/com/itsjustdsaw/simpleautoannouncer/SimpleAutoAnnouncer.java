package com.itsjustdsaw.simpleautoannouncer;

import com.itsjustdsaw.simpleautoannouncer.announcer.Announcer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class SimpleAutoAnnouncer extends JavaPlugin {

    private List<String> messages = new ArrayList<String>();
    private Announcer announcer;

    private void loadPlugin(){
        //PlayerJoinMessage playerJoin = new PlayerJoinMessage(this);
        //getServer().getPluginManager().registerEvents(playerJoin, this);
    }

    @Override
    public void onEnable() {
        loadConfig();
        setupAnnouncer();
        startupPrompt();
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
            @Override
            public void run(){
                announcer.startAnnouncer();
            }
        });
    }

    @Override
    public void onDisable() {
        System.out.println("Thanks For Using Simple Auto-Announcer");
    }

    private void setupAnnouncer(){
        //Fetch The Messages From Config
        messages = getConfig().getStringList("Messages");
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
}
