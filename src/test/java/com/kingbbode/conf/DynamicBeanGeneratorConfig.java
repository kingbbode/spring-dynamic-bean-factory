package com.kingbbode.conf;

import com.kingbbode.DynamicBeanFactory;
import com.kingbbode.generator.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by YG on 2017-01-11.
 */
@Configuration
public class DynamicBeanGeneratorConfig {
    @Bean
    public TestBeanADynamicGenerator testBeanADynamicGenerator() {
        return new TestBeanADynamicGenerator();
    }

    @Bean
    public TestBeanBDynamicGenerator testBeanBDynamicGenerator() {
        return new TestBeanBDynamicGenerator();
    }

    @Bean
    public TestBeanCDynamicGenerator testBeanCDynamicGenerator() {
        return new TestBeanCDynamicGenerator();
    }

    @Bean
    public TestBeanDDynamicGenerator testBeanDDynamicGenerator() {
        return new TestBeanDDynamicGenerator();
    }

    @Bean
    public AfterLoadBeanDynamicGenerator afterLoadBeanBeanDynamicGenerator() {
        return new AfterLoadBeanDynamicGenerator();
    }

    @Bean
    public AfterLoadChildBeanDynamicGenerator afterLoadChildBeanDynamicGenerator() {
        return new AfterLoadChildBeanDynamicGenerator();
    }

    @Bean
    public RecoverBeanDynamicGenerator recoverBeanDynamicGenerator() {
        return new RecoverBeanDynamicGenerator();
    }
    
    @Bean
    public TestExceptionBeanDynamicGenerator testExceptionBeanDynamicGenerator(){
        return new TestExceptionBeanDynamicGenerator();
    }
    
    @Bean
    public TestExecuteExceptionBeanDynamicGenerator testExecuteExceptionBeanDynamicGenerator(){
        return new TestExecuteExceptionBeanDynamicGenerator();
    }
    
    @Bean
    public DynamicBeanFactory dynamicBeanFactory(){
        return new DynamicBeanFactory();
    }
}
