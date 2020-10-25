package saaf.common.fmw.base.services;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import java.rmi.ServerException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.hibernate.exception.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.inter.ISaafUserGroup;
import saaf.common.fmw.common.model.inter.server.ShortDescMessageServer;
import saaf.common.fmw.services.CommonAbstractServices;


@Component("baseSaafUserGroupServices")
@Path("/saafUserGroupService")
public class BaseSaafUserGroupServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSaafUserGroupServices.class);

    @Autowired
    private ISaafUserGroup saafUserGroupServer;
    @Autowired
    private ShortDescMessageServer shortDescMessageServer;

    public BaseSaafUserGroupServices() {
        super();
    }

    /**
     * 查询用户列表
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Path("findSaafUserGroupList")
    @POST
    public String findSaafUserGroupList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //			ISaafUserGroup saafUserGroupServer = (ISaafUserGroup) this.getServerBean("saafUserGroupServer");
            Pagination usersList = saafUserGroupServer.findSaafUserGroup(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(usersList);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询用户列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询用户列表失败!" + e, 0, null);
        }
    }

    /**
     * 保存用户组信息
     *
     * @param params
     * @return
     */
    @Path("saveSaafUserGroup")
    @POST
    public String saveSaafUserGroup(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject json = saafUserGroupServer.saveSaafUserGroup(jsonParam);
            return json.toJSONString();
        } catch (ServerException se) {
            return shortDescMessageServer.getJsonResultStr("SAVE-FAILURE", new Object[] { se.getMessage() });
        } catch (Exception e) {
            final Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                return shortDescMessageServer.getJsonResultStr("UNIQUE-CONSTRAINT", new Object[] { "用户组编码" });
            }
            LOGGER.error("保存用户组信息异常：" + e);
            return shortDescMessageServer.getJsonResultStr("SAVE-FAILURE", new Object[] { "" });
        }
    }

    /**
     * 多选中心
     */
    @POST
    @Path("findMulLov")
    public String findMulLov(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination lovlist = saafUserGroupServer.findMulLov(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(lovlist);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询组织机构名称异常！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询组织机构名称失败!" + e, 0, null);
        }
    }
}
