package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import saaf.common.fmw.spm.model.entities.SrmSpmTplDimensionEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplDimensionEntity_HI_RO;

public interface ISrmSpmTplDimension {
    /**
     * 查询评分维度
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	List<SrmSpmTplDimensionEntity_HI> findSrmSpmTplDimensionInfo(JSONObject queryParamJSON);
    /**
     * 保存评分维度
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	Object saveSrmSpmTplDimensionInfo(JSONObject queryParamJSON);

	List<SrmSpmTplDimensionEntity_HI_RO> getDimensionLineList(JSONObject jsonParams);

}
