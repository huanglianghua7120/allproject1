package saaf.common.fmw.apimanager.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import saaf.common.fmw.apimanager.model.entities.SaafProjectManagerLEntity_HI;

public interface ISaafProjectManagerL {

	String findSaafProjectManagerLInfo(JSONObject queryParamJSON);
	String saveSaafProjectManagerLInfo(JSONObject queryParamJSON);
	String deleteProjectManagerLInfo(JSONObject queryParamJSON);

}
