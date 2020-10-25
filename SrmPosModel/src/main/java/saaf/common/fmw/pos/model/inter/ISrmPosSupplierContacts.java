package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierContactsEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierContactsEntity_HI_RO;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商联系人
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmPosSupplierContacts {
    /**
     * 删除联系人
     *
     * @param jsonParams
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject deleteContact(JSONObject jsonParams);

    /**
     * 保存供应商的联系人（档案自助维护/内部创建供应商）
     *
     * @param supplierContactsList
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
    public boolean saveSrmPosSupplierContactsInfo(List<SrmPosSupplierContactsEntity_HI> supplierContactsList, Integer userId, Integer supplierId);

    /**
     * 保存供应商的联系人——供应商接口（数据输入）
     *
     * @param supplierContactsList
     * @param userId
     * @param supplierId
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public void saveSrmPosSupplierContactsInfoExternal(List<SrmPosSupplierContactsEntity_HI> supplierContactsList, Integer userId, Integer supplierId);

    /**
     * 校验供应商的联系人必填项——供应商接口（数据输入）
     *
     * @param supplierContactsList
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public String judgeSpaceSupplierContacts(List<SrmPosSupplierContactsEntity_HI> supplierContactsList);

    /**
     * 查询供应商的联系人
     *
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
    public Pagination<SrmPosSupplierContactsEntity_HI_RO> findSupplierContacts(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

    public JSONObject saveSupplierContacts(JSONArray paramDataList, int userId, int supplierId) throws Exception;

    /**
     * 获取供应商的第一个联系人
     * @param supplierId
     * @return
     */
    public SrmPosSupplierContactsEntity_HI_RO findFirstContact(Integer supplierId);
    /**
     * 注册审核-重置密码
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmPosSupplierContactsEntity_HI_RO> findSupplierContact(Integer supplierId);
}
