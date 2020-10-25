package saaf.common.fmw.rule.services;


import com.alibaba.fastjson.JSON;
import com.yhg.hibernate.core.paging.Pagination;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.rule.model.entities.PageModelGroupDetailEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.PageModelGroupDetailEntity_HI_RO;
import saaf.common.fmw.rule.model.inter.IPageModelGroupDetailServer;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;


/**
 * Created by Administrator on Fri Jul 07 17:28:51 CST 2017
 */
@Component
@Path("/pageModelGroupDetailService")
public class PageModelGroupDetailService extends CommonAbstractServices{
    private static final Logger LOGGER = LoggerFactory.getLogger(PageModelGroupDetailService.class);

    @Autowired
    private IPageModelGroupDetailServer pageModelGroupDetailServer;

    @POST
    @Path("query")
    @Produces("application/json")
    public String query(@FormParam("params")
        String params, @FormParam("pageIndex")
        @DefaultValue("1")
        Integer curIndex, @FormParam("pageRows")
        @DefaultValue("10")
        Integer pageSize) {
        try {
            com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
            if (StringUtils.isNotBlank(params)) {
                jsonObject = com.alibaba.fastjson.JSONObject.parseObject(params);
            }
            Pagination<PageModelGroupDetailEntity_HI_RO> pagination = pageModelGroupDetailServer.find(jsonObject, curIndex, pageSize);
            com.alibaba.fastjson.JSONObject result = (com.alibaba.fastjson.JSONObject)JSON.toJSON(pagination);
            result.put("data", pagination.getData());
            result.put("status", "S");
            return result.toString();
        } catch (Exception e) {
            LOGGER.error("", e);
            return convertResultJSONObj("E",e.getMessage(),0,null).toString();
        }
    }

    @POST
    @Path("saveOrUpdate")
    @Produces("application/json")
    public String saveOrUpdate(@FormParam("params")
        String params) {
        try {
            if (StringUtils.isBlank(params))
                return convertResultJSONObj("P","parameter error",0,null).toString();
            JSONObject jsonParam = JSONObject.fromObject(params);
            //if (StringUtils.isEmpty(jsonParam.optString("ruleBusinessLineCode")))
            //return convertResultJSONObj("P","parameter error",0,null).toString();
            PageModelGroupDetailEntity_HI instance = pageModelGroupDetailServer.saveOrUpdate(jsonParam, -1);
            return convertResultJSONObj("S", "success", 1, instance).toString();
        } catch (JSONException e) {
            LOGGER.error("", e);
            return convertResultJSONObj("P","parameter error",0,null).toString();
        } catch (Exception e) {
            LOGGER.error("", e);
            return convertResultJSONObj("E",e.getMessage(),0,null).toString();
        }
    }

    @POST
    @Path("delete")
    @Produces("application/json")
    public String delete(@FormParam("params")
        String params) {
        if (StringUtils.isBlank(params))
            return convertResultJSONObj("P","parameter error",0,null).toString();
        try {
            net.sf.json.JSONObject jsonParam = net.sf.json.JSONObject.fromObject(params);
            if (StringUtils.isBlank(jsonParam.optString("id")))
                return convertResultJSONObj("P","parameter error",0,null).toString();
            String[] ids = jsonParam.optString("id", "").split(",");
            for (String id : ids) {
                pageModelGroupDetailServer.delete(Integer.parseInt(id));
            }
            return convertResultJSONObj("S", "success", 1, "").toString();
        } catch (JSONException e) {
            LOGGER.error("", e);
            return convertResultJSONObj("P","parameter error",0,null).toString();
        } catch (Exception e) {
            LOGGER.error("", e);
            return convertResultJSONObj("E",e.getMessage(),0,null).toString();
        }
    }

}
