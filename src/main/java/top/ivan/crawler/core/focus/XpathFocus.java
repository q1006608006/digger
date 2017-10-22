package top.ivan.crawler.core.focus;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import top.ivan.crawler.Focus;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by IVAN on 2017/10/21.
 */
public class XpathFocus implements Focus {
    @Override
    public String peek(String src, String target, String key) throws XPatherException {
        HtmlCleaner cleaner = new HtmlCleaner();
        TagNode tagNode = cleaner.clean(src);
        List list = new LinkedList();
        TagNode node;
        for(Object obj : tagNode.evaluateXPath(target)) {
            node = (TagNode) obj;
            if(null == key || "text".equalsIgnoreCase(key)) {
                list.add(node.getText());
                continue;
            }
            list.add(node.getAttributeByName(key));
        }
        return GSON.toJson(list);
    }


    public static void main(String[] args) throws XPatherException {
        String html = "<html><head></head><body><div class=\"div\" id=\"123\">fuck</div><div>1</div></body></html>";

        System.out.println(new XpathFocus().peek(html,"//div","id"));
    }
}
