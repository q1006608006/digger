package top.ivan.crawler.test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import jdk.nashorn.internal.parser.JSONParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import top.ivan.crawler.focus.JsonFocus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/12/4
 */
public class OrtherTest {
    public static void main(String[] args) throws IOException {
/*        String url = "http://arabic.sport360.com/category/tennis";
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36";
        String userAgent1 = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36";
        Connection connection = Jsoup.connect(url).userAgent(userAgent).ignoreHttpErrors(true).postDataCharset("utf-8").timeout(90000).header("Connection", "close");
//        connection.header("Cookie"," __cfduid=d917bb754d030327fbd9238305de249ad1512527869; cf_clearance=339521b47da33c17684bca84658e911797230dc8-1512527873-2592000; advanced-frontend_ar=64igbvli080fvd6pkjrvarng64; _ga=GA1.2.1272653942.1512527873; _gid=GA1.2.1980793631.1512527874; __gfp_64b=41dq0jW2vsv3j8yJGcP9PueE9MdMNad.iainTBTBYez.E7");
        connection.cookie("cf_clearance","339521b47da33c17684bca84658e911797230dc8-1512527873-2592000");
        connection.header("Host","arabic.sport360.com");
        System.out.println(connection.request().headers());
        System.out.println(connection.request().cookies());
        Connection.Response response = connection.execute();
        System.out.println(response.statusCode());*/

//        System.out.println(response.body());
        set(1);
    }
    public static void set(Integer s) {
        s.shortValue();
    }
}
