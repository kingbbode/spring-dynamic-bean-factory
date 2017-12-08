package com.kingbbode.utils;

import com.kingbbode.annotation.DynamicAutowired;
import com.kingbbode.interfaces.Executable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.reflections.ReflectionUtils.getAllFields;
import static org.reflections.ReflectionUtils.withAnnotation;

/**
 * Created by YG on 2017-01-10.
 */
public class DynamicBeanGeneratorUtils {
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Set<Field> getInjectedFields(Class<?> clazz) {
        return getAllFields(clazz, withAnnotation(DynamicAutowired.class));
    }

    public static Class<?> findConcreteClass(Class<?> injectedClazz, Set<Class<?>> preInstanticateBeans) {
        if (!injectedClazz.isInterface()) {
            return injectedClazz;
        }

        for (Class<?> clazz : preInstanticateBeans) {
            Set<Class<?>> interfaces = new HashSet<>(Arrays.asList(clazz.getInterfaces()));
            if (interfaces.contains(injectedClazz)) {
                return clazz;
            }
        }

        throw new IllegalStateException(injectedClazz + "인터페이스를 구현하는 Bean이 존재하지 않는다.");
    }
    
    public static boolean isExecutableGenerator(Class<?> generator){
        for(Class<?> interfaze : generator.getInterfaces()){
            if(interfaze.equals(Executable.class)){
                return true;
            }
        }
        return false;
    }
}
