package com.aurigaspa.simulators.api.exceptions;

public class SimulatorAPIException extends Exception {

    private static final long serialVersionUID = 1L;

    public SimulatorAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimulatorAPIException(String message) {
        super(message);
    }
}
