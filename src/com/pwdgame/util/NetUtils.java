package com.pwdgame.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.text.TextUtils;
import android.util.Log;


public class NetUtils {
    private static HttpClient httpClient = new DefaultHttpClient();
//    private static final String BASE_URL = "http://119.254.110.241:80/";
    private static final String BASE_URL = "http://webim.demo.rong.io/";

    /**
     * 发送GET请求方法
     *
     * @param requestUrl 请求的URL
     * @return 响应的数据
     */
    public static String sendGetRequest(String requestUrl) {
        HttpGet httpGet = new HttpGet( requestUrl);
//        HttpGet httpGet = new HttpGet(BASE_URL + requestUrl);
        if (SharedPreferenceUtil.getSharedPreferences() != null) {
            httpGet.addHeader("cookie", SharedPreferenceUtil.getRecordString("DEMO_COOKIE"));
        }else{
            Log.e("", "0313----yb DEMO_COOKIE  null ----:");
        }
        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
//                response.addHeader("cookie", DemoContext.getInstance().getSharedPreferences().getString("DEMO_COOKIE", null));
                getCookie(httpClient);
//                return entity.getContent();  //当需要返回为输入流InputStream时的返回值
                return EntityUtils.toString(entity); // 当返回的类型为Json数据时，调用此返回方法
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送post请求
     *
     * @param requestUrl 请求的URL
     * @param params     请求的参数
     * @return 响应的数据
     */
    public static String sendPostRequest(String requestUrl, Map<String, String> params) {

        HttpPost httpPost = new HttpPost(BASE_URL + requestUrl);
        if (SharedPreferenceUtil.getSharedPreferences()  != null) {
//            httpGet.addHeader("cookie","PHPAUTH=zEL1jDQc2rkuZHxN65gjEGWvWBWauB8pVMWty9fGioc%3D");
            httpPost.addHeader("cookie", SharedPreferenceUtil.getRecordString("DEMO_COOKIE"));
        }
        try {
            if (params != null && params.size() > 0) {
                List<NameValuePair> paramLists = new ArrayList<NameValuePair>();
                for (Entry<String, String> map : params.entrySet()) {
                    paramLists.add(new BasicNameValuePair(map.getKey(), map.getValue()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(paramLists, "UTF-8"));
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
//                response.addHeader("cookie", DemoContext.getInstance().getSharedPreferences().getString("DEMO_COOKIE", null));

                getCookie(httpClient);
//                return entity.getContent();  //当需要返回为输入流InputStream时的返回值
                return EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            System.out.println(BASE_URL + requestUrl);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得cookie
     *
     * @param httpClient
     */
    public static void getCookie(HttpClient httpClient) {
        List<Cookie> cookies = ((AbstractHttpClient) httpClient).getCookieStore().getCookies();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cookies.size(); i++) {
            Cookie cookie = cookies.get(i);
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();
            if (!TextUtils.isEmpty(cookieName)
                    && !TextUtils.isEmpty(cookieValue)) {
                sb.append(cookieName + "=");
                sb.append(cookieValue + ";");
            }
        }
        SharedPreferenceUtil.setRecordString("DEMO_COOKIE", sb.toString());
    }

}
