package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierCredentialsEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierCredentialsEntity_HI_RO;

import java.util.List;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商调度配置
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-07-08     谢晓霞             创建
 * ==============================================================================
 */
public interface ISrmPosJob {
    /**
     * 根据资质有效时间自动创建预警
     * @param
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-08      谢晓霞            创建
     * ==============================================================================
     */
    public JSONObject saveCreateWarning() throws Exception;
    /**
     * 保存ekpStatus
     *
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-05-24     谢晓霞             创建
     * =======================================================================
     */
    public JSONObject saveEkpStatus() throws Exception;
}
