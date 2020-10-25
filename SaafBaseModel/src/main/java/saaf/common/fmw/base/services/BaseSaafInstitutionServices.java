package saaf.common.fmw.base.services;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafInstitutionEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafInstitutionEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafInstitution;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.services.CommonAbstractServices;


/*===========================================================+
  |   Copyright (c) 2012 赛意信息科技有限公司                                          |
  |==========================================================+
  |  HISTORY                                                                        |
  | ============ ====== ============  ===========================                   |
  |  Date                     Ver.                                 Content          |
  | ============ ====== ============  ===========================                   |
  |  Aug 20, 2016            1.0          创建                       Creation        |
  |  Desc:用来处理组织维护
 +===========================================================*/

@Component("baseSaafInstitutionServices")
@Path("/saafInstitutionServlet")
public class BaseSaafInstitutionServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSaafInstitutionServices.class);
    @Autowired
    private ISaafInstitution iSaafInstitution;

    public BaseSaafInstitutionServices() {
        super();
    }

    /**
     * LOV:查询组织机构名称
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @POST
    @Path("findInstNameLov")
    public String findInstNameLov(@FormParam("params")
                                          String params, @FormParam("pageIndex")
                                          Integer pageIndex, @FormParam("pageRows")
                                          Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SaafInstitutionEntity_HI_RO> instLovlist = iSaafInstitution.findInstNameLov(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(instLovlist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询组织机构名称异常！" + e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "查询组织机构名称失败!", 0, null);
        }
    }

    /**
     * 查询组织机构列表
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @POST
    @Path("findInstList")
    public String findInstList(@FormParam("params")
        String params, @FormParam("pageIndex")   @DefaultValue("1")
        Integer pageIndex, @FormParam("pageRows")  @DefaultValue("20")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SaafInstitutionEntity_HI_RO> instlist = iSaafInstitution.findInstitution(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(instlist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询组织机构失败！" + e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "查询组织机构失败!", 0, null);
        }
    }

    /**
     * 查询组织机构
     *
     * @param params
     * @return
     */
    @POST
    @Path("findInstLine")
    public String findInstLine(@FormParam("params")
                                       String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("instId", jsonParam.getInteger("instId"));
            SaafInstitutionEntity_HI instLine = iSaafInstitution.findInstitutionLine(map);
            return CommonAbstractServices.convertResultJSONObj(SUCCESS_STATUS, "查询组织机构成功！", 1, instLine);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询组织机构失败！" + e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "查询组织机构失败!" + e, 0, null);
        }
    }

    /**
     * 保存组织机构
     *
     * @param params
     * @return
     */
    @POST
    @Path("saveInst")
    public String saveInst(@FormParam("params")
                                   String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject instJson = iSaafInstitution.saveInstitution(jsonParam);
            if (ERROR_STATUS.equals(instJson.get(STATUS))) {
                return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, instJson.getString(MSG), 0, null);
            } else {
                return CommonAbstractServices.convertResultJSONObj(SUCCESS_STATUS, "保存组织机构成功！", instJson.getInteger(COUNT), instJson.get(DATA));
            }
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("保存组织机构失败！" + e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "保存组织机构失败!" + e, 0, null);
        }
    }

    /**
     * 删除组织机构ById
     *
     * @param params
     * @return
     */
    @POST
    @Path("deleteInst")
    public String deleteInst(@FormParam("params")
                                     String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Integer instId = jsonParam.getInteger("instId");
            JSONObject instJson = iSaafInstitution.deleteInstitution(instId);
            return instJson.toString();
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("保存组织机构失败！" + e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "保存组织机构失败!" + e, 0, null);
        }
    }

    /**
     * add by luofenwu
     * 根据用户ID查询组织机构(所属中心)列表
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @POST
    @Path("findInstListByUserId")
    public String findInstListByUserId(@FormParam("params")
                                               String params, @FormParam("pageIndex")
                                               Integer pageIndex, @FormParam("pageRows")
                                               Integer pageRows) {
        try {
            JSONObject json = new JSONObject();
            json.put("userId", this.parseObject(null).get("varUserId"));
            Pagination<SaafInstitutionEntity_HI_RO> instlist = iSaafInstitution.findSaafInstitutionByUserId(json, pageIndex, pageRows);
            return JSONObject.toJSONString(instlist);
        } catch (Exception e) {
            LOGGER.error("根据用户ID查询组织机构失败！" + e);
            return null;
        }
    }

    /**
     * LOV:srm查询组织
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @POST
    @Path("queryInstitution")
    public String queryInstitution(@FormParam("params")
                                           String params, @FormParam("pageIndex")
                                           Integer pageIndex, @FormParam("pageRows")
                                           Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SaafInstitutionEntity_HI_RO> instLovlist = iSaafInstitution.queryInstitution(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(instLovlist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询组织机构名称异常！" + e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "查询组织机构名称失败!", 0, null);
        }
    }

    /**
     * LOV:srm查询业务实体Lov
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @POST
    @Path("findInstitutionListLov")
    public String findInstitutionListLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        String flag = request.getParameter("flag");
        LOGGER.info("flag-->>参数：" + flag + "！");
        try {
            JSONObject jsonParams = this.parseObject(params);

            Pagination<SaafInstitutionEntity_HI_RO> pag = iSaafInstitution.findInstitutionListLov(jsonParams, pageIndex, pageRows);
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("查询失败" + e, e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e, 0, null);
        }
    }

    /**
     * srm根据物料查询组织
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @POST
    @Path("queryItemInstitution")
    public String queryItemInstitution(@FormParam("params")
                                               String params, @FormParam("pageIndex")
                                               Integer pageIndex, @FormParam("pageRows")
                                               Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SaafInstitutionEntity_HI_RO> instLovlist = iSaafInstitution.queryItemInstitution(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(instLovlist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询组织机构名称异常！" + e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "查询组织机构名称失败!", 0, null);
        }
    }

    /**
     * 查询当前登录用户的组织表的业务实体/库存组织的权限范围（分页）
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @POST
    @Path("findInstitutionListByUserId")
    public String findInstitutionListByUserId(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info("参数：" + params.toString());
        try {
            JSONObject jsonParams = this.parseObject(params);
            Pagination<SaafInstitutionEntity_HI_RO> result = iSaafInstitution.findInstitutionListByUserId(jsonParams, pageIndex, pageRows);
            return JSON.toJSONString(result);
        } catch (Exception e) {
            LOGGER.error("查询失败" + e, e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
        }
    }

    /**
     * 查询指定业务实体下的库存组织
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @POST
    @Path("queryInstitutionByOrg")
    public String queryInstitutionByOrg(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info("参数：" + params.toString());
        try {
            JSONObject jsonParams = this.parseObject(params);
            Pagination<SaafInstitutionEntity_HI_RO> result = iSaafInstitution.queryInstitutionByOrg(jsonParams, pageIndex, pageRows);
            return JSON.toJSONString(result);
        } catch (Exception e) {
            LOGGER.error("查询失败" + e, e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e, 0, null);
        }
    }

    /**
     * 查询有效的部门信息
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @POST
    @Path("findInstitutionByDept")
    public String findInstitutionByDept(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info("findInstitutionByDept参数：" + params);
        try {
            JSONObject jsonParams = this.parseObject(params);
            Pagination<SaafInstitutionEntity_HI_RO> pag = iSaafInstitution.findInstitutionByDept(jsonParams, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e, 0, null);
        }
    }

}