package saaf.common.fmw.rule.model.inter;


import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.rule.model.entities.PageModelInfoEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.PageModelInfoEntity_HI_RO;

import java.lang.reflect.InvocationTargetException;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;


/**
 * Created by Administrator on Thu Jul 06 17:24:57 CST 2017
 */
public interface IPageModelInfoServer {

    PageModelInfoEntity_HI saveOrUpdate(JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    boolean delete(Integer id);

    Pagination<PageModelInfoEntity_HI_RO> find(com.alibaba.fastjson.JSONObject params, int nowPage, int pageSize);

    List<PageModelInfoEntity_HI> findByProperty(String name, Object value);

    List<PageModelInfoEntity_HI> findByProperty(Map<String, Object> map);

    PageModelInfoEntity_HI findById(Integer id);
}
