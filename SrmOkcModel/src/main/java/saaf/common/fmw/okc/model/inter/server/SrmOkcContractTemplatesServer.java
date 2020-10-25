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
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.okc.model.entities.SrmOkcContractTemplatesEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractTemplatesEntity_HI_RO;
import saaf.common.fmw.okc.model.inter.ISrmOkcContractTemplates;
import saaf.common.fmw.okc.utils.ConTempConstant;
import saaf.common.fmw.okc.utils.ConTempState;
import saaf.common.fmw.okc.utils.MapToBeanUtil;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：SrmOkcContractTemplatesServer.java
 * Description：合同模版服务实现类
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date     @Author(Updated By)     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019/5/31      欧阳岛          创建
 * V1.1       2019/7/24      秦晓钊          修改，新增方法findContractTemplatesToLov
 * <p>
 * ==============================================================================
 */
@Component("srmOkcContractTemplatesServer")
public class SrmOkcContractTemplatesServer implements ISrmOkcContractTemplates {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmOkcContractTemplatesServer.class);

    @Autowired
    private ViewObject<SrmOkcContractTemplatesEntity_HI> srmOkcContractTemplatesDAO_HI;

    @Autowired
    private BaseViewObject<SrmOkcContractTemplatesEntity_HI_RO> srmOkcContractTemplatesDAO_HI_RO;
    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;
    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值

    public SrmOkcContractTemplatesServer() {
        super();
    }

    /**
     * Description：分页查询合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmOkcContractTemplatesEntity_HI_RO> findSrmOkcContractTemplatesList(
            JSONObject parameters, Integer pageIndex, Integer pageRows) {
        LOGGER.debug("查询参数:==========>>>{}\t{}\t{}", parameters, pageIndex, pageRows);
        StringBuffer queryString = new StringBuffer(SrmOkcContractTemplatesEntity_HI_RO.QUERY_CONTRACTTEMPLATES_SQL);
        StringBuffer whereString = new StringBuffer();
        Map<String, Object> map = new HashMap();
        SaafToolUtils.parperParam(parameters, "ct.template_code", "templateCode", whereString, map, "LIKE");
        SaafToolUtils.parperParam(parameters, "ct.template_name", "templateName", whereString, map, "LIKE");
        SaafToolUtils.parperParam(parameters, "ct.version_number", "versionNumber", whereString, map, "=");
        SaafToolUtils.parperParam(parameters, "ct.template_type", "templateType", whereString, map, "=");
        SaafToolUtils.parperParam(parameters, "ct.org_id", "orgId", whereString, map, "=");
        SaafToolUtils.parperParam(parameters, "ct.created_by", "createdBy", whereString, map, "=");
        SaafToolUtils.parperParam(parameters, "ct.template_status", "templateStatus", whereString, map, "=");
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
            whereString.append(" AND ct.changing_flag = 'N ' ");
        }


       /* if (whereString.length() > 0) {
            queryString.append(" WHERE 1 = 1 ").append(whereString);
        }*/

        queryString.append(" WHERE ((ct.org_id in (SELECT distinct (sua.inst_id) Organization_Id\n" +
                "                               FROM saaf_user_access_orgs sua,\n" +
                "                                    saaf_institution      si,\n" +
                "                                    saaf_users            su\n" +
                "                              WHERE sua.user_id = su.user_id\n" +
                "                                AND sua.inst_id = si.inst_id\n" +
                "                                and sua.platform_code = 'SAAF'\n" +
                "                                and si.inst_type='ORG'\n" +
                "                                and sua.user_id = "+parameters.getInteger("varUserId")+") "+
                "      AND ct.template_type IN  (" +getTemplateType(parameters.getInteger("varUserId"))+")) or ct.created_by= "+parameters.getInteger("varUserId")+") " );

          if (whereString.length() > 0) {
            queryString.append(whereString);
        }
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" ORDER BY ct.template_code DESC ");
        return srmOkcContractTemplatesDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
    }

    /**
     * Description：查询合同模版类型
     * @param userId
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    private String getTemplateType(Integer userId){
        JSONObject json=new JSONObject();
        json.put("userId",userId);
        List<SrmBaseUserCategoriesEntity_HI_RO> userCategoriesList=srmBaseUserCategories.findUserCategories(json);
        Map map = new HashMap();
        map.put("lookupType", "SER_TENDER_TYPE");
        map.put("enabledFlag", "Y");
        List<SaafLookupValuesEntity_HI> lookupValues = saafLookupValuesDAO_HI.findByProperty(map);
        List  categoryCode=new ArrayList();
        for(SaafLookupValuesEntity_HI vo:lookupValues){
            for(SrmBaseUserCategoriesEntity_HI_RO ro:userCategoriesList){
                if(ro.getCategoryCode().equals(vo.getLookupCode())){
                    categoryCode.add(ro.getCategoryCode());
                }
            }
        }
        String templateType= String.valueOf(categoryCode.stream().distinct().collect(Collectors.joining("','")));
        templateType="'"+templateType+"'";
        return templateType;
    }

    /**
     * Description：保存合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveSrmOkcContractTemplates(JSONObject queryParamJSON) {
        LOGGER.debug("参数:==========>>>{}", queryParamJSON);
        SrmOkcContractTemplatesEntity_HI srmOkcContractTemplatesEntity_HI = null;
        Integer templateId = queryParamJSON.getInteger("templateId");
        Integer templateFileId = queryParamJSON.getInteger("templateFileId");
        Integer uploadUserId = queryParamJSON.getInteger("uploadUserId");
        if (templateId != null) {
            //修改
            srmOkcContractTemplatesEntity_HI = srmOkcContractTemplatesDAO_HI.findByProperty("templateId", templateId).get(0);
            if (!ConTempState.DRAFT.getValue().equals(srmOkcContractTemplatesEntity_HI.getTemplateStatus()) &&
                    !ConTempState.REJECTED.getValue().equals(srmOkcContractTemplatesEntity_HI.getTemplateStatus())) {
                return SToolUtils.convertResultJSONObj("E", "更新合同模版失败，非拟定或驳回状态", 0, null);
            }
            Integer oldTemplateFileId = srmOkcContractTemplatesEntity_HI.getTemplateFileId();
//            SrmOkcContractTemplatesEntity_HI tempObj = JSON.parseObject(queryParamJSON.toString(), SrmOkcContractTemplatesEntity_HI.class);
//            BeanUtils.copyProperties(tempObj, srmOkcContractTemplatesEntity_HI);
            MapToBeanUtil.copyPropertiesInclude(queryParamJSON, srmOkcContractTemplatesEntity_HI);
            if (templateFileId != null && (oldTemplateFileId == null || templateFileId.compareTo(oldTemplateFileId) != 0)) {
                srmOkcContractTemplatesEntity_HI.setUploadUserId(srmOkcContractTemplatesEntity_HI.getOperatorUserId());
                srmOkcContractTemplatesEntity_HI.setUploadDate(new Date());
            }
        } else {
            //新增
            srmOkcContractTemplatesEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmOkcContractTemplatesEntity_HI.class);
            srmOkcContractTemplatesEntity_HI.setTemplateCode(genTemplateCode());
            srmOkcContractTemplatesEntity_HI.setChangingFlag("N");
            if (templateFileId != null && uploadUserId == null) {
                srmOkcContractTemplatesEntity_HI.setUploadUserId(srmOkcContractTemplatesEntity_HI.getOperatorUserId());
                srmOkcContractTemplatesEntity_HI.setUploadDate(new Date());
            }
        }
        srmOkcContractTemplatesEntity_HI.setTemplateStatus(ConTempState.DRAFT.getValue());
        srmOkcContractTemplatesDAO_HI.saveOrUpdate(srmOkcContractTemplatesEntity_HI);
        JSONObject result = SToolUtils.convertResultJSONObj("S", "保存合同模版成功", 1, srmOkcContractTemplatesEntity_HI);
        return result;
    }

    @Override
    public void updateSrmOkcContractTemplates(SrmOkcContractTemplatesEntity_HI srmOkcContractTemplatesEntity_HI) {
        srmOkcContractTemplatesDAO_HI.saveOrUpdate(srmOkcContractTemplatesEntity_HI);
    }

    /**
     * Description：提交合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public JSONObject doSubmitSrmOkcContractTemplates(JSONObject queryParamJSON) {
        LOGGER.debug("参数:==========>>>{}", queryParamJSON);
        Integer templateId = queryParamJSON.getInteger("templateId");
        Integer operatorUserId = queryParamJSON.getInteger("operatorUserId");
//        SrmOkcContractTemplatesEntity_HI tempObj = JSON.parseObject(queryParamJSON.toString(), SrmOkcContractTemplatesEntity_HI.class);
        Integer templateFileId = queryParamJSON.getInteger("templateFileId");
        Integer uploadUserId = queryParamJSON.getInteger("uploadUserId");
        if (templateFileId != null && uploadUserId == null) {
//            tempObj.setUploadUserId(tempObj.getOperatorUserId());
//            tempObj.setUploadDate(new Date());
            queryParamJSON.put("uploadUserId", operatorUserId);
            queryParamJSON.put("uploadDate", new Date());
        }
        return updateTemplateStatus(templateId, operatorUserId, ConTempState.DRAFT.getValue(), ConTempState.DRAFT.getName(), ConTempState.SUBMITTED.getValue(), ConTempState.SUBMITTED.getOpt(), queryParamJSON, null);
    }

    /**
     * Description：审批合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public JSONObject doApprovalSrmOkcContractTemplates(JSONObject queryParamJSON) throws Exception {
        LOGGER.debug("参数:==========>>>{}", queryParamJSON);
        Integer templateId = queryParamJSON.getInteger("templateId");
        String approvalOpinion = queryParamJSON.getString("approvalOpinion");
        Integer operatorUserId = queryParamJSON.getInteger("operatorUserId");
        JSONObject updateRslt = updateTemplateStatus(templateId, operatorUserId, ConTempState.SUBMITTED.getValue(), ConTempState.SUBMITTED.getName(), ConTempState.APPROVED.getValue(), ConTempState.APPROVED.getOpt(), null, approvalOpinion);
        if ("S".equalsIgnoreCase(updateRslt.getString("status"))) {
//            更新的新版本批准后,老版本需禁用
            SrmOkcContractTemplatesEntity_HI contractTemplatesEntity = updateRslt.getObject("data", SrmOkcContractTemplatesEntity_HI.class);
            int versionNumber = contractTemplatesEntity.getVersionNumber();
            String templateCode = contractTemplatesEntity.getTemplateCode();
            forbidPreVersionTemplateStatus(templateCode, versionNumber, operatorUserId);
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
    private void forbidPreVersionTemplateStatus(String templateCode, int versionNumber, Integer operatorUserId) throws Exception {
        if (versionNumber > 0) {
            int preVersion = versionNumber - 1;
            Map sparam = new HashMap();
            sparam.put("templateCode", templateCode);
            sparam.put("versionNumber", preVersion);
            try {
                List<SrmOkcContractTemplatesEntity_HI> list = srmOkcContractTemplatesDAO_HI.findByProperty(sparam);
                SrmOkcContractTemplatesEntity_HI srmOkcContractTemplatesEntity_HI = list.get(0);
                if (!ConTempState.DISABLED.getValue().equalsIgnoreCase(srmOkcContractTemplatesEntity_HI.getTemplateStatus())) {
                    srmOkcContractTemplatesEntity_HI.setOperatorUserId(operatorUserId);
                    srmOkcContractTemplatesEntity_HI.setTemplateStatus(ConTempState.DISABLED.getValue());
                    srmOkcContractTemplatesDAO_HI.update(srmOkcContractTemplatesEntity_HI);
                    forbidPreVersionTemplateStatus(srmOkcContractTemplatesEntity_HI.getTemplateCode(), srmOkcContractTemplatesEntity_HI.getVersionNumber(), operatorUserId);
                }
            } catch (Exception e) {
                LOGGER.warn(e.getMessage(), e);
                throw new UtilsException("禁用老版本异常："+e.getMessage());
            }
        }
    }

    /**
     * Description：驳回合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public JSONObject doRejectSrmOkcContractTemplates(JSONObject queryParamJSON) {
        LOGGER.debug("参数:==========>>>{}", queryParamJSON);
        Integer templateId = queryParamJSON.getInteger("templateId");
        String approvalOpinion = queryParamJSON.getString("approvalOpinion");
        Integer operatorUserId = queryParamJSON.getInteger("operatorUserId");
        return updateTemplateStatus(templateId, operatorUserId, ConTempState.SUBMITTED.getValue(), ConTempState.SUBMITTED.getName(), ConTempState.REJECTED.getValue(), ConTempState.REJECTED.getOpt(), null, approvalOpinion);
    }

    /**
     * Description：更新合同模版状态
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    private JSONObject updateTemplateStatus(Integer templateId, Integer operatorUserId, String startStatus, String startStatusName, String endStatus, String endOpt, Map updateObj, String approvalOpinion) {
        SrmOkcContractTemplatesEntity_HI srmOkcContractTemplatesEntity_HI = null;
        if (templateId != null) {
            srmOkcContractTemplatesEntity_HI = srmOkcContractTemplatesDAO_HI.findByProperty("templateId", templateId).get(0);
            if (!startStatus.equalsIgnoreCase(srmOkcContractTemplatesEntity_HI.getTemplateStatus())) {
                return SToolUtils.convertResultJSONObj("E", endOpt + "合同模版失败,非" + startStatusName, 0, null);
            }
            if (updateObj != null) {
//                BeanUtils.copyProperties(updateObj, srmOkcContractTemplatesEntity_HI);
                MapToBeanUtil.copyPropertiesInclude(updateObj, srmOkcContractTemplatesEntity_HI);
            }
            if (approvalOpinion != null) {
                srmOkcContractTemplatesEntity_HI.setAttribute1(approvalOpinion);
            }
            srmOkcContractTemplatesEntity_HI.setTemplateStatus(endStatus);
            srmOkcContractTemplatesEntity_HI.setOperatorUserId(operatorUserId);
        } else {
            return SToolUtils.convertResultJSONObj("E", endOpt + "合同模版失败,未发现templateId", 0, null);
        }
        srmOkcContractTemplatesDAO_HI.update(srmOkcContractTemplatesEntity_HI);
        JSONObject result = SToolUtils.convertResultJSONObj("S", endOpt + "合同模版成功", 1, srmOkcContractTemplatesEntity_HI);
        return result;
    }

    /**
     * Description：更新合同模版版本
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public JSONObject doChangeSrmOkcContractTemplates(JSONObject queryParamJSON) {
        LOGGER.debug("参数:==========>>>{}", queryParamJSON);
        SrmOkcContractTemplatesEntity_HI oldSrmOkcContractTemplates = null;
        SrmOkcContractTemplatesEntity_HI newSrmOkcContractTemplates = new SrmOkcContractTemplatesEntity_HI();
        Integer templateId = queryParamJSON.getInteger("templateId");
        Integer newTemplateId = -1;
        Integer operatorUserId = queryParamJSON.getInteger("operatorUserId");
        if (templateId != null) {
            oldSrmOkcContractTemplates = srmOkcContractTemplatesDAO_HI.findByProperty("templateId", templateId).get(0);
            if (!ConTempState.APPROVED.getValue().equalsIgnoreCase(oldSrmOkcContractTemplates.getTemplateStatus())) {
                return SToolUtils.convertResultJSONObj("E", "更新合同模版失败,非批准状态", 0, null);
            }
            if (ConTempConstant.CHANGING_FLAG_Y.equalsIgnoreCase(oldSrmOkcContractTemplates.getChangingFlag())) {
                return SToolUtils.convertResultJSONObj("E", "更新合同模版失败,此版本已更新", 0, null);
            }
            BeanUtils.copyProperties(oldSrmOkcContractTemplates, newSrmOkcContractTemplates);
            oldSrmOkcContractTemplates.setChangingFlag("Y");
            oldSrmOkcContractTemplates.setOperatorUserId(operatorUserId);

            Integer newConTempVersionNumber = oldSrmOkcContractTemplates.getVersionNumber() + 1;
            Map<String, Object> conditionMap = new HashMap<>();
            conditionMap.put("templateCode", oldSrmOkcContractTemplates.getTemplateCode());
            conditionMap.put("versionNumber", newConTempVersionNumber);
            List<SrmOkcContractTemplatesEntity_HI> checkList = srmOkcContractTemplatesDAO_HI.findByProperty(conditionMap);
            if (checkList != null && checkList.size() > 0) {
                return SToolUtils.convertResultJSONObj("E", "更新合同模版失败,其他用户已抢先更新", 0, null);
            }
            newSrmOkcContractTemplates.setTemplateId(null);
            newSrmOkcContractTemplates.setCreatedBy(null);
            newSrmOkcContractTemplates.setCreationDate(null);
            newSrmOkcContractTemplates.setLastUpdatedBy(null);
            newSrmOkcContractTemplates.setLastUpdateDate(null);
            newSrmOkcContractTemplates.setAttribute1(null);
            newSrmOkcContractTemplates.setVersionNumber(newConTempVersionNumber);
            newSrmOkcContractTemplates.setTemplateStatus(ConTempState.DRAFT.getValue());
            newSrmOkcContractTemplates.setChangingFlag("N");
            newSrmOkcContractTemplates.setOperatorUserId(operatorUserId);

            srmOkcContractTemplatesDAO_HI.update(oldSrmOkcContractTemplates);
            newTemplateId = (Integer) srmOkcContractTemplatesDAO_HI.save(newSrmOkcContractTemplates);
        } else {
            return SToolUtils.convertResultJSONObj("E", "更新合同模版失败,未发现templateId", 0, null);
        }
        JSONObject result = SToolUtils.convertResultJSONObj("S", "更新合同模版成功", 1, newTemplateId);
        return result;
    }

    /**
     * Description：删除合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteSrmOkcContractTemplates(JSONObject params) throws Exception {
        LOGGER.info("删除合同模版信息，参数：" + params.toString());
        SrmOkcContractTemplatesEntity_HI srmOkcContractTemplatesEntity_HI = null;
        Integer templateId = params.getInteger("templateId");
        try {
            if (templateId == null) {
                return SToolUtils.convertResultJSONObj("E", "删除失败，" + params.getString("templateId") + "不存在", 0, null);
            }
            srmOkcContractTemplatesEntity_HI = srmOkcContractTemplatesDAO_HI.findByProperty("templateId", templateId).get(0);
            if (ConTempState.DRAFT.getValue().equals(srmOkcContractTemplatesEntity_HI.getTemplateStatus())) {
                srmOkcContractTemplatesDAO_HI.delete(srmOkcContractTemplatesEntity_HI);
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除失败,只能删除拟定的合同模版!", 0, null);
            }
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("删除合同模版异常："+e.getMessage());
        }
    }

    /**
     * Description：查询单个合同模版信息
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public JSONObject findSrmOkcContractTemplate(JSONObject jsonParam) {
        Integer templateId = jsonParam.getInteger("templateId");
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer(SrmOkcContractTemplatesEntity_HI_RO.QUERY_CONTRACTTEMPLATE_ALL_SQL);
        queryString.append(" where ct.template_id  = :templateId");
        map.put("templateId", templateId);
        List<SrmOkcContractTemplatesEntity_HI_RO> srmOkcContractTemplates = srmOkcContractTemplatesDAO_HI_RO.findList(queryString, map);
        if (srmOkcContractTemplates == null || srmOkcContractTemplates.size() != 1) {
            return SToolUtils.convertResultJSONObj("E", "查询合同模板信息失败!", 0, null);
        }
        return SToolUtils.convertResultJSONObj("S", "查询合同模板信息成功!", 0, srmOkcContractTemplates.get(0));
    }

    /**
     * Description：生成合同模版编号
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    private String genTemplateCode() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer templateCode = new StringBuffer("MB-").append(sdf.format(d).replaceAll("-", "")).append("-");
        SrmOkcContractTemplatesEntity_HI lastEntity = srmOkcContractTemplatesDAO_HI.get("FROM SrmOkcContractTemplatesEntity_HI WHERE creationDate LIKE  '" + sdf.format(d) + "%' ORDER BY templateCode DESC");
        if (lastEntity == null) {
//                当天创建的第一份合同模板
            templateCode.append("0001");
        } else {
            Integer maxTemplateCodeSuffix = Integer.parseInt(lastEntity.getTemplateCode().substring(12));
            templateCode.append(String.format("%04d", ++maxTemplateCodeSuffix));
        }
        return templateCode.toString();
    }

    /**
     * Description：获取合同模板
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/13      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public SrmOkcContractTemplatesEntity_HI findSrmOkcContractTemplatesEntity_HI(Integer templateId) {
        return srmOkcContractTemplatesDAO_HI.getById(templateId);
    }

    /**
     * Description：Lov分页查询合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.1       2019/7/24      秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmOkcContractTemplatesEntity_HI_RO> findContractTemplatesToLov(
            JSONObject parameters, Integer pageIndex, Integer pageRows) {
        LOGGER.debug("查询参数:==========>>>{}\t{}\t{}", parameters, pageIndex, pageRows);
        StringBuffer queryString = new StringBuffer(SrmOkcContractTemplatesEntity_HI_RO.QUERY_CONTRACTTEMPLATES_LOV_SQL);
        Map<String, Object> map = new HashMap<String, Object>();

        SaafToolUtils.parperParam(parameters, "ct.template_code", "templateCode", queryString, map, "like");
        SaafToolUtils.parperParam(parameters, "ct.template_name", "templateName", queryString, map, "like");
        SaafToolUtils.parperParam(parameters, "slv2.meaning", "templateTypeName", queryString, map, "like");
        SaafToolUtils.parperParam(parameters, "ct.template_status", "templateStatus", queryString, map, "=");

        //是否要包含全局模板
        if ("Y".equals(parameters.getString("isGlobal"))) {
            Integer orgId = parameters.getInteger("orgId") == null ? -1 : parameters.getInteger("orgId");
            queryString.append(" AND (ct.org_id = " + orgId + " OR ct.global_flag = 'Y') ");
        } else {
            SaafToolUtils.parperParam(parameters, "ct.org_id", "orgId", queryString, map, "=");
        }
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" ORDER BY ct.template_code desc ");
        return srmOkcContractTemplatesDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
    }

}
