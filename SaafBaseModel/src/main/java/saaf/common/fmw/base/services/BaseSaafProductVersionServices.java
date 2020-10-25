package saaf.common.fmw.base.services;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafProductVersionEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafProductVersion;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.services.CommonAbstractServices;


/**
 * Created by huangtao on 2016/9/9.
 */
@Component("baseSaafProductVersionServices")
@Path("/productVersion")
public class BaseSaafProductVersionServices extends CommonAbstractServices {
    private static final Logger log = LoggerFactory.getLogger(BaseSaafProductVersionServices.class);

    @Autowired
    private ISaafProductVersion saafProductVersionServer;

    @Context
    HttpServletRequest request;

    @Context
    HttpServletResponse response;

    @Context
    UriInfo uriInfo;


    /**
     *
     * @param queryPrameter -productCode
     *   queryPrameter-productName
     *   queryPrameter-updateDate
     *   queryPrameter-enableFlag
     *   queryPrameter-releasePlatform
     *   queryPrameter-forcedDownloadFlag
     * @param nowPage
     * @param pageSize
     * @return
     */
    @POST
    @Path("query")
    @Produces("application/json")
    public String queryProductVersion(@FormParam("params")
        String queryPrameter, @FormParam("pageIndex") @DefaultValue("1")
        Integer nowPage, @FormParam("pageSize") @DefaultValue("10")
        Integer pageSize) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        int userId = getUserSessionBean() == null ? -1 : getUserSessionBean().getUserId();
        JSONObject jsonParams = JSONObject.parseObject(queryPrameter);
        try {
            //SaafProductVersionEntity_HI obj =  StringUtils.isBlank(queryPrameter) ? new SaafProductVersionEntity_HI() : JSONObject.toJavaObject(JSON.parseObject(queryPrameter), SaafProductVersionEntity_HI.class);
            Pagination result = saafProductVersionServer.findProductVersion(jsonParams, nowPage, pageSize, userId);
            return JSON.toJSON(result).toString();
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }


    /**
     *
     * @param queryPrameter -productCode 版本号
     *  queryPrameter-productName 软件名
     *  queryPrameter-enableFlag 是否失效
     *  queryPrameter-forcedDownloadFlag 是否强制下载
     *  queryPrameter-releasePlatform 平台
     *  queryPrameter-versionRemark 版本说明
     *  queryPrameter-downloadUrl 下载地址
     * @return
     */
    @POST
    @Path("add")
    @Produces("application/json")
    public String addProductVersion(@FormParam("params")
        String queryPrameter) {
        int userId = getUserSessionBean() == null ? -1 : getUserSessionBean().getUserId();
        try {

            SaafProductVersionEntity_HI obj =
                StringUtils.isBlank(queryPrameter) ? new SaafProductVersionEntity_HI() : JSONObject.toJavaObject(JSON.parseObject(queryPrameter), SaafProductVersionEntity_HI.class);
            buildSaafProductVersionEntity(obj);
            //ISaafProductVersion saafProductVersionServer = (ISaafProductVersion)SaafToolUtils.context.getBean("saafProductVersionServer");

            if (saafProductVersionServer.uniqueCheck(obj) == false)
                return CommonAbstractServices.convertResultJSONObj("T", "data error", 0, null);
            boolean result = saafProductVersionServer.saveProductVersion(obj, userId);
            if (result)
                return SToolUtils.convertResultJSONObj("S", "success", 1, obj).toString();
        } catch (Exception e) {
            log.error("", e);
        }
        return SToolUtils.convertResultJSONObj("E", "新增失败", 0, null).toJSONString();
    }


    /**
     *
     * @param queryPrameter queryPrameter-productVersionId id(必填)
     * @return
     */
    @POST
    @Path("delete")
    @Produces("application/json")
    public String deleteProductVersion(@FormParam("params")
        String queryPrameter) {
        int userId = getUserSessionBean() == null ? -1 : getUserSessionBean().getUserId();
        try {

            if (StringUtils.isNumeric(queryPrameter) == false)
                throw new Exception("parameter error");
            //            ISaafProductVersion saafProductVersionServer = (ISaafProductVersion)SaafToolUtils.context.getBean("saafProductVersionServer");
            boolean result = saafProductVersionServer.deleteProductVersion(Integer.valueOf(queryPrameter), userId);
            if (result)
                return SToolUtils.convertResultJSONObj("S", "success", 1, result).toJSONString();
        } catch (Exception e) {
            log.error("编辑 SaafProductVersionEntity_HI 失败", e);
        }
        return SToolUtils.convertResultJSONObj("E", "删除失败", 0, null).toJSONString();
    }

    /**
     *
     * @param queryPrameter -productVersionId id
     *  queryPrameter-productCode 版本号
     *  queryPrameter -productName 软件名
     *  queryPrameter-enableFlag 是否失效
     *  queryPrameter-forcedDownloadFlag 是否强制下载
     *  queryPrameter-releasePlatform 平台
     *  queryPrameter-versionRemark 版本说明
     *  queryPrameter-downloadUrl 下载地址
     * @return
     */
    @POST
    @Path("edit")
    @Produces("application/json")
    public String editProductVersion(@FormParam("params")
        String queryPrameter) {
        int userId = getUserSessionBean() == null ? -1 : getUserSessionBean().getUserId();
        try {
            SaafProductVersionEntity_HI obj =
                StringUtils.isBlank(queryPrameter) ? new SaafProductVersionEntity_HI() : JSONObject.toJavaObject(JSON.parseObject(queryPrameter), SaafProductVersionEntity_HI.class);
            if (obj.getProductVersionId() == null)
                throw new Exception("parameter error");
            //            ISaafProductVersion saafProductVersionServer = (ISaafProductVersion)SaafToolUtils.context.getBean("saafProductVersionServer");
            SaafProductVersionEntity_HI result = saafProductVersionServer.editProductVersion(obj, userId);
            if (result != null)
                return SToolUtils.convertResultJSONObj("S", "success", 1, result).toJSONString();
        } catch (Exception e) {
            log.error("修改 SaafProductVersionEntity_HI 失败", e);
        }
        return SToolUtils.convertResultJSONObj("E", "修改失败", 0, null).toJSONString();
    }


    private SaafProductVersionEntity_HI buildSaafProductVersionEntity(SaafProductVersionEntity_HI instance) {
        if (instance == null)
            instance = new SaafProductVersionEntity_HI();
        if (StringUtils.isBlank(instance.getEnableFlag()))
            instance.setEnableFlag(ENABLE_FLAG);
        if (StringUtils.isBlank(instance.getForcedDownloadFlag()))
            instance.setForcedDownloadFlag(FORCEDDOWNLOAD_FLAG);
        if (instance.getDownloadNumber() == null)
            instance.setDownloadNumber(0);
        return instance;
    }


    public static void main(String[] args) {
        String str = "ANDROID";
        System.out.println(JSON.parse(str));
        SaafProductVersionEntity_HI entity_hi = new SaafProductVersionEntity_HI();
        entity_hi.setDownloadUrl("fc");
        entity_hi.setUpdateDate(new Date());
        System.out.println(entity_hi.getDownloadNumber());
        System.out.println(JSONObject.toJSON(entity_hi));
    }


}
