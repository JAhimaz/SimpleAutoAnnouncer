package com.itsjustdsaw.simpleautoannouncer.announcer;

import com.itsjustdsaw.simpleautoannouncer.SimpleAutoAnnouncer;
import com.itsjustdsaw.simpleautoannouncer.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Announcer {

    private List<Message> messages;
    private SimpleAutoAnnouncer plugin;
    private FileConfiguration config;

    public Announcer(SimpleAutoAnnouncer instance, List<Message> messageList) {
        this.plugin = instance;
        this.messages = messageList;
        this.config = this.plugin.getConfig();
    }

    public void startAnnouncer(){

        //Check If Randomised Or Ordered
        if(config.getBoolean("randomised")){

        }else{

        }
    }

}
