package saaf.common.fmw.rule.model.inter.server;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.rule.model.entities.RuleExpressiondimEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.RuleExpressiondimEntity_HI_RO;
import saaf.common.fmw.rule.model.inter.IRuleExpressiondim;
import saaf.common.fmw.rule.utils.Util;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component("ruleExpressiondimServer")
public class RuleExpressiondimServer implements IRuleExpressiondim {
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleExpressiondimServer.class);

    private ViewObject ruleExpressiondimDao;
    private BaseViewObject ruleExpressiondimRoDao;

    @Resource(name = "ruleExpressiondimDAO_HI")
    public void setRuleExpressiondimDao(ViewObject ruleExpressiondimDao) {
        this.ruleExpressiondimDao = ruleExpressiondimDao;
    }

    @Resource(name = "ruleExpressiondimDAO_HI_RO")
    public void setRuleExpressiondimRoDao(BaseViewObject ruleExpressiondimRoDao) {
        this.ruleExpressiondimRoDao = ruleExpressiondimRoDao;
    }

    @Override
    public RuleExpressiondimEntity_HI saveOrUpdate(net.sf.json.JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RuleExpressiondimEntity_HI instance = Util.setEntity(new RuleExpressiondimEntity_HI(), parameter,ruleExpressiondimDao,parameter.opt("ruleExpDimId"), userId);
        if (StringUtils.isBlank(parameter.optString("effectEndDate")))
            instance.setEffectEndDate(null);
        if(instance.getEffectDate()==null)
            instance.setEffectDate(new Date());
        ruleExpressiondimDao.saveOrUpdate(instance);
        return instance;
    }

    @Override
    public boolean delete(Integer id){
        if (id==null)
            return false;
        RuleExpressiondimEntity_HI instance= (RuleExpressiondimEntity_HI) ruleExpressiondimDao.getById(id);
        if (instance==null)
            return false;
        ruleExpressiondimDao.delete(instance);
        return true;
    }

    @Override
    public Pagination<RuleExpressiondimEntity_HI_RO> find(com.alibaba.fastjson.JSONObject params, int nowPage, int pageSize) {
        return Util.autoSearchPagination(ruleExpressiondimRoDao, RuleExpressiondimEntity_HI_RO.query, params, nowPage, pageSize,"order by re.RULE_EXP_DIM_ID");
    }

    @Override
    public Pagination<RuleExpressiondimEntity_HI_RO> findBySql(com.alibaba.fastjson.JSONObject params, String sql , int nowPage, int pageSize) {
        return Util.autoSearchPagination(ruleExpressiondimRoDao,sql, params, nowPage, pageSize,"order by re.RULE_EXP_DIM_ID");
    }




    @Override
    public List<RuleExpressiondimEntity_HI> findByProperty(String name, Object value){
        return ruleExpressiondimDao.findByProperty(name,value);
    }

    @Override
    public List<RuleExpressiondimEntity_HI> findByProperty(Map<String, Object> map){
        return ruleExpressiondimDao.findByProperty(map);
    }

    @Override
    public RuleExpressiondimEntity_HI findById(Integer id){
        return (RuleExpressiondimEntity_HI) ruleExpressiondimDao.getById(id);
    }

}
