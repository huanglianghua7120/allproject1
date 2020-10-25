package saaf.common.fmw.pos.model.inter.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierGradeLinesEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierGradeLinesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierGradeLines;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商等级行
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmPosSupplierGradeLinesServer")
public class SrmPosSupplierGradeLinesServer implements ISrmPosSupplierGradeLines {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierGradeLinesServer.class);

    public SrmPosSupplierGradeLinesServer() {
        super();
    }

    @Autowired
    private BaseViewObject<SrmPosSupplierGradeLinesEntity_HI_RO> srmPosSupplierGradeLinesDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPosSupplierGradeLinesEntity_HI> srmPosSupplierGradeLinesDAO_HI;

    /**
     * 查询供应商级别行(不带分页)
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosSupplierGradeLinesEntity_HI_RO> findSupplierGradeLinesList(
            JSONObject params) throws Exception {
        LOGGER.info("查询参数：" + params.toString());
        try {
            String sql = SrmPosSupplierGradeLinesEntity_HI_RO.QUERY_GRADE_LINES_SQL;
            StringBuffer queryString = new StringBuffer();
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(params, "gl.grade_id", "gradeId", queryString, map, "=");
            List<SrmPosSupplierGradeLinesEntity_HI_RO> filesList = srmPosSupplierGradeLinesDAO_HI_RO.findList(sql + queryString, map);
            return filesList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 保存供应商级别行
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public void saveSupplierGradeLinesList(
            List<SrmPosSupplierGradeLinesEntity_HI> linesList, Integer userId,
            Integer gradeId) throws Exception {
        try {
            if (null != linesList && linesList.size() > 0) {
                for (SrmPosSupplierGradeLinesEntity_HI i : linesList) {
                    i.setGradeId(gradeId);
                    i.setOperatorUserId(userId);
                }
                srmPosSupplierGradeLinesDAO_HI.saveOrUpdateAll(linesList);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    /**
     * 删除供应商级别行
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteSupplierGradeLines(JSONObject params) throws Exception {
        LOGGER.info("删除参数：" + params.toString());
        SrmPosSupplierGradeLinesEntity_HI linesRow = null;
        try {
            List<SrmPosSupplierGradeLinesEntity_HI> linesList = srmPosSupplierGradeLinesDAO_HI.findByProperty("gradeLineId", params.getInteger("gradeLineId"));
            if (linesList != null && linesList.size() > 0) {
                linesRow = linesList.get(0);
                srmPosSupplierGradeLinesDAO_HI.delete(linesRow);
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除失败，" + params.getString("gradeLineId") + "不存在", 0, null);
            }
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询导出级别行数据
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosSupplierGradeLinesEntity_HI_RO> findSupplierGradeLinesExport(
            JSONObject params) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmPosSupplierGradeLinesEntity_HI_RO.QUERY_GRADE_LINES_LIST_IMPORT);
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询过滤条件
            SaafToolUtils.parperParam(params, "gl.grade_id","gradeId", queryString, map, "=");
            // 排序
            queryString.append(" ORDER BY gl.creation_date DESC");
            return srmPosSupplierGradeLinesDAO_HI_RO.findList(queryString, map);
        } catch (Exception e) {
            throw new Exception("导出级别行数据失败");
        }
    }

    /**
     * 导入级别行数据
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveSupplierGradeLinesImport(JSONObject params)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 查询历史级别行数据
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosSupplierGradeLinesEntity_HI_RO> findSupplierHistoryGradeLines(
            JSONObject params) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmPosSupplierGradeLinesEntity_HI_RO.QUERY_GRADE_LINES_HISTORY_LIST);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("supplierId", params.getInteger("supplierId"));
            map.put("gradeLineId", params.getInteger("gradeLineId"));
            // 排序
            queryString.append(" ORDER BY psg.approval_date DESC");
            return srmPosSupplierGradeLinesDAO_HI_RO.findList(queryString, map);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}

