package com.teamticpro.forestalapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ogaclejapan.arclayout.ArcLayout;
import com.teamticpro.forestalapp.Componentes.AnimatedProgressBar;
import com.teamticpro.forestalapp.Componentes.AnimatorUtils;
import com.teamticpro.forestalapp.Componentes.Reveal.ClipRevealFrame;
import com.teamticpro.forestalapp.Componentes.TumblrRelativeLayout;
import com.teamticpro.forestalapp.Objetos.Message;
import com.teamticpro.forestalapp.Task.RunTask;
import com.teamticpro.forestalapp.game.GameActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private AnimatedProgressBar progressBar;
    private TextView titulo_view;
    private TextView message_view;
    private Button jugar;
    private TumblrRelativeLayout tumblrRelative;
    private RelativeLayout rootLayout;
    private ClipRevealFrame menuLayout;
    private ArcLayout arcLayout;
    private Button centerItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar_main);
        titulo_view = findViewById(R.id._texto_aleatorio_titulo);
         message_view = findViewById(R.id._texto_aleatorio);
        rootLayout = findViewById(R.id.root_container);
        menuLayout = findViewById(R.id.menu_layout);
        arcLayout = findViewById(R.id.arc_layout);
        centerItem = findViewById(R.id.center_item);
        jugar = findViewById(R.id.jugar_);
        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
        onLoadMessages();
        setupMessages();
        OverloadMessage();
        initViewTumblr();
    }

    private void initViewTumblr() {
        View.OnClickListener menuClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onFabClick(v);
                startActivity(new Intent(MainActivity.this, Information.class));
            }
        };
        tumblrRelative = findViewById(R.id.root_container);
        tumblrRelative.setMenuListener(menuClickListener);
    }

    public void onLoadMessages(){
        Message.addMessage("Sabias que...","Más de 5 millones de personas mueren cada año por beber agua contaminada.");
        Message.addMessage("Sabias que...","El 90% del agua que consume la población mundial procede del agua subterránea, un buen procesamiento de los desechos solidos ayuda a evitar la continumación en las agua subterraneas.");
        Message.addMessage("Sabias que...","El papel puede reciclarse hasta 11 veces.");
        Message.addMessage("Sabias que...","Las baterías de los móviles contienen metales pesados contaminantes para el suelo.");
        Message.addMessage("Sabias que...","Los ordenadores llevan integrado 1,5 kilos de cobre susceptible de ser reciclado.");
        Message.addMessage("Sabias que...","Cada año son vertidos en los océanos 14 mil millones de toneladas de basura.");
        Message.addMessage("Sabias que...","Generamos 21,5 millones de toneladas de residuos alimenticios cada año.");
        Message.addMessage("Sabias que...","Las latas de aluminio se pueden reciclar y volver a poner en el estante de una tienda en tan sólo unos 2 meses.");
        Message.addMessage("Sabias que...","El reciclaje de una botella de vidrio ahorra suficiente energía para alimentar una bombilla de 100 vatios durante cuatro horas.");
        Message.addMessage("Sabias que...","El reciclaje de una tonelada de plástico puede ahorrar hasta 1.000-2.000 litros de gasolina.");
    }

    private void OverloadMessage() {
        new RunTask().addTimerRunnable(new Runnable() {
            @Override
            public void run() {
                if (progressBar.getProgress() >= 100) {
                    progressBar.setProgress(0);
                    Message message = Message.getNextMessageFromLast();
                    titulo_view.setText(message.getTitle());
                    message_view.setText(message.getMessage());
                    return;
                }
                progressBar.setProgress(progressBar.getProgress() + 1);
            }
        }, 200);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void setupMessages(){
        TextView titulo_view = findViewById(R.id._texto_aleatorio_titulo);
        TextView message_view = findViewById(R.id._texto_aleatorio);
        Message m = Message.getRandomMessage();
        titulo_view.setText(m.getTitle());
        message_view.setText(m.getMessage());
    }


  /*
  ANIMACION ARC LAYOUT
   */



    private void onFabClick(View v) {
        int x = (v.getLeft() + v.getRight()) / 2;
        int y = (v.getTop() + v.getBottom()) / 2;
        float radiusOfFab = 1f * v.getWidth() / 2f;
        float radiusFromFabToRoot = (float) Math.hypot(
                Math.max(x, rootLayout.getWidth() - x),
                Math.max(y, rootLayout.getHeight() - y));

        if (v.isSelected()) {
            hideMenu(x, y, radiusFromFabToRoot, radiusOfFab);
        } else {
            showMenu(x, y, radiusOfFab, radiusFromFabToRoot);
        }
        v.setSelected(!v.isSelected());
    }


    private void showMenu(int cx, int cy, float startRadius, float endRadius) {
        menuLayout.setVisibility(View.VISIBLE);
        List<Animator> animList = new ArrayList<>();
        Animator revealAnim = createCircularReveal(menuLayout, cx, cy, startRadius, endRadius);
        revealAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        revealAnim.setDuration(200);
        animList.add(revealAnim);
        animList.add(createShowItemAnimator(centerItem));
        for (int i = 0, len = arcLayout.getChildCount(); i < len; i++) {
            animList.add(createShowItemAnimator(arcLayout.getChildAt(i)));
        }
        AnimatorSet animSet = new AnimatorSet();
        animSet.playSequentially(animList);
        animSet.start();
    }

    private void hideMenu(int cx, int cy, float startRadius, float endRadius) {
        List<Animator> animList = new ArrayList<>();
        for (int i = arcLayout.getChildCount() - 1; i >= 0; i--) {
            animList.add(createHideItemAnimator(arcLayout.getChildAt(i)));
        }
        animList.add(createHideItemAnimator(centerItem));
        Animator revealAnim = createCircularReveal(menuLayout, cx, cy, startRadius, endRadius);
        revealAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        revealAnim.setDuration(200);
        revealAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                menuLayout.setVisibility(View.INVISIBLE);
            }
        });
        animList.add(revealAnim);
        AnimatorSet animSet = new AnimatorSet();
        animSet.playSequentially(animList);
        animSet.start();
    }

    private Animator createShowItemAnimator(View item) {
        float dx = centerItem.getX() - item.getX();
        float dy = centerItem.getY() - item.getY();
        item.setScaleX(0f);
        item.setScaleY(0f);
        item.setTranslationX(dx);
        item.setTranslationY(dy);
        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.scaleX(0f, 1f),
                AnimatorUtils.scaleY(0f, 1f),
                AnimatorUtils.translationX(dx, 0f),
                AnimatorUtils.translationY(dy, 0f)
        );
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(50);
        return anim;
    }

    private Animator createHideItemAnimator(final View item) {
        final float dx = centerItem.getX() - item.getX();
        final float dy = centerItem.getY() - item.getY();
        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.scaleX(1f, 0f),
                AnimatorUtils.scaleY(1f, 0f),
                AnimatorUtils.translationX(0f, dx),
                AnimatorUtils.translationY(0f, dy)
        );
        anim.setInterpolator(new DecelerateInterpolator());
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                item.setTranslationX(0f);
                item.setTranslationY(0f);
            }
        });
        anim.setDuration(50);
        return anim;
    }


    private Animator createCircularReveal(final ClipRevealFrame view, int x, int y, float startRadius,
                                          float endRadius) {
        final Animator reveal;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            reveal = ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, endRadius);
        } else {
            view.setClipOutLines(true);
            view.setClipCenter(x, y);
            reveal = ObjectAnimator.ofFloat(view, "ClipRadius", startRadius, endRadius);
            reveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setClipOutLines(false);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
        return reveal;
    }

}
