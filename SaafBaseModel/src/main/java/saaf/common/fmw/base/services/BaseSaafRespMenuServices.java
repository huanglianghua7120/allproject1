package saaf.common.fmw.base.services;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafRespMenuEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafMenuResp;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.services.CommonAbstractServices;


/*===========================================================+
  |   Copyright (c) 2012 赛意信息科技有限公司                                          |
+===========================================================+
  |  HISTORY                                                                        |
  | ============ ====== ============  ===========================                   |
  |  Date                     Ver.        liuwenjun                Content          |
  | ============ ====== ============  ===========================                   |
  |  Aug 20, 2016            1.0          创建                      Creation        |
  |  Desc:用来处理职责维护
 +===========================================================*/
@Component("baseSaafRespMenuServices")
@Path("/saafRespMenuServlet")
public class BaseSaafRespMenuServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSaafRespMenuServices.class);
    @Autowired
    private ISaafMenuResp baseSaafMenuRespServer;

    public BaseSaafRespMenuServices() {
        super();
    }


    /**
     * 职责菜单,功能，按钮（已分配、未分配）
     * @param params
     * @return
     * @throws Exception
     */
    @Path("findMenuFuncBtn")
    @POST
    public String findMenuFuncBtn(@FormParam("params")
        String params) {
        try {
            LOGGER.info("dy,,,findMenuFuncBtn:" + params);
            JSONObject jsonParam = this.parseObject(params);
            //ISaafMenuResp saafMenuRespServer = (ISaafMenuResp)this.getServerBean("baneSaafMenuRespServer");
            Map<String, Object> param = new HashMap<String, Object>();

            param.put("respId", jsonParam.getInteger("respId"));
            param.put("platformCode", jsonParam.getString("platformCode"));

            List<SaafRespMenuEntity_HI_RO> menuTree = baseSaafMenuRespServer.findMenuFuncBtn(param);
            return CommonAbstractServices.convertResultJSONObj("S", "查询菜单成功!", menuTree.size(), menuTree);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询菜单失败:" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询菜单失败!" + e, 0, null);
        }
    }

    /**
     * 查询职责已分配菜单&功能Tree
     * @param params
     * @return
     */
    @Path("findRespMenuORBtn")
    @POST
    public String findRespMenuORBtn(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //baneSaafMenuRespServerISaafMenuResp saafMenuRespServer = (ISaafMenuResp)this.getServerBean("baneSaafMenuRespServer");
            Object[] param = new Object[2];

            param[0] = jsonParam.getString("varPlatformCode");
            param[1] = jsonParam.getInteger("respId");

            List menuTree = baseSaafMenuRespServer.findRespMenuORBtn(param);
            return CommonAbstractServices.convertResultJSONObj("S", "查询职责菜单成功!", menuTree.size(), menuTree);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询职责菜单失败:" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询职责菜单失败!" + e, 0, null);
        }
    }

    /**
     *  保存菜单职责，职责功能关系
     * @param params
     * @return
     */
    @Path("saveRespMenuFunc")
    @POST
    public String saveRespMenuFunc(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //ISaafMenuResp saafMenuRespServer = (ISaafMenuResp)this.getServerBean("baneSaafMenuRespServer");
            JSONObject jsondata = baseSaafMenuRespServer.saveSaafRespMenuBtn(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("职责分配菜单失败:" + e.getMessage() + e);
            return CommonAbstractServices.convertResultJSONObj("E", "职责分配菜单失败!" + e.getMessage() + e, 0, null);
        }
    }
    
    /**
     * 查询不在快捷菜单中的用户菜单
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @POST
	@Path("queryUserAllMenuNotInShortcut")
	@Produces("application/json")
	public String queryUserAllMenuNotInShortcut(@FormParam("params") String params,
			@FormParam("pageIndex") @DefaultValue("1") Integer pageIndex,
			@FormParam("pageRows") @DefaultValue("10") Integer pageSize) {
		LOGGER.info(params);
		
		JSONObject paramJSON = JSON.parseObject(params);
		paramJSON.put("varUserId", this.getUserSessionBean().getUserId());
		String resultStr = baseSaafMenuRespServer.queryUserAllMenuNotInShortcut(paramJSON, pageIndex, pageSize);
		LOGGER.info(resultStr);
		
		return resultStr;
	}
}
