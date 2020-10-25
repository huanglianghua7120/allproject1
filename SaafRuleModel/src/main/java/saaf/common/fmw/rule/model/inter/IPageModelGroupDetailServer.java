package saaf.common.fmw.rule.model.inter;


import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.rule.model.entities.PageModelGroupDetailEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.PageModelGroupDetailEntity_HI_RO;

import java.lang.reflect.InvocationTargetException;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;


/**
 * Created by Administrator on Fri Jul 07 17:28:51 CST 2017
 */
public interface IPageModelGroupDetailServer {

    PageModelGroupDetailEntity_HI saveOrUpdate(JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException,
                                                                                        IllegalAccessException;

    boolean delete(Integer id);

    Pagination<PageModelGroupDetailEntity_HI_RO> find(com.alibaba.fastjson.JSONObject params, int nowPage, int pageSize);

    List<PageModelGroupDetailEntity_HI> findByProperty(String name, Object value);

    List<PageModelGroupDetailEntity_HI> findByProperty(Map<String, Object> map);

    PageModelGroupDetailEntity_HI findById(Integer id);
}
