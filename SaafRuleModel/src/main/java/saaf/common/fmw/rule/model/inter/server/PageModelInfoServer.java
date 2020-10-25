package saaf.common.fmw.rule.model.inter.server;


import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.model.inter.server.GenerateCodeServer;
import saaf.common.fmw.rule.constant.Constant;
import saaf.common.fmw.rule.model.entities.PageModelInfoEntity_HI;
import saaf.common.fmw.rule.model.entities.readonly.PageModelInfoEntity_HI_RO;
import saaf.common.fmw.rule.model.inter.IPageModelInfoServer;
import saaf.common.fmw.rule.utils.Util;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on Thu Jul 06 17:24:57 CST 2017
 */
@Component("pageModelInfoServer")
public class PageModelInfoServer implements IPageModelInfoServer {

    private ViewObject pageModelInfoDao;
    private BaseViewObject pageModelInfoRoDao;

    @Autowired
    private GenerateCodeServer generateCodeServer;

    @Resource(name = "pageModelInfoDAO_HI")
    public void setPageModelInfoDao(ViewObject pageModelInfoDao) {
        this.pageModelInfoDao = pageModelInfoDao;
    }

    @Resource(name = "pageModelInfoDAO_HI_RO")
    public void setPageModelInfoRoDao(BaseViewObject pageModelInfoRoDao) {
        this.pageModelInfoRoDao = pageModelInfoRoDao;
    }

    @Override
    public PageModelInfoEntity_HI saveOrUpdate(JSONObject parameter, int userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException,
                                                                                        IllegalAccessException {

        PageModelInfoEntity_HI instance = Util.setEntity(new PageModelInfoEntity_HI(), parameter, pageModelInfoDao, parameter.opt("modelId"), userId);
        if (StringUtils.isBlank(instance.getModelCode())) {
            String seq = Constant.MODELCODE_SEQ + Constant.DATEFORMAT_DATE_SEQ.format(new Date());
            instance.setModelCode(generateCodeServer.generateCode(seq, seq, 4));
        }
        pageModelInfoDao.saveOrUpdate(instance);
        return instance;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null)
            return false;
        PageModelInfoEntity_HI instance = (PageModelInfoEntity_HI)pageModelInfoDao.getById(id);
        if (instance == null)
            return false;
        pageModelInfoDao.delete(instance);
        return true;
    }

    @Override
    public Pagination<PageModelInfoEntity_HI_RO> find(com.alibaba.fastjson.JSONObject params, int nowPage, int pageSize) {
        return Util.autoSearchPagination(pageModelInfoRoDao, PageModelInfoEntity_HI_RO.query, params, nowPage, pageSize);
    }

    @Override
    public List<PageModelInfoEntity_HI> findByProperty(String name, Object value) {
        return pageModelInfoDao.findByProperty(name, value);
    }

    @Override
    public List<PageModelInfoEntity_HI> findByProperty(Map<String, Object> map) {
        return pageModelInfoDao.findByProperty(map);
    }

    @Override
    public PageModelInfoEntity_HI findById(Integer id) {
        return (PageModelInfoEntity_HI)pageModelInfoDao.getById(id);
    }

}
