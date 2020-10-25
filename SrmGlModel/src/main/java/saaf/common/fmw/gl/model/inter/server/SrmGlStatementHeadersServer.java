package saaf.common.fmw.gl.model.inter.server;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.gl.model.entities.readonly.SrmGlStatementHeadersEntity_HI_RO;
import saaf.common.fmw.gl.model.inter.ISrmGlStatementHeaders;

@Component("/srmGlStatementHeadersServer")
public class SrmGlStatementHeadersServer implements ISrmGlStatementHeaders {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmGlStatementHeadersServer.class);

    public SrmGlStatementHeadersServer() {
        super();
    }

    @Autowired
    private BaseViewObject<SrmGlStatementHeadersEntity_HI_RO> srmGlStatementHeadersDAO_HI_RO;

    /**
     * 查询对账单
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public Pagination<SrmGlStatementHeadersEntity_HI_RO> findStatementList(JSONObject jsonParams, Integer pageIndex,
                                                                           Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmGlStatementHeadersEntity_HI_RO.QUERY_STATEMENT_LIST_SQL);
            SaafToolUtils.parperParam(jsonParams, "sgsh.supplier_id", "supplier_id", queryString, queryParamMap, "="); //供应商查询
            SaafToolUtils.parperParam(jsonParams, "sgsh.supplier_id", "supplierId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "sgsh.statement_number", "statementNumber", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "sgsh.statement_status", "statementStatus", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "sgsh.bill_status", "billStatus", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "spsi.supplier_name", "supplierName", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "ssgsh.creation_date", "creationDateFrom", queryString, queryParamMap, ">=");
            SaafToolUtils.parperParam(jsonParams, "sgsh.creation_date", "creationDateTo", queryString, queryParamMap, "<=");
            Pagination<SrmGlStatementHeadersEntity_HI_RO> result = srmGlStatementHeadersDAO_HI_RO.findPagination(queryString.toString(), queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询对账单lov
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     */
    public Pagination<SrmGlStatementHeadersEntity_HI_RO> findStatementLov(JSONObject jsonParams, Integer pageIndex,
                                                                          Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmGlStatementHeadersEntity_HI_RO.QUERY_STATEMENT_LOV_SQL);
            SaafToolUtils.parperParam(jsonParams, "sgsh.statement_number", "statementNumber", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "sgsh.supplier_id", "supplier_id", queryString, queryParamMap, "="); //供应商查询
            Pagination<SrmGlStatementHeadersEntity_HI_RO> result = srmGlStatementHeadersDAO_HI_RO.findPagination(queryString.toString(), queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}

