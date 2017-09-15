package top.ivan.digger.domain;

import top.ivan.digger.crawler.FilterRule;

import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/11
 */
public class DiggerTask {
    private Map<String,FilterRule> filterRules;
    private String target;

    public void addRule(String target,FilterRule rule) {
        filterRules.put(target,rule);
    }

    public Map<String, FilterRule> getFilterRules() {
        return filterRules;
    }

    public void setFilterRules(Map<String, FilterRule> filterRule) {
        this.filterRules = filterRule;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
