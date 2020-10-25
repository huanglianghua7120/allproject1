package saaf.common.fmw.rule.services;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import net.sf.json.JSONException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.rule.constant.Constant;
import saaf.common.fmw.rule.model.entities.readonly.RuleBusinessLineEntity_HI_RO;
import saaf.common.fmw.rule.model.inter.IRuleBusinessLine;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;


@Path("/ruleBusinessLineService")
@Component
public class RuleBusinessLineService extends CommonAbstractServices{
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleBusinessLineService.class);
    @Autowired
    private IRuleBusinessLine ruleBusinessLineServer ;


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
            LOGGER.info("ruleBusinessLineServer:{}",ruleBusinessLineServer==null);
            Pagination<RuleBusinessLineEntity_HI_RO> pagination = ruleBusinessLineServer.find(jsonObject, curIndex, pageSize);
            com.alibaba.fastjson.JSONObject result = (com.alibaba.fastjson.JSONObject)JSON.toJSON(pagination);
            result.put("data", pagination.getData());
            result.put("status", "S");
            JSONArray array = result.getJSONArray("data");
            JSONArray resultArray = new JSONArray();
            for (int i = 0; i < array.size(); i++) {
                JSONObject json = array.getJSONObject(i);
                json.put("ruleBusinessLineMapptypeMeaning", Constant.BUSINESSLINE_MAPPTYPE.get(json.getString("ruleBusinessLineMapptype")));
                resultArray.add(json);
            }
            result.put("data", resultArray);
            return result.toString();
        } catch (Exception e) {
            LOGGER.error("", e);
            return convertResultJSONObj("E",e.getMessage(),0,null).toString();
        }
    }


    /**
     * @param params propId
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
            if (StringUtils.isBlank(jsonParam.optString("ruleBusinessLineId")))
                return convertResultJSONObj("P","parameter error",0,null).toString();
            String[] ids = jsonParam.optString("ruleBusinessLineId", "").split(",");
            for (String id : ids) {
                ruleBusinessLineServer.delete(Integer.parseInt(id));
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
    @Path("findRuleBusinessLineInfo")
    @Consumes("application/json")
    @Produces("application/json")
    public String findRuleBusinessLineInfo(String postParam) {
        LOGGER.info(postParam);
        JSONObject paramJSON = JSON.parseObject(postParam);
        String resultStr = ruleBusinessLineServer.findRuleBusinessLineInfo(paramJSON);
        LOGGER.info(resultStr);
        return resultStr;
    }

}
