package saaf.common.fmw.spm.model.inter.server;

import saaf.common.fmw.spm.model.inter.ISrmSpmRequestNotice;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafMessageHeadEntity_HI_RO;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.spm.model.entities.SrmSpmRequestNoticeEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmRequestNoticeEntity_HI_RO;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

@Component("srmSpmRequestNoticeServer")
public class SrmSpmRequestNoticeServer implements ISrmSpmRequestNotice {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmRequestNoticeServer.class);

    @Autowired
    private ViewObject<SrmSpmRequestNoticeEntity_HI> srmSpmRequestNoticeDAO_HI;

    @Autowired
    private BaseViewObject<SaafMessageHeadEntity_HI_RO> messageHeadEntityDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmSpmRequestNoticeEntity_HI_RO> srmSpmRequestNoticeDAO_HI_RO;

    public SrmSpmRequestNoticeServer() {
        super();
    }

    public List<SrmSpmRequestNoticeEntity_HI> findSrmSpmRequestNoticeInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<SrmSpmRequestNoticeEntity_HI> findListResult = srmSpmRequestNoticeDAO_HI.findList("from SrmSpmRequestNoticeEntity_HI", queryParamMap);
        return findListResult;
    }

    public Object saveSrmSpmRequestNoticeInfo(JSONObject queryParamJSON) {
        SrmSpmRequestNoticeEntity_HI srmSpmRequestNoticeEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmSpmRequestNoticeEntity_HI.class);
        Object resultData = srmSpmRequestNoticeDAO_HI.save(srmSpmRequestNoticeEntity_HI);
        return resultData;
    }

    @Override
    public Pagination<SrmSpmRequestNoticeEntity_HI_RO> RequestNoticeList(JSONObject paramJSON, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        StringBuffer count = new StringBuffer();
        StringBuffer queryParam = new StringBuffer();
        sb.append(SrmSpmRequestNoticeEntity_HI_RO.QUERY_MANUAL_INFO_LIST);
        count.append(SrmSpmRequestNoticeEntity_HI_RO.QUERY_COUNT);
        SaafToolUtils.parperParam(paramJSON, "spt.attribute1", "noticeId", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(paramJSON, "spt.attribute2", "categoryId", queryParam, queryParamMap, "=");
        sb.append(queryParam);
        count.append(queryParam);
        sb.append(" ORDER BY spt.CREATION_DATE DESC, spt.indicator_id");
        LOGGER.debug(sb.toString());
        Pagination<SrmSpmRequestNoticeEntity_HI_RO> result = srmSpmRequestNoticeDAO_HI_RO.findPagination(sb.toString(), count, queryParamMap, pageIndex, pageRows);
        return result;
    }

    @Override
    public Map<String, Object> getNoticeResults(JSONObject jsonParam) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer(SrmSpmRequestNoticeEntity_HI_RO.QUERY_MANUAL_INFO_LIST);
        sb.append(" AND spt.attribute1 = '" + jsonParam.getInteger("noticeId") + "' AND  spt.attribute2 = '" + jsonParam.getInteger("categoryId") + "'");
        List<SrmSpmRequestNoticeEntity_HI_RO> list = srmSpmRequestNoticeDAO_HI_RO.findList(sb.toString(), queryParamMap);
        if (!list.isEmpty()) {
            int sourceId = list.get(0).getSourceId();
            Map<String, Object> queryParamMapt = new HashMap<String, Object>();
            StringBuffer sbt = new StringBuffer(SaafMessageHeadEntity_HI_RO.QUERY_NEED_SQL);
            sbt.append(" AND smh.attribute1 = '" + jsonParam.getInteger("categoryId") + "' AND smh.attribute2 = '" + jsonParam.getInteger("noticeId") + "' ");
            List<SaafMessageHeadEntity_HI_RO> listY = messageHeadEntityDAO_HI_RO.findList(sbt.toString(), queryParamMapt);
            if (!listY.isEmpty()) {
                map.put("message", listY.get(0).getMessageTex());
            }
        }
        return map;
    }
}
