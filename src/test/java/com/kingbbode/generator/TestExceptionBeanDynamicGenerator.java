package com.kingbbode.generator;

import com.kingbbode.adapters.DynamicBeanGeneratorAdapter;
import com.kingbbode.bean.ExceptionBean;

/**
 * Created by YG on 2017-01-11.
 */
public class TestExceptionBeanDynamicGenerator extends DynamicBeanGeneratorAdapter<ExceptionBean> {
    @Override
    public boolean isOnlyDirectGenerate() {
        return true;
    }
}
