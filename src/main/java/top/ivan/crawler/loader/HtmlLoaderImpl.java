package top.ivan.crawler.loader;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.ivan.crawler.HtmlLoader;
import top.ivan.crawler.IXHtml;

import java.io.IOException;
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
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.headers().toMultimap());
        System.out.println(response.cacheControl());
        System.out.println(response.code());
        System.out.println(response.isRedirect());
        System.out.println(response.body().string());
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

    public static void main(String[] args) throws IOException {
        HtmlLoader loader = new HtmlLoaderImpl();
        loader.loaderHtml("http://www.baidu.com");
    }
}
