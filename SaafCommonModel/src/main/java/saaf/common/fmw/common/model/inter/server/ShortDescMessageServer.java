package saaf.common.fmw.common.model.inter.server;


import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;


@Component("shortDescMessageServer")
public class ShortDescMessageServer {
    @Autowired
    private MessageSource messageSource; // = (MessageSource) SaafToolUtils.context.getBean("messageSource");

    public ShortDescMessageServer() {
        super();
    }

    /**
     * 获取消息
     *
     * @param msgCode
     * @return
     */
    public String getMessage(String msgCode) {
        if (msgCode.equals("SAVE-FAILURE")) { // 如果保存失败 则自动添加为空，防止出现{0}
            return messageSource.getMessage(msgCode, new Object[] { "" }, null);
        }
        return messageSource.getMessage(msgCode, null, null);
    }

    /**
     * 获取消息
     *
     * @param msgCode
     * @param params
     * @return
     */
    public String getMessage(String msgCode, Object[] params) {
        return messageSource.getMessage(msgCode, params, null);
    }

    /**
     * 获取JSON格式消息
     *
     * @param msgCode
     * @return
     */
    public String getJsonResultStr(String msgCode) {
        return getJsonResultStr(msgCode, null);
    }

    /**
     * 获取JSON格式消息
     *
     * @param msgCode
     * @param params
     * @return
     */
    public String getJsonResultStr(String msgCode, Object[] params) {
        return getJsonResult(msgCode, params).toJSONString();
    }

    /**
     * 获取JSON格式消息
     *
     * @param msgCode
     * @return
     */
    public JSONObject getJsonResult(String msgCode) {
        return getJsonResult(msgCode, null);
    }

    /**
     * 获取JSON格式消息
     *
     * @param msgCode
     * @param params
     * @return
     */
    public JSONObject getJsonResult(String msgCode, Object[] params) {
        return SToolUtils.convertResultJSONObj(msgCode, messageSource.getMessage(msgCode, params, null), 0, null);
    }
}
