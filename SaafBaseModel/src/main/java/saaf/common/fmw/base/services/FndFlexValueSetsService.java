package saaf.common.fmw.base.services;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.FndFlexValueSetsEntity_HI;
import saaf.common.fmw.base.model.inter.IFndFlexValidationTables;
import saaf.common.fmw.base.model.inter.IFndFlexValueSets;
import saaf.common.fmw.base.model.inter.IFndFlexValues;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.services.CommonAbstractServices;


@Component("fndFlexValueSetsService")
@Path("/fndFlexValueSetsService")
public class FndFlexValueSetsService extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(FndFlexValueSetsService.class);
    @Context
    protected ServletContext application;

    @Autowired
    private IFndFlexValueSets fndFlexValueSetsServer; // = (IFndFlexValueSets) getServerBean("fndFlexValueSetsServer");
    @Autowired
    private IFndFlexValues fndFlexValuesServer; // = (IFndFlexValueSets)getServerBean("fndFlexValueSetsServer");
    //IFndFlexValues flexValueServer = (IFndFlexValues)getServerBean("fndFlexValuesServer");
    @Autowired
    private IFndFlexValidationTables fndFlexValidationTablesServer; // = (IFndFlexValidationTables)getServerBean("fndFlexValidationTablesServer");

    public FndFlexValueSetsService() {
        super();
    }

    // /restServer/fndFlexValueSetsService/findFndFlexValueSetsInfo

    @POST
    @Path("findFndFlexValueSetsInfo")
    @Consumes("application/json")
    @Produces("application/json")
    public String findFndFlexValueSetsInfo(String postParam) {
        // LOGGER.info(postParam);
        JSONObject paramJSON = JSON.parseObject(postParam);
        //FndFlexValueSetsServer fndFlexValueSetsServer = (FndFlexValueSetsServer)SaafToolUtils.context.getBean("fndFlexValueSetsServer");
        String resultStr = fndFlexValueSetsServer.findFndFlexValueSetsInfo(paramJSON);
        // LOGGER.info(resultStr);
        return resultStr;
    }

    /**
     * 查询值集列表
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @POST
    @Path("findflexValueSet")
    public String findflexValueSet(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            //IFndFlexValueSets flexValueSetServer = (IFndFlexValueSets) getServerBean("fndFlexValueSetsServer");
            Pagination flexValueSetlist = fndFlexValueSetsServer.findFlexValueSets(jsonParams, pageIndex, pageRows);
            LOGGER.info("--->>findflexValueSet-->>参数：" + params + ",pageIndex:" + pageIndex + "pageRows" + pageRows + "查询值集成功！");
            return JSONObject.toJSONString(flexValueSetlist);
        } catch (NotLoginException e) {
            return convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询值集失败" + e);
            return convertResultJSONObj("E", "查询值集失败!" + e, 0, null);
        }
    }

    /**
     * 公用服务，用于根据值集名，查出值集值
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @POST
    @Path("findFlexValueByName")
    public String findFlexValueByName(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            //IFndFlexValueSets flexValueSetServer = (IFndFlexValueSets)getServerBean("fndFlexValueSetsServer");
            Pagination flexValueSetlist = fndFlexValueSetsServer.findFlexValueByName(jsonParams, pageIndex, pageRows);
            LOGGER.info("--->>findflexValueSet-->>参数：" + params + ",pageIndex:" + pageIndex + "pageRows" + pageRows + "查询值集值成功！");
            return JSONObject.toJSONString(flexValueSetlist);
        } catch (NotLoginException e) {
            return convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询值集值失败" + e);
            return convertResultJSONObj("E", "查询值集值失败!" + e, 0, null);
        }
    }

    /**
     * 查询值集明细
     *
     * @param params
     * @return
     */
    @POST
    @Path("findFndFlexValuesLine")
    public String findFndFlexValuesLine(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            //IFndFlexValueSets flexValueSetServer = (IFndFlexValueSets)getServerBean("fndFlexValueSetsServer");
            Integer flexValueSetId = jsonParams.getInteger("flexValueSetId");
            String validationType = jsonParams.getString("validationType");
            FndFlexValueSetsEntity_HI flexValueSetRow = fndFlexValueSetsServer.findFlexValueSetById(flexValueSetId);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("flexValueSetId", flexValueSetId);
            List flexValuesList = null;
            ;
            if ("S".equals(validationType)) {
                //IFndFlexValues flexValueServer = (IFndFlexValues)getServerBean("fndFlexValuesServer");
                flexValuesList = fndFlexValuesServer.findFndFlexValues(map);
            } else if ("T".equals(validationType)) {
                //IFndFlexValidationTables flexValueServer = (IFndFlexValidationTables)getServerBean("fndFlexValidationTablesServer");
                flexValuesList = fndFlexValidationTablesServer.findFndFlexValidation(map);
            }
            JSONObject jsonData = new JSONObject();
            jsonData.put("flexValueSetRow", flexValueSetRow);
            jsonData.put("flexValuesList", flexValuesList);
            return convertResultJSONObj("S", "查询值集成功！", 1, jsonData);
        } catch (NotLoginException e) {
            return convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询值集失败" + e);
            return convertResultJSONObj("E", "查询值集失败!" + e, 0, null);
        }
    }

    /**
     * 删除值集值
     *
     * @param params
     * @return
     */
    @POST
    @Path("deleteFlexValues")
    public String deleteFlexValues(@FormParam("params")
        String params) {
        try {
            //IFndFlexValues flexValueServer = (IFndFlexValues)getServerBean("fndFlexValuesServer");
            JSONObject jsonParams = this.parseObject(params);
            JSONObject json = fndFlexValuesServer.deleteFlexValues(jsonParams);
            if ("E".equals(json.getString("status"))) {
                return convertResultJSONObj("E", json.getString("msg"), 0, null);
            } else {
                return convertResultJSONObj("S", "删除值集值成功!", 1, null);
            }
        } catch (NotLoginException e) {
            return convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("删除值集值失败", e);
            return convertResultJSONObj("E", "删除值集值失败!" + e, 0, null);
        }
    }

    /**
     * 保存值集
     *
     * @param params
     * @return
     */
    @POST
    @Path("saveFlexValueSet")
    public String saveFlexValueSet(@FormParam("params")
        String params) {
        try {
            //IFndFlexValueSets flexValueSetServer = (IFndFlexValueSets)getServerBean("fndFlexValueSetsServer");
            JSONObject jsonParams = this.parseObject(params);
            JSONObject json = fndFlexValueSetsServer.saveFlexValueSet(jsonParams);
            if ("E".equals(json.getString("status"))) {
                return convertResultJSONObj("E", json.getString("msg"), 0, null);
            } else {
                return convertResultJSONObj("S", "保存值集成功!", 1, json.get("data"));
            }
        } catch (NotLoginException e) {
            return convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("保存值集失败", e);
            return convertResultJSONObj("E", "保存值集失败!" + e, 0, null);
        }
    }

    /**
     * 验证SQL
     *
     * @param params
     * @return
     */
    @POST
    @Path("validateSql")
    public String validateSql(@FormParam("params")
        String params) {
        try {
            //IFndFlexValueSets flexValueSetServer = (IFndFlexValueSets)getServerBean("fndFlexValueSetsServer");
            JSONObject jsonParams = this.parseObject(params);
            JSONObject json = fndFlexValueSetsServer.validateSql(jsonParams);
            if ("E".equals(json.getString("status"))) {
                return convertResultJSONObj("E", json.getString("msg"), 0, null);
            } else {
                return convertResultJSONObj("S", "验证SQL成功!", 1, json.get("data"));
            }
        } catch (NotLoginException e) {
            return convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("验证SQL失败", e);
            return convertResultJSONObj("E", "验证SQL失败!" + e, 0, null);
        }
    }

    // /restServer/fndFlexValueSetsService/user

    @GET
    @Path("user")
    @Produces("text/plain")
    public String getUser(@QueryParam("name")
        String name) {
        return "hello " + name;
    }
}
