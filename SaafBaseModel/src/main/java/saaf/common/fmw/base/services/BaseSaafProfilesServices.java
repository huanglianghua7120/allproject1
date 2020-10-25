package saaf.common.fmw.base.services;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafProfilesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafProfiles;
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
  |  Desc:用来处理配置文件维护
 +===========================================================*/

@Component("baseSaafProfilesServices")
@Path("/saafProfilesServlet")
public class BaseSaafProfilesServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSaafProfilesServices.class);
    @Autowired
    private ISaafProfiles baseSaafProfilesServer;

    public BaseSaafProfilesServices() {
        super();
    }

    /**
     * 查询配置文件值(公共方法)
     *
     * @param params
     * @return
     */
    @Path("findProfileValues")
    @POST
    public String findProfileValues(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            //            ISaafProfiles saafProfilesServer = (ISaafProfiles) this.getServerBean("baseSaafProfilesServer");
            Pagination profilesValues = baseSaafProfilesServer.findProfileValues(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "查询配置文件值成功!", profilesValues.getCount(), profilesValues.getData());
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询配置文件值失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询配置文件值失败!" + e, 0, null);
        }
    }


    /**
     * 查询配置文件值（公共方法）
     *
     * @param params 参数编码
     * @return
     * @author lag
     * @date 2017-02-13
     */
    @Path("findProfileValuesNew")
    @POST
    public String findProfileValuesNew(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            //            ISaafProfiles saafProfilesServer = (ISaafProfiles) this.getServerBean("baseSaafProfilesServer");
            String profileNumber = jsonParam.getString("profileNumber");
            String value = baseSaafProfilesServer.findProfileValueByProNum(profileNumber);
            return CommonAbstractServices.convertResultJSONObj("S", "查询配置文件值成功!", 0, value);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询配置文件值失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询配置文件值失败!" + e, 0, null);
        }
    }

    /**
     * 查询配置文件列表
     *
     * @param params
     * @return
     */
    @Path("findSaafProfilesList")
    @POST
    public String findSaafProfilesList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //            ISaafProfiles saafProfilesServer = (ISaafProfiles) this.getServerBean("baseSaafProfilesServer");
            Pagination profilesList = baseSaafProfilesServer.findSaafProfilesList(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(profilesList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询配置文件列表失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询配置文件列表失败!" + e, 0, null);
        }
    }

    /**
     * 查询配置文件行
     *
     * @param params
     * @return
     */
    @Path("findSaafProfilesLine")
    @POST
    public String findSaafProfilesLine(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //            ISaafProfiles saafProfilesServer = (ISaafProfiles) this.getServerBean("baseSaafProfilesServer");
            SaafProfilesEntity_HI_RO profilesLine = baseSaafProfilesServer.findSaafProfilesLine(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "查询配置文件成功!", 1, profilesLine);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询配置文件列表失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询配置文件失败!" + e, 0, null);
        }
    }

    /**
     * 查询配置文件列表
     *
     * @param params
     * @return
     */
    @Path("findProfileValue")
    @POST
    public String findProfileValue(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //            ISaafProfiles saafProfilesServer = (ISaafProfiles) this.getServerBean("baseSaafProfilesServer");
            Pagination profilesList = baseSaafProfilesServer.findProfileValue(jsonParam, pageIndex, pageRows);
            return CommonAbstractServices.convertResultJSONObj("S", "查询配置文件列表成功!", profilesList.getCount(), profilesList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询配置文件列表失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询配置文件列表失败!" + e, 0, null);
        }
    }

    /**
     * 保存配置文件
     *
     * @param params
     * @return
     */
    @Path("saveSaafProfiles")
    @POST
    public String saveSaafProfiles(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //            ISaafProfiles saafProfilesServer = (ISaafProfiles) this.getServerBean("baseSaafProfilesServer");
            JSONObject profiles = baseSaafProfilesServer.saveProfilesInfo(jsonParam);
            if ("E".equals(profiles.getString("status"))) {
                return CommonAbstractServices.convertResultJSONObj("E", profiles.getString("msg"), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("S", "保存配置文件成功!", 1, profiles.get("data"));
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("保存配置文件失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存配置文件失败!" + e.getMessage(), 0, null);
        }
    }
}
