
package saaf.common.fmw.po.model.inter;

import saaf.common.fmw.po.model.entities.readonly.SrmPoApprovedListEntity_HI_RO;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
/**
 * Project Name：IApprovedList
 * Company Name：SIE
 * Program Name：
 * Description：供应商批准列表
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
public interface IApprovedList {

    /**
     * Description：查询批准供应商列表
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
    Pagination<SrmPoApprovedListEntity_HI_RO> findApprovedList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：保存供应商批准信息
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * certifyProcess  认证合格工序  VARCHAR2  N
     * militaryProductsFlag  用于军品(Y/N)  VARCHAR2  N
     * carProductFlag  用于汽车产品(Y/N)  VARCHAR2  N
     * listId  列表ID  NUMBER  Y
     * supplierId  供应商ID  NUMBER  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  Y
     * itemId  物料ID  NUMBER  N
     * listStatus  状态,快码:ISP_ASL_STATUS  VARCHAR2  N
     * disabledFlag  禁用标识(Y/N)  VARCHAR2  N
     * dayCapacity  （废弃）供应商日产能（日供货量）  NUMBER  N
     * listNumber  （废弃）序号  NUMBER  N
     * passU9Flag  （废弃）推U9标识(N:未推送,S:已推送,U:已推送后修改)  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * introductionModelId  供应商引入头层-型号ID，关联表srm_pos_introduction_model  NUMBER  N
     * resourceModelId  资源开发-型号信息ID,关联表srm_pos_resource_model  NUMBER  N
     * monopolyFlag  是否垄断型供应商(Y/N)  VARCHAR2  N
     * introductionHeaderId  供应商引入头层ID,关联表srm_pos_introduction_header  NUMBER  N
     * introductionSiteId  供应商引入头层-地点ID  NUMBER  N
     * requestId  报文请求ID  VARCHAR2  N
     * supplierSiteId  供应商档案地点Id，关联表srm_pos_supplier_sites  NUMBER  N
     * modelId  型号库ID,关联表srm_base_model  NUMBER  N
     * listId  列表ID  NUMBER  Y
     * supplierId  供应商ID  NUMBER  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  Y
     * itemId  物料ID  NUMBER  N
     * listStatus  状态  VARCHAR2  N
     * disabledFlag  禁用标识(Y/N)  VARCHAR2  N
     * dayCapacity  （废弃）供应商日产能（日供货量）  NUMBER  N
     * listNumber  （废弃）序号  NUMBER  N
     * passU9Flag  （废弃）推U9标识(N:未推送,S:已推送,U:已推送后修改)  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    JSONObject saveApprovedInfo(JSONObject params) throws Exception;

    /**
     * Description：导入货源列表
     * @param params 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */

    JSONObject saveApprovedList(JSONObject params)throws Exception;

}
