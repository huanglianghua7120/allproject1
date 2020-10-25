package saaf.common.fmw.prc.model.inter.server;

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
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.readonly.SrmPoSupplyProportionHEntity_HI_RO;
import saaf.common.fmw.prc.model.dao.readonly.SrmPrcMappingItemsDAO_HI_RO;
import saaf.common.fmw.prc.model.entities.SrmPrcMappingHeadersEntity_HI;
import saaf.common.fmw.prc.model.entities.SrmPrcMappingItemsEntity_HI;
import saaf.common.fmw.prc.model.entities.SrmPrcMappingSuppliersEntity_HI;
import saaf.common.fmw.prc.model.entities.readonly.MappingSupplierEntity_HI_RO;
import saaf.common.fmw.prc.model.entities.readonly.SrmPrcMappingItmesEntity_HI_RO;
import saaf.common.fmw.prc.model.entities.readonly.SrmPrcMappingSuppliersEntity_HI_RO;
import saaf.common.fmw.prc.model.inter.IMappingItems;
import saaf.common.fmw.utils.SrmUtils;
import sun.rmi.runtime.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("mappingItemsServer")
public class MappingItemsServer implements IMappingItems {

    private static final Logger LOGGER = LoggerFactory.getLogger(MappingItemsServer.class);

    @Autowired
    private BaseViewObject<SrmPrcMappingItmesEntity_HI_RO> srmPrcMappingItemsDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPrcMappingHeadersEntity_HI> srmPrcMappingHeaderssDAO_HI;

    @Autowired
    private ViewObject<SrmPrcMappingItemsEntity_HI> srmPrcMappingItmesDAO_HI;

    @Autowired
    private ViewObject<SrmPrcMappingSuppliersEntity_HI> mappingSuppliersDAO_HI;

    @Autowired
    private BaseViewObject<SrmPrcMappingSuppliersEntity_HI_RO> srmPrcMappingsuppliersDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPoSupplyProportionHEntity_HI_RO> supplyProportionHDAO_HI_RO;


    @Override
    public JSONObject updateMappingHeaderStatus(JSONObject params) throws Exception {
        Integer mappingHeaderId = params.getInteger("mappingHeaderId");
        Integer userId = params.getInteger("varUserId");
        SrmPrcMappingHeadersEntity_HI headersEntityHi = null;
        if (mappingHeaderId == null) {
            return saveItems(params, "ACT");
        }
        try {
            headersEntityHi = srmPrcMappingHeaderssDAO_HI.getById(mappingHeaderId);
            headersEntityHi.setMappingStatus("ACT");
            headersEntityHi.setOperatorUserId(userId);
            srmPrcMappingHeaderssDAO_HI.update(headersEntityHi);
            return SToolUtils.convertResultJSONObj("S", "状态已变更：”生效”", 1, null);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            throw new Exception("变更失败！");
        }
    }

    //查询头
    @Override
    public SrmPrcMappingItmesEntity_HI_RO findByHeadreId(JSONObject params) throws Exception {
        StringBuffer sql = new StringBuffer(SrmPrcMappingItmesEntity_HI_RO.QUERY_FIND_BY_HEADERS_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == params.getString("mappingHeaderId") || "".equals(params.getString("mappingHeaderId").trim())) {
            throw new Exception("暂无头信息");
        }
        try {
            map.put("mappingHeaderId", params.getInteger("mappingHeaderId"));
            return srmPrcMappingItemsDAO_HI_RO.findList(sql, map).get(0);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.info(e.getMessage());
            throw new Exception("查询失败");
        }
    }

