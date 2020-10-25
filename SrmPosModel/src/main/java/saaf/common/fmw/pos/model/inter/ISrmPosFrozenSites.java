package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import saaf.common.fmw.pos.model.entities.SrmPosFrozenSitesEntity_HI;

public interface ISrmPosFrozenSites {

	List<SrmPosFrozenSitesEntity_HI> findSrmPosFrozenSitesInfo(JSONObject queryParamJSON);

	Object saveSrmPosFrozenSitesInfo(JSONObject queryParamJSON);

}
