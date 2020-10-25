package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonPaymentTermsEntity_HI_RO;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonPaymentTerms.java
 * Description：寻源--付款条件信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonPaymentTerms {

    /**
     * Description：付款条件查询（分页）
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return Pagination<SrmPonPaymentTermsEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public Pagination<SrmPonPaymentTermsEntity_HI_RO> findSrmPonPaymentTermsInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

    /**
     * Description：保存付款条件及其子表
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject savePonPaymentTermsAll(JSONObject jsonParams);

    /**
     * Description：删除付款条件——根据主键ID（单条数据）及其付款节点
     * @param paymentTermId
     * @return String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public String deletePonPaymentTerm(Integer paymentTermId);

    /**
     * Description：批量删除付款条件
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject deletePonPaymentTermByBatch(JSONObject jsonParams);

    /**
     * Description：批量失效付款条件
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject updateInvalidPonPaymentTermByBatch(JSONObject jsonParams);
}
