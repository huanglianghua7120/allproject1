package saaf.common.fmw.cua.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.cua.model.dao.SrmCuaInvoiceLinesDAO_HI;
import saaf.common.fmw.cua.model.dao.SrmCuaInvoicesDAO_HI;
import saaf.common.fmw.cua.model.dao.readonly.SrmCuaInvoiceLinesDAO_HI_RO;
import saaf.common.fmw.cua.model.entities.SrmCuaInvoiceLinesEntity_HI;
import saaf.common.fmw.cua.model.entities.SrmCuaInvoicesEntity_HI;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaInvoiceLinesEntity_HI_RO;
import saaf.common.fmw.cua.model.inter.ISrmCuaInvoiceLines;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

/**
 * @author zhangxuan
 * @date 2018/11/9
 */
@Component("srmCuaInvoiceLinesServer")
public class SrmCuaInvoiceLinesServer implements ISrmCuaInvoiceLines {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmCuaInvoiceLinesServer.class);

    @Resource
    private SrmCuaInvoiceLinesDAO_HI srmCuaInvoiceLinesDAO_HI;

    @Resource
    private SrmCuaInvoicesDAO_HI srmCuaInvoicesDAO_HI;

    @Resource
    private SrmCuaInvoiceLinesDAO_HI_RO srmCuaInvoiceLinesDAO_HI_RO;

    public SrmCuaInvoiceLinesServer() {
        super();
    }

    /**
     * 查询发票中可以添加的对账单列表
     */
    @Override
    public Pagination<SrmCuaInvoiceLinesEntity_HI_RO> findValidAccountLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        //获取分页查询的条件
        StringBuffer sb = new StringBuffer(SrmCuaInvoiceLinesEntity_HI_RO.QUERY_INVOICE_ACCOUNT_SQL);
        HashMap<String, Object> map = new HashMap<>();
        //对账单编号
        SaafToolUtils.parperParam(jsonParams, "sca.account_code", "accountCode", sb, map, "=");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //开始日期
        if (null != jsonParams && jsonParams.containsKey("startDate") && null != jsonParams.get("startDate")) {
            try {
                //开始日期格式转化
                Date date = simpleDateFormat.parse(jsonParams.get("startDate").toString());
                map.put("startDate", date);
            } catch (Exception e) {
                //e.printStackTrace();
            }
            sb.append(" AND sca.ACCOUNT_DATE >= :startDate ");
        }

        //结束日期
        if (null != jsonParams && jsonParams.containsKey("endDate") && null != jsonParams.get("endDate")) {
            try {
                //结束日期格式转化
                Date date = simpleDateFormat.parse(jsonParams.get("endDate").toString());
                map.put("endDate", date);
            } catch (Exception e) {
                //e.printStackTrace();
            }
            sb.append(" AND sca.ACCOUNT_DATE <= :endDate ");
        }
        //添加登录的供应商id
        if (null != jsonParams.getInteger("supplierId")) {
            sb.append(" AND sca.supplier_id = :supplierId ");
            map.put("supplierId", jsonParams.getInteger("supplierId"));
        }
        //添加业务实体id
        if (null != jsonParams.getInteger("instId")) {
            sb.append(" AND sca.org_id = :instId ");
            map.put("instId", jsonParams.getInteger("instId"));
        }
        //筛选已选中的对账单
        sb.append(" AND sca.account_id not in (select scil.account_id from srm_cua_invoice_lines scil,\r\n "
                + "srm_cua_invoices sci\r\n" +
                "  where scil.invoice_id = sci.invoice_id and sci.invoice_status != 'INVALID' )");
        return srmCuaInvoiceLinesDAO_HI_RO.findPagination(sb.toString(), "SELECT count(1) FROM (" + sb.toString() + ") xx", map, pageIndex, pageRows);
    }

    /**
     * 查询发票单行信息
     */
    @Override
    public Pagination<SrmCuaInvoiceLinesEntity_HI_RO> findInvoiceLineList(JSONObject parameters, Integer pageIndex,
                                                                          Integer pagerows) throws Exception {
        LOGGER.info("findInvoiceLineList--->>" + parameters);
        try {
            StringBuffer sqlBuffer = new StringBuffer(SrmCuaInvoiceLinesEntity_HI_RO.QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            sqlBuffer.append(" AND scil.INVOICE_ID = :invoiceId ");
            map.put("invoiceId", parameters.getInteger("invoiceId"));
            Pagination<SrmCuaInvoiceLinesEntity_HI_RO> lineList = srmCuaInvoiceLinesDAO_HI_RO.findPagination(sqlBuffer, "SELECT count(1) FROM (" + sqlBuffer.toString() + ") xx", map,
                    pageIndex, pagerows);
            LOGGER.info(lineList.toString());
            return lineList;
        } catch (Exception e) {
            LOGGER.error("findInvoiceLineList 发票查询异常" + e);
            //e.printStackTrace();
            throw new Exception("发票查询异常");
        }
    }

    /**
     * 保存发票单行信息
     *
     * @param parameters
     * @param line
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> saveInvoiceLine(JSONObject parameters, String line) throws Exception {
        // TODO Auto-generated method stub
        String currencyCode;
        BigDecimal taxRate;
        //返回的结果map
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //判断对账行信息的币种和税率是否一致，不一致则无法添加
        StringBuffer sqlBuffer = new StringBuffer(SrmCuaInvoiceLinesEntity_HI_RO.QUERY_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        sqlBuffer.append(" AND scil.INVOICE_ID = :invoiceId ");
        map.put("invoiceId", parameters.getInteger("invoiceId"));
        //对账单行信息
        List<SrmCuaInvoiceLinesEntity_HI_RO> linesList = JSON.parseArray(line, SrmCuaInvoiceLinesEntity_HI_RO.class);
        //获得对账行信息的数据
        List<SrmCuaInvoiceLinesEntity_HI_RO> list = srmCuaInvoiceLinesDAO_HI_RO.findList(sqlBuffer, map);
        if (null != list && list.size() != 0) {        //如果对账行信息不为空则获得币种和税率
            currencyCode = list.get(0).getCurrencyCode();    //获得币种
            taxRate = list.get(0).getTaxRate();        //获得税率

        } else {    //如果为空则遍历判断添加的行信息数据是否一致
            if (null == linesList || linesList.size() == 0) {    //如果添加的行信息为空
                resultMap.put("status", "E");
                resultMap.put("msg", "对账行为空");
                return resultMap;
            }
            currencyCode = linesList.get(0).getCurrencyCode();    //获得币种
            taxRate = linesList.get(0).getTaxRate();    //获得税率
        }
        LOGGER.info("currencyCode:" + currencyCode + "taxRate:" + taxRate);
        for (int i = 1; i < linesList.size(); i++) {    //遍历添加的行信息币种和税率是否一致
            if (!taxRate.equals(linesList.get(i).getTaxRate())) {
                resultMap.put("status", "E");
                resultMap.put("msg", "添加的税率不一致");
                return resultMap;
            }
            if (!currencyCode.equals(linesList.get(i).getCurrencyCode())) {
                resultMap.put("status", "E");
                resultMap.put("msg", "添加的币种不一致");
                return resultMap;
            }
        }
        //获得前端传来的对账单行信息
        List<SrmCuaInvoiceLinesEntity_HI> lines = JSON.parseArray(line, SrmCuaInvoiceLinesEntity_HI.class);
        LOGGER.info(lines.toString());

        for (int i = 0; i < lines.size(); i++) {
            lines.get(i).setCreatedBy(parameters.getInteger("varUserId"));
            lines.get(i).setInvoiceId(parameters.getInteger("invoiceId"));
            lines.get(i).setCreationDate(new Date());
            lines.get(i).setOperatorUserId(parameters.getInteger("varUserId"));
            srmCuaInvoiceLinesDAO_HI.saveOrUpdate(lines.get(i));
        }
        resultMap.put("status", "S");
        resultMap.put("msg", "添加对账行成功");
        return resultMap;
    }

    /**
     * 保存发票行信息后，计算发票金额税率等头信息并保存
     *
     * @param parameters
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> saveInvoiceLineAmount(JSONObject parameters) throws Exception {
        // TODO Auto-generated method stub

        //返回的结果map
        Map<String, Object> resultMap = new HashMap<String, Object>();
        StringBuffer sqlBuffer = new StringBuffer(SrmCuaInvoiceLinesEntity_HI_RO.QUERY_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        sqlBuffer.append(" AND scil.INVOICE_ID = :invoiceId ");
        map.put("invoiceId", parameters.getInteger("invoiceId"));
        List<SrmCuaInvoiceLinesEntity_HI_RO> list = srmCuaInvoiceLinesDAO_HI_RO.findList(sqlBuffer, map);
        String currencyCode = "";
        BigDecimal taxRate = new BigDecimal(0);
        BigDecimal invoiceAmount = new BigDecimal(0);    //对账总金额（含税）
        BigDecimal untaxedAmount = new BigDecimal(0);    //对账总金额（不含税）
        BigDecimal taxAmount = new BigDecimal(0);    //税额
        if (null != list && 0 != list.size()) {
            currencyCode = list.get(0).getCurrencyCode();    //币种
            taxRate = list.get(0).getTaxRate();    //税率
        }
        for (int i = 0; i < list.size(); i++) {
            invoiceAmount = invoiceAmount.add(list.get(i).getTaxAmount());
            untaxedAmount = untaxedAmount.add(list.get(i).getAmount());
            taxAmount = taxAmount.add(list.get(i).getTaxMoney());
        }
        SrmCuaInvoicesEntity_HI invoice = srmCuaInvoicesDAO_HI.getById(parameters.getInteger("invoiceId"));    //获得对账单实体，把计算后的对账金额保存到数据库里面
        invoice.setTaxAmount(taxAmount);    //税额
        invoice.setInvoiceAmount(invoiceAmount);    //发票金额（含税）
        invoice.setUntaxedAmount(untaxedAmount);    //未税金额
        invoice.setCurrencyCode(currencyCode);    //币种
        invoice.setTaxRate(taxRate);    //税率
        invoice.setOperatorUserId(parameters.getInteger("varUserId"));
        srmCuaInvoicesDAO_HI.saveOrUpdate(invoice);        //保存到数据库
        resultMap.put("taxAmount", taxAmount);
        resultMap.put("untaxedAmount", untaxedAmount);
        resultMap.put("invoiceAmount", invoiceAmount);
        resultMap.put("currencyCode", currencyCode);
        resultMap.put("taxRate", taxRate);
        //计算总金额返回给前端

        return resultMap;
    }

    /**
     * 删除发票单行信息
     */
    @Override
    public JSONObject deleteInvoiceLine(JSONObject jsonParams) throws Exception {
        //如果发票单行id不为空
        if (null != jsonParams.getInteger("invoiceLineId")) {
            SrmCuaInvoiceLinesEntity_HI line = srmCuaInvoiceLinesDAO_HI.findByProperty("invoice_line_id", jsonParams.getInteger("invoiceLineId")).get(0);
            if (line != null) {
                srmCuaInvoiceLinesDAO_HI.delete(line);
                return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "参数无效，无法删除", 1, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "参数无效，无法删除", 1, null);
        }
    }

}
