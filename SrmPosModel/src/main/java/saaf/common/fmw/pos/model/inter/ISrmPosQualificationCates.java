package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import saaf.common.fmw.pos.model.entities.SrmPosQualificationCatesEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosQualificationCatesEntity_HI_RO;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商资质
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
public interface ISrmPosQualificationCates {

    /**
     * 根据资质审查ID和是否新增标识，查询资质审查单下面供应商品类
     * @param jsonParams
     * @return
     * @throws Exception
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     zhj             创建
     * ===========================================================================
     */
    List<SrmPosQualificationCatesEntity_HI_RO> findQualificationCategoryByAddFlag(JSONObject jsonParams) throws Exception;
    /**
     * 删除
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject deleteQualificationCategory(JSONObject jsonParams) throws Exception;

}
