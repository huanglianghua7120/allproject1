package saaf.common.fmw.spm.model.inter.server;

import saaf.common.fmw.spm.model.inter.ISrmSpmTplDimension;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;

import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.spm.model.entities.SrmSpmTplDimensionEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplDimensionEntity_HI_RO;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("srmSpmTplDimensionServer")
public class SrmSpmTplDimensionServer implements ISrmSpmTplDimension {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmTplDimensionServer.class);

    @Autowired
    private ViewObject<SrmSpmTplDimensionEntity_HI> srmSpmTplDimensionDAO_HI;

    @Autowired
    private BaseViewObject<SrmSpmTplDimensionEntity_HI_RO> srmSpmTplDimensionDAO_HI_RO;

    public SrmSpmTplDimensionServer() {
        super();
    }
    /**
     * 查询评分维度
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmSpmTplDimensionEntity_HI> findSrmSpmTplDimensionInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<SrmSpmTplDimensionEntity_HI> findListResult = srmSpmTplDimensionDAO_HI.findList("FROM SrmSpmTplDimensionEntity_HI", queryParamMap);
        return findListResult;
    }
    /**
     * 保存评分维度
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Object saveSrmSpmTplDimensionInfo(JSONObject queryParamJSON) {
        SrmSpmTplDimensionEntity_HI srmSpmTplDimensionEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmSpmTplDimensionEntity_HI.class);
        Object resultData = srmSpmTplDimensionDAO_HI.save(srmSpmTplDimensionEntity_HI);
        return resultData;
    }

    /**
     * 获取
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmSpmTplDimensionEntity_HI_RO> getDimensionLineList(JSONObject jsonParams) {
        LOGGER.info("" + jsonParams);
        StringBuffer queryParam = new StringBuffer(SrmSpmTplDimensionEntity_HI_RO.QUERY_DIMENSION_INFO_LIST);
        Map<String, Object> queryParamMap = new HashMap();
        SaafToolUtils.parperParam(jsonParams, "sd.tpl_id", "tplId", queryParam, queryParamMap, "=");
        List<SrmSpmTplDimensionEntity_HI_RO> list = srmSpmTplDimensionDAO_HI_RO.findList(queryParam, queryParamMap);
        return list;
    }

}
