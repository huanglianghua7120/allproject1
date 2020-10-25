package saaf.common.fmw.spm.model.inter.server;

import saaf.common.fmw.spm.model.inter.ISrmSpmIndicatorItems;
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

import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.SrmSpmIndicatorItemsEntity_HI;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("srmSpmIndicatorItemsServer")
public class SrmSpmIndicatorItemsServer implements ISrmSpmIndicatorItems {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmIndicatorItemsServer.class);

    @Autowired
    private ViewObject<SrmSpmIndicatorItemsEntity_HI> srmSpmIndicatorItemsDAO_HI;

    @Autowired
    private BaseViewObject<SaafLookupValuesEntity_HI_RO> lookupValuesEntityDAO_HI_RO;

    public SrmSpmIndicatorItemsServer() {
        super();
    }


    public List<SrmSpmIndicatorItemsEntity_HI> findSrmSpmIndicatorItemsInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<SrmSpmIndicatorItemsEntity_HI> findListResult = srmSpmIndicatorItemsDAO_HI.findList("FROM SrmSpmIndicatorItemsEntity_HI", queryParamMap);
        return findListResult;
    }

    public Object saveSrmSpmIndicatorItemsInfo(JSONObject queryParamJSON) {
        SrmSpmIndicatorItemsEntity_HI srmSpmIndicatorItemsEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmSpmIndicatorItemsEntity_HI.class);
        Object resultData = srmSpmIndicatorItemsDAO_HI.save(srmSpmIndicatorItemsEntity_HI);
        return resultData;
    }


    @Override
    public List<SaafLookupValuesEntity_HI_RO> selectDimension(JSONObject paramJSON) throws Exception {
        LOGGER.debug(paramJSON.toJSONString());
        String rowId = paramJSON.getString("indicatorDimension");
        StringBuffer queryString = new StringBuffer();
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        if (!"T".equals(rowId)) {
            queryString.append(SaafLookupValuesEntity_HI_RO.QUERY_INVOICEID_LINE_SQL);
            queryParamMap.put("tag", rowId);
        } else {
            queryString.append(SaafLookupValuesEntity_HI_RO.QUERY_INVOICEID_LINET_SQL);
        }
        queryString.append(" AND slv.enabled_flag = 'Y'");
        List<SaafLookupValuesEntity_HI_RO> list = lookupValuesEntityDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        return list;
    }

}
