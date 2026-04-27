package com.hostmint.app.aop.audit;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Audit {
    String action();

    String entity();

    String level() default "INFO";

    String message() default "";

    String project() default "";

    String entityId() default "";

    String metadata() default "";
}
