package saaf.common.fmw.intf;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class testService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			String params = "params={\"intfType\":\"S0001\",\"userName\":\"U9\",\"data\":[{\"PLAN_DATE\":\"2017-12-01\",\"BRANCH_NAME\":\"201\",\"ITEM_CODE\":\"1002\",\"NEED_QUANTITY\":\"12\",\"NEED_BY_DATE\":\"2017-12-01\",\"SUPPLIER_NUMBER\":\"09099\",\"REMARK\":\"备注\"}]}";
	    System.out.println(params);
		URL url = new URL("http://192.168.6.167:8081/saafbase/restServer/SrmIssueToU9Services/saveData");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		httpConn.setUseCaches(false);
		
		httpConn.setRequestMethod("POST");
//		httpConn.setRequestProperty("Content-Type", "application/vnd.oracle.adf.resourceitem+json");
		httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8 ");
//		httpConn.setRequestProperty("Authorization", "Basic " + authEncoded);

		DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
		dos.write( params.getBytes());
		dos.flush();
		dos.close();
		int resultCode = httpConn.getResponseCode();
		System.out.println(resultCode);
		
		
		InputStream inStream = httpConn.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream,"UTF-8"   ));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		String str = buffer.toString();
		System.out.println(str);

	} catch (Exception e) {
		e.printStackTrace();

	}
	}

}
