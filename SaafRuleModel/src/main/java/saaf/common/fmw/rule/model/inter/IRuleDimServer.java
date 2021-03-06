package saaf.common.fmw.rule.model.inter;

import com.yhg.hibernate.core.paging.Pagination;
import net.sf.json.JSONObject;
import saaf.common.fmw.rule.model.entities.RuleDimEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.RuleDimEntity_HI_RO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
* Created by Administrator on Tue Jul 11 16:55:54 CST 2017
 */
public interface IRuleDimServer{

	RuleDimEntity_HI saveOrUpdate(JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

	boolean delete(Integer id);

	Pagination<RuleDimEntity_HI_RO> find(com.alibaba.fastjson.JSONObject params, int nowPage, int pageSize);

	List<RuleDimEntity_HI> findByProperty(String name, Object value);

	List<RuleDimEntity_HI> findByProperty(Map<String, Object> map);

	RuleDimEntity_HI findById(Integer id);
}