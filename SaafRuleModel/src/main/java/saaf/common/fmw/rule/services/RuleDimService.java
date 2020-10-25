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
import saaf.common.fmw.rule.model.entities.RuleBusinessLineEntity_HI;
import saaf.common.fmw.rule.model.entities.RuleDimEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.RuleDimEntity_HI_RO;
import saaf.common.fmw.rule.model.inter.IRuleBusinessLine;
import saaf.common.fmw.rule.model.inter.IRuleDim;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;


@Component
@Path("/ruleDimService")
public class RuleDimService extends CommonAbstractServices{
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleDimService.class);

    @Autowired
    private IRuleDim ruleDimServer ;

    @Autowired
    private IRuleBusinessLine ruleBusinessLineServer;

    /**
     * @param params
     * @param curIndex
     * @param pageSize
     * @return
     */
    @POST
    @Path("query")
    @Produces("application/json")
    public String query(@FormParam("params") String params,
                        @FormParam("pageIndex") @DefaultValue("1") Integer curIndex,
                        @FormParam("pageRows") @DefaultValue("10") Integer pageSize) {
        try {
            com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
            if (StringUtils.isNotBlank(params)) {
                jsonObject = com.alibaba.fastjson.JSONObject.parseObject(params);
            }
            Pagination<RuleDimEntity_HI_RO>  pagination = ruleDimServer.find(jsonObject, curIndex, pageSize);
            com.alibaba.fastjson.JSONObject result = (com.alibaba.fastjson.JSONObject) JSON.toJSON(pagination);
            result.put("data", pagination.getData());
            result.put("status", "S");
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
    public String delete(@FormParam("params") String params) {
        try {
            if (StringUtils.isBlank(params))
                return convertResultJSONObj("P","parameter error",0,null).toString();
            net.sf.json.JSONObject jsonParam = net.sf.json.JSONObject.fromObject(params);
            if (StringUtils.isBlank(jsonParam.optString("ruleDimId")))
                return convertResultJSONObj("P","parameter error",0,null).toString();
            String[] ids = jsonParam.optString("ruleDimId", "").split(",");
            for (String id : ids) {
                ruleDimServer.delete(Integer.parseInt(id));
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
    @Path("saveOrUpdateDim")
    @Produces("application/json")
    public String saveOrUpdateDim(@FormParam("params") String params) {
        try {
            if (StringUtils.isBlank(params))
                return convertResultJSONObj("P","parameter error",0,null).toString();
            JSONObject jsonParam = JSONObject.fromObject(params);
            if (StringUtils.isEmpty(jsonParam.optString("ruleBusinessLineCode")) )
                return convertResultJSONObj("P","parameter error",0,null).toString();
            if (jsonParam.opt("ruleDimCode")!=null){
                List<RuleDimEntity_HI> list= ruleDimServer.findByProperty(new com.alibaba.fastjson.JSONObject().fluentPut("ruleDimCode",jsonParam.optString("ruleDimCode")).fluentPut("ruleBusinessLineCode",jsonParam.optString("ruleBusinessLineCode")));
                if (list.size()>0 && jsonParam.opt("ruleDimId")==null)
                    return convertResultJSONObj("E","维度编码重复",0,null).toString();
                else  if (list.size()>0 && jsonParam.opt("ruleDimId")!=null && list.get(0).getRuleDimId().equals(jsonParam.optInt("ruleDimId"))==false)
                    return convertResultJSONObj("E","维度编码重复",0,null).toString();
            }
            RuleDimEntity_HI instance = ruleDimServer.saveOrUpdate(jsonParam, -1);
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
    @Path("saveOrUpdate")
    @Produces("application/json")
    public String saveOrUpdate(@FormParam("params") String params) {
        try {
            if (StringUtils.isBlank(params))
                return convertResultJSONObj("P","parameter error",0,null).toString();
            JSONObject jsonParam = JSONObject.fromObject(params);
            JSONObject props = null;
            LOGGER.info("jsonParam:{}", jsonParam);
            if (jsonParam.containsKey("businessline")) {
                LOGGER.info("businessline:{}", jsonParam.optString("businessline", "{}"));
                props = jsonParam.getJSONObject("businessline");
            }
            JSONArray propvals = jsonParam.containsKey("dims") ? jsonParam.getJSONArray("dims") : null;
            if (props == null && propvals == null)
                return convertResultJSONObj("P","parameter error",0,null).toString();

            List<RuleDimEntity_HI> valResult = new ArrayList<>();
            RuleBusinessLineEntity_HI propsResult = new RuleBusinessLineEntity_HI();
            com.alibaba.fastjson.JSONObject result = new com.alibaba.fastjson.JSONObject();

            if (props != null) {
                propsResult = ruleBusinessLineServer.saveOrUpdate(props, -1);
            }
            if (propvals != null) {
                for (int i = 0; i < propvals.size(); i++) {
                    JSONObject temp = propvals.getJSONObject(i);
                    if (StringUtils.isBlank(temp.optString("ruleBusinessLineCode")))
                        temp.put("ruleBusinessLineCode", propsResult.getRuleBusinessLineCode());
                    RuleDimEntity_HI obj = ruleDimServer.saveOrUpdate(temp, -1);
                    valResult.add(obj);
                }
            }
            result.put("businessline", propsResult);
            result.put("dims", valResult);
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
