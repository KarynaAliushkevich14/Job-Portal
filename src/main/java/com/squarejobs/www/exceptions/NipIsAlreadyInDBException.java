package com.squarejobs.www.exceptions;

public class NipIsAlreadyInDBException extends Exception {
    public NipIsAlreadyInDBException (String message) {
        super(message);
    }
}
