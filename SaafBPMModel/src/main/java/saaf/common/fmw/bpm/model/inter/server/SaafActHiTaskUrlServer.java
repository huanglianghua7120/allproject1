package saaf.common.fmw.bpm.model.inter.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.readonly.SaafActHiTaskUrlEntity_HI_RO;
import saaf.common.fmw.bpm.model.inter.ISaafActHiTaskUrl;
import saaf.common.fmw.common.model.inter.server.SessionBeanServer;


@Component("saafActHiTaskUrlServer")
public class SaafActHiTaskUrlServer extends SessionBeanServer implements ISaafActHiTaskUrl{
    @Autowired
    private DynamicViewObjectImpl<SaafActHiTaskUrlEntity_HI_RO> saafActHiTaskUrlDAO_HI_RO;
    public SaafActHiTaskUrlServer() {
        super();
    }

    public String findActTaskHistoryUrl(JSONObject queryParamJSON, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        StringBuffer querySQL = new StringBuffer(SaafActHiTaskUrlEntity_HI_RO.QUERY_SQL);
        if(!getUserInfoSessionBean().getIsAdmin().equals("Y")){
        	querySQL.append(" and ACTHI.ASSIGNEE_ = :assignee_");
        }
        Pagination<SaafActHiTaskUrlEntity_HI_RO> findPagination = saafActHiTaskUrlDAO_HI_RO.findPagination(querySQL, queryParamMap, pageIndex, pageRows);
        //JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findPagination.getCount(), findPagination);
        return JSON.toJSONString(findPagination);   
    }
}
