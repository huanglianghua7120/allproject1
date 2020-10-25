package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import java.util.Map;

import saaf.common.fmw.spm.model.entities.SrmSpmRequestNoticeEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmRequestNoticeEntity_HI_RO;

public interface ISrmSpmRequestNotice {

	List<SrmSpmRequestNoticeEntity_HI> findSrmSpmRequestNoticeInfo(JSONObject queryParamJSON);

	Object saveSrmSpmRequestNoticeInfo(JSONObject queryParamJSON);

	Pagination<SrmSpmRequestNoticeEntity_HI_RO> RequestNoticeList(JSONObject paramJSON, Integer pageIndex,Integer pageRows);

	Map<String, Object> getNoticeResults(JSONObject jsonParam);

}
