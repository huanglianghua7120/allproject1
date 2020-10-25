package saaf.common.fmw.pon.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pon.model.entities.SrmPonPaymentTermNodesEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonPaymentTermsEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonPaymentTermsEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonPaymentTermNodes;
import saaf.common.fmw.pon.model.inter.ISrmPonPaymentTerms;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonPaymentTermsServer.java
 * Description：寻源--付款条件信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonPaymentTermsServer")
public class SrmPonPaymentTermsServer implements ISrmPonPaymentTerms {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonPaymentTermsServer.class);

    @Autowired
    private ViewObject<SrmPonPaymentTermsEntity_HI> srmPonPaymentTermsDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonPaymentTermsEntity_HI_RO> srmPonPaymentTermsDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPonPaymentTermNodesEntity_HI> srmPonPaymentTermNodesDAO_HI;//付款节点

    @Autowired
    private ISrmPonPaymentTermNodes iSrmPonPaymentTermNodes; //付款节点

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    public SrmPonPaymentTermsServer() {
        super();
    }

    /**
     * Description：付款条件查询（分页）
     * @param jsonParams 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return Pagination<SrmPonPaymentTermsEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPonPaymentTermsEntity_HI_RO> findSrmPonPaymentTermsInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPonPaymentTermsEntity_HI_RO.QUERY_PAYMENTERMSLIST_SQL);
        SaafToolUtils.parperParam(jsonParams, "t.payment_term_id", "paymentTermId", sb, map, "=");
        SaafToolUtils.parperParam(jsonParams, "t.payment_term_code", "paymentTermCode", sb, map, "like");
        SaafToolUtils.parperParam(jsonParams, "t.payment_term_name", "paymentTermName", sb, map, "like");
        SaafToolUtils.parperParam(jsonParams, "se.employee_name", "createdByEmployeeName", sb, map, "like");
        SaafToolUtils.parperParam(jsonParams, "se2.employee_name", "lastUpdatedByEmployeeName", sb, map, "like");
        SaafToolUtils.parperParam(jsonParams, "t.payment_term_status", "paymentTermStatus", sb, map, "=");
//        SaafToolUtils.parperParam(jsonParams, "DATE_FORMAT(t.creation_date,'%Y-%m-%d')", "creationDateTo", sb, map, ">=");
//        SaafToolUtils.parperParam(jsonParams, "DATE_FORMAT(t.creation_date,'%Y-%m-%d')", "creationDateFrom", sb, map, "<=");
        String creationDateFrom = jsonParams.getString("creationDateFrom");
        if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
            sb.append(" AND    trunc(t.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
            map.put("creationDateFrom", creationDateFrom);
        }
        String creationDateTo = jsonParams.getString("creationDateTo");
        if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
            sb.append(" AND    trunc(t.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
            map.put("creationDateTo", creationDateTo);
        }
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY t.creation_date desc, t.payment_term_code DESC "); //排序
        Pagination<SrmPonPaymentTermsEntity_HI_RO> result = srmPonPaymentTermsDAO_HI_RO.findPagination(sb.toString(),countSql, map, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：保存付款条件及其子表
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject savePonPaymentTermsAll(JSONObject jsonParams) {
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId");  //用户Id
        String action = jsonParams.getString("action"); // 操作按钮
        if (null == action || "".equals(action)) {
            return SToolUtils.convertResultJSONObj("E", "操作字符为空！", 0, null);
        }
        String paymentTermCode;
        SrmPonPaymentTermsEntity_HI paymentTerm = JSON.parseObject(jsonParams.toJSONString(), SrmPonPaymentTermsEntity_HI.class);
        List<SrmPonPaymentTermNodesEntity_HI> paymentTermNodesList = null;
        if (!(jsonParams.getJSONArray("paymentTermNodesList") == null || "".equals(jsonParams.getJSONArray("paymentTermNodesList")))) {
            JSONArray paymentTermNodesListJSON = jsonParams.getJSONArray("paymentTermNodesList");
            paymentTermNodesList = JSON.parseArray(paymentTermNodesListJSON.toJSONString(), SrmPonPaymentTermNodesEntity_HI.class);
        }
        if (paymentTerm.getPaymentTermCode() == null || "".equals(paymentTerm.getPaymentTermCode())) {
            try {
                paymentTermCode = saafSequencesUtil.getDocSequences("srm_pon_payment_terms".toUpperCase(), "FK", 3);
                paymentTerm.setPaymentTermCode(paymentTermCode);
            } catch (Exception e) {
                LOGGER.error("创建付款编号出错！", e.getMessage());
                return SToolUtils.convertResultJSONObj("E", "创建付款编号出错！", 0, null);
            }
        }
        String paymentTermName = iSrmPonPaymentTermNodes.getPaymentTermName(paymentTermNodesList);
        paymentTerm.setPaymentTermName(paymentTermName); //付款条件
        paymentTerm.setOperatorUserId(userId);

        if ("save".equals(action)) { // 保存按钮
            srmPonPaymentTermsDAO_HI.saveEntity(paymentTerm);
            iSrmPonPaymentTermNodes.savePaymentTermNodesList(paymentTermNodesList, userId, paymentTerm.getPaymentTermCode());
            jsonData.put("paymentTermId", paymentTerm.getPaymentTermId());
            return SToolUtils.convertResultJSONObj("S", "保存成功", 1, jsonData);
        } else if ("saveV".equals(action)) {//删除付款节点后的保存
            srmPonPaymentTermsDAO_HI.saveEntity(paymentTerm);
            iSrmPonPaymentTermNodes.savePaymentTermNodesList(paymentTermNodesList, userId, paymentTerm.getPaymentTermCode());
            jsonData.put("paymentTermId", paymentTerm.getPaymentTermId());
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, jsonData);
        } else if ("valid".equals(action)) {//生效
            paymentTerm.setPaymentTermStatus("ACT");
            srmPonPaymentTermsDAO_HI.saveEntity(paymentTerm);
            iSrmPonPaymentTermNodes.savePaymentTermNodesList(paymentTermNodesList, userId, paymentTerm.getPaymentTermCode());
            jsonData.put("paymentTermId", paymentTerm.getPaymentTermId());
            return SToolUtils.convertResultJSONObj("S", "生效成功", 1, jsonData);
        }
        return SToolUtils.convertResultJSONObj("E", "没有操作", 0, null);
    }

    /**
     * Description：删除付款条件——根据主键ID（单条数据）及其付款节点
     * @param paymentTermId 付款条件ID
     * @return String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public String deletePonPaymentTerm(Integer paymentTermId) {
        String result = "";
        if (!(paymentTermId == null || "".equals(paymentTermId))) {
            SrmPonPaymentTermsEntity_HI paymentTerm = srmPonPaymentTermsDAO_HI.getById(paymentTermId);
            if (null != paymentTerm) {
                List<SrmPonPaymentTermNodesEntity_HI> paymentTermNodesList = srmPonPaymentTermNodesDAO_HI.findByProperty("paymentTermCode", paymentTerm.getPaymentTermCode());
                if (null != paymentTermNodesList && paymentTermNodesList.size() > 0) {
                    srmPonPaymentTermNodesDAO_HI.deleteAll(paymentTermNodesList);
                }
                srmPonPaymentTermsDAO_HI.delete(paymentTerm.getPaymentTermId());
                return result;
            } else {
                result = "无法删除，无此记录！参数：" + paymentTermId;
                return result;
            }
        }
        return result;
    }

    /**
     * Description：批量删除付款条件
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject deletePonPaymentTermByBatch(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());
        JSONArray paymentTermIdList = jsonParams.getJSONArray("data");
        for (Integer i = 0; i < paymentTermIdList.size(); i++) {
            Integer paymentTermId = paymentTermIdList.getInteger(i);
            String result = deletePonPaymentTerm(paymentTermId);
            if (!(result == null || "".equals(result))) {
                return SToolUtils.convertResultJSONObj("E", result, 0, null);
            }
        }
        return SToolUtils.convertResultJSONObj("S", "删除成功", paymentTermIdList.size(), null);
    }

    /**
     * Description：批量失效付款条件
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject updateInvalidPonPaymentTermByBatch(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());
        Integer userId = jsonParams.getInteger("varUserId");  //用户Id
        JSONArray paymentTermIdListJSON = jsonParams.getJSONArray("data");
        List<SrmPonPaymentTermsEntity_HI> paymentTermIdList = JSON.parseArray(paymentTermIdListJSON.toJSONString(), SrmPonPaymentTermsEntity_HI.class);
        if (null != paymentTermIdList && paymentTermIdList.size() > 0) {
            for (SrmPonPaymentTermsEntity_HI k : paymentTermIdList) {
                k.setPaymentTermStatus("INACT");
                k.setOperatorUserId(userId);
            }
            srmPonPaymentTermsDAO_HI.saveOrUpdateAll(paymentTermIdList);
            return SToolUtils.convertResultJSONObj("S", "失效成功", paymentTermIdList.size(), null);
        } else {
            return SToolUtils.convertResultJSONObj("E", "参数为空，不可操作", 0, null);
        }
    }
}
