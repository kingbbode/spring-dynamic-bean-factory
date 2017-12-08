package com.kingbbode.bean;

/**
 * Created by YG on 2017-01-11.
 */
public class TestBeanB {
    private TestBeanC testBeanC;

    public TestBeanB(TestBeanC testBeanC) {
        this.testBeanC = testBeanC;
    }

    public TestBeanC getTestBeanC() {
        return testBeanC;
    }
}
