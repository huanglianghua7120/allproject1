package saaf.common.fmw.cua.model.inter.server;

import saaf.common.fmw.cua.model.inter.ISrmCuaAccountLines;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.noggit.JSONParser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.cua.model.dao.SrmCuaAccountLinesDAO_HI;
import saaf.common.fmw.cua.model.dao.SrmCuaAccountsDAO_HI;
import saaf.common.fmw.cua.model.dao.readonly.SrmCuaAccountLinesDAO_HI_RO;
import saaf.common.fmw.cua.model.dao.readonly.SrmErpTransactionsVDAO_HI_RO;
import saaf.common.fmw.cua.model.entities.SrmCuaAccountLinesEntity_HI;
import saaf.common.fmw.cua.model.entities.SrmCuaAccountsEntity_HI;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaAccountLinesEntity_HI_RO;
import saaf.common.fmw.cua.model.entities.readonly.SrmErpTransactionsVEntity_HI_RO;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

/**
 * @author zhangxuan
 * @date 2018/10/30
 */
@Component("srmCuaAccountLinesServer")
public class SrmCuaAccountLinesServer implements ISrmCuaAccountLines {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmCuaAccountLinesServer.class);

    @Resource
    private SrmCuaAccountsDAO_HI srmCuaAccountsDAO_HI;

    @Resource
    private SrmCuaAccountLinesDAO_HI srmCuaAccountLinesDAO_HI;

    @Resource
    private SrmErpTransactionsVDAO_HI_RO srmErpTransactionsVDAO_HI_RO;

    @Resource
    private SrmCuaAccountLinesDAO_HI_RO srmCuaAccountLinesDAO_HI_RO;

    @Resource
    private ViewObject<SrmCuaAccountsEntity_HI> srmCuaAccountsEntity_HI;

    @Resource
    private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;

    public SrmCuaAccountLinesServer() {
        super();
    }


    /**
     * 查询视图中的对账单据信息
     */
    @Override
    public Pagination<SrmErpTransactionsVEntity_HI_RO> findViewList(JSONObject parameters, Integer pageIndex,
                                                                    Integer pagerows) throws Exception {
        try {
            LOGGER.info("findViewList--->>" + parameters);

            StringBuffer sqlBuffer = new StringBuffer(SrmErpTransactionsVEntity_HI_RO.QUERY_VIEW_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            Integer supplierId = parameters.getInteger("supplierId");
            // 供应商
            SrmPosSupplierInfoEntity_HI supplierInfo = srmPosSupplierInfoDAO_HI.getById(supplierId);    //获得供应商信息
            sqlBuffer.append(" AND v.vendor_code = '" + supplierInfo.getSupplierNumber() + "' \n");    //通过供应商编码关联
            //业务实体
            SaafToolUtils.parperParam(parameters, " v.ORG_ID", "instId", sqlBuffer, map, "=");
            //类型,如果类型不为全部
            if (null != parameters.getString("transactionType") && !"BOTH".equals(parameters.getString("transactionType"))) {
                SaafToolUtils.parperParam(parameters, " v.transaction_type", "transactionType", sqlBuffer, map, "=");
            }
            //物料编码
            SaafToolUtils.parperParam(parameters, " v.item_code", "itemCode", sqlBuffer, map, "like");
            //时间转换格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //开始日期
            if (null != parameters && parameters.containsKey("startDate") && null != parameters.get("startDate")) {
                try {
                    //开始日期格式转化
                    Date date = simpleDateFormat.parse(parameters.get("startDate").toString());
                    map.put("startDate", date);
                } catch (ParseException e) {
                    //e.printStackTrace();
                }
                sqlBuffer.append(" AND v.TRANSACTION_DATE > :startDate ");
            }

            //结束日期
            if (null != parameters && parameters.containsKey("endDate") && null != parameters.get("endDate")) {
                try {
                    //结束日期格式转化
                    Date date = simpleDateFormat.parse(parameters.get("endDate").toString());
                    map.put("endDate", date);
                } catch (ParseException e) {
                    //e.printStackTrace();
                }
                sqlBuffer.append(" AND v.TRANSACTION_DATE < :endDate ");
            }
            sqlBuffer.append(" AND v.transaction_id NOT IN (SELECT scal.transaction_id FROM srm_cua_account_lines scal  )");
            if (null != parameters.getString("lineList")) {    //添加但是还没保存的对账单
                List<SrmCuaAccountLinesEntity_HI_RO> lineList = JSON.parseArray(parameters.getString("lineList"), SrmCuaAccountLinesEntity_HI_RO.class);
                if (lineList.size() != 0) {    //如果添加但还没保存对账单不为空
                    sqlBuffer.append("AND v.transaction_id not in ( ");    //去掉已经添加单但还没保存的对账单
                    for (int i = 0; i < lineList.size() - 1; i++) {
                        sqlBuffer.append(lineList.get(i).getTransactionId() + ", ");
                    }
                    sqlBuffer.append(lineList.get(lineList.size() - 1).getTransactionId() + " ) ");
                }
            }
            pagerows = 8;
            Pagination<SrmErpTransactionsVEntity_HI_RO> viewList = srmErpTransactionsVDAO_HI_RO.findPagination(sqlBuffer, "SELECT count(1) FROM (" + sqlBuffer.toString() + ") xx", map,
                    pageIndex, pagerows);
            LOGGER.info(viewList.toString());
            return viewList;
        } catch (Exception e) {
            LOGGER.error("findAccountsList 对账查询异常" + e);
            //e.printStackTrace();
            throw new Exception("对账查询异常");
        }
    }


    /**
     * 查询对账单行信息
     */
    @Override
    public Pagination<SrmCuaAccountLinesEntity_HI_RO> findAccountLineForExcel(JSONObject parameters, Integer pageIndex,
                                                                              Integer pagerows) throws Exception {
        try {
            LOGGER.info("findAccountLineForExcel--->>" + parameters);

            StringBuffer sqlBuffer = new StringBuffer(SrmCuaAccountLinesEntity_HI_RO.QUERY_ACCOUNT_LINES_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            sqlBuffer.append(" AND scal.ACCOUNT_ID = :accountId ");
            map.put("accountId", parameters.getInteger("accountId"));
            Pagination<SrmCuaAccountLinesEntity_HI_RO> lineList = srmCuaAccountLinesDAO_HI_RO.findPagination(sqlBuffer, "SELECT count(1) FROM (" + sqlBuffer.toString() + ") xx", map,
                    pageIndex, pagerows);
            LOGGER.info(lineList.toString());
            return lineList;
        } catch (Exception e) {
            LOGGER.error("findAccountLineForExcel 对账查询异常" + e);
            //e.printStackTrace();
            throw new Exception("对账查询异常");
        }
    }

    /**
     * 查询对账单行
     */
    @Override
    public Pagination<SrmCuaAccountLinesEntity_HI_RO> findAccountLineList(JSONObject parameters, Integer pageIndex,
                                                                          Integer pagerows) throws Exception {
        try {
            LOGGER.info("findAccountLineList--->>" + parameters);
            StringBuffer sqlBuffer = new StringBuffer(SrmCuaAccountLinesEntity_HI_RO.QUERY_ACCOUNT_LINES_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            if (null != parameters.getInteger("accountId")) {
                sqlBuffer.append(" AND scal.ACCOUNT_ID = :accountId ");
                map.put("accountId", parameters.getInteger("accountId"));
            }
            Pagination<SrmCuaAccountLinesEntity_HI_RO> lineList = srmCuaAccountLinesDAO_HI_RO.findPagination(sqlBuffer, "SELECT count(1) FROM (" + sqlBuffer.toString() + ") xx", map,
                    pageIndex, pagerows);
            return lineList;
        } catch (Exception e) {
            LOGGER.error("findAccountLineList 对账查询异常" + e);
            //e.printStackTrace();
            throw new Exception("对账查询异常");
        }
    }

    /**
     * 删除对账单行信息
     */
    @Override
    public JSONObject deleteAccountLine(JSONObject jsonParams) throws Exception {
        //如果对账单行id不为空
        if (null != jsonParams.getInteger("accountLineId")) {
            SrmCuaAccountLinesEntity_HI line = srmCuaAccountLinesDAO_HI.findByProperty("account_line_id", jsonParams.getInteger("accountLineId")).get(0);
            if (line != null) {
                srmCuaAccountLinesDAO_HI.delete(line);
                return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "参数无效，无法删除", 1, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "参数无效，无法删除", 1, null);
        }
    }

    /**
     * 保存所有对账单行
     */
    @Override
    public void saveAllAccountLine(final JSONObject jsonParams) throws Exception {
        //把视图中所有的对账单行都复制到对账单行表
        String sql = new String("insert into srm_cua_account_lines (account_id, transaction_id, transaction_date, transaction_type,\r\n" +
                "item_id, item_code, item_name, uom_code, unit_of_measure,tax_rate, transaction_quantity, po_header_id, po_line_id,\r\n" +
                "po_line_location_id, po_distribution_id, currency_code, tax_price, non_tax_price, shipment_num, delivery_number, PO_CODE, receipt_num, SHIPMENT_LINE_ID, SHIPMENT_LINE_NUM, destination_type_code)\r\n" +
                "select " + jsonParams.getInteger("accountId") + ", transaction_id, transaction_date, transaction_type, item_id,\r\n" +
                "item_code, item_name, uom_code, unit_of_measure, tax_rate, quantity, po_header_id,\r\n" +
                "po_line_id, po_line_location_id, po_distribution_id, currency_code, UNIT_PRICE * ( 1 + 0.01 * tax_rate ), UNIT_PRICE, shipment_num, delivery_number, PO_CODE, receipt_num, SHIPMENT_LINE_ID, SHIPMENT_LINE_NUM, destination_type_code from srm_erp_transactions_v where org_id = " + jsonParams.getInteger("instId") + " and supplier_id = " + jsonParams.getInteger("supplierId") + "\r\n" +
                "and transaction_id not in (select scal.transaction_id from srm_cua_account_lines scal )");
        this.srmCuaAccountLinesDAO_HI.executeSqlUpdate(sql);
        //计算对账金额
    }

    /**
     * 保存对账单行信息
     *
     * @param parameters
     * @param line
     * @return
     * @throws Exception
     */
    //@Override
    public boolean saveAccountLine(JSONObject parameters, String line) throws Exception {
        // TODO Auto-generated method stub
        String currencyCode;
        BigDecimal taxRate;
        //判断对账行信息的币种和税率是否一致，不一致则无法添加
        StringBuffer sqlBuffer = new StringBuffer(SrmCuaAccountLinesEntity_HI_RO.QUERY_ACCOUNT_LINES_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        sqlBuffer.append(" AND scal.ACCOUNT_ID = :accountId ");
        map.put("accountId", parameters.getInteger("accountId"));
        //对账单行信息
        List<SrmCuaAccountLinesEntity_HI> linesList = JSON.parseArray(line, SrmCuaAccountLinesEntity_HI.class);
        //获得对账行信息的数据
        List<SrmCuaAccountLinesEntity_HI_RO> list = srmCuaAccountLinesDAO_HI_RO.findList(sqlBuffer, map);
        if (null != list && list.size() != 0) {        //如果对账行信息不为空则获得币种和税率
            currencyCode = list.get(0).getCurrencyCode();    //获得币种

        } else {    //如果为空则遍历判断添加的行信息数据是否一致
            if (null == linesList || linesList.size() == 0) {    //如果添加的行信息为空
                return false;
            }
            currencyCode = linesList.get(0).getCurrencyCode();    //获得币种
        }
        for (int i = 1; i < linesList.size(); i++) {    //遍历添加的行信息币种和税率是否一致
            if (!currencyCode.equals(linesList.get(i).getCurrencyCode())) {
                throw new RuntimeException("添加的币种不一致");
            }
        }
        for (int i = 0; i < linesList.size(); i++) {
            linesList.get(i).setAccountId(parameters.getInteger("accountId"));
            linesList.get(i).setCreatedBy(parameters.getInteger("varUserId"));
            linesList.get(i).setCreationDate(new Date());
            linesList.get(i).setOperatorUserId(parameters.getInteger("varUserId"));
            srmCuaAccountLinesDAO_HI.saveOrUpdate(linesList.get(i));
        }
        return true;
    }

    @Override
    public Map<String, Object> saveAccountLineAmount(JSONObject parameters, String line, Boolean isAll, Boolean isSave) throws Exception {
        // TODO Auto-generated method stub

        //返回的结果map
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //计算对账金额
        String sql = new String("UPDATE srm_cua_accounts sca SET\r\n" +
                "sca.receive_tax_amount = \r\n" +
                "(SELECT Round(sum(scal.TAX_PRICE * scal.TRANSACTION_QUANTITY),2) FROM srm_cua_account_lines scal\r\n" +
                " WHERE scal.account_id = sca.account_id AND scal.transaction_type = 'PUT_IN_STORAGE'),\r\n" +
                "sca.return_tax_amount = \r\n" +
                "(SELECT Round(sum(scal.TAX_PRICE * scal.TRANSACTION_QUANTITY),2) FROM srm_cua_account_lines scal \r\n" +
                "WHERE scal.account_id = sca.account_id AND scal.transaction_type = 'SALES_RETURN'),\r\n" +
                "sca.receive_amount = \r\n" +
                "(SELECT Round(sum(scal.NON_TAX_PRICE * scal.TRANSACTION_QUANTITY),2) FROM srm_cua_account_lines scal \r\n" +
                "where scal.account_id = sca.account_id AND scal.transaction_type = 'PUT_IN_STORAGE'),\r\n" +
                "sca.return_amount =\r\n" +
                "(SELECT Round(sum(scal.NON_TAX_PRICE * scal.TRANSACTION_QUANTITY),2) FROM srm_cua_account_lines scal \r\n" +
                "WHERE scal.account_id = sca.account_id AND scal.transaction_type = 'SALES_RETURN')\r\n" +
                "WHERE sca.account_id =\r\n" + parameters.getInteger("accountId") +
                " ");
        srmCuaAccountsDAO_HI.executeSqlUpdate(sql);
        SrmCuaAccountsEntity_HI account = srmCuaAccountsEntity_HI.getById(parameters.getInteger("accountId"));    //获得对账单实体，把计算后的对账金额保存到数据库里面
        BigDecimal receiveTaxAmount = account.getReceiveTaxAmount();    //接受总金额（含税）
        BigDecimal returnTaxAmount = account.getReturnTaxAmount();        //退货总金额（含税）
        BigDecimal receiveAmount = account.getReceiveAmount();    //接受总金额（不含税）
        BigDecimal returnAmount = account.getReturnAmount();    //退货总金额（不含税）
        BigDecimal taxAmount = new BigDecimal(0);    //对账总金额（含税）
        BigDecimal amount = new BigDecimal(0);    //对账总金额（不含税）
        if (receiveTaxAmount == null) {    //初始化
            receiveTaxAmount = new BigDecimal(0);
        }
        if (returnTaxAmount == null) {    //初始化
            returnTaxAmount = new BigDecimal(0);
        }
        if (receiveAmount == null) {    //初始化
            receiveAmount = new BigDecimal(0);
        }
        if (returnAmount == null) {    //初始化
            returnAmount = new BigDecimal(0);
        }
        taxAmount = taxAmount.add(receiveTaxAmount);
        taxAmount = taxAmount.add(returnTaxAmount);
        amount = amount.add(receiveAmount);
        amount = amount.add(returnAmount);
        taxAmount = taxAmount.setScale(2, BigDecimal.ROUND_HALF_UP);    //保留两位小数
        amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);    //保留两位小数
        receiveTaxAmount = receiveTaxAmount.setScale(2, BigDecimal.ROUND_HALF_UP);    //保留两位小数
        returnTaxAmount = returnTaxAmount.setScale(2, BigDecimal.ROUND_HALF_UP);    //保留两位小数
        receiveAmount = receiveAmount.setScale(2, BigDecimal.ROUND_HALF_UP);    //保留两位小数
        returnAmount = returnAmount.setScale(2, BigDecimal.ROUND_HALF_UP);    //保留两位小数
        account.setReceiveTaxAmount(receiveTaxAmount);
        account.setReturnTaxAmount(returnTaxAmount);
        account.setReceiveAmount(receiveAmount);
        account.setReturnAmount(returnAmount);
        account.setOperatorUserId(parameters.getInteger("varUserId"));
        srmCuaAccountsEntity_HI.saveOrUpdate(account);        //保存到数据库
        resultMap.put("taxAmount", taxAmount);
        resultMap.put("amount", amount);
        resultMap.put("receiveTaxAmount", receiveTaxAmount);
        resultMap.put("returnTaxAmount", returnTaxAmount);
        resultMap.put("receiveAmount", receiveAmount);
        resultMap.put("returnAmount", returnAmount);
        //计算总金额返回给前端
        return resultMap;
    }
}
