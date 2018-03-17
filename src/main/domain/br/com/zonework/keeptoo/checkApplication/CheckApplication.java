package br.com.zonework.keeptoo.checkApplication;

import br.com.zonework.keeptoo.checkApplication.process.CheckContractExpired;
import br.com.zonework.keeptoo.checkApplication.process.CheckEnvironment;
import br.com.zonework.keeptoo.checkApplication.process.FinisherProcess;
import br.com.zonework.keeptoo.controller.SlashController;
import br.com.zonework.keeptoo.exception.CheckApplicationException;
import br.com.zonework.keeptoo.utils.logger.LogInformation;

import java.util.Optional;

/**
 * @author Vinicius Cavalcanti
 * @version 1.0
 * @since 1.0 11/03/18 project keeptoo
 */
public class CheckApplication {
    private SlashController controller;
    private LogInformation logInformation = LogInformation.getLogInformation();

    public CheckApplication(SlashController controller) {
        logInformation.writeInfo("atribuindo controller");
        this.controller = controller;
    }

    public Boolean start() throws CheckApplicationException {
        logInformation.writeInfo("inicializando de checagem");

        CheckProcess checkEnvironment = new CheckEnvironment();
        CheckProcess searchContractExpired = new CheckContractExpired();
        CheckProcess finish = new FinisherProcess();

        checkEnvironment.setNext(Optional.of(searchContractExpired));
        searchContractExpired.setNext(Optional.of(finish));

        return checkEnvironment.isComplete(controller);
    }
}
