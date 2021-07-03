package net.dblsaiko.serialization.de;

public class DeserializerException extends Exception {
    public DeserializerException() {
        super();
    }

    public DeserializerException(String message) {
        super(message);
    }

    public DeserializerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeserializerException(Throwable cause) {
        super(cause);
    }

    protected DeserializerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
