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

import saaf.common.fmw.base.model.inter.ISaafInstitution;
import saaf.common.fmw.base.model.inter.ISaafUsersInst;
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
@Component("baseSaafUserInstServices")
@Path("/saafUserInstServlet")
public class BaseSaafUserInstServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSaafUserInstServices.class);
    @Autowired
    private ISaafInstitution baseSaafInstitutionServer;
    @Autowired
    private ISaafUsersInst baseSaafUsersInstServer;

    public BaseSaafUserInstServices() {
        super();
    }

    /**
     * 查询组织机构树
     * @param params
     * @return
     */
    @Path("findInstTree")
    @POST
    public String findInstList(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //            ISaafInstitution saafInstitutionServer = (ISaafInstitution)this.getServerBean("baseSaafInstitutionServer");
            List instlist = baseSaafInstitutionServer.findInstNameTree(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "查询组织机构树成功！", instlist.size(), instlist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询组织机构树失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询组织机构树失败!" + e, 0, null);
        }
    }

    /**
     * 查询组织下所有用户
     * @param params
     * @return
     */
    @Path("findInstUsers")
    @POST
    public String findInstUsers(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //ISaafUsersInst saafInstUserServer = (ISaafUsersInst)this.getServerBean("baseSaafUsersInstServer");
            List instUserlist = baseSaafUsersInstServer.findInstUsers(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "查询组织机构树成功！", instUserlist.size(), instUserlist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询组织机构树失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询组织机构树失败!" + e, 0, null);
        }
    }

    /**
     * 查询用用户属于所有组织树结构
     * @param params
     * @return
     */
    @Path("findUsersInstTree")
    @POST
    public String findUsersInstTree(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //ISaafUsersInst saafInstUserServer = (ISaafUsersInst)this.getServerBean("baseSaafUsersInstServer");
            List instUserlist = baseSaafUsersInstServer.findUsersInstTree(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "查询组织机构树成功！", instUserlist.size(), instUserlist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询组织机构树失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询组织机构树失败!" + e, 0, null);
        }
    }

    /**
     * 查询用用户自己所有的组织
     * @param params
     * @return
     */
    @Path("findUsersInstSelf")
    @POST
    public String findUsersInstSelf(@FormParam("params")
        String params, @FormParam("pageIndex")
        int pageIndex, @FormParam("pageRows")
        int pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //ISaafUsersInst saafInstUserServer = (ISaafUsersInst)this.getServerBean("baseSaafUsersInstServer");
            Pagination instUserlist = baseSaafUsersInstServer.findUsersInstByself(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(instUserlist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询组织机构失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询组织机构失败!" + e, 0, null);
        }
    }

    /**
     * LOV：给用户分配组织{不显示已分配的组织)
     * @param params
     * @return
     */
    @Path("findRemainderInst")
    @POST
    public String findRemainderInst(@FormParam("params")
        String params, @FormParam("pageIndex")
        int pageIndex, @FormParam("pageRows")
        int pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //ISaafUsersInst saafInstUserServer = (ISaafUsersInst)this.getServerBean("baseSaafUsersInstServer");
            Pagination RemainderInst = baseSaafUsersInstServer.findRemainderInst(jsonParam, pageIndex, pageRows);
            return CommonAbstractServices.convertResultJSONObj("S", "查询组织机构成功！", RemainderInst.getData().size(), RemainderInst);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询组织机构失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询组织机构失败!" + e, 0, null);
        }
    }

    /**
     * LOV：给组织分配用户{不显示当前组织已分配的用户)
     * @param params
     * @return
     */
    @Path("findRemainderUser")
    @POST
    public String findRemainderUser(@FormParam("params")
        String params, @FormParam("pageIndex")
        int pageIndex, @FormParam("pageRows")
        int pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //ISaafUsersInst saafInstUserServer = (ISaafUsersInst)this.getServerBean("baseSaafUsersInstServer");
            Pagination RemainderUser = baseSaafUsersInstServer.findRemainderUser(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(RemainderUser);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询用户信息失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询用户信息失败!" + e, 0, null);
        }
    }

    /**
     * 保存用户和组织关系
     * @param params
     * @return
     */
    @Path("saveUsersInst")
    @POST
    public String saveUsersInst(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //ISaafUsersInst saafInstUserServer = (ISaafUsersInst)this.getServerBean("baseSaafUsersInstServer");
            jsonParam.put("varUserId", this.getSessionUserId());
            JSONObject instUserIson = baseSaafUsersInstServer.saveUserInsts(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(instUserIson.getString("status"), instUserIson.getString("msg"), instUserIson.getInteger("count"),
                                                               instUserIson.get("data"));
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("保存用户组织关系失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存用户组织关系失败!" + e, 0, null);
        }
    }

    /**
     * 保存组织和用户关系
     * @param params
     * @return
     */
    @Path("saveInstUsers")
    @POST
    public String saveInstUsers(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //ISaafUsersInst saafInstUserServer = (ISaafUsersInst)this.getServerBean("baseSaafUsersInstServer");
            jsonParam.put("varUserId", this.getSessionUserId());
            JSONObject instUserIson = baseSaafUsersInstServer.saveInstUsers(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(instUserIson.getString("status"), instUserIson.getString("msg"), instUserIson.getInteger("count"),
                                                               instUserIson.get("data"));
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("保存用户组织关系失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存用户组织关系失败!" + e, 0, null);
        }
    }

    /**
     * 删除用户和组织关系
     * @param params
     * @return
     */
    @Path("deleteUsersInst")
    @POST
    public String deleteUsersInst(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //ISaafUsersInst saafInstUserServer = (ISaafUsersInst)this.getServerBean("baseSaafUsersInstServer");
            baseSaafUsersInstServer.deleteUserInst(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "删除成功！", 0, null);

        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("删除用户组织关系失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除用户组织关系失败!" + e, 0, null);
        }
    }

    /**
     * 删除组织和用户关系
     * @param params
     * @return
     */
    @Path("deleteInstUser")
    @POST
    public String deleteInstUser(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //ISaafUsersInst saafInstUserServer = (ISaafUsersInst)this.getServerBean("baseSaafUsersInstServer");
            baseSaafUsersInstServer.deleteInstUser(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "删除成功！", 0, null);

        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("删除用户组织关系失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除用户组织关系失败!" + e, 0, null);
        }
    }

    @Path("delete")
    @POST
    public String delete(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //ISaafUsersInst saafInstUserServer = (ISaafUsersInst)this.getServerBean("baseSaafUsersInstServer");
            List instUserlist = baseSaafUsersInstServer.findUserInsts(jsonParam.getInteger("userId"));
            return CommonAbstractServices.convertResultJSONObj("S", "删除成功！", instUserlist.size(), instUserlist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("删除失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败!" + e, 0, null);
        }
    }
}
