package top.ivan.crawler.core.focus;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import top.ivan.crawler.Focus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IVAN on 2017/10/21.
 */
public class XpathFocus implements Focus {
    /**
     * @param src
     * @param target xpath target
     * @param key
     * @return list of attribute
     * @throws XPatherException
     */
    @Override
    public String peek(String src, String target, String key) throws XPatherException {
        HtmlCleaner cleaner = new HtmlCleaner();
        TagNode tagNode = cleaner.clean(src);
        Object[] objs = tagNode.evaluateXPath(target);
        return anyKey(objs, key);
    }

    public String anyKey(Object[] nodes, String key) {
        if (null == key || "".equals(key) || !key.startsWith("list:")) {
            StringBuilder sb = new StringBuilder();
            ListExportFocus.foreach(nodes, o -> sb.append(anyNode((TagNode) o, key)).append("\n"));
            sb.deleteCharAt(sb.lastIndexOf("\n"));
            return sb.toString();
        }
        String listKey = key.replace("list:", "");
        List list = new ArrayList();
        ListExportFocus.foreach(nodes, o -> list.add(anyNode((TagNode) o, listKey)));
        return JsonFocus.toJson(list);
    }

    public String anyNode(TagNode node, String key) {
        String ret;
        if (null == key || "".equals(key) || "text()".equals(key)) {
            ret = node.getText().toString();
        } else {
            ret = node.getAttributeByName(key);
        }
        return TestExportFocus.nullValue(ret);
    }

}
