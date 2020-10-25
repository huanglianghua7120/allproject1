package saaf.common.fmw.rule.model.inter;


import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.rule.model.entities.SaafWebserviceInfoEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.SaafWebserviceInfoEntity_HI_RO;

import java.lang.reflect.InvocationTargetException;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;


/**
 * Created by Admin on 2017/7/5.
 */
public interface ISaafWebserviceInfoServer {
    SaafWebserviceInfoEntity_HI saveOrUpdate(JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException,
                                                                                      IllegalAccessException;

    boolean delete(Integer id);

    Pagination<SaafWebserviceInfoEntity_HI_RO> find(com.alibaba.fastjson.JSONObject params, int nowPage, int pageSize);

    List<SaafWebserviceInfoEntity_HI> findByProperty(String name, Object value);

    List<SaafWebserviceInfoEntity_HI> findByProperty(Map<String, Object> map);

    SaafWebserviceInfoEntity_HI findById(Integer id);
}
