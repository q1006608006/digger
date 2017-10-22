package top.ivan.crawler.core.focus;

import top.ivan.crawler.Focus;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */
public class RegexFocus implements Focus {

    public String peek(String src, String target, String key) {
        return src.replaceAll(target, key);
    }


    public static void main(String[] args) {
        String value = "{$1}{$2}$1{$3}{$4}";
        String src = "sdfaloowek(www.baidu.com)sdfsd";
//        String target = "[\\s\\S]*(www\\.[\\S]*)\\)[\\s\\S]*";
        Map map = new HashMap();
        String target = "s";
        String key = "";
        map.put("1","a");
        map.put("2","b");
        map.put("3","c");
        RegexFocus focus = new RegexFocus();
        key = Focus.peeKey(key,map);
        System.out.println(focus.peek(src,target,key));
    }
}
