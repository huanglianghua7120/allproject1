package saaf.common.fmw.base.model.inter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseBanksEntity_HI_RO;
import com.yhg.hibernate.core.paging.Pagination;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：银行
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmBaseBanks {
    /**
     * 银行查询
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
    public Pagination<SrmBaseBanksEntity_HI_RO> findSrmBaseBanksInfoList(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

    /**
     * Description：银行信息定时任务接口list
     * @param businessData
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-11-08     zhj             创建
     * ==============================================================================
     */
    public Integer updateSrmBaseBankListJob(JSONArray businessData);
}
