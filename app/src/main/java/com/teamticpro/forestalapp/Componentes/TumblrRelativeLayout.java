package com.teamticpro.forestalapp.Componentes;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

import com.teamticpro.forestalapp.R;

import java.util.ArrayList;
import java.util.List;


public class TumblrRelativeLayout extends RelativeLayout {

    private AnimateImageView topImageView;
    private List<AnimateImageView> imageViewList = new ArrayList<>();

    private int[] imageIds = {R.drawable.ic_iconback_2, R.drawable.ic_iconback_3, R.drawable.ic_iconback_1};
    private int[] buttonColors = {R.color.backLayout1, R.color.backLayout2, R.color.backLayout3};
    private int marginBottom = 40;
    private int marginRight = 40;


    private final ViewDragHelper mDragHelper;
    private GestureDetectorCompat gestureDetector;
    private ViewTrackController viewTrackController;

    public TumblrRelativeLayout(Context context) {
        this(context, null);
    }

    public TumblrRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TumblrRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        marginBottom = (int) getResources().getDimension(R.dimen.float_marginBottom);
        marginRight = (int) getResources().getDimension(R.dimen.float_marginRight);
        mDragHelper = ViewDragHelper
                .create(this, 5f, new DragHelperCallback());
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_BOTTOM);
        gestureDetector = new GestureDetectorCompat(context,
                new MoveDetector());

        viewTrackController = ViewTrackController.create();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int len = imageIds.length;
        Resources res = getResources();
        for (int i = 0; i < len; i++) {
            AnimateImageView imageView = new AnimateImageView(getContext());
            imageView.setImageResource(imageIds[i]);
            imageView.setBackgroundTintList(res.getColorStateList(buttonColors[i]));
            imageViewList.add(imageView);
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, marginRight, marginBottom);
             lp.addRule(ALIGN_PARENT_BOTTOM);
             lp.addRule(ALIGN_PARENT_RIGHT);

            addView(imageView, lp);
            if (i == len - 1) {
                topImageView = imageView;
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.setElevation(0);
                }
            }
        }
        viewTrackController.init(imageViewList);
    }

    class MoveDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float dx,
                                float dy) {
            return Math.abs(dy) + Math.abs(dx) > 5;
        }
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public void onViewPositionChanged(View changedView, int left, int top,
                                          int dx, int dy) {
            viewTrackController.onTopViewPosChanged(left, top);
        }

        @Override
        public boolean tryCaptureView(View child, int pointerId) {

            if (child == topImageView) {
                topImageView.stopAnimation();
                return true;
            }

            return false;
        }

        @Override
        public int getViewVerticalDragRange(View child) {

            return 1;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {

            viewTrackController.onRelease();
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {

            return top;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {

            return left;
        }
    }

    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        viewTrackController.setOriginPos(topImageView.getLeft(), topImageView.getTop());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            mDragHelper.abort();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean yScroll = gestureDetector.onTouchEvent(ev);
        boolean shouldIntercept = mDragHelper.shouldInterceptTouchEvent(ev);
        int action = ev.getActionMasked();

        if (action == MotionEvent.ACTION_DOWN) {
            mDragHelper.processTouchEvent(ev);
        }

        return shouldIntercept && yScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        try {
            mDragHelper.processTouchEvent(e);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return true;
    }
    public View getTopImage(){
        return topImageView;
    }
    public void setMenuListener(OnClickListener clickListener) {
        topImageView.setOnClickListener(clickListener);
    }
}
