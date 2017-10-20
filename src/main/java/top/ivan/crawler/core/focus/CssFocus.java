package top.ivan.crawler.core.focus;

import org.jsoup.select.Elements;
import top.ivan.crawler.Focus;
import top.ivan.crawler.annotation.Filter;
import top.ivan.crawler.annotation.Peek;

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
    public String peek(String src, String target, String key, Map<String, Object> tempMap) {
        return null;
    }
}
