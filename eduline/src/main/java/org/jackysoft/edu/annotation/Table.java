package org.jackysoft.edu.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mongodb.morphia.annotations.Entity;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Entity
public @interface Table {
    String prefix() default "jq_";
    String label() default "";
    boolean asmenu() default true;
}
