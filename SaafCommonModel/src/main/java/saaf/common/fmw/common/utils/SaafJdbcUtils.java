package saaf.common.fmw.common.utils;


import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import java.util.Properties;

import org.hibernate.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class SaafJdbcUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafJdbcUtils.class);

    public static int NUMBER = Types.NUMERIC;
    public static int DATE = Types.DATE;
    public static int VARCHAR2 = Types.VARCHAR;

    private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    private static final String JDBC_CONFIG = "saaf/common/fmw/config/db_jdbc.properties";
    public static Properties properties;

    private static String dbDriverName;
    private static String accessURL;
    private static String userName;
    private static String password;

    private static SaafJdbcUtils instance = new SaafJdbcUtils();

    public SaafJdbcUtils() {
        super();
    }

    public static SaafJdbcUtils getInstance() {
        return instance;
    }

    public static Connection getConnection() {
        InputStream in = SaafJdbcUtils.class.getClassLoader().getResourceAsStream(JDBC_CONFIG);
        Properties jdbcProperties = new Properties();
        try {
            jdbcProperties.load(in);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        //        Properties jdbcProperties = PropertiesUtils.loadProperties("saaf/common/fmw/config/db_jdbc.properties");
        dbDriverName = jdbcProperties.getProperty("jdbc.driverClassName");
        accessURL = jdbcProperties.getProperty("jdbc.url");
        //accessURL = accessURL.substring(0, accessURL.indexOf("?"));
        userName = jdbcProperties.getProperty("jdbc.username");
        password = jdbcProperties.getProperty("jdbc.password");
        try {
            Class.forName(dbDriverName);
        } catch (ClassNotFoundException e) {
            LOGGER.info("driver error: ", e);
        }
        try {
            return DriverManager.getConnection(accessURL, userName, password);
        } catch (SQLException e) {
            LOGGER.info("Url error: ", e);
        }
        return null;
    }


    public static void releaseConnection(Connection connection) {
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace();
            }
            Session session = threadLocal.get();
            if (null != session && session.isOpen()) {
                LOGGER.info("releaseConnection session hash code is : " + session.hashCode() + "**************");
                session.close();
            }
        }
    }


    /**
     * 执行存储过程
     * @param
     * @param sqlReturnType
     * @param stmt
     * @param bindVars
     * @return
     */
    //    public Object callStoredProcedure(int[] sqlReturnType, String stmt, Object[] bindVars) throws Exception {
    //        CallableStatement st = null;
    //        Connection con = null;
    //        Session session = null;
    //        try {
    //        	SessionFactory sf = (SessionFactory) SaafToolUtils.context.getBean("sessionFactory");
    //            session = threadLocal.get();
    //            if (null == session) {
    //                session = sf.openSession();
    //                threadLocal.set(session);
    //            }
    //            con = session.connection();
    //            // 1. Create a JDBC CallabledStatement
    //            LOGGER.info("执行存储过程：SQL==>begin " + stmt + ";end;");
    //            st = con.prepareCall("begin " + stmt + ";end;");
    //            // st = dbt.createCallableStatement("begin " + stmt + ";end;", 0);
    //            // 2. Register the first bind variable for the return value
    //
    //            if (bindVars != null) {
    //                // 3. Loop over values for the bind variables passed in, if any
    //                for (int z = 0; z < bindVars.length; z++) {
    //                    // 4. Set the value of user-supplied bind vars in the stmt
    //                    if (bindVars[z] == null) {
    //                        st.setObject(z + 1, "");
    //                    } else {
    //                        st.setObject(z + 1, bindVars[z]);
    //                    }
    //
    //
    //                }
    //            }
    //            if (sqlReturnType != null) {
    //                for (int z = bindVars.length; z < bindVars.length + sqlReturnType.length; z++) {
    //                    st.registerOutParameter(z + 1, sqlReturnType[z - bindVars.length]);
    //                }
    //            }
    //            // 5. Set the value of user-supplied bind vars in the stmt
    //            st.executeUpdate();
    //            List ls = new ArrayList();
    //            if (sqlReturnType != null) {
    //                for (int z = bindVars.length; z < bindVars.length + sqlReturnType.length; z++) {
    //                    ls.add(st.getObject(z + 1));
    //                }
    //            }
    //            // 6. Return the value of the first bind variable
    //            return ls;
    //        } catch (SQLException e) {
    //            throw new Exception(e);
    //        } finally {
    //        	try {
    //        		if(st != null){
    //        			st.close();
    //        		}
    //        		if(con != null){
    //        			con.close();
    //        		}
    //        		if(session != null){
    //        			session.close();
    //        		}
    //            } catch (SQLException e) {
    //                //e.printStackTrace();
    //            }
    //
    //        }
    //    }

    /**
     * 执行函数公共方法
     * @param
     * @param sqlReturnType
     * @param stmt
     * @param bindVars
     * @return
     */
    //    public Object callStoredFunction(int[] sqlReturnType, String stmt, Object[] bindVars) throws Exception {
    //        //        CallabledStatement = st = null;
    //    	CallableStatement st = null;
    //        Connection con = null;
    //        Session session = null;
    //        try {
    //        	SessionFactory sf = (SessionFactory) SaafToolUtils.context.getBean("sessionFactory");
    //            session = threadLocal.get();
    //            if (null == session) {
    //                session = sf.openSession();
    //                threadLocal.set(session);
    //            }
    //            con = session.connection();
    //            // 1. Create a JDBC CallabledStatement
    //            //            st =  dbt.prepareStatement("begin " + stmt + ";end;", 0);
    //            LOGGER.info("执行函数：SQL==>begin " + stmt + ";end;");
    //            st = con.prepareCall("begin " + stmt + ";end;");
    //            //  st = dbt.createCallableStatement("begin " + stmt + ";end;", 0);
    //            // 2. Register the first bind variable for the return value
    //
    //            if (bindVars != null) {
    //                // 3. Loop over values for the bind variables passed in, if any
    //                for (int z = 0; z < bindVars.length; z++) {
    //                    // 4. Set the value of user-supplied bind vars in the stmt
    //                    if (bindVars[z] == null) {
    //                        st.setObject(z + 2, "");
    //                    } else {
    //                        st.setObject(z + 2, bindVars[z]);
    //                    }
    //                }
    //            }
    //            if (sqlReturnType != null) {
    //                st.registerOutParameter(1, sqlReturnType[0]);
    //            }
    //            // 5. Set the value of user-supplied bind vars in the stmt
    //            st.executeUpdate();
    //            // 6. Return the value of the first bind variable
    //            return st.getObject(1);
    //        } catch (SQLException e) {
    //            LOGGER.error("执行函数异常！", e);
    //            throw new Exception(e);
    //        } finally {
    //        	try {
    //        		if(st != null){
    //        			st.close();
    //        		}
    //        		if(con != null){
    //        			con.close();
    //        		}
    //        		if(session != null){
    //        			session.close();
    //        		}
    //            } catch (SQLException e) {
    //                //e.printStackTrace();
    //            }
    //        }
    //    }

    /**
     *  判断json的某个元素是否为null
     * @param elementValue
     * @return
     */
    public Object jsonElementValueIsNull(Object elementValue) {
        if (elementValue instanceof JSONObject) {
            if ((((JSONObject)elementValue).isEmpty())) {
                elementValue = null;
            }
        }
        return elementValue;
    }

    //    public VOList executeQuery(String sSQL, int resultType) {
    //    	Connection con = null;
    //        Session session = null;
    //        	
    //        Statement st = null;
    //        ResultSet rs = null;
    //        VOList ret = null;
    //        try {
    //        	SessionFactory sf = (SessionFactory) SaafToolUtils.context.getBean("sessionFactory");
    //            session = threadLocal.get();
    //            if (null == session) {
    //                session = sf.openSession();
    //                threadLocal.set(session);
    //            }
    //            con = session.connection();
    //            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    //            rs = st.executeQuery(sSQL);
    //            ret = new VOList(resultType);
    //            ret.set(rs);
    //        } catch (SQLException e) {
    //            //e.printStackTrace();
    //        } catch (Exception e) {
    //            //e.printStackTrace();
    //        } finally {
    //            try {
    //                if (rs != null) {
    //                    rs.close();
    //                }
    //                if (st != null) {
    //                    st.close();
    //                }
    //        		if(con != null){
    //        			con.close();
    //        		}
    //        		if(session != null){
    //        			session.close();
    //        		}
    //            } catch (SQLException e) {
    //                //e.printStackTrace();
    //            }
    //        }
    //        return ret;
    //    }

    //    public boolean executeUpdate(String sql) {
    //    	Connection con = null;
    //        Session session = null;
    //        boolean flag = false;
    //        Statement st = null;
    //        try {
    //        	SessionFactory sf = (SessionFactory) SaafToolUtils.context.getBean("sessionFactory");
    //            session = threadLocal.get();
    //            if (null == session) {
    //                session = sf.openSession();
    //                threadLocal.set(session);
    //            }
    //            con = session.connection();
    //
    //            st = con.createStatement();
    //            flag = st.execute(sql);
    //        } catch (SQLException e) {
    //            //e.printStackTrace();
    //        } finally {
    //            try {
    //                if (st != null) {
    //                    st.close();
    //                }
    //                if(con != null){
    //        			con.close();
    //        		}
    //        		if(session != null){
    //        			session.close();
    //        		}
    //            } catch (SQLException e) {
    //                //e.printStackTrace();
    //            }
    //        }
    //        return flag;
    //    }

}
