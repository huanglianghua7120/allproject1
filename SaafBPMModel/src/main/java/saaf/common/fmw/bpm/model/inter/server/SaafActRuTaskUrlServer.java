package saaf.common.fmw.bpm.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.bpm.model.entities.readonly.SaafActRuTaskUrlEntity_HI_RO;
import saaf.common.fmw.bpm.model.inter.ISaafActRuTaskUrl;
import saaf.common.fmw.common.model.inter.server.SessionBeanServer;
import saaf.common.fmw.common.utils.SaafToolUtils;

@Component("saafActRuTaskUrlServer")
public class SaafActRuTaskUrlServer extends SessionBeanServer implements ISaafActRuTaskUrl{
    @Autowired
    private DynamicViewObjectImpl<SaafActRuTaskUrlEntity_HI_RO> saafActRuTaskUrlDAO_HI_RO;
    public SaafActRuTaskUrlServer() {
        super();
    }
    
    public String findRunTaskURLInfo(JSONObject queryParamJSON, Integer pageIndex, Integer pageRows){
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer querySQL = new StringBuffer(SaafActRuTaskUrlEntity_HI_RO.QUERY_SQL);
        SaafToolUtils.parperParam(queryParamJSON, "RUTASK.ASSIGNEE_","assignee_", querySQL, queryParamMap, "=");
        querySQL.append(" order by RUTASK.create_time_ asc");
        Pagination<SaafActRuTaskUrlEntity_HI_RO> findPagination = saafActRuTaskUrlDAO_HI_RO.findPagination(querySQL, queryParamMap, pageIndex, pageRows);
        //JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findPagination.getCount(), findPagination);
        return JSON.toJSONString(findPagination);    
    }
}
