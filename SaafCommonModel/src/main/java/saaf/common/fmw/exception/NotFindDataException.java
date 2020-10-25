package saaf.common.fmw.exception;

public class NotFindDataException extends Exception {
    public NotFindDataException(String string, Throwable throwable, boolean b, boolean b1) {
        super(string, throwable, b, b1);
    }

    public NotFindDataException(Throwable throwable) {
        super(throwable);
    }

    public NotFindDataException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public NotFindDataException(String string) {
        super(string);
    }

    public NotFindDataException() {
        super();
    }
}
