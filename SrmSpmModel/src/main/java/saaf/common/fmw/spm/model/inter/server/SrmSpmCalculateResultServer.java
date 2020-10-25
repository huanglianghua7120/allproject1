package saaf.common.fmw.spm.model.inter.server;

import saaf.common.fmw.spm.model.inter.ISrmSpmCalculateResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;
import saaf.common.fmw.spm.model.entities.SrmSpmCalculateResultEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("srmSpmCalculateResultServer")
public class SrmSpmCalculateResultServer implements ISrmSpmCalculateResult {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmCalculateResultServer.class);
    @Autowired
    private ViewObject<SrmSpmCalculateResultEntity_HI> srmSpmCalculateResultDAO_HI;

    public SrmSpmCalculateResultServer() {
        super();
    }
    /**
     * 查询绩效计算结果
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmSpmCalculateResultEntity_HI> findSrmSpmCalculateResultInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<SrmSpmCalculateResultEntity_HI> findListResult = srmSpmCalculateResultDAO_HI.findList("from SrmSpmCalculateResultEntity_HI", queryParamMap);
        return findListResult;
    }
    /**
     * 保存绩效计算结果
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Object saveSrmSpmCalculateResultInfo(JSONObject queryParamJSON) {
        SrmSpmCalculateResultEntity_HI srmSpmCalculateResultEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmSpmCalculateResultEntity_HI.class);
        Object resultData = srmSpmCalculateResultDAO_HI.save(srmSpmCalculateResultEntity_HI);
        return resultData;
    }
}
