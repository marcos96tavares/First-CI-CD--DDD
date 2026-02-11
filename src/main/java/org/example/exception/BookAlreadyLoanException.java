package org.example.exception;

public class BookAlreadyLoanException extends RuntimeException {
    public BookAlreadyLoanException(String message) {
        super(message);
    }
}
