package saaf.common.fmw.rule.model.inter;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.rule.model.entities.RuleBusinessLineEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.RuleBusinessLineEntity_HI_RO;

import java.lang.reflect.InvocationTargetException;

public interface IRuleBusinessLine {

    String findRuleBusinessLineInfo(JSONObject queryParamJSON);

    String saveRuleBusinessLineInfo(JSONObject queryParamJSON);

    RuleBusinessLineEntity_HI saveOrUpdate(net.sf.json.JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException,
                                                                                                IllegalAccessException;

    Pagination<RuleBusinessLineEntity_HI_RO> find(JSONObject params, int nowPage, int pageSize);

    boolean delete(Integer id);
}
