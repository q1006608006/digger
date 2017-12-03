package top.ivan.crawler.core;

import com.google.gson.reflect.TypeToken;
import top.ivan.crawler.Focus;
import top.ivan.crawler.FocusManager;
import top.ivan.crawler.UnSupportFocusException;
import top.ivan.crawler.focus.JsonFocus;
import top.ivan.crawler.utils.ClassBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/31
 */
public class FocusManagerBuilder {

    public static Config config() {
        return new Config();
    }

    public static class Config {
        private final String defaultJson = "[{\"type\":\"css\",\"class\":\"top.ivan.crawler.focus.CssFocus\"},{\"type\":\"json\",\"class\":\"top.ivan.crawler.focus.JsonFocus\"},{\"type\":\"list\",\"class\":\"top.ivan.crawler.focus.ListFocus\"},{\"type\":\"regex\",\"class\":\"top.ivan.crawler.focus.RegexFocus\"},{\"type\":\"test\",\"class\":\"top.ivan.crawler.focus.TestFocus\"},{\"type\":\"type\",\"class\":\"top.ivan.crawler.focus.TypeFocus\"},{\"type\":\"xpath\",\"class\":\"top.ivan.crawler.focus.XpathFocus\"},{\"type\":\"value\",\"class\":\"top.ivan.crawler.focus.ValueFocus\"},{\"type\":\"griddle\",\"class\":\"top.ivan.crawler.focus.GriddleFocus\"},{\"type\":\"map\",\"class\":\"top.ivan.crawler.focus.MapFocus\"}]";
        private Map<String, Class<? extends Focus>> typeMapping = new HashMap<>();
        public Config file(File file) throws IOException,ClassNotFoundException,UnSupportFocusException {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            StringBuilder builder = new StringBuilder();
            String temp;
            while ((temp = reader.readLine()) != null) {
                builder.append(temp);
            }
            reader.close();
            fileReader.close();
            json(builder.toString());
            return this;
        }

        public Config type(String typeName, Class<? extends Focus> focusClass) {
            typeMapping.put(typeName, focusClass);
            return this;
        }

        public Config type(String typeName,String classPath) throws ClassNotFoundException,UnSupportFocusException {
            Class c = Class.forName(classPath);
            if(!Focus.class.isAssignableFrom(c)) {
                throw new UnSupportFocusException(String.format("\"%s\" not a class type of %s",classPath,Focus.class.getCanonicalName()));
            }
            type(typeName,c);
            return this;
        }

        public Config map(Map<String,Class<? extends Focus>> map) {
            Iterator<Map.Entry<String,Class<? extends Focus>>> iterator = map.entrySet().iterator();
            Map.Entry<String,Class<? extends Focus>> entry;
            while (iterator.hasNext()) {
                entry = iterator.next();
                type(entry.getKey(),entry.getValue());
            }
            return this;
        }

        public Config json(String json) throws ClassNotFoundException, UnSupportFocusException {
            List<Map<String,String>> typesMapping = JsonFocus.fromJson(json,new TypeToken<List<Map<String,String>>>(){}.getType());
            Map<String,String> typeMap;
            for(int i = 0;i < typesMapping.size();i++) {
                typeMap = typesMapping.get(i);
                type(typeMap.get("type"),typeMap.get("class"));
            }
            return this;
        }

        public Config def() throws UnSupportFocusException, ClassNotFoundException {
            json(defaultJson);
            return this;
        }

        public FocusManager build() throws Exception {
            DefaultFocusManager manager = new DefaultFocusManager();
            Iterator<Map.Entry<String,Class<? extends Focus>>> iterator = typeMapping.entrySet().iterator();
            Map.Entry<String,Class<? extends Focus>> entry;
            Focus focus;
            while (iterator.hasNext()) {
                entry = iterator.next();
                focus = ClassBuilder.build(entry.getValue());
                manager.setFocus(entry.getKey(),focus);
            }
            return manager;
        }
    }

}
