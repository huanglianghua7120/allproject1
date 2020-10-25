package saaf.common.fmw.po.model.inter.server;

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
import saaf.common.fmw.base.model.entities.readonly.SaafInstitutionEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseItemsEntity_HI_RO;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.SrmPoApprovedListEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoApprovedListEntity_HI_RO;
import saaf.common.fmw.po.model.inter.IApprovedList;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierInfoEntity_HI_RO;

import java.util.*;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;

/**
 * Project Name：SrmPoApprovedListServer
 * Company Name：SIE
 * Program Name：
 * Description：供应商批准列表
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoApprovedListServer")
public class SrmPoApprovedListServer implements IApprovedList {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoApprovedListServer.class);

    @Autowired
    private BaseViewObject<SrmPoApprovedListEntity_HI_RO> srmPoApprovedListDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPoApprovedListEntity_HI> srmPoApprovedListDAO_HI;

    @Autowired
    private BaseViewObject<SrmBaseItemsEntity_HI_RO> srmBaseItemsDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPosSupplierInfoEntity_HI_RO> srmPosSupplierInfoDAO_HI_RO;

    @Autowired
    private BaseViewObject<SaafInstitutionEntity_HI_RO> saafInstitutionDAO_HI_RO;

    /**
     * Description：查询批准供应商列表
     * @param parameters 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoApprovedListEntity_HI_RO> findApprovedList(
            JSONObject parameters, Integer pageIndex, Integer pageRows)
            throws Exception {
        StringBuffer queryString = new StringBuffer(SrmPoApprovedListEntity_HI_RO.QUERY_APPROVED_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询过滤条件
        SaafToolUtils.parperParam(parameters, "bc.category_id", "categoryId", queryString, map, "=");
        SaafToolUtils.parperParam(parameters, "bi.item_code", "varItemCode", queryString, map, "=");
        SaafToolUtils.parperParam(parameters, "al.list_status", "listStatusNumber", queryString, map, "=");
        SaafToolUtils.parperParam(parameters, "al.supplier_id ", "searchSupplierId", queryString, map, "=");
        SaafToolUtils.parperParam(parameters, "al.org_id", "varOrgId", queryString, map, "=");
        SaafToolUtils.parperParam(parameters, "al.organization_id", "varOrganizationId", queryString, map, "=");
        if ("Y".equals(parameters.getString("enables"))) {
            queryString.append(" AND al.disabled_flag = 'N'");
        } else if ("Y".equals(parameters.getString("disabled"))) {
            SaafToolUtils.parperParam(parameters, "al.disabled_flag", "disabled", queryString, map, "=");
        }
        //只能查看当前账号已分配的业务实体相关的数据
        queryString.append(" AND check_org_f(" + parameters.getInteger("varUserId") + ", al.org_id) = 'Y' ");
        //总行数
        StringBuffer countSb = new StringBuffer("select count(1) from ("+queryString+")");
        //排序
        queryString.append(" ORDER BY al.last_update_date DESC");
        Pagination<SrmPoApprovedListEntity_HI_RO> approvedList = srmPoApprovedListDAO_HI_RO.findPagination(queryString,countSb, map, pageIndex, pageRows);
        return approvedList;
    }

    /**
     * Description：保存供应商批准信息
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * certifyProcess  认证合格工序  VARCHAR2  N
     * militaryProductsFlag  用于军品(Y/N)  VARCHAR2  N
     * carProductFlag  用于汽车产品(Y/N)  VARCHAR2  N
     * listId  列表ID  NUMBER  Y
     * supplierId  供应商ID  NUMBER  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  Y
     * itemId  物料ID  NUMBER  N
     * listStatus  状态,快码:ISP_ASL_STATUS  VARCHAR2  N
     * disabledFlag  禁用标识(Y/N)  VARCHAR2  N
     * dayCapacity  （废弃）供应商日产能（日供货量）  NUMBER  N
     * listNumber  （废弃）序号  NUMBER  N
     * passU9Flag  （废弃）推U9标识(N:未推送,S:已推送,U:已推送后修改)  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * introductionModelId  供应商引入头层-型号ID，关联表srm_pos_introduction_model  NUMBER  N
     * resourceModelId  资源开发-型号信息ID,关联表srm_pos_resource_model  NUMBER  N
     * monopolyFlag  是否垄断型供应商(Y/N)  VARCHAR2  N
     * introductionHeaderId  供应商引入头层ID,关联表srm_pos_introduction_header  NUMBER  N
     * introductionSiteId  供应商引入头层-地点ID  NUMBER  N
     * requestId  报文请求ID  VARCHAR2  N
     * supplierSiteId  供应商档案地点Id，关联表srm_pos_supplier_sites  NUMBER  N
     * modelId  型号库ID,关联表srm_base_model  NUMBER  N
     * listId  列表ID  NUMBER  Y
     * supplierId  供应商ID  NUMBER  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  Y
     * itemId  物料ID  NUMBER  N
     * listStatus  状态  VARCHAR2  N
     * disabledFlag  禁用标识(Y/N)  VARCHAR2  N
     * dayCapacity  （废弃）供应商日产能（日供货量）  NUMBER  N
     * listNumber  （废弃）序号  NUMBER  N
     * passU9Flag  （废弃）推U9标识(N:未推送,S:已推送,U:已推送后修改)  VARCHAR2  N
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
    public JSONObject saveApprovedInfo(JSONObject params) throws Exception {
        LOGGER.info("保存供应商批准列表，参数：" + params.toString());
        SrmPoApprovedListEntity_HI approvedRow = null;
        Integer operatorUserId = params.getInteger("varUserId");
        if(null == params.getInteger("supplierId")){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"供应商必填",0,null);
        }
        if(null == params.getInteger("itemId")){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"物料必填",0,null);
        }
        if(null == params.getInteger("organizationId")){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"库存组织必填",0,null);
        }
        boolean flag = false;
        if (null == params.getInteger("listId")) {
            flag = findExistsId(params.getIntValue("itemId"), params.getIntValue("organizationId"), params.getIntValue("supplierId"));
            // 判断供应商id,库存组织id和物料id是否重复
            if (flag) {
                return SToolUtils.convertResultJSONObj("E", "供应商id,库存组织id和物料id重复，保存失败!"
                                + params.getString("supplierId") + "和"
                                + params.getString("organizationId") + "和"
                                + params.getString("itemId") + "已存在",
                        0, null);
            } else {
                // 保存供应商批准列表判断
                approvedRow = new SrmPoApprovedListEntity_HI();

            }
        } else {
            // 判断存在就是修改
            approvedRow = srmPoApprovedListDAO_HI.findByProperty("listId", params.getInteger("listId")).get(0);
        }
        approvedRow.setItemId(params.getInteger("itemId"));
        approvedRow.setOrgId(params.getInteger("orgId"));
        approvedRow.setOrganizationId(params.getInteger("organizationId"));
        approvedRow.setSupplierId(params.getInteger("supplierId"));
        approvedRow.setListStatus(params.getString("listStatusNumber"));
        if ("".equals(params.getString("disabledFlag")) || null == params.getString("disabledFlag")) {
            approvedRow.setDisabledFlag("N");
        } else {
            approvedRow.setDisabledFlag(params.getString("disabledFlag"));
        }
        approvedRow.setOperatorUserId(operatorUserId);
        srmPoApprovedListDAO_HI.saveOrUpdate(approvedRow);
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, approvedRow);
    }

    /**
     * Description：验证批准列表供应商的id,库存组织的id和物料id是否存在
     *
     * @param itemId 物料ID
     * @param organizationId 库存组织ID
     * @param supplierId 供应商ID
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    public boolean findExistsId(int itemId, int organizationId, int supplierId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemId", itemId);
        map.put("organizationId", organizationId);
        map.put("supplierId", supplierId);
        List rowSet = this.srmPoApprovedListDAO_HI.findByProperty(map);
        if (rowSet.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Description：导入货源列表
     * @param params 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */

    @Override
    public JSONObject saveApprovedList(JSONObject params) throws Exception {
        LOGGER.info("params:-{}", JSON.toJSONString(params));
        JSONArray jsonArray = params.getJSONArray("data");
        if(null == jsonArray || jsonArray.isEmpty()){
            SToolUtils.convertResultJSONObj(ERROR_STATUS,"暂无数据",0,null);
        }
        SrmPoApprovedListEntity_HI listEntity = null;
        SrmBaseItemsEntity_HI_RO itemEntity = null;
        SrmPosSupplierInfoEntity_HI_RO supplierEntity = null;
        SaafInstitutionEntity_HI_RO orgEntity = null;
        SaafInstitutionEntity_HI_RO organizationEntity = null;
        Integer varUserId = params.getInteger("varUserId");
        boolean flag = false;
        JSONArray error = checkData(jsonArray);
        System.out.println("ERROR:" + error.size());
        if (error.size() > 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "保存失败", error.size(), error);
        }
        List<SrmPoApprovedListEntity_HI> approvedList = new ArrayList<SrmPoApprovedListEntity_HI>();
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            try {
                listEntity = new SrmPoApprovedListEntity_HI();
                //根据物料编码去查询物料id
                itemEntity = getItemId(obj.getString("itemCode"));
                //根据供应商名称去查询供应商id
                supplierEntity = getSupplierId(obj.getString("supplierName"));
                //根据组织名称去查询库存组织id
                organizationEntity = getOrganizationId(obj.getString("organizationName"));
                //根据业务实体名称去查询业务实体id
                orgEntity = getOrgId(obj.getString("orgName"));
                flag = findExistsId(itemEntity.getItemId(), organizationEntity.getInstId(), supplierEntity.getSupplierId());
                // 判断供应商id,库存组织id和物料id是否重复
                if (flag) {
                    return SToolUtils.convertResultJSONObj(
                            "E",
                            "供应商id,库存组织id和物料id重复，保存失败!"
                                    + supplierEntity.getSupplierId() + "和"
                                    + organizationEntity.getInstId() + "和"
                                    + itemEntity.getItemId() + "已存在",
                            0, null);
                } else {
                    // 保存货源列表
                    listEntity.setOrganizationId(organizationEntity.getInstId());
                    listEntity.setItemId(itemEntity.getItemId());
                    listEntity.setSupplierId(supplierEntity.getSupplierId());
                    listEntity.setOrgId(orgEntity.getInstId());
                    listEntity.setListStatus("NEW");
                    listEntity.setDisabledFlag("N");
                    listEntity.setCreationDate(new Date());
                    listEntity.setCreatedBy(varUserId);
                    listEntity.setLastUpdateDate(new Date());
                    listEntity.setLastUpdatedBy(varUserId);
                    listEntity.setOperatorUserId(varUserId);
                    approvedList.add(listEntity);
                    srmPoApprovedListDAO_HI.saveOrUpdate(listEntity);
                }
            } catch (Exception e) {
                
                LOGGER.error("批量导入货源列表,第" + (i + 1) + "失败" + JSONObject.toJSONString(obj));
                obj.put("ERR_MESSAGE", "error");
                obj.put("ROW_NUM", i + 1);
                error.add(obj);
            }
        }
        if (error.size() == 0) {
            return SToolUtils.convertResultJSONObj("S", "保存成功行数为" + jsonArray.size() + "行", 0, null);
        }
        return SToolUtils.convertResultJSONObj("ERR_IMPORT", "保存成功行数为" + (jsonArray.size() - error.size()) + "行", error.size(), error);
    }

    /**
     * Description：导入货源数据校验
     * @param jsonArray
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private JSONArray checkData(JSONArray jsonArray) {
        JSONArray error = new JSONArray();
        if(null == jsonArray || jsonArray.isEmpty()){
            return error;
        }
        JSONObject jsonObject = null;
        StringBuffer errorMessage = new StringBuffer("");
        for (int i = 0; i < jsonArray.size(); i++) {
            int count = 0;
            int count2 = 0;
            JSONObject obj1 = jsonArray.getJSONObject(i);
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject obj2 = jsonArray.getJSONObject(j);
                if (obj1.getString("itemCode").equals(obj2.getString("itemCode"))
                        && obj1.getString("supplierName").equals(obj2.getString("supplierName"))
                        && obj1.getString("organizationName").equals(obj2.getString("organizationName"))) {
                    count++;
                }
            }
            if (count > 1) {
                jsonObject = new JSONObject();
                errorMessage.append("库存组织,物料编码,供应商编码不允许重复 ");
                jsonObject.put("ERR_MESSAGE", errorMessage);
                jsonObject.put("ROW_NUM", i + 1);
                error.add(jsonObject);
            }

            String itemCode = obj1.getString("itemCode");
            String supplierName = obj1.getString("supplierName");
            String organizationName = obj1.getString("organizationName");
            String orgName = obj1.getString("orgName");
            SrmBaseItemsEntity_HI_RO itemEntity = getItemId(itemCode);
            SrmPosSupplierInfoEntity_HI_RO supplierEntity = getSupplierId(supplierName);
            SaafInstitutionEntity_HI_RO organizationEntity = getOrganizationId(organizationName);
            SaafInstitutionEntity_HI_RO orgEntity = getOrgId(orgName);
            if (null == itemEntity) {
                jsonObject = new JSONObject();
                errorMessage.append("物料编码错误 ");
                jsonObject.put("ERR_MESSAGE", errorMessage);
                jsonObject.put("ROW_NUM", i + 1);
                error.add(jsonObject);
            }
            if (null == supplierEntity) {
                jsonObject = new JSONObject();
                errorMessage.append("供应商名称错误 ");
                jsonObject.put("ERR_MESSAGE", errorMessage);
                jsonObject.put("ROW_NUM", i + 1);
                error.add(jsonObject);
            }
            if (null == organizationEntity) {
                jsonObject = new JSONObject();
                errorMessage.append("库存组织名称错误 ");
                jsonObject.put("ERR_MESSAGE", errorMessage);
                jsonObject.put("ROW_NUM", i + 1);
                error.add(jsonObject);
            }
            if (null == orgEntity) {
                jsonObject = new JSONObject();
                errorMessage.append("业务实体名称错误 ");
                jsonObject.put("ERR_MESSAGE", errorMessage);
                jsonObject.put("ROW_NUM", i + 1);
                error.add(jsonObject);
            }
            if (null != itemEntity && null != supplierEntity && null != organizationEntity) {
                if (checkRepeatData(itemEntity.getItemId(), supplierEntity.getSupplierId(), organizationEntity.getInstId()) > 0) {
                    jsonObject = new JSONObject();
                    errorMessage.append("数据不允许重复	");
                    jsonObject.put("ERR_MESSAGE", errorMessage);
                    jsonObject.put("ROW_NUM", i + 1);
                    error.add(jsonObject);
                }
            }
        }
        return error;

    }
    /**
     * Description：查询数据是否重复  如果存在大于0 否则返回 0 其他返回-1
     * @param itemId 物料ID
     * @param supplierId 供应商ID
     * @param instId 库存组织ID
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private int checkRepeatData(Integer itemId, Integer supplierId, Integer instId) {
        if (null == itemId || null == supplierId || null == instId) return -1;
        StringBuffer sqlBuff = new StringBuffer(SrmPoApprovedListEntity_HI_RO.QUERY_COUNT);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemId", itemId);
        map.put("supplierId", supplierId);
        map.put("organizationId", instId);
        try {
            return srmPoApprovedListDAO_HI_RO.findList(sqlBuff, map).get(0).getCount();
        } catch (Exception e) {
            return -1;
        }

    }

    /**
     * Description：根据物料编码查询物料id
     *
     * @param itemCode 物料编码
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private SrmBaseItemsEntity_HI_RO getItemId(String itemCode) {
        if (null == itemCode || "".equals(itemCode.trim())) return null;
        SrmBaseItemsEntity_HI_RO itemsEntity = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("itemCode", itemCode.trim());
            if (srmBaseItemsDAO_HI_RO.findList(SrmPoApprovedListEntity_HI_RO.QUERY_ITEM_ID, map).size() != 0) {
                itemsEntity = srmBaseItemsDAO_HI_RO.findList(SrmPoApprovedListEntity_HI_RO.QUERY_ITEM_ID, map).get(0);
            }
            return itemsEntity;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Description：根据供应商名称查询供应商id
     *
     * @param supplierName 供应商名称
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private SrmPosSupplierInfoEntity_HI_RO getSupplierId(String supplierName) {
        if (null == supplierName || "".equals(supplierName.trim())) return null;
        SrmPosSupplierInfoEntity_HI_RO supplierEntity = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("supplierName", supplierName.trim());
        if (srmPosSupplierInfoDAO_HI_RO.findList(SrmPoApprovedListEntity_HI_RO.QUERY_SUPPLIER_ID, map).size() != 0) {
            supplierEntity = srmPosSupplierInfoDAO_HI_RO.findList(SrmPoApprovedListEntity_HI_RO.QUERY_SUPPLIER_ID, map).get(0);
        }
        return supplierEntity;
    }

    /**
     * Description：根据库存组织名称获取库存组织对象
     * @param organizationName 库存组织名称
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private SaafInstitutionEntity_HI_RO getOrganizationId(String organizationName) {
        if (null == organizationName || "".equals(organizationName.trim())) return null;
        SaafInstitutionEntity_HI_RO organizationEntity = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("instName", organizationName.trim());
        if (saafInstitutionDAO_HI_RO.findList(SrmPoApprovedListEntity_HI_RO.QUERY_ORGANIZATION_ID, map).size() != 0) {
            organizationEntity = saafInstitutionDAO_HI_RO.findList(SrmPoApprovedListEntity_HI_RO.QUERY_ORGANIZATION_ID, map).get(0);
        }
        return organizationEntity;
    }

    /**
     * Description：根据业务实体名称获取业务实体对象
     * @param orgName 业务实体名称
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private SaafInstitutionEntity_HI_RO getOrgId(String orgName) {
        if (null == orgName || "".equals(orgName.trim())) return null;
        SaafInstitutionEntity_HI_RO orgEntity = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("instName", orgName.trim());
        if (saafInstitutionDAO_HI_RO.findList(SrmPoApprovedListEntity_HI_RO.QUERY_ORG_ID, map).size() != 0) {
            orgEntity = saafInstitutionDAO_HI_RO.findList(SrmPoApprovedListEntity_HI_RO.QUERY_ORG_ID, map).get(0);
        }
        return orgEntity;
    }
}
