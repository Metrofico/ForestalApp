package com.teamticpro.forestalapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamticpro.forestalapp.Componentes.AnimatedProgressBar;
import com.teamticpro.forestalapp.Objetos.Message;
import com.teamticpro.forestalapp.Task.RunTask;

public class MainActivity extends AppCompatActivity {


    private AnimatedProgressBar progressBar;
    private TextView titulo_view;
    private TextView message_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar_main);
        titulo_view = findViewById(R.id._texto_aleatorio_titulo);
         message_view = findViewById(R.id._texto_aleatorio);
        onLoadMessages();
        setupMessages();
        OverloadMessage();
    }

    public void onLoadMessages(){
        Message.addMessage("CUIDA EL MEDIO AMBIENTE","Asdjkahsjdhasjkdhjkashdjkashdjkhasjkdhasjkhdjka");
        Message.addMessage("CUIDA EL MEDIO AMBIENTE 1","Asdjkahsjdhasjkdhjkashdjkashdjkhasjkdhasjkhdjka");
        Message.addMessage("CUIDA EL MEDIO AMBIENTE 2","Asdjkahsjdhasjkdhjkashdjkashdjkhasjkdhasjkhdjka");
        Message.addMessage("CUIDA EL MEDIO AMBIENTE 3","Asdjkahsjdhasjkdhjkashdjkashdjkhasjkdhasjkhdjka");
        Message.addMessage("CUIDA EL MEDIO AMBIENTE 4","Asdjkahsjdhasjkdhjkashdjkashdjkhasjkdhasjkhdjka");
        Message.addMessage("CUIDA EL MEDIO AMBIENTE 5","Asdjkahsjdhasjkdhjkashdjkashdjkhasjkdhasjkhdjka");
        Message.addMessage("CUIDA EL MEDIO AMBIENTE 6","Asdjkahsjdhasjkdhjkashdjkashdjkhasjkdhasjkhdjka");
    }

    private Runnable OverloadMessage() {
        return new RunTask().addTimerRunnable(new Runnable() {
            @Override
            public void run() {
                if (progressBar.getProgress()>= 100) {
                    progressBar.setProgress(0);
                    Message message = Message.getNextMessageFromLast();
                    titulo_view.setText(message.getTitle());
                    message_view.setText(message.getMessage());
                    return;
                }
                progressBar.setProgress(progressBar.getProgress() + 1);
            }
        }, 200);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void setupMessages(){
        TextView titulo_view = findViewById(R.id._texto_aleatorio_titulo);
        TextView message_view = findViewById(R.id._texto_aleatorio);
        Message m = Message.getRandomMessage();
        titulo_view.setText(m.getTitle());
        message_view.setText(m.getMessage());
    }
}
