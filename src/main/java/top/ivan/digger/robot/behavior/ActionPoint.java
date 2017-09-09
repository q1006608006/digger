package top.ivan.digger.robot.behavior;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/9
 */
public class ActionPoint {
    private Method method;
    private Object execute;
    private Object proxy;
    private Object[] params;
    private Map<String,Object> keeper;

    public void doAction() throws Throwable {
        try {
            method.invoke(execute, params);
        } catch (Exception e){
            assert e instanceof IllegalAccessException : "not executable method";
            if(e instanceof InvocationTargetException) {
                //an exception throwed without catch in this method
                throw e.getCause();
            }
        }
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getExecute() {
        return execute;
    }

    public void setExecute(Object execute) {
        this.execute = execute;
    }

    public Object getProxy() {
        return proxy;
    }

    public void setProxy(Object proxy) {
        this.proxy = proxy;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

}
