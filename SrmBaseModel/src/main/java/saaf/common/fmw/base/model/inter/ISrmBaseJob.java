package saaf.common.fmw.base.model.inter;

import com.alibaba.fastjson.JSONObject;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmBaseJob.java
 * Description：基础模块调度配置
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       2020-07-09           谢晓霞             创建
 * ===========================================================================
 */
public interface ISrmBaseJob {
    /**
     * Description：银行接口定时任务
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-09      谢晓霞             创建
     * ==============================================================================
     */
    public JSONObject updateSrmBaseBankListJob() throws Exception;

    /**
     * Description：银行分行获取定时任务
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-09      谢晓霞             创建
     * ==============================================================================
     */
    public JSONObject updateSrmBaseBrancheListJob() throws Exception;
    /**
     * Description：付款条件接口定时任务,每天定时从EBS系统获取数据
     * @return Integer
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-07-09           谢晓霞             创建
     * =======================================================================
     */
    public JSONObject updateSrmPonPaymentTermsListJob() throws Exception;
}
