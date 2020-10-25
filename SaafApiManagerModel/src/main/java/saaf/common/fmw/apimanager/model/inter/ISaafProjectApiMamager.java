package saaf.common.fmw.apimanager.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import saaf.common.fmw.apimanager.model.entities.SaafProjectApiMamagerEntity_HI;

public interface ISaafProjectApiMamager {

	String findSaafProjectApiMamagerInfo(JSONObject queryParamJSON,int curIndex,int pageSize);

	//Object saveSaafProjectApiMamagerInfo(JSONObject queryParamJSON);
	
	SaafProjectApiMamagerEntity_HI findSaafProjectApiMamagerInfo(JSONObject queryParamJSON);
	
    String saveSaafProjectApiMamagerInfo(JSONObject queryParamJSON);
    
    String deleteSaafProjectApiManagerInfo(JSONObject queryParamJSON);

}
