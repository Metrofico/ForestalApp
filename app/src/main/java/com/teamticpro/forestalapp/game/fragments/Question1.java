package com.teamticpro.forestalapp.game.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.teamticpro.forestalapp.R;
import com.teamticpro.forestalapp.game.GameActivity;

import org.w3c.dom.Text;

public class Question1 extends Fragment {


    private GameActivity ga;

    public Question1() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.ga=(GameActivity) context;
    }

    public GameActivity getMainContext(){
        return this.ga;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question1, container, false);
        RadioGroup group = view.findViewById(R.id.radio_group_answers);
        TextView text=view.findViewById(R.id.pregunta_mensaje);
        Button boton=view.findViewById(R.id.next_button);

        Pregunta pregunta=new Pregunta("¿Qué son los desechos sólidos?","Es la basura en general",new String[]{"es La basura en general","Es la basura compuesta de metal","Es la basura organica"});
        Pregunta pregunta2=new Pregunta("¿Una bolsa de plastico aproximadamente cuanto demora en descomponerse?","500 años",new String[]{"20 años","1000 años","500 años"});
        Pregunta pregunta3=new Pregunta("¿Que son los desechos ordinarios?","Aquellos que no requiren un tratamiento especial antes de ser dispuestos",new String[]{"Aquellos que por ser ordinarios no contaminan","Aquellos que no requiren un tratamiento especial antes de ser dispuestos","Aquellos que si requiren un tratamiento especial antes de ser dispuestos"});
        Pregunta pregunta4=new Pregunta("¿Que desechos se decomponen mas rapido?","Los organicos",new String[]{"Los organicos","Los inorganicos"});
        Pregunta pregunta5=new Pregunta("¿En cuanto tiempo aproximadamente un cuarderno se descompone?","1 a 2 meses",new String[]{"3 dias","1 a 3 semanas","1 a 2 meses","6 a 8 meses","1 año"});
        Pregunta pregunta6=new Pregunta("¿Como se clasifican los desechos solidos?","Por su Origen, Composicion y tiempo de descomposicion",new String[]{"Por su tamaño, material y tiempo de descomposicion","Por su olor y tiempo de descomposicion","Por su Origen, Composicion y tiempo de descomposicion"});
        Pregunta pregunta7=new Pregunta("¿El origen de los desechos solidos lo determina el tipo de actividades que las personas realizan?","Si",new String[]{"Si","No"});
        Pregunta pregunta8=new Pregunta("¿Que factores deben estar presentes para crear condiciones optimas de descomposicion?","Oxigeno, luz solar y humedad",new String[]{"Dioxido de carbono y luz solar","Oxigeno, luz solar y humedad","Perioxido de carbono, Oxigeno y Humedad"});
        Pregunta pregunta9=new Pregunta("Los desechos especiales se caracterizan por poder ser...","Toxicos, explosivos o radioactivos",new String[]{"Grandes y robustos","Toxicos, explosivos o radioactivos","Pequqeños y de lenta descomposicion"});
        Pregunta pregunta10=new Pregunta("¿La industrializacion genera como efecto una gran cantidad de desechos solidos?","Si",new String[]{"Si","No"});
        Game game=new Game(group,boton,text,this);
        game.addPregunta(pregunta);
        game.addPregunta(pregunta2);
        game.addPregunta(pregunta3);
        game.addPregunta(pregunta4);
        game.addPregunta(pregunta5);
        game.addPregunta(pregunta6);
        game.addPregunta(pregunta7);
        game.addPregunta(pregunta8);
        game.addPregunta(pregunta9);
        game.addPregunta(pregunta10);
        game.showPregunta();
        return view;
    }

    public void returnToGameMain(){
        FragmentManager manager = ga.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_game,new GameMain());
        transaction.commit();
    }
}
