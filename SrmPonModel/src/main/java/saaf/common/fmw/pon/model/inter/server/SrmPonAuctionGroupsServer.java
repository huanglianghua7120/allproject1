package saaf.common.fmw.pon.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionGroupsEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionItemsEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionGroupsEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionGroups;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonAuctionGroupsServer.java
 * Description：寻源--寻源单据组别信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonAuctionGroupsServer")
public class SrmPonAuctionGroupsServer implements ISrmPonAuctionGroups {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonAuctionGroupsServer.class);

    @Autowired
    private ViewObject<SrmPonAuctionGroupsEntity_HI> srmPonAuctionGroupsDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonAuctionGroupsEntity_HI_RO> srmPonAuctionGroupsDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPonAuctionItemsEntity_HI> srmPonAuctionItemsDAO_HI; //洽谈行

    public SrmPonAuctionGroupsServer() {
        super();
    }

    /**
     * Description：删除洽谈组别——根据主键ID（单条数据）
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteSrmPonAuctionGroupsById(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());
        Integer groupId = jsonParams.getInteger("groupId");
        if (!(groupId == null || "".equals(groupId))) {
            SrmPonAuctionGroupsEntity_HI row = srmPonAuctionGroupsDAO_HI.getById(jsonParams.getInteger("groupId"));
            if (null != row) {
                List<SrmPonAuctionItemsEntity_HI> list = srmPonAuctionItemsDAO_HI.findByProperty("groupId", row.getGroupId());
                if (null != list && list.size() > 0) {
                    return SToolUtils.convertResultJSONObj("E", "不可删除，该组别已经被标的物使用！", 0, null);
                }
                srmPonAuctionItemsDAO_HI.delete(row.getGroupId());
                return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "无法删除，无此记录！参数：" + groupId, 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "删除失败，参数：" + groupId, 0, null);
        }
    }

    /**
     * Description：查询拟标头组别（不分页）
     * @param params 参数
     * @return List<SrmPonAuctionGroupsEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	    创建
     * =======================================================================
     */
    @Override
    public List<SrmPonAuctionGroupsEntity_HI_RO> getAuctionGroupList(JSONObject params) {
        StringBuffer querySql = new StringBuffer(SrmPonAuctionGroupsEntity_HI_RO.QUERY_AUCTION_GROUP_LIST_SQL);
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        if (auctionHeaderId == null || "".equals(auctionHeaderId)) {
            return null;
        }
        return srmPonAuctionGroupsDAO_HI_RO.findList(querySql, new Object[]{auctionHeaderId});
    }

    /**
     * Description：保存拟标组别（招标）
     * @param ponAuctionGroupsList 寻源组别信息
     * @param userId 操作者ID
     * @param auctionHeaderId 寻源单据ID
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	    创建
     * =======================================================================
     */
    @Override
    public void savePonAuctionGroupsList(List<SrmPonAuctionGroupsEntity_HI> ponAuctionGroupsList, Integer userId, Integer auctionHeaderId) {
        if (null != ponAuctionGroupsList && ponAuctionGroupsList.size() > 0) {
            for (SrmPonAuctionGroupsEntity_HI k : ponAuctionGroupsList) {
                k.setAuctionHeaderId(auctionHeaderId);
                k.setOperatorUserId(userId);
            }
            srmPonAuctionGroupsDAO_HI.saveOrUpdateAll(ponAuctionGroupsList);
        }
    }
}
