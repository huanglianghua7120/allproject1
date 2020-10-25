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
import saaf.common.fmw.base.model.entities.readonly.SrmBaseCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseCategories;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierCategoriesEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierCategoriesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierCategories;

import java.util.*;

@Component("srmPosSupplierCategoriesServer")
public class SrmPosSupplierCategoriesServer implements ISrmPosSupplierCategories {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierCategoriesServer.class);

    @Autowired
    private ViewObject<SrmPosSupplierCategoriesEntity_HI> srmPosSupplierCategoriesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosSupplierCategoriesEntity_HI_RO> srmPosSupplierCategoriesDAO_HI_RO;

    @Autowired
    private ISrmBaseCategories iSrmBaseCategories; //采购分类

    public SrmPosSupplierCategoriesServer() {
        super();
    }

    /**
     * 删除产品与服务行
     */
    @Override
    public JSONObject deleteCategory(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());
        Integer supplierCategoryId = jsonParams.getInteger("supplierCategoryId");
        if (!(supplierCategoryId == null || "".equals(supplierCategoryId))) {
            SrmPosSupplierCategoriesEntity_HI row = srmPosSupplierCategoriesDAO_HI.getById(supplierCategoryId);
            if (null != row) {
                srmPosSupplierCategoriesDAO_HI.delete(row.getSupplierCategoryId());
                return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除失败，无此记录！参数为：" + supplierCategoryId, 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "删除失败，参数" + supplierCategoryId, 0, null);
        }
    }

    /**
     * 保存供应商的产品和服务（档案自助维护/内部创建供应商）
     *
     * @param categoriesList
     * @param userId
     * @param supplierId
     * @return
     */
    @Override
    public boolean saveSrmPosSupplierCategoriesInfo(List<SrmPosSupplierCategoriesEntity_HI> categoriesList, Integer userId, Integer supplierId) {
        boolean flag = false;
        List<SrmPosSupplierCategoriesEntity_HI> newCategoriesList = new ArrayList<>();
        if (null != categoriesList && categoriesList.size() > 0) {
            for (SrmPosSupplierCategoriesEntity_HI k : categoriesList) {
                SrmPosSupplierCategoriesEntity_HI findRow = srmPosSupplierCategoriesDAO_HI.getById(k.getSupplierCategoryId());
                if (null != findRow) {
                    findRow.setBigCategoryCode(k.getBigCategoryCode());
                    findRow.setCategoryId(k.getCategoryId());
                    findRow.setSupplierId(supplierId);
                    findRow.setOperatorUserId(userId);
                    newCategoriesList.add(findRow);
                } else {
                    k.setSupplierId(supplierId);
                    k.setOperatorUserId(userId);
                    newCategoriesList.add(k);
                }
            }
            srmPosSupplierCategoriesDAO_HI.saveOrUpdateAll(newCategoriesList);
            flag = true;
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 保存供应商的产品和服务——供应商接口（数据输入）
     *
     * @param supplierCategoryInfoList
     * @param userId
     * @param supplierId
     */
    @Override
    public void saveSupplierCategoryInfoListExternal(List<SrmPosSupplierCategoriesEntity_HI> supplierCategoryInfoList, Integer userId, Integer supplierId) {
        List<SrmPosSupplierCategoriesEntity_HI> newCategoriesList = new ArrayList<>();
        if (null != supplierCategoryInfoList && supplierCategoryInfoList.size() > 0) {
            for (SrmPosSupplierCategoriesEntity_HI k : supplierCategoryInfoList) {
                SrmPosSupplierCategoriesEntity_HI findRow = null;
                JSONObject jsonParams = new JSONObject();
                jsonParams.put("sourceCode", k.getSourceCode());
                jsonParams.put("sourceId", k.getSourceId());
                jsonParams.put("supplierId", supplierId);
                Pagination<SrmPosSupplierCategoriesEntity_HI_RO> listPagination = findSupplierCategory(jsonParams, 1, 1000);
                List<SrmPosSupplierCategoriesEntity_HI_RO> listRO = listPagination.getData();
                if (null != listRO && listRO.size() > 0) {
                    SrmPosSupplierCategoriesEntity_HI_RO findRO = listRO.get(0);  //获取主键ID
                    findRow = srmPosSupplierCategoriesDAO_HI.getById(findRO.getSupplierCategoryId());
                }
                if (null != findRow) { //update
                    findRow.setBigCategoryCode(k.getBigCategoryCode());
                    findRow.setStatus(k.getStatus());
                    findRow.setApprovedDate(k.getApprovedDate());
                    findRow.setInvalidDate(k.getInvalidDate());

                    JSONObject jsonParamsBaseCategories = new JSONObject();
                    jsonParamsBaseCategories.put("fullCategory_Code", k.getBigCategoryCode()); //根据采购编码查找出对应的采购分类的CategoryId
                    Pagination<SrmBaseCategoriesEntity_HI_RO> listBaseCategoriesPagination = iSrmBaseCategories.findSrmBaseCategoriesInfo(jsonParamsBaseCategories, 1, 1000);
                    List<SrmBaseCategoriesEntity_HI_RO> listBaseCategoriesRO = listBaseCategoriesPagination.getData();
                    if (null != listBaseCategoriesRO && listBaseCategoriesRO.size() > 0) {
                        SrmBaseCategoriesEntity_HI_RO baseCategoriesRO = listBaseCategoriesRO.get(0);
                        findRow.setCategoryId(baseCategoriesRO.getCategoryId()); //将采购分类的CategoryId赋值给k(产品和服务)对象
                    }

                    findRow.setSupplierId(supplierId);
                    findRow.setOperatorUserId(userId);
                    newCategoriesList.add(findRow);
                } else { //insert
                    JSONObject jsonParamsBaseCategories = new JSONObject();
                    jsonParamsBaseCategories.put("fullCategoryCode", k.getBigCategoryCode()); //根据采购编码查找出对应的采购分类的CategoryId
                    Pagination<SrmBaseCategoriesEntity_HI_RO> listBaseCategoriesPagination = iSrmBaseCategories.findSrmBaseCategoriesInfo(jsonParamsBaseCategories, 1, 1000);
                    List<SrmBaseCategoriesEntity_HI_RO> listBaseCategoriesRO = listBaseCategoriesPagination.getData();
                    if (null != listBaseCategoriesRO && listBaseCategoriesRO.size() > 0) {
                        SrmBaseCategoriesEntity_HI_RO baseCategoriesRO = listBaseCategoriesRO.get(0);
                        k.setCategoryId(baseCategoriesRO.getCategoryId()); //将采购分类的CategoryId赋值给k(产品和服务)对象
                    }
                    k.setSupplierId(supplierId);
                    k.setOperatorUserId(userId);
                    newCategoriesList.add(k);
                }
            }
            srmPosSupplierCategoriesDAO_HI.saveOrUpdateAll(newCategoriesList);
        }
    }

    /**
     * 校验供应商的产品和服务重复与必填项——供应商接口（数据输入）
     *
     * @param supplierCategoryInfoList
     * @return
     */
    @Override
    public String judgeSpaceSupplierCategories(List<SrmPosSupplierCategoriesEntity_HI> supplierCategoryInfoList) {
        String result = "";
        if (null == supplierCategoryInfoList || supplierCategoryInfoList.size() <= 0) {
            return result;
        }
        HashSet<Object> validBigCategoryCode = new HashSet<>();
        Integer index = 0;
        for (SrmPosSupplierCategoriesEntity_HI k : supplierCategoryInfoList) {
            index++;
            if (null == k.getSourceId() || "".equals(k.getSourceId())) {
                result += "请填写产品与服务" + "第" + index + "行的数据来源Id——sourceId";
                return result;
            }
            if (null == k.getSourceCode() || "".equals(k.getSourceCode())) {
                result += "请填写产品与服务" + "第" + index + "行的数据来源类型Code——sourceCode";
                return result;
            }
            if (null == k.getBigCategoryCode() || "".equals(k.getBigCategoryCode())) {
                result += "请填写产品与服务" + "第" + index + "行的必填项——类别编码bigCategoryCode";
                return result;
            }
            validBigCategoryCode.add(k.getBigCategoryCode());
        }
        boolean flag = supplierCategoryInfoList.size() != validBigCategoryCode.size() ? true : false;
        if (flag) {
            result += "产品与服务的类别编码有重复，请删除重复的行！";
            return result;
        }
        return result;
    }

    /**
     * 保存供应商的产品与服务
     *
     * @param dataList
     * @param userId
     * @param supplierId
     * @return
     */
    public JSONObject saveSupplierCategory(JSONArray dataList, int userId, int supplierId) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        List<SrmPosSupplierCategoriesEntity_HI> rowList = new ArrayList<SrmPosSupplierCategoriesEntity_HI>();
        for (int i = 0; i < dataList.size(); i++) {
            paramData = dataList.getJSONObject(i);
            SrmPosSupplierCategoriesEntity_HI row = null;
            if (null == paramData.getInteger("supplierCategoryId")) {
                row = new SrmPosSupplierCategoriesEntity_HI();
                row.setSupplierId(supplierId);
                row.setStatus("NEW");
                row.setCreationDate(new Date());
                row.setCreatedBy(userId);
            } else {
                row = srmPosSupplierCategoriesDAO_HI.getById(paramData.getInteger("supplierCategoryId"));
            }
            row.setBigCategoryCode(paramData.getString("bigCategoryCode"));
            row.setMiddleCategoryCode(paramData.getString("middleCategoryCode"));
            row.setSmallCategoryCode(paramData.getString("smallCategoryCode"));
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            rowList.add(row);
        }
        srmPosSupplierCategoriesDAO_HI.save(rowList);
        return resultjson;
    }

    /**
     * 获取供应商的供货类别
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SrmPosSupplierCategoriesEntity_HI_RO> findSupplierCategory(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosSupplierCategoriesEntity_HI_RO.QUERY_CATEGORIES);
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplier_id", sb, queryParamMap, "=");//如果是供应商查询
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplierId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.`status`", "status", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.big_category_code", "bigCategoryCode", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.supplier_category_id", "supplierCategoryId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.source_code", "sourceCode", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.source_id", "sourceId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosSupplierCategoriesEntity_HI_RO> result =
                srmPosSupplierCategoriesDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    @Override
    public List<SrmPosSupplierCategoriesEntity_HI_RO> findSrmPosSupplierCategoriesById(
            JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            String classFlag = jsonParams.getString("classFlag");
            String freezeType = jsonParams.getString("freezeType");
            String freezeStatus = jsonParams.getString("freezeStatus");

            if ("update".equals(classFlag)) {
                Integer frozenId = jsonParams.getInteger("frozenId");
                queryString.append(SrmPosSupplierCategoriesEntity_HI_RO.QUERY_CATEGORIES_UPDATE_SQL);
                String sql = "\t,srm_pos_frozen_categories c \r\n";
                queryString.append(sql);
                queryString.append("\t,srm_base_categories a WHERE a.category_id = b.category_id\r\n");
                queryString.append("\tAND b.supplier_category_id = c.supplier_category_id AND b.category_id = c.category_id AND c.frozen_id=\r\n");
                queryString.append(frozenId);

                if ("frozen".equals(freezeType) && !"APPROVED".equals(freezeStatus)) {
                    //冻结只能冻结合格的状态
                    queryString.append("\tAND (c.category_status = 'EFFECTIVE' ) \r\n");
                } else if ("RECOVERY".equals(freezeType) && !"APPROVED".equals(freezeStatus)) {
                    queryString.append("\tAND c.category_status = 'DISABLED' \r\n");
                }
            } else {
                if (!("APPROVED".equals(freezeStatus) || "APPROVING".equals(freezeStatus))) {
                    queryString.append(SrmPosSupplierCategoriesEntity_HI_RO.QUERY_CATEGORIES_SQL);
                    if ("frozen".equals(freezeType)) {
                        queryString.append("\t AND (b.status = 'EFFECTIVE' OR b.status =  'INTRODUCING' ) \r\n");
                    }
                } else if ("APPROVED".equals(freezeStatus) || "APPROVING".equals(freezeStatus)) {
                    if ("frozen".equals(freezeType)) {
                        Integer frozenId = jsonParams.getInteger("frozenId");
                        queryString.append(SrmPosSupplierCategoriesEntity_HI_RO.QUERY_CATEGORIES_SQL1);
                        queryString.append("\tAND b.supplier_category_id = c.supplier_category_id AND b.category_id=c.category_id AND c.frozen_id=\r\n");
                        queryString.append(frozenId);
                    } else {
                        queryString.append(SrmPosSupplierCategoriesEntity_HI_RO.QUERY_CATEGORIES_SQL);
                    }
                }
            }
            SaafToolUtils.parperParam(jsonParams, "b.supplier_id", "supplierId", queryString, queryParamMap, "=");
            if ("update".equals(classFlag)) {
            } else {
                SaafToolUtils.parperParam(jsonParams, "b.status", "statusType", queryString, queryParamMap, "=");
            }
            List<SrmPosSupplierCategoriesEntity_HI_RO> rowSet = srmPosSupplierCategoriesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询供应商有效的品类
     *
     * @param jsonParams
     * @return
     */
    @Override
    public List<SrmPosSupplierCategoriesEntity_HI_RO> findSupplierCategoriesByEff(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmPosSupplierCategoriesEntity_HI_RO.QUERY_CATEGORIES_BY_EFF);
            SaafToolUtils.parperParam(jsonParams, "psc.supplier_id", "supplierId", queryString, queryParamMap, "=");
            List<SrmPosSupplierCategoriesEntity_HI_RO> result = srmPosSupplierCategoriesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
