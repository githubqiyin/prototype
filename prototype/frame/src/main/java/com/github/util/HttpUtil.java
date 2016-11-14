package com.github.util;

import java.io.IOException;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import com.github.common.Code;
import com.github.exception.ServiceException;

public final class HttpUtil {

    private static final int CONNECT_TIMEOUT = 3000;

    private static final int CONNECTION_REQUEST_TIMEOUT = 10000;

    private static HttpUtil instance;

    private CloseableHttpClient httpClient;

    public static HttpUtil getInstance() {
        return instance != null ? instance : new HttpUtil();
    }

    private HttpUtil() {
        setHttpClient(initHttpClient());
    }

    public static CloseableHttpClient initHttpClient() {

        RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST)).setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).setSocketTimeout(5000)
                .setConnectTimeout(CONNECT_TIMEOUT).setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).build();

        return HttpClients.custom().setConnectionManager(initconnManager()).setDefaultRequestConfig(defaultRequestConfig).build();
    }

    public static PoolingHttpClientConnectionManager initconnManager() {

        Registry<ConnectionSocketFactory> socketFactory = RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(SSLContexts.createSystemDefault())).build();

        HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory = new ManagedHttpClientConnectionFactory(new DefaultHttpRequestWriterFactory(),
                new DefaultHttpResponseParserFactory());

        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactory, connFactory, new SystemDefaultDnsResolver());

        ConnectionConfig config = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE).setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8).build();

        connManager.setDefaultConnectionConfig(config);

        return connManager;
    }

    public static String doPost(String url, String message) throws Exception {
        return doPost(url, null, new StringEntity(message, Consts.UTF_8));
    }

    public static String doPost(String url, Map<String, String> headerParams, String message) throws Exception {
        // 消息头
        List<BasicHeader> headerList = new ArrayList<BasicHeader>();
        if (!headerParams.isEmpty()) {
            for (String key : headerParams.keySet()) {
                headerList.add(new BasicHeader(key, headerParams.get(key)));
            }
        }
        return doPost(url, headerList.toArray(new Header[] {}), new StringEntity(message, Consts.UTF_8));
    }

    public static String doPost(String url, Map<String, String> bodyParams) throws Exception {
        return doPost(url, null, bodyParams);
    }

    public static String doPost(String url, Map<String, String> headerParams, Map<String, String> bodyParams) throws Exception {
        // 消息头
        List<BasicHeader> headerList = new ArrayList<BasicHeader>();
        if (MapUtils.isNotEmpty(headerParams)) {
            for (String key : headerParams.keySet()) {
                headerList.add(new BasicHeader(key, headerParams.get(key)));
            }
        }
        // 消息体
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (String key : bodyParams.keySet()) {
            nvps.add(new BasicNameValuePair(key, bodyParams.get(key)));
        }
        return doPost(url, headerList.toArray(new Header[] {}), new UrlEncodedFormEntity(nvps, Consts.UTF_8));
    }

    public static String doPost(String url, Header[] headers, HttpEntity entity) throws Exception {

        CloseableHttpClient httpclient = HttpUtil.getInstance().getHttpClient();

        CloseableHttpResponse response = null;

        try {

            HttpPost httpPost = new HttpPost(url);

            // 消息头
            if (ArrayUtils.isNotEmpty(headers)) {
                httpPost.setHeaders(headers);
            }
            // 消息体
            httpPost.setEntity(entity);

            // 请求
            response = httpclient.execute(httpPost);

            // 响应
            int status = response.getStatusLine().getStatusCode();

            if (status < 200 || status >= 300) {
                throw new ServiceException(Code.NETWORK_ERROR);
            }

            return EntityUtils.toString(response.getEntity(), Consts.UTF_8);

        } catch (Exception e) {
            throw e;
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }
}