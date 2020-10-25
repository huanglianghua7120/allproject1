package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import java.util.List;

import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.SrmSpmIndicatorItemsEntity_HI;

public interface ISrmSpmIndicatorItems {

	List<SrmSpmIndicatorItemsEntity_HI> findSrmSpmIndicatorItemsInfo(JSONObject queryParamJSON);

	Object saveSrmSpmIndicatorItemsInfo(JSONObject queryParamJSON);

	List<SaafLookupValuesEntity_HI_RO> selectDimension(JSONObject paramJSON)throws Exception;

}
