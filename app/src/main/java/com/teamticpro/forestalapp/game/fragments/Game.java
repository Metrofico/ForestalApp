package com.teamticpro.forestalapp.game.fragments;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class Game {

    private ArrayList<Pregunta> pasadas;
    private ArrayList<Pregunta> preguntas;
    private RadioGroup group;
    private int actual;
    private Question1 question;

    public Game(RadioGroup group,Question1 question){
        this.preguntas=new ArrayList<>();
        this.pasadas=new ArrayList<>();
        this.group=group;
        this.question=question;
        actual=0;
    }

    public void addPregunta(Pregunta pregunta){
        this.preguntas.add(pregunta);
    }

    public int getNumberPreguntas(){
        return this.preguntas.size();
    }

    private int getActualPregunta(){
        return actual;
    }

    private void showPregunta(){
        actual=getRandomNumber();

        Pregunta pregunta=this.preguntas.get(actual);


        for(String s:pregunta.getOpciones()){
            RadioButton bb = new RadioButton(this.question.getContext());
            bb.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT));
            bb.setText(s);
            bb.setId(View.generateViewId());
            group.addView(bb);
        }
    }

    private int getRandomNumber(){
        return ((int)Math.random()*this.preguntas.size());
    }



}
