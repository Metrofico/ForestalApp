package com.teamticpro.forestalapp.game.fragments;

public class Pregunta {

    private String texto;
    private String respuesta;
    private String[] opciones;

    public Pregunta(String texto,String respuesta,String[] opciones){
        this.texto = texto;
        this.respuesta=respuesta.trim();
        this.opciones=opciones;
        allLower();
    }

    private void allLower() {
        for(int i=0;i<opciones.length;i++){
            opciones[i]=opciones[i].toLowerCase();
        }
    }

    public String getTexto(){
        return this.texto;
    }

    public String getRespuesta(){
        return this.respuesta;
    }

    public String[] getOpciones(){
        return this.opciones;
    }

}
