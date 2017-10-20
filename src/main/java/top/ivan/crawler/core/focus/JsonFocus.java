package top.ivan.crawler.core.focus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import top.ivan.crawler.Focus;
import top.ivan.crawler.annotation.Parameter;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */

public class JsonFocus implements Focus {
    private static Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private static final String INDEX = "\\[(\\d*?)\\]";
    private static Pattern pattern = Pattern.compile(INDEX);

    @Override
    public String peek(String src, String target, String key, Map<String, Object> tempMap) {
        return takeObject(GSON.fromJson(src,JsonObject.class),target).toString();
    }

    private Object takeObject(JsonObject json,String path) {
        String[] parts = path.split("\\.");
        JsonObject temp = json;
        for(String key : parts) {
            if(key.contains("[")) {
                temp = getArrayValue(temp,key);
            }
            temp = temp.getAsJsonObject(key);
        }
        return temp;
    }

    public static JsonObject getArrayValue(JsonObject json,String curkey) {
        Matcher matcher = pattern.matcher(curkey);
        List<Integer> indexs = new LinkedList<>();
        while (matcher.find()) {
            indexs.add(Integer.valueOf(matcher.group(1)));
        }
        JsonArray array = json.getAsJsonArray(curkey.replaceAll("\\[\\S*",""));
        for(int i=0;i < indexs.size() - 1;i++) {
            array = array.get(i).getAsJsonArray();
        }
        return array.get(indexs.get(indexs.size())).getAsJsonObject();
    }

    public static void main(String[] args) {
        String json = "{id:1,value:2,fuck:{id:1,fuck:[1,[3],3,4]}}";

        System.out.println(new JsonFocus().peek(json,"fuck.fuck[2]",null,null));
    }
}
