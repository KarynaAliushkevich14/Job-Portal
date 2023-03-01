package com.squarejobs.www.exceptions;

public class CompanyIsAlreadyExistException extends IllegalArgumentException{

    public CompanyIsAlreadyExistException (String message) {
        super(message);
    }
}
