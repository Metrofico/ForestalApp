package com.teamticpro.forestalapp.game.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Game {

    private ArrayList<Pregunta> preguntas;
    private RadioGroup group;
    private Button boton;
    private TextView text;
    private Question1 question;
    private int score;
    private int maximo=0;

    public Game(RadioGroup group,Button boton,TextView text,Question1 question){
        this.preguntas=new ArrayList<>();
        this.group=group;
        this.question=question;
        this.boton=boton;
        this.text=text;
        this.score=0;
        this.maximo=0;

    }

    public void addPregunta(Pregunta pregunta){
        this.preguntas.add(pregunta);
    }

    public int getNumberPreguntas(){
        return this.preguntas.size();
    }

    public void showPregunta(){
        if(this.maximo==0){
            this.maximo=this.preguntas.size();
        }
        int actual_res=getRandomNumber();
        final Pregunta pregunta=this.preguntas.get(actual_res);
        this.text.setText(pregunta.getTexto());
        if(this.preguntas.size()==1){
            this.boton.setText("Finalizar");
        }
        this.preguntas.remove(pregunta);
        for(String s:pregunta.getOpciones()){
            RadioButton bb = new RadioButton(this.question.getContext());
            bb.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT));
            bb.setText(s);
            bb.setId(View.generateViewId());
            group.addView(bb);
        }
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean awnser_found=false;
                for(int i=0;i<group.getChildCount();i++){
                    RadioButton tmp=(RadioButton)group.getChildAt(i);
                    if(tmp.isChecked()&&tmp.getText().toString().equals(pregunta.getRespuesta())){
                        System.out.println("Acerto");
                        awnser_found=true;
                        break;
                    }
                }
                if(awnser_found){
                    score+=1;
                    Toast.makeText(question.getMainContext(),"Respuesta correcta",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(question.getMainContext(),"Respuesta INCORRECTA",Toast.LENGTH_LONG).show();
                }
                if(boton.getText().toString().toLowerCase().equals("finalizar")){

                    AlertDialog.Builder dialog=new AlertDialog.Builder(question.getMainContext());
                    dialog.setTitle("El juego ha acabado");
                    dialog.setMessage("Tu puntaje es de: "+score+"/"+maximo);
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SharedPreferences prefs=question.getMainContext().getSharedPreferences("puntaje",MODE_PRIVATE);
                            final SharedPreferences.Editor editor=prefs.edit();
                            final EditText text_nombre=new EditText(question.getMainContext());
                            if(prefs.getString("score",null)==null){
                                AlertDialog.Builder dialog2=new AlertDialog.Builder(question.getMainContext());
                                dialog2.setTitle("¡Nuevo maximo puntaje!");
                                dialog2.setMessage("Ingrese su nombre: ");
                                dialog2.setView(text_nombre);
                                dialog2.setCancelable(false);
                                dialog2.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        editor.putString("nombre",(!text_nombre.getText().toString().equals(""))?text_nombre.getText().toString():"Desconocido");
                                        editor.putString("score",String.valueOf(score));
                                        editor.apply();
                                        question.returnToGameMain();
                                    }
                                });
                                dialog2.show();
                            }else{
                                int lastHighScore=Integer.parseInt(prefs.getString("score",null));
                                if(score>=lastHighScore){
                                    AlertDialog.Builder dialog2=new AlertDialog.Builder(question.getMainContext());
                                    dialog2.setTitle("¡Nuevo maximo puntaje!");
                                    dialog2.setMessage("Ingrese su nombre: ");
                                    dialog2.setView(text_nombre);
                                    dialog2.setCancelable(false);
                                    dialog2.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            editor.putString("nombre",(!text_nombre.getText().toString().equals(""))?text_nombre.getText().toString():"Desconocido");
                                            editor.putString("score",String.valueOf(score));
                                            editor.apply();
                                            question.returnToGameMain();
                                        }
                                    });
                                    dialog2.show();
                                }else{
                                    question.returnToGameMain();
                                }
                            }

                        }
                    });
                    dialog.show();
                }else{
                    group.removeAllViews();
                    showPregunta();
                }

            }
        });
    }

    private int getRandomNumber(){
        return (int)(Math.random()*this.preguntas.size());
    }
}
