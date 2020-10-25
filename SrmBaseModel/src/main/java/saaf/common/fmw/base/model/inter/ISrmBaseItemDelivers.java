package saaf.common.fmw.base.model.inter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseItemDeliversEntity_HI_RO;
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
public interface ISrmBaseItemDelivers {

	/**
	 * 查询物料回货方式
	 * @param jsonParams
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	Pagination<SrmBaseItemDeliversEntity_HI_RO> findCategoryReturn(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

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

	JSONObject saveCategoryReturn(JSONArray jsonParams, Integer userId) throws Exception;

	/**
	 * 批量删除物料回货方式
	 * @param jsonArray
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	JSONObject deleteCategoryReturn(JSONArray jsonArray) throws Exception ;

	/**
	 * 批量失效
	 * @param jsonArray
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	JSONObject updateInvalidCategoryReturn(JSONArray jsonArray, Integer userId) throws Exception ;

	/**
	 * 批量生效
	 * @param jsonParams
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	JSONObject updateEffectCategoryReturn(JSONObject jsonParams)throws Exception;

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

	JSONObject saveBatchImportCategoryReturn(JSONArray jsonArray, Integer userId)throws Exception;
}
