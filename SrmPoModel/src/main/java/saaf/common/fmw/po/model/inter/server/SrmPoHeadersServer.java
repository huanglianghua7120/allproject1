package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.po.model.entities.SrmPoHeadersEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoShoppingCartsEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoHeadersEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoHeaders;
import saaf.common.fmw.po.model.inter.ISrmPoShoppingCarts;
import saaf.common.fmw.utils.SrmUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Project Name：SrmPoHeadersServer
 * Company Name：SIE
 * Program Name：
 * Description：订单
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoHeadersServer")
public class SrmPoHeadersServer implements ISrmPoHeaders {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoHeadersServer.class);

    @Autowired
    private ViewObject<SrmPoHeadersEntity_HI> srmPoHeadersDAO_HI;

    @Autowired
    private BaseViewObject<SrmPoHeadersEntity_HI_RO> srmPoHeadersDAO_HI_RO;

    @Autowired
    private ISrmPoShoppingCarts iSrmPoShoppingCarts;//购物车

    public SrmPoHeadersServer() {
        super();
    }


    /**
     * Description：根据报价头ID查询订单/协议
     * @param jsonParams 查询参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SrmPoHeadersEntity_HI> findPoHeadersList(JSONObject jsonParams) {
        String sourceId = jsonParams.getString("bidHeaderId");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sourceCode", "srm_pon_bid_headers");
        map.put("sourceId", sourceId);
        List<SrmPoHeadersEntity_HI> poHeader = srmPoHeadersDAO_HI.findByProperty(map);
        return poHeader;
    }

    /**
     * Description：创建订单——购物车的创建订单按钮
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * shipLocationCode  收货方  VARCHAR2  N
     * locationCode  收单方  VARCHAR2  N
     * supPoFileId  供应商附件ID  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * agreementClause  协议条款  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * supplierReadTime  供应商查看日期
     DATE  N
     * exchangeRate  汇率  NUMBER  N
     * exchangeRateDate  汇率日期  DATE  N
     * syncErpFlag  是否同步到erp  VARCHAR2  N
     * syncErpMsg  同步到erp异常信息  VARCHAR2  N
     * isGlobal  是否全局  VARCHAR2  N
     * orderType  订单类型  VARCHAR2  N
     * erpOrderNo  erp订单编号  VARCHAR2  N
     * bazaarOrderNo  市场订单  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * buyerExecuteId  采购履行专员  NUMBER  N
     * organizationId  库存组织  NUMBER  N
     * prNumber  采购申请单号  VARCHAR2  N
     * locationCode  收货地点  VARCHAR2  N
     * poType  类型  VARCHAR2  N
     * shipToOrganizationId  收货组织  NUMBER  N
     * shipLocationCode    VARCHAR2  N
     * shipToLocationCode  收货方  VARCHAR2  N
     * billToLocationCode  收单方  VARCHAR2  N
     * shipToLocationId  收货方  NUMBER  N
     * billToLocationId  收单方  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * isGlobal  是否全局  VARCHAR2  N
     * agreementClause  协议条款  VARCHAR2  N
     * contractId  合同ID，关联表：srm_okc_contracts  NUMBER  N
     * contractCode  合同编号  VARCHAR2  N
     * contractTemplateId  合同模板ID，关联表：srm_okc_contract_templates  NUMBER  N
     * contractFileId  合同文档ID  NUMBER  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    @Override
    public JSONObject savePoHeadersList(JSONObject jsonParams) throws Exception{
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId");//当前登录用户Id
        Integer employeeId = jsonParams.getInteger("varEmployeeId");//当前登录者的人员Id
        List<SrmPoShoppingCartsEntity_HI> poShoppingCartsList = null;
        if (null != jsonParams.getJSONArray("poShoppingCartsList") && !"".equals(jsonParams.getJSONArray("poShoppingCartsList"))) {
            JSONArray poShoppingCartsListJSON = jsonParams.getJSONArray("poShoppingCartsList");
            poShoppingCartsList = JSON.parseArray(poShoppingCartsListJSON.toJSONString(), SrmPoShoppingCartsEntity_HI.class);
        }
        if (null == poShoppingCartsList || poShoppingCartsList.size() == 0 || "".equals(poShoppingCartsList)) {
            return SToolUtils.convertResultJSONObj("E", "没有操作", 0, null);
        }
        Integer poHeaderId = null;
        List<Integer> poHeaderIdList = new ArrayList<>();
        List<List<SrmPoShoppingCartsEntity_HI>> poShoppingCartsListGroup = iSrmPoShoppingCarts.getShoppingCartsGrouping(poShoppingCartsList);
        for (Integer i = 0; i < poShoppingCartsListGroup.size(); i++) {
            List<SrmPoShoppingCartsEntity_HI> poShoppingCartsListV = poShoppingCartsListGroup.get(i);
            poHeaderId = iSrmPoShoppingCarts.savePoOrderAndShoppingCartAll(userId, employeeId, poShoppingCartsListV);
            if (null != poHeaderId && !"".equals(poHeaderId)) {
                poHeaderIdList.add(poHeaderId);
            }
        }
        if (null != poHeaderIdList && poHeaderIdList.size() > 0) {
            jsonData.put("poHeaderIds", poHeaderIdList);
            return SToolUtils.convertResultJSONObj("S", "成功创建" + poHeaderIdList.size() + "条订单", poHeaderIdList.size(), jsonData);
        }
        return SToolUtils.convertResultJSONObj("E", "没有操作", 0, null);
    }

    /**
     * Description：获取最大的poNumber订单编号
     * @param poNumberStr PO-yyyyMMdd
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public String getNewPoNumber(String poNumberStr) {
        String poNumber = "";
        if (null == poNumberStr || "".equals(poNumberStr)) {
            return poNumber;
        }
        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(poNumber)) {
            return null;
        }
        StringBuilder sb = new StringBuilder("SELECT MAX(t.po_number) AS poNumber FROM srm_po_headers t ");
        sb.append(" WHERE t.po_number LIKE '" + poNumberStr + "%'");
        List<SrmPoHeadersEntity_HI_RO> poHeaderList = srmPoHeadersDAO_HI_RO.findList(sb.toString());
        if (null == poHeaderList || poHeaderList.size() == 0 || "".equals(poHeaderList)) {
            poNumber = poNumberStr + "0001";
        } else {
            SrmPoHeadersEntity_HI_RO poHeader = poHeaderList.get(0);
            if (null == poHeader.getPoNumber() || "".equals(poHeader.getPoNumber())) {
                poNumber = poNumberStr + "0001";
            } else {
                String poNumberV = poHeader.getPoNumber();
                Integer number = Integer.parseInt(poNumberV.substring(poNumberV.length() - 4));
                number++;
                String newPoNumber = StringUtils.leftPad(number.toString(), 4, "0");
                poNumber = poNumberV.substring(0, poNumberV.length() - 4) + newPoNumber;
            }
        }
        return poNumber;
    }

    /**
     * Description：获取最大的xyNumber协议编号，xyNumberStr是XY-yyyyMMdd
     *
     * @param xyNumberStr
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    @Override
    public String getNewXyNumber(String xyNumberStr) {
        String xyNumber = "";
        if (null == xyNumberStr || "".equals(xyNumberStr)) {
            return xyNumber;
        }
        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(xyNumber)) {
            return null;
        }
        StringBuilder sb = new StringBuilder("SELECT MAX(t.po_number) AS poNumber FROM srm_po_headers t ");
        sb.append(" WHERE t.po_number LIKE '" + xyNumberStr + "%'");
        List<SrmPoHeadersEntity_HI_RO> poHeaderList = srmPoHeadersDAO_HI_RO.findList(sb.toString());
        if (null == poHeaderList || poHeaderList.size() == 0 || "".equals(poHeaderList)) {
            xyNumber = xyNumberStr + "001";
        } else {
            SrmPoHeadersEntity_HI_RO poHeader = poHeaderList.get(0);
            if (null == poHeader.getPoNumber() || "".equals(poHeader.getPoNumber())) {
                xyNumber = xyNumberStr + "001";
            } else {
                String poNumberV = poHeader.getPoNumber();
                Integer number = Integer.parseInt(poNumberV.substring(poNumberV.length() - 3));
                number++;
                String newPoNumber = StringUtils.leftPad(number.toString(), 3, "0");
                xyNumber = poNumberV.substring(0, poNumberV.length() - 3) + newPoNumber;
            }
        }
        return xyNumber;
    }
}
