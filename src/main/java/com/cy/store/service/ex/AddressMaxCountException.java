package com.cy.store.service.ex;

public class AddressMaxCountException extends ServiceException{
    public AddressMaxCountException() {
        super();
    }

    public AddressMaxCountException(String message) {
        super(message);
    }

    public AddressMaxCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressMaxCountException(Throwable cause) {
        super(cause);
    }

    protected AddressMaxCountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
