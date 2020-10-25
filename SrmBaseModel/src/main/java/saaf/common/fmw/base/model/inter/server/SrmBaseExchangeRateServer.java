package saaf.common.fmw.base.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.base.adf.common.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseExchangeRateEntity_HI;

import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.base.model.entities.readonly.SrmBaseExchangeRateEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseExchangeRate;
import saaf.common.fmw.common.utils.SaafToolUtils;

import javax.ws.rs.DefaultValue;

@Component("srmBaseExchangeRateServer")
public class SrmBaseExchangeRateServer implements ISrmBaseExchangeRate {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseExchangeRateServer.class);

    @Autowired
    private BaseViewObject<SrmBaseExchangeRateEntity_HI_RO> srmBaseExchangeRateDAO_HI_RO;

    @Autowired
    private ViewObject<SrmBaseExchangeRateEntity_HI> srmBaseExchangeRateDAO_HI;

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;

    public SrmBaseExchangeRateServer() {
        super();
    }

    /**
     * 查询汇率列表
     *
     * @param params
     * @return
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmBaseExchangeRateEntity_HI_RO> findBaseExchangeRate(JSONObject params, @DefaultValue("1") Integer pageIndex, @DefaultValue("10") Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmBaseExchangeRateEntity_HI_RO.QUERY_EXCHANGE_RATE_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询过滤条件
            SaafToolUtils.parperParam(params, "er.exchange_rate_id", "exchangeRateId", queryString, map, "=");
            SaafToolUtils.parperParam(params, "er.rate_type", "rateType", queryString, map, "=");
            SaafToolUtils.parperParam(params, "er.original_currency", "originalCurrency", queryString, map, "=");
            SaafToolUtils.parperParam(params, "er.target_currency", "targetCurrency", queryString, map, "=");
            SaafToolUtils.parperParam(params, "su.user_full_name", "creater", queryString, map, "like");
//            SaafToolUtils.parperParam(params, "DATE_FORMAT(er.effective_start_date,'%Y-%m-%d') ", "effectiveStartDateTo", queryString, map, "<=");
//            SaafToolUtils.parperParam(params, "DATE_FORMAT(er.effective_start_date,'%Y-%m-%d') ", "effectiveStartDateFrom", queryString, map, ">=");
//            SaafToolUtils.parperParam(params, "DATE_FORMAT(er.creation_date,'%Y-%m-%d') ", "creationDateTo", queryString, map, "<=");
//            SaafToolUtils.parperParam(params, "DATE_FORMAT(er.creation_date,'%Y-%m-%d') ", "creationDateFrom", queryString, map, ">=");
            String effectiveStartDateFrom = params.getString("effectiveStartDateFrom");
            if (effectiveStartDateFrom != null && !"".equals(effectiveStartDateFrom.trim())) {
                queryString.append(" AND    trunc(er.effective_start_date) >= to_date(:effectiveStartDateFrom, 'yyyy-mm-dd')\n");
                map.put("effectiveStartDateFrom", effectiveStartDateFrom);
            }
            String effectiveStartDateTo = params.getString("effectiveStartDateTo");
            if (effectiveStartDateTo != null && !"".equals(effectiveStartDateTo.trim())) {
                queryString.append(" AND    trunc(er.effective_start_date) <= to_date(:effectiveStartDateTo, 'yyyy-mm-dd')\n");
                map.put("effectiveStartDateTo", effectiveStartDateTo);
            }

            String creationDateFrom = params.getString("creationDateFrom");
            if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
                queryString.append(" AND    trunc(er.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
                map.put("creationDateFrom", creationDateFrom);
            }
            String creationDateTo = params.getString("creationDateTo");
            if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
                queryString.append(" AND    trunc(er.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
                map.put("creationDateTo", creationDateTo);
            }
            String countSql = "select count(1) from (" + queryString + ")";
            //queryString.append(" GROUP BY er.exchange_rate_id,er.rate_type,er.original_currency,er.target_currency\n" + "ORDER BY er.effective_start_date DESC ");
            queryString.append(" ORDER BY er.effective_start_date DESC ");
            Pagination<SrmBaseExchangeRateEntity_HI_RO> list = srmBaseExchangeRateDAO_HI_RO
                    .findPagination(queryString,countSql, map, pageIndex, pageRows);
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询默认汇率类型
     *
     * @return
     * @throws Exception
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public SaafLookupValuesEntity_HI findDefaulRateType() throws Exception {
        try {
            SaafLookupValuesEntity_HI lookupValuesEntity = null;
            Map queryMap = new HashMap();
            queryMap.put("lookupType", "EXCHANGE_RATE_TYPE");
            queryMap.put("tag", "Y");
            List<SaafLookupValuesEntity_HI> lookupValuesEntityList = saafLookupValuesDAO_HI.findByProperty(queryMap);
            if (lookupValuesEntityList.size() > 0) {
                lookupValuesEntity = lookupValuesEntityList.get(0);
            }
            return lookupValuesEntity;
        } catch (Exception e) {
            //LOGGER.error("未知错误:{}", e)();
            throw new Exception(e);
        }
    }

    /**
     * Description：保存汇率
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * exchangeRateId  汇率表ID  NUMBER  Y
     * exchangeRate  汇率  NUMBER  N
     * rateType  汇率类型  VARCHAR2  N
     * originalCurrency  原币种  VARCHAR2  N
     * targetCurrency    VARCHAR2  N
     * rateDate    DATE  N
     * effectiveStartDate    DATE  N
     * effectiveEndDate  有效结束日期  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * exchangeRateId  汇率ID  NUMBER  Y
     * exchangeRate  汇率  NUMBER  N
     * rateType  汇率类型  VARCHAR2  N
     * originalCurrency  原币种  VARCHAR2  N
     * targetCurrency  目标币种  VARCHAR2  N
     * effectiveStartDate  有效开始日期  DATE  N
     * effectiveEndDate  有效结束日期  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveBaseExchangeRate(JSONObject params) throws Exception {
        LOGGER.info("保存供应商批准列表，参数：" + params.toString());
        List<SrmBaseExchangeRateEntity_HI> exchangeRateList = new ArrayList<>();
        SrmBaseExchangeRateEntity_HI exchangeRateRow = null;
        try {
            Integer operatorUserId = params.getInteger("varUserId");
            String rateType = params.getString("rateType");
            BigDecimal exchangeRate = params.getBigDecimal("exchangeRate");
            String originalCurrency = params.getString("originalCurrency");
            String targetCurrency = params.getString("targetCurrency");
            if (params.getInteger("exchangeRateId") != null) {
                exchangeRateRow = srmBaseExchangeRateDAO_HI.getById(params.getInteger("exchangeRateId"));
                Map checkMap = new HashMap();
                checkMap.put("rateType", rateType);
                checkMap.put("originalCurrency", originalCurrency);
                checkMap.put("targetCurrency", targetCurrency);
                checkMap.put("effectiveStartDate", exchangeRateRow.getEffectiveStartDate());
                List<SrmBaseExchangeRateEntity_HI> rateList = srmBaseExchangeRateDAO_HI.findByProperty(checkMap);
                if (rateList.size() > 0) {
                    for (SrmBaseExchangeRateEntity_HI rate : rateList) {
                        if (rate.getExchangeRateId() != params.getInteger("exchangeRateId")) {
                            return SToolUtils.convertResultJSONObj("W", "已存在相同数据，请调整日期范围，再确认", 1, exchangeRateRow);
                        }
                    }
                }
                exchangeRateRow.setRateType(rateType);
                exchangeRateRow.setExchangeRate(exchangeRate);
                exchangeRateRow.setOriginalCurrency(originalCurrency);
                exchangeRateRow.setTargetCurrency(targetCurrency);
                exchangeRateRow.setOperatorUserId(operatorUserId);
                exchangeRateList.add(exchangeRateRow);
            } else {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                Calendar canlandar1 = Calendar.getInstance();//开始时间
                Calendar canlandar2 = Calendar.getInstance();//结束时间
                canlandar1.setTime(params.getDate("createDateStart"));
                canlandar2.setTime(params.getDate("createDateEnd"));
                List<Date> returnList = new ArrayList<Date>();
                while (canlandar1.compareTo(canlandar2) < 1) {
                    returnList.add(canlandar1.getTime());
                    canlandar1.add(canlandar1.DATE, 1);//每次循环增加一天
                }
                for (int i = 0; i < returnList.size(); i++) {
                    exchangeRateRow = new SrmBaseExchangeRateEntity_HI();
                    Map checkMap = new HashMap();
                    checkMap.put("rateType", rateType);
                    checkMap.put("originalCurrency", originalCurrency);
                    checkMap.put("targetCurrency", targetCurrency);
                    checkMap.put("effectiveStartDate", new SimpleDateFormat("yyyy-MM-dd").parse(f.format(returnList.get(i).getTime())));
                    List<SrmBaseExchangeRateEntity_HI> rateList = srmBaseExchangeRateDAO_HI.findByProperty(checkMap);
                    if (rateList.size() > 0) {
                        return SToolUtils.convertResultJSONObj("W", "已存在相同数据，请调整日期范围，再确认", 1, exchangeRateRow);
                    } else {
                        exchangeRateRow.setRateType(rateType);
                        exchangeRateRow.setExchangeRate(exchangeRate);
                        exchangeRateRow.setOriginalCurrency(originalCurrency);
                        exchangeRateRow.setTargetCurrency(targetCurrency);
                        exchangeRateRow.setEffectiveStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(f.format(returnList.get(i).getTime())));
                        exchangeRateRow.setOperatorUserId(operatorUserId);
                        exchangeRateList.add(exchangeRateRow);
                    }
                }
            }
            srmBaseExchangeRateDAO_HI.saveAll(exchangeRateList);
            return SToolUtils.convertResultJSONObj("S", "保存成功", 1, exchangeRateRow);
        } catch (Exception e) {
            //LOGGER.error("未知错误:{}", e)();
            throw new Exception(e);
        }
    }

    /**
     * 删除资质审查
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteExchangeRate(JSONObject params) throws Exception {
        LOGGER.info("删除汇率，参数：" + params.toString());
        try {
            SrmBaseExchangeRateEntity_HI exchangeRateRow = null;
            Integer exchangeRateId = params.getInteger("exchangeRateId");
            if (exchangeRateId != null && exchangeRateId > 0) {
                exchangeRateRow = srmBaseExchangeRateDAO_HI.getById(exchangeRateId);
                //删除汇率
                if (exchangeRateRow != null) {
                    srmBaseExchangeRateDAO_HI.delete(exchangeRateRow);
                }
            }
            return SToolUtils.convertResultJSONObj("S", "删除汇率成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：批量导入
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * exchangeRateId  汇率表ID  NUMBER  Y
     * exchangeRate  汇率  NUMBER  N
     * rateType  汇率类型  VARCHAR2  N
     * originalCurrency  原币种  VARCHAR2  N
     * targetCurrency    VARCHAR2  N
     * rateDate    DATE  N
     * effectiveStartDate    DATE  N
     * effectiveEndDate  有效结束日期  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * exchangeRateId  汇率ID  NUMBER  Y
     * exchangeRate  汇率  NUMBER  N
     * rateType  汇率类型  VARCHAR2  N
     * originalCurrency  原币种  VARCHAR2  N
     * targetCurrency  目标币种  VARCHAR2  N
     * effectiveStartDate  有效开始日期  DATE  N
     * effectiveEndDate  有效结束日期  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveImportExchangeRate(JSONObject jsonParams, Integer userId) {
        JSONObject info = jsonParams.getJSONObject("info");
        JSONArray jsonArray = jsonParams.getJSONArray("data");//校验保存的数据
        JSONArray error = cehckArray(jsonArray, info);
        Integer operatorUserId = jsonParams.getInteger("varUserId");
        List<SrmBaseExchangeRateEntity_HI> srmBaseExchangeRateList = new ArrayList<>();
        if (error.size() > 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "导入失败", error.size(), error);
        }

        int count = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String rateType = null;
            String originalCurrency = null;
            String targetCurrency = null;
            SrmBaseExchangeRateEntity_HI srmBaseExchangeRate = new SrmBaseExchangeRateEntity_HI();

            Map queryRateType = new HashMap();
            queryRateType.put("lookupType", "EXCHANGE_RATE_TYPE");
            queryRateType.put("meaning", object.getString("rateTypeMean"));
            List<SaafLookupValuesEntity_HI> lookupValuesRateType = saafLookupValuesDAO_HI.findByProperty(queryRateType);
            if (lookupValuesRateType.size() > 0) {
                rateType = lookupValuesRateType.get(0).getLookupCode();
            }
            Map queryOriginal = new HashMap();
            queryOriginal.put("lookupType", "BANK_CURRENCY");
            queryOriginal.put("meaning", object.getString("originalCurrencyMean"));
            List<SaafLookupValuesEntity_HI> lookupValuesOriginal = saafLookupValuesDAO_HI.findByProperty(queryOriginal);
            if (lookupValuesOriginal.size() > 0) {
                originalCurrency = lookupValuesOriginal.get(0).getLookupCode();
            }
            Map queryTarget = new HashMap();
            queryTarget.put("lookupType", "BANK_CURRENCY");
            queryTarget.put("meaning", object.getString("targetCurrencyMean"));
            List<SaafLookupValuesEntity_HI> lookupValuesTarget = saafLookupValuesDAO_HI.findByProperty(queryTarget);
            if (lookupValuesTarget.size() > 0) {
                targetCurrency = lookupValuesTarget.get(0).getLookupCode();
            }

            srmBaseExchangeRate.setRateType(rateType);
            srmBaseExchangeRate.setOriginalCurrency(originalCurrency);
            srmBaseExchangeRate.setTargetCurrency(targetCurrency);
            srmBaseExchangeRate.setExchangeRate(object.getBigDecimal("exchangeRate"));
            srmBaseExchangeRate.setEffectiveStartDate(object.getDate("effectiveStartDate"));
            srmBaseExchangeRate.setOperatorUserId(operatorUserId);
            srmBaseExchangeRateList.add(srmBaseExchangeRate);
            ++count;
        }
        srmBaseExchangeRateDAO_HI.saveOrUpdateAll(srmBaseExchangeRateList);
        JSONObject resultObj = new JSONObject();
        resultObj.put("msg", "导入成功行数为:" + count + "行!");
        resultObj.put("status", "S");
        return resultObj;
    }

    private JSONArray cehckArray(JSONArray jsonArray, JSONObject info) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        if (jsonArray.isEmpty()) return null;
        JSONArray error = new JSONArray();
        JSONObject e = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject firstObject = jsonArray.getJSONObject(i);
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject secondObject = jsonArray.getJSONObject(j);
                if (firstObject.getString("rateTypeMean").equals(secondObject.getString("rateTypeMean")) &&
                        firstObject.getString("originalCurrencyMean").equals(secondObject.getString("originalCurrencyMean")) &&
                        firstObject.getString("targetCurrencyMean").equals(secondObject.getString("targetCurrencyMean")) &&
                        firstObject.getDate("effectiveStartDate").compareTo(secondObject.getDate("effectiveStartDate")) == 0 &&
                        !firstObject.getString("ROW_NUM").equals(secondObject.getString("ROW_NUM"))) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "Excel中有重复数据");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }
        }

        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            Map queryRateType = new HashMap();
            queryRateType.put("lookupType", "EXCHANGE_RATE_TYPE");
            queryRateType.put("meaning", object.getString("rateTypeMean"));
            List<SaafLookupValuesEntity_HI> lookupValuesRateType = saafLookupValuesDAO_HI.findByProperty(queryRateType);
            if (lookupValuesRateType == null || lookupValuesRateType.size() < 1) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "汇率类型不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            Map queryOriginal = new HashMap();
            queryOriginal.put("lookupType", "BANK_CURRENCY");
            queryOriginal.put("meaning", object.getString("originalCurrencyMean"));
            List<SaafLookupValuesEntity_HI> lookupValuesOriginal = saafLookupValuesDAO_HI.findByProperty(queryOriginal);
            if (lookupValuesOriginal == null || lookupValuesOriginal.size() < 1) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "原币种不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            Map queryTarget = new HashMap();
            queryTarget.put("lookupType", "BANK_CURRENCY");
            queryTarget.put("meaning", object.getString("targetCurrencyMean"));
            List<SaafLookupValuesEntity_HI> lookupValuesTarget = saafLookupValuesDAO_HI.findByProperty(queryTarget);
            if (lookupValuesTarget == null || lookupValuesTarget.size() < 1) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "目标币种不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            String aaaa = object.getString("effectiveStartDate");
            String[] bb = aaaa.split("-");
            if (bb.length < 2) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "日期格式有误，举例：2019-04-29");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            if (object.getString("originalCurrencyMean").equals(object.getString("targetCurrencyMean"))) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "原币种与目标币种不允许重复");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            Map checkMap = new HashMap();
            checkMap.put("rateType", lookupValuesRateType.get(0).getLookupCode());
            checkMap.put("originalCurrency", lookupValuesOriginal.get(0).getLookupCode());
            checkMap.put("targetCurrency", lookupValuesTarget.get(0).getLookupCode());
            checkMap.put("effectiveStartDate", object.getDate("effectiveStartDate"));
            List<SrmBaseExchangeRateEntity_HI> rateList = srmBaseExchangeRateDAO_HI.findByProperty(checkMap);
            if (rateList.size() > 0) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "已存在相同数据");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
        }
        return error;
    }

}
