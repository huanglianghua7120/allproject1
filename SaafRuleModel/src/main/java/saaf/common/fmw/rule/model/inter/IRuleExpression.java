package saaf.common.fmw.rule.model.inter;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.rule.model.entities.readonly.RuleExpressionEntity_HI_RO;
import saaf.common.fmw.rule.model.entities.RuleExpressionEntity_HI;

import java.lang.reflect.InvocationTargetException;

import java.util.List;
import java.util.Map;


public interface IRuleExpression {


    RuleExpressionEntity_HI saveOrUpdate(net.sf.json.JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException,
                                                                                              IllegalAccessException;

    boolean delete(Integer id);

    Pagination<RuleExpressionEntity_HI_RO> find(JSONObject params, int nowPage, int pageSize);

    List<RuleExpressionEntity_HI> findByProperty(String name, Object value);

    List<RuleExpressionEntity_HI> findByProperty(Map<String, Object> map);

    RuleExpressionEntity_HI findById(Integer id);
}
