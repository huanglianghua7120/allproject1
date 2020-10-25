package saaf.common.fmw.genform.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.adf.common.utils.FileUtil;

/**
 * 获取saaf.common.fmw.config.genform.properties配置的工具类
 * 
 * @author Rocky
 *
 */
public class GenFormPropertiesUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(GenFormPropertiesUtil.class);
	private static final Properties prop = new Properties();
	
	private GenFormPropertiesUtil(){
		
	}
	
	static {
		LOGGER.info("加载表单自定义配置文件:genform.properties");
		InputStream in = FileUtil.getUserResourceAsStream("/genform.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			//e.printStackTrace();
		}finally{
			try {
				if(in != null) 
					in.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
	}
	
	public static String getProperty(String key){
		return prop.getProperty(key);
	}
	
	public static String getProperty(String key,String defaultValue){
		return prop.getProperty(key,defaultValue);
	}
	
	public static boolean getBoolean(String key){
		return BooleanUtils.toBoolean(getProperty(key));
	}
	
	public static boolean getBoolean(String key,Boolean defaultValue){
		Boolean ret = BooleanUtils.toBooleanObject(getProperty(key));
		if(ret == null)
			return defaultValue;
		return ret.booleanValue();
	}
	
	public static void main(String[] args) {
		String contextPath = GenFormPropertiesUtil.getProperty("server.context.path","http://127.0.0.1:8080/GenFormApp");
		System.out.println(contextPath);
	}
}
