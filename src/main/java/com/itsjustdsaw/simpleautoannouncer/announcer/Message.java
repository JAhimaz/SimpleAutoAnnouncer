package com.itsjustdsaw.simpleautoannouncer.announcer;

import java.util.List;

public class Message {

    private List<String> sentences;
    private String name;

    public Message(String messageName, List<String> lines){
        this.name = messageName;
        this.sentences = lines;
    }

    public List<String> getSentences() {
        return sentences;
    }

    public String getName() {
        return name;
    }
}
