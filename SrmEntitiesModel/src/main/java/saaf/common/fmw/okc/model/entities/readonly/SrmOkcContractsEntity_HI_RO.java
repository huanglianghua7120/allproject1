package saaf.common.fmw.okc.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SrmOkcContractsEntity_HI_RO implements Serializable {

    public static String SRM_OKC_CONTRACTS_QUERY_SQL =
                    "SELECT\n" +
                    "  t.contract_id AS contractId,\n" +
                    "  t.contract_code AS contractCode,\n" +
                    "  t.contract_name AS contractName,\n" +
                    "  t.contract_type AS contractType,\n" +
                    "  t.party_a_id AS partyAId,\n" +
                    "  t.party_b_id AS partyBId,\n" +
                    "  t.party_c_id AS partyCId,\n" +
                    "  t.contract_status AS contractStatus,\n" +
                    "  t.contract_approval_status AS contractApprovalStatus,\n" +
                    "  t.handle_department_id AS handleDepartmentId,\n" +
                    "  t.main_contract_flag AS mainContractFlag,\n" +
                    "  t.transfer_po_flag AS transferPoFlag,\n" +
                    "  t.version_number AS versionNumber,\n" +
                    "  t.created_mode AS createdMode,\n" +
                    "  t.template_id AS templateId,\n" +
                    "  t.watermark_id AS watermarkId,\n" +
                    "  t.payment_term_id AS paymentTermId,\n" +
                    "  t.total_amount AS totalAmount,\n" +
                    "  t.paid_amount AS paidAmount,\n" +
                    "  t.currency_code AS currencyCode,\n" +
                    "  t.party_a_sign_date AS partyASignDate,\n" +
                    "  t.party_b_sign_date AS partyBSignDate,\n" +
                    "  t.party_c_sign_date AS partyCSignDate,\n" +
                    "  t.start_date AS startDate,\n" +
                    "  t.end_date AS endDate,\n" +
                    "  t.main_contract_id AS mainContractId,\n" +
                    "  t.signed_site AS signedSite,\n" +
                    "  t.termination_date AS terminationDate,\n" +
                    "  t.termination_reasons AS terminationReasons,\n" +
                    "  t.comments AS comments,\n" +
                    "  t.source_code AS sourceCode,\n" +
                    "  t.version_num AS versionNum,\n" +
                    "  t.creation_date AS creationDate,\n" +
                    "  t.created_by AS createdBy,\n" +
                    "  t.last_update_date AS lastUpdateDate,\n" +
                    "  t.last_updated_by AS lastUpdatedBy,\n" +
                    "  t.last_update_login AS lastUpdateLogin,\n" +
                    "  t.attribute_category AS attributeCategory,\n" +
                    "  t.attribute1 AS attribute1,\n" +
                    "  t.attribute2 AS attribute2,\n" +
                    "  t.attribute3 AS attribute3,\n" +
                    "  t.attribute4 AS attribute4,\n" +
                    "  t.attribute5 AS attribute5,\n" +
                    "  t.attribute6 AS attribute6,\n" +
                    "  t.attribute7 AS attribute7,\n" +
                    "  t.attribute8 AS attribute8,\n" +
                    "  t.attribute9 AS attribute9,\n" +
                    "  t.attribute10 AS attribute10,\n" +
                    "  t.attribute11 AS attribute11,\n" +
                    "  t.attribute12 AS attribute12,\n" +
                    "  t.attribute13 AS attribute13,\n" +
                    "  t.attribute14 AS attribute14,\n" +
                    "  t.attribute15 AS attribute15,\n" +
                    "  t.attribute16 AS attribute16,\n" +
                    "  t.attribute17 AS attribute17,\n" +
                    "  t.attribute18 AS attribute18,\n" +
                    "  t.attribute19 AS attribute19,\n" +
                    "  t.attribute20 AS attribute20,\n" +
                    "  t.attribute21 AS attribute21,\n" +
                    "  t.attribute22 AS attribute22,\n" +
                    "  t.attribute23 AS attribute23,\n" +
                    "  t.attribute24 AS attribute24,\n" +
                    "  t.attribute25 AS attribute25,\n" +
                    "  t.attribute26 AS attribute26,\n" +
                    "  t.attribute27 AS attribute27,\n" +
                    "  t.attribute28 AS attribute28,\n" +
                    "  t.attribute29 AS attribute29,\n" +
                    "  t.attribute30 AS attribute30,\n" +
                    "  lvcs.meaning AS contractStatusLabel,\n" +
                    "  lvcas.meaning AS contractApprovalStatusLabel,\n" +
                    "  a.inst_name AS partyAName,\n" +
                    "  b.supplier_name AS partyBName,\n" +
                    "  c.supplier_name AS partyCName,\n" +
                    "  tpl.template_name AS templateName,\n" +
                    "  u.user_full_name AS createdByName,\n" +
                    "  u2.user_name AS lastUpdatedByName,\n" +
                    "  mc.contract_code AS mainContractCode,\n" +
                    "  i.inst_name AS handleDepartmentName,\n" +
                    "  w.watermark_name AS watermarkName,\n" +
                    "  pt.payment_term_code AS paymentTermCode,\n" +
                    "  pt.payment_term_name AS paymentTermName,\n" +
                    "  t.ekp_number AS ekpNumber,\n" +
                    "  t.received_amount AS receivedAmount\n" +
                    "FROM\n" +
                    "  srm_okc_contracts t\n" +
                    "  INNER JOIN saaf_institution a\n" +
                    "    ON t.party_a_id = a.inst_id\n" +
                    "  INNER JOIN srm_pos_supplier_info b\n" +
                    "    ON t.party_b_id = b.supplier_id\n" +
                    "  LEFT JOIN saaf_institution i\n" +
                    "    ON t.handle_department_id = i.inst_id\n" +
                    "  LEFT JOIN srm_okc_watermarks w\n" +
                    "    ON t.watermark_id = w.watermark_id\n" +
                    "  LEFT JOIN srm_okc_contracts mc\n" +
                    "    ON t.main_contract_id = mc.contract_id\n" +
                    "  LEFT JOIN saaf_users u\n" +
                    "    ON u.user_id = t.created_by\n" +
                    "  INNER JOIN srm_pon_payment_terms pt\n" +
                    "    ON pt.payment_term_id = t.payment_term_id\n" +
                    "  LEFT JOIN saaf_users u2\n" +
                    "    ON u2.user_id = t.last_updated_by\n" +
                    "  LEFT JOIN saaf_lookup_values lvcs\n" +
                    "    ON lvcs.lookup_type = 'CON_CON_STATE'\n" +
                    "    AND lvcs.lookup_code = t.contract_status\n" +
                    "  LEFT JOIN saaf_lookup_values lvcas\n" +
                    "    ON lvcas.lookup_type = 'CON_CON_APPROVE_STATE'\n" +
                    "    AND lvcas.lookup_code = t.contract_approval_status\n" +
                    "  LEFT JOIN srm_okc_contract_templates tpl\n" +
                    "    ON t.template_id = tpl.template_id\n" +
                    "  LEFT JOIN srm_pos_supplier_info c\n" +
                    "    ON t.party_c_id = c.supplier_id\n" +
                    "WHERE (\n" +
                    "    t.created_by = :varUserId\n" +
                    "    OR EXISTS\n" +
                    "    (SELECT\n" +
                    "      1\n" +
                    "    FROM\n" +
                    "      saaf_user_resp sur,\n" +
                    "      saaf_responsibilitys sr\n" +
                    "    WHERE sur.responsibility_id = sr.responsibility_id\n" +
                    "      AND sr.responsibility_key = '000065'\n" +
                    "      AND sur.user_id = :varUserId)\n" +
                    "  )";

    public static String SRM_OKC_CONTRACTS_BY_ID =
                    "SELECT\n" +
                    "  t.contract_id AS contractId,\n" +
                    "  t.contract_code AS contractCode,\n" +
                    "  t.contract_name AS contractName,\n" +
                    "  t.contract_type AS contractType,\n" +
                    "  t.party_a_id AS partyAId,\n" +
                    "  t.party_b_id AS partyBId,\n" +
                    "  t.party_c_id AS partyCId,\n" +
                    "  t.contract_status AS contractStatus,\n" +
                    "  t.contract_approval_status AS contractApprovalStatus,\n" +
                    "  t.handle_department_id AS handleDepartmentId,\n" +
                    "  t.main_contract_flag AS mainContractFlag,\n" +
                    "  t.transfer_po_flag AS transferPoFlag,\n" +
                    "  t.version_number AS versionNumber,\n" +
                    "  t.created_mode AS createdMode,\n" +
                    "  t.template_id AS templateId,\n" +
                    "  t.watermark_id AS watermarkId,\n" +
                    "  t.payment_term_id AS paymentTermId,\n" +
                    "  t.total_amount AS totalAmount,\n" +
                    "  t.paid_amount AS paidAmount,\n" +
                    "  t.currency_code AS currencyCode,\n" +
                    "  t.party_a_sign_date AS partyASignDate,\n" +
                    "  t.party_b_sign_date AS partyBSignDate,\n" +
                    "  t.party_c_sign_date AS partyCSignDate,\n" +
                    "  t.start_date AS startDate,\n" +
                    "  t.end_date AS endDate,\n" +
                    "  t.main_contract_id AS mainContractId,\n" +
                    "  t.signed_site AS signedSite,\n" +
                    "  t.termination_date AS terminationDate,\n" +
                    "  t.termination_reasons AS terminationReasons,\n" +
                    "  t.comments AS comments,\n" +
                    "  t.source_code AS sourceCode,\n" +
                    "  t.source_id AS sourceId,\n" +
                    "  (SELECT\n" +
                    "    sph.po_number\n" +
                    "  FROM\n" +
                    "    srm_po_headers sph\n" +
                    "  WHERE sph.po_header_id = t.source_id) sourceBillCode,\n" +
                    "  t.version_num AS versionNum,\n" +
                    "  t.creation_date AS creationDate,\n" +
                    "  t.created_by AS createdBy,\n" +
                    "  t.last_update_date AS lastUpdateDate,\n" +
                    "  t.last_updated_by AS lastUpdatedBy,\n" +
                    "  t.last_update_login AS lastUpdateLogin,\n" +
                    "  t.attribute_category AS attributeCategory,\n" +
                    "  t.attribute1 AS attribute1,\n" +
                    "  t.attribute2 AS attribute2,\n" +
                    "  t.attribute3 AS attribute3,\n" +
                    "  t.attribute4 AS attribute4,\n" +
                    "  t.attribute5 AS attribute5,\n" +
                    "  t.attribute6 AS attribute6,\n" +
                    "  t.attribute7 AS attribute7,\n" +
                    "  t.attribute8 AS attribute8,\n" +
                    "  t.attribute9 AS attribute9,\n" +
                    "  t.attribute10 AS attribute10,\n" +
                    "  t.attribute11 AS attribute11,\n" +
                    "  t.attribute12 AS attribute12,\n" +
                    "  t.attribute13 AS attribute13,\n" +
                    "  t.attribute14 AS attribute14,\n" +
                    "  t.attribute15 AS attribute15,\n" +
                    "  t.attribute16 AS attribute16,\n" +
                    "  t.attribute17 AS attribute17,\n" +
                    "  t.attribute18 AS attribute18,\n" +
                    "  t.attribute19 AS attribute19,\n" +
                    "  t.attribute20 AS attribute20,\n" +
                    "  t.attribute21 AS attribute21,\n" +
                    "  t.attribute22 AS attribute22,\n" +
                    "  t.attribute23 AS attribute23,\n" +
                    "  t.attribute24 AS attribute24,\n" +
                    "  t.attribute25 AS attribute25,\n" +
                    "  t.attribute26 AS attribute26,\n" +
                    "  t.attribute27 AS attribute27,\n" +
                    "  t.attribute28 AS attribute28,\n" +
                    "  t.attribute29 AS attribute29,\n" +
                    "  t.attribute30 AS attribute30,\n" +
                    "  lvcs.meaning AS contractStatusLabel,\n" +
                    "  lvcas.meaning AS contractApprovalStatusLabel,\n" +
                    "  a.inst_name AS partyAName,\n" +
                    "  b.supplier_name AS partyBName,\n" +
                    "  c.supplier_name AS partyCName,\n" +
                    "  tpl.template_name AS templateName,\n" +
                    "  u.user_full_name AS createdByName,\n" +
                    "  u2.user_full_name AS lastUpdatedByName,\n" +
                    "  mc.contract_code AS mainContractCode,\n" +
                    "  i.inst_name AS handleDepartmentName,\n" +
                    "  w.watermark_name AS watermarkName,\n" +
                    "  pt.payment_term_code AS paymentTermCode,\n" +
                    "  pt.payment_term_name AS paymentTermName,\n" +
                    "  t.ekp_number AS ekpNumber,\n" +
                    "  t.received_amount AS receivedAmount\n" +
                    "FROM\n" +
                    "  srm_okc_contracts t\n" +
                    "  INNER JOIN saaf_institution a\n" +
                    "    ON t.party_a_id = a.inst_id\n" +
                    "  INNER JOIN srm_pos_supplier_info b\n" +
                    "    ON t.party_b_id = b.supplier_id\n" +
                    "  LEFT JOIN saaf_institution i\n" +
                    "    ON t.handle_department_id = i.inst_id\n" +
                    "  LEFT JOIN srm_okc_watermarks w\n" +
                    "    ON t.watermark_id = w.watermark_id\n" +
                    "  LEFT JOIN srm_okc_contracts mc\n" +
                    "    ON t.main_contract_id = mc.contract_id\n" +
                    "  LEFT JOIN saaf_users u\n" +
                    "    ON u.user_id = t.created_by\n" +
                    "  INNER JOIN srm_pon_payment_terms pt\n" +
                    "    ON pt.payment_term_id = t.payment_term_id\n" +
                    "  LEFT JOIN saaf_users u2\n" +
                    "    ON u2.user_id = t.last_updated_by\n" +
                    "  LEFT JOIN saaf_lookup_values lvcs\n" +
                    "    ON lvcs.lookup_type = 'CON_CON_STATE'\n" +
                    "    AND lvcs.lookup_code = t.contract_status\n" +
                    "  LEFT JOIN saaf_lookup_values lvcas\n" +
                    "    ON lvcas.lookup_type = 'CON_CON_APPROVE_STATE'\n" +
                    "    AND lvcas.lookup_code = t.contract_approval_status\n" +
                    "  LEFT JOIN srm_okc_contract_templates tpl\n" +
                    "    ON t.template_id = tpl.template_id\n" +
                    "  LEFT JOIN srm_pos_supplier_info c\n" +
                    "    ON t.party_c_id = c.supplier_id\n" +
                    "WHERE 1 = 1";

    public static String SRM_OKC_CONTRACTS_WARN_QUERY_SQL = "select " +
            "   t.contract_id as contractId ,  \r\n" +
            "   t.contract_code as contractCode ,  \r\n" +
            "   t.contract_name as contractName ,  \r\n" +
            "   t.contract_type as contractType ,  \r\n" +
            "   t.party_a_id as partyAId ,  \r\n" +
            "   t.party_b_id as partyBId ,  \r\n" +
            "   t.party_c_id as partyCId ,  \r\n" +
            "   t.contract_status as contractStatus ,  \r\n" +
            "   t.contract_approval_status as contractApprovalStatus ,  \r\n" +
            "   t.handle_department_id as handleDepartmentId ,  \r\n" +
            "   t.main_contract_flag as mainContractFlag ,  \r\n" +
            "   t.transfer_po_flag as transferPoFlag ,  \r\n" +
            "   t.version_number as versionNumber ,  \r\n" +
            "   t.created_mode as createdMode ,  \r\n" +
            "   t.template_id as templateId ,  \r\n" +
            "   t.watermark_id as watermarkId ,  \r\n" +
            "   t.payment_term_id as paymentTermId ,  \r\n" +
            "   t.total_amount as totalAmount ,  \r\n" +
            "   t.paid_amount as paidAmount ,  \r\n" +
            "   t.currency_code as currencyCode ,  \r\n" +
            "   t.party_a_sign_date as partyASignDate ,  \r\n" +
            "   t.party_b_sign_date as partyBSignDate ,  \r\n" +
            "   t.party_c_sign_date as partyCSignDate ,  \r\n" +
            "   t.start_date as startDate ,  \r\n" +
            "   t.end_date as endDate ,  \r\n" +
            "   t.main_contract_id as mainContractId ,  \r\n" +
            "   t.signed_site as signedSite ,  \r\n" +
            "   t.termination_date as terminationDate ,  \r\n" +
            "   t.termination_reasons as terminationReasons ,  \r\n" +
            "   t.comments as comments ,  \r\n" +
            "   t.source_code as sourceCode ,  \r\n" +
            "   t.version_num as versionNum ,  \r\n" +
            "   t.creation_date as creationDate ,  \r\n" +
            "   t.created_by as createdBy ,  \r\n" +
            "   t.last_update_date as lastUpdateDate ,  \r\n" +
            "   t.last_updated_by as lastUpdatedBy ,  \r\n" +
            "   t.last_update_login as lastUpdateLogin ,  \r\n" +
            "   t.attribute_category as attributeCategory ,  \r\n" +
            "   cp.payment_stage_id as paymentStageId ,  \r\n" +
            "   cp.payment_stage_name as paymentStageName ,  \r\n" +
            "   cp.payment_stage_explain as paymentStageExplain ,  \r\n" +
            "   cp.payment_proportion as paymentProportion ,  \r\n" +
            "   cp.estimate_payment_date as estimatePaymentDate ,  \r\n" +
            "   cp.warning_advance_days as warningAdvanceDays ,  \r\n" +
            "   cp.payable_amount as payableAmount ,  \r\n" +
            "   cp.paid_amount as paymentStagePaidAmount ,  \r\n" +
            "   cp.payment_completed_flag as paymentCompletedFlag ,  \r\n" +
            "   lvcs.meaning AS contractStatusLabel ,  \r\n" +
            "   lvcas.meaning AS contractApprovalStatusLabel ,  \r\n" +
            "   a.inst_name as partyAName,   \r\n" +
            "   b.supplier_name as partyBName,   \r\n" +
            "   c.supplier_name as partyCName,   \r\n" +
            "   tpl.template_name as templateName,   \r\n" +
            "   u.user_name as createdByName,   \r\n" +
            "   u.user_name as userAccount,   \r\n" +
            "   u2.user_name as lastUpdatedByName,   \r\n" +
            "   mc.contract_code as mainContractCode ,   \r\n" +
            "   i.inst_name as handleDepartmentName ,   \r\n" +
            "    w.watermark_name as watermarkName ,   \r\n" +
            "   pt.payment_term_code as paymentTermCode ,  \r\n" +
            "   pt.payment_term_name as paymentTermName,  \r\n" +
            "  t.ekp_number AS ekpNumber,\r\n" +
            "  t.received_amount AS receivedAmount\r\n" +
            " FROM srm_okc_contracts t INNER JOIN saaf_institution a on t.party_a_id = a.inst_id\n" +
            " INNER JOIN srm_okc_contract_payments cp on t.contract_id = cp.contract_id \r\n" +
            " INNER JOIN   srm_pos_supplier_info b on t.party_b_id = b.supplier_id \r\n" +
            " LEFT JOIN saaf_institution i on t.handle_department_id = i.inst_id \r\n" +
            " LEFT JOIN srm_okc_watermarks w on t.watermark_id = w.watermark_id  \r\n" +
            " LEFT JOIN srm_okc_contracts mc on t.main_contract_id = mc.contract_id \r\n" +
            " LEFT JOIN  saaf_users u  on u.user_id = t.created_by \r\n" +
            " INNER JOIN srm_pon_payment_terms pt on pt.payment_term_id = t.payment_term_id \r\n" +
            " LEFT JOIN  saaf_users u2  on u2.user_id = t.last_updated_by \r\n" +
            " LEFT JOIN saaf_lookup_values lvcs ON lvcs.lookup_type='CON_CON_STATE' AND lvcs.lookup_code=t.contract_status \r\n" +
            " LEFT JOIN saaf_lookup_values lvcas ON lvcas.lookup_type='CON_CON_APPROVE_STATE' AND lvcas.lookup_code=t.contract_approval_status  \r\n" +
            " LEFT JOIN  srm_okc_contract_templates tpl on t.template_id = tpl.template_id \r\n" +
            " LEFT JOIN   srm_pos_supplier_info c on t.party_c_id = c.supplier_id \r\n" +
            " WHERE  t.created_by = :varUserId";

    public static String SUB_CONTRACT_QUERY_SQL =
                    "SELECT\n" +
                    "  soc.contract_id AS contractId,\n" +
                    "  soc.contract_code AS contractCode,\n" +
                    "  soc.contract_name AS contractName,\n" +
                    "  soc.contract_type AS contractType,\n" +
                    "  soc.party_a_id AS partyAId,\n" +
                    "  soc.party_b_id AS partyBId,\n" +
                    "  soc.party_c_id AS partyCId,\n" +
                    "  soc.contract_status AS contractStatus,\n" +
                    "  soc.contract_approval_status AS contractApprovalStatus,\n" +
                    "  soc.handle_department_id AS handleDepartmentId,\n" +
                    "  soc.main_contract_flag AS mainContractFlag,\n" +
                    "  soc.transfer_po_flag AS transferPoFlag,\n" +
                    "  soc.version_number AS versionNumber,\n" +
                    "  soc.created_mode AS createdMode,\n" +
                    "  soc.template_id AS templateId,\n" +
                    "  soc.watermark_id AS watermarkId,\n" +
                    "  soc.payment_term_id AS paymentTermId,\n" +
                    "  soc.total_amount AS totalAmount,\n" +
                    "  soc.paid_amount AS paidAmount,\n" +
                    "  soc.currency_code AS currencyCode,\n" +
                    "  soc.party_a_sign_date AS partyASignDate,\n" +
                    "  soc.party_b_sign_date AS partyBSignDate,\n" +
                    "  soc.party_c_sign_date AS partyCSignDate,\n" +
                    "  soc.start_date AS startDate,\n" +
                    "  soc.end_date AS endDate,\n" +
                    "  soc.main_contract_id AS mainContractId,\n" +
                    "  soc.signed_site AS signedSite,\n" +
                    "  soc.termination_date AS terminationDate,\n" +
                    "  soc.termination_reasons AS terminationReasons,\n" +
                    "  soc.comments AS comments,\n" +
                    "  soc.source_code AS sourceCode,\n" +
                    "  soc.version_num AS versionNum,\n" +
                    "  soc.creation_date AS creationDate,\n" +
                    "  soc.created_by AS createdBy,\n" +
                    "  soc.last_update_date AS lastUpdateDate,\n" +
                    "  soc.last_updated_by AS lastUpdatedBy,\n" +
                    "  soc.last_update_login AS lastUpdateLogin,\n" +
                    "  soc.attribute_category AS attributeCategory,\n" +
                    "  soc.attribute1 AS attribute1,\n" +
                    "  soc.attribute2 AS attribute2,\n" +
                    "  soc.attribute3 AS attribute3,\n" +
                    "  soc.attribute4 AS attribute4,\n" +
                    "  soc.attribute5 AS attribute5,\n" +
                    "  soc.attribute6 AS attribute6,\n" +
                    "  soc.attribute7 AS attribute7,\n" +
                    "  soc.attribute8 AS attribute8,\n" +
                    "  soc.attribute9 AS attribute9,\n" +
                    "  soc.attribute10 AS attribute10,\n" +
                    "  soc.attribute11 AS attribute11,\n" +
                    "  soc.attribute12 AS attribute12,\n" +
                    "  soc.attribute13 AS attribute13,\n" +
                    "  soc.attribute14 AS attribute14,\n" +
                    "  soc.attribute15 AS attribute15,\n" +
                    "  soc.attribute16 AS attribute16,\n" +
                    "  soc.attribute17 AS attribute17,\n" +
                    "  soc.attribute18 AS attribute18,\n" +
                    "  soc.attribute19 AS attribute19,\n" +
                    "  soc.attribute20 AS attribute20,\n" +
                    "  soc.attribute21 AS attribute21,\n" +
                    "  soc.attribute22 AS attribute22,\n" +
                    "  soc.attribute23 AS attribute23,\n" +
                    "  soc.attribute24 AS attribute24,\n" +
                    "  soc.attribute25 AS attribute25,\n" +
                    "  soc.attribute26 AS attribute26,\n" +
                    "  soc.attribute27 AS attribute27,\n" +
                    "  soc.attribute28 AS attribute28,\n" +
                    "  soc.attribute29 AS attribute29,\n" +
                    "  soc.attribute30 AS attribute30,\n" +
                    "  slv1.meaning AS contractStatusLabel,\n" +
                    "  slv2.meaning AS contractApprovalStatusLabel,\n" +
                    "  sia.inst_name AS partyAName,\n" +
                    "  psib.supplier_name AS partyBName,\n" +
                    "  c.supplier_name AS partyCName,\n" +
                    "  tpl.template_name AS templateName,\n" +
                    "  emp1.employee_number AS createdByName,\n" +
                    "  sid.inst_name AS handleDepartmentName,\n" +
                    "  sow.watermark_name AS watermarkName,\n" +
                    "  pt.payment_term_code AS paymentTermCode,\n" +
                    "  pt.payment_term_name AS paymentTermName,\n" +
                    "  soc.ekp_number AS ekpNumber,\n" +
                    "  soc.received_amount AS receivedAmount\r\n" +
                    "FROM\n" +
                    "  srm_okc_contracts soc\n" +
                    "  INNER JOIN saaf_institution sia\n" +
                    "    ON sia.inst_id = soc.party_a_id\n" +
                    "  INNER JOIN srm_pos_supplier_info psib\n" +
                    "    ON psib.supplier_id = soc.party_b_id\n" +
                    "  LEFT JOIN saaf_institution sid\n" +
                    "    ON sid.inst_id = soc.handle_department_id\n" +
                    "  LEFT JOIN srm_okc_watermarks sow\n" +
                    "    ON sow.watermark_id = soc.watermark_id\n" +
                    "  LEFT JOIN saaf_users su1\n" +
                    "    ON su1.user_id = soc.created_by\n" +
                    "  LEFT JOIN saaf_employees emp1\n" +
                    "    ON emp1.employee_id = su1.employee_id\n" +
                    "  INNER JOIN srm_pon_payment_terms pt\n" +
                    "    ON pt.payment_term_id = soc.payment_term_id\n" +
                    "  LEFT JOIN saaf_lookup_values slv1\n" +
                    "    ON slv1.lookup_code = soc.contract_status\n" +
                    "    AND slv1.lookup_type = 'CON_CON_STATE'\n" +
                    "  LEFT JOIN saaf_lookup_values slv2\n" +
                    "    ON slv2.lookup_code = soc.contract_approval_status\n" +
                    "    AND slv2.lookup_type = 'CON_CON_APPROVE_STATE'\n" +
                    "  LEFT JOIN srm_okc_contract_templates tpl\n" +
                    "    ON soc.template_id = tpl.template_id\n" +
                    "  LEFT JOIN srm_pos_supplier_info c\n" +
                    "    ON soc.party_c_id = c.supplier_id\n" +
                    "WHERE soc.main_contract_id = :mainContractId";



    public static final String QUERY_INST_REGION="SELECT Siv.Inst_Id        AS Instid\n" +
            "      ,Siv.Inst_Code      AS Instcode\n" +
            "      ,Siv.Inst_Region\n" +
            "  FROM Saaf_Institution Siv\n" +
            " WHERE (Siv.Inst_Type = 'ORG' OR Siv.Inst_Type = 'ORGANIZATION')\n" +
            "   AND Siv.Platform_Code LIKE '%SAAF%' ";

    public static  final  String QUERY_EKP_NUMBER="select soc.contract_id,\n" +
            "       soc.contract_code,\n" +
            "       soc.contract_name,\n" +
            "       soc.contract_status,\n" +
            "       soc.contract_approval_status,\n" +
            "       soc.Attribute1,\n" +
            "       soc.ekp_number,\n" +
            "       soc.party_A_Id,\n" +
            "       soc.contract_type\n" +
            "  from srm_okc_contracts soc\n" +
            " where soc.contract_approval_status in ('2', '5')";


    private Integer Instid;
    private String Instcode;
    private String InstRegion;

    private Integer contractId;

	private String contractCode;

	private String contractName;

	private String contractType;

	private Integer partyAId;

	private Integer partyBId;

	private Integer partyCId;

	private String contractStatus;

	private String contractStatusLabel;

	private String contractApprovalStatusLabel;

	private String contractApprovalStatus;

	private Integer handleDepartmentId;

	private String mainContractFlag;

	private String transferPoFlag;

	private Integer versionNumber;

	private String createdMode;

	private Integer templateId;

	private Integer watermarkId;

	private Integer paymentTermId;

	private BigDecimal totalAmount;

	private BigDecimal paidAmount;

	private String currencyCode;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date partyASignDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date partyBSignDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date partyCSignDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date startDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date endDate;

	private Integer mainContractId;

	private String signedSite;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date terminationDate;

	private String terminationReasons;

	private String comments;

	private String sourceCode;

    private String sourceId;

    private String sourceBillCode;

	private Integer versionNum;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;

	private Integer createdBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;

	private Integer lastUpdatedBy;

	private Integer lastUpdateLogin;

	private String attributeCategory;

	private String attribute1;

	private String attribute2;

	private String attribute3;

	private String attribute4;

	private String attribute5;

	private String attribute6;

	private String attribute7;

	private String attribute8;

	private String attribute9;

	private String attribute10;

	private String attribute11;

	private String attribute12;

	private String attribute13;

	private String attribute14;

	private String attribute15;

	private Integer attribute16;

	private Integer attribute17;

	private Integer attribute18;

	private Integer attribute19;

	private Integer attribute20;

	private BigDecimal attribute21;

	private BigDecimal attribute22;

	private BigDecimal attribute23;

	private BigDecimal attribute24;

	private BigDecimal attribute25;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute26;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute27;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute28;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute29;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute30;

    private String partyAName;
    private String partyBName;
    private String partyCName;
    private String templateName;
    private String createdByName;
    private String lastUpdatedByName;
    private String paymentTermName;
    private String paymentTermCode;

    private String handleDepartmentName;
    private String watermarkName;
    private String mainContractCode;


    private Integer paymentStageId;

    private String paymentStageName;

    private String paymentStageExplain;

    private BigDecimal paymentProportion;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date estimatePaymentDate;

    private Integer warningAdvanceDays;

    private BigDecimal payableAmount;

    private BigDecimal paymentStagePaidAmount;

    private String paymentCompletedFlag;

    private String userAccount;
    private String ekpNumber;
    private BigDecimal receivedAmount;

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public SrmOkcContractsEntity_HI_RO(){
        super();
    }


    public String getEkpNumber() {
        return ekpNumber;
    }

    public void setEkpNumber(String ekpNumber) {
        this.ekpNumber = ekpNumber;
    }

    public Integer getInstid() {
        return Instid;
    }

    public void setInstid(Integer instid) {
        Instid = instid;
    }

    public String getInstcode() {
        return Instcode;
    }

    public void setInstcode(String instcode) {
        Instcode = instcode;
    }

    public String getInstRegion() {
        return InstRegion;
    }

    public void setInstRegion(String instRegion) {
        InstRegion = instRegion;
    }

    public Integer  getContractId() {
        return contractId;
    }

    public void setContractId( Integer contractId) {
        this.contractId = contractId;
    }


    public String  getContractCode() {
        return contractCode;
    }

    public void setContractCode( String contractCode) {
        this.contractCode = contractCode;
    }


    public String  getContractName() {
        return contractName;
    }

    public void setContractName( String contractName) {
        this.contractName = contractName;
    }


    public String  getContractType() {
        return contractType;
    }

    public void setContractType( String contractType) {
        this.contractType = contractType;
    }


    public Integer  getPartyAId() {
        return partyAId;
    }

    public void setPartyAId( Integer partyAId) {
        this.partyAId = partyAId;
    }


    public Integer  getPartyBId() {
        return partyBId;
    }

    public void setPartyBId( Integer partyBId) {
        this.partyBId = partyBId;
    }


    public Integer  getPartyCId() {
        return partyCId;
    }

    public void setPartyCId( Integer partyCId) {
        this.partyCId = partyCId;
    }


    public String  getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus( String contractStatus) {
        this.contractStatus = contractStatus;
    }


    public String  getContractApprovalStatus() {
        return contractApprovalStatus;
    }

    public void setContractApprovalStatus( String contractApprovalStatus) {
        this.contractApprovalStatus = contractApprovalStatus;
    }


    public Integer  getHandleDepartmentId() {
        return handleDepartmentId;
    }

    public void setHandleDepartmentId( Integer handleDepartmentId) {
        this.handleDepartmentId = handleDepartmentId;
    }


    public String  getMainContractFlag() {
        return mainContractFlag;
    }

    public void setMainContractFlag( String mainContractFlag) {
        this.mainContractFlag = mainContractFlag;
    }


    public String  getTransferPoFlag() {
        return transferPoFlag;
    }

    public void setTransferPoFlag( String transferPoFlag) {
        this.transferPoFlag = transferPoFlag;
    }


    public Integer  getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber( Integer versionNumber) {
        this.versionNumber = versionNumber;
    }


    public String  getCreatedMode() {
        return createdMode;
    }

    public void setCreatedMode( String createdMode) {
        this.createdMode = createdMode;
    }


    public Integer  getTemplateId() {
        return templateId;
    }

    public void setTemplateId( Integer templateId) {
        this.templateId = templateId;
    }


    public Integer  getWatermarkId() {
        return watermarkId;
    }

    public void setWatermarkId( Integer watermarkId) {
        this.watermarkId = watermarkId;
    }


    public Integer  getPaymentTermId() {
        return paymentTermId;
    }

    public void setPaymentTermId( Integer paymentTermId) {
        this.paymentTermId = paymentTermId;
    }


    public BigDecimal  getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount( BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }


    public BigDecimal  getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount( BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }


    public String  getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode( String currencyCode) {
        this.currencyCode = currencyCode;
    }


    public Date  getPartyASignDate() {
        return partyASignDate;
    }

    public void setPartyASignDate( Date partyASignDate) {
        this.partyASignDate = partyASignDate;
    }


    public Date  getPartyBSignDate() {
        return partyBSignDate;
    }

    public void setPartyBSignDate( Date partyBSignDate) {
        this.partyBSignDate = partyBSignDate;
    }


    public Date  getPartyCSignDate() {
        return partyCSignDate;
    }

    public void setPartyCSignDate( Date partyCSignDate) {
        this.partyCSignDate = partyCSignDate;
    }


    public Date  getStartDate() {
        return startDate;
    }

    public void setStartDate( Date startDate) {
        this.startDate = startDate;
    }


    public Date  getEndDate() {
        return endDate;
    }

    public void setEndDate( Date endDate) {
        this.endDate = endDate;
    }


    public Integer  getMainContractId() {
        return mainContractId;
    }

    public void setMainContractId( Integer mainContractId) {
        this.mainContractId = mainContractId;
    }


    public String  getSignedSite() {
        return signedSite;
    }

    public void setSignedSite( String signedSite) {
        this.signedSite = signedSite;
    }


    public Date  getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate( Date terminationDate) {
        this.terminationDate = terminationDate;
    }


    public String  getTerminationReasons() {
        return terminationReasons;
    }

    public void setTerminationReasons( String terminationReasons) {
        this.terminationReasons = terminationReasons;
    }


    public String  getComments() {
        return comments;
    }

    public void setComments( String comments) {
        this.comments = comments;
    }


    public String  getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode( String sourceCode) {
        this.sourceCode = sourceCode;
    }


    public Integer  getVersionNum() {
        return versionNum;
    }

    public void setVersionNum( Integer versionNum) {
        this.versionNum = versionNum;
    }


    public Date  getCreationDate() {
        return creationDate;
    }

    public void setCreationDate( Date creationDate) {
        this.creationDate = creationDate;
    }


    public Integer  getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy( Integer createdBy) {
        this.createdBy = createdBy;
    }


    public Date  getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate( Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }


    public Integer  getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy( Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }


    public Integer  getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin( Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }


    public String  getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory( String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }


    public String  getAttribute1() {
        return attribute1;
    }

    public void setAttribute1( String attribute1) {
        this.attribute1 = attribute1;
    }


    public String  getAttribute2() {
        return attribute2;
    }

    public void setAttribute2( String attribute2) {
        this.attribute2 = attribute2;
    }


    public String  getAttribute3() {
        return attribute3;
    }

    public void setAttribute3( String attribute3) {
        this.attribute3 = attribute3;
    }


    public String  getAttribute4() {
        return attribute4;
    }

    public void setAttribute4( String attribute4) {
        this.attribute4 = attribute4;
    }


    public String  getAttribute5() {
        return attribute5;
    }

    public void setAttribute5( String attribute5) {
        this.attribute5 = attribute5;
    }


    public String  getAttribute6() {
        return attribute6;
    }

    public void setAttribute6( String attribute6) {
        this.attribute6 = attribute6;
    }


    public String  getAttribute7() {
        return attribute7;
    }

    public void setAttribute7( String attribute7) {
        this.attribute7 = attribute7;
    }


    public String  getAttribute8() {
        return attribute8;
    }

    public void setAttribute8( String attribute8) {
        this.attribute8 = attribute8;
    }


    public String  getAttribute9() {
        return attribute9;
    }

    public void setAttribute9( String attribute9) {
        this.attribute9 = attribute9;
    }


    public String  getAttribute10() {
        return attribute10;
    }

    public void setAttribute10( String attribute10) {
        this.attribute10 = attribute10;
    }


    public String  getAttribute11() {
        return attribute11;
    }

    public void setAttribute11( String attribute11) {
        this.attribute11 = attribute11;
    }


    public String  getAttribute12() {
        return attribute12;
    }

    public void setAttribute12( String attribute12) {
        this.attribute12 = attribute12;
    }


    public String  getAttribute13() {
        return attribute13;
    }

    public void setAttribute13( String attribute13) {
        this.attribute13 = attribute13;
    }


    public String  getAttribute14() {
        return attribute14;
    }

    public void setAttribute14( String attribute14) {
        this.attribute14 = attribute14;
    }


    public String  getAttribute15() {
        return attribute15;
    }

    public void setAttribute15( String attribute15) {
        this.attribute15 = attribute15;
    }


    public Integer  getAttribute16() {
        return attribute16;
    }

    public void setAttribute16( Integer attribute16) {
        this.attribute16 = attribute16;
    }


    public Integer  getAttribute17() {
        return attribute17;
    }

    public void setAttribute17( Integer attribute17) {
        this.attribute17 = attribute17;
    }


    public Integer  getAttribute18() {
        return attribute18;
    }

    public void setAttribute18( Integer attribute18) {
        this.attribute18 = attribute18;
    }


    public Integer  getAttribute19() {
        return attribute19;
    }

    public void setAttribute19( Integer attribute19) {
        this.attribute19 = attribute19;
    }


    public Integer  getAttribute20() {
        return attribute20;
    }

    public void setAttribute20( Integer attribute20) {
        this.attribute20 = attribute20;
    }


    public BigDecimal  getAttribute21() {
        return attribute21;
    }

    public void setAttribute21( BigDecimal attribute21) {
        this.attribute21 = attribute21;
    }


    public BigDecimal  getAttribute22() {
        return attribute22;
    }

    public void setAttribute22( BigDecimal attribute22) {
        this.attribute22 = attribute22;
    }


    public BigDecimal  getAttribute23() {
        return attribute23;
    }

    public void setAttribute23( BigDecimal attribute23) {
        this.attribute23 = attribute23;
    }


    public BigDecimal  getAttribute24() {
        return attribute24;
    }

    public void setAttribute24( BigDecimal attribute24) {
        this.attribute24 = attribute24;
    }


    public BigDecimal  getAttribute25() {
        return attribute25;
    }

    public void setAttribute25( BigDecimal attribute25) {
        this.attribute25 = attribute25;
    }


    public Date  getAttribute26() {
        return attribute26;
    }

    public void setAttribute26( Date attribute26) {
        this.attribute26 = attribute26;
    }


    public Date  getAttribute27() {
        return attribute27;
    }

    public void setAttribute27( Date attribute27) {
        this.attribute27 = attribute27;
    }


    public Date  getAttribute28() {
        return attribute28;
    }

    public void setAttribute28( Date attribute28) {
        this.attribute28 = attribute28;
    }


    public Date  getAttribute29() {
        return attribute29;
    }

    public void setAttribute29( Date attribute29) {
        this.attribute29 = attribute29;
    }


    public Date  getAttribute30() {
        return attribute30;
    }

    public void setAttribute30( Date attribute30) {
        this.attribute30 = attribute30;
    }

    public String getPartyAName() {
        return partyAName;
    }

    public void setPartyAName(String partyAName) {
        this.partyAName = partyAName;
    }

    public String getPartyBName() {
        return partyBName;
    }

    public void setPartyBName(String partyBName) {
        this.partyBName = partyBName;
    }

    public String getPartyCName() {
        return partyCName;
    }

    public void setPartyCName(String partyCName) {
        this.partyCName = partyCName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getLastUpdatedByName() {
        return lastUpdatedByName;
    }

    public void setLastUpdatedByName(String lastUpdatedByName) {
        this.lastUpdatedByName = lastUpdatedByName;
    }

    public String getPaymentTermName() {
        return paymentTermName;
    }

    public void setPaymentTermName(String paymentTermName) {
        this.paymentTermName = paymentTermName;
    }

    public String getContractStatusLabel() {
        return contractStatusLabel;
    }

    public void setContractStatusLabel(String contractStatusLabel) {
        this.contractStatusLabel = contractStatusLabel;
    }

    public String getContractApprovalStatusLabel() {
        return contractApprovalStatusLabel;
    }

    public void setContractApprovalStatusLabel(String contractApprovalStatusLabel) {
        this.contractApprovalStatusLabel = contractApprovalStatusLabel;
    }

    public String getHandleDepartmentName() {
        return handleDepartmentName;
    }

    public void setHandleDepartmentName(String handleDepartmentName) {
        this.handleDepartmentName = handleDepartmentName;
    }

    public String getWatermarkName() {
        return watermarkName;
    }

    public void setWatermarkName(String watermarkName) {
        this.watermarkName = watermarkName;
    }

    public String getMainContractCode() {
        return mainContractCode;
    }

    public void setMainContractCode(String mainContractCode) {
        this.mainContractCode = mainContractCode;
    }

    public String getPaymentTermCode() {
        return paymentTermCode;
    }

    public void setPaymentTermCode(String paymentTermCode) {
        this.paymentTermCode = paymentTermCode;
    }

    public Integer getPaymentStageId() {
        return paymentStageId;
    }

    public void setPaymentStageId(Integer paymentStageId) {
        this.paymentStageId = paymentStageId;
    }

    public String getPaymentStageName() {
        return paymentStageName;
    }

    public void setPaymentStageName(String paymentStageName) {
        this.paymentStageName = paymentStageName;
    }

    public String getPaymentStageExplain() {
        return paymentStageExplain;
    }

    public void setPaymentStageExplain(String paymentStageExplain) {
        this.paymentStageExplain = paymentStageExplain;
    }

    public BigDecimal getPaymentProportion() {
        return paymentProportion;
    }

    public void setPaymentProportion(BigDecimal paymentProportion) {
        this.paymentProportion = paymentProportion;
    }

    public Date getEstimatePaymentDate() {
        return estimatePaymentDate;
    }

    public void setEstimatePaymentDate(Date estimatePaymentDate) {
        this.estimatePaymentDate = estimatePaymentDate;
    }

    public Integer getWarningAdvanceDays() {
        return warningAdvanceDays;
    }

    public void setWarningAdvanceDays(Integer warningAdvanceDays) {
        this.warningAdvanceDays = warningAdvanceDays;
    }

    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }

    public BigDecimal getPaymentStagePaidAmount() {
        return paymentStagePaidAmount;
    }

    public void setPaymentStagePaidAmount(BigDecimal paymentStagePaidAmount) {
        this.paymentStagePaidAmount = paymentStagePaidAmount;
    }

    public String getPaymentCompletedFlag() {
        return paymentCompletedFlag;
    }

    public void setPaymentCompletedFlag(String paymentCompletedFlag) {
        this.paymentCompletedFlag = paymentCompletedFlag;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceBillCode() {
        return sourceBillCode;
    }

    public void setSourceBillCode(String sourceBillCode) {
        this.sourceBillCode = sourceBillCode;
    }
}
