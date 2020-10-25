package saaf.common.fmw.rule.model.inter;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.rule.model.entities.readonly.RuleExpressiondimEntity_HI_RO;
import saaf.common.fmw.rule.model.entities.RuleExpressiondimEntity_HI;

import java.lang.reflect.InvocationTargetException;

import java.util.List;
import java.util.Map;


public interface IRuleExpressiondim {

    RuleExpressiondimEntity_HI saveOrUpdate(net.sf.json.JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException,
                                                                                                 IllegalAccessException;

    boolean delete(Integer id);

    Pagination<RuleExpressiondimEntity_HI_RO> find(JSONObject params, int nowPage, int pageSize);

    Pagination<RuleExpressiondimEntity_HI_RO> findBySql(JSONObject params, String sql, int nowPage, int pageSize);

    List<RuleExpressiondimEntity_HI> findByProperty(String name, Object value);

    List<RuleExpressiondimEntity_HI> findByProperty(Map<String, Object> map);

    RuleExpressiondimEntity_HI findById(Integer id);
}
