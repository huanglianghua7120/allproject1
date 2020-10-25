package saaf.common.fmw.po.model.inter;

import saaf.common.fmw.po.model.entities.readonly.SrmPoForecastDetailInfoEntity_HI_RO;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
/**
 * Project Name：ISrmPoForecastDetailInfo
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
public interface ISrmPoForecastDetailInfo {

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
    public Pagination<SrmPoForecastDetailInfoEntity_HI_RO> findForecastDetailInfoList(JSONObject parameters, Integer pageIndex, Integer pageRows)throws Exception ;
}
