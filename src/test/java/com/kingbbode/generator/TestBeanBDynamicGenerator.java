package com.kingbbode.generator;

import com.kingbbode.adapters.DynamicBeanGeneratorAdapter;
import com.kingbbode.annotation.DynamicAutowired;
import com.kingbbode.bean.TestBeanB;
import com.kingbbode.bean.TestBeanC;

/**
 * Created by YG on 2017-01-11.
 */
public class TestBeanBDynamicGenerator extends DynamicBeanGeneratorAdapter<TestBeanB> {

    @DynamicAutowired
    private TestBeanC testBeanC;

    @Override
    public TestBeanB generate() throws Exception {
        return new TestBeanB(testBeanC);
    }
}
