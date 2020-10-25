package saaf.common.fmw.apimanager.model.inter.server;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.apimanager.model.entities.SaafProjectApiMamagerEntity_HI;
import saaf.common.fmw.apimanager.model.inter.ISaafProjectApiMamager;
import saaf.common.fmw.apimanager.utils.SaafToolUtils;

@Component("saafProjectApiMamagerServer")
public class SaafProjectApiMamagerServer implements ISaafProjectApiMamager{
	private static final Logger LOGGER = LoggerFactory.getLogger(SaafProjectApiMamagerServer.class);
	@Autowired
	private ViewObject<SaafProjectApiMamagerEntity_HI> saafProjectApiMamagerDAO_HI;
	public SaafProjectApiMamagerServer() {
		super();
	}

	public String findSaafProjectApiMamagerInfo(JSONObject queryParamJSON,int curIndex,int pageSize) {

		SaafProjectApiMamagerEntity_HI msEntity_HI = JSON.parseObject(queryParamJSON.toString(),
				SaafProjectApiMamagerEntity_HI.class);
		StringBuffer sbhql = new StringBuffer();
		sbhql.append(" from SaafProjectApiMamagerEntity_HI s where 1=1 ");
		Map<String, Object> map = new HashMap<String, Object>();
		// 利用java反射机制
		Field[] fields = msEntity_HI.getClass().getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			if (StringUtils.isEmpty(queryParamJSON.getString(fieldName)))
				continue;
			if ("spaId".equals(fieldName))// 前端详情查询某一条数据
				sbhql.append(" and  s." + fieldName + " = " + queryParamJSON.getString(fieldName) + " ");
			else
				sbhql.append(" and  s." + fieldName + " like '%" + queryParamJSON.getString(fieldName) + "%' ");
		}

		Pagination<SaafProjectApiMamagerEntity_HI> findListResult = saafProjectApiMamagerDAO_HI.findPagination(sbhql, map,
				curIndex, pageSize);
		JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(findListResult));
		jsonObject.put("status", "S");
		String resultData = JSON.toJSONString(jsonObject);
		System.out.println(resultData);
		return resultData;
	}
	
	public SaafProjectApiMamagerEntity_HI findSaafProjectApiMamagerInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		StringBuffer sbhql = new StringBuffer();
		sbhql.append(" from SaafProjectApiMamagerEntity_HI s where 1=1");
		if (null != queryParamJSON.getString("spaId")) {
			sbhql.append(" and s.spaId = :spaId ");
		}
		SaafProjectApiMamagerEntity_HI entity_HI = saafProjectApiMamagerDAO_HI.get(sbhql.toString(), queryParamMap);
		return entity_HI;
	}
	public String saveSaafProjectApiMamagerInfo(JSONObject queryParamJSON) {
		SaafProjectApiMamagerEntity_HI entity_HI = JSON.parseObject(queryParamJSON.toString(), SaafProjectApiMamagerEntity_HI.class);
		JSONObject queryParamJSON_ = new JSONObject();
		queryParamJSON_.put("spaId",
				null == entity_HI.getSpaId()? 0 : entity_HI.getSpaId());
		SaafProjectApiMamagerEntity_HI tempEntity =findSaafProjectApiMamagerInfo(queryParamJSON_);
		LOGGER.info(JSONObject.toJSONString(entity_HI));
		if (null == tempEntity) {
			entity_HI.setDeveloper(entity_HI.getDeveloper());
			saafProjectApiMamagerDAO_HI.save(entity_HI);
		} else {

			tempEntity.setModelCode(entity_HI.getModelCode());
			tempEntity.setModelName(entity_HI.getModelName());
			tempEntity.setProjectCode(entity_HI.getProjectCode());
			tempEntity.setProjectName(entity_HI.getProjectName());
			
			tempEntity.setInterfaceName(entity_HI.getInterfaceName());
			tempEntity.setRequestMode(entity_HI.getRequestMode());
		    tempEntity.setSpaStatus(entity_HI.getSpaStatus());
		    tempEntity.setUrlAddress(entity_HI.getUrlAddress());
		    tempEntity.setSpaDesc(entity_HI.getSpaDesc());
		    tempEntity.setParam(entity_HI.getParam());
		    tempEntity.setParamDict(entity_HI.getParamDict());
		    tempEntity.setReturnedValue(entity_HI.getReturnedValue());
		    tempEntity.setReturnedValueDict(entity_HI.getReturnedValueDict());
		    tempEntity.setDeveloper(entity_HI.getDeveloper());
			
			
			tempEntity.setLastUpdateDate(new Date());
			tempEntity.setLastUpdatedBy(0);
			saafProjectApiMamagerDAO_HI.update(tempEntity);
		}
		queryParamJSON_.clear();
		//前端要求返回的数据
		queryParamJSON_.put("spaId",
				(null == tempEntity? entity_HI.getSpaId():tempEntity.getSpaId() ));
		SaafProjectApiMamagerEntity_HI tempEntity_ = findSaafProjectApiMamagerInfo(queryParamJSON_);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, tempEntity_);
		return resultStr.toString();
	}
	public String deleteSaafProjectApiManagerInfo(JSONObject queryParamJSON) {

		SaafProjectApiMamagerEntity_HI entity_HI = JSON.parseObject(queryParamJSON.toString(), SaafProjectApiMamagerEntity_HI.class);
	    

		saafProjectApiMamagerDAO_HI.delete(entity_HI);

		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, "delete success");
		return resultStr.toString();
	}
	public static void main(String[] args) {
		SaafProjectApiMamagerServer saafProjectApiMamagerServer = (SaafProjectApiMamagerServer)SaafToolUtils.context.getBean("saafProjectApiMamagerServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = saafProjectApiMamagerServer.findSaafProjectApiMamagerInfo(paramJSON,1,10);
	}
}
