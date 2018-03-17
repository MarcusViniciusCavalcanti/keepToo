package br.com.zonework.keeptoo.checkApplication;

import br.com.zonework.keeptoo.controller.SlashController;
import br.com.zonework.keeptoo.exception.CheckApplicationException;

import java.util.Optional;

/**
 *
 */
public interface CheckProcess {
    Boolean isComplete(SlashController controller) throws CheckApplicationException;
    void setNext(Optional<CheckProcess> checkProcess);
}
