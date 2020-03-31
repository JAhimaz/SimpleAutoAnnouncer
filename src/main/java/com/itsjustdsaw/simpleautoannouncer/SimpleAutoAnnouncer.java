package com.itsjustdsaw.simpleautoannouncer;

import com.itsjustdsaw.simpleautoannouncer.message.Message;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class SimpleAutoAnnouncer extends JavaPlugin {

    private List<Message> messages = new ArrayList<Message>();

    private void loadPlugin(){
        //PlayerJoinMessage playerJoin = new PlayerJoinMessage(this);
        //getServer().getPluginManager().registerEvents(playerJoin, this);
    }

    @Override
    public void onEnable() {
        loadConfig();
        checkMessages();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void checkMessages(){
        System.out.println("=======================================================");

        System.out.println("=======================================================");
    }

    public void loadConfig(){
        final FileConfiguration config = this.getConfig();
        getConfig().options().copyDefaults(true);
        //Placeholders
        saveDefaultConfig();
    }
}
