package br.com.zonework.keeptoo.app;

import br.com.zonework.keeptoo.service.StartApplicationService;
import br.com.zonework.keeptoo.utils.logger.LogInformation;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * @author Vinicius Cavalcanti
 * @version 1.0
 * @since 1.0 12/03/18 project keeptoo
 */
public class KeepToo extends Application {
    private Stage primaryStage;


    public static void main(String[] args) {
        LogInformation logInformation = LogInformation.getLogInformation(args);
        logInformation.writeInfo("iniciando keeptoo");

        launch(args);
    }

    public EventHandler<WindowEvent> onCloseEvent() {
        return t -> {
            Platform.exit();
            System.exit( 0 );
        };
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        LogInformation logInformation = LogInformation.getLogInformation();

        try {
            StartApplicationService startApplicationService = new StartApplicationService(primaryStage, this);
            startApplicationService.start();
        } catch (IOException e) {
            logInformation.writeError(e, "carregando tela slash.fxml");
        }
    }

    public void startApplication() {
        configurePrimaryStage();
    }

    private void configurePrimaryStage() {
        this.primaryStage = new Stage();

        try {
            Parent main = FXMLLoader.load(getClass().getResource("/views/main.fxml"));
            Scene scene = new Scene(main);

            primaryStage.setScene(scene);
            primaryStage.show();

            primaryStage.setMinHeight(720);
            primaryStage.setMinWidth(1280);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
