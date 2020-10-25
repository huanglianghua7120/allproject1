package saaf.common.fmw.bpm.model.inter.server;

import com.alibaba.fastjson.JSONObject;

import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import com.yhg.hibernate.core.paging.Pagination;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.dao.readonly.SaafActTaskHistoryDAO_HI_RO;
import saaf.common.fmw.bpm.model.entities.readonly.SaafActRuTaskUrlEntity_HI_RO;
import saaf.common.fmw.bpm.model.entities.readonly.SaafActTaskHistoryEntity_HI_RO;
import saaf.common.fmw.bpm.model.inter.ISaafActTaskHistory;

@Component("saafActTaskHistoryServer")
public class SaafActTaskHistoryServer implements ISaafActTaskHistory{
    @Autowired
    private DynamicViewObjectImpl<SaafActTaskHistoryEntity_HI_RO> saafActTaskHistoryDAO_HI_RO;
    public SaafActTaskHistoryServer() {
        super();
    }
    
    public String findActTaskHistory(JSONObject queryParamJSON, Integer pageIndex, Integer pageRows){
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        StringBuffer querySQL = new StringBuffer(SaafActTaskHistoryEntity_HI_RO.QUERY_SQL);
        //querySQL.append(" and RUTASK.ASSIGNEE_ = :assignee_");
        Pagination<SaafActTaskHistoryEntity_HI_RO> findPagination = saafActTaskHistoryDAO_HI_RO.findPagination(querySQL, queryParamMap, pageIndex, pageRows);
        JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findPagination.getCount(), findPagination);
        return resultStr.toString();    
    }
}
