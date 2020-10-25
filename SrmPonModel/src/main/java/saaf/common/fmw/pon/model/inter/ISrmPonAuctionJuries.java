package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONObject;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionJuriesEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionJuriesEntity_HI_RO;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonAuctionJuries.java
 * Description：寻源--寻源协作小组信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonAuctionJuries {

    /**
     * Description：删除洽谈协作小组——根据主键ID（单条数据）
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject deleteSrmPonAuctionJuriesById(JSONObject jsonParams);

    /**
     * Description：查询协作小组页签
     * @param params
     * @return List<SrmPonAuctionJuriesEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    List<SrmPonAuctionJuriesEntity_HI_RO> getAuctionJuryList(JSONObject params);

    /**
     * Description：保存拟标——洽谈协作小组==评分人员（招标）
     * @param ponAuctionJuriesList
     * @param userId
     * @param auctionHeaderId
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public void savePonAuctionJuriesList(List<SrmPonAuctionJuriesEntity_HI> ponAuctionJuriesList,Integer userId,Integer auctionHeaderId);
}
