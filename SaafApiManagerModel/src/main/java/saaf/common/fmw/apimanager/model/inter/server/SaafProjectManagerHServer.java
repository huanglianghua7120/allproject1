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

import saaf.common.fmw.apimanager.model.entities.SaafProjectManagerHEntity_HI;
import saaf.common.fmw.apimanager.model.entities.SaafProjectManagerLEntity_HI;
import saaf.common.fmw.apimanager.model.inter.ISaafProjectManagerH;
import saaf.common.fmw.apimanager.utils.SaafToolUtils;

@Component("saafProjectManagerHServer")
public class SaafProjectManagerHServer implements ISaafProjectManagerH{
	private static final Logger LOGGER = LoggerFactory.getLogger(SaafProjectManagerHServer.class);
	@Autowired
	private ViewObject<SaafProjectManagerHEntity_HI> saafProjectManagerHDAO_HI;

	@Autowired
	private ViewObject<SaafProjectManagerLEntity_HI> saafProjectManagerLDAO_HI;
	
	@Autowired
	private SaafProjectManagerLServer saafProjectManagerLServer;
	public SaafProjectManagerHServer() {
		super();
	}

	public String findSaafProjectManagerInfo(JSONObject queryParamJSON, Integer curIndex, Integer pageSize) {
		// Map<String, Object> queryParamMap =
		// SToolUtils.fastJsonObj2Map(queryParamJSON);
		SaafProjectManagerHEntity_HI msEntity_HI = JSON.parseObject(queryParamJSON.toString(),
				SaafProjectManagerHEntity_HI.class);
		StringBuffer sbhql = new StringBuffer();
		sbhql.append(" from SaafProjectManagerHEntity_HI s where 1=1 ");
		Map<String, Object> map = new HashMap<String, Object>();
		// 利用java反射机制
		Field[] fields = msEntity_HI.getClass().getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			if (StringUtils.isEmpty(queryParamJSON.getString(fieldName)))
				continue;
			if ("sphId".equals(fieldName))// 前端详情查询某一条数据
				sbhql.append(" and  s." + fieldName + " = " + queryParamJSON.getString(fieldName) + " ");
			else
				sbhql.append(" and  s." + fieldName + " like '%" + queryParamJSON.getString(fieldName) + "%' ");
		}

		Pagination<SaafProjectManagerHEntity_HI> findListResult = saafProjectManagerHDAO_HI.findPagination(sbhql, map,
				curIndex, pageSize);
		JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(findListResult));
		jsonObject.put("status", "S");
		String resultData = JSON.toJSONString(jsonObject);
		System.out.println(resultData);
		return resultData;
	}
	public SaafProjectManagerHEntity_HI findSaafProjectManagerInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		StringBuffer sbhql = new StringBuffer();
		sbhql.append(" from SaafProjectManagerHEntity_HI s where 1=1");
		if (null != queryParamJSON.getString("sphId")) {
			sbhql.append(" and s.sphId = :sphId ");
		}
		SaafProjectManagerHEntity_HI entity_HI = saafProjectManagerHDAO_HI.get(sbhql.toString(), queryParamMap);
		return entity_HI;
	}
	
	 /**
     * 新增修改主从表数据
     * @param queryParamJSON
     * @return
     */
	public String saveProjectManagerAllInfo(JSONObject queryParamJSON) {


		SaafProjectManagerHEntity_HI pmentity_HI = JSON.parseObject(queryParamJSON.toString(), SaafProjectManagerHEntity_HI.class);
		JSONObject queryParamJSON_ = new JSONObject();
		JSONObject resultStr;
		queryParamJSON_.put("sphId",
				null == pmentity_HI.getSphId() ? 0 : pmentity_HI.getSphId());
		SaafProjectManagerHEntity_HI tempEntity =findSaafProjectManagerInfo(queryParamJSON_);
		StringBuffer sbhql = new StringBuffer();
		sbhql.append(" from SaafProjectManagerHEntity_HI h where 1=1 ");
		if (null != pmentity_HI.getProjectCode()) {
			sbhql.append(" and h.projectCode = '"+pmentity_HI.getProjectCode()+"' ");
		}
		if (null != tempEntity) {//修改时判断
			sbhql.append(" and h.sphId <> "+tempEntity.getSphId());
		}
		SaafProjectManagerHEntity_HI entity_HI = saafProjectManagerHDAO_HI.get(sbhql.toString(), new HashMap<String, Object>());
		
        if(entity_HI!=null){
        	
        	resultStr = SToolUtils.convertResultJSONObj("F", "", 1, pmentity_HI.getProjectCode()+"不允许保存多条");
        	return String.valueOf(resultStr);
        }

		//LOGGER.info(JSONObject.toJSONString(entity_HI));
		if (null == tempEntity) {
			saafProjectManagerHDAO_HI.save(pmentity_HI);
		} else {

			tempEntity.setProjectName(pmentity_HI.getProjectName());
			tempEntity.setProjectCode(pmentity_HI.getProjectCode());
			
			
			tempEntity.setLastUpdateDate(new Date());
			tempEntity.setLastUpdatedBy(0);
			saafProjectManagerHDAO_HI.update(tempEntity);
		}
		 resultStr = SToolUtils.convertResultJSONObj("S", "", 1, "save success");
		return resultStr.toString();
	
	
	}

	public String deleteSaafProjectManagerInfo(JSONObject queryParamJSON) {

		SaafProjectManagerHEntity_HI entity = JSON.parseObject(queryParamJSON.toString(),
				SaafProjectManagerHEntity_HI.class);

		saafProjectManagerHDAO_HI.delete(entity);

		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, "delete success");
		return resultStr.toString();
	}
    
	public static void main(String[] args) {
		SaafProjectManagerHServer saafProjectManagerServer = (SaafProjectManagerHServer)SaafToolUtils.context.getBean("saafProjectManagerHServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = saafProjectManagerServer.findSaafProjectManagerInfo(paramJSON,1,10);
	}
}
