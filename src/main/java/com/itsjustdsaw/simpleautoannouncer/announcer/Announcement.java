package com.itsjustdsaw.simpleautoannouncer.announcer;

import com.itsjustdsaw.simpleautoannouncer.SimpleAutoAnnouncer;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class Announcement extends BukkitRunnable {
    private List<Message> messages;
    private boolean isEnabled;
    private SimpleAutoAnnouncer plugin;
    private int counter;

    public Announcement(SimpleAutoAnnouncer instance, List<Message> messages, boolean isEnabled) {
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
            Message message = getRandomMessage(messages);
            printMessage(message);
        }else{
            if(counter == (messages.size())){
                counter = 0;
            }
            Message message = messages.get(counter);
            printMessage(message);
            counter++;
        }
    }

    private void printMessage(Message message) {
        List<String> messageSentences = message.getSentences();
        for(String sentence : messageSentences){
            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', sentence));
        }
    }

    public Message getRandomMessage(List<Message> strings) {
        Random rand = new Random();
        Message randomElement = strings.get(rand.nextInt(strings.size()));
        return randomElement;
    }
}

