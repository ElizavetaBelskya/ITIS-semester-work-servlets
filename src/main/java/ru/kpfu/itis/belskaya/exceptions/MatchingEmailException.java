package ru.kpfu.itis.belskaya.exceptions;

public class MatchingEmailException extends Exception {
    public MatchingEmailException() {
        super();
    }

    public MatchingEmailException(String message) {
        super(message);
    }

    public MatchingEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public MatchingEmailException(Throwable cause) {
        super(cause);
    }

    protected MatchingEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
