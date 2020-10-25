package saaf.common.fmw.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import saaf.common.fmw.common.utils.SaafJdbcUtils;


public class SqlToAttribute {
    public static String QUERY_SQL = " SELECT\n" +
        "	er.responsibility_name,\n" +
        "	er.responsibility_id,\n" +
        "       eur.user_resp_id, \n" +
        "	er.end_date_active,\n" +
        "	er.start_date_active\n" +
        " FROM\n" +
        "	saaf_responsibilitys er,\n" +
        "	saaf_user_resp eur\n" +
        " WHERE\n" +
        "	er.responsibility_id = eur.responsibility_id\n" +
        " AND trunc(er.start_date_active) <= trunc(sysdate) \n" +
        " AND (er.end_date_active is  null or trunc(er.end_date_active) >= trunc(sysdate))\n" +
        " /*AND eur.user_id = ?*/ \n";


    public static void main(String[] args) {
        getAttributeAndSql();
        //updateAlias();
    }

    public static void getAttributeAndSql() {
        Connection conn = SaafJdbcUtils.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        QUERY_SQL = QUERY_SQL.toUpperCase();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(QUERY_SQL);
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String field = rsmd.getColumnName(i);
                String name = columnAdjustment(field);
                re(field, name);
                //                switch (rsmd.getColumnType(i)) {
                //                case Types.VARCHAR:
                //                    System.out.println("private String " + name + ";");
                //                    break;
                //                case Types.INTEGER:
                //                    System.out.println("private Integer " + name + ";");
                //                    break;
                //                case Types.TIMESTAMP:
                //                    System.out.println("private Date " + name + ";");
                //                    break;
                //                case Types.DATE:
                //                    System.out.println("private Date " + name + ";");
                //                    break;
                //                case Types.DOUBLE:
                //                    System.out.println("private java.math.BigDecimal " + name + ";");
                //                    break;
                //                case Types.FLOAT:
                //                    System.out.println("private java.math.BigDecimal " + name + ";");
                //                    break;
                //                case Types.CLOB:
                //                    break;
                //                case Types.NUMERIC:
                //                    System.out.println("private Integer " + name + ";");
                //                    break;
                //                default:
                //                    System.out.println("private 默认 " + name + ";");
                //                    break;
                //                }
            }
            System.out.println(QUERY_SQL);
        } catch (SQLException e) {
            //e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                SaafJdbcUtils.releaseConnection(conn);
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
    }

    public static void updateAlias() {
        Connection conn = SaafJdbcUtils.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(QUERY_SQL);
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String field = rsmd.getColumnName(i);
                re2(field);
            }
            System.out.println(QUERY_SQL);
        } catch (SQLException e) {
            //e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                SaafJdbcUtils.releaseConnection(conn);
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
    }

    /**
     * 将字符转为小写,并将下划线去掉,并将下划线后的首字符大写
     * 如:CREATE_DATE  ===>>>  createDate
     * @param columnName
     * @return
     */
    public static String columnAdjustment(String columnName) {
        columnName = columnName.toLowerCase();
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

    private static void re(String str, String str1) {
        //        str = str.toLowerCase();
        str = str.toUpperCase();
        String t = "." + str;
        QUERY_SQL = QUERY_SQL.replaceFirst(str, str + " \"" + str1 + "\"");
    }

    private static void re2(String str) {
        int i = QUERY_SQL.toUpperCase().indexOf(" " + str);
        String str1 = str;
        if (i > 0) {
            str1 = QUERY_SQL.substring(i + 1, i + 1 + str1.length());
            //System.out.println(str1);
        }
        QUERY_SQL = QUERY_SQL.replaceFirst(" " + str1, " \"" + str1 + "\"");
    }
}
