package top.ivan.crawler.core.focus;

import org.jsoup.Jsoup;
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
    private static final String REGEX = "\\{\\$([\\s\\S]*?)\\}";
    private static Pattern pattern = Pattern.compile(REGEX);


    public String peek(String src, String target, String key, Map<String, Object> tempMap) {
        return src.replaceAll(target,peekTemp(key,tempMap));
    }

    private String peekTemp(String key,Map<String, Object> tempMap) {
        Matcher matcher = pattern.matcher(key);
        String repart,rekey,temp;
        while (matcher.find()) {
            repart = matcher.group(0);
            rekey = matcher.group(1);
            temp = tempMap.get(rekey) + "";
            key = key.replace(repart,temp);
        }
        return key;
    }


    public static void main(String[] args) {
        String value = "{$1}{$2}$1{$3}{$4}";
        String src = "sdfaloowek(www.baidu.com)sdfsd";
        String target = "[\\s\\S]*(www\\.[\\S]*)\\)[\\s\\S]*";
        Map map = new HashMap();
        map.put("1","a");
        map.put("2","b");
        map.put("3","c");
        RegexFocus focus = new RegexFocus();
        System.out.println(focus.peek(src,target,value,map));
    }
}
