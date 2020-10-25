package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONObject;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionGroupsEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionGroupsEntity_HI_RO;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonAuctionGroups.java
 * Description：寻源--寻源单据组别信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonAuctionGroups {

    /**
     * Description：删除洽谈组别——根据阶梯数量的主键ID（单条数据）
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject deleteSrmPonAuctionGroupsById(JSONObject jsonParams);

    /**
     * Description：查询拟标头组别（不分页）
     * @param params 参数
     * @return List<SrmPonAuctionGroupsEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    List<SrmPonAuctionGroupsEntity_HI_RO> getAuctionGroupList(JSONObject params);

    /**
     * Description：保存拟标组别（招标）
     * @param ponAuctionGroupsList
     * @param userId
     * @param auctionHeaderId
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public void savePonAuctionGroupsList(List<SrmPonAuctionGroupsEntity_HI> ponAuctionGroupsList,Integer userId,Integer auctionHeaderId);
}
