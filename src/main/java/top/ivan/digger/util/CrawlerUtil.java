package top.ivan.digger.util;

import com.sun.istack.internal.Nullable;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
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
    private static Logger logger = LogManager.getLogger(CrawlerUtil.class);

    public static String getHtml(String url, @Nullable Integer timeOut, @Nullable Map<String, String> header, @Nullable Map<String, String> cookies) throws IOException {
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

    private static HttpResponse getResponse(HttpURLConnection connection, Request request) throws IOException {
        Request.fixConnection(connection, request);
        connection.connect();
        int status = connection.getResponseCode();
        if (status != 200) {
            if (status == 302 || status == 301 || status == 303) {
                //重定向
                return getResponse((HttpURLConnection) new URL(connection.getHeaderField("Location")).openConnection(), request);
            }
        }
        HttpResponse response = new HttpResponse(connection);
        response.setOriginRequest(request);
        return response;
    }

    public static HttpResponse getResponse(String url, @Nullable Integer timeOut, @Nullable Map<String, String> header, @Nullable Map<String, String> cookies) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        Request request = new Request(timeOut, header, cookies);
        return getResponse(connection, request);
    }

    public static HttpRequest request(String url) {
        return new HttpRequest(url);
    }

    public static class HttpRequest {
        private Request request;
        private ByteArrayOutputStream outputStream;
        private HttpResponse response;

        private HttpRequest(String url) {
            request = new Request();
            this.request.setUrl(url);
        }

        private synchronized void initCookie() {
            if (null == request.getCookie()) {
                request.setCookie(new HashMap<>());
            }
        }

        private synchronized void initOutput() {
            if (null == outputStream) {
                outputStream = new ByteArrayOutputStream();
            }
        }

        private synchronized void initHeader() {
            if (null == request.getHeader()) {
                request.setHeader(new HashMap<>());
            }
        }

        public HttpRequest cookie(String key, String value) {
            initCookie();
            request.getCookie().put(key, value);
            return this;
        }

        public HttpRequest cookies(Map<String, String> cookieMap) {
            request.setCookie(cookieMap);
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

        public synchronized HttpResponse response() throws IOException {
            if (null == response) {
                HttpURLConnection connection = (HttpURLConnection) new URL(this.request.url).openConnection();
                if (null != this.request.method) {
                    connection.setRequestMethod(this.request.method);
                }
                if (null != outputStream) {
                    connection.setDoOutput(true);
                    connection.getOutputStream().write(outputStream.toByteArray());
                    outputStream.close();
                    outputStream = null;
                }
                response = getResponse(connection, request);
            }
            return response;
        }

        public HttpRequest method(String method) {
            this.request.setMethod(method);
            return this;
        }

        public HttpRequest write(byte[] data) throws IOException {
            initOutput();
            outputStream.write(data);
            return this;
        }

    }

    public static class Request {
        private Map<String, String> header;
        private Map<String, String> cookie;
        private Integer timeout;

        private String url;
        private String method;

        private Request() {
        }


        private Request(Integer timeout, Map<String, String> header, Map<String, String> cookie) {
            this.timeout = timeout;
            this.header = header;
            this.cookie = cookie;
        }

        private static void praseHeader(HttpURLConnection connection, Map<String, String> header) {
            if (null != header && header.size() > 0) {
                Set<Map.Entry<String, String>> headerSet = header.entrySet();
                for (Map.Entry<String, String> entry : headerSet) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
        }

        private static void praseCookie(HttpURLConnection connection, Map<String, String> cookies) {
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

        private static void fixConnection(HttpURLConnection connection, Request request) {
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

        private void setTimeout(Integer timeout) {
            this.timeout = timeout;
        }

        public Map<String, String> getHeader() {
            return header;
        }

        private void setHeader(Map<String, String> header) {
            this.header = header;
        }

        public Map<String, String> getCookie() {
            return cookie;
        }

        private void setCookie(Map<String, String> cookie) {
            this.cookie = cookie;
        }

        public String getUrl() {
            return url;
        }

        private void setUrl(String url) {
            this.url = url;
        }

        public String getMethod() {
            return method;
        }

        private void setMethod(String method) {
            this.method = method;
        }

    }


    public static class HttpResponse {
        private Request request;
        private HttpURLConnection connection;
        private Boolean streamLock = null;
        private byte[] bodyData;

        private HttpResponse(HttpURLConnection connection) {
            this.connection = connection;
        }

        public void streamReflect(boolean isLockStream) throws IOException {
            if (null != streamLock && streamLock == isLockStream) {
                if (streamLock) {
                    throw new IOException("resource is reflect while you had got in put stream");
                } else {
                    throw new IOException("resource is reflect while you had got response body");
                }
            }
        }

        public String getBody() throws IOException {
            String charset = readCharsetFromContentType(connection.getContentType());
            if (null == charset) {
                charset = "utf8";
            }
            return Charset.forName(charset).decode(ByteBuffer.wrap(getBodyAsBytes())).toString();
        }

        public synchronized byte[] getBodyAsBytes() throws IOException {
            streamReflect(true);
            streamLock = false;
            if (null == bodyData) {
                try (InputStream in = connection.getInputStream()) {
                    bodyData = readAsBytes(in);
                }
            }
            return bodyData;
        }

        private static String readCharsetFromContentType(String contentType) {
            String charset = contentType.replaceAll("([\\s\\S]*)?charset=(.*);?([\\s\\S]*)?", "$2");
            return charset;
        }

        private static byte[] readAsBytes(InputStream in) throws IOException {
            try (ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
                bout.write(in);
                byte[] data = bout.toByteArray();
                return data;
            }
        }

        public Map<String, List<String>> getHeader() {
            return connection.getHeaderFields();
        }

        public List<String> getSetCookie() {
            return getHeader().get("Set-Cookie");
        }

        public synchronized InputStream getInputStream() throws IOException {
            streamReflect(false);
            streamLock = true;
            return connection.getInputStream();
        }

        public HttpURLConnection getOriginConnection() {
            return connection;
        }

        public Request getOriginRequest() {
            return request;
        }

        private void setOriginRequest(Request request) {
            this.request = request;
        }

        public int getResponseCode() throws IOException {
            return connection.getResponseCode();
        }
    }
}
