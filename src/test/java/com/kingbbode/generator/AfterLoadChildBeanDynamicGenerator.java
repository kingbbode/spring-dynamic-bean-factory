package com.kingbbode.generator;


import com.kingbbode.adapters.DynamicBeanGeneratorAdapter;
import com.kingbbode.bean.AfterLoadChildBean;

/**
 * Created by YG on 2017-01-11.
 */
public class AfterLoadChildBeanDynamicGenerator extends DynamicBeanGeneratorAdapter<AfterLoadChildBean> {

    @Override
    public boolean isOnlyDirectGenerate() {
        return true;
    }
}
