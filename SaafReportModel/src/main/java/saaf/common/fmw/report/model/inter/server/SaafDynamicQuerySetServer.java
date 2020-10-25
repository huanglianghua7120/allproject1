/**
 * create by sie liujun
 */
package saaf.common.fmw.report.model.inter.server;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.FndFlexValidationTablesEntity_HI;
import saaf.common.fmw.base.model.entities.FndFlexValueSetsEntity_HI;
import saaf.common.fmw.base.model.inter.IFndFlexValueSets;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.report.model.entities.SaafDynamicQuerySetHEntity_HI;
import saaf.common.fmw.report.model.entities.SaafDynamicQuerySetLEntity_HI;
import saaf.common.fmw.report.model.inter.ISaafDynamicQuerySet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.DynamicBaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;


@Component("saafDynamicQuerySetServer")
public class SaafDynamicQuerySetServer implements ISaafDynamicQuerySet {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafDynamicQuerySetServer.class);
    @Autowired
    private DynamicBaseViewObject commonDAO_HI_DY;
    
    @Autowired
    private ViewObject<SaafDynamicQuerySetHEntity_HI> saafDynamicQuerySetHDAO_HI;
    @Autowired
    private ViewObject<SaafDynamicQuerySetLEntity_HI> saafDynamicQuerySetLDAO_HI;
    @Autowired
    private BasicDataSource dataSource;
    @Autowired
    private ViewObject<FndFlexValueSetsEntity_HI> fndFlexValueSetsDAO_HI;
    @Autowired
    private IFndFlexValueSets fndFlexValueSetsServer;
    
    /**
     * 查询
     *
     * @param parameters
     *            参数：
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public String findHeadersList(JSONObject parameters, Integer pageIndex, Integer pageRows) {
        Map<String, Object> map = SToolUtils.fastJsonObj2Map(parameters);
        StringBuffer hql = new StringBuffer();
        hql.append("from SaafDynamicQuerySetHEntity_HI where 1=1 ");
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        SaafToolUtils.parperHbmParam(SaafDynamicQuerySetHEntity_HI.class,parameters, "querySetHeaderId", "querySetHeaderId", hql, queryParamMap, "=");
        SaafToolUtils.parperHbmParam(SaafDynamicQuerySetHEntity_HI.class,parameters, "querySetNum", "querySetNum", hql, queryParamMap, "like");
        SaafToolUtils.parperHbmParam(SaafDynamicQuerySetHEntity_HI.class,parameters, "setType", "setType", hql, queryParamMap, "=");
        SaafToolUtils.parperHbmParam(SaafDynamicQuerySetHEntity_HI.class,parameters, "functionTabName", "functionTabName", hql, queryParamMap, "like");
        Pagination<SaafDynamicQuerySetHEntity_HI> findListResult = saafDynamicQuerySetHDAO_HI.findPagination(hql, queryParamMap, pageIndex, pageRows);
        return JSON.toJSONString(findListResult);
    }

    /**
     * 查询
     *
     * @param parameters
     *            参数：
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public String findLinesList(JSONObject parameters, Integer pageIndex, Integer pageRows) {
        Map<String, Object> map = SToolUtils.fastJsonObj2Map(parameters);
        StringBuffer hql = new StringBuffer();
        hql.append("from SaafDynamicQuerySetLEntity_HI where 1=1 ");
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        SaafToolUtils.parperHbmParam(SaafDynamicQuerySetLEntity_HI.class,parameters, "querySetHeaderId", "querySetLineId", hql, queryParamMap, "=");
        SaafToolUtils.parperHbmParam(SaafDynamicQuerySetLEntity_HI.class,parameters, "querySetHeaderId", "querySetHeaderId", hql, queryParamMap, "=");
        SaafToolUtils.parperHbmParam(SaafDynamicQuerySetLEntity_HI.class,parameters, "querySetNum", "querySetNum", hql, queryParamMap, "=");
        SaafToolUtils.parperHbmParam(SaafDynamicQuerySetLEntity_HI.class,parameters, "enabledFlag", "enabledFlag", hql, queryParamMap, "=");
        hql.append(" order by columnSeq ");
        Pagination<SaafDynamicQuerySetLEntity_HI> findListResult = saafDynamicQuerySetLDAO_HI.findPagination(hql, queryParamMap, pageIndex, pageRows);
        List<SaafDynamicQuerySetLEntity_HI> list = findListResult.getData();
        rebuildEntity(list);//重构数据，主要用于查询取得值集的相关信息

        return JSON.toJSONString(findListResult);
    }
    
    private void rebuildEntity(List<SaafDynamicQuerySetLEntity_HI> list) {
        if(list != null && !list.isEmpty()){
            //循环数据，判断如果是值集数据，则查询相关值集信息再重构数据
            try {
                for(SaafDynamicQuerySetLEntity_HI entity : list){
                    if(entity.getListFlag()!= null && "Y".equals(entity.getListFlag())){
                        List flexValueSetList = fndFlexValueSetsDAO_HI.findByProperty("flexValueSetName", entity.getListFlexValueSetName());
                        if(flexValueSetList!=null && !flexValueSetList.isEmpty()){
                            FndFlexValueSetsEntity_HI fndFlexValueSetRow = (FndFlexValueSetsEntity_HI)flexValueSetList.get(0);
                            Integer flexValueSetId = fndFlexValueSetRow.getFlexValueSetId();
                            String validationType = fndFlexValueSetRow.getValidationType();

                            StringBuffer queryString = new StringBuffer();
                            if ("S".equals(validationType)) {
                                queryString.append("SELECT ");
                                queryString.append(" t.FLEX_VALUE idColumnName,t.FLEX_VALUE_MEANING valueColumnName,t.DESCRIPTION meaningColumnName ");
                                queryString.append(" FROM fnd_flex_values t ");
                                queryString.append(" WHERE t.enabled_flag = 'Y' and date_format(t.start_date_active,'%Y-%m-%d') <=sysdate() and (t.end_date_active is null or date_format(t.end_date_active,'%Y-%m-%d') >= sysdate())");
                                queryString.append(" and t.FLEX_VALUE_SET_ID = "+flexValueSetId);

                                entity.setListDisplay("meaningColumnName");
                                entity.setListDisplayName("描述");
                                entity.setListValue("idColumnName");
                                entity.setListValueName("值");

                            } else if ("T".equals(validationType)) {
                                FndFlexValidationTablesEntity_HI fndFlexValidationRow = fndFlexValueSetsServer.findFlexValidationById(flexValueSetId);
                                queryString.append("select ");
                                queryString.append(fndFlexValidationRow.getIdColumnName() + " idColumnName, ");
                                queryString.append(fndFlexValidationRow.getValueColumnName() + " valueColumnName, ");
                                queryString.append(fndFlexValidationRow.getMeaningColumnName() + " meaningColumnName ");
                                String additionalQuickpickColumns = fndFlexValidationRow.getAdditionalQuickpickColumns();
                                if (null != additionalQuickpickColumns && !"".equals(additionalQuickpickColumns.trim())) {
                                    queryString.append("," + additionalQuickpickColumns);
                                }
                                queryString.append(" from " + fndFlexValidationRow.getApplicationTableName());
                                queryString.append(" " + fndFlexValidationRow.getAdditionalWhereClause());

                                entity.setListDisplay("meaningColumnName");
                                entity.setListDisplayName("描述");
                                entity.setListValue("valueColumnName");
                                entity.setListValueName("值");
                            }
                            entity.setListDataSource(queryString.toString());
                            //entity.setListFlexValueSetName(entity.getListValue());
                        }
                    }
                }
            } catch (Exception e) {
                ////e.printStackTrace();
                LOGGER.error("",e);
            }
        }
    }

    /**
     * 保存头
     *
     * @param parameters
     * @return
     * @throws Exception
     */
    public JSONObject saveHeader(JSONObject parameters) throws Exception {
        int userId = parameters.getInteger("varUserId");
        SaafDynamicQuerySetHEntity_HI row = parameters.toJavaObject(SaafDynamicQuerySetHEntity_HI.class);
        if (null == parameters.getInteger("querySetHeaderId")) { // 新增
            row.setCreatedBy(userId); // 用户登录的userId，从session里面获取
            row.setCreationDate(new Date());
        } else {
            row = saafDynamicQuerySetHDAO_HI.getById(parameters.getIntValue("querySetHeaderId"));
            JSONObject tempJson = (JSONObject)JSON.toJSON(row);
            tempJson.putAll(parameters);
            parameters = tempJson;
            SaafDynamicQuerySetHEntity_HI temp = parameters.toJavaObject(SaafDynamicQuerySetHEntity_HI.class);
            BeanUtils.copyProperties(temp, row);
        }
        row.setLastUpdatedBy(userId);
        row.setLastUpdateDate(new Date());
        row.setLastUpdateLogin(userId);

        saafDynamicQuerySetHDAO_HI.saveOrUpdate(row);
        return SToolUtils.convertResultJSONObj("S", "保存成功！", 1, row);
    }

    /**
     * 根据头ID删除行
     *
     * @param parameters
     * @return
     * @throws Exception
     */
    public JSONObject deleteHeaderAndLines(JSONObject parameters) throws Exception {
        if (null == parameters.getInteger("querySetHeaderId")) { // 新增
            return SToolUtils.convertResultJSONObj("E", "删除失败，头ID不能为空！", 0, null);
        } else {
            Integer querySetHeaderId = parameters.getInteger("querySetHeaderId");
            SaafDynamicQuerySetHEntity_HI saafDynamicQuerySetHEntity_HI = saafDynamicQuerySetHDAO_HI.getById(querySetHeaderId);
            saafDynamicQuerySetHDAO_HI.delete(saafDynamicQuerySetHEntity_HI);
            List<SaafDynamicQuerySetLEntity_HI> lines = saafDynamicQuerySetLDAO_HI.findByProperty("querySetHeaderId", querySetHeaderId);
            if (lines.size() > 0) {
                saafDynamicQuerySetLDAO_HI.deleteAll(lines);
            }
        }
        return SToolUtils.convertResultJSONObj("S", "删除成功！", 1, null);
    }

    /**
     * 保存行
     *
     * @param parameters
     * @return
     * @throws Exception
     */
    public JSONObject saveLine(JSONObject parameters) throws Exception {
        int userId = parameters.getInteger("varUserId");
        SaafDynamicQuerySetLEntity_HI row = parameters.toJavaObject(SaafDynamicQuerySetLEntity_HI.class);
        if (null == parameters.getInteger("querySetLineId")) { // 新增
            row.setCreatedBy(userId); // 用户登录的userId，从session里面获取
            row.setCreationDate(new Date());
        } else {
            row = saafDynamicQuerySetLDAO_HI.getById(parameters.getIntValue("querySetLineId"));
            JSONObject tempJson = (JSONObject)JSON.toJSON(row);
            tempJson.putAll(parameters);
            parameters = tempJson;
            SaafDynamicQuerySetLEntity_HI temp = parameters.toJavaObject(SaafDynamicQuerySetLEntity_HI.class);
            BeanUtils.copyProperties(temp, row);
        }
        row.setLastUpdatedBy(userId);
        row.setLastUpdateDate(new Date());
        row.setLastUpdateLogin(userId);
        saafDynamicQuerySetLDAO_HI.saveOrUpdate(row);
        return SToolUtils.convertResultJSONObj("S", "保存成功！", 1, row);
    }

    /**
     * 删除行
     *
     * @param parameters
     * @return
     * @throws Exception
     */
    public JSONObject deleteLine(JSONObject parameters) throws Exception {
        if (null == parameters.getInteger("querySetLineId")) {
            return SToolUtils.convertResultJSONObj("E", "删除失败，行ID不能为空！", 0, null);
        } else {
            SaafDynamicQuerySetLEntity_HI saafDynamicQuerySetLEntity_HI = saafDynamicQuerySetLDAO_HI.getById(parameters.getIntValue("querySetLineId"));
            saafDynamicQuerySetLDAO_HI.delete(saafDynamicQuerySetLEntity_HI);
        }

        return SToolUtils.convertResultJSONObj("S", "删除成功！", 1, null);
    }

    public String generateColumns(JSONObject parameters) throws Exception {
        Connection conn = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            SaafDynamicQuerySetHEntity_HI header = saafDynamicQuerySetHDAO_HI.getById(parameters.getInteger("querySetHeaderId"));
            String sql = replaceSystemVariable(header.getDataSource());
            //List queryResult = commonDAO_HI_DY.findList(sql, new Object[]{});
            conn = dataSource.getConnection();
            ps = conn.createStatement();
            rs = ps.executeQuery(sql);

            Map<String, String> dataTypes = new HashMap<String, String>();
            dataTypes.put("TINYINT", "NUMBER");
            dataTypes.put("SMALLINT", "NUMBER");
            dataTypes.put("MEDIUMINT", "NUMBER");
            dataTypes.put("INTEGER", "NUMBER");
            dataTypes.put("INT", "NUMBER");
            dataTypes.put("BIGINT", "NUMBER");
            dataTypes.put("FLOAT", "NUMBER");
            dataTypes.put("DOUBLE", "NUMBER");
            dataTypes.put("DECIMAL", "NUMBER");
            dataTypes.put("DATE", "DATE");
            dataTypes.put("DATETIME", "DATETIME");
            dataTypes.put("TIMESTAMP", "DATETIME");

            ResultSetMetaData data = rs.getMetaData();
            JSONObject columnsObj = null;
            String columnTypeName;
            String columnRemark;
            String msg = "栏位已生成";
            int i;
            for (i = 1; i <= data.getColumnCount(); i++) {
                if (i > 50) {
                    msg = "动态查询页面不允许超出50个栏位，超出部分不加载！";
                    break;
                }
                columnTypeName = "STRING";
                if (dataTypes.get(data.getColumnTypeName(i)) != null) {
                    columnTypeName = dataTypes.get(data.getColumnTypeName(i));
                }
                columnRemark = getColumnRemark(conn, data.getCatalogName(i), data.getSchemaName(i), data.getTableName(i), data.getColumnName(i));
                columnRemark = columnRemark != null && !columnRemark.equals("") ? columnRemark : data.getColumnLabel(i);

                columnsObj = new JSONObject();
                columnsObj.put("varUserId", parameters.getInteger("varUserId"));
                columnsObj.put("querySetHeaderId", header.getQuerySetHeaderId());
                columnsObj.put("querySetNum", header.getQuerySetNum());
                columnsObj.put("columnSeq", i * 10);
                columnsObj.put("columnName", data.getColumnLabel(i));
                columnsObj.put("columnType", columnTypeName);
                columnsObj.put("columnTital", columnRemark);
                columnsObj.put("conditionFlag", "N");
                columnsObj.put("rangeFlag", "N");
                columnsObj.put("lookupFlag", "N");
                columnsObj.put("displayFlag", "Y");
                columnsObj.put("enabledFlag", "Y");
                this.saveLine(columnsObj);
            }
            return SToolUtils.convertResultJSONObj("S", msg, i, null).toJSONString();
//            return SToolUtils.convertResultJSONObj("S", "", 1, null).toJSONString();
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            return SToolUtils.convertResultJSONObj("E", "SQL语句错误： " + e.getMessage(), 0, null).toJSONString();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if(conn != null){
                	conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.toString());
            }
        }
    }

    /**
     * 获取栏位备注
     * @param conn
     * @param catalog
     * @param schema
     * @param table
     * @param columnName
     * @return
     */
    public static String getColumnRemark(Connection conn, String catalog, String schema, String table, String columnName) {
        String columnRemarks = null;
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet resultSet = dbmd.getTables(catalog, schema, table, new String[] { "TABLE" });

            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");

                if (tableName.equals(table)) {
                    ResultSet rs = conn.getMetaData().getColumns(catalog, schema, tableName.toUpperCase(), columnName);
                    while (rs.next()) {
                        //System.out.println("字段名："+rs.getString("COLUMN_NAME")+"--字段注释："+rs.getString("REMARKS")+"--字段数据类型："+rs.getString("TYPE_NAME"));
                        columnRemarks = rs.getString("REMARKS");
                    }
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return columnRemarks;
    }

    private String replaceSystemVariable(String sql) {
        String str = sql;
        str = str.replace("{system.instId}", "-999999");
        str = str.replace("{system.platformCode}", "'X'");
        str = str.replace("{system.userName}", "'X'");
        str = str.replace("{system.userId}", "-999999");
        str = str.replace("{system.memberId}", "-999999");
        str = str.replace("{system.isAdmin}", "'X'");
        return str;
    }

}
