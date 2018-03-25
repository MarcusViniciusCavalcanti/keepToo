package br.com.zonework.keeptoo.controller;

import br.com.zonework.keeptoo.utils.logger.LogInformation;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * @author Vinicius Cavalcanti
 * @version 1.0
 * @since 1.0 12/03/18 project keeptoo
 */
public class MainController implements Initializable {
    private @FXML Button btnContract, btnDashboard;
    private @FXML AnchorPane secondaryPane;

    private AnchorPane dashboard, contractList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.createPages();
    }

    @FXML
    public void goToContracts(ActionEvent event) throws IOException {
        resetColorsBtns();
        this.secondaryPane.getChildren().clear();
        this.secondaryPane.getChildren().add(contractList);
        this.btnContract.getStyleClass().add("btnsMenuSelected");
    }

    @FXML
    public void goToDashboard(ActionEvent event) {
        resetColorsBtns();
        this.secondaryPane.getChildren().clear();
        this.secondaryPane.getChildren().add(dashboard);
        this.btnContract.getStyleClass().add("btnsMenuSelected");
    }

    private void resetColorsBtns() {
        this.btnContract.getStyleClass().remove("btnsMenuSelected");
        this.btnDashboard.getStyleClass().remove("btnsMenuSelected");
    }

    private void createPages() {
        try {
            this.dashboard = FXMLLoader.load(getClass().getResource("/views/dashboard.fxml"));
            this.contractList = FXMLLoader.load(getClass().getResource("/views/contractList.fxml"));
        } catch (Exception e) {
            LogInformation.getLogInformation().writeError(e,"criando cache de telas");
        }
    }
}
