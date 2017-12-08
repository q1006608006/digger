package top.ivan.uav.service;

/**
 * description
 *
 * @author Administrator
 * @date 2017/12/8
 */
public class TestServiceImpl implements TestService {
    @Override
    public String hello(String name) {
        return "hello " + name;
    }
}
