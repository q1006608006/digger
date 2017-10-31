package top.ivan.crawler.core;

import top.ivan.crawler.Focus;
import top.ivan.crawler.FocusManager;

import java.io.File;
import java.util.HashMap;
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
        private Map<String, Class<? extends Focus>> typeMapping = new HashMap<>();


        public Config file(File file) {

            return this;
        }

        public Config type(String typeName, Class<? extends Focus> focusClass) {
            typeMapping.put(typeName, focusClass);
            return this;
        }

        public Config json(String json) {

            return this;
        }

        public FocusManager build() {
            return null;
        }
    }
}
