package com.teamticpro.forestalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamticpro.forestalapp.Componentes.AnimatedProgressBar;
import com.teamticpro.forestalapp.Objetos.Message;
import com.teamticpro.forestalapp.Task.RunTask;
import com.teamticpro.forestalapp.game.GameActivity;

public class MainActivity extends AppCompatActivity {


    private AnimatedProgressBar progressBar;
    private TextView titulo_view;
    private TextView message_view;
    private Button jugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar_main);
        titulo_view = findViewById(R.id._texto_aleatorio_titulo);
         message_view = findViewById(R.id._texto_aleatorio);
        jugar = findViewById(R.id.jugar_);
        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
        onLoadMessages();
        setupMessages();
        OverloadMessage();
    }

    public void onLoadMessages(){
        Message.addMessage("Sabias que...","Más de 5 millones de personas mueren cada año por beber agua contaminada.");
        Message.addMessage("Sabias que...","El 90% del agua que consume la población mundial procede del agua subterránea, un buen procesamiento de los desechos solidos ayuda a evitar la continumación en las agua subterraneas.");
        Message.addMessage("Sabias que...","El papel puede reciclarse hasta 11 veces.");
        Message.addMessage("Sabias que...","Las baterías de los móviles contienen metales pesados contaminantes para el suelo.");
        Message.addMessage("Sabias que...","Los ordenadores llevan integrado 1,5 kilos de cobre susceptible de ser reciclado.");
        Message.addMessage("Sabias que...","Cada año son vertidos en los océanos 14 mil millones de toneladas de basura.");
        Message.addMessage("Sabias que...","Generamos 21,5 millones de toneladas de residuos alimenticios cada año.");
        Message.addMessage("Sabias que...","Las latas de aluminio se pueden reciclar y volver a poner en el estante de una tienda en tan sólo unos 2 meses.");
        Message.addMessage("Sabias que...","El reciclaje de una botella de vidrio ahorra suficiente energía para alimentar una bombilla de 100 vatios durante cuatro horas.");
        Message.addMessage("Sabias que...","El reciclaje de una tonelada de plástico puede ahorrar hasta 1.000-2.000 litros de gasolina.");
    }

    private void OverloadMessage() {
        new RunTask().addTimerRunnable(new Runnable() {
            @Override
            public void run() {
                if (progressBar.getProgress() >= 100) {
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
