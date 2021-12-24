package com.paydirect.pokedex.exception;

/**
 * Custom Exception class
 *
 * @author Stanly
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1397396345295798033L;

    public ResourceNotFoundException() {
        super("Request resource not found. Try with valid name");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
