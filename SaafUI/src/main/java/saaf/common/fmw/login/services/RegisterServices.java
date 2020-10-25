package saaf.common.fmw.login.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.Base64Utils;
import saaf.common.fmw.login.model.inter.IRegister;
import saaf.common.fmw.message.email.utils.SendMailUtil;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/registerServices")
@Component
public class RegisterServices extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterServices.class);

    private static final String PARAMS = "params";

    @Autowired
    private IRegister registerServer;

    public RegisterServices() {
        super();
    }

    /***
     * 自助注册保存
     * @param params
     * @return
     */
    @POST
    @Path("saveRegister")
    public String saveRegister(@FormParam("params") String params) {
        LOGGER.info(params);
        try {
            JSONObject jsonParam = JSON.parseObject(params);
            String contactName = jsonParam.getString("contactName");
            String emailAddress = jsonParam.getString("emailAddress");
            String userName = jsonParam.getString("userName");
            //密码解密
            String password = Base64Utils.decode2PreToString(userName,jsonParam.getString("encryptedPassword"));;
            jsonParam.put("encryptedPassword",password);
            JSONObject json = registerServer.saveRegister(jsonParam);
            //发送注册成功的邮件给联系人
            if ("S".equals(json.getString("status")) && contactName != null && emailAddress != null) {
                String subject = "SRM系统注册成功通知";
                StringBuffer content = new StringBuffer("<p>尊敬的" + contactName + "，您好：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;恭喜您，贵司已成功注册SRM系统！<br/>");
                content.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您注册的登录账号为：" + userName + "，登录密码为：" + password + "。</p>");
                content.append("<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请尽快登录系统，完善公司资料，谢谢！</p>");
                SendMailUtil mailUtil = new SendMailUtil(false);
                mailUtil.doSendHtmlEmail(subject, content.toString(), new String[]{emailAddress});
            }
            return CommonAbstractServices.convertResultJSONObj(json.getString("status"), json.getString("msg"), json.getInteger("count"), json.get("data"));
        } catch (Exception e) {
            return CommonAbstractServices.convertResultJSONObj("E", "注册失败!", 0, null);
        }
    }

}
