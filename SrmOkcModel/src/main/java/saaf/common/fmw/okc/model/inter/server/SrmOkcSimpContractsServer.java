package saaf.common.fmw.okc.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.DynamicBaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.okc.model.entities.SrmOkcContractsEntity_HI;
import saaf.common.fmw.okc.model.inter.ISrmOkcSimpContracts;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：SrmOkcSimpContractsServer.java
 * Description：简版合同查询服务类
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date     @Author(Updated By)     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019/6/18      欧阳岛          创建
 * <p>
 * ==============================================================================
 */
@Component
public class SrmOkcSimpContractsServer implements ISrmOkcSimpContracts {

    @Autowired
    private DynamicBaseViewObject commonDAO_HI_DY;

    @Autowired
    private ViewObject<SrmOkcContractsEntity_HI> srmOkcContractsDAO_HI;

    public static String SIMP_QUERY_SQL =
                    "SELECT\n" +
                    "  soc.contract_id,\n" +
                    "  soc.contract_code,\n" +
                    "  soc.contract_name,\n" +
                    "  soc.contract_status,\n" +
                    "  soc.start_date,\n" +
                    "  soc.party_b_id,\n" +
                    "  soc.party_c_id,\n" +
                    "  ca.attachment_file_id,\n" +
                    "  brf.file_Name,\n" +
					"  brf.file_Type,\n" +
                    "  brf.access_Path\n" +
                    "FROM\n" +
                    "  srm_okc_contracts soc\n" +
                    "  LEFT JOIN srm_okc_contract_attachments ca\n" +
                    "    ON ca.contract_id = soc.contract_id\n" +
                    "  LEFT JOIN saaf_base_result_file brf\n" +
                    "    ON brf.file_id = ca.attachment_file_id\n" +
                    "WHERE soc.contract_status != '1'";
//-- group by soc.contract_id

    public static String ENHANCE_QUERY_SQL =
                    "SELECT soc.contract_id\n" +
                    "      ,soc.contract_code\n" +
                    "      ,soc.version_number\n" +
                    "      ,soc.contract_name\n" +
                    "      ,soc.party_a_id\n" +
                    "      ,sia.inst_name AS party_a_name\n" +
                    "      ,soc.party_b_id\n" +
                    "      ,sps.supplier_name AS party_b_name\n" +
                    "      ,soc.created_by\n" +
                    "      ,su.user_full_name AS created_by_name\n" +
                    "      ,soc.start_date\n" +
                    "      ,soc.total_amount\n" +
                    "      ,soc.paid_amount\n" +
                    "      ,soc.contract_type\n" +
                    "      ,slv.meaning AS contract_type_name\n" +
                    "      ,soc.source_code\n" +
                    "      ,decode(soc.termination_date, NULL, 'N', 'Y') AS termination_flag\n" +
                    "      ,soc.termination_date\n" +
                    "FROM   srm_okc_contracts soc\n" +
                    "LEFT   JOIN saaf_institution sia\n" +
                    "ON     sia.inst_type = 'ORG'\n" +
                    "AND    sia.inst_id = soc.party_a_id\n" +
                    "LEFT   JOIN srm_pos_supplier_info sps\n" +
                    "ON     sps.supplier_id = soc.party_b_id\n" +
                    "LEFT   JOIN saaf_users su\n" +
                    "ON     su.user_id = soc.created_by\n" +
                    "LEFT   JOIN saaf_lookup_values slv\n" +
                    "ON     slv.lookup_type = 'CON_CON_TYPE'\n" +
                    "AND    slv.lookup_code = soc.contract_type\n" +
                    "WHERE  soc.contract_status = '5'";

//-- group by soc.contract_id

