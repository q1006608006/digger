package top.ivan.crawler.core;

import com.google.gson.reflect.TypeToken;
import top.ivan.crawler.FocusManager;
import top.ivan.crawler.Griddle;
import top.ivan.crawler.focus.JsonFocus;
import top.ivan.crawler.focus.TestFocus;
import top.ivan.crawler.griddle.AisleBean;
import top.ivan.crawler.griddle.DefaultGriddleAisle;
import top.ivan.crawler.griddle.FIFOGriddle;
import top.ivan.crawler.griddle.GriddleAisle;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/1
 */
public class GriddleBuilder {

    public static GriddleBuilder builder() {
        return new GriddleBuilder();
    }

    private List<AisleBean> aisleBeans;
    private List<GriddleAisle> griddleAisles;
    private FocusManager manager;

    public GriddleBuilder json(String json,String path) {
        if(TestFocus.nullValue(path) != null) {
            json = JsonFocus.takeJsonValue(json, path);
        }
        return json(json);
    }

    public GriddleBuilder json(String json) {
        List<AisleBean> aisles = JsonFocus.fromJson(json, new TypeToken<List<AisleBean>>() {}.getType());
        if (aisleBeans == null) {
            aisleBeans = aisles;
        } else {
            aisleBeans.addAll(aisles);
        }
        return this;
    }

    public GriddleBuilder file(File file,String jsonPath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String temp;
        while ((temp = reader.readLine()) != null) {
            builder.append(temp).append('\n');
        }
        reader.close();
        return json(builder.toString(),jsonPath);
    }
    public GriddleBuilder file(File file) throws IOException {
        return file(file,null);
    }

    public GriddleBuilder manager(FocusManager manager) {
        this.manager = manager;
        return this;
    }

    public GriddleBuilder aisleList(List<GriddleAisle> list) {
        this.griddleAisles = list;
        return this;
    }

    public GriddleBuilder aisle(AisleBean aisle) {
        if (aisleBeans == null) {
            aisleBeans = new ArrayList<>();
        }
        aisleBeans.add(aisle);
        return this;
    }

    public Griddle build() throws Exception {
        if (griddleAisles == null || griddleAisles.size() == 0) {
            griddleAisles = new ArrayList<>();
            if (null != aisleBeans) {
                if (manager == null) {
                    manager = FocusManagerBuilder.config().def().build();
                }
                AisleBean bean;
                for (int i = 0; i < aisleBeans.size(); i++) {
                    bean = aisleBeans.get(i);
                    griddleAisles.add(new DefaultGriddleAisle(bean, manager));
                }
            }
        }
        return new FIFOGriddle(griddleAisles);
    }
}
