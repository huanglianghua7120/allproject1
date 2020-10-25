package saaf.common.fmw.spm.model.inter.server;

import saaf.common.fmw.spm.model.inter.ISrmTplCalculateTempItem;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;
import saaf.common.fmw.spm.model.entities.SrmTplCalculateTempItemEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("srmTplCalculateTempItemServer")
public class SrmTplCalculateTempItemServer implements ISrmTplCalculateTempItem {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmTplCalculateTempItemServer.class);
    @Autowired
    private ViewObject<SrmTplCalculateTempItemEntity_HI> srmTplCalculateTempItemDAO_HI;

    public SrmTplCalculateTempItemServer() {
        super();
    }

    public List<SrmTplCalculateTempItemEntity_HI> findSrmTplCalculateTempItemInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<SrmTplCalculateTempItemEntity_HI> findListResult = srmTplCalculateTempItemDAO_HI.findList("FROM SrmTplCalculateTempItemEntity_HI", queryParamMap);
        return findListResult;
    }

    public Object saveSrmTplCalculateTempItemInfo(JSONObject queryParamJSON) {
        SrmTplCalculateTempItemEntity_HI srmTplCalculateTempItemEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmTplCalculateTempItemEntity_HI.class);
        Object resultData = srmTplCalculateTempItemDAO_HI.save(srmTplCalculateTempItemEntity_HI);
        return resultData;
    }

}
