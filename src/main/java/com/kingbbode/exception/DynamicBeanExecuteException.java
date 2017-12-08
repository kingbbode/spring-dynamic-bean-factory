package com.kingbbode.exception;

/**
 * Created by YG on 2017-01-13.
 */
public class DynamicBeanExecuteException extends Exception{
    public DynamicBeanExecuteException() {
        super("Dynamic Bean Execute Exception");
    }

    public DynamicBeanExecuteException(String message) {
        super(message);
    }
}
