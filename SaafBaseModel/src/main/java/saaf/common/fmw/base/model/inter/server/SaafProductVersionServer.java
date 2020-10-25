package saaf.common.fmw.base.model.inter.server;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafProductVersionEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafProductVersionEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafProductVersion;
import saaf.common.fmw.common.utils.SaafToolUtils;


/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafProductVersion.java
 * Description：项目维护护
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
@Component("saafProductVersionServer")
public class SaafProductVersionServer implements ISaafProductVersion {

    private final static Logger log = LoggerFactory.getLogger(SaafProductVersionServer.class);

;    @Autowired
    private BaseViewObject<SaafProductVersionEntity_HI_RO> saafProductVersionDAO_HI_RO;

    @Autowired
    private ViewObject<SaafProductVersionEntity_HI> saafProductVersionDAO_HI;

    /**
     * @param
     * @param nowPage
     * @param pageSzie
     * @param userId
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    @Override
    public Pagination findProductVersion(JSONObject jsonParams, int nowPage, int pageSzie, int userId) {
        StringBuffer querySql = new StringBuffer(SaafProductVersionEntity_HI_RO.query);
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(jsonParams, "spv.PRODUCT_CODE", "productCode", querySql, map, "like");
        SaafToolUtils.parperParam(jsonParams, "spv.PRODUCT_NAME", "productName", querySql, map, "like");
        if (jsonParams.getDate("updateDate") != null) {
            Date dt = new Date(jsonParams.getDate("updateDate").getTime() + 1000 * 60 * 60 * 24);
            querySql.append(" and spv.UPDATE_DATE>=:startTime and UPDATE_DATE<:endTime ");
            map.put("startTime", jsonParams.getDate("updateDate"));
            map.put("endTime", dt);
        }
        SaafToolUtils.parperParam(jsonParams, "spv.PRODUCT_VERSION_ID", "productVersionId", querySql, map, "like");
        String countSql = "select count(1) from (" + querySql + ")";
        querySql.append(" order by spv.UPDATE_DATE desc");
        Pagination<SaafProductVersionEntity_HI_RO> result = saafProductVersionDAO_HI_RO.findPagination(querySql,countSql, map, nowPage, pageSzie);
        return result;
    }
    /**
     *新增产品版本
     * @param instance
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    @Override
    public boolean saveProductVersion(SaafProductVersionEntity_HI instance, int userId) {
        if (instance == null)
            return false;
        instance.setProductVersionId(null);
        instance.setCreatedBy(userId);
        instance.setCreationDate(new Date());
        instance.setLastUpdateDate(new Date());
        instance.setLastUpdateLogin(userId);
        instance.setUpdateDate(new Date());
        instance.setLastUpdatedBy(userId);
        instance.setDownloadNumber(0);
        if (StringUtils.isBlank(instance.getEnableFlag()))
            instance.setEnableFlag("Y");
        if (StringUtils.isBlank(instance.getForcedDownloadFlag()))
            instance.setForcedDownloadFlag("Y");
        saafProductVersionDAO_HI.save(instance);
        return true;
    }

    /**
     * 修改产品版本信息
     * @param instance
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    @Override
    public SaafProductVersionEntity_HI editProductVersion(SaafProductVersionEntity_HI instance, int userId) {
        if (instance.getProductVersionId() == null)
            return null;
        SaafProductVersionEntity_HI source = saafProductVersionDAO_HI.getById(instance.getProductVersionId());
        if (source == null)
            return instance;
        if (StringUtils.isNotBlank(instance.getProductName()))
            source.setProductName(instance.getProductName());
        if (StringUtils.isNotBlank(instance.getProductCode()))
            source.setProductCode(instance.getProductCode());
        if (StringUtils.isNotBlank(instance.getForcedDownloadFlag()))
            source.setForcedDownloadFlag(instance.getForcedDownloadFlag());
        if (StringUtils.isNotBlank(instance.getDownloadUrl()))
            source.setDownloadUrl(instance.getDownloadUrl());
        if (StringUtils.isNotBlank(instance.getEnableFlag()))
            source.setEnableFlag(instance.getEnableFlag());
        source.setVersionRemark(instance.getVersionRemark());
        if (instance.getVersionNumber() != null)
            source.setVersionNumber(instance.getVersionNumber());
        if (StringUtils.isNotBlank(instance.getProgramName()))
            source.setProgramName(instance.getProgramName());
        if (StringUtils.isNotBlank(instance.getReleasePlatform()))
            source.setReleasePlatform(instance.getReleasePlatform());
        if (instance.getDownloadNumber() != null)
            source.setDownloadNumber(instance.getDownloadNumber());
        source.setUpdateDate(new Date());
        source.setLastUpdateDate(new Date());
        source.setLastUpdatedBy(userId);
        saafProductVersionDAO_HI.update(source);
        return source;
    }

    /**
     *
     * @param id
     * @param userId
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public boolean deleteProductVersion(Integer id, int userId) {
        if (id == null)
            return false;
        try {
            SaafProductVersionEntity_HI saafProductVersionEntity_HI = saafProductVersionDAO_HI.getById(id);
            saafProductVersionDAO_HI.delete(saafProductVersionEntity_HI);
            return true;
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }

    /**
     * 唯一性校验
     *
     * @param instance
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public boolean uniqueCheck(SaafProductVersionEntity_HI instance) {
        if (instance == null)
            return true;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("productCode", instance.getProductCode());
        map.put("productName", instance.getProductName());
        List list = saafProductVersionDAO_HI.findByProperty(map);
        if (list.size() > 0)
            return false;
        return true;
    }
}
