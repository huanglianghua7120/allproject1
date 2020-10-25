package saaf.common.fmw.rule.model.inter.server;


import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import saaf.common.fmw.rule.model.entities.PageModelGroupInfoEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.PageModelGroupInfoEntity_HI_RO;
import saaf.common.fmw.rule.model.inter.IPageModelGroupInfoServer;
import saaf.common.fmw.rule.utils.Util;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on Fri Jul 07 17:27:23 CST 2017
 */
@Component("pageModelGroupInfoServer")
public class PageModelGroupInfoServer implements IPageModelGroupInfoServer {

    private ViewObject pageModelGroupInfoDao;
    private BaseViewObject pageModelGroupInfoRoDao;

    @Resource(name = "pageModelGroupInfoDAO_HI")
    public void setPageModelGroupInfoDao(ViewObject pageModelGroupInfoDao) {
        this.pageModelGroupInfoDao = pageModelGroupInfoDao;
    }

    @Resource(name = "pageModelGroupInfoDAO_HI_RO")
    public void setPageModelGroupInfoRoDao(BaseViewObject pageModelGroupInfoRoDao) {
        this.pageModelGroupInfoRoDao = pageModelGroupInfoRoDao;
    }

    @Override
    public PageModelGroupInfoEntity_HI saveOrUpdate(JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException,
                                                                                             IllegalAccessException {
        PageModelGroupInfoEntity_HI instance = Util.setEntity(new PageModelGroupInfoEntity_HI(), parameter, pageModelGroupInfoDao, parameter.opt("groupId"), userId);
        pageModelGroupInfoDao.saveOrUpdate(instance);
        return instance;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null)
            return false;
        PageModelGroupInfoEntity_HI instance = (PageModelGroupInfoEntity_HI)pageModelGroupInfoDao.getById(id);
        if (instance == null)
            return false;
        pageModelGroupInfoDao.delete(instance);
        return true;
    }

    @Override
    public Pagination<PageModelGroupInfoEntity_HI_RO> find(com.alibaba.fastjson.JSONObject params, int nowPage, int pageSize) {
        return Util.autoSearchPagination(pageModelGroupInfoRoDao, PageModelGroupInfoEntity_HI_RO.query, params, nowPage, pageSize);
    }

    @Override
    public List<PageModelGroupInfoEntity_HI> findByProperty(String name, Object value) {
        return pageModelGroupInfoDao.findByProperty(name, value);
    }

    @Override
    public List<PageModelGroupInfoEntity_HI> findByProperty(Map<String, Object> map) {
        return pageModelGroupInfoDao.findByProperty(map);
    }

    @Override
    public PageModelGroupInfoEntity_HI findById(Integer id) {
        return (PageModelGroupInfoEntity_HI)pageModelGroupInfoDao.getById(id);
    }

}
