package top.ivan.crawler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */
public interface Focus {
    String peek(String src, String target, String key) throws Exception;

    String REGEX = "\\{\\$([\\s\\S]*?)\\}";
    Pattern pattern = Pattern.compile(REGEX);
    Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    static String peeKey(String key, Map<String, Object> tempMap) {
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
}
