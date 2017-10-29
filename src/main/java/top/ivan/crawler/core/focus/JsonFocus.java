package top.ivan.crawler.core.focus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import top.ivan.crawler.Focus;

import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */
public class JsonFocus implements Focus {

    /**
     * json type data select
     * @param src
     * @param target data path
     * @param key unused
     * @return
     */
    @Override
    public String peek(String src, String target, String key) {
        return GSON.toJson(takeObject(fromJson(src,JsonElement.class),target));
    }

    private Object takeObject(JsonElement json,String path) {
        String[] parts = path.split("\\.");
        JsonElement temp = json;
        String key;
        for(int i = 0;i < parts.length; i++) {
            key = parts[i];
            temp = anyKey(temp,key);
        }
        return temp;
    }

    private JsonElement anyKey(JsonElement json,String key) {
        if(key.contains("[")) {
            return getArrayValue(json,key);
        }
        return json.getAsJsonObject().get(key);
    }

    private static JsonElement getArrayValue(JsonElement json, String curkey) {
        Matcher matcher = pattern.matcher(curkey);
        String key = curkey.replaceAll("\\[\\S*","");
        JsonArray array;
        if("".equals(key)) {
            array = json.getAsJsonArray();
        } else {
            array = json.getAsJsonObject().getAsJsonArray(key);
        }
        while (matcher.find()) {
            json = array.get(Integer.valueOf(matcher.group(1)));
            if(json instanceof JsonArray) {
                array = (JsonArray) json;
            }
        }
        return json;
    }

    private static final Pattern pattern = Pattern.compile("\\[(\\d*?)\\]");
    private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public static <T> T fromJson(String json, Type type) {
        return GSON.fromJson(json,type);
    }
    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T fromJson(String json,Class<? extends T> clazz) {
        return GSON.fromJson(json,clazz);
    }

}
