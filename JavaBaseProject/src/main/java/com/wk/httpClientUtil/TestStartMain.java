package com.wk.httpClientUtil;

import com.wk.httpClientUtil.bean.test.Content;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.impl.client.CloseableHttpClient;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 *
 */
@Slf4j
public class TestStartMain {

    private static String trustKey = "F:\\github_code\\Mine\\springbootDemo\\HTTPS_Interface\\src\\main\\resources\\clienttrust.key";
    private static String trustKey2 = "D:\\certificate\\client\\clientTrust";
    private static String trustKeyPwd="123456";
    private static Integer socketTimeOut = 10000;
    private static Integer connectTimeout = 10000;

    private static String clientKey = "F:\\github_code\\Mine\\springbootDemo\\HTTPS_Interface\\src\\main\\resources\\sslclient.key";
    private static String clientKey2 = "D:\\certificate\\client\\client.keystore";
    private static String clientKeyPwd = "123456";

    /**
     *  绕过认证测试
     */
    public static void trustAll() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        // CloseableHttpClient httpSSLClient = ClientFactory.createHttpSSLClient(socketTimeOut, connectTimeout);
        // 带连接池的测试
        CloseableHttpClient httpSSLClient = ClientFactory.createHttpClientWithPoolNoAuth(socketTimeOut, connectTimeout);
        HttpConfig config = HttpConfig.instance().client(httpSSLClient)
                .methods(HttpMethods.GET)
                .url("https://10.154.129.144:9999/getUser");
        String s = HttpClientUtil.httpGetMethod(config);
        log.info("trustAll authentication..:"+s);
    }

    /**
     *  单向认证测试
     */
    public static void authenticationOne(){
        // CloseableHttpClient httpSSLClient = ClientFactory.createHttpSSLClient(trustKey, trustKeyPwd, socketTimeOut, connectTimeout);
        // 带连接池的测试
        CloseableHttpClient httpSSLClient = ClientFactory.createHttpSSLClientOneAuthenticateAndHostNameWithPool(clientKey2, trustKeyPwd,
                                                                                                socketTimeOut, connectTimeout);

        HttpConfig config = HttpConfig.instance().client(httpSSLClient)
                .methods(HttpMethods.GET)
                .url("https://169.254.110.194:9999/getUser");
        String s = HttpClientUtil.httpGetMethod(config);
        log.info("one way authentication..:"+s);
    }

    /**
     * 双向认证测试
     */
    public static void authenticationDouble(){
        /*CloseableHttpClient httpSSLClient = ClientFactory.createHttpSSLClient(clientKey, clientKeyPwd,
                trustKey, trustKeyPwd, socketTimeOut, connectTimeout);*/
        CloseableHttpClient httpSSLClient = ClientFactory.createHttpSSLClientDoubleAuthenticateAndHostNameWithPool(clientKey2,
                clientKeyPwd,
                trustKey, trustKeyPwd, socketTimeOut, connectTimeout);
        HttpConfig config = HttpConfig.instance().client(httpSSLClient)
                .methods(HttpMethods.GET)
                .url("https://169.254.110.194:9999/getUser");
        String s = HttpClientUtil.httpGetMethod(config);
        log.info("double authentication..:"+s);
    }
    // fd15:4ba5:5a2b:1008:3511:6c4f:3082:a429
    public static void sendRequestWithIpv6() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        CloseableHttpClient httpClient = ClientFactory.createHttpClientWithPoolNoAuth(10000, 10000);
        HttpConfig httpConfig = HttpConfig.instance().client(httpClient)
                .methods(HttpMethods.GET)
                .url("http://[fd15:4ba5:5a2b:1008:3511:6c4f:3082:a429]:8989/user/getuser");
        String result = HttpClientUtil.httpGetMethod(httpConfig);
        System.out.println("result is :"+result);
    }

    public static void sendChinese(){
        String url = "http://192.168.72.1:8989/user/chinese";
        String msg = "收到活动告警，告警标题：vim与omc关联，告警级别：3，告警发生时间：2019-13-18 16:15:14";
        Content content = new Content();
        content.setContent(msg);
        CloseableHttpClient httpClient = ClientFactory.httpClientPooled(10000, 10000);
        Header[] headers = HttpHeader.instance().contentType("application/json;charset=utf-8").build();
        HttpConfig config = HttpConfig.instance().methods(HttpMethods.POST)
                .url(url).header(headers).client(httpClient).beanParam(JSONUtil.beanToJson(content));
        Map<String, String> result = HttpClientUtil.httpPostMethodWithStatusCode(config);

        System.out.println(result.toString());
    }

    public static void  deleteWithEntity(){
        String url = "http://192.168.72.1:8989/user/del.do";
        String msg = "{\"sourceId\": \"030\",\"taskId\": 355}";
        Header[] headers = HttpHeader.instance().contentType(HttpHeader.Headers.TEXT_JSON_UTF8).build();
        CloseableHttpClient httpClient = ClientFactory.httpClientPooled(1000, 1000);
        HttpConfig config = HttpConfig.instance().url(url).client(httpClient).beanParam(msg).header(headers);
        Map<String, String> map = HttpClientUtil.httpDeleteMethodWithEntityWithStatusCode(config);
        System.out.println("result :"+ map.toString());
    }

    public static void  testPoolClient(int counts){
        String url = "http://192.168.72.1:8989/user/del.do";
        String msg = "{\"sourceId\": \"030\",\"taskId\": 355}";
        Header[] headers = HttpHeader.instance().contentType(HttpHeader.Headers.TEXT_JSON_UTF8).build();
        for (int i=0; i < counts; i++) {
            System.out.println("count : " + i);
            CloseableHttpClient httpClient = ClientFactoryWithPool.getHttpClient();
            HttpConfig config = HttpConfig.instance().url(url).client(httpClient).beanParam(msg).header(headers);
            Map<String, String> map = HttpClientUtil.httpDeleteMethodWithEntityWithStatusCode(config);
            System.out.println("result :" + map.toString());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        // trustAll();
        // authenticationOne();
//        authenticationDouble();
       // sendRequestWithIpv6();
//        sendChinese();
//        deleteWithEntity();
        testPoolClient(50);
    }
}
