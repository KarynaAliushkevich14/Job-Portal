package com.squarejobs.www.exceptions;

public class OfferIsAlreadyExistException extends IllegalArgumentException {
    public OfferIsAlreadyExistException (String message) {
        super(message);
    }
}
