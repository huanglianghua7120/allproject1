//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package saaf.common.fmw.schedule.utils;

import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

public class ScheduleUtil {
	public ScheduleUtil() {
	}

	public static void logInfo(JobExecutionContext context, String msg) {
		MyLogUtils.info(context.getJobDetail().getKey().getName(), msg);
	}

	public static void logError(JobExecutionContext context, String msg) {
		MyLogUtils.error(context.getJobDetail().getKey().getName(), msg);
	}

	public static Object getArguments(JobExecutionContext context, String argName) {
		Object value = null;

		try {
			value = context.getJobDetail().getJobDataMap().get(argName);//updated by sie 20180323
			//value = ((HashMap) context.getJobDetail().getJobDataMap().get(argName)).values().toArray()[0];
			MyLogUtils.info(context.getJobDetail().getKey().getName(), "指定名称 - " + argName + " :" + value);
		} catch (Exception var4) {
			MyLogUtils.error(context.getJobDetail().getKey().getName(), "指定名称 - " + argName + " - 的参数不存在");
		}

		return value;
	}

	private static boolean isParameter(String paramName) {
		return !"jobType".equals(paramName) && !"executableName".equals(paramName) && !"method".equals(paramName)
				&& !"failAttemptFrequency".equals(paramName) && !"timeout".equals(paramName)
				&& !"previousFireTime".equals(paramName) && !"scheduleType".equals(paramName)
				&& !"scheduleId".equals(paramName);
	}

	public static String getParamsJsonStr(JobExecutionContext parameters) {
		StringBuffer params = new StringBuffer("{systemCode:'SAAF'");
		String paramName = null;
		String paramValue = null;
		JobDataMap jobData = parameters.getJobDetail().getJobDataMap();
		Object[] var8;
		int var7 = (var8 = jobData.keySet().toArray()).length;

		for (int var6 = 0; var6 < var7; ++var6) {
			Object item = var8[var6];
			paramName = String.valueOf(item);
			if (isParameter(paramName)) {
				paramValue = String.valueOf(((HashMap) jobData.get(paramName)).values().toArray()[0]);
				paramValue = getDateStr(paramValue);
				params.append(",");
				params.append(paramName);
				params.append(":'");
				params.append(paramValue);
				params.append("'");
			}
		}

		params.append("}");
		return params.toString();
	}

	public static String getParamsJsonStr(JobExecutionContext parameters, String replacePreviousFireTime,
			String dateFormat) {
		StringBuffer params = new StringBuffer("{systemCode:'SAAF'");
		String paramName = null;
		String paramValue = null;
		JobDataMap jobData = parameters.getJobDetail().getJobDataMap();
		Object[] var10;
		int var9 = (var10 = jobData.keySet().toArray()).length;

		for (int var8 = 0; var8 < var9; ++var8) {
			Object item = var10[var8];
			paramName = String.valueOf(item);
			if (isParameter(paramName)) {
				if (replacePreviousFireTime.equals(paramName)) {
					Date dt = (Date) ((HashMap) jobData.get("previousFireTime")).get("Private");
					SimpleDateFormat df = new SimpleDateFormat(dateFormat);
					paramValue = df.format(dt);
				} else {
					paramValue = String.valueOf(((HashMap) jobData.get(paramName)).values().toArray()[0]);
					paramValue = getDateStr(paramValue);
				}

				params.append(",");
				params.append(paramName);
				params.append(":'");
				params.append(paramValue);
				params.append("'");
			}
		}

		params.append("}");
		return params.toString();
	}

	public static String getParamStr(JobExecutionContext context, String argName) {
		String paramValue = String.valueOf(getArguments(context, argName));
		if (paramValue == null || "null".equals(paramValue)) {
			return "";
		}

		return paramValue;
	}

	public static String getParamDateStr(JobExecutionContext context, String argName) {
		String paramValue = String.valueOf(getArguments(context, argName));
		if (paramValue == null || "null".equals(paramValue)) {
			return "";
		}
		return getDateStr(paramValue);
	}

	private static String getDateStr(String paramValue) {
		String dateStr = paramValue;
		if (paramValue.indexOf("{year}") != -1) {
			dateStr = getNewDate("{year}", "yyyy", 1, paramValue);
		} else if (paramValue.indexOf("{month}") != -1) {
			dateStr = getNewDate("{month}", "yyyyMM", 2, paramValue);
		} else if (paramValue.indexOf("{sysdate}") != -1) {
			dateStr = getNewDate("{sysdate}", "yyyy-MM-dd", 6, paramValue);
		} else if (paramValue.indexOf("{systime}") != -1) {
			dateStr = getNewDate("{systime}", "yyyy-MM-dd HH:mm:ss", 13, paramValue);
		}

		return dateStr;
	}

	public static String getNewDate(String dateCron, String dateFormat, int dateType, String valueStr) {
		int date = 0;
		String str = valueStr.replace(dateCron, "");
		if (!"".equals(str)) {
			ScriptEngine jse = (new ScriptEngineManager()).getEngineByName("JavaScript");

			try {
				date = (int) Double.parseDouble(String.valueOf(jse.eval(str)));
			} catch (NumberFormatException var10) {
				var10.printStackTrace();
			} catch (ScriptException var11) {
				var11.printStackTrace();
			}
		}

		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(new Date());
		rightNow.add(dateType, date);
		Date dt = rightNow.getTime();
		String newDate = df.format(dt);
		return newDate;
	}

	public static void saveErrorData(JobExecutionContext parameters, String scheduleData, String errorStr) {
		try {
			JSONObject errParams = new JSONObject();
			errParams.put("varUserId", -999999);
			errParams.put("scheduleId", getArguments(parameters, "scheduleId"));
			errParams.put("scheduleData", scheduleData);
			errParams.put("errorStr", errorStr);
			errParams.put("status", "NOT_PROCESSED");
		} catch (Exception var5) {
			var5.printStackTrace();
		}

	}

	public static void main(String[] args) {
		String year = "{year}-3";
		String month = "{month}-15";
		String sysdate = "{sysdate}-1*50";
		String systime = "{systime}-60*60*24*10";
		System.out.println("getYear:" + getNewDate("{year}", "yyyy", 1, year));
		System.out.println("getMonth:" + getNewDate("{month}", "yyyyMM", 2, month));
		System.out.println("getNewDate:" + getNewDate("{sysdate}", "yyyy-MM-dd", 6, sysdate));
		System.out.println("getNewTime:" + getNewDate("{systime}", "yyyy-MM-dd HH:mm:ss", 13, systime));
	}
}
