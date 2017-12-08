package com.kingbbode;

import com.kingbbode.adapters.DynamicBeanGeneratorAdapter;
import com.kingbbode.exception.DynamicBeanExecuteException;
import com.kingbbode.exception.DynamicBeanInitailizeException;
import com.kingbbode.interfaces.Executable;
import com.kingbbode.utils.DynamicBeanGeneratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by YG on 2017-01-10.
 */
@Slf4j
@Component
public class DynamicBeanFactory<T extends DynamicBeanGeneratorAdapter<?>> {


    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    @Autowired
    private Map<String, T> beanGeneratorMap;

    private Map<Class<?>, T> dynamicBeanrelationMap;

    @PostConstruct
    public void init() {
        dynamicBeanrelationMap = new HashMap<>();
        Set<T> initGenerators = new HashSet<>();
        for (T generator : beanGeneratorMap.values()) {
            if (!generator.isOnlyDirectGenerate()) {
                initGenerators.add(generator);
            }
            dynamicBeanrelationMap.put(generator.getBeanClass(), generator);
        }
        generateBeans(initGenerators);
    }

    public void generateBeans() {
        generate(beanGeneratorMap.values());
    }

    public void generateBeans(Collection<T> list) {
        generate(list);
    }

    public void generateBean(Class<?> clazz) throws Exception {
        if (!dynamicBeanrelationMap.containsKey(clazz)) {
            throw new NullPointerException("Not Found Generator");
        }
        generateBean(dynamicBeanrelationMap.get(clazz));
    }

    public void generateBean(T generator) throws Exception {
        if (!isRegistered(generator)) {
            beanFactory.registerSingleton(generator.getBeanName(), instantiateDependencies(generator));
            if(DynamicBeanGeneratorUtils.isExecutableGenerator(generator.getClass())){
                try{
                    ((Executable) generator).execute();
                }catch(Exception e){
                    throw new DynamicBeanExecuteException(e.getMessage());
                }
            }
        }
    }

    public boolean hasNonInitializeBeans() {
        for (DynamicBeanGeneratorAdapter generator : beanGeneratorMap.values()) {
            if (!generator.isInitialize()) {
                return true;
            }
        }
        return false;
    }

    private boolean isRegistered(T generator) {
        return beanFactory.containsBean(generator.getBeanName());
    }

    private void generate(Collection<T> list) {
        for (T generator : list) {
            if (!generator.isInitialize()) {
                try {
                    generateBean(generator);
                } catch (Exception e) {
                    log.error("can't not generateBean dynamic bean : {} - {}", generator.getBeanName(), e.getMessage());
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Object instantiateDependencies(T object) throws Exception {
        if (object.isInitialize()) {
            return object.getBean();
        }

        for (Field field : DynamicBeanGeneratorUtils.getInjectedFields(object.getClass())) {
            if (!dynamicBeanrelationMap.containsKey(field.getType())) {
                throw new NullPointerException("Not Found Dynamic Bean Generator - Class : " + field.getType());
            }
            T generator = dynamicBeanrelationMap.get(field.getType());
            Object bean = instantiateDependencies(generator);
            field.setAccessible(true);            
            field.set(object, bean);
            generateBean(generator);
        }
        try {
            object.initialize();
        } catch (NullPointerException e) {
            throw new DynamicBeanInitailizeException();
        }
        return object.getBean();
    }

    public void recovery(int now) {
        for (T generator : beanGeneratorMap.values()) {
            recovery(generator, now);
        }
    }

    private void recovery(T generator, int now) {
        if (!generator.isRecoverable() || !generator.isNowSchedule(now)) {
            return;
        }

        try {
            if (!isRegistered(generator)) {
                generateBean(generator);
                log.info("Recover Success : {}", generator);
            }
        } catch (Exception e) {
            log.error("Dynamic Bean Recover Failed : {} - {}", generator, e.getMessage());
        }
    }
}
