package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;

public interface ISrmSpmSupplierScore {

    /**
     * 更新供应商得分表数据
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
    JSONObject updateSupplierScore(JSONObject paramJSON) throws Exception;
}
