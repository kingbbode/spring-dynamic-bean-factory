package com.kingbbode.interfaces;

/**
 * Created by YG on 2017-01-10.
 */
public interface DynamicGenerator<T> {
    T generate() throws Exception;
    
    boolean isOnlyDirectGenerate();
}
