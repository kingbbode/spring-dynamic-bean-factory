package com.kingbbode;

import com.kingbbode.bean.*;
import com.kingbbode.conf.DynamicBeanGeneratorConfig;
import com.kingbbode.exception.DynamicBeanExecuteException;
import com.kingbbode.exception.DynamicBeanInitailizeException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by YG on 2017-01-11.
 */
public class DynamicBeanGeneratorTest {

    private AnnotationConfigApplicationContext beanFactory;
    
    private DynamicBeanFactory dynamicBeanFactory;

    @Before
    public void setup() throws IllegalAccessException {
        beanFactory =  new AnnotationConfigApplicationContext(DynamicBeanGeneratorConfig.class);
        dynamicBeanFactory = beanFactory.getBean(DynamicBeanFactory.class);
    }

    @Test
    public void 복구_스케줄에_등록된_빈이_복구_스케줄링에서_생성되는가() throws Exception {
        assertFalse(beanFactory.containsBean("recoverBean"));
        dynamicBeanFactory.recovery(86400);
        assertTrue(beanFactory.containsBean("recoverBean"));
    }

    @Test
    public void 연관관계의_모든_Bean이_잘_생성되는가() throws Exception {
        assertTrue(beanFactory.containsBean("testBeanA"));
        assertTrue(beanFactory.containsBean("testBeanB"));
        assertTrue(beanFactory.containsBean("testBeanC"));
        assertTrue(beanFactory.containsBean("testBeanD"));
    }
    
    @Test
    public void Executable_Generator는_execute를_실행하는가(){
        TestBeanA testBeanA = (TestBeanA) beanFactory.getBean("testBeanA");
        assertTrue(testBeanA.isExecute());
    }

    @Test
    public void 즉시생성을_하지않는_Bean은_초기화시_생성되지않는가() throws Exception {
        assertFalse(beanFactory.containsBean("afterLoadBean"));
        assertFalse(beanFactory.containsBean("afterLoadChildBean"));
    }

    @Test
    public void 단일_Bean_생성시_하위_빈까지_잘_생성되는가 () throws Exception {
        assertFalse(beanFactory.containsBean("afterLoadBean"));
        assertFalse(beanFactory.containsBean("afterLoadChildBean"));
        dynamicBeanFactory.generateBean(AfterLoadBean.class);
        assertTrue(beanFactory.containsBean("afterLoadBean"));
        assertTrue(beanFactory.containsBean("afterLoadChildBean"));
    }

    @Test(expected = NullPointerException.class)
    public void Generator가_없는_Bean_생성시_NullPointerException이_발생하는가() throws Exception {
        dynamicBeanFactory.generateBean(DummyBean.class);
    }

    @Test(expected = DynamicBeanInitailizeException.class)
    public void 빈을_생성할_수_없을_때_DynamicBeanInitailizeException이_발생하는가() throws Exception {
        dynamicBeanFactory.generateBean(ExceptionBean.class);
    }
    
    @Test(expected = DynamicBeanExecuteException.class)
    public void Execute_도중_Excetion_발생시_DynamicBeanExecuteException아_발생하는가() throws Exception {
        dynamicBeanFactory.generateBean(ExecuteExceptionBean.class);
    }

    @Test
    public void 한번_생성된_후_다시_생성해도_Bean_중복_Exception_이_발생하지않는가 () throws Exception {
        assertTrue(beanFactory.containsBean("testBeanA"));
        dynamicBeanFactory.generateBean(TestBeanA.class);
        assertTrue(beanFactory.containsBean("testBeanA"));
    }
}

