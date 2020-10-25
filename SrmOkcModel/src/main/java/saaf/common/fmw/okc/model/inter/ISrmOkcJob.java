package saaf.common.fmw.okc.model.inter;

import com.alibaba.fastjson.JSONObject;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmOkcJob.java
 * Description：合同调度配置
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       2020-07-09           谢晓霞             创建
 * ===========================================================================
 */
public interface ISrmOkcJob {
    /**
     * Description：合同EKP审批状态获取定时任务
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-09      谢晓霞             创建
     * ==============================================================================
     */
    public JSONObject saveEkpStatus() throws Exception;
}
