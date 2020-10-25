package saaf.common.fmw.po.model.inter;

import java.util.List;

import saaf.common.fmw.po.model.entities.readonly.SrmPoSupplyProportionHEntity_HI_RO;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供货比例头表
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-20     hgq             modify
 * ==============================================================================
 */
public interface ISrmPoSupplyProportionH {
    /**
     * Description：查询供货比例
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
     Pagination<SrmPoSupplyProportionHEntity_HI_RO> findSupplyProportion(JSONObject parameters, Integer pageIndex, Integer pageRows)throws Exception ;

    /**
     * Description：保存供货列表
     * @param params 参数
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * supplyId  表ID，主键，供其他表做外键  NUMBER  Y
     * supplyType  供货比例类型  VARCHAR2  Y
     * smallCategoryCode  物料类别  VARCHAR2  N
     * smallCategoryName  物料类别名称  VARCHAR2  N
     * itemId    NUMBER  N
     * itemCode  物料编码  VARCHAR2  N
     * itemDescription  物料编码名称  VARCHAR2  N
     * instId  分厂ID  NUMBER  N
     * startDateActive  起始日期  DATE  Y
     * endDateActive  终止日期  DATE  N
     * description  说明、备注  VARCHAR2  N
     * status  状态  VARCHAR2  Y
     * supplyId  表ID，主键，供其他表做外键  NUMBER  Y
     * supplyType  供货比例类型  VARCHAR2  Y
     * smallCategoryCode  物料类别  VARCHAR2  N
     * smallCategoryName  物料类别名称  VARCHAR2  N
     * itemId    NUMBER  N
     * itemCode  物料编码  VARCHAR2  N
     * itemDescription  物料编码名称  VARCHAR2  N
     * instId  分厂ID  NUMBER  N
     * startDateActive  起始日期  DATE  Y
     * endDateActive  终止日期  DATE  N
     * description  说明、备注  VARCHAR2  N
     * status  状态  VARCHAR2  Y
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
     JSONObject saveSupplyProportionH(JSONObject params) throws Exception;

    /**
     * Description：导出供货比例
     * @param params 参数
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
     List<SrmPoSupplyProportionHEntity_HI_RO> findSupplyExport(JSONObject params)throws Exception;

    /**
     * Description：批量导入供货比例
     * @param params 参数
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
     JSONObject saveList(JSONObject params)throws Exception;
}
