package com.ega.exception;

import java.io.Serial;

public class UnauthorizedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4893320765855582206L;

    public UnauthorizedException(String message) {
        super(message);
    }

}
