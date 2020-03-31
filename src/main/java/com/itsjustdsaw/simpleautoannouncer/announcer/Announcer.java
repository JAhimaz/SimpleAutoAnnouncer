package com.itsjustdsaw.simpleautoannouncer.announcer;

import com.itsjustdsaw.simpleautoannouncer.SimpleAutoAnnouncer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.server.ServerLoadEvent;

import java.util.List;

public class Announcer{

    private List<String> messages;
    private SimpleAutoAnnouncer plugin;
    private FileConfiguration config;
    private String prefix;
    private int delay;

    public Announcer(SimpleAutoAnnouncer instance, List<String> messageList) {
        this.plugin = instance;
        this.messages = messageList;
        this.config = this.plugin.getConfig();
        prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("announcer-prefix"));
        //Gets the delay and converts it to ticks (20 ticks = 1 second)
        delay = (plugin.getConfig().getInt("message-delay")*20);
    }

    public void startAnnouncer(){
        //First Test Announcement
        Bukkit.getScheduler().runTaskLater (plugin, () -> this.plugin.getServer().broadcastMessage(prefix + " " + ChatColor.GREEN + "Test Announcement (Will Only Run Once On Server Startup"), 120);

        //Check If Randomised Or Ordered
        if(config.getBoolean("randomised")){
        
        }else{

        }
    }

}
