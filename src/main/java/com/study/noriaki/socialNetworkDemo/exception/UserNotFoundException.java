package com.study.noriaki.socialNetworkDemo.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String exception) {
        super(exception);
    }
}