package saaf.common.fmw.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static saaf.common.fmw.services.CommonAbstractServices.*;

public class APClientUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(APClientUtils.class);
    private Properties properties = new Properties();
    //AP的IP地址
    public String apUrl = "";
    public String apUserName = "";
    public String apPassword = "";
    /**
     * 初始化参数
     */
    public APClientUtils(){
        InputStream in = ActClientUtils.class.getResourceAsStream("/wsConfig.properties");
        try {
            properties.load(in);
            this.apUrl = properties.getProperty("apUrl");
            this.apUserName = properties.getProperty("apUserName");
            this.apPassword = properties.getProperty("apPassword");
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOGGER.error("未知错误:{}", e);
                }
            }
        }
    }



    private String getToken () throws Exception{
        Long startTime = System.currentTimeMillis();
        JSONObject json = new JSONObject();
        JSONArray parameters = new JSONArray();
        JSONObject userName = new JSONObject();
        userName.put("Value",apUserName);
        JSONObject password = new JSONObject();
        password.put("Value",apPassword);
        parameters.add(userName);
        parameters.add(password);
        json.put("ApiType","AuthenticationController");
        json.put("Method","Login");
        json.put("Context",new JSONObject());
        json.put("Parameters",parameters);
        // http请求
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(apUrl);
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(300000).setConnectTimeout(300000).build();
        post.setConfig(requestConfig);
        //请求的body
        StringEntity body = new StringEntity(json.toJSONString(), ContentType.APPLICATION_JSON);
        //设置请求头参数
        post.addHeader("Content-Type","application/json;charset=UTF-8");
        post.addHeader("Charset","UTF-8");
        //post.addHeader("Authorization", token);
        // 设置请求体
        post.setEntity(body);
        // 响应内容
        String responseContent = null;
        CloseableHttpResponse response = null;
        JSONObject resultJsonObject = null;
        try {
            response = client.execute(post);
            long costMs = System.currentTimeMillis() - startTime;
            LOGGER.info("<————URL："+apUrl+"，返回数据————>：{}", JSON.toJSONString(response));
            LOGGER.info("<————{}-{}请求结束，耗时：{}ms————>", "AuthenticationController","Login", costMs);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                responseContent = EntityUtils.toString(entity, "UTF-8");
            }else{
                //请求失败，非200的状态码
                if (response != null){
                    response.close();
                }
                if (client != null){
                    client.close();
                }
                LOGGER.error("返回的错误信息:{}",EntityUtils.toString(response.getEntity(), "UTF-8"));
                return null;
            }

            if(null == responseContent || "".equals(responseContent)){
                return null;
            }else{
                resultJsonObject = JSONObject.parseObject(responseContent);
            }

            if(null == resultJsonObject){
                return null;
            }
            if(null == resultJsonObject.getJSONObject("Context")){
                return null;
            }
            return resultJsonObject.getJSONObject("Context").getString("Ticket");
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            return null;
        } finally {
            if (response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    LOGGER.error("未知错误:{}", e);
                }
            }
            if (client != null){
                try {
                    client.close();
                } catch (IOException e) {
                    LOGGER.error("未知错误:{}", e);
                }
            }
        }
    }


    /**
     * ZSY
     * @param apiType 控制层名称
     * @param parameters 业务参数
     * @param method 方法名
     * @return
     * @throws Exception
     */
    public JSONObject saveSupplierRestDateToApSystem(String apiType,JSONArray parameters,String method) throws Exception {
        Long startTime = System.currentTimeMillis();
        //返回结果
        JSONObject result = new JSONObject();
        String token = getToken();
        if(null == token || "".equals(token)){
            LOGGER.error("请求失败,token为空");
            result.put(STATUS, ERROR_STATUS);
            result.put(MSG, "请求失败,token为空");
            result.put(COUNT, 0);
            result.put(DATA, null);
            return result;
        }
        JSONObject json = new JSONObject();
        json.put("ApiType",apiType);
        json.put("Parameters",parameters);
        json.put("Method",method);
        JSONObject context = new JSONObject();
        context.put("Ticket",token);
        context.put("InvOrgId",1);
        json.put("Context",context);
        // http请求
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(apUrl);
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(300000).setConnectTimeout(300000).build();
        post.setConfig(requestConfig);
        //请求的body
        StringEntity body = new StringEntity(json.toJSONString(), ContentType.APPLICATION_JSON);
        //设置请求头参数
        post.addHeader("Content-Type","application/json;charset=UTF-8");
        post.addHeader("Charset","UTF-8");
        //post.addHeader("Authorization", token);
        // 设置请求体
        post.setEntity(body);
        // 响应内容
        String responseContent = null;
        CloseableHttpResponse response = null;
        JSONObject resultJsonObject = null;
        try {
            response = client.execute(post);
            long costMs = System.currentTimeMillis() - startTime;
            LOGGER.info("<————URL："+apUrl+"，返回数据————>：{}", JSON.toJSONString(response));
            LOGGER.info("<————{}-{}请求结束，耗时：{}ms————>", "AuthenticationController","Login", costMs);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                responseContent = EntityUtils.toString(entity, "UTF-8");
            }else{
                //请求失败，非200的状态码
                if (response != null){
                    response.close();
                }
                if (client != null){
                    client.close();
                }
                LOGGER.error("返回的错误信息:{}",EntityUtils.toString(response.getEntity(), "UTF-8"));
                result.put(STATUS, ERROR_STATUS);
                result.put(MSG, "请求失败,调用接口不是返回200");
                result.put(COUNT, 0);
                result.put(DATA, null);
                return result;
            }

            if(null == responseContent || "".equals(responseContent)){
                LOGGER.error("请求失败,responseContent为空");
                result.put(STATUS, ERROR_STATUS);
                result.put(MSG, "请求失败,responseContent为空");
                result.put(COUNT, 0);
                result.put(DATA, null);
                return result;
            }else{
                resultJsonObject = JSONObject.parseObject(responseContent);
            }

            if(null == resultJsonObject){
                LOGGER.error("请求失败,responseContent为空");
                result.put(STATUS, ERROR_STATUS);
                result.put(MSG, "请求失败,responseContent为空");
                result.put(COUNT, 0);
                result.put(DATA, null);
                return result;
            }
            if(!resultJsonObject.getBoolean("Success")){
                LOGGER.error("请求失败,Success返回false");
                result.put(STATUS, ERROR_STATUS);
                result.put(MSG, "请求失败,Success返回false");
                result.put(COUNT, 0);
                result.put(DATA, null);
                return result;
            }
            if(null == resultJsonObject.getJSONObject("Result")){
                LOGGER.error("请求失败,Result为空");
                result.put(STATUS, ERROR_STATUS);
                result.put(MSG, "请求失败,Result为空");
                result.put(COUNT, 0);
                result.put(DATA, null);
                return result;
            }
            if(!resultJsonObject.getJSONObject("Result").getBoolean("Success")){
                LOGGER.error(resultJsonObject.getJSONObject("Result").getString("Message"));
                result.put(STATUS, ERROR_STATUS);
                result.put(MSG, resultJsonObject.getJSONObject("Result").getString("Message"));
                result.put(COUNT, 0);
                result.put(DATA, null);
                return result;
            }
            result.put(STATUS, SUCCESS_STATUS);
            result.put(MSG, "请求成功");
            result.put(COUNT, 1);
            result.put(DATA, null);
            return result;
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("--------------------------->操作失败！参数：{}" , responseContent + "，异常：{}" , e);
            result.put(STATUS, ERROR_STATUS);
            result.put(MSG, "请求失败");
            result.put(COUNT, 0);
            result.put(DATA, null);
            return result;
        } finally {
            if (response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    LOGGER.error("未知错误:{}", e);
                }
            }
            if (client != null){
                try {
                    client.close();
                } catch (IOException e) {
                    LOGGER.error("未知错误:{}", e);
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            APClientUtils ap = new APClientUtils();
            JSONArray array = new JSONArray();
            JSONObject row = new JSONObject();
            JSONArray value = new JSONArray();
            JSONObject head = new JSONObject();
            head.put("SupplierCode","100663");
            JSONArray line = new JSONArray();
            line.add("2019-07-16 00:00:00");
            line.add("2019-07-17 00:00:00");
            head.put("RestDates",line);
            value.add(head);
            row.put("Value",value);
            array.add(row);
            ap.saveSupplierRestDateToApSystem("SupplierController",array,"ReceiveSupplierRestDateResult");
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
        }

    }

}
