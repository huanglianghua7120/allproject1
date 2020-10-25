package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.po.model.entities.readonly.SrmPoForecastLinesEntity_HI_RO;
/**
 * Project Name：ISrmPoForecastLines
 * Company Name：SIE
 * Program Name：
 * Description：采购预测行
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
public interface ISrmPoForecastLines {

    /**
     * Description：采购预测行删除
     * @param jsonParam 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	JSONObject deleteForecastLine(JSONObject jsonParam)throws Exception;

    /**
     * Description：采购预测物料查询
     * @param jsonParams 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	Pagination<SrmPoForecastLinesEntity_HI_RO> findPoForecastItemLov(JSONObject jsonParams, Integer pageIndex,Integer pageRows) throws Exception;

    /**
     * Description：采购预测物料查询
     * @param jsonParams 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	Pagination<SrmPoForecastLinesEntity_HI_RO> findPoForecastItemLovForLine(JSONObject jsonParams, Integer pageIndex,Integer pageRows) throws Exception;



}
