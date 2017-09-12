package top.ivan.digger.util;

import com.sun.istack.internal.Nullable;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/12
 */
public class CrawlerUtil {

    static public String getHtml(String url, @Nullable Integer timeOut, @Nullable Map<String, String> header, @Nullable Map<String, String> cookies) throws IOException {
        Connection connection = Jsoup.connect(url);
        if (null != header) {
            Set<Map.Entry<String, String>> headerSet = header.entrySet();
            for (Map.Entry<String, String> entry : headerSet) {
                connection.header(entry.getKey(), entry.getValue());
            }
        }
        if (null != timeOut) {
            connection.timeout(timeOut);
        }
        if (null != cookies) {
            connection.cookies(cookies);
        }
        return connection.get().outerHtml();
    }

    static private HttpResponse getResponse(HttpURLConnection connection, Request request) throws IOException {
        Request.fixConnection(connection, request);
        connection.connect();
        int status = connection.getResponseCode();

        if (status != 200) {
            if (status == 302 || status == 301 || status == 303) {
                return getResponse(connection.getHeaderField("Location"), request.timeout, request.getHeader(), request.getCookie());
            } else {
                throw new IOException(String.format("can not prase url{%s} with response code: %d", connection.getURL().toString(), status));
            }
        }

        HttpResponse response = new HttpResponse(connection);
        response.setOriginRequest(request);
        return response;
    }

    static public HttpResponse getResponse(String url, @Nullable Integer timeOut, @Nullable Map<String, String> header, @Nullable Map<String, String> cookies) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

        Request request = new Request(timeOut, header, cookies);

        return getResponse(connection, request);
    }

    static public HttpRequest request(String url) {
        return new HttpRequest(url);
    }

    public static class HttpRequest {
        private Request request;
        private String url;

        private HttpRequest(String url) {
            this.url = url;
            request = new Request();
        }

        private void initCookie() {
            if (null == request.getCookie()) {
                request.setCookie(new HashMap<>());
            }
        }

        private void initHeader() {
            if (null == request.getHeader()) {
                request.setHeader(new HashMap<>());
            }
        }

        public HttpRequest cookie(String key, String value) {
            initCookie();
            request.getCookie().put(key, value);
            return this;
        }

        public HttpRequest header(String head, String value) {
            initHeader();
            request.getHeader().put(head, value);
            return this;
        }

        public HttpRequest timeout(int timeout) {
            request.setTimeout(timeout);
            return this;
        }

        public HttpResponse response() throws IOException {
            return getResponse((HttpURLConnection) new URL(url).openConnection(), request);
        }

    }

    public static class Request {
        private Map<String, String> header;
        private Map<String, String> cookie;
        private Integer timeout;

        private Request() {
        }


        private Request(Integer timeout, Map<String, String> header, Map<String, String> cookie) {
            this.timeout = timeout;
            this.header = header;
            this.cookie = cookie;
        }

        static private void praseHeader(HttpURLConnection connection, Map<String, String> header) {
            if (null != header && header.size() > 0) {
                Set<Map.Entry<String, String>> headerSet = header.entrySet();
                for (Map.Entry<String, String> entry : headerSet) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
        }

        static private void praseCookie(HttpURLConnection connection, Map<String, String> cookies) {
            if (null != cookies && cookies.size() > 0) {
                Set<Map.Entry<String, String>> cookieSet = cookies.entrySet();
                StringBuilder cookieBuilder = new StringBuilder();
                for (Map.Entry<String, String> entry : cookieSet) {
                    cookieBuilder.append(entry.getKey()).append('=').append(entry.getValue()).append("; ");
                }
                int builderLen = cookieBuilder.length();
                cookieBuilder.delete(builderLen - 2, builderLen);
                connection.setRequestProperty("Cookie", cookieBuilder.toString());
            }
        }

        static private void fixConnection(HttpURLConnection connection, Request request) {
            Integer timeout = request.getTimeout();
            if (null != timeout) {
                connection.setConnectTimeout(timeout);
            }
            Request.praseHeader(connection, request.getHeader());
            Request.praseCookie(connection, request.getCookie());
        }

        public Integer getTimeout() {
            return timeout;
        }

        public void setTimeout(Integer timeout) {
            this.timeout = timeout;
        }

        private void cookie(String key, String value) {

        }

        public Map<String, String> getHeader() {
            return header;
        }

        public void setHeader(Map<String, String> header) {
            this.header = header;
        }

        public Map<String, String> getCookie() {
            return cookie;
        }

        public void setCookie(Map<String, String> cookie) {
            this.cookie = cookie;
        }

    }


    public static class HttpResponse {
        private Request request;
        private HttpURLConnection connection;

        private Boolean streamLock = null;

        private HttpResponse(HttpURLConnection connection) {
            this.connection = connection;
        }

        public synchronized String getBody() throws IOException {
            if (null == streamLock && streamLock) {
                throw new IOException("resource is reflect while you had got the input stream");
            }
            streamLock = false;
            ByteOutputStream bout = new ByteOutputStream();
            bout.write(getInputStream());
            String body = new String(bout.getBytes());
            getInputStream().close();
            bout.close();
            return body;
        }

        public Map<String, List<String>> getHeader() {
            return connection.getHeaderFields();
        }

        public List<String> getSetCookie() {
            return getHeader().get("Set-Cookie");
        }

        public synchronized InputStream getInputStream() throws IOException {
            if(null != streamLock && !streamLock) {
                throw new IOException("resource is reflect while you had got response body");
            }
            streamLock = true;
            return connection.getInputStream();
        }

        public HttpURLConnection getOriginConnection() {
            return connection;
        }

        public Request getOriginRequest() {
            return request;
        }

        protected void setOriginRequest(Request request) {
            this.request = request;
        }
    }
}
