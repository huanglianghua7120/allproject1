package properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class IntfPropertiesUtil {
	public   Properties properties;
	 
	public IntfPropertiesUtil(){
          properties = new Properties();
        try {
            InputStream in =
                getClass().getResourceAsStream("Interface.properties");
            properties.load(in);
        } catch (IOException e) {
            //e.printStackTrace();
        }
	};
	public String getStringValue(String key){
		Object value = properties.getProperty(key);
		if(value!=null){
		return value.toString();
		}
		return "";
		
	}
}
