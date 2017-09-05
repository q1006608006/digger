package test;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * description
 *
 * @author Administrator
 * @date 2017/8/31
 */
public class WebCliTest {


    @Test
    public void testJs() throws IOException {
        System.load("C:\\Program Files\\Java\\jdk1.8.0_141\\jre\\bin\\webp-imageio.dll");
    }


    public static final String TARGET_URL = "http://www.konozarabs.net";
    @Test
    public void webClientTest() throws IOException {
/*        Document document = Jsoup.connect("http://www.taobao.com").get();
        System.out.println(document);*/

        WebClient client = new WebClient(BrowserVersion.CHROME);
        client.getOptions().setTimeout(1000);
        client.setAjaxController(new NicelyResynchronizingAjaxController());
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setCssEnabled(false);
        client.getCookieManager().setCookiesEnabled(true);
        HtmlPage page = client.getPage(TARGET_URL);
        System.out.println(page.asXml());
        page.cleanUp();
        client.close();

    }

    @Test
    public void jsoupTest() throws IOException {
        Document document = Jsoup.connect(TARGET_URL).get();

        System.out.println(document);
    }
}
