package saaf.common.fmw;

import saaf.common.fmw.base.model.inter.ISaafMessagePush;
import saaf.common.fmw.message.utils.SaafToolUtils;

public class TestMessage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ISaafMessagePush saafMessagePushServer = (ISaafMessagePush) SaafToolUtils.context
				.getBean("saafMessagePushServer");
		String desc = "测试待办描述222";
		String url = "#/home/userMaintenanceDetail/1/";
		String sourceType = "SUPPLIER";
		String personSql = "SELECT saaf_users.USER_ID FROM saaf_users WHERE saaf_users.USER_NAME LIKE '%pingan%'";
		String sourceId = "1";
		
		String content ="<p>sdfdsdfsdfsdf</p>"+"<p>sdfdsdfsdfsdf</p>";
		try {
			saafMessagePushServer.createPushOfNeed(desc, url, personSql, sourceType, sourceId);
			saafMessagePushServer.createPushOfNotice("消息", content, personSql);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