    /**
     * Description：查询供应商已签订合同列表
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/18      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public Pagination<JSONObject> findSrmOkcSimpContractsList(JSONObject parameters, Integer supplierId, Integer pageIndex, Integer pageRows) {
        StringBuffer queryString = new StringBuffer(SIMP_QUERY_SQL);
        queryString.append(" AND (soc.party_b_id = ").append(supplierId).append(" OR soc.party_c_id= ").append(supplierId).append(") ");
        Map<String, Object> map = new HashMap<>();
        SaafToolUtils.parperParam(parameters, "soc.contract_code", "contractCode", queryString, map, "=");
        SaafToolUtils.parperParam(parameters, "soc.contract_name", "contractName", queryString, map, "like");
        SaafToolUtils.parperParam(parameters, "soc.start_date", "startDateFrom", queryString, map, ">=");
        SaafToolUtils.parperParam(parameters, "soc.start_date", "startDateTo", queryString, map, "<=");
        String countSql = "select count(1) from (" + queryString + ")";
        return commonDAO_HI_DY.findPagination(queryString,countSql, map, pageIndex, pageRows);
    }

    /**
     * Description：供应商“发起供方确认”及已签订合同列表
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/19      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public Pagination<JSONObject> findSrmOkcEnhanceContractsList(JSONObject parameters, Integer supplierId, Integer pageIndex, Integer pageRows) {
        StringBuffer queryString = new StringBuffer(ENHANCE_QUERY_SQL);
        queryString.append(" AND (soc.party_b_id = ").append(supplierId).append(" OR soc.party_c_id = ").append(supplierId).append(")\n");
        Map<String, Object> map = new HashMap<>();
        SaafToolUtils.parperParam(parameters, "soc.contract_code", "contractCode", queryString, map, "=");
        SaafToolUtils.parperParam(parameters, "soc.contract_name", "contractName", queryString, map, "LIKE");
        String startDateFrom = parameters.getString("startDateFrom");
        if (startDateFrom != null && !"".equals(startDateFrom.trim())) {
            queryString.append(" AND trunc(soc.start_date) >= to_date(:startDateFrom, 'yyyy-mm-dd')\n");
            map.put("startDateFrom", startDateFrom);
        }
        String startDateTo = parameters.getString("startDateTo");
        if (startDateTo != null && !"".equals(startDateTo.trim())) {
            queryString.append(" AND trunc(soc.start_date) <= to_date(:startDateTo, 'yyyy-mm-dd')\n");
            map.put("startDateTo", startDateTo);
        }
        String countSql = "select count(1) from (" + queryString + ")";
        return commonDAO_HI_DY.findPagination(queryString,countSql, map, pageIndex, pageRows);
    }

    /**
     * Description：确认合同
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/24      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public String doConfirmContract(Integer contractId, Integer supplierId, Integer operatorId) {
        SrmOkcContractsEntity_HI srmOkcContractsEntity_HI = srmOkcContractsDAO_HI.getById(contractId);
        String contractStatus = srmOkcContractsEntity_HI.getContractStatus();
        if (!"5".equalsIgnoreCase(contractStatus)) {
            return "合同不是待确认状态，不允许确认！";
        }
        Integer partyBId = srmOkcContractsEntity_HI.getPartyBId();
        if (partyBId != null && partyBId.compareTo(supplierId) == 0) {
            srmOkcContractsEntity_HI.setPartyBSignDate(new Date());
        }
        Integer partyCId = srmOkcContractsEntity_HI.getPartyCId();
        if (partyCId != null && partyCId.compareTo(supplierId) == 0) {
            srmOkcContractsEntity_HI.setPartyCSignDate(new Date());
        }
        // 如果有丙方,需要乙方与丙方都确认后才能修改合同状态
        Date partyBSignDate = srmOkcContractsEntity_HI.getPartyBSignDate();
        if (partyBSignDate != null) {
            if (partyCId == null) {
                srmOkcContractsEntity_HI.setContractStatus("6");
            } else {
                Date partyCSignDate = srmOkcContractsEntity_HI.getPartyCSignDate();
                if (partyCSignDate != null) {
                    srmOkcContractsEntity_HI.setContractStatus("6");
                }
            }
        }
        srmOkcContractsEntity_HI.setOperatorUserId(operatorId);
        srmOkcContractsDAO_HI.update(srmOkcContractsEntity_HI);
		return "S";
    }

    /**
     * Description：拒绝合同
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/24      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public String doRejectContract(Integer contractId, Integer supplierId, Integer operatorId) {
        SrmOkcContractsEntity_HI srmOkcContractsEntity_HI = srmOkcContractsDAO_HI.getById(contractId);
        String contractStatus = srmOkcContractsEntity_HI.getContractStatus();
        if (!"5".equalsIgnoreCase(contractStatus)) {
            return "合同不是待确认状态，不允许拒绝！";
        }
        boolean doFlag = false;
        Integer partyBId = srmOkcContractsEntity_HI.getPartyBId();
        if (partyBId != null && partyBId.compareTo(supplierId) == 0) {
            doFlag = true;
        }
        Integer partyCId = srmOkcContractsEntity_HI.getPartyCId();
        if (partyCId != null && partyCId.compareTo(supplierId) == 0) {
            doFlag = true;
        }
        if (doFlag) {
            //清除乙方与丙方的签字日期,避免影响下次确认判断
            srmOkcContractsEntity_HI.setPartyBSignDate(null);
            srmOkcContractsEntity_HI.setPartyCSignDate(null);
            srmOkcContractsEntity_HI.setContractStatus("7");
            srmOkcContractsEntity_HI.setOperatorUserId(operatorId);
            srmOkcContractsDAO_HI.update(srmOkcContractsEntity_HI);
        }
		return "S";
    }

}
