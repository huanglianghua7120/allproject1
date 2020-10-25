package saaf.common.fmw.cua.model.inter.server;

import saaf.common.fmw.cua.model.inter.ISrmCuaInvoices;
import saaf.common.fmw.intf.model.inter.server.SrmUserInstSqlUtil;
import saaf.common.fmw.pos.model.dao.SrmPosSupplierInfoDAO_HI;
import saaf.common.fmw.pos.model.dao.readonly.SrmPosSupplierBankDAO_HI_RO;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierBankEntity_HI_RO;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.noggit.JSONParser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yhg.base.utils.SToolUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.dao.readonly.SrmBaseBanksDAO_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseBanksEntity_HI_RO;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.cua.model.dao.SrmCuaInvoiceLinesDAO_HI;
import saaf.common.fmw.cua.model.dao.SrmCuaInvoicesDAO_HI;
import saaf.common.fmw.cua.model.dao.readonly.SrmCuaInvoiceLinesDAO_HI_RO;
import saaf.common.fmw.cua.model.dao.readonly.SrmCuaInvoicesDAO_HI_RO;
import saaf.common.fmw.cua.model.entities.SrmCuaInvoiceLinesEntity_HI;
import saaf.common.fmw.cua.model.entities.SrmCuaInvoicesEntity_HI;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaInvoiceLinesEntity_HI_RO;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaInvoicesEntity_HI_RO;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.utils.SrmUtils;

/**
 * @author zhangxuan
 * @date 2018/11/9
 */
