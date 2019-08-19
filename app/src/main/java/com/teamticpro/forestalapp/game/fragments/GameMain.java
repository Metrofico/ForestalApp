package com.teamticpro.forestalapp.game.fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.teamticpro.forestalapp.R;
import com.teamticpro.forestalapp.game.GameActivity;

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
        Button asdsa = main.findViewById(R.id.start_game);
        asdsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return main;
    }

}
