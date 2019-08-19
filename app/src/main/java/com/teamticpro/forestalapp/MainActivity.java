package com.teamticpro.forestalapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamticpro.forestalapp.Objetos.Message;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onLoadMessages();
        setupMessages();
    }

    public void onLoadMessages(){
        Message.addMessage("CUIDA EL MENDIO AMBIENTE","Asdjkahsjdhasjkdhjkashdjkashdjkhasjkdhasjkhdjka");
        Message.addMessage("CUIDA EL MENDIO AMBIENTE 1","Asdjkahsjdhasjkdhjkashdjkashdjkhasjkdhasjkhdjka");
        Message.addMessage("CUIDA EL MENDIO AMBIENTE 2","Asdjkahsjdhasjkdhjkashdjkashdjkhasjkdhasjkhdjka");
        Message.addMessage("CUIDA EL MENDIO AMBIENTE 3","Asdjkahsjdhasjkdhjkashdjkashdjkhasjkdhasjkhdjka");
        Message.addMessage("CUIDA EL MENDIO AMBIENTE 4","Asdjkahsjdhasjkdhjkashdjkashdjkhasjkdhasjkhdjka");
        Message.addMessage("CUIDA EL MENDIO AMBIENTE 5","Asdjkahsjdhasjkdhjkashdjkashdjkhasjkdhasjkhdjka");
        Message.addMessage("CUIDA EL MENDIO AMBIENTE 6","Asdjkahsjdhasjkdhjkashdjkashdjkhasjkdhasjkhdjka");
    }

    public void setupMessages(){
        TextView titulo_view = findViewById(R.id._texto_aleatorio_titulo);
        TextView message_view = findViewById(R.id._texto_aleatorio);
        Message m = Message.getRandomMessage();
        titulo_view.setText(m.getTitle());
        message_view.setText(m.getMessage());
    }
}
