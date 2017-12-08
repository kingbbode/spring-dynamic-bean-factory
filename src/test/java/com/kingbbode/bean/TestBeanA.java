package com.kingbbode.bean;

/**
 * Created by YG on 2017-01-11.
 */
public class TestBeanA {
    private TestBeanB testBeanB;
    private TestBeanD testBeanD;
    
    private boolean execute;

    public TestBeanA(TestBeanB testBeanB, TestBeanD testBeanD) {
        this.testBeanB = testBeanB;
        this.testBeanD = testBeanD;
    }
    
    public void execute(){
        this.execute = true;
    }

    public boolean isExecute() {
        return execute;
    }

    public TestBeanB getTestBeanB() {
        return testBeanB;
    }

    public TestBeanD getTestBeanD() {
        return testBeanD;
    }
}
