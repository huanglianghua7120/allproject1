package saaf.common.fmw.spm.model.inter.server;

import saaf.common.fmw.spm.model.inter.ISrmSpmTplIndicatorItems;
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
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.spm.model.entities.SrmSpmTplIndicatorItemsEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplIndicatorItemsEntity_HI_RO;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("srmSpmTplIndicatorItemsServer")
public class SrmSpmTplIndicatorItemsServer implements ISrmSpmTplIndicatorItems{

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmTplIndicatorItemsServer.class);

	@Autowired
	private ViewObject<SrmSpmTplIndicatorItemsEntity_HI> srmSpmTplIndicatorItemsDAO_HI;

	@Autowired
	private BaseViewObject<SaafLookupValuesEntity_HI_RO> lookupValuesEntityDAO_HI_RO;
	
	@Autowired
	private BaseViewObject<SrmSpmTplIndicatorItemsEntity_HI_RO> srmSpmTplIndicatorItemsDAO_HI_RO;

	public SrmSpmTplIndicatorItemsServer() {
		super();
	}
    /**
     *
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	public List<SrmSpmTplIndicatorItemsEntity_HI> findSrmSpmTplIndicatorItemsInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<SrmSpmTplIndicatorItemsEntity_HI> findListResult = srmSpmTplIndicatorItemsDAO_HI.findList("FROM SrmSpmTplIndicatorItemsEntity_HI", queryParamMap);
		return findListResult;
	}
	public Object saveSrmSpmTplIndicatorItemsInfo(JSONObject queryParamJSON) {
		SrmSpmTplIndicatorItemsEntity_HI srmSpmTplIndicatorItemsEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmSpmTplIndicatorItemsEntity_HI.class);
		Object resultData = srmSpmTplIndicatorItemsDAO_HI.save(srmSpmTplIndicatorItemsEntity_HI);
		return resultData;
	}

	@Override
	public List<SrmSpmTplIndicatorItemsEntity_HI_RO> getInvoiceItemsList(JSONObject jsonParams) {
		LOGGER.info(""+jsonParams);
		 StringBuffer queryParam = new StringBuffer(SrmSpmTplIndicatorItemsEntity_HI_RO.QUERY_DIMENSION_INFO_LIST);
		 Map<String, Object> queryParamMap = new HashMap<String, Object>();
		 SaafToolUtils.parperParam(jsonParams, "spit.TPL_INDICATOR_ID", "tplIndicatorId", queryParam, queryParamMap, "=");
		 List<SrmSpmTplIndicatorItemsEntity_HI_RO> list =srmSpmTplIndicatorItemsDAO_HI_RO.findList(queryParam, queryParamMap);
		return list;
	}

	@Override
	public List<SaafLookupValuesEntity_HI_RO> selectTplDimension(JSONObject jsonParam) throws Exception {
		String rowId =jsonParam.getString("indicatorDimension");		
		StringBuffer queryString = new StringBuffer();
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		if(!"T".equals(rowId)) {
			queryString.append(SaafLookupValuesEntity_HI_RO.QUERY_INVOICEID_TPL_SQL);
			queryParamMap.put("tag", rowId);
		}else {
			queryString.append(SaafLookupValuesEntity_HI_RO.QUERY_INVOICEID_TPL_SQL);
		}
		queryString.append(" AND slv.enabled_flag = 'Y'");
		List<SaafLookupValuesEntity_HI_RO> list =lookupValuesEntityDAO_HI_RO.findList(queryString.toString(), queryParamMap);
		return list;
	}



}
