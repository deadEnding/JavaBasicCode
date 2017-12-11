package annotation.defaultannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: yuhui
 * @date: 05/06/2017
 */


public @interface Defalut {

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface String {
        java.lang.String value() default "";
    }
}
