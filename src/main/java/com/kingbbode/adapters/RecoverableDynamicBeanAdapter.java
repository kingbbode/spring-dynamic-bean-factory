package com.kingbbode.adapters;

import com.kingbbode.interfaces.Recoverable;

/**
 * Created by YG on 2017-01-11.
 */
public abstract class RecoverableDynamicBeanAdapter implements Recoverable {

    @Override
    public boolean isRecoverable(){
        return getDelaySeconds() != 0;
    }
    
    @Override
    public boolean isNowSchedule(int now){
        return !(getDelaySeconds() == 0 || now % getDelaySeconds() != 0);
    }

    @Override
    public int getDelaySeconds() {
        return 0;
    }
}
