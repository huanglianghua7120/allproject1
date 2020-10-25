package saaf.common.fmw.base.services;


import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.model.inter.ISaafMessagePush;
import saaf.common.fmw.common.model.inter.server.ShortDescMessageServer;
import saaf.common.fmw.services.CommonAbstractServices;


@Path("/saafMessagePushService")
@Component
public class BaseSaafMessagePushService extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSaafMessagePushService.class);

    @Autowired
    private ISaafMessagePush saafMessagePushServer;

    @Autowired
    private ShortDescMessageServer shortDescMessageServer;

    public BaseSaafMessagePushService() {
        super();
    }

    /**
     * 查询已选着的用户(库存查询权限)
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @POST
    @Path("findBySelf")
    public String findBySelf(@FormParam(PARAMS)
        String params, @FormParam(PAGE_INDEX)
        Integer pageIndex, @FormParam(PAGE_ROWS)
        Integer pageRows) {
        Pagination resultStr = null;
        try {
            JSONObject paramJSON = this.parseObject(params);
            resultStr = saafMessagePushServer.findBySelf(paramJSON, pageIndex, pageRows);
        } catch (Exception e) {
            LOGGER.error("查询用户的推送信息" + "失败" + e);
            return shortDescMessageServer.getJsonResultStr("QUERY-FAILURE", new Object[] { e.getMessage() });
        }
        return JSONObject.toJSONString(resultStr);
    }

    /**
     * 设置为已读状态
     *
     * @param params
     * @return
     */
    @POST
    @Path("setReaded")
    public String setReaded(@FormParam(PARAMS)
        String params) {
        LOGGER.info(params);

        try {
            JSONObject paramJSON = this.parseObject(params);
            //ISaafMessagePush server = (ISaafMessagePush) getServerBean("saafMessagePushServer");

            boolean resultStr = saafMessagePushServer.setReaded(paramJSON);
            return CommonAbstractServices.convertResultJSONObj("S", "", /*SaafToolUtils.getMessage("SAVE-SUCCESS")*/1, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("设置为已读状态异常" + e);
            return shortDescMessageServer.getJsonResultStr("OPERATION-FAILURE", new Object[] { e.getMessage() });
        }


    }

    /**
     * 推送待办测试
     *
     * @param params
     * @return
     */
    @POST
    @Path("pushToUser")
    public String pushToUser(@FormParam(PARAMS)
        String params) {

        try {
            JSONObject paramJSON = this.parseObject(params);
            //ISaafMessagePush server = (ISaafMessagePush) getServerBean("saafMessagePushServer");
            String sql = "SELECT saaf_users.USER_ID FROM saaf_users WHERE saaf_users.USER_NAME LIKE '%sysadmin%'";
            boolean resultStr = saafMessagePushServer.createPushOfNeed("测试", "#/home/userMaintenance", sql, "DINGDAN", "23213123");
            return CommonAbstractServices.convertResultJSONObj("S", "", /*SaafToolUtils.getMessage("SAVE-SUCCESS")*/1, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("设置为已读状态异常" + e);
            return shortDescMessageServer.getJsonResultStr("OPERATION-FAILURE", new Object[] { e.getMessage() });
        }


    }

    /**
     * 推送待办测试
     *
     * @param params
     * @return
     */
    @POST
    @Path("setDone")
    public String setDone(@FormParam(PARAMS)
        String params) {

        try {
            JSONObject paramJSON = this.parseObject(params);
            //ISaafMessagePush server = (ISaafMessagePush) getServerBean("saafMessagePushServer");
            boolean resultStr = saafMessagePushServer.setDone(paramJSON.getString("sourceType"), paramJSON.getString("sourceId"), -1);
            return CommonAbstractServices.convertResultJSONObj("S", "", /*SaafToolUtils.getMessage("SAVE-SUCCESS")*/1, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("设置为已读状态异常" + e);
            return shortDescMessageServer.getJsonResultStr("OPERATION-FAILURE", new Object[] { e.getMessage() });
        }


    }
    
  
}
