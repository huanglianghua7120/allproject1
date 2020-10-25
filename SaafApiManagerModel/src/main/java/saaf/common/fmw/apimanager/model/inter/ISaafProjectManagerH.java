package saaf.common.fmw.apimanager.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import saaf.common.fmw.apimanager.model.entities.SaafProjectManagerHEntity_HI;

public interface ISaafProjectManagerH {

	String findSaafProjectManagerInfo(JSONObject queryParamJSON,Integer curIndex,Integer pageSize);

	String saveProjectManagerAllInfo(JSONObject queryParamJSON);
	
   String deleteSaafProjectManagerInfo(JSONObject queryParamJSON);

}
