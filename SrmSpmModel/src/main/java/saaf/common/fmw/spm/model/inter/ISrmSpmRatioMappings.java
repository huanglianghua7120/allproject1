package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmRatioMappingsEntity_HI_RO;

import java.util.List;

public interface ISrmSpmRatioMappings {

    /**
     * 供货比例对照关系查询
     *
     * @param paramJSON
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     */
    Pagination<SrmSpmRatioMappingsEntity_HI_RO> findRatioMappingsList(JSONObject paramJSON, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：保存供货比例对照关系数据
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveRatioMappings(JSONObject paramJSON) throws Exception;

    /**
     * 删除供货比例对照关系数据
     *
     * @param paramJSON
     * @return
     * @throws Exception
     */
    JSONObject deleteRatioMappings(JSONObject paramJSON) throws Exception;

    /**
     * 导出供货比例对照关系数据
     *
     * @param paramJSON
     * @return
     * @throws Exception
     */
    List<SrmSpmRatioMappingsEntity_HI_RO> findRatioMappingsExport(JSONObject paramJSON)throws Exception;

    /**
     * 导入供货比例对照关系数据
     * 1.导入时，校验“评价组织+供应商数量+采购分类只能有一条的数据
     * 2.验证控制每行的预设比例之和为100%,且控制“预设比例”的个数等于“供应商数量”
     *
     * @param paramJSON
     * @return
     * @throws Exception
     */
    JSONObject saveRatioMappingsList(JSONObject paramJSON)throws Exception;
}
