package saaf.common.fmw.base.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.inter.ISaafShortcut;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.services.CommonAbstractServices;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import saaf.common.fmw.utils.SHA1Util;

@Component("saafShortcutService")
@Path("/saafShortcutService")
public class SaafShortcutService extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaafShortcutService.class);

    @Autowired
    private ISaafShortcut saafShortcutServer;

    public SaafShortcutService() {
        super();
    }

    @POST
    @Path("delete")
    @Produces("application/json")
    public String delete(@FormParam("params") String params) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = JSON.parseObject(params);
            Integer id = paramJSON.getInteger("id");
            saafShortcutServer.deleteShortcut(id);
            return convertResultJSONObj(SUCCESS_STATUS, "删除成功", 0, null);
        } catch (Exception e) {
            LOGGER.error("删除快捷菜单失败:", e);
            return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        }
    }

    @POST
    @Path("save")
    @Produces("application/json")
    public String save(@FormParam("params") String params) {
        LOGGER.info(params);
        JSONObject paramJSON = JSON.parseObject(params);
        paramJSON.put("varUserId", this.getUserSessionBean().getUserId());
        JSONObject retJSON = saafShortcutServer.save(paramJSON);
        return retJSON.toJSONString();
    }

    @POST
    @Path("queryShortcutInUser")
    @Produces("application/json")
    public String queryShortcutInUser(@FormParam("params") String params) {
        LOGGER.info("queryShortcutInUser:" + params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            String varToken = paramJSON.getString("varToken");
            SHA1Util sha1 = new SHA1Util();
            String encryptRespId = sha1.getEncrypt("varRespId:" + paramJSON.get("varRespId"));
            if (varToken != null && varToken.equals(encryptRespId)) {
                return saafShortcutServer.queryShortcutInUser(paramJSON);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
        } catch (Exception e) {
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
        }
    }

    /**
     * @param params
     * @param curIndex
     * @param pageSize
     * @return
     */
    @POST
    @Path("findSaafShortcutInfo")
    @Consumes("application/json")
    @Produces("application/json")
    // /restServer/saafShortcutService/findSaafShortcutInfo
    public String findSaafShortcutInfo(@FormParam("params") String params,
                                       @FormParam("pageIndex") @DefaultValue("1") Integer curIndex,
                                       @FormParam("pageRows") @DefaultValue("10") Integer pageSize) {
        LOGGER.info(params);
        JSONObject paramJSON = JSON.parseObject(params);
        String resultStr = JSONObject.toJSONString(saafShortcutServer
                .findSaafShortcutInfo(paramJSON));
        LOGGER.info(resultStr);
        return resultStr;
    }
}
