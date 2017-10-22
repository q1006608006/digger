package top.ivan.crawler.core.focus;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.*;
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

    private static final String INDEX = "\\[(\\d*?)\\]";
    private static Pattern pattern = Pattern.compile(INDEX);

    @Override
    public String peek(String src, String target, String key) {
        return takeObject(GSON.fromJson(src,JsonElement.class),target).toString();
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

    public static void main(String[] args) {
        String json = "{id:1,value:2,fuck:{id:1,fuck:[1,{id:7}]}}";
        String json2 = "[[1,2],[2],3]";
//        System.out.println(GSON.fromJson(json,JsonObject.class));
        System.out.println(new JsonFocus().peek(json2,"[1]",null));
    }
}
