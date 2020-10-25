package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONObject;
/**
 * Project Name：ISrmPoRequisitionLines
 * Company Name：SIE
 * Program Name：
 * Description：采购申请行
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-07-02     SIE 谢晓霞       创建
 * ===========================================================================
 */
public interface ISrmPoRequisitionLines {
    /**
     * Description：拆分申请行
     * @param jsonParams 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject saveSplitRequisitionLine(JSONObject jsonParams) throws Exception;

    /**
     * Description：保存供应商
     * @param jsonParams 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject saveRequisitionLine(JSONObject jsonParams) throws Exception;


}
