package saaf.common.fmw.rule.model.inter;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.rule.model.entities.RuleMappingBusinessEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.RuleMappingBusinessEntity_HI_RO;

import java.lang.reflect.InvocationTargetException;

import java.util.List;
import java.util.Map;


public interface IRuleMappingBusiness {

    RuleMappingBusinessEntity_HI saveOrUpdate(net.sf.json.JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException,
                                                                                                   IllegalAccessException;

    boolean delete(Integer id);

    Pagination<RuleMappingBusinessEntity_HI_RO> find(JSONObject params, int nowPage, int pageSize);

    List<RuleMappingBusinessEntity_HI> findByProperty(String name, Object value);

    List<RuleMappingBusinessEntity_HI> findByProperty(Map<String, Object> map);

    RuleMappingBusinessEntity_HI findById(Integer id);
}
