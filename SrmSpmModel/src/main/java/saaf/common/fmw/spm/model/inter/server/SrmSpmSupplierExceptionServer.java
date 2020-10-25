package saaf.common.fmw.spm.model.inter.server;


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
import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.readonly.SrmPoBaseCategoriesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierInfoEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.SrmSpmSupplierExceptionEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmSupplierExceptionEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmSupplierException;

import java.rmi.ServerException;
import java.util.*;

@Component("srmSpmSupplierExceptionServer")
public class SrmSpmSupplierExceptionServer implements ISrmSpmSupplierException{

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmSupplierExceptionServer.class);

	public SrmSpmSupplierExceptionServer() {
		super();
	}

	@Autowired
	private ViewObject<SrmSpmSupplierExceptionEntity_HI> srmSpmSupplierExceptionDAO_HI;

	@Autowired
	private BaseViewObject<SrmSpmSupplierExceptionEntity_HI_RO> srmSpmSupplierExceptionDAO_HI_RO;

	@Autowired
	private BaseViewObject<SrmPosSupplierInfoEntity_HI_RO> srmPosSupplierInfoDAO_HI_RO;

	@Autowired
	private BaseViewObject<SaafLookupValuesEntity_HI_RO> saafLookupValuesDAO_HI_RO;

	@Autowired
	private BaseViewObject<SrmPoBaseCategoriesEntity_HI_RO> srmPoBaseCategoriesDao_HI_RO;

	@Autowired
	private BaseViewObject<SaafInstitutionEntity_HI_RO> saafInstitutionDAO_HI_RO;


    /**
     * 导入绩效例外数据
     *
     * 1.导入时，校验“评价组织+供应商+例外类型”或者“评价组织+分类+例外类型”只有一条生效的数据
     * 2.导入成功的数据，状态默认为“生效”
     * @param paramJSON
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public JSONObject saveExceptionList(JSONObject paramJSON) throws Exception {
		LOGGER.info("params:-{}", JSON.toJSONString(paramJSON));
		JSONArray jsonArray = paramJSON.getJSONArray("data");
		SrmSpmSupplierExceptionEntity_HI listEntity = null;
		SrmPosSupplierInfoEntity_HI_RO supplierEntity = null;
		SaafLookupValuesEntity_HI_RO lookupValuesEntity = null;
		SrmPoBaseCategoriesEntity_HI_RO categoriesEntity = null;
		SaafInstitutionEntity_HI_RO institutionEntity = null;
		Integer varUserId = paramJSON.getInteger("varUserId");
		JSONArray error = checkData(jsonArray);
		if (error.size() > 0) {
			return SToolUtils.convertResultJSONObj("ERR_IMPORT", "保存失败", error.size(), error);
		}
		try {
			List<SrmSpmSupplierExceptionEntity_HI> exceptionList = new ArrayList<SrmSpmSupplierExceptionEntity_HI>();
			for (int i = 0, j = jsonArray.size(); i < j; i++) {
				JSONObject obj = jsonArray.getJSONObject(i);
				try {
					listEntity = new SrmSpmSupplierExceptionEntity_HI();
					//根据组织的名称去查组织Id
					institutionEntity = getOrgId(obj.getString("orgName"));
					listEntity.setOrgId(institutionEntity.getInstId());
					//根据码值的名称去查询码值lookup_code
					String exceptionObject = getLookCode("SPM_EXCEPTION_OBJECT",obj.getString("exceptionObject"));//例外对象
					listEntity.setExceptionObject(exceptionObject);
					if ("SUPPLIER".equals(exceptionObject)){//供应商
						//根据供应商名称去查询供应商id
						supplierEntity = getSupplierId(obj.getString("supplierName"));
						listEntity.setSupplierId(supplierEntity.getSupplierId());//供应商Id
						listEntity.setCategoryId(null);
					}else{//品类
						//根据分类编码去查询分类id
						categoriesEntity = getCategoryId(obj.getString("categoryCode"));
						listEntity.setCategoryId(categoriesEntity.getCategoryId());
						listEntity.setSupplierId(null);
					}
					String exceptionType = getLookCode("SPM_EXCEPTION_TYPE",obj.getString("exceptionType"));//例外类型
					listEntity.setExceptionType(exceptionType);
					listEntity.setExceptionStatus("ACTIVE");//设置默认绩效状态为生效
					listEntity.setStartDate(new Date()); //设置生效日期
					listEntity.setDescription(obj.getString("description")); //备注
					listEntity.setOperatorUserId(varUserId);
					exceptionList.add(listEntity);
					srmSpmSupplierExceptionDAO_HI.save(exceptionList);
				} catch (Exception e) {
					//e.printStackTrace();
					LOGGER.error("批量导入绩效例外列表,第" + (i + 1) + "失败" + JSONObject.toJSONString(obj));
					obj.put("ERR_MESSAGE", "error");
					obj.put("ROW_NUM", i + 1);
					error.add(obj);
				}
			}
			if (error.size() == 0) {
				return SToolUtils.convertResultJSONObj("S", "保存成功行数为" + jsonArray.size() + "行", 0, null);
			}
			return SToolUtils.convertResultJSONObj("ERR_IMPORT", "保存成功行数为" + (jsonArray.size() - error.size()) + "行", error.size(), error);
		} catch (Exception e) {
			LOGGER.error("导入失败:" + e);
			throw new UtilsException("导入失败: " + e.getMessage());

		}
	}



	/**
	 * 校验数据
	 * @param jsonArray
	 * @return
	 */
	/*private JSONArray checkData(JSONArray jsonArray) throws Exception{
		JSONArray error = new JSONArray();
		JSONObject jsonObject = null;
		Integer supplierId = null;
		Integer categoryId = null;
		String categoryCode = null;
		String supplierName = null;
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			//组织Id
			Integer instId = getInstId(obj.getString("instName")).getInstId();
			//例外对象
			String exceptionObject = getLookCode("SPM_EXCEPTION_OBJECT",obj.getString("exceptionObject"));
			supplierName = obj.getString("supplierName");
			categoryCode = obj.getString("categoryCode");
			if(null !=supplierName && !"".equals(supplierName)){
				//供应商Id
				supplierId = getSupplierId(supplierName).getSupplierId();
			}
			if(null !=categoryCode && !"".equals(categoryCode)){
				//分类Id
				categoryId = getCategoryId(categoryCode).getCategoryId();
			}
			//例外类型
			String exceptionType = getLookCode("SPM_EXCEPTION_TYPE",obj.getString("exceptionType"));
			if(checkRepeatData(instId, exceptionObject, supplierId, categoryId, exceptionType)>0){
				jsonObject = new JSONObject();
				jsonObject.put("ERR_MESSAGE", "数据不允许重复	");
				jsonObject.put("ROW_NUM", i + 1);
				error.add(jsonObject);
			}
		}
		return error;
	}*/



    /**
     * 校验数据
     * @param jsonArray
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	private JSONArray checkData(JSONArray jsonArray){
		SrmPoBaseCategoriesEntity_HI_RO categoriesEntity = null;
		JSONArray error = new JSONArray();
		JSONObject jsonObject = null;
		for (int i = 0; i < jsonArray.size(); i++) {
			int count = 0;
			int count2 = 0;
			JSONObject obj1 = jsonArray.getJSONObject(i);
			for (int j = 0; j < jsonArray.size(); j++) {
				JSONObject obj2 = jsonArray.getJSONObject(j);
				if ("供应商".equals(obj1.getString("exceptionObject")) && "供应商".equals(obj2.getString("exceptionObject"))){
					if(obj1.getString("orgName").equals(obj2.getString("orgName"))
							&& obj1.getString("exceptionType").equals(obj2.getString("exceptionType"))
							&& obj1.getString("supplierName").equals(obj2.getString("supplierName"))){
						count++;
					}
				}else{
					if((obj1.getString("supplierName")!=null && !"".equals(obj1.getString("supplierName")))
							&& (obj2.getString("categoryCode")!=null && !"".equals(obj2.getString("categoryCode")))){
						if(obj1.getString("orgName").equals(obj2.getString("orgName"))
								&& obj1.getString("exceptionType").equals(obj2.getString("exceptionType"))){
							count++;
						}
					}
				}
				if ("品类".equals(obj1.getString("exceptionObject")) && "品类".equals(obj2.getString("exceptionObject"))){
					if(obj1.getString("orgName").equals(obj2.getString("orgName"))
							&& obj1.getString("exceptionType").equals(obj2.getString("exceptionType"))
							&& obj1.getString("categoryCode").equals(obj2.getString("categoryCode"))){
						count2++;
					}
				}else{
					if((obj1.getString("supplierName")!=null && !"".equals(obj1.getString("supplierName")))
							&& (obj2.getString("categoryCode")!=null && !"".equals(obj2.getString("categoryCode")))){
						if(obj1.getString("orgName").equals(obj2.getString("orgName"))
								&& obj1.getString("exceptionType").equals(obj2.getString("exceptionType"))){
							count++;
						}
					}
				}
			}
			if(count>1){
				jsonObject = new JSONObject();
				jsonObject.put("ERR_MESSAGE", "评价组织,供应商名称,绩效例外类型不允许重复");
				jsonObject.put("ROW_NUM", i + 1);
				error.add(jsonObject);
			}
			if(count2>1){
				jsonObject = new JSONObject();
				jsonObject.put("ERR_MESSAGE", "评价组织,分类编码和绩效例外类型不允许重复	");
				jsonObject.put("ROW_NUM", i + 1);
				error.add(jsonObject);
			}

			//组织Id
			Integer instId = getOrgId(obj1.getString("orgName")).getInstId();
			//例外对象
			String exceptionObject = getLookCode("SPM_EXCEPTION_OBJECT",obj1.getString("exceptionObject"));
			String supplierName = obj1.getString("supplierName");
			String categoryCode = obj1.getString("categoryCode");
			Integer supplierId = null;
			Integer categoryId = null;
			if ("SUPPLIER".equals(exceptionObject)){//供应商
				if (null ==supplierName || "".equals(supplierName)){
					jsonObject = new JSONObject();
					jsonObject.put("ERR_MESSAGE", "供应商名称为空");
					jsonObject.put("ROW_NUM", i + 1);
					error.add(jsonObject);
				}else{
					//供应商Id
					supplierId = getSupplierId(supplierName).getSupplierId();
				}
			} else {
				if (null ==categoryCode || "".equals(categoryCode)){
					jsonObject = new JSONObject();
					jsonObject.put("ERR_MESSAGE", "分类编码为空");
					jsonObject.put("ROW_NUM", i + 1);
					error.add(jsonObject);
				}else{
					//分类Id
					categoriesEntity = getCategoryId(categoryCode);
					if (categoriesEntity != null){
						categoryId = getCategoryId(categoryCode).getCategoryId();
					} else {
						jsonObject = new JSONObject();
						jsonObject.put("ERR_MESSAGE", "分类编码填写有误");
						jsonObject.put("ROW_NUM", i + 1);
						error.add(jsonObject);
					}
				}
			}

			//例外类型
			String exceptionType = getLookCode("SPM_EXCEPTION_TYPE",obj1.getString("exceptionType"));
			if(checkRepeatData(instId, exceptionObject, supplierId, categoryId, exceptionType)>0){
				jsonObject = new JSONObject();
				jsonObject.put("ERR_MESSAGE", "数据不允许重复");
				jsonObject.put("ROW_NUM", i + 1);
				error.add(jsonObject);
			}
		}
		return error;
	}


	//查询数据是否重复  如果存在大于0 否则返回 0 其他返回-1
	private int checkRepeatData(Integer instId, String exceptionObject, Integer supplierId, Integer categoryId, String exceptionType){
		if (null == instId || null==exceptionType || null==exceptionObject) return -1;
		StringBuffer sql3 = new StringBuffer(SrmSpmSupplierExceptionEntity_HI_RO.QUERY_COUNT3);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("instId", instId);
		map.put("supplierId", supplierId);
		map.put("categoryId", categoryId);
		map.put("exceptionType", exceptionType);
		try {
			return srmSpmSupplierExceptionDAO_HI_RO.findList(sql3, map).get(0).getCount3();
		} catch (Exception e) {
			return -1;
		}
	}



    /**
     * 根据组织名称查询组织id
     *
     * @param orgName
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	private SaafInstitutionEntity_HI_RO getOrgId(String orgName) {
		if (null == orgName || "".equals(orgName.trim())) return null;
		SaafInstitutionEntity_HI_RO instEntity = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgName", orgName.trim());
			instEntity = saafInstitutionDAO_HI_RO.findList(SrmSpmSupplierExceptionEntity_HI_RO.QUERY_INST_ID, map).get(0);
			return instEntity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

    /**
     * 根据分类编码查询分类id
     * @param categoryCode
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	private SrmPoBaseCategoriesEntity_HI_RO getCategoryId(String categoryCode) {
		if (null == categoryCode || "".equals(categoryCode.trim())) return null;
		SrmPoBaseCategoriesEntity_HI_RO entity = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("categoryCode", categoryCode.trim());
			entity = srmPoBaseCategoriesDao_HI_RO.findList(SrmSpmSupplierExceptionEntity_HI_RO.QUERY_CATEGORY_CODE, map).size()==0 ? null :
					 srmPoBaseCategoriesDao_HI_RO.findList(SrmSpmSupplierExceptionEntity_HI_RO.QUERY_CATEGORY_CODE, map).get(0);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

    /**
     * 根据码值查询lookupCode
     * @param lookupType 码值类型，meaning 码值名称
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	private String getLookCode(String lookupType, String meaning) {
		String lookupCode = null;
		if (null == meaning || "".equals(meaning.trim())) return null;
		SaafLookupValuesEntity_HI_RO entity = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("lookupType", lookupType.trim());
			map.put("meaning", meaning.trim());
			entity = saafLookupValuesDAO_HI_RO.findList(SrmSpmSupplierExceptionEntity_HI_RO.QUERY_LOOKUP_CODE, map).get(0);
			if (entity !=null){
				lookupCode = entity.getLookupCode();
			}
			return lookupCode;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}


    /**
     * 根据供应商名称查询供应商id
     *
     * @param supplierName
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	private SrmPosSupplierInfoEntity_HI_RO getSupplierId(String supplierName) {
		if (null == supplierName || "".equals(supplierName.trim())) return null;
		SrmPosSupplierInfoEntity_HI_RO supplierEntity = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("supplierName", supplierName.trim());
			supplierEntity = srmPosSupplierInfoDAO_HI_RO.findList(SrmSpmSupplierExceptionEntity_HI_RO.QUERY_SUPPLIER_ID, map).get(0);
			return supplierEntity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}



    /**
     * 生效
     * 批量更新
     * @param paramJSON （ids）
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public JSONObject updateEffectiveException(JSONObject paramJSON) throws Exception {
		JSONArray exceptionIds = paramJSON.getJSONArray("data");
		Integer varUserId = paramJSON.getInteger("varUserId");
		if (exceptionIds.isEmpty()) {
			return SToolUtils.convertResultJSONObj("S", "无生效的数据", 0, null);
		}
		SrmSpmSupplierExceptionEntity_HI entity = null;
		List<SrmSpmSupplierExceptionEntity_HI> list = new ArrayList<SrmSpmSupplierExceptionEntity_HI>();
		for (int i = 0, j = exceptionIds.size(); i < j; i++) {
			Integer exceptionId = exceptionIds.getInteger(i);
			checkData(exceptionId);
			/*int count = checkData(exceptionId);
			if (count>0){
				throw new ServerException("“评价组织+供应商+例外类型”或者“评价组织+分类+例外类型”只有一条生效的数据！");
			}*/
			entity = srmSpmSupplierExceptionDAO_HI.getById(exceptionId);
			if ("NEW".equals(entity.getExceptionStatus())){//拟定->生效
				entity.setExceptionStatus("ACTIVE");
				entity.setStartDate(new Date());
				entity.setOperatorUserId(varUserId);
			}
			if (entity != null) {
				list.add(entity);
			}
		}
		if (list.isEmpty()) {
			return SToolUtils.convertResultJSONObj("S", "无生效的数据", 0, null);
		}
		try {
			srmSpmSupplierExceptionDAO_HI.update(list);
			return SToolUtils.convertResultJSONObj("S", "生效成功", list.size(), null);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new UtilsException("生效失败");
		}
	}


	/**
     * 查询数据是否重复  如果存在大于0 否则返回 0 其他返回-1（校验“评价组织+供应商+例外类型”或者“评价组织+分类+例外类型”只有一条生效的数据）
     * @param exceptionId （ids）
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	private void checkData(Integer exceptionId) throws Exception{
		Map<String, Object> map = new HashMap();
		map.put("exceptionId", exceptionId);
		StringBuffer sqlBuff = new StringBuffer(SrmSpmSupplierExceptionEntity_HI_RO.QUERY_SUPPLIER_CATEGORY);
		StringBuffer sqlBuff1 = new StringBuffer(SrmSpmSupplierExceptionEntity_HI_RO.QUERY_COUNT1);
		StringBuffer sqlBuff2 = new StringBuffer(SrmSpmSupplierExceptionEntity_HI_RO.QUERY_COUNT1);
		List<SrmSpmSupplierExceptionEntity_HI_RO> list = srmSpmSupplierExceptionDAO_HI_RO.findList(sqlBuff, map);
		Integer supplierId = list.get(0).getSupplierId();
		Integer categoryId = list.get(0).getCategoryId();
		if (categoryId!=null){
			int count2 =  srmSpmSupplierExceptionDAO_HI_RO.findList(sqlBuff2, map).get(0).getCount2();
			if (count2>0){//评价组织+分类+例外类型
				throw new UtilsException("评价组织+供应商+例外类型 或者 评价组织+分类+例外类型只能有一条生效的数据！");
			}
		} else if (supplierId != null){
			int count1 =  srmSpmSupplierExceptionDAO_HI_RO.findList(sqlBuff1, map).get(0).getCount1();
			if (count1>0){//评价组织+供应商+例外类型
				throw new UtilsException("评价组织+供应商+例外类型 或者 评价组织+分类+例外类型只能有一条生效的数据！");
			}
		}
	}


    /**
     * 失效
     * 批量更新
     * @param paramJSON （ids）
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public JSONObject updateInvalidException(JSONObject paramJSON) throws Exception {
		JSONArray exceptionIds = paramJSON.getJSONArray("data");
		Integer varUserId = paramJSON.getInteger("varUserId");
		if (exceptionIds.isEmpty()) {
			return SToolUtils.convertResultJSONObj("S", "无失效的数据", 0, null);
		}
		SrmSpmSupplierExceptionEntity_HI entity = null;
		List<SrmSpmSupplierExceptionEntity_HI> list = new ArrayList();
		for (int i = 0, j = exceptionIds.size(); i < j; i++) {
			Integer exceptionId = exceptionIds.getInteger(i);
			entity = srmSpmSupplierExceptionDAO_HI.getById(exceptionId);
			if ("ACTIVE".equals(entity.getExceptionStatus())){//生效->失效
				entity.setExceptionStatus("INACTIVE");
				entity.setEndDate(new Date());
				entity.setOperatorUserId(varUserId);
			}
			if (entity != null) {
				list.add(entity);
			}
		}
		if (list.isEmpty()) {
			return SToolUtils.convertResultJSONObj("S", "无可失效的数据", 0, null);
		}
		try {
			srmSpmSupplierExceptionDAO_HI.update(list);
			return SToolUtils.convertResultJSONObj("S", "失效成功", list.size(), null);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new UtilsException("失效失败");
		}
	}



    /**
     * 删除绩效例外数据
     * 批量删除
     * @param paramJSON （ids）
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public JSONObject deleteException(JSONObject paramJSON) throws Exception {
		JSONArray exceptionIds = paramJSON.getJSONArray("data");
		if (exceptionIds.isEmpty()) {
			return SToolUtils.convertResultJSONObj("E", "无可删除的数据", 0, null);
		}
		SrmSpmSupplierExceptionEntity_HI entity = null;
		List<SrmSpmSupplierExceptionEntity_HI> list = new ArrayList<SrmSpmSupplierExceptionEntity_HI>();
		for (int i = 0, j = exceptionIds.size(); i < j; i++) {
			Integer exceptionId = exceptionIds.getInteger(i);
			entity = srmSpmSupplierExceptionDAO_HI.getById(exceptionId);
			if (entity != null && "NEW".equals(entity.getExceptionStatus())) { //拟定删除
				list.add(entity);
			}
		}
		if (list.isEmpty()) {
			return SToolUtils.convertResultJSONObj("E", "无可删除的数据", 0, null);
		}
		try {
			srmSpmSupplierExceptionDAO_HI.delete(list);
			return SToolUtils.convertResultJSONObj("S", "删除成功", list.size(), null);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new UtilsException("删除失败");
		}
	}



    /**
     *导出绩效例外数据
     *
     * @param paramJSON 查询条件
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public List<SrmSpmSupplierExceptionEntity_HI_RO> findExceptionExport(JSONObject paramJSON) throws Exception {
		try {
			StringBuffer queryString = new StringBuffer(SrmSpmSupplierExceptionEntity_HI_RO.QUERY_EXCEPTION_INFO_LIST);
			Map<String, Object> map = new HashMap<String, Object>();
			// 查询过滤条件
			SaafToolUtils.parperParam(paramJSON, "se.org_id", "orgId", queryString, map, "=");
			SaafToolUtils.parperParam(paramJSON, "se.exception_object", "exceptionObject", queryString, map, "=");
			SaafToolUtils.parperParam(paramJSON, "date_format(se.start_date,'%Y-%m-%d')", "startDateFrom", queryString, map, ">=");
			SaafToolUtils.parperParam(paramJSON, "se.supplier_id ", "supplierId", queryString, map, "=");
			SaafToolUtils.parperParam(paramJSON, "se.exception_type ", "exceptionType", queryString, map, "=");
			SaafToolUtils.parperParam(paramJSON, "date_format(se.start_date,'%Y-%m-%d')", "startDateTo", queryString, map, "<=");
			SaafToolUtils.parperParam(paramJSON, "se.category_id ", "categoryId", queryString, map, "=");
			SaafToolUtils.parperParam(paramJSON, "bc.category_name", "categoryName", queryString, map, "like");
			SaafToolUtils.parperParam(paramJSON, "date_format(se.creation_date,'%Y-%m-%d')", "creationDateFrom", queryString, map, ">=");
			SaafToolUtils.parperParam(paramJSON, "date_format(se.end_date,'%Y-%m-%d')", "endDateFrom", queryString, map, ">=");
			SaafToolUtils.parperParam(paramJSON, "date_format(se.creation_date,'%Y-%m-%d')", "creationDateTo", queryString, map, "<=");
			SaafToolUtils.parperParam(paramJSON, "date_format(se.end_date,'%Y-%m-%d')", "endDateTo", queryString, map, "<=");
			SaafToolUtils.parperParam(paramJSON, "se.exception_status", "exceptionStatus", queryString, map, "=");
			SaafToolUtils.parperParam(paramJSON, "su.user_full_name", "userFullName", queryString, map, "like");
			// 排序
			queryString.append(" ORDER BY se.creation_date DESC");
			return srmSpmSupplierExceptionDAO_HI_RO.findList(queryString, map);
		} catch (Exception e) {
			throw new UtilsException("导出失败");
		}
	}


    /**
     * 保存绩效例外数据
     *
     * @param params
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public JSONObject saveException(JSONObject params) throws Exception {
		SrmSpmSupplierExceptionEntity_HI entity_hi = null;
		System.out.println("---->>" + params);
		JSONObject obj = params.getJSONObject("data");
		try {
			Integer varUserId = params.getInteger("varUserId");
			//如果id为空则新增 否则为修改
			if (null == obj.get("exceptionId")) {
				entity_hi = new SrmSpmSupplierExceptionEntity_HI();
				entity_hi.setOrgId(obj.getInteger("orgId"));
				entity_hi.setExceptionObject(obj.getString("exceptionObject"));
				entity_hi.setSupplierId(obj.getInteger("supplierId"));
				entity_hi.setCategoryId(obj.getInteger("categoryId"));
				entity_hi.setSegment1(obj.getString("segment1"));
				entity_hi.setSegment2(obj.getString("segment2"));
				entity_hi.setSegment3(obj.getString("segment3"));
				entity_hi.setSegment4(obj.getString("segment4"));
				entity_hi.setExceptionType(obj.getString("exceptionType"));
				entity_hi.setStartDate(obj.getDate("startDate"));
				entity_hi.setEndDate(obj.getDate("endDate"));
				entity_hi.setDescription(obj.getString("description"));
				entity_hi.setExceptionStatus(obj.getString("en_exceptionStatus"));
				entity_hi.setOperatorUserId(varUserId);
				srmSpmSupplierExceptionDAO_HI.save(entity_hi);
			} else { // 更新
				entity_hi = srmSpmSupplierExceptionDAO_HI.findByProperty("exceptionId", obj.get("exceptionId")).get(0);
				entity_hi.setOrgId(obj.getInteger("orgId"));
				entity_hi.setExceptionObject(obj.getString("exceptionObject"));
				entity_hi.setSupplierId(obj.getInteger("supplierId"));
				entity_hi.setCategoryId(obj.getInteger("categoryId"));
				entity_hi.setSegment1(obj.getString("segment1"));
				entity_hi.setSegment2(obj.getString("segment2"));
				entity_hi.setSegment3(obj.getString("segment3"));
				entity_hi.setSegment4(obj.getString("segment4"));
				entity_hi.setExceptionType(obj.getString("exceptionType"));
				entity_hi.setStartDate(obj.getDate("startDate"));
				entity_hi.setEndDate(obj.getDate("endDate"));
				entity_hi.setDescription(obj.getString("description"));
				entity_hi.setExceptionStatus(obj.getString("en_exceptionStatus"));
				entity_hi.setOperatorUserId(varUserId);
				srmSpmSupplierExceptionDAO_HI.update(entity_hi);
			}
			return SToolUtils.convertResultJSONObj("S", "保存绩效例外数据成功", 1, entity_hi);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new UtilsException("保存失败");
		}
	}


    /**
     * 绩效例外查询
     * @param paramJSON 查询条件
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
	@Override
	public Pagination<SrmSpmSupplierExceptionEntity_HI_RO> findExceptionInfoList(JSONObject paramJSON, Integer pageIndex, Integer pageRows) throws Exception{
		try {
			StringBuffer queryString = new StringBuffer(SrmSpmSupplierExceptionEntity_HI_RO.QUERY_EXCEPTION_INFO_LIST);
			Map<String, Object> map = new HashMap();
			// 查询过滤条件
			SaafToolUtils.parperParam(paramJSON, "se.org_id", "orgId", queryString, map, "=");
			SaafToolUtils.parperParam(paramJSON, "se.exception_object", "exceptionObject", queryString, map, "=");
			String startDateFrom = paramJSON.getString("startDateFrom");
			if (startDateFrom != null && !"".equals(startDateFrom.trim())) {
				queryString.append(" AND trunc(se.start_date) >= to_date(:startDateFrom, 'yyyy-mm-dd')\n");
				map.put("startDateFrom", startDateFrom);
			}
			String startDateTo = paramJSON.getString("startDateTo");
			if (startDateTo != null && !"".equals(startDateTo.trim())) {
				queryString.append(" AND trunc(se.start_date) <= to_date(:startDateTo, 'yyyy-mm-dd')\n");
				map.put("startDateTo", startDateTo);
			}
			String endDateFrom = paramJSON.getString("endDateFrom");
			if (endDateFrom != null && !"".equals(endDateFrom.trim())) {
				queryString.append(" AND trunc(se.end_date) >= to_date(:endDateFrom, 'yyyy-mm-dd')\n");
				map.put("endDateFrom", endDateFrom);
			}
			String endDateTo = paramJSON.getString("endDateTo");
			if (endDateTo != null && !"".equals(endDateTo.trim())) {
				queryString.append(" AND trunc(se.end_date) <= to_date(:endDateTo, 'yyyy-mm-dd')\n");
				map.put("endDateTo", endDateTo);
			}
			String creationDateFrom = paramJSON.getString("creationDateFrom");
			if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
				queryString.append(" AND trunc(se.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
				map.put("creationDateFrom", creationDateFrom);
			}
			String creationDateTo = paramJSON.getString("creationDateTo");
			if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
				queryString.append(" AND trunc(se.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
				map.put("creationDateTo", creationDateTo);
			}
			SaafToolUtils.parperParam(paramJSON, "se.supplier_id ", "supplierId", queryString, map, "=");
			SaafToolUtils.parperParam(paramJSON, "se.exception_type ", "exceptionType", queryString, map, "=");
			SaafToolUtils.parperParam(paramJSON, "se.category_id ", "categoryId", queryString, map, "=");
			SaafToolUtils.parperParam(paramJSON, "bc.category_name", "categoryName", queryString, map, "like");
			SaafToolUtils.parperParam(paramJSON, "se.exception_status", "exceptionStatus", queryString, map, "=");
			SaafToolUtils.parperParam(paramJSON, "su.user_full_name", "userFullName", queryString, map, "like");
            String countSql = "select count(1) from (" + queryString + ")";
			// 排序
			queryString.append(" ORDER BY se.creation_date DESC");
			Pagination<SrmSpmSupplierExceptionEntity_HI_RO> exceptionList = srmSpmSupplierExceptionDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
			return exceptionList;
		} catch (Exception e) {
			throw new UtilsException("查询失败:"+e.getMessage());
		}
	}

}
