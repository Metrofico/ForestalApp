package com.teamticpro.forestalapp;

import android.os.Bundle;
import android.util.SparseArray;

import androidx.appcompat.app.AppCompatActivity;

import com.teamticpro.forestalapp.Objetos.Message;

public class MainActivity extends AppCompatActivity {

    private SparseArray<Message> mensajes = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLoadMessages(){

    }
}
