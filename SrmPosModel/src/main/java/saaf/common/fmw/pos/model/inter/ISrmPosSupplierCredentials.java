package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierCredentialsEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierCredentialsEntity_HI_RO;

import java.util.List;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商资质信息
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmPosSupplierCredentials {
    /**
     * 查询供应商的资质信息
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosSupplierCredentialsEntity_HI_RO> findSrmPosSupplierCredentialsInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows);
    /**
     * 保存供应商的资质信息（档案自助维护/内部创建供应商）
     * @param credentialsList
     * @param userId
     * @param supplierId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public boolean saveSrmPosSupplierCredentialsInfo(List<SrmPosSupplierCredentialsEntity_HI> credentialsList, Integer userId, Integer supplierId);

    /**
     * 保存供应商的资质信息——供应商接口（数据输入）
     * @param supplierCredentialsInfo
     * @param userId
     * @param supplierId
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public void saveSupplierCredentialsInfoExternal(SrmPosSupplierCredentialsEntity_HI supplierCredentialsInfo, Integer userId, Integer supplierId);

    /**
     * 校验供应商的资质信息必填项——供应商接口（数据输入）
     * @param supplierCredentialsInfo
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public String judgeSpaceSupplierCredentialsInfo(SrmPosSupplierCredentialsEntity_HI supplierCredentialsInfo);
}
