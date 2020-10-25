package saaf.common.fmw.pon.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionItemLaddersEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionItemLaddersEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionItemLadders;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonAuctionItemLaddersServer.java
 * Description：寻源--寻源标的物阶梯信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonAuctionItemLaddersServer")
public class SrmPonAuctionItemLaddersServer implements ISrmPonAuctionItemLadders {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonAuctionItemLaddersServer.class);

    @Autowired
    private ViewObject<SrmPonAuctionItemLaddersEntity_HI> srmPonAuctionItemLaddersDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonAuctionItemLaddersEntity_HI_RO> srmPonAuctionItemLaddersDAO_HI_RO;

    public SrmPonAuctionItemLaddersServer() {
        super();
    }

    /**
     * Description：查询拟标头
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteSrmPonAuctionItemLadderById(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());
        Integer itemLadderId = jsonParams.getInteger("itemLadderId");
        if (!(itemLadderId == null || "".equals(itemLadderId))) {
            SrmPonAuctionItemLaddersEntity_HI row = srmPonAuctionItemLaddersDAO_HI.getById(jsonParams.getInteger("itemLadderId"));
            if (null != row) {
                srmPonAuctionItemLaddersDAO_HI.delete(row.getItemLadderId());
                return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "未知错误", 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * Description：查询阶梯数量（不分页）
     * @param params 参数
     * @return List<SrmPonAuctionItemLaddersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<SrmPonAuctionItemLaddersEntity_HI_RO> getPonAuctionItemLaddersList(JSONObject params) {
        LOGGER.info("参数：" + params.toString());
        StringBuffer querySql = new StringBuffer(SrmPonAuctionItemLaddersEntity_HI_RO.QUERY_AUCTION_ITEMSLADDERS_LIST_SQL);
        Integer auctionLineId = params.getInteger("auctionLineId");
        if (auctionLineId == null || "".equals(auctionLineId)) {
            return null;
        }
        List<SrmPonAuctionItemLaddersEntity_HI_RO> itemLadders = null;
        itemLadders = srmPonAuctionItemLaddersDAO_HI_RO.findList(querySql, new Object[]{auctionLineId});
        if (itemLadders.size() > 0) {
            for (int i = 0; i < itemLadders.size(); i++) {
                itemLadders.get(i).setLadderCount(itemLadders.size());
            }
        }
        return itemLadders;
    }

    /**
     * Description：保存阶梯数量List（需要传入标的物的行的auctionLineId,auctionHeaderId）
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject savePonAuctionItemLaddersList(JSONObject jsonParams) {
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId");  //用户Id
        Integer auctionLineId = jsonParams.getInteger("auctionLineId");
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId");
        if (auctionHeaderId == null || "".equals(auctionHeaderId)) {
            return SToolUtils.convertResultJSONObj("E", "头层auctionHeaderId不能为空", 0, null);
        }
        if (auctionLineId == null || "".equals(auctionLineId)) {
            return SToolUtils.convertResultJSONObj("E", "标的物auctionLineId不能为空", 0, null);
        }

        JSONArray ponAuctionItemLaddersListJSON = jsonParams.getJSONArray("ponAuctionItemLaddersList");//阶梯数量
        List<SrmPonAuctionItemLaddersEntity_HI> ponAuctionItemLaddersList = JSON.parseArray(ponAuctionItemLaddersListJSON.toJSONString(), SrmPonAuctionItemLaddersEntity_HI.class);
        if (null != ponAuctionItemLaddersList && ponAuctionItemLaddersList.size() > 0) {
            for (SrmPonAuctionItemLaddersEntity_HI k : ponAuctionItemLaddersList) {
                k.setAuctionHeaderId(auctionHeaderId);
                k.setAuctionLineId(auctionLineId);
                k.setOperatorUserId(userId);
            }
            srmPonAuctionItemLaddersDAO_HI.saveOrUpdateAll(ponAuctionItemLaddersList);
            jsonData.put("ponAuctionItemLaddersList", ponAuctionItemLaddersList);
            return SToolUtils.convertResultJSONObj("S", "保存成功！", 1, jsonData);
        }
        return SToolUtils.convertResultJSONObj("E", "没有传入数据保存！", 0, null);
    }
}
