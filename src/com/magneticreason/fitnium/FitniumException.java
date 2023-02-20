package com.magneticreason.fitnium;

public class FitniumException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    public FitniumException() {
    }

    public FitniumException(String message) {
        super(message);
    }

    public FitniumException(String message, Throwable cause) {
        super(message, cause);
    }

    public FitniumException(Throwable cause) {
        super(cause);
    }

    public String getMessage() {
        return getCause() != null ? super.getMessage() + ". Nested exception:"
                + getCause().toString() : super.getMessage();
    }
}