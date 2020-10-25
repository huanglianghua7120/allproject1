package saaf.common.fmw.base.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
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
import org.springframework.stereotype.Component;
import saaf.common.fmw.services.CommonAbstractServices;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @Description: (获取总线token)
 * @Auther: LiPengFei
 * @Date: 2019/10/25 16:22
 */
@Component
public class AouthClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(AouthClient.class);
    public String accessTokenUrl ;
    public String oauthUsername;
    public String oauthPassword;

    public AouthClient(){

        try {
            InputStream in = AouthClient.class.getResourceAsStream("/AouthConfig.properties");
            Properties properties = new Properties();
            properties.load(in);
            accessTokenUrl = properties.getProperty("ACCESS_TOKEN_URL");
            oauthUsername = properties.getProperty("OAUTH_USERNAME");
            oauthPassword = properties.getProperty("OAUTH_PASSWORD");
        } catch (IOException e) {
            LOGGER.error("未知错误:{}", e);
        }
    }
    /**
     * 构造Basic Auth认证头信息
     *
     * @return
     */
    private String getHeader() {
        String auth = oauthUsername + ":" + oauthPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }
    /**
     * 功能描述: 获取token
     * @param: []
     * @return: java.lang.String
     * @auther: LiPengFei
     * @date: 2019/10/30 15:11
     */
    public String getToken() {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(accessTokenUrl);
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(300000).setConnectTimeout(300000).build();
        post.setConfig(requestConfig);
        // 构造请求数据
        StringEntity myEntity = new StringEntity("Content-Type", ContentType.APPLICATION_JSON);
        post.addHeader("Authorization", getHeader());
        post.setEntity(myEntity); // 设置请求体
        String responseContent = null; // 响应内容
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
            System.out.println(JSON.toJSONString(response));
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                responseContent = EntityUtils.toString(entity, "UTF-8");
            }
            if (response != null){
                response.close();
            }
            if (client != null){
                client.close();
            }
            return CommonAbstractServices.convertResultJSONObj("S", "获取token成功", 0, responseContent);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            return CommonAbstractServices.convertResultJSONObj("E", "获取token失败", 0, null);
        }
    }
}
