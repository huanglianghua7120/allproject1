package saaf.common.fmw.bpm.model.inter.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.ActReProcdefEntity_HI;
import saaf.common.fmw.bpm.model.entities.readonly.ActReProcdefEntity_HI_RO;
import saaf.common.fmw.bpm.model.inter.IActReProcdef;
import saaf.common.fmw.common.utils.SaafToolUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
/**
 * 获取流程的部署版本信息
 * 用于流程启动
 */
@Component("actReProcdefServer")
public class ActReProcdefServer implements IActReProcdef {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActReProcdefServer.class);
    @Autowired
    private ViewObject<ActReProcdefEntity_HI> actReProcdefDAO_HI;
    @Autowired
    private DynamicViewObjectImpl<ActReProcdefEntity_HI_RO> saafActReProcdefDAO_HI_RO;

    public ActReProcdefServer() {
        super();
    }

    public String findActReProcdefInfo(JSONObject queryParamJSON, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        //根据key_分组，查询最新版本号的总记录数
        String countString = "select count(1) from (select key_,max(version_) as version_ from Act_Re_Procdef group by key_) tmp ";
        
        StringBuffer sb = new StringBuffer();
        sb.append(ActReProcdefEntity_HI_RO.QUERY_SQL);
        Pagination<ActReProcdefEntity_HI_RO> findPagination = saafActReProcdefDAO_HI_RO.findPagination(sb, countString, queryParamMap, pageIndex, pageRows);
        
//        Pagination<ActReProcdefEntity_HI> findPagination = actReProcdefDAO_HI.findPagination("FROM ActReProcdefEntity_HI", queryParamMap, pageIndex, pageRows);
//        List<ActReProcdefEntity_HI> findListResult = findPagination.getData();
//        Map<String, ActReProcdefEntity_HI> actReProcdefEntity_HIMap = new HashMap<String, ActReProcdefEntity_HI>();
//        for(int i=0; i<findListResult.size(); i++){
//            ActReProcdefEntity_HI actReProcdefEntity_HI = findListResult.get(i);
//            String key_ = actReProcdefEntity_HI.getKey_();
//            Integer version_ = actReProcdefEntity_HI.getVersion_();
//            ActReProcdefEntity_HI entity_HI = actReProcdefEntity_HIMap.get(key_);
//            if(null == entity_HI){
//                actReProcdefEntity_HIMap.put(key_, actReProcdefEntity_HI);        
//            }else{
//                Integer exitVersion_ = entity_HI.getVersion_();
//                if(exitVersion_ > version_){
//                    continue;    
//                }else{
//                    actReProcdefEntity_HIMap.put(key_, actReProcdefEntity_HI);
//                }
//            }
//        }
//        Collection<ActReProcdefEntity_HI> values = actReProcdefEntity_HIMap.values();
//        findListResult.clear();
//        findListResult.addAll(values);
//        findPagination.setCount(countList.size());
//        findPagination.setData(findListResult);
        //JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findPagination.getCount(), findPagination);
        return JSON.toJSONString(findPagination);
    }

	@Override
	public ActReProcdefEntity_HI getById(String Id) {
		return actReProcdefDAO_HI.getById(Id);
	}

	@Override
	public String findProcessDefinitions(JSONObject queryParamJSON,
			Integer pageIndex, Integer pageRows) {
		StringBuffer sb = new StringBuffer();
		sb.append("from ActReProcdefEntity_HI where 1=1 ");
		
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		SaafToolUtils.parperParam(queryParamJSON, "key_", sb, queryParamMap, "=");
		Pagination<ActReProcdefEntity_HI> findPagination = actReProcdefDAO_HI.findPagination(sb, queryParamMap, pageIndex, pageRows);
        
		return JSON.toJSONString(findPagination);
	}
	
	
}
