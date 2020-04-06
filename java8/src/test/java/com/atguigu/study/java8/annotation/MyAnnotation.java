package com.atguigu.study.java8.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, TYPE_USE})
@Retention(RetentionPolicy.SOURCE)
@Repeatable(MyAnnotations.class)
public @interface MyAnnotation {
    String value();
}
