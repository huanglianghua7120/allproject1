package saaf.common.fmw.pos.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SaafInstitutionEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseCategoriesEntity_HI;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.*;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosInvestigationPlanLinesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierCategoriesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierSitesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosInvestigationPlanLines;

import javax.ws.rs.DefaultValue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：现场考察品类
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
@Component("srmPosInvestigationPlanLinesServer")
public class SrmPosInvestigationPlanLinesServer implements ISrmPosInvestigationPlanLines {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosInvestigationPlanLinesServer.class);

    @Autowired
    private ViewObject<SrmPosInvestigationPlanLinesEntity_HI> srmPosInvestigationPlanLinesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosInvestigationPlanLinesEntity_HI_RO> srmPosInvestigationPlanLinesDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPosInvestigationPlanSitesEntity_HI> srmPosInvestigationPlanSitesDAO_HI;

    @Autowired
    private ViewObject<SrmPosInvestigationPlanCategoriesEntity_HI> srmPosInvestigationPlanCategoriesDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierSitesEntity_HI> srmPosSupplierSitesDAO_HI;

    @Autowired
    private ViewObject<SrmBaseCategoriesEntity_HI> srmBaseCategoriesDAO_HI;

    @Autowired
    private ViewObject<SaafInstitutionEntity_HI> saafInstitutionDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosSupplierSitesEntity_HI_RO> srmPosSupplierSitesDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPosSupplierCategoriesEntity_HI_RO> srmPosSupplierCategoriesDAO_HI_RO;


    public SrmPosInvestigationPlanLinesServer() {
        super();
    }

    /**
     * LOV:计划考察单
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmPosInvestigationPlanLinesEntity_HI_RO> findInvestigationPlanNumber(
            JSONObject parameters, @DefaultValue("1") Integer pageIndex, @DefaultValue("10") Integer pageRows)
            throws Exception {
        try {
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer sb = new StringBuffer();
            sb.append(SrmPosInvestigationPlanLinesEntity_HI_RO.LOV_INVESTIGATION_QUERY_SQL);
            SaafToolUtils.parperParam(parameters, "ipl.supplier_id", "supplierId", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(parameters, "ipl.investigation_plan_id", "investigationPlanId", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(parameters, "ipl.investigation_plan_lines_id", "investigationPlanLinesId", sb, queryParamMap, "=");
            sb.append("\tAND ip.plan_status ='APPROVED' \n");
            String countSql = "select count(1) from (" + sb + ")";
            Pagination<SrmPosInvestigationPlanLinesEntity_HI_RO> rowSet =
                    srmPosInvestigationPlanLinesDAO_HI_RO.findPagination(sb.toString(), countSql,queryParamMap, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询考察品类信息
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosInvestigationPlanLinesEntity_HI_RO> findSrmReviewPlanCategories(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmPosInvestigationPlanLinesEntity_HI_RO.QUERY_PLAN_CATEGORIES_SQL);
            SaafToolUtils.parperParam(jsonParams, "ipl.supplier_id", "supplierId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "ipl.investigation_plan_lines_id", "investigationPlanLinesId", queryString, queryParamMap, "=");
            queryParamMap.put("localeReviewId", jsonParams.getInteger("localeReviewId"));
            queryString.append(" GROUP BY categoryId ");
            List<SrmPosInvestigationPlanLinesEntity_HI_RO> rowSet = srmPosInvestigationPlanLinesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
            for (int i = 0; i < rowSet.size(); i++) {
                rowSet.get(i).setSelectedFlag("Y");
            }
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    /**
     * 批量导入
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveList(JSONObject params) throws Exception {
        LOGGER.info("批量导入考察计划行，参数：" + params.toString());
        JSONArray jsonArray = params.getJSONArray("data");
        Integer userId = params.getInteger("varUserId");
        Integer investigationPlanId = params.getJSONObject("info").getInteger("investigationPlanId");
        //判断是否通过校验
        JSONArray error = checkArray(jsonArray, investigationPlanId);
        if (error.size() > 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "保存失败", error.size(), error);
        }

        List<SrmPosInvestigationPlanLinesEntity_HI> linesList = new ArrayList<>();
        SrmPosInvestigationPlanLinesEntity_HI lineEntity = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //遍历数据放入linesList,去重后先保存行数据
        for (int i = 0; i < jsonArray.size(); i++) {
            lineEntity = new SrmPosInvestigationPlanLinesEntity_HI();
            lineEntity.setInvestigationPlanId(investigationPlanId);
            lineEntity.setSupplierId(srmPosSupplierInfoDAO_HI.findByProperty("supplier_name", jsonArray.getJSONObject(i).getString("supplierName")).get(0).getSupplierId());
            lineEntity.setPlannedTime(sdf.parse(jsonArray.getJSONObject(i).getString("plannedTime")));
            lineEntity.setPlannedBy(jsonArray.getJSONObject(i).getString("plannedBy"));
            lineEntity.setLinesDescription(jsonArray.getJSONObject(i).getString("linesDescription"));
            lineEntity.setOperatorUserId(userId);
            linesList.add(lineEntity);
        }
        //去重复后的行数据放入 LinesList1 中，然后执行保存操作
        List<SrmPosInvestigationPlanLinesEntity_HI> linesList1 = new ArrayList<SrmPosInvestigationPlanLinesEntity_HI>();
        for (SrmPosInvestigationPlanLinesEntity_HI lineEntity1 : linesList) {
            if (!linesList1.contains(lineEntity1)) {
                linesList1.add(lineEntity1);
            }
        }
        srmPosInvestigationPlanLinesDAO_HI.saveOrUpdateAll(linesList1);
        //保存行信息对应的地点和品类信息
        for (int j = 0; j < linesList1.size(); j++) {
            LOGGER.info("批量导入考察计划行id，==============================>：" + linesList1.get(j).getInvestigationPlanLinesId());
            Integer investigationPlanLinesId = linesList1.get(j).getInvestigationPlanLinesId();
            List<SrmPosInvestigationPlanSitesEntity_HI> sitesList = new ArrayList<>();
            SrmPosInvestigationPlanSitesEntity_HI sitesEntity = null;
            List<SrmPosInvestigationPlanCategoriesEntity_HI> cateList = new ArrayList<>();
            SrmPosInvestigationPlanCategoriesEntity_HI cateEntity = null;
            List<SrmPosSupplierInfoEntity_HI> supplierInfoList = new ArrayList<>();
            int supplierId = 0;
            for (int z = 0; z < jsonArray.size(); z++) {
                supplierInfoList = srmPosSupplierInfoDAO_HI.findByProperty("supplier_name", jsonArray.getJSONObject(z).getString("supplierName"));
                if(supplierInfoList!=null&&supplierInfoList.size() > 0) {
                    supplierId = supplierInfoList.get(0).getSupplierId();
                    if (supplierId == linesList1.get(j).getSupplierId()) {
                        if (!"".equals(jsonArray.getJSONObject(z).getString("instName")) && null != jsonArray.getJSONObject(z).getString("instName")) {
                            Integer orgId = saafInstitutionDAO_HI.findByProperty("inst_name", jsonArray.getJSONObject(z).getString("instName")).get(0).getInstId();
                            //通过业务实体id和供应商id查找对应的地点id
                            StringBuffer sql = new StringBuffer("SELECT ss.supplier_site_id supplierSiteId\n" +
                                    "FROM srm_pos_supplier_sites ss\n" +
                                    "WHERE ss.org_id = " + orgId + " AND ss.supplier_id = " + supplierId + "");
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("orgId", orgId);
                            map.put("supplierId", supplierId);
                            List<SrmPosSupplierSitesEntity_HI_RO> suppilerSitesList = srmPosSupplierSitesDAO_HI_RO.findList(sql, map);
                            Integer sitesId = suppilerSitesList.get(0).getSupplierSiteId();

                            sitesEntity = new SrmPosInvestigationPlanSitesEntity_HI();
                            sitesEntity.setInvestigationPlanLinesId(investigationPlanLinesId);
                            sitesEntity.setSupplierSiteId(sitesId);
                            sitesEntity.setOrgId(orgId);
                            sitesEntity.setOperatorUserId(userId);
                            sitesList.add(sitesEntity);
                        }

                        if (!"".equals(jsonArray.getJSONObject(z).getString("fullCategoryCode")) && null != jsonArray.getJSONObject(z).getString("fullCategoryCode")) {
                            Integer cateId = srmBaseCategoriesDAO_HI.
                                    findByProperty("full_category_code", jsonArray.getJSONObject(z).getString("fullCategoryCode")).get(0).getCategoryId();
                            cateEntity = new SrmPosInvestigationPlanCategoriesEntity_HI();
                            cateEntity.setInvestigationPlanLinesId(investigationPlanLinesId);
                            cateEntity.setCategoryId(cateId);
                            cateEntity.setOperatorUserId(userId);
                            cateList.add(cateEntity);
                        }
                    }
                }
            }
            if (sitesList!=null&&sitesList.size() > 0) {
                srmPosInvestigationPlanSitesDAO_HI.saveOrUpdateAll(sitesList);
            }
            if (cateList!=null&&cateList.size() > 0) {
                srmPosInvestigationPlanCategoriesDAO_HI.saveOrUpdateAll(cateList);
            }
        }
        JSONObject resultObj = new JSONObject();
        resultObj.put("msg", "成功导入供应商信息:" + linesList1.size() + "行!");
        resultObj.put("status", "S");
        return resultObj;
    }

    /**
     * 批量导入的校验方法
     *
     * @param jsonArray
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    private JSONArray checkArray(JSONArray jsonArray, Integer investigationPlanId) {
        if (jsonArray.isEmpty()) {
            return null;
        }
        JSONArray error = new JSONArray();
        JSONObject e = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            //判断供应商是否存在
            List<SrmPosSupplierInfoEntity_HI> SupplierList = srmPosSupplierInfoDAO_HI.findByProperty("supplier_name", obj.getString("supplierName"));
            if (null == SupplierList || SupplierList.size() == 0) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "供应商不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            Integer supplierId = SupplierList.get(0).getSupplierId();
            //判断该考察计划下是否已经存在该供应商
            StringBuffer lineSql = new StringBuffer("SELECT ipl.investigation_plan_lines_id investigationPlanLinesId\n" +
                    "FROM srm_pos_investigation_plan_lines ipl\n" +
                    "WHERE ipl.supplier_id = " + supplierId + " AND ipl.investigation_plan_id = " + investigationPlanId + "\n");
            Map<String, Object> lineMap = new HashMap<String, Object>();
            lineMap.put("investigationPlanId", investigationPlanId);
            lineMap.put("supplierId", supplierId);
            List<SrmPosInvestigationPlanLinesEntity_HI_RO> linesList = srmPosInvestigationPlanLinesDAO_HI_RO.findList(lineSql, lineMap);
            if (null != linesList && linesList.size() > 0) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "该考察计划已经存在该供应商");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            //日期类型的限制（生效时间）
            if (!valueIsDate(obj.getString("plannedTime"))) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "日期类型为：'yyyy-mm-dd hh:mm:ss'");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            if (("".equals(obj.getString("instName")) || null == obj.getString("instName")) &&
                    ("".equals(obj.getString("siteName")) || null == obj.getString("siteName")) &&
                    ("".equals(obj.getString("fullCategoryCode")) || null == obj.getString("fullCategoryCode")) &&
                    ("".equals(obj.getString("fullCategoryName")) || null == obj.getString("fullCategoryName"))) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "业务实体、地点名称、品类编码和名称不能同时为空");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            //-------------------地点与业务实体的判断----------star
            //1、判断业务实体和地点是否同时有值
            if ((!"".equals(obj.getString("instName")) && null != obj.getString("instName")) &&
                    ("".equals(obj.getString("siteName")) || null == obj.getString("siteName"))) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "业务实体与地点名称应同时录入");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            if (("".equals(obj.getString("instName")) || null == obj.getString("instName")) &&
                    (!"".equals(obj.getString("siteName")) && null != obj.getString("siteName"))) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "业务实体与地点名称应同时录入");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            //2、同时有值，判断业务实体是否正确，地点是否存在、有效且与业务实体对应
            if ((!"".equals(obj.getString("instName")) && null != obj.getString("instName")) &&
                    (!"".equals(obj.getString("siteName")) && null != obj.getString("siteName"))) {
                //判断该业务实体是否存在
                List<SaafInstitutionEntity_HI> instList = saafInstitutionDAO_HI.
                        findByProperty("inst_name", obj.getString("instName"));
                if (null == instList || instList.size() == 0) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "业务实体不存在");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
                Integer orgId = instList.get(0).getInstId();
                //判断该供应商下的业务实体是否存在合格的地点
                String siteName = obj.getString("siteName");
                StringBuffer sql = new StringBuffer("SELECT ss.supplier_site_id supplierSiteId\n" +
                        "FROM srm_pos_supplier_sites ss\n" +
                        "WHERE ss.org_id = " + orgId + " AND ss.supplier_id = " + supplierId + "  AND ss.site_status = 'EFFECTIVE'" +
                        " AND ss.site_name = '" + siteName + "'");
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("orgId", orgId);
                map.put("supplierId", supplierId);
                map.put("siteName", siteName);
                List<SrmPosSupplierSitesEntity_HI_RO> suppilerSitesList = srmPosSupplierSitesDAO_HI_RO.findList(sql, map);
                if (null == suppilerSitesList || suppilerSitesList.size() == 0) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "地点不存在或者不是有效状态");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }
            //-------------------地点与业务实体的判断----------end
            //-------------------品类的判断----------star
            //1、判断品类编码和品类名称是否同时有值
            if ((!"".equals(obj.getString("fullCategoryCode")) && null != obj.getString("fullCategoryCode")) &&
                    ("".equals(obj.getString("fullCategoryName")) || null == obj.getString("fullCategoryName"))) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "品类编码与品类名称应同时录入");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            if (("".equals(obj.getString("fullCategoryCode")) || null == obj.getString("fullCategoryCode")) &&
                    (!"".equals(obj.getString("fullCategoryName")) && null != obj.getString("fullCategoryName"))) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "品类编码与品类名称应同时录入");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            //2、同时有值，判断品类编码是否存在，品类名称是否有效且与品类编码对应
            if ((!"".equals(obj.getString("fullCategoryCode")) && null != obj.getString("fullCategoryCode")) &&
                    (!"".equals(obj.getString("fullCategoryName")) && null != obj.getString("fullCategoryName"))) {
                //判断品类编码是否存在
                List<SrmBaseCategoriesEntity_HI> cateList = srmBaseCategoriesDAO_HI.
                        findByProperty("full_category_code", obj.getString("fullCategoryCode"));
                if (null == cateList || cateList.size() == 0) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "品类编码不存在");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
                Integer cateId = cateList.get(0).getCategoryId();
                //判断品类名称是否正确
                String siteName = cateList.get(0).getFullCategoryName();
                if (!siteName.equals(obj.getString("fullCategoryName"))) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "品类名称不正确");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
                //判断该供应商下是否存在该合格的品类
                StringBuffer cateSql = new StringBuffer("SELECT sc.category_id categoryId\n" +
                        "FROM srm_pos_supplier_categories sc\n" +
                        "WHERE sc.category_id = " + cateId + " AND sc.supplier_id = " + supplierId + " AND sc.`status` = 'EFFECTIVE'");
                Map<String, Object> cateMap = new HashMap<String, Object>();
                cateMap.put("cateId", cateId);
                cateMap.put("supplierId", supplierId);
                List<SrmPosSupplierCategoriesEntity_HI_RO> suppilerCateList = srmPosSupplierCategoriesDAO_HI_RO.findList(cateSql, cateMap);
                if (null == suppilerCateList || suppilerCateList.size() == 0) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "品类不存在或者不是有效状态");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }
            //-------------------品类的判断----------end
        }
        //每条信息校验无误后，执行信息之间的校验
        if (jsonArray.size() > 1 && error.size() == 0) {
            for (int i = 0; i < jsonArray.size() - 1; i++) {
                for (int j = i + 1; j < jsonArray.size(); j++) {
                    if (error.size() == 0) {
                        JSONObject object1 = jsonArray.getJSONObject(i);
                        JSONObject object2 = jsonArray.getJSONObject(j);
                        //同个供应商下  考察时间、考察人、行备注需保持一致
                        if (object1.getString("supplierName").equals(object2.getString("supplierName"))) {
                            if (!object1.getString("plannedTime").equals(object2.getString("plannedTime"))) {
                                e = new JSONObject();
                                e.put("ERR_MESSAGE", "同个供应商下的考察时间需保持一致");
                                e.put("ROW_NUM", i + 1);
                                error.add(e);
                                e = new JSONObject();
                                e.put("ERR_MESSAGE", "同个供应商下的考察时间需保持一致");
                                e.put("ROW_NUM", j + 1);
                                error.add(e);
                                break;
                            }
                            if (!object1.getString("plannedBy").equals(object2.getString("plannedBy"))) {
                                e = new JSONObject();
                                e.put("ERR_MESSAGE", "同个供应商下的考察人需保持一致");
                                e.put("ROW_NUM", i + 1);
                                error.add(e);
                                e = new JSONObject();
                                e.put("ERR_MESSAGE", "同个供应商下的考察人需保持一致");
                                e.put("ROW_NUM", j + 1);
                                error.add(e);
                                break;
                            }
                            if (!object1.getString("linesDescription").equals(object2.getString("linesDescription"))) {
                                e = new JSONObject();
                                e.put("ERR_MESSAGE", "同个供应商下的行备注需保持一致");
                                e.put("ROW_NUM", i + 1);
                                error.add(e);
                                e = new JSONObject();
                                e.put("ERR_MESSAGE", "同个供应商下的行备注需保持一致");
                                e.put("ROW_NUM", j + 1);
                                error.add(e);
                                break;
                            }
                            if (object1.getString("instName").equals(object2.getString("instName"))) {
                                e = new JSONObject();
                                e.put("ERR_MESSAGE", "业务实体信息重复录入");
                                e.put("ROW_NUM", i + 1);
                                error.add(e);
                                e = new JSONObject();
                                e.put("ERR_MESSAGE", "业务实体信息重复录入");
                                e.put("ROW_NUM", j + 1);
                                error.add(e);
                                break;
                            }
                            if (object1.getString("fullCategoryCode").equals(object2.getString("fullCategoryCode"))) {
                                e = new JSONObject();
                                e.put("ERR_MESSAGE", "品类信息重复录入");
                                e.put("ROW_NUM", i + 1);
                                error.add(e);
                                e = new JSONObject();
                                e.put("ERR_MESSAGE", "品类信息重复录入");
                                e.put("ROW_NUM", j + 1);
                                error.add(e);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return error;
    }

    //校验时间格式
    private boolean valueIsDate(String s) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            dateFormat.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

}
