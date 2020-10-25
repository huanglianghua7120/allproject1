package saaf.common.fmw.okc.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.okc.model.entities.SrmOkcContractTermsEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractTermsEntity_HI_RO;
import saaf.common.fmw.okc.model.inter.ISrmOkcContractTerms;
import saaf.common.fmw.okc.utils.ConTempConstant;
import saaf.common.fmw.okc.utils.ConTempState;
import saaf.common.fmw.okc.utils.MapToBeanUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：SrmOkcContractTermsServer.java
 * Description：合同条款服务类
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date     @Author(Updated By)     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019/6/4      欧阳岛          创建
 * <p>
 * ==============================================================================
 */
@Component("srmOkcContractTermsServer")
public class SrmOkcContractTermsServer implements ISrmOkcContractTerms {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmOkcContractTermsServer.class);

    @Autowired
    private ViewObject<SrmOkcContractTermsEntity_HI> srmOkcContractTermsDAO_HI;

    @Autowired
    private BaseViewObject<SrmOkcContractTermsEntity_HI_RO> srmOkcContractTermsDAO_HI_RO;

    public SrmOkcContractTermsServer() {
        super();
    }

    /**
     * Description：合同条款分页查询列表
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmOkcContractTermsEntity_HI_RO> findSrmOkcContractTermsList(
            JSONObject parameters, Integer pageIndex, Integer pageRows) {
        LOGGER.debug("查询参数:==========>>>{}\t{}\t{}", parameters, pageIndex, pageRows);
        StringBuffer queryString = new StringBuffer(SrmOkcContractTermsEntity_HI_RO.QUERY_CONTRACTTERMS_SQL);
        StringBuffer whereString = new StringBuffer();
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(parameters, "ct.term_code", "termCode", whereString, map, "=");
        SaafToolUtils.parperParam(parameters, "ct.term_name", "termName", whereString, map, "like");
        SaafToolUtils.parperParam(parameters, "ct.version_number", "versionNumber", whereString, map, "=");
        SaafToolUtils.parperParam(parameters, "ct.term_type", "termType", whereString, map, "=");
        SaafToolUtils.parperParam(parameters, "ct.org_id", "orgId", whereString, map, "=");
        SaafToolUtils.parperParam(parameters, "ct.created_by", "createdBy", whereString, map, "=");
        SaafToolUtils.parperParam(parameters, "ct.term_status", "termStatus", whereString, map, "=");
        String creationDateFrom = parameters.getString("creationDateFrom");
        if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
            whereString.append(" AND trunc(ct.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
            map.put("creationDateFrom", creationDateFrom);
        }
        String creationDateTo = parameters.getString("creationDateTo");
        if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
            whereString.append(" AND trunc(ct.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
            map.put("creationDateTo", creationDateTo);
        }
        String dispLatestFlag = parameters.getString("dispLatestFlag");
        if (dispLatestFlag != null && ConTempConstant.DISP_LATEST_FLAG_Y.equalsIgnoreCase(dispLatestFlag)) {
            whereString.append(" and ct.changing_flag = 'N ' ");
        }

        if (whereString.length() > 0) {
            queryString.append(" where 1 = 1 ").append(whereString);
        }
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" ORDER BY ct.term_code desc ");
        return srmOkcContractTermsDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
    }

    /**
     * Description：保存合同条款
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveSrmOkcContractTerms(JSONObject queryParamJSON) {
        LOGGER.debug("参数:==========>>>{}", queryParamJSON);
        SrmOkcContractTermsEntity_HI srmOkcContractTermsEntity_HI = null;
        Integer termId = queryParamJSON.getInteger("termId");
        if (termId != null) {
//            修改
            srmOkcContractTermsEntity_HI = srmOkcContractTermsDAO_HI.findByProperty("termId", termId).get(0);
            if (!ConTempState.DRAFT.getValue().equals(srmOkcContractTermsEntity_HI.getTermStatus()) &&
                    !ConTempState.REJECTED.getValue().equals(srmOkcContractTermsEntity_HI.getTermStatus())) {
                return SToolUtils.convertResultJSONObj("E", "更新合同条款失败,非拟定或驳回状态", 0, null);
            }
//            SrmOkcContractTermsEntity_HI tempObj = JSON.parseObject(queryParamJSON.toString(), SrmOkcContractTermsEntity_HI.class);
//            BeanUtils.copyProperties(tempObj, srmOkcContractTermsEntity_HI);
            MapToBeanUtil.copyPropertiesInclude(queryParamJSON, srmOkcContractTermsEntity_HI);
        } else {
//            新增
            srmOkcContractTermsEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmOkcContractTermsEntity_HI.class);
            srmOkcContractTermsEntity_HI.setTermCode(genTermCode());
            srmOkcContractTermsEntity_HI.setChangingFlag("N");
        }
        srmOkcContractTermsEntity_HI.setTermStatus(ConTempState.DRAFT.getValue());
        srmOkcContractTermsDAO_HI.saveOrUpdate(srmOkcContractTermsEntity_HI);
        JSONObject result = SToolUtils.convertResultJSONObj("S", "保存合同条款成功", 1, srmOkcContractTermsEntity_HI);
        return result;
    }

    /**
     * Description：提交合同条款
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public JSONObject doSubmitSrmOkcContractTerms(JSONObject queryParamJSON) {
        LOGGER.debug("参数:==========>>>{}", queryParamJSON);
        Integer termId = queryParamJSON.getInteger("termId");
        Integer operatorUserId = queryParamJSON.getInteger("operatorUserId");
//        SrmOkcContractTermsEntity_HI tempObj = JSON.parseObject(queryParamJSON.toString(), SrmOkcContractTermsEntity_HI.class);
        return updateTermStatus(termId, operatorUserId, ConTempState.DRAFT.getValue(), ConTempState.DRAFT.getName(), ConTempState.SUBMITTED.getValue(), ConTempState.SUBMITTED.getOpt(), queryParamJSON, null);
    }

    /**
     * Description：审批合同条款
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public JSONObject doApprovalSrmOkcContractTerms(JSONObject queryParamJSON) throws Exception {
        LOGGER.debug("参数:==========>>>{}", queryParamJSON);
        Integer termId = queryParamJSON.getInteger("termId");
        String approvalOpinion = queryParamJSON.getString("approvalOpinion");
        Integer operatorUserId = queryParamJSON.getInteger("operatorUserId");
        JSONObject updateRslt = updateTermStatus(termId, operatorUserId, ConTempState.SUBMITTED.getValue(), ConTempState.SUBMITTED.getName(), ConTempState.APPROVED.getValue(), ConTempState.APPROVED.getOpt(), null, approvalOpinion);
        if ("S".equalsIgnoreCase(updateRslt.getString("status"))) {
//            更新的新版本批准后,老版本需禁用
            SrmOkcContractTermsEntity_HI contractTermsEntity = updateRslt.getObject("data", SrmOkcContractTermsEntity_HI.class);
            int versionNumber = contractTermsEntity.getVersionNumber();
            String termCode = contractTermsEntity.getTermCode();
            forbidPreVersionTermStatus(termCode, versionNumber, operatorUserId);
        }
        return updateRslt;
    }

    /**
     * Description：禁用老版本
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/20      欧阳岛          创建
     * ==============================================================================
     */
    private void forbidPreVersionTermStatus(String termCode, int versionNumber, Integer operatorUserId) throws Exception {
        if (versionNumber > 0) {
            int preVersion = versionNumber - 1;
            Map sparam = new HashMap();
            sparam.put("termCode", termCode);
            sparam.put("versionNumber", preVersion);
            try {
                List<SrmOkcContractTermsEntity_HI> list = srmOkcContractTermsDAO_HI.findByProperty(sparam);
                SrmOkcContractTermsEntity_HI srmOkcContractTermsEntity_HI = list.get(0);
                if (!ConTempState.DISABLED.getValue().equalsIgnoreCase(srmOkcContractTermsEntity_HI.getTermStatus())) {
                    srmOkcContractTermsEntity_HI.setOperatorUserId(operatorUserId);
                    srmOkcContractTermsEntity_HI.setTermStatus(ConTempState.DISABLED.getValue());
                    srmOkcContractTermsDAO_HI.update(srmOkcContractTermsEntity_HI);
                    forbidPreVersionTermStatus(srmOkcContractTermsEntity_HI.getTermCode(), srmOkcContractTermsEntity_HI.getVersionNumber(), operatorUserId);
                }
            } catch (Exception e) {
                LOGGER.warn(e.getMessage(), e);
                throw new UtilsException("禁用老版本异常："+e.getMessage());
            }
        }
    }

    /**
     * Description：驳回合同条款
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public JSONObject doRejectSrmOkcContractTerms(JSONObject queryParamJSON) {
        LOGGER.debug("参数:==========>>>{}", queryParamJSON);
        Integer termId = queryParamJSON.getInteger("termId");
        String approvalOpinion = queryParamJSON.getString("approvalOpinion");
        Integer operatorUserId = queryParamJSON.getInteger("operatorUserId");
        return updateTermStatus(termId, operatorUserId, ConTempState.SUBMITTED.getValue(), ConTempState.SUBMITTED.getName(), ConTempState.REJECTED.getValue(), ConTempState.REJECTED.getOpt(), null, approvalOpinion);
    }

    /**
     * Description：更新合同条款状态
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    private JSONObject updateTermStatus(Integer termId, Integer operatorUserId, String startStatus, String startStatusName, String endStatus, String endOpt, Map updateObj, String approvalOpinion) {
        SrmOkcContractTermsEntity_HI srmOkcContractTermsEntity_HI = null;
        if (termId != null) {
            srmOkcContractTermsEntity_HI = srmOkcContractTermsDAO_HI.findByProperty("termId", termId).get(0);
            if (!startStatus.equalsIgnoreCase(srmOkcContractTermsEntity_HI.getTermStatus())) {
                return SToolUtils.convertResultJSONObj("E", endOpt + "合同条款失败,非" + startStatusName, 0, null);
            }
            if (updateObj != null) {
//                BeanUtils.copyProperties(updateObj, srmOkcContractTermsEntity_HI);
                MapToBeanUtil.copyPropertiesInclude(updateObj, srmOkcContractTermsEntity_HI);
            }
            if (approvalOpinion != null) {
                srmOkcContractTermsEntity_HI.setAttribute1(approvalOpinion);
            }
            srmOkcContractTermsEntity_HI.setTermStatus(endStatus);
            srmOkcContractTermsEntity_HI.setOperatorUserId(operatorUserId);
        } else {
            return SToolUtils.convertResultJSONObj("E", endOpt + "合同条款失败,未发现termId", 0, null);
        }
        srmOkcContractTermsDAO_HI.update(srmOkcContractTermsEntity_HI);
        JSONObject result = SToolUtils.convertResultJSONObj("S", endOpt + "合同条款成功", 1, srmOkcContractTermsEntity_HI);
        return result;
    }

    /**
     * Description：变更合同条款版本
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public JSONObject doChangeSrmOkcContractTerms(JSONObject queryParamJSON) {
        SrmOkcContractTermsEntity_HI oldSrmOkcContractTerms = null;
        SrmOkcContractTermsEntity_HI newSrmOkcContractTerms = new SrmOkcContractTermsEntity_HI();
        Integer termId = queryParamJSON.getInteger("termId");
        Integer newTermId = -1;
        Integer operatorUserId = queryParamJSON.getInteger("operatorUserId");
        if (termId != null) {
            oldSrmOkcContractTerms = srmOkcContractTermsDAO_HI.findByProperty("termId", termId).get(0);
            if (!ConTempState.APPROVED.getValue().equalsIgnoreCase(oldSrmOkcContractTerms.getTermStatus())) {
                return SToolUtils.convertResultJSONObj("E", "更新合同条款失败,非批准状态", 0, null);
            }
            if (ConTempConstant.CHANGING_FLAG_Y.equalsIgnoreCase(oldSrmOkcContractTerms.getChangingFlag())) {
                return SToolUtils.convertResultJSONObj("E", "更新合同条款失败,此版本已更新", 0, null);
            }
            BeanUtils.copyProperties(oldSrmOkcContractTerms, newSrmOkcContractTerms);
            oldSrmOkcContractTerms.setChangingFlag("Y");
            oldSrmOkcContractTerms.setOperatorUserId(operatorUserId);

            Integer newConTempVersionNumber = oldSrmOkcContractTerms.getVersionNumber() + 1;
            Map<String, Object> conditionMap = new HashMap<>();
            conditionMap.put("termCode", oldSrmOkcContractTerms.getTermCode());
            conditionMap.put("versionNumber", newConTempVersionNumber);
            List<SrmOkcContractTermsEntity_HI> checkList = srmOkcContractTermsDAO_HI.findByProperty(conditionMap);
            if (checkList != null && checkList.size() > 0) {
                return SToolUtils.convertResultJSONObj("E", "更新合同条款失败,其他用户已抢先更新", 0, null);
            }
            newSrmOkcContractTerms.setTermId(null);
            newSrmOkcContractTerms.setCreatedBy(null);
            newSrmOkcContractTerms.setCreationDate(null);
            newSrmOkcContractTerms.setLastUpdatedBy(null);
            newSrmOkcContractTerms.setLastUpdateDate(null);
            newSrmOkcContractTerms.setAttribute1(null);
            newSrmOkcContractTerms.setVersionNumber(newConTempVersionNumber);
            newSrmOkcContractTerms.setTermStatus(ConTempState.DRAFT.getValue());
            newSrmOkcContractTerms.setChangingFlag("N");
            newSrmOkcContractTerms.setOperatorUserId(operatorUserId);

            srmOkcContractTermsDAO_HI.update(oldSrmOkcContractTerms);
            newTermId = (Integer) srmOkcContractTermsDAO_HI.save(newSrmOkcContractTerms);
        } else {
            return SToolUtils.convertResultJSONObj("E", "更新合同条款失败,未发现termId", 0, null);
        }
        JSONObject result = SToolUtils.convertResultJSONObj("S", "更新合同条款成功", 1, newTermId);
        return result;
    }

    /**
     * Description：删除合同条款
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteSrmOkcContractTerms(JSONObject params) throws Exception {
        LOGGER.info("删除合同条款信息，参数：" + params.toString());
        SrmOkcContractTermsEntity_HI srmOkcContractTermsEntity_HI = null;
        Integer termId = params.getInteger("termId");
        try {
            if (termId == null) {
                return SToolUtils.convertResultJSONObj("E", "删除失败，" + params.getString("termId") + "不存在", 0, null);
            }
            srmOkcContractTermsEntity_HI = srmOkcContractTermsDAO_HI.findByProperty("termId", termId).get(0);
            if (ConTempState.DRAFT.getValue().equals(srmOkcContractTermsEntity_HI.getTermStatus())) {
                srmOkcContractTermsDAO_HI.delete(srmOkcContractTermsEntity_HI);
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除失败,只能删除拟定的合同条款!", 0, null);
            }
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("删除合同条款异常："+e.getMessage());
        }
    }

    /**
     * Description：查询单条合同条款记录
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public JSONObject findSrmOkcContractTerms(JSONObject jsonParam) {
        Integer termId = jsonParam.getInteger("termId");
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer(SrmOkcContractTermsEntity_HI_RO.QUERY_CONTRACTTERMS_ALL_SQL);
        queryString.append(" where ct.term_id  = :termId");
        map.put("termId", termId);
        List<SrmOkcContractTermsEntity_HI_RO> srmOkcContractTerms = srmOkcContractTermsDAO_HI_RO.findList(queryString, map);
        if (srmOkcContractTerms == null || srmOkcContractTerms.size() != 1) {
            return SToolUtils.convertResultJSONObj("E", "查询合同模板信息失败!", 0, null);
        }
        return SToolUtils.convertResultJSONObj("S", "查询合同模板信息成功!", 0, srmOkcContractTerms.get(0));
    }

    /**
     * Description：生成合同条款编码
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    private String genTermCode() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer termCode = new StringBuffer("TK-").append(sdf.format(d).replaceAll("-", "")).append("-");
        SrmOkcContractTermsEntity_HI lastEntity = srmOkcContractTermsDAO_HI.get("FROM SrmOkcContractTermsEntity_HI WHERE creationDate LIKE  '" + sdf.format(d) + "%' ORDER BY termCode DESC");
        if (lastEntity == null) {
//                当天创建的第一份合同模板
            termCode.append("0001");
        } else {
            Integer maxTermCodeSuffix = Integer.parseInt(lastEntity.getTermCode().substring(12));
            termCode.append(String.format("%04d", ++maxTermCodeSuffix));
        }
        return termCode.toString();
    }
}
