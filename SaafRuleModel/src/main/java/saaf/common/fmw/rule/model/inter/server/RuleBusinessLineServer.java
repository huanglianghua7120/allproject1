package saaf.common.fmw.rule.model.inter.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.model.inter.server.GenerateCodeServer;
import saaf.common.fmw.rule.constant.Constant;
import saaf.common.fmw.rule.model.dao.RuleBusinessLineDAO_HI;
import saaf.common.fmw.rule.model.dao.RuleDimDAO_HI;
import saaf.common.fmw.rule.model.dao.RuleExpressionDAO_HI;
import saaf.common.fmw.rule.model.dao.RuleExpressiondimDAO_HI;
import saaf.common.fmw.rule.model.dao.readonly.RuleBusinessLineDAO_HI_RO;
import saaf.common.fmw.rule.model.entities.RuleBusinessLineEntity_HI;
import saaf.common.fmw.rule.model.entities.RuleDimEntity_HI;
import saaf.common.fmw.rule.model.entities.RuleExpressionEntity_HI;
import saaf.common.fmw.rule.model.entities.RuleExpressiondimEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.RuleBusinessLineEntity_HI_RO;
import saaf.common.fmw.rule.model.inter.IRuleBusinessLine;
import saaf.common.fmw.rule.utils.Util;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Component("ruleBusinessLineServer")
public class RuleBusinessLineServer implements IRuleBusinessLine {
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleBusinessLineServer.class);

    @Autowired
    private GenerateCodeServer generateCodeServer;

    @Resource(name = "ruleBusinessLineDAO_HI")
    private RuleBusinessLineDAO_HI ruleBusinessLineDAO_HI;

    @Resource(name = "ruleDimDAO_HI")
    private RuleDimDAO_HI ruleDimDAOHi;

    @Resource(name = "ruleBusinessLineDAO_HI_RO")
    private RuleBusinessLineDAO_HI_RO ruleBusinessLineDAO_HI_RO;

    @Resource(name = "ruleExpressiondimDAO_HI")
    private RuleExpressiondimDAO_HI ruleExpressiondimDAO;

    @Resource(name = "ruleExpressionDAO_HI")
    private RuleExpressionDAO_HI ruleExpressionDAO;

    public RuleBusinessLineServer() {
        super();
    }

    public String findRuleBusinessLineInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<RuleBusinessLineEntity_HI> findListResult = ruleBusinessLineDAO_HI.findList("from RuleBusinessLineEntity_HI", queryParamMap);
        //String resultData = JSON.toJSONString(findListResult);
        JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), findListResult);
        return resultStr.toString();
    }

    public String saveRuleBusinessLineInfo(JSONObject queryParamJSON) {
        RuleBusinessLineEntity_HI ruleBusinessLineEntity_HI = JSON.parseObject(queryParamJSON.toString(), RuleBusinessLineEntity_HI.class);
        Object resultData = ruleBusinessLineDAO_HI.save(ruleBusinessLineEntity_HI);
        JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
        return resultStr.toString();
    }


    @Override
    public RuleBusinessLineEntity_HI saveOrUpdate(net.sf.json.JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException,
                                                                                                       IllegalAccessException {
        RuleBusinessLineEntity_HI instance =
            Util.setEntity(new RuleBusinessLineEntity_HI(), parameter, ruleBusinessLineDAO_HI, parameter.optString("ruleBusinessLineId", null), userId);
        if (StringUtils.isBlank(instance.getRuleBusinessLineCode())) {
            String seq = Constant.BUSINESSLINE_CODE + Constant.DATEFORMAT_DATE_SEQ.format(new Date());
            instance.setRuleBusinessLineCode(generateCodeServer.generateCode(seq, seq, 4));
        }
        ruleBusinessLineDAO_HI.saveOrUpdate(instance);
        return instance;
    }

    @Override
    public Pagination<RuleBusinessLineEntity_HI_RO> find(com.alibaba.fastjson.JSONObject params, int nowPage, int pageSize) {
        return Util.autoSearchPagination(ruleBusinessLineDAO_HI_RO, RuleBusinessLineEntity_HI_RO.query, params, nowPage, pageSize, "order by rbl.rule_business_line_id");
    }


    @Override
    public boolean delete(Integer id) {
        if (id == null)
            return false;
        RuleBusinessLineEntity_HI instance = ruleBusinessLineDAO_HI.getById(id);
        if (instance == null)
            return false;
        List<RuleDimEntity_HI> list = ruleDimDAOHi.findByProperty("ruleBusinessLineCode", instance.getRuleBusinessLineCode());
        List<RuleExpressiondimEntity_HI> expressiondimList = ruleExpressiondimDAO.findByProperty("ruleBusinessLineCode", instance.getRuleBusinessLineCode());
        List<RuleExpressionEntity_HI> expressionList = ruleExpressionDAO.findByProperty("ruleBusinessLineCode", instance.getRuleBusinessLineCode());
        ruleDimDAOHi.delete(list);
        ruleExpressionDAO.delete(expressionList);
        ruleExpressiondimDAO.delete(expressiondimList);
        ruleBusinessLineDAO_HI.delete(instance);
        return true;
    }

}