    //查询行
    @Override
    public Pagination<SrmPrcMappingItmesEntity_HI_RO> findByMappingHeadreId(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer sql = new StringBuffer(SrmPrcMappingItmesEntity_HI_RO.QUERY_FIND_BY_MAPPINGING_ITEMS_ID_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == params.getString("mappingHeaderId") || "".equals(params.getString("mappingHeaderId").trim())) {
            return new Pagination();
        }
        try {
            map.put("mappingHeaderId", params.getInteger("mappingHeaderId"));
            Pagination<SrmPrcMappingItmesEntity_HI_RO> pagination = srmPrcMappingItemsDAO_HI_RO.findPagination(sql, map, pageIndex, pageRows);
            return pagination;
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.info(e.getMessage());
            throw new Exception("查询失败");
        }
    }

    //导入
    @Override
    public JSONObject saveItemsList(JSONObject params) throws Exception {
        JSONObject obj = null;
        SrmPrcMappingItmesEntity_HI_RO itemsEntityHi_RO = null;
        SrmPrcMappingItemsEntity_HI itemsEntityHi = null;
        SrmPrcMappingSuppliersEntity_HI mappingSuppliersEntityHi = null;

        StringBuffer sql = new StringBuffer(SrmPrcMappingItmesEntity_HI_RO.COUNT_MAPPINGF_HEADER_SQL);
        Integer userId = params.getInteger("varUserId");
        Integer mappingHeaderId = params.getJSONObject("info").getInteger("date");
        if (mappingHeaderId == null) {
            JSONObject resultObj = new JSONObject();
            resultObj.put("msg", "请先选择材质指标编码，并保存。");
            resultObj.put("status", "E");
            return resultObj;
        }

        JSONArray jsonArray = params.getJSONArray("data");
        JSONArray errorArray = this.resutError(jsonArray, mappingHeaderId);
        if (errorArray.size() > 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "保存失败", errorArray.size(), errorArray);
        }
        List<SrmPrcMappingItmesEntity_HI_RO> tempItemList = new ArrayList<SrmPrcMappingItmesEntity_HI_RO>();
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            obj = jsonArray.getJSONObject(i);
            itemsEntityHi_RO = new SrmPrcMappingItmesEntity_HI_RO();
            itemsEntityHi_RO.setMappingHeaderId(mappingHeaderId);
            itemsEntityHi_RO.setItemNumber(obj.getString("itemNumber").trim());
            SrmPoSupplyProportionHEntity_HI_RO suplierEntity = this.contaisItemCode(obj.getString("itemNumber"));
            itemsEntityHi_RO.setItemId(suplierEntity.getItemId());
            itemsEntityHi_RO.setItemDescription(suplierEntity.getItemDescription());
            itemsEntityHi_RO.setSupplierId(this.contaisSupplier(obj.getString("supplierNumber")));
            itemsEntityHi_RO.setUnitOfMeasure(obj.getString("unitOfMeasure"));
            itemsEntityHi_RO.setItemPrice(obj.getBigDecimal("itemPrice"));
            itemsEntityHi_RO.setLengthValue(obj.getBigDecimal("lengthValue"));
            itemsEntityHi_RO.setWidthValue(obj.getBigDecimal("widthValue"));
            itemsEntityHi_RO.setHeightValue(obj.getBigDecimal("heightValue"));//
            itemsEntityHi_RO.setSupplierName(obj.getString("supplierName"));
            itemsEntityHi_RO.setSupplierNumber(obj.getString("supplierNumber"));
            itemsEntityHi_RO.setSupplierPrice(obj.getBigDecimal("supplierPrice"));
            tempItemList.add(itemsEntityHi_RO);
        }
        List<MappingSupplierEntity_HI_RO> mappingSupplierList = new ArrayList<MappingSupplierEntity_HI_RO>();
        for (SrmPrcMappingItmesEntity_HI_RO mappingItemInfo : tempItemList) {
            itemsEntityHi = new SrmPrcMappingItemsEntity_HI();
            itemsEntityHi.setMappingHeaderId(mappingItemInfo.getMappingHeaderId());
            itemsEntityHi.setItemNumber(mappingItemInfo.getItemNumber());
            itemsEntityHi.setItemId(mappingItemInfo.getItemId());
            itemsEntityHi.setItemDescription(mappingItemInfo.getItemDescription());
            itemsEntityHi.setUnitOfMeasure(mappingItemInfo.getUnitOfMeasure());
            itemsEntityHi.setItemPrice(mappingItemInfo.getItemPrice());
            itemsEntityHi.setHeightValue(mappingItemInfo.getHeightValue());
            itemsEntityHi.setLengthValue(mappingItemInfo.getLengthValue());
            itemsEntityHi.setWidthValue(mappingItemInfo.getWidthValue());
            itemsEntityHi.setOperatorUserId(userId);

            mappingSuppliersEntityHi = new SrmPrcMappingSuppliersEntity_HI();
            mappingSuppliersEntityHi.setSupplierId(mappingItemInfo.getSupplierId());
            mappingSuppliersEntityHi.setMappingHeaderId(mappingItemInfo.getMappingHeaderId());
            mappingSuppliersEntityHi.setMappingSupplierId(mappingItemInfo.getMappingHeaderId());
//            mappingSuppliersEntityHi.setMappingItemId(mappingItemInfo.getItemId());
            mappingSuppliersEntityHi.setOriginalPrice(mappingItemInfo.getSupplierPrice());//原始价格
            mappingSuppliersEntityHi.setOperatorUserId(userId);
            int index = this.indexOf(mappingSupplierList, itemsEntityHi);
            //如果存在
            if (index >= 0) {
                mappingSupplierList.get(index).getSuppliersEntityHiList().add(mappingSuppliersEntityHi);
            } else {
                //如果不存在
                List<SrmPrcMappingSuppliersEntity_HI> supplierList = new ArrayList<SrmPrcMappingSuppliersEntity_HI>();
                supplierList.add(mappingSuppliersEntityHi);
                mappingSupplierList.add(new MappingSupplierEntity_HI_RO(itemsEntityHi, supplierList));
            }
        }
        try {
            for (MappingSupplierEntity_HI_RO resEntity : mappingSupplierList) {

                if (resEntity.getItemsEntityHi() != null) {
                    List<SrmPrcMappingItmesEntity_HI_RO> mappingItmesList = srmPrcMappingItemsDAO_HI_RO.findList(sql, new Object[]{resEntity.getItemsEntityHi().getItemNumber(), resEntity.getItemsEntityHi().getMappingHeaderId()});
                    //如果没有则新增
                    if (mappingItmesList.isEmpty() || mappingItmesList.get(0).getMappingItemId() == null) {
                        srmPrcMappingItmesDAO_HI.save(resEntity.getItemsEntityHi());
                    } else {
                        resEntity.getItemsEntityHi().setMappingItemId(mappingItmesList.get(0).getMappingItemId());
                    }
                }
                if (!resEntity.getSuppliersEntityHiList().isEmpty()) {
                    setMappingItemListId(resEntity.getItemsEntityHi().getMappingItemId(), resEntity.getSuppliersEntityHiList());
                    mappingSuppliersDAO_HI.saveAll(resEntity.getSuppliersEntityHiList());
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            JSONObject resultObj = new JSONObject();
            resultObj.put("msg", "导入失败，请联系管理员");
            resultObj.put("status", "E");
        }

        JSONObject resultObj = new JSONObject();
        resultObj.put("msg", "导入成功行数为:" + mappingSupplierList.size() + "行!");
        resultObj.put("status", "S");
        return resultObj;
    }

    private int indexOf(List<MappingSupplierEntity_HI_RO> mappingSupplierList, SrmPrcMappingItemsEntity_HI itemsEntityHi) {
        if (itemsEntityHi == null) return -1;
        for (int i = 0, j = mappingSupplierList.size(); i < j; i++) {
            MappingSupplierEntity_HI_RO supplierEntity = mappingSupplierList.get(i);
            if (supplierEntity.getItemsEntityHi().getItemNumber().equals(itemsEntityHi.getItemNumber())
                    && supplierEntity.getItemsEntityHi().getItemId().equals(itemsEntityHi.getItemId())) {
                return i;
            }
        }
        return -1;
    }

    private void setMappingItemListId(Integer mappingItemId, List<SrmPrcMappingSuppliersEntity_HI> mappingSupplierList) {
        for (SrmPrcMappingSuppliersEntity_HI suppliersEntityHi : mappingSupplierList) {
            suppliersEntityHi.setMappingItemId(mappingItemId);
        }
    }

    private JSONArray resutError(JSONArray jsonArray, Integer mappingHeaderId) {
        JSONArray errorArray = new JSONArray();
        JSONObject errorInfo = null;
        Map<String, Object> paramsMap = null;
        StringBuffer querySql = new StringBuffer(SrmPrcMappingItmesEntity_HI_RO.QUERY_MAPPING_COUNT_SQL);
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String itemNumber = obj.getString("itemNumber");
            String supplierNumber = obj.getString("supplierNumber");


            try {
                obj.getBigDecimal("lengthValue");
            } catch (Exception e) {
                errorInfo = new JSONObject();
                errorInfo.put("ERR_MESSAGE", "长度数值错误");
                errorInfo.put("ROW_NUM", i + 1);
                errorArray.add(errorInfo);
                continue;
            }
            try {
                obj.getBigDecimal("widthValue");
            } catch (Exception e) {
                errorInfo = new JSONObject();
                errorInfo.put("ERR_MESSAGE", "长度数值错误");
                errorInfo.put("ROW_NUM", i + 1);
                errorArray.add(errorInfo);
                continue;
            }
            try {
                obj.getBigDecimal("heightValue");
            } catch (Exception e) {
                errorInfo = new JSONObject();
                errorInfo.put("ERR_MESSAGE", "长度数值错误");
                errorInfo.put("ROW_NUM", i + 1);
                errorArray.add(errorInfo);
                continue;
            }

            if (supplierNumber == null || "".equals(supplierNumber.trim())) {
                errorInfo = new JSONObject();
                errorInfo.put("ERR_MESSAGE", "供应商编码不能为空");
                errorInfo.put("ROW_NUM", i + 1);
                errorArray.add(errorInfo);
                continue;
            }
            SrmPoSupplyProportionHEntity_HI_RO supplyProportionHEntityHiRo = this.contaisItemCode(itemNumber);
            if (supplyProportionHEntityHiRo == null || supplyProportionHEntityHiRo.getItemId() == -1 || supplyProportionHEntityHiRo.getItemId() == null) {
                errorInfo = new JSONObject();
                errorInfo.put("ERR_MESSAGE", "物料编码不存在");
                errorInfo.put("ROW_NUM", i + 1);
                errorArray.add(errorInfo);
                continue;
            }
            Integer supplierId = this.contaisSupplier(supplierNumber);
            if (supplierId == -1 || supplierNumber == null) {
                errorInfo = new JSONObject();
                errorInfo.put("ERR_MESSAGE", "供应商编码不存在");
                errorInfo.put("ROW_NUM", i + 1);
                errorArray.add(errorInfo);
                continue;
            }
            Integer countDB = srmPrcMappingItemsDAO_HI_RO.findList(querySql, new Object[]{supplyProportionHEntityHiRo.getItemId(), supplierId, mappingHeaderId}).get(0).getCount();
            if (countDB > 0) {
                errorInfo = new JSONObject();
                errorInfo.put("ERR_MESSAGE", "数据库已存在");
                errorInfo.put("ROW_NUM", i + 1);
                errorArray.add(errorInfo);
                continue;
            }
            int count = this.resultCount(jsonArray, itemNumber, supplierNumber);
            if (count > 1) {
                errorInfo = new JSONObject();
                errorInfo.put("ERR_MESSAGE", "物料编码跟供应商编码相同");
                errorInfo.put("ROW_NUM", i + 1);
                errorArray.add(errorInfo);
                continue;
            }
            paramsMap = new HashMap<String, Object>();
            paramsMap.put("itemNumber", itemNumber);
            paramsMap.put("mappingHeaderId", supplierId);
            List<SrmPrcMappingItemsEntity_HI> itmesList = srmPrcMappingItmesDAO_HI.findByProperty(paramsMap);
            //数据库里面没有才保存
            if (itmesList.size() > 0) {
                errorInfo = new JSONObject();
                errorInfo.put("ERR_MESSAGE", "已存在的數據");
                errorInfo.put("ROW_NUM", i + 1);
                errorArray.add(errorInfo);
                continue;
            }

        }
        return errorArray;
    }

    private int resultCount(JSONArray jsonArray, String itemNumber, String supplierNumber) {
        int count = -1;
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            if (itemNumber.equals(obj.getString("itemNumber")) && supplierNumber.equals(obj.getString("supplierNumber"))) {
                count++;
            }
        }
        return count;
    }

    //如果存在怎返回id 不存在则返回-1
    private SrmPoSupplyProportionHEntity_HI_RO contaisItemCode(String code) {
        if (null == code || "".equals(code.trim())) return null;
        StringBuffer sqlBuff = new StringBuffer(SrmPoSupplyProportionHEntity_HI_RO.QUERY_ITEMNAME_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemCode", code.trim());
        try {
            return supplyProportionHDAO_HI_RO.findList(sqlBuff, map).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    //查询供应商是否存在
    private Integer contaisSupplier(String number) {
        if (null == number || "".equals(number.trim())) return -1;
        StringBuffer sqlBuff = new StringBuffer(SrmPoSupplyProportionHEntity_HI_RO.QUERY_SUPPLIER_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("supplierNumber", number.trim());
        try {
            List<SrmPoSupplyProportionHEntity_HI_RO> supplierList = supplyProportionHDAO_HI_RO.findList(sqlBuff, map);
            if (supplierList == null || supplierList.isEmpty()) {
                return -1;
            }
            return supplierList.get(0).getSupplierId();
        } catch (Exception e) {
            //e.printStackTrace();
            return -1;
        }
    }

    //保存关联物料
    @Override
    public JSONObject saveItems(JSONObject params, String status) throws Exception {
        SrmPrcMappingHeadersEntity_HI mappingHeadersEntityHi = null;
        SrmPrcMappingItemsEntity_HI mappingItemsEntityHi = null;
        StringBuffer countSql = new StringBuffer(SrmPrcMappingItmesEntity_HI_RO.QUERY_FIND_MAPPINGF_HEADER_ID_SQL);
        try {
            Integer userId = params.getInteger("varUserId");
            mappingHeadersEntityHi = new SrmPrcMappingHeadersEntity_HI();
            mappingHeadersEntityHi.setMappingHeaderId(params.getInteger("mappingHeaderId"));
            mappingHeadersEntityHi.setIndicatorNumber(params.getString("indicatorNumber"));
            mappingHeadersEntityHi.setMappingStatus(status);
            mappingHeadersEntityHi.setOperatorUserId(userId);

            if (mappingHeadersEntityHi.getMappingHeaderId() == null) {
                srmPrcMappingHeaderssDAO_HI.save(mappingHeadersEntityHi);
            } else {
                mappingHeadersEntityHi.setVersionNum(params.getInteger("versionNum"));
                srmPrcMappingHeaderssDAO_HI.update(mappingHeadersEntityHi);
            }

            JSONArray jsonArray = params.getJSONArray("data");
            if (jsonArray != null) {
                for (int i = 0, j = jsonArray.size(); i < j; i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    mappingItemsEntityHi = new SrmPrcMappingItemsEntity_HI();
                    mappingItemsEntityHi.setMappingItemId(obj.getInteger("mappingItemId"));
                    mappingItemsEntityHi.setMappingHeaderId(mappingHeadersEntityHi.getMappingHeaderId());
                    mappingItemsEntityHi.setItemNumber(obj.getString("itemNumber"));
                    mappingItemsEntityHi.setItemId(obj.getInteger("itemId"));
                    mappingItemsEntityHi.setItemDescription(obj.getString("itemDescription"));
                    mappingItemsEntityHi.setUnitOfMeasure(obj.getString("unitOfMeasure"));
                    mappingItemsEntityHi.setLengthValue(obj.getBigDecimal("lengthValue"));
                    mappingItemsEntityHi.setWidthValue(obj.getBigDecimal("widthValue"));
                    mappingItemsEntityHi.setHeightValue(obj.getBigDecimal("heightValue"));
                    mappingItemsEntityHi.setItemPrice(obj.getBigDecimal("itemPrice"));
                    mappingItemsEntityHi.setOperatorUserId(userId);
                    List<SrmPrcMappingItmesEntity_HI_RO> mappingItemsList = srmPrcMappingItemsDAO_HI_RO.findList(countSql, new Object[]{mappingItemsEntityHi.getItemNumber(), mappingHeadersEntityHi.getMappingHeaderId()});
                    if (mappingItemsEntityHi.getMappingItemId() == null) {
                        if (mappingItemsList.size() != 0 && mappingItemsList.get(0).getCount() > 0) {
                            throw new Exception("编码“" + mappingItemsEntityHi.getItemNumber() + "”已存在！");
                        }
                        srmPrcMappingItmesDAO_HI.save(mappingItemsEntityHi);
                    } else {
                        mappingItemsEntityHi.setVersionNum(obj.getInteger("versionNum"));
                        srmPrcMappingItmesDAO_HI.update(mappingItemsEntityHi);
                    }
                }
            }
            return SToolUtils.convertResultJSONObj("S", "保存成功", 1, mappingHeadersEntityHi);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean findByNumbereaderId(JSONObject params) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("itemNumber", params.getString("itemNumber"));
            map.put("mappingHeaderId", params.getInteger("headerId"));
            List<SrmPrcMappingItemsEntity_HI> mappingItemList = srmPrcMappingItmesDAO_HI.findByProperty(map);
            if (mappingItemList.size() == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
        return true;
    }

    //lov
    @Override
    public Pagination<SrmPrcMappingItmesEntity_HI_RO> findMappingItemsLov(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer sql = new StringBuffer(SrmPrcMappingItmesEntity_HI_RO.QUERY_MAPPING_ITEMS_LOV_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            SaafToolUtils.parperParam(params, "ih.INDICATOR_NUMBER", "indicatorNumber", sql, map, "like");
            SaafToolUtils.parperParam(params, "ih.INDICATOR_NAME", "indicatorName", sql, map, "like");
            return srmPrcMappingItemsDAO_HI_RO.findPagination(sql, map, pageIndex, pageRows);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            throw new Exception("查询材质指标编码失败!");
        }
    }

    @Override
    public JSONObject deleteMappingItems(JSONObject params) throws Exception {
        Integer mappingItemsId = params.getInteger("mappingItemsId");
        if (mappingItemsId == null) {
            throw new Exception("请选择删除的行！");
        }
        try {
            srmPrcMappingItmesDAO_HI.delete(mappingItemsId);
            return SToolUtils.convertResultJSONObj("S", "删除品类成功", 1, null);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            throw new Exception("删除失败！");
        }
    }

    @Override
    public List<SrmPrcMappingSuppliersEntity_HI_RO> findByMappingItemId(JSONObject params) throws Exception {
        Integer mappingHeaderId = params.getInteger("mappingItemId");
        if (mappingHeaderId == null) {
            throw new Exception("请选择要查询的行！");
        }
        try {
            StringBuffer supplierSql = new StringBuffer(SrmPrcMappingSuppliersEntity_HI_RO.QUERY_MAPPING_SUPPLIER_BY_ID);
            Map<String, Object> supplierMap = new HashMap<String, Object>();
            supplierMap.put("mappingItemId", mappingHeaderId);
            return srmPrcMappingsuppliersDAO_HI_RO.findList(supplierSql, supplierMap);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.info(e.getMessage());
            throw new Exception("查询失败！");
        }
    }

    @Override
    public boolean findbyIndicatorNumber(JSONObject params) throws Exception {
        List<SrmPrcMappingItmesEntity_HI_RO> mappingItmesList = null;
        StringBuffer querySql = new StringBuffer(SrmPrcMappingItmesEntity_HI_RO.QUERY_MAPPING_HEADER_ACT_SQL);
        String indicatorNumber = params.getString("indicatorNumber");
        try {
            mappingItmesList = srmPrcMappingItemsDAO_HI_RO.findList(querySql, new Object[]{indicatorNumber});
            if (!mappingItmesList.isEmpty() && mappingItmesList.get(0).getCount() > 0) {
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("查询编码是否存在异常");
            throw new Exception("验证指标编码失败！");
        }

    }

    //查询指标关联物料
    @Override
    public Pagination<SrmPrcMappingItmesEntity_HI_RO> findMappingItemsPage(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {


        StringBuffer sql = new StringBuffer(SrmPrcMappingItmesEntity_HI_RO.QUERY_MAPPING_ITEMS_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            SaafToolUtils.parperParam(params, "mh.INDICATOR_NUMBER", "indicatorNumber", sql, map, "like");
            SaafToolUtils.parperParam(params, "ih.INDICATOR_NAME", "indicatorName", sql, map, "like");
            SaafToolUtils.parperParam(params, "su.user_full_name", "createUserName", sql, map, "like");
            SaafToolUtils.parperParam(params, "su2.user_full_name", "lastUpdateUserName", sql, map, "like");
            SaafToolUtils.parperParam(params, "mi.ITEM_NUMBER", "itemNumber", sql, map, "like");
            SaafToolUtils.parperParam(params, "mi.ITEM_DESCRIPTION", "itemDescription", sql, map, "like");
            SaafToolUtils.parperParam(params, "mh.MAPPING_STATUS", "mappingStatus", sql, map, "like");

            String createStartDate = params.getString("createStartDate");
            String createEndDate = params.getString("createEndDate");
            if (createStartDate != null && createEndDate != null) {
                sql.append(" and mh.CREATION_DATE between :createStartDate and :createEndDate");
                map.put("createStartDate", createStartDate);
                map.put("createEndDate", createEndDate);
            } else if (createStartDate != null && createEndDate == null) {
                sql.append(" and mh.CREATION_DATE >= :createStartDate");
                map.put("createStartDate", createStartDate);
            } else if (createEndDate != null && createStartDate == null) {
                sql.append(" and mh.CREATION_DATE <= :createEndDate");
                map.put("createEndDate", createEndDate);
            }

            String lastUpdateStartDate = params.getString("lastUpdateStartDate");
            String lastUpdateEndDate = params.getString("lastUpdateEndDate");
            if (lastUpdateStartDate != null && lastUpdateEndDate != null) {
                sql.append(" and mh.CREATION_DATE between :lastUpdateStartDate and :lastUpdateEndDate");
                map.put("lastUpdateStartDate", lastUpdateStartDate);
                map.put("lastUpdateEndDate", lastUpdateEndDate);
            } else if (lastUpdateStartDate != null && lastUpdateEndDate == null) {
                sql.append(" and mh.CREATION_DATE >= :lastUpdateStartDate");
                map.put("lastUpdateStartDate", lastUpdateStartDate);
            } else if (lastUpdateEndDate != null && lastUpdateStartDate == null) {
                sql.append(" and mh.CREATION_DATE <= :lastUpdateEndDate");
                map.put("lastUpdateEndDate", lastUpdateEndDate);
            }
            sql.append(" ORDER BY mh.CREATION_DATE DESC ");
            return srmPrcMappingItemsDAO_HI_RO.findPagination(sql, map, pageIndex, pageRows);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("查询失败");
        }
    }
}
