package br.com.zonework.keeptoo.service;

import br.com.zonework.keeptoo.app.KeepToo;
import br.com.zonework.keeptoo.checkApplication.CheckApplication;
import br.com.zonework.keeptoo.controller.SlashController;
import br.com.zonework.keeptoo.utils.logger.LogInformation;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class StartApplicationService extends Service<Boolean> {
    private final FXMLLoader loader;
    private Stage stage;
    private KeepToo app;
    private LogInformation logInformation = LogInformation.getLogInformation();


    public StartApplicationService(Stage primaryStage, KeepToo app) throws IOException {
        logInformation.writeInfo("inicializando serviço de check da aplicação");

        loader = new FXMLLoader(getClass().getResource("/views/slash.fxml"));
        configureStage(primaryStage);
        this.app = app;
    }

    private void configureStage(Stage primaryStage) throws IOException {
        logInformation.writeInfo("configurando stage do slash");

        this.stage = primaryStage;
        Parent root = loader.load();

        stage.initStyle(StageStyle.TRANSPARENT);

        Scene scene = new Scene(root);
        scene.setFill(null);

        stage.setScene(scene);
        logInformation.writeInfo("finalizado configuração");

    }

    @Override
    public void start() {
        super.start();
        stage.show();

    }

    @Override
    protected Task<Boolean> createTask() {
        return new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                logInformation.writeInfo("Inicializando task de check");
                SlashController controller = loader.getController();
                return new CheckApplication(controller).start();
            }
        };
    }

    @Override
    protected void succeeded() {
        logInformation.writeInfo("task de check concluída com sucesso");
        stage.close();
        app.startApplication();
    }

    @Override
    protected void failed() {
        stage.close();
        LogInformation.getLogInformation().writeError(new Exception(this.getException()), "erro na task de inicialização");
    }
}
