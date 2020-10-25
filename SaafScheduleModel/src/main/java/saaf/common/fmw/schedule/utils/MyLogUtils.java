package saaf.common.fmw.schedule.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;

/**
 * 读、写请求执行日志。
 */
public class MyLogUtils {

	// 用于缓存日志内容。格式为（HashMap<日志文件名称，日志内容>）
	private static HashMap<String, StringBuilder> openedFileConstants = new HashMap<String, StringBuilder>();
	// 默认日志文件后缀
	private static String defaultLogFileSuffix = ".log";
	// windows目录
	// private static String folderOfLogFile = "F:"+File.separator+"logs";
	private static String folderOfLogFile = File.separator + "home" + File.separator + "tomcat2" + File.separator
			+ "logs" + File.separator + "joblog";
	//
	private static String logFileSuffix = ".log";

	private MyLogUtils() {
		super();
	}

	private static synchronized StringBuilder getFromOpenedFileConstants(String key) {

		return openedFileConstants.get(key);
	}

	private static synchronized Object putIntoOpenedFileConstants(String key, StringBuilder value) {

		return openedFileConstants.put(key, value);
	}

	private static synchronized StringBuilder removeFromOpenedFileConstants(String key) {

		return openedFileConstants.remove(key);
	}

	/**
	 * 用于日志记录。
	 * 
	 * @param fileName
	 * @param info
	 */
	public static void info(String fileName, String info) {

		if (getFromOpenedFileConstants(fileName) != null) {
			getFromOpenedFileConstants(fileName).append("< "
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " > INFO > " + info + "\r\n");
		} else {
			StringBuilder newFileLogConstants = new StringBuilder();

			String fileConstants = "";
			if (isFileExist(fileName)) {
				fileConstants = readLogFile(fileName);
				newFileLogConstants.append(fileConstants + "\r\n");
			}

			newFileLogConstants.append("< " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
					+ " > INFO > " + info + "\r\n");
			putIntoOpenedFileConstants(fileName, newFileLogConstants);
		}
	}

	/**
	 * 用于记录错误日志。
	 * 
	 * @param fileName
	 * @param info
	 */
	public static void error(String fileName, String info) {

		if (getFromOpenedFileConstants(fileName) != null) {
			getFromOpenedFileConstants(fileName)
					.append("< " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
							+ " > *** ERROR *** > " + info + "\r\n");
		} else {
			StringBuilder newFileLogConstants = new StringBuilder();

			String fileConstants = "";
			if (isFileExist(fileName)) {
				fileConstants = readLogFile(fileName);
				newFileLogConstants.append(fileConstants + "\r\n");
			}

			newFileLogConstants.append("< " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
					+ " > *** ERROR *** > " + info + "\r\n");
			putIntoOpenedFileConstants(fileName, newFileLogConstants);
		}
	}

	/**
	 * 实际写日志文件。（指定文件不存在则创建。）
	 */
	private static void writeLogFile(String fileName, String info) {

		File logFolder = new File(folderOfLogFile);

		// 如果 folderOfLogFile 配置为null 或空字符串，则函数立即返回。
		if (folderOfLogFile == null || folderOfLogFile.trim().length() == 0 || fileName == null
				|| fileName.trim().length() == 0 || info == null || info.trim().length() == 0
				|| !logFolder.isDirectory()) {
			return;
		}

		// 如果目录不存在，将创建。（如果目录创建失败，则函数立即返回。）
		if (!logFolder.exists()) {
			if (!logFolder.mkdirs()) {
				return;
			}
		}

		// 使用这种方式拼写字符串，不能达到预期目的。?
		// String logFileAbsolutePath =
		// folderOfLogFile + File.separator + fileName.trim() + logFileSuffix ==
		// null || logFileSuffix.length() == 0 ||
		// !logFileSuffix.trim().startsWith(".", 0) ? defaultLogFileSuffix :
		// logFileSuffix.trim();

		// 使用StringBuilder代替String。可行。
		StringBuilder logFileAbsolutePath = new StringBuilder();
		logFileAbsolutePath.append(folderOfLogFile);
		logFileAbsolutePath.append(File.separator);
		logFileAbsolutePath.append(fileName.trim());
		logFileAbsolutePath
				.append(logFileSuffix == null || logFileSuffix.length() == 0 || !logFileSuffix.trim().startsWith(".", 0)
						? defaultLogFileSuffix : logFileSuffix.trim());

		File logFile = new File(logFileAbsolutePath.toString());
		// 如果日志文件不存在，则创建。
		if (!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
			}
		}

