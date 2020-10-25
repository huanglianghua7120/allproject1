package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActHiAttachment{

	String findActHiAttachmentInfo(JSONObject queryParamJSON);

	String saveActHiAttachmentInfo(JSONObject queryParamJSON);

}
