package com.github.tenmao.redismock.exception;

/**
 * Created by Xiaolu on 2015/4/22.
 */
public class WrongValueTypeException extends RuntimeException {

    public WrongValueTypeException() {
        super();
    }

    public WrongValueTypeException(String message) {
        super(message);
    }
}
