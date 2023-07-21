package exception;

import java.io.Serializable;

public class BadRequestException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 7125181722982428083L;
    public BadRequestException(String message) {
        super(message);
    }
}
