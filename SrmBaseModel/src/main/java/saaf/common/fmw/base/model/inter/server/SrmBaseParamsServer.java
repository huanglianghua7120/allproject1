package saaf.common.fmw.base.model.inter.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.base.model.entities.SrmBaseParamsEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseParamsEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseParams;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：配置样式
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmBaseParamsServer")
public class SrmBaseParamsServer implements ISrmBaseParams {

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseParamsServer.class);

	@Autowired
	private BaseViewObject<SrmBaseParamsEntity_HI_RO> srmBaseParamsDAO_HI_RO;

	@Autowired
	private ViewObject<SrmBaseParamsEntity_HI> srmBaseParamsDAO_HI;

	public SrmBaseParamsServer() {
		super();
	}

	/**
	 * 查询样式
	 * @param jsonParams
	 * @return
	 * Update History
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Override
	public List<SrmBaseParamsEntity_HI_RO> findBaseParams(JSONObject jsonParams) {
		LOGGER.info("查询参数：" + jsonParams.toString());
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(SrmBaseParamsEntity_HI_RO.QUERY_PARAMS_SQL);
			List<SrmBaseParamsEntity_HI_RO> findList = srmBaseParamsDAO_HI_RO.findList(sql.toString(), queryParamMap);
			return findList;
		} catch (Exception e) {
			LOGGER.info("查询异常：" + e.getMessage());
			return null;
		}
	}

	/**
	 * Description：保存样式
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * paramTitle  参数标题  VARCHAR2  N
	 * paramId  参数ID  NUMBER  Y
	 * paramCode  参数编号  VARCHAR2  N
	 * paramComments  参数说明  VARCHAR2  Y
	 * paramValue1  参数值1  VARCHAR2  N
	 * paramValue2  参数值2  VARCHAR2  N
	 * paramValue3  参数值3  VARCHAR2  N
	 * paramValue4  参数值4  VARCHAR2  N
	 * paramValue5  参数值5  VARCHAR2  N
	 * description  描述  VARCHAR2  N
	 * explaining  说明  VARCHAR2  N
	 * paramId  参数ID  NUMBER  Y
	 * paramCode  参数编号  VARCHAR2  N
	 * paramComments  参数说明  VARCHAR2  Y
	 * paramValue1  参数值1  VARCHAR2  N
	 * paramValue2  参数值2  VARCHAR2  N
	 * paramValue3  参数值3  VARCHAR2  N
	 * paramValue4  参数值4  VARCHAR2  N
	 * paramValue5  参数值5  VARCHAR2  N
	 * description  描述  VARCHAR2  N
	 * explaining  说明  VARCHAR2  N
	 * paramTitle  参数标题  VARCHAR2  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */

	@Override
	public JSONObject saveBaseParams(JSONObject params) throws Exception {
		LOGGER.info("保存参数：" + params.toString());
		try {
			List<SrmBaseParamsEntity_HI> baseList = new ArrayList<SrmBaseParamsEntity_HI>();
			JSONArray valuesArray = params.getJSONArray("paramData");
			for (int i = 0; i < valuesArray.size(); i++) {
				JSONObject valuesJson = valuesArray.getJSONObject(i);
				SrmBaseParamsEntity_HI baseRow = null;
				baseRow = srmBaseParamsDAO_HI.getById(valuesJson.getInteger("paramId"));
				Integer operatorUserId = params.getInteger("varUserId");
				baseRow.setParamValue1(valuesJson.getString("paramValue1"));
				baseRow.setOperatorUserId(operatorUserId);
				baseList.add(baseRow);
			}
			srmBaseParamsDAO_HI.updateAll(baseList);
			return SToolUtils.convertResultJSONObj("S", "保存成功", 1, baseList);
		} catch (Exception e) {
			LOGGER.info("保存异常：" + e.getMessage());
			throw new Exception(e);
		}
	}

}