		try {
			FileWriter newWriter = new FileWriter(logFile);
			// 换行字符串：“\r\n”
			StringBuilder constants = getFromOpenedFileConstants(fileName);
			if (constants != null) {
				newWriter.append(constants.toString());
				//
				newWriter.flush();
			}
		} catch (IOException e) {
		}

	}

	/**
	 * 实际读日志文件。（指定文件不存在则返回提示信息。）
	 */
	private static String readLogFile(String fileName) {

		StringBuilder logFileAbsolutePath = new StringBuilder();
		logFileAbsolutePath.append(folderOfLogFile);
		logFileAbsolutePath.append(File.separator);
		logFileAbsolutePath.append(fileName.trim());
		logFileAbsolutePath
				.append(logFileSuffix == null || logFileSuffix.length() == 0 || !logFileSuffix.trim().startsWith(".", 0)
						? defaultLogFileSuffix : logFileSuffix.trim());

		StringBuilder fileConstants = new StringBuilder();

		if (fileName != null) {
			try {

				FileInputStream readFile = new FileInputStream(logFileAbsolutePath.toString());

				InputStreamReader inputStreamReader = new InputStreamReader(readFile);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

				String start = bufferedReader.readLine();
				if (start != null) {
					fileConstants.append(start + "\r\n");
				}

				while (start != null) {
					start = bufferedReader.readLine();
					if (start != null) {
						fileConstants.append(start + "\r\n");
					}
				}

				// 关闭操作
				bufferedReader.close();
				inputStreamReader.close();
				readFile.close();

			} catch (FileNotFoundException e) {
				fileConstants.append("指定日志文件不存在<或>日志功能被禁用， 请检查配置文件中<日志目录>是否配置正确。");
			} catch (IOException e) {

			}
		}

		return fileConstants.toString();
	}

	/**
	 * 判断指定文件是否存在
	 * 
	 * @param fileName
	 * @return
	 */
	private static Boolean isFileExist(String fileName) {

		StringBuilder logFileAbsolutePath = new StringBuilder();
		logFileAbsolutePath.append(folderOfLogFile);
		logFileAbsolutePath.append(File.separator);
		logFileAbsolutePath.append(fileName.trim());
		logFileAbsolutePath
				.append(logFileSuffix == null || logFileSuffix.length() == 0 || !logFileSuffix.trim().startsWith(".", 0)
						? defaultLogFileSuffix : logFileSuffix.trim());

		return new File(logFileAbsolutePath.toString()).exists();

	}

	/**
	 * 用于获取指定名称的日志文件的内容。
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getLogContents(String fileName) {

		if (getFromOpenedFileConstants(fileName) == null) { // 如果指定的日志文件未缓存，则尝试读取。

			return readLogFile(fileName);
		}

		return getFromOpenedFileConstants(fileName).toString();
	}

	/**
	 * 实际写入单个日志文件。（请求执行完毕时调用）
	 */
	public static void flushLogFileConstants(String fileName) {
		writeLogFile(fileName, getFromOpenedFileConstants(fileName).toString());
		removeFromOpenedFileConstants(fileName);
	}

	/**
	 * 实际写入所有日志文件。（web应用关闭时调用）
	 */
	public static void flushAllLogFileConstants() {

		for (String fileName : openedFileConstants.keySet()) {
			writeLogFile(fileName, getFromOpenedFileConstants(fileName).toString());
		}
	}

}
