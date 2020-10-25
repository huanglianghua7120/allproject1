package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.SrmSpmIndicatorItemsEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmManualScoreEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmManualScoreEntity_HI_RO;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：加减分查询接口
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     liuwenjun             创建
 * ===========================================================================
 */
public interface ISrmSpmManualScore {

	List<SrmSpmManualScoreEntity_HI> findSrmSpmManualScoreInfo(JSONObject queryParamJSON);

	Object saveSrmSpmManualScoreInfo(JSONObject queryParamJSON);

    /**
     * Description：查询加减分
     * @param paramJSON 加减分查询参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	Pagination<SrmSpmManualScoreEntity_HI_RO> findManualInfoList(JSONObject paramJSON, Integer pageIndex,Integer pageRows);
    /**
     * Description：删除加减分
     * @param jsonParams
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	Object deleteManualScore(JSONObject jsonParams)throws Exception;

    /**
     * Description：保存加减分
     * @param jsonParams 加减分参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	Object saveManualScore(JSONObject jsonParams)throws Exception;
    /**
     * Description：生效加减分
     * @param jsonParams
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	Object updateEffectiveManualScore(JSONObject jsonParams)throws Exception;
    /**
     * Description：指标录入项失效
     * @param jsonParams
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	Object updateInvalidManualScore(JSONObject jsonParams)throws Exception;
    /**
     * Description：指标录入项导出
     * @param jsonParams
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	List<SrmSpmManualScoreEntity_HI_RO> exportManualScore(JSONObject jsonParams)throws Exception;
    /**
     * Description：查询绩效类型
     * @param jsonParam
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	List<SaafLookupValuesEntity_HI_RO> selectindicatorType(JSONObject jsonParam)throws Exception;
    /**
     * Description：查询绩效类型
     * @param jsonParam
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	Map<String,Object> findManualIndicatorType(JSONObject jsonParam)throws Exception;

	SrmSpmIndicatorItemsEntity_HI findManualIndicatorItems(JSONObject jsonParam);
    /**
     * Description：导入
     * @param jsonParam
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	JSONObject saveManualImport(JSONObject jsonParam)throws Exception;

	public List<SrmSpmManualScoreEntity_HI_RO> caculateManualScore(Integer supplierId, String happenedDateFrom, String happenedDateTo);


}
