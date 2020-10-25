package saaf.common.fmw.schedule.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;

public final class SaafToolUtils {
	private static final Logger logger = LoggerFactory.getLogger(SaafToolUtils.class);
	public static final String STATUS = "status";
	public static final String MSG = "msg";
	public static final String COUNT = "count";
	public static final String DATA = "data";
	public static ApplicationContext context;

	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	static {
	    try{
            if(null == SaafToolUtils.context){
                context = new FileSystemXmlApplicationContext(
                        "classpath:saaf/common/fmw/config/spring.hibernate.cfg.xml");
            }
        }catch (Exception e){
	        e.printStackTrace();
        }
    }

	public SaafToolUtils() {
		super();
	}

	public static Connection getConnection() {
		SessionFactory sf = (SessionFactory) SaafToolUtils.context.getBean("sessionFactory");
		Session session = threadLocal.get();
		if (null == session) {
			session = sf.openSession();
			threadLocal.set(session);
		}
		logger.info("getConnection session hash code is : " + session.hashCode() + "======================");
		return sf.openSession().connection();
	}

	public static void releaseConnection(Connection connection) {
		if (null != connection) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
			Session session = threadLocal.get();
			logger.info("releaseConnection session hash code is : " + session.hashCode() + "**************");
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
	}

	/**
	 * 返回一个JSON对象，主要用在服务求之后获取的数据转成一个json对象
	 * 
	 * @param resultStatus
	 * @param resultMessage
	 * @param resultCount
	 * @param resultData
	 * @return
	 */
	public static JSONObject convertResultJSONObj(String resultStatus, String resultMessage, long resultCount,
			String resultData) {
		JSONObject json = new JSONObject();
		json.put(STATUS, resultStatus);
		json.put(MSG, resultMessage);
		json.put(COUNT, resultCount);
		json.put(DATA, resultData);
		return json;
	}

	/**
	 * 返回一个JSON对象，主要用在服务求之后获取的数据转成一个json对象
	 * 
	 * @param resultStatus
	 * @param resultMessage
	 * @param resultCount
	 * @param resultData
	 * @return
	 */
	public static JSONObject convertResultJSONObj(String resultStatus, String resultMessage, long resultCount,
			Object resultData) {
		JSONObject json = new JSONObject();
		json.put(STATUS, resultStatus);
		json.put(MSG, resultMessage);
		json.put(COUNT, resultCount);
		json.put(DATA, resultData);
		return json;
	}
}
