package saaf.common.fmw.okc.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractTermsEntity_HI_RO;
/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：ISrmOkcContractTerms.java
 * Description：合同条款服务接口
 *
 * Update History
 * ==============================================================================
 *  Version    Date     @Author(Updated By)     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019/6/4      欧阳岛          创建

 * ==============================================================================
 */
public interface ISrmOkcContractTerms {

    /**
     * Description：合同条款分页查询列表
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    Pagination<SrmOkcContractTermsEntity_HI_RO> findSrmOkcContractTermsList(
            JSONObject parameters, Integer pageIndex, Integer pageRows);

    /**
     * Description：保存合同条款
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    JSONObject saveSrmOkcContractTerms(JSONObject queryParamJSON);

    /**
     * Description：提交合同条款
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    JSONObject doSubmitSrmOkcContractTerms(JSONObject queryParamJSON);

    /**
     * Description：审批合同条款
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    JSONObject doApprovalSrmOkcContractTerms(JSONObject queryParamJSON) throws Exception;

    /**
     * Description：驳回合同条款
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    JSONObject doRejectSrmOkcContractTerms(JSONObject queryParamJSON);

    /**
     * Description：变更合同条款版本
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    JSONObject doChangeSrmOkcContractTerms(JSONObject queryParamJSON);

    /**
     * Description：删除合同条款
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    JSONObject deleteSrmOkcContractTerms(JSONObject params) throws Exception;

    /**
     * Description：查询单条合同条款记录
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    JSONObject findSrmOkcContractTerms(JSONObject jsonParam);

}
