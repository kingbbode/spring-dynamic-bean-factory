package com.kingbbode.annotation;

import java.lang.annotation.*;

/**
 * Created by YG on 2017-01-10.
 */
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicAutowired {

}