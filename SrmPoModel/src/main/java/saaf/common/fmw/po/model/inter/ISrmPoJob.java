package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPoJob.java
 * Description：PO调度配置
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       2020-07-09           谢晓霞             创建
 * ===========================================================================
 */
public interface ISrmPoJob {
    /**
     * Description：获取EBS技改编号
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject savePoTechnicalNum() throws Exception;


    /**
     * Description：获取EBS子项目编号
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject savePoSubprojectNum() throws Exception;


    /**
     * Description：获取EBS技改编号-保存
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    public Integer updateSrmPoTechnicalNum(JSONArray businessData) throws Exception;

    /**
     * Description：获取EBS子项目编号-保存
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    public Integer updateSrmPoSubprojectNum(JSONArray businessData) throws Exception;
}
