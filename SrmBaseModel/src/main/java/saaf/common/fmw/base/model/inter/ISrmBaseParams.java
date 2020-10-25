package saaf.common.fmw.base.model.inter;

import java.util.List;

import saaf.common.fmw.base.model.entities.readonly.SrmBaseParamsEntity_HI_RO;

import com.alibaba.fastjson.JSONObject;


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
public interface ISrmBaseParams {
	
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
	public List<SrmBaseParamsEntity_HI_RO> findBaseParams(JSONObject jsonParams);

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

    JSONObject saveBaseParams(JSONObject params) throws Exception;
}
