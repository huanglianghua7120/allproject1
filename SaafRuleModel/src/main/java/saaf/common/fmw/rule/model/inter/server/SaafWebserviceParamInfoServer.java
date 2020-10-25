package saaf.common.fmw.rule.model.inter.server;


import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.rule.model.entities.SaafWebserviceParamInfoEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.SaafWebserviceParamInfoEntity_HI_RO;
import saaf.common.fmw.rule.model.inter.ISaafWebserviceParamInfoServer;
import saaf.common.fmw.rule.utils.Util;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;


@Component("saafWebserviceParamInfoServer")
public class SaafWebserviceParamInfoServer implements ISaafWebserviceParamInfoServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafWebserviceParamInfoServer.class);
    private ViewObject saafWebserviceParamInfoDao;
    private BaseViewObject saafWebserviceParamInfoRoDao;

    @Resource(name = "saafWebserviceParamInfoDAO_HI")
    public void setSaafWebserviceParamInfoDao(ViewObject saafWebserviceParamInfoDao) {
        this.saafWebserviceParamInfoDao = saafWebserviceParamInfoDao;
    }

    @Resource(name = "saafWebserviceParamInfoDAO_HI_RO")
    public void setSaafWebserviceParamInfoDao(BaseViewObject saafWebserviceParamInfoRoDao) {
        this.saafWebserviceParamInfoRoDao = saafWebserviceParamInfoRoDao;
    }

    @Override
    public SaafWebserviceParamInfoEntity_HI saveOrUpdate(JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException,
                                                                                                  IllegalAccessException {
        SaafWebserviceParamInfoEntity_HI instance =
            Util.setEntity(new SaafWebserviceParamInfoEntity_HI(), parameter, saafWebserviceParamInfoDao, parameter.opt("paramId"), userId);
        saafWebserviceParamInfoDao.saveOrUpdate(instance);
        return instance;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null)
            return false;
        SaafWebserviceParamInfoEntity_HI instance = (SaafWebserviceParamInfoEntity_HI)saafWebserviceParamInfoDao.getById(id);
        if (instance == null)
            return false;
        saafWebserviceParamInfoDao.delete(instance);
        return true;
    }

    @Override
    public Pagination<SaafWebserviceParamInfoEntity_HI_RO> find(com.alibaba.fastjson.JSONObject params, int nowPage, int pageSize) {
        return Util.autoSearchPagination(saafWebserviceParamInfoRoDao, SaafWebserviceParamInfoEntity_HI_RO.query, params, nowPage, pageSize);
    }

    @Override
    public List<SaafWebserviceParamInfoEntity_HI> findByProperty(String name, Object value) {
        return saafWebserviceParamInfoDao.findByProperty(name, value);
    }

    @Override
    public List<SaafWebserviceParamInfoEntity_HI> findByProperty(Map<String, Object> map) {
        return saafWebserviceParamInfoDao.findByProperty(map);
    }

    @Override
    public SaafWebserviceParamInfoEntity_HI findById(Integer id) {
        return (SaafWebserviceParamInfoEntity_HI)saafWebserviceParamInfoDao.getById(id);
    }


}

