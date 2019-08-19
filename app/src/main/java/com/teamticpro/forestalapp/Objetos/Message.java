package com.teamticpro.forestalapp.Objetos;

import android.util.SparseArray;

import java.util.Random;

public class Message {

    private static final SparseArray<Message> mensajes = new SparseArray<>();
    private static int lastMessage = 0;
    public static void addMessage(String title, String message){
        mensajes.put(mensajes.size(), new Message(title, message));
    }
    public static Message getRandomMessage(){
        Random random = new Random();
        int _INDEX_ = random.nextInt(mensajes.size());
        lastMessage = _INDEX_;
        return  mensajes.get(_INDEX_);
    }
    public static Message getNextMessageFromLast(){
        if(lastMessage >= mensajes.size()){
            return new Message("Error Internal"," Error internal for administrator");
        }
        lastMessage++;
        if(lastMessage >= mensajes.size()){
            lastMessage = 0;
        }
        return mensajes.get(lastMessage);
    }

    private String title;
    private String message;

    Message(String title, String message){
        this.title = title;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }
}
