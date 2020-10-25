package saaf.common.fmw.base.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.*;
import saaf.common.fmw.base.model.entities.readonly.*;
import saaf.common.fmw.base.model.inter.*;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.readonly.SrmPoForecastLinesEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionItemsEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonBidItemPricesEntity_HI;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：SrmBaseItemsBServer.java
 * Description：物料基表的Server层接口实现类
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019-05-29     秦晓钊          创建
 * ==============================================================================
 */
@Component
public class SrmBaseItemsBServer implements ISrmBaseItemsB {

    @Autowired
    private ViewObject<SrmBaseItemsBEntity_HI> srmBaseItemsBDAO_HI;

    @Autowired
    private BaseViewObject<SrmBaseItemsBEntity_HI_RO> srmBaseItemsBDAO_HI_RO;

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;


    @Autowired
    private ISaafLookup baseSaafLookupServer;

    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;
    @Autowired
    private ISaafInstitution iSaafInstitution;

    @Autowired
    private ISrmBaseItem iSrmBaseItemServer;
    @Autowired
    private ViewObject<SrmBaseItemsEntity_HI> srmBaseItemsDAO_HI;

    @Autowired
    private BaseViewObject<SaafLookupValuesEntity_HI_RO> saafLookupValuesDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmBaseCategoriesEntity_HI_RO> srmBaseCategoriesDAO_HI_RO;
    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值

