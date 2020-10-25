package saaf.common.fmw.pos.model.inter.server;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.pos.model.entities.SrmPosLocaleReviewCatesEntity_HI;
import saaf.common.fmw.pos.model.inter.ISrmPosLocaleReviewCates;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：现场考察地点
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
@Component("srmPosLocaleReviewCatesServer")
public class SrmPosLocaleReviewCatesServer implements ISrmPosLocaleReviewCates {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosLocaleReviewCatesServer.class);

    @Autowired
    private ViewObject<SrmPosLocaleReviewCatesEntity_HI> srmPosLocaleReviewCatesDAO_HI;

    public SrmPosLocaleReviewCatesServer() {
        super();
    }
    /**
     *
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmPosLocaleReviewCatesEntity_HI> findSrmPosLocaleReviewCatesInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<SrmPosLocaleReviewCatesEntity_HI> findListResult = srmPosLocaleReviewCatesDAO_HI.findList("from SrmPosLocaleReviewCatesEntity_HI", queryParamMap);
        return findListResult;
    }

    public Object saveSrmPosLocaleReviewCatesInfo(JSONObject queryParamJSON) {
        SrmPosLocaleReviewCatesEntity_HI srmPosLocaleReviewCatesEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmPosLocaleReviewCatesEntity_HI.class);
        Object resultData = srmPosLocaleReviewCatesDAO_HI.save(srmPosLocaleReviewCatesEntity_HI);
        return resultData;
    }
}
