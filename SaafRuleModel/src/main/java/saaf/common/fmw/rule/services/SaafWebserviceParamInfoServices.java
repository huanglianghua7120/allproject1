package saaf.common.fmw.rule.services;


import com.alibaba.fastjson.JSON;
import com.yhg.hibernate.core.paging.Pagination;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.rule.model.entities.SaafWebserviceInfoEntity_HI;
import saaf.common.fmw.rule.model.entities.SaafWebserviceParamInfoEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.SaafWebserviceParamInfoEntity_HI_RO;
import saaf.common.fmw.rule.model.inter.ISaafWebserviceInfoServer;
import saaf.common.fmw.rule.model.inter.ISaafWebserviceParamInfoServer;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Admin on 2017/7/5.
 */
@Path("/saafWebserviceParamInfoServices")
@Component
public class SaafWebserviceParamInfoServices extends CommonAbstractServices{

    private static final Logger LOGGER = LoggerFactory.getLogger(RuleMappingBusinessService.class);

    @Autowired
    private ISaafWebserviceInfoServer saafWebserviceInfoServer ;

    @Autowired
    private ISaafWebserviceParamInfoServer saafWebserviceParamInfoServer;


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
            Pagination<SaafWebserviceParamInfoEntity_HI_RO> pagination = saafWebserviceParamInfoServer.find(jsonObject, curIndex, pageSize);
            com.alibaba.fastjson.JSONObject result = (com.alibaba.fastjson.JSONObject)JSON.toJSON(pagination);
            result.put("data", pagination.getData());
            result.put("status", "S");
            return result.toString();
        } catch (Exception e) {
            LOGGER.error("", e);
            return convertResultJSONObj("E",e.getMessage(),0,null).toString();
        }
    }


    /**
     * @param params id
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
                saafWebserviceParamInfoServer.delete(Integer.parseInt(id));
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


    @POST
    @Path("saveOrUpdate")
    @Produces("application/json")
    public String saveOrUpdate(@FormParam("params")
        String params) {
        try {
            if (StringUtils.isBlank(params))
                return convertResultJSONObj("P","parameter error",0,null).toString();
            JSONObject jsonParam = JSONObject.fromObject(params);
            if (StringUtils.isBlank(jsonParam.optString("webserviceCode")) || StringUtils.isBlank(jsonParam.optString("paramCode")))
                return convertResultJSONObj("P","parameter error",0,null).toString();

            Map<String, Object> map = new HashMap<>();
            map.put("webserviceCode", jsonParam.optString("webserviceCode"));
            map.put("paramCode", jsonParam.optString("paramCode"));

            if (StringUtils.isBlank(jsonParam.optString("paramId"))) {
                List<SaafWebserviceParamInfoEntity_HI> list = saafWebserviceParamInfoServer.findByProperty(map);
                if (list.size() > 0)
                    return convertResultJSONObj("E","paramCode重复",0,"").toString();
            }

            SaafWebserviceParamInfoEntity_HI instance = saafWebserviceParamInfoServer.saveOrUpdate(jsonParam, -1);
            return convertResultJSONObj("S", "success", 1, instance).toString();
        } catch (JSONException e) {
            LOGGER.error("", e);
            return convertResultJSONObj("P","parameter error",0,null).toString();
        } catch (Exception e) {
            LOGGER.error("", e);
            return convertResultJSONObj("E",e.getMessage(),0,null).toString();
        }
    }


    /**
     * @param params
     * @return
     */
    @POST
    @Path("saveOrUpdateAll")
    @Produces("application/json")
    public String saveOrUpdateAll(@FormParam("params")
        String params) {
        try {
            if (StringUtils.isBlank(params))
                return convertResultJSONObj("P","parameter error",0,null).toString();
            JSONObject jsonParam = JSONObject.fromObject(params);
            JSONObject props = null;
            LOGGER.info("jsonParam:{}", jsonParam);
            if (jsonParam.containsKey("services")) {
                LOGGER.info("services:{}", jsonParam.optString("services", "{}"));
                props = jsonParam.getJSONObject("services");
            }
            JSONArray propvals = jsonParam.containsKey("params") ? jsonParam.getJSONArray("params") : null;
            if (props == null && propvals == null)
                return convertResultJSONObj("P","parameter error",0,null).toString();

            List<SaafWebserviceParamInfoEntity_HI> valResult = new ArrayList<>();
            SaafWebserviceInfoEntity_HI propsResult = new SaafWebserviceInfoEntity_HI();
            com.alibaba.fastjson.JSONObject result = new com.alibaba.fastjson.JSONObject();

            if (props != null) {
                List<SaafWebserviceInfoEntity_HI> list = saafWebserviceInfoServer.findByProperty("webserviceCode", jsonParam.optString("webserviceCode"));
                if (list.size() > 0 && jsonParam.optString("webserviceId", null) == null)
                    return convertResultJSONObj("e","webserviceCode 已存在",0,"").toString();
                propsResult = saafWebserviceInfoServer.saveOrUpdate(props, -1);
            }
            if (propvals != null) {
                for (int i = 0; i < propvals.size(); i++) {
                    JSONObject temp = propvals.getJSONObject(i);
                    SaafWebserviceParamInfoEntity_HI obj = saafWebserviceParamInfoServer.saveOrUpdate(temp, -1);
                    valResult.add(obj);
                }
            }
            result.put("services", propsResult);
            result.put("params", valResult);
            return convertResultJSONObj("S", "success", 1, result).toString();
        } catch (JSONException e) {
            LOGGER.error("", e);
            return convertResultJSONObj("P","parameter error",0,null).toString();
        } catch (Exception e) {
            LOGGER.error("", e);
            return convertResultJSONObj("E",e.getMessage(),0,null).toString();
        }
    }
}
