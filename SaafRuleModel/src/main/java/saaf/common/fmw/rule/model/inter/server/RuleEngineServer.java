package saaf.common.fmw.rule.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.rule.model.beans.RuleMappingResult;
import saaf.common.fmw.rule.model.entities.readonly.RuleExpressionFullViewEntity_HI_RO;
import saaf.common.fmw.rule.model.inter.IRuleEngineServer;
import saaf.common.fmw.rule.model.inter.IRuleExpressionFullView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 2017/8/2.
 */
@Component("ruleEngineServer")
public class RuleEngineServer implements IRuleEngineServer {
    private static final Logger log = LoggerFactory.getLogger(RuleEngineServer.class);

    @Resource(name = "ruleExpressionFullViewServer")
    private IRuleExpressionFullView ruleExpressionFullViewServer;

    @Override
    public RuleMappingResult execute(String ruleBusinessLineCode, String ruleExpCode, Map<String, Object> dimParams) {
        if (StringUtils.isBlank(ruleBusinessLineCode))
            return new RuleMappingResult(false, "参数ruleBusinessLineCode缺失");

        if (dimParams == null)
            dimParams = new JSONObject();
        try {
            JSONObject paramJSON = new JSONObject();
            paramJSON.put("ruleBusinessLineCode", ruleBusinessLineCode);
            paramJSON.put("ruleExpCode", ruleExpCode);

            Map<RuleExpressionFullViewEntity_HI_RO, Object> resul = ruleExpressionFullViewServer.matchingExpResult(paramJSON, dimParams);
            log.info("result:{}",JSONObject.toJSON(resul));
            if (resul == null || resul.size() == 0) {
                return new RuleMappingResult(false, "RuleMappingBusiness not found");
            }
            List<RuleMappingResult.Action> lists = new ArrayList<>();
            Set<RuleExpressionFullViewEntity_HI_RO> keys = resul.keySet();
            for (RuleExpressionFullViewEntity_HI_RO obj : keys) {
                JSONObject jsonObject = (JSONObject) resul.get(obj);
                for (int i = 0; i < jsonObject.getJSONArray("dimOperatorValues").size(); i++) {
                    JSONObject tmp = jsonObject.getJSONArray("dimOperatorValues").getJSONObject(i);
                    RuleMappingResult.Action action = new RuleMappingResult().new Action();
                    action.setBusTargetType(tmp.getString("confirmed"));
                    action.setDimName(tmp.getString("dimName"));
                    action.setDimValue(tmp.getString("dimValue"));
                    action.setOperator(tmp.getString("operator"));
                    lists.add(action);
                }
                for (int i = 0; i < jsonObject.getJSONArray("dimServiceBeans").size(); i++) {
                    JSONObject tmp = jsonObject.getJSONArray("dimServiceBeans").getJSONObject(i);
                    RuleMappingResult.Action action = new RuleMappingResult().new Action();
                    action.setBusTargetType(tmp.getString("serviceURL"));
                    action.setBusTargetSource(tmp.getString("busTargetSource"));
                    action.setDimName(tmp.getString("dimValue"));
                    action.setWebserviceUrl(tmp.getString("webserviceUrl"));
                    lists.add(action);
                }
            }
            return new RuleMappingResult(true, "success", lists);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new RuleMappingResult(false, e.getMessage());
        }
    }


    /*public void updateTable(){
        ViewObject linee= (ViewObject) SaafToolUtils.context.getBean("ruleBusinessLineDAO_HI");
        ViewObject dim= (ViewObject) SaafToolUtils.context.getBean("ruleDimDAO_HI");
        List<RuleBusinessLineEntity_HI> lines=linee.findByProperty(new HashMap<String, Object>());
        for (RuleBusinessLineEntity_HI obj:lines){
            List<RuleDimEntity_HI> dims=dim.findByProperty("ruleBusinessLineCode",obj.getRuleBusinessLineCode());
            String tmp=null;
            for (RuleDimEntity_HI gg:dims){
                tmp=createPlaceholder(tmp);
                gg.setPlaceholder(tmp);
                dim.update(gg);
            }
        }
    }*/

    public static void main(String[] args) {

        /*RuleEngineServer ruleEngineServer= (RuleEngineServer) SaafToolUtils.context.getBean("ruleEngineServer");
        ruleEngineServer.updateTable();


        System.exit(0);*/


        /*IRuleEngineServer server = (RuleEngineServer) SaafToolUtils.context.getBean("ruleEngineServer");
        Map<String, Object> queryParamsMap = new HashMap<String, Object>();
        queryParamsMap.put("System_Platform", "EX");
        queryParamsMap.put("DeliveryQty_Last_Month", "50");
        queryParamsMap.put("delivery_line_id", "14");
        System.out.println(JSONObject.toJSON(server.execute("BUSINESSLINE201707310002",null,queryParamsMap)));*/



    }

    private  static String createPlaceholder(String placeholder){
        if (StringUtils.isBlank(placeholder))
            return "#aa#";
        placeholder=placeholder.replace("#","");
        int n=Integer.valueOf(placeholder,36);
        placeholder=Integer.toString(++n,36);
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(placeholder);
        while (m.matches()){
            placeholder=Integer.toString(++n,36);
            m = p.matcher(placeholder);
        }
        return "#"+placeholder+"#";
    }


}
