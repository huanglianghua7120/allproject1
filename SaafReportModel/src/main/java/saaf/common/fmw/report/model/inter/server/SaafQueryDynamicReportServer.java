/**
 * create by sie liujun
 */
package saaf.common.fmw.report.model.inter.server;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.inter.IFndFlexValueSets;
import saaf.common.fmw.report.model.entities.SaafDynamicQuerySetHEntity_HI;
import saaf.common.fmw.report.model.entities.SaafDynamicQuerySetLEntity_HI;
import saaf.common.fmw.report.model.inter.ISaafQueryDynamicReport;
import saaf.common.fmw.utils.SrmUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.dao.DynamicBaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;


@Component("saafQueryDynamicReportServer")
public class SaafQueryDynamicReportServer implements ISaafQueryDynamicReport {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafQueryDynamicReportServer.class);
    @Autowired
    private ViewObject<SaafDynamicQuerySetHEntity_HI> saafDynamicQuerySetHDAO_HI;
    @Autowired
    private ViewObject<SaafDynamicQuerySetLEntity_HI> saafDynamicQuerySetLDAO_HI;
    @Autowired
    private DynamicBaseViewObject commonDAO_HI_DY;
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
    public String findList(String querySetNum, JSONObject parameters, Map<String, String> sessionBean, Integer pageIndex, Integer pageRows) {
        String hql = "from SaafDynamicQuerySetHEntity_HI where querySetNum = :querySetNum and setType = 'SINGLE'";
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        queryParamMap.put("querySetNum", querySetNum);
        List<SaafDynamicQuerySetHEntity_HI> dynamicQuerySetH = saafDynamicQuerySetHDAO_HI.findList(hql, queryParamMap);
        if (dynamicQuerySetH.size() > 0) {
            SaafDynamicQuerySetHEntity_HI hrow = dynamicQuerySetH.get(0);
            String hql2 = "from SaafDynamicQuerySetLEntity_HI where querySetNum = :querySetNum and enabledFlag = 'Y' order by columnSeq";
            List<SaafDynamicQuerySetLEntity_HI> dynamicQuerySetL = saafDynamicQuerySetLDAO_HI.findList(hql2, queryParamMap);

            Map<String, Object> map = new HashMap<String, Object>();
            int index1 = 0;
            StringBuffer whereClause = createQueryWhereClause(parameters, map,dynamicQuerySetL);

            String sourceSql = hrow.getDataSource();
            sourceSql = replaceSystemVariable(sourceSql, sessionBean);

            StringBuffer sql = new StringBuffer();
            sql.append("select ");

            SaafDynamicQuerySetLEntity_HI lrow = null;
            String columnName = null;
            int index2 = 0;
            int j = 0;
            for (int i = 0; i < dynamicQuerySetL.size(); i++) {
                lrow = dynamicQuerySetL.get(i);
                if ("Y".equals(lrow.getDisplayFlag())) {
                    j++;
                    if (j != 1) {
                        sql.append(", ");
                    }

                    index2 = lrow.getColumnName().indexOf(".") + 1;
                    index1 = lrow.getColumnName().indexOf(" ");
                    index1 = index1 < 0 ? index2 : index1;
                    columnName = lrow.getColumnName().substring(index1);
                    if ("DATE".equals(lrow.getColumnType())) {
                        sql.append(" date_format(");
                        sql.append(columnName);
                        sql.append(",'%Y-%m-%d')");
                    } else if ("DATETIME".equals(lrow.getColumnType())) {
                        sql.append(" date_format(");
                        sql.append(columnName);
                        sql.append(",'%Y-%m-%d %H:%i:%s')");
                    } else if ("NUMBER".equals(lrow.getColumnType())) {
                        sql.append(" CAST(");
                        sql.append(columnName);
                        sql.append(" AS CHAR)");
                    } else {
                        sql.append(columnName);
                    }
                    sql.append(" \"column");
                    sql.append(j);
                    sql.append("\"");
                }
            }

            sql.append(" from (");
            sql.append(sourceSql);
            sql.append(whereClause);
            sql.append("\n");
            sql.append(hrow.getOrderBy() != null ? hrow.getOrderBy() : ""); //Order By、Group By
            sql.append(" ) t ");
            LOGGER.info(sql.toString());

            StringBuffer countSql = new StringBuffer();
            if (hrow.getCountSql() != null && !"".equals(hrow.getCountSql().trim())) {
                countSql.append(replaceSystemVariable(hrow.getCountSql(), sessionBean));
                countSql.append(whereClause);
            } else {
                //				countSql.append("select count(1) from (");
                //				countSql.append(sourceSql);
                //				countSql.append(whereClause);
                //				countSql.append(") t");
                countSql = null;
            }
            LOGGER.info(String.valueOf(countSql));
            try {
                Pagination findListResult = commonDAO_HI_DY.findPagination(sql, countSql, map, pageIndex, pageRows);
                return JSON.toJSONString(findListResult);
            } catch (Exception e) {
                //e.printStackTrace();
                LOGGER.error("查询报表异常！报表编码：" + querySetNum, e);
                return SToolUtils.convertResultJSONObj("E", "查询失败！执行SQL：\n " + sql, 0, null).toJSONString();
            }
        }

        return SToolUtils.convertResultJSONObj("E", "查询失败！报表编码为" + querySetNum + "的报表不存在，请联系管理员！", 0, null).toJSONString();
    }

    public String findLov(JSONObject parameters, Map<String, String> sessionBean, Integer pageIndex, Integer pageRows) {
        Integer querySetLineId = parameters.getInteger("querySetLineId");
        
        String listValue = parameters.getString("listValue");
        String listDisplay = parameters.getString("listDisplay");
        String listDataSource = parameters.getString("listDataSource");
        parameters.remove("listValue");
        parameters.remove("listDisplay");
        parameters.remove("listDataSource");
        
        //SaafDynamicQuerySetLEntity_HI row = saafDynamicQuerySetLDAO_HI.getById(querySetLineId);
        JSONObject parames = parameters;
        parames.remove("querySetLineId");
        StringBuffer whereClause = new StringBuffer();

        Map<String, Object> map = new HashMap<String, Object>();
        for (Entry<String, Object> entry : parames.entrySet()) {
        	if(entry.getValue()!=null) {
                if(entry.getValue() instanceof String && StringUtils.isEmpty(entry.getValue().toString())){
                    continue;
                }
            whereClause.append(" and ");
            whereClause.append(entry.getKey());
            whereClause.append(" like :");
            whereClause.append(entry.getKey());
            map.put(entry.getKey(), "%" + entry.getValue() + "%");
        	}
        }

        String lovSql = listDataSource;
        lovSql = replaceSystemVariable(lovSql, sessionBean);

        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(listValue);
        sql.append(" \"column1\",");
        sql.append(listDisplay);
        sql.append(" \"column2\" from (");
        sql.append(lovSql);
        sql.append(" ) t where 1=1 ");
        sql.append(whereClause);
        LOGGER.info(sql.toString());

        //		StringBuffer countSql = new StringBuffer();
        //		countSql.append("select count(1) from (");
        //		countSql.append(lovSql);
        //		countSql.append(whereClause);
        //		countSql.append(") t");
        //		LOGGER.info(countSql.toString());
        try {
            Pagination findListResult = commonDAO_HI_DY.findPagination(sql, null, map, pageIndex, pageRows);
            return JSON.toJSONString(findListResult);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询报表动态LOV异常！", e);
            return SToolUtils.convertResultJSONObj("E", "LOV查询失败！执行SQL：\n " + sql, 0, null).toJSONString();
        }
    }

    /**
     * 公共服务引用
     *
     * @param parameters 参数
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public String findService(String querySetNum, JSONObject parameters, Map<String, String> sessionBean, Integer pageIndex, Integer pageRows) {
        String hql = "from SaafDynamicQuerySetHEntity_HI where querySetNum = :querySetNum and setType = 'SINGLE'";
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        queryParamMap.put("querySetNum", querySetNum);
        List<SaafDynamicQuerySetHEntity_HI> dynamicQuerySetH = saafDynamicQuerySetHDAO_HI.findList(hql, queryParamMap);
        if (dynamicQuerySetH.size() > 0) {
            SaafDynamicQuerySetHEntity_HI hrow = dynamicQuerySetH.get(0);
            String hql2 = "from SaafDynamicQuerySetLEntity_HI where querySetNum = :querySetNum and enabledFlag = 'Y' order by columnSeq";
            List<SaafDynamicQuerySetLEntity_HI> dynamicQuerySetL = saafDynamicQuerySetLDAO_HI.findList(hql2, queryParamMap);

            Map<String, Object> map = new HashMap<String, Object>();
            int index1 = 0;
            StringBuffer whereClause = createQueryWhereClause(parameters, map,dynamicQuerySetL);

            String sourceSql = hrow.getDataSource();
            sourceSql = replaceSystemVariable(sourceSql, sessionBean);

            StringBuffer sql = new StringBuffer();
            sql.append("select ");
            SaafDynamicQuerySetLEntity_HI lrow = null;
            String columnName = null;
            int index2 = 0;
            int j = 0;
            for (int i = 0; i < dynamicQuerySetL.size(); i++) {
                lrow = dynamicQuerySetL.get(i);
                if ("Y".equals(lrow.getDisplayFlag())) {
                    j++;
                    if (j != 1) {
                        sql.append(", ");
                    }
                    index2 = lrow.getColumnName().indexOf(".") + 1;
                    index1 = lrow.getColumnName().indexOf(" ");
                    index1 = index1 < 0 ? index2 : index1;
                    columnName = lrow.getColumnName().substring(index1);
                    sql.append(columnName);
                    sql.append(" \"");
                    columnName = columnAdjustment(columnName);
                    sql.append(columnName);
                    sql.append("\"");
                }
            }

            sql.append(" from (");
            sql.append(sourceSql);
            sql.append(whereClause);
            sql.append("\n");
            sql.append(hrow.getOrderBy() != null ? hrow.getOrderBy() : ""); //Order By、Group By
            sql.append(" ) t ");
            LOGGER.info(sql.toString());

            StringBuffer countSql = new StringBuffer();
            if (hrow.getCountSql() != null && !"".equals(hrow.getCountSql().trim())) {
                countSql.append(replaceSystemVariable(hrow.getCountSql(), sessionBean));
                countSql.append(whereClause);
            } else {
                //				countSql.append("select count(1) from (");
                //				countSql.append(sourceSql);
                //				countSql.append(whereClause);
                //				countSql.append(") t");
                countSql = null;
            }
            LOGGER.info(String.valueOf(countSql));
            try {
                Pagination findListResult = commonDAO_HI_DY.findPagination(sql, countSql, map, pageIndex, pageRows);
                return JSON.toJSONString(findListResult);
            } catch (Exception e) {
                //e.printStackTrace();
                LOGGER.error("查询报表异常！报表编码：" + querySetNum, e);
                return SToolUtils.convertResultJSONObj("E", "查询失败！执行SQL：\n " + sql, 0, null).toJSONString();
            }
        }
        return SToolUtils.convertResultJSONObj("E", "查询失败！报表编码为" + querySetNum + "的报表不存在，请联系管理员！", 0, null).toJSONString();
    }
    
    private StringBuffer createQueryWhereClause(JSONObject parameters, Map<String, Object> map,List<SaafDynamicQuerySetLEntity_HI> dynamicQuerySetL) {
        StringBuffer whereClause = new StringBuffer();
        Object value = null;
        String key = null;
        int index1 = 0;
        String paramName;
        String clauseStr = "";
        for (Entry<String, Object> entry : parameters.entrySet()) {
            value = entry.getValue();
            key = entry.getKey();
            
            if(value == null) continue;
            
            index1 = key.indexOf(" ");
            index1 = index1 < 0 ? key.length() : index1;
            paramName = entry.getKey().substring(0, index1);
            if (!key.startsWith("default_var_")) {
                whereClause.append(" and ");
                if (key.startsWith("var_min_")) {
                    key = key.substring(8, index1);
                    //key = "to_char("+key+",'yyyy-mm-dd')";
                    //whereClause.append(key);
                    //whereClause.append(" >= :");
                    clauseStr = " >= ";
                } else if (key.startsWith("var_max_")) {
                    key = key.substring(8, index1);
                    //key = "to_char("+key+",'yyyy-mm-dd')";
                    //whereClause.append(key);
                    //whereClause.append(" <= :");
                    clauseStr = " <= ";
                } else if (key.startsWith("var_equal_")) {
                    key = key.substring(10, index1);
                   // whereClause.append(key);
                   // whereClause.append(" = :");
                    clauseStr = " = ";
                } else if (key.startsWith("var_like_")) {
                    key = key.substring(9, index1);
                    //whereClause.append(key);
                    //whereClause.append(" like :");
                    clauseStr = " like ";
                    value = value != null ? "%" + value + "%" : "";
                } else if (key.startsWith("var_noEqual_")) {
                    key = key.substring(12, index1);
                    //whereClause.append(key);
                    //whereClause.append(" <> :");
                    clauseStr = " <> ";
                }
                SaafDynamicQuerySetLEntity_HI lrow = null;
                int j = 0;
                for (int i = 0; i < dynamicQuerySetL.size(); i++) {
                    lrow = dynamicQuerySetL.get(i);
                    if(key.equals(lrow.getColumnName())&&"DATE".equals(lrow.getColumnType())){
                        whereClause.append(key);
                    	whereClause.append(clauseStr);
                    	whereClause.append("STR_TO_DATE(:");
                    	whereClause.append(paramName);
                    	whereClause.append(",'%Y-%m-%d')");
                    	j=1;
                    	break;
                    }else if(key.equals(lrow.getColumnName())&&"DATETIME".equals(lrow.getColumnType())){
                    	whereClause.append(key);
                    	whereClause.append(clauseStr);
                    	whereClause.append("STR_TO_DATE(:");
                    	whereClause.append(paramName);
                    	whereClause.append(",'%Y-%m-%d %H:%i:%s')");
                    	j=1;
                    	break;
                    }
                }
                
                if(j==0){
                	whereClause.append(key);
                	whereClause.append(clauseStr);
                	whereClause.append(":");
                	whereClause.append(paramName);
                }
            }
            map.put(paramName, value);
        }
        return whereClause;
    }

    private String replaceSystemVariable(String sql, Map<String, String> sessionBean) {
        String str = sql;
        str = str.replace("{system.instId}", sessionBean.get("instId") != null ? sessionBean.get("instId") : "-999999");
        str = str.replace("{system.platformCode}", sessionBean.get("platformCode"));
        str = str.replace("{system.userName}", sessionBean.get("userName"));
        str = str.replace("{system.userId}", sessionBean.get("userId"));
        str = str.replace("{system.memberId}", sessionBean.get("memberId") != null ? sessionBean.get("memberId") : "-999999");
        str = str.replace("{system.isAdmin}", sessionBean.get("isAdmin"));
        str = str.replace("{system.supplierId}",!SrmUtils.isNvl( sessionBean.get("supplierId")) ? sessionBean.get("supplierId") : "-999999");
        str = str.replace("{system.employeeId}", !SrmUtils.isNvl(sessionBean.get("employeeId")) ? sessionBean.get("employeeId") : "-999999");
        str = str.replace("{system.employeeName}",!SrmUtils.isNvl(sessionBean.get("employeeName"))?sessionBean.get("employeeName"):"-999999");
        return str;
    }

    /**
     * 将字符转为小写,并将下划线去掉,并将下划线后的首字符大写
     * 如:CREATE_DATE  ===>>>  createDate
     * @param columnName
     * @return
     */
    public static String columnAdjustment(String columnName) {
        columnName = columnName.toLowerCase();
        columnName.substring(columnName.indexOf(".") + 1);
        String[] str = columnName.split("_");
        if (str.length > 0) {
            columnName = "";
            for (int i = 0; i < str.length; i++) {
                if (i == 0) {
                    columnName = str[i];
                } else {
                    columnName += str[i].substring(0, 1).toUpperCase() + str[i].substring(1);
                }
            }
        }
        return columnName;
    }

}
