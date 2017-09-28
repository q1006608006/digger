package test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/11
 */
public class SaTest {

    @org.junit.Test
    public void initLoad() throws Exception {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.class.getResourceAsStream("/sa-config.xml")));
/*
        FileSystemBeanContext context = new FileSystemBeanContext("sa-config.xml");
//        System.out.println(context);
        Object object = context.getBean("sourceInterceptor");
        CrawlerSourceInterceptor interceptor = (CrawlerSourceInterceptor) object;
        System.out.println(interceptor.getSource("http://www.baidu.com"));*/
//        System.out.println(context.getBean("viewController"));

        Method method = SaTest.class.getMethod("testInputPrar", String.class);
        Stream<Parameter> stream = Arrays.stream(method.getParameters());

        stream.forEach(p -> {
            System.out.println(p.getName());
            System.out.println(p.getAnnotation(ParameterName.class).value());
        });

    }

    public static interface Test {
        String test();
    }

    public static void testInputPrar(@ParameterName("ins") String in) {
        System.out.println(in);
    }
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ParameterName {
        String value();
    }

/*    @org.junit.Test
    public void testReadClass() {
        Class c = SaTest.class;
        System.out.println(System.getProperties());
    }*/

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = SaTest.class.getMethod("testInputPrar", String.class);
        Stream<Parameter> stream = Arrays.stream(method.getParameters());

        System.out.println("g=");

        stream.forEach(p -> {
            System.out.println(p.isNamePresent());
            System.out.println(p.getAnnotation(ParameterName.class).value());
        });
    }
}
