package saaf.common.fmw.intf.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class IntfUtils {

	public static String replaceBackslash(String value) {

		String retValue =  value.replaceAll("\\\\", "\\\\\\\\"); 
		retValue= retValue.replaceAll("'", "\\\\\"");
		return retValue;

	}

	public static String getYesNo(String value) {
		if ("1".equals(value)) {
			return "Y";
		}

		if ("True".equals(value) || "true".equals(value)) {
			return "Y";
		}

		if ("Y".equals(value)) {
			return "Y";
		}

		return "N";

	}

	// 传U9
	public static String getU9YesNo(String value) {
		if ("Y".equals(value)) {
			return "1";
		}

		return "0";

	}

	public static String jointStr(String str1, String separator, String str2) {
		if (str1 == null) {
			return str2;
		} else if (str2 == null) {

			return str1;
		} else {
			return str1 + separator + str2;
		}

	}

	public static BigDecimal getBigDecimalValue(Object value) throws Exception {

		if (value != null) {
			try {
				return new BigDecimal(value.toString());
			} catch (Exception e) {
				return new BigDecimal(0);
			}
		}

		return new BigDecimal(0);
	}

	public static String getDateStr(Date date, String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		return f.format(date);
	}

	public static Date dateAdd(Date date, Integer value) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, value);// 今天 加 value 天

		Date date2 = c.getTime();
		return date2;
	}

}
