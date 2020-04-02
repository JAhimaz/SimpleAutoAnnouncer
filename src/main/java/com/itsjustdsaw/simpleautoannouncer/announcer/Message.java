package com.itsjustdsaw.simpleautoannouncer.announcer;

import java.util.List;

public class Message {

    private List<String> sentences;

    public void Message(List<String> lines){
        this.sentences = lines;
    }

    public List<String> getSentences() {
        return sentences;
    }
}
