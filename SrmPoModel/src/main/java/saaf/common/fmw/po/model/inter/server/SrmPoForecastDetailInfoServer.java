package saaf.common.fmw.po.model.inter.server;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.readonly.SrmPoForecastDetailInfoEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoForecastDetailInfo;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.paging.Pagination;
/**
 * Project Name：SrmPoForecastDetailInfoServer
 * Company Name：SIE
 * Program Name：
 * Description：预测信息供应商明细
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoForecastDetailInfoServer")
public class SrmPoForecastDetailInfoServer implements ISrmPoForecastDetailInfo {

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoForecastDetailInfoServer.class);

	@Autowired
	private BaseViewObject<SrmPoForecastDetailInfoEntity_HI_RO> baseViewObject;
    /**
     * Description：查询采购预测明细
     * @param parameters 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@Override
	public Pagination<SrmPoForecastDetailInfoEntity_HI_RO> findForecastDetailInfoList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
		// 预测信息供应商明细表		
		StringBuffer queryString = new StringBuffer(SrmPoForecastDetailInfoEntity_HI_RO.QUERY_FORECAST_DETAIL_INFO_SQL);
		Map<String, Object> map = new HashMap<String, Object>();
		SaafToolUtils.parperParam(parameters, "l.FORECAST_ID","forecastId", queryString, map, "=");
		//总行数
		StringBuffer countSb = new StringBuffer("select count(1) from ("+queryString+")");
		queryString.append(" ORDER BY l.LAST_UPDATE_DATE DESC");
		Pagination<SrmPoForecastDetailInfoEntity_HI_RO> h2Entity_HI_ROs=baseViewObject.findPagination(queryString, countSb,map, pageIndex, pageRows);
		return h2Entity_HI_ROs;
	}

}
