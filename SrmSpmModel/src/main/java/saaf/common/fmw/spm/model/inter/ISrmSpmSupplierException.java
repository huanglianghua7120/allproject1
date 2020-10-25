package saaf.common.fmw.spm.model.inter;



import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmSupplierExceptionEntity_HI_RO;

import java.util.List;

public interface ISrmSpmSupplierException {

    /**
     * 绩效例外查询
     * @param paramJSON 查询条件
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmSpmSupplierExceptionEntity_HI_RO> findExceptionInfoList(JSONObject paramJSON, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 保存绩效例外数据
     *
     * @param paramJSON
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveException(JSONObject paramJSON) throws Exception;
    /**
     *导出绩效例外数据
     *
     * @param paramJSON 查询条件
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SrmSpmSupplierExceptionEntity_HI_RO> findExceptionExport(JSONObject paramJSON)throws Exception;
    /**
     * 删除绩效例外数据
     * 批量删除
     * @param paramJSON （ids）
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject deleteException(JSONObject paramJSON) throws Exception;
    /**
     * 生效
     * 批量更新
     * @param paramJSON （ids）
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updateEffectiveException(JSONObject paramJSON) throws Exception;
    /**
     * 失效
     * 批量更新
     * @param paramJSON （ids）
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updateInvalidException(JSONObject paramJSON) throws Exception;
    /**
     * 导入绩效例外数据
     *
     * 1.导入时，校验“评价组织+供应商+例外类型”或者“评价组织+分类+例外类型”只有一条生效的数据
     * 2.导入成功的数据，状态默认为“生效”
     * @param paramJSON
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveExceptionList(JSONObject paramJSON)throws Exception;

}
