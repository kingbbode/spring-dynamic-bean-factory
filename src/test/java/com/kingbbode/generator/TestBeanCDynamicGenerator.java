package com.kingbbode.generator;

import com.kingbbode.adapters.DynamicBeanGeneratorAdapter;
import com.kingbbode.bean.TestBeanC;

/**
 * Created by YG on 2017-01-11.
 */
public class TestBeanCDynamicGenerator extends DynamicBeanGeneratorAdapter<TestBeanC> {
    @Override
    public TestBeanC generate() throws Exception {
        return new TestBeanC();
    }
}
