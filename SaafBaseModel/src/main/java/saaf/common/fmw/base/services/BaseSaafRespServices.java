package saaf.common.fmw.base.services;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafResponsibilitysEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafResponsibilitys;
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
@Component("baseSaafRespServices")
@Path("/saafRespServlet")
public class BaseSaafRespServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSaafRespServices.class);
    @Autowired
    private ISaafResponsibilitys baseSaafResponsibilitysServer;

    public BaseSaafRespServices() {
        super();
    }

    /**
     * 查询职责LOV(公共方法)
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Path("findRespNameLov")
    @POST
    public String findRespNameLov(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination respList = baseSaafResponsibilitysServer.findRespNameLov(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(respList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询职责LOV失败:" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询职责LOV失败!" + e, 0, null);
        }
    }

    /**
     *查询职责列表
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Path("findRespList")
    @POST
    public String findResponsibilityList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination respList = baseSaafResponsibilitysServer.findResponsibilityList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(respList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询职责列表失败:" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询职责列表失败!" + e, 0, null);
        }
    }

    /**
     * 查询职责行
     * @param params
     * @return
     */
    @Path("findRespById")
    @POST
    public String findRespById(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("responsibilityId", jsonParam.getInteger("responsibilityId"));
            //ISaafResponsibilitys saafProfilesServer = (ISaafResponsibilitys)this.getServerBean("baseSaafResponsibilitysServer");
            SaafResponsibilitysEntity_HI resp = baseSaafResponsibilitysServer.findRespById(map);
            return CommonAbstractServices.convertResultJSONObj("S", "查询职责成功!", 1, resp);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询职责失败:" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询职责失败!" + e, 0, null);
        }
    }

    /**
     * 查询有效职责
     * @param params
     * @return
     */
    @Path("findRespist")
    @POST
    public String findRespist(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination resplist = baseSaafResponsibilitysServer.findRespist(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(resplist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询职责失败:" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询职责失败!" + e, 0, null);
        }
    }


    /**
     * 保存职责列表
     * @param params
     * @return
     */
    @Path("saveResp")
    @POST
    public String saveResp(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //ISaafResponsibilitys saafProfilesServer = (ISaafResponsibilitys)this.getServerBean("baseSaafResponsibilitysServer");
            JSONObject respJson = baseSaafResponsibilitysServer.saveResponsibility(jsonParam);
            if ("E".equals(respJson.get("status"))) {
                return CommonAbstractServices.convertResultJSONObj("E", respJson.getString("msg"), 0, null);
            } else {
                return CommonAbstractServices.convertResultJSONObj("S", "保存职责成功!", 1, respJson.get("data"));
            }
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("保存职责失败:" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存职责失败!" + e, 0, null);
        }
    }
}

