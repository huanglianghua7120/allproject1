package saaf.common.fmw.okc.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.readonly.SaafBaseResultFileEntity_HI_RO;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.okc.model.entities.SrmOkcContractPartiesEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractPartiesEntity_HI_RO;
import saaf.common.fmw.okc.model.dao.SrmOkcContractPartiesDAO_HI;
import saaf.common.fmw.okc.model.dao.readonly.SrmOkcContractPartiesDAO_HI_RO;
import saaf.common.fmw.okc.model.entities.SrmOkcContractItemsEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractItemsEntity_HI_RO;
import saaf.common.fmw.okc.model.dao.SrmOkcContractItemsDAO_HI;
import saaf.common.fmw.okc.model.dao.readonly.SrmOkcContractItemsDAO_HI_RO;
import saaf.common.fmw.okc.model.entities.SrmOkcContractTextsEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractTextsEntity_HI_RO;
import saaf.common.fmw.okc.model.dao.SrmOkcContractTextsDAO_HI;
import saaf.common.fmw.okc.model.dao.readonly.SrmOkcContractTextsDAO_HI_RO;
import saaf.common.fmw.okc.model.entities.SrmOkcContractFreightsEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractFreightsEntity_HI_RO;
import saaf.common.fmw.okc.model.dao.SrmOkcContractFreightsDAO_HI;
import saaf.common.fmw.okc.model.dao.readonly.SrmOkcContractFreightsDAO_HI_RO;
import saaf.common.fmw.okc.model.entities.SrmOkcContractAttachmentsEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractAttachmentsEntity_HI_RO;
import saaf.common.fmw.okc.model.dao.SrmOkcContractAttachmentsDAO_HI;
import saaf.common.fmw.okc.model.dao.readonly.SrmOkcContractAttachmentsDAO_HI_RO;
import saaf.common.fmw.okc.model.entities.SrmOkcContractPaymentsEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractPaymentsEntity_HI_RO;
import saaf.common.fmw.okc.model.dao.SrmOkcContractPaymentsDAO_HI;
import saaf.common.fmw.okc.model.dao.readonly.SrmOkcContractPaymentsDAO_HI_RO;
import saaf.common.fmw.okc.model.entities.SrmOkcContractsEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractsEntity_HI_RO;
import saaf.common.fmw.okc.model.dao.SrmOkcContractsDAO_HI;
import saaf.common.fmw.okc.model.dao.readonly.SrmOkcContractsDAO_HI_RO;
import saaf.common.fmw.okc.model.inter.ISrmOkcContracts;
import saaf.common.fmw.po.model.entities.readonly.SrmPoHeadersEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierBankEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierContactsEntity_HI_RO;

import java.util.List;
import java.util.Map;

public interface ISrmOkcContracts {

    /**
     * 查询
     *
     * @param parameters
     * @return
     */
//    public Object saveSrmOkcContractParties(JSONObject parameters) throws Exception;

