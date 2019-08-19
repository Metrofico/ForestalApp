package com.teamticpro.forestalapp.game.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.teamticpro.forestalapp.R;
import com.teamticpro.forestalapp.game.GameActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameMain extends Fragment {


    private GameActivity contexto;

    public GameMain() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.contexto = (GameActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View main = inflater.inflate(R.layout.fragment_game_main, container, false);
        TextView nombre=main.findViewById(R.id.nombre);
        TextView h_s=main.findViewById(R.id.high_score);
        SharedPreferences prefs=contexto.getSharedPreferences("puntaje",MODE_PRIVATE);
        String score=prefs.getString("score",null);
        if(score!=null){
            h_s.setText(score);
        }else{
            h_s.setText("0");
        }
        String nombre_score=prefs.getString("nombre",null);
        if(nombre_score!=null){
            nombre.setText(nombre_score);
        }else{
            nombre.setText("Nadie ha jugado aun");
        }
        Button asdsa = main.findViewById(R.id.start_game);
        asdsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = contexto.getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container_game,new Question1());
                transaction.commit();
            }
        });

        return main;
    }

}
