package com.kingbbode.generator;

import com.kingbbode.adapters.DynamicBeanGeneratorAdapter;
import com.kingbbode.bean.TestBeanD;

/**
 * Created by YG on 2017-01-11.
 */
public class TestBeanDDynamicGenerator extends DynamicBeanGeneratorAdapter<TestBeanD> {
    @Override
    public TestBeanD generate() throws Exception {
        return new TestBeanD();
    }
}
