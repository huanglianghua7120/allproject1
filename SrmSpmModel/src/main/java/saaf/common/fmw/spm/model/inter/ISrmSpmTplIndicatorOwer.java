package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import saaf.common.fmw.spm.model.entities.SrmSpmTplIndicatorOwerEntity_HI;

public interface ISrmSpmTplIndicatorOwer {

	List<SrmSpmTplIndicatorOwerEntity_HI> findSrmSpmTplIndicatorOwerInfo(JSONObject queryParamJSON);

	Object saveSrmSpmTplIndicatorOwerInfo(JSONObject queryParamJSON);

}
