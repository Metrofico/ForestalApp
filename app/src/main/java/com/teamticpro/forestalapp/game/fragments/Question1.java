package com.teamticpro.forestalapp.game.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.teamticpro.forestalapp.R;

public class Question1 extends Fragment {




    public Question1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        // LA PREGUNTA
        // LA RESPUESTA
        // LAS RESPUESTAS
        // EL BOTON
        // LA POSICION DE LA PREGUNTA


        //Game.addNewAnswer("¿Que es para ti el videojuego?", "El video juego es facil", "SIGUIENTE");
       // Game.addNewAnswer("¿Que es para ti el videojuego?", "El video juego es facil", "SIGUIENTE");
       // Game.addNewAnswer("¿Que es para ti el videojuego?", "El video juego es facil", "SIGUIENTE");
        //Game.addNewAnswer("¿Que es para ti el videojuego?", "El video juego es facil", "SIGUIENTE");
       // Game.addNewAnswer("¿Que es para ti el videojuego?", "El video juego es facil", "FINALIZAR");
       // Game g = new Game();
        View view = inflater.inflate(R.layout.fragment_question1, container, false);
        RadioGroup group = view.findViewById(R.id.radio_group_answers);
        String[] a = {"RESPUESTA 1"," RESPUESTA 2"};
        for(String g : a){
            RadioButton bb = new RadioButton(getContext());
            bb.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT));
            bb.setText(g);
            bb.setId(View.generateViewId());
            group.addView(bb);
        }
        return view;
    }







}
