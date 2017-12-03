package top.ivan.crawler.focus;

import com.google.gson.reflect.TypeToken;
import top.ivan.crawler.ExportFocusHandle;
import top.ivan.crawler.Focus;
import top.ivan.crawler.FocusManager;
import top.ivan.crawler.Griddle;
import top.ivan.crawler.core.GriddleBuilder;

import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/30
 */
public class GriddleFocus implements Focus,ExportFocusHandle {
    private FocusManager manager;
    @Override
    public void setManager(FocusManager manager) {
        this.manager = manager;
    }

    @Override
    public String peek(String src, String target, String key) throws Exception {
        Map<String,String> map = JsonFocus.fromJson(key,new TypeToken<Map<String,String>>(){}.getType());
        Griddle griddle = GriddleBuilder.builder().manager(manager).json(target).build();
        map = griddle.doFilter(src,map);
        return JsonFocus.toJson(map);
    }
}
