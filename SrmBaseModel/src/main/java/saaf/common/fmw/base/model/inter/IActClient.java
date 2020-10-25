package saaf.common.fmw.base.model.inter;

import com.alibaba.fastjson.JSONObject;
import saaf.common.fmw.base.utils.UtilsException;

import java.util.Map;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：IActClient.java
 * Description：工作流引擎类
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-07-29     zhj             创建
 * ==============================================================================
 */
public interface IActClient {
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
    public JSONObject saveSubmitToWorkFlow(String userName, String actKey, String busId, String flowTitle, String busUrl, Map<String, Object> variables, Map<String, Object> expressionParameter);
    /**
     * Description：调用工作流驳回，终止流程(业务ID，审批意见)
     * @param busId 业务ID
     * @param opinion 审批意见
     * @param userName 操作者的用户名
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-07-29     zhj             创建
     * ==============================================================================
     */
    public JSONObject saveRejectToEnd(String busId, String opinion, String userName);

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
    public JSONObject saveApproveToAct(String busId, String opinion, String userName, Map<String, Object> variables);
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
    public JSONObject saveApproveToAct2(String busId, String opinion, String userName, Map<String, Object> variables, Map<String, Object> expressionParameter);
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
    public JSONObject saveShineProcImage(JSONObject jsonParams) throws UtilsException;
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
    public JSONObject saveBehaviorPoHeader(JSONObject jsonParams);
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
    public JSONObject saveCommentList(JSONObject jsonParams);

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
    public JSONObject saveCancelFailFlow(String busId, String userName);
    /**
     * 功能描述: 获取待办列表
     * @param: [userName, pageSiez, pageNum]
     * @return: com.alibaba.fastjson.JSONObject
     * @auther: LiPengFei
     * @date: 2019/11/4 16:12
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-12-30     zhj             创建
     * ==============================================================================
     */
    JSONObject getMyBacklog(JSONObject jsonObject, Integer pageSiez, Integer pageNum);
    /**
     * 功能描述: 获取我提交的流程
     * @param: [jsonObject, pageSiez, pageNum]
     * @return: com.alibaba.fastjson.JSONObject
     * @auther: LiPengFei
     * @date: 2019/11/6 17:30
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-12-30     zhj             创建
     * ==============================================================================
     */
    JSONObject findMyProcess(JSONObject jsonObject, Integer pageSiez, Integer pageNum);
    /**
     * 功能描述: 获取已办列表
     * @param: [userName, pageSiez, pageNum]
     * @return: com.alibaba.fastjson.JSONObject
     * @auther: LiPengFei
     * @date: 2019/11/4 17:21
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-12-30     zhj             创建
     * ==============================================================================
     */
    JSONObject getHaveDoneLog(JSONObject jsonObject, Integer pageSiez, Integer pageNum);
}
