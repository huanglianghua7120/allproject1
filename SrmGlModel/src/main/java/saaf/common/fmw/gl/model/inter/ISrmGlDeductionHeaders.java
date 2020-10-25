package saaf.common.fmw.gl.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.gl.model.entities.readonly.SrmGlDeductionHeadersEntity_HI_RO;

public interface ISrmGlDeductionHeaders {

    Pagination<SrmGlDeductionHeadersEntity_HI_RO> findDeductionInfoList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    Pagination<SrmGlDeductionHeadersEntity_HI_RO> findDeductionNumber(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

    Pagination<SrmGlDeductionHeadersEntity_HI_RO> findDeductionCreatedByUser(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;
}