    @Autowired
    private ViewObject<SrmBaseItemsEntity_HI> srmBaseItemDAO_HI;

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseItemsBServer.class);

    private static String TABLENAME = "srm_base_items_b";
    @Autowired
    private ViewObject<SaafInstitutionEntity_HI> saafInstitutionDAO_HI;

    @Autowired
    private ViewObject<SrmPonAuctionItemsEntity_HI> srmPonAuctionItemsDAO_HI;
    @Autowired
    private ViewObject<SrmPonAuctionHeadersEntity_HI> srmPonAuctionHeadersDAO_HI;
    @Autowired
    private ViewObject<SrmPonBidItemPricesEntity_HI> srmPonBidItemPricesDAO_HI;

    public SrmBaseItemsBServer() {
        super();
    }

    /**
     * Description：保存物料，自动生成的方法
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * itemId  物料ID  NUMBER  Y
     * itemCode  物料编码  VARCHAR2  Y
     * itemName  物料名称  VARCHAR2  Y
     * itemDescription  物料说明  VARCHAR2  N
     * globalFlag  全局标识  VARCHAR2  N
     * enabledAsl  启用ASL  VARCHAR2  N
     * uomCode  计量单位  VARCHAR2  N
     * itemStatus  物料状态  VARCHAR2  N
     * purchasingFlag  可采购标识  VARCHAR2  N
     * agentId  采购员ID  NUMBER  N
     * categoryId  采购分类ID  NUMBER  Y
     * purchaseCycle  采购周期  NUMBER  N
     * purchasingLeadTime  采购提前期  NUMBER  N
     * minPacking  最小包装量  NUMBER  N
     * benchmarkPrice  基准价  NUMBER  N
     * invalidDate  失效时间  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * imageId  图片ID  NUMBER  N
     * itemCode  物料编码  VARCHAR2  Y
     * itemName  物料名称  VARCHAR2  Y
     * itemDescription  物料说明  VARCHAR2  N
     * globalFlag  全局标识(Y/N)  VARCHAR2  N
     * enabledAsl  启用ASL  VARCHAR2  N
     * uomCode  计量单位  VARCHAR2  N
     * itemStatus  物料状态  VARCHAR2  N
     * purchasingFlag  可采购标识  VARCHAR2  N
     * agentId  采购员ID  NUMBER  N
     * categoryId  采购分类ID  NUMBER  Y
     * purchaseCycle  采购周期  NUMBER  N
     * purchasingLeadTime  采购提前期  NUMBER  N
     * minPacking  最小包装量  NUMBER  N
     * benchmarkPrice  基准价  NUMBER  N
     * invalidDate  失效时间  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * imageId  图片ID  NUMBER  N
     * cost  成本  NUMBER  N
     * specification  规格型号  VARCHAR2  N
     * region  组织区域  VARCHAR2  N
     * itemId  物料ID  NUMBER  Y
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    public JSONObject saveSrmBaseItemsB(JSONObject jsonParams) throws Exception {
        try {
            SrmBaseItemsBEntity_HI validateItem = JSON.parseObject(jsonParams.toString(), SrmBaseItemsBEntity_HI.class);
            SrmBaseItemsBEntity_HI item = null;
            if (null == validateItem.getCategoryId() || "".equals(validateItem.getCategoryId())){
                return SToolUtils.convertResultJSONObj("E", "品类编码不能为空，请选择品类编码！", 0, null);
            }
            if (null == validateItem.getItemName() || "".equals(validateItem.getItemName())){
                return SToolUtils.convertResultJSONObj("E", "物料描述不能为空，请填写物料描述！", 0, null);
            }
            if (null == validateItem.getUomCode() || "".equals(validateItem.getUomCode())){
                return SToolUtils.convertResultJSONObj("E", "计量单位不能为空，请选择计量单位！", 0, null);
            }
            if (null == validateItem.getRegion() || "".equals(validateItem.getRegion())){
                return SToolUtils.convertResultJSONObj("E", "区域不能为空，请选择区域！", 0, null);
            }
            if (null == validateItem.getItemId()){//新增
                item = JSON.parseObject(jsonParams.toString(), SrmBaseItemsBEntity_HI.class);
                //物料编码前缀
                String prefix = null;
                JSONObject params = new JSONObject();
                params.put("lookupType", "ITEM_PREFIX");
                params.put("lookupCode", item.getRegion());
                List<SaafLookupValuesEntity_HI_RO> regionList = baseSaafLookupServer.findLookupCode(params);
                if (null != regionList && regionList.size() > 0){
                    prefix = regionList.get(0).getTag();
                }else {
                    return SToolUtils.convertResultJSONObj("E", "编码前缀尚未维护，请联系系统管理员！", 0, null);
                }
                //物料编码
                String itemCode = saafSequencesUtil.getDocSequences((TABLENAME + prefix).toUpperCase(),
                        prefix, 7);
                item.setItemCode(itemCode);
                //全局标识(选择，未选择)
                if (null != item.getGlobalFlag() || !"".equals(item.getGlobalFlag())) {
                    item.setGlobalFlag("Y");
                } else {
                    item.setGlobalFlag("N");
                }
                //启用ASL表示(选择，“未选择)
                if (null != item.getEnabledAsl() || !"".equals(item.getEnabledAsl())) {
                    item.setEnabledAsl("Y");
                } else {
                    item.setEnabledAsl("N");
                }
                //可采购(选择，未选择)
                if (null != item.getPurchasingFlag() || !"".equals(item.getPurchasingFlag())) {
                    item.setPurchasingFlag("Y");
                } else {
                    item.setPurchasingFlag("N");
                }
                //item.setItemStatus("NEW");//物料状态为“新增”
                if("Y".equals(jsonParams.getString("isValid"))){
                    item.setItemStatus("ACT");
                }else{
                    item.setItemStatus("NEW");//物料状态为“拟定”
                }
                //item.setItemStatus(item.);
            }else {//修改
                SrmBaseItemsBEntity_HI srmBaseItemsBEntityHi = JSON.parseObject(jsonParams.toString(), SrmBaseItemsBEntity_HI.class);
                item = srmBaseItemsBDAO_HI.getById(srmBaseItemsBEntityHi.getItemId());
                /*if (null == item.getVersionNum() || null == srmBaseItemsBEntityHi.getVersionNum() || item.getVersionNum().intValue() != srmBaseItemsBEntityHi.getVersionNum().intValue()){
                    throw new Exception("物料已被更新，请重新查询后操作");
                }*/
                PropertyUtils.copyProperties(item, srmBaseItemsBEntityHi);
                if("Y".equals(jsonParams.getString("isValid"))){
                    item.setItemStatus("ACT");
                }
                if("Y".equals(jsonParams.getString("isInValid"))){
                    item.setItemStatus("INACT");
                }
            }
            item.setOperatorUserId(jsonParams.getInteger("varUserId"));
            srmBaseItemsBDAO_HI.saveOrUpdate(item);
            //分配组织
            if(!"NEW".equals(item.getItemStatus())){
                iSrmBaseItemServer.saveMatterData(jsonParams, item);
            }
            return SToolUtils.convertResultJSONObj("S", "保存成功", 1, item);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：查询列表，自动生成的方法
     *
     * @param parameters 查询参数，JSON格式数据
     * @param pageIndex  页码
     * @param pageRows   每页显示的行数
     * @return 对象
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    public Pagination<SrmBaseItemsBEntity_HI_RO> findSrmBaseItemsBList(JSONObject parameters, Integer pageIndex, Integer pageRows){
        StringBuffer queryString = new StringBuffer(SrmBaseItemsBEntity_HI_RO.SRM_BASE_ITEMS_B_QUERY_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(parameters, "sib.item_code", "itemCode", queryString, map, "like");
        SaafToolUtils.parperParam(parameters, "sib.item_name", "itemName", queryString, map, "like");
        SaafToolUtils.parperParam(parameters, "sib.category_id", "categoryId", queryString, map, "=");
        SaafToolUtils.parperParam(parameters, "sbc.full_category_code", "categoryCode", queryString, map, "like");
        SaafToolUtils.parperParam(parameters, "sbc.full_category_name", "categoryName", queryString, map, "like");
        SaafToolUtils.parperParam(parameters, "sib.item_Status", "itemStatus", queryString, map, "=");
        SaafToolUtils.parperParam(parameters, "sib.item_Id", "itemId", queryString, map, "=");
        queryString.append(" AND sib.Category_Id IN  (" +getCategoryId(parameters.getInteger("varUserId"))+")");

        if ("Y".equals(parameters.getString("importTemplates"))){
            queryString.append(" and 1=2");
        }

        String itemIdStr = parameters.getString("itemIdStr");
        if (null != itemIdStr && !"".equals(itemIdStr)) {
            queryString.append(" AND sib.item_id IN (" + itemIdStr + ") ") ;
        }
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" order by sib.creation_date desc");
        Pagination<SrmBaseItemsBEntity_HI_RO> resultSet = srmBaseItemsBDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
        return resultSet;
    }

    private String getCategoryId(Integer userId){
        JSONObject json=new JSONObject();
        json.put("userId",userId);
        List<SrmBaseUserCategoriesEntity_HI_RO> userCategoriesList=srmBaseUserCategories.findUserCategories(json);
        List  categoryIds=new ArrayList();
        for(SrmBaseUserCategoriesEntity_HI_RO ro : userCategoriesList){
            categoryIds.add(ro.getCategoryId().toString());
        }

        String categoryId= String.valueOf(categoryIds.stream().distinct().collect(Collectors.joining(",")));
        return categoryId;
    }

    /**
     * Description：查询指定ID的物料，自动生成的方法
     *
     * @param parameters 查询参数，JSON格式数据
     * @return 物料对象
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    public SrmBaseItemsBEntity_HI_RO findSrmBaseItemsBById(JSONObject parameters) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmBaseItemsBEntity_HI_RO.SRM_BASE_ITEMS_B_QUERY_SQL);
            queryString.append(" AND sib.item_id = :itemId");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("itemId", parameters.getInteger("itemId"));
            SrmBaseItemsBEntity_HI_RO srmBaseItemsBEntity_HI_RO = srmBaseItemsBDAO_HI_RO.findList(queryString, map).get(0);
            return srmBaseItemsBEntity_HI_RO;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：根据指定的业务实体ID，查询物料
     *
     * @param parameters 查询参数，JSON格式数据
     * @param pageIndex  页码
     * @param pageRows   每页显示的行数
     * @return 物料列表
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    public Pagination findItemsBByOrgId(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmBaseItemsBEntity_HI_RO.QUERY_ITEMS_BY_ORG_SQL);
            Map<String, Object> map = new HashMap();
            map.put("orgId", parameters.getInteger("orgId"));
            SaafToolUtils.parperParam(parameters, "sib.item_code", "itemCode", queryString, map, "LIKE");
            SaafToolUtils.parperParam(parameters, "sib.item_name", "itemName", queryString, map, "LIKE");
            SaafToolUtils.parperParam(parameters, "sbc.full_category_code", "categoryCode", queryString, map, "LIKE");
            SaafToolUtils.parperParam(parameters, "sbc.full_category_name", "categoryName", queryString, map, "LIKE");
            String countSql = "select count(1) from (" + queryString + ")";
            Pagination<SrmBaseItemsBEntity_HI_RO> resultSet = srmBaseItemsBDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return resultSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：失效物料基表
     *
     * @param jsonParams 物料的JSON格式数据
     * @return 对象
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-03-04     吴嘉利          创建
     * ==============================================================================
     */
    /*public JSONObject updateInvalidBaseItemB(JSONObject jsonParams) throws Exception {
        try {
            Integer itemId = jsonParams.getInteger("itemId");
            Integer userId = jsonParams.getInteger("varUserId");
            SrmBaseItemsBEntity_HI item = null;
            if (null != itemId){
                item = srmBaseItemsBDAO_HI.getById(itemId);
                item.setItemStatus("INACT");
                item.setInvalidDate(new Date());
                item.setOperatorUserId(userId);
                srmBaseItemsBDAO_HI.saveOrUpdate(item);
            }
            return SToolUtils.convertResultJSONObj("S", "失效成功", 1, item);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }*/



    /**
     * Description：生效/失效组织物料
     *
     * @param jsonParams 物料的JSON格式数据
     * @return 对象
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-03-04     吴嘉利          创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateInvalidBaseItemB(JSONObject jsonParams) throws Exception {
        JSONArray array = jsonParams.getJSONArray("list");
        Integer userId = jsonParams.getInteger("varUserId");
        String action = jsonParams.getString("action");
        String msg = null;
        if ("ACT".equals(action)) {
            msg = "生效";
        }else if("INACT".equals(action)){
            msg = "失效";
        }else {
            return SToolUtils.convertResultJSONObj("E", "参数错误！", 0, null);
        }
        try {
            List<SrmBaseItemsBEntity_HI> srmBaseItemBList = JSON.parseArray(array.toJSONString(), SrmBaseItemsBEntity_HI.class);
            List<SrmBaseItemsBEntity_HI> updateList = new ArrayList<>();
            Date date = new Date();
            if (null != srmBaseItemBList && srmBaseItemBList.size() > 0) {
                Map<String, Object> map = new HashMap<>();
                List itemIdList = new ArrayList();
                for(SrmBaseItemsBEntity_HI item : srmBaseItemBList){
                    //查找组织物料
                    map.put("itemId", item.getItemId());
                    itemIdList.add(item.getItemId());
                    List<SrmBaseItemsBEntity_HI> list = srmBaseItemsBDAO_HI.findByProperty(map);
                    if (null != list && list.size() == 1){
                        SrmBaseItemsBEntity_HI baseItemsBEntityHi = list.get(0);
                        JSONObject obj=new JSONObject();
                        obj.put("itemId",baseItemsBEntityHi.getItemId());
                        //查詢物料所在的所有組織
                        List<SrmBaseItemsEntity_HI_RO> itemsOrglist = iSrmBaseItemServer.findMatterDataManageOrgList(obj);
                        //生效或失效
                        if ("INACT".equals(action)){
                            if ("ACT".equals(baseItemsBEntityHi.getItemStatus())) {
                                List instFilelist=new ArrayList();
                                List itemsOrgFilelist=new ArrayList();
                                //查询组织权限
                                obj.clear();
                                obj.put("varUserId",userId);
                                obj.put("region",baseItemsBEntityHi.getRegion());
                                obj.put("instType","ORGANIZATION");
                                obj.put("enabled","Y");
                                obj.put("orderByFlag","Y");
                                obj.put("isPo","Y");
                                List<SaafInstitutionEntity_HI_RO> instlist = iSaafInstitution.findInstitutionList(obj);
                                for(SaafInstitutionEntity_HI_RO ro:instlist){
                                    if("ORGANIZATION".equals(ro.getInstType())){
                                        instFilelist.add(ro.getInstId());
                                    }
                                }
                                //查询物料所属库存组织
                                /*obj.clear();
                                obj.put("itemId",baseItemsBEntityHi.getItemId());
                                List<SrmBaseItemsEntity_HI_RO> itemsOrglist = iSrmBaseItemServer.findMatterDataManageOrgList(obj);*/
                                for(SrmBaseItemsEntity_HI_RO ro:itemsOrglist){
                                    if("Y".equals(ro.getEnabledFlag())) {
                                        itemsOrgFilelist.add(ro.getInstId());
                                    }
                                }
                                if(instFilelist.containsAll(itemsOrgFilelist)){
                                    baseItemsBEntityHi.setItemStatus("INACT");
                                    baseItemsBEntityHi.setInvalidDate(date);
                                    for(SrmBaseItemsEntity_HI_RO ro:itemsOrglist){
                                            Map<String, Object> itemMap = new HashMap<>();
                                            itemMap.put("organizationId",ro.getInstId());
                                            itemMap.put("itemId", baseItemsBEntityHi.getItemId());
                                            List<SrmBaseItemsEntity_HI> orgList= srmBaseItemsDAO_HI.findByProperty(map);
                                            SrmBaseItemsEntity_HI itemOrg=orgList.get(0);
                                            itemOrg.setItemStatus("INACT");
                                            itemOrg.setInvalidDate(date);
                                            itemOrg.setOperatorUserId(userId);
                                            srmBaseItemsDAO_HI.saveOrUpdate(itemOrg);
                                            srmBaseItemsDAO_HI.fluch();

                                    }
                                }else{
                                    throw new UtilsException("组织权限不足，您无权失效物料"+baseItemsBEntityHi.getItemCode());
                                }
                            }else {
                                return SToolUtils.convertResultJSONObj("E", "失效失败！物料" + baseItemsBEntityHi.getItemCode() + "状态不是“生效”，无法进行失效", 1, null);
                            }
                        }else if ("ACT".equals(action)){
                            if ("NEW".equals(baseItemsBEntityHi.getItemStatus())) {
                                baseItemsBEntityHi.setItemStatus("ACT");
                                baseItemsBEntityHi.setInvalidDate(null);
                                JSONObject paramJSON=new JSONObject();
                                paramJSON.put("varUserId",userId);
                                paramJSON.put("orgList",itemsOrglist);
                                iSrmBaseItemServer.saveMatterData(paramJSON, baseItemsBEntityHi);
                            }else {
                                return SToolUtils.convertResultJSONObj("E", "生效失败！物料" + baseItemsBEntityHi.getItemCode() + "状态不是“拟定”，无法进行生效", 1, null);
                            }
                        }
                        baseItemsBEntityHi.setOperatorUserId(userId);
                        updateList.add(baseItemsBEntityHi);
                    }
                    map.clear();
                }
                srmBaseItemsBDAO_HI.updateAll(updateList);
                srmBaseItemsBDAO_HI.fluch();
            }
            return SToolUtils.convertResultJSONObj("S", msg + "成功！", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    /**
     * Description：删除物料
     *
     * @param jsonParams 物料的JSON格式数据
     * @return 对象
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-03     SIE 谢晓霞          创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteBaseItemsB(JSONObject jsonParams) throws Exception {
        JSONArray array = jsonParams.getJSONArray("list");
        try {
            List<SrmBaseItemsBEntity_HI> srmBaseItemBList = JSON.parseArray(array.toJSONString(), SrmBaseItemsBEntity_HI.class);
            List<SrmBaseItemsBEntity_HI> deleteList = new ArrayList<>();
            if (null != srmBaseItemBList && srmBaseItemBList.size() > 0) {
                Map<String, Object> map = new HashMap<>();
                for(SrmBaseItemsBEntity_HI item : srmBaseItemBList){
                    map.put("itemId", item.getItemId());
                    List<SrmBaseItemsBEntity_HI> list = srmBaseItemsBDAO_HI.findByProperty(map);
                    if (null != list && list.size() == 1){
                        SrmBaseItemsBEntity_HI baseItemsBEntityHi = list.get(0);
                        if ("NEW".equals(baseItemsBEntityHi.getItemStatus())){
                            deleteList.add(baseItemsBEntityHi);
                        }else {
                            return SToolUtils.convertResultJSONObj("E",  "物料:" + baseItemsBEntityHi.getItemCode() + ",状态不是拟定，请重新查询后操作！", 1, null);
                        }
                    }
                    map.clear();
                }
            }
            srmBaseItemsBDAO_HI.deleteAll(deleteList);
            srmBaseItemsBDAO_HI.fluch();
            return SToolUtils.convertResultJSONObj("S",  "删除成功！", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }



    /**
     * Description：新增物料主数据导入
     * @param jsonParams 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-07     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject saveImportMaterialMaster(JSONObject jsonParams) throws Exception {
        int operatorUserId = jsonParams.getInteger("operatorUserId");
        JSONObject entity = jsonParams.getJSONObject("info");
        JSONArray jsonArray = jsonParams.getJSONArray("data");
        //int organizationId = jsonParams.getInteger("organizationId");

        if(null == jsonArray || jsonArray.isEmpty()){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"暂无数据",0,null);
        }
            JSONArray error = checkArray(jsonArray,operatorUserId);
            if (null != error && error.size() > 0) {
                return SToolUtils.convertResultJSONObj("ERR_IMPORT", "保存失败", error.size(), error);
            }

        //提取导入数据
        int count =jsonArray.size();
        while(jsonArray.size()>0&&!ObjectUtils.isEmpty(jsonArray)){
            SrmBaseItemsBEntity_HI item = JSON.parseObject(jsonArray.getJSONObject(0).toString(), SrmBaseItemsBEntity_HI.class);
            //保存
            //物料编码前缀
            String prefix = null;
            JSONObject params = new JSONObject();
            params.put("lookupType", "ITEM_PREFIX");
            params.put("lookupCode", item.getRegion());
            List<SaafLookupValuesEntity_HI_RO> regionList = baseSaafLookupServer.findLookupCode(params);
            if (null != regionList && regionList.size() > 0){
                prefix = regionList.get(0).getTag();
            }else {
                return SToolUtils.convertResultJSONObj("E", "编码前缀尚未维护，请联系系统管理员！", 0, null);
            }
            //物料编码
            String itemCode = saafSequencesUtil.getDocSequences((TABLENAME + prefix).toUpperCase(),
                    prefix, 7);
            item.setItemCode(itemCode);
            //全局标识(选择，未选择)
            item.setGlobalFlag("N");
            //启用ASL表示(选择，“未选择)
            item.setEnabledAsl("N");
            //可采购(选择，未选择)
            item.setPurchasingFlag("N");
            item.setItemStatus("ACT");
            item.setOperatorUserId(jsonParams.getInteger("varUserId"));
            srmBaseItemsBDAO_HI.saveOrUpdate(item);
            //分配组织
           if(!ObjectUtils.isEmpty(jsonArray.getJSONObject(0).getInteger("instId"))){
                Integer instId=jsonArray.getJSONObject(0).getInteger("instId");
                Map<String, Object> map = new HashMap<>();
                SrmBaseItemsEntity_HI items = new SrmBaseItemsEntity_HI();
                BeanUtils.copyProperties(item,items);
                items.setOrganizationId(instId);
                items.setOperatorUserId(operatorUserId);
                srmBaseItemDAO_HI.saveOrUpdate(items);
                srmBaseItemDAO_HI.fluch();
            }

            for(int n = 1; n < jsonArray.size(); n++){
                JSONObject obj = jsonArray.getJSONObject(n);
                if(item.getItemName().equals(obj.getString("itemName"))&&item.getSpecification().equals(obj.getString("specification"))&&item.getUomCode().equals(obj.getString("uomCode"))&&item.getCategoryId().equals(obj.getInteger("categoryId"))&&item.getRegion().equals(obj.getString("region"))){
                    if(!ObjectUtils.isEmpty(obj.getInteger("instId"))){
                        Integer instId=obj.getInteger("instId");
                        Map<String, Object> map = new HashMap<>();
                        SrmBaseItemsEntity_HI items = new SrmBaseItemsEntity_HI();
                        BeanUtils.copyProperties(item,items);
                        items.setOrganizationId(instId);
                        items.setOperatorUserId(operatorUserId);
                        srmBaseItemDAO_HI.saveOrUpdate(items);
                        srmBaseItemDAO_HI.fluch();
                    }
                    jsonArray.remove(n);
                    n--;
                }
            }
            jsonArray.remove(0);
        }
        return SToolUtils.convertResultJSONObj("S", "成功导入" + count + "行数据", count, null);
    }

    /**
     * Description：导入数据校验
     * @param jsonArray
     * @param operatorUserId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-07     SIE 谢晓霞       创建
     * ==============================================================================
     */
    private JSONArray checkArray(JSONArray jsonArray , Integer operatorUserId) throws Exception{
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        if (null == jsonArray || jsonArray.isEmpty()){
            return null;
        }
        JSONArray error = new JSONArray();
        JSONObject e = null;
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            //检查单位
            if (object.getString("uomCodeName") != null && !"".equals(object.getString("uomCodeName"))) {
                queryParamMap = new HashMap<String, Object>();
                queryString = new StringBuffer();
                queryString.append(SaafLookupValuesEntity_HI_RO.QUERY_SQL);
                SaafToolUtils.parperParam(object, "slv.meaning", "uomCodeName", queryString, queryParamMap, "=");
                List<SaafLookupValuesEntity_HI_RO> result = saafLookupValuesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
                if (result == null || result.size() < 1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "单位不存在");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }else{
                    object.put("uomCode",result.get(0).getLookupCode());
                }
            }
            if (object.getString("fullCategoryCode") != null && !"".equals(object.getString("fullCategoryCode"))) {
                queryParamMap = new HashMap<String, Object>();
                queryString = new StringBuffer(SrmBaseCategoriesEntity_HI_RO.GET_PO_HEADER_SQLS);
                SaafToolUtils.parperParam(object, "t.full_category_code", "fullCategoryCode", queryString, queryParamMap, "=");
                queryString.append(" AND t.Category_Id IN (" +getCategoryId(operatorUserId)+") and t.LEAF_FLAG ='Y' ");
                List<SrmBaseCategoriesEntity_HI_RO> result = srmBaseCategoriesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
                if (result == null || result.size() < 1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "分类编码不存在或组织权限不足");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }else{
                    object.put("categoryId",result.get(0).getCategoryId());
                }
            }
            //区域
            if (object.getString("regionName") != null && !"".equals(object.getString("regionName"))) {
                Map map = new HashMap();
                map.put("lookupType", "BASE_INST_REGION");
                map.put("meaning", object.getString("regionName"));
                map.put("enabledFlag", "Y");
                List<SaafLookupValuesEntity_HI> lookupValues = saafLookupValuesDAO_HI.findByProperty(map);
                if (lookupValues == null || lookupValues.size() < 1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "区域不存在");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }else{
                    object.put("region",lookupValues.get(0).getLookupCode());
                    //组织名称
                    if(object.getString("instName") != null && !"".equals(object.getString("instName"))){
                        JSONObject obj=new JSONObject();
                        obj.put("varUserId",operatorUserId);
                        obj.put("region",lookupValues.get(0).getLookupCode());
                        obj.put("instType","ORGANIZATION");
                        obj.put("enabled","Y");
                        obj.put("orderByFlag","Y");
                        obj.put("isPo","Y");
                        List<SaafInstitutionEntity_HI_RO> instlist = iSaafInstitution.findInstitutionList(obj);
                        if(!ObjectUtils.isEmpty(instlist)){
                            List instFilelist=new ArrayList();
                            for(SaafInstitutionEntity_HI_RO ro:instlist){
                                if("ORGANIZATION".equals(ro.getInstType())){
                                    instFilelist.add(ro.getInstName());
                                }
                            }
                            if(instFilelist.contains(object.getString("instName"))){
                                for(SaafInstitutionEntity_HI_RO ro:instlist){
                                    if(object.getString("instName").equals(ro.getInstName())){
                                        object.put("instId",ro.getInstId());
                                    }
                                }
                            }else{
                                e = new JSONObject();
                                e.put("ERR_MESSAGE", "在该区域下，您组织权限不足");
                                e.put("ROW_NUM", i + 1);
                                error.add(e);
                                continue;
                            }
                        }else{
                            e = new JSONObject();
                            e.put("ERR_MESSAGE", "在该区域下，您组织权限不足");
                            e.put("ROW_NUM", i + 1);
                            error.add(e);
                            continue;
                        }
                    }
                }

            }

            //成本
            if(!StringUtils.isEmpty(object.getString("cost"))){
                boolean cost = isBigDecimal(object.getString("cost"));
                if (!cost) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "成本" + object.getString("cost") + "输入有误");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }else{
                //相同物料不同成本，报错
                for (int n = i+1; n < jsonArray.size(); n++) {
                    JSONObject objectB = jsonArray.getJSONObject(n);
                    if(object.getString("itemName").equals(objectB.getString("itemName"))&&object.getString("itemSpec").equals(objectB.getString("itemSpec"))&&object.getString("uomCodeName").equals(objectB.getString("uomCodeName"))&&object.getString("fullCategoryCode").equals(objectB.getString("fullCategoryCode"))&&object.getString("regionName").equals(objectB.getString("regionName"))&&!object.getBigDecimal("cost").equals(objectB.getBigDecimal("cost"))){
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "该物料与"+(n+1)+"行物料相同但成本不一致");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    }
                }
            }


            //查询系统中是否存在该物料
            SrmBaseItemsBEntity_HI item = JSON.parseObject(object.toString(), SrmBaseItemsBEntity_HI.class);
            Map<String, Object> map = new HashMap<>();
            map.put("itemName",item.getItemName());
            map.put("region",item.getRegion());
            map.put("categoryId",item.getCategoryId());
            map.put("specification",item.getSpecification());
            map.put("uomCode",item.getUomCode());
            List<SrmBaseItemsBEntity_HI> itemList=srmBaseItemsBDAO_HI.findByProperty(map);
            if(!ObjectUtils.isEmpty(itemList)){
                e = new JSONObject();
                e.put("ERR_MESSAGE", "系统中已存在该物料");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }



        }
        return error;
    }

    /**
     * Description：BigDecimal数据类型校验
     * @param integer 输入数据
     * @return boolean
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-07-07           SIE 谢晓霞             创建
     * =======================================================================
     */
    public static boolean isBigDecimal(String integer) {
        try{
            BigDecimal bd = new BigDecimal(integer);
            return true;
        }catch(NumberFormatException e)
        {
            return false;
        }
    }


    /**
     * Description：招标询价转订单/合同生成物料主数据
     * @param jsonParams 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-07     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject createPonMaterialMaster(JSONObject jsonParams) throws Exception {
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId");
        Integer operatorUserId = jsonParams.getInteger("varUserId");
        SrmPonAuctionHeadersEntity_HI auctionHeader=srmPonAuctionHeadersDAO_HI.getById(auctionHeaderId);
        List<SrmPonAuctionItemsEntity_HI> auctionItems=srmPonAuctionItemsDAO_HI.findByProperty("auctionHeaderId",auctionHeaderId);
        List<SrmPonBidItemPricesEntity_HI> bidItems=srmPonBidItemPricesDAO_HI.findByProperty("auctionHeaderId",auctionHeaderId);
        Integer instId = auctionHeader.getOrganizationId();
        boolean flag=false;
        if(!ObjectUtils.isEmpty(auctionItems)){
            for(int i=0;i<auctionItems.size();i++){
                if(ObjectUtils.isEmpty(auctionItems.get(i).getItemId())){
                    flag=true;
                }
            }
        }
        if(flag){
            if(!ObjectUtils.isEmpty(auctionItems)){
                //查询库存组织区域
                SaafInstitutionEntity_HI orgRegion = saafInstitutionDAO_HI.getById(auctionHeader.getOrganizationId());
                if (ObjectUtils.isEmpty(orgRegion)) {
                    throw new UtilsException("系统中已不存在当前库存组织，请重新选择！");
                } else if (ObjectUtils.isEmpty(orgRegion.getInstRegion())) {
                    throw new UtilsException("当前库存未维护区域，请先进行维护");
                }
                //物料编码前缀
                String prefix = null;
                JSONObject params = new JSONObject();
                params.put("lookupType", "ITEM_PREFIX");
                params.put("lookupCode", orgRegion.getInstRegion());
                List<SaafLookupValuesEntity_HI_RO> regionList = baseSaafLookupServer.findLookupCode(params);
                if (null != regionList && regionList.size() > 0) {
                    prefix = regionList.get(0).getTag();
                } else {
                    throw new UtilsException("编码前缀尚未维护，请联系系统管理员！");
                }

                for(int i=0;i<auctionItems.size();i++){
                    SrmPonAuctionItemsEntity_HI item=auctionItems.get(i);
                    SrmBaseItemsBEntity_HI itemB =new SrmBaseItemsBEntity_HI();
                    BeanUtils.copyProperties(item,itemB);
                    itemB.setItemName(item.getItemDescription());
                    itemB.setUomCode(item.getUnitOfMeasure());
                    itemB.setRegion(orgRegion.getInstRegion());
                    //查询物料主数据中是否已存在该描述招标物料
                    Map<String, Object> map = new HashMap<>();
                    map.put("itemName",itemB.getItemName());
                    map.put("uomCode",itemB.getUomCode());
                    map.put("categoryId",itemB.getCategoryId());
                    map.put("specification",itemB.getSpecification());
                    map.put("region",itemB.getRegion());
                    // map.put("itemStatus","ACT");
                    List<SrmBaseItemsBEntity_HI> itemM= srmBaseItemsBDAO_HI.findByProperty(map);
                    if(!ObjectUtils.isEmpty(itemM)&&!"INACT".equals(itemM.get(0).getItemStatus())){
                        itemB= itemM.get(0);
                        //若为拟定状态，将物料生效
                        if("NEW".equals(itemB.getItemStatus())){
                            itemB.setItemStatus("ACT");
                            itemB.setOperatorUserId(operatorUserId);
                            srmBaseItemsBDAO_HI.saveOrUpdate(itemB);

                        }
                        //查询组织物料里是否为该物料分配组织
                        map.clear();
                        map.put("itemId",itemB.getItemId());
                        map.put("organizationId",instId);
                        List<SrmBaseItemsEntity_HI> itemA=srmBaseItemDAO_HI.findByProperty(map);
                        if(ObjectUtils.isEmpty(itemA)){
                            SrmBaseItemsEntity_HI items = new SrmBaseItemsEntity_HI();
                            BeanUtils.copyProperties(itemB, items);
                            items.setOrganizationId(instId);
                            items.setOperatorUserId(operatorUserId);
                            srmBaseItemDAO_HI.saveOrUpdate(items);
                            //srmBaseItemDAO_HI.fluch();
                        }
                    }else{
                        //物料编码
                        String itemCode = saafSequencesUtil.getDocSequences((TABLENAME + prefix).toUpperCase(),
                                prefix, 7);
                        itemB.setItemCode(itemCode);
                        //全局标识(选择，未选择)
                        itemB.setGlobalFlag("N");
                        //启用ASL表示(选择，“未选择)
                        itemB.setEnabledAsl("N");
                        //可采购(选择，未选择)
                        itemB.setPurchasingFlag("N");
                        itemB.setItemStatus("ACT");
                        itemB.setOperatorUserId(operatorUserId);
                        if(!ObjectUtils.isEmpty(itemB.getItemId())){
                            itemB.setItemId(null);
                        }
                        srmBaseItemsBDAO_HI.saveOrUpdate(itemB);
                        //srmBaseItemsBDAO_HI.fluch();
                        //分配组织
                        SrmBaseItemsEntity_HI items = new SrmBaseItemsEntity_HI();
                        BeanUtils.copyProperties(itemB, items);
                        items.setOrganizationId(instId);
                        items.setOperatorUserId(operatorUserId);
                        srmBaseItemDAO_HI.saveOrUpdate(items);
                        //srmBaseItemDAO_HI.fluch();
                    }
                    //srmBaseItemsBDAO_HI.fluch();
                    List<SrmPonAuctionItemsEntity_HI> ponItems=new ArrayList<>();
                    item.setItemId(itemB.getItemId());
                    item.setOperatorUserId(operatorUserId);
                    ponItems.add(item);
                    //去掉重复物料
                    for(int n = i+1; n < auctionItems.size(); n++){
                        SrmPonAuctionItemsEntity_HI itemC=auctionItems.get(n);
                        if(item.getItemDescription().equals(itemC.getItemDescription())&&item.getSpecification().equals(itemC.getSpecification())&&item.getUnitOfMeasure().equals(itemC.getUnitOfMeasure())&&item.getCategoryId().equals(itemC.getCategoryId())){
                            itemC.setItemId(itemB.getItemId());
                            itemC.setOperatorUserId(operatorUserId);
                            ponItems.add(itemC);
                            auctionItems.remove(n);
                            i--;
                        }
                    }
                    //回写物料ID至招标询价行
                    srmPonAuctionItemsDAO_HI.saveOrUpdateAll(ponItems);
                    //srmPonAuctionItemsDAO_HI.fluch();

                    //回写物料ID至报价行
                    if(!ObjectUtils.isEmpty(bidItems)){
                        for(int n=0;n<bidItems.size();n++){
                            SrmPonBidItemPricesEntity_HI bidItem=bidItems.get(n);
                            for(int t=0;t<ponItems.size();t++){
                                SrmPonAuctionItemsEntity_HI p=ponItems.get(t);
                                if(bidItem.getAuctionLineId().equals(p.getAuctionLineId())){
                                    bidItem.setItemId(itemB.getItemId());
                                    bidItem.setOperatorUserId(operatorUserId);
                                    srmPonBidItemPricesDAO_HI.saveOrUpdate(bidItem);
                                    //srmPonBidItemPricesDAO_HI.fluch();
                                }
                            }
                        }
                    }
                }
            }
        }

        return SToolUtils.convertResultJSONObj("S", "成功生成物料主数据", 0, null);
    }


}
