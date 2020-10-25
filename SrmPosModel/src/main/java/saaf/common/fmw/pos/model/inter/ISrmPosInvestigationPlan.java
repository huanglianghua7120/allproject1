package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosInvestigationPlanEntity_HI_RO;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：考察计划
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
public interface ISrmPosInvestigationPlan {

    /**
     * 分页查询考察计划数据
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     zhj             创建
     * ===========================================================================
     */
    public Pagination<SrmPosInvestigationPlanEntity_HI_RO> findSrmPosInvestigationPlanInfoList(JSONObject jsonParams, Integer pageIndex, Integer pageRows);
    /**
     * 根据ID查询考察计划信息
     *
     * @param jsonParams
     * @return
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     zhj             创建
     * ===========================================================================
     */
    public List<SrmPosInvestigationPlanEntity_HI_RO> findInvestigationPlanHeaderInfoById(JSONObject jsonParams);

    /**
     * Description：保存或更新现场考察数据
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * investigationPlanId  考察计划ID  NUMBER  Y
     * planName  计划名称  VARCHAR2  Y
     * planNumber  单据编号  VARCHAR2  Y
     * planStatus  单据状态（POS_APPROVAL_STATUS）  VARCHAR2  Y
     * description  备注说明  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    public JSONObject saveInvestigationPlanInfo(JSONObject jsonParams) throws Exception;

    /**
     * 根据行ID删除考察计划的供应商数据
     *
     * @param jsonParams
     * @return
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     zhj             创建
     * ===========================================================================
     */
    public JSONObject deleteInvestigationPlanInfo(JSONObject jsonParams);
}
