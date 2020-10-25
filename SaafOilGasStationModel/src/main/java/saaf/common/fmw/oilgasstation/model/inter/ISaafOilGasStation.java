package saaf.common.fmw.oilgasstation.model.inter;

import saaf.common.fmw.oilgasstation.model.entities.readonly.SaafOilGasStationEntity_HI_RO;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface ISaafOilGasStation {

	Pagination<SaafOilGasStationEntity_HI_RO> findSaafOilGasStationInfo(JSONObject queryParamJSON,Integer pageIndex,Integer pageRows);

	Object saveSaafOilGasStationInfo(JSONObject queryParamJSON);

	JSONObject saveSaafOilGasStationInfos(JSONObject jsonParams);

}
