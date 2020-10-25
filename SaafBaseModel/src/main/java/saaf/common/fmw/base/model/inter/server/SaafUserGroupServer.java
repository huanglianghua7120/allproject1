package saaf.common.fmw.base.model.inter.server;


import com.alibaba.fastjson.JSONObject;

import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import java.rmi.ServerException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafUserGroupEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafUserGroupEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafUserGroup;
import saaf.common.fmw.common.model.inter.server.ShortDescMessageServer;
import saaf.common.fmw.common.utils.SaafToolUtils;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：saafUserGroupServer.java
 * Description：用户分组维护控制类
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
@Component("saafUserGroupServer")
public class SaafUserGroupServer implements ISaafUserGroup {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafUserGroupServer.class);
    @Autowired
    private ShortDescMessageServer shortDescMessageServer;

    public SaafUserGroupServer() {
        super();
    }

    @Autowired
    private BaseViewObject<SaafUserGroupEntity_HI_RO> saafUserGroupDAO_HI_RO;
    @Autowired
    private ViewObject<SaafUserGroupEntity_HI> saafUserGroupDAO_HI;

    /**
     * 查询用户组信息
     *
     * @param jsonParams
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    @Override
    public Pagination findSaafUserGroup(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SaafUserGroupEntity_HI_RO.QUERY_SQL);
        SaafToolUtils.parperParam(jsonParams, "sug.enabled_flag", "enabledFlag", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sug.group_code", "groupCode", sb, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sug.group_desc", "groupDesc", sb, queryParamMap, "like");
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(SaafUserGroupEntity_HI_RO.QUERY_SQL_ORDERBY);
        Pagination<SaafUserGroupEntity_HI_RO> findListResult = saafUserGroupDAO_HI_RO.findPagination(sb.toString(),
                countSql, queryParamMap, pageIndex, pageRows);
        return findListResult;
    }

    /**
     * 保存用户组信息
     * @param params
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    @Override
    public JSONObject saveSaafUserGroup(JSONObject params) throws Exception {
        SaafUserGroupEntity_HI sug = null;
        int userId = params.getIntValue("varUserId");
        if (null == params.getInteger("userGroupId")) {
            sug = new SaafUserGroupEntity_HI();
            sug.setCreatedBy(userId);
            sug.setCreationDate(new Date());
            sug.setGroupCode("");//SaafToolUtils.getBillNumber("SAAF_USER_GROUP"));
        } else {
            sug = saafUserGroupDAO_HI.getById(params.getIntValue("userGroupId"));
        }
        sug.setLastUpdateDate(new Date());
        sug.setLastUpdatedBy(userId);
        sug.setLastUpdateLogin(userId);
        sug.setEnabledFlag(params.getString("enabledFlag"));
        sug.setGroupDesc(params.getString("groupDesc"));
        sug.setGroupType(params.getString("groupType"));
        sug.setSelectResult(params.getString("selectResult"));
        sug.setTypeDetail(params.getString("typeDetail"));
        sug.setUserType(params.getString("userType"));
        String inStr = params.getString("typeDetail");
        inStr = inStr.substring(1, inStr.length() - 1);
        StringBuffer sb = new StringBuffer();
        switch (params.getString("groupType")) {
        case "FROM_RESP":
            makeRespSql(sug.getUserType(), inStr, sb);
            break;
        case "FROM_INST":
            makeInstSql(sug.getUserType(), inStr, sb);
            break;
        case "FROM_AGENT":
            sb.append(" select DISTINCT su.user_id userId from  saaf_users su ,customer_info ci where 1=1 and su.member_id=ci.customer_id");
            sb.append(" and ci.customer_type='AGENT'");
            sb.append(" and curdate() between su.start_date_active and ifnull(su.end_date_active, curdate())");
            sb.append(" and ci.customer_id in ( ").append(inStr).append(")");
            //System.out.println("FROM_AGENT");
            break;
        case "FROM_DISTRIBUTOR":
            sb.append(" select DISTINCT su.user_id userId from  saaf_users su ,customer_info ci where 1=1 and su.member_id=ci.customer_id");
            sb.append(" and ci.customer_type='DISTRIBUTOR'");
            sb.append(" and curdate() between su.start_date_active and ifnull(su.end_date_active, curdate())");
            sb.append(" and ci.customer_id in ( ").append(inStr).append(")");
            //System.out.println("FROM_DISTRIBUTOR");
            break;
        case "FROM_DIRDCT":
            sb.append(" select DISTINCT su.user_id userId from  saaf_users su ,customer_info ci where 1=1 and su.member_id=ci.customer_id");
            sb.append(" and ci.customer_type='DIRDCT'");
            sb.append(" and curdate() between su.start_date_active and ifnull(su.end_date_active, curdate())");
            sb.append(" and ci.customer_id in ( ").append(inStr).append(")");
            //System.out.println("FROM_DIRDCT");
            break;
        case "FROM_STORE":
            sb.append(" select DISTINCT su.user_id userId from  saaf_users su ,customer_info ci where 1=1 and su.member_id=ci.customer_id");
            sb.append(" and ci.customer_type='STORE'");
            sb.append(" and curdate() between su.start_date_active and ifnull(su.end_date_active, curdate())");
            sb.append(" and ci.customer_id in ( ").append(inStr).append(")");
            //System.out.println("FROM_STORE");
            break;
        case "FROM_USERS":
            sb.append(" select su.USER_ID userId from saaf_users su where 1=1 and su.USER_ID in (").append(inStr).append(")");
            sb.append(" and curdate() between su.start_date_active and ifnull(su.end_date_active, curdate())");
            //System.out.println("FROM_USERS");
            break;
        default:
            LOGGER.error("用户组模式groupType为空,无法产生sql");
            throw new ServerException(shortDescMessageServer.getMessage("SAAFBASE-ERR-OO1"));
        }
        //System.out.println("最后生产的sql----------------->" + sb);
        sug.setGroupSql(sb.toString());
        saafUserGroupDAO_HI.saveOrUpdate(sug);
        return SToolUtils.convertResultJSONObj("S", shortDescMessageServer.getMessage("SAVE-SUCCESS"), 1, sug);
    }

    /**
     * 生成根据职责的sql
     * @param userType 用户类型
     * @param inStr 处理后的typeDetail
     * @param sb
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    private void makeRespSql(String userType, String inStr, StringBuffer sb) {
        sb.append(" select su.user_id userId from saaf_user_resp sur,saaf_users su where 1=1 and su.user_id=sur.user_id");
        sb.append(" and sur.responsibility_id in ( ").append(inStr).append(")");
        sb.append(" and curdate() between su.start_date_active and ifnull(su.end_date_active, curdate())");
        switch (userType) {
        case "AUX": // 中心
            sb.append(" and su.platform_code='AUX'");
            break;
        case "AGENT": // 代理商
            sb.append(" and su.platform_code='AGENT'");
            break;
        case "DISTRIBUTOR": // 分销商
            sb.append(" and su.platform_code='DISTRIBUTOR'");
            break;
        default: // 默认ALL
            break;
        }
    }

    /**
     * 生成根据中心的sql
     * @param userType 用户类型
     * @param inStr 处理后的typeDetail
     * @param sb
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    private void makeInstSql(String userType, String inStr, StringBuffer sb) {
        switch (userType) {
        case "AUX": // 中心
            sb.append(" select DISTINCT su.USER_ID userId from saaf_user_access_orgs suao,saaf_users su where 1=1 and su.user_id=suao.user_id");
            sb.append(" and suao.inst_id in ( ").append(inStr).append(")");
            sb.append(" and curdate() between su.start_date_active and ifnull(su.end_date_active, curdate())");
            break;
        case "AGENT": //代理商
            sb.append(" select DISTINCT su.USER_ID userId from  saaf_users su ,customer_info ci where 1=1 and su.member_id=ci.customer_id");
            sb.append(" and ci.inst_code in ( ").append(inStr).append(")");
            sb.append(" and curdate() between su.start_date_active and ifnull(su.end_date_active, curdate())");
            sb.append(" and ci.customer_type='AGENT'");
            break;
        case "DISTRIBUTOR": //分销商
            sb.append(" select DISTINCT su.USER_ID userId from  saaf_users su ,customer_info ci where 1=1 and su.member_id=ci.customer_id");
            sb.append(" and ci.inst_code in ( ").append(inStr).append(")");
            sb.append(" and curdate() between su.start_date_active and ifnull(su.end_date_active, curdate())");
            sb.append(" and ci.customer_type='DISTRIBUTOR'");
            break;
        default: // 默认ALL
            sb.append(" SELECT A.userId from( ");
            sb.append(" select DISTINCT su.USER_ID userId from saaf_user_access_orgs suao,saaf_users su where 1=1 and su.user_id=suao.user_id");
            sb.append(" and suao.inst_id in ( ").append(inStr).append(")");
            sb.append(" and su.USER_ID=suao.USER_ID and curdate() between su.start_date_active and ifnull(su.end_date_active, curdate())");
            sb.append(" union all ");
            sb.append(" select DISTINCT su.USER_ID userId from  saaf_users su ,customer_info ci where 1=1 and su.member_id=ci.customer_id");
            sb.append(" and ci.inst_code in ( ").append(inStr).append(")");
            sb.append(" and curdate() between su.start_date_active and ifnull(su.end_date_active, curdate())");
            sb.append(" ) A order by A.userId");
            break;
        }
    }

    /**
     * 多选框，选择
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    @Override
    public Pagination findMulLov(JSONObject jsonParam, Integer pageIndex, Integer pageRows) {
        Map<String, Object> map = SToolUtils.fastJsonObj2Map(jsonParam);
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        queryParamMap.put("groupCode", map.get("groupCode")); // 务必传入
        String groupType = map.get("groupType").toString(); // 务必传入
        StringBuffer sb = new StringBuffer();
        if ("FROM_INST".equals(groupType)) {
            sb.append(SaafUserGroupEntity_HI_RO.MUL_LOV_INST_QUERY_SQL);
            SaafToolUtils.parperParam(jsonParam, "si.inst_code", "mulCode", sb, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParam, "si.inst_name", "mulName", sb, queryParamMap, "like");
        } else if ("FROM_RESP".equals(groupType)) {
            sb.append(SaafUserGroupEntity_HI_RO.MUL_LOV_RESP_QUERY_SQL);
            SaafToolUtils.parperParam(jsonParam, "slv.meaning", "mulCode", sb, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParam, "sr.responsibility_name", "mulName", sb, queryParamMap, "like");
        } else if ("FROM_USERS".equals(groupType)) {
            sb.append(SaafUserGroupEntity_HI_RO.MUL_LOV_USER_QUERY_SQL);
            SaafToolUtils.parperParam(jsonParam, "mt.custType", "custType", sb, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParam, "mt.mulCode", "mulCode", sb, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParam, "mt.mulName", "mulName", sb, queryParamMap, "like");
        } else { // 选择代理商/分销商/直营商/卖场
            sb.append(SaafUserGroupEntity_HI_RO.MUL_LOV_CUSTOMER_QUERY_SQL);
            SaafToolUtils.parperParam(jsonParam, "ci.customer_code", "mulCode", sb, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParam, "ci.customer_name", "mulName", sb, queryParamMap, "like");
            String cusType = groupType.split("_", 4)[1];
            queryParamMap.put("customerType", cusType);
            if ("DISTRIBUTOR".equals(cusType)) { // 如果是分销商，则状态必须为已审核
                sb.append(" and ci.status = 'AUDITED' ");
            }
        }
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY checkFlag desc,mulName");
        Pagination findListResult = saafUserGroupDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return findListResult;
    }
}
