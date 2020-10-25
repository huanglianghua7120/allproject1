package saaf.common.fmw.pos.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierGradeEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierGradeLinesEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierGradeEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierGrade;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierGradeLines;
import saaf.common.fmw.utils.SrmUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商等级
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmPosSupplierGradeServer")
public class SrmPosSupplierGradeServer implements ISrmPosSupplierGrade {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierGradeServer.class);

    public SrmPosSupplierGradeServer() {
        super();
    }

    @Autowired
    private BaseViewObject<SrmPosSupplierGradeEntity_HI_RO> srmPosSupplierGradeDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPosSupplierGradeEntity_HI> srmPosSupplierGradeDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierGradeLinesEntity_HI> srmPosSupplierGradeLinesDAO_HI;

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private ISrmPosSupplierGradeLines iSrmPosSupplierGradeLines;

    @Autowired
    private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;

    /**
     * 查询供应商级别管理列表
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmPosSupplierGradeEntity_HI_RO> findSupplierGradeList(
            JSONObject params, Integer pageIndex, Integer pageRows)
            throws Exception {
        try {
            String supplierName = params.getString("supplierName");
            StringBuffer queryString = new StringBuffer(SrmPosSupplierGradeEntity_HI_RO.QUERY_GRADE_LIST_SQL);
            Map<String, Object> map = new HashMap();
            // 查询过滤条件
            SaafToolUtils.parperParam(params, "sg.grade_status", "gradeStatus", queryString, map, "=");
            SaafToolUtils.parperParam(params, "sg.grade_code", "gradeCode", queryString, map, "like");
            SaafToolUtils.parperParam(params, "sg.evaluate_period", "evaluatePeriod", queryString, map, "=");
            SaafToolUtils.parperParam(params, "su.user_full_name ", "founder", queryString, map, "=");
            String creationDateFrom = params.getString("creationDateFrom");
            if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
                queryString.append(" AND trunc(sg.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
                map.put("creationDateFrom", creationDateFrom);
            }
            String creationDateTo = params.getString("creationDateTo");
            if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
                queryString.append(" AND trunc(sg.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
                map.put("creationDateTo", creationDateTo);
            }
            String approvalDateFrom = params.getString("approvalDateFrom");
            if (approvalDateFrom != null && !"".equals(approvalDateFrom.trim())) {
                queryString.append(" AND trunc(sg.approval_date) >= to_date(:approvalDateFrom, 'yyyy-mm-dd')\n");
                map.put("approvalDateFrom", approvalDateFrom);
            }
            String approvalDateTo = params.getString("approvalDateTo");
            if (approvalDateTo != null && !"".equals(approvalDateTo.trim())) {
                queryString.append(" AND trunc(sg.approval_date) <= to_date(:approvalDateTo, 'yyyy-mm-dd')\n");
                map.put("approvalDateTo", approvalDateTo);
            }
            if (supplierName != null && !"".equals(supplierName)) {
                //验证字符串是否含有SQL关键字及字符，有则返回NULL
                if (SrmUtils.isContainSQL(supplierName)) {
                    return null;
                }
                queryString.append("AND    EXISTS (SELECT 1\n" +
                                   "        FROM   srm_pos_supplier_grade_lines sgl\n" +
                                   "              ,srm_pos_supplier_info        psi\n" +
                                   "        WHERE  sgl.grade_id = sg.grade_id\n" +
                                   "        AND    sgl.supplier_id = psi.supplier_id\n" +
                                   "        AND    psi.supplier_name LIKE :suppliername)\n");
                map.put("supplierName", "%" + supplierName + "%");
            }
            String countSql = "select count(1) from (" + queryString + ")";
            // 排序
            queryString.append(" ORDER BY sg.creation_date DESC");
            Pagination<SrmPosSupplierGradeEntity_HI_RO> gradeList = srmPosSupplierGradeDAO_HI_RO
                    .findPagination(queryString,countSql, map, pageIndex, pageRows);
            return gradeList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询供应商级别信息(不带分页)
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosSupplierGradeEntity_HI_RO> findSupplierGradeInfo(
            JSONObject params) throws Exception {
        LOGGER.info("查询信息，参数：" + params.toString());
        try {
            String sql = SrmPosSupplierGradeEntity_HI_RO.QUERY_GRADE_INFO_SQL;
            StringBuffer queryString = new StringBuffer();
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询过滤条件
            SaafToolUtils.parperParam(params, "sg.grade_id", "gradeId", queryString, map, "=");
            List<SrmPosSupplierGradeEntity_HI_RO> list = srmPosSupplierGradeDAO_HI_RO.findList(sql + queryString, map);
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：保存和提交供应商级别信息
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * gradeId  供应商级别头ID  NUMBER  Y
     * gradeCode  供应商级别编号  VARCHAR2  Y
     * gradeStatus  供应商级别状态，快码  VARCHAR2  Y
     * evaluatePeriod  评定维度，快码：SPM_TEMPLATE_FREQUENCY  VARCHAR2  Y
     * evaluateStartDate  评价开始时间  DATE  N
     * evaluateEndDate  评价结束时间  DATE  N
     * approvalEmployeeId  审核人  NUMBER  N
     * approvalDate  审核通过时间  DATE  N
     * fileId  附件ID  NUMBER  N
     * gradeNote  备注  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveSupplierGrade(JSONObject params) throws Exception {
        LOGGER.info("保存资质审查信息，参数：" + params.toString());
        SrmPosSupplierGradeEntity_HI gradeRow = null;
        try {
            Integer operatorUserId = params.getInteger("varUserId");
            Integer gradeId = params.getInteger("gradeId");
            String action = params.getString("action");
            if (action == null || "".equals(action.trim())) {
                action = "SAVE";
            }

            if(params.get("evaluatePeriod")==null){
                JSONObject result = SToolUtils.convertResultJSONObj("E", "评价周期必须选择", 1, gradeRow);
                return result;
            }
            Date date = new Date();
            // 级别ID不存在，就是新增，否则就是修改
            if (gradeId == null) {
                gradeRow = new SrmPosSupplierGradeEntity_HI();
                String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
                String gradeCode = saafSequencesUtil.getDocSequences("SRM_POS_SUPPLIER_GRADE", "GFJB-", dateFromate, 4);
                gradeRow.setGradeCode(gradeCode);
            } else {
                gradeRow = srmPosSupplierGradeDAO_HI.findByProperty("gradeId", params.getInteger("gradeId")).get(0);
                if ("SUBMITTED".equals(gradeRow.getGradeStatus()) || "APPROVED".equals(gradeRow.getGradeStatus())) {
                    return SToolUtils.convertResultJSONObj("E", "已提交或已批准的供应商级别单据，不允许保存或提交！", 0, null);
                }
            }

            gradeRow.setGradeStatus("DRAFT");
            gradeRow.setEvaluatePeriod(params.getString("evaluatePeriod"));
            gradeRow.setEvaluateStartDate(params.getDate("evaluateStartDate"));
            gradeRow.setEvaluateEndDate(params.getDate("evaluateEndDate"));
            gradeRow.setFileId(params.getInteger("gradeFileId"));
            gradeRow.setGradeNote(params.getString("gradeNote"));
            gradeRow.setOperatorUserId(operatorUserId);
            srmPosSupplierGradeDAO_HI.save(gradeRow);
            gradeId = gradeRow.getGradeId();
            LOGGER.info("getGradeId：" + gradeId);

            List<SrmPosSupplierGradeLinesEntity_HI> gradeLinesList = null;// 供应商级别行信息
            if (!(params.getJSONArray("gradeLinesList") == null || "".equals(params.getJSONArray("gradeLinesList")))) {
                JSONArray noticeFilesListJSON = params.getJSONArray("gradeLinesList");
                gradeLinesList = JSON.parseArray(noticeFilesListJSON.toJSONString(), SrmPosSupplierGradeLinesEntity_HI.class);
            }
            iSrmPosSupplierGradeLines.saveSupplierGradeLinesList(gradeLinesList, operatorUserId, gradeRow.getGradeId());

            JSONObject result = null;
            if ("SUBMIT".equals(action)) {
                gradeRow.setGradeStatus("APPROVING");
                srmPosSupplierGradeDAO_HI.saveOrUpdate(gradeRow);
                result = SToolUtils.convertResultJSONObj("S", "提交供应商级别成功", 1, gradeRow);
            } else {
                result = SToolUtils.convertResultJSONObj("S", "保存供应商级别成功", 1, gradeRow);
            }

            result.put("gradeId", gradeRow.getGradeId());
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 删除供应商级别信息
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteSupplierGrade(JSONObject params) throws Exception {
        LOGGER.info("删除参数：" + params.toString());
        try {
            if (null != params.getInteger("gradeId")) {
                SrmPosSupplierGradeEntity_HI gradeRow = srmPosSupplierGradeDAO_HI.getById(params.getInteger("gradeId"));
                if ("DRAFT".equals(gradeRow.getGradeStatus()) || "REJECTED".equals(gradeRow.getGradeStatus())) {
                    if (null != gradeRow) {
                        List<SrmPosSupplierGradeLinesEntity_HI> linesList = srmPosSupplierGradeLinesDAO_HI.findByProperty("gradeId", gradeRow.getGradeId());
                        if (linesList != null && linesList.size() > 0) {
                            srmPosSupplierGradeLinesDAO_HI.delete(linesList);
                        }
                        srmPosSupplierGradeDAO_HI.delete(gradeRow);
                    }
                } else {
                    return SToolUtils.convertResultJSONObj("E", "删除级别信息失败,只能删除拟定和驳回的单据!", 0, null);
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除级别信息失败，"
                        + params.getString("gradeId") + "不存在", 0, null);
            }
            return SToolUtils.convertResultJSONObj("S", "删除级别信息成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 审批供应商级别信息
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateApprovalSupplierGrade(JSONObject params) throws Exception {
        LOGGER.info("审批参数：" + params.toString());
        try {
            Integer gradeId = params.getInteger("gradeId");
            if (gradeId == null) {
                return SToolUtils.convertResultJSONObj("E", "审批失败,请传入参数gradeId", 0, null);
            }
            Integer operatorUserId = params.getInteger("varUserId");
            SrmPosSupplierGradeEntity_HI gradeRow = srmPosSupplierGradeDAO_HI.getById(Integer.parseInt(gradeId.toString()));
            List<SrmPosSupplierGradeLinesEntity_HI> linesList = srmPosSupplierGradeLinesDAO_HI.findByProperty("gradeId", gradeRow.getGradeId());
            List<SrmPosSupplierInfoEntity_HI> supplierList = null;
            if (linesList != null && linesList.size() > 0) {
                for (SrmPosSupplierGradeLinesEntity_HI lines : linesList) {
                    supplierList = srmPosSupplierInfoDAO_HI.findByProperty("supplierId", lines.getSupplierId());
                    for (SrmPosSupplierInfoEntity_HI row : supplierList) {
                        row.setGradeLineId(lines.getGradeLineId());
                        row.setOperatorUserId(operatorUserId);
                    }
                }
                srmPosSupplierInfoDAO_HI.updateAll(supplierList);
            }
            gradeRow.setApprovalEmployeeId(operatorUserId);
            gradeRow.setApprovalDate(new Date());
            gradeRow.setGradeStatus("APPROVED");
            gradeRow.setOperatorUserId(operatorUserId);
            srmPosSupplierGradeDAO_HI.update(gradeRow);
            return SToolUtils.convertResultJSONObj("S", "审批成功", 1, gradeRow);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 驳回供应商级别信息
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateRejectSupplierGrade(JSONObject params) throws Exception {
        LOGGER.info("驳回参数：" + params.toString());
        try {
            Integer gradeId = params.getInteger("gradeId");
            if (gradeId == null) {
                return SToolUtils.convertResultJSONObj("E", "驳回失败,请传入参数gradeId", 0, null);
            }
            SrmPosSupplierGradeEntity_HI gradeRow = srmPosSupplierGradeDAO_HI.getById(Integer.parseInt(gradeId.toString()));
            Integer operatorUserId = params.getInteger("varUserId");
            gradeRow.setGradeStatus("REJECTED");
            gradeRow.setOperatorUserId(operatorUserId);
            srmPosSupplierGradeDAO_HI.update(gradeRow);
            return SToolUtils.convertResultJSONObj("S", "驳回成功", 1, gradeRow);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 重新审批供应商级别信息
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateChangeSupplierGrade(JSONObject params)
            throws Exception {
        LOGGER.info("重新审批参数：" + params.toString());
        try {
            Integer gradeId = params.getInteger("gradeId");
            if (gradeId == null) {
                return SToolUtils.convertResultJSONObj("E", "重新审批失败,请传入参数gradeId", 0, null);
            }
            SrmPosSupplierGradeEntity_HI gradeRow = srmPosSupplierGradeDAO_HI.getById(Integer.parseInt(gradeId.toString()));
            Integer operatorUserId = params.getInteger("varUserId");
            gradeRow.setGradeStatus("RECONFIRMING");
            gradeRow.setOperatorUserId(operatorUserId);
            if (null != gradeRow) {
                List<SrmPosSupplierGradeLinesEntity_HI> linesList = srmPosSupplierGradeLinesDAO_HI.findByProperty("gradeId", gradeRow.getGradeId());
                if (linesList != null && linesList.size() > 0) {
                    for (SrmPosSupplierGradeLinesEntity_HI lines : linesList) {
                        lines.setEndDateActive(params.getDate("endDateActive"));
                        lines.setOperatorUserId(operatorUserId);
                    }
                    srmPosSupplierGradeLinesDAO_HI.saveOrUpdateAll(linesList);
                }
                srmPosSupplierGradeDAO_HI.update(gradeRow);
            }
            return SToolUtils.convertResultJSONObj("S", "重新审批成功", 1, gradeRow);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询导出级别头数据
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmPosSupplierGradeEntity_HI_RO> findSupplierGradeExport(JSONObject params)
            throws Exception {
        try {
            String supplierName = params.getString("supplierName");
            StringBuffer queryString = new StringBuffer(SrmPosSupplierGradeEntity_HI_RO.QUERY_GRADE_LIST_IMPORT);
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询过滤条件
            SaafToolUtils.parperParam(params, "sg.grade_status", "gradeStatus", queryString, map, "=");
            SaafToolUtils.parperParam(params, "sg.grade_code", "gradeCode", queryString, map, "like");
            SaafToolUtils.parperParam(params, "sg.evaluate_period", "evaluatePeriod", queryString, map, "=");
            SaafToolUtils.parperParam(params, "DATE_FORMAT(sg.creation_date,'%Y-%m-%d') ", "creationDateTo", queryString, map, ">=");
            SaafToolUtils.parperParam(params, "DATE_FORMAT(sg.creation_date,'%Y-%m-%d') ", "creationDateFrom", queryString, map, "<=");
            SaafToolUtils.parperParam(params, "DATE_FORMAT(sg.approval_date,'%Y-%m-%d') ", "approvalDateTo", queryString, map, ">=");
            SaafToolUtils.parperParam(params, "DATE_FORMAT(sg.approval_date,'%Y-%m-%d') ", "approvalDateFrom", queryString, map, "<=");
            SaafToolUtils.parperParam(params, "su.user_full_name ", "founder", queryString, map, "=");
            if (supplierName != null && !"".equals(supplierName)) {
                //验证字符串是否含有SQL关键字及字符，有则返回NULL
                if (SrmUtils.isContainSQL(supplierName)) {
                    return null;
                }
                queryString.append(" AND EXISTS(\r\n" +
                        "SELECT sgl.grade_id FROM srm_pos_supplier_grade_lines sgl \r\n" +
                        "WHERE sgl.grade_id=sg.grade_id AND\r\n" +
                        "EXISTS(SELECT si.supplier_id from srm_pos_supplier_info si\r\n" +
                        "WHERE si.supplier_id = sgl.supplier_id \r\n" +
                        "AND si.supplier_name LIKE :supplierName\r\n" +
                        ")\r\n" +
                        ")");
                map.put("supplierName", "%" + supplierName + "%");
            }

            // 排序
            queryString.append(" order by gl.creation_date desc");
            return srmPosSupplierGradeDAO_HI_RO.findList(queryString, map);
        } catch (Exception e) {
            throw new Exception("导出级别头数据失败");
        }
    }


}

