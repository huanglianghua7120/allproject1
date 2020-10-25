package saaf.common.fmw.base.model.inter.server;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONArray;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseCategoriesEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseItemsEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseItemDeliversEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseItemDelivers;

import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yhg.base.utils.SToolUtils;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SrmBaseItemDeliversEntity_HI;

import com.yhg.hibernate.core.dao.ViewObject;

@Component("srmBaseItemDeliversServer")
public class SrmBaseItemDeliversServer implements ISrmBaseItemDelivers{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseItemDeliversServer.class);
	@Autowired
	private ViewObject<SrmBaseItemDeliversEntity_HI> srmBaseItemDeliversDAO_HI;
	@Autowired
	private BaseViewObject<SrmBaseItemDeliversEntity_HI_RO> srmBaseItemDeliversDAO_HI_RO;
	@Autowired
	private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;
	@Autowired
	private ViewObject<SrmBaseItemsEntity_HI> srmBaseItemsDAO_HI;
	@Autowired
	private ViewObject<SrmBaseCategoriesEntity_HI> srmBaseCategoriesDAO_HI;

	public SrmBaseItemDeliversServer() {
		super();
	}
	

	/**
	 * 查询物料回货方式
	 * @param jsonParams
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2019-05-29     秦晓钊          创建
	 * ==============================================================================
	 */
	@Override
	public Pagination<SrmBaseItemDeliversEntity_HI_RO> findCategoryReturn(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		try{
			StringBuffer queryString = new StringBuffer();
			StringBuffer appendString = new StringBuffer();
			queryString.append(SrmBaseItemDeliversEntity_HI_RO.QUERY_ITEM_DELIVERS);
			saaf.common.fmw.common.utils.SaafToolUtils.parperParam(jsonParams, "deli.deliver_type", "deliverType",queryString, queryParamMap, "=");
			saaf.common.fmw.common.utils.SaafToolUtils.parperParam(jsonParams, "items.item_name", "itemName",queryString, queryParamMap, "=");
			saaf.common.fmw.common.utils.SaafToolUtils.parperParam(jsonParams, "cate.full_category_name", "fullCategoryName",queryString, queryParamMap, "=");
			saaf.common.fmw.common.utils.SaafToolUtils.parperParam(jsonParams, "su.user_full_name", "createdByName",queryString, queryParamMap, "=");
			saaf.common.fmw.common.utils.SaafToolUtils.parperParam(jsonParams, "sfu.user_full_name", "lastUpdatedByName",queryString, queryParamMap, "=");
			saaf.common.fmw.common.utils.SaafToolUtils.parperParam(jsonParams, "deli.deliver_status", "divisionStatus",queryString, queryParamMap, "=");
			appendString.append(queryString);
			String countSql = "select count(1) from (" + queryString + ")";
			appendString.append(" ORDER BY deli.LAST_UPDATE_DATE DESC");
			Pagination<SrmBaseItemDeliversEntity_HI_RO> result = srmBaseItemDeliversDAO_HI_RO.findPagination(appendString.toString(), countSql,queryParamMap, pageIndex, pageRows);
			return result;
		}catch (Exception e){
			throw new Exception(e);
		}
	}

	/**
	 * Description：保存物料回货方式
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * itemDeliverId  回货方式ID  NUMBER  Y
	 * categoryId  采购分类ID  NUMBER  Y
	 * itemId  物料ID  NUMBER  N
	 * deliverType  回货类型  VARCHAR2  Y
	 * deliverStatus  状态  VARCHAR2  N
	 * itemDeliverId  回货方式ID  NUMBER  Y
	 * categoryId  采购分类ID  NUMBER  Y
	 * itemId  物料ID  NUMBER  N
	 * deliverType  回货类型  VARCHAR2  Y
	 * deliverStatus  状态  VARCHAR2  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */

	@Override
	public JSONObject saveCategoryReturn(JSONArray jsonParams,Integer userId) throws Exception {
		JSONObject object = null;
		SrmBaseItemDeliversEntity_HI itemDeliversEntity = null;
		boolean categoryFlag = false;
		boolean itemFlag = false;
		try {
			for (int i = 0;i < jsonParams.size();i++){
				object = jsonParams.getJSONObject(i);
				if(null == object.getInteger("itemDeliverId")){
					itemFlag = findExistItemId(object.getInteger("itemId"));
					//检查相同物料,只能同时存在一种回货方式
					if (itemFlag){
						return SToolUtils.convertResultJSONObj("E", "相同物料,只能同时存在一种回货方式!", 0, null);
					}
					categoryFlag = findExistCategoryId(object.getInteger("categoryId"));
					//检查相同分类,只能同时存在一种回货方式
					if (categoryFlag){
						return SToolUtils.convertResultJSONObj("E", "相同分类,只能同时存在一种回货方式!", 0, null);
					}
					
					itemDeliversEntity = new SrmBaseItemDeliversEntity_HI();	
				}else{
					itemDeliversEntity = srmBaseItemDeliversDAO_HI.getById(object.getInteger("itemDeliverId"));
				}
				itemDeliversEntity.setCategoryId(object.getInteger("categoryId"));
				itemDeliversEntity.setItemId(object.getInteger("itemId"));
				itemDeliversEntity.setDeliverType(object.getString("deliverType"));
				itemDeliversEntity.setDeliverStatus(object.getString("deliverStatus"));
				itemDeliversEntity.setOperatorUserId(userId);
				srmBaseItemDeliversDAO_HI.saveOrUpdate(itemDeliversEntity);			
			}
			return SToolUtils.convertResultJSONObj("S", "保存成功", 1, itemDeliversEntity);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 批量删除物料回货方式
	 * @param jsonArray
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2019-05-29     秦晓钊          创建
	 * ==============================================================================
	 */
	public JSONObject deleteCategoryReturn(JSONArray jsonArray) throws Exception {
		JSONObject resultObj = new JSONObject();
		try{
			for(int i = 0;i < jsonArray.size();i++){
				JSONObject object = jsonArray.getJSONObject(i);
				srmBaseItemDeliversDAO_HI.delete(object.getInteger("itemDeliverId"));
			}
			resultObj.put("msg", "删除成功！");
			resultObj.put("status", "S");
			return resultObj;
		}catch (Exception e){
			throw new Exception(e);
		}
	}

	/**
	 * 批量失效
	 * @param jsonArray
	 * @return
	 * ==============================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2019-05-29     秦晓钊          创建
	 * ==============================================================================
	 */
	@Override
	public JSONObject updateInvalidCategoryReturn(JSONArray jsonArray,Integer userId) throws Exception {
		JSONObject resultObj = new JSONObject();
		List<SrmBaseItemDeliversEntity_HI> list = new ArrayList<>();
		try{
			SrmBaseItemDeliversEntity_HI srmBaseItemDeliversEntity = null;
			for(int i = 0;i < jsonArray.size();i++){
				JSONObject object = jsonArray.getJSONObject(i);
				srmBaseItemDeliversEntity = srmBaseItemDeliversDAO_HI.getById(object.getInteger("itemDeliverId"));
				srmBaseItemDeliversEntity.setDeliverStatus("INACT");
				srmBaseItemDeliversEntity.setOperatorUserId(userId);
				list.add(srmBaseItemDeliversEntity);
			}
			srmBaseItemDeliversDAO_HI.saveOrUpdateAll(list);
			resultObj.put("msg", "失效成功！");
			resultObj.put("status", "S");
			return resultObj;
		}catch (Exception e){
			throw new Exception(e);
		}
	}
	/**
	 * 批量生效
	 * @param jsonParams
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2019-05-29     秦晓钊          创建
	 * ==============================================================================
	 */
	@Override
	public JSONObject updateEffectCategoryReturn(JSONObject jsonParams)throws Exception{
		SrmBaseItemDeliversEntity_HI srmBaseItemDeliversEntity = null;
		JSONArray lineArray = jsonParams.getJSONArray("array");
		List<SrmBaseItemDeliversEntity_HI> list= new ArrayList<>();
		try {
			Integer userId = jsonParams.getInteger("varUserId");
			for(int i = 0; i<lineArray.size();i++){
				JSONObject object = lineArray.getJSONObject(i);
				LOGGER.info("line:"+object.toString());
				if("Y".equals(String.valueOf(object.getString("flag")))){
					if(!"DRAFT".equals(object.getString("deliverStatus"))&&!"INACT".equals(object.getString("deliverStatus"))){
						return SToolUtils.convertResultJSONObj("E","非拟定状态不可生效，请重选！",0,null);
					}
					srmBaseItemDeliversEntity = srmBaseItemDeliversDAO_HI.getById(object.getInteger("itemDeliverId"));
					srmBaseItemDeliversEntity.setDeliverStatus("ACT");
					srmBaseItemDeliversEntity.setOperatorUserId(userId);
					list.add(srmBaseItemDeliversEntity);
				}
			}
			srmBaseItemDeliversDAO_HI.saveOrUpdateAll(list);
			return SToolUtils.convertResultJSONObj("S","生效成功！",1,null);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Description：导入物料回货方式
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
	public JSONObject saveBatchImportCategoryReturn(JSONArray jsonArray,Integer userId) throws Exception {
		try {
			JSONArray error = cehckArray(jsonArray);
			if (error.size() > 0) {
				return SToolUtils.convertResultJSONObj("ERR_IMPORT", "导入失败", error.size(), error);
			}
			List<SrmBaseItemDeliversEntity_HI> list= new ArrayList<>();
			SrmBaseItemDeliversEntity_HI srmBaseItemDeliversEntity = null;
			int count = 0;
			for(int i = 0;i < jsonArray.size();i++){
				srmBaseItemDeliversEntity = new SrmBaseItemDeliversEntity_HI();
				JSONObject object = jsonArray.getJSONObject(i);
				Map<String, Object> queryLookupMap = new HashMap<String, Object>();
				queryLookupMap.put("lookup_type","ISP_RETURN_TYPE");
				queryLookupMap.put("meaning",object.getString("deliverType"));
				SaafLookupValuesEntity_HI saafLookupValuesEntity = saafLookupValuesDAO_HI.findByProperty(queryLookupMap).get(0);
				if(saafLookupValuesEntity.getLookupCode() != null && !"".equals(saafLookupValuesEntity.getLookupCode())){
					srmBaseItemDeliversEntity.setDeliverType(saafLookupValuesEntity.getLookupCode());
				}
				SrmBaseItemsEntity_HI srmBaseItemsEntity = srmBaseItemsDAO_HI.findByProperty("itemCode",object.getString("itemCode")).get(0);
				if (srmBaseItemsEntity != null && srmBaseItemsEntity.getItemId() > 0){
					srmBaseItemDeliversEntity.setItemId(srmBaseItemsEntity.getItemId());
				}
				if (srmBaseItemsEntity.getCategoryId()>0) {
					srmBaseItemDeliversEntity.setCategoryId(srmBaseItemsEntity.getCategoryId());
				}

				srmBaseItemDeliversEntity.setDeliverStatus("DRAFT");
				srmBaseItemDeliversEntity.setOperatorUserId(userId);
				list.add(srmBaseItemDeliversEntity);
				++count;
			}
			srmBaseItemDeliversDAO_HI.saveAll(list);
			JSONObject resultObj = new JSONObject();
			resultObj.put("msg", "导入成功行数为:" + count + "行!");
			resultObj.put("status", "S");
			return resultObj;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	private JSONArray cehckArray(JSONArray jsonArray) throws Exception{
        try {
            Map<String, Object> queryParamMap = new HashMap();
            if (jsonArray.isEmpty()) return null;

            JSONArray error = new JSONArray();
            JSONObject e = null;
            JSONObject object = null;
            for (int i = 0, j = jsonArray.size(); i < j; i++) {
                object = jsonArray.getJSONObject(i);
                Map<String, Object> queryLookupMap = new HashMap();
                queryLookupMap.put("lookup_type", "ISP_RETURN_TYPE");
                queryLookupMap.put("meaning", object.getString("deliverType"));
                List<SaafLookupValuesEntity_HI> saafLookupValuesEntityList = saafLookupValuesDAO_HI.findByProperty(queryLookupMap);
                if (saafLookupValuesEntityList == null || saafLookupValuesEntityList.size() < 1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "回货类型不存在！");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }

                List<SrmBaseCategoriesEntity_HI> srmBaseCategoriesEntityList = srmBaseCategoriesDAO_HI.findByProperty("fullCategoryCode", object.getString("categoryCode"));
                if (srmBaseCategoriesEntityList == null || srmBaseCategoriesEntityList.size() < 1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "分类编码不存在！");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }

                if (object.getString("itemCode") == null || "".equals(object.getString("itemCode").trim())) {
                    queryParamMap.put("deliver_type", saafLookupValuesEntityList.get(0).getLookupCode());
                    queryParamMap.put("category_id", srmBaseCategoriesEntityList.get(0).getCategoryId());
                    List<SrmBaseItemDeliversEntity_HI> list = srmBaseItemDeliversDAO_HI.findByProperty(queryParamMap);
                    if (list!=null&&list.size() > 0) {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "已存在相同的回货类型+分类编码！");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    }
                } else {
                    List<SrmBaseItemsEntity_HI> srmBaseItemEntityList = srmBaseItemsDAO_HI.findByProperty("itemCode", object.getString("itemCode"));
                    if (srmBaseItemEntityList == null || srmBaseItemEntityList.size() < 1) {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "物料编码不存在!");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    }
                    queryParamMap.put("deliver_type", saafLookupValuesEntityList.get(0).getLookupCode());
                    //queryParamMap.put("category_id", srmBaseCategoriesEntityList.get(0).getCategoryId());
                    queryParamMap.put("item_id", srmBaseItemEntityList.get(0).getItemId());
                    List<SrmBaseItemDeliversEntity_HI> list = srmBaseItemDeliversDAO_HI.findByProperty(queryParamMap);
                    if (list!=null&&list.size() > 0) {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "已存在相同的回货类型+物料编码！");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    }
                }
            }
            return error;
        } catch (Exception e) {
            //LOGGER.error("未知错误:{}", e)();
			LOGGER.error("未知错误:{}", e);
            throw new Exception(e);
        }
	}
	
	/**
	 * 检查分类ID是否重复
	 * @param categoryId 采购分类ID
	 * @return
	 * @throws Exception
	 * @author zhonghanyong
	 * ==============================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2019-05-29     秦晓钊          创建
	 * ==============================================================================
	 */
	public boolean findExistCategoryId(Integer categoryId)
			throws Exception {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("categoryId", categoryId);
			List<SrmBaseItemDeliversEntity_HI> rowSet = this.srmBaseItemDeliversDAO_HI
					.findByProperty(map);
			if (rowSet.size() > 0) {
				return true;
			}
			return false;
         }catch(Exception e){
             throw new Exception(e);
         }
	}
	
	/**
	 * 检查物料ID是否重复
	 * @param itemId 采购分类ID
	 * @return
	 * @throws Exception
	 * @author zhonghanyong
	 * ==============================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2019-05-29     秦晓钊          创建
	 * ==============================================================================
	 */
	public boolean findExistItemId(Integer itemId)
			throws Exception {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("itemId", itemId);
			List<SrmBaseItemDeliversEntity_HI> rowSet = this.srmBaseItemDeliversDAO_HI
					.findByProperty(map);
			if (rowSet.size() > 0) {
				return true;
			}
			return false;
         }catch(Exception e){
            throw new Exception(e);
         }
	}

}
