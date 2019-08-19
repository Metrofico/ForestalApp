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
        View view = inflater.inflate(R.layout.fragment_question1, container, false);
        RadioGroup group = view.findViewById(R.id.radio_group_answers);

        Pregunta pregunta=new Pregunta("¿Qué son los desechos sólidos?","La basura en general",new String[]{"La basura en general","Es la basura compuesta de metal","Es la basura organica"});
        

        return view;
    }







}
