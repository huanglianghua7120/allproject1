package saaf.common.fmw.pos.model.inter.server;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.SrmPosManagerCateLinesEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosManagerCateOthersEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosManagerCatesEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierCategoriesEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosManagerCatesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosManagerCatesLinesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosManagerCatesOthersEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmManagerCates;

@Component("srmPosManagerCatesServer")
public class SrmPosManagerCatesServer implements ISrmManagerCates {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosManagerCatesServer.class);

    @Autowired
    private BaseViewObject<SrmPosManagerCatesEntity_HI_RO> cates2Entity_HI_RO;

    @Autowired
    private BaseViewObject<SrmPosManagerCatesLinesEntity_HI_RO> lines2Entity_HI_RO;

    @Autowired
    private BaseViewObject<SrmPosManagerCatesOthersEntity_HI_RO> Others2Entity_HI_RO;

    @Autowired
    private ViewObject<SrmPosManagerCatesEntity_HI> managerCates_HI;

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private ViewObject<SrmPosManagerCateLinesEntity_HI> linesDao_HI;

    @Autowired
    private ViewObject<SrmPosManagerCateOthersEntity_HI> othersDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierCategoriesEntity_HI> categoriesDAO_HI;
    /**
     * 查询品类列表
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmPosManagerCatesEntity_HI_RO> findManagerCatesList(
            JSONObject parameters, Integer pageIndex, Integer pageRows)
            throws Exception {
        LOGGER.info("--->" + parameters);
        try {
            StringBuffer sqlBuffer = new StringBuffer(SrmPosManagerCatesEntity_HI_RO.QUERY_MANAGER_CATES_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "spsi.supplier_name", "supplierName", sqlBuffer, map, "like");
            SaafToolUtils.parperParam(parameters, "spmc.document_number", "documentNumber", sqlBuffer, map, "like");
            SaafToolUtils.parperParam(parameters, "spmc.document_status", "documentStatus", sqlBuffer, map, "like");
            SaafToolUtils.parperParam(parameters, "su.user_name", "createName", sqlBuffer, map, "like");
            SaafToolUtils.parperParam(parameters, "spmc.manager_cate_id", "managerCateId", sqlBuffer, map, "=");
            SaafToolUtils.parperParam(parameters, "spmc.document_type", "documentType", sqlBuffer, map, "=");

            String startDate = null;
            String endDate = null;

            if (parameters.getString("startDate") != null && !"".equals(parameters.getString("startDate").trim())) {
                startDate = parameters.getString("startDate");
            }
            if (parameters.getString("endDate") != null && !"".equals(parameters.getString("endDate").trim())) {
                endDate = parameters.getString("endDate");
            }
            if (startDate != null && endDate != null) {
                sqlBuffer.append(" and spmc.CREATION_DATE between :startDate and :endDate");
                map.put("startDate", startDate);
                map.put("endDate", endDate);
            } else if (startDate != null && endDate == null) {
                sqlBuffer.append(" and spmc.CREATION_DATE >= :startDate");
                map.put("startDate", startDate);
            } else if (endDate != null && startDate == null) {
                sqlBuffer.append(" and spmc.CREATION_DATE <= :endDate");
                map.put("endDate", endDate);
            }
            String countSql = "select count(1) from (" + sqlBuffer + ")";
            sqlBuffer.append(" ORDER BY spmc.LAST_UPDATE_DATE desc");
            return cates2Entity_HI_RO.findPagination(sqlBuffer,countSql, map, pageIndex, pageRows);
        } catch (Exception e) {
            throw new Exception("查询合作品类表失败");
        }
    }
    /**
     * 删除品类
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteManagerCates(JSONObject params) throws Exception {
        LOGGER.info("-->" + params.toString());
        SrmPosManagerCatesEntity_HI hi_RO = null;
        try {
            hi_RO = managerCates_HI.findByProperty("manager_cate_id", params.getInteger("managerCateId")).get(0);
            if (hi_RO != null) {
                managerCates_HI.delete(hi_RO);
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除品类失败，" + params.getString("managerCateId") + "不存在", 0, null);
            }
            return SToolUtils.convertResultJSONObj("S", "删除品类成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：品类增加修改
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * managerCateId  新增品类单ID  NUMBER  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  业务实体ID,关联表:srm_base_orgs(废弃)  NUMBER  N
     * documentType  单据类型(POS_MANAGER_CATE_TYPE)  VARCHAR2  Y
     * documentNumber  新增品类单号  VARCHAR2  Y
     * documentStatus  新增品类单状态（POS_QUALIFICATION_STATUS）  VARCHAR2  N
     * needOnsiteInspection  是否现场考察  VARCHAR2  N
     * supplierAdvantage  供方优势  VARCHAR2  N
     * description  说明  VARCHAR2  N
     * reason  原因  VARCHAR2  N
     * fileId  附件id  NUMBER  N
     * managerCateId  新增品类单ID  NUMBER  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  业务实体ID,关联表:srm_base_orgs(废弃)  NUMBER  N
     * documentType  单据类型(POS_MANAGER_CATE_TYPE)  VARCHAR2  Y
     * documentNumber  新增品类单号  VARCHAR2  Y
     * documentStatus  新增品类单状态（POS_QUALIFICATION_STATUS）  VARCHAR2  N
     * needOnsiteInspection  是否现场考察  VARCHAR2  N
     * supplierAdvantage  供方优势  VARCHAR2  N
     * description  说明  VARCHAR2  N
     * reason  原因  VARCHAR2  N
     * fileId  附件id  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveManaerCates(JSONObject params) throws Exception {
        SrmPosManagerCatesEntity_HI hi_RO = null;
        SrmPosManagerCateLinesEntity_HI cateLinesEntity_HI = null;
        SrmPosManagerCateOthersEntity_HI cateOthersEntity_HI = null;
        LOGGER.info("--->>" + params);
        try {
            Integer managerCateId = params.getInteger("managerCateId");
            Integer userId = params.getInteger("varUserId");
            if (managerCateId == null) {
                //新增
                hi_RO = new SrmPosManagerCatesEntity_HI();
                hi_RO.setSupplierId(params.getInteger("supplierId"));
                hi_RO.setOrgId(params.getInteger("instId"));
                hi_RO.setReason(params.getString("reason"));
                hi_RO.setFileId(params.getInteger("fileId"));
                hi_RO.setLastUpdatedBy(userId);
                hi_RO.setCreationDate(new Date());
                hi_RO.setCreatedBy(params.getInteger("varUserId"));
                hi_RO.setLastUpdateDate(new Date());
                hi_RO.setOperatorUserId(params.getInteger("varUserId"));//SaafDateUtils.string2UtilDate(new Date().toString(), "yyyyMMdd")
                String documentNumber = null;
                if ("NEW".equals(params.getString("documentType"))) {
                    documentNumber = saafSequencesUtil.getDocSequences("SAAF_EMPLOYEE", "XZPL-" + DateUtil.date2Str(new Date(), "yyyyMMdd"), 4);
                } else if ("INACT".equals(params.getString("documentType"))) {
                    documentNumber = saafSequencesUtil.getDocSequences("SAAF_EMPLOYEE", "PLSX-" + DateUtil.date2Str(new Date(), "yyyyMMdd"), 4);
                }
                hi_RO.setDocumentNumber(documentNumber);
                hi_RO.setDocumentType(params.getString("documentType"));
                hi_RO.setDocumentStatus("DRAFT");
                hi_RO.setNeedOnsiteInspection(params.getString("needOnsiteInspection"));
                managerCates_HI.save(hi_RO);
                JSONObject object = new JSONObject();
                object.put("managerCateId", hi_RO.getManagerCateId());
                SrmPosManagerCatesEntity_HI_RO entity_HI_RO = findManagerCatesList(object, 0, 0).getData().get(0);
                ////新增lines
                JSONArray linesArray = params.getJSONArray("linesData");
                for (int i = 0, j = linesArray.size(); i < j; i++) {
                    JSONObject valuesJson = linesArray.getJSONObject(i);
                    if (null == valuesJson.getInteger("lineId") || "".equals(valuesJson.getString("lineId").trim())) {
                        cateLinesEntity_HI = new SrmPosManagerCateLinesEntity_HI();
                        cateLinesEntity_HI.setManagerCateId(hi_RO.getManagerCateId());
                        cateLinesEntity_HI.setCategoryId(valuesJson.getInteger("supplierCategoryId"));
                        cateLinesEntity_HI.setSelectFlag(valuesJson.getString("selectFlag"));
                        cateLinesEntity_HI.setBigCategoryCode(valuesJson.getString("bigCategoryCode"));
                        cateLinesEntity_HI.setMiddleCategoryCode(valuesJson.getString("middleCategoryCode"));
                        cateLinesEntity_HI.setSmallCategoryCode(valuesJson.getString("smallCategoryCode"));
                        cateLinesEntity_HI.setCategoryStatus(valuesJson.getString("status"));
                        cateLinesEntity_HI.setOperatorUserId(userId);
                        cateLinesEntity_HI.setCreatedBy(userId);
                        cateLinesEntity_HI.setCreationDate(new Date());
                        cateLinesEntity_HI.setLastUpdatedBy(userId);
                        cateLinesEntity_HI.setLastUpdateDate(new Date());
                        LOGGER.info("-->>" + cateLinesEntity_HI);
                        linesDao_HI.save(cateLinesEntity_HI);
                    }
                    //managerCateId
                }
                //新增Other
                JSONArray othersDataArray = params.getJSONArray("othersData");
                for (int i = 0, j = othersDataArray.size(); i < j; i++) {
                    JSONObject valuesJson = othersDataArray.getJSONObject(i);
                    cateOthersEntity_HI = new SrmPosManagerCateOthersEntity_HI();
                    cateOthersEntity_HI.setManagerCateId(hi_RO.getManagerCateId());
                    cateOthersEntity_HI.setBigCategoryCode(valuesJson.getString("bigCategoryCode"));
                    cateOthersEntity_HI.setMiddleCategoryCode(valuesJson.getString("middleCategoryCode"));
                    cateOthersEntity_HI.setSmallCategoryCode(valuesJson.getString("smallCategoryCode"));
                    cateOthersEntity_HI.setCategoryStatus(valuesJson.getString("status"));
                    cateOthersEntity_HI.setOperatorUserId(userId);
                    cateOthersEntity_HI.setCreatedBy(userId);
                    cateOthersEntity_HI.setCreationDate(new Date());
                    cateOthersEntity_HI.setLastUpdatedBy(userId);
                    cateOthersEntity_HI.setLastUpdateDate(new Date());
                    othersDAO_HI.save(cateOthersEntity_HI);
                }
                return SToolUtils.convertResultJSONObj("S", "保存成功", 1, entity_HI_RO);
            } else {
                //修改
                hi_RO = managerCates_HI.findByProperty("manager_cate_id", params.getInteger("managerCateId")).get(0);
                hi_RO.setSupplierId(params.getInteger("supplierId"));
                hi_RO.setOrgId(params.getInteger("instId"));
                hi_RO.setReason(params.getString("reason"));
                hi_RO.setFileId(params.getInteger("fileId"));
                hi_RO.setLastUpdatedBy(userId);
                hi_RO.setLastUpdateDate(new Date());
                hi_RO.setOperatorUserId(params.getInteger("varUserId"));
                hi_RO.setNeedOnsiteInspection(params.getString("needOnsiteInspection"));
                managerCates_HI.update(hi_RO);

                JSONArray linesArray = params.getJSONArray("linesData");
                for (int i = 0, j = linesArray.size(); i < j; i++) {
                    JSONObject valuesJson = linesArray.getJSONObject(i);
                    //修改liners
                    cateLinesEntity_HI = linesDao_HI.findByProperty("line_id", valuesJson.getInteger("lineId")).get(0);
                    cateLinesEntity_HI.setSelectFlag(valuesJson.getString("selectFlag"));
                    cateLinesEntity_HI.setOperatorUserId(userId);
                    cateLinesEntity_HI.setCreatedBy(userId);
                    cateLinesEntity_HI.setCreationDate(new Date());
                    cateLinesEntity_HI.setLastUpdatedBy(userId);
                    cateLinesEntity_HI.setLastUpdateDate(new Date());
                    LOGGER.info("-->>" + cateLinesEntity_HI);
                    linesDao_HI.update(cateLinesEntity_HI);
                }
                return SToolUtils.convertResultJSONObj("S", "修改成功", 1, hi_RO);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 提交
     *
     * @param params
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateManaerCates(JSONObject params) throws Exception {
        SrmPosManagerCatesEntity_HI hi_RO = null;
        SrmPosManagerCateLinesEntity_HI cateLinesEntity_HI = null;
        SrmPosManagerCateOthersEntity_HI cateOthersEntity_HI = null;
        LOGGER.info("--->>" + params);
        try {
            Integer managerCateId = params.getInteger("managerCateId");
            Integer userId = params.getInteger("varUserId");
            if (managerCateId == null) {
                hi_RO = new SrmPosManagerCatesEntity_HI();
                hi_RO.setSupplierId(params.getInteger("supplierId"));
                hi_RO.setOrgId(params.getInteger("instId"));
                hi_RO.setReason(params.getString("reason"));
                hi_RO.setFileId(params.getInteger("fileId"));
                hi_RO.setLastUpdatedBy(userId);
                hi_RO.setCreationDate(new Date());
                hi_RO.setCreatedBy(params.getInteger("varUserId"));
                hi_RO.setLastUpdateDate(new Date());
                hi_RO.setOperatorUserId(params.getInteger("varUserId"));//SaafDateUtils.string2UtilDate(new Date().toString(), "yyyyMMdd")
                String documentNumber = null;
                if ("NEW".equals(params.getString("documentType"))) {
                    documentNumber = saafSequencesUtil.getDocSequences("SAAF_EMPLOYEE", "XZPL-" + DateUtil.date2Str(new Date(), "yyyyMMdd"), 4);
                } else if ("INACT".equals(params.getString("documentType"))) {
                    documentNumber = saafSequencesUtil.getDocSequences("SAAF_EMPLOYEE", "PLSX-" + DateUtil.date2Str(new Date(), "yyyyMMdd"), 4);
                }
                hi_RO.setDocumentNumber(documentNumber);
                hi_RO.setDocumentType(params.getString("documentType"));
                hi_RO.setDocumentStatus("APPROVING");//
                hi_RO.setNeedOnsiteInspection(params.getString("needOnsiteInspection"));
                managerCates_HI.save(hi_RO);
                JSONObject object = new JSONObject();
                object.put("managerCateId", hi_RO.getManagerCateId());
                SrmPosManagerCatesEntity_HI_RO entity_HI_RO = findManagerCatesList(object, 0, 0).getData().get(0);
                ////新增lines
                JSONArray linesArray = params.getJSONArray("linesData");
                for (int i = 0, j = linesArray.size(); i < j; i++) {
                    JSONObject valuesJson = linesArray.getJSONObject(i);
                    if (null == valuesJson.getInteger("lineId") || "".equals(valuesJson.getString("lineId").trim())) {
                        cateLinesEntity_HI = new SrmPosManagerCateLinesEntity_HI();
                        cateLinesEntity_HI.setManagerCateId(hi_RO.getManagerCateId());
                        cateLinesEntity_HI.setCategoryId(valuesJson.getInteger("supplierCategoryId"));
                        cateLinesEntity_HI.setSelectFlag(valuesJson.getString("selectFlag"));
                        cateLinesEntity_HI.setBigCategoryCode(valuesJson.getString("bigCategoryCode"));
                        cateLinesEntity_HI.setMiddleCategoryCode(valuesJson.getString("middleCategoryCode"));
                        cateLinesEntity_HI.setSmallCategoryCode(valuesJson.getString("smallCategoryCode"));
                        cateLinesEntity_HI.setCategoryStatus(valuesJson.getString("status"));
                        cateLinesEntity_HI.setOperatorUserId(userId);
                        cateLinesEntity_HI.setCreatedBy(userId);
                        cateLinesEntity_HI.setCreationDate(new Date());
                        cateLinesEntity_HI.setLastUpdatedBy(userId);
                        cateLinesEntity_HI.setLastUpdateDate(new Date());
                        LOGGER.info("-->>" + cateLinesEntity_HI);
                        linesDao_HI.save(cateLinesEntity_HI);
                    }
                }
                //新增Other
                JSONArray othersDataArray = params.getJSONArray("othersData");
                for (int i = 0, j = othersDataArray.size(); i < j; i++) {
                    JSONObject valuesJson = othersDataArray.getJSONObject(i);
                    cateOthersEntity_HI = new SrmPosManagerCateOthersEntity_HI();
                    cateOthersEntity_HI.setManagerCateId(hi_RO.getManagerCateId());
                    cateOthersEntity_HI.setBigCategoryCode(valuesJson.getString("bigCategoryCode"));
                    cateOthersEntity_HI.setMiddleCategoryCode(valuesJson.getString("middleCategoryCode"));
                    cateOthersEntity_HI.setSmallCategoryCode(valuesJson.getString("smallCategoryCode"));
                    cateOthersEntity_HI.setCategoryStatus(valuesJson.getString("status"));
                    cateOthersEntity_HI.setOperatorUserId(userId);
                    cateOthersEntity_HI.setCreatedBy(userId);
                    cateOthersEntity_HI.setCreationDate(new Date());
                    cateOthersEntity_HI.setLastUpdatedBy(userId);
                    cateOthersEntity_HI.setLastUpdateDate(new Date());
                    othersDAO_HI.save(cateOthersEntity_HI);
                }
                return SToolUtils.convertResultJSONObj("S", "保存成功", 1, entity_HI_RO);
            } else {
                //修改
                hi_RO = managerCates_HI.findByProperty("manager_cate_id", params.getInteger("managerCateId")).get(0);
                hi_RO.setSupplierId(params.getInteger("supplierId"));
                hi_RO.setOrgId(params.getInteger("instId"));
                hi_RO.setReason(params.getString("reason"));
                hi_RO.setFileId(params.getInteger("fileId"));
                hi_RO.setLastUpdatedBy(userId);
                hi_RO.setLastUpdateDate(new Date());
                hi_RO.setDocumentStatus("APPROVING");
                hi_RO.setNeedOnsiteInspection(params.getString("needOnsiteInspection"));
                hi_RO.setOperatorUserId(params.getInteger("varUserId"));
                managerCates_HI.update(hi_RO);
                JSONObject object = new JSONObject();
                object.put("managerCateId", hi_RO.getManagerCateId());
                SrmPosManagerCatesEntity_HI_RO entity_HI_RO = findManagerCatesList(object, 0, 0).getData().get(0);
                JSONArray linesArray = params.getJSONArray("linesData");
                for (int i = 0, j = linesArray.size(); i < j; i++) {
                    JSONObject valuesJson = linesArray.getJSONObject(i);
                    //修改liners
                    cateLinesEntity_HI = linesDao_HI.findByProperty("line_id", valuesJson.getInteger("lineId")).get(0);
                    cateLinesEntity_HI.setSelectFlag(valuesJson.getString("selectFlag"));
                    cateLinesEntity_HI.setOperatorUserId(userId);
                    cateLinesEntity_HI.setCreatedBy(userId);
                    cateLinesEntity_HI.setCreationDate(new Date());
                    cateLinesEntity_HI.setLastUpdatedBy(userId);
                    cateLinesEntity_HI.setLastUpdateDate(new Date());
                    LOGGER.info("-->>" + cateLinesEntity_HI);
                    linesDao_HI.update(cateLinesEntity_HI);
                }
                return SToolUtils.convertResultJSONObj("S", "提交成功", 1, entity_HI_RO);
            }
        } catch (Exception e) {
            throw new Exception("提交品类失败");
        }
    }

    /**
     * 审批
     *
     * @param params
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateActManagerCates(JSONObject params) throws Exception {
        SrmPosManagerCatesEntity_HI hi_RO = null;
        SrmPosManagerCateLinesEntity_HI cateLinesEntity_HI = null;
        SrmPosSupplierCategoriesEntity_HI categoriesEntity_HI = null;
        LOGGER.info("updateInActManagerCates-->>" + params);
        try {
            Integer userId = params.getInteger("varUserId");
            LOGGER.info("--->userId" + userId);
            Integer managerCateId = params.getInteger("managerCateId");
            if (managerCateId == null) {
                throw new Exception("审批失败");
            }
            hi_RO = managerCates_HI.findByProperty("manager_cate_id", managerCateId).get(0);
            hi_RO.setDocumentStatus("APPROVED");
            hi_RO.setLastUpdateDate(new Date());
            hi_RO.setLastUpdatedBy(userId);
            hi_RO.setOperatorUserId(params.getInteger("operatorUserId"));
            managerCates_HI.update(hi_RO);

            JSONArray linesArray = params.getJSONArray("linesData");
            for (int i = 0, j = linesArray.size(); i < j; i++) {
                JSONObject valuesJson = linesArray.getJSONObject(i);
                //修改liners
                cateLinesEntity_HI = linesDao_HI.findByProperty("line_id", valuesJson.getInteger("lineId")).get(0);
                //只修改已选择的品类
                if (null != valuesJson.getString("selectFlag") && "Y".equals(valuesJson.getString("selectFlag"))) {
                    if (params.getString("documentType") != null && "INACT".equals(params.getString("documentType"))) {
                        cateLinesEntity_HI.setCategoryStatus("INACT");//失效
                    } else if (params.getString("documentType") != null && "NEW".equals(params.getString("documentType"))) {
                        cateLinesEntity_HI.setCategoryStatus("ACT");//新增
                    }
                }
                cateLinesEntity_HI.setOperatorUserId(userId);
                cateLinesEntity_HI.setLastUpdatedBy(userId);
                cateLinesEntity_HI.setLastUpdateDate(new Date());
                LOGGER.info("-->>" + cateLinesEntity_HI);
                linesDao_HI.update(cateLinesEntity_HI);

                categoriesEntity_HI = categoriesDAO_HI.findByProperty("supplier_category_id", cateLinesEntity_HI.getCategoryId()).get(0);
                if ("Y".equals(valuesJson.getString("selectFlag"))) {
                    if (params.getString("documentType") != null && "INACT".equals(params.getString("documentType"))) {
                        categoriesEntity_HI.setStatus("INACT");//失效
                        categoriesEntity_HI.setInvalidDate(new Date());
                    } else if (params.getString("documentType") != null && "NEW".equals(params.getString("documentType"))) {
                        categoriesEntity_HI.setStatus("ACT");//新增
                        categoriesEntity_HI.setInvalidDate(null);//新增时失效时间必须为空
                    }
                }
                categoriesEntity_HI.setLastUpdateDate(new Date());
                categoriesEntity_HI.setLastUpdatedBy(userId);
                categoriesEntity_HI.setOperatorUserId(userId);
                categoriesDAO_HI.update(categoriesEntity_HI);
            }
            return SToolUtils.convertResultJSONObj("S", "审批成功", 1, hi_RO);
        } catch (Exception e) {
            throw new Exception("审批失败");
        }
    }
    /**
     * 驳回
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateInActManagerCates(JSONObject params) throws Exception {
        SrmPosManagerCatesEntity_HI hi_RO = null;
        LOGGER.info("updateInActManagerCates-->>" + params);
        try {
            Integer userId = params.getInteger("varUserId");
            Integer managerCateId = params.getInteger("managerCateId");
            if (managerCateId == null) {
                throw new Exception("驳回失败");
            }
            hi_RO = managerCates_HI.findByProperty("manager_cate_id", managerCateId).get(0);
            hi_RO.setDocumentStatus("REJECT");
            hi_RO.setLastUpdateDate(new Date());
            hi_RO.setLastUpdatedBy(userId);
            hi_RO.setOperatorUserId(params.getInteger("operatorUserId"));
            managerCates_HI.update(hi_RO);
            return SToolUtils.convertResultJSONObj("S", "驳回成功", 1, hi_RO);
        } catch (Exception e) {
            throw new Exception("驳回失败");
        }
    }

    /**
     * 查询需求品类
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmPosManagerCatesLinesEntity_HI_RO> findManagerCatesLinesList(
            JSONObject parameters, Integer pageIndex, Integer pageRows)
            throws Exception {
        LOGGER.info("findManagerCatesLinesList-->>" + parameters);
        try {
            StringBuffer sqlBuffer = new StringBuffer(SrmPosManagerCatesLinesEntity_HI_RO.QUERY_MANAGER_CATES_LINES_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "spmcl.manager_cate_id", "managerCateId", sqlBuffer, map, "=");
            String countSql = "select count(1) from (" + sqlBuffer + ")";
            sqlBuffer.append(" ORDER BY spmcl.LAST_UPDATE_DATE desc");
            return lines2Entity_HI_RO.findPagination(sqlBuffer,countSql, map, pageIndex, pageRows);
        } catch (Exception e) {
            LOGGER.error("-->查询需求品类失败");
            throw new Exception("查询需求品类失败");
        }
    }

    /**
     * 查询其他品类表
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmPosManagerCatesOthersEntity_HI_RO> findManagerCatesOhersList(
            JSONObject parameters, Integer pageIndex, Integer pageRows)
            throws Exception {
        LOGGER.info("findManagerCatesLinesList-->>" + parameters);
        try {
            StringBuffer sqlBuffer = new StringBuffer(SrmPosManagerCatesOthersEntity_HI_RO.QUERY_OTHER_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "spmco.manager_cate_id", "managerCateId", sqlBuffer, map, "=");
            String countSql = "select count(1) from (" + sqlBuffer + ")";
            sqlBuffer.append(" ORDER BY spmco.LAST_UPDATE_DATE desc");
            return Others2Entity_HI_RO.findPagination(sqlBuffer,countSql, map, pageIndex, pageRows);
        } catch (Exception e) {
            LOGGER.error("-->查询其他品类失败");
            throw new Exception("查询其他品类失败");
        }
    }
}
