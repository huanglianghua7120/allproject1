package saaf.common.fmw.base.model.inter.server;

import com.yhg.hibernate.core.paging.Pagination;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseItemsBEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafLookup;
import saaf.common.fmw.base.model.inter.ISrmBaseItem;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.base.model.entities.SrmBaseItemsEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseItemsEntity_HI_RO;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.alibaba.fastjson.JSONArray;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;
import saaf.common.fmw.utils.SrmUtils;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：物料
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmBaseItemServer")
public class SrmBaseItemServer implements ISrmBaseItem {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseItemServer.class);

    @Autowired
    private ViewObject<SrmBaseItemsEntity_HI> srmBaseItemDAO_HI;

    @Autowired
    private ViewObject<SrmBaseItemsBEntity_HI> srmBaseItemBDAO_HI;

    @Autowired
    private BaseViewObject<SrmBaseItemsEntity_HI_RO> srmBaseItemsDAO_HI_RO;

    @Autowired
    private ViewObject<SrmIntfLogsEntity_HI> srmIntfLogsDAO_HI;//日志

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值


    private final String NEW = "NEW";

    private final String INACT = "INACT";

    private final String ACT = "ACT";

    public SrmBaseItemServer() {
        super();
    }

    /**
     * 查看物料（招标模块）——分页
     *
     * @param jsonParams
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
    public Pagination<SrmBaseItemsEntity_HI_RO> findSrmBaseItemList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        LOGGER.info("参数：" + jsonParams.toString());
        Map<String, Object> map = new HashMap();
        //Integer receiveToOrganizationId = jsonParams.getInteger("receiveToOrganizationId");
        StringBuffer sb = new StringBuffer();
        sb.append(SrmBaseItemsEntity_HI_RO.QUERY_PON_BASE_ITEM_SQL);
        /*if (null != receiveToOrganizationId && !"".equals(receiveToOrganizationId)) {
            sb.append(" AND EXISTS\n"
                    + "  (SELECT 1 FROM saaf_institution t3\n"
                    + "  WHERE t3.inst_id = t.organization_id\n"
                    + "    AND t3.inst_type = 'ORGANIZATION'\n"
                    + "    AND t3.inst_id =" + receiveToOrganizationId + ") ");
        }*/
        // 查询过滤条件
        SaafToolUtils.parperParam(jsonParams, "t.item_code", "itemCode", sb, map, "like");
        SaafToolUtils.parperParam(jsonParams, "t.item_name", "itemName", sb, map, "like");
        SaafToolUtils.parperParam(jsonParams, "sbc.full_category_code", "fullCategoryCode", sb, map, "like");
        SaafToolUtils.parperParam(jsonParams, "sbc.full_category_name", "fullCategoryName", sb, map, "like");
        SaafToolUtils.parperParam(jsonParams, "t.item_code", "item_Code", sb, map, "=");//物料编码精准查询
        SaafToolUtils.parperParam(jsonParams, "t.item_status", "itemStatus", sb, map, "=");
        sb.append(" and t.item_id in (SELECT Bi.Item_Id\n" +
                "  FROM Srm_Base_Items Bi\n" +
                " WHERE Bi.Organization_Id IN\n" +
                "       (SELECT Sai.Inst_Id\n" +
                "          FROM Saaf_Institution Sai\n" +
                "         WHERE Sai.Parent_Inst_Id = " +jsonParams.getInteger("parentInstId")+
                "           AND Sai.Inst_Type = 'ORGANIZATION') "+
                "        AND Bi.Category_Id IN (" +getCategoryId(jsonParams.getInteger("varUserId"))+"))");
        sb.append(" ");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmBaseItemsEntity_HI_RO> baseItemList = srmBaseItemsDAO_HI_RO.findPagination(sb.toString(),countSql, map, pageIndex, pageRows);
        return baseItemList;
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
     * 点击菜单查询物料数据
     *
     * @param jsonParams
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
    public Pagination<SrmBaseItemsEntity_HI_RO> findMatterDataManageList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        LOGGER.info("参数：" + jsonParams.toString());
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer(SrmBaseItemsEntity_HI_RO.QUERY_SRM_BASE_ITME_SQL);
        StringBuffer appendString = new StringBuffer();
        // 查询过滤条件
        SaafToolUtils.parperParam(jsonParams, "sbi.item_id", "itemId", appendString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "ins.inst_code", "instCode", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_code", "itemCode", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "bc.category_code", "categoryCode", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "ins.inst_name", "instName", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_name", "itemName", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "bc.category_name", "categoryName", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "se.employee_name", "employeeNameSearch", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_status", "itemStatus", appendString, queryParamMap, "=");

        queryString.append(appendString);
        /*queryString.append(" sbi.Organization_Id IN\n" +
                "       (SELECT Sai.Inst_Id\n" +
                "          FROM Saaf_Institution Sai\n" +
                "         WHERE Sai.Parent_Inst_Id = " +jsonParams.getInteger("parentInstId")+
                "           AND Sai.Inst_Type = 'ORGANIZATION') "+
                "        AND Bi.Category_Id IN (" +getCategoryId(jsonParams.getInteger("varUserId"))+")");*/

        queryString.append(" and sbi.Organization_Id in (SELECT distinct (sua.inst_id) Organization_Id\n" +
                "                               FROM saaf_user_access_orgs sua,\n" +
                "                                    saaf_institution      si,\n" +
                "                                    saaf_users            su\n" +
                "                              WHERE sua.user_id = su.user_id\n" +
                "                                AND sua.inst_id = si.inst_id\n" +
                "                                and sua.platform_code = 'SAAF'\n" +
                "                                and si.inst_type='ORGANIZATION'\n" +
                "                                and sua.user_id = "+jsonParams.getInteger("varUserId")+") "+
                "      AND sbi.Category_Id IN  (" +getCategoryId(jsonParams.getInteger("varUserId"))+")");
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" order by sbi.item_code desc, ins.inst_code asc");
        Pagination<SrmBaseItemsEntity_HI_RO> baseItemList = srmBaseItemsDAO_HI_RO.findPagination(queryString,countSql, queryParamMap, pageIndex, pageRows);
        return baseItemList;
    }

    /**
     * 根据物料ID查询物料数据
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmBaseItemsEntity_HI_RO> findMatterDataManageListByIdList(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());
        //item_id串
        String itemIdStr = jsonParams.getString("itemIdStr");
        Integer itemId = jsonParams.getInteger("itemId");

        String queryString = SrmBaseItemsEntity_HI_RO.QUERY_SRM_BASE_ITME_SQL;
        // 查询过滤条件
        if (null != itemId){
            queryString = queryString + " AND sbi.item_id = " + itemId;
        }
        if (null != itemIdStr && !"".equals(itemIdStr)) {
            queryString = queryString + " AND sbi.item_id IN (" + itemIdStr + ") ";
        }
        List<SrmBaseItemsEntity_HI_RO> list = srmBaseItemsDAO_HI_RO.findList(queryString);
        return list;
    }

    /**
     * 根据物料ID查询组织分配情况
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmBaseItemsEntity_HI_RO> findMatterDataManageOrgList(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());

        Integer itemId = jsonParams.getInteger("itemId");

        String queryString = SrmBaseItemsEntity_HI_RO.QUERY_ORG_ITEM_SQL;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemId", itemId);
        /*queryString.append(" and t.item_id in (SELECT Bi.Item_Id\n" +
                "  FROM Srm_Base_Items Bi\n" +
                " WHERE Bi.Organization_Id IN\n" +
                "       (SELECT Sai.Inst_Id\n" +
                "          FROM Saaf_Institution Sai\n" +
                "         WHERE Sai.Parent_Inst_Id = " +jsonParams.getInteger("parentInstId")+
                "           AND Sai.Inst_Type = 'ORGANIZATION') "+
                "        AND Bi.Category_Id IN (" +getCategoryId(jsonParams.getInteger("varUserId"))+"))");*/
        List<SrmBaseItemsEntity_HI_RO> list = srmBaseItemsDAO_HI_RO.findList(queryString, map);
        return list;
    }

    /**
     * 保存物料
     *
     * @param
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateMatterDataManage(JSONArray jsonArray, int operatorUserId) {
        LOGGER.info("参数：" + jsonArray.toString());
        try {
            SrmBaseItemsEntity_HI srmBaseItemEntity = null;
            List<SrmBaseItemsEntity_HI> srmBaseItemlist = JSON.parseArray(jsonArray.toJSONString(), SrmBaseItemsEntity_HI.class);//将json数据转成list的实体
            List<SrmBaseItemsEntity_HI> newSrmBaseItemList = new ArrayList<SrmBaseItemsEntity_HI>();
            if (null != srmBaseItemlist && srmBaseItemlist.size() > 0) {
                for (SrmBaseItemsEntity_HI srmBaseItemsEntity : srmBaseItemlist) {
                    if (srmBaseItemsEntity.getItemId() != null && srmBaseItemsEntity.getItemId() > 0) {
                        srmBaseItemDAO_HI.executeSqlUpdate("UPDATE srm_base_items t SET t.`invalid_date` = " + "NOW()" + " WHERE t.`item_id` = " + srmBaseItemsEntity.getItemId());
                    }
                }
                for (SrmBaseItemsEntity_HI items : srmBaseItemlist) {
                    srmBaseItemEntity = new SrmBaseItemsEntity_HI();
                    if (items.getOrganizationId() != null && items.getOrganizationId() > 0 && items.getItemId() != null && items.getItemId() > 0) {
                        srmBaseItemEntity.setItemId(items.getItemId());
                        StringBuffer appendString = new StringBuffer();
                        Map<String, Object> queryParamMap = new HashMap<String, Object>();
                        appendString.append(SrmBaseItemsEntity_HI_RO.QUERY_ITEM);
                        queryParamMap.put("organizationId", items.getOrganizationId());
                        queryParamMap.put("itemId", items.getItemId());
                        System.out.println("appendString==" + appendString);
                        List<SrmBaseItemsEntity_HI_RO> baseItemList = srmBaseItemsDAO_HI_RO.findList(appendString.toString(), queryParamMap);

                        if (baseItemList != null && baseItemList.size() > 0) {
                            for (SrmBaseItemsEntity_HI_RO entity : baseItemList) {
                                if (entity.getInvalidDate() != null && !"".equals(entity.getInvalidDate())) {
                                    srmBaseItemDAO_HI.executeSqlUpdate("UPDATE srm_base_items t SET t.`invalid_date` = NULL, t.item_status = 'ACT' WHERE t.`item_id` = " + items.getItemId() + " AND t.`organization_id` = " + items.getOrganizationId());
                                }
                            }
                        } else {
                            StringBuffer append = new StringBuffer("SELECT * FROM `srm_base_items` t WHERE t.`item_id` = :itemId ");
                            Map<String, Object> queryMap = new HashMap<String, Object>();
                            queryMap.put("itemId", items.getItemId());
                            SrmBaseItemsEntity_HI_RO srmBaseItem = new SrmBaseItemsEntity_HI_RO();
                            List<SrmBaseItemsEntity_HI_RO> ff = srmBaseItemsDAO_HI_RO.findList(append, queryMap);
                            int a = ff.size();
                            for (int i = 0; i < ff.size(); i++) {
                                srmBaseItem = ff.get(0);
                            }
                            srmBaseItem.setOrganizationId(items.getOrganizationId());
                            srmBaseItem.setOperatorUserId(operatorUserId);
                            SrmBaseItemsEntity_HI baseItem = new SrmBaseItemsEntity_HI();
                            BeanUtils.copyProperties(srmBaseItem, baseItem);
                            srmBaseItemDAO_HI.save(baseItem);
                        }
                    } else {
                        if ((null != items.getItemId())) {//修改
                            srmBaseItemEntity.setLastUpdatedBy(operatorUserId);//更新人
                            srmBaseItemEntity.setLastUpdateDate(new Date());//更新时间
                        } else {//新增
                            StringBuffer queryString = new StringBuffer();
                            Map<String, Object> queryParamMap = new HashMap<String, Object>();
                            queryString.append("SELECT MAX(item_id) itemId FROM `srm_base_items` ");
                            SrmBaseItemsEntity_HI_RO srmBaseItemsEntity = srmBaseItemsDAO_HI_RO.findList(queryString, queryParamMap).get(0);
                            srmBaseItemEntity.setItemId(srmBaseItemsEntity.getItemId() + 1);
                            srmBaseItemEntity.setCreationDate(new Date());//创建时间
                            srmBaseItemEntity.setCreatedBy(operatorUserId);//创建人
                            srmBaseItemEntity.setLastUpdatedBy(operatorUserId);//更新人
                            srmBaseItemEntity.setLastUpdateDate(new Date());//更新时间
                        }
                        //全局标识(选择，未选择)
                        if (null != items.getGlobalFlag() || !"".equals(items.getGlobalFlag())) {
                            srmBaseItemEntity.setGlobalFlag("Y");
                        } else {
                            srmBaseItemEntity.setGlobalFlag("N");
                        }
                        //启用ASL表示(选择，“未选择)
                        if (null != items.getEnabledAsl() || !"".equals(items.getEnabledAsl())) {
                            srmBaseItemEntity.setEnabledAsl("Y");
                        } else {
                            srmBaseItemEntity.setEnabledAsl("N");
                        }
                        srmBaseItemEntity.setCategoryId(items.getCategoryId());
                        srmBaseItemEntity.setItemCode(items.getItemCode());
                        srmBaseItemEntity.setItemName(items.getItemName());
                        srmBaseItemEntity.setItemDescription(items.getItemName());
                        srmBaseItemEntity.setUomCode(items.getUomCode());
                        srmBaseItemEntity.setItemStatus("ACT");//物料状态为“生效”
                        srmBaseItemEntity.setPurchasingFlag(items.getPurchasingFlag());
                        srmBaseItemEntity.setAgentId(items.getAgentId());
                        srmBaseItemEntity.setOrganizationId(items.getOrganizationId());//组织id
                        srmBaseItemEntity.setPurchaseCycle(items.getPurchaseCycle());
                        srmBaseItemEntity.setPurchasingLeadTime(items.getPurchasingLeadTime());
                        srmBaseItemEntity.setMinPacking(items.getMinPacking());
                        srmBaseItemEntity.setImageId(items.getImageId());
                        srmBaseItemEntity.setOperatorUserId(operatorUserId);
                        if (items.getOrganizationId() != null && items.getOrganizationId() > 0) {
                            if (items.getItemId() != null && items.getItemId() > 0) {
                                srmBaseItemDAO_HI.saveOrUpdate(srmBaseItemEntity);
                            } else {
                                srmBaseItemDAO_HI.save(srmBaseItemEntity);
                            }
                        } else {
                            return SToolUtils.convertResultJSONObj("E", "组织不能为空，请选择组织！", 0, null);
                        }
                        newSrmBaseItemList.add(srmBaseItemEntity);
                    }
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "获取数据异常！", 0, null);
            }
            return SToolUtils.convertResultJSONObj("S", "保存成功！", 1, null);
        } catch (DuplicateKeyException e) {
            return SToolUtils.convertResultJSONObj("E", "不能新增两条相同的库存组织！", 0, null);
        }

    }

    /**
     * 保存物料
     *
     * @param
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateOneMatterData(JSONObject jsonParams, int operatorUserId) {
        LOGGER.info("参数：" + jsonParams.toString());
        try {
            SrmBaseItemsEntity_HI srmBaseItemEntity = null;
            SrmBaseItemsBEntity_HI srmBaseItemBEntity = null;
            SrmBaseItemsEntity_HI items = JSON.parseObject(jsonParams.toJSONString(), SrmBaseItemsEntity_HI.class);//将json数据转成实体
            if (null == items.getOrganizationId() || "".equals(items.getOperatorUserId())){
                return SToolUtils.convertResultJSONObj("E", "组织不能为空，请选择组织！", 0, null);
            }
            String instName = jsonParams.getString("instName");
            if (null == instName || "".equals(instName)){
                return SToolUtils.convertResultJSONObj("E", "组织名称不能为空！", 0, null);
            }
            if (null == items.getCategoryId() || "".equals(items.getCategoryId())){
                return SToolUtils.convertResultJSONObj("E", "品类编码不能为空，请选择品类编码！", 0, null);
            }
            if (null == items.getItemName() || "".equals(items.getItemName())){
                return SToolUtils.convertResultJSONObj("E", "物料描述不能为空，请填写物料描述！", 0, null);
            }
            if (null == items.getUomCode() || "".equals(items.getUomCode())){
                return SToolUtils.convertResultJSONObj("E", "计量单位不能为空，请选择计量单位！", 0, null);
            }
            if ((null != items.getItemId())) {//修改
                //分配表
                Map<String, Object> queryMap = new HashMap<String, Object>();
                queryMap.put("itemId", items.getItemId());
                queryMap.put("organizationId", items.getOrganizationId());
                List<SrmBaseItemsEntity_HI> list = srmBaseItemDAO_HI.findByProperty(queryMap);
                if (null != list && list.size() >0){
                    srmBaseItemEntity = list.get(0);
                    srmBaseItemEntity.setLastUpdatedBy(operatorUserId);//更新人
                    srmBaseItemEntity.setLastUpdateDate(new Date());//更新时间
                } else {
                    throw new RuntimeException();
                }
                //基表
                List<SrmBaseItemsBEntity_HI> itemBList = srmBaseItemBDAO_HI.findByProperty("itemId", items.getItemId());
                if (null != itemBList && itemBList.size() >0){
                    srmBaseItemBEntity = itemBList.get(0);
                    srmBaseItemBEntity.setLastUpdatedBy(operatorUserId);//更新人
                    srmBaseItemBEntity.setLastUpdateDate(new Date());//更新时间
                } else {
                    throw new RuntimeException();
                }
            } else {//新增
                //分配表
                srmBaseItemEntity = new SrmBaseItemsEntity_HI();
                StringBuffer queryString = new StringBuffer();
                Map<String, Object> queryParamMap = new HashMap<String, Object>();
                queryString.append("SELECT MAX(item_id) itemId FROM srm_base_items ");
                SrmBaseItemsEntity_HI_RO srmBaseItemsEntity = srmBaseItemsDAO_HI_RO.findList(queryString, queryParamMap).get(0);
                srmBaseItemEntity.setItemId(srmBaseItemsEntity.getItemId() + 1);
                //物料编码
                String prefix = null;
                if (instName.indexOf("深圳") != -1){
                    prefix = "SZ";
                }else if (instName.indexOf("无锡") != -1){
                    prefix = "WX";
                }else if (instName.indexOf("南通") != -1){
                    prefix = "NT";
                }else{
                    throw new RuntimeException("组织无效！");
                }
                String itemCode = saafSequencesUtil.getDocSequences("srm_base_items".toUpperCase(),
                        prefix, 7);
                srmBaseItemEntity.setItemCode(itemCode);
                srmBaseItemEntity.setCreationDate(new Date());//创建时间
                srmBaseItemEntity.setCreatedBy(operatorUserId);//创建人
                srmBaseItemEntity.setLastUpdatedBy(operatorUserId);//更新人
                srmBaseItemEntity.setLastUpdateDate(new Date());//更新时间
                //基表
                srmBaseItemBEntity = new SrmBaseItemsBEntity_HI();
                srmBaseItemBEntity.setItemId(srmBaseItemEntity.getItemId());
                srmBaseItemBEntity.setItemCode(srmBaseItemEntity.getItemCode());
                srmBaseItemBEntity.setCreationDate(srmBaseItemEntity.getCreationDate());
                srmBaseItemBEntity.setCreatedBy(srmBaseItemEntity.getCreatedBy());
                srmBaseItemBEntity.setLastUpdatedBy(srmBaseItemEntity.getLastUpdatedBy());
                srmBaseItemBEntity.setLastUpdateDate(srmBaseItemEntity.getLastUpdateDate());
            }
            //全局标识(选择，未选择)
            if (null != items.getGlobalFlag() || !"".equals(items.getGlobalFlag())) {
                srmBaseItemEntity.setGlobalFlag("Y");
            } else {
                srmBaseItemEntity.setGlobalFlag("N");
            }
            //启用ASL表示(选择，“未选择)
            if (null != items.getEnabledAsl() || !"".equals(items.getEnabledAsl())) {
                srmBaseItemEntity.setEnabledAsl("Y");
            } else {
                srmBaseItemEntity.setEnabledAsl("N");
            }
            srmBaseItemEntity.setCategoryId(items.getCategoryId());
            srmBaseItemEntity.setItemName(items.getItemName());
            srmBaseItemEntity.setItemDescription(items.getItemName());
            srmBaseItemEntity.setUomCode(items.getUomCode());
            srmBaseItemEntity.setItemStatus("ACT");//物料状态为“生效”
            srmBaseItemEntity.setPurchasingFlag(items.getPurchasingFlag());
            srmBaseItemEntity.setAgentId(items.getAgentId());
            srmBaseItemEntity.setOrganizationId(items.getOrganizationId());//组织id
            srmBaseItemEntity.setPurchaseCycle(items.getPurchaseCycle());
            srmBaseItemEntity.setPurchasingLeadTime(items.getPurchasingLeadTime());
            srmBaseItemEntity.setMinPacking(items.getMinPacking());
            srmBaseItemEntity.setImageId(items.getImageId());
            srmBaseItemEntity.setCost(items.getCost());
            srmBaseItemEntity.setSpecification(items.getSpecification());
            srmBaseItemEntity.setOperatorUserId(operatorUserId);
            //基表
            srmBaseItemBEntity.setGlobalFlag(srmBaseItemEntity.getGlobalFlag());
            srmBaseItemBEntity.setEnabledAsl(srmBaseItemEntity.getEnabledAsl());
            srmBaseItemBEntity.setCategoryId(srmBaseItemEntity.getCategoryId());
            srmBaseItemBEntity.setItemName(srmBaseItemEntity.getItemName());
            srmBaseItemBEntity.setItemDescription(srmBaseItemEntity.getItemName());
            srmBaseItemBEntity.setUomCode(srmBaseItemEntity.getUomCode());
            srmBaseItemBEntity.setItemStatus(srmBaseItemEntity.getItemStatus());
            srmBaseItemBEntity.setPurchasingFlag(srmBaseItemEntity.getPurchasingFlag());
            srmBaseItemBEntity.setAgentId(srmBaseItemEntity.getAgentId());
            srmBaseItemBEntity.setPurchaseCycle(srmBaseItemEntity.getPurchaseCycle());
            srmBaseItemBEntity.setPurchasingLeadTime(srmBaseItemEntity.getPurchasingLeadTime());
            srmBaseItemBEntity.setMinPacking(srmBaseItemEntity.getMinPacking());
            srmBaseItemBEntity.setImageId(srmBaseItemEntity.getImageId());
            srmBaseItemBEntity.setCost(srmBaseItemEntity.getCost());
            srmBaseItemBEntity.setSpecification(srmBaseItemEntity.getSpecification());
            srmBaseItemBEntity.setOperatorUserId(operatorUserId);

            if (items.getItemId() != null && items.getItemId() > 0) {
                srmBaseItemDAO_HI.update(srmBaseItemEntity);
                srmBaseItemBDAO_HI.update(srmBaseItemBEntity);
            } else {
                srmBaseItemDAO_HI.save(srmBaseItemEntity);
                srmBaseItemBDAO_HI.save(srmBaseItemBEntity);
            }

            return SToolUtils.convertResultJSONObj("S", "保存成功！", 1, srmBaseItemEntity.getItemId());
        } catch (DuplicateKeyException e) {
            return SToolUtils.convertResultJSONObj("E", "不能新增两条相同的库存组织！", 0, null);
        } catch (Exception e){
            LOGGER.error("保存物料失败！" + e);
            return SToolUtils.convertResultJSONObj("E", "保存物料失败:" + e.getMessage(), 0, null);
        }

    }

    /**
     * 保存物料
     *
     * @param
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public void saveMatterData(JSONObject paramJSON, SrmBaseItemsBEntity_HI srmBaseItemsBEntityHi) throws Exception {
        JSONArray orgList = paramJSON.getJSONArray("orgList");
        Integer userId = paramJSON.getInteger("varUserId");
        if(null != orgList) {
            for (int i = 0; i < orgList.size(); i++) {
                JSONObject org = orgList.getJSONObject(i);
                if ("Y".equals(org.getString("selectFlag"))) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("item_id", srmBaseItemsBEntityHi.getItemId());
                    map.put("organization_id", org.getInteger("instId"));
                    List<SrmBaseItemsEntity_HI> itemList = srmBaseItemDAO_HI.findByProperty(map);
                    SrmBaseItemsEntity_HI item = null;
                    if (null == itemList || itemList.isEmpty()) {//新增
                        item = new SrmBaseItemsEntity_HI();
                        item.setItemId(srmBaseItemsBEntityHi.getItemId());
                        item.setOrganizationId(org.getInteger("instId"));
                       // item.setItemStatus(ACT);
                    } else {//修改
                        item = itemList.get(0);
                    }
                    item.setSpecification(srmBaseItemsBEntityHi.getSpecification());
                    item.setCost(srmBaseItemsBEntityHi.getCost());
                    item.setEnabledAsl(srmBaseItemsBEntityHi.getEnabledAsl());
                    item.setGlobalFlag(srmBaseItemsBEntityHi.getGlobalFlag());
                    item.setAgentId(srmBaseItemsBEntityHi.getAgentId());
                    item.setItemCode(srmBaseItemsBEntityHi.getItemCode());
                    item.setItemDescription(srmBaseItemsBEntityHi.getItemDescription());
                    item.setItemName(srmBaseItemsBEntityHi.getItemName());
                    item.setPurchasingFlag(srmBaseItemsBEntityHi.getPurchasingFlag());
                    item.setCategoryId(srmBaseItemsBEntityHi.getCategoryId());
                    item.setMinPacking(srmBaseItemsBEntityHi.getMinPacking());
                    item.setUomCode(srmBaseItemsBEntityHi.getUomCode());
                    item.setRegion(srmBaseItemsBEntityHi.getRegion());
                    item.setItemStatus(srmBaseItemsBEntityHi.getItemStatus());
                    item.setOperatorUserId(userId);
                    srmBaseItemDAO_HI.saveOrUpdate(item);
                    srmBaseItemDAO_HI.fluch();

                }else if ("N".equals(org.getString("selectFlag"))) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("item_id", srmBaseItemsBEntityHi.getItemId());
                    map.put("organization_id", org.getInteger("instId"));
                    List<SrmBaseItemsEntity_HI> itemList = srmBaseItemDAO_HI.findByProperty(map);
                    SrmBaseItemsEntity_HI item = null;
                    if (null != itemList && !itemList.isEmpty()) {
                        item = itemList.get(0);
                        item.setItemStatus("INACT");
                        item.setOperatorUserId(userId);
                        srmBaseItemDAO_HI.saveOrUpdate(item);
                        srmBaseItemDAO_HI.fluch();
                        
                    }
                }
            }
        }
    }

    /**
     * 物料-失效
     *
     * @param
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateInvalidBaseItem(JSONArray jsonArray, int operatorUserId) throws Exception {
        LOGGER.info("失效信息，参数：" + jsonArray.toString());
        List<SrmBaseItemsEntity_HI> srmBaseItemlist = JSON.parseArray(jsonArray.toJSONString(), SrmBaseItemsEntity_HI.class);
        try {
            if (null != srmBaseItemlist && srmBaseItemlist.size() > 0) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    if (!"Y".equals(object.getString("enabledFlag"))) {
                        return SToolUtils.convertResultJSONObj("E", "请选择需要失效的行！", 0, null);
                    } else {
                        if ("ACT".equals(object.getString("itemStatus"))) {
                            if (object != null && object.getInteger("itemId") > 0) {
                                Map<String, Object> queryParamMap = new HashMap<>();
                                StringBuffer appendString = new StringBuffer();
                                appendString.append("UPDATE srm_base_items t SET t.`invalid_date` = NOW(),t.item_status = 'INACT',t.last_updated_by = ");
                                appendString.append(operatorUserId);
                                appendString.append(" ,t.last_update_date= NOW()");
                                appendString.append(" ,t.last_updated_by=");
                                appendString.append(operatorUserId);
                                appendString.append(" WHERE t.`item_id` = ");
                                appendString.append(object.getInteger("itemId"));
                                if (object.getInteger("organizationId") > 0) {
                                    appendString.append(" AND t.`organization_id` = ");
                                    appendString.append(object.getInteger("organizationId"));
                                }
                                srmBaseItemDAO_HI.executeSqlUpdate(appendString.toString());
                            }
                        } else {
                            String identifying = "所选中的第：" + (++i) + " 行不是生效状态，请重新选择！";
                            return SToolUtils.convertResultJSONObj("E", identifying, 0, null);
                        }
                    }
                }
            }
            return SToolUtils.convertResultJSONObj("S", "生效成功！", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：失效组织物料
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
    /*@Override
    public void updateInvalidBaseItems(JSONObject jsonParams) throws Exception {
        try {
            Integer itemId = jsonParams.getInteger("itemId");
            Integer userId = jsonParams.getInteger("varUserId");
            Date date = new Date();
            if (null != jsonParams){
                List<SrmBaseItemsEntity_HI> oldList = srmBaseItemDAO_HI.findByProperty("itemId", itemId);
                List<SrmBaseItemsEntity_HI> newList = new ArrayList<>();
                for (int i = 0;i < oldList.size(); i ++){
                    SrmBaseItemsEntity_HI item = oldList.get(i);
                    item.setItemStatus("INACT");
                    item.setInvalidDate(date);
                    item.setOperatorUserId(userId);
                    newList.add(item);
                }
                if (null != oldList && oldList.size() > 0){
                    srmBaseItemDAO_HI.saveOrUpdateAll(newList);
                    srmBaseItemDAO_HI.fluch();
                }
            }
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
    public JSONObject updateValidOrInvalidBaseItems(JSONObject jsonParams) throws Exception {
        JSONArray array = jsonParams.getJSONArray("list");
        Integer userId = jsonParams.getInteger("varUserId");
        String action = jsonParams.getString("action");
        String msg = null;
        if (ACT.equals(action)) {
            msg = "生效";
        }else if(INACT.equals(action)){
            msg = "失效";
        }else {
            return SToolUtils.convertResultJSONObj("E", "参数错误！", 0, null);
        }
        try {
            List<SrmBaseItemsEntity_HI> srmBaseItemList = JSON.parseArray(array.toJSONString(), SrmBaseItemsEntity_HI.class);
            List<SrmBaseItemsEntity_HI> updateList = new ArrayList<>();
            Date date = new Date();
            if (null != srmBaseItemList && srmBaseItemList.size() > 0) {
                Map<String, Object> map = new HashMap<>();
                List itemIdList = new ArrayList();
                for(SrmBaseItemsEntity_HI item : srmBaseItemList){
                    //查找组织物料
                    map.put("itemId", item.getItemId());
                    itemIdList.add(item.getItemId());
                    map.put("organizationId", item.getOrganizationId());
                    List<SrmBaseItemsEntity_HI> list = srmBaseItemDAO_HI.findByProperty(map);
                    if (null != list && list.size() == 1){
                        SrmBaseItemsEntity_HI baseItemsEntityHi = list.get(0);
                        //生效或失效
                        if (INACT.equals(action)){
                            if ("ACT".equals(baseItemsEntityHi.getItemStatus())) {
                                baseItemsEntityHi.setItemStatus(INACT);
                                baseItemsEntityHi.setInvalidDate(date);
                            }else {
                                return SToolUtils.convertResultJSONObj("E", "失效失败！物料" + baseItemsEntityHi.getItemCode() + "状态不是“生效”，无法进行失效", 1, null);
                            }
                        }else if (ACT.equals(action)){
                            if ("NEW".equals(baseItemsEntityHi.getItemStatus())) {
                                baseItemsEntityHi.setItemStatus(ACT);
                                baseItemsEntityHi.setInvalidDate(null);
                            }else {
                                return SToolUtils.convertResultJSONObj("E", "生效失败！物料" + baseItemsEntityHi.getItemCode() + "状态不是“拟定”，无法进行生效", 1, null);
                            }
                        }
                        baseItemsEntityHi.setOperatorUserId(userId);
                        updateList.add(baseItemsEntityHi);
                    }
                    map.clear();
                }
                srmBaseItemDAO_HI.updateAll(updateList);
                srmBaseItemDAO_HI.fluch();

                //去重
                /*itemIdList = SaafToolUtils.removeDuplicationByHashSet(itemIdList);
                //对主物料进行状态控制
                if (null != itemIdList && itemIdList.size() > 0){
                    for (int i = 0;i < itemIdList.size();i ++){
                        Integer itemId = (Integer)itemIdList.get(i);
                        SrmBaseItemsBEntity_HI baseItem = srmBaseItemBDAO_HI.getById(itemId);
                        if (ACT.equals(action)){
                            baseItem.setItemStatus(ACT);
                            baseItem.setInvalidDate(null);
                            baseItem.setOperatorUserId(userId);
                            srmBaseItemBDAO_HI.update(baseItem);
                            srmBaseItemBDAO_HI.fluch();
                        }else if (INACT.equals(action)){
                            //如果所有组织物料都非生效状态，则设为失效
                            String queryString = SrmBaseItemsEntity_HI_RO.QUERY_ITEMB_STATUS_SQL;
                            Map<String, Object> paramsMap = new HashMap<>();
                            paramsMap.put("itemId", itemId);
                            List<SrmBaseItemsEntity_HI_RO> list = srmBaseItemsDAO_HI_RO.findList(queryString, paramsMap);
                            if (null != list && list.size() == 1){
                                if (0 == list.get(0).getCount().intValue() ){
                                    baseItem.setItemStatus(INACT);
                                    baseItem.setInvalidDate(date);
                                    baseItem.setOperatorUserId(userId);
                                    srmBaseItemBDAO_HI.update(baseItem);
                                    srmBaseItemBDAO_HI.fluch();
                                }
                            }
                        }

                    }
                }*/
            }
            return SToolUtils.convertResultJSONObj("S", msg + "成功！", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：删除组织物料
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
    public JSONObject deleteBaseItems(JSONObject jsonParams) throws Exception {
        JSONArray array = jsonParams.getJSONArray("list");
        try {
            List<SrmBaseItemsEntity_HI> srmBaseItemList = JSON.parseArray(array.toJSONString(), SrmBaseItemsEntity_HI.class);
            List<SrmBaseItemsEntity_HI> deleteList = new ArrayList<>();
            if (null != srmBaseItemList && srmBaseItemList.size() > 0) {
                Map<String, Object> map = new HashMap<>();
                for(SrmBaseItemsEntity_HI item : srmBaseItemList){
                    //查找组织物料
                    map.put("itemId", item.getItemId());
                    map.put("organizationId", item.getOrganizationId());
                    List<SrmBaseItemsEntity_HI> list = srmBaseItemDAO_HI.findByProperty(map);
                    if (null != list && list.size() == 1){
                        SrmBaseItemsEntity_HI baseItemsEntityHi = list.get(0);
                        if (NEW.equals(baseItemsEntityHi.getItemStatus())){
                            deleteList.add(baseItemsEntityHi);
                        }else {
                            return SToolUtils.convertResultJSONObj("E",  "物料:" + baseItemsEntityHi.getItemCode() + ",状态不是拟定，请重新查询后操作！", 1, null);
                        }
                    }
                    map.clear();
                }
            }
            srmBaseItemDAO_HI.deleteAll(deleteList);
            srmBaseItemDAO_HI.fluch();
            return SToolUtils.convertResultJSONObj("S",  "删除成功！", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：保存/修改物料数据
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * consignmentFlag  是否寄售(Y/N)  VARCHAR2  N
     * itemId  物料ID  NUMBER  Y
     * itemCode  物料编码  VARCHAR2  Y
     * itemName  物料名称  VARCHAR2  Y
     * itemDescription  物料说明  VARCHAR2  N
     * globalFlag  全局标识  VARCHAR2  N
     * enabledAsl  启用ASL  VARCHAR2  N
     * organizationId  库存组织ID  NUMBER  Y
     * uomCode  计量单位  VARCHAR2  N
     * itemStatus  物料状态  VARCHAR2  N
     * purchasingFlag  可采购标识  VARCHAR2  N
     * agentId  采购员ID  NUMBER  N
     * categoryId  采购分类ID  NUMBER  N
     * purchaseCycle  采购周期  VARCHAR2  N
     * purchasingLeadTime  采购提前期  NUMBER  N
     * minPacking  最小包装量  NUMBER  N
     * benchmarkPrice  基准价  NUMBER  N
     * imageId  图片ID  NUMBER  N
     * invalidDate  失效时间  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * minPoQuantity  最小采购量  NUMBER  N
     * purchaseStatus    VARCHAR2  N
     * modelCode  型号编码  VARCHAR2  N
     * modelName  物料型号  VARCHAR2  N
     * modelStorageDuration    DATE  N
     * temperatureUpper    NUMBER  N
     * temperatureLower    NUMBER  N
     * humidityUpper    NUMBER  N
     * humidityLower    NUMBER  N
     * ulType    VARCHAR2  N
     * copper    VARCHAR2  N
     * sizec    NUMBER  N
     * ppLength    NUMBER  N
     * realitySize    NUMBER  N
     * moqc    NUMBER  N
     * itemRank    VARCHAR2  N
     * conversionRatio  转换比例  VARCHAR2  N
     * inventoryCode  库存单位  VARCHAR2  N
     * isTest  是否检验  VARCHAR2  N
     * isTighten  是否加严  VARCHAR2  N
     * itemPlanWay  物料计划方式  VARCHAR2  N
     * copperFoilType  铜箔类型  VARCHAR2  N
     * standardSize  标准尺寸  VARCHAR2  N
     * moqOrderQuantity  MOQ（起订量）  NUMBER  N
     * itemId  物料ID  NUMBER  Y
     * itemCode  物料编码  VARCHAR2  Y
     * itemName  物料名称  VARCHAR2  Y
     * itemDescription  物料说明  VARCHAR2  N
     * globalFlag  全局标识  VARCHAR2  N
     * enabledAsl  启用ASL  VARCHAR2  N
     * organizationId  库存组织ID  NUMBER  Y
     * uomCode  计量单位  VARCHAR2  N
     * itemStatus  物料状态  VARCHAR2  N
     * purchasingFlag  可采购标识  VARCHAR2  N
     * agentId  采购员ID  NUMBER  N
     * categoryId  采购分类ID  NUMBER  N
     * purchaseCycle  采购周期  VARCHAR2  N
     * purchasingLeadTime  采购提前期  NUMBER  N
     * minPacking  最小包装量  NUMBER  N
     * benchmarkPrice  基准价  NUMBER  N
     * imageId  图片ID  NUMBER  N
     * invalidDate  失效时间  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * cost  成本  NUMBER  N
     * specification  规格型号  VARCHAR2  N
     * region  组织区域  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Override
    public JSONObject updateMatterDataManageExternal(JSONObject jsonParams, int userId) throws Exception {
        LOGGER.info("参数：" + jsonParams.toString());
        JSONArray jsonArray = jsonParams.getJSONArray("array");
        SrmBaseItemsEntity_HI srmBaseItemEntity = null;
        JSONObject jsonData = new JSONObject();  //最终结果的返回
        try {
            List<SrmBaseItemsEntity_HI> srmBaseItemlist = JSON.parseArray(jsonArray.toJSONString(), SrmBaseItemsEntity_HI.class);//将json数据转成list的实体
            List<SrmBaseItemsEntity_HI> newSrmBaseItemList = new ArrayList<SrmBaseItemsEntity_HI>();
            if (null != srmBaseItemlist && srmBaseItemlist.size() > 0) {
                for (SrmBaseItemsEntity_HI srmBaseItemsEntity : srmBaseItemlist) {
                    if (srmBaseItemsEntity.getItemId() != null && srmBaseItemsEntity.getItemId() > 0) {
                        srmBaseItemDAO_HI.executeSqlUpdate("UPDATE srm_base_items t SET t.`invalid_date` = " + "NOW()" + " WHERE t.`item_id` = " + srmBaseItemsEntity.getItemId());
                    }
                }
                for (SrmBaseItemsEntity_HI items : srmBaseItemlist) {
                    srmBaseItemEntity = new SrmBaseItemsEntity_HI();
                    if (items.getOrganizationId() != null && items.getOrganizationId() > 0 && items.getItemId() != null && items.getItemId() > 0) {
                        srmBaseItemEntity.setItemId(items.getItemId());
                        StringBuffer appendString = new StringBuffer();
                        Map<String, Object> queryParamMap = new HashMap<String, Object>();
                        appendString.append(SrmBaseItemsEntity_HI_RO.QUERY_ITEM);
                        queryParamMap.put("organizationId", items.getOrganizationId());
                        queryParamMap.put("itemId", items.getItemId());
                        System.out.println("appendString==" + appendString);
                        List<SrmBaseItemsEntity_HI_RO> baseItemList = srmBaseItemsDAO_HI_RO.findList(appendString.toString(), queryParamMap);
                        if (baseItemList != null && baseItemList.size() > 0) {
                            for (SrmBaseItemsEntity_HI_RO entity : baseItemList) {
                                if (entity.getInvalidDate() != null && !"".equals(entity.getInvalidDate())) {
                                    srmBaseItemDAO_HI.executeSqlUpdate("UPDATE srm_base_items t SET t.`invalid_date` = NULL WHERE t.`item_id` = " + items.getItemId() + " AND t.`organization_id` = " + items.getOrganizationId());
                                }
                            }
                        } else {
                            StringBuffer append = new StringBuffer("SELECT * FROM `srm_base_items` t WHERE t.`item_id` = :itemId ");
                            Map<String, Object> queryMap = new HashMap<String, Object>();
                            queryMap.put("itemId", items.getItemId());
                            SrmBaseItemsEntity_HI_RO srmBaseItem = new SrmBaseItemsEntity_HI_RO();
                            List<SrmBaseItemsEntity_HI_RO> ff = srmBaseItemsDAO_HI_RO.findList(append, queryMap);
                            int a = ff.size();
                            for (int i = 0; i < ff.size(); i++) {
                                srmBaseItem = ff.get(0);
                            }
                            srmBaseItem.setOrganizationId(items.getOrganizationId());
                            srmBaseItem.setOperatorUserId(userId);
                            SrmBaseItemsEntity_HI baseItem = new SrmBaseItemsEntity_HI();
                            BeanUtils.copyProperties(srmBaseItem, baseItem);
                            srmBaseItemDAO_HI.save(baseItem);
                        }
                    } else {
                        if ((null != items.getItemId())) {//修改
                            srmBaseItemEntity.setLastUpdatedBy(userId);//更新人
                            srmBaseItemEntity.setLastUpdateDate(new Date());//更新时间
                        } else {//新增
                            if (null != items.getItemCode() && !"".equals(items.getItemCode())) {
                                StringBuffer queryString = new StringBuffer();
                                Map<String, Object> queryParamMap = new HashMap<String, Object>();
                                queryString.append("SELECT MAX(item_id) itemId FROM `srm_base_items` ");
                                SrmBaseItemsEntity_HI_RO srmBaseItemsEntity = srmBaseItemsDAO_HI_RO.findList(queryString, queryParamMap).get(0);
                                srmBaseItemEntity.setItemId(srmBaseItemsEntity.getItemId() + 1);
                                srmBaseItemEntity.setCreationDate(new Date());//创建时间
                                srmBaseItemEntity.setCreatedBy(userId);//创建人
                                srmBaseItemEntity.setLastUpdatedBy(userId);//更新人
                                srmBaseItemEntity.setLastUpdateDate(new Date());//更新时间
                            }
                        }
                        //全局标识(选择，未选择)
                        if (null != items.getGlobalFlag() || !"".equals(items.getGlobalFlag())) {
                            srmBaseItemEntity.setGlobalFlag("Y");
                        } else {
                            srmBaseItemEntity.setGlobalFlag("N");
                        }
                        //启用ASL表示(选择，“未选择)
                        if (null != items.getEnabledAsl() || !"".equals(items.getEnabledAsl())) {
                            srmBaseItemEntity.setEnabledAsl("Y");
                        } else {
                            srmBaseItemEntity.setEnabledAsl("N");
                        }
                        srmBaseItemEntity.setCategoryId(items.getCategoryId());
                        srmBaseItemEntity.setItemCode(items.getItemCode());
                        srmBaseItemEntity.setItemName(items.getItemName());
                        srmBaseItemEntity.setItemDescription(items.getItemName());
                        srmBaseItemEntity.setUomCode(items.getUomCode());
                        srmBaseItemEntity.setItemStatus("ACT");//物料状态为“生效”
                        srmBaseItemEntity.setPurchasingFlag(items.getPurchasingFlag());
                        srmBaseItemEntity.setAgentId(items.getAgentId());
                        srmBaseItemEntity.setOrganizationId(items.getOrganizationId());//组织id
                        srmBaseItemEntity.setPurchaseCycle(items.getPurchaseCycle());
                        srmBaseItemEntity.setPurchasingLeadTime(items.getPurchasingLeadTime());
                        srmBaseItemEntity.setMinPacking(items.getMinPacking());
                        srmBaseItemEntity.setImageId(items.getImageId());
                        srmBaseItemEntity.setOperatorUserId(userId);
                        if (items.getOrganizationId() != null && items.getOrganizationId() > 0) {
                            if (items.getItemId() != null && items.getItemId() > 0) {
                                srmBaseItemDAO_HI.saveOrUpdate(srmBaseItemEntity);
                            } else {
                                srmBaseItemDAO_HI.save(srmBaseItemEntity);
                            }
                        } else {
                            return SToolUtils.convertResultJSONObj("E", "组织不能为空！", 0, null);
                        }
                        newSrmBaseItemList.add(srmBaseItemEntity);
                    }
                }
                SrmIntfLogsEntity_HI logsEntity = null;
                List<SrmIntfLogsEntity_HI> logsEntityList = null;
                if (newSrmBaseItemList != null && newSrmBaseItemList.size() > 0) {
                    for (SrmBaseItemsEntity_HI srmBaseItemsEntity : newSrmBaseItemList) {
                        logsEntity = new SrmIntfLogsEntity_HI();
                        logsEntity.setIntfType("ITEM_IN");//接口类型BASE_INTF_TYPE
                        logsEntity.setTableName("srm_base_items");
                        logsEntity.setTableId(srmBaseItemsEntity.getItemId());//接口取数对应的表ID
                        logsEntity.setDataDirection("IN");//数据方向(IN：输入， OUT：输出)
                        logsEntity.setSendSystem(srmBaseItemsEntity.getSourceCode());//数据发送方
                        logsEntity.setReceiveSystem("SRM");
                        logsEntity.setInData(jsonParams.toJSONString());//输入报文
                        jsonData.put("srmBaseItemsEntity", srmBaseItemsEntity);
                        logsEntity.setOutData(jsonData.toJSONString());//输出报文
                        logsEntity.setDescription("物料维护输入接口");
                        logsEntity.setOperatorUserId(userId);
                        logsEntity.setIntfStatus("S");
                        logsEntityList.add(logsEntity);
                    }
                }
                srmIntfLogsDAO_HI.saveAll(logsEntityList);
            } else {
                return SToolUtils.convertResultJSONObj("E", "获取数据异常！", 0, null);
            }
            return SToolUtils.convertResultJSONObj("S", "保存成功！", 1, jsonData);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 物料数据接口（数据输出）——查询物料数据（用于外部访问的接口）
     * 需要提供用户和密码
     * 点击菜单查询物料数据
     *
     * @param jsonParams
     * @param userId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Map<String, Object> pushFindMatterDataManageList(JSONObject jsonParams, int userId) {
        LOGGER.info("参数：" + jsonParams.toString());
        //保存日志
        SrmIntfLogsEntity_HI logsEntity = new SrmIntfLogsEntity_HI();
        logsEntity.setIntfType("ITEM_OUT");//接口类型BASE_INTF_TYPE
        logsEntity.setTableName("srm_base_items");
        logsEntity.setDataDirection("OUT");//数据方向(IN：输入， OUT：输出)
        logsEntity.setSendSystem("SRM");//数据发送方
        logsEntity.setInData(jsonParams.toJSONString());//输入报文
        logsEntity.setDescription("物料维护输出接口");
        logsEntity.setOperatorUserId(userId);
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        String parentFullCategoryCode = jsonParams.getString("parentFullCategoryCode");

        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(parentFullCategoryCode)) {
            return null;
        }

        StringBuffer appendString = new StringBuffer();
        appendString.append(SrmBaseItemsEntity_HI_RO.QUERY_SRM_BASE_ITME_SQL);
        // 查询过滤条件
        SaafToolUtils.parperParam(jsonParams, "sbi.item_code", "itemCode", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_name", "itemName", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "se.employee_name", "employeeNameSearch", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_status", "itemStatus", appendString, queryParamMap, "=");
        if (parentFullCategoryCode != null && !"".equals(parentFullCategoryCode)) {
            appendString.append(
                            "AND EXISTS\n" +
                            "(SELECT\n" +
                            "  1\n" +
                            "FROM\n" +
                            "  srm_base_categories sbc\n" +
                            "WHERE sbc.category_id = sbi.category_id\n" +
                            "  AND sbc.full_category_code LIKE '%" + parentFullCategoryCode + "%')\n" +
                            "AND (\n" +
                            "  sbi.organization_id =\n" +
                            "  (SELECT\n" +
                            "    MIN(yy.organization_id)\n" +
                            "  FROM\n" +
                            "    srm_base_items yy\n" +
                            "  WHERE yy.item_id = sbi.item_id)\n" +
                            "  OR sbi.organization_id IS NULL\n" +
                            ")");
        } else {
            appendString.append(
                            "AND EXISTS\n" +
                            "(SELECT\n" +
                            "  1\n" +
                            "FROM\n" +
                            "  srm_base_categories sbc\n" +
                            "WHERE sbc.category_id = sbi.category_id\n" +
                            "  AND sbc.category_level = 1)\n" +
                            "AND (\n" +
                            "  sbi.organization_id =\n" +
                            "  (SELECT\n" +
                            "    MIN(yy.organization_id)\n" +
                            "  FROM\n" +
                            "    srm_base_items yy\n" +
                            "  WHERE yy.item_id = sbi.item_id)\n" +
                            "  OR sbi.organization_id IS NULL\n" +
                            ")");
        }
        appendString.append(" ORDER BY sbi.last_update_date DESC ");
        List<SrmBaseItemsEntity_HI_RO> list = srmBaseItemsDAO_HI_RO.findList(appendString.toString(), queryParamMap);
        Map<String, Object> map = new HashMap<String, Object>();
        if (list != null && list.size() > 0) {
            map.put("data", list);
            map.put("count", 1);
            map.put("msg", "查询成功");
            map.put("status", "S");
            logsEntity.setIntfStatus("S");//查无数据状态为E，查有数据状态为S
            srmIntfLogsDAO_HI.save(logsEntity);
        } else {
            map.put("status", "E");
            map.put("msg", "查无此数据！");
            map.put("data", new ArrayList<>());
            map.put("count", 0);
            logsEntity.setIntfStatus("E");
            srmIntfLogsDAO_HI.save(logsEntity);
        }
        return map;
    }

    /**
     * 物料查询Lov，按组织查询
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmBaseItemsEntity_HI_RO> findItemListByOrganization(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer appendString = new StringBuffer();
        appendString.append(SrmBaseItemsEntity_HI_RO.QUERY_ITEM_BY_ORGANIZATION);
        // 查询过滤条件
        queryParamMap.put("organizationId", jsonParams.getInteger("organizationId"));
        SaafToolUtils.parperParam(jsonParams, "sbi.item_code", "itemCode", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_name", "itemName", appendString, queryParamMap, "like");
        String countSql = "select count(1) from (" + appendString + ")";
        Pagination<SrmBaseItemsEntity_HI_RO> baseItemList = srmBaseItemsDAO_HI_RO
                .findPagination(appendString.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return baseItemList;
    }

}
