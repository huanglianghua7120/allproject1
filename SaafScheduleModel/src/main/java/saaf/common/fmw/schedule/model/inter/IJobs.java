package saaf.common.fmw.schedule.model.inter;

import com.alibaba.fastjson.JSONObject;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：并发程序
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             modify
 * ==============================================================================
 */
public interface IJobs {
    /**
     * Description：保存并发程序
     * @param parameters 参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             modify
     * ==============================================================================
     */
    String saveJob(JSONObject parameters);
    /**
     * Description：保存并发程序
     * @param parameters 参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             modify
     * ==============================================================================
     */
    String saveJobInfo(JSONObject parameters);
    /**
     * Description：删除并发程序
     * @param parameters 参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             modify
     * ==============================================================================
     */
    String deleteJob(JSONObject parameters);
    /**
     * Description：修改并发程序
     * @param parameters 参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             modify
     * ==============================================================================
     */
    String updateJob(JSONObject parameters);

    /**
     * Description：查询并发程序
     * @param parameters 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             modify
     * ==============================================================================
     */
    String findJobs(JSONObject parameters, Integer pageIndex, Integer pageRows);
}
