package top.ivan.digger.crawler;

import java.io.IOException;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/9
 */
public interface HttpLoader {
    String getSource(String url) throws IOException;
}
