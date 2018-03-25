package br.com.zonework.keeptoo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContractController implements Initializable {
    private @FXML AnchorPane mainAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void goToNewContract(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/views/addContract.fxml"));;
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setOnShowing(onCloseEvent -> this.onClose());
        stage.setOnHiding(onOpenEvent -> this.onOpen());

        stage.show();
    }

    private void onClose() {
        BoxBlur boxBlur = new BoxBlur();
        this.mainAnchorPane.setEffect(boxBlur);
    }

    private void onOpen() {
        this.mainAnchorPane.setEffect(null);
    }
}
