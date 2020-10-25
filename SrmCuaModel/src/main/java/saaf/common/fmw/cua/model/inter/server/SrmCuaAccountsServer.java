package saaf.common.fmw.cua.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.HibernateHandler;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.hibernate.Query;
import org.hibernate.Session;
import org.noggit.JSONParser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.dao.readonly.SaafInstitutionDAO_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SaafInstitutionEntity_HI_RO;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.cua.model.dao.SrmCuaAccountLinesDAO_HI;
import saaf.common.fmw.cua.model.dao.SrmCuaAccountsDAO_HI;
import saaf.common.fmw.cua.model.dao.readonly.SrmCuaAccountLinesDAO_HI_RO;
import saaf.common.fmw.cua.model.dao.readonly.SrmCuaAccountsDAO_HI_RO;
import saaf.common.fmw.cua.model.entities.SrmCuaAccountLinesEntity_HI;
import saaf.common.fmw.cua.model.entities.SrmCuaAccountsEntity_HI;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaAccountLinesEntity_HI_RO;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaAccountsEntity_HI_RO;
import saaf.common.fmw.cua.model.inter.ISrmCuaAccounts;
import saaf.common.fmw.intf.model.inter.server.SrmUserInstSqlUtil;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierInfoEntity_HI_RO;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhangxuan
 * @date 2018/10/30
 */
