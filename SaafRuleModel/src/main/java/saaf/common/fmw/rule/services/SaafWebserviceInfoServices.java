package saaf.common.fmw.rule.services;


import com.alibaba.fastjson.JSON;
import com.yhg.hibernate.core.paging.Pagination;
import net.sf.json.JSONException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.rule.model.entities.SaafWebserviceInfoEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.SaafWebserviceInfoEntity_HI_RO;
import saaf.common.fmw.rule.model.inter.ISaafWebserviceInfoServer;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
import java.util.List;


/**
 * Created by Admin on 2017/7/5.
 */
@Component
@Path("/saafWebserviceInfoServices")
public class SaafWebserviceInfoServices extends CommonAbstractServices{
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleMappingBusinessService.class);

    @Autowired
    private ISaafWebserviceInfoServer saafWebserviceInfoServer;


    /**
     * @param params
     * @param curIndex
     * @param pageSize
     * @return
     */
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
            Pagination<SaafWebserviceInfoEntity_HI_RO> pagination = saafWebserviceInfoServer.find(jsonObject, curIndex, pageSize);
            com.alibaba.fastjson.JSONObject result = (com.alibaba.fastjson.JSONObject)JSON.toJSON(pagination);
            result.put("data", pagination.getData());
            result.put("status", "S");
            return result.toString();
        } catch (Exception e) {
            LOGGER.error("", e);
            return convertResultJSONObj("E",e.getMessage(),0,null);
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
            net.sf.json.JSONObject jsonParam = net.sf.json.JSONObject.fromObject(params);
            if (StringUtils.isBlank(jsonParam.optString("webserviceUrl")))
                return convertResultJSONObj("P","parameter error",0,null).toString();
            if (StringUtils.isBlank(jsonParam.optString("webserviceId"))) {
                List<SaafWebserviceInfoEntity_HI> list = saafWebserviceInfoServer.findByProperty("webserviceCode", jsonParam.optString("webserviceCode"));
                if (list.size() > 0)
                    return convertResultJSONObj("e","webserviceCode 已存在",0,"").toString();
            }
            SaafWebserviceInfoEntity_HI instance = saafWebserviceInfoServer.saveOrUpdate(jsonParam, -1);
            return convertResultJSONObj("S", "success", 1, instance).toString();
        } catch (JSONException e) {
            LOGGER.error("", e);
            return convertResultJSONObj("P","parameter error",0,null).toString();
        } catch (Exception e) {
            LOGGER.error("", e);
            return convertResultJSONObj("E",e.getMessage(),0,null);
        }
    }


    /**
     * @param params ruleMappBusId
     * @return
     */
    @POST
    @Path("delete")
    @Produces("application/json")
    public String delete(@FormParam("params")
        String params) {
        try {
            if (StringUtils.isBlank(params))
                return convertResultJSONObj("P","parameter error",0,null).toString();
            net.sf.json.JSONObject jsonParam = net.sf.json.JSONObject.fromObject(params);
            if (StringUtils.isBlank(jsonParam.optString("id")))
                return convertResultJSONObj("P","parameter error",0,null).toString();
            String[] ids = jsonParam.optString("id", "").split(",");
            for (String id : ids) {
                saafWebserviceInfoServer.delete(Integer.parseInt(id));
            }
            return convertResultJSONObj("S", "success", 1, "").toString();
        } catch (JSONException e) {
            LOGGER.error("", e);
            return convertResultJSONObj("P","parameter error",0,null).toString();
        } catch (Exception e) {
            LOGGER.error("", e);
            return convertResultJSONObj("E",e.getMessage(),0,null);
        }
    }


}
