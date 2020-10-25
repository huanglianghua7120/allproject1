package saaf.common.fmw.pos.model.inter;

 

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.pos.model.entities.readonly.SrmPosManagerCatesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosManagerCatesLinesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosManagerCatesOthersEntity_HI_RO;



/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：需求品类
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
public interface ISrmManagerCates {

 
    /**
     * 查询品类列表
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmPosManagerCatesEntity_HI_RO> findManagerCatesList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    
    /**
     * 删除品类
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject deleteManagerCates(JSONObject params)throws Exception;

    /**
     * Description：品类增加修改
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * managerCateId  新增品类单ID  NUMBER  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  业务实体ID,关联表:srm_base_orgs(废弃)  NUMBER  N
     * documentType  单据类型(POS_MANAGER_CATE_TYPE)  VARCHAR2  Y
     * documentNumber  新增品类单号  VARCHAR2  Y
     * documentStatus  新增品类单状态（POS_QUALIFICATION_STATUS）  VARCHAR2  N
     * needOnsiteInspection  是否现场考察  VARCHAR2  N
     * supplierAdvantage  供方优势  VARCHAR2  N
     * description  说明  VARCHAR2  N
     * reason  原因  VARCHAR2  N
     * fileId  附件id  NUMBER  N
     * managerCateId  新增品类单ID  NUMBER  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  业务实体ID,关联表:srm_base_orgs(废弃)  NUMBER  N
     * documentType  单据类型(POS_MANAGER_CATE_TYPE)  VARCHAR2  Y
     * documentNumber  新增品类单号  VARCHAR2  Y
     * documentStatus  新增品类单状态（POS_QUALIFICATION_STATUS）  VARCHAR2  N
     * needOnsiteInspection  是否现场考察  VARCHAR2  N
     * supplierAdvantage  供方优势  VARCHAR2  N
     * description  说明  VARCHAR2  N
     * reason  原因  VARCHAR2  N
     * fileId  附件id  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    public JSONObject saveManaerCates(JSONObject params)throws Exception;
    
    /**
     * 品类提交
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject updateManaerCates(JSONObject params)throws Exception;

    /**
     * 查询用品类处理类别
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmPosManagerCatesLinesEntity_HI_RO> findManagerCatesLinesList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询其他品类
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmPosManagerCatesOthersEntity_HI_RO> findManagerCatesOhersList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 审批
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject updateActManagerCates(JSONObject params)throws Exception;
    /**
     * 驳回
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject updateInActManagerCates(JSONObject params)throws Exception;
}
