package com.teamticpro.forestalapp.Componentes;

import android.content.Context;
import android.util.AttributeSet;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class AnimateImageView extends FloatingActionButton {
    private Spring springX, springY;
    private SimpleSpringListener followerListenerX, followerListenerY;

    public AnimateImageView(Context context) {
        this(context, null);
        this.setScaleX(1.3f);
        this.setScaleY(1.3f);
    }

    public AnimateImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimateImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        SpringSystem mSpringSystem = SpringSystem.create();

        springX = mSpringSystem.createSpring();
        springY = mSpringSystem.createSpring();

        springX.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                int xPos = (int) spring.getCurrentValue();
                setScreenX(xPos);
            }
        });

        springY.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                int yPos = (int) spring.getCurrentValue();
                setScreenY(yPos);
            }
        });

        followerListenerX = new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                int xPos = (int) spring.getCurrentValue();
                springX.setEndValue(xPos);
            }
        };

        followerListenerY = new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                int yPos = (int) spring.getCurrentValue();
                springY.setEndValue(yPos);
            }
        };
    }

    private void setScreenX(int screenX) {
        this.offsetLeftAndRight(screenX - getLeft());
    }

    private void setScreenY(int screenY) {
        this.offsetTopAndBottom(screenY - getTop());
    }

    public void animTo(int xPos, int yPos) {
        springX.setEndValue(xPos);
        springY.setEndValue(yPos);
    }


    public void stopAnimation() {
        springX.setAtRest();
        springY.setAtRest();
    }


    public void onRelease(int xPos, int yPos) {
        setCurrentSpringPos(getLeft(), getTop());
        animTo(xPos, yPos);
    }

    public void setCurrentSpringPos(int xPos, int yPos) {
        springX.setCurrentValue(xPos);
        springY.setCurrentValue(yPos);
    }

    public Spring getSpringX() {
        return springX;
    }

    public Spring getSpringY() {
        return springY;
    }

    public SimpleSpringListener getFollowerListenerX() {
        return followerListenerX;
    }

    public SimpleSpringListener getFollowerListenerY() {
        return followerListenerY;
    }
}
