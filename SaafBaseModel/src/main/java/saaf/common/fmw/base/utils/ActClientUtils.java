package saaf.common.fmw.base.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author: ZHJ
 * @date:Created in 13:41 2019/3/16
 * @modify By:
 * @description : 工作流引擎接口工具
 */
public class ActClientUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActClientUtils.class);
    //工作流引擎IP地址
    public String actUrl = "";
    //启动流程
    public String startFlow = "";
    //获取代办列表：例如 backlog/{userName}
    public String backlog = "";
    //获取已办列表
    public String haveDoneLog="";
    //我提交的流程
    public String findMyProcess="";
    //审批通过
    public String submitFlow = "";
    //获取审批历史信息：例如 commentList/{busId}
    public String commentList = "";
    //终止流程
    public String backStartUser = "";
    //撤销流程
    public String cancelFailFlow = "";
    //获取实时流程图：例如：getShineProcImage/{busId}
    public String getShineProcImage = "";
    //转办流程
    public String transfer = "";
    //发起沟通
    public String communication = "";
    //加签
    public String complaint = "";
    //获取可驳回节点列表：getBackAvtivity/{busId}
    public String getBackAvtivity = "";
    //驳回到指定节点
    public String backProcess = "";
    //查询当前用户是否拥有任务审批权限，例如：behavior/{busId}/{activeUser}
    public String behavior ="";
    public Properties properties = new Properties();
    //采购订单、框架协议的表名，用于生成业务ID的前缀，例如：srm_po_headers-502
    public static String poHeadersTableName = "srm_po_headers-";
    //供应商的表名，用于生成业务ID的前缀，例如：srm_pos_supplier_info-502
    public static String posSupplierInfoTableName = "srm_pos_supplier_info-";
    //供应商引入头层的表名，用于生成业务ID的前缀，例如：srm_pos_supplier_info-502
    public static String SRM_POS_INTRODUCTION_HEADER = "srm_pos_introduction_header-";
    //8D报告头层的表名，用于生成业务ID的前缀，例如：srm_qco_eightd_head-502
    public static String SRM_QCO_EIGHTD_HEAD = "srm_qco_eightd_head-";
    //异常反馈报告头层的表名，用于生成业务ID的前缀，例如：srm_qco_improve_head-502
    public static String SRM_QCO_IMPROVE_HEAD = "srm_qco_improve_head-";
    //质量预警报告头层的表名，用于生成业务ID的前缀，例如：srm_qco_qual_warn_head-502
    public static String SRM_QCO_QUAL_WARN_HEAD = "srm_qco_qual_warn_head-";

    /**
     * 初始化参数
     */
    public ActClientUtils(){
        InputStream in = ActClientUtils.class.getResourceAsStream("/wsConfig.properties");
        try {
            properties.load(in);
            this.actUrl = properties.getProperty("act_url");
            this.startFlow = properties.getProperty("start_flow");
            this.backlog = properties.getProperty("back_log");
            this.submitFlow = properties.getProperty("submit_flow");
            this.commentList = properties.getProperty("comment_list");
            this.backStartUser = properties.getProperty("back_start_user");
            this.cancelFailFlow = properties.getProperty("cancel_fail_flow");
            this.getShineProcImage = properties.getProperty("get_shine_proc_image");
            this.transfer = properties.getProperty("transfer");
            this.communication = properties.getProperty("communication");
            this.complaint = properties.getProperty("complaint");
            this.getBackAvtivity = properties.getProperty("get_back_avtivity");
            this.backProcess = properties.getProperty("back_process");
            this.behavior = properties.getProperty("behavior");
            this.haveDoneLog = properties.getProperty("have_done_log");
            this.findMyProcess = properties.getProperty("find_my_process");
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
     * 发post请求访问act指定方法，构造函数
     * @param method 方法名
     * @param jsonObject 传入的参数
     * @return
     */
    public ClientResponse doPost(String method, JSONObject jsonObject){
        LOGGER.info("<————请求开始，执行方法————>：{}",actUrl+method);
        //方法执行开始时间
        Long startTime = System.currentTimeMillis();
        LOGGER.info("<————URL："+actUrl+method+"，请求报文————>：{}",jsonObject);
        ClientConfig cc = new DefaultClientConfig();
        cc.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 120 * 1000);
        Client client = Client.create(cc);
        WebResource resource = client.resource(actUrl+method);
        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post(ClientResponse.class,jsonObject);
        client.destroy();
        long costMs = System.currentTimeMillis() - startTime;
        LOGGER.info("<————{}请求结束，耗时：{}ms————>", actUrl+method, costMs);
        return response;
    }

    /**
     * 发get请求访问指定方法，构造函数
     * @param method 方法名
     * @param jsonObject 传入的参数
     * @return
     */
    public ClientResponse doGet(String method,JSONObject jsonObject){
        LOGGER.info("<————请求开始，执行方法————>：{}",actUrl+method);
        //方法执行开始时间
        Long startTime = System.currentTimeMillis();
        LOGGER.info("<————URL："+actUrl+method+"，请求报文————>：{}",jsonObject);
        ClientConfig cc = new DefaultClientConfig();
        cc.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 120 * 1000);
        Client client = Client.create(cc);
        WebResource resource = client.resource(actUrl+method);
        ClientResponse response = resource.queryParams(parseJSON2Map(jsonObject)).accept(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        client.destroy();
        long costMs = System.currentTimeMillis() - startTime;
        LOGGER.info("<————{}请求结束，耗时：{}ms————>", actUrl+method, costMs);
        return response;
    }

    /**
     * 发post请求访问doPost方法，返回json对象
     * @param method 方法名
     * @param jsonObject 传入的参数
     * @return
     */
    public JSONObject callPostFlowBusiness(String method,JSONObject jsonObject){
        JSONObject json = new JSONObject();
        ClientResponse response = doPost(method,jsonObject);
        if(null == response){
            json.put("success",false);
            LOGGER.error("<————URL："+actUrl+method+"，请求错误，返回数据————>：{}",json);
            return json;
        }
        String result = response.getEntity(String.class);
        json = JSONObject.parseObject(result);
        if(200 != response.getStatus()){
            json.put("success",false);
            LOGGER.error("<————URL："+actUrl+method+"，请求错误，返回数据————>：{}",json);
            return json;
        }else{
            //success为true表示请求成功
            json.put("success",true);
            LOGGER.info("<————URL："+actUrl+method+"，返回数据————>：{}",json);
            return json;
        }
    }

    /**
     * 发get请求访问doGet方法，返回json对象
     * @param method 方法名
     * @param jsonObject 传入的参数
     * @return
     */
    public JSONObject callGetFlowBusiness(String method,JSONObject jsonObject){
        JSONObject json = new JSONObject();
        ClientResponse response = doGet(method,jsonObject);
        if(null == response){
            json.put("success",false);
            LOGGER.error("<————URL："+actUrl+method+"，请求错误，返回数据————>：{}",json);
            return json;
        }
        String result = response.getEntity(String.class);
        json = JSONObject.parseObject(result);
        if(200 != response.getStatus()){
            json.put("success",false);
            LOGGER.error("<————URL："+actUrl+method+"，请求错误，返回数据————>：{}",json);
            return json;
        }else{
            //success为true表示请求成功
            json.put("success",true);
            LOGGER.info("<————URL："+actUrl+method+"，返回数据————>：{}",json);
            return json;
        }
    }
    /**
     * 将json对象转换为MultivaluedMap
     * @param jsonObject
     * @return
     */
    public static MultivaluedMap parseJSON2Map(JSONObject jsonObject){
        MultivaluedMap map = new MultivaluedMapImpl();
        if(null == jsonObject || "".equals(jsonObject)){
            return map;
        }
        for(Map.Entry<String,Object> entry : jsonObject.entrySet()){
            Object obj = entry.getValue();
            //如果内层还是数组的话，继续解析
            if(obj instanceof JSONArray){
                List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
                Iterator<Object> it = ((JSONArray) obj).iterator();
                while (it.hasNext()){
                    JSONObject json = (JSONObject)it.next();
                    list.add(parseJSON2Map(json));
                }
                map.add(entry.getKey(),obj);
            }else{
                map.add(entry.getKey(),obj);
            }
        }
        return map;
    }
    /**
     * 流程任务审批行为
     */
    public enum ActTaskResult {
        /**
         * 提交=0
         */
        SUBMIT("提交"),
        /**
         * 同意=1
         */
        AGREE("同意"),
        /**
         * 终止=2，终止流程
         */
        //TERMINATION("终止"),
        TERMINATION("驳回"),
        /**
         * 沟通=3
         */
        COMMUNICATION("沟通"),
        /**
         * 驳回=4，驳回到指定节点
         */
        TURN_DOWN("驳回"),
        /**
         * 转办=5
         */
        TURN_DO("转办"),

        /**
         * 加签=6
         */
        COMPLAINT("加签"),
        /**
         * 撤销=7
         */
        Cancel("撤销");
        private String value;

        private ActTaskResult(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    /**
     * 流程状态
     */
    public enum ActStauts {
        /**
         * 草稿
         */
        DRAFT("1"),
        /**
         * 审批中
         */
        APPROVAL("2"),
        /**
         * 结束
         */
        END("3"),
        /**
         * 撤销
         */
        Cancel("4");

        private String value;

        private ActStauts(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    //测试main
    public static void main(String[] args) {
        ActClientUtils act = new ActClientUtils();
        JSONObject jsonObject = new JSONObject();
        /**
         * 启动流程
         */
        //业务ID
/*        jsonObject.put("busId",2);
        //业务分类标识
        jsonObject.put("actKey","PurchaseOrderProcess");
        //提交者
        jsonObject.put("startUserLoginName","sysadmin");
        jsonObject.put("flowTitle","采购订单审批");
        jsonObject.put("busUrl","/test");
        jsonObject.put("code", UUID.randomUUID());
        Map<String,Object> map = new HashMap<>();
        map.put("money",2999);
        jsonObject.put("variables",map);
        ClientResponse response = act.doPost(act.startFlow+"?tokenCommend=j42L1O90x31bmqYJOyH1H15i8zV59t1553241360620",jsonObject);*/
        /**
         * 获取代办列表
         */
/*        jsonObject.put("pageNum",1);
        jsonObject.put("pageSize",120);
        ClientResponse response = act.doPost(act.backlog+"sysadmin",jsonObject);*/

        /**
         * 获取实时流程图
         */
       /* ClientResponse response = act.doGet(act.getShineProcImage+"1",null);
        System.out.println("状态码："+response.getStatus());
        System.out.println("信息："+response.getEntity(String.class));
        JSONObject json = act.callGetFlowBusiness(act.getShineProcImage+"1000",null);
        System.out.println("信息："+json.toString());*/
    }
}
