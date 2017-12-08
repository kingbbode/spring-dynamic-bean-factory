package com.kingbbode.generator;

import com.kingbbode.adapters.DynamicBeanGeneratorAdapter;
import com.kingbbode.bean.RecoverBean;

/**
 * Created by YG on 2017-01-11.
 */
public class RecoverBeanDynamicGenerator extends DynamicBeanGeneratorAdapter<RecoverBean> {
    private boolean isAfter;
    
    @Override
    public RecoverBean generate() throws Exception {
        if(!isAfter){
            isAfter = true;
            throw new NullPointerException();
        }
        return new RecoverBean();
    }

    @Override
    public boolean isRecoverable() {
        return true;
    }

    @Override
    public int getDelaySeconds() {
        return 30;
    }
}
