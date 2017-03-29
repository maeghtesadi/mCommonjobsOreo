package com.oreo.mcommonjobs.Session;

/**
 * Created by jason on 2017-03-28.
 */

public class ButtonSession {

    private static ButtonSession buttonCtrl = null;
    private String buttonClicked;

    protected ButtonSession() {

    }

    public static ButtonSession getInstance() {
        if (buttonCtrl == null) {
            buttonCtrl = new ButtonSession();
        }
        return buttonCtrl;
    }


    public String getButtonClicked() {
        return buttonClicked;
    }

    public void setButtonClicked(String buttonClicked) {
        this.buttonClicked = buttonClicked;
    }
}
