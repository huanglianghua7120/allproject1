package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.po.model.entities.readonly.SrmPoForecastInfoEntity_HI_RO;

import java.util.List;
/**
 * Project Name：ISrmPoForecastInfo
 * Company Name：SIE
 * Program Name：
 * Description：采购预测
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
public interface ISrmPoForecastInfo {
    /**
     * Description：保存采购预测接口，输入头字段（forecastStatus，forecastName，predictionType，orgId，organizationId）
     * 行字段（categoryId，itemId，supplierId，demandQuantity，demandDate，buyerId）
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * sourceId  数据来源ID  VARCHAR2  N
     * forecastType  预测分类  VARCHAR2  N
     * remark  备注  VARCHAR2  N
     * forecastNumber  预测单号  VARCHAR2  N
     * forecastId  预测ID  NUMBER  Y
     * forecastName  预测名称  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * predictionType  预测类型  VARCHAR2  N
     * forecastStatus  预测状态  VARCHAR2  N
     * invalidDate  失效日期  DATE  N
     * invalidBy  失效人  NUMBER  N
     * releaseDate  发布日期  DATE  N
     * categoryName  （废弃）类别名称  VARCHAR2  N
     * categoryCode  （废弃）类别编码  VARCHAR2  N
     * forecastDate  （废弃）预测日期  DATE  N
     * itemCode  （废弃）物料编码  VARCHAR2  N
     * itemDescription  （废弃）物料名称  VARCHAR2  N
     * employeeId  （废弃）采购员  NUMBER  N
     * needQuantity  （废弃）需求数量  NUMBER  N
     * needByDate  （废弃）需求日期  DATE  N
     * vendnameGroup  （废弃）供应商组合  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * forecastId  预测ID  NUMBER  Y
     * forecastName  预测名称  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * predictionType  预测类型  VARCHAR2  N
     * forecastStatus  预测状态  VARCHAR2  N
     * invalidDate  失效日期  DATE  N
     * invalidBy  失效人  NUMBER  N
     * releaseDate  （废弃）发布日期  DATE  N
     * categoryName  （废弃）类别名称  VARCHAR2  N
     * categoryCode  （废弃）类别编码  VARCHAR2  N
     * forecastDate  （废弃）预测日期  DATE  N
     * itemCode  （废弃）物料编码  VARCHAR2  N
     * itemDescription  （废弃）物料名称  VARCHAR2  N
     * employeeId  （废弃）采购员  NUMBER  N
     * needQuantity  （废弃）需求数量  NUMBER  N
     * needByDate  （废弃）需求日期  DATE  N
     * vendnameGroup  （废弃）供应商组合  VARCHAR2  N
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

    JSONObject updatePoForecastInfoExternal(JSONObject jsonParams,int userId)throws Exception;

    /**
     * Description：采购预测(供应商) 业务实体及收获组织下拉框
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject findPoForecastSupplierOption(JSONObject jsonParams);

    /**
     * Description：提交采购预测
     * @param jsonParam
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject updateSubmitForecast(JSONObject jsonParam)throws Exception;

    /**
     * Description：失效采购预测
     * @param jsonParam
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject updateInvalidForecast(JSONObject jsonParam)throws Exception;

    /**
     * Description：撤回采购预测
     * @param jsonParam
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject updateRetractForecast(JSONObject jsonParam)throws Exception;

    /**
     * Description：发布采购预测
     * @param jsonParam
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject updatePublishForecast(JSONObject jsonParam)throws Exception;
    /**
     * Description：批量导入
     * @param jsonParams 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject saveImportForecast(JSONObject jsonParams,Integer userId);

    /**
     * Description：查询头
     * @param jsonParams 查询参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    List<SrmPoForecastInfoEntity_HI_RO> queryForecastInfoList(JSONObject jsonParams);

    /**
     * Description：查询采购预测
     * @param jsonParams 查询参数
     * @param pageIndex 页码
     * @param pageRows 行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    Pagination<SrmPoForecastInfoEntity_HI_RO> findForecastInfoList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：保存采购预测，输入头字段（forecastStatus，forecastName，predictionType，orgId，organizationId，operatorUserId）
     *      行字段（categoryId，itemId，supplierId，demandQuantity，demandDate，buyerId）
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * sourceId  数据来源ID  VARCHAR2  N
     * forecastType  预测分类  VARCHAR2  N
     * remark  备注  VARCHAR2  N
     * forecastNumber  预测单号  VARCHAR2  N
     * forecastId  预测ID  NUMBER  Y
     * forecastName  预测名称  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * predictionType  预测类型  VARCHAR2  N
     * forecastStatus  预测状态  VARCHAR2  N
     * invalidDate  失效日期  DATE  N
     * invalidBy  失效人  NUMBER  N
     * releaseDate  发布日期  DATE  N
     * categoryName  （废弃）类别名称  VARCHAR2  N
     * categoryCode  （废弃）类别编码  VARCHAR2  N
     * forecastDate  （废弃）预测日期  DATE  N
     * itemCode  （废弃）物料编码  VARCHAR2  N
     * itemDescription  （废弃）物料名称  VARCHAR2  N
     * employeeId  （废弃）采购员  NUMBER  N
     * needQuantity  （废弃）需求数量  NUMBER  N
     * needByDate  （废弃）需求日期  DATE  N
     * vendnameGroup  （废弃）供应商组合  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * forecastId  预测ID  NUMBER  Y
     * forecastName  预测名称  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * predictionType  预测类型  VARCHAR2  N
     * forecastStatus  预测状态  VARCHAR2  N
     * invalidDate  失效日期  DATE  N
     * invalidBy  失效人  NUMBER  N
     * releaseDate  （废弃）发布日期  DATE  N
     * categoryName  （废弃）类别名称  VARCHAR2  N
     * categoryCode  （废弃）类别编码  VARCHAR2  N
     * forecastDate  （废弃）预测日期  DATE  N
     * itemCode  （废弃）物料编码  VARCHAR2  N
     * itemDescription  （废弃）物料名称  VARCHAR2  N
     * employeeId  （废弃）采购员  NUMBER  N
     * needQuantity  （废弃）需求数量  NUMBER  N
     * needByDate  （废弃）需求日期  DATE  N
     * vendnameGroup  （废弃）供应商组合  VARCHAR2  N
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
    JSONObject updatePoForecastInfo(JSONObject jsonParams) throws Exception;

}
