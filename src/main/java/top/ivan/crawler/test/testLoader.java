package top.ivan.crawler.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import top.ivan.crawler.Griddle;
import top.ivan.crawler.IXHtml;
import top.ivan.crawler.core.FocusManagerBuilder;
import top.ivan.crawler.core.GriddleBuilder;
import top.ivan.crawler.focus.JsonFocus;
import top.ivan.crawler.loader.HtmlLoaderImpl;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/21
 */
public class testLoader {

    public static void main(String[] args) throws Exception {
        HtmlLoaderImpl loader = new HtmlLoaderImpl();
        IXHtml html = loader.loaderHtml("https://detail.tmall.com/item.htm?id=559791372740");
        Griddle grddle = GriddleBuilder.builder().manager(FocusManagerBuilder.config().def().build()).file(new File("config/test.json"),"filters").build();
        HashMap<String,String> map = new HashMap<>();
        map.put("id","559791372740");
        String value = grddle.doFilter(html.getBody(),map).get("value");
        System.out.println(value);
        List lit = JsonFocus.fromJson(value,new TypeToken<List>(){}.getType());
        lit.forEach(o->{
            System.out.println(o);
        });
//        lit.forEach(System.out::println);
//        System.out.println();
//        System.out.println(Arrays.asList("1","2"));

//        System.out.println(LocalDate.now().withMonth(1).toString().replace("-","").substring(0,4));
    }

}
