package com.itsjustdsaw.simpleautoannouncer.announcer;

import com.itsjustdsaw.simpleautoannouncer.SimpleAutoAnnouncer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class Announcer{

    private List<Message> messages;
    private SimpleAutoAnnouncer plugin;
    private FileConfiguration config;
    private String prefix;
    private int delay;
    public BukkitTask curr;

    public Announcer(SimpleAutoAnnouncer instance, List<Message> messageList) {
        this.plugin = instance;
        this.messages = messageList;
        this.config = this.plugin.getConfig();
        prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("announcer-prefix"));
        //Gets the delay and converts it to ticks (20 ticks = 1 second)
        delay = (plugin.getConfig().getInt("message-delay")*20);
    }

    public void stopAnnouncer(){
        if(curr != null){
            curr.cancel();
            curr = null;
            return;
        }
        System.out.println("No Announcer Is Running");
    }

    public void startAnnouncer(){
        curr = new Announcement(plugin, messages, config.getBoolean("plugin-enabled")).runTaskTimerAsynchronously(plugin,  0, delay);
    }
}
