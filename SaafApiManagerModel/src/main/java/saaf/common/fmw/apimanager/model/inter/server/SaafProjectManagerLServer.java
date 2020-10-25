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

import saaf.common.fmw.apimanager.model.entities.SaafProjectManagerLEntity_HI;
import saaf.common.fmw.apimanager.model.inter.ISaafProjectManagerL;
import saaf.common.fmw.apimanager.utils.SaafToolUtils;

@Component("saafProjectManagerLServer")
public class SaafProjectManagerLServer implements ISaafProjectManagerL{
	private static final Logger LOGGER = LoggerFactory.getLogger(SaafProjectManagerLServer.class);
	@Autowired
	private ViewObject<SaafProjectManagerLEntity_HI> saafProjectManagerLDAO_HI;
	public SaafProjectManagerLServer() {
		super();
	}

	public String findSaafProjectManagerLInfo(JSONObject queryParamJSON) {
		//Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		SaafProjectManagerLEntity_HI msEntity_HI = JSON.parseObject(queryParamJSON.toString(),
				SaafProjectManagerLEntity_HI.class);
		StringBuffer sfhql = new StringBuffer();
		sfhql.append(" from SaafProjectManagerLEntity_HI s where 1=1 ");
		Map<String, Object> map =new HashMap<String, Object>();
		//利用java反射机制 
		 Field[] fields = msEntity_HI.getClass().getDeclaredFields();
		 for(Field field:fields) {
			 String fieldName = field.getName();
			 if(StringUtils.isEmpty(queryParamJSON.getString(fieldName)))
				 continue;
			 if("splId".equals(fieldName))//前端详情查询某一条数据
			 sfhql.append(" and  s."+fieldName+" = "+queryParamJSON.getString(fieldName)+" ");
			 else
			 sfhql.append(" and  s."+fieldName+" = '"+queryParamJSON.getString(fieldName)+"' ");
		 }
		Pagination<SaafProjectManagerLEntity_HI> findListResult = saafProjectManagerLDAO_HI.findPagination(sfhql.toString(), map,1,10);
		LOGGER.info("KKKKKKKKKKKKKKKKK==="+JSON.toJSONString(findListResult));
		JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(findListResult));
		jsonObject.put("status", "S");
		String resultData = JSON.toJSONString(jsonObject);
		System.out.println(resultData);
		return resultData;
	}
	public SaafProjectManagerLEntity_HI findSaafProjectManagerLineInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		StringBuffer sbhql = new StringBuffer();
		sbhql.append(" from SaafProjectManagerLEntity_HI s where 1=1");
		if (null != queryParamJSON.getString("splId")) {
			sbhql.append(" and s.splId = :splId ");
		}
		SaafProjectManagerLEntity_HI entity_HI = saafProjectManagerLDAO_HI.get(sbhql.toString(), queryParamMap);
		return entity_HI;
	}
	public String saveSaafProjectManagerLInfo(JSONObject queryParamJSON) {

		SaafProjectManagerLEntity_HI entity_HI = JSON.parseObject(queryParamJSON.toString(), SaafProjectManagerLEntity_HI.class);
		JSONObject queryParamJSON_ = new JSONObject();
		queryParamJSON_.put("splId",
				null == entity_HI.getSplId() ? 0 : entity_HI.getSplId());
		SaafProjectManagerLEntity_HI tempEntity =findSaafProjectManagerLineInfo(queryParamJSON_);
		LOGGER.info(JSONObject.toJSONString(entity_HI));
		if (null == tempEntity) {
			saafProjectManagerLDAO_HI.save(entity_HI);
		} else {

			tempEntity.setModelCode(entity_HI.getModelCode());
			tempEntity.setModelName(entity_HI.getModelName());
			tempEntity.setProjectCode(entity_HI.getProjectCode());
			
			
			tempEntity.setLastUpdateDate(new Date());
			tempEntity.setLastUpdatedBy(0);
			saafProjectManagerLDAO_HI.update(tempEntity);
		}
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, "save success");
		return resultStr.toString();
	
	}
	public String deleteProjectManagerLInfo(JSONObject queryParamJSON) {

		SaafProjectManagerLEntity_HI messageDetailEntity_HI = JSON.parseObject(queryParamJSON.toString(), SaafProjectManagerLEntity_HI.class);
	    

		saafProjectManagerLDAO_HI.delete(messageDetailEntity_HI);

		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, "delete success");
		return resultStr.toString();
	}
	public static void main(String[] args) {
		SaafProjectManagerLServer saafProjectManagerLServer = (SaafProjectManagerLServer)SaafToolUtils.context.getBean("saafProjectManagerLServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = saafProjectManagerLServer.findSaafProjectManagerLInfo(paramJSON);
		System.out.println(resultStr);
	}


}
