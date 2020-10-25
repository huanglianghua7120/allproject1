package saaf.common.fmw.pos.model.inter;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.pos.model.entities.SrmPosSupplierCertificateEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierCertificateEntity_HI_RO;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商认证与证书
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmPosSupplierCertificate {
    /**
     *删除认证与证书
     * @param jsonParams
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject deleteCertificate(JSONObject jsonParams);

    /**
     * 保存供应商的认证与证书（档案自助维护/内部创建供应商）
     * @param certificateList
     * @param userId
     * @param supplierId
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public boolean saveSrmPosSupplierCertificateInfo(List<SrmPosSupplierCertificateEntity_HI> certificateList,Integer userId,Integer supplierId);

    /**
     * 保存供应商的认证与证书——供应商接口（数据输入）
     * @param supplierCertificateInfoList
     * @param userId
     * @param supplierId
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public void saveSupplierCertificateInfoListExternal(List<SrmPosSupplierCertificateEntity_HI> supplierCertificateInfoList,Integer userId,Integer supplierId);

    /**
     * 校验供应商的认证与证书必填项——供应商接口（数据输入）
     * @param supplierCertificateInfoList
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public String judgeSpaceSupplierCertificate(List<SrmPosSupplierCertificateEntity_HI> supplierCertificateInfoList);
    /**
     * 查询供应商的认证与证书
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosSupplierCertificateEntity_HI_RO> findSupplierCertificate(JSONObject jsonParams, Integer pageIndex, Integer pageRows);
    
    /**
     * 资质到期查询
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosSupplierCertificateEntity_HI_RO> findSupplierCertificateDue(JSONObject jsonParams, Integer pageIndex, Integer pageRows);


    /**
     * 根据资质有效时间自动创建预警
     * @return
     */
    public String saveCreateWarning() throws Exception;
}
