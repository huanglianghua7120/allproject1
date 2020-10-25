package saaf.common.fmw.prc.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import saaf.common.fmw.prc.model.inter.IMappingItems;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Component("mappingItemsServices")
@Path("/mappingItemsServlet")
public class SrmPrcMappingItemsServices extends CommonAbstractServices {


    @Autowired
    private IMappingItems mappingItemsServer;

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPrcMappingItemsServices.class);

    @Path("findByItems")
    @POST
    public String findByMappingHeadreId(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            return JSON.toJSONString(mappingItemsServer.findByMappingHeadreId(jsonParam, pageIndex, pageRows));
        } catch (Exception e) {
            ////e.printStackTrace();
            LOGGER.error("查询行异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询行失败：" + e.getMessage(), 0, null);
        }

    }

    @Path("findMappingItemsPage")
    @POST
    public String findMappingItemsPage(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {

        try {
            JSONObject jsonParam = this.parseObject(params);
            return JSON.toJSONString(mappingItemsServer.findMappingItemsPage(jsonParam, pageIndex, pageRows));
        } catch (Exception e) {
            ////e.printStackTrace();
            LOGGER.error("指标关联物料查询：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "指标关联物料查询失败：" + e.getMessage(), 0, null);
        }

    }

    @Path("findMappingItemsLov")
    @POST
    public String findMappingItemsLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {

        try {
            JSONObject jsonParam = this.parseObject(params);
            return JSON.toJSONString(mappingItemsServer.findMappingItemsLov(jsonParam, pageIndex, pageRows));

        } catch (Exception e) {
            ////e.printStackTrace();
            LOGGER.error("指标关联物料lov查询：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "指标关联物料查询失败：" + e.getMessage(), 0, null);
        }
    }

    @Path("findByHeadreId")
    @POST
    public String findByHeadreId(@FormParam(PARAMS) String params) {

        try {
            JSONObject jsonParam = this.parseObject(params);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功", 1, mappingItemsServer.findByHeadreId(jsonParam));
        } catch (Exception e) {
            ////e.printStackTrace();
            LOGGER.error("指标关联物料lov查询：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "指标关联物料查询失败：" + e.getMessage(), 0, null);
        }
    }
    @Path("findbyIndicatorNumber")
    @POST
    public String findbyIndicatorNumber(@FormParam(PARAMS) String params) {

        try {
            JSONObject jsonParam = this.parseObject(params);
            return JSON.toJSONString(mappingItemsServer.findbyIndicatorNumber(jsonParam));
        } catch (Exception e) {
            ////e.printStackTrace();
            LOGGER.error("验证指标编码失败：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
        }
    }

    @Path("findByMappingItemId")
    @POST
    public String findByMappingItemId(@FormParam(PARAMS) String params) {

        try {
            JSONObject jsonParam = this.parseObject(params);
            return JSON.toJSONString(mappingItemsServer.findByMappingItemId(jsonParam));
        } catch (Exception e) {
            ////e.printStackTrace();
            LOGGER.error("查询失败：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败：" + e.getMessage(), 0, null);
        }
    }
    @Path("findByNumbereaderId")
    @POST
    public String findByNumbereaderId(@FormParam(PARAMS) String params) {

        try {
            JSONObject jsonParam = this.parseObject(params);
            return JSON.toJSONString(mappingItemsServer.findByNumbereaderId(jsonParam));
        } catch (Exception e) {
            ////e.printStackTrace();
            LOGGER.error("查询失败：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败：" + e.getMessage(), 0, null);
        }
    }

    @Path("updateMappingHeaderStatus")
    @POST
    public String updateMappingHeaderStatus(@FormParam(PARAMS) String params) {

        try {
            JSONObject jsonParam = this.parseObject(params);
            return JSON.toJSONString(mappingItemsServer.updateMappingHeaderStatus(jsonParam));
        } catch (Exception e) {
            ////e.printStackTrace();
            LOGGER.error("：指标关联物料状态变更失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "指标关联物料状态变更失败：" + e.getMessage(), 0, null);
        }
    }

    @Path("deleteMappingItems")
    @POST
    public String deleteMappingItems(@FormParam(PARAMS) String params) {

        try {
            JSONObject jsonParam = this.parseObject(params);
            return JSON.toJSONString(mappingItemsServer.deleteMappingItems(jsonParam));
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("删除指标失败：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "指标关联物料删除失败：" + e.getMessage(), 0, null);
        }
    }

    @Path("saveItems")
    @POST
    public String saveItems(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            return JSON.toJSONString(mappingItemsServer.saveItems(jsonParam,"DRAFT"));
        } catch (HibernateOptimisticLockingFailureException e) {
            //e.printStackTrace();
            return CommonAbstractServices.convertResultJSONObj("E", "操作失败：操作人数过多，请刷新页面重试!", 0, null);
        } catch (Exception e) {
            LOGGER.error("保存指标失败：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
        }

    }
    @Path("saveMappingItemsList")
    @POST
    public String saveMappingItemsList(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            return JSON.toJSONString(mappingItemsServer.saveItemsList(jsonParam));
        }  catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("保存指标失败：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
        }

    }
}
