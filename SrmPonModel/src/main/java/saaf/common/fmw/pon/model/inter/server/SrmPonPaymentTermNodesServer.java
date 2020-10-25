package saaf.common.fmw.pon.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pon.model.entities.SrmPonPaymentTermNodesEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonPaymentTermNodesEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonPaymentTermNodes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonPaymentTermNodesServer.java
 * Description：寻源--付款节点信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonPaymentTermNodesServer")
public class SrmPonPaymentTermNodesServer implements ISrmPonPaymentTermNodes {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonPaymentTermNodesServer.class);

    @Autowired
    private ViewObject<SrmPonPaymentTermNodesEntity_HI> srmPonPaymentTermNodesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonPaymentTermNodesEntity_HI_RO> srmPonPaymentTermNodesDAO_HI_RO;

    public SrmPonPaymentTermNodesServer() {
        super();
    }

    /**
     * Description：付款节点查询（不分页）
     * @param jsonParams 参数
     * @return List<SrmPonPaymentTermNodesEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<SrmPonPaymentTermNodesEntity_HI_RO> findSrmPonPaymentTermNodesInfo(JSONObject jsonParams) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPonPaymentTermNodesEntity_HI_RO.QUERYPAYMENTTERMNODESLIST_SQL);
        SaafToolUtils.parperParam(jsonParams, "ppt.payment_term_id", "paymentTermId", sb, map, "=");
        SaafToolUtils.parperParam(jsonParams, "pptn.payment_term_code", "paymentTermCode", sb, map, "=");
        SaafToolUtils.parperParam(jsonParams, "pptn.term_code_id", "termCodeId", sb, map, "=");
        sb.append(" ORDER BY pptn.line_number ASC "); //排序
        List<SrmPonPaymentTermNodesEntity_HI_RO> list = srmPonPaymentTermNodesDAO_HI_RO.findList(sb.toString(), map);
        return list;
    }

    /**
     * Description：删除付款节点——根据主键Id（单条数据）
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteTermCodeId(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());
        Integer termCodeId = jsonParams.getInteger("termCodeId");
        if (!(termCodeId == null || "".equals(termCodeId))) {
            SrmPonPaymentTermNodesEntity_HI row = srmPonPaymentTermNodesDAO_HI.getById(termCodeId);
            if (null != row) {
                srmPonPaymentTermNodesDAO_HI.delete(row.getTermCodeId());
                return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "无法删除，无此记录！参数：" + termCodeId, 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "删除失败，参数：" + termCodeId, 0, null);
        }
    }

    /**
     * Description：保存付款节点List（需要传入付款条件的paymentTermCode）
     * @param paymentTermNodesList 付款节点信息列表
     * @param userId 操作者ID
     * @param paymentTermCode 付款编码
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public void savePaymentTermNodesList(List<SrmPonPaymentTermNodesEntity_HI> paymentTermNodesList, Integer userId, String paymentTermCode) {
        if (null != paymentTermNodesList && paymentTermNodesList.size() > 0) {
            Integer index = 0;
            for (SrmPonPaymentTermNodesEntity_HI k : paymentTermNodesList) {
                index++;
                k.setLineNumber(index);
                k.setPaymentTermCode(paymentTermCode);
                k.setOperatorUserId(userId);
            }
            srmPonPaymentTermNodesDAO_HI.saveOrUpdateAll(paymentTermNodesList);
        }
    }

    /**
     * Description：根据付款节点获取付款条件名称
     * @param paymentTermNodesList 付款节点信息列表
     * @return String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public String getPaymentTermName(List<SrmPonPaymentTermNodesEntity_HI> paymentTermNodesList) {
        String paymentTermName = "";
        if (null == paymentTermNodesList || paymentTermNodesList.size() <= 0) {
            return paymentTermName;
        }
        for (SrmPonPaymentTermNodesEntity_HI k : paymentTermNodesList) {
            if (k.getBiasDays() == null || "".equals(k.getBiasDays()) || k.getBiasDays() <= 0) {
                paymentTermName += k.getPaymentTermNode() + k.getPaymentProportion() + "%" + "，";
            } else {
                paymentTermName += k.getPaymentTermNode() + k.getBiasDays() + "天后" + k.getPaymentProportion() + "%" + "，";
            }
        }
        paymentTermName = paymentTermName.substring(0, paymentTermName.length() - 1);    //截取最后一位逗号
        return paymentTermName;
    }
}
