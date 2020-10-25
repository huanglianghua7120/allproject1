package saaf.common.fmw.genform.model.inter;

import saaf.common.fmw.genform.model.entities.SaafDynamicFormInfoEntity_HI;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface ISaafDynamicFormInfo{

	String findPagenation(int pageIndex,int pageSize,JSONObject queryParamJSON);
	
	String findSaafDynamicFormInfoInfo(JSONObject queryParamJSON);

	String saveSaafDynamicFormInfoInfo(JSONObject queryParamJSON);

	boolean deleteSaafDynamicFormInfo(Integer id);
	
	SaafDynamicFormInfoEntity_HI getById(Integer id);

}
