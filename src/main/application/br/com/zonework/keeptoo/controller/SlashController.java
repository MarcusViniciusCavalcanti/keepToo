package br.com.zonework.keeptoo.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;


public class SlashController {
    private @FXML Label lblLoadFile;
    private @FXML Label lblContractExpired;
    private @FXML Label lblInitialize;

    public void showLoadFile() {
        setAnimation(lblLoadFile);
    }

    public void showCheckContractExpired() {
        try {
            Thread.sleep(1500);
            setAnimation(lblContractExpired);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showInitializeLabel() {
        try {
            Thread.sleep(1500);
            setAnimation(lblInitialize);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setAnimation(Label label) {
        label.setVisible(true);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), label);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
}
