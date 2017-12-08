package com.kingbbode.interfaces;

/**
 * Created by YG on 2017-01-11.
 */
public interface Recoverable {
    boolean isRecoverable();
    boolean isNowSchedule(int now);
    int getDelaySeconds();
}
