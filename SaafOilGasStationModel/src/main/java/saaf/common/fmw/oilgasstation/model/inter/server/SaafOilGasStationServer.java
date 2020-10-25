package saaf.common.fmw.oilgasstation.model.inter.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.oilgasstation.model.dao.readonly.SaafOilGasStationDAO_HI_RO;
import saaf.common.fmw.oilgasstation.model.entities.SaafOilGasStationEntity_HI;
import saaf.common.fmw.oilgasstation.model.entities.readonly.SaafOilGasStationEntity_HI_RO;
import saaf.common.fmw.oilgasstation.model.inter.ISaafOilGasStation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.ViewObjectImpl;
import com.yhg.hibernate.core.paging.Pagination;

@Component("saafOilGasStationServer")
public class SaafOilGasStationServer implements ISaafOilGasStation{
	private static final Logger LOGGER = LoggerFactory.getLogger(SaafOilGasStationServer.class);
	
	@Autowired
	private ViewObjectImpl<SaafOilGasStationEntity_HI> saafOilGasStationDAO_HI;
	@Autowired
	private SaafOilGasStationDAO_HI_RO saafOilGasStationDAO_HI_RO;
	
	public SaafOilGasStationServer() {
		super();
	}

	public Pagination<SaafOilGasStationEntity_HI_RO> findSaafOilGasStationInfo(JSONObject queryParamJSON,Integer pageIndex,Integer pageRows) {
		Map<String, Object> queryParamMap = new HashMap<String,Object>();
		StringBuffer queryString = new StringBuffer();
		queryString.append(SaafOilGasStationEntity_HI_RO.QUERY_GAS_STATION_SQL);
		appendWhereCondition(queryParamJSON, queryParamMap, queryString);
		
		StringBuffer countString = new StringBuffer();
		countString.append("select count(gas_id) from saaf_oil_gas_station saafOilGasStation where 1=1 ");
		appendWhereCondition(queryParamJSON, queryParamMap, countString);
		
		Pagination<SaafOilGasStationEntity_HI_RO> findPagination = saafOilGasStationDAO_HI_RO.findPagination(queryString,countString,queryParamMap,pageIndex, pageRows);
		return findPagination;
	}

	/**
	 * where条件
	 * @param queryParamJSON
	 * @param queryParamMap
	 * @param countString
	 */
	private void appendWhereCondition(JSONObject queryParamJSON,
			Map<String, Object> queryParamMap, StringBuffer countString) {
		SaafToolUtils.parperParam(queryParamJSON, "saafOilGasStation.title", "title", countString, queryParamMap, "like");
		SaafToolUtils.parperParam(queryParamJSON, "saafOilGasStation.province", "province", countString, queryParamMap, "like");
		SaafToolUtils.parperParam(queryParamJSON, "saafOilGasStation.city", "city", countString, queryParamMap, "like");
	}
	
	public Object saveSaafOilGasStationInfo(JSONObject queryParamJSON) {
		SaafOilGasStationEntity_HI saafOilGasStationEntity_HI = JSON.parseObject(queryParamJSON.toString(), SaafOilGasStationEntity_HI.class);
		Object resultData = saafOilGasStationDAO_HI.save(saafOilGasStationEntity_HI);
		return resultData;
	}
	
	public List<String> findAllOilGasStationUid(){
		StringBuffer sb = new StringBuffer();
		sb.append(SaafOilGasStationEntity_HI_RO.QUERY_UID_SQL);
		Object[] params = null;
		List<SaafOilGasStationEntity_HI_RO> list = saafOilGasStationDAO_HI_RO.findList(sb, params);
		List<String> uids = new ArrayList<String>();
		if(list != null && !list.isEmpty()){
			for(SaafOilGasStationEntity_HI_RO entity : list){
				uids.add(entity.getUid());
			}
		}
		return uids;
	}

	@Override
	public JSONObject saveSaafOilGasStationInfos(JSONObject jsonParams) {
		
		int ignoreNum = 0,saveNum = 0;
		
		List<SaafOilGasStationEntity_HI> paramList = new ArrayList<SaafOilGasStationEntity_HI>();
		JSONArray datas = jsonParams.getJSONArray("datas");
		if(datas != null && !datas.isEmpty()){
			
			List<String> uids = this.findAllOilGasStationUid();
			
			Iterator<Object> it = datas.iterator();
			while(it.hasNext()){
				JSONObject dataObj = JSON.parseObject(it.next().toString());
				
				if(uids.contains(dataObj.getString("uid"))){
					ignoreNum++;
					continue;
				}
				saveNum++;
				
				SaafOilGasStationEntity_HI entity = new SaafOilGasStationEntity_HI();
				entity.setOperatorUserId(jsonParams.getInteger("varUserId"));
				entity.setAddress(dataObj.getString("address"));
				entity.setCity(dataObj.getString("city"));
				entity.setDetailurlBd(dataObj.getString("detailUrl"));
				entity.setPhonenumber(dataObj.getString("phoneNumber"));
				
				JSONObject point = dataObj.getJSONObject("point");
				entity.setPointLat(point.getString("lat"));
				entity.setPointLng(point.getString("lng"));
				
				entity.setPostcode(dataObj.getString("postcode"));
				entity.setProvince(dataObj.getString("province"));
				
				JSONArray tags = dataObj.getJSONArray("tags");
				if(tags != null && !tags.isEmpty()){
					for(int i=0;i<tags.size();i++){
						if(i==0)
							entity.setTags0(tags.getString(0));
						else if(i==1)
							entity.setTags1(tags.getString(1));
						else if(i==2)
							entity.setTags2(tags.getString(2));
					}
				}
				
				entity.setTitle(dataObj.getString("title"));
				entity.setUid(dataObj.getString("uid"));
				entity.setUrl(dataObj.getString("url"));
				
				paramList.add(entity);
			}
			saafOilGasStationDAO_HI.saveAll(paramList);
		}
		
		JSONObject retObj = new JSONObject();
		retObj.put("status", "S");
		retObj.put("msg", "保存"+jsonParams.getString("cityName")+"加油站成功:保存"+saveNum+"条；忽略"+ignoreNum+"条");
		retObj.put("count", paramList.size());
		retObj.put("data", null);
		return retObj;
	}
}
