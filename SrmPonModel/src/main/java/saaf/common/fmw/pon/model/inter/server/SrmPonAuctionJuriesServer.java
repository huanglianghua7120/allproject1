package saaf.common.fmw.pon.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionJuriesEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionJuriesEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionJuries;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonAuctionJuriesServer.java
 * Description：寻源--寻源协作小组信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonAuctionJuriesServer")
public class SrmPonAuctionJuriesServer implements ISrmPonAuctionJuries {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonAuctionJuriesServer.class);
    @Autowired
    private ViewObject<SrmPonAuctionJuriesEntity_HI> srmPonAuctionJuriesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonAuctionJuriesEntity_HI_RO> srmPonAuctionJuriesDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPonAuctionHeadersEntity_HI> srmPonAuctionHeadersDAO_HI;//洽谈头层

    public SrmPonAuctionJuriesServer() {
        super();
    }

    /**
     * Description：删除洽谈协作小组——根据主键ID（单条数据）
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteSrmPonAuctionJuriesById(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());
        Integer juryId = jsonParams.getInteger("juryId");
        if (!(juryId == null || "".equals(juryId))) {
            SrmPonAuctionJuriesEntity_HI row = srmPonAuctionJuriesDAO_HI.getById(jsonParams.getInteger("juryId"));
            if (null != row) {
                srmPonAuctionJuriesDAO_HI.delete(row.getJuryId());
                return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "未知错误", 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * Description：查询协作小组页签
     * @param params 参数
     * @return List<SrmPonAuctionJuriesEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<SrmPonAuctionJuriesEntity_HI_RO> getAuctionJuryList(JSONObject params) {
        StringBuffer sb = new StringBuffer(SrmPonAuctionJuriesEntity_HI_RO.QUERY_AUCTION_JURY_LIST_SQL);
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        if (auctionHeaderId == null || "".equals(auctionHeaderId)) {
            return null;
        }
        SrmPonAuctionHeadersEntity_HI auctionHeadersEntity = srmPonAuctionHeadersDAO_HI.getById(auctionHeaderId);
        if (null == auctionHeadersEntity) {
            return null;
        }
        if ("TENDER".equals(auctionHeadersEntity.getAuctionType())) { //招标
            sb.append(" AND slv.lookup_type = 'PON_USER_DUTY' ");
            sb.append(" WHERE t.auction_header_id = " + auctionHeaderId);
            return srmPonAuctionJuriesDAO_HI_RO.findList(sb);
        } else if ("INQUIRY".equals(auctionHeadersEntity.getAuctionType())) { //询价
            sb.append(" AND slv.lookup_type = 'PON_INQUIRY_USER_DUTY' ");
            sb.append(" WHERE t.auction_header_id = " + auctionHeaderId);
            return srmPonAuctionJuriesDAO_HI_RO.findList(sb);
        }
        return null;
    }

    /**
     * Description：保存拟标——洽谈协作小组==评分人员（招标）
     * @param ponAuctionJuriesList 协作小组信息
     * @param userId 操作人ID
     * @param auctionHeaderId 寻源单据ID
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public void savePonAuctionJuriesList(List<SrmPonAuctionJuriesEntity_HI> ponAuctionJuriesList, Integer userId, Integer auctionHeaderId) {
        if (null != ponAuctionJuriesList && ponAuctionJuriesList.size() > 0) {
            for (SrmPonAuctionJuriesEntity_HI k : ponAuctionJuriesList) {
                k.setAuctionHeaderId(auctionHeaderId);
                k.setOperatorUserId(userId);
            }
            srmPonAuctionJuriesDAO_HI.saveOrUpdateAll(ponAuctionJuriesList);
        }
    }
}
