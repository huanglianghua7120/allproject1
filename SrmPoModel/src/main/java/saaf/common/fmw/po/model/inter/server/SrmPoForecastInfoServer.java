package saaf.common.fmw.po.model.inter.server;

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
import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;
import saaf.common.fmw.intf.model.inter.server.SrmUserInstSqlUtil;
import saaf.common.fmw.po.model.entities.SrmPoForecastInfoEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoForecastLinesEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoForecastInfoEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoHeadersEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoForecastInfo;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;
import saaf.common.fmw.utils.SrmUtils;

import java.util.*;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;

/**
 * Project Name：SrmPoForecastInfoServer
 * Company Name：SIE
 * Program Name：
 * Description：采购预测
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoForecastInfoServer")
public class SrmPoForecastInfoServer implements ISrmPoForecastInfo {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoForecastInfoServer.class);

    @Autowired
    private BaseViewObject<SrmPoForecastInfoEntity_HI_RO> forecasInfoDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPoForecastInfoEntity_HI> forecasInfoDAO_HI;

    @Autowired
    private ViewObject<SaafEmployeesEntity_HI> saafEmployeesDAO_HI;

    @Autowired
    private ViewObject<SrmPoForecastLinesEntity_HI> forecastLinesDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;

    @Autowired
    private BaseViewObject<SrmPoHeadersEntity_HI_RO> SrmPoHeadersDAO_HI_RO;

    @Autowired
    private ViewObject<SrmIntfLogsEntity_HI> srmIntfLogsDAO_HI;//日志

    @Autowired
    private SrmUserInstSqlUtil srmUserInstSqlUtil;

    /**
     * Description：采购预测(供应商) 业务实体及收获组织下拉框
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject findPoForecastSupplierOption(JSONObject jsonParams) {
        Map<String, Object> queryParamMap = new HashMap<>();
        List<SrmPoForecastInfoEntity_HI_RO> forecastOrg = null;
        List<SrmPoForecastInfoEntity_HI_RO> forecastOrganization = null;
        if (jsonParams.getString("flag") != null && "supplier".equals(jsonParams.getString("flag")) && jsonParams.getString("varSupplierId") != null) {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmPoForecastInfoEntity_HI_RO.QUERY_FORECAST_ORG_SQL);
            SaafToolUtils.parperParam(jsonParams, "fl.supplier_id", "varSupplierId", queryString, queryParamMap, "=");
            forecastOrg = forecasInfoDAO_HI_RO.findList(queryString.toString(), queryParamMap);

            StringBuffer queryString1 = new StringBuffer();
            queryString1.append(SrmPoForecastInfoEntity_HI_RO.QUERY_FORECAST_ORG_SQL);
            SaafToolUtils.parperParam(jsonParams, "fl.supplier_id", "varSupplierId", queryString1, queryParamMap, "=");
            forecastOrganization = forecasInfoDAO_HI_RO.findList(queryString1.toString(), queryParamMap);
        }
        JSONObject object = new JSONObject();
        object.put("forecastOrg", forecastOrg);
        object.put("forecastOrganization", forecastOrganization);
        return object;
    }

    /**
     * Description：提交采购预测
     * @param jsonParam
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject updateSubmitForecast(JSONObject jsonParam) throws Exception {
        if (jsonParam.getInteger("forecastId") != null && jsonParam.getInteger("forecastId") > 0) {
            SrmPoForecastInfoEntity_HI srmPoForecastInfoEntity_HI = forecasInfoDAO_HI.getById(jsonParam.getInteger("forecastId"));
            srmPoForecastInfoEntity_HI.setForecastStatus("APPROVED");
            srmPoForecastInfoEntity_HI.setOperatorUserId(jsonParam.getInteger("operatorUserId"));
            forecasInfoDAO_HI.update(srmPoForecastInfoEntity_HI);
        }
        return SToolUtils.convertResultJSONObj("S", "提交成功", 1, "");
    }

    /**
     * Description：失效采购预测
     * @param jsonParam
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject updateInvalidForecast(JSONObject jsonParam) throws Exception {
        if (jsonParam.getInteger("forecastId") != null && jsonParam.getInteger("forecastId") > 0) {
            SrmPoForecastInfoEntity_HI srmPoForecastInfoEntity_HI = forecasInfoDAO_HI.getById(jsonParam.getInteger("forecastId"));
            srmPoForecastInfoEntity_HI.setForecastStatus("DISABLED");
            srmPoForecastInfoEntity_HI.setInvalidDate(new Date());
            srmPoForecastInfoEntity_HI.setInvalidBy(jsonParam.getInteger("operatorUserId"));
            srmPoForecastInfoEntity_HI.setOperatorUserId(jsonParam.getInteger("operatorUserId"));
            forecasInfoDAO_HI.update(srmPoForecastInfoEntity_HI);
            List<SrmPoForecastLinesEntity_HI> srmPoForecastLinesEntityList = forecastLinesDAO_HI.findByProperty("forecast_id", jsonParam.getInteger("forecastId"));
            List<SrmPoForecastLinesEntity_HI> list = new ArrayList<>();
            for (SrmPoForecastLinesEntity_HI srmPoForecastLinesEntity : srmPoForecastLinesEntityList) {
                SrmPoForecastLinesEntity_HI srmPoForecastLines = forecastLinesDAO_HI.getById(srmPoForecastLinesEntity.getForecastLineId());
                srmPoForecastLines.setInvalidDate(new Date());
                srmPoForecastLines.setOperatorUserId(jsonParam.getInteger("operatorUserId"));
                list.add(srmPoForecastLines);
            }
            forecastLinesDAO_HI.saveOrUpdateAll(list);
        }
        return SToolUtils.convertResultJSONObj("S", "失效成功", 1, "");
    }

    /**
     * Description：撤回采购预测
     * @param jsonParam
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject updateRetractForecast(JSONObject jsonParam) throws Exception {
        if (jsonParam.getInteger("forecastId") != null && jsonParam.getInteger("forecastId") > 0) {
            SrmPoForecastInfoEntity_HI srmPoForecastInfoEntity_HI = forecasInfoDAO_HI.getById(jsonParam.getInteger("forecastId"));
            srmPoForecastInfoEntity_HI.setForecastStatus("REJECTED");
            srmPoForecastInfoEntity_HI.setOperatorUserId(jsonParam.getInteger("operatorUserId"));
            forecasInfoDAO_HI.update(srmPoForecastInfoEntity_HI);
        }
        return SToolUtils.convertResultJSONObj("S", "发布成功", 1, null);
    }

    /**
     * Description：发布采购预测
     * @param jsonParam
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject updatePublishForecast(JSONObject jsonParam) throws Exception {
        if (jsonParam.getInteger("forecastId") != null && jsonParam.getInteger("forecastId") > 0) {
            SrmPoForecastInfoEntity_HI srmPoForecastInfoEntity_HI = forecasInfoDAO_HI.getById(jsonParam.getInteger("forecastId"));
            srmPoForecastInfoEntity_HI.setForecastStatus("PUBLISHED");
            srmPoForecastInfoEntity_HI.setOperatorUserId(jsonParam.getInteger("operatorUserId"));
            forecasInfoDAO_HI.update(srmPoForecastInfoEntity_HI);
            List<SrmPoForecastLinesEntity_HI> srmPoForecastLinesEntityList = forecastLinesDAO_HI.findByProperty("forecast_id", jsonParam.getInteger("forecastId"));
            List<SrmPoForecastLinesEntity_HI> list = new ArrayList<>();
            for (SrmPoForecastLinesEntity_HI srmPoForecastLinesEntity : srmPoForecastLinesEntityList) {
                SrmPoForecastLinesEntity_HI srmPoForecastLines = forecastLinesDAO_HI.getById(srmPoForecastLinesEntity.getForecastLineId());
                srmPoForecastLines.setReleaseFlag("Y");
                srmPoForecastLines.setReleaseDate(new Date());
                srmPoForecastLines.setOperatorUserId(jsonParam.getInteger("operatorUserId"));
                list.add(srmPoForecastLines);
            }
            forecastLinesDAO_HI.saveOrUpdateAll(list);
        }
        return SToolUtils.convertResultJSONObj("S", "发布成功", 1, null);
    }

    /**
     * Description：批量导入
     * @param jsonParams 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */

    @Override
    public JSONObject saveImportForecast(JSONObject jsonParams, Integer userId) {
        JSONObject info = jsonParams.getJSONObject("info");
        int forecastId = -1;
        JSONArray jsonArray = jsonParams.getJSONArray("data");
        if(null == jsonArray || jsonArray.isEmpty()){
            SToolUtils.convertResultJSONObj(ERROR_STATUS,"暂无数据",0,null);
        }
        JSONArray error = cehckArray(jsonArray, info);
        if (null != error && error.size() > 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "导入失败", error.size(), error);
        }
        if (info.getString("forecastId") != null && !"".equals(info.getString("forecastId"))) {
            forecastId = info.getInteger("forecastId");
        } else if ("".equals(info.getString("forecastId")) || forecastId < 1) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "请先保存预测", error.size(), error);
        }
        SrmPoForecastLinesEntity_HI srmPoForecastLinesEntity_HI = null;
        List<SrmPoForecastLinesEntity_HI> list = new ArrayList<SrmPoForecastLinesEntity_HI>();
        int count = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            srmPoForecastLinesEntity_HI = new SrmPoForecastLinesEntity_HI();
            JSONObject object = jsonArray.getJSONObject(i);
            SrmPosSupplierInfoEntity_HI srmPosSupplierInfoEntity = srmPosSupplierInfoDAO_HI.findByProperty("supplier_name", object.getString("supplierName")).get(0);
            if (srmPosSupplierInfoEntity != null && srmPosSupplierInfoEntity.getSupplierId() > 0) {
                srmPoForecastLinesEntity_HI.setSupplierId(srmPosSupplierInfoEntity.getSupplierId());
                if (srmPosSupplierInfoEntity.getBlacklistFlag() != null && "Y".equals(srmPosSupplierInfoEntity.getBlacklistFlag())) {
                    return SToolUtils.convertResultJSONObj("E", "第" + (++i) + "行，供应商为黑名单", 0, null);
                }
            }
            SaafEmployeesEntity_HI saafEmployeesEntity_HI = saafEmployeesDAO_HI.findByProperty("employeeName", object.getString("employeeName")).get(0);
            if (saafEmployeesEntity_HI != null && saafEmployeesEntity_HI.getEmployeeId() > 0) {
                srmPoForecastLinesEntity_HI.setBuyerId(saafEmployeesEntity_HI.getEmployeeId());
            }
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_ITEM_LOV_SQL);
            SaafToolUtils.parperParam(object, "sbi.item_code", "itemCode", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(info, "sbi.organization_id", "organizationId", queryString, queryParamMap, "=");
            List<SrmPoHeadersEntity_HI_RO> srmPoHeadersEntitylist = SrmPoHeadersDAO_HI_RO.findList(queryString, queryParamMap);
            for (int y = 0; y < srmPoHeadersEntitylist.size(); y++) {
                srmPoForecastLinesEntity_HI.setItemId(srmPoHeadersEntitylist.get(0).getItemId());
            }
            srmPoForecastLinesEntity_HI.setForecastId(forecastId);
            srmPoForecastLinesEntity_HI.setDemandQuantity(object.getBigDecimal("demandQuantity"));
            srmPoForecastLinesEntity_HI.setDemandDate(object.getDate("demandDate"));
            srmPoForecastLinesEntity_HI.setOperatorUserId(userId);
            list.add(srmPoForecastLinesEntity_HI);
            ++count;
        }
        forecastLinesDAO_HI.saveOrUpdateAll(list);
        JSONObject resultObj = new JSONObject();
        resultObj.put("msg", "导入成功行数为:" + count + "行!");
        resultObj.put("status", "S");
        return resultObj;
    }

    /**
     * Description：导入数据校验
     * @param jsonArray 数组
     * @param info 头层信息
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private JSONArray cehckArray(JSONArray jsonArray, JSONObject info) {
        if (null == jsonArray || jsonArray.isEmpty()){
            return null;
        }
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        JSONArray error = new JSONArray();
        JSONObject e = null;
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            List<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoEntity = srmPosSupplierInfoDAO_HI.findByProperty("supplier_name", object.getString("supplierName"));
            if (srmPosSupplierInfoEntity == null || srmPosSupplierInfoEntity.size() < 1) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "供应商名称不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            List<SaafEmployeesEntity_HI> saafEmployeesEntity_HI = saafEmployeesDAO_HI.findByProperty("employeeName", object.getString("employeeName"));
            if (saafEmployeesEntity_HI == null || saafEmployeesEntity_HI.size() < 1) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "采购员不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            String aaaa = object.getString("demandDate");
            String[] bb = aaaa.split("-");
            if (bb.length < 2) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "日期格式有误，举例：2018-12-02");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_ITEM_LOV_SQL);
            SaafToolUtils.parperParam(object, "sbi.item_code", "itemCode", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(info, "sbi.organization_id", "organizationId", queryString, queryParamMap, "=");
            List<SrmPoHeadersEntity_HI_RO> srmPoHeadersEntitylist = SrmPoHeadersDAO_HI_RO.findList(queryString, queryParamMap);
            if (srmPoHeadersEntitylist == null || srmPoHeadersEntitylist.size() < 1) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "物料编码不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
        }
        return error;
    }

    /**
     * Description：查询头
     * @param jsonParams 查询参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SrmPoForecastInfoEntity_HI_RO> queryForecastInfoList(JSONObject jsonParams) {
        Map<String, Object> queryParamMap1 = new HashMap<String, Object>();
        StringBuffer queryString1 = new StringBuffer();
        queryString1.append(SrmPoForecastInfoEntity_HI_RO.QUERY_FORECAST_HEADER_SQL);
        SaafToolUtils.parperParam(jsonParams, "fi.forecast_id", "forecastId", queryString1, queryParamMap1, "=");
        List<SrmPoForecastInfoEntity_HI_RO> result = forecasInfoDAO_HI_RO.findList(queryString1.toString(), queryParamMap1);
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPoForecastInfoEntity_HI_RO.QUERY_FORECAST_LINE_SQL);
        SaafToolUtils.parperParam(jsonParams, "fi.forecast_id", "forecastId", queryString, queryParamMap, "=");
        String categoryNameOrCode = jsonParams.getString("categoryNameOrCode");
        if (categoryNameOrCode != null) {
            //验证字符串是否含有SQL关键字及字符，有则返回NULL
            if (SrmUtils.isContainSQL(categoryNameOrCode)) {
                return null;
            }
            queryString.append(" AND (bi.item_code LIKE '%");
            queryString.append(categoryNameOrCode);
            queryString.append("%'");
            queryString.append(" OR bi.item_name LIKE '%");
            queryString.append(categoryNameOrCode);
            queryString.append("%')");
        }
        List<SrmPoForecastInfoEntity_HI_RO> linesResult = forecasInfoDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        result.get(0).setLineList(linesResult);
        return result;
    }

    /**
     * Description：查询采购预测
     * @param jsonParams 查询参数
     * @param pageIndex 页码
     * @param pageRows 行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoForecastInfoEntity_HI_RO> findForecastInfoList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer queryString = new StringBuffer();
        StringBuffer appendString = new StringBuffer();
        queryString.append(SrmPoForecastInfoEntity_HI_RO.QUERY_FORECAST_SQL);
        if (jsonParams.getInteger("forecastId") != null && jsonParams.getInteger("forecastId") > 0) {
            SaafToolUtils.parperParam(jsonParams, "fi.forecast_id", "forecastId", queryString, queryParamMap, "=");
            appendString.append(queryString);
            String categoryNameOrCode = jsonParams.getString("categoryNameOrCode");
            if (categoryNameOrCode != null) {
                //验证字符串是否含有SQL关键字及字符，有则返回NULL
                if (SrmUtils.isContainSQL(categoryNameOrCode)) {
                    return null;
                }
                appendString.append(" AND (bi.item_code LIKE '%");
                appendString.append(categoryNameOrCode);
                appendString.append("%'");
                appendString.append(" OR bi.item_name LIKE '%");
                appendString.append(categoryNameOrCode);
                appendString.append("%')");
            }
        } else {
            if (jsonParams.getString("flag") != null && "supplier".equals(jsonParams.getString("flag")) && jsonParams.getString("varSupplierId") != null) {
                SaafToolUtils.parperParam(jsonParams, "fl.supplier_id", "varSupplierId", queryString, queryParamMap, "=");
            } else {
                SaafToolUtils.parperParam(jsonParams, "fl.supplier_id", "supplierId", queryString, queryParamMap, "=");
            }
            SaafToolUtils.parperParam(jsonParams, "fi.forecast_name", "forecastName", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "fi.prediction_type", "predictionType", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "fi.forecast_status", "forecastStatus", queryString, queryParamMap, "=");
            String demandDateFrom = jsonParams.getString("demandDateFrom");
            if (demandDateFrom != null && !"".equals(demandDateFrom.trim())) {
                queryString.append(" AND fl.demand_date >= to_date(:demandDateFrom, 'yyyy-mm-dd')\n");
                queryParamMap.put("demandDateFrom", demandDateFrom);
            }
            String demandDateTo = jsonParams.getString("demandDateTo");
            if (demandDateTo != null && !"".equals(demandDateTo.trim())) {
                queryString.append(" AND fl.demand_date <= to_date(:demandDateTo, 'yyyy-mm-dd')\n");
                queryParamMap.put("demandDateTo", demandDateTo);
            }
            SaafToolUtils.parperParam(jsonParams, "fl.buyer_id", "employeeId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "emp.employee_name", "employeeName", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "bc.full_category_code", "fullCategoryCode", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "bi.item_code", "itemCode", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "fi.org_id", "orgId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "fi.organization_id", "organizationId", queryString, queryParamMap, "=");
            appendString.append(queryString);
        }
        if (jsonParams.getString("flag") != null && "supplier".equals(jsonParams.getString("flag"))) {
            appendString.append(" AND fi.forecast_status IN ('DISABLED','PUBLISHED')");
        }
        //只能查看当前账号已分配的业务实体相关的数据
        //srmUserInstSqlUtil.concatUserInstSql(jsonParams.getInteger("varUserId"), appendString, queryParamMap, "fi");
        queryString.append(" AND srm_sys_check_access_f(" + jsonParams.getInteger("varUserId") + ", fi.org_id, bc.category_id, null, null, null, null, null) = 'Y' ");
        String countSql = "select count(1) from (" + queryString + ")";
        appendString.append(" ORDER BY fi.LAST_UPDATE_DATE DESC");
        Pagination<SrmPoForecastInfoEntity_HI_RO> result = forecasInfoDAO_HI_RO.findPagination(appendString.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：保存采购预测，输入头字段（forecastStatus，forecastName，predictionType，orgId，organizationId，operatorUserId）
     *      行字段（categoryId，itemId，supplierId，demandQuantity，demandDate，buyerId）
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * sourceId  数据来源ID  VARCHAR2  N
     * forecastType  预测分类  VARCHAR2  N
     * remark  备注  VARCHAR2  N
     * forecastNumber  预测单号  VARCHAR2  N
     * forecastId  预测ID  NUMBER  Y
     * forecastName  预测名称  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * predictionType  预测类型  VARCHAR2  N
     * forecastStatus  预测状态  VARCHAR2  N
     * invalidDate  失效日期  DATE  N
     * invalidBy  失效人  NUMBER  N
     * releaseDate  发布日期  DATE  N
     * categoryName  （废弃）类别名称  VARCHAR2  N
     * categoryCode  （废弃）类别编码  VARCHAR2  N
     * forecastDate  （废弃）预测日期  DATE  N
     * itemCode  （废弃）物料编码  VARCHAR2  N
     * itemDescription  （废弃）物料名称  VARCHAR2  N
     * employeeId  （废弃）采购员  NUMBER  N
     * needQuantity  （废弃）需求数量  NUMBER  N
     * needByDate  （废弃）需求日期  DATE  N
     * vendnameGroup  （废弃）供应商组合  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * forecastId  预测ID  NUMBER  Y
     * forecastName  预测名称  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * predictionType  预测类型  VARCHAR2  N
     * forecastStatus  预测状态  VARCHAR2  N
     * invalidDate  失效日期  DATE  N
     * invalidBy  失效人  NUMBER  N
     * releaseDate  （废弃）发布日期  DATE  N
     * categoryName  （废弃）类别名称  VARCHAR2  N
     * categoryCode  （废弃）类别编码  VARCHAR2  N
     * forecastDate  （废弃）预测日期  DATE  N
     * itemCode  （废弃）物料编码  VARCHAR2  N
     * itemDescription  （废弃）物料名称  VARCHAR2  N
     * employeeId  （废弃）采购员  NUMBER  N
     * needQuantity  （废弃）需求数量  NUMBER  N
     * needByDate  （废弃）需求日期  DATE  N
     * vendnameGroup  （废弃）供应商组合  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */
    @Override
    public JSONObject updatePoForecastInfo(JSONObject jsonParams) throws Exception {
        if(null == jsonParams.getString("forecastName") || "".equals(jsonParams.getString("forecastName"))){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"预测名称必填",0,null);
        }
        if(null == jsonParams.getString("predictionType") || "".equals(jsonParams.getString("predictionType"))){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"预测类型必填",0,null);
        }
        if(null == jsonParams.getInteger("orgId") || "".equals(jsonParams.getInteger("orgId"))){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"业务实体必填",0,null);
        }
        SrmPoForecastInfoEntity_HI srmPoForecastInfoEntity_HI = new SrmPoForecastInfoEntity_HI();
        JSONArray jsonParam = jsonParams.getJSONArray("lines");
        if (jsonParams.getInteger("forecastId") != null) {
            srmPoForecastInfoEntity_HI = forecasInfoDAO_HI.getById(jsonParams.getInteger("forecastId"));
            if (srmPoForecastInfoEntity_HI == null) {
                return SToolUtils.convertResultJSONObj("E", "单据不存在，或已被删除！", 0, null);
            }
        } else {
            srmPoForecastInfoEntity_HI.setForecastStatus("DRAFT");
        }
        if (jsonParams.getString("flag") != null && "submit".equals(jsonParams.getString("flag"))) {
            srmPoForecastInfoEntity_HI.setForecastStatus("APPROVED");
        }
        srmPoForecastInfoEntity_HI.setForecastName(jsonParams.getString("forecastName"));
        srmPoForecastInfoEntity_HI.setPredictionType(jsonParams.getString("predictionType"));
        srmPoForecastInfoEntity_HI.setOrgId(jsonParams.getInteger("orgId"));
        srmPoForecastInfoEntity_HI.setOrganizationId(jsonParams.getInteger("organizationId"));
        srmPoForecastInfoEntity_HI.setOperatorUserId(jsonParams.getInteger("operatorUserId"));
        forecasInfoDAO_HI.saveOrUpdate(srmPoForecastInfoEntity_HI);
        JSONObject object = null;
        List<SrmPoForecastLinesEntity_HI> list = new ArrayList<>();
        SrmPoForecastLinesEntity_HI srmPoForecastLinesEntity_HI = null;
        if(null != jsonParam && jsonParam.size()>0){
            for (int i = 0; i < jsonParam.size(); i++) {
                object = jsonParam.getJSONObject(i);
                //验证是否存在黑名单的供应商
                SrmPosSupplierInfoEntity_HI srmPosSupplierInfoEntity = srmPosSupplierInfoDAO_HI.getById(object.getInteger("supplierId"));
                if (srmPosSupplierInfoEntity != null && "Y".equals(srmPosSupplierInfoEntity.getBlacklistFlag())) {
                    return SToolUtils.convertResultJSONObj("E", "第" + (++i) + "行，供应商为黑名单", 0, null);
                }

            /*if(object.getInteger("forecastLineId") != null && object.getInteger("forecastLineId") > 0){
                srmPoForecastLinesEntity_HI.setForecastLineId(object.getInteger("forecastLineId"));
            }*/
                if (object.getString("ts") != null && "N".equals(object.getString("ts"))) {
                    //如果不存在黑名单的供应商则进行保存或提交
                    srmPoForecastLinesEntity_HI = new SrmPoForecastLinesEntity_HI();
                    srmPoForecastLinesEntity_HI.setForecastId(srmPoForecastInfoEntity_HI.getForecastId());
                    srmPoForecastLinesEntity_HI.setCategoryId(object.getInteger("categoryId"));
                    srmPoForecastLinesEntity_HI.setItemId(object.getInteger("itemId"));
                    srmPoForecastLinesEntity_HI.setSupplierId(object.getInteger("supplierId"));
                    srmPoForecastLinesEntity_HI.setDemandQuantity(object.getBigDecimal("demandQuantity"));
                    srmPoForecastLinesEntity_HI.setDemandDate(object.getDate("demandDate"));
                    srmPoForecastLinesEntity_HI.setBuyerId(object.getInteger("employeeId"));
                    srmPoForecastLinesEntity_HI.setOperatorUserId(jsonParams.getInteger("operatorUserId"));
                    list.add(srmPoForecastLinesEntity_HI);
                }
            }
            forecastLinesDAO_HI.saveOrUpdateAll(list);
        }
        JSONObject revert = new JSONObject();
        if (jsonParams.getString("flag") != null && "submit".equals(jsonParams.getString("flag"))) {
            revert.put("msg", "提交成功！");
        } else {
            revert.put("msg", "保存成功！");
        }
        revert.put("status", "S");
        revert.put("srmPoForecastInfoEntity_HI", srmPoForecastInfoEntity_HI);
        return revert;
    }

    /**
     * Description：保存采购预测接口，输入头字段（forecastStatus，forecastName，predictionType，orgId，organizationId）
     * 行字段（categoryId，itemId，supplierId，demandQuantity，demandDate，buyerId）
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * sourceId  数据来源ID  VARCHAR2  N
     * forecastType  预测分类  VARCHAR2  N
     * remark  备注  VARCHAR2  N
     * forecastNumber  预测单号  VARCHAR2  N
     * forecastId  预测ID  NUMBER  Y
     * forecastName  预测名称  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * predictionType  预测类型  VARCHAR2  N
     * forecastStatus  预测状态  VARCHAR2  N
     * invalidDate  失效日期  DATE  N
     * invalidBy  失效人  NUMBER  N
     * releaseDate  发布日期  DATE  N
     * categoryName  （废弃）类别名称  VARCHAR2  N
     * categoryCode  （废弃）类别编码  VARCHAR2  N
     * forecastDate  （废弃）预测日期  DATE  N
     * itemCode  （废弃）物料编码  VARCHAR2  N
     * itemDescription  （废弃）物料名称  VARCHAR2  N
     * employeeId  （废弃）采购员  NUMBER  N
     * needQuantity  （废弃）需求数量  NUMBER  N
     * needByDate  （废弃）需求日期  DATE  N
     * vendnameGroup  （废弃）供应商组合  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * forecastId  预测ID  NUMBER  Y
     * forecastName  预测名称  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * predictionType  预测类型  VARCHAR2  N
     * forecastStatus  预测状态  VARCHAR2  N
     * invalidDate  失效日期  DATE  N
     * invalidBy  失效人  NUMBER  N
     * releaseDate  （废弃）发布日期  DATE  N
     * categoryName  （废弃）类别名称  VARCHAR2  N
     * categoryCode  （废弃）类别编码  VARCHAR2  N
     * forecastDate  （废弃）预测日期  DATE  N
     * itemCode  （废弃）物料编码  VARCHAR2  N
     * itemDescription  （废弃）物料名称  VARCHAR2  N
     * employeeId  （废弃）采购员  NUMBER  N
     * needQuantity  （废弃）需求数量  NUMBER  N
     * needByDate  （废弃）需求日期  DATE  N
     * vendnameGroup  （废弃）供应商组合  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */
    @Override
    public JSONObject updatePoForecastInfoExternal(JSONObject jsonParams, int userId) throws Exception {
        SrmPoForecastInfoEntity_HI srmPoForecastInfoEntity_HI = new SrmPoForecastInfoEntity_HI();
        JSONArray jsonParam = jsonParams.getJSONArray("lines");
        if (jsonParams.getInteger("forecastId") != null) {
            srmPoForecastInfoEntity_HI = forecasInfoDAO_HI.getById(jsonParams.getInteger("forecastId"));
        } else {
            srmPoForecastInfoEntity_HI.setForecastStatus("DRAFT");
        }
        if (jsonParams.getString("flag") != null && "submit".equals(jsonParams.getString("flag"))) {
            srmPoForecastInfoEntity_HI.setForecastStatus("APPROVED");
        }
        srmPoForecastInfoEntity_HI.setForecastName(jsonParams.getString("forecastName"));
        srmPoForecastInfoEntity_HI.setPredictionType(jsonParams.getString("predictionType"));
        srmPoForecastInfoEntity_HI.setOrgId(jsonParams.getInteger("orgId"));
        srmPoForecastInfoEntity_HI.setOrganizationId(jsonParams.getInteger("organizationId"));
        srmPoForecastInfoEntity_HI.setOperatorUserId(jsonParams.getInteger("operatorUserId"));
        forecasInfoDAO_HI.saveOrUpdate(srmPoForecastInfoEntity_HI);
        JSONObject object = null;
        List<SrmPoForecastLinesEntity_HI> list = new ArrayList<>();
        SrmPoForecastLinesEntity_HI srmPoForecastLinesEntity_HI = null;
        if(null != jsonParam && jsonParam.size()>0){
            for (int i = 0; i < jsonParam.size(); i++) {
                object = jsonParam.getJSONObject(i);
                //验证是否存在黑名单的供应商
                SrmPosSupplierInfoEntity_HI srmPosSupplierInfoEntity = srmPosSupplierInfoDAO_HI.getById(object.getInteger("supplierId"));
                if (srmPosSupplierInfoEntity != null/* && "Y".equals(srmPosSupplierInfoEntity.getBlacklistFlag())*/) {
                    return SToolUtils.convertResultJSONObj("E", "第" + (++i) + "行，供应商为黑名单", 0, null);
                }
                if (object.getString("ts") != null && "N".equals(object.getString("ts"))) {
                    //如果不存在黑名单的供应商则进行保存或提交
                    srmPoForecastLinesEntity_HI = new SrmPoForecastLinesEntity_HI();
                    srmPoForecastLinesEntity_HI.setForecastId(srmPoForecastInfoEntity_HI.getForecastId());
                    srmPoForecastLinesEntity_HI.setCategoryId(object.getInteger("categoryId"));
                    srmPoForecastLinesEntity_HI.setItemId(object.getInteger("itemId"));
                    srmPoForecastLinesEntity_HI.setSupplierId(object.getInteger("supplierId"));
                    srmPoForecastLinesEntity_HI.setDemandQuantity(object.getBigDecimal("demandQuantity"));
                    srmPoForecastLinesEntity_HI.setDemandDate(object.getDate("demandDate"));
                    srmPoForecastLinesEntity_HI.setBuyerId(object.getInteger("employeeId"));
                    srmPoForecastLinesEntity_HI.setOperatorUserId(jsonParams.getInteger("operatorUserId"));
                    list.add(srmPoForecastLinesEntity_HI);
                }
            }
            forecastLinesDAO_HI.saveOrUpdateAll(list);
        }
        JSONObject revert = new JSONObject();
        if (jsonParams.getString("flag") != null && "submit".equals(jsonParams.getString("flag"))) {
            revert.put("msg", "提交成功！");
        } else {
            revert.put("msg", "保存成功！");
        }
        revert.put("status", "S");
        revert.put("srmPoForecastInfoEntity_HI", srmPoForecastInfoEntity_HI);
        //保存日志
        JSONObject jsonData = new JSONObject();  //最终结果的返回
        SrmIntfLogsEntity_HI logsEntity = null;
        logsEntity = new SrmIntfLogsEntity_HI();
        logsEntity.setIntfType("PO_FORECAST_IN");//接口类型BASE_INTF_TYPE
        logsEntity.setTableName("srm_po_forecast_info");
        logsEntity.setTableId(srmPoForecastInfoEntity_HI.getForecastId());//接口取数对应的表ID
        logsEntity.setDataDirection("IN");//数据方向(IN：输入， OUT：输出)
        logsEntity.setSendSystem(srmPoForecastInfoEntity_HI.getSourceCode());//数据发送方
        logsEntity.setReceiveSystem("SRM");
        logsEntity.setInData(jsonParams.toJSONString());//输入报文
        jsonData.put("srmPoForecastInfoEntity_HI", srmPoForecastInfoEntity_HI);
        jsonData.put("srmPoForecastLinesEntityList", list);
        logsEntity.setOutData(jsonData.toJSONString());//输出报文
        logsEntity.setDescription("采购预测输入接口");
        logsEntity.setOperatorUserId(userId);
        logsEntity.setIntfStatus("S");
        srmIntfLogsDAO_HI.save(logsEntity);
        revert.put("jsonData", jsonData);
        return revert;
    }
}
