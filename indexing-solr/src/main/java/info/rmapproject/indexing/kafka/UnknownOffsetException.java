package info.rmapproject.indexing.kafka;

public class UnknownOffsetException extends Exception {

    private static final long serialVersionUID = 1L;

    public UnknownOffsetException(String message) {
        super(message);
    }

    public UnknownOffsetException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownOffsetException(Throwable cause) {
        super(cause);
    }

    public UnknownOffsetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
