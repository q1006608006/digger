import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;
import top.ivan.digger.util.CrawlerUtil;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

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
    public void testServer() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(80);
        Socket socket = serverSocket.accept();

        PrintWriter pr = new PrintWriter(socket.getOutputStream(),true);
//        Thread.sleep(200);
        pr.println("HTTP/1.1 200 OK");
        pr.println("Connection: close");
        pr.println("Content-Length: 2");
        pr.println();
        pr.println("==");

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str = reader.readLine();
        while (str != null) {
            System.out.println(str);
            str = reader.readLine();
        }
    }

    @Test
    public void testClient() {
        try {
            System.out.println(CrawlerUtil.request("http://localhost").timeout(1000).write("hello".getBytes()).header("1","1").response().getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testHtmlUnit() throws IOException {
/*        String str = CrawlerUtil.getHtml("https://detail.tmall.com/item.htm?spm=a230r.1.14.12.76bf523kvHIwB&id=558385606674&cm_id=140105335569ed55e27b&abbucket=5&sku_properties=5919063:6536025;12304035:48072;122216431:27772", null, null, null);
        System.out.println(str);*/
        Connection connection = Jsoup.connect("https://localhost?spm=a230r.1.14.12.76bf523kvHIwB&id=558385606674&cm_id=140105335569ed55e27b&abbucket=5&sku_properties=5919063:6536025;12304035:48072;122216431:27772");
        connection.get();
        Connection.Response response = connection.response();
        System.out.println(response.body());


    }

    @Test
    public void socketTest() throws IOException {
        String url = "detail.tmall.com";

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(url, 80));
        PrintWriter pr = new PrintWriter(socket.getOutputStream(),true);
        pr.println("GET /item.htm?id=557759288520 HTTP/1.1");
        pr.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        pr.println("Accept-encoding: gzip, deflate, br");
        pr.println("Cache-control: max-age=0");
        pr.println("user-agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
        pr.println("Connection: close");

        pr.println();
//        pr.println();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str = reader.readLine();
        while (null != str) {
            System.out.println(str);
            str = reader.readLine();
        }
    }
}
