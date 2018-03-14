package br.com.zonework.keeptoo.utils.exception;

/**
 * Exception que é lançando quando um erro no logger.
 *
 * @version 1.0
 * @author Vinicius Cavalcanti
 * @since 1.0 11/03/18 project keeptoo
 */
public class LoggerException extends RuntimeException {
    public LoggerException(String msg) {
        super(msg);
    }
}
