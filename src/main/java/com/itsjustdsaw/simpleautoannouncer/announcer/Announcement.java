package com.itsjustdsaw.simpleautoannouncer.announcer;

import com.itsjustdsaw.simpleautoannouncer.SimpleAutoAnnouncer;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class Announcement extends BukkitRunnable {
    private List<String> messages;
    private boolean isEnabled;
    private SimpleAutoAnnouncer plugin;
    private int counter;

    public Announcement(SimpleAutoAnnouncer instance, List<String> messages, boolean isEnabled) {
        this.plugin = instance;
        this.messages = messages;
        this.isEnabled = isEnabled;
        this.counter = 0;
    }

    @Override
    public void run() {
        if (!isEnabled) {
            cancel();
            return;
        }

        //Announcer Code
        if(plugin.getConfig().getBoolean("randomised")){
            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("announcer-prefix") + " " +  getRandomString(messages)));
        }else{
            if(counter == (messages.size())){
                counter = 0;
            }
            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("announcer-prefix") + " " +  messages.get(counter)));
            counter++;
        }
    }

    public String getRandomString(List<String> strings) {
        Random rand = new Random();
        String randomElement = strings.get(rand.nextInt(strings.size()));
        return randomElement;
    }
}

