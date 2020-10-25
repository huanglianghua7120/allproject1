package saaf.common.fmw.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.MediaType;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpConnectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpConnectionUtil.class);

    private boolean doOutput = true;
    private boolean doInput = true;
    private boolean useCaches = false;
    private String method = "POST";
    private String charset = "UTF-8";
    private String contentType = MediaType.APPLICATION_JSON;
    private String connection = "Keep-Alive";
    private String sessionId = null;

    /**
     * 调用http请求
     * @param pathUrl 请求URL
     * @param params 参数
     * @return
     */
    public String httpUrlConnection(String pathUrl, String params) {
        LOGGER.info("--------->>pathUrl:" + pathUrl + ",params:" + params);
        HttpURLConnection httpConn = null;
        try {
            // 建立连接
            URL url = new URL(pathUrl);
            httpConn = (HttpURLConnection)url.openConnection();

            // //设置连接属性
            httpConn.setDoOutput(doOutput); // 使用 URL 连接进行输出
            httpConn.setDoInput(doInput); // 使用 URL 连接进行输入
            httpConn.setUseCaches(useCaches); // 忽略缓存
            httpConn.setRequestMethod(method); // 设置URL请求方法
            if (sessionId != null) {
                httpConn.setRequestProperty("Cookie", sessionId);
            }

            if ("POST".equals(method)) {
                // 设置请求属性
                // 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
                byte[] paramsBytes = params.getBytes(charset);
                httpConn.setRequestProperty("Content-length", String.valueOf(paramsBytes.length));
                httpConn.setRequestProperty("Content-Type", contentType);
                httpConn.setRequestProperty("Connection", connection); // 维持长连接
                httpConn.setRequestProperty("Charset", charset);

                // 建立输出流，并写入数据
                OutputStream outputStream = httpConn.getOutputStream();
                outputStream.write(paramsBytes);
                outputStream.close();
            }

            // 获得响应状态
            int responseCode = httpConn.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) { // 连接成功
                // 当正确响应时处理数据
                StringBuffer sb = new StringBuffer();
                String readLine;
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), charset));
                while ((readLine = responseReader.readLine()) != null) {
                    sb.append(readLine).append("\n");
                }
                httpConn.getInputStream().close();
                responseReader.close();

                String cookieValue = httpConn.getHeaderField("Set-Cookie");
                if (cookieValue != null) {
                    sessionId = cookieValue.substring(0, cookieValue.indexOf(";"));
                    LOGGER.info("--------->>sessionId:" + sessionId);
                }

                return sb.toString();
            } else {
                return "HTTP-CONN-ERROR: 请求失败，响应状态编号：" + responseCode;
            }
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return "HTTP-CONN-ERROR: " + e.getMessage();
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }

    }

    /**
     * 获取引用URL的sessionId并写入当前session中
     * @param request
     * @param sessionAttrName 保存在session中的属性名称例如调度系统sessionId为：scheduleSessionId
     * @param pathUrl
     * @param params
     * @return
     */
    public String httpUrlConnection(HttpServletRequest request, String sessionAttrName, String pathUrl, String params) {
        setSessionId(String.valueOf(request.getSession(false).getAttribute(sessionAttrName)));
        String result = httpUrlConnection(pathUrl, params);
        request.getSession(false).setAttribute(sessionAttrName, getSessionId());
        return result;
    }

    /**
     * xml格式的字符串转换成JSONObject
     * @param xml
     * @return
     */
    public JSONObject xmlToJson(String xml) {
        JSONObject json = new JSONObject();
        try {
            Document doc = DocumentHelper.parseText(xml);
            Element element = doc.getRootElement();

            dom4j2Json(element, json);
        } catch (DocumentException e) {
            //e.printStackTrace();
        }
        return json;
    }

    public void dom4j2Json(Element element, JSONObject json) {
        List<Element> chdEl = element.elements();
        for (Element e : chdEl) { //有子元素
            if (!e.elements().isEmpty()) { //子元素也有子元素
                JSONObject chdjson = new JSONObject();
                dom4j2Json(e, chdjson);
                Object o = json.get(e.getName());
                if (o != null) {
                    JSONArray jsona = null;
                    if (o instanceof JSONObject) { //如果此元素已存在,则转为jsonArray
                        JSONObject jsono = (JSONObject)o;
                        json.remove(e.getName());
                        jsona = new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if (o instanceof JSONArray) {
                        jsona = (JSONArray)o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                } else {
                    if (!chdjson.isEmpty()) {
                        json.put(e.getName(), chdjson);
                    }
                }

            } else { //子元素没有子元素
                if (!e.getText().isEmpty()) {
                    json.put(e.getName(), e.getText());
                }
            }
        }
    }


    public boolean isDoOutput() {
        return doOutput;
    }

    public void setDoOutput(boolean doOutput) {
        this.doOutput = doOutput;
    }

    public boolean isDoInput() {
        return doInput;
    }

    public void setDoInput(boolean doInput) {
        this.doInput = doInput;
    }

    public boolean isUseCaches() {
        return useCaches;
    }

    public void setUseCaches(boolean useCaches) {
        this.useCaches = useCaches;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public static void main(String[] args) {
        //		String url = "http://100.100.70.51:7004/cmsDispatcherServlet.ims?methodName=areaService";
        //		String params = "{areaCode:'13050',lastUpdateDate: '2014-11-01'}";
        //		String url = "http://localhost:8080/ScheduleApp/restServer/scheduleServices/findRequests";
        //		String params = "params={\"varUserName\":\"sysadmin\",\"varUserId\":1,\"varPlatformCode\":\"AUX\",\"varIsAdmin\":\"Y\",\"varInstIdData\":\"34,54,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106\"}&pageIndex=1&pageRows=23&user=dBO2IzqFgNUy8qruaYFmSg==&pwd=74CXPOAvqbV1aVrDHSvyGA==";
        String url = "http://100.100.2.218:9080/auxcssws/services/SearchCardInstalled.SearchCardInstalledHttpEndpoint/";
        String params =
            "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ser=\"http://service.searchCardInstalled.svac.ws.css.ermsuite.neusoft.com\" xmlns:xsd=\"http://vo.searchCardInstalled.svac.ws.css.ermsuite.neusoft.com/xsd\">" +
            "<soap:Header/>" + "<soap:Body>" + "<ser:doService>" + "<ser:request>" + "<xsd:endDate>2017-01-04 00:00:00</xsd:endDate>" +
            "<xsd:startDate>2017-01-03 00:00:00</xsd:startDate>" + "<xsd:sysType>CM</xsd:sysType>" + "<xsd:userVo>" + "<xsd:password>123456</xsd:password>" +
            "<xsd:user_code>CMS</xsd:user_code>" + "</xsd:userVo>" + "</ser:request>" + "</ser:doService>" + "</soap:Body>" + "</soap:Envelope>";
        HttpConnectionUtil httpConn = new HttpConnectionUtil();
        //		httpConn.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String sr = httpConn.httpUrlConnection(url, params);
        //String sr = httpConn.xmlWebseivice(url, params, null);
        System.out.println("sr:" + sr);
        //System.out.println(httpConn.xmlToJson(sr));		
    }

}
