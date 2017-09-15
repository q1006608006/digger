import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;
import top.ivan.digger.util.CrawlerUtil;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/12
 */
public class UtilTest {

    @Test
    public void getPage() throws IOException {

//        System.out.println(CrawlerUtil.getHtml("http://localhost:18080/view/hello",null,null,null));
        CrawlerUtil.HttpRequest request = CrawlerUtil.request("https://detail.tmall.com/item.htm?spm=a230r.1.14.12.76bf523kvHIwB&id=558385606674&cm_id=140105335569ed55e27b&abbucket=5&sku_properties=5919063:6536025;12304035:48072;122216431:27772");
        request.header("Accept-Charset","utf-8")
                .header("Content-Type","application/json;charset=utf-8");
        //        request.method("GET");
        CrawlerUtil.HttpResponse response = null;
        try {
            response = request.response();
//            response.encode("UTF8");
            String body = response.getBody();
            System.out.println(response.getHeader());

            FileOutputStream fout = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\myTemp\\shouji_test.html");
            fout.write(body.getBytes());
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
//            response.getInputStream();
            System.out.println(response.getResponseCode());
        }


    }


    @Test
    public void testHtmlUnit() throws IOException {
/*        String str = CrawlerUtil.getHtml("https://detail.tmall.com/item.htm?spm=a230r.1.14.12.76bf523kvHIwB&id=558385606674&cm_id=140105335569ed55e27b&abbucket=5&sku_properties=5919063:6536025;12304035:48072;122216431:27772", null, null, null);
        System.out.println(str);*/
        Connection connection = Jsoup.connect("https://detail.tmall.com/item.htm?spm=a230r.1.14.12.76bf523kvHIwB&id=558385606674&cm_id=140105335569ed55e27b&abbucket=5&sku_properties=5919063:6536025;12304035:48072;122216431:27772");
        connection.get();
        Connection.Response response = connection.response();
        System.out.println(response.body());
    }
}