    /**
     * 查询合同的交易方
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmOkcContractPartiesEntity_HI_RO> findSrmOkcContractPartiesList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 根据ID查询合同交易方
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SrmOkcContractPartiesEntity_HI_RO findSrmOkcContractPartiesById(JSONObject parameters) throws Exception;


    /**
     * 保存合同的标的物
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Object saveSrmOkcContractItems(JSONObject parameters) throws Exception;

    /**
     * Description：查询合同标的物列表
     * @param parameters 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    List<SrmOkcContractItemsEntity_HI_RO> findSrmOkcContractItemsList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询合同的标的物
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SrmOkcContractItemsEntity_HI_RO findSrmOkcContractItemsById(JSONObject parameters) throws Exception;
    /**
     * 删除合同的标的物
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    void deleteSrmOkcContractItem(JSONObject parameters) throws Exception;


    /**
     * 保存合同文本
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Object saveSrmOkcContractTexts(JSONObject parameters) throws Exception;
    /**
     * 保存合同文本
     * @param text
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Object saveSrmOkcContractTexts(SrmOkcContractTextsEntity_HI text) throws Exception;

    /**
     * 查询合同文本
     * @param parameters
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmOkcContractTextsEntity_HI_RO> findSrmOkcContractTextsList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 根据ID查询合同文本
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SrmOkcContractTextsEntity_HI_RO findSrmOkcContractTextsById(JSONObject parameters) throws Exception;


    /**
     * 保存合同运货单
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Object saveSrmOkcContractFreights(JSONObject parameters) throws Exception;

    /**
     * 查询运输方式列表
     * @param parameters
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmOkcContractFreightsEntity_HI_RO> findSrmOkcContractFreightsList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 根据ID查询合同运货单
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SrmOkcContractFreightsEntity_HI_RO findSrmOkcContractFreightsById(JSONObject parameters) throws Exception;


    /**
     * 保存合同附件
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Object saveSrmOkcContractAttachments(JSONObject parameters) throws Exception;

    /**
     * 查询合同附件
     * @param parameters
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmOkcContractAttachmentsEntity_HI_RO> findSrmOkcContractAttachmentsList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 根据ID查询合同附件
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SrmOkcContractAttachmentsEntity_HI_RO findSrmOkcContractAttachmentsById(JSONObject parameters) throws Exception;


    /**
     * 保存合同付款方式
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Object saveSrmOkcContractPayments(JSONObject parameters) throws Exception;

    /**
     * 查询该合同的付款阶段信息
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SrmOkcContractPaymentsEntity_HI_RO> findSrmOkcContractPaymentsList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 根据合同ID，查询该合同的付款阶段信息
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SrmOkcContractPaymentsEntity_HI_RO findSrmOkcContractPaymentsById(JSONObject parameters) throws Exception;


    /**
     * Description：保存合同
     * @param parameters 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public Object saveSrmOkcContracts(JSONObject parameters) throws Exception;

    /**
     * Description：查询合同列表
     * @param parameters 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public Pagination findSrmOkcContractsList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：根据ID查询合同
     * @param parameters 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public SrmOkcContractsEntity_HI_RO findSrmOkcContractsById(JSONObject parameters) throws Exception;
    /**
     * 更新在线编辑文档
     * @param userId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    boolean updateEditFile(Integer contractId, Integer fileId, Integer userId) throws Exception;
    /**
     * 查询合同交易方联系人
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmPosSupplierContactsEntity_HI_RO> findSrmOkcContractPerson(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询合同银行
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmPosSupplierBankEntity_HI_RO> findSrmOkcContractBank(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * Description：合同查询供应商地点
     * @param parameters 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    Pagination<JSONObject> findSrmOkcContractSite(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 根据ID加载合同
     * @param contractId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    SrmOkcContractsEntity_HI loadSrmOkcContractsById(Integer contractId) throws Exception;
    /**
     * 根据ID保存合同
     * @param contract
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    void updateSrmOkcContractsById(SrmOkcContractsEntity_HI contract) throws Exception;
    /**
     * 更新合同文本
     * @param fileId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    void updateTextFile(Integer id, Integer fileId);
    /**
     * 根据ID查询合同模板
     * @param id
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    SaafBaseResultFileEntity_HI_RO findTemplateFileById(Integer id);
    /**
     * 保存变更合同
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Object saveRenewSrmOkcContracts(JSONObject parameters) throws Exception;
    /**
     * 更新合同状态
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    void updateSrmOkcContractStatus(JSONObject parameters) throws Exception;
    /**
     * 查询合同采购订单
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SrmPoHeadersEntity_HI_RO> findSrmOkcContractPoList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 删除运输方式
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    void deleteSrmOkcContractFreightExpense(JSONObject parameters) throws Exception;
    /**
     * 删除合同付款阶段
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    void deleteSrmOkcContractPayment(JSONObject parameters) throws Exception;
    /**
     * 查询合同预警
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination findSrmOkcContractsWarnList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;


    /**
     * 查询合同的补充合同列表
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination findSubContractsList(JSONObject parameters, Integer pageIndex, Integer pageRows);

    /**
     * 删除合同附件
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject deleteSrmOkcContractAttachments(JSONObject params) throws Exception;


    /**
     * Description：提交合同发送至EKP系统
     *
     * @param contractId 合同ID
     * @param userId     作者ID
     * @return JSONObject
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-05-29         xiexiaoxia      创建
     * =======================================================================
     */
    JSONObject saveOkcContractsToEkp(Integer contractId, Integer userId,String contractType) throws Exception;
}
