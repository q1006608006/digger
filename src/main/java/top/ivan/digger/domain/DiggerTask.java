package top.ivan.digger.domain;

import top.ivan.digger.filter.DiggerFilter;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/11
 */
public class DiggerTask {
    public DiggerTask(String id) {
        this.id = id;
    }

    private DiggerFilter filter;
    private String target;
    private String id;

    public DiggerFilter getFilter() {
        return filter;
    }

    public void setFilter(DiggerFilter filter) {
        this.filter = filter;
    }

    public String getTaskId() {
        return id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
