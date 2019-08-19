package com.teamticpro.forestalapp.Objetos;

import android.util.SparseArray;

import java.util.Random;

public class Message {

    private static final SparseArray<Message> mensajes = new SparseArray<>();

    public static void addMessage(String title, String message){
        mensajes.put(mensajes.size(), new Message(title, message));
    }
    public static Message getRandomMessage(){
        Random random = new Random();
        int _INDEX_ = random.nextInt(mensajes.size());
        return mensajes.get(_INDEX_);
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
