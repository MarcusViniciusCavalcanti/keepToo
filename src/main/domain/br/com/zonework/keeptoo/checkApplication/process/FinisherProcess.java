package br.com.zonework.keeptoo.checkApplication.process;

import br.com.zonework.keeptoo.checkApplication.CheckProcess;
import br.com.zonework.keeptoo.controller.SlashController;
import br.com.zonework.keeptoo.exception.CheckApplicationException;
import br.com.zonework.keeptoo.utils.logger.LogInformation;

import java.util.Optional;

/**
 * @author Vinicius Cavalcanti
 * @version 1.0
 * @since 1.0 12/03/18 project keeptoo
 */
public class FinisherProcess implements CheckProcess {
    @Override
    public Boolean isComplete(SlashController controller) throws CheckApplicationException {
        LogInformation.getLogInformation().writeInfo("Finalizando check inicial da aplicação.");
        controller.showInitializeLabel();
        return true;
    }

    @Override
    public void setNext(Optional<CheckProcess> checkProcess) {
        // not implement
    }
}
