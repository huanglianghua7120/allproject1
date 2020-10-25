package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONObject;
import saaf.common.fmw.po.model.entities.readonly.SrmPoStarvingEntity_HI_RO;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：缺料
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-20     hgq             modify
 * ==============================================================================
 */
public interface ISrmPoStarving {
    /**
     * Description：查询缺料
     * @param params 参数
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    List<SrmPoStarvingEntity_HI_RO> findStarvingList(JSONObject params) throws Exception;

    /**
     * Description：查询缺料明细
     * @param params 参数
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    List<SrmPoStarvingEntity_HI_RO> findStarvingInfoPoList(JSONObject params) throws Exception;

    /**
     * Description：创建缺料信息
     * @param params 参数
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * poStarvingId    NUMBER  Y
     * shortCheckDate  缺料检查日期  DATE  N
     * wipEntityNumber  工单号  VARCHAR2  N
     * instId  分厂id  NUMBER  N
     * itemCode    VARCHAR2  N
     * itemId  物料id  NUMBER  N
     * categoryCode  分类code  VARCHAR2  N
     * needQuantity  需求数量  NUMBER  N
     * needByDate  需求日期  DATE  N
     * supplierNumber  指定供应商  VARCHAR2  N
     * specialUseNum  番号  VARCHAR2  N
     * demandClassify  需求分类  VARCHAR2  N
     * employeeId  采购员id  NUMBER  N
     * noticeQty  已创建送货通知数量  NUMBER  N
     * poStarvingId    NUMBER  Y
     * shortCheckDate  缺料检查日期  DATE  N
     * wipEntityNumber  工单号  VARCHAR2  N
     * instId  分厂id  NUMBER  N
     * itemCode    VARCHAR2  N
     * itemId  物料id  NUMBER  N
     * categoryCode  分类code  VARCHAR2  N
     * needQuantity  需求数量  NUMBER  N
     * needByDate  需求日期  DATE  N
     * supplierNumber  指定供应商  VARCHAR2  N
     * specialUseNum  番号  VARCHAR2  N
     * demandClassify  需求分类  VARCHAR2  N
     * employeeId  采购员id  NUMBER  N
     * noticeQty  已创建送货通知数量  NUMBER  N
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    JSONObject saveNoticeLines(JSONObject params) throws Exception;
}
