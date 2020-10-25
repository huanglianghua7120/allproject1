/*================================================================================================================================================+
|   Copyright (c) 2018   SIE.                                                                                                                     |
+=================================================================================================================================================+
|  HISTORY                                                                                                                                        |
|                 Date                    Ver.                 Author                       Content                                               |
|               2018-03-17                1.0                 JinShaoJun                    Creation                                              |
+================================================================================================================================================*/
package saaf.common.fmw.pon.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionItemsEntity_HI;
import saaf.common.fmw.pon.model.inter.ISrmPonDestinations;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.entities.SrmPonDestinationsEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;
import saaf.common.fmw.utils.SrmUtils;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonDestinationsServer.java
 * Description：寻源--目的地信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonDestinationsServer")
public class SrmPonDestinationsServer implements ISrmPonDestinations {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonDestinationsServer.class);

    @Autowired
    private ViewObject<SrmPonDestinationsEntity_HI> srmPonDestinationsDAO_HI;

    @Autowired
    private ViewObject<SrmPonAuctionItemsEntity_HI> srmPonAuctionItemsDAO_HI;

    /**
     * Description：查询目的地列表
     * @param parameters 参数
     * @param pageRows 起始页
     * @param pageIndex 行数
     * @return Pagination<SrmPonDestinationsEntity_HI>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPonDestinationsEntity_HI> findDestinationInfo(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer("FROM SrmPonDestinationsEntity_HI l where 1 = 1 ");
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询过滤条件
            SaafToolUtils.parperParam(parameters, "l.province", "province", queryString, map, "like");
            SaafToolUtils.parperParam(parameters, "l.zoneCode", "zoneCode", queryString, map, "like");
            SaafToolUtils.parperParam(parameters, "l.destinationAddress", "destinationAddress", queryString, map, "like");
            String countSql = "select count(1) from (" + queryString + ")";
            // 排序
            queryString.append(" ORDER BY l.lastUpdateDate DESC ");
            Pagination<SrmPonDestinationsEntity_HI> list = srmPonDestinationsDAO_HI.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：保存目的地信息
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveDestinationInfo(JSONObject params) throws Exception {
        JSONObject json = new JSONObject();
        JSONArray array = null;
        JSONObject jsonObj = null;
        List<SrmPonDestinationsEntity_HI> list = new ArrayList<>();
        SrmPonDestinationsEntity_HI info;

        if (SrmUtils.isNvl(params) || SrmUtils.isNvl(params.getJSONArray("lineData")) || params.getJSONArray("lineData").size() == 0) {
            return SToolUtils.convertResultJSONObj("E", "没有需要保存的数据", 1, json);
        } else {
            array = params.getJSONArray("lineData");
        }

        Integer userId = params.getInteger("varUserId");
        String province;
        String zoneCode;
        BigDecimal timeForArrival;
        String destinationAddress;
        String startDateStr;
        Date startDate;
        String endDateStr;
        Date endDate;
        List<SrmPonDestinationsEntity_HI> queryList;

        for (int i = 0; i < array.size(); i++) {
            jsonObj = array.getJSONObject(i);
            province = jsonObj.getString("province");
            zoneCode = jsonObj.getString("zoneCode");
            timeForArrival = jsonObj.getBigDecimal("timeForArrival");
            destinationAddress = jsonObj.getString("destinationAddress");
            if (jsonObj.getInteger("destinationId") == null) {
                //验证是否已存在同样的记录
                Map<String, Object> map = new HashMap<String, Object>();
                map.clear();
                map.put("province", province);
                map.put("zone_code", zoneCode);
                map.put("time_for_arrival", timeForArrival);
                map.put("destination_address", destinationAddress);
                queryList = srmPonDestinationsDAO_HI.findByProperty(map);
                if (queryList != null && queryList.size() > 0) {
                    throw new ServerException("已存在同样的记录！省份【" + province + "】,目的地区号【" + zoneCode + "】,要求到货时间【" + timeForArrival + "】,目的地【" + destinationAddress + "】");
                }

                info = new SrmPonDestinationsEntity_HI();
                info.setStatus("DRAFT");
            } else {
                info = srmPonDestinationsDAO_HI.getById(jsonObj.getInteger("destinationId"));
                if (info == null) {  //信息不存在
                    throw new ServerException("信息不存在！");
                }
            }
            if (!"DRAFT".equals(info.getStatus())) {
                throw new ServerException("该单据状态不允许修改！");
            }

            startDateStr = jsonObj.getString("startDateActive");
            endDateStr = jsonObj.getString("endDateActive");

            //获取开始日期
            if (!SrmUtils.isNvl(startDateStr)) {
                startDate = SToolUtils.string2DateTime(startDateStr, "yyyy-MM-dd");
            } else {
                startDate = null;
            }
            //获取结束日期
            if (!SrmUtils.isNvl(endDateStr)) {
                endDate = SToolUtils.string2DateTime(endDateStr, "yyyy-MM-dd");
            } else {
                endDate = null;
            }
            info.setProvince(province);
            info.setZoneCode(zoneCode);
            info.setTimeForArrival(timeForArrival);
            info.setDestinationAddress(destinationAddress);
            info.setStartDateActive(startDate);
            info.setEndDateActive(endDate);
            info.setDescription(jsonObj.getString("description"));
            info.setAttribute1(jsonObj.getString("attribute1"));
            info.setAttribute2(jsonObj.getString("attribute2"));
            info.setAttribute3(jsonObj.getString("attribute3"));
            info.setAttribute4(jsonObj.getString("attribute4"));
            info.setAttribute5(jsonObj.getString("attribute5"));
            info.setAttribute6(jsonObj.getString("attribute6"));
            info.setAttribute7(jsonObj.getString("attribute7"));
            info.setAttribute8(jsonObj.getString("attribute8"));
            info.setAttribute9(jsonObj.getString("attribute9"));
            info.setAttribute10(jsonObj.getString("attribute10"));
            info.setAttribute12(jsonObj.getString("attribute11"));
            info.setAttribute12(jsonObj.getString("attribute12"));
            info.setAttribute13(jsonObj.getString("attribute13"));
            info.setAttribute14(jsonObj.getString("attribute14"));
            info.setAttribute15(jsonObj.getString("attribute15"));
            info.setVersionNum(jsonObj.getInteger("versionNum"));
            info.setOperatorUserId(userId);
            list.add(info);
        }
        srmPonDestinationsDAO_HI.save(list);
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, json);
    }

    /**
     * Description：保存目的地信息
     * @param jsonArray 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    private JSONArray resutError(JSONArray jsonArray) {
        JSONArray errorArray = new JSONArray();
        JSONObject errorInfo = null;
        JSONObject jsonObj = null;
        String province = null;
        String zoneCode = null;
        BigDecimal timeForArrival = null;
        String destinationAddress = null;
        List<SrmPonDestinationsEntity_HI> queryList = null;

        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObj = jsonArray.getJSONObject(i);
            province = jsonObj.getString("province");
            zoneCode = jsonObj.getString("zoneCode");
            try {
                timeForArrival = jsonObj.getBigDecimal("timeForArrival");
            } catch (Exception e) {
                //e.printStackTrace();
                errorInfo = new JSONObject();
                errorInfo.put("ERR_MESSAGE", "要求到货时间只能输入一个正数");
                errorInfo.put("ROW_NUM", i + 1);
                errorArray.add(errorInfo);
                continue;
            }
            destinationAddress = jsonObj.getString("destinationAddress");

            if (SrmUtils.isNvl(province) || SrmUtils.isNvl(zoneCode) || SrmUtils.isNvl(timeForArrival) || SrmUtils.isNvl(destinationAddress)) {
                errorInfo = new JSONObject();
                errorInfo.put("ERR_MESSAGE", "省份、目的地区号、要求到货时间和目的地均不能为空");
                errorInfo.put("ROW_NUM", i + 1);
                errorArray.add(errorInfo);
                continue;
            }
            //验证是否已存在同样的记录
            Map<String, Object> map = new HashMap<String, Object>();
            map.clear();
            map.put("province", province);
            map.put("zone_code", zoneCode);
            map.put("time_for_arrival", timeForArrival);
            map.put("destination_address", destinationAddress);
            queryList = srmPonDestinationsDAO_HI.findByProperty(map);
            if (queryList != null && queryList.size() > 0) {
                errorInfo = new JSONObject();
                errorInfo.put("ERR_MESSAGE", "已存在同样的记录！省份【" + province + "】,目的地区号【" + zoneCode + "】,要求到货时间【" + timeForArrival + "】,目的地【" + destinationAddress + "】");
                errorInfo.put("ROW_NUM", i + 1);
                errorArray.add(errorInfo);
                continue;
            }
        }

        return errorArray;
    }

    /**
     * Description：EXCEL导入目的地信息
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveDestinationByImport(JSONObject params) throws Exception {
        JSONObject json = new JSONObject();
        JSONArray array = null;
        JSONObject jsonObj = null;
        List<SrmPonDestinationsEntity_HI> list = new ArrayList<>();
        SrmPonDestinationsEntity_HI info = null;
        if (SrmUtils.isNvl(params) || SrmUtils.isNvl(params.getJSONArray("data")) || params.getJSONArray("data").size() == 0) {
            return SToolUtils.convertResultJSONObj("E", "没有需要导入的数据", 1, json);
        } else {
            array = params.getJSONArray("data");
        }

        JSONArray errorArray = this.resutError(array);
        if (errorArray.size() > 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "导入失败", errorArray.size(), errorArray);
        }

        Integer userId = params.getInteger("varUserId");
        String province = null;
        String zoneCode = null;
        BigDecimal timeForArrival;
        String destinationAddress = null;
        String startDateStr = null;
        Date startDate = null;
        String endDateStr = null;
        Date endDate = null;

        for (int i = 0; i < array.size(); i++) {
            jsonObj = array.getJSONObject(i);
            province = jsonObj.getString("province");
            zoneCode = jsonObj.getString("zoneCode");
            timeForArrival = jsonObj.getBigDecimal("timeForArrival");
            destinationAddress = jsonObj.getString("destinationAddress");
            info = new SrmPonDestinationsEntity_HI();
            info.setStatus("DRAFT");
            startDateStr = jsonObj.getString("startDateActive");
            endDateStr = jsonObj.getString("endDateActive");

            //获取开始日期
            if (!SrmUtils.isNvl(startDateStr)) {
                startDate = SToolUtils.string2DateTime(startDateStr, "yyyy-MM-dd");
            } else {
                startDate = null;
            }
            //获取结束日期
            if (!SrmUtils.isNvl(endDateStr)) {
                endDate = SToolUtils.string2DateTime(endDateStr, "yyyy-MM-dd");
            } else {
                endDate = null;
            }
            info.setProvince(province);
            info.setZoneCode(zoneCode);
            info.setTimeForArrival(timeForArrival);
            info.setDestinationAddress(destinationAddress);
            info.setStartDateActive(startDate);
            info.setEndDateActive(endDate);
            info.setDescription(jsonObj.getString("description"));
            info.setAttribute1(jsonObj.getString("attribute1"));
            info.setAttribute2(jsonObj.getString("attribute2"));
            info.setAttribute3(jsonObj.getString("attribute3"));
            info.setAttribute4(jsonObj.getString("attribute4"));
            info.setAttribute5(jsonObj.getString("attribute5"));
            info.setAttribute6(jsonObj.getString("attribute6"));
            info.setAttribute7(jsonObj.getString("attribute7"));
            info.setAttribute8(jsonObj.getString("attribute8"));
            info.setAttribute9(jsonObj.getString("attribute9"));
            info.setAttribute10(jsonObj.getString("attribute10"));
            info.setAttribute12(jsonObj.getString("attribute11"));
            info.setAttribute12(jsonObj.getString("attribute12"));
            info.setAttribute13(jsonObj.getString("attribute13"));
            info.setAttribute14(jsonObj.getString("attribute14"));
            info.setAttribute15(jsonObj.getString("attribute15"));
            info.setVersionNum(jsonObj.getInteger("versionNum"));
            info.setOperatorUserId(userId);
            list.add(info);
        }
        srmPonDestinationsDAO_HI.save(list);
        JSONObject resultObj = new JSONObject();
        resultObj.put("msg", "导入成功行数为:" + list.size() + "行!");
        resultObj.put("status", "S");
        return resultObj;
    }

    /**
     * Description：删除目的地信息
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteDestinationInfo(JSONObject params) throws Exception {
        JSONObject json = new JSONObject();
        JSONArray array = null;
        JSONObject jsonObj = null;
        List<SrmPonDestinationsEntity_HI> list = new ArrayList<>();
        SrmPonDestinationsEntity_HI info = null;

        if (SrmUtils.isNvl(params) || SrmUtils.isNvl(params.getJSONArray("lineData")) || params.getJSONArray("lineData").size() == 0) {
            return SToolUtils.convertResultJSONObj("E", "没有需要删除的数据", 1, json);
        } else {
            array = params.getJSONArray("lineData");
        }

        List<SrmPonAuctionItemsEntity_HI> queryList = null;
        String province = null;
        String zoneCode = null;
        BigDecimal timeForArrival;
        String destinationAddress = null;

        for (int i = 0; i < array.size(); i++) {
            jsonObj = array.getJSONObject(i);
            if (jsonObj.getInteger("destinationId") == null) {
                continue;
            } else {
                info = srmPonDestinationsDAO_HI.getById(jsonObj.getInteger("destinationId"));
                if (info == null) {  //信息不存在
                    throw new ServerException("信息不存在！");
                }
            }
            if (!"DRAFT".equals(info.getStatus())) {
                throw new ServerException("该单据状态不允许删除！");
            }

            province = info.getProvince();
            zoneCode = info.getZoneCode();
            timeForArrival = info.getTimeForArrival();
            destinationAddress = info.getDestinationAddress();

            //验证是否是被询价使用的记录
            Map<String, Object> map = new HashMap<String, Object>();
            map.clear();
            map.put("destination_id", jsonObj.getInteger("destinationId"));
            queryList = srmPonAuctionItemsDAO_HI.findByProperty(map);
            if (queryList != null && queryList.size() > 0) {
                throw new ServerException("已被询价使用，不允许删除！省份【" + province + "】,目的地区号【" + zoneCode + "】,要求到货时间【" + timeForArrival + "】,目的地【" + destinationAddress + "】");
            }
            list.add(info);
        }
        srmPonDestinationsDAO_HI.delete(list);
        return SToolUtils.convertResultJSONObj("S", "删除成功", 1, json);
    }
}
