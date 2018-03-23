package br.com.zonework.keeptoo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Vinicius Cavalcanti
 * @version 1.0
 * @since 1.0 12/03/18 project keeptoo
 */
public class MainController {

    @FXML
    private Button btnContract, btnDashboard;

    @FXML
    private AnchorPane secondaryPane;

    @FXML
    public void goToContracts(ActionEvent event) throws IOException {
        resetColorsBtns();
        this.secondaryPane.getChildren().add(FXMLLoader.load(getClass().getResource("/views/contractList.fxml")));
        this.btnContract.getStyleClass().add("btnsMenuSelected");
    }

    private void resetColorsBtns() {
        this.btnContract.getStyleClass().remove("btnsMenuSelected");
        this.btnDashboard.getStyleClass().remove("btnsMenuSelected");
    }


}
