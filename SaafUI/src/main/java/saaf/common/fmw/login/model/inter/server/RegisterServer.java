package saaf.common.fmw.login.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafResponsibilitysEntity_HI;
import saaf.common.fmw.base.model.entities.SaafUserRespEntity_HI;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.login.model.inter.IRegister;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierContactsEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierAddressesEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierCredentialsEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;
import saaf.common.fmw.utils.SHA1Util;

import java.util.Date;
import java.util.List;

@Component("registerServer")
public class RegisterServer implements IRegister {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterServer.class);

    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierCredentialsEntity_HI> srmPosSupplierCredentialsDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierContactsEntity_HI> srmPosSupplierContactsDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierAddressesEntity_HI> srmPosSupplierAddressesDAO_HI;

    @Autowired
    private ViewObject<SaafResponsibilitysEntity_HI> saafResponsibilitysDAO_HI;

    @Autowired
    private ViewObject<SaafUserRespEntity_HI> saafUserRespDAO_HI;

    @Override
    public JSONObject saveRegister(JSONObject params) {
        LOGGER.info("自助注册参数信息：" + params);
        List<SaafUsersEntity_HI> userList = saafUsersDAO_HI.findByProperty("userName", params.getString("userName"));
        if (userList.size() > 0) {
            return SToolUtils.convertResultJSONObj("E", "该用户名已存在！", 0, null);
        }
        List<SrmPosSupplierInfoEntity_HI> supplierList = srmPosSupplierInfoDAO_HI.findByProperty("supplierName", params.getString("supplierName"));
        if (supplierList.size() > 0) {
            return SToolUtils.convertResultJSONObj("E", "该公司名称已存在！", 0, null);
        }
        List<SrmPosSupplierCredentialsEntity_HI> supplierCredentialsLicenseNum = srmPosSupplierCredentialsDAO_HI.findByProperty("licenseNum", params.getString("licenseNum"));
        if (supplierCredentialsLicenseNum.size() > 0) {
            return SToolUtils.convertResultJSONObj("E", "该营业执照号已存在！", 0, null);
        }
        SaafResponsibilitysEntity_HI sr = null;
        List<SaafResponsibilitysEntity_HI> srList = saafResponsibilitysDAO_HI.findByProperty("responsibilityKey", "SUPPLIER_RESP");
        if (srList.size() > 0) {
            sr = srList.get(0);
        } else {
            return SToolUtils.convertResultJSONObj("E", "供应商的默认职责不存在，请联系管理员！", 0, null);
        }
        //创建供应商
        Integer supplierId = createSupplierInfo(params);
        if (supplierId.intValue() > 0) {
            //创建供应商资质信息
            createSupplierCredentials(supplierId, params);
            //创建供应商联系人
            createSupplierContact(supplierId, params);
            //创建地址信息
            createSupplierAddress(supplierId, params);
            //创建登录账号
            createSupplierUser(supplierId, sr.getResponsibilityId(), sr.getResponsibilityName(), params);
            return SToolUtils.convertResultJSONObj("S", "注册成功！", 1, null);
        } else {
            return SToolUtils.convertResultJSONObj("E", "注册失败，请联系管理员！", 1, null);
        }
    }

    /**
     * 创建供应商主信息
     *
     * @param params
     * @return 返回供应商ID
     */
    private Integer createSupplierInfo(JSONObject params) {
        SrmPosSupplierInfoEntity_HI supplier = new SrmPosSupplierInfoEntity_HI();
        supplier.setSupplierName(params.getString("supplierName"));
        supplier.setSupplierStatus("DRAFT"); //设置状态为‘拟定’
        supplier.setAbleEditFlag("Y");   //档案信息可修改
        supplier.setPassU9Flag("N");   //档案信息可修改
        supplier.setAddress(params.getString("registeredAddress"));
        supplier.setSupplierType(params.getString("supplierType"));
        supplier.setLogisticsSupplier(params.getString("logisticsSupplier"));
        supplier.setPurchaseFlag("Y");
        supplier.setSrmDelivery("Y"); //启用srm平台送货
        supplier.setPaymentFlag("Y");
        supplier.setAbleCheckOrderFlag("N");
        supplier.setSourceCode("REGISTER"); //注册标识
        supplier.setCompanyRegisteredAddress(params.getString("companyRegisteredAddress")); //公司注册地址
        supplier.setRegisteredCapital(params.getString("registeredCapital")); //注册资金(万)
        supplier.setIndependentLegalPersonFlag(params.getString("independentLegalPersonFlag")); //是否独立法人
        supplier.setValueAddedTaxInvoiceFlag(params.getString("valueAddedTaxInvoiceFlag")); //能否开6个税点的增值税专用发票
        supplier.setValueAddedTaxInvoiceDesc(params.getString("valueAddedTaxInvoiceDesc")); //能否开6个税点的增值税专用发票-说明
        supplier.setAssociatedCompany(params.getString("associatedCompany")); //关联公司
        supplier.setRegion(params.getString("region")); //意向服务区域
        supplier.setOperatorUserId(-1);
        try {
            srmPosSupplierInfoDAO_HI.saveEntity(supplier);
            return supplier.getSupplierId();
        } catch (Exception e) {
            LOGGER.error("创建供应商信息异常：" + e.getMessage());
            return -1;
        }
    }

    /**
     * 创建供应商资质信息
     *
     * @param supplierId
     * @param params
     */
    private void createSupplierCredentials(Integer supplierId, JSONObject params) {
        SrmPosSupplierCredentialsEntity_HI credential = new SrmPosSupplierCredentialsEntity_HI();
        credential.setSupplierId(supplierId);
        credential.setBusinessScope(params.getString("businessScope"));
        credential.setEstablishmentDate(params.getDate("establishmentDate"));
        credential.setLicenseNum(params.getString("licenseNum"));
        if ("N".equals(params.getString("longLicenseIndate"))) {
            credential.setLicenseIndate(params.getDate("licenseIndate"));
        }
        credential.setBankPermitNumber(params.getString("bankPermitNumber"));
        System.out.println("muchInOne:" + params.getString("muchInOne"));
        credential.setMuchInOne(params.getString("muchInOne"));
        credential.setLongLicenseIndate(params.getString("longLicenseIndate"));
        credential.setLicenseFileId(params.getIntValue("licenseFileId"));
        credential.setBankPermitFileId(params.getIntValue("bankPermitFileId"));
        if ("N".equals(params.getString("muchInOne"))) {
            credential.setTissueCode(params.getString("tissueCode"));
            credential.setTissueIndate(params.getDate("tissueIndate"));
            credential.setTissueFileId(params.getIntValue("tissueFileId"));
            credential.setTaxCode(params.getString("taxCode"));
            credential.setTaxFileId(params.getIntValue("taxFileId"));
        }
        credential.setOperatorUserId(-1);
        try {
            srmPosSupplierCredentialsDAO_HI.saveEntity(credential);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    /**
     * 创建供应商地址信息
     *
     * @param supplierId
     * @param params
     */
    private void createSupplierAddress(Integer supplierId, JSONObject params) {
        SrmPosSupplierAddressesEntity_HI addresses = new SrmPosSupplierAddressesEntity_HI();
        addresses.setSupplierId(supplierId);
        addresses.setAddressName(params.getString("addressName"));
        addresses.setCountry(params.getString("country"));
        addresses.setProvince(params.getString("province"));
        addresses.setCity(params.getString("city"));
        addresses.setCounty(params.getString("county"));
        addresses.setDetailAddress(params.getString("detailAddress"));
        addresses.setOperatorUserId(-1);
        try {
            srmPosSupplierAddressesDAO_HI.saveEntity(addresses);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    /**
     * 创建供应商联系人信息
     *
     * @param supplierId
     * @param params
     */
    private void createSupplierContact(Integer supplierId, JSONObject params) {
        SrmPosSupplierContactsEntity_HI contact = new SrmPosSupplierContactsEntity_HI();
        contact.setSupplierId(supplierId);
        contact.setContactName(params.getString("contactName"));
        contact.setMobilePhone(params.getString("mobilePhone"));
        contact.setEmailAddress(params.getString("emailAddress"));
        contact.setFixedPhone(params.getString("fixedPhone"));
        contact.setOperatorUserId(-1);
        try {
            srmPosSupplierContactsDAO_HI.saveEntity(contact);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    /**
     * 创建供应商用户
     *
     * @param supplierId
     * @param params
     */
    private void createSupplierUser(Integer supplierId, Integer responsibilityId, String responsibilityName, JSONObject params) {
        SaafUsersEntity_HI user = new SaafUsersEntity_HI();
        user.setSupplierId(supplierId);
        //通过注册的账号，默认为供应商主账号
        try {
            user.setSupplierPrimaryFlag("Y");
            user.setUserName(params.getString("userName"));
            SHA1Util sha1 = new SHA1Util();
            try {
                user.setEncryptedPassword(sha1.getEncrypt(params.getString("encryptedPassword")));
            } catch (Exception e) {
                //e.printStackTrace();
            }
            user.setIsadmin("N");
            //user.setUserFullName(params.getString("userFullName"));
            if(!ObjectUtils.isEmpty(params.getString("userFullName"))){
                user.setUserFullName(params.getString("userFullName"));
            }else{
                user.setUserFullName(params.getString("userName"));
            }
            user.setPlatformCode("EX");
            user.setStartDateActive(new Date());
            user.setOperatorUserId(-1);
            saafUsersDAO_HI.save(user);
            //给供应商分配默认的职责
            SaafUserRespEntity_HI resp = new SaafUserRespEntity_HI();
            resp.setStartDateActive(new Date());
            resp.setUserId(user.getUserId());
            resp.setResponsibilityId(responsibilityId);
            resp.setUserRespName(responsibilityName);
            resp.setPlatformCode("EX");
            resp.setOperatorUserId(-1);
            saafUserRespDAO_HI.save(resp);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
