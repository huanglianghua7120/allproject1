package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONObject;
import saaf.common.fmw.pon.model.entities.SrmPonJuryCountersignsEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonJuryCountersignsEntity_HI_RO;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonJuryCountersigns.java
 * Description：寻源--会签信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonJuryCountersigns {

    /**
     * Description：会签的发起、同意、拒绝按钮操作（已截止）
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject saveJuryCountersigns(JSONObject jsonParams);

    /**
     * Description：找出最大的轮次
     * @param juryCountersignsList
     * @return Integer
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public Integer getMaxRound(List<SrmPonJuryCountersignsEntity_HI> juryCountersignsList);

    /**
     * Description：会签查询（不分页）——已截止
     * @param jsonParams
     * @return List<SrmPonJuryCountersignsEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public List<SrmPonJuryCountersignsEntity_HI_RO> findSrmPonJuryCountersignsList(JSONObject jsonParams);
}
