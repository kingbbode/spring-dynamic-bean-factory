package com.kingbbode.exception;

/**
 * Created by YG on 2017-01-11.
 */
public class DynamicBeanInitailizeException extends Exception{
    public DynamicBeanInitailizeException() {
        super("Dynamic Bean Initailize Exception");
    }

    public DynamicBeanInitailizeException(String message) {
        super(message);
    }
}
