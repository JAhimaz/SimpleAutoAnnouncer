package com.itsjustdsaw.simpleautoannouncer;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class SimpleAutoAnnouncer extends JavaPlugin {

    private List<String> messages = new ArrayList<String>();

    private void loadPlugin(){
        //PlayerJoinMessage playerJoin = new PlayerJoinMessage(this);
        //getServer().getPluginManager().registerEvents(playerJoin, this);
    }

    @Override
    public void onEnable() {
        loadConfig();
        fetchMessages();
        startupPrompt();
    }

    @Override
    public void onDisable() {
        System.out.println("Thanks For Using Simple Auto-Announcer");
    }

    private void fetchMessages(){
        messages = getConfig().getStringList("Messages");
    }

    private void startupPrompt(){
        System.out.println("=======================================================");
        System.out.println("Fetched ");
        if(messages.size() > 0){
            System.out.print(ChatColor.GREEN + "" + messages.size());
        }else{
            System.out.print(ChatColor.RED + "" + messages.size());
        }
        System.out.print(" Messages From The Config");
        System.out.println("=======================================================");
    }


    private void loadConfig(){
        final FileConfiguration config = this.getConfig();
        getConfig().options().copyDefaults(true);
        //Placeholders
        saveDefaultConfig();
    }
}
