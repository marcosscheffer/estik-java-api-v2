package com.marcos.estik.solution.domain.exception;

public class SolutionAlreadyResolvedException extends RuntimeException {
    public SolutionAlreadyResolvedException(String message) {
        super(message);
    }
}