@Component("srmCuaAccountsServer")
public class SrmCuaAccountsServer implements ISrmCuaAccounts {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmCuaAccountsServer.class);

    @Resource
    private SrmCuaAccountsDAO_HI srmCuaAccountsDAO_HI;

    @Resource
    private SaafInstitutionDAO_HI_RO saafInstitutionDAO_HI_RO;

    @Resource
    private SrmUserInstSqlUtil srmUserInstSqlUtil;

    @Resource
    private SrmCuaAccountsDAO_HI_RO srmCuaAccountsDAO_HI_RO;

    @Resource
    private SrmCuaAccountLinesDAO_HI srmCuaAccountLinesDAO_HI;

    @Resource
    private BaseViewObject<SrmPosSupplierInfoEntity_HI_RO> srmPosSupplierInfoDAO_HI_RO;

    public SrmCuaAccountsServer() {
        super();
    }


    /**
     * 查询对账单列表信息
     */
    @Override
    public Pagination<SrmCuaAccountsEntity_HI_RO> findAccountList(JSONObject parameters, Integer pageIndex,
                                                                  Integer pagerows) throws Exception {
        try {
            LOGGER.info("findAccountsList--->>" + parameters);
            StringBuffer sqlBuffer = new StringBuffer(SrmCuaAccountsEntity_HI_RO.QUERY_ACCOUNTS_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            //如果职责不是管理员，则只查询对账单状态不为拟定的数据
            if (parameters.containsKey("resp") && "supplier".equals(parameters.getString("resp"))) {
                sqlBuffer.append(" AND sca.ACCOUNT_STATUS not in ('PROTOCOL', 'REJUCT') ");
                if (null != parameters.getInteger("varSupplierId")) {    //如果有供应商id,则只查自己的数据
                    sqlBuffer.append(" AND sca.SUPPLIER_ID = :supplierId ");
                    map.put("supplierId", parameters.getInteger("varSupplierId"));
                }
            } else {    //是管理员
                srmUserInstSqlUtil.concatUserInstSql(parameters.getInteger("varUserId"), sqlBuffer, map, "sca");
            }
            // 供应商
            SaafToolUtils.parperParam(parameters, " sca.SUPPLIER_ID", "supplierId", sqlBuffer, map, "=");
            //对账单号
            SaafToolUtils.parperParam(parameters, " sca.ACCOUNT_CODE", "accountCode", sqlBuffer, map, "like");
            //单据状态
            SaafToolUtils.parperParam(parameters, " sca.ACCOUNT_STATUS", "accountStatus", sqlBuffer, map, "=");
            //业务实体
            SaafToolUtils.parperParam(parameters, " sca.ORG_ID", "instId", sqlBuffer, map, "=");
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
                sqlBuffer.append(" AND sca.ACCOUNT_DATE >= :startDate ");
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
                sqlBuffer.append(" AND sca.ACCOUNT_DATE <= :endDate ");
            }
            sqlBuffer.append(" ORDER BY sca.ACCOUNT_CODE DESC ");
            Pagination<SrmCuaAccountsEntity_HI_RO> accountsList = srmCuaAccountsDAO_HI_RO.findPagination(sqlBuffer.toString(), "SELECT count(1) FROM (" + sqlBuffer.toString() + ") xx", map,
                    pageIndex, pagerows);
            return accountsList;
        } catch (Exception e) {
            LOGGER.error("findAccountsList 对账查询异常" + e);
            //e.printStackTrace();
            throw new Exception("对账查询异常");
        }

    }

    /**
     * 业务实体lov
     */
    @Override
    public Pagination<SaafInstitutionEntity_HI_RO> findAccountInstitutionLov(JSONObject queryParam, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            Map<String, Object> queryParamMap = new HashMap();
            StringBuffer sb = new StringBuffer();
            sb.append(SrmCuaAccountsEntity_HI_RO.QUERY_INSTITUTION_LOV);
            if (null != queryParam.getInteger("varSupplierId")) {
                //业务实体不和用户关联
                sb = new StringBuffer();
                sb.append(SrmCuaAccountsEntity_HI_RO.QUERY_INSTITUTION_LOV_NO_CONNECTION);
            } else {
                SaafToolUtils.parperParam(queryParam, "t2.user_id", "varUserId", sb, queryParamMap, "=");
            }
            SaafToolUtils.parperParam(queryParam, "t1.inst_type", "instType", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(queryParam, "t1.inst_code", "instCode", sb, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(queryParam, "t1.inst_name", "instName", sb, queryParamMap, "LIKE");
            Pagination<SaafInstitutionEntity_HI_RO> result = saafInstitutionDAO_HI_RO.findPagination(sb.toString(), "SELECT count(1) FROM (" + sb.toString() + ") xx", queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 删除对账单信息
     */
    @Override
    public JSONObject deleteAccount(JSONObject jsonParams) throws Exception {
        //如果对账单id不为空
        Integer accountId = jsonParams.getInteger("accountId");
        if (null != accountId) {
            //获得对账单实体
            SrmCuaAccountsEntity_HI account = srmCuaAccountsDAO_HI.getById(accountId);
            if (account != null) {
                if (!account.getAccountStatus().equals("PROTOCOL") && !account.getAccountStatus().equals("REJUCT")) {
                    throw new RuntimeException("单据状态已变化，请刷新！");
                }
                deleteAccountLineByAccountId(accountId);
                //删除对账单数据
                srmCuaAccountsDAO_HI.delete(account);
                return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "参数无效，无法删除", 1, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "参数无效，无法删除", 1, null);
        }
    }

    /**
     * 获得对账单状态
     */
    public JSONObject findAccountStatus(JSONObject jsonParams) throws Exception {
        if (null != jsonParams.getInteger("accountId")) {
            String newStatus = srmCuaAccountsDAO_HI.getById(jsonParams.getInteger("accountId")).getAccountStatus();    //新的单据状态，查看是否有其他人更改过单据状态
            return SToolUtils.convertResultJSONObj("S", "查询成功", 1, newStatus);
        } else {
            return SToolUtils.convertResultJSONObj("E", "查询失败", 1, null);
        }
    }

    /**
     * 查询对账单接收退货报表
     *
     * @param jsonParam 查询过滤条件参数
     * @param pageIndex 页码索引
     * @param pageRows  每页显示记录数量
     * @return 返回分页查询的对账单信息的json字符串
     */
    @Override
    public Pagination<SrmCuaAccountsEntity_HI_RO> findAccountReport(JSONObject jsonParam, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer queryString = new StringBuffer(SrmCuaAccountsEntity_HI_RO.QUERY_ACCOUNT_REPORT);
        Map<String, Object> queryParams = new HashMap<>(jsonParam.size());

        SaafToolUtils.parperParam(jsonParam, "ca.account_code", "accountCode", queryString, queryParams, "like");
        SaafToolUtils.parperParam(jsonParam, "ci.supplier_id", "supplierId", queryString, queryParams, "=");
        SaafToolUtils.parperParam(jsonParam, "cal.item_id", "itemId", queryString, queryParams, "=");
        SaafToolUtils.parperParam(jsonParam, "ca.org_id", "instId", queryString, queryParams, "=");
        SaafToolUtils.parperParam(jsonParam, "ci.invoice_code", "invoiceCode", queryString, queryParams, "like");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //开始日期
        if (null != jsonParam && jsonParam.containsKey("startDate") && null != jsonParam.get("startDate")) {
            try {
                //开始日期格式转化
                Date date = simpleDateFormat.parse(jsonParam.get("startDate").toString());
                queryParams.put("startDate", date);
            } catch (ParseException e) {
                //e.printStackTrace();
            }
            queryString.append(" AND ca.account_date >= to_date(:startDate, 'yyyy-mm-dd')\n");
        }

        //结束日期
        if (null != jsonParam && jsonParam.containsKey("endDate") && null != jsonParam.get("endDate")) {
            try {
                //结束日期格式转化
                Date date = simpleDateFormat.parse(jsonParam.get("endDate").toString());
                queryParams.put("endDate", date);
            } catch (ParseException e) {
                //e.printStackTrace();
            }
            queryString.append(" AND ca.account_date <= to_date(:endDate, 'yyyy-mm-dd')\n");
        }

        srmUserInstSqlUtil.concatUserInstSql(jsonParam.getInteger("varUserId"), queryString, queryParams, "ca");
        Pagination<SrmCuaAccountsEntity_HI_RO> pagination = this.srmCuaAccountsDAO_HI_RO.findPagination(queryString, "SELECT count(1) FROM (" + queryString + ") xx", queryParams, pageIndex, pageRows);
        return pagination;
    }

    /**
     * 更新对账单状态
     */
    @Override
    public JSONObject updateAccountStatus(JSONObject jsonParams, String line) throws Exception {
        SrmCuaAccountsEntity_HI account = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");    //规定日期格式
        if (null != jsonParams.getString("accountStatus")) {            //如果对账单状态不为空
            if (null != jsonParams.getInteger("accountId")) {        //如果对账单id不为空
                String newStatus = srmCuaAccountsDAO_HI.getById(jsonParams.getInteger("accountId")).getAccountStatus();    //新的单据状态，查看是否有其他人更改过单据状态
                if (!newStatus.equals(jsonParams.getString("oldStatus"))) {
                    return SToolUtils.convertResultJSONObj("E", "操作失败,状态已改变，请刷新", 1, null);
                }
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("accountId", jsonParams.getInteger("accountId"));
                account = srmCuaAccountsDAO_HI.findByProperty(map).get(0);        //获得对账单信息
                SaafToolUtils.json2javaObj(SrmCuaAccountsEntity_HI.class, jsonParams, account);
                account.setAccountStatus(jsonParams.getString("accountStatus"));    //设置对账单状态
                if ("TO_BE_CONFIRMED".equals(jsonParams.getString("accountStatus"))) {    //如果是点击提交
                    account.setAccountDate(sdf.parse(sdf.format(new Date())));        //添加对账日期
                }
                if ("AFFIRM".equals(jsonParams.getString("accountStatus"))) {    //如果是点击确认
                    account.setConfirmDate(sdf.parse(sdf.format(new Date())));    //添加确认日期
                }
                if ("AFFIRM".equals(jsonParams.getString("accountStatus")))
                    if (null != jsonParams.getInteger("fileId")) {    //如果附件不为空
                        account.setAccountFileId(jsonParams.getInteger("fileId"));
                    }
                account.setOperatorUserId(jsonParams.getInteger("varUserId"));
                srmCuaAccountsDAO_HI.update(account);
            }

            //更新对账单行信息
            List<SrmCuaAccountLinesEntity_HI> linesList = JSON.parseArray(line, SrmCuaAccountLinesEntity_HI.class);
            //如果对账单行不为空
            if (linesList != null) {
                for (int i = 0; i < linesList.size(); i++) {
                    if (null == linesList.get(i).getAccountLineId()) {    //如果是新添加的
                        linesList.get(i).setAccountId(jsonParams.getInteger("accountId"));
                        linesList.get(i).setOperatorUserId(jsonParams.getInteger("varUserId"));
                        srmCuaAccountLinesDAO_HI.saveOrUpdate(linesList.get(i));
                    } else if (linesList.get(i).getLineComments() != null) { //如果备注不为空
                        SrmCuaAccountLinesEntity_HI accountLine = srmCuaAccountLinesDAO_HI.getById(linesList.get(i).getAccountLineId());    //获得旧备注
                        if (null == accountLine.getLineComments() || !accountLine.getLineComments().equals(linesList.get(i).getLineComments())) {    //如果旧备注为空或者旧备注不等于新备注
                            accountLine.setLineComments(linesList.get(i).getLineComments());
                            accountLine.setOperatorUserId(jsonParams.getInteger("varUserId"));
                            srmCuaAccountLinesDAO_HI.saveOrUpdate(accountLine);
                        }
                    }
                }
            }
            return SToolUtils.convertResultJSONObj("S", "修改成功", 1, account);
        } else {
            return SToolUtils.convertResultJSONObj("E", "参数无效，无法修改", 1, null);
        }

    }

    /**
     * 查询对账单详细信息
     */
    @Override
    public Map<String, Object> findAccountInfo(JSONObject jsonParams) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer accountId = jsonParams.getInteger("accountId");
        //修改按钮跳转，需要记录对账单头信息的数据
        if (accountId != 0) {
            Map<String, Object> sqlMap = new HashMap<String, Object>();
            StringBuffer sqlBuffer = new StringBuffer(SrmCuaAccountsEntity_HI_RO.QUERY_ACCOUNT_DETAIL_SQL);
            StringBuffer linesBuffer = new StringBuffer(SrmCuaAccountLinesEntity_HI_RO.QUERY_ACCOUNT_LINES_SQL);
            if (accountId != null) {
                sqlBuffer.append("AND sca.ACCOUNT_ID = :accountId");
                sqlMap.put("accountId", accountId);
                linesBuffer.append("AND scal.ACCOUNT_ID = :accountId");
            }
            //获得对账单详细信息
            List<SrmCuaAccountsEntity_HI_RO> accountList = srmCuaAccountsDAO_HI_RO.findList(sqlBuffer, sqlMap);
            SrmCuaAccountsEntity_HI_RO account = accountList.get(0);
            if (account.getCreationDate().equals(account.getLastUpdateDate())) {
                map.put("isEquals", true);
            } else {
                map.put("isEquals", false);
            }
            //返回数据
            map.put("accountEntity", accountList.get(0));
        }
        //新增按钮跳转，需要新建
        else {
			//获得对账单创建日期是当天的数量，用于创建流水号
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");    //规定日期格式
            SrmCuaAccountsEntity_HI_RO info = new SrmCuaAccountsEntity_HI_RO();
            info.setAccountStatus("PROTOCOL");
            info.setReceiveAmount(new java.math.BigDecimal(0));
            info.setReturnAmount(new java.math.BigDecimal(0));
            info.setReceiveTaxAmount(new java.math.BigDecimal(0));
            info.setReturnTaxAmount(new java.math.BigDecimal(0));
            info.setAmount(new java.math.BigDecimal(0));
            info.setTaxAmount(new java.math.BigDecimal(0));
            info.setCreationDate(sdf.parse(sdf.format(new Date())));
            info.setUserName(jsonParams.getString("varEmployeeName"));
            map.put("accountEntity", info);
        }
        return map;
    }


    /**
     * 生成新插入记录的协议号，协议号格式为XY+年月日+流水号
     *
     * @return 返回新的协议号
     */
    private String generateNewAccountCode() {
        List<SrmCuaAccountsEntity_HI_RO> accountList = srmCuaAccountsDAO_HI_RO.findList(SrmCuaAccountsEntity_HI_RO.QUERY_LAST_ACCOUNT_CODE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String formatDate = dateFormat.format(new Date());
        if (null == accountList || accountList.size() == 0 || null == accountList.get(0).getAccountCode()) {
            //当天插入的第一条记录，流水号为001
            return "C" + formatDate + "001";
        } else {
            String lastAccountCodeToday = accountList.get(0).getAccountCode();
            //取出最后三个字符的流水号，加一作为新的流水号
            String numStr = lastAccountCodeToday.substring(lastAccountCodeToday.length() - 3);
            Formatter formatter = new Formatter();
            String newNum = formatter.format("%03d", Integer.parseInt(numStr) + 1).toString();
            formatter.close();
            return "C" + formatDate + newNum;
        }
    }


    /**
     * 保存对账单信息
     *
     * @param parameters
     * @param line
     * @return
     * @throws Exception
     */
//	@Override
    public SrmCuaAccountsEntity_HI saveAccount(JSONObject parameters, String line) throws Exception {
        // TODO Auto-generated method stub
        //如果是新增对账单信息则创建一个新的类
        Integer varUserId = parameters.getInteger("varUserId");
        SrmCuaAccountsEntity_HI account;
        Integer accountId = parameters.getInteger("accountId");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("accountId", parameters.getInteger("accountId"));
        //如果是修改对账单头信息则把数据插进原有的信息里
        if (null != accountId && !"".equals(accountId.toString().trim())) {
            account = srmCuaAccountsDAO_HI.getById(accountId);
            account.setComments(parameters.getString("comments"));
            account.setReceiveAmount(parameters.getBigDecimal("receiveAmount"));
            account.setReceiveTaxAmount(parameters.getBigDecimal("receiveTaxAmount"));
            account.setReturnAmount(parameters.getBigDecimal("returnAmount"));
            account.setReturnTaxAmount(parameters.getBigDecimal("returnTaxAmount"));

        } else {    //如果是新建对账单
            account = JSON.toJavaObject(parameters, SrmCuaAccountsEntity_HI.class);
            account.setCreationDate(new Date());
            account.setCreatedBy(varUserId);
            account.setAccountCode(generateNewAccountCode());
        }
        account.setOperatorUserId(parameters.getInteger("varUserId"));
        srmCuaAccountsDAO_HI.saveOrUpdate(account);

        //对账单行信息
        List<SrmCuaAccountLinesEntity_HI> linesList = JSON.parseArray(line, SrmCuaAccountLinesEntity_HI.class);
        //如果前端返回的行信息不为空
        if (linesList != null) {
            LOGGER.info(linesList.toString());
            for (int i = 0; i < linesList.size(); i++) {
                if (null == linesList.get(i).getAccountLineId()) {    //如果是新添加的
                    linesList.get(i).setAccountId(parameters.getInteger("accountId"));
                    linesList.get(i).setOperatorUserId(parameters.getInteger("varUserId"));
                    srmCuaAccountLinesDAO_HI.saveOrUpdate(linesList.get(i));
                } else if (linesList.get(i).getLineComments() != null) { //如果备注不为空
                    SrmCuaAccountLinesEntity_HI accountLine = srmCuaAccountLinesDAO_HI.getById(linesList.get(i).getAccountLineId());    //获得旧备注
                    if (null == accountLine.getLineComments() || !accountLine.getLineComments().equals(linesList.get(i).getLineComments())) {    //如果旧备注为空或者旧备注不等于新备注

                        accountLine.setLineComments(linesList.get(i).getLineComments());
                        accountLine.setOperatorUserId(parameters.getInteger("varUserId"));
                        srmCuaAccountLinesDAO_HI.saveOrUpdate(accountLine);
                    }
                }

            }
        }
        return account;
    }

    /**
     * 通过审核计划id删除子行记录
     *
     * @param accountId 审核计划Id
     */
    private void deleteAccountLineByAccountId(final Integer accountId) {
        if (accountId == null) {
            return;
        }
        final StringBuffer hql = new StringBuffer("delete from ");
        hql.append(SrmCuaAccountLinesEntity_HI.class.getName()).append(" where accountId = :accountId");
        srmCuaAccountLinesDAO_HI.executeUpdate(new HibernateHandler() {
            @Override
            public Object doInHibernate(Session session) {
                Query query = session.createQuery(hql.toString());
                query.setParameter("accountId", accountId);
                return query.executeUpdate();
            }
        });
    }


    @Override
    public Pagination<SrmCuaAccountsEntity_HI_RO> findValidSupplierLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        //获取分页查询的条件
        StringBuffer querySql = new StringBuffer(SrmCuaAccountsEntity_HI_RO.QUERY_VALID_SUPPLIER);
        HashMap<String, Object> paramMap = new HashMap<>(3);
        //供应商名称
        SaafToolUtils.parperParam(jsonParams, "spsi.supplier_name", "supplierName", querySql, paramMap, "like");
        //供应商编号
        String supplierNumber = jsonParams.getString("supplierNumber");
        if (supplierNumber != null && !"".equals(supplierNumber)) {
            querySql.append(" AND spsi.supplier_number LIKE :supplierNumber");
            paramMap.put("supplierNumber", "%" + supplierNumber + "%");
        }
        //查询有效的供应商
        querySql.append(" AND (slv.lookup_code = 'INTRODUCING' OR slv.lookup_code = 'EFFETIVE' OR slv.lookup_code = 'APPROVED') ");
        return srmCuaAccountsDAO_HI_RO.findPagination(querySql.toString(), "SELECT count(1) FROM (" + querySql + ") xx", paramMap, pageIndex, pageRows);
    }

    /*
     * 供应商lov
     */
    @Override
    public Pagination<SrmPosSupplierInfoEntity_HI_RO> findSupplierLov(JSONObject queryParam, Integer pageIndex,
                                                                      Integer pageRows) throws Exception {
        try {
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer sb = new StringBuffer();
            sb.append(SrmCuaAccountsEntity_HI_RO.QUERY_SUPPLIER_LOV);
            SaafToolUtils.parperParam(queryParam, "si.supplier_name", "supplierName", sb, queryParamMap, "like");
            SaafToolUtils.parperParam(queryParam, "si.supplier_number", "supplierNumber", sb, queryParamMap, "like");
            String supplierStatus = queryParam.getString("supplierStatus");
            if ("QUALIFICATION".equals(supplierStatus)) {
                //查询状态为合格或潜在的供应商
                sb.append(" AND si.supplier_status IN ('EFFETIVE','APPROVED','INTRODUCING','SUBMITTED' )");
            } else if ("RAWABNORMAL".equals(supplierStatus)) {
                //查询状态为合格的供应商
                sb.append(" AND si.supplier_status IN ('EFFETIVE','APPROVED','INTRODUCING' )");
            }
            Pagination<SrmPosSupplierInfoEntity_HI_RO> result = srmPosSupplierInfoDAO_HI_RO.findPagination(sb.toString(), "SELECT count(1) FROM (" + sb.toString() + ") xx", queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
