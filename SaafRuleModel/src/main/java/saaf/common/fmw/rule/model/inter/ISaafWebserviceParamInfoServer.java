package saaf.common.fmw.rule.model.inter;


import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.rule.model.entities.SaafWebserviceParamInfoEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.SaafWebserviceParamInfoEntity_HI_RO;

import java.lang.reflect.InvocationTargetException;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;


/**
 * Created by Admin on 2017/7/5.
 */
public interface ISaafWebserviceParamInfoServer {
    SaafWebserviceParamInfoEntity_HI saveOrUpdate(JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException,
                                                                                           IllegalAccessException;

    boolean delete(Integer id);

    Pagination<SaafWebserviceParamInfoEntity_HI_RO> find(com.alibaba.fastjson.JSONObject params, int nowPage, int pageSize);

    List<SaafWebserviceParamInfoEntity_HI> findByProperty(String name, Object value);

    List<SaafWebserviceParamInfoEntity_HI> findByProperty(Map<String, Object> map);

    SaafWebserviceParamInfoEntity_HI findById(Integer id);
}
