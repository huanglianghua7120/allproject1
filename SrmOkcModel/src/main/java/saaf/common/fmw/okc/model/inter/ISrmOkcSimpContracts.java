package saaf.common.fmw.okc.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface ISrmOkcSimpContracts {

    /**
     * 查询列表
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     */
    Pagination<JSONObject> findSrmOkcSimpContractsList(JSONObject parameters, Integer supplierId, Integer pageIndex, Integer pageRows);

    /**
     * Description：供应商“发起供方确认”及已签订合同列表
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/24      欧阳岛          创建
     * ==============================================================================
     */
    Pagination<JSONObject> findSrmOkcEnhanceContractsList(JSONObject parameters, Integer supplierId, Integer pageIndex, Integer pageRows);

    /**
     * Description：确认合同
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/24      欧阳岛          创建
     * ==============================================================================
     */
    String doConfirmContract(Integer contractId, Integer supplierId, Integer operatorId);

    /**
     * Description：拒绝合同
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/24      欧阳岛          创建
     * ==============================================================================
     */
    String doRejectContract(Integer contractId, Integer supplierId, Integer operatorId);
}
