package com.teamticpro.forestalapp.Objetos;

public class Message {

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