@Component("srmCuaInvoicesServer")
public class SrmCuaInvoicesServer implements ISrmCuaInvoices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmCuaInvoicesServer.class);

    @Resource
    private SrmCuaInvoicesDAO_HI srmCuaInvoicesDAO_HI;

    @Resource
    private SrmCuaInvoicesDAO_HI_RO srmCuaInvoicesDAO_HI_RO;

    @Resource
    private SrmCuaInvoiceLinesDAO_HI srmCuaInvoiceLinesDAO_HI;

    @Resource
    private SrmCuaInvoiceLinesDAO_HI_RO srmCuaInvoiceLinesDAO_HI_RO;

    @Resource
    private SrmPosSupplierInfoDAO_HI srmPosSupplierInfoDAO_HI;

    @Resource
    private SrmUserInstSqlUtil srmUserInstSqlUtil;

    @Resource
    private SrmPosSupplierBankDAO_HI_RO srmPosSupplierBankDAO_HI_RO;

    public SrmCuaInvoicesServer() {
        super();
    }

    /**
     * 查询发票列表信息
     */
    @Override
    public Pagination<SrmCuaInvoicesEntity_HI_RO> findInvoiceList(JSONObject parameters, Integer pageIndex,
                                                                  Integer pagerows) throws Exception {
        try {
            LOGGER.info("findInvoicesList--->>" + parameters);
            StringBuffer sqlBuffer = new StringBuffer(SrmCuaInvoicesEntity_HI_RO.QUERY_INVOICE_SQL);
            Map<String, Object> map = new HashMap();
            // 供应商
            if (null != parameters.getInteger("varSupplierId")) {
                SaafToolUtils.parperParam(parameters, " sci.supplier_id", "varSupplierId", sqlBuffer, map, "=");
            } else { //内部人员
                srmUserInstSqlUtil.concatUserInstSql(parameters.getInteger("varUserId"), sqlBuffer, map, "sci");
                SaafToolUtils.parperParam(parameters, " sci.supplier_id", "supplierId", sqlBuffer, map, "=");
            }

            //对账单号,通过发票id查出多个对账单id，并分组合并到一个字段里面
            String accountCode = parameters.getString("accountCode");
            if (accountCode != null && !"".equals(accountCode)) {
                //验证字符串是否含有SQL关键字及字符，有则返回NULL
                if (SrmUtils.isContainSQL(accountCode)) {
                    return null;
                }
                sqlBuffer.append(
                                "AND    (SELECT listagg(sca.account_code) within GROUP(ORDER BY sca.account_code)\n" +
                                "        FROM   srm_cua_accounts      sca\n" +
                                "              ,srm_cua_invoice_lines scil\n" +
                                "        WHERE  scil.account_id = sca.account_id) LIKE '%" + accountCode + "%'\n");
                map.put("accountCode", accountCode);
            }
            //如果职责是采购员，则只查询对账单状态不为拟定的数据
            if (parameters.containsKey("resp") && "po".equals(parameters.getString("resp"))) {
                sqlBuffer.append(" AND sci.invoice_status NOT IN ('PROTOCOL', 'REJUCT') ");
            }
            //单据状态
            SaafToolUtils.parperParam(parameters, " sci.invoice_status", "invoiceStatus", sqlBuffer, map, "=");
            //业务实体
            SaafToolUtils.parperParam(parameters, " sci.org_id", "instId", sqlBuffer, map, "=");
            //发票编号
            SaafToolUtils.parperParam(parameters, " sci.invoice_code", "invoiceCode", sqlBuffer, map, "LIKE");
            //发票号
            SaafToolUtils.parperParam(parameters, " sci.ext_invoice_code", "extInvoiceCode", sqlBuffer, map, "LIKE");
            //经办人
            SaafToolUtils.parperParam(parameters, " su.user_full_name", "userName", sqlBuffer, map, "LIKE");
            //接口状态
            SaafToolUtils.parperParam(parameters, " sci.interface_status", "interfaceStatus", sqlBuffer, map, "=");

            //开始日期
            String startDate = parameters.getString("startDate");
            if (startDate != null && !"".equals(startDate.trim())) {
                sqlBuffer.append(" AND sci.billing_date >= to_date(:startDate, 'yyyy-mm-dd')\n");
                map.put("startDate", startDate);
            }
            //结束日期
            String endDate = parameters.getString("endDate");
            if (endDate != null && !"".equals(endDate.trim())) {
                sqlBuffer.append(" AND sci.billing_date <= to_date(:endDate, 'yyyy-mm-dd')\n");
                map.put("endDate", endDate);
            }
            sqlBuffer.append(" ORDER BY sci.invoice_code DESC ");
            Pagination<SrmCuaInvoicesEntity_HI_RO> accountsList = srmCuaInvoicesDAO_HI_RO.findPagination(sqlBuffer, "SELECT count(1) FROM (" + sqlBuffer + ") xx", map,
                    pageIndex, pagerows);
            return accountsList;
        } catch (Exception e) {
            LOGGER.error("findInvoiceList 发票查询异常" + e);
            //e.printStackTrace();
            throw new Exception("发票查询异常");
        }

    }

    /**
     * 删除发票信息
     */
    @Override
    public JSONObject deleteInvoice(JSONObject jsonParams) throws Exception {
        //如果发票id不为空
        if (null != jsonParams.getInteger("invoiceId")) {
            //获得发票实体
            SrmCuaInvoicesEntity_HI invoice = srmCuaInvoicesDAO_HI.getById(jsonParams.getInteger("invoiceId"));
            if (invoice != null) {
                //获得所有对应的发票单行
                if (!"PROTOCOL".equals(invoice.getInvoiceStatus()) && !"REJUCT".equals(invoice.getInvoiceStatus())) {
                    return SToolUtils.convertResultJSONObj("E", "单据状态已变化，请刷新!", 1, null);
                }
                List<SrmCuaInvoiceLinesEntity_HI> list = srmCuaInvoiceLinesDAO_HI.findByProperty("invoice_id", jsonParams.getInteger("invoiceId"));
                if (!list.isEmpty()) {
                    //删除发票行数据
                    srmCuaInvoiceLinesDAO_HI.delete(list);
                }
                //删除发票数据
                srmCuaInvoicesDAO_HI.delete(invoice);
                return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "参数无效，无法删除", 1, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "参数无效，无法删除", 1, null);
        }
    }


    @Override
    public SrmCuaInvoicesEntity_HI_RO findInvoiceById(JSONObject params) {
        Integer invoiceId = params.getInteger("invoiceId");
        SrmCuaInvoicesEntity_HI_RO invoice = new SrmCuaInvoicesEntity_HI_RO();
        if (null == invoiceId) {    //如果发票id为空，则是新增页面跳转
            //经办人
            invoice.setUserName(params.getString("varUserName"));
            if (null != params.getString("varSupplierId")) {    //如果是供应商登录，则回显供应商名称
                //获得供应商名称
                SrmPosSupplierInfoEntity_HI supplier = srmPosSupplierInfoDAO_HI.findByProperty("supplier_id", params.getInteger("varSupplierId")).get(0);
                invoice.setSupplierId(params.getInteger("varSupplierId"));
                invoice.setSupplierName(supplier.getSupplierName());
            }
            invoice.setInvoiceStatus("PROTOCOL");
            invoice.setInterfaceStatus("N");
        } else {        //否则是修改页面跳转
            List<SrmCuaInvoicesEntity_HI_RO> invoiceList =
                    srmCuaInvoicesDAO_HI_RO.findList(SrmCuaInvoicesEntity_HI_RO.QUERY_INVOICEBYID_SQL + " and sci.invoice_id = ?", invoiceId);
            if (null == invoiceList || invoiceList.size() == 0) {
                return invoice;
            }
            //按照id查询，取出第一条记录
            invoice = invoiceList.get(0);
            //查询预警领导
            findAccountsByInvoiceId(invoice);
        }
        return invoice;
    }


    /**
     * 根据发票id来查询对账单，并将属性添加到传入的对象中
     *
     * @param invoice 发票对象，执行该方法会将对账单的信息添加到该对象中
     */
    private void findAccountsByInvoiceId(SrmCuaInvoicesEntity_HI_RO invoice) {
        if (null == invoice || invoice.getInvoiceId() == null) {
            return;
        }
        List<SrmCuaInvoiceLinesEntity_HI_RO> lineList =
                srmCuaInvoiceLinesDAO_HI_RO.findList(SrmCuaInvoiceLinesEntity_HI_RO.QUERY_SQL + " and scil.invoice_id = ?", invoice.getInvoiceId());
        if (null == lineList || lineList.size() == 0) {
            return;
        }
        //将对账单信息添加到发票中
        invoice.setInvoiceLineList(lineList);
    }

    /**
     * 保存发票
     *
     * @param parameters
     * @return
     * @throws Exception
     */
    @Override
    public SrmCuaInvoicesEntity_HI saveInvoice(JSONObject parameters) throws Exception {
        LOGGER.info("saveInvoice--->>" + parameters);
        //如果是新增发票头信息则创建一个新的类
        Integer invoiceId = parameters.getInteger("invoiceId");
        Integer varUserId = parameters.getInteger("varUserId");
        SrmCuaInvoicesEntity_HI invoice;
        //如果是作废
        if (null != parameters.getBoolean("onlyChangeStatus") && parameters.getBoolean("onlyChangeStatus") == true) {
            invoice = srmCuaInvoicesDAO_HI.getById(invoiceId);
            invoice.setInvoiceStatus("INVALID");    //状态变成作废
            invoice.setInterfaceStatus("I");//接口状态变成作废
            invoice.setOperatorUserId(varUserId);
            srmCuaInvoicesDAO_HI.saveOrUpdate(invoice);
            return invoice;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("invoiceId", parameters.getInteger("invoiceId"));

        //如果是修改发票头信息则把数据插进原有的信息里
        if (null != invoiceId && !"".equals(invoiceId.toString().trim())) {
            invoice = srmCuaInvoicesDAO_HI.getById(invoiceId);
            if (!updateValidate(parameters, invoice)) {
                return null;
            }
            try {
                SaafToolUtils.json2javaObj(SrmCuaInvoicesEntity_HI.class, parameters, invoice);
            } catch (Exception e) {
                //e.printStackTrace();
                throw new RuntimeException(e);
            }
            invoice.setCondition(parameters.getString("paymentTermCode"));    //条件
            if (null != parameters.getString("invoiceStatus") && parameters.getString("invoiceStatus").equals("AFFIRM")) {    //如果发票状态为确认
                invoice.setApprovalDate(new Date());    //确认日期
                invoice.setInterfaceStatus("W");    //接口状态改为同步中
            }

        } else {    //新增一个发票
            invoice = JSON.toJavaObject(parameters, SrmCuaInvoicesEntity_HI.class);
            invoice.setCreationDate(new Date());
            invoice.setCreatedBy(varUserId);
            invoice.setInvoiceCode(generateNewInvoiceCode());
            invoice.setOrgId(parameters.getInteger("instId"));
        }
        invoice.setOperatorUserId(varUserId);
        //存进数据库，获得发票id
        LOGGER.info("saveInvoice的invoice--->>" + invoice.toString());
        srmCuaInvoicesDAO_HI.saveOrUpdate(invoice);
        return invoice;
    }

    /**
     * 生成新插入记录的协议号，协议号格式为XY+年月日+流水号
     *
     * @return 返回新的协议号
     */
    private String generateNewInvoiceCode() {
        List<SrmCuaInvoicesEntity_HI_RO> invoiceList = srmCuaInvoicesDAO_HI_RO.findList(SrmCuaInvoicesEntity_HI_RO.QUERY_LAST_INVOICE_CODE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String formatDate = dateFormat.format(new Date());

        if (null == invoiceList || invoiceList.size() == 0 || null == invoiceList.get(0).getInvoiceCode()) {
            //当天插入的第一条记录，流水号为001
            return "FF" + formatDate + "001";
        } else {
            String lastInvoiceCodeToday = invoiceList.get(0).getInvoiceCode();
            //取出最后三个字符的流水号，加一作为新的流水号
            String numStr = lastInvoiceCodeToday.substring(lastInvoiceCodeToday.length() - 3);
            Formatter formatter = new Formatter();
            String newNum = formatter.format("%03d", Integer.parseInt(numStr) + 1).toString();
            formatter.close();
            return "FF" + formatDate + newNum;
        }
    }

    /**
     * 校验更新数据合法性
     *
     * @param jsonParams 新的单据信息
     * @param invoice    旧的单据对象
     * @return 返回校验结果
     */
    private boolean updateValidate(JSONObject jsonParams, SrmCuaInvoicesEntity_HI invoice) {
        if (invoice == null) {
            throw new RuntimeException("该记录已经被删除！");
        }
        String oldStatus = jsonParams.getString("oldStatus");
        if (oldStatus == null || !oldStatus.equals(invoice.getInvoiceStatus())) {
            throw new RuntimeException("单据状态已变化，请刷新！");
        }
        return true;
    }

    /**
     * 银行查询
     *
     * @param jsonParams
     * @return
     */
    @Override
    public List<SrmPosSupplierBankEntity_HI_RO> findSupplierBankInfoToInvoice(JSONObject jsonParams) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM srm_pos_supplier_bank t WHERE  ( t.INVALID_DATE  is null or t.invalid_date > sysdate()) ");
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplierId", sb, queryParamMap, "=");
        List<SrmPosSupplierBankEntity_HI_RO> result = srmPosSupplierBankDAO_HI_RO.findList(sb.toString(), queryParamMap);
        return result;
    }
}
