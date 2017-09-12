package test;

import org.junit.Test;
import top.badtheway.sa.core.container.FileSystemBeanContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/11
 */
public class SaTest {

    @Test
    public void initLoad() throws Exception {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.class.getResourceAsStream("/sa-config.xml")));

        FileSystemBeanContext context = new FileSystemBeanContext("/sa-config.xml");
        System.out.println(context);
//        System.out.println(context.getBean("viewController"));

    }
}
