package saaf.common.fmw.pos.model.inter.server;

import com.yhg.hibernate.core.dao.BaseViewObject;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierQuitSitesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierQuitSites;
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
import saaf.common.fmw.pos.model.entities.SrmPosSupplierQuitSitesEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商退出地点
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmPosSupplierQuitSitesServer")
public class SrmPosSupplierQuitSitesServer implements ISrmPosSupplierQuitSites {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierQuitSitesServer.class);

    @Autowired
    private ViewObject<SrmPosSupplierQuitSitesEntity_HI> srmPosSupplierQuitSitesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosSupplierQuitSitesEntity_HI_RO> srmPosSupplierQuitSitesDAO_HI_RO;

    public SrmPosSupplierQuitSitesServer() {
        super();
    }
    /**
     *  获取供应商地址
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosSupplierQuitSitesEntity_HI> findSrmPosSupplierQuitSitesInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<SrmPosSupplierQuitSitesEntity_HI> findListResult = srmPosSupplierQuitSitesDAO_HI.findList("from SrmPosSupplierQuitSitesEntity_HI", queryParamMap);
        return findListResult;
    }

    @Override
    public Object saveSrmPosSupplierQuitSitesInfo(JSONObject queryParamJSON) {
        SrmPosSupplierQuitSitesEntity_HI srmPosSupplierQuitSitesEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmPosSupplierQuitSitesEntity_HI.class);
        Object resultData = srmPosSupplierQuitSitesDAO_HI.save(srmPosSupplierQuitSitesEntity_HI);
        return resultData;
    }

    /**
     * 查询供应商地点
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosSupplierQuitSitesEntity_HI_RO> findSupplierSites(JSONObject jsonParams) {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPosSupplierQuitSitesEntity_HI_RO.QUERY_SITES);
        SaafToolUtils.parperParam(jsonParams, "t.supplier_quit_id", "supplierQuitId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.supplier_address_id", "supplierAddressId", queryString, queryParamMap, "=");
        List<SrmPosSupplierQuitSitesEntity_HI_RO> rowSet = srmPosSupplierQuitSitesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        return rowSet;
    }
}
