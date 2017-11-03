package test;

import org.junit.Test;

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

    /**
     * peek : id
     * focus : {"type":"","target":"","key":"","interceptor":{"type":"test","target":"regex[\\s]","key":"#abc#"}}
     */

    private String peek;
    private FocusBean focus;

    /**
     * filters : {"id":{"type":"","target":"","order":"","interceptor":{"type":"test","target":"regex[\\s]","order":"#abc#"}},"value":{"type":"","target":"","order":"","interceptor":null}}
     */
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

    public String getPeek() {
        return peek;
    }

    public void setPeek(String peek) {
        this.peek = peek;
    }

    public FocusBean getFocus() {
        return focus;
    }

    public void setFocus(FocusBean focus) {
        this.focus = focus;
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


    public static class FocusBean {
        /**
         * type :
         * target :
         * key :
         * interceptor : {"type":"test","target":"regex[\\s]","key":"#abc#"}
         */

        private String type;
        private String target;
        private String key;
        private InterceptorBean interceptor;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public InterceptorBean getInterceptor() {
            return interceptor;
        }

        public void setInterceptor(InterceptorBean interceptor) {
            this.interceptor = interceptor;
        }

        public static class InterceptorBean {
            /**
             * type : test
             * target : regex[\s]
             * key : #abc#
             */

            private String type;
            private String target;
            private String key;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTarget() {
                return target;
            }

            public void setTarget(String target) {
                this.target = target;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }
        }
    }
}
