package saaf.common.fmw.rule.model.inter.server;


import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.model.inter.server.GenerateCodeServer;
import saaf.common.fmw.rule.model.entities.RuleExpressionEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.RuleExpressionEntity_HI_RO;
import saaf.common.fmw.rule.model.inter.IRuleExpression;
import saaf.common.fmw.rule.utils.Util;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Component("ruleExpressionServer")
public class RuleExpressionServer implements IRuleExpression {
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleExpressionServer.class);
    private ViewObject ruleExpressionDao;
    private BaseViewObject ruleExpressionRoDao;

    @Autowired
    private GenerateCodeServer generateCodeServer;

    @Resource(name = "ruleExpressionDAO_HI")
    public void setRuleExpressionDao(ViewObject ruleExpressionDao) {
        this.ruleExpressionDao = ruleExpressionDao;
    }

    @Resource(name = "ruleExpressionDAO_HI_RO")
    public void setRuleExpressionDao(BaseViewObject ruleExpressionRoDao) {
        this.ruleExpressionRoDao = ruleExpressionRoDao;
    }

    @Override
    public RuleExpressionEntity_HI saveOrUpdate(net.sf.json.JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException,
                                                                                                     IllegalAccessException {
        RuleExpressionEntity_HI instance = Util.setEntity(new RuleExpressionEntity_HI(), parameter, ruleExpressionDao, parameter.opt("ruleExpId"), userId);
        if (StringUtils.isBlank(instance.getRuleExpCode())) {
            String seq = instance.getRuleBusinessLineCode() + "_exp_";
            instance.setRuleExpCode(generateCodeServer.generateCode(seq, seq, 2));
        }
        if (StringUtils.isBlank(parameter.optString("effectEndDate")))
            instance.setEffectEndDate(null);
        if (instance.getEffectDate() == null)
            instance.setEffectDate(new Date());

        ruleExpressionDao.saveOrUpdate(instance);
        return instance;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null)
            return false;
        RuleExpressionEntity_HI instance = (RuleExpressionEntity_HI)ruleExpressionDao.getById(id);
        if (instance == null)
            return false;
        ruleExpressionDao.delete(instance);
        return true;
    }

    @Override
    public Pagination<RuleExpressionEntity_HI_RO> find(com.alibaba.fastjson.JSONObject params, int nowPage, int pageSize) {
        return Util.autoSearchPagination(ruleExpressionRoDao, RuleExpressionEntity_HI_RO.query, params, nowPage, pageSize, "order by  re.RULE_EXP_ID");
    }

    @Override
    public List<RuleExpressionEntity_HI> findByProperty(String name, Object value) {
        return ruleExpressionDao.findByProperty(name, value);
    }

    @Override
    public List<RuleExpressionEntity_HI> findByProperty(Map<String, Object> map) {
        return ruleExpressionDao.findByProperty(map);
    }

    @Override
    public RuleExpressionEntity_HI findById(Integer id) {
        return (RuleExpressionEntity_HI)ruleExpressionDao.getById(id);
    }
}
