package saaf.common.fmw.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
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
import saaf.common.fmw.base.utils.enums.ESBParams;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import static saaf.common.fmw.services.CommonAbstractServices.*;

/**
 * @author: ZHJ
 * @date:Created in 10:34 2019/10/18
 * @modify By:
 * @description :
 */
public class ESBClientUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ESBClientUtils.class);
    private Properties properties = new Properties();
    private  AouthClient aouthClient = new AouthClient();
    //ESB的IP地址
    public String esbUrl = "";
    /**
     * 初始化参数
     */
    public ESBClientUtils(){
        InputStream in = ActClientUtils.class.getResourceAsStream("/wsConfig.properties");
        try {
            properties.load(in);
            this.esbUrl = properties.getProperty("esbUrl");
        } catch (FileNotFoundException e) {
            LOGGER.error("未知错误:{}", e);
        } catch (IOException e) {
            LOGGER.error("未知错误:{}", e);
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
    /**
     * 发post请求访问指定方法，构造函数
     * @param application 应用名称
     * @param sourceSystem 源系统(PCB_APS/PCBA_APS/SRM/E-SOURCE)
     * @param targetSystem 目标系统（ESB、EBS、INPLAN）
     * @param serviceName 服务接口程序名
     * @param businessServiceName 实际调用接口程序名
     * @param serviceOperation 服务操作类型{"S":"查询","E":"执行"}
     * @param baseQueryParams 通用查询参数(全量、增量接口)
     *                        参数名称 字符类型 格式 描述
                            organizationId number 库存组织
                            lastUpdateDateF String 最后更新日期起  yyyy-MM-dd HH:mm:ss/yyyy-MM-dd
                            lastUpdateDateT String 最后更新日期至  yyyy-MM-dd HH:mm:ss/yyyy-MM-dd
                            startPosition number 起始行号位置
                            rowsCnt number 请求行数[0-2000]之间
     * @param businessData 具体业务参数——json数组
     *                     参数名称 字符类型 格式 描述 是否必填
     *                     注: 区分具体接口具体分析。结果返回先参照之前的接口表
     * @return
     */
    public JSONObject doPost(String application,String sourceSystem,String targetSystem,String serviceName,String businessServiceName,String serviceOperation,JSONObject baseQueryParams,JSONArray businessData){
        LOGGER.info("<————请求开始，执行方法————>：{}",serviceName);
        //返回结果
        JSONObject result = new JSONObject();
        JSONObject tokenJSON = this.getToken();
        if(null == tokenJSON || "".equals(tokenJSON)){
            result.put(STATUS, ERROR_STATUS);
            result.put(MSG, "token为空，请求失败");
            result.put(COUNT, 0);
            result.put(DATA, null);
            return result;
        }
        String token = tokenJSON.getString("token");
        String access_token = tokenJSON.getString("access_token");
        //方法执行开始时间
        Long startTime = System.currentTimeMillis();
        JSONObject json = new JSONObject();
        json.put("baseQueryParams",baseQueryParams);
        json.put("businessData",businessData);
        //系统参数
        JSONObject systemParams = new JSONObject();
        //当前的时间戳
        Long timestamp = System.currentTimeMillis();
        Integer nonce = (int)((Math.random()*9+1)*100000);
        String requestId = application+serviceName+businessServiceName+nonce+timestamp;
        String serviceVersion = "1.0";
        systemParams.put("application",application);
        systemParams.put("sourceSystem",sourceSystem);
        systemParams.put("targetSystem",targetSystem);
        systemParams.put("serviceName",serviceName);
        if(null != businessServiceName && !"".equals(businessServiceName)){
            systemParams.put("businessServiceName",businessServiceName);
        }
        systemParams.put("serviceOperation",serviceOperation);
        systemParams.put("nonce",nonce);
        systemParams.put("timestamp",timestamp);
        systemParams.put("serviceVersion",serviceVersion);
        systemParams.put("requestId",requestId);
        systemParams.put("token",token);
        json.put("systemParams",systemParams);
        //请求总线的URL
        String url = esbUrl+"?access_token="+access_token;
        result.put("esbUrl",url);
        //请求的requestId
        result.put("requestId", requestId);
        //请求的baseQueryParams
        result.put("baseQueryParams",baseQueryParams);
        LOGGER.info("<————URL：{}，业务请求接口————>：{}，请求报文————>：{}",url,serviceName+businessServiceName,json);
        if(null == token || "".equals(token)){
            LOGGER.error("token为空，请求失败；请求参数:{}",result);
            result.put(STATUS, ERROR_STATUS);
            result.put(MSG, "token为空，请求失败");
            result.put(COUNT, 0);
            result.put(DATA, null);
            return result;
        }
        // http请求
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(300000).setConnectTimeout(300000).build();
        post.setConfig(requestConfig);
        //请求的body
        StringEntity body = new StringEntity(json.toJSONString(),ContentType.APPLICATION_JSON);
        //设置请求头参数
        post.addHeader("Content-Type","application/json;charset=UTF-8");
        post.addHeader("Charset","UTF-8");
        post.addHeader("Authorization", token);
        // 设置请求体
        post.setEntity(body);
        // 响应内容
        String responseContent = null;
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
            long costMs = System.currentTimeMillis() - startTime;
            LOGGER.info("<————URL："+url+"，返回数据————>：{}",JSON.toJSONString(response));
            LOGGER.info("<————{}-{}请求结束，耗时：{}ms————>", serviceName,businessServiceName, costMs);
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
                String errorMsg = EntityUtils.toString(response.getEntity(), "UTF-8");
                LOGGER.error("返回的错误信息:{}",errorMsg);
                result.put(STATUS, ERROR_STATUS);
                result.put(MSG, "请求失败，错误："+errorMsg);
                result.put(COUNT, 0);
                result.put(DATA, null);
                return result;
            }
            result.put(STATUS, SUCCESS_STATUS);
            result.put(MSG, "操作成功");
            result.put(COUNT, 1);
            if(null == responseContent || "".equals(responseContent)){
                result.put(DATA, null);
            }else{
                result.put(DATA, JSONObject.parseObject(responseContent));
            }
            return result;
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            LOGGER.error("--------------------------->操作失败！参数：{}" , result.toString() + "，异常：{}" , e);
            result.put(STATUS, ERROR_STATUS);
            result.put(MSG, "操作失败");
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
    /**
     * 获取Token
     * @return
     */
    public JSONObject getToken() {
        LOGGER.info("====================getToken==============");
        JSONObject result = null;
        String token = null;
        JSONObject json = JSONObject.parseObject(aouthClient.getToken());
        LOGGER.info("<————token，返回数据————>：{}",JSON.toJSONString(json));
        if(null != json && null != json.getString(STATUS) && SUCCESS_STATUS.equals(json.getString(STATUS))){
            JSONObject data = json.getJSONObject(DATA);
            if(null != data){
                result = new JSONObject();
                token = data.getString("token_type")+" "+data.getString("access_token");
                result.put("token",token);
                result.put("access_token",data.getString("access_token"));
            }
        }
        return result;
    }
    //测试类
    public static void main(String[] args) {
        ESBClientUtils esb = new ESBClientUtils();
        //测试银行接口
        JSONObject baseQueryParams = new JSONObject();
        baseQueryParams.put("lastUpdateDateF","2010-09-20");
        JSONArray businessData = new JSONArray();
        JSONObject data = new JSONObject();
        data.put("LAST_UPDATE_DATE_F","2019-11-01 14:10:58");
        data.put("LAST_UPDATE_DATE_T", DateUtil.date2Str(new Date(),"yyyy-MM-dd HH:mm:ss"));
        businessData.add(data);
//        JSONObject result = esb.doPost(ESBParams.SystemName.PCB_SRM.getValue(), ESBParams.SystemName.PCB_APS.getValue(), ESBParams.SystemName.EBS.getValue(), ESBParams.ServiceName.callSrmProcedure.getValue(),ESBParams.BusinessServiceName.REST_SRM_PULLBANKLIST.getValue(),"S",baseQueryParams,businessData);
//        System.out.println("银行信息：");
//        System.out.println(result);
//        JSONObject result2 = esb.doPost(ESBParams.SystemName.PCB_SRM.getValue(), ESBParams.SystemName.PCB_APS.getValue(), ESBParams.SystemName.EBS.getValue(), ESBParams.ServiceName.callSrmProcedure.getValue(),ESBParams.BusinessServiceName.REST_SRM_PULLBKBBRHLIST.getValue(),"S",baseQueryParams,businessData);
//        System.out.println("银行分行：");
//        System.out.println(result2);
        JSONObject result3 = esb.doPost(ESBParams.SystemName.PCB_SRM.getValue(), ESBParams.SystemName.PCB_APS.getValue(), ESBParams.SystemName.EBS.getValue(), ESBParams.ServiceName.callSrmProcedure.getValue(),ESBParams.BusinessServiceName.REST_SRM_PUSHPOREQLIST.getValue(),"S",baseQueryParams,businessData);
        System.out.println("采购订单：");
        System.out.println(result3);

        JSONObject data1 = result3.getJSONObject("data");
        JSONObject jsonObject = data1.getJSONObject("obj");
        JSONArray dataArray = jsonObject.getJSONArray("businessData");

        System.out.println(dataArray);
    }
}