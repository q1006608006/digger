package top.ivan.crawler.core.loader;

import top.ivan.crawler.HtmlLoader;
import top.ivan.crawler.IXHtml;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/31
 */
public class HtmlLoaderImpl implements HtmlLoader {
    @Override
    public IXHtml loaderHtml(String url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
        return null;
    }

    @Override
    public IXHtml loaderHtml(String url, Map<String, String> heads) throws IOException {
        return null;
    }

    @Override
    public IXHtml loaderHtml(String url, Map<String, String> heads, Map<String, String> cookies) throws IOException {
        return null;
    }
}
