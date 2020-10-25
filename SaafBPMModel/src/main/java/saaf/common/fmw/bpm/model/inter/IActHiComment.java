package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActHiComment{

	String findActHiCommentInfo(JSONObject queryParamJSON);

	String saveActHiCommentInfo(JSONObject queryParamJSON);

}
