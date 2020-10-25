package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionHeadersEntity_HI_RO;

import java.util.Date;
import java.util.List;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonJob.java
 * Description：寻源调度配置
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       2020-07-09           谢晓霞             创建
 * ===========================================================================
 */
public interface ISrmPonJob {
    /**
     * Description：寻源自动截标定时任务
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-09      谢晓霞             创建
     * ==============================================================================
     */
    public JSONObject updatePonAuctionHeaderJob();


    /**
     * Description：招标询价EKP审批状态获取定时任务
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
