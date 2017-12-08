package com.kingbbode.adapters;

import com.kingbbode.interfaces.DynamicGenerator;
import org.springframework.util.StringUtils;

import java.lang.reflect.ParameterizedType;

/**
 * Created by YG on 2017-01-10.
 */
public abstract class DynamicBeanGeneratorAdapter<T> extends RecoverableDynamicBeanAdapter implements DynamicGenerator<T> {
    private boolean initialize;
    
    private T bean;

    public void initialize() throws Exception {
        this.bean = generate();
        this.initialize = true;
    }
    
    public T getBean(){
        return bean;
    }

    public boolean isInitialize() {
        return initialize;
    }

    @SuppressWarnings("unchecked")
    public Class<T> getBeanClass(){
        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public String getBeanName(){
        return StringUtils.uncapitalize(getBeanClass().getSimpleName());
    }

    @Override
    public String toString() {
        return getBeanName();
    }

    @Override
    public boolean isOnlyDirectGenerate() {
        return false;
    }

    @Override
    public T generate() throws Exception {
        return getBeanClass().newInstance();
    }
}
