package saaf.common.fmw.base.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.inter.IActClient;
import saaf.common.fmw.base.utils.ActClientUtils;
import saaf.common.fmw.base.utils.UtilsException;

import java.util.Map;
import java.util.UUID;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;
import static saaf.common.fmw.services.CommonAbstractServices.SUCCESS_STATUS;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ActClientServer.java
 * Description：工作流引擎类
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-07-29     zhj             创建
 * ==============================================================================
 */
@Component("actClientServer")
public class ActClientServer implements IActClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActClientServer.class);
    public static final String SUCCESS = "success";
    public static final String CODE = "code";
    public static final String ZERO = "0";
    private ActClientUtils act = new ActClientUtils();
    /**
     * Description：流程提交至工作流引擎，流程当前状态 ：ActClientUtils.ActStauts, DRAFT("1")-草稿,APPROVAL("2")-审批中,END("3")-结束,Cancel("4")-撤销;
     * @param userName 流程提交者的userName
     * @param actKey 流程定义的actKey
     * @param busId  业务ID ，必填，格式是：表名-主键ID，例如：srm_po_headers-502
     * @param flowTitle 业务标题
     * @param busUrl  业务请求地址URL
     * @param variables 流程变量
     * @param expressionParameter 如果下一个节点办理人是指定角色并且是通过SQL或URL获取角色用户，SQL或URL对应的参数
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-07-29     zhj             创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveSubmitToWorkFlow(String userName, String actKey, String busId, String flowTitle, String busUrl, Map<String, Object> variables, Map<String, Object> expressionParameter) {
        JSONObject json = new JSONObject();
        json.put("startUserLoginName",userName);
        json.put("actKey",actKey);
        json.put("busId",busId);
        json.put("flowTitle",flowTitle);
        json.put("busUrl",busUrl);
        json.put("code", UUID.randomUUID());
        if(null != variables && variables.size()>0){
            json.put("variables",variables);
        }
        if(null != expressionParameter && expressionParameter.size()>0){
            json.put("expressionParameter",expressionParameter);
        }
        LOGGER.info("流程提交的json:{}",json);
        JSONObject result = act.callPostFlowBusiness(act.startFlow,json);
        LOGGER.info("流程提交返回的数据：{}",result);
        if(result.getBoolean(SUCCESS)){
            if(null != result.getString(CODE) && ZERO.equals(result.getString(CODE))){
                //流程当前当前状态：ActClientUtils.ActStauts
                json.put("status",result.getString("status"));
                return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "提交成功", 1, json);
            }else{
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
            }
        }else{
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
    /**
     * Description：调用工作流驳回，终止流程(业务ID，审批意见)
     * @param busId 业务ID
     * @param opinion 审批意见
     * @param userName 提交驳回的用户名
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-07-29     zhj             创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveRejectToEnd(String busId, String opinion, String userName){
        JSONObject json = new JSONObject();
        json.put("activeUser",userName);
        json.put("busId",busId);
        json.put("opinion",null == opinion || "".equals(opinion) ? "驳回":opinion);
        LOGGER.info("流程提交的json:{}",json);
        JSONObject result = act.callPostFlowBusiness(act.backStartUser,json);
        LOGGER.info("流程提交返回的数据：{}",result);
        if(result.getBoolean(SUCCESS)){
            if(null != result.getString(CODE) && ZERO.equals(result.getString(CODE))){
                return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "驳回成功", 1, result);
            }else{
                LOGGER.error(result.toJSONString());
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, "未知错误" , 0, result);
            }
        }else{
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, result);
        }
    }
    /**
     * Description：调用工作流审批接口，办理流程——流程当前状态 ：ActClientUtils.ActStauts, DRAFT("1")-草稿,APPROVAL("2")-审批中,END("3")-结束,Cancel("4")-撤销;
     * @param busId 业务ID
     * @param opinion 审批意见
     * @param userName 操作者的用户名
     * @param variables 流程变量
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-07-29     zhj             创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveApproveToAct(String busId, String opinion, String userName, Map<String,Object> variables) {
        JSONObject json = new JSONObject();
        json.put("activeUser",userName);
        json.put("busId",busId);
        json.put("opinion",null == opinion || "".equals(opinion) ? "同意":opinion);
        if(null != variables && variables.size()>0){
            json.put("variables",variables);
        }
        LOGGER.info("流程提交的json:{}",json);
        JSONObject result = act.callPostFlowBusiness(act.submitFlow,json);
        LOGGER.info("流程提交返回的数据：{}",result);
        if(result.getBoolean(SUCCESS)){
            if(null != result.getString(CODE) && ZERO.equals(result.getString(CODE))){
                //流程当前当前状态：ActClientUtils.ActStauts
                json.put("status",result.getString("status"));
                return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "审批成功", 1, json);
            }else{
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
            }
        }else{
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：调用工作流审批接口，办理流程——流程当前状态 ：ActClientUtils.ActStauts, DRAFT("1")-草稿,APPROVAL("2")-审批中,END("3")-结束,Cancel("4")-撤销;
     * @param busId 业务ID
     * @param opinion 审批意见
     * @param userName 操作者的用户名
     * @param variables 流程变量
     * @param expressionParameter 如果下一个节点办理人是指定角色并且是通过SQL或URL获取角色用户，SQL或URL对应的参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-02-25     wjl             创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveApproveToAct2(String busId, String opinion, String userName, Map<String,Object> variables, Map<String, Object> expressionParameter) {
        JSONObject json = new JSONObject();
        json.put("activeUser",userName);
        json.put("busId",busId);
        json.put("opinion",null == opinion || "".equals(opinion) ? "同意":opinion);
        if(null != variables && variables.size()>0){
            json.put("variables",variables);
        }
        if(null != expressionParameter && expressionParameter.size()>0){
            json.put("expressionParameter",expressionParameter);
        }
        LOGGER.info("流程提交的json:{}",json);
        JSONObject result = act.callPostFlowBusiness(act.submitFlow,json);
        LOGGER.info("流程提交返回的数据：{}",result);
        if(result.getBoolean(SUCCESS)){
            if(null != result.getString(CODE) && ZERO.equals(result.getString(CODE))){
                //流程当前当前状态：ActClientUtils.ActStauts
                json.put("status",result.getString("status"));
                return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "审批成功", 1, json);
            }else{
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, "未知错误"  , 0, null);
            }
        }else{
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：根据业务ID获取实时流程图
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-07-29     zhj             创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveShineProcImage(JSONObject jsonParams) throws UtilsException {
        //业务ID
        String busId = jsonParams.getString("busId");
        if(null == busId || "".equals(busId)){
            throw new UtilsException("业务ID不可为空");
//            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "业务ID不可为空", 0, null);
        }
        JSONObject actResult = act.callGetFlowBusiness(act.getShineProcImage+"/"+busId,null);
        JSONObject json = new JSONObject();
        if(actResult.getBoolean(SUCCESS)){
            if(null != actResult.getString(CODE) && ZERO.equals(actResult.getString(CODE))){
                json.put("busId",busId);
                JSONArray images = actResult.getJSONArray("images");
                if(null != images && images.size()>0){
                    json.put("images",images);
                    return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "获取实时流程图成功", 1, json);
                }
                return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "获取实时流程图成功", 1, json);
            }else{
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, "未知错误" , 0, null);
            }
        }else{
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
    /**
     * Description：查询当前用户是否有办理权限
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-07-29     zhj             创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveBehaviorPoHeader(JSONObject jsonParams) {
        //业务ID
        String busId = jsonParams.getString("busId");
        if(null == busId || "".equals(busId)){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "业务ID不可为空", 0, null);
        }
        //当前登录用户账号
        String userName = jsonParams.getString("varUserName");
        JSONObject actResult = act.callPostFlowBusiness(act.behavior+"/"+busId+"/"+userName,null);
        JSONObject json = new JSONObject();
        if(actResult.getBoolean(SUCCESS)){
            if(null != actResult.getString(CODE) && ZERO.equals(actResult.getString(CODE))){
                json.put("permissions",actResult.getString("permissions"));
                json.put("busId",busId);
                return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "获取用户审批权限成功", 1, json);
            }else{
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, "获取用户审批权限失败，错误："+actResult.getString("msg"), 0, null);
            }
        }else{
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
    /**
     * Description：根据业务ID获取审批历史信息
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-07-29     zhj             创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveCommentList(JSONObject jsonParams) {
        //业务ID
        String busId = jsonParams.getString("busId");
        if(null == busId || "".equals(busId)){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "业务ID不可为空", 0, null);
        }
        JSONObject actResult = act.callPostFlowBusiness(act.commentList+"/"+busId,null);
        JSONObject json = new JSONObject();
        if(actResult.getBoolean(SUCCESS)){
            if(null != actResult.getString(CODE) && ZERO.equals(actResult.getString(CODE))){
                JSONArray commentList = actResult.getJSONArray("commentList");
                if(null != commentList && commentList.size()>0){
                    for(int i=0;i<commentList.size();i++){
                        JSONObject obj = commentList.getJSONObject(i);
                        String appAction = obj.getString("appAction");
                        if(null != appAction && "0".equals(appAction)){
                            obj.put("appActionName", ActClientUtils.ActTaskResult.SUBMIT.getValue());
                        }else if(null != appAction && "1".equals(appAction)){
                            obj.put("appActionName", ActClientUtils.ActTaskResult.AGREE.getValue());
                        }else if(null != appAction && "2".equals(appAction)){
                            obj.put("appActionName", ActClientUtils.ActTaskResult.TERMINATION.getValue());
                        }else if(null != appAction && "3".equals(appAction)){
                            obj.put("appActionName", ActClientUtils.ActTaskResult.COMMUNICATION.getValue());
                        }else if(null != appAction && "4".equals(appAction)){
                            obj.put("appActionName", ActClientUtils.ActTaskResult.TURN_DOWN.getValue());
                        }else if(null != appAction && "5".equals(appAction)){
                            obj.put("appActionName", ActClientUtils.ActTaskResult.TURN_DO.getValue());
                        }else if(null != appAction && "6".equals(appAction)){
                            obj.put("appActionName", ActClientUtils.ActTaskResult.COMPLAINT.getValue());
                        }else if(null != appAction && "7".equals(appAction)){
                            obj.put("appActionName", ActClientUtils.ActTaskResult.Cancel.getValue());
                        }
                        String dealDate = DateUtil.date2Str(obj.getDate("dealTime"),"yyyy-MM-dd HH:mm:ss");
                        obj.put("dealDate",dealDate);
                        String createDate = DateUtil.date2Str(obj.getDate("createTime"),"yyyy-MM-dd HH:mm:ss");
                        obj.put("createDate",createDate);
                    }
                    json.put("commentList",commentList);
                }
                json.put("busId",busId);
                return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "获取审批历史信息成功", 1, json);
            }else{
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, "获取审批历史信息失败，错误："+actResult.getString("msg"), 0, null);
            }
        }else{
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
    /**
     * Description：提交者撤销流程
     * @param busId 业务ID
     * @param userName 启动流程的提交者的userName
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-08-16     zhj             创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveCancelFailFlow(String busId, String userName) {
        //业务ID
        if(null == busId || "".equals(busId)){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "业务ID不可为空", 0, null);
        }
        if(null == userName || "".equals(userName)){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "提交者不可为空", 0, null);
        }
        JSONObject actResult = act.callPostFlowBusiness(act.cancelFailFlow+"/"+busId+"/"+userName,null);
        if(actResult.getBoolean(SUCCESS)){
            if(null != actResult.getString(CODE) && ZERO.equals(actResult.getString(CODE))){
                return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "撤销成功", 1, null);
            }else{
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, "撤销失败，错误："+actResult.getString("msg"), 0, null);
            }
        }else{
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "撤销失败，请求超时", 0, null);
        }
    }
    /**
     * 功能描述: 获取待办列表
     * @param: [userName, pageSiez, pageNum]
     * @return: com.alibaba.fastjson.JSONObject
     * @auther: LiPengFei
     * @date: 2019/11/4 16:13
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-12-30     zhj             创建
     * ==============================================================================
     */
    public JSONObject getMyBacklog(JSONObject jsonParam, Integer pageSize, Integer pageNum){
        jsonParam.put("pageSize",pageSize);
        jsonParam.put("pageNum",pageNum);
        JSONObject actResult = act.callPostFlowBusiness(act.backlog,jsonParam);
        return actResult;
    }
    /**
     * 功能描述: 获取已办列表
     * @param: [userName, pageSiez, pageNum]
     * @return: com.alibaba.fastjson.JSONObject
     * @auther: LiPengFei
     * @date: 2019/11/4 17:20
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-12-30     zhj             创建
     * ==============================================================================
     */
    public JSONObject getHaveDoneLog(JSONObject jsonParam, Integer pageSiez, Integer pageNum){
        jsonParam.put("pageSize",pageSiez);
        jsonParam.put("pageNum",pageNum);
        JSONObject actResult = act.callPostFlowBusiness(act.haveDoneLog,jsonParam);
        return actResult;
    }
    /**
     * 功能描述: 我提交的流程
     * @param: [jsonParam, pageSiez, pageNum]
     * @return: com.alibaba.fastjson.JSONObject
     * @auther: LiPengFei
     * @date: 2019/11/6 17:14
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-12-30     zhj             创建
     * ==============================================================================
     */
    public JSONObject findMyProcess(JSONObject jsonParam, Integer pageSiez, Integer pageNum){
        jsonParam.put("pageSize",pageSiez);
        jsonParam.put("pageNum",pageNum);
        JSONObject actResult = act.callPostFlowBusiness(act.findMyProcess,jsonParam);
        return actResult;
    }
}
