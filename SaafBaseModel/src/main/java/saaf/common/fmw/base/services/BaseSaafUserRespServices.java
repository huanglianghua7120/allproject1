package saaf.common.fmw.base.services;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.inter.ISaafUserResp;
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
  |  Desc:用来处理用户维护
 +===========================================================*/
@Component("baseSaafUserRespServices")
@Path("/saafUsersRespServlet")
public class BaseSaafUserRespServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSaafUserRespServices.class);
    @Autowired
    private ISaafUserResp baseSaafUserRespServer;

    public BaseSaafUserRespServices() {
        super();
    }


    /**
     * 查询职责维护列表（用户维护）
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Path("findSaafUsersRespList")
    @POST
    public String findSaafUsersRespList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //            ISaafUserResp saafUsersRespServer = (ISaafUserResp)this.getServerBean("baseSaafUserRespServer");
            Pagination usersRespList = baseSaafUserRespServer.findSaafUserRespList(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(usersRespList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询用户职责列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询用户职责列表失败!" + e, 0, null);
        }
    }

    /**
     * 查询职责所有用户（职责分配用户）
     * @param params
     * @return
     */
    @Path("findSaafUsersResp")
    @POST
    public String findSaafUsersResp(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //ISaafUserResp saafUsersRespServer = (ISaafUserResp)this.getServerBean("baseSaafUserRespServer");
            Pagination usersRespList = baseSaafUserRespServer.findRespUserList(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(usersRespList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询用户职责异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询用户职责失败!" + e, 0, null);
        }
    }

    /**
     * 查询用户所有职责（职责分配用户）
     * @param params
     * @return
     */
    @Path("findUserRespAll")
    @POST
    public String findUserRespAll(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination usersRespList = baseSaafUserRespServer.findUserRespAll(jsonParam);
            return JSON.toJSONString(usersRespList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询用户职责异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询用户职责失败!", 0, null);
        }
    }


    /**
     * 查询职责未分配所有用户（职责分配用户）
     * @param params
     * @return
     */
    @Path("findRespRemaining")
    @POST
    public String findRespRemaining(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination usersRespList = baseSaafUserRespServer.findRespRemaining(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(usersRespList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询用户职责异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询用户职责失败!" + e, 0, null);
        }
    }

    /**
     * LOV：查询未分配给用户的所以职责
     * @param params
     * @return
     */
    @Path("findRemainderUserResp")
    @POST
    public String findRemainderUserResp(@FormParam("params")
        String params, @FormParam("pageIndex")
        int pageIndex, @FormParam("pageRows")
        int pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //ISaafUserResp SaafUserResp = (ISaafUserResp)this.getServerBean("baseSaafUserRespServer");
            Pagination RemainderUserResp = baseSaafUserRespServer.findRemainderUserResp(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(RemainderUserResp);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询职责失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询职责失败!" + e, 0, null);
        }
    }

    /**
     * 保存用户职责
     * @param params
     * @return
     */
    @Path("saveUserResp")
    @POST
    public String saveUserResp(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //ISaafUserResp saafUsersRespServer = (ISaafUserResp)this.getServerBean("baseSaafUserRespServer");
            if (baseSaafUserRespServer.findExisUserRespName(jsonParam)) {
                return CommonAbstractServices.convertResultJSONObj("E", "此职责存在重复用户,不允许保存请检查", 0, null);
            }
            List usersRespList = baseSaafUserRespServer.saveSaafUserResp(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "保存用户职责列表成功!", usersRespList.size(), usersRespList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("保存用户职责列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存用户职责列表失败!" + e, 0, null);
        }
    }

    /**
     * 删除用户职责
     * @param params
     * @return
     */
    @Path("deleteUsersResp")
    @POST
    public String deleteUserResp(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //ISaafUserResp saafUsersRespServer = (ISaafUserResp)this.getServerBean("baseSaafUserRespServer");
            JSONObject jsonData = baseSaafUserRespServer.deleteSaafUserResp(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(jsonData.getString("status"), jsonData.getString("msg"), jsonData.getInteger("count"), null);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("删除用户职责列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除用户职责列表失败!" + e, 0, null);
        }
    }
}
