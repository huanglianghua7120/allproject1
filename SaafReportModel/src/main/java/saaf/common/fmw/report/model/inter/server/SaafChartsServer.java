/**
 * create by sie liujun
 */
package saaf.common.fmw.report.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.report.model.entities.SaafChartsEntity_HI;
import saaf.common.fmw.report.model.entities.readonly.SaafChartsEntity_HI_RO;
import saaf.common.fmw.report.model.inter.ISaafCharts;


@Component("saafChartsServer")
public class SaafChartsServer implements ISaafCharts {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafChartsServer.class);
    @Autowired
    private ViewObject<SaafChartsEntity_HI> saafChartsDAO_HI;
    
    @Autowired
    private BaseViewObject<SaafChartsEntity_HI_RO> saafChartsDAO_HI_RO;

    /**
     * 查询
     *
     * @param parameters
     *            参数：
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public String findList(JSONObject parameters, Integer pageIndex, Integer pageRows) {
        Map<String, Object> map = SToolUtils.fastJsonObj2Map(parameters);
        StringBuffer hql = new StringBuffer();
        hql.append("from SaafChartsEntity_HI where 1=1 ");
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        SaafToolUtils.parperParam(parameters, "chartsId", "chartsId", hql, queryParamMap, "=");
        SaafToolUtils.parperParam(parameters, "chartsCode", "chartsCode", hql, queryParamMap, "like");
        SaafToolUtils.parperParam(parameters, "chartsName", "chartsName", hql, queryParamMap, "like");
        SaafToolUtils.parperParam(parameters, "chartsTitle", "chartsTitle", hql, queryParamMap, "like");
        Pagination<SaafChartsEntity_HI> findListResult = saafChartsDAO_HI.findPagination(hql, queryParamMap, pageIndex, pageRows);
        return JSON.toJSONString(findListResult);
    }

    /**
     * 查询
     *
     * @param parameters
     * 参数：
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public String findSqlList(JSONObject parameters, Integer pageIndex, Integer pageRows) {
        StringBuffer sql = new StringBuffer(SaafChartsEntity_HI_RO.QUERY_SQL);
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        SaafToolUtils.parperParam(parameters, "charts_id", "chartsId", sql, queryParamMap, "=");
        SaafToolUtils.parperParam(parameters, "charts_id", "chartsId", sql, queryParamMap, "=");
        SaafToolUtils.parperParam(parameters, "charts_code", "chartsCode", sql, queryParamMap, "like");
        SaafToolUtils.parperParam(parameters, "charts_name", "chartsName", sql, queryParamMap, "like");
        SaafToolUtils.parperParam(parameters, "charts_type", "chartsType", sql, queryParamMap, "like");
        SaafToolUtils.parperParam(parameters, "SC.description", "description", sql, queryParamMap, "like");
        sql.append(" ORDER BY sc.charts_type");
        Pagination<SaafChartsEntity_HI_RO> findPagination = saafChartsDAO_HI_RO.findPagination(sql, queryParamMap, pageIndex, pageRows);
        return JSON.toJSONString(findPagination);
    }

    /**
     * 保存
     *
     * @param parameters
     * @return
     * @throws Exception
     */
    public JSONObject save(JSONObject parameters) throws Exception {
        int userId = parameters.getInteger("varUserId");
        SaafChartsEntity_HI row = parameters.toJavaObject(SaafChartsEntity_HI.class);
        if (null == parameters.getInteger("chartsId")) { // 新增
            row.setCreatedBy(userId); // 用户登录的userId，从session里面获取
            row.setCreationDate(new Date());
        } else {
            row = saafChartsDAO_HI.getById(parameters.getIntValue("chartsId"));
            JSONObject tempJson = (JSONObject)JSON.toJSON(row);
            tempJson.putAll(parameters);
            parameters = tempJson;
            SaafChartsEntity_HI temp = parameters.toJavaObject(SaafChartsEntity_HI.class);
            BeanUtils.copyProperties(temp, row);
        }
        row.setLastUpdatedBy(userId);
        row.setLastUpdateDate(new Date());
        row.setLastUpdateLogin(userId);

        saafChartsDAO_HI.saveOrUpdate(row);
        return SToolUtils.convertResultJSONObj("S", "保存成功！", 1, row);
    }

    /**
     * 删除
     *
     * @param parameters
     * @return
     * @throws Exception
     */
    public JSONObject delete(JSONObject parameters) throws Exception {
        if (null == parameters.getInteger("chartsId")) {
            LOGGER.error("删除失败，行ID不能为空！");
            return SToolUtils.convertResultJSONObj("E", "删除失败，行ID不能为空！", 0, null);
        } else {
            SaafChartsEntity_HI saafChartsEntity_HI = saafChartsDAO_HI.getById(parameters.getIntValue("chartsId"));
            saafChartsDAO_HI.delete(saafChartsEntity_HI);
        }
        return SToolUtils.convertResultJSONObj("S", "删除成功！", 1, null);
    }
}
