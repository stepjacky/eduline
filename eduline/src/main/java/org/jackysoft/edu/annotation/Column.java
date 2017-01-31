package org.jackysoft.edu.annotation;

import org.mongodb.morphia.annotations.Property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Column {
	//是否主键
    boolean id() default false;
    
    //主键策略   
    IdStrategy idStrategy() default IdStrategy.MANUAL;
      
    //名称
    String label() default "";
    //是否显示在列表中
    boolean list() default true;
    //是否显示在输入中
    boolean input() default true;
    //是否显示查询条件
    boolean query() default false;
    //对应表单控件
    InputType formType() default InputType.INPUT_TEXT;
    String[] data() default {};
    
    AutoValue autoValue() default AutoValue.NONE;
    
    String defaultValue() default "";
} 
