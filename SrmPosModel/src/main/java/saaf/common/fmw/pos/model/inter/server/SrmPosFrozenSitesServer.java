package saaf.common.fmw.pos.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yhg.base.utils.SToolUtils;

import org.springframework.stereotype.Component;

import saaf.common.fmw.pos.model.entities.SrmPosFrozenSitesEntity_HI;
import saaf.common.fmw.pos.model.inter.ISrmPosFrozenSites;

import com.yhg.hibernate.core.dao.ViewObject;

@Component("srmPosFrozenSitesServer")
public class SrmPosFrozenSitesServer implements ISrmPosFrozenSites {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosFrozenSitesServer.class);
    @Autowired
    private ViewObject<SrmPosFrozenSitesEntity_HI> srmPosFrozenSitesDAO_HI;

    public SrmPosFrozenSitesServer() {
        super();
    }

    public List<SrmPosFrozenSitesEntity_HI> findSrmPosFrozenSitesInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<SrmPosFrozenSitesEntity_HI> findListResult = srmPosFrozenSitesDAO_HI.findList("from SrmPosFrozenSitesEntity_HI", queryParamMap);
        return findListResult;
    }

    public Object saveSrmPosFrozenSitesInfo(JSONObject queryParamJSON) {
        SrmPosFrozenSitesEntity_HI srmPosFrozenSitesEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmPosFrozenSitesEntity_HI.class);
        Object resultData = srmPosFrozenSitesDAO_HI.save(srmPosFrozenSitesEntity_HI);
        return resultData;
    }
}
