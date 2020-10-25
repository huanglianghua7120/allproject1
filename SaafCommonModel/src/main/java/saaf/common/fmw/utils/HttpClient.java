package saaf.common.fmw.utils;


import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class HttpClient {
    /**
     * JAVA HttpClient模拟post请求
     * @param url
     * @param map
     * @return
     */
    public static String postForm(String url, Map<String, Object> map) {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();

        for (Map.Entry entity : map.entrySet()) {
            formparams.add(new BasicNameValuePair(entity.getKey().toString(), entity.getValue() == null ? "" : entity.getValue().toString()));
        }
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, "UTF-8");
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            return "{\"status\":\"F\",\"msg\":\"post请求失败:" + e + "\"}";
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        //http://localhost:8080/frontUIWebapp/restServer/photoMessageService/send
        //http://localhost:8080/JinYiApp/restServer/cmsPushMessageService/sendPushSms
        String url = "http://localhost:8080/JinYiApp/restServer/cinActorMessage/findLov";
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject json = new JSONObject();
        //        json.put("pProfileNumber", "TICKET_TIME");
        //        json.put("MemberCardNumber", "343604429497");
        //
        map.put("params", json);
        map.put("pageIndex", 0);
        map.put("pageRows", -1);
        System.out.println("====resultData====" + postForm(url, map));


    }
}

