package br.com.zonework.keeptoo.checkApplication.process;

import br.com.zonework.keeptoo.checkApplication.CheckProcess;
import br.com.zonework.keeptoo.controller.SlashController;
import br.com.zonework.keeptoo.utils.exception.CheckApplicationException;
import br.com.zonework.keeptoo.utils.logger.LogInformation;

import java.util.Optional;

/**
 * @author Vinicius Cavalcanti
 * @version 1.0
 * @since 1.0 12/03/18 project keeptoo
 */
public class CheckEnvironment implements CheckProcess {
    private CheckProcess process;
    private LogInformation logInformation;

    @Override
    public Boolean isComplete(SlashController controller) throws CheckApplicationException {
        logInformation = LogInformation.getLogInformation();
        logInformation.writeInfo("Inicializando checagem de ambiente.");
        controller.showLoadFile();

        checkConnectionDatabase();
        checkDatabase();

        return process.isComplete(controller);
    }

    @Override
    public void setNext(Optional<CheckProcess> checkProcess) {
        checkProcess.ifPresent(process -> this.process = process);
    }

    private void checkDatabase() throws CheckApplicationException {
        logInformation.writeInfo("Checando banco de dados");
    }

    private void checkConnectionDatabase() throws CheckApplicationException{
        logInformation.writeInfo("Chequando conexão com banco de dados.");
    }
}
