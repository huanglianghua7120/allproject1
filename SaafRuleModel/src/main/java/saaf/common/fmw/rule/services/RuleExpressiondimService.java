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
import saaf.common.fmw.rule.model.entities.RuleExpressionEntity_HI;
import saaf.common.fmw.rule.model.entities.RuleExpressiondimEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.RuleExpressiondimEntity_HI_RO;
import saaf.common.fmw.rule.model.inter.IRuleExpression;
import saaf.common.fmw.rule.model.inter.IRuleExpressiondim;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
@Path("/ruleExpressiondimService")
public class RuleExpressiondimService extends CommonAbstractServices{
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleExpressiondimService.class);

    @Autowired
    private IRuleExpressiondim expressiondimServer;

    @Autowired
    private IRuleExpression expressionServer;



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
            Pagination<RuleExpressiondimEntity_HI_RO> pagination = expressiondimServer.find(jsonObject, curIndex, pageSize);
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
     * @param params
     * @param curIndex
     * @param pageSize
     * @return
     */
    @POST
    @Path("queryAll")
    @Produces("application/json")
    public String queryAll(@FormParam("params") String params,
                           @FormParam("pageIndex") @DefaultValue("1") Integer curIndex,
                           @FormParam("pageRows") @DefaultValue("10") Integer pageSize) {
        try {
            com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
            if (StringUtils.isNotBlank(params)) {
                jsonObject = com.alibaba.fastjson.JSONObject.parseObject(params);
            }
            Pagination<RuleExpressiondimEntity_HI_RO> pagination = expressiondimServer.find(jsonObject, curIndex, pageSize);
            String sql=RuleExpressiondimEntity_HI_RO.rightQuery ;
            String ruleExpCode=jsonObject.getString("var_equal_ruleExpCode");
            if (StringUtils.isNotBlank(ruleExpCode)){
                sql=sql.replace("#replace#","AND re.RULE_EXP_CODE='"+ruleExpCode+"'");
                jsonObject.remove("var_equal_ruleExpCode");
            }else {
                sql=sql.replace("#replace#","");
            }
            Pagination<RuleExpressiondimEntity_HI_RO> rightPagination = expressiondimServer.findBySql(jsonObject,sql,curIndex, pageSize);
            com.alibaba.fastjson.JSONObject result = (com.alibaba.fastjson.JSONObject) JSON.toJSON(pagination);
            List<RuleExpressiondimEntity_HI_RO> list=new ArrayList<>();
            list.addAll(pagination.getData());
            list.addAll(rightPagination.getData());
            Collections.sort(list);
            result.put("count",pagination.getCount()+rightPagination.getCount());
            result.put("data", list);
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
    public String saveOrUpdate(@FormParam("params") String params) {
        try {
            if (StringUtils.isBlank(params))
                return convertResultJSONObj("P","parameter error",0,null).toString();
            JSONObject jsonParam = JSONObject.fromObject(params);
            if (StringUtils.isEmpty(jsonParam.optString("ruleBusinessLineCode")) )
                return convertResultJSONObj("P","parameter error",0,null).toString();
            RuleExpressiondimEntity_HI instance = expressiondimServer.saveOrUpdate(jsonParam, -1);
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
    public String saveOrUpdateAll(@FormParam("params") String params) {
        try {
            if (StringUtils.isBlank(params))
                return convertResultJSONObj("P","parameter error",0,null).toString();
            JSONObject jsonParam = JSONObject.fromObject(params);
            JSONObject props = new JSONObject();
            LOGGER.info("jsonParam:{}", jsonParam);
            if (jsonParam.containsKey("expression")) {
                LOGGER.info("businessline:{}", jsonParam.optString("expression", "{}"));
                props = jsonParam.getJSONObject("expression");
            }
            JSONArray propvals = jsonParam.containsKey("dimExpressions") ? jsonParam.getJSONArray("dimExpressions") : null;
            if (props == null && propvals == null)
                return convertResultJSONObj("P","parameter error",0,null).toString();

            List<RuleExpressiondimEntity_HI> valResult = new ArrayList<>();
            RuleExpressionEntity_HI propsResult = new RuleExpressionEntity_HI();
            com.alibaba.fastjson.JSONObject result = new com.alibaba.fastjson.JSONObject();

            if (props != null) {
                propsResult = expressionServer.saveOrUpdate(props, -1);
            }
            if (propvals != null) {
                for (int i = 0; i < propvals.size(); i++) {
                    JSONObject temp = propvals.getJSONObject(i);
                    temp.put("ruleExpCode", propsResult.getRuleExpCode());
                    RuleExpressiondimEntity_HI obj = expressiondimServer.saveOrUpdate(temp, -1);
                    valResult.add(obj);
                }
            }
            result.put("expression", propsResult);
            result.put("dimExpressions", valResult);
            return convertResultJSONObj("S", "success", 1, result).toString();
        } catch (JSONException e) {
            LOGGER.error("", e);
            return convertResultJSONObj("P","parameter error",0,null).toString();
        } catch (Exception e) {
            LOGGER.error("", e);
            return convertResultJSONObj("E",e.getMessage(),0,null).toString();
        }
    }



    /**
     * @param params ruleExpDimId
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
            if (StringUtils.isBlank(jsonParam.optString("ruleExpDimId")))
                return convertResultJSONObj("P","parameter error",0,null).toString();
            String[] ids = jsonParam.optString("ruleExpDimId", "").split(",");
            for (String id : ids) {
                expressiondimServer.delete(Integer.parseInt(id));
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





    @GET
    @Path("user")
    @Produces("text/plain")
    public String getUser(@QueryParam("name")
                                  String name) {
        return "hello " + name;
    }
}
