package saaf.common.fmw.pos.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.SrmPosInvestigationPlanCategoriesEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosInvestigationPlanEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosInvestigationPlanLinesEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosInvestigationPlanSitesEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosInvestigationPlanEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosInvestigationPlanLinesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosInvestigationPlan;

import java.math.BigDecimal;
import java.util.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：考察计划
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
@Component("srmPosInvestigationPlanServer")
public class SrmPosInvestigationPlanServer implements ISrmPosInvestigationPlan {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosInvestigationPlanServer.class);

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private ViewObject<SrmPosInvestigationPlanEntity_HI> srmPosInvestigationPlanDAO_HI;

    @Autowired
    private ViewObject<SrmPosInvestigationPlanLinesEntity_HI> srmPosInvestigationPlanLinesDAO_HI;

    @Autowired
    private ViewObject<SrmPosInvestigationPlanCategoriesEntity_HI> srmPosInvestigationPlanCategoriesDAO_HI;

    @Autowired
    private ViewObject<SrmPosInvestigationPlanSitesEntity_HI> srmPosInvestigationPlanSitesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosInvestigationPlanEntity_HI_RO> srmPosInvestigationPlanDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPosInvestigationPlanLinesEntity_HI_RO> srmPosInvestigationPlanLinesDAO_HI_RO;

    public SrmPosInvestigationPlanServer() {
        super();
    }

    /**
     * 分页查询现场考察数据
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     zhj             创建
     * ===========================================================================
     */
    @Override
    public Pagination<SrmPosInvestigationPlanEntity_HI_RO> findSrmPosInvestigationPlanInfoList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer sql = new StringBuffer(SrmPosInvestigationPlanEntity_HI_RO.PAGE_SQL);
        SaafToolUtils.parperParam(jsonParams, "si.supplier_name", "supplierName", sql, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "ip.plan_name", "planName", sql, queryParamMap, "LIKE");
        //计算出当前时间与计划考察时间相差的天书，然后拼接查询条件
        BigDecimal surplusDays = jsonParams.getBigDecimal("surplusDays");
        if (surplusDays != null && !"".equals(surplusDays)) {
            sql.append(" AND round(to_number(ipl.planned_time - SYSDATE)) < :surplusDays\n");
            queryParamMap.put("surplusDays", surplusDays);
        }
        String startDate = jsonParams.getString("startDate");
        if (startDate != null && !"".equals(startDate.trim())) {
            sql.append(" AND trunc(ipl.planned_time) >= to_date(:startDate, 'yyyy-mm-dd')\n");
            queryParamMap.put("startDate", startDate);
        }
        String endDate = jsonParams.getString("endDate");
        if (endDate != null && !"".equals(endDate.trim())) {
            sql.append(" AND trunc(ipl.planned_time) <= to_date(:endDate, 'yyyy-mm-dd')\n");
            queryParamMap.put("endDate", endDate);
        }
        //按考察计划编号、供应商进行分组
        //sql.append(" GROUP BY ip.investigation_plan_id, si.supplier_id");
        // 排序
        String countSql = "select count(1) from (" + sql + ")";
        sql.append(" ORDER BY ip.creation_date desc, si.supplier_name ");
        Pagination<SrmPosInvestigationPlanEntity_HI_RO> result = srmPosInvestigationPlanDAO_HI_RO.findPagination
                (sql.toString(), countSql,queryParamMap, pageIndex, pageRows);
        //查询每个单据编号每个供应商需要考察的详细的地点和品类信息
        Integer supplierId = 0;
        Integer investigationPlanId = 0;
        Map<String, Object> map = new HashMap();
        //开始遍历result，对每一条数据查询其详细的地点和品类信息
        for (int i = 0; i < result.getData().size(); i++) {
            supplierId = result.getData().get(i).getSupplierId();
            investigationPlanId = result.getData().get(i).getInvestigationPlanId();
            map.put("supplierId", supplierId);
            map.put("investigationPlanId", investigationPlanId);
            //查询地点信息
            StringBuffer sitesInfoSql = new StringBuffer("SELECT ss.site_name siteName\n" +
                    "FROM srm_pos_investigation_plan_lines ipl\n" +
                    "LEFT JOIN srm_pos_investigation_plan_sites ips ON ips.investigation_plan_lines_id = ipl.investigation_plan_lines_id\n" +
                    "LEFT JOIN srm_pos_supplier_sites ss ON ss.supplier_site_id = ips.supplier_site_id" +
                    " WHERE ipl.supplier_id = " + supplierId + " AND ipl.investigation_plan_id = " + investigationPlanId + "");
            List<SrmPosInvestigationPlanLinesEntity_HI_RO> sitesInfoList = srmPosInvestigationPlanLinesDAO_HI_RO.findList(sitesInfoSql, map);
            //查询品类信息
            StringBuffer categoriesInfoSql = new StringBuffer("SELECT bc.full_category_name fullCategoryName\n" +
                    "FROM srm_pos_investigation_plan_lines ipl\n" +
                    "LEFT JOIN srm_pos_investigation_plan_categories ipc ON ipc.investigation_plan_lines_id = ipl.investigation_plan_lines_id\n" +
                    "LEFT JOIN srm_base_categories bc ON bc.category_id = ipc.category_id" +
                    " WHERE ipl.supplier_id = " + supplierId + " AND ipl.investigation_plan_id = " + investigationPlanId + "");
            List<SrmPosInvestigationPlanLinesEntity_HI_RO> categoriesInfoList = srmPosInvestigationPlanLinesDAO_HI_RO.findList(categoriesInfoSql, map);
            //比较两条数组的长短，把较长的数组放入到infoList，并把较短的数组的信息写入到infoList，返回最终的结果
            List<SrmPosInvestigationPlanLinesEntity_HI_RO> infoList = null;
            //以下分三种情形 ：1、地点和品类都有信息2、地点为空品类不为空3、品类为空地点不为空
            if (sitesInfoList != null && categoriesInfoList != null) {
                if (sitesInfoList.size() > categoriesInfoList.size()) {
                    infoList = sitesInfoList;
                    for (int j = 0; j < categoriesInfoList.size(); j++) {
                        infoList.get(j).setFullCategoryName(categoriesInfoList.get(j).getFullCategoryName());
                    }
                } else if (sitesInfoList.size() <= categoriesInfoList.size()) {
                    infoList = categoriesInfoList;
                    for (int j = 0; j < sitesInfoList.size(); j++) {
                        infoList.get(j).setSiteName(sitesInfoList.get(j).getSiteName());
                    }
                }
            } else if (sitesInfoList == null && categoriesInfoList != null) {
                infoList = categoriesInfoList;
            } else if (sitesInfoList != null && categoriesInfoList == null) {
                infoList = sitesInfoList;
            }
            result.getData().get(i).setCategoryAndSitesList(infoList);
        }
        return result;
    }


    /**
     * 根据ID查询现场考察详细数据
     *
     * @param jsonParams
     * @return
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     zhj             创建
     * ===========================================================================
     */
    @Override
    public List<SrmPosInvestigationPlanEntity_HI_RO> findInvestigationPlanHeaderInfoById(JSONObject jsonParams) {
        Integer investigationPlanId = jsonParams.getInteger("investigationPlanId");
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        queryParamMap.put("investigationPlanId", investigationPlanId);
        StringBuffer sql = new StringBuffer(SrmPosInvestigationPlanEntity_HI_RO.INVESTIGATION_PLAN_INFO_SQL);
        SaafToolUtils.parperParam(jsonParams, "ip.investigation_plan_id", "investigationPlanId", sql, queryParamMap, "=");
        List<SrmPosInvestigationPlanEntity_HI_RO> result = srmPosInvestigationPlanDAO_HI_RO.findList(sql, queryParamMap);

        Integer investigationPlanLinesId = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        //开始遍历result，对每一个供应商查询其详细的地点和品类信息
        for (int i = 0; i < result.size(); i++) {
            investigationPlanLinesId = result.get(i).getInvestigationPlanLinesId();
            map.put("investigationPlanLinesId", investigationPlanLinesId);
            //根据行ID查询某供应商的地点信息
            StringBuffer sitesInfoSql = new StringBuffer("SELECT ips.investigation_plan_sites_id investigationPlanSitesId,\n" +
                    "ips.investigation_plan_lines_id investigationPlanLinesId,\n" +
                    "ips.supplier_site_id supplierSiteId,\n" +
                    "ss.site_name siteName,\n" +
                    "ips.org_id orgId,\n" +
                    "t1.inst_name instName\n" +
                    "FROM srm_pos_investigation_plan_sites ips\n" +
                    "\tLEFT JOIN  srm_pos_supplier_sites ss ON ss.supplier_site_id = ips.supplier_site_id\n" +
                    "\tLEFT JOIN saaf_institution t1 ON  t1.inst_id = ips.org_id\n" +
                    "WHERE  ips.investigation_plan_lines_id =  " + investigationPlanLinesId + "");
            List<SrmPosInvestigationPlanLinesEntity_HI_RO> sitesInfoList = srmPosInvestigationPlanLinesDAO_HI_RO.findList(sitesInfoSql, map);
            //根据行ID查询某供应商的品类信息
            StringBuffer categoriesInfoSql = new StringBuffer("SELECT ipc.investigation_plan_categories_id investigationPlanCategoriesId,\n" +
                    "ipc.investigation_plan_lines_id investigationPlanLinesId,\n" +
                    "ipc.category_id categoryId,\n" +
                    "bc.full_category_name fullCategoryName,\n" +
                    "bc.full_category_code fullCategoryCode\n" +
                    "FROM srm_pos_investigation_plan_categories ipc\n" +
                    "\tLEFT JOIN srm_base_categories bc ON bc.category_id = ipc.category_id\n" +
                    "WHERE  ipc.investigation_plan_lines_id = " + investigationPlanLinesId + " ");
            List<SrmPosInvestigationPlanLinesEntity_HI_RO> categoriesInfoList = srmPosInvestigationPlanLinesDAO_HI_RO.findList(categoriesInfoSql, map);
            //把查询到的结果放到result中去
            result.get(i).setSitesInfoList(sitesInfoList);
            result.get(i).setCategoriesInfoList(categoriesInfoList);
        }
        return result;
    }

    /**
     * Description：保存或更新现场考察数据
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * investigationPlanId  考察计划ID  NUMBER  Y
     * planName  计划名称  VARCHAR2  Y
     * planNumber  单据编号  VARCHAR2  Y
     * planStatus  单据状态（POS_APPROVAL_STATUS）  VARCHAR2  Y
     * description  备注说明  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveInvestigationPlanInfo(JSONObject params) throws Exception {
        LOGGER.info("保存考察计划信息，参数：" + params.toString());
        SrmPosInvestigationPlanEntity_HI investigationPlanEntity = null;
        try {
            Integer operatorUserId = params.getInteger("varUserId");
            Date date = new Date();
            String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
            JSONArray linesArray = params.getJSONArray("data");//行数据
            if(params.get("planName")==null){
                return SToolUtils.convertResultJSONObj("E", "计划名称必须填", 0, null);
            }
            boolean repeatFlag = false;
            repeatFlag = checkLinesParams(params);
            if ("submit".equals(params.getString("operate"))) {
                if (repeatFlag) {
                    return SToolUtils.convertResultJSONObj("E", "一张计划考察单据不能有相同的供应商！", 0, null);
                }
            }
            Map<String, Object> map = checkSiteAndCategoryParams(params);
            if ("submit".equals(params.getString("operate"))) {
                if ("true".equals(map.get("flag"))) {
                    return SToolUtils.convertResultJSONObj("E", "第" + map.get("row") + "行供应商下的考察地点和品类不能同时为空", 0, null);
                }
            }
            // 保存考察计划数据信息判断
            if (null == params.getInteger("investigationPlanId")) {
                investigationPlanEntity = new SrmPosInvestigationPlanEntity_HI();
                String investigationPlanNumber = saafSequencesUtil.getDocSequences("SRM_POS_INVESTIGATION_PLAN", "KCJH-", dateFromate, 4);
                investigationPlanEntity.setPlanNumber(investigationPlanNumber);
                investigationPlanEntity.setPlanStatus("DRAFT");// 新增为拟定状态
                investigationPlanEntity.setCreatedBy(operatorUserId);
                investigationPlanEntity.setCreationDate(new Date());
            } else {
                // 判断存在就是修改
                investigationPlanEntity = srmPosInvestigationPlanDAO_HI.findByProperty("investigationPlanId", params.getInteger("investigationPlanId")).get(0);
            }

            // 如果为提交操作，则现场考察数据为SUBMITTED状态
            if ("submit".equals(params.getString("operate"))) {
                investigationPlanEntity.setPlanStatus("APPROVED");// 已批准
            }

            investigationPlanEntity.setPlanName(params.getString("planName"));
            investigationPlanEntity.setDescription(params.getString("description"));
            investigationPlanEntity.setOperatorUserId(operatorUserId);
            srmPosInvestigationPlanDAO_HI.saveOrUpdate(investigationPlanEntity);
            LOGGER.info("investigationPlanId：" + investigationPlanEntity.getInvestigationPlanId());

            //根据头ID删除旧的行数据
            List<SrmPosInvestigationPlanLinesEntity_HI> oldLinesData = srmPosInvestigationPlanLinesDAO_HI.findByProperty("investigationPlanId", investigationPlanEntity.getInvestigationPlanId());
            for (int x = 0; x < oldLinesData.size(); x++) {
                //根据行id删除旧的地点信息
                List<SrmPosInvestigationPlanSitesEntity_HI> oldSitesData = srmPosInvestigationPlanSitesDAO_HI.findByProperty("investigationPlanLinesId", oldLinesData.get(x).getInvestigationPlanLinesId());
                srmPosInvestigationPlanSitesDAO_HI.delete(oldSitesData);
                //根据行id删除旧的品类信息
                List<SrmPosInvestigationPlanCategoriesEntity_HI> oldCategoryData = srmPosInvestigationPlanCategoriesDAO_HI.findByProperty("investigationPlanLinesId", oldLinesData.get(x).getInvestigationPlanLinesId());
                srmPosInvestigationPlanCategoriesDAO_HI.delete(oldCategoryData);
            }
            srmPosInvestigationPlanLinesDAO_HI.delete(oldLinesData);
            // 保存行层
            List<SrmPosInvestigationPlanLinesEntity_HI> linesList = new ArrayList<SrmPosInvestigationPlanLinesEntity_HI>();

            if (null != linesArray && !linesArray.isEmpty()) {
                for (int i = 0; i < linesArray.size(); i++) {
                    JSONObject linesJson = linesArray.getJSONObject(i);
                    // 保存行数据
                    SrmPosInvestigationPlanLinesEntity_HI investigationPlanLinesEntity = new SrmPosInvestigationPlanLinesEntity_HI();
                    investigationPlanLinesEntity.setInvestigationPlanId(investigationPlanEntity.getInvestigationPlanId());
                    investigationPlanLinesEntity.setCreatedBy(operatorUserId);
                    investigationPlanLinesEntity.setCreationDate(new Date());
                    investigationPlanLinesEntity.setPlannedBy(linesJson.getString("plannedBy"));
                    investigationPlanLinesEntity.setSupplierId(linesJson.getInteger("supplierId"));
                    investigationPlanLinesEntity.setPlannedTime(linesJson.getDate("plannedTime"));
                    investigationPlanLinesEntity.setLinesDescription(linesJson.getString("linesDescription"));
                    investigationPlanLinesEntity.setOperatorUserId(operatorUserId);
                    linesList.add(investigationPlanLinesEntity);
                }
                srmPosInvestigationPlanLinesDAO_HI.saveOrUpdateAll(linesList);
                //保存供应商下的地点和品类信息
                for (int j = 0; j < linesArray.size(); j++) {
                    //保存地点
                    JSONArray sitesArray = linesArray.getJSONObject(j).getJSONArray("sitesSelectList");
                    if (null != sitesArray && !sitesArray.isEmpty()) {
                        List<SrmPosInvestigationPlanSitesEntity_HI> sitesList = new ArrayList<SrmPosInvestigationPlanSitesEntity_HI>();
                        for (int a = 0; a < sitesArray.size(); a++) {
                            JSONObject sitesJson = sitesArray.getJSONObject(a);
                            //保存地点行数据
                            SrmPosInvestigationPlanSitesEntity_HI investigationPlanSitesEntity_HI = new SrmPosInvestigationPlanSitesEntity_HI();
                            investigationPlanSitesEntity_HI.setInvestigationPlanLinesId(linesList.get(j).getInvestigationPlanLinesId());
                            investigationPlanSitesEntity_HI.setCreatedBy(operatorUserId);
                            investigationPlanSitesEntity_HI.setCreationDate(new Date());
                            investigationPlanSitesEntity_HI.setSupplierSiteId(sitesJson.getInteger("supplierSiteId"));
                            investigationPlanSitesEntity_HI.setOrgId(sitesJson.getInteger("orgId"));
                            investigationPlanSitesEntity_HI.setOperatorUserId(operatorUserId);
                            sitesList.add(investigationPlanSitesEntity_HI);
                        }
                        srmPosInvestigationPlanSitesDAO_HI.saveOrUpdateAll(sitesList);
                    }


                    //保存品类
                    JSONArray categoryArray = linesArray.getJSONObject(j).getJSONArray("categoriesSelectList");
                    if (null != categoryArray && !categoryArray.isEmpty()) {
                        List<SrmPosInvestigationPlanCategoriesEntity_HI> categoriesList = new ArrayList<SrmPosInvestigationPlanCategoriesEntity_HI>();
                        for (int b = 0; b < categoryArray.size(); b++) {
                            JSONObject categoryJson = categoryArray.getJSONObject(b);
                            // 保存品类行数据
                            SrmPosInvestigationPlanCategoriesEntity_HI investigationPlanCategoriesEntity_HI = new SrmPosInvestigationPlanCategoriesEntity_HI();
                            investigationPlanCategoriesEntity_HI.setInvestigationPlanLinesId(linesList.get(j).getInvestigationPlanLinesId());
                            investigationPlanCategoriesEntity_HI.setCreatedBy(operatorUserId);
                            investigationPlanCategoriesEntity_HI.setCreationDate(new Date());
                            investigationPlanCategoriesEntity_HI.setCategoryId(categoryJson.getInteger("categoryId"));
                            investigationPlanCategoriesEntity_HI.setOperatorUserId(operatorUserId);
                            categoriesList.add(investigationPlanCategoriesEntity_HI);
                        }
                        srmPosInvestigationPlanCategoriesDAO_HI.saveOrUpdateAll(categoriesList);
                    }
                }
            }
            //srmPosInvestigationPlanLinesDAO_HI.delete(oldLinesData);//删除旧的供应商行数据，需要等执行完地点和品类的删除操作
            JSONObject result = SToolUtils.convertResultJSONObj("S", "保存成功", 1, investigationPlanEntity);
            result.put("investigationPlanId", investigationPlanEntity.getInvestigationPlanId());
            return result;
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            throw new Exception(e);
        }
    }


    /**
     * 校验保存的行数据（逻辑：一张单据下不能有相同的供应商）
     *
     * @param params
     * @return boolean
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     zhj             创建
     * ===========================================================================
     */
    public boolean checkLinesParams(JSONObject params) {
        LOGGER.info("校验保存的行数据");
        boolean flag = false;
        JSONArray linesArray = params.getJSONArray("data");
        if (null != linesArray && !linesArray.isEmpty() && linesArray.size() > 1) {
            for (int i = 0; i < linesArray.size() - 1; i++) {
                for (int j = i + 1; j < linesArray.size(); j++) {
                    if (linesArray.getJSONObject(i).getString("supplierName").equals(linesArray.getJSONObject(j).getString("supplierName"))) {
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 校验保存的地点和品类数据（逻辑：一个供应商下，考察地点和品类不能都为空）
     *
     * @param params
     * @return boolean
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     zhj             创建
     * ===========================================================================
     */
    public Map<String, Object> checkSiteAndCategoryParams(JSONObject params) {
        LOGGER.info("校验保存的地点和品类数据");
        Map<String, Object> map = new HashMap<>();
        JSONArray linesArray = params.getJSONArray("data");
        if (null != linesArray && !linesArray.isEmpty()) {
            for (int i = 0; i < linesArray.size(); i++) {
                JSONArray sitesArray = linesArray.getJSONObject(i).getJSONArray("sitesSelectList");
                JSONArray categoryArray = linesArray.getJSONObject(i).getJSONArray("categoriesSelectList");
                if ((null != sitesArray && !sitesArray.isEmpty()) || (null != categoryArray && !categoryArray.isEmpty())) {
                    map.put("flag", "false");
                } else {
                    map.put("flag", "true");
                    map.put("row", i + 1);
                    break;
                }
            }
        }
        return map;
    }

    /**
     * 根据ID删除现场考察数据
     *
     * @param jsonParams
     * @return
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     zhj             创建
     * ===========================================================================
     */
    @Override
    public JSONObject deleteInvestigationPlanInfo(JSONObject jsonParams) {
        LOGGER.info("-->" + jsonParams.toString());
        SrmPosInvestigationPlanEntity_HI headerEntity = null;
        Integer investigationPlanId = jsonParams.getInteger("investigationPlanId");
        if (investigationPlanId != null && investigationPlanId > 0) {
            SrmPosInvestigationPlanEntity_HI linesEntity1 = srmPosInvestigationPlanDAO_HI.findByProperty("investigationPlanId", investigationPlanId).get(0);
            if (linesEntity1 != null) {
                if (!"DRAFT".equals(linesEntity1.getPlanStatus())) {
                    return SToolUtils.convertResultJSONObj("E", "非拟定状态的计划考察单据不允许删除！", 0, null);
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除计划考察失败，" + jsonParams.getString("qualificationId") + "不存在！", 0, null);
            }
        }

        SrmPosInvestigationPlanLinesEntity_HI linesEntity = null;
        List<SrmPosInvestigationPlanLinesEntity_HI> lineList = null;
        List<SrmPosInvestigationPlanSitesEntity_HI> siteList = null;
        List<SrmPosInvestigationPlanCategoriesEntity_HI> cateList = null;
        try {
            if (null != jsonParams.getInteger("investigationPlanLinesId") && !"".equals(jsonParams.getInteger("investigationPlanLinesId"))) {
                linesEntity = srmPosInvestigationPlanLinesDAO_HI
                        .findByProperty("investigation_plan_lines_id", jsonParams.getInteger("investigationPlanLinesId")).get(0);

                if (linesEntity != null) {
                    siteList = srmPosInvestigationPlanSitesDAO_HI.findByProperty("investigation_plan_lines_id", linesEntity.getInvestigationPlanLinesId());
                    cateList = srmPosInvestigationPlanCategoriesDAO_HI.findByProperty("investigation_plan_lines_id", linesEntity.getInvestigationPlanLinesId());
                    //删除考察计划地点表的信息
                    if (siteList != null && siteList.size() != 0) {
                        srmPosInvestigationPlanSitesDAO_HI.delete(siteList);
                    }
                    //删除考察计划品类表的信息
                    if (cateList != null && cateList.size() != 0) {
                        srmPosInvestigationPlanCategoriesDAO_HI.delete(cateList);
                    }
                    //删除考察计划行表的供应商信息
                    srmPosInvestigationPlanLinesDAO_HI.delete(linesEntity);

                    //用头ID查询是否还有供应商行数据
                    lineList = srmPosInvestigationPlanLinesDAO_HI
                            .findByProperty("investigation_plan_id", jsonParams.getInteger("investigationPlanId"));
                    if (lineList != null && lineList.size() != 0) {
                        //如果不为空，说明该头信息下还有对应的供应商行信息，不删除
                    } else {
                        //否则删除该头信息
                        headerEntity = srmPosInvestigationPlanDAO_HI.findByProperty("investigation_plan_id", jsonParams.getInteger("investigationPlanId")).get(0);
                        srmPosInvestigationPlanDAO_HI.delete(headerEntity);
                    }
                } else {
                    return SToolUtils.convertResultJSONObj("E", "删除失败，" + jsonParams.getInteger("investigationPlanLinesId") + "不存在", 0,
                            null);
                }
            } else {
                headerEntity = srmPosInvestigationPlanDAO_HI
                        .findByProperty("investigation_plan_id", jsonParams.getInteger("investigationPlanId")).get(0);
                srmPosInvestigationPlanDAO_HI.delete(headerEntity);
            }
            return SToolUtils.convertResultJSONObj("S", "删除成功!", 1, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            return SToolUtils.convertResultJSONObj("E", "删除失败!" + e, 0, null);
        }
    }
}
