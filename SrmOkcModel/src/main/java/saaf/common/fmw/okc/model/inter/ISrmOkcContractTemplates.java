package saaf.common.fmw.okc.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.okc.model.entities.SrmOkcContractTemplatesEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractTemplatesEntity_HI_RO;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：ISrmOkcContractTemplates.java
 * Description：合同模版服务类接口
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date     @Author(Updated By)     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019/5/31      欧阳岛          创建
 * V1.1       2019/7/24      秦晓钊          修改，新增方法findContractTemplatesToLov
 * <p>
 * ==============================================================================
 */
public interface ISrmOkcContractTemplates {

    /**
     * Description：分页查询合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    Pagination<SrmOkcContractTemplatesEntity_HI_RO> findSrmOkcContractTemplatesList(
            JSONObject parameters, Integer pageIndex, Integer pageRows);

    /**
     * Description：保存合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    JSONObject saveSrmOkcContractTemplates(JSONObject queryParamJSON);

    void updateSrmOkcContractTemplates(SrmOkcContractTemplatesEntity_HI srmOkcContractTemplatesEntity_HI);

    /**
     * Description：提交合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    JSONObject doSubmitSrmOkcContractTemplates(JSONObject queryParamJSON);

    /**
     * Description：审批合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    JSONObject doApprovalSrmOkcContractTemplates(JSONObject queryParamJSON) throws Exception;

    /**
     * Description：驳回合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    JSONObject doRejectSrmOkcContractTemplates(JSONObject queryParamJSON);

    /**
     * Description：更新合同模版版本
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    JSONObject doChangeSrmOkcContractTemplates(JSONObject queryParamJSON);

    /**
     * Description：删除合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    JSONObject deleteSrmOkcContractTemplates(JSONObject params) throws Exception;

    /**
     * Description：查询单个合同模版信息
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    JSONObject findSrmOkcContractTemplate(JSONObject jsonParam);

    /**
     * Description：获取合同模板
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/13      欧阳岛          创建
     * ==============================================================================
     */
    SrmOkcContractTemplatesEntity_HI findSrmOkcContractTemplatesEntity_HI(Integer templateId);

    /**
     * Description：Lov分页查询合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.1       2019/7/24      秦晓钊          创建
     * ==============================================================================
     */
    Pagination<SrmOkcContractTemplatesEntity_HI_RO> findContractTemplatesToLov(
            JSONObject parameters, Integer pageIndex, Integer pageRows);
}
