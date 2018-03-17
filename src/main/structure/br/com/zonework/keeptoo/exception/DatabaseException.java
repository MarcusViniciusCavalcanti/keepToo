package br.com.zonework.keeptoo.exception;

import org.hibernate.HibernateException;

public class DatabaseException extends HibernateException {
    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }
}
