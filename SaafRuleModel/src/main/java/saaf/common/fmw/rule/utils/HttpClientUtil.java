package saaf.common.fmw.rule.utils;

import java.io.IOException;
import java.io.InterruptedIOException;

import java.net.URLEncoder;
import java.net.UnknownHostException;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Admin on 2017/7/5.
 */
public class HttpClientUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
    protected static CloseableHttpClient httpClient = createHttpClient(100, 10, 5000, 2);


    public static CloseableHttpClient createHttpClient(int maxTotal, int maxPerRoute, int timeout, int retryExecutionCount) {
        try {
            SSLContext sslContext = SSLContexts.custom().useSSL().build();
            SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
            poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
            SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(timeout).build();
            poolingHttpClientConnectionManager.setDefaultSocketConfig(socketConfig);
            return HttpClientBuilder.create().setConnectionManager(poolingHttpClientConnectionManager).setSSLSocketFactory(sf).setRetryHandler(new HttpClientUtil.HttpRequestRetryHandlerImpl(retryExecutionCount)).build();
        } catch (KeyManagementException var8) {
            var8.printStackTrace();
        } catch (NoSuchAlgorithmException var9) {
            var9.printStackTrace();
        }

        return null;
    }

    public static String send(String url) throws IOException {
        return send(url, null, false, "utf-8");
    }

    public static String send(String url, Map paramMap) throws IOException {
        return send(url, paramMap, true, "utf-8");
    }

    public static String send(String url, Map paramMap, boolean post) throws IOException {
        return send(url, paramMap, post, "utf-8");
    }

    public static String send(String url, Map paramMap, boolean post, String charset) throws IOException {
        if (url == null || "".equals(url.trim()))
            throw new IllegalArgumentException("url is empty");
        RequestBuilder requestBuilder = post ? RequestBuilder.post() : RequestBuilder.get();
        if (paramMap != null && paramMap.size() > 0) {
            if (post) {
                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                for (Object key : paramMap.keySet()) {
                    formparams.add(new BasicNameValuePair(key.toString(), paramMap.get(key).toString()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
                requestBuilder.setEntity(entity);
            } else {
                StringBuilder sb = new StringBuilder(url);
                sb = sb.indexOf("?") == -1 ? sb.append("?") : sb.indexOf("&") == sb.length() - 1 ? sb : sb.append("&");
                for (Object key : paramMap.keySet()) {
                    sb.append(URLEncoder.encode(key.toString(), "utf-8")).append("=").append(URLEncoder.encode(Objects.toString(paramMap.get(key), ""), "utf-8")).append("&");
                }
                url = sb.toString();
            }
        }
        HttpUriRequest httpUriRequest = requestBuilder.setUri(url).build();
        CloseableHttpResponse response = execute(httpUriRequest);

        String result = EntityUtils.toString(response.getEntity(), charset);
        response.close();
        return result;
    }


    public static CloseableHttpResponse execute(HttpUriRequest request) {
        try {
            return httpClient.execute(request, HttpClientContext.create());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private static class HttpRequestRetryHandlerImpl implements HttpRequestRetryHandler {
        private int retryExecutionCount;

        public HttpRequestRetryHandlerImpl(int retryExecutionCount) {
            this.retryExecutionCount = retryExecutionCount;
        }

        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            if (executionCount > this.retryExecutionCount) {
                return false;
            } else if (exception instanceof InterruptedIOException) {
                return false;
            } else if (exception instanceof UnknownHostException) {
                return false;
            } else if (exception instanceof ConnectTimeoutException) {
                return true;
            } else if (exception instanceof SSLException) {
                return false;
            } else {
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                return idempotent;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        System.out.println(send("http://www.17wo.cn/"));
    }

}
