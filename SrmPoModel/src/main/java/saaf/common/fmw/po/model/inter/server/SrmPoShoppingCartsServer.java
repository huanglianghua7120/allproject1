package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.dao.ViewObjectImpl;
import com.yhg.hibernate.core.paging.Pagination;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.SrmPoAgreementAssignsEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoHeadersEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoLinesEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoShoppingCartsEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoShoppingCartsEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoHeaders;
import saaf.common.fmw.po.model.inter.ISrmPoShoppingCarts;
import saaf.common.fmw.utils.SrmUtils;

import java.math.BigDecimal;
import java.util.*;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;

/**
 * Project Name：SrmPoShoppingCartsServer
 * Company Name：SIE
 * Program Name：
 * Description：购物车
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoShoppingCartsServer")
public class SrmPoShoppingCartsServer implements ISrmPoShoppingCarts {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoShoppingCartsServer.class);

    @Autowired
    private ViewObject<SrmPoShoppingCartsEntity_HI> srmPoShoppingCartsDAO_HI;

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private BaseViewObject<SrmPoShoppingCartsEntity_HI_RO> srmPoShoppingCartsDAO_HI_RO;

    @Autowired
    private ViewObjectImpl<SrmPoAgreementAssignsEntity_HI> srmPoAgreementAssignsDAO_HI;//采购协议分配组织

    @Autowired
    private ViewObject<SrmPoHeadersEntity_HI> srmPoHeadersDAO_HI;//采购订单头表

    @Autowired
    private ViewObject<SrmPoLinesEntity_HI> srmPoLinesDAO_HI;//采购订单行表

    @Autowired
    private ISrmPoHeaders iSrmPoHeaders;//订单头

    public SrmPoShoppingCartsServer() {
        super();
    }

    /**
     * Description：批量删除购物车
     * @param jsonParams 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteShoppingCartByBatch(JSONObject jsonParams) throws Exception{
        LOGGER.info("参数：" + jsonParams.toString());
        JSONArray shoppingCartIdList = jsonParams.getJSONArray("data");
        if(null == shoppingCartIdList || shoppingCartIdList.isEmpty()){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"暂无数据",0,null);
        }
        List<SrmPoShoppingCartsEntity_HI> list = new ArrayList<>();
        for (Integer i = 0; i < shoppingCartIdList.size(); i++) {
            Integer shoppingCartId = shoppingCartIdList.getInteger(i);
            SrmPoShoppingCartsEntity_HI entity = srmPoShoppingCartsDAO_HI.getById(shoppingCartId);
            if (null != entity) {
                list.add(entity);
            }
        }
        if (null != list && list.size() > 0) {
            srmPoShoppingCartsDAO_HI.delete(list);
        }
        return SToolUtils.convertResultJSONObj("S", "删除成功", list.size(), null);
    }

    /**
     * Description：购物车查询list（分页）
     * @param jsonParams 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoShoppingCartsEntity_HI_RO> findSrmPoShoppingCartsInfoList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        String itemCodeOrName = "";
        if (null != jsonParams.getString("itemCodeOrName") && !"".equals(jsonParams.getString("itemCodeOrName"))) {
            itemCodeOrName = jsonParams.getString("itemCodeOrName");//物料名称或物料编码
        }
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer(SrmPoShoppingCartsEntity_HI_RO.QUERY_SHOPPINGCARTSLIST_SQL);
        sb.append(" AND (t.created_po_flag IS NULL OR t.created_po_flag='' OR t.created_po_flag='N') ");
        if (null != itemCodeOrName && !"".equals(itemCodeOrName)) {
            //验证字符串是否含有SQL关键字及字符，有则返回NULL
            if (SrmUtils.isContainSQL(itemCodeOrName)) {
                return null;
            }
            sb.append(" AND (spl.item_name like '%" + itemCodeOrName + "%' OR sbi.item_code like '%" + itemCodeOrName + "%') ");
        }
        SaafToolUtils.parperParam(jsonParams, "t.created_by", "varUserId", sb, map, "=");//当前登录用户Id
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY t.creation_date DESC"); //排序
        Pagination<SrmPoShoppingCartsEntity_HI_RO> result = srmPoShoppingCartsDAO_HI_RO.findPagination(sb.toString(),countSql, map, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：保存购物车list
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * shoppingCartId  购物车ID  NUMBER  Y
     * orgId  业务实体ID  NUMBER  Y
     * agreementLineId  框架协议行ID，关联表：srm_po_lines  NUMBER  N
     * demandQty  需求数量  NUMBER  N
     * demandDate  需求时间  DATE  N
     * receiveOrganizationId  接收组织ID  NUMBER  N
     * createdPoFlag  创建采购订单标识（Y/N）  VARCHAR2  N
     * poLineId  采购订单行ID，关联表：srm_po_lines  NUMBER  N
     * shoppingCartId  购物车ID  NUMBER  Y
     * orgId  业务实体ID  NUMBER  Y
     * agreementLineId  框架协议行ID，关联表：srm_po_lines  NUMBER  N
     * demandQty  需求数量  NUMBER  N
     * demandDate  需求时间  DATE  N
     * receiveOrganizationId  接收组织ID  NUMBER  N
     * createdPoFlag  创建采购订单标识（Y/N）  VARCHAR2  N
     * poLineId  采购订单行ID，关联表：srm_po_lines  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveShoppingCartsList(JSONObject jsonParams) throws Exception{
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId");//当前登录用户Id
        Integer orgId = null;//业务实体Id
        Integer receiveToOrganizationId = null;//收货组织Id
        Date demandDate = null;//需求日期
        if (null != jsonParams.getInteger("orgId") && !"".equals(jsonParams.getInteger("orgId"))) {
            orgId = jsonParams.getInteger("orgId");
        }
        if (null != jsonParams.getInteger("receiveToOrganizationId") && !"".equals(jsonParams.getInteger("receiveToOrganizationId"))) {
            receiveToOrganizationId = jsonParams.getInteger("receiveToOrganizationId");
        }
        if (null != jsonParams.getDate("demandDate") && !"".equals(jsonParams.getDate("demandDate"))) {
            demandDate = jsonParams.getDate("demandDate");
        }
        List<SrmPoShoppingCartsEntity_HI> poShoppingCartsList = null;
        if (null != jsonParams.getJSONArray("poShoppingCartsList") && !"".equals(jsonParams.getJSONArray("poShoppingCartsList"))) {
            JSONArray poShoppingCartsListJSON = jsonParams.getJSONArray("poShoppingCartsList");
            poShoppingCartsList = JSON.parseArray(poShoppingCartsListJSON.toJSONString(), SrmPoShoppingCartsEntity_HI.class);
        }
        String result = "";
        if (null != poShoppingCartsList && poShoppingCartsList.size() > 0) {
            Integer index = 0;
            for (SrmPoShoppingCartsEntity_HI k : poShoppingCartsList) {
                index++;
                k.setOperatorUserId(userId);
                if (null != orgId && !"".equals(orgId)) {
                    k.setOrgId(orgId);
                }
                if (null != k.getOrgId() && !"".equals(k.getOrgId())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("poHeaderId", k.getPoHeaderId());
                    map.put("orgId", k.getOrgId());
                    List<SrmPoAgreementAssignsEntity_HI> list = srmPoAgreementAssignsDAO_HI.findByProperty(map);
                    if (null == list || list.size() == 0 || "".equals(list)) {
                        result = "第" + index + "行所选业务实体不在框架协议范围内，请重新选择";
                        break;
                    }
                }

                if (null != receiveToOrganizationId && !"".equals(receiveToOrganizationId)) {
                    k.setReceiveOrganizationId(receiveToOrganizationId);
                }
                //需求日期
                if (null != demandDate && !"".equals(demandDate)) {
                    if (null != k.getStartDate() && !"".equals(k.getStartDate())) {
                        if (k.getStartDate().getTime() <= demandDate.getTime()) {
                            if (null == k.getEndDate() || "".equals(k.getEndDate())) {
                                k.setDemandDate(demandDate);//需求日期在价格的有效时间内
                            } else {
                                if (demandDate.getTime() <= k.getEndDate().getTime()) {
                                    k.setDemandDate(demandDate);//需求日期在价格的有效时间内
                                } else {
                                    result = "第" + index + "行所选需求日期不在价格有效时间范围内，请重新选择";
                                    break;
                                }
                            }
                        } else {
                            result = "第" + index + "行所选需求日期不在价格有效时间范围内，请重新选择";
                            break;
                        }
                    } else {
                        k.setDemandDate(demandDate);//需求日期在价格的有效时间内
                    }
                }
            }
        }
        if (null != poShoppingCartsList && poShoppingCartsList.size() > 0) {
            if (null == result || "".equals(result)) {
                srmPoShoppingCartsDAO_HI.save(poShoppingCartsList);
                jsonData.put("poShoppingCartsList", poShoppingCartsList);
                return SToolUtils.convertResultJSONObj("S", "保存成功", poShoppingCartsList.size(), jsonData);
            } else {
                return SToolUtils.convertResultJSONObj("E", result, 0, null);
            }
        }
        return SToolUtils.convertResultJSONObj("E", "没有操作", 0, null);
    }

    /**
     * Description：加入购物车的保存（单条数据）
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * shoppingCartId  购物车ID  NUMBER  Y
     * orgId  业务实体ID  NUMBER  Y
     * agreementLineId  框架协议行ID，关联表：srm_po_lines  NUMBER  N
     * demandQty  需求数量  NUMBER  N
     * demandDate  需求时间  DATE  N
     * receiveOrganizationId  接收组织ID  NUMBER  N
     * createdPoFlag  创建采购订单标识（Y/N）  VARCHAR2  N
     * poLineId  采购订单行ID，关联表：srm_po_lines  NUMBER  N
     * shoppingCartId  购物车ID  NUMBER  Y
     * orgId  业务实体ID  NUMBER  Y
     * agreementLineId  框架协议行ID，关联表：srm_po_lines  NUMBER  N
     * demandQty  需求数量  NUMBER  N
     * demandDate  需求时间  DATE  N
     * receiveOrganizationId  接收组织ID  NUMBER  N
     * createdPoFlag  创建采购订单标识（Y/N）  VARCHAR2  N
     * poLineId  采购订单行ID，关联表：srm_po_lines  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveShoppingCart(JSONObject jsonParams) throws Exception{
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId");//当前登录用户Id
        Integer orgId = null;//业务实体Id
        Integer agreementLineId = null;//框架协议行ID
        if (null != jsonParams.getInteger("orgId") && !"".equals(jsonParams.getInteger("orgId"))) {
            orgId = jsonParams.getInteger("orgId");
        }
        if (null != jsonParams.getInteger("agreementLineId") && !"".equals(jsonParams.getInteger("agreementLineId"))) {
            agreementLineId = jsonParams.getInteger("agreementLineId");
        }
        if (null != orgId && !"".equals(orgId) && null != agreementLineId && !"".equals(agreementLineId)) {
            StringBuffer sb = new StringBuffer(SrmPoShoppingCartsEntity_HI_RO.QUERY_SHOPPINGCARTSLIST_SQL);
            sb.append(" AND (t.created_po_flag IS NULL OR t.created_po_flag='' OR t.created_po_flag='N') ");
            sb.append(" AND t.org_id = " + orgId);
            sb.append(" AND t.agreement_line_id = " + agreementLineId);
            sb.append(" ANd t.created_by = " + userId);
            List<SrmPoShoppingCartsEntity_HI_RO> list = srmPoShoppingCartsDAO_HI_RO.findList(sb.toString());
            SrmPoShoppingCartsEntity_HI entity = null;
            if (null != list && list.size() > 0) {
                entity = srmPoShoppingCartsDAO_HI.getById(list.get(0).getShoppingCartId());
                if (null != entity.getDemandQty() && !"".equals(entity.getDemandQty())) {
                    entity.setDemandQty(entity.getDemandQty().add(new BigDecimal(1)));
                } else {
                    entity.setDemandQty(new BigDecimal(1));
                }
                entity.setOperatorUserId(userId);
            } else {
                entity = new SrmPoShoppingCartsEntity_HI();
                entity.setOrgId(orgId);
                entity.setAgreementLineId(agreementLineId);
                entity.setDemandQty(new BigDecimal(1));
                entity.setCreatedPoFlag("N");
                entity.setOperatorUserId(userId);
            }
            srmPoShoppingCartsDAO_HI.saveEntity(entity);
            jsonData.put("shoppingCartId", entity.getShoppingCartId());
            return SToolUtils.convertResultJSONObj("S", "加入购物车成功", 1, jsonData);
        }
        return SToolUtils.convertResultJSONObj("E", "没有操作", 0, null);
    }

    /**
     * Description：分组已确定创建订单的组别——购物车的创建订单 【业务实体+供应商+币种+税率+付款方式+回货方式】相同的产生同一个订单
     * @param poShoppingCartsList 购物车参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<List<SrmPoShoppingCartsEntity_HI>> getShoppingCartsGrouping(List<SrmPoShoppingCartsEntity_HI> poShoppingCartsList) {
        List<List<SrmPoShoppingCartsEntity_HI>> poShoppingCartsListGroup = new ArrayList<>();
        Set<String> list = new HashSet<>();
        if (null != poShoppingCartsList && poShoppingCartsList.size() > 0) {
            for (SrmPoShoppingCartsEntity_HI k : poShoppingCartsList) {
                String tem = k.getOrgId().toString();
                if (null != k.getSupplierId()) {
                    tem += k.getSupplierId().toString();
                }
                if (null != k.getCurrencyCode()) {
                    tem += k.getCurrencyCode().toString();
                }
                if (null != k.getTaxRateCode()) {
                    tem += k.getTaxRateCode().toString();
                }
                if (null != k.getPaymentCondition()) {
                    tem += k.getPaymentCondition().toString();
                }
                list.add(tem);
            }
        }
        if (null != list && list.size() > 0) {
            for (String t : list) {
                List<SrmPoShoppingCartsEntity_HI> newList = new ArrayList<>();
                for (SrmPoShoppingCartsEntity_HI k : poShoppingCartsList) {
                    String tem = k.getOrgId().toString();
                    if (null != k.getSupplierId()) {
                        tem += k.getSupplierId().toString();
                    }
                    if (null != k.getCurrencyCode()) {
                        tem += k.getCurrencyCode().toString();
                    }
                    if (null != k.getTaxRateCode()) {
                        tem += k.getTaxRateCode().toString();
                    }
                    if (null != k.getPaymentCondition()) {
                        tem += k.getPaymentCondition().toString();
                    }
                    if (null != tem && tem.equals(t)) {
                        newList.add(k);
                    }
                }
                if (null != newList && newList.size() > 0) {
                    poShoppingCartsListGroup.add(newList);
                }
            }
        }
        return poShoppingCartsListGroup;
    }

    /**
     * Description：根据poShoppingCartsList创建订单、订单行以及更新购物车的订单标识——购物车的创建订单
     * @param poShoppingCartsList 购物车参数
     * @param userId
     * @param employeeId 员工ID
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Integer savePoOrderAndShoppingCartAll(Integer userId, Integer employeeId, List<SrmPoShoppingCartsEntity_HI> poShoppingCartsList) throws Exception {
        Integer poHeaderId = null;
        if (null == poShoppingCartsList || poShoppingCartsList.size() == 0 || "".equals(poShoppingCartsList)) {
            return poHeaderId;
        }
        SrmPoHeadersEntity_HI headersEntity = new SrmPoHeadersEntity_HI();
        SrmPoShoppingCartsEntity_HI k = poShoppingCartsList.get(0);

        Date date = new Date();
        String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
//		String poNumberStr = "PO-"+dateFromate;
//		String poNumber = iSrmPoHeaders.getNewPoNumber(poNumberStr);//订单编号 注释原因：生成编号冲突，更换生成方法
        String poNumber = saafSequencesUtil.getDocSequences("srm_po_headers", "PO-", dateFromate, 4);//订单编号
        headersEntity.setPoNumber(poNumber);

        headersEntity.setBillToOrganizationId(k.getOrgId());//收单组织===业务实体
        headersEntity.setStatus("DRAFT");
        headersEntity.setOrgId(k.getOrgId());//业务实体Id
        headersEntity.setPoDocType("ORDER");//订单类型ORDER
        headersEntity.setSupplierId(k.getSupplierId());
        headersEntity.setCurrencyCode(k.getCurrencyCode());
        headersEntity.setTaxRateCode(k.getTaxRateCode());
        headersEntity.setBuyerId(employeeId);
        headersEntity.setReturnGoodsType("BASE_ON_PO");//回货类型BASE_ON_PO（采购订单回货）
        headersEntity.setPaymentCondition(k.getPaymentCondition());
        headersEntity.setSettlementWay(k.getSettlementWay());
        headersEntity.setPoVersions(new BigDecimal(0));
        headersEntity.setOperatorUserId(userId);
        srmPoHeadersDAO_HI.saveEntity(headersEntity);
        poHeaderId = headersEntity.getPoHeaderId();
        Integer index = 0;
        for (SrmPoShoppingCartsEntity_HI tem : poShoppingCartsList) {
            index++;
            SrmPoLinesEntity_HI poLinesEntity = new SrmPoLinesEntity_HI();
            poLinesEntity.setPoHeaderId(poHeaderId);
            poLinesEntity.setOnWayQty(new BigDecimal(0));
            poLinesEntity.setReceivedQty(new BigDecimal(0));
            poLinesEntity.setLineNumber(index);
            poLinesEntity.setItemId(tem.getItemId());
            poLinesEntity.setItemName(tem.getItemName());
            poLinesEntity.setCategoryId(tem.getCategoryId());
            poLinesEntity.setDemandQty(tem.getDemandQty());
            poLinesEntity.setDemandDate(tem.getDemandDate());
            poLinesEntity.setTaxPrice(tem.getTaxPrice());
            poLinesEntity.setNonTaxPrice(tem.getNonTaxPrice());
            poLinesEntity.setReceiveToOrganizationId(tem.getReceiveOrganizationId());
            poLinesEntity.setStatus("OPEN");//订单行的状态为打开OPEN
            poLinesEntity.setOperatorUserId(userId);
            srmPoLinesDAO_HI.saveEntity(poLinesEntity);

            tem.setCreatedPoFlag("Y");
            tem.setPoLineId(poLinesEntity.getPoLineId());
            tem.setOperatorUserId(userId);
            srmPoShoppingCartsDAO_HI.saveEntity(tem);
        }
        return poHeaderId;
    }
}
