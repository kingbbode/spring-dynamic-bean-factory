package com.kingbbode.generator;


import com.kingbbode.adapters.DynamicBeanGeneratorAdapter;
import com.kingbbode.annotation.DynamicAutowired;
import com.kingbbode.bean.AfterLoadBean;
import com.kingbbode.bean.AfterLoadChildBean;

/**
 * Created by YG on 2017-01-11.
 */
public class AfterLoadBeanDynamicGenerator extends DynamicBeanGeneratorAdapter<AfterLoadBean> {
    @DynamicAutowired
    private AfterLoadChildBean afterLoadChildBean;

    @Override
    public boolean isOnlyDirectGenerate() {
        return true;
    }
}
