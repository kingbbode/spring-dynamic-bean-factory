package com.kingbbode.generator;

import com.kingbbode.adapters.DynamicBeanGeneratorAdapter;
import com.kingbbode.annotation.DynamicAutowired;
import com.kingbbode.bean.TestBeanA;
import com.kingbbode.bean.TestBeanB;
import com.kingbbode.bean.TestBeanD;
import com.kingbbode.interfaces.Executable;

/**
 * Created by YG on 2017-01-11.
 */
public class TestBeanADynamicGenerator extends DynamicBeanGeneratorAdapter<TestBeanA> implements Executable {
    
    @DynamicAutowired
    private TestBeanB testBeanB;
    
    @DynamicAutowired
    private TestBeanD testBeanD;
    
    @Override
    public TestBeanA generate() throws Exception {
        return new TestBeanA(testBeanB, testBeanD);
    }

    @Override
    public void execute() throws Exception {
        getBean().execute();
    }
}
