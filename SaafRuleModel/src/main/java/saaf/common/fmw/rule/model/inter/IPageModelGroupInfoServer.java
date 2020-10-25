package saaf.common.fmw.rule.model.inter;


import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.rule.model.entities.PageModelGroupInfoEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.PageModelGroupInfoEntity_HI_RO;
import net.sf.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on Fri Jul 07 17:27:23 CST 2017
 */
public interface IPageModelGroupInfoServer {

    PageModelGroupInfoEntity_HI saveOrUpdate(JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException,
                                                                                      IllegalAccessException;

    boolean delete(Integer id);

    Pagination<PageModelGroupInfoEntity_HI_RO> find(com.alibaba.fastjson.JSONObject params, int nowPage, int pageSize);

    List<PageModelGroupInfoEntity_HI> findByProperty(String name, Object value);

    List<PageModelGroupInfoEntity_HI> findByProperty(Map<String, Object> map);

    PageModelGroupInfoEntity_HI findById(Integer id);
}
