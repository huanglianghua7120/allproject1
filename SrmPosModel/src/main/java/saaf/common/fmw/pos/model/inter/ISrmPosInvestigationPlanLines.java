package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosInvestigationPlanLinesEntity_HI_RO;

import javax.ws.rs.DefaultValue;
import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：现场考察品类
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
public interface ISrmPosInvestigationPlanLines {

    /**
     * LOV:资质审查单
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     zhj             创建
     * ===========================================================================
     */
    public Pagination<SrmPosInvestigationPlanLinesEntity_HI_RO> findInvestigationPlanNumber(
            JSONObject parameters, @DefaultValue("1")Integer pageIndex, @DefaultValue("10")Integer pageRows)
            throws Exception;
    /**
     * 查询现场考察品类
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmPosInvestigationPlanLinesEntity_HI_RO> findSrmReviewPlanCategories(JSONObject jsonParams) throws Exception;

    /**
     * 批量导入
     *
     * @param params
     * @return
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     zhj             创建
     * ===========================================================================
     */
    JSONObject saveList(JSONObject params)throws Exception;


}
