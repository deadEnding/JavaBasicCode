package annotation.defaultannotation;

import java.lang.annotation.*;

/**
 * @author: yuhui
 * @date: 05/06/2017
 */


@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface ClassTag {
    String value() default "unknown";
}

@ClassTag("test")
public class AnnotationDemo {

    public static void main(String[] args) {
        Class clz = AnnotationDemo.class;
        Annotation[] ans = clz.getAnnotations();
        for (Annotation an : ans) {
            System.out.println(an);
            ClassTag f = AnnotationDemo.class.getAnnotation(ClassTag.class);
            System.out.println(f.value());
        }
    }
}
