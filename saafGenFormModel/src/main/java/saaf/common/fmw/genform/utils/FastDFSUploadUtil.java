package saaf.common.fmw.genform.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;
import com.yhg.fastdfs.core.bean.FastDFSFileEntity;
import com.yhg.fastdfs.core.bean.ResutlFileEntity;
import com.yhg.fastdfs.core.utils.FileManagerUtils;

public class FastDFSUploadUtil {
	private static final Logger log = LoggerFactory
			.getLogger(FastDFSUploadUtil.class);
	private static final String configFilePath = "/fdfs_client.properties";
	private FileManagerUtils fileManager = FileManagerUtils
			.getInstance(configFilePath);

	public FastDFSUploadUtil() {
		super();
	}

	/**
	 * 上传至文件服务器
	 * 
	 * @param filename
	 *            文件名
	 * @param stream
	 *            输入流
	 * @return
	 */
	public JSONObject uploadFile(String filename, InputStream stream) {
		List<ResutlFileEntity> resultData = new ArrayList<ResutlFileEntity>();
		try {
			String ext = filename.substring(filename.lastIndexOf(".") + 1);
			FastDFSFileEntity DFSFileEntity = new FastDFSFileEntity(filename,
					InputStreamToByte(stream), ext);
			ResutlFileEntity resutlFileEntity = fileManager
					.uploadFile2FastDFS(DFSFileEntity);
			resultData.add(resutlFileEntity);
		} catch (Exception e) {
			log.error("", e);
			return fail(e.getMessage());
		}
		return success("success", resultData);
	}

	/**
	 * 从文件服务器中删除文件
	 * @param groupName 组名
	 * @param remoteFileName 远程文件名
	 * @return
	 */
	public JSONObject delete(String groupName,String remoteFileName) {
		if (StringUtils.isBlank(groupName)
				|| StringUtils.isBlank(remoteFileName))
			return fail("parameter error");
		try {
			FileManagerUtils fileManager = FileManagerUtils
					.getInstance(configFilePath);
			ResutlFileEntity resutlFileEntity = fileManager.deleteFileFromFastDSF(groupName, remoteFileName);
			return success("success", resutlFileEntity);
		} catch (Exception e) {
			log.error("", e);
			return fail(e.getMessage());
		}
	}

	/**
	 * 将InputStream转换成byte数组
	 *
	 * @param in
	 *            InputStream
	 * @return byte[]
	 * @throws IOException
	 */
	private byte[] InputStreamToByte(InputStream in) throws IOException {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[2048];
		int count = -1;
		while ((count = in.read(data, 0, 2048)) != -1)
			outStream.write(data, 0, count);

		data = null;
		return outStream.toByteArray();
	}

	private JSONObject success(String message, Object data) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("staus", "S");
		jsonObject.put("msg", message);
		jsonObject.put("data", data);
		return jsonObject;
	}

	private JSONObject fail(String message) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("staus", "E");
		jsonObject.put("msg", message);
		return jsonObject;
	}

	public static void main(String[] args) throws IOException {
		FastDFSUploadUtil util = new FastDFSUploadUtil();
		//InputStream stream = new FileInputStream(new File("/Users/Rocky/Documents/Project/sie/workspace/GenFormApp/WebContent/genform/gen/test.txt"));
//		JSONObject obj = util.uploadFile("test.txt", stream);
		JSONObject obj = util.delete("group1", "M00/00/00/rBIxPlmSWXWABZhXAAAiUXCVGhQ68.html");
		System.out.println(obj);
	}
	
}
