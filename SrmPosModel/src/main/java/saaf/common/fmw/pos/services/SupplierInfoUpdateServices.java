package saaf.common.fmw.pos.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.intf.model.inter.IIntfSupplier;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosChangeInfoEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISupplierInfo;
import saaf.common.fmw.pos.model.inter.ISupplierInfoUpdate;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商变更
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Path("/supplierInfoUpdateServices")
@Component
public class SupplierInfoUpdateServices extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierInfoUpdateServices.class);

    private static final String PARAMS = "params";

    private static final String PAGE_INDEX = "pageIndex";

    private static final String PAGE_ROWS = "pageRows";

    @Autowired
    private ISupplierInfoUpdate supplierInfoUpdateServer;

    @Autowired
    private ISupplierInfo supplierInfoServer;

    @Autowired
    private IIntfSupplier intfSupplierServer;

    public SupplierInfoUpdateServices() {
        super();
    }

    /**
     * 查询变更头信息
     *
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
    @POST
    @Path("findSupplierInfoUpdate")
    public String findSupplierInfoUpdate(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            if ("EX".equals(paramJSON.getString("varPlatformCode"))) { //是供应商查询
                paramJSON.put("supplier_id", paramJSON.getIntValue("varSupplierId"));
            }
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findSupplierInfoUpdate(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * 查询变更头信息-门户
     *
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
    @POST
    @Path("findDoorSupplierInfoUpdate")
    public String findDoorSupplierInfoUpdate(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Integer supplierId =  paramJSON.getInteger("varSupplierId");
            if (supplierId != null && supplierId.intValue() > 0) {
                Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findDoorSupplierInfoUpdate(paramJSON, pageIndex, pageRows);
                return JSON.toJSONString(pag);
            } else {
                return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "非供应商登录，查询失败！", 0, null));
            }
        } catch (Exception e) {
            LOGGER.error("供应商门户查询变更信息列表异常：" + e.getMessage());
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败！", 0, null));
        }
    }

    /**
     * 查询变更基础信息
     *
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
    @POST
    @Path("findBaseInfoUpdate")
    public String findBaseInfoUpdate(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            if ("EX".equals(paramJSON.getString("varPlatformCode"))) { //是供应商查询
                paramJSON.put("supplier_id", paramJSON.getIntValue("varSupplierId"));
            }
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findBaseInfoUpdate(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * 查询变更的产品与服务
     *
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
    @POST
    @Path("findChangeCategory")
    public String findChangeCategory(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            if ("EX".equals(paramJSON.getString("varPlatformCode"))) { //是供应商查询
                paramJSON.put("supplier_id", paramJSON.getIntValue("varSupplierId"));
            } else if (null == paramJSON.getInteger("supplierId")) {
                paramJSON.put("supplierId", -1);
            }
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findChangeCategory(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * 查询供应商的认证证书
     *
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
    @POST
    @Path("findChangeCertificate")
    public String findChangeCertificate(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findChangeCertificate(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * 查询供应商的认证证书(变更前)
     *
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
    @POST
    @Path("findChangeCertificateBf")
    public String findChangeCertificateBf(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findChangeCertificateBf(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * 查询供应商的认证证书(变更后)
     *
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
    @POST
    @Path("findChangeCertificateAf")
    public String findChangeCertificateAf(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findChangeCertificateAf(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * 删除供应商的认证证书(变更后)
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("deleteChangeCertificateAf")
    @POST
    public String deleteChangeCertificateAf(@FormParam("params")
                                                    String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = supplierInfoUpdateServer.deleteChangeCertificateAf(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败!", 0, null);
        }
    }

    /**
     * 查询供应商的银行信息
     *
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
    @POST
    @Path("findChangeBankInfo")
    public String findChangeBankInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findChangeBankInfo(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * 查询供应商的银行信息(变更前)
     *
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
    @POST
    @Path("findChangeBankBfInfo")
    public String findChangeBankBfInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findChangeBankBfInfo(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * 查询供应商的银行信息（变更后）
     *
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
    @POST
    @Path("findChangeBankAfInfo")
    public String findChangeBankAfInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findChangeBankAfInfo(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * 删除供应商的银行信息(变更后)
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("deleteChangeBankAf")
    @POST
    public String deleteChangeBankAf(@FormParam("params")
                                             String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = supplierInfoUpdateServer.deleteChangeBankAf(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败!", 0, null);
        }
    }

    /**
     * 查询供应商的联系人
     *
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
    @POST
    @Path("findChangeContacts")
    public String findChangeContacts(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findChangeContacts(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * 查询供应商的联系人（变更前）
     *
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
    @POST
    @Path("findChangeContactsBf")
    public String findChangeContactsBf(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findChangeContactsBf(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * 查询供应商的联系人（变更后）
     *
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
    @POST
    @Path("findChangeContactsAf")
    public String findChangeContactsAf(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findChangeContactsAf(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * 查询供应商的地址簿（变更前）
     *
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
    @POST
    @Path("findAddressesBf")
    public String findAddressesBf(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findAddressesBf(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * 查询供应商的地址簿（变更后）
     *
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
    @POST
    @Path("findAddressesAf")
    public String findAddressesAf(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findAddressesAf(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * 删除供应商的地址簿(变更后)
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("deleteAddressesAf")
    @POST
    public String deleteAddressesAf(@FormParam("params")
                                            String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = supplierInfoUpdateServer.deleteAddressesAf(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败!", 0, null);
        }
    }

    /**
     * 查询供应商旧信息
     *
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
    @POST
    @Path("findSupplierInfo")
    public String findSupplierInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            if ("EX".equals(paramJSON.getString("varPlatformCode"))) { //是供应商查询
                paramJSON.put("supplier_id", paramJSON.getIntValue("varSupplierId"));
            }
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findSupplierInfo(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * 查询供应商旧信息-门户
     *
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
    @POST
    @Path("findDoorSupplierInfo")
    public String findDoorSupplierInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            if ("EX".equals(paramJSON.getString("varPlatformCode"))) { //是供应商查询
                paramJSON.put("supplier_id", paramJSON.getIntValue("varSupplierId"));
            }
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findDoorSupplierInfo(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * 查询变更前供应商的银行信息
     *
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
    @POST
    @Path("findOldBankInfo")
    public String findOldBankInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            if ("EX".equals(paramJSON.getString("varPlatformCode"))) { //是供应商查询
                paramJSON.put("supplier_id", paramJSON.getIntValue("varSupplierId"));
            }
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer.findOldBankInfo(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!", 0, null));
        }
    }

    /**
     * Description：保存/提交供应商变更信息
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * changeId  资料变更ID  NUMBER  Y
     * changeNumber  资料变更编号  VARCHAR2  N
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * sourceType  来源类型（BUYER：采购员，SUPPLIER：供应商）  VARCHAR2  N
     * changeStatus  变更状态（POS_APPROVAL_STATUS）  VARCHAR2  Y
     * changeReason  变更原因  VARCHAR2  N
     * fileId  变更函附件ID  NUMBER  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核时间  DATE  N
     * approvalOpinion  审核意见  VARCHAR2  N
     * changeId  资料变更ID  NUMBER  Y
     * changeNumber  资料变更编号  VARCHAR2  N
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * sourceType  来源类型（BUYER：采购员，SUPPLIER：供应商）  VARCHAR2  N
     * changeStatus  变更状态（POS_APPROVAL_STATUS）  VARCHAR2  Y
     * changeReason  变更原因  VARCHAR2  N
     * fileId  变更函附件ID  NUMBER  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核时间  DATE  N
     * approvalOpinion  审核意见  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @POST
    @Path("saveChangeInfo")
    public String saveChangeInfo(@FormParam(PARAMS) String params) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            JSONObject json = supplierInfoUpdateServer.saveChangeInfo(paramJSON);
            return JSON.toJSONString(json);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "保存失败!", 0, null));
        }
    }


    /**
     * 删除变更单据
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("deleteChangeInfo")
    @POST
    public String deleteChangeInfo(@FormParam("params") String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = supplierInfoUpdateServer.deleteChangeInfo(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (Exception e) {
            LOGGER.error("删除失败！异常：" + e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败！", 0, null);
        }
    }

    /**
     * 删除变更产品与服务行
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("deleteChangeCategory")
    @POST
    public String deleteChangeCategory(@FormParam("params") String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = supplierInfoUpdateServer.deleteChangeCategory(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (Exception e) {
            LOGGER.error("删除失败！参数：" + params + ",异常：" + e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败!", 0, null);
        }
    }

    /**
     * 删除联系人
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("deleteChangeContact")
    @POST
    public String deleteChangeContact(@FormParam("params")
                                              String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = supplierInfoUpdateServer.deleteChangeContact(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            LOGGER.error("删除失败！参数：" + params + ",异常：" + e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败!", 0, null);
        }
    }



    /**
     * Description：审批通过档案变更
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * changeContactAfId  供应商联系人变更后ID  NUMBER  Y
     * changeId  变更ID  NUMBER  Y
     * supplierContactId  供应商联系人ID  NUMBER  N
     * contactName  联系人姓名  VARCHAR2  N
     * mobilePhone  手机号码  VARCHAR2  N
     * fixedPhone  固定电话  VARCHAR2  N
     * faxPhone  传真号码  VARCHAR2  N
     * emailAddress  联系人邮箱  VARCHAR2  N
     * departmentName  部门  VARCHAR2  N
     * positionName  职位  VARCHAR2  N
     * needAccountFlag  付款标识  VARCHAR2  N
     * userName  用户名  VARCHAR2  N
     * birthDate  出生日期  DATE  N
     * failureDate  失效日期  DATE  N
     * contactFileIdAf  联系人附件ID  NUMBER  N
     * position  职位，快码:POSITION  VARCHAR2  N
     * superiors  上级领导，快码:SUPERIORS  VARCHAR2  N
     * changeContactAfId  供应商联系人变更后ID  NUMBER  Y
     * changeId  变更ID  NUMBER  Y
     * supplierContactId  供应商联系人ID  NUMBER  N
     * contactName  联系人姓名  VARCHAR2  N
     * mobilePhone  手机号码  VARCHAR2  N
     * fixedPhone  固定电话  VARCHAR2  N
     * faxPhone  传真号码  VARCHAR2  N
     * emailAddress  联系人邮箱  VARCHAR2  N
     * departmentName  部门  VARCHAR2  N
     * positionName  职位  VARCHAR2  N
     * needAccountFlag  付款标识  VARCHAR2  N
     * userName  用户名  VARCHAR2  N
     * birthDate  出生日期  DATE  N
     * failureDate  失效日期  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    @Path("saveApproveUpdate")
    @POST
    public String saveApproveUpdate(@FormParam("params")
                                            String params) {
        int userId = getSessionUserId();
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = supplierInfoUpdateServer.approveUpdate(jsonParams, userId);
            return JSON.toJSONString(jsondata);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "审批失败!", 0, null);
        }
    }

    /**
     * Description：驳回档案变更
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * changeId  资料变更ID  NUMBER  Y
     * changeNumber  资料变更编号  VARCHAR2  N
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * sourceType  来源类型（BUYER：采购员，SUPPLIER：供应商）  VARCHAR2  N
     * changeStatus  变更状态（POS_APPROVAL_STATUS）  VARCHAR2  Y
     * changeReason  变更原因  VARCHAR2  N
     * fileId  变更函附件ID  NUMBER  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核时间  DATE  N
     * approvalOpinion  审核意见  VARCHAR2  N
     * changeId  资料变更ID  NUMBER  Y
     * changeNumber  资料变更编号  VARCHAR2  N
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * sourceType  来源类型（BUYER：采购员，SUPPLIER：供应商）  VARCHAR2  N
     * changeStatus  变更状态（POS_APPROVAL_STATUS）  VARCHAR2  Y
     * changeReason  变更原因  VARCHAR2  N
     * fileId  变更函附件ID  NUMBER  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核时间  DATE  N
     * approvalOpinion  审核意见  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Path("saveRejectUpdate")
    @POST
    public String saveRejectUpdate(@FormParam("params")
                                           String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = supplierInfoUpdateServer.saveRejectUpdate(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "驳回失败!", 0, null);
        }
    }

    /**
     * 查询供应商的认证证书
     *
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
    @POST
    @Path("findUpdateSupplierCertificate")
    public String findUpdateSupplierCertificate(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
                                                @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            if ("EX".equals(paramJSON.getString("varPlatformCode"))) { // 是供应商查询
                paramJSON.put("supplier_id", paramJSON.getIntValue("varSupplierId"));
            } else if (null == paramJSON.getInteger("supplierId")) {
                paramJSON.put("supplierId", -1);
            }
            Pagination<SrmPosChangeInfoEntity_HI_RO> pag = supplierInfoUpdateServer
                    .findUpdateSupplierCertificate(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!", 0, null));
        }
    }

}
	
	
