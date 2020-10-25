package saaf.common.fmw.base.model.inter;

import com.alibaba.fastjson.JSONObject;
import java.util.List;

import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseExchangeRateEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseExchangeRateEntity_HI_RO;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：汇率列表
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmBaseExchangeRate {

    /**
     * 查询汇率列表
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmBaseExchangeRateEntity_HI_RO> findBaseExchangeRate(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询默认汇率类型
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SaafLookupValuesEntity_HI findDefaulRateType() throws Exception;

    /**
     * Description：保存汇率
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * exchangeRateId  汇率表ID  NUMBER  Y
     * exchangeRate  汇率  NUMBER  N
     * rateType  汇率类型  VARCHAR2  N
     * originalCurrency  原币种  VARCHAR2  N
     * targetCurrency    VARCHAR2  N
     * rateDate    DATE  N
     * effectiveStartDate    DATE  N
     * effectiveEndDate  有效结束日期  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * exchangeRateId  汇率ID  NUMBER  Y
     * exchangeRate  汇率  NUMBER  N
     * rateType  汇率类型  VARCHAR2  N
     * originalCurrency  原币种  VARCHAR2  N
     * targetCurrency  目标币种  VARCHAR2  N
     * effectiveStartDate  有效开始日期  DATE  N
     * effectiveEndDate  有效结束日期  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    JSONObject saveBaseExchangeRate(JSONObject params) throws Exception;

    /**
     * 删除汇率
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject deleteExchangeRate(JSONObject params) throws Exception;

    /**
     * Description：批量导入
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * exchangeRateId  汇率表ID  NUMBER  Y
     * exchangeRate  汇率  NUMBER  N
     * rateType  汇率类型  VARCHAR2  N
     * originalCurrency  原币种  VARCHAR2  N
     * targetCurrency    VARCHAR2  N
     * rateDate    DATE  N
     * effectiveStartDate    DATE  N
     * effectiveEndDate  有效结束日期  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * exchangeRateId  汇率ID  NUMBER  Y
     * exchangeRate  汇率  NUMBER  N
     * rateType  汇率类型  VARCHAR2  N
     * originalCurrency  原币种  VARCHAR2  N
     * targetCurrency  目标币种  VARCHAR2  N
     * effectiveStartDate  有效开始日期  DATE  N
     * effectiveEndDate  有效结束日期  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    JSONObject saveImportExchangeRate(JSONObject jsonParams,Integer userId);
}
