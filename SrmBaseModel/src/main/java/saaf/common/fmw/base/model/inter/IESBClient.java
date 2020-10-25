package saaf.common.fmw.base.model.inter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierBankEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierContactsEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierInfoEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierSitesEntity_HI_RO;

import java.util.List;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：ESB总线接口
 *
 * Update History
 * ==============================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019-11-12     zhj             创建
 * ==============================================================================
 */
public interface IESBClient {
    /**
     * Description：根据EKP认证流程单号获取EKP数据
     * @param jsonParams
     * @return
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-11-12     zhj             创建
     * ==============================================================================
     */
    public JSONObject findEkpNumberInfoByEkpNumber(JSONObject jsonParams) throws Exception;

    /**
     * Description：将供应商档案信息（供应商基础信息、地点、银行、联系人）推送到EBS
     * @param supplierId  标识供应商supplierId，用于日志查询供应商
     * @param actionCode  创建和修改业务为NORMAL，冻结和冻结恢复为FREEZE
     * @param freezeRecoveryFlag  是否冻结恢复(Y/N)，Y为冻结恢复
     * @param supplierInfo
     * @param siteList
     * @param contactsList
     * @param bankList
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject findSupplierInfoSendToEBS(Integer supplierId, String actionCode, String freezeRecoveryFlag, SrmPosSupplierInfoEntity_HI_RO supplierInfo, List<SrmPosSupplierSitesEntity_HI_RO> siteList, List<SrmPosSupplierContactsEntity_HI_RO> contactsList, List<SrmPosSupplierBankEntity_HI_RO> bankList) throws Exception;
    /**
     * Description：付款条件定时任务接口list
     * @param businessData
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-12-20     zhj             创建
     * ==============================================================================
     */
    public Integer updateSrmPonPaymentTermsListJob(JSONArray businessData);

    /**
     * Description：物资编码申请接口定时任务List
     * @param businessData
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-12-30     zhj             创建
     * ==============================================================================
     */
    public Integer updateSrmPosGoodsCodeHeaderListJob(JSONArray businessData);
}
