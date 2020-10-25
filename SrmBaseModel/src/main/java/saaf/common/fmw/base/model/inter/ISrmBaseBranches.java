package saaf.common.fmw.base.model.inter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseBranchesEntity_HI_RO;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：银行分行
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmBaseBranches {
    /**
     * 分行查询
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmBaseBranchesEntity_HI_RO> findSrmBaseBranchesInfoList(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

    /**
     * Description：银行分行定时任务接口list
     * @param businessData
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-11-11     zhj             创建
     * ==============================================================================
     */
    public Integer updateSrmBaseBrancheListJob(JSONArray businessData);
}
