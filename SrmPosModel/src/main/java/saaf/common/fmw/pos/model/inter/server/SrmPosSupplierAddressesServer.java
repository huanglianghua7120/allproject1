package saaf.common.fmw.pos.model.inter.server;

import com.alibaba.fastjson.JSON;
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

import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierAddressesEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierSitesEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierAddressesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierAddresses;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierSites;

import java.util.*;

@Component("srmPosSupplierAddressesServer")
public class SrmPosSupplierAddressesServer implements ISrmPosSupplierAddresses {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierAddressesServer.class);

    @Autowired
    private ViewObject<SrmPosSupplierAddressesEntity_HI> srmPosSupplierAddressesDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierSitesEntity_HI> srmPosSupplierSitesDAO_HI; //供应商地点

    @Autowired
    private BaseViewObject<SrmPosSupplierAddressesEntity_HI_RO> srmPosSupplierAddressesDAO_HI_RO;

    @Autowired
    private ISrmPosSupplierSites iSrmPosSupplierSites; //供应商地点

    public SrmPosSupplierAddressesServer() {
        super();
    }

    /**
     * 删除供应商地址及其子表地点
     *
     * @param jsonParams
     * @return
     */
    @Override
    public JSONObject deleteSupplierAddress(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());
        Integer supplierAddressId = jsonParams.getInteger("supplierAddressId");
        if (!(supplierAddressId == null || "".equals(supplierAddressId))) {
            SrmPosSupplierAddressesEntity_HI row = srmPosSupplierAddressesDAO_HI.getById(jsonParams.getInteger("supplierAddressId"));
            if (null != row) {
                List<SrmPosSupplierSitesEntity_HI> list = srmPosSupplierSitesDAO_HI.findByProperty("supplierAddressId", row.getSupplierAddressId());
                if (list!=null&&list.size() > 0) {
                    srmPosSupplierSitesDAO_HI.deleteAll(list);
                }
                srmPosSupplierAddressesDAO_HI.delete(row.getSupplierAddressId());
                return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除失败，无此记录！参数为：" + supplierAddressId, 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "删除失败，参数" + supplierAddressId, 0, null);
        }
    }

    /**
     * 保存供应商地址谱的头表（档案自助维护/内部创建供应商）
     *
     * @param supplierAddressesList
     * @param userId
     * @param supplierId
     */
    @Override
    public boolean saveSupplierAddressHead(List<SrmPosSupplierAddressesEntity_HI> supplierAddressesList, Integer userId,
                                           Integer supplierId) {
        boolean flag = false;
        List<SrmPosSupplierAddressesEntity_HI> newSupplierAddressesList = new ArrayList<>();
        if (null != supplierAddressesList && supplierAddressesList.size() > 0) {
            for (SrmPosSupplierAddressesEntity_HI k : supplierAddressesList) {
                SrmPosSupplierAddressesEntity_HI findRow = srmPosSupplierAddressesDAO_HI
                        .getById(k.getSupplierAddressId());
                if (null != findRow) {
                    findRow.setAddressName(k.getAddressName());
                    findRow.setCountry(k.getCountry());
                    findRow.setProvince(k.getProvince());
                    findRow.setCity(k.getCity());
                    findRow.setCounty(k.getCounty());
                    findRow.setCounty(k.getCounty());
                    findRow.setDetailAddress(k.getDetailAddress());
                    findRow.setSupplierId(supplierId);
                    findRow.setOperatorUserId(userId);
                    newSupplierAddressesList.add(findRow);
                } else {
                    k.setSupplierId(supplierId);
                    k.setOperatorUserId(userId);
                    newSupplierAddressesList.add(k);
                }
            }
            srmPosSupplierAddressesDAO_HI.saveOrUpdateAll(newSupplierAddressesList);
            flag = true;
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 保存供应商地址谱的头表——供应商接口（数据输入）
     *
     * @param supplierAddressesInfoAllList
     * @param userId
     * @param supplierId
     */
    @Override
    public void saveSupplierAddressAllExternal(List<Map> supplierAddressesInfoAllList, Integer userId, Integer supplierId) {
        if (null != supplierAddressesInfoAllList && supplierAddressesInfoAllList.size() > 0) {
            for (Map k : supplierAddressesInfoAllList) {
                SrmPosSupplierAddressesEntity_HI supplierAddressesInfo = null;
                if (!(k.get("supplierAddressesInfo") == null || "".equals(k.get("supplierAddressesInfo")))) {
                    JSONObject jsonAddress = (JSONObject) JSONObject.toJSON(k.get("supplierAddressesInfo"));
                    supplierAddressesInfo = JSON.parseObject(jsonAddress.toJSONString(), SrmPosSupplierAddressesEntity_HI.class); //重新封装为json格式，再进行反射机制转换为对应的实体类
                }
                List<SrmPosSupplierSitesEntity_HI> supplierSitesList = null;
                if (!(k.get("supplierSitesInfoList") == null || "".equals(k.get("supplierSitesInfoList")))) {
                    JSONArray jsonArraySite = (JSONArray) JSONArray.toJSON(k.get("supplierSitesInfoList"));
                    supplierSitesList = JSON.parseArray(jsonArraySite.toJSONString(), SrmPosSupplierSitesEntity_HI.class); //重新封装为json格式，再进行反射机制转换为对应的实体类
                }
                Integer supplierAddressId = null;
                SrmPosSupplierAddressesEntity_HI findRow = null;
                if (null != supplierAddressesInfo) {
                    JSONObject jsonParams = new JSONObject();
                    jsonParams.put("sourceCode", supplierAddressesInfo.getSourceCode());
                    jsonParams.put("sourceId", supplierAddressesInfo.getSourceId());
                    jsonParams.put("supplierId", supplierId);
                    Pagination<SrmPosSupplierAddressesEntity_HI_RO> listPagination = findSupplierAddresses(jsonParams, 1, 1000);
                    List<SrmPosSupplierAddressesEntity_HI_RO> listRO = listPagination.getData();
                    if (null != listRO && listRO.size() > 0) {
                        SrmPosSupplierAddressesEntity_HI_RO findRO = listRO.get(0);
                        findRow = srmPosSupplierAddressesDAO_HI.getById(findRO.getSupplierAddressId());
                    }
                }
                if (null != findRow) {
                    findRow.setAddressName(supplierAddressesInfo.getAddressName());
                    findRow.setCountry(supplierAddressesInfo.getCountry());
                    findRow.setProvince(supplierAddressesInfo.getProvince());
                    findRow.setCity(supplierAddressesInfo.getCity());
                    findRow.setCounty(supplierAddressesInfo.getCounty());
                    findRow.setCounty(supplierAddressesInfo.getCounty());
                    findRow.setDetailAddress(supplierAddressesInfo.getDetailAddress());
                    findRow.setSupplierId(supplierId);
                    findRow.setOperatorUserId(userId);
                    srmPosSupplierAddressesDAO_HI.saveEntity(findRow);
                    supplierAddressId = findRow.getSupplierAddressId();
                } else {
                    supplierAddressesInfo.setSupplierId(supplierId);
                    supplierAddressesInfo.setOperatorUserId(userId);
                    srmPosSupplierAddressesDAO_HI.saveEntity(supplierAddressesInfo);
                    supplierAddressId = supplierAddressesInfo.getSupplierAddressId();
                }
                iSrmPosSupplierSites.saveSupplierSitesExternal(supplierSitesList, supplierAddressId, userId, supplierId);
            }
        }
    }

    /**
     * 校验供应商的地址重复与必填项——供应商接口（数据输入）
     *
     * @param supplierAddressesInfoAllList
     * @return
     */
    @Override
    public String judgeSpaceSupplierAddress(List<Map> supplierAddressesInfoAllList) {
        String result = "";
        if (null == supplierAddressesInfoAllList || supplierAddressesInfoAllList.size() <= 0) {
            return result;
        }
        Integer index = 0;
        HashSet<Object> vaildAddressName = new HashSet<>();
        for (Map k : supplierAddressesInfoAllList) {
            SrmPosSupplierAddressesEntity_HI supplierAddressesInfo = null;
            if (!(k.get("supplierAddressesInfo") == null || "".equals(k.get("supplierAddressesInfo")))) {
                index++;
                JSONObject jsonAddress = (JSONObject) JSONObject.toJSON(k.get("supplierAddressesInfo"));
                supplierAddressesInfo = JSON.parseObject(jsonAddress.toJSONString(), SrmPosSupplierAddressesEntity_HI.class); //重新封装为json格式，再进行反射机制转换为对应的实体类
                if (null == supplierAddressesInfo.getSourceId() || "".equals(supplierAddressesInfo.getSourceId())) {
                    result += "请填写地址" + "第" + index + "行supplierAddressesInfo的数据来源Id——sourceId";
                    return result;
                }
                if (null == supplierAddressesInfo.getSourceCode() || "".equals(supplierAddressesInfo.getSourceCode())) {
                    result += "请填写地址" + "第" + index + "行supplierAddressesInfo的数据来源类型Code——sourceCode";
                    return result;
                }
                if (null == supplierAddressesInfo.getAddressName() || "".equals(supplierAddressesInfo.getAddressName())) {
                    result += "请填写地址" + "第" + index + "行supplierAddressesInfo的必填项——地址名称";
                    return result;
                }
                if (null == supplierAddressesInfo.getDetailAddress() || "".equals(supplierAddressesInfo.getDetailAddress())) {
                    result += "请填写地址" + "第" + index + "行supplierAddressesInfo的必填项——详细地址";
                    return result;
                }
                vaildAddressName.add(supplierAddressesInfo.getAddressName());
            }
            List<SrmPosSupplierSitesEntity_HI> supplierSitesList = null;
            if (!(k.get("supplierSitesInfoList") == null || "".equals(k.get("supplierSitesInfoList")))) {
                JSONArray jsonArraySite = (JSONArray) JSONArray.toJSON(k.get("supplierSitesInfoList"));
                supplierSitesList = JSON.parseArray(jsonArraySite.toJSONString(), SrmPosSupplierSitesEntity_HI.class); //重新封装为json格式，再进行反射机制转换为对应的实体类
            }
            String resultSupplierSites = iSrmPosSupplierSites.judgeSpaceSupplierSites(supplierSitesList);
            if (!(null == resultSupplierSites || "".equals(resultSupplierSites))) {
                result += "请填写地址的第" + index + "行：" + resultSupplierSites;
                return result;
            }
        }
        boolean flag = index != vaildAddressName.size() ? true : false;
        if (flag) {
            result += "地址的地址名称有重复，请删除重复的行！";
            return result;
        }
        return result;
    }

    /**
     * 查询供应商的地址
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SrmPosSupplierAddressesEntity_HI_RO> findSupplierAddresses(JSONObject jsonParams,
                                                                                 Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosSupplierAddressesEntity_HI_RO.QUERY_ADDRESS);
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplier_id", sb, queryParamMap, "=");// 如果是供应商查询
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplierId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.supplier_address_id", "supplierAddressId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.source_code", "sourceCode", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.source_id", "sourceId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosSupplierAddressesEntity_HI_RO> result = srmPosSupplierAddressesDAO_HI_RO
                .findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }


    /**
     * 查询供应商的地址
     *
     * @param jsonParams
     * @return
     */
    @Override
    public List<SrmPosSupplierAddressesEntity_HI_RO> findSupplierAddressesById(JSONObject jsonParams) {
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer sbf = new StringBuffer();
        Integer frozenId = jsonParams.getInteger("frozenId");
        sbf.append(SrmPosSupplierAddressesEntity_HI_RO.QUERY_ADDRESS);
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplierId", sbf, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.supplier_address_id", "supplierAddressId", sbf, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.source_code", "sourceCode", sbf, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.source_id", "sourceId", sbf, queryParamMap, "=");
        String queryDate = jsonParams.getString("queryDate");
        if (queryDate != null && !"".equals(queryDate.trim())) {
            sbf.append(" AND trunc(t.creation_date) <= to_date(:queryDate, 'yyyy-mm-dd')\n");
            queryParamMap.put("queryDate", queryDate.substring(0, 10));
        }
        List<SrmPosSupplierAddressesEntity_HI_RO> list = srmPosSupplierAddressesDAO_HI_RO.findList(sbf, queryParamMap);
        if (null != frozenId) {
            StringBuffer strSql = new StringBuffer();
            Map<String, Object> map = new HashMap();
            strSql.append(SrmPosSupplierAddressesEntity_HI_RO.QUERY_ADDRESS_FROZEN_SQL);
            SaafToolUtils.parperParam(jsonParams, "b.frozen_id", "frozenId", strSql, map, "=");
            List<SrmPosSupplierAddressesEntity_HI_RO> entityList = srmPosSupplierAddressesDAO_HI_RO.findList(strSql, map);
            if (entityList != null && entityList.size() > 0) {
                SrmPosSupplierAddressesEntity_HI_RO entity = entityList.get(0);
                Integer frozenSupplierAddressId = entity.getSupplierAddressId();
                for (SrmPosSupplierAddressesEntity_HI_RO addressEntity : list) {
                    Integer supplierAddressId = addressEntity.getSupplierAddressId();
                    if (frozenSupplierAddressId.equals(supplierAddressId)) {
                        addressEntity.setSelectedFlag(entity.getSelectedFlag());
                    }
                }
            }
        }
        return list;
    }

    /**
     * 保存供应商地址
     *
     * @param paramDataList
     * @param userId
     * @param supplierId
     * @return
     */
    public JSONObject saveSupplierAddress(JSONArray paramDataList, int userId, int supplierId) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        List<SrmPosSupplierAddressesEntity_HI> addressList = new ArrayList<SrmPosSupplierAddressesEntity_HI>();
        for (int c = 0; c < paramDataList.size(); c++) {
            paramData = paramDataList.getJSONObject(c);
            SrmPosSupplierAddressesEntity_HI row = null;
            if (null == paramData.getInteger("supplierAddressId")) {
                row = new SrmPosSupplierAddressesEntity_HI();
                row.setSupplierId(supplierId);
                row.setCreationDate(new Date());
                row.setCreatedBy(userId);
            } else {
                row = srmPosSupplierAddressesDAO_HI.getById(paramData.getInteger("supplierAddressId"));
            }
            row.setAddressName(paramData.getString("addressName"));
            row.setCountry(paramData.getString("country"));
            row.setProvince(paramData.getString("province"));
            row.setCity(paramData.getString("city"));
            row.setCounty(paramData.getString("county"));
            row.setDetailAddress(paramData.getString("detailAddress"));
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            addressList.add(row);
        }
        srmPosSupplierAddressesDAO_HI.save(addressList);
        return resultjson;
    }
}
