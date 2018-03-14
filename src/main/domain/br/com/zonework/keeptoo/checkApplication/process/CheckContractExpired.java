package br.com.zonework.keeptoo.checkApplication.process;

import br.com.zonework.keeptoo.checkApplication.CheckProcess;
import br.com.zonework.keeptoo.controller.SlashController;
import br.com.zonework.keeptoo.utils.exception.CheckApplicationException;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Vinicius Cavalcanti
 * @version 1.0
 * @since 1.0 12/03/18 project keeptoo
 */
public class CheckContractExpired implements CheckProcess {
    private CheckProcess checkProcess;
    @Override
    public Boolean isComplete(SlashController controller) throws CheckApplicationException {
        controller.showCheckContractExpired();
        return checkProcess.isComplete(controller);
    }

    @Override
    public void setNext(Optional<CheckProcess> checkProcess) {
        checkProcess.ifPresent(process -> this.checkProcess = process);
    }
}
