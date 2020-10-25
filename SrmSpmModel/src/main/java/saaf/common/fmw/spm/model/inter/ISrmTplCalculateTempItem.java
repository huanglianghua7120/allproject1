package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import saaf.common.fmw.spm.model.entities.SrmTplCalculateTempItemEntity_HI;

public interface ISrmTplCalculateTempItem {

	List<SrmTplCalculateTempItemEntity_HI> findSrmTplCalculateTempItemInfo(JSONObject queryParamJSON);

	Object saveSrmTplCalculateTempItemInfo(JSONObject queryParamJSON);

}
