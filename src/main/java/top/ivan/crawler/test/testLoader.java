package top.ivan.crawler.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/21
 */
public class testLoader {

    public static void main(String[] args) throws Exception {
//        HtmlLoaderImpl loader = new HtmlLoaderImpl();
//        IXHtml html = loader.loaderHtml("https://detail.tmall.com/item.htm?id=559791372740");
//        Griddle grddle = GriddleBuilder.builder().manager(FocusManagerBuilder.config().def().build()).file(new File("config/test.json"),"filters").build();
//        System.out.println(grddle.doFilter(html.getBody()));
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-11-22 18:40:18").getTime());

        Test t = new TestImpl();
        Prox prox = new Prox(t);
        t = (Test) Proxy.newProxyInstance(Test.class.getClassLoader(),new Class[]{Test.class},prox);
        System.out.println(t.getList());
    }

    public static class Prox implements InvocationHandler{
        private Object target;
        public Prox(Object o) {
            this.target = o;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return Arrays.asList("you kidding me ?".split(" "));
        }
    }

    public static class TestImpl implements Test {
        public List getList() {
            return null;
        }
    }

    public interface Test {
        List getList();
    }
}
