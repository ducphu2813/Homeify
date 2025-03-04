package com.homeify.apigateway.APIGateway.Exceptions;

public class ForbiddenException extends RuntimeException{

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

}
