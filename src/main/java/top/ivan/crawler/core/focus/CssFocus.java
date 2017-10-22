package top.ivan.crawler.core.focus;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import top.ivan.crawler.Focus;
import top.ivan.crawler.annotation.Filter;
import top.ivan.crawler.annotation.Peek;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */
@Filter("css")
public class CssFocus implements Focus {

    @Override
    public String peek(String src, String target, String key) {
        Elements elements = Jsoup.parse(src).select(target);
        return anyKey(elements,key);
    }

    private String anyKey(Elements els,String key) {
        if(null == key || "".equals(key)) {
            return els.outerHtml();
        }
        if("text".equals(key)) {
            return els.text();
        }
        if(key.startsWith("foreach:")) {
            return GSON.toJson("");
        }
        return els.attr(key);
    }

    public static void main(String[] args) {
        String html = "html-clean-demo.html\n" +
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd \">\n" +
                "<html xmlns = \"http://www.w3.org/1999/xhtml \" xml:lang = \"zh-CN\" dir = \"ltr\">\n" +
                "<head>\n" +
                "\t<meta http-equiv = \"Content-Type\" content = \"text/html; charset=GBK\" /> \n" +
                "\t<meta http-equiv = \"Content-Language\" content = \"zh-CN\" /> \n" +
                "\t<title>html clean demo </title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class = \"d_1\">\n" +
                "\t<ul>\n" +
                "\t\t<li>bar </li>\n" +
                "\t\t<li>foo </li>\n" +
                "\t\t<li>gzz </li>\n" +
                "\t</ul>\n" +
                "</div>\n" +
                "<div>\n" +
                "\t<ul>\n" +
                "\t\t<li><a name = \"my_href\" href = \"1.html\">text-1 </a></li>\n" +
                "\t\t<li><a name = \"my_href\" href = \"2.html\">text-2 </a></li>\n" +
                "\t\t<li><a name = \"my_href\" href = \"3.html\">text-3 </a></li>\n" +
                "\t\t<li><a name = \"my_href\" href = \"4.html\">text-4 </a></li>\n" +
                "\t</ul>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";

        System.out.println(new CssFocus().peek(html,"div li a[href*=html]",""));
    }
}
