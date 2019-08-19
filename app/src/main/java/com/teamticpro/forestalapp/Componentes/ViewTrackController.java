package com.teamticpro.forestalapp.Componentes;

import java.util.List;

class ViewTrackController {
    private List<AnimateImageView> imageViewList;
    private AnimateImageView topView;
    private AnimateImageView topFollowerView;
    private int resetPosX, resetPosY;

    private ViewTrackController() {
    }

    static ViewTrackController create() {
        return new ViewTrackController();
    }

    void init(List<AnimateImageView> imageViewList) {
        this.imageViewList = imageViewList;
        int len = imageViewList.size();
        this.topView = imageViewList.get(len - 1);
        this.topFollowerView = imageViewList.get(len - 2);

        for (int i = 1; i < len; i++) {
            AnimateImageView view1 = imageViewList.get(i - 1);
            AnimateImageView view2 = imageViewList.get(i);
            view2.getSpringX().addListener(view1.getFollowerListenerX());
            view2.getSpringY().addListener(view1.getFollowerListenerY());
        }
    }


    public void onTopViewPosChanged(int xPos, int yPos) {
        topFollowerView.animTo(xPos, yPos);
    }


    public void onRelease() {
        topView.onRelease(resetPosX, resetPosY);
    }


    public void setOriginPos(int xPos, int yPos) {
        resetPosX = xPos;
        resetPosY = yPos;

        int len = imageViewList.size();
        for (int i = 0; i < len; i++) {
            imageViewList.get(i).setCurrentSpringPos(xPos, yPos);
        }
    }
}
