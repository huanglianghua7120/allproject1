package saaf.common.fmw.genform.model.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.dao.ViewObjectDynamicFormImpl;

@Component("saafDynamicTableInfoDAO")
public class SaafDynamicTableInfoDAO extends ViewObjectDynamicFormImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaafDynamicTableInfoDAO.class);

    public SaafDynamicTableInfoDAO() {
        super();
    }

    /**
     * 查询表字段名称
     * @param table
     * @return 返回只包含名称的数组
     */
    public JSONArray findColumnNames(String table) {
        JSONArray array = this.findColumns(table).getJSONArray("data");
        JSONArray ret = new JSONArray();
        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            ret.add(obj.getString("COLUMN_NAME"));
        }
        return ret;
    }

    /**
     * 查询表字段
     * @param table 数据表
     * @return
     */
    public JSONObject findColumns(String table) {
        Connection conn = null;
        ResultSet rs = null;
        JSONObject result = new JSONObject();
        try {
            conn = getHibernateTemplate().getSessionFactory().getCurrentSession().connection();
            DatabaseMetaData md = conn.getMetaData();

            rs = md.getColumns(null, null, table, null);
            ResultSetMetaData rsmd = rs.getMetaData();

            JSONArray fields = new JSONArray(); //字段数组
            JSONObject field = null; //一个字段
            while (rs.next()) {
                field = new JSONObject();
                for (int i = 1, len = rsmd.getColumnCount(); i <= len; i++) {
                    String columnName = rsmd.getColumnName(i);
                    int columnType = rsmd.getColumnType(i);
                    if (columnType == Types.CHAR || columnType == Types.VARCHAR) {
                        field.put(columnName, rs.getString(columnName));
                    } else if (columnType == Types.INTEGER || columnType == Types.SMALLINT) {
                        field.put(columnName, rs.getInt(columnName));
                    }
                }
                fields.add(field);
            }
            result.put("data", fields);
            result.put("status", 0);
            result.put("msg", "success");
            return result;
        } catch (SQLException e) {
            LOGGER.error("", e);
            result.put("status", 1);
            result.put("msg", "fail:" + e.getMessage());
            return result;
        } finally {
            closeAll(conn, rs);
        }
    }

    /**
     * 查询表信息
     * @param table 数据表
     * @return
     */
    public JSONObject findTableInfo(String table) {
        Connection conn = null;
        ResultSet rs = null;
        JSONObject result = new JSONObject();
        try {
            conn = getHibernateTemplate().getSessionFactory().getCurrentSession().connection();

            DatabaseMetaData md = conn.getMetaData();
            rs = md.getTables(null, null, table, new String[] { "TABLE" });
            ResultSetMetaData rsmd = rs.getMetaData();
            if (rs.next()) {
                for (int i = 1, len = rsmd.getColumnCount(); i <= len; i++) {
                    String columnName = rsmd.getColumnName(i);
                    int columnType = rsmd.getColumnType(i);
                    if (columnType == Types.CHAR || columnType == Types.VARCHAR) {
                        result.put(columnName, rs.getString(columnName));
                    } else if (columnType == Types.INTEGER || columnType == Types.SMALLINT) {
                        result.put(columnName, rs.getString(columnName));
                    }
                }
            }

            result.put("status", 0);
            result.put("msg", "success");
            return result;
        } catch (SQLException e) {
            LOGGER.error("", e);
            result.put("status", 1);
            result.put("msg", "fail:" + e.getMessage());
            return result;
        } finally {
            closeAll(conn, rs);
        }
    }

    private void closeAll(Connection conn, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            LOGGER.error("", e);
        }
    }

}
