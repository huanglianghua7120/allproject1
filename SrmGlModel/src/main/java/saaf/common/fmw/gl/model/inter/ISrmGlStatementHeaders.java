package saaf.common.fmw.gl.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.gl.model.entities.readonly.SrmGlStatementHeadersEntity_HI_RO;

public interface ISrmGlStatementHeaders {

    Pagination<SrmGlStatementHeadersEntity_HI_RO> findStatementList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    Pagination<SrmGlStatementHeadersEntity_HI_RO> findStatementLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

}
