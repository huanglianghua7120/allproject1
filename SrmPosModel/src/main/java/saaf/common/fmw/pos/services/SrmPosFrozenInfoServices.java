package saaf.common.fmw.pos.services;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.intf.model.inter.IIntfSupplier;
import saaf.common.fmw.pon.model.entities.readonly.SrmPosFrozenCategoriesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosFrozenInfoEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierAddressesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierSitesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.IFrozenInfo;
import saaf.common.fmw.pos.model.inter.ISrmPosFrozenCategories;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierAddresses;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierSites;
import saaf.common.fmw.services.CommonAbstractServices;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商冻结、恢复
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     hgq             创建
 * ===========================================================================
 */
@Path("/srmPosFrozenInfoServices")
@Component
public class SrmPosFrozenInfoServices extends CommonAbstractServices {
	
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosFrozenInfoServices.class);
	
	@Autowired
	private IFrozenInfo srmPosFrozenInfoServer;	
	@Autowired
	private IIntfSupplier intfSupplierServer;
	@Autowired
	private ISrmPosSupplierAddresses srmPosSupplierAddressesServer;
	@Autowired
	private ISrmPosSupplierSites srmPosSupplierSitesServer;
	@Autowired
	private ISrmPosFrozenCategories srmPosFrozenCategoriesServer;
	
	
	/**   
     * 查询列表
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findFrozenInfoList")
    @POST
    public String findFrozenInfoList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info("------jsonParam-----" + jsonParam.toString());
            Pagination<SrmPosFrozenInfoEntity_HI_RO> infoList = srmPosFrozenInfoServer.findFrozenInfoList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("查询列表异常：" + e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询列表失败！", 0, null);
        }
    }
    
    /**
     * 查询供应商地址
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSupplierAddresses")
    public String findSupplierAddresses(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            List<SrmPosSupplierAddressesEntity_HI_RO> LookupCodelist = srmPosSupplierAddressesServer.findSupplierAddressesById(jsonParams);
            LOGGER.info("--->>findSupplierAddresses-->>参数：" + params + "查询供应商地址成功！");
            return CommonAbstractServices.convertResultJSONObj("S", "查询供应商地址成功！", LookupCodelist.size(), LookupCodelist);
        } catch (Exception e) {
            LOGGER.error("查询供应商地址失败" + e,e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询供应商地址失败！", 0, null);
        }
    }
    
    /**
     * 查询供应商地点
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmPosSupplierSites")
    public String findSrmPosSupplierSites(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            List<SrmPosSupplierSitesEntity_HI_RO> LookupCodelist = srmPosSupplierSitesServer.findSites(jsonParams);
//            List<SrmPosSupplierSitesEntity_HI_RO> LookupCodelist = srmPosSupplierSitesServer.findSrmPosSupplierSites(jsonParams);
            LOGGER.info("--->>findSrmPosSupplierSites-->>参数：" + params + "查询供应商地点成功！");
            return CommonAbstractServices.convertResultJSONObj("S", "查询供应商地点成功！", LookupCodelist.size(), LookupCodelist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询供应商地点失败" + e,e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询供应商地点失败!" + e, 0, null);
        }
    }
    
    /**
     * 查询冻结品类信息
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmPosFrozenCategories")
    public String findSrmPosFrozenCategories(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            List<SrmPosFrozenCategoriesEntity_HI_RO> LookupCodelist = srmPosFrozenCategoriesServer.findSrmPosFrozenCategoriesByCon(jsonParams);
            LOGGER.info("--->>findSrmPosSupplierSites-->>参数：" + params + "查询冻结品类信息成功！");
            return CommonAbstractServices.convertResultJSONObj("S", "查询冻结品类信息成功！", LookupCodelist.size(), LookupCodelist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询冻结品类信息失败" + e,e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询冻结品类信息失败!" + e, 0, null);
        }
    }

    /**
     * Description：保存
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * frozenId  冻结单ID  NUMBER  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  业务实体ID,关联表:srm_base_orgs  NUMBER  N
     * freezeNumber  冻结单据号  VARCHAR2  N
     * freezeStatus  冻结单状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * freezingInstructions  (废弃)原因  VARCHAR2  N
     * forbidPurchaseFlag  禁止采购  VARCHAR2  N
     * forbidPaymentFlag  禁止付款  VARCHAR2  N
     * unfrozenDate  解冻时间  DATE  N
     * description  说明  VARCHAR2  N
     * frozenFileId  供应商冻结相关文件id  NUMBER  N
     * approveBy  审批人  NUMBER  N
     * approveDate  审批时间  DATE  N
     * freezeItem  冻结的条目（SNBC_TYPE：地点或者品类）  VARCHAR2  N
     * oaNum  oa审批编号  VARCHAR2  N
     * freezeType  区分冻结和恢复单据  VARCHAR2  N
     * organizationId  库存组织id  NUMBER  N
     * frozenId  冻结单ID  NUMBER  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  (废弃)业务实体ID,关联表:srm_base_orgs  NUMBER  N
     * freezeType  类型(POS_FREEZE_TYPE  冻结或解冻)  VARCHAR2  N
     * freezeNumber  冻结单号  VARCHAR2  N
     * freezeStatus  冻结单状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * freezingInstructions  (废弃)原因  VARCHAR2  N
     * forbidPurchaseFlag  禁止采购  VARCHAR2  N
     * forbidPaymentFlag  禁止付款  VARCHAR2  N
     * unfrozenDate  解冻时间  DATE  N
     * description  说明  VARCHAR2  N
     * frozenFileId  供应商冻结相关文件id  NUMBER  N
     * approveBy  审批人  NUMBER  N
     * approveDate  审批时间  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    @Path("saveFrozenInfo")
    @POST
    public String saveFrozenInfo(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPosFrozenInfoServer.saveSupplierFrozenInfo(jsonParam);
            //JSONObject posJson = srmPosFrozenInfoServer.saveFrozenInfo(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (Exception e) {
            LOGGER.error("保存失败！" + e,e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "保存失败!" + e, 0, null);
        }
    }

    /**
     * 删除
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("deleteFrozenInfo")
    @POST
    public String deleteFrozenInfo(@FormParam("params")
        String params) {
    	LOGGER.info("删除信息，参数：" + params.toString());
    	try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPosFrozenInfoServer.deleteFrozenInfo(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (Exception e) {
            LOGGER.error("--->>删除失败！参数：" + params + ",异常：" + e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败!", 0, null);
        }
    }
    
    /**
     *查询
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findFrozenInfo")
    @POST
    public String findFrozenInfo(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Integer frozenId = jsonParam.getInteger("frozenId");
            /*if (frozenId == null || frozenId.equals("")) {
                return convertResultJSONObj("E", "请检查frozenId参数", 0, null);
            }*/
            List<SrmPosFrozenInfoEntity_HI_RO> frozenInfo = srmPosFrozenInfoServer.findFrozenInfo(frozenId);
            jsonParam.put("frozenInfo", frozenInfo);
            jsonParam.put("status", "S");
            jsonParam.put("msg", "查询成功");
            return jsonParam.toJSONString();
        } catch (Exception e) {
            LOGGER.error("查询失败！", e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return convertResultJSONObj("E", "查询失败!" + e, 0, null);
        }
    }

    /**
     * Description：提交供应商冻结、恢复信息
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * frozenId  冻结单ID  NUMBER  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  业务实体ID,关联表:srm_base_orgs  NUMBER  N
     * freezeNumber  冻结单据号  VARCHAR2  N
     * freezeStatus  冻结单状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * freezingInstructions  (废弃)原因  VARCHAR2  N
     * forbidPurchaseFlag  禁止采购  VARCHAR2  N
     * forbidPaymentFlag  禁止付款  VARCHAR2  N
     * unfrozenDate  解冻时间  DATE  N
     * description  说明  VARCHAR2  N
     * frozenFileId  供应商冻结相关文件id  NUMBER  N
     * approveBy  审批人  NUMBER  N
     * approveDate  审批时间  DATE  N
     * freezeItem  冻结的条目（SNBC_TYPE：地点或者品类）  VARCHAR2  N
     * oaNum  oa审批编号  VARCHAR2  N
     * freezeType  区分冻结和恢复单据  VARCHAR2  N
     * organizationId  库存组织id  NUMBER  N
     * frozenId  冻结单ID  NUMBER  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  (废弃)业务实体ID,关联表:srm_base_orgs  NUMBER  N
     * freezeType  类型(POS_FREEZE_TYPE  冻结或解冻)  VARCHAR2  N
     * freezeNumber  冻结单号  VARCHAR2  N
     * freezeStatus  冻结单状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * freezingInstructions  (废弃)原因  VARCHAR2  N
     * forbidPurchaseFlag  禁止采购  VARCHAR2  N
     * forbidPaymentFlag  禁止付款  VARCHAR2  N
     * unfrozenDate  解冻时间  DATE  N
     * description  说明  VARCHAR2  N
     * frozenFileId  供应商冻结相关文件id  NUMBER  N
     * approveBy  审批人  NUMBER  N
     * approveDate  审批时间  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Path("saveApprovalFrozenInfo")
    @POST
    public String saveApprovalFrozenInfo(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPosFrozenInfoServer.saveApprovalFrozenInfo(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (Exception e) {
            LOGGER.error("提交失败！" + e,e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "提交失败!" + e, 0, null);
        }
    }
    
    /**
     * 审批冻结信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("updateApprovalFrozenInfo")
    @POST
    public String updateApprovalFrozenInfo(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPosFrozenInfoServer.updateApprovalFrozenInfo(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (Exception e) {
            LOGGER.error("审批失败！" + e,e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "审批失败!" + e, 0, null);
        }
    }
    
    /**
     * 驳回
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("updateRejectFrozenInfo")
    @POST
    public String updateRejectFrozenInfo(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson =  srmPosFrozenInfoServer.updateRejectFrozenInfo(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (Exception e) {
            LOGGER.error("驳回失败！" + e,e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "驳回失败!" + e, 0, null);
        }
    }
    
    /**
     * 审批恢复信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("updateApprovalRecoveryInfo")
    @POST
    public String updateApprovalRecoveryInfo(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPosFrozenInfoServer.updateApprovalRecoveryInfo(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (Exception e) {
            LOGGER.error("审批失败！" + e,e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "审批失败!" + e, 0, null);
        }
    }


    /**
     * 审批恢复信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("updateApprovalRecoveryJob")
    @POST
    public String updateApprovalRecoveryJob(@FormParam("params")
                                                     String params) {
        try {
            JSONObject jsonParam =  this.parseObject(params);
            JSONObject posJson = srmPosFrozenInfoServer.updateApprovalRecoveryJob(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (Exception e) {
            LOGGER.error("审批失败！" + e,e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "审批失败!" + e, 0, null);
        }
    }

    
    /**
     *根据id查询供应商标识信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSupplierFlag")
    @POST
    public String findSupplierFlag(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Integer supplierId = jsonParam.getInteger("supplierId");
            if (supplierId == null || supplierId.equals("")) {
                return convertResultJSONObj("E", "请检查supplierId参数", 0, null);
            }
            List<SrmPosFrozenInfoEntity_HI_RO> frozenInfo = srmPosFrozenInfoServer.findSupplierFlag(supplierId);
            jsonParam.put("frozenInfo", frozenInfo);
            jsonParam.put("status", "S");
            jsonParam.put("msg", "查询成功");
            return jsonParam.toJSONString();
        } catch (Exception e) {
            LOGGER.error("查询供应标识信息失败！", e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return convertResultJSONObj("E", "查询供应商标识信息失败!" + e, 0, null);
        }
    }
    
    /**
     * 查询冻结、恢复供应商LOV
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findFrozenOrRecoverySupplierLov")
    @POST
    public String findFrozenOrRecoverySupplierLov(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmPosFrozenInfoEntity_HI_RO> supplierlov = srmPosFrozenInfoServer.findFrozenOrRecoverySupplierLov(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(supplierlov);
        }catch (Exception e) {
            LOGGER.error("查询供应商LOV失败！" + e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询供应商LOV失败!" + e, 0, null);
        }
    }
}
