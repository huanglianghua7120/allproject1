package saaf.common.fmw.rule.model.inter;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.rule.model.entities.RuleDimEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.RuleDimEntity_HI_RO;

import java.lang.reflect.InvocationTargetException;

import java.util.List;
import java.util.Map;


public interface IRuleDim {

    RuleDimEntity_HI saveOrUpdate(net.sf.json.JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException,
                                                                                       IllegalAccessException;

    boolean delete(Integer id);

    Pagination<RuleDimEntity_HI_RO> find(JSONObject params, int nowPage, int pageSize);

    List<RuleDimEntity_HI> findByProperty(String name, Object value);

    List<RuleDimEntity_HI> findByProperty(Map<String, Object> map);

    RuleDimEntity_HI findById(Integer id);

    String findRuleDimInfo(JSONObject queryParamJSON);

    String saveRuleDimInfo(JSONObject queryParamJSON);

}
