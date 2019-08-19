package com.teamticpro.forestalapp.game;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.teamticpro.forestalapp.R;
import com.teamticpro.forestalapp.game.fragments.GameMain;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void showRandomFragment(){

    }

}
