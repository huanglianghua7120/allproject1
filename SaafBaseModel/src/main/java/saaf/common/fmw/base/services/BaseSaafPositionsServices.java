package saaf.common.fmw.base.services;


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

import saaf.common.fmw.base.model.entities.readonly.SaafPositionsEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafPositions;
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
  |  Desc:用来处理部门维护
 +===========================================================*/
@Component("baseSaafPositionsServices")
@Path("/saafPositionsServlet")
public class BaseSaafPositionsServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSaafPositionsServices.class);
    @Autowired
    private ISaafPositions baseSaafPositionsServer;

    public BaseSaafPositionsServices() {
        super();
    }

    /**
     * 查询部门LOV
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Path("findPositionLov")
    @POST
    public String findPositionLov(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //            ISaafPositions saafPositionsServer = (ISaafPositions)this.getServerBean("baseSaafPositionsServer");
            Pagination poslov = baseSaafPositionsServer.findPositionsName(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(poslov);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询部门LOV失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询部门LOV失败!" + e, 0, null);
        }
    }

    /**
     * 查询部门列表
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Path("findPositionList")
    @POST
    public String findPositionList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //            ISaafPositions saafPositionsServer = (ISaafPositions)this.getServerBean("baseSaafPositionsServer");
            Pagination poslist = baseSaafPositionsServer.findPositionsList(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(poslist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询部门列表失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询部门列表失败!" + e, 0, null);
        }
    }

    /**
     * 查询部门Line
     * @param params
     * @return
     */
    @Path("findPositionLine")
    @POST
    public String findPositionLine(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("posId", jsonParam.getInteger("posId"));
            //            ISaafPositions saafPositionsServer = (ISaafPositions)this.getServerBean("baseSaafPositionsServer");
            SaafPositionsEntity_HI_RO posLine = baseSaafPositionsServer.findPositionsLine(map);
            return CommonAbstractServices.convertResultJSONObj("S", "查询部门成功！", 1, posLine);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询部门失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询部门失败!" + e, 0, null);
        }
    }

    /**
     * 保存部门
     * @param params
     * @return
     */
    @Path("savePosition")
    @POST
    public String savePosition(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //            ISaafPositions saafPositionsServer = (ISaafPositions)this.getServerBean("baseSaafPositionsServer");
            JSONObject posJson = baseSaafPositionsServer.savePositions(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("保存部门失败！" + e,e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存部门失败!" + e, 0, null);
        }
    }

    /**
     * 删除部门
     * @param params
     * @return
     */
    @Path("deletePosition")
    @POST
    public String deletePosition(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //            ISaafPositions saafPositionsServer = (ISaafPositions)this.getServerBean("baseSaafPositionsServer");
            Integer posId = jsonParam.getInteger("posId");
            JSONObject posJson = baseSaafPositionsServer.deletePositions(posId);
            return posJson.toString();
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("删除部门失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除部门失败!" + e, 0, null);
        }
    }
}
