package saaf.common.fmw.po.model.inter;

import saaf.common.fmw.po.model.entities.readonly.SrmPoSupplyProportionLEntity_HI_RO;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供货比例行表
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-20     hgq             modify
 * ==============================================================================
 */
public interface ISrmPoSupplyProportionL {
    /**
     * Description：查询供货比例行表
     * @param parameters 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    public Pagination<SrmPoSupplyProportionLEntity_HI_RO> findSupplyProportionL(JSONObject parameters, Integer pageIndex, Integer pageRows)throws Exception ;
    
    /**
     * Description：保存供货列表详情
     * @param params 参数
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    public JSONObject saveSupplyProportionL(JSONObject params) throws Exception;
}
