package saaf.common.fmw.pos.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.*;
import saaf.common.fmw.base.model.entities.readonly.*;
import saaf.common.fmw.base.model.inter.*;
import saaf.common.fmw.base.utils.enums.ESBParams;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;
import saaf.common.fmw.message.email.utils.SendMailUtil;
import saaf.common.fmw.pos.model.entities.*;
import saaf.common.fmw.pos.model.entities.readonly.*;
import saaf.common.fmw.pos.model.inter.*;
import saaf.common.fmw.pos.model.inter.ISaafUsers;
import saaf.common.fmw.utils.SHA1Util;
import saaf.common.fmw.utils.SrmUtils;

import java.math.BigDecimal;
import java.rmi.ServerException;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static saaf.common.fmw.services.CommonAbstractServices.*;
import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;
import static saaf.common.fmw.services.CommonAbstractServices.MSG;

@Component("supplierInfoServer")
public class SupplierInfoServer implements ISupplierInfo {

    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierInfoServer.class);
    @Autowired
    private ISrmPosSupplierCredentials iSrmPosSupplierCredentials;  //资质信息

    @Autowired
    private ISrmPosSupplierCertificate iSrmPosSupplierCertificate;  //认证与证书

    @Autowired
    private ISrmPosSupplierCategories iSrmPosSupplierCategories;  //产品与服务行

    @Autowired
    private ISrmPosSupplierContacts iSrmPosSupplierContacts;  //联系人

    @Autowired
    private ISrmPosSupplierBank iSrmPosSupplierBank; //银行信息

    @Autowired
    private ISrmPosSupplierAddresses iSrmPosSupplierAddresses; //地址簿的头表

    @Autowired
    private ISrmPosSupplierSites iSrmPosSupplierSites; //地址簿的地点

    @Autowired
    private ISaafUsers iSaafUsers;  //用户server

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;  //获取规则序列号的util

    @Autowired
    private BaseViewObject<SrmPosSupplierInfoEntity_HI_RO> srmPosSupplierInfoDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierCredentialsEntity_HI> srmPosSupplierCredentialsDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierCategoriesEntity_HI> srmPosSupplierCategoriesDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierCertificateEntity_HI> srmPosSupplierCertificateDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierBankEntity_HI> srmPosSupplierBankDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierContactsEntity_HI> srmPosSupplierContactsDAO_HI;

    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;  //用户DAO

    @Autowired
    private ViewObject<SrmPosChangeInfoEntity_HI> srmPosChangeInfoDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeBaseEntity_HI> srmPosChangeBaseDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeCredentialsEntity_HI> srmPosChangeCredentialsDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeCategoriesEntity_HI> srmPosChangeCategoriesDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeCertificateEntity_HI> srmPosChangeCertificateDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeContactsEntity_HI> srmPosChangeContactsDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeBankEntity_HI> srmPosChangeBankDAO_HI;

    @Autowired
    private ViewObject<SaafResponsibilitysEntity_HI> saafResponsibilitysDAO_HI;//职责

    @Autowired
    private ViewObject<SaafUserRespEntity_HI> saafUserRespDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierSitesEntity_HI> srmPosSupplierSitesDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierAddressesEntity_HI> srmPosSupplierAddressesDAO_HI;

    @Autowired
    private ViewObject<SrmIntfLogsEntity_HI> srmIntfLogsDAO_HI;//日志

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值

    @Autowired
    private BaseViewObject<SaafLookupValuesEntity_HI_RO> saafLookupValuesDAO_HI_RO;//快码值

    @Autowired
    private ISrmBaseNotifications iSrmBaseNotifications;//系统通知

    @Autowired
    private ISrmPosSupplierQuit iSrmPosSupplierQuit;//供应商退出

    @Autowired
    private IQualificationInfo iQualificationInfo;//供应商资质审查

    @Autowired
    private BaseViewObject<SaafInstitutionEntity_HI_RO> saafInstitutionEntity_HI_RO;

    @Autowired
    private ISaafUsersInst iSaafUsersInst; //用户分配组织关系

    @Autowired
    private ISaafUserResp iSaafUserResp; //用户分配职责关系

    @Autowired
    private IESBClient iesbClient;

    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;
    private static SendMailUtil sendMailUtil = new SendMailUtil(true);


    @Autowired
    private BaseViewObject<SaafUserEmployeesEntity_HI_RO> saafUserEmployeesDAO_HI_RO;

    private static final String N = "N";
    private static final String NORMAL = "NORMAL";
    private static final String FREEZE = "FREEZE";
    private static final String Y = "Y";

    /**
     * 校验供应商的基础信息的必填项——供应商接口（数据输入）
     *
     * @param supplierInfo
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public String judgeSpaceSupplierInfo(SrmPosSupplierInfoEntity_HI supplierInfo) {
        String result = "";
        if (null == supplierInfo || "".equals(supplierInfo)) {
            result += "供应商基础信息不可为空";
            return result;
        }
        if (null == supplierInfo.getSourceId() || "".equals(supplierInfo.getSourceId())) {
            result += "请填写供应商基础信息的数据来源Id——sourceId";
            return result;
        }
        if (null == supplierInfo.getSourceCode() || "".equals(supplierInfo.getSourceCode())) {
            result += "请填写供应商基础信息的数据来源类型Code——sourceCode";
            return result;
        }
        if (null == supplierInfo.getSupplierName() || "".equals(supplierInfo.getSupplierName())) {
            result += "请填写供应商基础信息的公司名称";
            return result;
        }
        if (null == supplierInfo.getSupplierType() || "".equals(supplierInfo.getSupplierType())) {
            result += "请填写供应商基础信息的供应商类型";
            return result;
        }
        if (null == supplierInfo.getSupplierStatus() || "".equals(supplierInfo.getSupplierStatus())) {
            result += "请填写供应商基础信息的供应商状态";
            return result;
        }
        return result;
    }

    /**
     * 供应商接口（数据输入）——保存/修改一个供应商所有信息（用于外部访问的接口）
     *
     * @param jsonParams
     * @param userId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveSupplierAllExternal(JSONObject jsonParams, Integer userId) {
        JSONObject jsonData = new JSONObject();  //最终结果的返回
        SrmPosSupplierInfoEntity_HI supplierInfo = null;
        if (!(jsonParams.getJSONObject("supplierInfo") == null || "".equals(jsonParams.getJSONObject("supplierInfo")))) {
            JSONObject supplierInfoJSON = jsonParams.getJSONObject("supplierInfo");  //供应商基信息
            supplierInfo = JSON.parseObject(supplierInfoJSON.toJSONString(), SrmPosSupplierInfoEntity_HI.class);
        }
        SrmPosSupplierCredentialsEntity_HI supplierCredentialsInfo = null;
        if (!(jsonParams.getJSONObject("supplierCredentialsInfo") == null || "".equals(jsonParams.getJSONObject("supplierCredentialsInfo")))) {
            JSONObject supplierCredentialsInfoJSON = jsonParams.getJSONObject("supplierCredentialsInfo"); //资质信息
            supplierCredentialsInfo = JSON.parseObject(supplierCredentialsInfoJSON.toJSONString(), SrmPosSupplierCredentialsEntity_HI.class);
        }
        List<SrmPosSupplierCategoriesEntity_HI> supplierCategoryInfoList = null;
        if (!(jsonParams.getJSONArray("supplierCategoryInfoList") == null || "".equals(jsonParams.getJSONArray("supplierCategoryInfoList")))) {
            JSONArray supplierCategoryInfoListJSON = jsonParams.getJSONArray("supplierCategoryInfoList"); //产品和服务
            supplierCategoryInfoList = JSON.parseArray(supplierCategoryInfoListJSON.toJSONString(), SrmPosSupplierCategoriesEntity_HI.class);
        }
        List<SrmPosSupplierCertificateEntity_HI> supplierCertificateInfoList = null;
        if (!(jsonParams.getJSONArray("supplierCertificateInfoList") == null || "".equals(jsonParams.getJSONArray("supplierCertificateInfoList")))) {
            JSONArray supplierCertificateInfoListJSON = jsonParams.getJSONArray("supplierCertificateInfoList");//认证与证书
            supplierCertificateInfoList = JSON.parseArray(supplierCertificateInfoListJSON.toJSONString(), SrmPosSupplierCertificateEntity_HI.class);
        }
        List<SrmPosSupplierBankEntity_HI> supplierBankInfoList = null;
        if (!(jsonParams.getJSONArray("supplierBankInfoList") == null || "".equals(jsonParams.getJSONArray("supplierBankInfoList")))) {
            JSONArray supplierBankInfoListJSON = jsonParams.getJSONArray("supplierBankInfoList");//银行信息
            supplierBankInfoList = JSON.parseArray(supplierBankInfoListJSON.toJSONString(), SrmPosSupplierBankEntity_HI.class);
        }
        List<SrmPosSupplierContactsEntity_HI> supplierContactsInfoList = null;
        if (!(jsonParams.getJSONArray("supplierContactsInfoList") == null || "".equals(jsonParams.getJSONArray("supplierContactsInfoList")))) {
            JSONArray supplierContactsInfoListJSON = jsonParams.getJSONArray("supplierContactsInfoList");//联系人
            supplierContactsInfoList = JSON.parseArray(supplierContactsInfoListJSON.toJSONString(), SrmPosSupplierContactsEntity_HI.class);

        }
        List<Map> supplierAddressesInfoAllList = null;
        if (!(jsonParams.getJSONArray("supplierAddressesInfoAllList") == null || "".equals(jsonParams.getJSONArray("supplierAddressesInfoAllList")))) {
            JSONArray supplierAddressesInfoAllListJSON = jsonParams.getJSONArray("supplierAddressesInfoAllList");//地址及地点
            supplierAddressesInfoAllList = (List<Map>) JSON.parseArray(supplierAddressesInfoAllListJSON.toJSONString(), Map.class);
        }

        /**
         * 校验重复性和必填项
         * ==============================================================================
         *  Version    Date           Updated By     Description
         *  -------    -----------    -----------    ---------------
         *  V1.0       2020-04-15     hgq             创建
         * ==============================================================================
         */
        //校验供应商的基础信息的必填项
        String resultSupplierInfo = judgeSpaceSupplierInfo(supplierInfo);
        if (!(resultSupplierInfo == null || "".equals(resultSupplierInfo))) {
            return SToolUtils.convertResultJSONObj("E", resultSupplierInfo, 0, null);
        }
        //校验供应商的资质信息必填项
        String resultSupplierCredentialsInfo = iSrmPosSupplierCredentials.judgeSpaceSupplierCredentialsInfo(supplierCredentialsInfo);
        if (!(resultSupplierCredentialsInfo == null || "".equals(resultSupplierCredentialsInfo))) {
            return SToolUtils.convertResultJSONObj("E", resultSupplierCredentialsInfo, 0, null);
        }
        //校验供应商的产品和服务重复与必填项
        String resultSupplierCategories = iSrmPosSupplierCategories.judgeSpaceSupplierCategories(supplierCategoryInfoList);
        if (!(resultSupplierCategories == null || "".equals(resultSupplierCategories))) {
            return SToolUtils.convertResultJSONObj("E", resultSupplierCategories, 0, null);
        }
        //校验供应商的认证与证书必填项
        String resultSupplierCertificate = iSrmPosSupplierCertificate.judgeSpaceSupplierCertificate(supplierCertificateInfoList);
        if (!(resultSupplierCertificate == null || "".equals(resultSupplierCertificate))) {
            return SToolUtils.convertResultJSONObj("E", resultSupplierCertificate, 0, null);
        }
        //校验供应商的银行信息重复与必填项
        String resultSupplierBank = iSrmPosSupplierBank.judgeSpaceSupplierBank(supplierBankInfoList);
        if (!(null == resultSupplierBank || "".equals(resultSupplierBank))) {
            return SToolUtils.convertResultJSONObj("E", resultSupplierBank, 0, null);
        }
        //校验供应商的联系人必填项
        String resultSupplierContacts = iSrmPosSupplierContacts.judgeSpaceSupplierContacts(supplierContactsInfoList);
        if (!(null == resultSupplierContacts || "".equals(resultSupplierContacts))) {
            return SToolUtils.convertResultJSONObj("E", resultSupplierContacts, 0, null);
        }
        //校验供应商的地址重复与必填项
        String resultSupplierAddress = iSrmPosSupplierAddresses.judgeSpaceSupplierAddress(supplierAddressesInfoAllList);
        if (!(null == resultSupplierAddress || "".equals(resultSupplierAddress))) {
            return SToolUtils.convertResultJSONObj("E", resultSupplierAddress, 0, null);
        }

        SrmPosSupplierInfoEntity_HI findSupplierInfo = null;
        Map<String, Object> checkList = new HashMap<String, Object>();  //判断供应商校验（公司名称，营业执照号）
        if (null != supplierInfo.getSupplierName() && !"".equals(supplierInfo.getSupplierName())) {
            checkList.put("supplierName", supplierInfo.getSupplierName().trim());
        }
        if (!(supplierInfo.getSourceId() == null || "".equals(supplierInfo.getSourceId()) || supplierInfo.getSourceCode() == null || "".equals(supplierInfo.getSourceCode()))) {  //供应商sourceId与sourceCode不为空
            JSONObject jsonParamsSupplier = new JSONObject();
            jsonParamsSupplier.put("sourceCode", supplierInfo.getSourceCode());
            jsonParamsSupplier.put("sourceId", supplierInfo.getSourceId());
            List<SrmPosSupplierInfoEntity_HI_RO> supplierList = findSupplierInfoForSelf(jsonParamsSupplier);
            if (supplierList != null && supplierList.size() > 0) {
                SrmPosSupplierInfoEntity_HI_RO findRO = supplierList.get(0);
                findSupplierInfo = srmPosSupplierInfoDAO_HI.getById(findRO.getSupplierId());  //说明该供应商已经存在，update操作
                checkList.put("supplierId", findSupplierInfo.getSupplierId());
                supplierCredentialsInfo.setSupplierId(findSupplierInfo.getSupplierId());  //将供应商的supplierId赋值给资质信息对象
            }
        }

        if (!(supplierInfo.getSupplierName() == null && "".equals(supplierInfo.getSupplierName()))) {
            checkList.put("supplierName", supplierInfo.getSupplierName().trim());  //判断供应商的公司名称
        }
        if (!(supplierInfo.getSupplierNumber() == null || "".equals(supplierInfo.getSupplierNumber()))) {
            checkList.put("supplierNumber", supplierInfo.getSupplierNumber().trim());  //判断供应商的供应商编码
            checkList.put("user_Name", supplierInfo.getSupplierNumber().trim());  //用于供应商接口（数据输入）的供应商编码判断是否重复，此时供应商编码等同于用户登录账号
        }
        if (!(supplierCredentialsInfo.getLicenseNum() == null || "".equals(supplierCredentialsInfo.getLicenseNum()))) {
            checkList.put("licenseNum", supplierCredentialsInfo.getLicenseNum().trim());  //验证营业执照
        }
        if (!(supplierCredentialsInfo.getSupplierId() == null || "".equals(supplierCredentialsInfo.getSupplierId()))) {  //根据供应商supplierId查出是否有对应的供应商的资质信息
            List<SrmPosSupplierCredentialsEntity_HI> credentialsList = srmPosSupplierCredentialsDAO_HI.findByProperty("supplierId", supplierCredentialsInfo.getSupplierId());
            if (null != credentialsList && credentialsList.size() > 0) {
                checkList.put("credentialsId", credentialsList.get(0).getCredentialsId());
            }
        }
        String result = findSupplierCheck(checkList);
        if (!(result == null || "".equals(result))) {  //判断供应商校验（公司名称，营业执照号，用户登录账号）
            return SToolUtils.convertResultJSONObj("E", result, 0, null);
        }

        boolean flag = false;
        if (null != findSupplierInfo) {  //供应商基础信息为update操作
            findSupplierInfo.setSupplierName(supplierInfo.getSupplierName());
            findSupplierInfo.setSupplierShortName(supplierInfo.getSupplierShortName());
            findSupplierInfo.setBlacklistFlag(supplierInfo.getBlacklistFlag());
            findSupplierInfo.setSupplierStatus(supplierInfo.getSupplierStatus());
            findSupplierInfo.setSupplierType(supplierInfo.getSupplierType());
            findSupplierInfo.setCompanyPhone(supplierInfo.getCompanyPhone());
            findSupplierInfo.setStaffNum(supplierInfo.getStaffNum());
            findSupplierInfo.setSupplierIndustry(supplierInfo.getSupplierIndustry());
            findSupplierInfo.setCompanyFax(supplierInfo.getCompanyFax());
            findSupplierInfo.setSupplierFileId(supplierInfo.getSupplierFileId());
            findSupplierInfo.setCompanyDescription(supplierInfo.getCompanyDescription());
            findSupplierInfo.setApprovalComments(supplierInfo.getApprovalComments());
            findSupplierInfo.setApprovalDate(supplierInfo.getApprovalDate());
            findSupplierInfo.setApprovalUserId(supplierInfo.getApprovalUserId());
            findSupplierInfo.setHomeUrl(supplierInfo.getHomeUrl());
            findSupplierInfo.setOperatorUserId(userId);
            flag = true;
        } else {  //供应商基础信息为insert操作
            if (supplierInfo.getSupplierNumber() == null || "".equals(supplierInfo.getSupplierNumber())) { //insert时，供应商编码为空时自动赋值
                String newSupplierNumber = getSupplierNumber();
                supplierInfo.setSupplierNumber(newSupplierNumber);  //供应商编码
            }
            supplierInfo.setAbleEditFlag("Y");
            supplierInfo.setPassU9Flag("N");
            supplierInfo.setCreatedBy(userId);
            supplierInfo.setOperatorUserId(userId);
            flag = false;
        }
        Integer supplierId;
        String supplierNumber;  //供应商编码
        SaafUsersEntity_HI saafUser = new SaafUsersEntity_HI(); //供应商对应的用户
        if (flag) {  //update
            srmPosSupplierInfoDAO_HI.saveEntity(findSupplierInfo);
            supplierId = findSupplierInfo.getSupplierId();
            supplierNumber = findSupplierInfo.getSupplierNumber();
            jsonData.put("supplierId", supplierId);
            jsonData.put("supplierInfo", findSupplierInfo);
        } else {  //insert
            srmPosSupplierInfoDAO_HI.saveEntity(supplierInfo);
            supplierId = supplierInfo.getSupplierId();
            supplierNumber = supplierInfo.getSupplierNumber();
            jsonData.put("supplierId", supplierId);
            jsonData.put("supplierInfo", supplierInfo);
        }

        Map<String, Object> checkList2 = new HashMap<String, Object>();  //判断验证用户登录账号
        checkList2.put("user_Name", supplierNumber.trim());  //用于供应商接口（数据输入）的供应商编码判断是否重复，此时供应商编码等同于用户登录账号
        String result2 = findSupplierCheck(checkList2);
        if (!(result2 == null || "".equals(result2))) {  //再次验证用户登录账号
            return SToolUtils.convertResultJSONObj("E", result, 0, null);
        }
        saafUser.setUserName(supplierNumber); //默认供应商的编码是用户的登录账号

        iSrmPosSupplierCredentials.saveSupplierCredentialsInfoExternal(supplierCredentialsInfo, userId, supplierId);
        iSrmPosSupplierCertificate.saveSupplierCertificateInfoListExternal(supplierCertificateInfoList, userId, supplierId);
        iSrmPosSupplierCategories.saveSupplierCategoryInfoListExternal(supplierCategoryInfoList, userId, supplierId);
        iSrmPosSupplierBank.saveSrmPosSupplierBankInfoExternal(supplierBankInfoList, userId, supplierId);
        iSrmPosSupplierContacts.saveSrmPosSupplierContactsInfoExternal(supplierContactsInfoList, userId, supplierId);
        iSrmPosSupplierAddresses.saveSupplierAddressAllExternal(supplierAddressesInfoAllList, userId, supplierId);
        if (!(saafUser.getUserName() == null || "".equals(saafUser.getUserName()))) {
            iSaafUsers.saveSaafUser(saafUser, userId, supplierId);
            jsonData.put("saafUser", saafUser);
        }

        jsonData.put("supplierCredentialsInfo", supplierCredentialsInfo);
        jsonData.put("supplierCategoryInfoList", supplierCategoryInfoList);
        jsonData.put("supplierCertificateInfoList", supplierCertificateInfoList);
        jsonData.put("supplierBankInfoList", supplierBankInfoList);
        jsonData.put("supplierContactsInfoList", supplierContactsInfoList);
        jsonData.put("supplierAddressesInfoAllList", supplierAddressesInfoAllList);

        //保存日志
        SrmIntfLogsEntity_HI logsEntity = new SrmIntfLogsEntity_HI();
        logsEntity.setIntfType("SUPPLIER_IN");//接口类型BASE_INTF_TYPE
        logsEntity.setTableName("srm_pos_supplier_info");
        logsEntity.setTableId(supplierId);//接口取数对应的表ID
        logsEntity.setDataDirection("IN");//数据方向(IN：输入， OUT：输出)
        logsEntity.setSendSystem(supplierInfo.getSourceCode());//数据发送方
        logsEntity.setReceiveSystem("SRM");
        logsEntity.setInData(jsonParams.toJSONString());//输入报文
        logsEntity.setOutData(jsonData.toJSONString());//输出报文
        logsEntity.setDescription("供应商输入接口");
        logsEntity.setOperatorUserId(userId);
        logsEntity.setIntfStatus("S");
        srmIntfLogsDAO_HI.save(logsEntity);
        return SToolUtils.convertResultJSONObj("S", "保存成功！", 1, jsonData);
    }

    /**
     * 供应商接口（数据输出）——查询供应商所有信息（用于外部访问的接口）
     * 需要提供用户和密码
     *
     * @param jsonParams
     * @param userId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Map<String, Object> pushFindSupplierListExternal(JSONObject jsonParams, Integer userId) {
        //保存日志
        SrmIntfLogsEntity_HI logsEntity = new SrmIntfLogsEntity_HI();
        logsEntity.setIntfType("SUPPLIER_OUT");//接口类型BASE_INTF_TYPE
        logsEntity.setTableName("srm_pos_supplier_info");
        logsEntity.setDataDirection("OUT");//数据方向(IN：输入， OUT：输出)
        logsEntity.setSendSystem("SRM");//数据发送方
        logsEntity.setInData(jsonParams.toJSONString());//输入报文
        logsEntity.setDescription("供应商输出接口");
        logsEntity.setOperatorUserId(userId);

        Map<String, Object> map = new HashMap<>();  //最终结果的返回
        List<SrmPosSupplierInfoEntity_HI_RO> supplierList = findSupplierInfoForSelf(jsonParams);
        if (null == supplierList || supplierList.size() <= 0) {
            map.put("status", "E");
            map.put("msg", "查无此数据！");
            map.put("data", new ArrayList<>());
            map.put("count", 0);
            logsEntity.setIntfStatus("E");//查无数据状态为E，查有数据状态为S
            srmIntfLogsDAO_HI.save(logsEntity);
            return map;
        }
        List<Map<String, Object>> supplierListMap = new ArrayList<>(); //供应商所有信息List
        for (SrmPosSupplierInfoEntity_HI_RO k : supplierList) {
            Map<String, Object> supplierAllList = new HashMap<>();  //存放一个供应商的信息及其子表的所有信息
            JSONObject supplierParams = new JSONObject();  //存放供应商Id
            supplierParams.put("supplierId", k.getSupplierId());

            //供应商基础信息
            supplierAllList.put("supplierInfo", k);
            //供应商的资质信息
            Pagination<SrmPosSupplierCredentialsEntity_HI_RO> supplierCredentialsInfoListPagination = iSrmPosSupplierCredentials.findSrmPosSupplierCredentialsInfo(supplierParams, 1, 2000);
            List<SrmPosSupplierCredentialsEntity_HI_RO> supplierCredentialsInfoList = supplierCredentialsInfoListPagination.getData();
            if (null != supplierCredentialsInfoList && supplierCredentialsInfoList.size() > 0) {
                supplierAllList.put("supplierCredentialsInfo", supplierCredentialsInfoList.get(0));
            }
            //供应商的产品与服务
            Pagination<SrmPosSupplierCategoriesEntity_HI_RO> supplierCategoryInfoListPagination = iSrmPosSupplierCategories.findSupplierCategory(supplierParams, 1, 2000);
            List<SrmPosSupplierCategoriesEntity_HI_RO> supplierCategoryInfoList = supplierCategoryInfoListPagination.getData();
            if (null != supplierCategoryInfoList && supplierCategoryInfoList.size() > 0) {
                supplierAllList.put("supplierCategoryInfoList", supplierCategoryInfoList);
            }
            //供应商的认证与证书
            Pagination<SrmPosSupplierCertificateEntity_HI_RO> supplierCertificateInfoListPagination = iSrmPosSupplierCertificate.findSupplierCertificate(supplierParams, 1, 2000);
            List<SrmPosSupplierCertificateEntity_HI_RO> supplierCertificateInfoList = supplierCertificateInfoListPagination.getData();
            if (null != supplierCertificateInfoList && supplierCertificateInfoList.size() > 0) {
                supplierAllList.put("supplierCertificateInfoList", supplierCertificateInfoList);
            }
            //供应商银行信息
            Pagination<SrmPosSupplierBankEntity_HI_RO> supplierBankInfoListPagination = iSrmPosSupplierBank.findSupplierBankInfo(supplierParams, 1, 2000);
            List<SrmPosSupplierBankEntity_HI_RO> supplierBankInfoList = supplierBankInfoListPagination.getData();
            if (null != supplierBankInfoList && supplierBankInfoList.size() > 0) {
                supplierAllList.put("supplierBankInfoList", supplierBankInfoList);
            }
            //供应商的联系人
            Pagination<SrmPosSupplierContactsEntity_HI_RO> supplierContactsInfoListPagination = iSrmPosSupplierContacts.findSupplierContacts(supplierParams, 1, 2000);
            List<SrmPosSupplierContactsEntity_HI_RO> supplierContactsInfoList = supplierContactsInfoListPagination.getData();
            if (null != supplierContactsInfoList && supplierContactsInfoList.size() > 0) {
                supplierAllList.put("supplierContactsInfoList", supplierContactsInfoList);
            }
            //供应商的地址簿
            Pagination<SrmPosSupplierAddressesEntity_HI_RO> supplierAddressesInfoListPagination = iSrmPosSupplierAddresses.findSupplierAddresses(supplierParams, 1, 2000);
            List<SrmPosSupplierAddressesEntity_HI_RO> supplierAddressesInfoList = supplierAddressesInfoListPagination.getData();
            if (null != supplierAddressesInfoList && supplierAddressesInfoList.size() > 0) {
                List<Map<String, Object>> supplierAddressesInfoAllList = new ArrayList<>(); //存放所有的地址簿及其地点
                for (SrmPosSupplierAddressesEntity_HI_RO address : supplierAddressesInfoList) {
                    Map<String, Object> supplierAddressesInfoAll = new HashMap<>();  //一条地址簿及其所有的地点
                    JSONObject supplierAddressIdParams = new JSONObject();  //存放供应商的地址簿的头表Id
                    supplierAddressesInfoAll.put("supplierAddressesInfo", address);
                    supplierAddressIdParams.put("supplierAddressId", address.getSupplierAddressId());
                    //供应商的地址簿的地址
                    List<SrmPosSupplierSitesEntity_HI_RO> supplierSitesInfoList = iSrmPosSupplierSites.findSupplierSites(supplierAddressIdParams);
                    if (null != supplierSitesInfoList && supplierSitesInfoList.size() > 0) {
                        supplierAddressesInfoAll.put("supplierSitesInfoList", supplierSitesInfoList);
                    }
                    supplierAddressesInfoAllList.add(supplierAddressesInfoAll);
                }
                supplierAllList.put("supplierAddressesInfoAllList", supplierAddressesInfoAllList);
            }
            supplierListMap.add(supplierAllList);
        }
        map.put("data", supplierListMap);
        map.put("count", 1);
        map.put("msg", "查询成功");
        map.put("status", "S");
        logsEntity.setIntfStatus("S");//查无数据状态为E，查有数据状态为S
        srmIntfLogsDAO_HI.save(logsEntity);
        return map;
    }

    /**
     * 供应商基础信息查询（带有分页）
     *
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
    @Override
    public Pagination<SrmPosSupplierInfoEntity_HI_RO> findSupplierInfoForSelf(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosSupplierInfoEntity_HI_RO.QUERY_SUPPLIER_INFO);
        if (jsonParams != null && null != jsonParams.getString("supplierStatusV") && !"".equals(jsonParams.getString("supplierStatusV"))) {
            String supplierStatusV = jsonParams.getString("supplierStatusV");  //内部供应商查询范围为拟定和已批准状态
            String[] supplierStatusVList = supplierStatusV.split(",");  //按照“,”分割
            sb.append(" And (");
            int i = 0;
            for (String status : supplierStatusVList) {
                if (i != 0) {
                    sb.append(" or ");
                }
                // 改参数方式处理　hanguoqiang 2020/06/23
                sb.append("psi.supplier_status = :supplierStatusParam").append(i);
                queryParamMap.put("supplierStatusParam" + i, status);
                i++;
            }
            sb.append(")");
        }
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_id", "supplier_id", sb, queryParamMap, "=");//如果是登录者本身为供应商，查询
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_id", "supplierId", sb, queryParamMap, "=");//供应商Id查询
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_name", "supplierName", sb, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_number", "supplierNumber", sb, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_type", "supplierType", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_status", "supplierStatus", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "psi.source_code", "sourceCode", sb, queryParamMap, "=");  //创建来源标识
        SaafToolUtils.parperParam(jsonParams, "psi.source_id", "sourceId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spsc.license_num", "licenseNum", sb, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "spsc.tissue_code", "tissueCode", sb, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "se.employee_name", "createByName", sb, queryParamMap, "LIKE");  //创建人查询

        String creationDateFrom = jsonParams.getString("creationDateFrom");
        if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
            sb.append(" AND    trunc(psi.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')");
            queryParamMap.put("creationDateFrom", creationDateFrom);
        }
        String creationDateTo = jsonParams.getString("creationDateTo");
        if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
            sb.append(" AND    trunc(psi.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')");
            queryParamMap.put("creationDateTo", creationDateTo);
        }
        jsonParams.put("supplierType", getSupplierType(jsonParams.getInteger("varUserId")));
        SaafToolUtils.parperParam(jsonParams, "psi.Supplier_Type", "supplierType", sb, queryParamMap, "IN");
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY psi.creation_date DESC"); //排序
        Pagination<SrmPosSupplierInfoEntity_HI_RO> result = srmPosSupplierInfoDAO_HI_RO.findPagination(sb.toString(),
                countSql,queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * 供应商基础信息查询（不带分页）
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosSupplierInfoEntity_HI_RO> findSupplierInfoForSelf(JSONObject jsonParams) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosSupplierInfoEntity_HI_RO.QUERY_SUPPLIER_INFO);
        if (jsonParams != null && null != jsonParams.getString("supplierStatusV") && !"".equals(jsonParams.getString("supplierStatusV"))) {
            String supplierStatusV = jsonParams.getString("supplierStatusV");  //内部供应商查询范围为拟定和已批准状态
            String[] supplierStatusVList = supplierStatusV.split(",");  //按照“,”分割
            sb.append(" And (");
            int i = 0;
            for (String status : supplierStatusVList) {
                if (i != 0) {
                    sb.append(" or ");
                }
                // 改参数方式处理　hanguoqiang 2020/06/23
                sb.append("psi.supplier_status = :supplierStatusParam").append(i);
                queryParamMap.put("supplierStatusParam" + i, status);
                i++;
            }
            sb.append(")");
        }
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplier_id", sb, queryParamMap, "=");//如果是登录者本身为供应商，查询
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplierId", sb, queryParamMap, "=");//供应商Id查询
        SaafToolUtils.parperParam(jsonParams, "t.supplier_name", "supplierName", sb, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "t.supplier_number", "supplierNumber", sb, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "t.supplier_number", "supplier_Number", sb, queryParamMap, "="); //供应商编码精准查询
        SaafToolUtils.parperParam(jsonParams, "t.supplier_type", "supplierType", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.supplier_status", "supplierStatus", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.source_code", "sourceCode", sb, queryParamMap, "=");  //创建来源标识
        SaafToolUtils.parperParam(jsonParams, "t.source_id", "sourceId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spsc.license_num", "licenseNum", sb, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "spsc.tissue_code", "tissueCode", sb, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "DATE_FORMAT(t.creation_date,'%Y-%m-%d')", "creationDateTo", sb, queryParamMap, ">=");
        SaafToolUtils.parperParam(jsonParams, "DATE_FORMAT(t.creation_date,'%Y-%m-%d')", "creationDateFrom", sb, queryParamMap, "<=");
        SaafToolUtils.parperParam(jsonParams, "se.employee_name", "createByName", sb, queryParamMap, "like");  //创建人查询
        sb.append(" ORDER BY t.creation_date DESC"); //排序
        List<SrmPosSupplierInfoEntity_HI_RO> result = srmPosSupplierInfoDAO_HI_RO.findList(sb.toString(), queryParamMap);
        return result;
    }

    /**
     * 判断供应商校验（公司名称，营业执照号，用户名称）-档案自助维护/内部创建供应商
     *
     * @param checkList
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public String findSupplierCheck(Map<String, Object> checkList) {
        String result = "";
        if (checkList.size() <= 0) {
            return result;
        }
        //公司名称
        SrmPosSupplierInfoEntity_HI findSupplier = null;
        if (!(checkList.get("supplierId") == null || "".equals(checkList.get("supplierId")))) {
            Integer supplierId = Integer.parseInt(checkList.get("supplierId").toString());
            findSupplier = srmPosSupplierInfoDAO_HI.getById(supplierId);
        } else {  //新建供应商时判断公司名称是否重复
            if (!("".equals(checkList.get("supplierName")) || checkList.get("supplierName") == null)) {
                List<SrmPosSupplierInfoEntity_HI> supplierList = srmPosSupplierInfoDAO_HI.findByProperty("supplierName", checkList.get("supplierName"));
                if (supplierList != null && supplierList.size() > 0) {
                    result = "该公司名称已存在！";
                    return result;
                }
            }
            if (!(checkList.get("supplierNumber") == null || "".equals(checkList.get("supplierNumber")))) {
                List<SrmPosSupplierInfoEntity_HI> supplierList = srmPosSupplierInfoDAO_HI.findByProperty("supplierNumber", checkList.get("supplierNumber"));
                if (supplierList != null && supplierList.size() > 0) {
                    result = "该供应商编码已存在！";
                    return result;
                }
            }
        }

        if (null != findSupplier) {  //修改时判断公司名称、供应商编码是否重复
            if (!("".equals(checkList.get("supplierName")) || checkList.get("supplierName") == null)) {
                String supplierName = checkList.get("supplierName").toString();
                if (!supplierName.equals(findSupplier.getSupplierName())) {
                    List<SrmPosSupplierInfoEntity_HI> supplierList = srmPosSupplierInfoDAO_HI.findByProperty("supplierName", checkList.get("supplierName"));
                    if (supplierList != null && supplierList.size() > 0) {
                        result = "该公司名称已存在！";
                        return result;
                    }
                }
            }
            if (!("".equals(checkList.get("supplierNumber")) || checkList.get("supplierNumber") == null)) {
                String supplierNumber = checkList.get("supplierNumber").toString();
                if (!supplierNumber.equals(findSupplier.getSupplierNumber())) {
                    List<SrmPosSupplierInfoEntity_HI> supplierList = srmPosSupplierInfoDAO_HI.findByProperty("supplierNumber", checkList.get("supplierNumber"));
                    if (supplierList != null && supplierList.size() > 0) {
                        result = "该供应商编码已存在！";
                        return result;
                    }
                }
            }
        }
        //营业执照号
        SrmPosSupplierCredentialsEntity_HI findCredentials = null;
        if (!(checkList.get("credentialsId") == null || "".equals(checkList.get("credentialsId")))) {
            Integer credentialsId = Integer.parseInt(checkList.get("credentialsId").toString());
            findCredentials = srmPosSupplierCredentialsDAO_HI.getById(credentialsId);
        } else {  //新建供应商时判断营业执照号是否重复
            if (!("".equals(checkList.get("licenseNum")) || checkList.get("licenseNum") == null)) {
                List<SrmPosSupplierCredentialsEntity_HI> credentialsList = srmPosSupplierCredentialsDAO_HI.findByProperty("licenseNum", checkList.get("licenseNum"));
                if (null != credentialsList && credentialsList.size() > 0) {
                    result = "该营业执照号已存在！";
                    return result;
                }
            }
        }

        if (null != findCredentials) {  //修改时供应商时判断营业执照号是否重复
            if (!("".equals(checkList.get("licenseNum")) || checkList.get("licenseNum") == null)) {
                String licenseNum = checkList.get("licenseNum").toString();
                if (!licenseNum.equals(findCredentials.getLicenseNum())) {
                    List<SrmPosSupplierCredentialsEntity_HI> credentialsList = srmPosSupplierCredentialsDAO_HI.findByProperty("licenseNum", checkList.get("licenseNum"));
                    if (null != credentialsList && credentialsList.size() > 0) {
                        result = "该营业执照号已存在！";
                        return result;
                    }
                }
            }
        }
        //登录账号
        SaafUsersEntity_HI findSaafUser = null;
        if (!(checkList.get("userId") == null || "".equals(checkList.get("userId")))) {
            Integer userId = Integer.parseInt(checkList.get("userId").toString());
            findSaafUser = saafUsersDAO_HI.getById(userId);
        } else if (!(checkList.get("user_Name") == null || "".equals(checkList.get("user_Name")))) { //根据用户登录名称来查找用户信息
            List<SaafUsersEntity_HI> saafUserList = saafUsersDAO_HI.findByProperty("userName", checkList.get("user_Name"));
            if (null != saafUserList && saafUserList.size() > 0) {
                SaafUsersEntity_HI entityUser = saafUserList.get(0);
                findSaafUser = saafUsersDAO_HI.getById(entityUser.getUserId());
            }
        } else {  ////新建供应商时判断登录账号是否重复
            if (!(checkList.get("userName") == null || "".equals(checkList.get("userName")))) {
                List<SaafUsersEntity_HI> saafUserList = saafUsersDAO_HI.findByProperty("userName", checkList.get("userName"));
                if (null != saafUserList && saafUserList.size() > 0) {
                    result = "该登录账号已存在！";
                    return result;
                }
            }
            if (!(checkList.get("user_Name") == null || "".equals(checkList.get("user_Name")))) {
                List<SaafUsersEntity_HI> saafUserList = saafUsersDAO_HI.findByProperty("userName", checkList.get("user_Name"));
                if (null != saafUserList && saafUserList.size() > 0) {
                    result = "该登录账号已存在，供应商编码即是用户登录账号！";
                    return result;
                }
            }
        }
        if (null != findSaafUser) {
            if (!(checkList.get("userName") == null || "".equals(checkList.get("userName")))) {
                String userName = checkList.get("userName").toString();
                if (!userName.equals(findSaafUser.getUserName())) {
                    List<SaafUsersEntity_HI> saafUserList = saafUsersDAO_HI.findByProperty("userName", checkList.get("userName"));
                    if (null != saafUserList && saafUserList.size() > 0) {
                        result = "该登录账号已存在！";
                        return result;
                    }
                }
            }
            if (!(checkList.get("user_Name") == null || "".equals(checkList.get("user_Name")))) {  //用于供应商接口（数据输入）的供应商编码判断是否重复，此时供应商编码等同于用户登录账号
                String userName = checkList.get("user_Name").toString();
                if (!userName.equals(findSaafUser.getUserName())) {
                    List<SaafUsersEntity_HI> saafUserList = saafUsersDAO_HI.findByProperty("userName", checkList.get("user_Name"));
                    if (null != saafUserList && saafUserList.size() > 0) {
                        result = "该登录账号已存在，供应商编码即是用户登录账号！";
                        return result;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Description：注册审核
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * supplierId  供应商ID  NUMBER  Y
     * supplierNumber  供应商编码  VARCHAR2  N
     * supplierName  供应商名称  VARCHAR2  Y
     * supplierShortName  供应商简称  VARCHAR2  N
     * supplierType  供应商类型(POS_SUPPLIER_TYPE)  VARCHAR2  N
     * supplierClassify  供应商类别(POS_SUPPLIER_CLASSIFY)  VARCHAR2  N
     * supplierIndustry  供应商行业(POS_SUPPLIER_INDUSTRY)  VARCHAR2  N
     * supplierStatus  供应商状态(POS_SUPPLIER_STATUS)  VARCHAR2  N
     * homeUrl  企业网址  VARCHAR2  N
     * companyPhone  公司电话  VARCHAR2  N
     * companyFax  公司传真  VARCHAR2  N
     * relatedSupplierId  关联供应商ID   NUMBER  N
     * parentSupplierId  父供应商ID   NUMBER  N
     * staffNum  员工数量  NUMBER  N
     * floorArea  占地面积  VARCHAR2  N
     * companyDescription  公司简介  CLOB  N
     * purchaseFlag  可采购标识  VARCHAR2  N
     * paymentFlag  可付款标识  VARCHAR2  N
     * finClassify  (废弃)财务分类(POS_FIN_CLASSIFY)  VARCHAR2  N
     * settleAcctType  (废弃)结算方式(POS_SETTLE_ACCT_TYPE)  VARCHAR2  N
     * acctCheckStaff  (废弃)对账人员编码(POS_ACCT_CHECK_CREW)  VARCHAR2  N
     * acctCheckType  (废弃)对账方式(POS_ACCT_CHECK_TYPE)  VARCHAR2  N
     * passU9Flag  (废弃)传U9标识  VARCHAR2  N
     * supplierFileId  公司简介附件  NUMBER  N
     * posTax  (废弃)税组合(POS_TAX_LIST)  VARCHAR2  N
     * posAcctCondition  (废弃)立账条件(POS_ACCOUNT_CONDITION)  VARCHAR2  N
     * ableCheckOrderFlag  (废弃)允许确认采购标识  VARCHAR2  N
     * ableEditFlag  (废弃)是否可修改  VARCHAR2  N
     * address  (废弃)供应商地址  VARCHAR2  N
     * srmDelivery  (废弃)允许平台送货  VARCHAR2  N
     * logisticsSupplier  (废弃)是否为物流供应商  VARCHAR2  N
     * blacklistFlag  黑名单供应商(Y/N)  VARCHAR2  N
     * sourceCode  供应商来源(REGISTER:注册，CREATE:创建，其他类型为其他系统来源)  VARCHAR2  N
     * sourceId  供应商来源ID（当供应商数据来源于其他系统时才有）  VARCHAR2  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核通过时间  DATE  N
     * approvalComments  审核意见  VARCHAR2  N
     * isManufacturer  制造商(Y/N)  VARCHAR2  N
     * isAgent  代理商(Y/N)  VARCHAR2  N
     * isAvl  AVL供应商(Y/N)  VARCHAR2  N
     * isPurchaser  代购商(Y/N)  VARCHAR2  N
     * isTraders  贸易商(Y/N)  VARCHAR2  N
     * feedbackFileId  供应商反馈信息附件ID  NUMBER  N
     * managementScope  经营范围  VARCHAR2  N
     * registeredCapital  注册资金  VARCHAR2  N
     * supplierEnterpriseType  企业类型  VARCHAR2  N
     * outputSales  年产值/年销售额  VARCHAR2  N
     * paymentTerm  付款条件  VARCHAR2  N
     * paymentMethod  付款方式  VARCHAR2  N
     * taxonomy  税分类  VARCHAR2  N
     * interfaceStart  接口开始时间  DATE  N
     * returnType  回货方式  VARCHAR2  N
     * isOther  其他供应商(Y/N)  VARCHAR2  N
     * monthly  月结  NUMBER  N
     * parentCompany  母公司  VARCHAR2  N
     * selfOutletsNumber  自营网点数量  NUMBER  N
     * ownVehiclesNumber  自有车辆数量  NUMBER  N
     * callingVehiclesNumber  挂靠车辆数量  NUMBER  N
     * isAdvancedCertification  高级认证企业(Y/N)  VARCHAR2  N
     * isGeneralCertification  一般认证企业(Y/N)  VARCHAR2  N
     * isGeneralCredit  一般信用企业(Y/N)  VARCHAR2  N
     * isUntrustworthy  失信企业(Y/N)  VARCHAR2  N
     * managersNumber  管理人员数量  NUMBER  N
     * techniciansNumber  技术人员数量  NUMBER  N
     * workersNumber  工人数量  NUMBER  N
     * aeoFileId  AEO认证附件  NUMBER  N
     * supplierEbsNumber  供应商EBS编号  VARCHAR2  N
     * temporaryFlag  临时供应商标记(Y/N)  VARCHAR2  N
     * requestId  报文请求ID  VARCHAR2  N
     * companyDescription  供应商简介  CLOB  N
     * companyRegisteredAddress  公司注册地址  VARCHAR2  N
     * registeredCapital  注册资金(万)  VARCHAR2  N
     * independentLegalPersonFlag  是否独立法人  VARCHAR2  N
     * valueAddedTaxInvoiceFlag  能否开6个税点的增值税专用发票  VARCHAR2  N
     * valueAddedTaxInvoiceDesc  能否开6个税点的增值税专用发票-说明  VARCHAR2  N
     * associatedCompany  关联公司  VARCHAR2  N
     * region  意向服务区域  VARCHAR2  N
     * supplierEbsNumber  供应商EBS编号  VARCHAR2  N
     * requestId  报文请求ID  VARCHAR2  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierNumber  供应商编码  VARCHAR2  N
     * supplierName  供应商名称  VARCHAR2  Y
     * supplierShortName  供应商简称  VARCHAR2  N
     * supplierType  供应商类型(POS_SUPPLIER_TYPE)  VARCHAR2  N
     * supplierClassify  (废弃)供应商分类(POS_SUPPLIER_CLASSIFY)  VARCHAR2  N
     * supplierIndustry  供应商行业(POS_SUPPLIER_INDUSTRY)  VARCHAR2  N
     * supplierStatus  供应商状态(POS_SUPPLIER_STATUS)  VARCHAR2  N
     * homeUrl  公司官网  VARCHAR2  N
     * companyPhone  公司电话  VARCHAR2  N
     * companyFax  公司传真  VARCHAR2  N
     * relatedSupplierId  关联供应商ID   NUMBER  N
     * parentSupplierId  父供应商ID   NUMBER  N
     * staffNum  员工数量  NUMBER  N
     * floorArea  占地面积  VARCHAR2  N
     * purchaseFlag  可采购标识  VARCHAR2  N
     * paymentFlag  可付款标识  VARCHAR2  N
     * finClassify  (废弃)财务分类(POS_FIN_CLASSIFY)  VARCHAR2  N
     * settleAcctType  (废弃)结算方式(POS_SETTLE_ACCT_TYPE)  VARCHAR2  N
     * acctCheckStaff  (废弃)对账人员编码(POS_ACCT_CHECK_CREW)  VARCHAR2  N
     * acctCheckType  (废弃)对账方式(POS_ACCT_CHECK_TYPE)  VARCHAR2  N
     * passU9Flag  (废弃)传U9标识  VARCHAR2  N
     * supplierFileId  公司简介附件  NUMBER  N
     * posTax  (废弃)税组合(POS_TAX_LIST)  VARCHAR2  N
     * posAcctCondition  (废弃)立账条件(POS_ACCOUNT_CONDITION)  VARCHAR2  N
     * ableCheckOrderFlag  (废弃)允许确认采购标识  VARCHAR2  N
     * ableEditFlag  (废弃)是否可修改  VARCHAR2  N
     * address  (废弃)供应商地址  VARCHAR2  N
     * srmDelivery  (废弃)允许平台送货  VARCHAR2  N
     * logisticsSupplier  (废弃)是否为物流供应商  VARCHAR2  N
     * blacklistFlag  黑名单供应商(Y/N)  VARCHAR2  N
     * sourceCode  供应商来源(REGISTER:注册，CREATE:创建，其他类型为其他系统来源)  VARCHAR2  N
     * sourceId  供应商来源ID（当供应商数据来源于其他系统时才有）  VARCHAR2  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核通过时间  DATE  N
     * approvalComments  审核意见  VARCHAR2  N
     * gradeLineId  供应商级别行ID，srm_pos_supplier_grade_lines  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveRegisterAuditSta(JSONObject params) throws Exception {
        LOGGER.info("注册审核信息，参数：" + params.toString());
        Integer userId = params.getInteger("varUserId");  //用户Id
        String action = params.getString("action");  //操作按钮
        SrmPosSupplierInfoEntity_HI supplierRow = null;
        Integer supplierId = params.getInteger("supplierId");
        SendMailUtil sendMailUtil = new SendMailUtil(true);
        //String[] str;
        if (null == action || "".equals(action)) {
            return SToolUtils.convertResultJSONObj("E", "操作字符为空！", 0, null);
        }
        if (!(supplierId == null || "".equals(supplierId))) {
            supplierRow = srmPosSupplierInfoDAO_HI.getById(supplierId);
            supplierRow.setApprovalUserId(userId);//审核人
            supplierRow.setApprovalDate(new Date());//审核时间
            supplierRow.setVersionNum(1);
            supplierRow.setOperatorUserId(userId);
            supplierRow.setCreationDate(supplierRow.getCreationDate());
//			supplierRow.setCreatedBy(userId);
            supplierRow.setLastUpdateDate(new Date());
            supplierRow.setLastUpdatedBy(userId);
            List<SrmPosSupplierContactsEntity_HI> contactsList = srmPosSupplierContactsDAO_HI.findByProperty("supplierId", supplierId);
            //插入系统通知
            List<SaafUsersEntity_HI> usersList = saafUsersDAO_HI.findByProperty("supplierId", supplierId);
            SaafUsersEntity_HI usersEntity = null;
            if (null != usersList && usersList.size() > 0) {
                usersEntity = usersList.get(0);
            }
            if (contactsList != null && contactsList.size() > 0) {
                SrmPosSupplierInfoEntity_HI checkSubmitStatus = srmPosSupplierInfoDAO_HI.getById(supplierId);
                //查询单据状态是否一致
                if ("SUBMITTED".equals(checkSubmitStatus.getSupplierStatus())) {
                    if ("pass".equals(action)) {//通过
                        supplierRow.setSupplierStatus("APPROVED");//已批准
                        srmPosSupplierInfoDAO_HI.saveEntity(supplierRow);
                        if (contactsList != null && contactsList.size() > 0) {
                            for (int c = 0; c < contactsList.size(); c++) {
                                String emailAddress = contactsList.get(c).getEmailAddress();
                                //发送邮件给账号的联系人
                                sendMailUtil.doSendHtmlEmail("恭喜！您注册的企业审核已通过！", "<p>尊敬的：" +
                                        contactsList.get(c).getContactName() + "，您好！</p><br/>通过SRM平台申请的资料，我们已经完成初步审核；贵公司已经纳入我们的潜在供应商档案中；后续及时关注我们的信息，谢谢合作！", new String[]{emailAddress});
                            }
                        }
                        //插入系统通知
                        iSrmBaseNotifications.insertSrmBaseNotifications("供应商注册审核", "您好！\n" +
                                        "通过SRM平台申请的资料，我们已经完成初步审核；贵公司已经纳入我们的潜在供应商档案中；后续及时关注我们的信息，谢谢合作！"
                                , usersEntity.getUserId(), "srm_pos_supplier_info", supplierId, "supplierId", "home.registerAuditListDetail", userId);
                        /*if (null != usersList && usersList.size() > 0) {
                            for(int t=0;t<usersList.size();t++){
                                iSrmBaseNotifications.insertSrmBaseNotifications("供应商注册审核", "您好！\n" +
                                                "通过SRM平台申请的资料，我们已经完成初步审核；贵公司已经纳入我们的潜在供应商档案中；后续及时关注我们的信息，谢谢合作！"
                                        , usersList.get(t).getUserId(), "srm_pos_supplier_info", supplierId, "supplierId", "home.registerAuditListDetail", userId);
                            }
                        }*/

                    } else {//驳回
                        supplierRow.setApprovalComments(params.getString("approvalComments"));//审核意见
                        supplierRow.setSupplierStatus("REJECTED");//驳回
                        srmPosSupplierInfoDAO_HI.saveEntity(supplierRow);
                        if (contactsList != null && contactsList.size() > 0) {
                            for (int d = 0; d < contactsList.size(); d++) {
                                String emailAddress2 = contactsList.get(d).getEmailAddress();
                                //发送邮件给账号的联系人
                                sendMailUtil.doSendHtmlEmail("抱歉！您注册的企业审核未能通过！",  "<p>尊敬的：" +
                                        contactsList.get(d).getContactName() +"，您好！</p><br/>" + "您所提交的资料，没有通过我们的初步审核，请您查看具体的原因，并确认您的资料是否有误，如果需要可重新提交或者跟我们联系！", new String[]{emailAddress2});
                            }
                        }
                        //插入系统通知
                        iSrmBaseNotifications.insertSrmBaseNotifications("供应商注册审核", "您好！\n" +
                                        "您所提交的资料，没有通过我们的初步审核，请您查看具体的原因，并确认您的资料是否有误，如果需要可重新提交或者跟我们联系！"
                                , usersEntity.getUserId(), "srm_pos_supplier_info", supplierId, "supplierId", "home.registerAuditListDetail", userId);
                        /*if (null != usersList && usersList.size() > 0) {
                            for(int t=0;t<usersList.size();t++){
                                iSrmBaseNotifications.insertSrmBaseNotifications("供应商注册审核", "您好！\n" +
                                                "您所提交的资料，没有通过我们的初步审核，请您查看具体的原因，并确认您的资料是否有误，如果需要可重新提交或者跟我们联系！"
                                        , usersList.get(t).getUserId(), "srm_pos_supplier_info", supplierId, "supplierId", "home.registerAuditListDetail", userId);
                            }
                        }*/
                    }
                } else {
                    return SToolUtils.convertResultJSONObj("E", "提交失败，该单据状态已变更，请刷新页面！", 1, null);
                }
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "审核失败，" + params.getString("supplierId") + "不存在", 0, null);
        }
        return SToolUtils.convertResultJSONObj("S", "审核通过", 1, null);
    }

    /**
     * 注册审核-重置密码
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateResetPassWord(JSONObject params) throws Exception {
        LOGGER.info("重置密码信息，参数：" + params.toString());
        Integer supplierId = params.getInteger("supplierId");
        String userName = params.getString("userName");
        Integer userId = params.getInteger("varUserId");  //用户Id

        if (!(supplierId == null || "".equals(supplierId))) {
            SHA1Util sha1 = new SHA1Util();
            SaafUsersEntity_HI userEntity = new SaafUsersEntity_HI();
            //生成6位随机密码(字母+数字)
            String passWord = getStringRandom(6);
            List<SaafUsersEntity_HI> userList = saafUsersDAO_HI.findByProperty("userName", userName);
            if (userList != null && userList.size() > 0) {
                userEntity = userList.get(0);
                try {
                    //密码加密
                    userEntity.setEncryptedPassword(sha1.getEncrypt(passWord));
                } catch (Exception e) {
                    LOGGER.error("未知错误:{}", e);
                    return com.yhg.base.utils.SToolUtils.convertResultJSONObj("E", "密码加密错误！", 0, null);
                }
                userEntity.setOperatorUserId(userId);
                saafUsersDAO_HI.update(userEntity);

                /*SrmPosSupplierContactsEntity_HI_RO contactsEntity = iSrmPosSupplierContacts.findFirstContact(supplierId);
                if (contactsEntity != null && contactsEntity.getEmailAddress() != null) {
                    SendMailUtil sendMailUtil = new SendMailUtil(false);
                    String emailAddress = contactsEntity.getEmailAddress();
                    //发送邮件给账号的联系人
                    sendMailUtil.doSendHtmlEmail("SRM系统重置密码成功", "<p>您好！<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp您的密码已被重置，新密码为：" + passWord + "。</p>", new String[]{emailAddress});
                }*/
                List<SrmPosSupplierContactsEntity_HI_RO> contactsEntity = iSrmPosSupplierContacts.findSupplierContact(supplierId);
                String title = "SRM系统重置密码成功";
                String content = "<p>您好！<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp您的 "+userName+" 密码已被重置，新密码为：" + passWord + "。</p>";
                sendEmailToContacts(contactsEntity, title, content);
            }
        } else {
            return com.yhg.base.utils.SToolUtils.convertResultJSONObj("E", "重置密码失败，" + params.getString("supplierId") + "不存在", 0, null);
        }
        return com.yhg.base.utils.SToolUtils.convertResultJSONObj("S", "重置密码成功", 1, null);

    }


    private void sendEmailToContacts(final List<SrmPosSupplierContactsEntity_HI_RO> contactList, final String title, final String content) {
        ArrayList<String> emailAddressList = new ArrayList<>();
        for (SrmPosSupplierContactsEntity_HI_RO contact : contactList) {
            String emailAddress = contact.getEmailAddress();
            //如果邮箱不为空
            if (emailAddress != null && !"".equals(emailAddress)) {
                //如果当前联系人没有失效
                if (contact.getFailureDate() == null || contact.getFailureDate().after(new Date())) {
                    emailAddressList.add(emailAddress);
                }
            }
        }
        String[] emailAddress = new String[emailAddressList.size()];
        emailAddress = emailAddressList.toArray(emailAddress);
        sendMailUtil.doSendHtmlEmail(title, content, emailAddress);
    }


    private void sendEmailToRes(final List<SaafUserEmployeesEntity_HI_RO> contactList, final String title, final String content) {
        ArrayList<String> emailAddressList = new ArrayList<>();
        for (SaafUserEmployeesEntity_HI_RO contact : contactList) {
            String emailAddress = contact.getEmail();
            //如果邮箱不为空
            if (emailAddress != null && !"".equals(emailAddress)) {
                //如果当前联系人没有失效
                if (contact.getEndDateActive() == null || contact.getEndDateActive().after(new Date())) {
                    emailAddressList.add(emailAddress);
                }
            }
        }
        String[] emailAddress = new String[emailAddressList.size()];
        emailAddress = emailAddressList.toArray(emailAddress);
        sendMailUtil.doSendHtmlEmail(title, content, emailAddress);
    }

    //生成随机数字和字母,
    public String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        //length为几位密码
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 删除供应商
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteSupplier(JSONObject params) throws Exception {
        LOGGER.info("删除供应商信息，参数：" + params.toString());
        try {
            SrmPosSupplierInfoEntity_HI supplierRow = null;
            Integer supplierId = params.getInteger("supplierId");
            if (!(supplierId == null || "".equals(supplierId))) {
                supplierRow = srmPosSupplierInfoDAO_HI.getById(supplierId);
                List<SrmPosSupplierCredentialsEntity_HI> credentialsList = srmPosSupplierCredentialsDAO_HI.findByProperty("supplierId", supplierId);
                List<SrmPosSupplierCategoriesEntity_HI> categoriesList = srmPosSupplierCategoriesDAO_HI.findByProperty("supplierId", supplierId);
                List<SrmPosSupplierCertificateEntity_HI> certificateList = srmPosSupplierCertificateDAO_HI.findByProperty("supplierId", supplierId);
                List<SrmPosSupplierBankEntity_HI> bankList = srmPosSupplierBankDAO_HI.findByProperty("supplierId", supplierId);
                List<SrmPosSupplierContactsEntity_HI> contactsList = srmPosSupplierContactsDAO_HI.findByProperty("supplierId", supplierId);
                List<SrmPosSupplierAddressesEntity_HI> addressesList = srmPosSupplierAddressesDAO_HI.findByProperty("supplierId", supplierId);
                if ("DRAFT".equals(supplierRow.getSupplierStatus()) || "REJECTED".equals(supplierRow.getSupplierStatus())) {
                    if (supplierRow != null) {
                        srmPosSupplierInfoDAO_HI.delete(supplierRow);
                    }
                    if (credentialsList != null && credentialsList.size() > 0) {
                        for (int i = 0; i < credentialsList.size(); i++) {
                            srmPosSupplierCredentialsDAO_HI.delete(credentialsList.get(i).getCredentialsId());
                        }
                    }
                    if (categoriesList != null && categoriesList.size() > 0) {
                        for (int j = 0; j < categoriesList.size(); j++) {
                            srmPosSupplierCategoriesDAO_HI.delete(categoriesList.get(j).getSupplierCategoryId());
                        }
                    }
                    if (certificateList != null && certificateList.size() > 0) {
                        for (int a = 0; a < certificateList.size(); a++) {
                            srmPosSupplierCertificateDAO_HI.delete(certificateList.get(a).getCertificateId());
                        }
                    }
                    if (bankList != null && bankList.size() > 0) {
                        for (int b = 0; b < bankList.size(); b++) {
                            srmPosSupplierBankDAO_HI.delete(bankList.get(b).getBankAccountId());
                        }
                    }
                    if (contactsList != null && contactsList.size() > 0) {
                        for (int c = 0; c < contactsList.size(); c++) {
                            srmPosSupplierContactsDAO_HI.delete(contactsList.get(c).getSupplierContactId());
                        }
                    }
                    if (addressesList != null && addressesList.size() > 0) {
                        for (int d = 0; d < addressesList.size(); d++) {
                            srmPosSupplierAddressesDAO_HI.delete(addressesList.get(d).getSupplierAddressId());
                        }
                    }
                } else {
                    return SToolUtils.convertResultJSONObj("E", "删除供应商失败,只能删除拟定和驳回的单据!", 0, null);
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除供应商失败，" + params.getString("supplierId") + "不存在", 0, null);
            }
            return SToolUtils.convertResultJSONObj("S", "删除供应商成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：保存供应商及其子表信息
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * supplierId  供应商ID  NUMBER  Y
     * supplierNumber  供应商编码  VARCHAR2  N
     * supplierName  供应商名称  VARCHAR2  Y
     * supplierShortName  供应商简称  VARCHAR2  N
     * supplierType  供应商类型(POS_SUPPLIER_TYPE)  VARCHAR2  N
     * supplierClassify  供应商类别(POS_SUPPLIER_CLASSIFY)  VARCHAR2  N
     * supplierIndustry  供应商行业(POS_SUPPLIER_INDUSTRY)  VARCHAR2  N
     * supplierStatus  供应商状态(POS_SUPPLIER_STATUS)  VARCHAR2  N
     * homeUrl  企业网址  VARCHAR2  N
     * companyPhone  公司电话  VARCHAR2  N
     * companyFax  公司传真  VARCHAR2  N
     * relatedSupplierId  关联供应商ID   NUMBER  N
     * parentSupplierId  父供应商ID   NUMBER  N
     * staffNum  员工数量  NUMBER  N
     * floorArea  占地面积  VARCHAR2  N
     * companyDescription  公司简介  CLOB  N
     * purchaseFlag  可采购标识  VARCHAR2  N
     * paymentFlag  可付款标识  VARCHAR2  N
     * finClassify  (废弃)财务分类(POS_FIN_CLASSIFY)  VARCHAR2  N
     * settleAcctType  (废弃)结算方式(POS_SETTLE_ACCT_TYPE)  VARCHAR2  N
     * acctCheckStaff  (废弃)对账人员编码(POS_ACCT_CHECK_CREW)  VARCHAR2  N
     * acctCheckType  (废弃)对账方式(POS_ACCT_CHECK_TYPE)  VARCHAR2  N
     * passU9Flag  (废弃)传U9标识  VARCHAR2  N
     * supplierFileId  公司简介附件  NUMBER  N
     * posTax  (废弃)税组合(POS_TAX_LIST)  VARCHAR2  N
     * posAcctCondition  (废弃)立账条件(POS_ACCOUNT_CONDITION)  VARCHAR2  N
     * ableCheckOrderFlag  (废弃)允许确认采购标识  VARCHAR2  N
     * ableEditFlag  (废弃)是否可修改  VARCHAR2  N
     * address  (废弃)供应商地址  VARCHAR2  N
     * srmDelivery  (废弃)允许平台送货  VARCHAR2  N
     * logisticsSupplier  (废弃)是否为物流供应商  VARCHAR2  N
     * blacklistFlag  黑名单供应商(Y/N)  VARCHAR2  N
     * sourceCode  供应商来源(REGISTER:注册，CREATE:创建，其他类型为其他系统来源)  VARCHAR2  N
     * sourceId  供应商来源ID（当供应商数据来源于其他系统时才有）  VARCHAR2  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核通过时间  DATE  N
     * approvalComments  审核意见  VARCHAR2  N
     * isManufacturer  制造商(Y/N)  VARCHAR2  N
     * isAgent  代理商(Y/N)  VARCHAR2  N
     * isAvl  AVL供应商(Y/N)  VARCHAR2  N
     * isPurchaser  代购商(Y/N)  VARCHAR2  N
     * isTraders  贸易商(Y/N)  VARCHAR2  N
     * feedbackFileId  供应商反馈信息附件ID  NUMBER  N
     * managementScope  经营范围  VARCHAR2  N
     * registeredCapital  注册资金  VARCHAR2  N
     * supplierEnterpriseType  企业类型  VARCHAR2  N
     * outputSales  年产值/年销售额  VARCHAR2  N
     * paymentTerm  付款条件  VARCHAR2  N
     * paymentMethod  付款方式  VARCHAR2  N
     * taxonomy  税分类  VARCHAR2  N
     * interfaceStart  接口开始时间  DATE  N
     * returnType  回货方式  VARCHAR2  N
     * isOther  其他供应商(Y/N)  VARCHAR2  N
     * monthly  月结  NUMBER  N
     * parentCompany  母公司  VARCHAR2  N
     * selfOutletsNumber  自营网点数量  NUMBER  N
     * ownVehiclesNumber  自有车辆数量  NUMBER  N
     * callingVehiclesNumber  挂靠车辆数量  NUMBER  N
     * isAdvancedCertification  高级认证企业(Y/N)  VARCHAR2  N
     * isGeneralCertification  一般认证企业(Y/N)  VARCHAR2  N
     * isGeneralCredit  一般信用企业(Y/N)  VARCHAR2  N
     * isUntrustworthy  失信企业(Y/N)  VARCHAR2  N
     * managersNumber  管理人员数量  NUMBER  N
     * techniciansNumber  技术人员数量  NUMBER  N
     * workersNumber  工人数量  NUMBER  N
     * aeoFileId  AEO认证附件  NUMBER  N
     * supplierEbsNumber  供应商EBS编号  VARCHAR2  N
     * temporaryFlag  临时供应商标记(Y/N)  VARCHAR2  N
     * requestId  报文请求ID  VARCHAR2  N
     * companyDescription  供应商简介  CLOB  N
     * companyRegisteredAddress  公司注册地址  VARCHAR2  N
     * registeredCapital  注册资金(万)  VARCHAR2  N
     * independentLegalPersonFlag  是否独立法人  VARCHAR2  N
     * valueAddedTaxInvoiceFlag  能否开6个税点的增值税专用发票  VARCHAR2  N
     * valueAddedTaxInvoiceDesc  能否开6个税点的增值税专用发票-说明  VARCHAR2  N
     * associatedCompany  关联公司  VARCHAR2  N
     * region  意向服务区域  VARCHAR2  N
     * supplierEbsNumber  供应商EBS编号  VARCHAR2  N
     * requestId  报文请求ID  VARCHAR2  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierNumber  供应商编码  VARCHAR2  N
     * supplierName  供应商名称  VARCHAR2  Y
     * supplierShortName  供应商简称  VARCHAR2  N
     * supplierType  供应商类型(POS_SUPPLIER_TYPE)  VARCHAR2  N
     * supplierClassify  (废弃)供应商分类(POS_SUPPLIER_CLASSIFY)  VARCHAR2  N
     * supplierIndustry  供应商行业(POS_SUPPLIER_INDUSTRY)  VARCHAR2  N
     * supplierStatus  供应商状态(POS_SUPPLIER_STATUS)  VARCHAR2  N
     * homeUrl  公司官网  VARCHAR2  N
     * companyPhone  公司电话  VARCHAR2  N
     * companyFax  公司传真  VARCHAR2  N
     * relatedSupplierId  关联供应商ID   NUMBER  N
     * parentSupplierId  父供应商ID   NUMBER  N
     * staffNum  员工数量  NUMBER  N
     * floorArea  占地面积  VARCHAR2  N
     * purchaseFlag  可采购标识  VARCHAR2  N
     * paymentFlag  可付款标识  VARCHAR2  N
     * finClassify  (废弃)财务分类(POS_FIN_CLASSIFY)  VARCHAR2  N
     * settleAcctType  (废弃)结算方式(POS_SETTLE_ACCT_TYPE)  VARCHAR2  N
     * acctCheckStaff  (废弃)对账人员编码(POS_ACCT_CHECK_CREW)  VARCHAR2  N
     * acctCheckType  (废弃)对账方式(POS_ACCT_CHECK_TYPE)  VARCHAR2  N
     * passU9Flag  (废弃)传U9标识  VARCHAR2  N
     * supplierFileId  公司简介附件  NUMBER  N
     * posTax  (废弃)税组合(POS_TAX_LIST)  VARCHAR2  N
     * posAcctCondition  (废弃)立账条件(POS_ACCOUNT_CONDITION)  VARCHAR2  N
     * ableCheckOrderFlag  (废弃)允许确认采购标识  VARCHAR2  N
     * ableEditFlag  (废弃)是否可修改  VARCHAR2  N
     * address  (废弃)供应商地址  VARCHAR2  N
     * srmDelivery  (废弃)允许平台送货  VARCHAR2  N
     * logisticsSupplier  (废弃)是否为物流供应商  VARCHAR2  N
     * blacklistFlag  黑名单供应商(Y/N)  VARCHAR2  N
     * sourceCode  供应商来源(REGISTER:注册，CREATE:创建，其他类型为其他系统来源)  VARCHAR2  N
     * sourceId  供应商来源ID（当供应商数据来源于其他系统时才有）  VARCHAR2  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核通过时间  DATE  N
     * approvalComments  审核意见  VARCHAR2  N
     * gradeLineId  供应商级别行ID，srm_pos_supplier_grade_lines  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveSupplierAll(JSONObject jsonParams) throws Exception{
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId");  //用户Id
        String action = jsonParams.getString("action");  //操作按钮
        if (null == action || "".equals(action)) {
            return SToolUtils.convertResultJSONObj("E", "操作字符为空！", 0, null);
        }

        Integer supplierId;  //供应商Id
        SrmPosSupplierInfoEntity_HI supplierInfo = JSON.parseObject(jsonParams.toJSONString(), SrmPosSupplierInfoEntity_HI.class); //供应商基础信息

        JSONObject saafUserJSON = jsonParams.getJSONObject("saafUser");  //供应商对应的用户信息
        SaafUsersEntity_HI saafUser = JSON.parseObject(saafUserJSON.toJSONString(), SaafUsersEntity_HI.class);

        JSONArray credentialsListJSON = jsonParams.getJSONArray("credentialsList");//资质信息
        List<SrmPosSupplierCredentialsEntity_HI> credentialsList = JSON.parseArray(credentialsListJSON.toJSONString(), SrmPosSupplierCredentialsEntity_HI.class);

        JSONArray categoriesListJSON = jsonParams.getJSONArray("categoriesList"); //产品和服务
        List<SrmPosSupplierCategoriesEntity_HI> categoriesList = JSON.parseArray(categoriesListJSON.toJSONString(), SrmPosSupplierCategoriesEntity_HI.class);

        JSONArray certificateListJSON = jsonParams.getJSONArray("certificateList");//认证与证书
        List<SrmPosSupplierCertificateEntity_HI> certificateList = JSON.parseArray(certificateListJSON.toJSONString(), SrmPosSupplierCertificateEntity_HI.class);

        JSONArray supplierBankListJSON = jsonParams.getJSONArray("supplierBankList");//银行信息
        List<SrmPosSupplierBankEntity_HI> supplierBankList = JSON.parseArray(supplierBankListJSON.toJSONString(), SrmPosSupplierBankEntity_HI.class);

        JSONArray supplierContactsListJSON = jsonParams.getJSONArray("supplierContactsList");//联系人
        List<SrmPosSupplierContactsEntity_HI> supplierContactsList = JSON.parseArray(supplierContactsListJSON.toJSONString(), SrmPosSupplierContactsEntity_HI.class);

        JSONArray supplierAddressesListJSON = jsonParams.getJSONArray("supplierAddressesList"); //地址簿头表
        List<SrmPosSupplierAddressesEntity_HI> supplierAddressesList = JSON.parseArray(supplierAddressesListJSON.toJSONString(), SrmPosSupplierAddressesEntity_HI.class);

        Map<String, Object> checkList = new HashMap<String, Object>();  //判断供应商校验（公司名称，营业执照号，用户名称）-档案自助维护/内部创建供应商
        if (null != supplierInfo.getSupplierName() && !"".equals(supplierInfo.getSupplierName())) {
            checkList.put("supplierName", supplierInfo.getSupplierName().trim());
        }
        if (!(supplierInfo.getSupplierId() == null || "".equals(supplierInfo.getSupplierId()))) {
            checkList.put("supplierId", supplierInfo.getSupplierId());
        }

        if (credentialsList.size() > 0) {
            String licenseNum = null;
            Integer credentialsId = null;
            if (credentialsList.get(0).getLicenseNum() != null) {
                licenseNum = credentialsList.get(0).getLicenseNum().trim();
            }
            if (credentialsList.get(0).getCredentialsId() != null) {
                credentialsId = credentialsList.get(0).getCredentialsId();
            }
            if (!(null == licenseNum || "".equals(licenseNum))) {
                checkList.put("licenseNum", licenseNum);
            }
            if (!(null == credentialsId || "".equals(credentialsId))) {
                checkList.put("credentialsId", credentialsId);
            }
        }

        if (!(saafUser.getUserId() == null || "".equals(saafUser.getUserId()))) {
            checkList.put("userId", saafUser.getUserId());
        }
        if (!(saafUser.getUserName() == null || "".equals(saafUser.getUserName()))) {
            checkList.put("userName", saafUser.getUserName().trim());
        }

        String result = findSupplierCheck(checkList);
        if (!(result == null || "".equals(result))) {  //判断供应商校验（公司名称，营业执照号，用户名称）-档案自助维护/内部创建供应商
            return SToolUtils.convertResultJSONObj("E", result, 0, null);
        }

        SrmPosSupplierInfoEntity_HI findSupplierInfo = srmPosSupplierInfoDAO_HI.getById(supplierInfo.getSupplierId());
        boolean flag = false;
        boolean temp = false;
        //添加内部供应商提交，否则内部提交报错
        if ("save".equals(action) || "submit".equals(action) || "saveInside".equals(action) || "submitInside".equals(action)) {
            if (null != findSupplierInfo) {
                if (findSupplierInfo.getSupplierNumber() == null || "".equals(findSupplierInfo.getSupplierNumber())) { //updatet时，供应商编码为空时自动赋值
                    String newSupplierNumber = getSupplierNumber();
                    findSupplierInfo.setSupplierNumber(newSupplierNumber);  //供应商编码
                }
                findSupplierInfo.setSupplierName(supplierInfo.getSupplierName());
                findSupplierInfo.setSupplierShortName(supplierInfo.getSupplierShortName());
                findSupplierInfo.setSupplierType(supplierInfo.getSupplierType());
                findSupplierInfo.setCompanyRegisteredAddress(supplierInfo.getCompanyRegisteredAddress());
                findSupplierInfo.setRegisteredCapital(supplierInfo.getRegisteredCapital());
                findSupplierInfo.setIndependentLegalPersonFlag(supplierInfo.getIndependentLegalPersonFlag());
                findSupplierInfo.setValueAddedTaxInvoiceFlag(supplierInfo.getValueAddedTaxInvoiceFlag());
                findSupplierInfo.setValueAddedTaxInvoiceDesc(supplierInfo.getValueAddedTaxInvoiceDesc());
                findSupplierInfo.setAssociatedCompany(supplierInfo.getAssociatedCompany());
                findSupplierInfo.setRegion(supplierInfo.getRegion());
                findSupplierInfo.setCompanyPhone(supplierInfo.getCompanyPhone());
                findSupplierInfo.setStaffNum(supplierInfo.getStaffNum());
                findSupplierInfo.setSupplierIndustry(supplierInfo.getSupplierIndustry());
                findSupplierInfo.setCompanyFax(supplierInfo.getCompanyFax());
                findSupplierInfo.setSupplierFileId(supplierInfo.getSupplierFileId());
                findSupplierInfo.setCompanyDescription(supplierInfo.getCompanyDescription());
                findSupplierInfo.setSupplierStatus(supplierInfo.getSupplierStatus());
                findSupplierInfo.setApprovalComments(supplierInfo.getApprovalComments());
                findSupplierInfo.setOperatorUserId(userId);
                flag = true;
            } else {
                if (supplierInfo.getSupplierNumber() == null || "".equals(supplierInfo.getSupplierNumber())) { //insert时，供应商编码为空时自动赋值
                    String newSupplierNumber = getSupplierNumber();
                    supplierInfo.setSupplierNumber(newSupplierNumber);  //供应商编码
                }
                supplierInfo.setAbleEditFlag("Y");
                supplierInfo.setPassU9Flag("N");
                supplierInfo.setCreatedBy(userId);
                supplierInfo.setOperatorUserId(userId);
                flag = false;
            }
        }

        if ("save".equals(action)) {   //保存按钮
            if (flag) {
                srmPosSupplierInfoDAO_HI.saveEntity(findSupplierInfo);
                supplierId = findSupplierInfo.getSupplierId();
                jsonData.put("supplier", findSupplierInfo);
            } else {
                srmPosSupplierInfoDAO_HI.saveEntity(supplierInfo);
                supplierId = supplierInfo.getSupplierId();
                jsonData.put("supplier", supplierInfo);
            }
            iSrmPosSupplierCredentials.saveSrmPosSupplierCredentialsInfo(credentialsList, userId, supplierId);
            iSrmPosSupplierCertificate.saveSrmPosSupplierCertificateInfo(certificateList, userId, supplierId);
            iSrmPosSupplierCategories.saveSrmPosSupplierCategoriesInfo(categoriesList, userId, supplierId);
            iSrmPosSupplierBank.saveSrmPosSupplierBankInfo(supplierBankList, userId, supplierId);
            iSrmPosSupplierContacts.saveSrmPosSupplierContactsInfo(supplierContactsList, userId, supplierId);
            iSrmPosSupplierAddresses.saveSupplierAddressHead(supplierAddressesList, userId, supplierId);

            jsonData.put("supplierId", supplierId);
            jsonData.put("credentialsList", credentialsList);
            jsonData.put("certificateList", certificateList);
            jsonData.put("categoriesList", categoriesList);
            jsonData.put("supplierBankList", supplierBankList);
            jsonData.put("supplierContactsList", supplierContactsList);
            jsonData.put("supplierAddressesList", supplierAddressesList);
            return SToolUtils.convertResultJSONObj("S", "保存成功！", 1, jsonData);
        } else if ("submit".equals(action)) {  //提交按钮
            SrmPosSupplierInfoEntity_HI checkSubmitStatus = srmPosSupplierInfoDAO_HI.getById(findSupplierInfo.getSupplierId());
            if ("DRAFT".equals(checkSubmitStatus.getSupplierStatus()) || "REJECTED".equals(checkSubmitStatus.getSupplierStatus())) {
                String supplierName = "";
                if (flag) {
                    findSupplierInfo.setSupplierStatus("SUBMITTED");  //待审批状态
                    findSupplierInfo.setCreatedBy(userId);
                    srmPosSupplierInfoDAO_HI.saveEntity(findSupplierInfo);
                    supplierId = findSupplierInfo.getSupplierId();
                    supplierName = findSupplierInfo.getSupplierName();
                    jsonData.put("supplier", findSupplierInfo);
                } else {
                    supplierInfo.setSupplierStatus("SUBMITTED");  //待审批状态
                    findSupplierInfo.setCreatedBy(userId);
                    srmPosSupplierInfoDAO_HI.saveEntity(supplierInfo);
                    supplierId = supplierInfo.getSupplierId();
                    supplierName = supplierInfo.getSupplierName();
                    jsonData.put("supplier", supplierInfo);
                }
                iSrmPosSupplierCredentials.saveSrmPosSupplierCredentialsInfo(credentialsList, userId, supplierId);
                iSrmPosSupplierCertificate.saveSrmPosSupplierCertificateInfo(certificateList, userId, supplierId);
                iSrmPosSupplierCategories.saveSrmPosSupplierCategoriesInfo(categoriesList, userId, supplierId);
                iSrmPosSupplierBank.saveSrmPosSupplierBankInfo(supplierBankList, userId, supplierId);
                iSrmPosSupplierContacts.saveSrmPosSupplierContactsInfo(supplierContactsList, userId, supplierId);
                iSrmPosSupplierAddresses.saveSupplierAddressHead(supplierAddressesList, userId, supplierId);

                jsonData.put("supplierId", supplierId);
                jsonData.put("credentialsList", credentialsList);
                jsonData.put("certificateList", certificateList);
                jsonData.put("categoriesList", categoriesList);
                jsonData.put("supplierBankList", supplierBankList);
                jsonData.put("supplierContactsList", supplierContactsList);
                jsonData.put("supplierAddressesList", supplierAddressesList);


                List<SaafUserEmployeesEntity_HI_RO> userList=findUserToEmail(userId,jsonData.getJSONObject("supplier").getString("region"),jsonData.getJSONObject("supplier").getString("supplierType"));
                //发邮件
                String title = "供应商注册通知";
                String content = "<p>您好！<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp您有新的供应商"+supplierName+"注册档案等待处理，请及时处理，谢谢！</p>";
                sendEmailToRes(userList, title, content);
                //插入系统通知
                if(null != userList && userList.size()>0){
                    for(int i=0;i<userList.size();i++){
                        SaafUserEmployeesEntity_HI_RO usersEntity = userList.get(i);
                        iSrmBaseNotifications.insertSrmBaseNotifications("公司档案维护", "您好！有新的供应商：" + supplierName + "注册档案等待处理，请及时处理，谢谢！"
                                , usersEntity.getUserId(), "srm_pos_supplier_info", supplierId, "supplierId", "home.supplierInfoForSelf", userId);
                    }
                }

               /* //插入系统通知，通知开发专员
                List<SaafResponsibilitysEntity_HI> responsibilityList = saafResponsibilitysDAO_HI.findByProperty("responsibilityName", "开发专员");
                if (null != responsibilityList && responsibilityList.size() > 0) {
                    SaafResponsibilitysEntity_HI resp = responsibilityList.get(0);
                    iSrmBaseNotifications.insertSrmBaseNotifications("公司档案维护", "您好！有新的供应商：" + supplierName + "，注册成功，请审核！"
                            , resp.getResponsibilityId(), "srm_pos_supplier_info", supplierId, "supplierId", "home.supplierInfoForSelf", userId);
                }*/
                return SToolUtils.convertResultJSONObj("S", "提交成功！", 1, jsonData);
            } else {
                return SToolUtils.convertResultJSONObj("E", "提交失败，该单据状态已变更，请刷新页面！", 1, jsonData);
            }
        } else if ("withdraw".equals(action)) {  //撤回按钮
            SrmPosSupplierInfoEntity_HI checkWithdrawStatus = srmPosSupplierInfoDAO_HI.getById(findSupplierInfo.getSupplierId());
            if ("SUBMITTED".equals(checkWithdrawStatus.getSupplierStatus())) {
                if (flag) {
                    checkWithdrawStatus.setSupplierStatus("DRAFT");  //拟定状态
                    checkWithdrawStatus.setOperatorUserId(userId);
                    srmPosSupplierInfoDAO_HI.saveEntity(checkWithdrawStatus);
                    supplierId = checkWithdrawStatus.getSupplierId();
                    jsonData.put("supplier", checkWithdrawStatus);
                } else {
                    checkWithdrawStatus.setSupplierStatus("DRAFT");  //拟定状态
                    checkWithdrawStatus.setOperatorUserId(userId);
                    srmPosSupplierInfoDAO_HI.saveEntity(checkWithdrawStatus);
                    supplierId = checkWithdrawStatus.getSupplierId();
                    jsonData.put("supplier", checkWithdrawStatus);
                }
                jsonData.put("supplierId", supplierId);
                return SToolUtils.convertResultJSONObj("S", "撤回成功！", 1, jsonData);
            } else {
                return SToolUtils.convertResultJSONObj("E", "撤回失败，该单据状态已变更，请刷新页面！", 1, jsonData);
            }
        } else if ("saveInside".equals(action)) {  //保存按钮（内部创建供应商）
            if (flag) {
                srmPosSupplierInfoDAO_HI.saveEntity(findSupplierInfo);
                supplierId = findSupplierInfo.getSupplierId();
                jsonData.put("supplier", findSupplierInfo);
            } else {
                supplierInfo.setSourceCode("CREATE");  //内部创建标识
                srmPosSupplierInfoDAO_HI.saveEntity(supplierInfo);
                supplierId = supplierInfo.getSupplierId();
                jsonData.put("supplier", supplierInfo);
            }
            temp = iSrmPosSupplierCredentials.saveSrmPosSupplierCredentialsInfo(credentialsList, userId, supplierId);
            temp = iSrmPosSupplierCertificate.saveSrmPosSupplierCertificateInfo(certificateList, userId, supplierId);
            temp = iSrmPosSupplierCategories.saveSrmPosSupplierCategoriesInfo(categoriesList, userId, supplierId);
            temp = iSrmPosSupplierBank.saveSrmPosSupplierBankInfo(supplierBankList, userId, supplierId);
            temp = iSrmPosSupplierContacts.saveSrmPosSupplierContactsInfo(supplierContactsList, userId, supplierId);
            temp = iSrmPosSupplierAddresses.saveSupplierAddressHead(supplierAddressesList, userId, supplierId);
            if (temp) {
                jsonData.put("supplierId", supplierId);
                jsonData.put("credentialsList", credentialsList);
                jsonData.put("certificateList", certificateList);
                jsonData.put("categoriesList", categoriesList);
                jsonData.put("supplierBankList", supplierBankList);
                jsonData.put("supplierContactsList", supplierContactsList);
                jsonData.put("supplierAddressesList", supplierAddressesList);
                return SToolUtils.convertResultJSONObj("S", "保存成功！", 1, jsonData);
            } else {
                return SToolUtils.convertResultJSONObj("E", "保存失败！", 0, null);
            }

        } else if ("submitInside".equals(action)) {  //提交按钮（内部创建供应商）
            if (flag) {
                findSupplierInfo.setAbleEditFlag("N");
                findSupplierInfo.setSupplierStatus("APPROVED");  //已批准状态
                srmPosSupplierInfoDAO_HI.saveEntity(findSupplierInfo);
                supplierId = findSupplierInfo.getSupplierId();
                jsonData.put("supplier", findSupplierInfo);
            } else {
                supplierInfo.setSourceCode("CREATE");  //内部创建标识
                supplierInfo.setAbleEditFlag("N");
                supplierInfo.setSupplierStatus("APPROVED");  //已批准状态
                srmPosSupplierInfoDAO_HI.saveEntity(supplierInfo);
                supplierId = supplierInfo.getSupplierId();
                jsonData.put("supplier", supplierInfo);
            }
            temp = iSrmPosSupplierCredentials.saveSrmPosSupplierCredentialsInfo(credentialsList, userId, supplierId);
            temp = iSrmPosSupplierCertificate.saveSrmPosSupplierCertificateInfo(certificateList, userId, supplierId);
            temp = iSrmPosSupplierCategories.saveSrmPosSupplierCategoriesInfo(categoriesList, userId, supplierId);
            temp = iSrmPosSupplierBank.saveSrmPosSupplierBankInfo(supplierBankList, userId, supplierId);
            temp = iSrmPosSupplierContacts.saveSrmPosSupplierContactsInfo(supplierContactsList, userId, supplierId);
            temp = iSrmPosSupplierAddresses.saveSupplierAddressHead(supplierAddressesList, userId, supplierId);
            temp = iSaafUsers.saveSaafUser(saafUser, userId, supplierId);
            if (temp) {
                jsonData.put("supplierId", supplierId);
                jsonData.put("saafUser", saafUser);
                jsonData.put("credentialsList", credentialsList);
                jsonData.put("certificateList", certificateList);
                jsonData.put("categoriesList", categoriesList);
                jsonData.put("supplierBankList", supplierBankList);
                jsonData.put("supplierContactsList", supplierContactsList);
                jsonData.put("supplierAddressesList", supplierAddressesList);
                return SToolUtils.convertResultJSONObj("S", "提交成功！", 1, jsonData);
            } else {
                return SToolUtils.convertResultJSONObj("E", "提交失败！", 0, null);
            }
        }
        return SToolUtils.convertResultJSONObj("E", "没有操作！", 0, jsonData);
    }

    public List<SaafUserEmployeesEntity_HI_RO> findUserToEmail(Integer userId,String region,String supplierType) throws Exception  {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        JSONObject json=new JSONObject();
        json.put("categoryCode",supplierType);
        List<SrmBaseUserCategoriesEntity_HI_RO> userCategoriesList=srmBaseUserCategories.findUserCategories(json);
        List<String>  userIdList1=new ArrayList();
        List<String>  userIdList2=new ArrayList();
        for(SrmBaseUserCategoriesEntity_HI_RO ro:userCategoriesList){
            userIdList1.add(String.valueOf(ro.getUserId()));
        }
        //区域
        List<SaafUserInstEntity_HI_RO> regionList = iSaafUsersInst.findInstRegionUsers(region);
        for(SaafUserInstEntity_HI_RO ro:regionList){
            //userIdList2.add(ro.getUserId());
            userIdList2.add(String.valueOf(ro.getUserId()));
        }
        //取交集
        List<String> intersection = userIdList1.stream().filter(item -> userIdList2.contains(item)).collect(toList());
        String userList= String.valueOf(intersection.stream().distinct().collect(Collectors.joining("','")));
        queryString.append(SaafUserEmployeesEntity_HI_RO.QUERY_USERLIST_SQL);
        queryString.append(" and su.user_id in ('" +userList+"')");
        //区分职责
        queryString.append(" and su.user_id in (SELECT t.user_id\n" +
                "  FROM Saaf_User_Resp t\n" +
                " WHERE t.Responsibility_Id IN (127\n" +
                "                              ,129\n" +
                "                              ,130\n" +
                "                              ,128))");
        List<SaafUserEmployeesEntity_HI_RO> user = saafUserEmployeesDAO_HI_RO.findList(queryString.toString());
        return user;
    }

    /**
     * 查询供应商信息
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
    public Pagination<SrmPosSupplierInfoEntity_HI_RO> findSupplierInfo(JSONObject jsonParams, Integer pageIndex,
                                                                       Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer sb = new StringBuffer();
        StringBuffer queryParam = new StringBuffer();
        sb.append(SrmPosSupplierInfoEntity_HI_RO.QUERY_SUPPLIER_INFO_LIST);
//        String categoryName = jsonParams.getString("categoryName");
//        //验证字符串是否含有SQL关键字及字符，有则返回NULL
//        if (SrmUtils.isContainSQL(categoryName)) {
//            return null;
//        }
//        if (categoryName != null && !"".equals(categoryName.trim()) && !"null".equals(categoryName)) {
//            categoryName = categoryName.replaceAll(",", "','");
//            sb.append(" AND t.supplier_id in (select B.supplier_id\n" +
//                    "  from srm_pos_supplier_categories B\n" +
//                    "  left join srm_base_categories bc on bc.category_id = B.category_id\n" +
//                    " WHERE bc.category_name IN ('" + categoryName + "'))");
//        }
//        String searchStaFlag = String.valueOf(jsonParams.get("searchStaFlag"));
//        if (!"".equals(searchStaFlag.trim()) && searchStaFlag != null && "Y".equals(searchStaFlag)) {
//            sb.append(" AND t.supplier_Status in ('APPROVED','EFFETIVE','INTRODUCING','QUIT')");
//        }
//        //注册审核-供应商状态标识
//        String regSearchStaFlag = String.valueOf(jsonParams.getString("regSearchStaFlag"));
//        if (!"".equals(regSearchStaFlag.trim()) && regSearchStaFlag != null && !"null".equals(regSearchStaFlag) && "R".equals(regSearchStaFlag)) {
//            sb.append(" AND t.supplier_Status in ('DRAFT','SUBMITTED','REJECTED','APPROVED')");
//            sb.append(" AND t.source_code in ('REGISTER')");//注册标识
//        }
        if ("0".equals(jsonParams.getString("supplierId"))) {
            jsonParams.put("supplier_id", jsonParams.getIntValue("varSupplierId"));
        } else {
            SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplierId", queryParam, queryParamMap, "=");
        }
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplier_id", queryParam, queryParamMap, "=");//如果是供应商查询
        SaafToolUtils.parperParam(jsonParams, "t.supplier_name", "supplierName", queryParam, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "t.supplier_number", "supplierNumber", queryParam, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "t.supplier_type", "supplierType", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.supplier_status", "supplierStatus", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spsc.license_num", "licenseNum", queryParam, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "spsc.tissue_code", "tissueCode", queryParam, queryParamMap, "like");
        if (jsonParams != null && null != jsonParams.getString("supplierStatusV") && !"".equals(jsonParams.getString("supplierStatusV"))) {
            String supplierStatusV = jsonParams.getString("supplierStatusV");  //内部供应商查询范围为拟定和已批准状态
            String[] supplierStatusVList = supplierStatusV.split(",");  //按照“,”分割
            sb.append(" And (");
            int i = 0;
            for (String status : supplierStatusVList) {
                if (i != 0) {
                    sb.append(" or ");
                }
                // 改参数方式处理　hanguoqiang 2020/06/23
                sb.append("t.supplier_Status = :supplierStatusParam").append(i);
                queryParamMap.put("supplierStatusParam" + i, status);
                i++;
            }
            sb.append(")");
        }
        sb.append(queryParam);

        //未交易的供应商数量报表
        String timeFrame = "";
        if (null != jsonParams.getString("timeFrame") && !"".equals(jsonParams.getString("timeFrame"))) {
            timeFrame = jsonParams.getString("timeFrame");//交易周期
            //验证字符串是否含有SQL关键字及字符，有则返回NULL
            if (SrmUtils.isContainSQL(timeFrame)) {
                return null;
            }
            if ("More 2 YEAR".equals(timeFrame)) {//2年以上未交易的供应商
                sb.append("AND (EXISTS(\n"
                        + "SELECT sph.supplier_id FROM srm_po_headers sph WHERE sph.supplier_id=t.supplier_id\n"
                        + "AND sph.po_doc_type='ORDER'\n"
                        + "AND DATE(sph.creation_date)<DATE_SUB(CURDATE(),INTERVAL 2 YEAR))\n"
                        + "OR NOT EXISTS (SELECT sph.supplier_id FROM srm_po_headers sph WHERE sph.supplier_id=t.supplier_id\n"
                        + "AND sph.po_doc_type='ORDER'))\n"
                        + "AND t.creation_date<=DATE_SUB(CURDATE(),INTERVAL 2 YEAR) ");
            } else {
                sb.append(" AND NOT EXISTS(\n"
                        + "SELECT sph.supplier_id FROM srm_po_headers sph WHERE sph.supplier_id=t.supplier_id\n"
                        + "AND sph.po_doc_type='ORDER'\n"
                        + "AND DATE_SUB(CURDATE(),INTERVAL " + timeFrame + ")<=DATE(sph.creation_date)) "
                        + "AND t.creation_date<=DATE_SUB(CURDATE(),INTERVAL " + timeFrame + ")");
            }
        }
        //供应商引入退出报表
        if (null != jsonParams.getString("supplierCurrentYear") && !"".equals(jsonParams.getString("supplierCurrentYear"))) {//三年的供应商引入退出
            Date supplierCurrentYearDate = DateUtil.str2Date(jsonParams.getString("supplierCurrentYear"), "yyyy");
            String supplierCurrentYear = DateUtil.date2Str(supplierCurrentYearDate, "yyyy-MM-dd");
            if (null != jsonParams.getString("supplierIntroduceName") && !"".equals(jsonParams.getString("supplierIntroduceName"))) {//供应商引入
                sb.append(" AND EXISTS(SELECT spqi.supplier_id\n"
                        + "FROM(SELECT tt.qualification_id,tt.supplier_id,tt.approve_date,\n"
                        + "get_qualification_success_lastFinishDate_f (tt.qualification_id,tt.supplier_id,tt.scene_type) AS lastFinishDate\n"
                        + "FROM srm_pos_qualification_info tt\n"
                        + "WHERE get_qualification_success_lastFinishDate_f (tt.qualification_id,tt.supplier_id,tt.scene_type) IS NOT NULL) spqi\n"
                        + "WHERE t.supplier_id= spqi.supplier_id\n"
                        + "AND YEAR(spqi.lastFinishDate)=YEAR('" + supplierCurrentYear + "') \n"
                        + "AND NOT EXISTS(\n"
                        + "SELECT spqi2.supplier_id\n"
                        + "FROM(SELECT tt.qualification_id,tt.supplier_id,tt.approve_date,\n"
                        + "get_qualification_success_lastFinishDate_f (tt.qualification_id,tt.supplier_id,tt.scene_type) AS lastFinishDate\n"
                        + "FROM srm_pos_qualification_info tt\n"
                        + "WHERE get_qualification_success_lastFinishDate_f (tt.qualification_id,tt.supplier_id,tt.scene_type) IS NOT NULL) spqi2\n"
                        + "WHERE spqi2.supplier_id=spqi.supplier_id\n"
                        + "AND YEAR(spqi2.lastFinishDate)<YEAR('" + supplierCurrentYear + "'))\n"
                        + "GROUP BY spqi.supplier_id) ");
            }
        }
        if (null != jsonParams.getString("supplierYearMonth") && !"".equals(jsonParams.getString("supplierYearMonth"))) {//24个月的供应商的引入
            Date supplierYearMonthDate = DateUtil.str2Date(jsonParams.getString("supplierYearMonth"), "yyyy-MM");
            String supplierYearMonth = DateUtil.date2Str(supplierYearMonthDate, "yyyy-MM-dd");
            //年份的供应商引入，例如查询2018年8月到2018年1月份的数据
            if (null != jsonParams.getInteger("supplierYearCount") && !"".equals(jsonParams.getInteger("supplierYearCount"))) {
                Integer supplierYearCount = jsonParams.getInteger("supplierYearCount") - 1;
                sb.append(" AND EXISTS(SELECT spqi.supplier_id\n"
                        + "FROM(SELECT tt.qualification_id,tt.supplier_id,tt.approve_date,\n"
                        + "get_qualification_success_lastFinishDate_f (tt.qualification_id,tt.supplier_id,tt.scene_type) AS lastFinishDate\n"
                        + "FROM srm_pos_qualification_info tt\n"
                        + "WHERE get_qualification_success_lastFinishDate_f (tt.qualification_id,tt.supplier_id,tt.scene_type) IS NOT NULL) spqi\n"
                        + "WHERE t.supplier_id= spqi.supplier_id\n"
                        + "AND YEAR(spqi.lastFinishDate)=YEAR(DATE_SUB('" + supplierYearMonth + "', INTERVAL " + (-supplierYearCount) + " MONTH))\n"
                        + "AND date_format(spqi.lastFinishDate,'%Y-%m')<=date_format(DATE_SUB('" + supplierYearMonth + "', INTERVAL " + (-supplierYearCount) + " MONTH),'%Y-%m')\n"
                        + "AND NOT EXISTS(\n"
                        + "SELECT spqi2.supplier_id\n"
                        + "FROM(SELECT tt.qualification_id,tt.supplier_id,tt.approve_date,\n"
                        + "get_qualification_success_lastFinishDate_f (tt.qualification_id,tt.supplier_id,tt.scene_type) AS lastFinishDate\n"
                        + "FROM srm_pos_qualification_info tt\n"
                        + "WHERE get_qualification_success_lastFinishDate_f (tt.qualification_id,tt.supplier_id,tt.scene_type) IS NOT NULL) spqi2\n"
                        + "WHERE spqi2.supplier_id=spqi.supplier_id\n"
                        + "AND date_format(spqi2.lastFinishDate,'%Y-%m')<date_format('" + supplierYearMonth + "','%Y-%m'))\n"
                        + "GROUP BY spqi.supplier_id) ");
            } else {//月份的供应商引入查询
                sb.append(" AND EXISTS(SELECT spqi.supplier_id\n"
                        + "FROM(SELECT tt.qualification_id,tt.supplier_id,tt.approve_date,\n"
                        + "get_qualification_success_lastFinishDate_f (tt.qualification_id,tt.supplier_id,tt.scene_type) AS lastFinishDate\n"
                        + "FROM srm_pos_qualification_info tt\n"
                        + "WHERE get_qualification_success_lastFinishDate_f (tt.qualification_id,tt.supplier_id,tt.scene_type) IS NOT NULL) spqi\n"
                        + "WHERE t.supplier_id= spqi.supplier_id\n"
                        + "AND date_format(spqi.lastFinishDate,'%Y-%m')=date_format('" + supplierYearMonth + "','%Y-%m')\n"
                        + "AND NOT EXISTS(\n"
                        + "SELECT spqi2.supplier_id\n"
                        + "FROM(SELECT tt.qualification_id,tt.supplier_id,tt.approve_date,\n"
                        + "get_qualification_success_lastFinishDate_f (tt.qualification_id,tt.supplier_id,tt.scene_type) AS lastFinishDate\n"
                        + "FROM srm_pos_qualification_info tt\n"
                        + "WHERE get_qualification_success_lastFinishDate_f (tt.qualification_id,tt.supplier_id,tt.scene_type) IS NOT NULL) spqi2\n"
                        + "WHERE spqi2.supplier_id=spqi.supplier_id\n"
                        + "AND date_format(spqi2.lastFinishDate,'%Y-%m')<date_format('" + supplierYearMonth + "','%Y-%m'))\n"
                        + "GROUP BY spqi.supplier_id) ");
            }
        }
        //供应商分级统计报表
        if (null != jsonParams.getString("supplierStatusV") && "EFFETIVE".equals(jsonParams.getString("supplierStatusV")) && null != jsonParams.get("supplierGrade")) {
            if ("null".equals(jsonParams.getString("supplierGrade"))) {
                //查询未分级的供应商
                sb.append(" AND (t.grade_line_id IS NULL OR t.grade_line_id NOT IN (SELECT grade_line_id FROM srm_pos_supplier_grade_lines WHERE 1 = 1 ) ) ");
            } else if ("all".equals(jsonParams.get("supplierGrade"))) {
                //查询所有分级的供应商
                sb.append(" AND (t.grade_line_id = spsgl.grade_line_id OR (t.grade_line_id IS NULL OR t.grade_line_id NOT IN (SELECT grade_line_id FROM srm_pos_supplier_grade_lines WHERE 1 = 1 ))) ");
            } else {
                //查询已分级的供应商
                SaafToolUtils.parperParam(jsonParams, "spsgl.adjust_grade", "supplierGrade", queryParam, queryParamMap, "=");
                sb.append(queryParam);
                sb.append(" AND t.grade_line_id = spsgl.grade_line_id ");
            }
        }
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY t.LAST_UPDATE_DATE DESC");
        Pagination<SrmPosSupplierInfoEntity_HI_RO> result = srmPosSupplierInfoDAO_HI_RO.findPagination
                (sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosSupplierInfoEntity_HI_RO> findSupplierCategory(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosSupplierInfoEntity_HI_RO.QUERY_SUPPLIER_INFO_LIST);
        SaafToolUtils.parperParam(jsonParams, "spsc.supplier_id", "supplier_id", sb, queryParamMap, "=");//如果是供应商查询
        SaafToolUtils.parperParam(jsonParams, "spsc.supplier_id", "supplierId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spsc.status", "actStatus", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spsc.status", "inActStatus", sb, queryParamMap, "!=");
        //合作品类供应商过滤
        String status1 = jsonParams.getString("status1");
        String status2 = jsonParams.getString("status2");
        if (null != jsonParams.getString("status1") && null != jsonParams.getString("status2")) {
            //验证字符串是否含有SQL关键字及字符，有则返回NULL
            if (SrmUtils.isContainSQL(status1) || SrmUtils.isContainSQL(status2)) {
                return null;
            }
            sb.append(" AND (spsc.status = '" + status1 + "' OR spsc.status = '" + status2 + "')");
        }
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosSupplierInfoEntity_HI_RO> result = srmPosSupplierInfoDAO_HI_RO.findPagination
                (sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * 供应商重复性校验（公司名称，营业执照号）-供应商档案编辑
     *
     * @param checkMap
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public String findCheckForSupplier(Map<String, Object> checkMap) {
        String result = "Y";
        if (("".equals(checkMap.get("supplierId")) || checkMap.get("supplierId") == null)) {
            List<SrmPosSupplierInfoEntity_HI> supplierList = srmPosSupplierInfoDAO_HI.findByProperty("supplierName", checkMap.get("supplierName"));
            if (supplierList.size() > 0) {  //新创建的公司名称已存在
                result = "公司名称已存在，无法保存！";
            }
            List<SaafUsersEntity_HI> userList = saafUsersDAO_HI.findByProperty("userName", checkMap.get("userName"));
            if (userList.size() > 0) {
                result = "登陆账号已存在，无法保存！";
            }
        } else {
            StringBuffer hql = new StringBuffer();
            hql.append("SELECT A FROM SrmPosSupplierInfoEntity_HI A WHERE A.supplierId <>:supplierId AND A.supplierName =:supplierName AND :userName = :userName ");
            List<SrmPosSupplierInfoEntity_HI> supplierList = srmPosSupplierInfoDAO_HI.findList(hql, checkMap);
            if (supplierList.size() > 0) {  //新创建的公司名称已存在
                result = "公司名称已存在，无法保存！";
            }
            hql = new StringBuffer();
            hql.append("SELECT A FROM SaafUsersEntity_HI A WHERE A.supplierId <>:supplierId AND A.userName =:userName AND :supplierName = :supplierName");
            List<SaafUsersEntity_HI> userList = saafUsersDAO_HI.findList(hql, checkMap);
            if (userList.size() > 0) {  //新创建的公司名称已存在
                result = "登陆账号已存在，无法保存！";
            }
        }
        return result;
    }

    /**
     * 供应商校验（头行表必填信息校验项)
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public String findCheckForRequired(JSONObject jsonParams) {
        String result = "Y";
        if (null == jsonParams.get("supplierName") || null == jsonParams.get("userName")) {
            result = "公司名称或账户名称为空，无法保存";
            return result;
        } else if (null == jsonParams.get("businessScope") || null == jsonParams.get("bankPermitNumber") || null == jsonParams.get("establishmentDate") || null == jsonParams.get("licenseNum")) {
            result = "公司资质信息有必填项为空，无法保存";
            return result;
        }
        if (null == jsonParams.get("muchInOne") || "N".equals(jsonParams.get("muchInOne")) || "null".equals(jsonParams.get("muchInOne"))) {
            if (null == jsonParams.get("taxCode") || null == jsonParams.get("tissueCode") || null == jsonParams.get("tissueIndate")) {
                result = "公司资质信息有必填项为空，无法保存";
                return result;
            }
        }
        if (null == jsonParams.get("longLicenseIndate") || "N".equals(jsonParams.getString("longLicenseIndate"))) {
            if (null == jsonParams.get("licenseIndate") || "".equals(jsonParams.getString("licenseIndate").trim())) {
                result = "营业期限为空，无法保存";
                return result;
            }
        }

        if (null == jsonParams.get("supplierStatus")) {
            result = "供应商状态为空，无法保存";
            return result;
        }
        if (null == jsonParams.get("supplierType")) {
            result = "供应商类别为空，无法保存";
            return result;
        }
        if (null != jsonParams.getJSONArray("lineDataList1")) {
            JSONArray dataList = jsonParams.getJSONArray("lineDataList1");
            for (int i = 0; i < dataList.size(); i++) {
                if (null == dataList.getJSONObject(i).get("categoryId") || "".equals(dataList.getJSONObject(i).getString("categoryId"))) {
                    result = "产品类别信息有必填项为空，无法保存";
                }
            }
        }
        if (null != jsonParams.getJSONArray("lineDataList2")) {
            JSONArray dataList = jsonParams.getJSONArray("lineDataList2");
            for (int i = 0; i < dataList.size(); i++) {
                if (null == dataList.getJSONObject(i).get("certificateName") || "".equals(dataList.getJSONObject(i).getString("certificateName"))) {
                    result = "认证证书信息有必填项为空，无法保存";
                } else if (null == dataList.getJSONObject(i).get("certificateNumber") || "".equals(dataList.getJSONObject(i).getString("certificateNumber"))) {
                    result = "认证证书信息有必填项为空，无法保存";
                } else if (null == dataList.getJSONObject(i).get("endDate") || "".equals(dataList.getJSONObject(i).getString("endDate"))) {
                    result = "认证证书信息有必填项为空，无法保存";
                }
            }
        }
        if (null != jsonParams.getJSONArray("lineDataList3")) {
            JSONArray dataList = jsonParams.getJSONArray("lineDataList3");
            for (int i = 0; i < dataList.size(); i++) {
                if (null == dataList.getJSONObject(i).get("bankUserName") || "".equals(dataList.getJSONObject(i).getString("bankUserName"))) {
                    result = "银行信息有必填项为空，无法保存";
                } else if (null == dataList.getJSONObject(i).get("bankAccountNumber") || "".equals(dataList.getJSONObject(i).getString("bankAccountNumber"))) {
                    result = "银行信息有必填项为空，无法保存";
                } else if (null == dataList.getJSONObject(i).get("bankName") || "".equals(dataList.getJSONObject(i).getString("bankName"))) {
                    result = "银行信息有必填项为空，无法保存";
                } else if (null == dataList.getJSONObject(i).get("branchName") || "".equals(dataList.getJSONObject(i).getString("branchName"))) {
                    result = "银行信息有必填项为空，无法保存";
                } else if (null == dataList.getJSONObject(i).get("bankCurrency") || "".equals(dataList.getJSONObject(i).getString("bankCurrency"))) {
                    result = "银行信息有必填项为空，无法保存";
                }
            }
        }
        if (null != jsonParams.getJSONArray("lineDataList4")) {
            JSONArray dataList = jsonParams.getJSONArray("lineDataList4");
            for (int i = 0; i < dataList.size(); i++) {
                if (null == dataList.getJSONObject(i).get("contactName") || "".equals(dataList.getJSONObject(i).getString("contactName"))) {
                    result = "联系人信息有必填项为空，无法保存";
                } else if (null == dataList.getJSONObject(i).get("mobilePhone") || "".equals(dataList.getJSONObject(i).getString("mobilePhone"))) {
                    result = "联系人信息有必填项为空，无法保存";
                } else if (null == dataList.getJSONObject(i).get("emailAddress") || "".equals(dataList.getJSONObject(i).getString("emailAddress"))) {
                    result = "联系人邮件信息为空或者邮件格式不符合规范，无法保存";
                }
            }
        }
        return result;
    }

    /**
     * 地址地点校验（头行表必填信息校验项)
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public String findCheckAddressRequired(JSONObject jsonParams) {
        String result = "Y";
        JSONArray dataList11 = jsonParams.getJSONArray("lineDataList6");
        if (null != jsonParams.getJSONArray("lineDataList5")) {
            JSONArray dataList = jsonParams.getJSONArray("lineDataList5");
            for (int i = 0; i < dataList.size(); i++) {
                if (null == dataList.getJSONObject(i).get("addressName") || "".equals(dataList.getJSONObject(i).getString("addressName"))) {
                    result = "地址信息有必填项为空，无法保存";
                } else if (null == dataList.getJSONObject(i).get("country") || "".equals(dataList.getJSONObject(i).getString("country"))) {
                    result = "地址信息有必填项为空，无法保存";
                } else if (null == dataList.getJSONObject(i).get("detailAddress") || "".equals(dataList.getJSONObject(i).getString("detailAddress"))) {
                    result = "地址信息有必填项为空，无法保存";
                }
            }
        }
        if (null != jsonParams.getJSONArray("lineDataList6")) {
            JSONArray dataList = jsonParams.getJSONArray("lineDataList6");
            for (int i = 0; i < dataList.size(); i++) {
                if (null == dataList.getJSONObject(i).get("siteName") || "".equals(dataList.getJSONObject(i).getString("siteName"))) {
                    result = "地点信息有必填项为空，无法保存";
                } else if (null == dataList.getJSONObject(i).get("instName") || "".equals(dataList.getJSONObject(i).getString("instName"))) {
                    result = "地点信息有必填项为空，无法保存";
                }
            }
        }
        return result;
    }

    /**
     * 营业执照号重复验证
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public String findCheckForRepeat(JSONObject jsonParams) {
        String result = "Y";
        StringBuffer hql = null;
        int supplierId = 0;
        if (null != jsonParams.get("supplierId")) {
            supplierId = jsonParams.getInteger("supplierId");
        }
        //检查营业执照号是否存在
        Map<String, Object> credentialMap = new HashMap<String, Object>();
        if (supplierId == 0) {
            List<SrmPosSupplierCredentialsEntity_HI> credentialList = srmPosSupplierCredentialsDAO_HI.findByProperty("licenseNum", jsonParams.getString("licenseNum"));
            if (credentialList.size() > 0) {
                result = "营业执照号已存在，无法保存！";
            }
        } else {
            credentialMap.put("supplierId", jsonParams.getInteger("supplierId"));
            credentialMap.put("licenseNum", jsonParams.getString("licenseNum"));
            hql = new StringBuffer();
            hql.append("SELECT A FROM SrmPosSupplierCredentialsEntity_HI A WHERE A.supplierId <> :supplierId AND A.licenseNum = :licenseNum ");
            List<SrmPosSupplierCredentialsEntity_HI> credentialList = srmPosSupplierCredentialsDAO_HI.findList(hql, credentialMap);
            if (credentialList.size() > 0) {
                result = "营业执照号已存在，无法保存！";
            }
        }
        return result;
    }

    /**
     * Description：供应商信息保存
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * supplierId  供应商ID  NUMBER  Y
     * supplierNumber  供应商编码  VARCHAR2  N
     * supplierName  供应商名称  VARCHAR2  Y
     * supplierShortName  供应商简称  VARCHAR2  N
     * supplierType  供应商类型(POS_SUPPLIER_TYPE)  VARCHAR2  N
     * supplierClassify  供应商类别(POS_SUPPLIER_CLASSIFY)  VARCHAR2  N
     * supplierIndustry  供应商行业(POS_SUPPLIER_INDUSTRY)  VARCHAR2  N
     * supplierStatus  供应商状态(POS_SUPPLIER_STATUS)  VARCHAR2  N
     * homeUrl  企业网址  VARCHAR2  N
     * companyPhone  公司电话  VARCHAR2  N
     * companyFax  公司传真  VARCHAR2  N
     * relatedSupplierId  关联供应商ID   NUMBER  N
     * parentSupplierId  父供应商ID   NUMBER  N
     * staffNum  员工数量  NUMBER  N
     * floorArea  占地面积  VARCHAR2  N
     * companyDescription  公司简介  CLOB  N
     * purchaseFlag  可采购标识  VARCHAR2  N
     * paymentFlag  可付款标识  VARCHAR2  N
     * finClassify  (废弃)财务分类(POS_FIN_CLASSIFY)  VARCHAR2  N
     * settleAcctType  (废弃)结算方式(POS_SETTLE_ACCT_TYPE)  VARCHAR2  N
     * acctCheckStaff  (废弃)对账人员编码(POS_ACCT_CHECK_CREW)  VARCHAR2  N
     * acctCheckType  (废弃)对账方式(POS_ACCT_CHECK_TYPE)  VARCHAR2  N
     * passU9Flag  (废弃)传U9标识  VARCHAR2  N
     * supplierFileId  公司简介附件  NUMBER  N
     * posTax  (废弃)税组合(POS_TAX_LIST)  VARCHAR2  N
     * posAcctCondition  (废弃)立账条件(POS_ACCOUNT_CONDITION)  VARCHAR2  N
     * ableCheckOrderFlag  (废弃)允许确认采购标识  VARCHAR2  N
     * ableEditFlag  (废弃)是否可修改  VARCHAR2  N
     * address  (废弃)供应商地址  VARCHAR2  N
     * srmDelivery  (废弃)允许平台送货  VARCHAR2  N
     * logisticsSupplier  (废弃)是否为物流供应商  VARCHAR2  N
     * blacklistFlag  黑名单供应商(Y/N)  VARCHAR2  N
     * sourceCode  供应商来源(REGISTER:注册，CREATE:创建，其他类型为其他系统来源)  VARCHAR2  N
     * sourceId  供应商来源ID（当供应商数据来源于其他系统时才有）  VARCHAR2  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核通过时间  DATE  N
     * approvalComments  审核意见  VARCHAR2  N
     * isManufacturer  制造商(Y/N)  VARCHAR2  N
     * isAgent  代理商(Y/N)  VARCHAR2  N
     * isAvl  AVL供应商(Y/N)  VARCHAR2  N
     * isPurchaser  代购商(Y/N)  VARCHAR2  N
     * isTraders  贸易商(Y/N)  VARCHAR2  N
     * feedbackFileId  供应商反馈信息附件ID  NUMBER  N
     * managementScope  经营范围  VARCHAR2  N
     * registeredCapital  注册资金  VARCHAR2  N
     * supplierEnterpriseType  企业类型  VARCHAR2  N
     * outputSales  年产值/年销售额  VARCHAR2  N
     * paymentTerm  付款条件  VARCHAR2  N
     * paymentMethod  付款方式  VARCHAR2  N
     * taxonomy  税分类  VARCHAR2  N
     * interfaceStart  接口开始时间  DATE  N
     * returnType  回货方式  VARCHAR2  N
     * isOther  其他供应商(Y/N)  VARCHAR2  N
     * monthly  月结  NUMBER  N
     * parentCompany  母公司  VARCHAR2  N
     * selfOutletsNumber  自营网点数量  NUMBER  N
     * ownVehiclesNumber  自有车辆数量  NUMBER  N
     * callingVehiclesNumber  挂靠车辆数量  NUMBER  N
     * isAdvancedCertification  高级认证企业(Y/N)  VARCHAR2  N
     * isGeneralCertification  一般认证企业(Y/N)  VARCHAR2  N
     * isGeneralCredit  一般信用企业(Y/N)  VARCHAR2  N
     * isUntrustworthy  失信企业(Y/N)  VARCHAR2  N
     * managersNumber  管理人员数量  NUMBER  N
     * techniciansNumber  技术人员数量  NUMBER  N
     * workersNumber  工人数量  NUMBER  N
     * aeoFileId  AEO认证附件  NUMBER  N
     * supplierEbsNumber  供应商EBS编号  VARCHAR2  N
     * temporaryFlag  临时供应商标记(Y/N)  VARCHAR2  N
     * requestId  报文请求ID  VARCHAR2  N
     * companyDescription  供应商简介  CLOB  N
     * companyRegisteredAddress  公司注册地址  VARCHAR2  N
     * registeredCapital  注册资金(万)  VARCHAR2  N
     * independentLegalPersonFlag  是否独立法人  VARCHAR2  N
     * valueAddedTaxInvoiceFlag  能否开6个税点的增值税专用发票  VARCHAR2  N
     * valueAddedTaxInvoiceDesc  能否开6个税点的增值税专用发票-说明  VARCHAR2  N
     * associatedCompany  关联公司  VARCHAR2  N
     * region  意向服务区域  VARCHAR2  N
     * supplierEbsNumber  供应商EBS编号  VARCHAR2  N
     * requestId  报文请求ID  VARCHAR2  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierNumber  供应商编码  VARCHAR2  N
     * supplierName  供应商名称  VARCHAR2  Y
     * supplierShortName  供应商简称  VARCHAR2  N
     * supplierType  供应商类型(POS_SUPPLIER_TYPE)  VARCHAR2  N
     * supplierClassify  (废弃)供应商分类(POS_SUPPLIER_CLASSIFY)  VARCHAR2  N
     * supplierIndustry  供应商行业(POS_SUPPLIER_INDUSTRY)  VARCHAR2  N
     * supplierStatus  供应商状态(POS_SUPPLIER_STATUS)  VARCHAR2  N
     * homeUrl  公司官网  VARCHAR2  N
     * companyPhone  公司电话  VARCHAR2  N
     * companyFax  公司传真  VARCHAR2  N
     * relatedSupplierId  关联供应商ID   NUMBER  N
     * parentSupplierId  父供应商ID   NUMBER  N
     * staffNum  员工数量  NUMBER  N
     * floorArea  占地面积  VARCHAR2  N
     * purchaseFlag  可采购标识  VARCHAR2  N
     * paymentFlag  可付款标识  VARCHAR2  N
     * finClassify  (废弃)财务分类(POS_FIN_CLASSIFY)  VARCHAR2  N
     * settleAcctType  (废弃)结算方式(POS_SETTLE_ACCT_TYPE)  VARCHAR2  N
     * acctCheckStaff  (废弃)对账人员编码(POS_ACCT_CHECK_CREW)  VARCHAR2  N
     * acctCheckType  (废弃)对账方式(POS_ACCT_CHECK_TYPE)  VARCHAR2  N
     * passU9Flag  (废弃)传U9标识  VARCHAR2  N
     * supplierFileId  公司简介附件  NUMBER  N
     * posTax  (废弃)税组合(POS_TAX_LIST)  VARCHAR2  N
     * posAcctCondition  (废弃)立账条件(POS_ACCOUNT_CONDITION)  VARCHAR2  N
     * ableCheckOrderFlag  (废弃)允许确认采购标识  VARCHAR2  N
     * ableEditFlag  (废弃)是否可修改  VARCHAR2  N
     * address  (废弃)供应商地址  VARCHAR2  N
     * srmDelivery  (废弃)允许平台送货  VARCHAR2  N
     * logisticsSupplier  (废弃)是否为物流供应商  VARCHAR2  N
     * blacklistFlag  黑名单供应商(Y/N)  VARCHAR2  N
     * sourceCode  供应商来源(REGISTER:注册，CREATE:创建，其他类型为其他系统来源)  VARCHAR2  N
     * sourceId  供应商来源ID（当供应商数据来源于其他系统时才有）  VARCHAR2  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核通过时间  DATE  N
     * approvalComments  审核意见  VARCHAR2  N
     * gradeLineId  供应商级别行ID，srm_pos_supplier_grade_lines  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    public JSONObject saveSupplierInfo(JSONObject jsonParams) throws Exception {
        JSONObject json = new JSONObject();
        int userId = jsonParams.getInteger("varUserId");
        SrmPosSupplierInfoEntity_HI supplier = null;

        //检查必填
        String requiredResult = "N";
        requiredResult = findCheckForRequired(jsonParams);

        if (!"Y".equals(requiredResult)) {
            return SToolUtils.convertResultJSONObj("E", "" + requiredResult, 0, null);
        }
        Map<String, Object> checkMap = new HashMap<String, Object>();
        if (null != jsonParams.getInteger("supplierId")) {
            checkMap.put("supplierId", jsonParams.getInteger("supplierId"));
        } else {
            checkMap.put("supplierId", null);
        }

        checkMap.put("supplierName", jsonParams.getString("supplierName"));
        checkMap.put("userName", jsonParams.getString("userName"));
        //供应商信息重复校验
        String checkResult = "N";
        checkResult = findCheckForSupplier(checkMap);
        if (!"Y".equals(checkResult)) {
            return SToolUtils.convertResultJSONObj("E", "" + checkResult, 0, null);
        }
        //营业执照重复校验
        String credentialResult = "N";
        credentialResult = findCheckForRepeat(jsonParams);
        if (!"Y".equals(credentialResult)) {
            return SToolUtils.convertResultJSONObj("E", "" + credentialResult, 0, null);
        }
        try {
            if (null == jsonParams.getInteger("supplierId")) {
                supplier = new SrmPosSupplierInfoEntity_HI();
                supplier.setSupplierStatus("POTENTIAL"); //设置状态为‘潜在’
                supplier.setAbleEditFlag("Y");   //档案信息可修改
                supplier.setPassU9Flag("N");
                supplier.setCreatedBy(userId);
                supplier.setCreationDate(new Date());
            } else {
                supplier = srmPosSupplierInfoDAO_HI.getById(jsonParams.getInteger("supplierId"));
            }
            supplier.setSupplierName(jsonParams.getString("supplierName"));
            supplier.setSupplierType(jsonParams.getString("supplierType"));
            supplier.setSupplierShortName(jsonParams.getString("supplierShortName"));
            supplier.setCompanyPhone(jsonParams.getString("companyPhone"));
            supplier.setSupplierStatus(jsonParams.getString("supplierStatus"));
            supplier.setStaffNum(jsonParams.getInteger("staffNum"));
            supplier.setSupplierIndustry(jsonParams.getString("supplierIndustry"));
            supplier.setAddress(jsonParams.getString("address"));
            supplier.setCompanyFax(jsonParams.getString("companyFax"));
            supplier.setPurchaseFlag(jsonParams.getString("purchaseFlag"));
            supplier.setPaymentFlag(jsonParams.getString("paymentFlag"));
            supplier.setSrmDelivery(jsonParams.getString("srmDelivery"));
            supplier.setCompanyDescription(jsonParams.getString("companyDescription"));
            supplier.setSupplierFileId(jsonParams.getInteger("supplierFileId"));
            supplier.setFinClassify(jsonParams.getString("finClassify"));
            supplier.setBlacklistFlag(jsonParams.getString("blacklistFlag"));
            supplier.setSettleAcctType(jsonParams.getString("settleAcctType"));
            supplier.setAcctCheckStaff(jsonParams.getString("acctCheckStaff"));
            supplier.setAcctCheckType(jsonParams.getString("acctCheckType"));
            supplier.setPosAcctCondition(jsonParams.getString("posAcctCondition"));
            supplier.setPosTax(jsonParams.getString("posTax"));
            supplier.setAbleCheckOrderFlag(jsonParams.getString("ableCheckOrderFlag"));
            if (supplier.getSupplierClassify() == null) {
                supplier.setSupplierClassify("0");
            }
            if (supplier.getAbleEditFlag() == null) {
                supplier.setAbleEditFlag("Y");
            }
            if (supplier.getVersionNum() == null) {
                supplier.setVersionNum(0);
            }
            //如果是提交，档案信息不可修改
            if ("submit".equals(jsonParams.getString("action"))) {
                supplier.setAbleEditFlag("N");   //档案信息不可修改
            }
            supplier.setCreationDate(new Date());
            supplier.setLastUpdateDate(new Date());
            supplier.setLastUpdatedBy(userId);
            supplier.setOperatorUserId(userId);
            srmPosSupplierInfoDAO_HI.saveEntity(supplier);
            //创建用户
            if (null == jsonParams.getInteger("supplierId")) {
                SaafUsersEntity_HI user = new SaafUsersEntity_HI();
                user.setSupplierId(supplier.getSupplierId());
                user.setUserName(jsonParams.getString("userName"));
                SHA1Util sha1 = new SHA1Util();
                user.setEncryptedPassword(sha1.getEncrypt("111111"));
                user.setOperatorUserId(userId);
                user.setIsadmin("N");
                user.setPlatformCode("EX");
                user.setStartDateActive(new Date());
                user.setCreatedBy(userId);
                user.setCreationDate(new Date());
                user.setLastUpdateDate(new Date());
                user.setLastUpdatedBy(userId);
                saafUsersDAO_HI.save(user);
                //给供应商分配默认的职责
                SaafUserRespEntity_HI resp = new SaafUserRespEntity_HI();
                SaafResponsibilitysEntity_HI sr = null;
                List<SaafResponsibilitysEntity_HI> srList = saafResponsibilitysDAO_HI.findByProperty("responsibilityKey", "SUPPLIER_RESP");
                if (srList.size() > 0) {
                    sr = srList.get(0);
                } else {
                    throw new ServerException("供应商的默认职责不存在，请联系系统管理员！");
                }
                resp.setStartDateActive(new Date());
                resp.setUserId(user.getUserId());
                resp.setResponsibilityId(sr.getResponsibilityId());
                resp.setUserRespName(sr.getResponsibilityName());
                resp.setPlatformCode("EX");
                resp.setCreatedBy(userId);
                resp.setCreationDate(new Date());
                resp.setLastUpdateDate(new Date());
                resp.setLastUpdatedBy(userId);
                resp.setLastUpdateLogin(userId);
                resp.setOperatorUserId(userId);
                saafUserRespDAO_HI.save(resp);
            }
            List<SaafUsersEntity_HI> userList = new ArrayList<SaafUsersEntity_HI>();
            if (null != jsonParams.getInteger("supplierId")) {
                userList = saafUsersDAO_HI.findByProperty("supplierId", jsonParams.getInteger("supplierId"));
                if (userList.size() > 0) {
                    SaafUsersEntity_HI user = new SaafUsersEntity_HI();
                    user = userList.get(0);
                    user.setUserName(jsonParams.getString("userName"));
                    user.setOperatorUserId(userId);
                    user.setCreationDate(new Date());
                    user.setLastUpdateDate(new Date());
                    user.setLastUpdatedBy(userId);
                    saafUsersDAO_HI.save(user);
                }
            }
            //维护供应商资质信息
            SrmPosSupplierCredentialsEntity_HI credential = null;
            if (null == jsonParams.getInteger("credentialsId")) { //新增
                credential = new SrmPosSupplierCredentialsEntity_HI();
                credential.setSupplierId(supplier.getSupplierId());
                credential.setCreatedBy(userId);
                credential.setCreationDate(new Date());
            } else {
                credential = srmPosSupplierCredentialsDAO_HI.getById(jsonParams.getInteger("credentialsId"));
            }
            credential.setBusinessScope(jsonParams.getString("businessScope"));
            credential.setRegisteredAddress(jsonParams.getString("registeredAddress"));
            credential.setEstablishmentDate(jsonParams.getDate("establishmentDate"));
            credential.setLicenseNum(jsonParams.getString("licenseNum"));
            credential.setBankPermitFileId(jsonParams.getInteger("bankPermitFileId"));
            credential.setBankPermitNumber(jsonParams.getString("bankPermitNumber"));
            credential.setMuchInOne(jsonParams.getString("muchInOne"));
            credential.setLicenseFileId(jsonParams.getInteger("licenseFileId"));
            if (null == jsonParams.get("muchInOne") || "N".equals(jsonParams.getString("muchInOne"))) {
                credential.setTissueCode(jsonParams.getString("tissueCode"));
                credential.setTissueIndate(jsonParams.getDate("tissueIndate"));
                credential.setTissueFileId(jsonParams.getInteger("tissueFileId"));
                credential.setTaxFileId(jsonParams.getInteger("taxFileId"));
                credential.setTaxCode(jsonParams.getString("taxCode"));
            }
            credential.setOthersFileId(jsonParams.getInteger("othersFileId"));
            credential.setLongLicenseIndate(jsonParams.getString("longLicenseIndate"));
            if (null == jsonParams.get("longLicenseIndate") || "N".equals(jsonParams.getString("longLicenseIndate"))) {
                credential.setLicenseIndate(jsonParams.getDate("licenseIndate"));
            } else if ("Y".equals(jsonParams.getString("longLicenseIndate"))) {
                credential.setLicenseIndate(null);
            }
            credential.setCorporateIdentity(jsonParams.getString("corporateIdentity"));
            credential.setRepresentativeName(jsonParams.getString("representativeName"));
            credential.setOperatorUserId(userId);
            credential.setCreationDate(new Date());
            credential.setLastUpdateDate(new Date());
            credential.setLastUpdatedBy(userId);
            srmPosSupplierCredentialsDAO_HI.saveEntity(credential);

            if (null != jsonParams.getJSONArray("lineDataList1")) {
                saveSupplierCategory(jsonParams.getJSONArray("lineDataList1"), userId, supplier.getSupplierId());
            }
            if (null != jsonParams.getJSONArray("lineDataList2")) {
                saveSupplierCertificate(jsonParams.getJSONArray("lineDataList2"), userId, supplier.getSupplierId());
            }
            if (null != jsonParams.getJSONArray("lineDataList3")) {
                saveSupplierBankInfo(jsonParams.getJSONArray("lineDataList3"), userId, supplier.getSupplierId());
            }
            if (null != jsonParams.getJSONArray("lineDataList4")) {
                saveSupplierContacts(jsonParams.getJSONArray("lineDataList4"), userId, supplier.getSupplierId());
            }
            if (null != jsonParams.getJSONArray("lineDataList5")) {
                saveSupplierAddress(jsonParams);
            }
            json.put("supplierId", supplier.getSupplierId());
            return SToolUtils.convertResultJSONObj("S", "保存成功", 1, json);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
        }
        return null;
    }


    /**
     * 保存供应商的产品与服务
     *
     * @param dataList
     * @param userId
     * @param supplierId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveSupplierCategory(JSONArray dataList, int userId, int supplierId) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        List<SrmPosSupplierCategoriesEntity_HI> rowList = new ArrayList<SrmPosSupplierCategoriesEntity_HI>();
        for (int i = 0; i < dataList.size(); i++) {
            paramData = dataList.getJSONObject(i);
            SrmPosSupplierCategoriesEntity_HI row = null;
            if (null == paramData.getInteger("supplierCategoryId")) {
                row = new SrmPosSupplierCategoriesEntity_HI();
                row.setSupplierId(supplierId);
                row.setVersionNum(0);
            } else {
                row = srmPosSupplierCategoriesDAO_HI.getById(paramData.getInteger("supplierCategoryId"));
            }
            row.setCategoryId(paramData.getInteger("categoryId"));
            row.setStatus(paramData.getString("status"));
            row.setBigCategoryCode(paramData.getString("bigCategoryCode"));
            row.setMiddleCategoryCode(paramData.getString("middleCategoryCode"));
            row.setSmallCategoryCode(paramData.getString("smallCategoryCode"));
            if (!"".equals(paramData.getString("approvedDate"))) {
                row.setApprovedDate(paramData.getDate("approvedDate"));
            }
            if (!"".equals(paramData.getString("invalidDate"))) {
                row.setInvalidDate(paramData.getDate("invalidDate"));
            }
            row.setOperatorUserId(userId);
            rowList.add(row);
        }
        srmPosSupplierCategoriesDAO_HI.save(rowList);
        return resultjson;
    }

    /**
     * 保存供应商的认证证书
     *
     * @param paramDataList
     * @param userId
     * @param supplierId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveSupplierCertificate(JSONArray paramDataList, int userId, int supplierId) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        for (int a = 0; a < paramDataList.size(); a++) {
            paramData = paramDataList.getJSONObject(a);
            SrmPosSupplierCertificateEntity_HI row = null;
            if (null == paramData.getInteger("certificateId")) {
                row = new SrmPosSupplierCertificateEntity_HI();
                row.setSupplierId(supplierId);
                row.setCreationDate(new Date());
                row.setVersionNum(0);
                row.setCreatedBy(userId);
            } else {
                row = srmPosSupplierCertificateDAO_HI.getById(paramData.getInteger("certificateId"));
            }
            row.setCertificateName(paramData.getString("certificateName"));
            row.setCertificateNumber(paramData.getString("certificateNumber"));
            row.setCertificateDescription(paramData.getString("certificateDescription"));
            row.setFileId(paramData.getInteger("fileId"));
            if (!"".equals(paramData.getString("endDate"))) {
                row.setEndDate(paramData.getDate("endDate"));
            }
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            srmPosSupplierCertificateDAO_HI.saveEntity(row);
        }
        return resultjson;
    }

    /**
     * 保存供应商的银行信息
     *
     * @param paramDataList
     * @param userId
     * @param supplierId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveSupplierBankInfo(JSONArray paramDataList, int userId, int supplierId) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        for (int b = 0; b < paramDataList.size(); b++) {
            paramData = paramDataList.getJSONObject(b);
            SrmPosSupplierBankEntity_HI row = null;
            if (null == paramData.getInteger("bankAccountId")) {
                row = new SrmPosSupplierBankEntity_HI();
                row.setSupplierId(supplierId);
                row.setCreationDate(new Date());
                row.setCreatedBy(userId);
                row.setVersionNum(0);
                row.setStartDate(new Date());
            } else {
                row = srmPosSupplierBankDAO_HI.getById(paramData.getInteger("bankAccountId"));
            }
            row.setBankAccountNumber(paramData.getString("bankAccountNumber"));
            row.setBankUserName(paramData.getString("bankUserName"));
            row.setSwiftCode(paramData.getString("swiftCode"));
            row.setIbanCode(paramData.getString("ibanCode"));
            row.setBankName(paramData.getString("bankName"));
            row.setBranchName(paramData.getString("branchName"));
            row.setBranchNumber(paramData.getString("branchNumber"));
            row.setBankCurrency(paramData.getString("bankCurrency"));
            if (null != SrmUtils.getString(paramData.getString("endDate"))) {
                row.setEndDate(paramData.getDate("endDate"));
            }
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            srmPosSupplierBankDAO_HI.saveEntity(row);
        }
        return resultjson;
    }

    /**
     * 保存供应商的联系人
     *
     * @param paramDataList
     * @param userId
     * @param supplierId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveSupplierContacts(JSONArray paramDataList, int userId, int supplierId) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        for (int c = 0; c < paramDataList.size(); c++) {
            paramData = paramDataList.getJSONObject(c);
            SrmPosSupplierContactsEntity_HI row = null;
            if (null == paramData.getInteger("supplierContactId")) {
                row = new SrmPosSupplierContactsEntity_HI();
                row.setSupplierId(supplierId);
                row.setCreationDate(new Date());
                row.setVersionNum(0);
                row.setCreatedBy(userId);
            } else {
                row = srmPosSupplierContactsDAO_HI.getById(paramData.getInteger("supplierContactId"));
            }
            row.setContactName(paramData.getString("contactName"));
            row.setDepartmentName(paramData.getString("departmentName"));
            row.setPositionName(paramData.getString("positionName"));
            row.setMobilePhone(paramData.getString("mobilePhone"));
            row.setEmailAddress(paramData.getString("emailAddress"));
            row.setFixedPhone(paramData.getString("fixedPhone"));
            row.setFaxPhone(paramData.getString("faxPhone"));
            if (null != paramData.get("failureDate")) {
                row.setFailureDate(paramData.getDate("failureDate"));
            }
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            srmPosSupplierContactsDAO_HI.saveEntity(row);
        }
        return resultjson;
    }

    /**
     * Description：保存供应商地址信息
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * supplierAddressId  供应商地址ID  NUMBER  Y
     * supplierId  供应商ID  NUMBER  Y
     * addressName  地址名称  VARCHAR2  N
     * country  国家(BASE_COUNTRY)  VARCHAR2  N
     * province  省(州)  VARCHAR2  N
     * city  城市  VARCHAR2  N
     * county  县(区)  VARCHAR2  N
     * detailAddress  详细地址  VARCHAR2  N
     * invalidDate  失效时间  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * contacts  联系人ID  NUMBER  N
     * addressCategory  地址类别，快码:ADDRESS_CATEGORY  VARCHAR2  N
     * supplierAddressId  供应商地址ID  NUMBER  Y
     * supplierId  供应商ID  NUMBER  Y
     * addressName  地址名称  VARCHAR2  N
     * country  国家(BASE_COUNTRY)  VARCHAR2  N
     * province  省(州)  VARCHAR2  N
     * city  城市  VARCHAR2  N
     * county  县(区)  VARCHAR2  N
     * detailAddress  详细地址  VARCHAR2  N
     * invalidDate  失效时间  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveSupplierAddress(JSONObject jsonParams) throws Exception {
        int userId = jsonParams.getInteger("varUserId");
        String addressResult = "N";
        addressResult = findCheckAddressRequired(jsonParams);
        if (!"Y".equals(addressResult)) {
            return SToolUtils.convertResultJSONObj("E", "" + addressResult, 0, null);
        }
        SrmPosSupplierAddressesEntity_HI addressesRow = null;
        SrmPosSupplierSitesEntity_HI sitesRow = null;
        List<SrmPosSupplierAddressesEntity_HI> addressesList = new ArrayList<SrmPosSupplierAddressesEntity_HI>();
        List<SrmPosSupplierSitesEntity_HI> sitesList = new ArrayList<SrmPosSupplierSitesEntity_HI>();
        int supplierId = jsonParams.getInteger("supplierId");
        int supplierAddressId = 0;
        if (null != jsonParams.get("supplierAddressId")) {
            supplierAddressId = jsonParams.getInteger("supplierAddressId");
        }

        if (null != jsonParams.getJSONArray("lineDataList5")) {
            JSONArray dataList = jsonParams.getJSONArray("lineDataList5");
            JSONObject paramData = null;
            for (int i = 0; i < dataList.size(); i++) {
                paramData = dataList.getJSONObject(i);
                if (null == paramData.getInteger("supplierAddressId")) {
                    addressesRow = new SrmPosSupplierAddressesEntity_HI();
                    addressesRow.setSupplierId(supplierId);
                    addressesRow.setVersionNum(0);
                } else {
                    addressesRow = srmPosSupplierAddressesDAO_HI.getById(paramData.getInteger("supplierAddressId"));
                }
                addressesRow.setAddressName(paramData.getString("addressName"));
                addressesRow.setCountry(paramData.getString("country"));
                addressesRow.setProvince(paramData.getString("province"));
                addressesRow.setCity(paramData.getString("city"));
                addressesRow.setCounty(paramData.getString("county"));
                addressesRow.setDetailAddress(paramData.getString("detailAddress"));
                addressesRow.setInvalidDate(paramData.getDate("invalidDate"));
                addressesRow.setOperatorUserId(userId);
                addressesList.add(addressesRow);
            }
            srmPosSupplierAddressesDAO_HI.save(addressesList);
        }
        if (null != jsonParams.get("supplierAddressId") && null != jsonParams.getJSONArray("lineDataList6")) {
            JSONArray dataList = jsonParams.getJSONArray("lineDataList6");
            JSONObject paramData = null;
            for (int i = 0; i < dataList.size(); i++) {
                paramData = dataList.getJSONObject(i);
                if (null == paramData.getInteger("supplierSiteId")) {
                    sitesRow = new SrmPosSupplierSitesEntity_HI();
                    sitesRow.setSupplierId(supplierId);
                    sitesRow.setVersionNum(0);
                } else {
                    sitesRow = srmPosSupplierSitesDAO_HI.getById(paramData.getInteger("supplierSiteId"));
                }
                sitesRow.setSiteName(paramData.getString("siteName"));
                sitesRow.setOperatorUserId(userId);
                sitesRow.setOrgId(paramData.getInteger("orgId"));
                sitesRow.setSupplierAddressId(supplierAddressId);
                sitesRow.setSiteStatus(paramData.getString("siteStatus"));
                sitesRow.setQualifiedDate(paramData.getDate("qualifiedDate"));
                sitesRow.setInvalidDate(paramData.getDate("invalidDate"));
                sitesRow.setUnfrozeDate(paramData.getDate("unfrozeDate"));
                sitesRow.setPurchaseFlag(paramData.getString("purchaseFlag"));
                sitesRow.setPaymentFlag(paramData.getString("paymentFlag"));
                sitesRow.setFrozeFlag(paramData.getString("frozeFlag"));
                sitesRow.setTemporarySiteFlag(paramData.getString("temporarySiteFlag"));
                sitesRow.setOperatorUserId(userId);
                srmPosSupplierSitesDAO_HI.saveOrUpdate(sitesRow);
            }
        }
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, null);
    }
    /**
     * 提交审批供应商的档案信息
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject saveApproveUpdate(JSONObject jsonParams) throws Exception {
        int userId = jsonParams.getInteger("varUserId");
        SrmPosChangeInfoEntity_HI change = null;
        SrmPosSupplierInfoEntity_HI supplier = null;
        SrmPosSupplierCredentialsEntity_HI credential = null;
        SrmPosSupplierCategoriesEntity_HI categories = null;
        SrmPosSupplierCertificateEntity_HI certificate = null;
        SrmPosSupplierBankEntity_HI bank = null;
        SrmPosSupplierContactsEntity_HI contacts = null;
        change = srmPosChangeInfoDAO_HI.getById(jsonParams.getInteger("changeId"));
        if (null != change) {
            supplier = srmPosSupplierInfoDAO_HI.getById(change.getSupplierId());
            if (null != supplier) {
                List<SrmPosSupplierCredentialsEntity_HI> credentialList = srmPosSupplierCredentialsDAO_HI.findByProperty("supplierId", supplier.getSupplierId());
                if (credentialList.size() > 0) {
                    credential = credentialList.get(0);
                }
            }
        } else {
            throw new Exception("变更单据在系统中不存在！");
        }
        SrmPosChangeBaseEntity_HI base = null;
        List<SrmPosChangeBaseEntity_HI> baseList = srmPosChangeBaseDAO_HI.findByProperty("changeId", jsonParams.getInteger("changeId"));
        if (baseList.size() > 0) {
            base = baseList.get(0);
            supplier.setSupplierName(base.getSupplierNameAf());
            supplier.setSupplierType(base.getSupplierTypeAf());
            supplier.setSupplierShortName(base.getSupplierShortNameAf());
            supplier.setCompanyPhone(base.getCompanyPhoneAf());
            supplier.setStaffNum(base.getStaffNumAf());
            supplier.setCompanyFax(base.getCompanyFaxAf());
            supplier.setPurchaseFlag(base.getPurchaseFlagAf());
            supplier.setPaymentFlag(base.getPaymentFlagAf());
            supplier.setSrmDelivery(base.getSrmDeliveryAf());
            supplier.setCompanyDescription(base.getCompanyDescription());
            supplier.setSupplierFileId(base.getSupplierFileIdAf());
            supplier.setFinClassify(base.getFinClassifyAf());
            supplier.setSettleAcctType(base.getSettleAcctTypeAf());
            supplier.setAcctCheckStaff(base.getAcctCheckStaffAf());
            supplier.setAcctCheckType(base.getAcctCheckTypeAf());
            supplier.setPosAcctCondition(base.getPosAcctConditionAf());
            supplier.setPosTax(base.getPosTaxAf());
            supplier.setAbleCheckOrderFlag(base.getAbleCheckOrderFlagAf());
            supplier.setAddress(base.getAddressAf());
            supplier.setPassU9Flag("U");
            supplier.setLastUpdateDate(new Date());
            supplier.setLastUpdatedBy(userId);
            supplier.setOperatorUserId(userId);
            srmPosSupplierInfoDAO_HI.saveEntity(supplier);
        }

        //变更资质信息
        List<SrmPosChangeCredentialsEntity_HI> credentialChangeList = srmPosChangeCredentialsDAO_HI.findByProperty("changeId", jsonParams.getInteger("changeId"));
        if (credentialChangeList.size() > 0 && null != credential) {
            SrmPosChangeCredentialsEntity_HI credenChange = credentialChangeList.get(0);
            credential.setBusinessScope(credenChange.getBusinessScopeAf());
            credential.setRegisteredAddress(credenChange.getRegisteredAddressAf());
            credential.setEstablishmentDate(credenChange.getEstablishmentDateAf());
            credential.setLicenseNum(credenChange.getLicenseNumAf());
            credential.setBankPermitFileId(credenChange.getBankPermitFileIdAf());
            credential.setBankPermitNumber(credenChange.getBankPermitNumberAf());
            credential.setMuchInOne(credenChange.getMuchInOneAf());
            credential.setLicenseFileId(credenChange.getLicenseFileIdAf());
            if ("N".equals(credenChange.getMuchInOneAf())) {
                credential.setTissueCode(credenChange.getTissueCodeAf());
                credential.setTissueIndate(credenChange.getTissueIndateAf());
                credential.setTissueFileId(credenChange.getTissueFileIdAf());
                credential.setTaxFileId(credenChange.getTaxFileIdAf());
                credential.setTaxCode(credenChange.getTaxCodeAf());
            } else {
                credential.setTissueCode(null);
                credential.setTissueIndate(null);
                credential.setTissueFileId(null);
                credential.setTaxFileId(null);
                credential.setTaxCode(null);
            }
            credential.setOthersFileId(credenChange.getOthersFileIdAf());
            credential.setLongLicenseIndate(credenChange.getLongLicenseIndateAf());
            if ("N".equals(credenChange.getLongLicenseIndateAf())) {
                credential.setLicenseIndate(credenChange.getLicenseIndateAf());
            }
            credential.setCorporateIdentity(credenChange.getCorporateIdentityAf());
            credential.setRepresentativeName(credenChange.getRepresentativeNameAf());
            credential.setOperatorUserId(userId);
            credential.setLastUpdateDate(new Date());
            credential.setLastUpdatedBy(userId);
            srmPosSupplierCredentialsDAO_HI.saveEntity(credential);
        }

        //变更产品与服务信息
        List<SrmPosChangeCategoriesEntity_HI> categoriesChangeList = srmPosChangeCategoriesDAO_HI.findByProperty("changeId", jsonParams.getInteger("changeId"));
        List<Integer> categoriesArrayList = new ArrayList<Integer>();
        for (int u = 0; u < categoriesChangeList.size(); u++) {
            if (null != categoriesChangeList.get(u).getSupplierCategoryId()) {
                categoriesArrayList.add(categoriesChangeList.get(u).getSupplierCategoryId());
            }
        }
        List<SrmPosSupplierCategoriesEntity_HI> supplierCategoriesList = srmPosSupplierCategoriesDAO_HI.findByProperty("supplierId", supplier.getSupplierId());
        for (int r = 0; r < supplierCategoriesList.size(); r++) {
            if (!categoriesArrayList.contains(supplierCategoriesList.get(r).getSupplierCategoryId())) {
                SrmPosSupplierCategoriesEntity_HI rowCategories = srmPosSupplierCategoriesDAO_HI.getById(supplierCategoriesList.get(r).getSupplierCategoryId());
                if (null != rowCategories) {
                    srmPosSupplierCategoriesDAO_HI.delete(rowCategories);
                }
            }
        }
        for (int t = 0; t < categoriesChangeList.size(); t++) {
            if (null == categoriesChangeList.get(t).getSupplierCategoryId()) {
                categories = new SrmPosSupplierCategoriesEntity_HI();
                categories.setSupplierId(supplier.getSupplierId());
                categories.setCreationDate(new Date());
                categories.setCreatedBy(userId);
            } else {
                categories = srmPosSupplierCategoriesDAO_HI.getById(categoriesChangeList.get(t).getSupplierCategoryId());
            }
            categories.setBigCategoryCode(categoriesChangeList.get(t).getBigCategoryCode());
            categories.setMiddleCategoryCode(categoriesChangeList.get(t).getMiddleCategoryCode());
            categories.setSmallCategoryCode(categoriesChangeList.get(t).getSmallCategoryCode());
            categories.setStatus(categoriesChangeList.get(t).getStatus());
            categories.setLastUpdateDate(new Date());
            categories.setLastUpdatedBy(userId);
            categories.setLastUpdateLogin(userId);
            categories.setOperatorUserId(userId);
            srmPosSupplierCategoriesDAO_HI.saveEntity(categories);
        }

        //变更认证证书信息
        List<SrmPosChangeCertificateEntity_HI> CertificateChangeList = srmPosChangeCertificateDAO_HI.findByProperty("changeId", jsonParams.getInteger("changeId"));
        List<Integer> certificateArrayList = new ArrayList<Integer>();
        for (int q = 0; q < CertificateChangeList.size(); q++) {
            if (null != CertificateChangeList.get(q).getCertificateId()) {
                certificateArrayList.add(CertificateChangeList.get(q).getCertificateId());
            }
        }
        List<SrmPosSupplierCertificateEntity_HI> supplierCertificateList = srmPosSupplierCertificateDAO_HI.findByProperty("supplierId", supplier.getSupplierId());
        for (int z = 0; z < supplierCertificateList.size(); z++) {
            if (!certificateArrayList.contains(supplierCertificateList.get(z).getCertificateId())) {
                SrmPosSupplierCertificateEntity_HI rowCertificate = srmPosSupplierCertificateDAO_HI.getById(supplierCertificateList.get(z).getCertificateId());
                if (null != rowCertificate) {
                    srmPosSupplierCertificateDAO_HI.delete(rowCertificate);
                }
            }
        }
        for (int k = 0; k < CertificateChangeList.size(); k++) {
            if (null == CertificateChangeList.get(k).getCertificateId()) {
                certificate = new SrmPosSupplierCertificateEntity_HI();
                certificate.setSupplierId(supplier.getSupplierId());
                certificate.setCreationDate(new Date());
                certificate.setCreatedBy(userId);
            } else {
                certificate = srmPosSupplierCertificateDAO_HI.getById(CertificateChangeList.get(k).getCertificateId());
            }
            certificate.setCertificateName(CertificateChangeList.get(k).getCertificateName());
            certificate.setCertificateNumber(CertificateChangeList.get(k).getCertificateNumber());
            certificate.setCertificateDescription(CertificateChangeList.get(k).getCertificateDescription());
            certificate.setFileId(CertificateChangeList.get(k).getFileId());
            if (null != CertificateChangeList.get(k).getStartDate()) {
                certificate.setStartDate(CertificateChangeList.get(k).getStartDate());
            }
            certificate.setLastUpdateDate(new Date());
            certificate.setLastUpdatedBy(userId);
            certificate.setLastUpdateLogin(userId);
            certificate.setOperatorUserId(userId);
            srmPosSupplierCertificateDAO_HI.saveEntity(certificate);
        }

        //变更银行信息
        List<SrmPosChangeBankEntity_HI> bankChangeList = srmPosChangeBankDAO_HI.findByProperty("changeId", jsonParams.getInteger("changeId"));
        for (int z = 0; z < bankChangeList.size(); z++) {
            SrmPosChangeBankEntity_HI changeBank = bankChangeList.get(z);
            if (null == changeBank.getBankAccountIdBf()) {
                bank = new SrmPosSupplierBankEntity_HI();
                bank.setSupplierId(supplier.getSupplierId());
                bank.setCreationDate(new Date());
                bank.setCreatedBy(userId);
                bank.setStartDate(new Date());
            } else {
                bank = srmPosSupplierBankDAO_HI.getById(changeBank.getBankAccountIdBf());
            }
            bank.setBankAccountNumber(changeBank.getBankAccountNumberAf());
            bank.setBankUserName(changeBank.getBankUserNameAf());
            bank.setBankName(changeBank.getBankNameAf());
            bank.setBranchName(changeBank.getBranchNameAf());
            bank.setBranchNumber(changeBank.getBranchNumberAf());
            bank.setBankCurrency(changeBank.getBankCurrencyAf());
            if (null != changeBank.getEndDateAf()) {
                bank.setEndDate(changeBank.getEndDateAf());
            }
            bank.setLastUpdateDate(new Date());
            bank.setLastUpdatedBy(userId);
            bank.setLastUpdateLogin(userId);
            bank.setOperatorUserId(userId);
            srmPosSupplierBankDAO_HI.saveEntity(bank);
            //失效变更前的银行信息
            changeBank.setInvalidDateBf(new Date());
            changeBank.setLastUpdateDate(new Date());
            changeBank.setLastUpdatedBy(userId);
            changeBank.setLastUpdateLogin(userId);
            changeBank.setOperatorUserId(userId);
            srmPosChangeBankDAO_HI.save(changeBank);
        }

        //变更联系人信息
        List<SrmPosChangeContactsEntity_HI> contactsChangeList = srmPosChangeContactsDAO_HI.findByProperty("changeId", jsonParams.getInteger("changeId"));
        List<Integer> contactsArrayList = new ArrayList<Integer>();
        for (int d = 0; d < contactsChangeList.size(); d++) {
            if (null != contactsChangeList.get(d).getSupplierContactId()) {
                contactsArrayList.add(contactsChangeList.get(d).getSupplierContactId());
            }
        }
        List<SrmPosSupplierContactsEntity_HI> supplierContactsList = srmPosSupplierContactsDAO_HI.findByProperty("supplierId", supplier.getSupplierId());
        for (int p = 0; p < supplierContactsList.size(); p++) {
            if (!contactsArrayList.contains(supplierContactsList.get(p).getSupplierContactId())) {
                SrmPosSupplierContactsEntity_HI rowContacts = srmPosSupplierContactsDAO_HI.getById(supplierContactsList.get(p).getSupplierContactId());
                if (null != rowContacts) {
                    srmPosSupplierContactsDAO_HI.delete(rowContacts);
                }
            }
        }
        for (int b = 0; b < contactsChangeList.size(); b++) {
            if (null == contactsChangeList.get(b).getSupplierContactId()) {
                contacts = new SrmPosSupplierContactsEntity_HI();
                contacts.setSupplierId(supplier.getSupplierId());
                contacts.setCreationDate(new Date());
                contacts.setCreatedBy(userId);
            } else {
                contacts = srmPosSupplierContactsDAO_HI.getById(contactsChangeList.get(b).getSupplierContactId());
            }
            contacts.setContactName(contactsChangeList.get(b).getContactName());
            contacts.setMobilePhone(contactsChangeList.get(b).getMobilePhone());
            contacts.setEmailAddress(contactsChangeList.get(b).getEmailAddress());
            contacts.setFixedPhone(contactsChangeList.get(b).getFixedPhone());
            contacts.setFaxPhone(contactsChangeList.get(b).getFaxPhone());
            contacts.setLastUpdateDate(new Date());
            contacts.setLastUpdatedBy(userId);
            contacts.setLastUpdateLogin(userId);
            contacts.setOperatorUserId(userId);
            srmPosSupplierContactsDAO_HI.saveEntity(contacts);
        }
        change.setChangeStatus("APPROVED");
        change.setLastUpdateDate(new Date());
        change.setLastUpdatedBy(userId);
        change.setLastUpdateLogin(userId);
        change.setOperatorUserId(userId);
        srmPosChangeInfoDAO_HI.save(change);
        return SToolUtils.convertResultJSONObj("S", "审批成功", 1, supplier.getSupplierNumber());
    }

    /**
     * 查询引入进度
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmPosSupplierInfoEntity_HI_RO> findIntroducingProgress(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer(SrmPosSupplierInfoEntity_HI_RO.QUERY_INTRODUCING_PROGRESS);
            SaafToolUtils.parperParam(jsonParams, "t.supplierName", "supplierName", queryString, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(jsonParams, "t.scheduleStatus", "scheduleStatus", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "t.instName", "instName", queryString, queryParamMap, "LIKE");
            String approveDateFrom = jsonParams.getString("approveDateFrom");
            if (approveDateFrom != null && !"".equals(approveDateFrom.trim())) {
                queryString.append(" AND trunc(t.approveDateQ) >= to_date(:approveDateFrom, 'yyyy-mm-dd')\n");
                queryParamMap.put("approveDateFrom", approveDateFrom);
            }
            String approveDateTo = jsonParams.getString("approveDateTo");
            if (approveDateTo != null && !"".equals(approveDateTo.trim())) {
                queryString.append(" AND trunc(t.approveDateQ) <= to_date(:approveDateTo, 'yyyy-mm-dd')\n");
                queryParamMap.put("approveDateTo", approveDateTo);
            }
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" ORDER BY t.approveDateQ DESC");
            Pagination<SrmPosSupplierInfoEntity_HI_RO> result = srmPosSupplierInfoDAO_HI_RO.findPagination
                    (queryString,countSql, queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询供应商变更lov
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosSupplierInfoEntity_HI_RO> findSupplierUpdateLov(JSONObject jsonParams, Integer pageIndex,
                                                                            Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmPosSupplierInfoEntity_HI_RO.QUERY_SUPPLIER_UPDATE_LOV_SQL);
            SaafToolUtils.parperParam(jsonParams, "spsi.supplier_name", "supplierName", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "spsi.supplier_number", "supplierNumber", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "spsi.supplier_id", "supplier_id", queryString, queryParamMap, "="); //供应商查询
            SaafToolUtils.parperParam(jsonParams, "spsi.supplier_id", "supplierId", queryString, queryParamMap, "=");
            //可采购
            SaafToolUtils.parperParam(jsonParams, "spsi.purchase_flag", "purchaseFlag", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "spsi.srm_delivery", "srmDelivery", queryString, queryParamMap, "=");
            String countSql = "select count(1) from (" + queryString + ")";
            Pagination<SrmPosSupplierInfoEntity_HI_RO> result = srmPosSupplierInfoDAO_HI_RO.findPagination
                    (queryString.toString(),countSql, queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    /**
     * 获取供应商编号
     *
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public String getSupplierNumber() {
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPosSupplierInfoEntity_HI_RO.QUERY_SUPPLIER_NUMBER);
        SrmPosSupplierInfoEntity_HI_RO srmPosSupplierInfoEntity_HI_RO = srmPosSupplierInfoDAO_HI_RO.findList(queryString.toString()).get(0);
        return srmPosSupplierInfoEntity_HI_RO.getSupplierNumber();
    }

    /**
     * LOV:供应商名称
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmPosSupplierInfoEntity_HI_RO> findSupplierLov(
            JSONObject parameters, Integer pageIndex, Integer pageRows)
            throws Exception {
        try {
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer sb = new StringBuffer();
            sb.append(SrmPosSupplierInfoEntity_HI_RO.LOV_SUPPLIER_QUERY_SQL);
            SaafToolUtils.parperParam(parameters, "psi.supplier_id", "supplier_id", sb, queryParamMap, "=");// 如果是供应商查询
            SaafToolUtils.parperParam(parameters, "psi.supplier_id", "supplierId", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(parameters, "psi.supplier_name", "supplierName", sb, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(parameters, "psi.supplier_number", "supplierNumber", sb, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(parameters, "psi.supplier_status", "supplierStatus", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(parameters, "slv1.meaning", "supplierStatusDesc", sb, queryParamMap, "LIKE");
            String blacklistFlag = parameters.getString("blacklistFlag");
            if ("N".equals(blacklistFlag)) {
                sb.append(" AND (psi.blacklist_flag = 'N' OR psi.blacklist_flag IS NULL)");
            }
            String supplierStatus = parameters.getString("supplierStatus");
            if ("QUALIFICATION".equals(supplierStatus)) {
                sb.append(" AND psi.supplier_status IN ('APPROVED', 'INTRODUCING', 'EFFETIVE', 'QUIT')");
            }
            if ("CHANGE".equals(supplierStatus)) {
                sb.append(" AND psi.supplier_status IN ('APPROVED', 'INTRODUCING', 'EFFETIVE')");
            }
            parameters.put("supplierType", getSupplierType(parameters.getInteger("varUserId")));
            SaafToolUtils.parperParam(parameters, "psi.Supplier_Type", "supplierType", sb, queryParamMap, "IN");

            String countSql = "select count(1) from (" + sb + ")";
            Pagination<SrmPosSupplierInfoEntity_HI_RO> rowSet = srmPosSupplierInfoDAO_HI_RO.findPagination(sb,countSql, queryParamMap, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 供应商状态分布报表查询
     *
     * @param jsonParam
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Map<String, Object> findSupplierStatusDistribution(JSONObject jsonParam) {
        Map<String, Object> map = new HashMap<>();  //最终结果的返回
        Map<String, Object> mapLook = new HashMap<>();
        mapLook.put("lookupType", "POS_SUPPLIER_STATUS");
        mapLook.put("enabledFlag", "Y");
        List<SaafLookupValuesEntity_HI> lookupValuesList = saafLookupValuesDAO_HI.findByProperty(mapLook);
        if (lookupValuesList == null || lookupValuesList.size() == 0) {
            map.put("status", "E");
            map.put("msg", "查无此数据！");
            map.put("data", new ArrayList<>());
            map.put("count", 0);
            return map;
        }
        StringBuilder sql = new StringBuilder("SELECT * FROM srm_pos_supplier_info t where 1=1 ");
        String status = "'" + lookupValuesList.get(0).getLookupCode() + "'";
        for (Integer i = 1; i < lookupValuesList.size(); i++) {
            if (null != lookupValuesList.get(i).getLookupCode()) {
                status += ",'" + lookupValuesList.get(i).getLookupCode() + "'";
            }
        }
        sql.append(" AND t.supplier_status in(" + status + ")");
        List<SrmPosSupplierInfoEntity_HI_RO> list = srmPosSupplierInfoDAO_HI_RO.findList(sql.toString());
        if (list == null || list.size() == 0) {
            map.put("status", "E");
            map.put("msg", "查无此数据！");
            map.put("data", new ArrayList<>());
            map.put("count", 0);
            return map;
        }
        BigDecimal sum = new BigDecimal(list.size());//供应商总数量
        //下面将结果转化成百分比
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(4);
        Map<String, Object> mapV = new HashMap<>();
        Iterator<SaafLookupValuesEntity_HI> it = lookupValuesList.iterator();
        while (it.hasNext()) {
            SaafLookupValuesEntity_HI k = it.next();
            if (null != k.getLookupCode() && (k.getLookupCode().equals("DRAFT") || k.getLookupCode().equals("REJECTED") || k.getLookupCode().equals("SUBMITTED"))) {
                it.remove();
            }
        }
        String[] legend = new String[lookupValuesList.size() + 1];
        legend[0] = "注册中";
        for (Integer i = 0; i < lookupValuesList.size(); i++) {
            SaafLookupValuesEntity_HI k = lookupValuesList.get(i);
            if (null != k.getLookupCode()) {
                if ("APPROVED".equals(k.getLookupCode())) {//APPROVED已批准的代表是潜在
                    k.setMeaning("潜在");
                } else if ("QUIT".equals(k.getLookupCode())) {//QUIT退出的代表是不合作
                    k.setMeaning("退出不合作");
                }
            }
            legend[i + 1] = k.getMeaning();
        }
        mapV.put("legend", legend);//图例名称数组
        JSONArray dataArray = new JSONArray();//存储图表的详细信息

        List<SrmPosSupplierInfoEntity_HI_RO> supplierInfoList = new ArrayList<>();//供应商状态分布的表格
        //注册中的供应商的数量
        if (true) {
            StringBuilder sb = new StringBuilder("SELECT * FROM srm_pos_supplier_info t where 1=1 ");
            sb.append(" AND t.supplier_status in('DRAFT','REJECTED','SUBMITTED') ");
            List<SrmPosSupplierInfoEntity_HI_RO> listV = srmPosSupplierInfoDAO_HI_RO.findList(sb.toString());
            if (null != listV) {
                SrmPosSupplierInfoEntity_HI_RO supplierInfo = new SrmPosSupplierInfoEntity_HI_RO();
                supplierInfo.setSupplierStatus("DRAFT,REJECTED,SUBMITTED");
                supplierInfo.setStatusStr("注册中");
                supplierInfo.setSupplierCount(new BigDecimal(listV.size()));//供应商数量
                BigDecimal supplierProportion = supplierInfo.getSupplierCount().divide(sum, 4, BigDecimal.ROUND_HALF_UP);//BigDecimal.ROUND_HALF_UP四舍五入，2.35变成2.4
                supplierInfo.setSupplierProportion(percent.format(supplierProportion));//供应商数量的百分比
                supplierInfoList.add(supplierInfo);
                JSONObject json = new JSONObject();//用来存储一个状态类型的供应商的数据列
                json.put("name", "注册中");
                json.put("y", listV.size());
                json.put("status", "DRAFT,REJECTED,SUBMITTED");
                dataArray.add(json);
            }
        }
        for (SaafLookupValuesEntity_HI k : lookupValuesList) {
            if (null != k.getLookupCode() && !"".equals(k.getLookupCode())) {
                StringBuilder sbV = new StringBuilder("SELECT * FROM srm_pos_supplier_info t where 1=1 ");
                sbV.append(" AND t.supplier_status='" + k.getLookupCode() + "'");
                List<SrmPosSupplierInfoEntity_HI_RO> listV = srmPosSupplierInfoDAO_HI_RO.findList(sbV.toString());
                if (null != listV) {
                    SrmPosSupplierInfoEntity_HI_RO supplierInfo = new SrmPosSupplierInfoEntity_HI_RO();
                    supplierInfo.setSupplierStatus(k.getLookupCode());
                    supplierInfo.setStatusStr(k.getMeaning());
                    supplierInfo.setSupplierCount(new BigDecimal(listV.size()));//供应商数量
                    BigDecimal supplierProportion = supplierInfo.getSupplierCount().divide(sum, 4, BigDecimal.ROUND_HALF_UP);//BigDecimal.ROUND_HALF_UP四舍五入，2.35变成2.4
                    supplierInfo.setSupplierProportion(percent.format(supplierProportion));//供应商数量的百分比
                    supplierInfoList.add(supplierInfo);
                    JSONObject json = new JSONObject();//用来存储一个状态类型的供应商的数据列
                    json.put("name", k.getMeaning());
                    json.put("y", listV.size());
                    json.put("status", k.getLookupCode());
                    dataArray.add(json);
                }
            }
        }
        mapV.put("supplierInfoList", supplierInfoList);
        mapV.put("series", dataArray);

        map.put("data", mapV);
        map.put("count", 1);
        map.put("msg", "查询成功");
        map.put("status", "S");
        return map;
    }

    /**
     * 未产生交易的供应商数量报表
     *
     * @param jsonParam
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Map<String, Object> findSupplierTransactionSituation(JSONObject jsonParam) {
        Map<String, Object> map = new HashMap<>();  //最终结果的返回
        StringBuffer sbL = new StringBuffer("SELECT * FROM saaf_lookup_values t WHERE t.lookup_type = 'TRANSACTIONCYCLE' AND t.enabled_flag = 'Y' ORDER  BY t.lookup_values_id DESC");
        List<SaafLookupValuesEntity_HI_RO> lookupValuesList = saafLookupValuesDAO_HI_RO.findList(sbL.toString());
        if (lookupValuesList == null || lookupValuesList.size() == 0) {
            map.put("status", "E");
            map.put("msg", "查无此数据！");
            map.put("data", new ArrayList<>());
            map.put("count", 0);
            return map;
        }
        Map<String, Object> mapV = new HashMap<>();
        String[] categories = new String[lookupValuesList.size()];//x轴名称数组
        JSONArray dataArray = new JSONArray();//存储图表的详细信息
        List<SrmPosSupplierInfoEntity_HI_RO> supplierInfoList = new ArrayList<>();//未产生交易的供应商数量的表格
        for (Integer i = 0; i < lookupValuesList.size(); i++) {
            SaafLookupValuesEntity_HI_RO k = lookupValuesList.get(i);
            if (null != k.getLookupCode() && !"".equals(k.getLookupCode())) {
                categories[i] = k.getMeaning();
                StringBuilder sb = new StringBuilder("SELECT * FROM srm_pos_supplier_info t WHERE t.supplier_status = 'EFFETIVE'\n");
                if ("More 2 YEAR".equals(k.getLookupCode())) {//2年以上
                    sb.append(      "AND    (EXISTS\n" +
                                    "       (SELECT sph.supplier_id\n" +
                                    "         FROM   srm_po_headers sph\n" +
                                    "         WHERE  sph.supplier_id = t.supplier_id\n" +
                                    "         AND    sph.po_doc_type = 'ORDER'\n" +
                                    "         AND    trunc(sph.creation_date) < trunc(add_months(SYSDATE, -24))) OR\n" +
                                    "       NOT EXISTS (SELECT sph.supplier_id\n" +
                                    "                    FROM   srm_po_headers sph\n" +
                                    "                    WHERE  sph.supplier_id = t.supplier_id\n" +
                                    "                    AND    sph.po_doc_type = 'ORDER'))\n" +
                                    "AND    trunc(t.creation_date) <= trunc(add_months(SYSDATE, -24))");
                } else {
                    sb.append(      "AND    NOT EXISTS\n" +
                                    " (SELECT sph.supplier_id\n" +
                                    "        FROM   srm_po_headers sph\n" +
                                    "        WHERE  sph.supplier_id = t.supplier_id\n" +
                                    "        AND    sph.po_doc_type = 'ORDER'\n" +
                                    "        AND    add_months(SYSDATE, -" + k.getLookupCode() + ") <= trunc(sph.creation_date))\n" +
                                    "AND    trunc(t.creation_date) <= add_months(SYSDATE, -" + k.getLookupCode() + ")");
                }
                List<SrmPosSupplierInfoEntity_HI_RO> listV = srmPosSupplierInfoDAO_HI_RO.findList(sb.toString());
                if (null != listV) {
                    SrmPosSupplierInfoEntity_HI_RO supplierInfo = new SrmPosSupplierInfoEntity_HI_RO();
                    supplierInfo.setTimeFrame(k.getLookupCode());
                    supplierInfo.setTimeFrameName(k.getMeaning());
                    supplierInfo.setSupplierCount(new BigDecimal(listV.size()));//供应商数量
                    supplierInfoList.add(supplierInfo);
                    JSONObject json = new JSONObject();//用来存储一个未交易周期类型的供应商的数据列
                    json.put("name", k.getMeaning());
                    json.put("y", listV.size());
                    json.put("timeFrame", k.getLookupCode());
                    dataArray.add(json);
                }
            }
        }
        mapV.put("categories", categories);//x轴名称数组
        mapV.put("supplierInfoList", supplierInfoList);
        mapV.put("series", dataArray);

        map.put("data", mapV);
        map.put("count", 1);
        map.put("msg", "查询成功");
        map.put("status", "S");
        return map;
    }

    /**
     * 供应商引入退出报表
     *
     * @param jsonParam
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Map<String, Object> findIntroductionAndQuit(JSONObject jsonParam) {
        Map<String, Object> map = new HashMap<>();  //最终结果的返回
        Calendar cal = Calendar.getInstance();
        String currentYear = DateUtil.date2Str(new Date(), "yyyy-MM-dd");
        String currentYearMonth = DateUtil.date2Str(new Date(), "yyyy-MM-dd");
        Integer currentYearArray = cal.get(Calendar.YEAR);
        Date yearMonth = new Date();
        if (null != jsonParam.getString("currentYear") && !"".equals(jsonParam.getString("currentYear"))) {
            Date year = DateUtil.str2Date(jsonParam.getString("currentYear"), "yyyy");
            currentYear = DateUtil.date2Str(year, "yyyy-MM-dd");
            currentYearArray = Integer.parseInt(jsonParam.getString("currentYear"));
        }
        if (null != jsonParam.getString("currentYearMonth") && !"".equals(jsonParam.getString("currentYearMonth"))) {
            yearMonth = DateUtil.str2Date(jsonParam.getString("currentYearMonth"), "yyyy-MM");
            currentYearMonth = DateUtil.date2Str(yearMonth, "yyyy-MM-dd");
        }
        String[] xAxisData = new String[3];//引入退出，x轴——3年
        xAxisData[0] = (currentYearArray - 2) + "年";
        xAxisData[1] = (currentYearArray - 1) + "年";
        xAxisData[2] = currentYearArray + "年";
        JSONArray supplierInfoList = new JSONArray();//供应商引入退出——表格数据，3年
        map.put("xAxisData", xAxisData);//供应商引入退出，x轴的数据
        JSONArray seriesQuitData = new JSONArray();//供应商引入退出——退出，y轴的数据
        JSONArray seriesIntroduceData = new JSONArray();//供应商引入退出——引入，y轴的数据
        //三年内引入退出统计
        for (Integer i = 2; i >= 0; i--) {
            JSONObject supplier = new JSONObject();
            supplier.put("supplierYear", xAxisData[2 - i]);
            supplier.put("supplierCurrentYear", (currentYearArray - i));
            supplier.put("supplierIntroduceName", "INTRODUCE");
            supplier.put("supplierQuitName", "QUIT");
            Integer quitList = iSrmPosSupplierQuit.findSupplierQuitCount(currentYear, i);//退出
            Integer introduceList = iQualificationInfo.findSupplierIntroduceCount(currentYear, null, i);//引入
            if (null != quitList) {
                JSONObject json = new JSONObject();//用来存储一个年份供应商退出的数据列
                json.put("name", xAxisData[2 - i]);
                json.put("supplierQuitName", "QUIT");
                json.put("supplierCurrentYear", (currentYearArray - i));
                json.put("value", quitList);
                seriesQuitData.add(json);
                supplier.put("supplierQuitCount", quitList);
            }
            if (null != introduceList) {
                JSONObject jsonIntroduce = new JSONObject();//用来存储一个年份供应商引入的数据列
                jsonIntroduce.put("name", xAxisData[2 - i]);
                jsonIntroduce.put("supplierIntroduceName", "INTRODUCE");
                jsonIntroduce.put("supplierCurrentYear", (currentYearArray - i));
                jsonIntroduce.put("value", introduceList);
                seriesIntroduceData.add(jsonIntroduce);
                supplier.put("supplierIntroduceCount", introduceList);
            }
            supplierInfoList.add(supplier);
        }
        map.put("seriesQuitData", seriesQuitData);
        map.put("seriesIntroduceData", seriesIntroduceData);
        map.put("supplierInfoList", supplierInfoList);

        JSONArray supplierIntroduceList = new JSONArray();//供应商引入——表格数据，24个月
        String[] xAxisVData = new String[24];//引入，x轴——24个月
        JSONArray seriesVData = new JSONArray();//供应商引入——数量，y轴的数据
        JSONArray seriesVSumData = new JSONArray();//供应商引入——总数量，y轴的数据
        //两年内引入统计
        Integer yearV = 0;
        for (Integer i = 23; i >= 0; i--) {
            JSONObject supplier = new JSONObject();
            Calendar newCal = Calendar.getInstance();
            newCal.setTime(yearMonth);
            newCal.add(Calendar.MARCH, -i);
            xAxisVData[23 - i] = DateUtil.date2Str(newCal.getTime(), "yyyy-MM");
            Integer introduceList = iQualificationInfo.findSupplierIntroduceCount(null, currentYearMonth, i);//引入——月份
            supplier.put("supplierYear", newCal.get(Calendar.YEAR) + "年");
            supplier.put("supplierYearMonth", xAxisVData[23 - i]);
            if (null != introduceList) {
                JSONObject json = new JSONObject();//用来存储一个月份供应商引入的数据列
                json.put("name", xAxisVData[23 - i]);
                json.put("value", introduceList);
                seriesVData.add(json);
                supplier.put("supplierIntroduceCount", introduceList);
            }
            supplierIntroduceList.add(supplier);
        }
        //供应商引入——24个月，表格数据拼接
        Map<String, Object> mapV = new HashMap<>();//计算年份，供应商引入
        Map<String, Object> mapVV = new HashMap<>();//计算累计的月份，供应商引入
        JSONObject supplierV = (JSONObject) supplierIntroduceList.get(0);
        String introduceYear = supplierV.getString("supplierYear");
        Integer count = 0;
        Integer sum = 0;
        for (Integer i = 0; i < supplierIntroduceList.size(); i++) {
            JSONObject supplier = (JSONObject) supplierIntroduceList.get(i);
            if (introduceYear.equals(supplier.getString("supplierYear"))) {
                count++;
                sum += supplier.getInteger("supplierIntroduceCount");//累加每个月份
            } else {
                mapV.put(introduceYear, count);
                mapVV.put(introduceYear, sum);
                count = 1;
                sum = 0;
                introduceYear = supplier.getString("supplierYear");
            }
            if (i == (supplierIntroduceList.size() - 1)) {
                mapV.put(introduceYear, count);
                mapVV.put(introduceYear, sum);
            }
        }
        for (Integer i = 0; i < supplierIntroduceList.size(); i++) {
            JSONObject supplier = (JSONObject) supplierIntroduceList.get(i);
            supplier.put("supplierYearCount", mapV.get(supplier.getString("supplierYear")));
            supplier.put("supplierIntroduceYearCount", mapVV.get(supplier.getString("supplierYear")));//累加的月份的总数量
        }
        String introduceYearV = supplierV.getString("supplierYear");
        JSONObject jsonV = new JSONObject();//用来存储一个年份供应商引入的数据列
        jsonV.put("name", ((JSONObject) supplierIntroduceList.get(0)).getString("supplierYearMonth"));
        jsonV.put("supplierYearCount", supplierV.getInteger("supplierYearCount"));
        jsonV.put("value", supplierV.getInteger("supplierIntroduceYearCount"));
        seriesVSumData.add(jsonV);
        for (Integer i = 1; i < supplierIntroduceList.size(); i++) {
            JSONObject supplier = (JSONObject) supplierIntroduceList.get(i);
            JSONObject json = new JSONObject();//用来存储一个年份供应商引入的数据列
            json.put("name", supplier.getString("supplierYearMonth"));
            if (introduceYearV.equals(supplier.getString("supplierYear"))) {
                supplier.put("supplierYearCount", -1);
                json.put("value", null);
            } else {
                json.put("value", supplier.getInteger("supplierIntroduceYearCount"));
                json.put("supplierYearCount", supplier.getInteger("supplierYearCount"));
                introduceYearV = supplier.getString("supplierYear");
            }
            seriesVSumData.add(json);
        }
        map.put("seriesVData", seriesVData);
        map.put("seriesVSumData", seriesVSumData);
        map.put("xAxisVData", xAxisVData);
        map.put("supplierIntroduceList", supplierIntroduceList);
        return map;
    }

    /**
     * 供应商分级状态报表查询
     *
     * @param jsonParam
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Map<String, Object> findSupplierGradeDistribution(JSONObject jsonParam) {
        //最终结果的返回
        Map<String, Object> map = new HashMap<>();
        /* 快码查找 */
        Map<String, Object> mapLook = new HashMap<>();
        mapLook.put("lookupType", "GRADE");
        mapLook.put("enabledFlag", "Y");
        List<SaafLookupValuesEntity_HI> lookupValuesList = saafLookupValuesDAO_HI.findByProperty(mapLook);
        //判断数据是否存在
        if (lookupValuesList == null || lookupValuesList.size() == 0) {
            map.put("status", "E");
            map.put("msg", "查无此数据！");
            map.put("data", new ArrayList<>());
            map.put("count", 0);
            return map;
        }
        /* 查找合格状态的供应商 */
        StringBuilder sql = new StringBuilder("SELECT " +
                "\tt.supplier_id AS supplierId,\n" +
                "\tt.supplier_number AS supplierNumber,\n" +
                "\tt.supplier_name AS supplierName,\n" +
                "\tt.supplier_short_name AS supplierShortName,\n" +
                "\tt.supplier_type AS supplierType,\n" +
                "\tt.supplier_classify AS supplierClassify,\n" +
                "\tt.supplier_industry AS supplierIndustry,\n" +
                "\tt.supplier_status AS supplierStatus\n" +
                "FROM srm_pos_supplier_info t where 1=1 ");
        sql.append(" AND t.supplier_status = 'EFFETIVE' ");
        String grade = "'" + lookupValuesList.get(0).getLookupCode() + "'";
        for (Integer i = 1; i < lookupValuesList.size(); i++) {
            if (null != lookupValuesList.get(i).getLookupCode()) {
                grade += ",'" + lookupValuesList.get(i).getLookupCode() + "'";
            }
        }
        List<SrmPosSupplierInfoEntity_HI_RO> list = srmPosSupplierInfoDAO_HI_RO.findList(sql.toString());
        //判断数据是否存在
        if (list == null || list.size() == 0) {
            map.put("status", "E");
            map.put("msg", "查无此数据！");
            map.put("data", new ArrayList<>());
            map.put("count", 0);
            return map;
        }
        //合格状态的供应商总数量
        BigDecimal sum = new BigDecimal(list.size());
        //下面将结果转化成百分比
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(4);
        Map<String, Object> mapV = new HashMap<>();
        String[] legend = new String[lookupValuesList.size() + 1];
        legend[0] = "未分级";
        for (Integer i = 0; i < lookupValuesList.size(); i++) {
            SaafLookupValuesEntity_HI k = lookupValuesList.get(i);
            if (null != k.getLookupCode()) {
                if ("STRATEGY".equals(k.getLookupCode())) {
                    //STRATEGY的代表是战略
                    k.setMeaning("战略");
                } else if ("OPTIMIZATION".equals(k.getLookupCode())) {
                    //STRATEGY的代表是优选
                    k.setMeaning("优选");
                } else if ("EFFETIVE".equals(k.getLookupCode())) {
                    //EFFETIVE的代表是合格
                    k.setMeaning("合格");
                } else if ("ELIMINATION".equals(k.getLookupCode())) {
                    //ELIMINATION的代表是淘汰
                    k.setMeaning("淘汰");
                }
            }
            legend[i] = k.getMeaning();
        }
        //图例名称数组
        mapV.put("legend", legend);
        //存储图表的详细信息
        JSONArray dataArray = new JSONArray();
        //供应商状态分布的表格
        List<SrmPosSupplierInfoEntity_HI_RO> supplierInfoList = new ArrayList<>();
        //计算未分级的供应商的数量
        if (true) {
            StringBuilder sb = new StringBuilder("SELECT * FROM srm_pos_supplier_info t " +
                    "WHERE 1=1  " +
                    "AND t.supplier_status = 'EFFETIVE' " +
                    "AND (t.grade_line_id IS NULL OR t.grade_line_id NOT IN (SELECT grade_line_id FROM srm_pos_supplier_grade_lines WHERE 1 = 1 )) ");
            List<SrmPosSupplierInfoEntity_HI_RO> listV = srmPosSupplierInfoDAO_HI_RO.findList(sb.toString());
            if (null != listV) {
                SrmPosSupplierInfoEntity_HI_RO supplierInfo = new SrmPosSupplierInfoEntity_HI_RO();
                supplierInfo.setSupplierGrade(null);
                supplierInfo.setGradeStr("未分级");
                //供应商数量
                supplierInfo.setSupplierCount(new BigDecimal(listV.size()));
                //BigDecimal.ROUND_HALF_UP四舍五入，2.35变成2.4
                BigDecimal supplierProportion = supplierInfo.getSupplierCount().divide(sum, 4, BigDecimal.ROUND_HALF_UP);
                //供应商数量的百分比
                supplierInfo.setSupplierProportion(percent.format(supplierProportion));
                supplierInfoList.add(supplierInfo);
                //用来存储一个状态类型的供应商的数据列
                JSONObject json = new JSONObject();
                json.put("name", "未分级");
                json.put("y", listV.size());
                json.put("grade", null);
                dataArray.add(json);
            }
        }
        //各个分级供应商的数量
        for (SaafLookupValuesEntity_HI k : lookupValuesList) {
            if (null != k.getLookupCode() && !"".equals(k.getLookupCode())) {
                StringBuilder sbV = new StringBuilder("SELECT " +
                        "\ta.supplier_id AS supplierId,\n" +
                        "\ta.supplier_number AS supplierNumber,\n" +
                        "\ta.supplier_name AS supplierName,\n" +
                        "\ta.supplier_short_name AS supplierShortName,\n" +
                        "\ta.supplier_type AS supplierType,\n" +
                        "\ta.supplier_classify AS supplierClassify,\n" +
                        "\ta.supplier_industry AS supplierIndustry,\n" +
                        "\ta.supplier_status AS supplierStatus,\n" +
                        "\tspsgl.adjust_grade  \n" +
                        "\tFROM (SELECT * FROM srm_pos_supplier_info t WHERE 1=1  AND t.supplier_status = 'EFFETIVE') a \n" +
                        "\tLEFT JOIN srm_pos_supplier_grade_lines spsgl ON a.supplier_id=spsgl.supplier_id AND a.grade_line_id=spsgl.grade_line_id \n" +
                        "\tWHERE 1=1 \n");
                sbV.append("\t AND spsgl.adjust_grade='" + k.getLookupCode() + "'\n");
                List<SrmPosSupplierInfoEntity_HI_RO> listV = srmPosSupplierInfoDAO_HI_RO.findList(sbV.toString());
                if (null != listV) {
                    SrmPosSupplierInfoEntity_HI_RO supplierInfo = new SrmPosSupplierInfoEntity_HI_RO();
                    supplierInfo.setSupplierGrade(k.getLookupCode());
                    supplierInfo.setGradeStr(k.getMeaning());
                    //供应商数量
                    supplierInfo.setSupplierCount(new BigDecimal(listV.size()));
                    //BigDecimal.ROUND_HALF_UP四舍五入，2.35变成2.4
                    BigDecimal supplierProportion = supplierInfo.getSupplierCount().divide(sum, 4, BigDecimal.ROUND_HALF_UP);
                    //供应商数量的百分比
                    supplierInfo.setSupplierProportion(percent.format(supplierProportion));
                    supplierInfoList.add(supplierInfo);
                    //用来存储一个状态类型的供应商的数据列
                    JSONObject json = new JSONObject();
                    json.put("name", k.getMeaning());
                    json.put("y", listV.size());
                    json.put("grade", k.getLookupCode());
                    dataArray.add(json);
                }
            }
        }
        mapV.put("supplierInfoList", supplierInfoList);
        mapV.put("series", dataArray);
        /* 返回前端json数据 */
        map.put("data", mapV);
        map.put("count", 1);
//		map.put("sum",sum);//返回合格供应商的总数
        map.put("msg", "查询成功");
        map.put("status", "S");
        return map;
    }

    /**
     * 采购总金额统计报表
     *
     * @param jsonParam
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Map<String, Object> findPurchaseAmountCount(JSONObject jsonParam) {
        String startDate = jsonParam.getString("startDateOfTotal");
        String endDate = jsonParam.getString("endDateOfTotal");
        Map<String, Object> map = new HashMap<>();  //最终结果的返回
        StringBuffer sbL = new StringBuffer("SELECT si.inst_name FROM saaf_institution si WHERE si.inst_type = 'ORG' AND si.enabled = 'Y' ");
        List<SaafInstitutionEntity_HI_RO> saafInstitutionEntityList = saafInstitutionEntity_HI_RO.findList(sbL.toString());//查找所有业务实体
        if (saafInstitutionEntityList == null || saafInstitutionEntityList.size() == 0) {
            map.put("status", "E");
            map.put("msg", "查无此数据！");
            map.put("data", new ArrayList<>());
            map.put("count", 0);
            return map;
        }
        Map<String, Object> mapV = new HashMap<>();//存储查询的数据
        String[] categories = new String[saafInstitutionEntityList.size()];//x轴名称数组
        JSONArray dataArray = new JSONArray();//存储订单总金额图表的详细信息
        JSONArray dataArray2 = new JSONArray();//存储接收总金额图表的详细信息
        List<SrmPosSupplierInfoEntity_HI_RO> supplierInfoList = new ArrayList<>();//未产生交易的供应商数量的表格
        BigDecimal sumTotalPrice = new BigDecimal(0);//存储所有业务实体的订单总金额的合计
        BigDecimal sumReceivePrice = new BigDecimal(0);//存储所有业务实体的接收总金额的合计
        //下面将结果转化成百分比
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(4);
        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(startDate) || SrmUtils.isContainSQL(endDate)) {
            return null;
        }
        //查询所有业务实体订单总金额的总和
        StringBuilder sumTotalPriceSQL = new StringBuilder("SELECT SUM(spl.demand_qty*spl.non_tax_price) AS nonTaxTotalPrice \n" +
                "FROM  srm_po_headers sph,srm_po_lines spl,saaf_institution si\n" +
                "WHERE sph.po_header_id = spl.po_header_id AND sph.org_id = si.inst_id AND trunc(spl.creation_date) >= to_date('" + startDate + "', 'yyyy-mm-dd') AND trunc(spl.creation_date) < to_date('" + endDate + "', 'yyyy-mm-dd')\n");
        List<SrmPosSupplierInfoEntity_HI_RO> TotalPrice = srmPosSupplierInfoDAO_HI_RO.findList(sumTotalPriceSQL.toString());//查找订单总金额
        if (null == TotalPrice.get(0).getNonTaxTotalPrice()) {//判断是否为空，空则赋值为0
            sumTotalPrice = BigDecimal.valueOf(0);
        } else {
            sumTotalPrice = TotalPrice.get(0).getNonTaxTotalPrice();
        }

        //查询所有业务实体接收总金额的总和
        StringBuilder sumReceivePriceSQL = new StringBuilder("SELECT SUM(spdl.received_qty*spl.non_tax_price) AS nonTaxReceivePrice \n" +
                "FROM  srm_po_delivery_lines spdl,srm_po_lines spl,srm_po_delivery_headers spdh,saaf_institution si\n" +
                "WHERE spdl.po_line_id = spl.po_line_id AND spdh.delivery_header_id = spdl.delivery_header_id AND si.inst_id = spdh.org_id ");
        List<SrmPosSupplierInfoEntity_HI_RO> ReceivePrice = srmPosSupplierInfoDAO_HI_RO.findList(sumReceivePriceSQL.toString());//查找接收总金额
        if (null == ReceivePrice.get(0).getNonTaxReceivePrice()) {//判断是否为空，空则赋值0
            sumReceivePrice = BigDecimal.valueOf(0);
        } else {
            sumReceivePrice = ReceivePrice.get(0).getNonTaxReceivePrice();
        }

        for (Integer i = 0; i < saafInstitutionEntityList.size(); i++) {//根据每个业务实体查找其订单总金额和接收总金额
            SaafInstitutionEntity_HI_RO k = saafInstitutionEntityList.get(i);
            if (null != k.getInstName() && !"".equals(k.getInstName())) {
                categories[i] = k.getInstName();
                StringBuilder sb = new StringBuilder(" SELECT si.inst_name instName, SUM(spl.demand_qty*spl.non_tax_price) AS nonTaxTotalPrice \n" +
                        " FROM  srm_po_headers sph,srm_po_lines spl,saaf_institution si\n" +
                        " WHERE sph.po_header_id = spl.po_header_id AND sph.org_id = si.inst_id AND si.inst_name = '" + k.getInstName() + "'AND trunc(spl.creation_date) >= to_date('" + startDate + "', 'yyyy-mm-dd') AND trunc(spl.creation_date) < to_date('" + endDate + "', 'yyyy-mm-dd')\n" +
                        " GROUP BY si.inst_name\n");
                StringBuilder sb2 = new StringBuilder(" SELECT si.inst_name instName, SUM(spdl.received_qty*spl.non_tax_price) AS nonTaxReceivePrice \n" +
                        " FROM  srm_po_delivery_lines spdl,srm_po_lines spl,srm_po_delivery_headers spdh,saaf_institution si\n" +
                        " WHERE spdl.po_line_id = spl.po_line_id AND spdh.delivery_header_id = spdl.delivery_header_id AND si.inst_id = spdh.org_id\n" +
                        " AND si.inst_name = '" + k.getInstName() + "' GROUP BY si.inst_name");
                List<SrmPosSupplierInfoEntity_HI_RO> listV = srmPosSupplierInfoDAO_HI_RO.findList(sb.toString());  //查找订单总金额
                List<SrmPosSupplierInfoEntity_HI_RO> listV2 = srmPosSupplierInfoDAO_HI_RO.findList(sb2.toString());  //查找接收总金额
                SrmPosSupplierInfoEntity_HI_RO supplierInfo = new SrmPosSupplierInfoEntity_HI_RO();
                supplierInfo.setInstName(k.getInstName());  //业务实体
                //订单总金额
                if (listV != null && listV.size() > 0 && listV.get(0).getNonTaxTotalPrice() != null) {
                    supplierInfo.setNonTaxTotalPrice(listV.get(0).getNonTaxTotalPrice());
                } else {
                    supplierInfo.setNonTaxTotalPrice(BigDecimal.valueOf(0));
                }
                //接收总金额
                if (listV2 != null && listV2.size() > 0 && listV2.get(0).getNonTaxReceivePrice() != null) {
                    supplierInfo.setNonTaxReceivePrice(listV2.get(0).getNonTaxReceivePrice());
                } else {
                    supplierInfo.setNonTaxReceivePrice(BigDecimal.valueOf(0));
                }

//                if (listV.get(0).getNonTaxTotalPrice() == null && listV2.get(0).getNonTaxReceivePrice() == null) {//判断若都为空，则订单总金额和接收总金额都为0
//                    supplierInfo.setInstName(k.getInstName());//业务实体
//                    supplierInfo.setNonTaxTotalPrice(BigDecimal.valueOf(0));//订单总金额
//                    supplierInfo.setNonTaxReceivePrice(BigDecimal.valueOf(0));//接收总金额
//                } else {
//                    supplierInfo.setInstName(k.getInstName());//业务实体
//                    if (listV.get(0).getNonTaxTotalPrice() == null && listV2.get(0).getNonTaxReceivePrice() != null) {//订单总金额为空而就收总金额不为空
//                        supplierInfo.setNonTaxTotalPrice(BigDecimal.valueOf(0));//订单总金额.
//                        supplierInfo.setNonTaxReceivePrice(listV2.get(0).getNonTaxReceivePrice());//接收总金额
//                    } else if (listV.get(0).getNonTaxTotalPrice() != null && listV2.get(0).getNonTaxReceivePrice() == null) {//订单总金额不为空而就收总金额为空
//                        supplierInfo.setNonTaxTotalPrice(listV.get(0).getNonTaxTotalPrice());//订单总金额
//                        supplierInfo.setNonTaxReceivePrice(BigDecimal.valueOf(0));//接收总金额.
//                    } else {
//                        supplierInfo.setNonTaxTotalPrice(listV.get(0).getNonTaxTotalPrice());//订单总金额
//                        supplierInfo.setNonTaxReceivePrice(listV2.get(0).getNonTaxReceivePrice());//接收总金额
//                    }
//                }

                BigDecimal supplierProportion = null;
                if (null == TotalPrice.get(0).getNonTaxTotalPrice()) {//判断总金额是否为0
                    supplierProportion = BigDecimal.valueOf(0);
                } else {//不为0则作为分母，计算各业务实体的订单金额的百分比
                    supplierProportion = supplierInfo.getNonTaxTotalPrice().divide(sumTotalPrice, 4, BigDecimal.ROUND_HALF_UP);//BigDecimal.ROUND_HALF_UP四舍五入，2.35变成2.4
                }
                supplierInfo.setSupplierProportion(percent.format(supplierProportion));//订单总金额的百分比
                supplierInfoList.add(supplierInfo);
                JSONObject json = new JSONObject();//用来存储一个采购订单总金额的数据列
                json.put("instName", supplierInfo.getInstName());
                json.put("y", supplierInfo.getNonTaxTotalPrice());
                dataArray.add(json);
                JSONObject json2 = new JSONObject();//用来存储一个采购接收总金额的数据列
                json2.put("instName", supplierInfo.getInstName());
                json2.put("y", supplierInfo.getNonTaxReceivePrice());
                dataArray2.add(json2);
            }
        }
        //存储最后一行总计的数据
        SrmPosSupplierInfoEntity_HI_RO supplierInfoLast = new SrmPosSupplierInfoEntity_HI_RO();
        supplierInfoLast.setInstName("总计");
        supplierInfoLast.setNonTaxTotalPrice(sumTotalPrice);
        supplierInfoLast.setNonTaxReceivePrice(sumReceivePrice);
        supplierInfoLast.setSupplierProportion("100%");
        supplierInfoList.add(supplierInfoLast);

        mapV.put("categories", categories);//x轴名称数组
        mapV.put("supplierInfoList", supplierInfoList);
        mapV.put("series", dataArray);
        mapV.put("series2", dataArray2);

        map.put("data", mapV);
        map.put("count", 1);
        map.put("msg", "查询成功");
        map.put("status", "S");
        return map;
    }

    /**
     * 查询订单总金额统计报表详细
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmPosSupplierInfoEntity_HI_RO> findPurchaseAmountCountInfo(JSONObject jsonParams, Integer pageIndex,
                                                                                  Integer pageRows) {
        //查询订单总金额的信息
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosSupplierInfoEntity_HI_RO.QUERY_PURCHASE_TOTAL_PRICE_INFO);
        SaafToolUtils.parperParam(jsonParams, "si.inst_name", "instName", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spsi.supplier_name", "supplierName", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spl.creation_date", "startDateOfTotal", sb, queryParamMap, ">=");
        SaafToolUtils.parperParam(jsonParams, "spl.creation_date", "endDateOfTotal", sb, queryParamMap, "<");
        sb.append(" GROUP BY si.inst_name, spsi.supplier_number, spsi.supplier_name, spsc.license_num, slv.meaning");
        if ("".equals(jsonParams.getString("instName")) || null == jsonParams.getString("instName")) {//如果查询所有业务实体，此时业务实体为空，需要在Group By语句加上si.inst_name
            sb.append(", si.inst_name");
        }
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosSupplierInfoEntity_HI_RO> result = srmPosSupplierInfoDAO_HI_RO
                .findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        //查询接收总金额的信息
        Map<String, Object> queryParamMap2 = new HashMap<String, Object>();
        StringBuffer sb2 = new StringBuffer();
        sb2.append(SrmPosSupplierInfoEntity_HI_RO.QUERY_PURCHASE_RECEIVE_PRICE_INFO);
        SaafToolUtils.parperParam(jsonParams, "si.inst_name", "instName", sb2, queryParamMap2, "=");
        SaafToolUtils.parperParam(jsonParams, "spsi.supplier_name", "supplierName", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spdl.creation_date", "startDateOfReceive", sb2, queryParamMap2, ">=");
        SaafToolUtils.parperParam(jsonParams, "spdl.creation_date", "endDateOfReceive", sb2, queryParamMap2, "<");
        sb2.append(" GROUP BY spsi.supplier_name");
        if ("".equals(jsonParams.getString("instName")) || null == jsonParams.getString("instName")) {//如果查询所有业务实体，此时业务实体为空，需要在Group By语句加上si.inst_name
            sb2.append(", si.inst_name");
        }
        List<SrmPosSupplierInfoEntity_HI_RO> result2 = srmPosSupplierInfoDAO_HI_RO.findList(sb2.toString(), queryParamMap2);
        //根据供应商的信息进行比对，把 result2 中 nonTaxReceivePrice 的信息放入 result 中去，最后返回 result 即可
        for (int i = 0; i < result.getData().size(); i++) {
            for (int j = 0; j < result2.size(); j++) {
                if (result.getData().get(i).getSupplierName().equals(result2.get(j).getSupplierName()) && result.getData().get(i).getInstName().equals(result2.get(j).getInstName())) {//比对业务实体和供应商，都相同则把信息写入到result中
                    result.getData().get(i).setNonTaxReceivePrice(result2.get(j).getNonTaxReceivePrice());
                }
            }
        }

        return result;
    }


    /**
     * 查询供应商列表
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosSupplierInfoEntity_HI_RO> findSupplierList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception  {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer(SrmPosSupplierInfoEntity_HI_RO.QUERY_SUPPLIER_LIST);
        StringBuffer countString = new StringBuffer(SrmPosSupplierInfoEntity_HI_RO.COUNT_SUPPLIER_LIST);
        StringBuffer paramString = new StringBuffer();
        String searchFlag = jsonParams.getString("searchFlag");
        System.out.println("searchFlag:" + searchFlag);
        System.out.println("supplierStatus:" + jsonParams.getString("supplierStatus"));
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_id", "supplierId", paramString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_name", "supplierName", paramString, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_number", "supplierNumber", paramString, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_type", "supplierType", paramString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_status", "supplierStatus", paramString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "psc.license_num", "licenseNum", paramString, queryParamMap, "like");
        //注册时间"从-至"搜索
        String startDate = jsonParams.getString("startDate");
        String endDate = jsonParams.getString("endDate");
        if (startDate != null && endDate == null) {
            paramString.append(" AND trunc(psi.creation_date) >= to_date(:startDate, 'YYYY-MM-DD')\n");
            queryParamMap.put("startDate", startDate);
        } else if (endDate != null && startDate == null) {
            paramString.append(" AND trunc(psi.creation_date) <= to_date(:endDate, 'YYYY-MM-DD')\n");
            queryParamMap.put("endDate", endDate);
        }
        //产品服务查询
        if (jsonParams.getInteger("categoryId") != null) {
            paramString.append("AND EXISTS\n" +
                    "(SELECT\n" +
                    "  1\n" +
                    "FROM\n" +
                    "  srm_pos_supplier_categories psg\n" +
                    "WHERE psg.supplier_id = psi.supplier_id AND psg.category_id = " + jsonParams.getInteger("categoryId") + ")\n");
        }
        //供应商档案查询，过滤状态
        if ("archivesSearch".equals(searchFlag)) {
            paramString.append(" AND psi.supplier_Status IN ('APPROVED','EFFETIVE','INTRODUCING','QUIT')");
        } else if ("auditSearch".equals(searchFlag)) {  //供应商注册审核查询，过滤状态
            paramString.append(" AND psi.supplier_Status IN ('DRAFT','SUBMITTED','REJECTED','APPROVED') AND psi.source_code = 'REGISTER'");
            //供应商注册审核根据供应商类型、人员岗位、人员管理区域指向不同审批人
            int userId = jsonParams.getInteger("varUserId");
            try {
                //获取当前用户的组织信息
                List<SaafUserInstEntity_HI_RO> regionList = iSaafUsersInst.findUsersInstRegion(userId);
                if (null != regionList && regionList.size() > 0 ) {
                    StringBuffer regionStr = new StringBuffer("");
                    for (SaafUserInstEntity_HI_RO region : regionList) {
                        regionStr.append(region.getInstRegion());
                        regionStr.append(",");
                    }
                    regionStr.delete(regionStr.length() - 1, regionStr.length());

                    jsonParams.put("region", regionStr.toString());
                    SaafToolUtils.parperParam(jsonParams, "psi.Region", "region", paramString, queryParamMap, "IN");
                }else {
                    //用户未分配组织则不允许查看
                    paramString.append(" AND 1=2");
                }
                //获取当前用户的切换的职责信息
                Integer currentUserLoginRespId = jsonParams.getJSONObject("currentUserRespList").getJSONObject("undefined").getInteger("responsibilityId");
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("userId", userId);
                userMap.put("responsibilityId", currentUserLoginRespId);
                List<SaafUserMainRespEntity_HI_RO> respList = iSaafUserResp.findSaafUserSupplierRespByUserId(userMap);
                if (null != respList && respList.size() > 0 ) {
                    StringBuffer supplierTypeStr = new StringBuffer("");
                    for (SaafUserMainRespEntity_HI_RO resp : respList) {
                        supplierTypeStr.append(resp.getSupplierType());
                        supplierTypeStr.append(",");
                    }
                    supplierTypeStr.delete(supplierTypeStr.length() - 1, supplierTypeStr.length());

                    jsonParams.put("supplierType", supplierTypeStr.toString());
                    SaafToolUtils.parperParam(jsonParams, "psi.Supplier_Type", "supplierType", paramString, queryParamMap, "IN");
                }else {
                    //用户未分配组织则不允许查看
                    paramString.append(" AND 1=2");
                }
            }catch (Exception e){
                throw new Exception(e);
            }
        } else if ("statusDistribution".equals(searchFlag)) {  //供应商状态分布报表
            if (jsonParams != null && null != jsonParams.getString("supplierStatusV") && !"".equals(jsonParams.getString("supplierStatusV"))) {
                String supplierStatusV = jsonParams.getString("supplierStatusV");  //内部供应商查询范围为拟定和已批准状态
                String[] supplierStatusVList = supplierStatusV.split(",");  //按照“,”分割
                paramString.append(" And (");
                int i = 0;
                for (String status : supplierStatusVList) {
                    if (i != 0) {
                        paramString.append(" or ");
                    }
                    // 改参数方式处理　hanguoqiang 2020/06/23
                    paramString.append("psi.supplier_status = :supplierStatusParam").append(i);
                    queryParamMap.put("supplierStatusParam" + i, status);
                    i++;
                }
                paramString.append(")");
            }
        } else if ("notEngender".equals(searchFlag)) {  //未产生交易数量的报表
            String timeFrame = jsonParams.getString("timeFrame");  //交易周期
            if (null != timeFrame && !"".equals(timeFrame)) {
                //验证字符串是否含有SQL关键字及字符，有则返回NULL
                if (SrmUtils.isContainSQL(timeFrame)) {
                    return null;
                }
                if ("More 2 YEAR".equals(timeFrame)) {//2年以上未交易的供应商
                    paramString.append(
                                    " AND (\n" +
                                    "  EXISTS\n" +
                                    "  (SELECT\n" +
                                    "    sph.supplier_id\n" +
                                    "  FROM\n" +
                                    "    srm_po_headers sph\n" +
                                    "  WHERE sph.supplier_id = psi.supplier_id\n" +
                                    "    AND sph.po_doc_type = 'ORDER'\n" +
                                    "    AND trunc(sph.creation_date) < trunc(add_months(SYSDATE, -24)))\n" +
                                    "  OR NOT EXISTS\n" +
                                    "  (SELECT\n" +
                                    "    sph.supplier_id\n" +
                                    "  FROM\n" +
                                    "    srm_po_headers sph\n" +
                                    "  WHERE sph.supplier_id = psi.supplier_id\n" +
                                    "    AND sph.po_doc_type = 'ORDER')\n" +
                                    ")\n" +
                                    "AND trunc(psi.creation_date) <= trunc(add_months(SYSDATE, -24))");
                } else {
                    paramString.append(
                                    " AND NOT EXISTS\n" +
                                    "(SELECT\n" +
                                    "  sph.supplier_id\n" +
                                    "FROM\n" +
                                    "  srm_po_headers sph\n" +
                                    "WHERE sph.supplier_id = psi.supplier_id\n" +
                                    "  AND sph.po_doc_type = 'ORDER'\n" +
                                    "  AND DATE_SUB(CURDATE(), INTERVAL " + timeFrame + ") <= trunc(sph.creation_date))\n" +
                                    "AND psi.creation_date <= DATE_SUB(CURDATE(), INTERVAL " + timeFrame + ")");
                }
            }
        } else if ("supplierQuit".equals(searchFlag)) {  //供应商退出报表
            if (null != jsonParams.getString("supplierCurrentYear") && !"".equals(jsonParams.getString("supplierCurrentYear"))) {//三年的供应商引入退出
                Date supplierCurrentYearDate = DateUtil.str2Date(jsonParams.getString("supplierCurrentYear"), "yyyy");
                String supplierCurrentYear = DateUtil.date2Str(supplierCurrentYearDate, "yyyy-MM-dd");
                if (null != jsonParams.getString("supplierIntroduceName") && !"".equals(jsonParams.getString("supplierIntroduceName"))) {//供应商引入
                    paramString.append(
                                    " AND EXISTS\n" +
                                    "(SELECT\n" +
                                    "  spqi.supplier_id\n" +
                                    "FROM\n" +
                                    "  (SELECT\n" +
                                    "    tt.qualification_id,\n" +
                                    "    tt.supplier_id,\n" +
                                    "    tt.approve_date,\n" +
                                    "    get_qualification_success_lastFinishDate_f (\n" +
                                    "      tt.qualification_id,\n" +
                                    "      tt.supplier_id,\n" +
                                    "      tt.scene_type\n" +
                                    "    ) AS lastFinishDate\n" +
                                    "  FROM\n" +
                                    "    srm_pos_qualification_info tt\n" +
                                    "  WHERE get_qualification_success_lastFinishDate_f (\n" +
                                    "      tt.qualification_id,\n" +
                                    "      tt.supplier_id,\n" +
                                    "      tt.scene_type\n" +
                                    "    ) IS NOT NULL) spqi\n" +
                                    "WHERE psi.supplier_id = spqi.supplier_id\n" +
                                    "  AND YEAR(spqi.lastFinishDate) = YEAR('" + supplierCurrentYear + "')\n" +
                                    "  AND NOT EXISTS\n" +
                                    "  (SELECT\n" +
                                    "    spqi2.supplier_id\n" +
                                    "  FROM\n" +
                                    "    (SELECT\n" +
                                    "      tt.qualification_id,\n" +
                                    "      tt.supplier_id,\n" +
                                    "      tt.approve_date,\n" +
                                    "      get_qualification_success_lastFinishDate_f (\n" +
                                    "        tt.qualification_id,\n" +
                                    "        tt.supplier_id,\n" +
                                    "        tt.scene_type\n" +
                                    "      ) AS lastFinishDate\n" +
                                    "    FROM\n" +
                                    "      srm_pos_qualification_info tt\n" +
                                    "    WHERE get_qualification_success_lastFinishDate_f (\n" +
                                    "        tt.qualification_id,\n" +
                                    "        tt.supplier_id,\n" +
                                    "        tt.scene_type\n" +
                                    "      ) IS NOT NULL) spqi2\n" +
                                    "  WHERE spqi2.supplier_id = spqi.supplier_id\n" +
                                    "    AND YEAR(spqi2.lastFinishDate) < YEAR('" + supplierCurrentYear + "'))\n" +
                                    "GROUP BY spqi.supplier_id)");
                }
            }
            if (null != jsonParams.getString("supplierYearMonth") && !"".equals(jsonParams.getString("supplierYearMonth"))) {//24个月的供应商的引入
                Date supplierYearMonthDate = DateUtil.str2Date(jsonParams.getString("supplierYearMonth"), "yyyy-MM");
                String supplierYearMonth = DateUtil.date2Str(supplierYearMonthDate, "yyyy-MM-dd");
                //年份的供应商引入，例如查询2018年8月到2018年1月份的数据
                if (null != jsonParams.getInteger("supplierYearCount") && !"".equals(jsonParams.getInteger("supplierYearCount"))) {
                    Integer supplierYearCount = jsonParams.getInteger("supplierYearCount") - 1;
                    paramString.append(
                                    " AND EXISTS\n" +
                                    "(SELECT\n" +
                                    "  spqi.supplier_id\n" +
                                    "FROM\n" +
                                    "  (SELECT\n" +
                                    "    tt.qualification_id,\n" +
                                    "    tt.supplier_id,\n" +
                                    "    tt.approve_date,\n" +
                                    "    get_qualification_success_lastFinishDate_f (\n" +
                                    "      tt.qualification_id,\n" +
                                    "      tt.supplier_id,\n" +
                                    "      tt.scene_type\n" +
                                    "    ) AS lastFinishDate\n" +
                                    "  FROM\n" +
                                    "    srm_pos_qualification_info tt\n" +
                                    "  WHERE get_qualification_success_lastFinishDate_f (\n" +
                                    "      tt.qualification_id,\n" +
                                    "      tt.supplier_id,\n" +
                                    "      tt.scene_type\n" +
                                    "    ) IS NOT NULL) spqi\n" +
                                    "WHERE psi.supplier_id = spqi.supplier_id\n" +
                                    "  AND YEAR(spqi.lastFinishDate) = YEAR(\n" +
                                    "    DATE_SUB('" + supplierYearMonth + "', INTERVAL " + (-supplierYearCount) + " MONTH)\n" +
                                    "  )\n" +
                                    "  AND DATE_FORMAT(spqi.lastFinishDate, '%Y-%m') <= DATE_FORMAT(\n" +
                                    "    DATE_SUB('" + supplierYearMonth + "', INTERVAL " + (-supplierYearCount) + " MONTH),\n" +
                                    "    '%Y-%m'\n" +
                                    "  )\n" +
                                    "  AND NOT EXISTS\n" +
                                    "  (SELECT\n" +
                                    "    spqi2.supplier_id\n" +
                                    "  FROM\n" +
                                    "    (SELECT\n" +
                                    "      tt.qualification_id,\n" +
                                    "      tt.supplier_id,\n" +
                                    "      tt.approve_date,\n" +
                                    "      get_qualification_success_lastFinishDate_f (\n" +
                                    "        tt.qualification_id,\n" +
                                    "        tt.supplier_id,\n" +
                                    "        tt.scene_type\n" +
                                    "      ) AS lastFinishDate\n" +
                                    "    FROM\n" +
                                    "      srm_pos_qualification_info tt\n" +
                                    "    WHERE get_qualification_success_lastFinishDate_f (\n" +
                                    "        tt.qualification_id,\n" +
                                    "        tt.supplier_id,\n" +
                                    "        tt.scene_type\n" +
                                    "      ) IS NOT NULL) spqi2\n" +
                                    "  WHERE spqi2.supplier_id = spqi.supplier_id\n" +
                                    "    AND DATE_FORMAT(spqi2.lastFinishDate, '%Y-%m') < DATE_FORMAT('" + supplierYearMonth + "', '%Y-%m'))\n" +
                                    "GROUP BY spqi.supplier_id)");
                } else {//月份的供应商引入查询
                    paramString.append(
                                    " AND EXISTS\n" +
                                    "(SELECT\n" +
                                    "  spqi.supplier_id\n" +
                                    "FROM\n" +
                                    "  (SELECT\n" +
                                    "    tt.qualification_id,\n" +
                                    "    tt.supplier_id,\n" +
                                    "    tt.approve_date,\n" +
                                    "    get_qualification_success_lastFinishDate_f (\n" +
                                    "      tt.qualification_id,\n" +
                                    "      tt.supplier_id,\n" +
                                    "      tt.scene_type\n" +
                                    "    ) AS lastFinishDate\n" +
                                    "  FROM\n" +
                                    "    srm_pos_qualification_info tt\n" +
                                    "  WHERE get_qualification_success_lastFinishDate_f (\n" +
                                    "      tt.qualification_id,\n" +
                                    "      tt.supplier_id,\n" +
                                    "      tt.scene_type\n" +
                                    "    ) IS NOT NULL) spqi\n" +
                                    "WHERE psi.supplier_id = spqi.supplier_id\n" +
                                    "  AND DATE_FORMAT(spqi.lastFinishDate, '%Y-%m') = DATE_FORMAT(\n" +
                                    "    '" + supplierYearMonth + "',\n" +
                                    "    '%Y-%m'\n" +
                                    "  )\n" +
                                    "  AND NOT EXISTS\n" +
                                    "  (SELECT\n" +
                                    "    spqi2.supplier_id\n" +
                                    "  FROM\n" +
                                    "    (SELECT\n" +
                                    "      tt.qualification_id,\n" +
                                    "      tt.supplier_id,\n" +
                                    "      tt.approve_date,\n" +
                                    "      get_qualification_success_lastFinishDate_f (\n" +
                                    "        tt.qualification_id,\n" +
                                    "        tt.supplier_id,\n" +
                                    "        tt.scene_type\n" +
                                    "      ) AS lastFinishDate\n" +
                                    "    FROM\n" +
                                    "      srm_pos_qualification_info tt\n" +
                                    "    WHERE get_qualification_success_lastFinishDate_f (\n" +
                                    "        tt.qualification_id,\n" +
                                    "        tt.supplier_id,\n" +
                                    "        tt.scene_type\n" +
                                    "      ) IS NOT NULL) spqi2\n" +
                                    "  WHERE spqi2.supplier_id = spqi.supplier_id\n" +
                                    "    AND DATE_FORMAT(spqi2.lastFinishDate, '%Y-%m') < DATE_FORMAT(\n" +
                                    "      '" + supplierYearMonth + "',\n" +
                                    "      '%Y-%m'\n" +
                                    "    ))\n" +
                                    "GROUP BY spqi.supplier_id)");
                }
            }
        } else {
            paramString.append(" AND 1 = 2");
        }
        jsonParams.put("supplierType", getSupplierType(jsonParams.getInteger("varUserId")));
        SaafToolUtils.parperParam(jsonParams, "psi.Supplier_Type", "supplierType", paramString, queryParamMap, "IN");


        queryString.append(paramString);
        queryString.append(" ORDER BY psi.creation_date DESC ");
        countString.append(paramString);
        //会造成分页出错
        //Pagination<SrmPosSupplierInfoEntity_HI_RO> result = srmPosSupplierInfoDAO_HI_RO.findPagination(queryString, countString, queryParamMap, pageIndex, pageRows);
        String countSql = "select count(1) from (" + queryString + ")";
        Pagination<SrmPosSupplierInfoEntity_HI_RO> result = srmPosSupplierInfoDAO_HI_RO.findPagination(queryString, countSql,queryParamMap, pageIndex, pageRows);
        return result;
    }


    private String getSupplierType(Integer userId){
        JSONObject json=new JSONObject();
        json.put("userId",userId);
        List<SrmBaseUserCategoriesEntity_HI_RO> userCategoriesList=srmBaseUserCategories.findUserCategories(json);
        Map map = new HashMap();
        map.put("lookupType", "POS_SUPPLIER_TYPE");
        map.put("enabledFlag", "Y");
        List<SaafLookupValuesEntity_HI> lookupValues = saafLookupValuesDAO_HI.findByProperty(map);
        List  categoryCode=new ArrayList();
        for(SaafLookupValuesEntity_HI vo:lookupValues){
            for(SrmBaseUserCategoriesEntity_HI_RO ro:userCategoriesList){
                if(ro.getCategoryCode().equals(vo.getLookupCode())){
                    categoryCode.add(ro.getCategoryCode());
                }
            }
        }
        String supplierType= String.valueOf(categoryCode.stream().distinct().collect(Collectors.joining(",")));
        return supplierType;
    }


    /**
     * Description：供应商档案信息——发送到EBS系统
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-11-20     zhj             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateSrmPosSupplierInfoSendToEBS(JSONObject jsonParams) throws Exception {
        //用户Id
        Integer userId = jsonParams.getInteger("varUserId");
        Integer supplierId = jsonParams.getInteger("supplierId");
        if(null == supplierId || "".equals(supplierId)){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "参数为空", 0, null);
        }
        JSONObject json = new JSONObject();
        json.put("supplierId",supplierId);
        json.put("varUserId",userId);
        Pagination<SrmPosSupplierInfoEntity_HI_RO> list = findSupplierInfoForSelf(json,1,1);
        if(null == list || null == list.getData() || list.getData().isEmpty()){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "查无此单据，已被删除", 0, null);
        }
        //供应商基本信息
        SrmPosSupplierInfoEntity_HI_RO supplierInfo = list.getData().get(0);
        //供应商地点
        List<SrmPosSupplierSitesEntity_HI_RO> siteList = iSrmPosSupplierSites.findSupplierSites(json);
        if(null == siteList || siteList.isEmpty()){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "该供应商没有地点信息", 0, null);
        }
        //联系人
        List<SrmPosSupplierContactsEntity_HI_RO> contactsList = new ArrayList<>();
        Pagination<SrmPosSupplierContactsEntity_HI_RO> contactsPag = iSrmPosSupplierContacts.findSupplierContacts(json,1,Integer.MAX_VALUE);
        if(null != contactsPag && null != contactsPag.getData() && contactsPag.getData().size()>0){
            contactsList = contactsPag.getData();
        }
        //银行信息
        List<SrmPosSupplierBankEntity_HI_RO> bankList = new ArrayList<>();
        Pagination<SrmPosSupplierBankEntity_HI_RO> bankPag = iSrmPosSupplierBank.findSupplierBankInfo(json,1,Integer.MAX_VALUE);
        if(null != bankPag && null != bankPag.getData() && bankPag.getData().size()>0){
            bankList = bankPag.getData();
        }
        //调用ESB总线接口——供应商档案信息
        JSONObject result = iesbClient.findSupplierInfoSendToEBS(supplierInfo.getSupplierId(),NORMAL,N,supplierInfo,siteList,contactsList,bankList);
        LOGGER.info("供应商档案信息--->EBS的返回数据:{}",result);
        LOGGER.info("返回参数：{}" , result.toJSONString());
        if(null == result || null == result.getString(STATUS) || "".equals(result.getString(STATUS)) || !SUCCESS_STATUS.equals(result.getString(STATUS))){
            LOGGER.error("接口请求失败，错误：{}",result.getString(MSG));
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, result.getString(MSG), 0, null);
        }
        JSONObject data = result.getJSONObject(DATA);
        if(null == data){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "发送EBS失败，没有返回值", 0, null);
        }
        JSONObject obj = data.getJSONObject("obj");
        if(null == obj || "".equals(obj)){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "发送EBS失败，没有返回值"+data.getString(MSG), 0, null);
        }
        JSONObject returnSystemParam = obj.getJSONObject(ESBParams.StatusCode.SYSTEM_PARAMS.getValue());
        if(null != returnSystemParam && null != returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue())
                && ESBParams.StatusCode.ESB_CODE_OK.getValue().equals(returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue()))){
            JSONArray businessDataValue = obj.getJSONArray(ESBParams.StatusCode.BUSINESS_DATA.getValue());
            if(null == businessDataValue || businessDataValue.isEmpty()){
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, "发送EBS失败，错误："+data.getString(MSG), 0, null);
            }
            //同步数据返回的状态码
            String processCode = businessDataValue.getJSONObject(0).getString(ESBParams.StatusCode.PROCESS_CODE.getValue());
            if(null == processCode || "".equals(processCode) || !SUCCESS_STATUS.equals(processCode)){
                String processMsg = businessDataValue.getJSONObject(0).getString(ESBParams.StatusCode.PROCESS_MSG.getValue());
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, "发送EBS失败，错误："+processMsg, 0, null);
            }
            String requestId = result.getString("requestId");
            String vendorNum = businessDataValue.getJSONObject(0).getString("VENDOR_NUM");
            //更新供应商编码
            SrmPosSupplierInfoEntity_HI supplier = srmPosSupplierInfoDAO_HI.getById(supplierId);
            supplier.setRequestId(requestId);
            if(null == supplier.getSupplierEbsNumber() || "".equals(supplier.getSupplierEbsNumber())){
                supplier.setSupplierEbsNumber(vendorNum);
            }
            supplier.setOperatorUserId(userId);
            srmPosSupplierInfoDAO_HI.saveOrUpdate(supplier);
            //更新地点
            List<SrmPosSupplierSitesEntity_HI> sitesEntityList = new ArrayList<>();
            if(null != siteList && siteList.size()>0){
                /*siteList.forEach(k->{
                    SrmPosSupplierSitesEntity_HI site = srmPosSupplierSitesDAO_HI.getById(k.getSupplierSiteId());
                    site.setRequestId(requestId);
                    site.setOperatorUserId(userId);
                    sitesEntityList.add(site);
                });*/
                for(SrmPosSupplierSitesEntity_HI_RO k : siteList){
                    SrmPosSupplierSitesEntity_HI site = srmPosSupplierSitesDAO_HI.getById(k.getSupplierSiteId());
                    site.setRequestId(requestId);
                    site.setOperatorUserId(userId);
                    sitesEntityList.add(site);
                }
                srmPosSupplierSitesDAO_HI.saveOrUpdateAll(sitesEntityList);
            }
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "发送成功", 0, null);
        }else{
            LOGGER.error("发送EBS失败，错误：{}",data.getString(MSG));
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "发送EBS失败，错误："+data.getString(MSG), 0, null);
        }
    }

    /**
     * Description：供应商冻结、恢复、退出——发送到EBS系统
     * @param supplierId  供应商ID
     * @param actionCode  创建和修改业务为NORMAL，冻结和冻结恢复为FREEZE
     * @param freezeRecoveryFlag 是否冻结恢复(Y/N)，Y为冻结恢复
     * @param frozenId srm_pos_frozen_info的主键ID
     * @param userId 当前用户Id
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-12-18     zhj             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateSrmPosSupplierInfoSendToEBSInfo(Integer supplierId,String actionCode,String freezeRecoveryFlag,Integer frozenId,Integer userId) throws Exception {
        JSONObject json = new JSONObject();
        json.put("supplierId",supplierId);
        json.put("varUserId",userId);
        Pagination<SrmPosSupplierInfoEntity_HI_RO> list = findSupplierInfoForSelf(json,1,1);
        if(null == list || null == list.getData() || list.getData().isEmpty()){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "查无此单据，已被删除", 0, null);
        }
        //供应商基本信息
        SrmPosSupplierInfoEntity_HI_RO supplierInfo = list.getData().get(0);
        //联系人
        List<SrmPosSupplierContactsEntity_HI_RO> contactsList = null;
        //银行信息
        List<SrmPosSupplierBankEntity_HI_RO> bankList = null;
        //供应商地点
        List<SrmPosSupplierSitesEntity_HI_RO> siteList = null;
        if(null != actionCode && NORMAL.equals(actionCode)){
            //供应商insert、update
            //供应商退出地点
            json.put("siteStatus","QUIT");
            siteList = iSrmPosSupplierSites.findSupplierSites(json);
            Pagination<SrmPosSupplierContactsEntity_HI_RO> contactsPag = iSrmPosSupplierContacts.findSupplierContacts(json,1,Integer.MAX_VALUE);
            if(null != contactsPag && null != contactsPag.getData() && contactsPag.getData().size()>0){
                //联系人
                contactsList = contactsPag.getData();
            }
            Pagination<SrmPosSupplierBankEntity_HI_RO> bankPag = iSrmPosSupplierBank.findSupplierBankInfo(json,1,Integer.MAX_VALUE);
            if(null != bankPag && null != bankPag.getData() && bankPag.getData().size()>0){
                //银行信息
                bankList = bankPag.getData();
            }
        }/*else if(null != actionCode && FREEZE.equals(actionCode)){
            //地点冻结、冻结恢复
            StringBuffer sb = new StringBuffer(SrmPosSupplierSitesEntity_HI_RO.QUERY_SUPPLIER_SITES);
            sb.append(" and A.supplier_id =").append(supplierId);
            sb.append(" and exists(select fs.supplier_site_id from srm_pos_frozen_sites fs where fs.supplier_site_id=A.SUPPLIER_SITE_ID and fs.frozen_id=").append(frozenId).append(") ");
            siteList = srmPosSupplierSitesDAO_HI_RO.findList(sb.toString());
        }*/
        if(null == siteList || siteList.isEmpty()){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "该供应商没有地点信息", 0, null);
        }
        //调用ESB总线接口——供应商档案信息
        JSONObject result = iesbClient.findSupplierInfoSendToEBS(supplierInfo.getSupplierId(),actionCode,freezeRecoveryFlag,supplierInfo,siteList,contactsList,bankList);
        LOGGER.info("供应商档案信息--->EBS的返回数据:{}",result);
        LOGGER.info("返回参数：{}" , result.toJSONString());
        if(null == result || null == result.getString(STATUS) || "".equals(result.getString(STATUS)) || !SUCCESS_STATUS.equals(result.getString(STATUS))){
            LOGGER.error("接口请求失败，错误：{}",result.getString(MSG));
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, result.getString(MSG), 0, null);
        }
        JSONObject data = result.getJSONObject(DATA);
        if(null == data){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "发送EBS失败，没有返回值", 0, null);
        }
        JSONObject obj = data.getJSONObject("obj");
        if(null == obj || "".equals(obj)){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "发送EBS失败，没有返回值"+data.getString(MSG), 0, null);
        }
        JSONObject returnSystemParam = obj.getJSONObject(ESBParams.StatusCode.SYSTEM_PARAMS.getValue());
        if(null != returnSystemParam && null != returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue())
                && ESBParams.StatusCode.ESB_CODE_OK.getValue().equals(returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue()))){
            JSONArray businessDataValue = obj.getJSONArray(ESBParams.StatusCode.BUSINESS_DATA.getValue());
            if(null == businessDataValue || businessDataValue.isEmpty()){
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, "发送EBS失败，错误："+data.getString(MSG), 0, null);
            }
            //同步数据返回的状态码
            String processCode = businessDataValue.getJSONObject(0).getString(ESBParams.StatusCode.PROCESS_CODE.getValue());
            if(null == processCode || "".equals(processCode) || !SUCCESS_STATUS.equals(processCode)){
                String processMsg = businessDataValue.getJSONObject(0).getString(ESBParams.StatusCode.PROCESS_MSG.getValue());
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, "发送EBS失败，错误："+processMsg, 0, null);
            }
            String requestId = result.getString("requestId");
            String vendorNum = businessDataValue.getJSONObject(0).getString("VENDOR_NUM");
            //更新供应商编码
            SrmPosSupplierInfoEntity_HI supplier = srmPosSupplierInfoDAO_HI.getById(supplierId);
            supplier.setRequestId(requestId);
            if(null == supplier.getSupplierEbsNumber() || "".equals(supplier.getSupplierEbsNumber())){
                supplier.setSupplierEbsNumber(vendorNum);
            }
            supplier.setOperatorUserId(userId);
            srmPosSupplierInfoDAO_HI.saveOrUpdate(supplier);
            //更新地点
            List<SrmPosSupplierSitesEntity_HI> sitesEntityList = new ArrayList<>();
            if(null != siteList && siteList.size()>0){
                /*siteList.forEach(k->{
                    SrmPosSupplierSitesEntity_HI site = srmPosSupplierSitesDAO_HI.getById(k.getSupplierSiteId());
                    site.setRequestId(requestId);
                    site.setOperatorUserId(userId);
                    sitesEntityList.add(site);
                });*/
                for(SrmPosSupplierSitesEntity_HI_RO k : siteList){
                    SrmPosSupplierSitesEntity_HI site = srmPosSupplierSitesDAO_HI.getById(k.getSupplierSiteId());
                    site.setRequestId(requestId);
                    site.setOperatorUserId(userId);
                    sitesEntityList.add(site);
                }
                srmPosSupplierSitesDAO_HI.saveOrUpdateAll(sitesEntityList);
            }
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "发送成功", 0, null);
        }else{
            LOGGER.error("发送EBS失败，错误：{}",data.getString(MSG));
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "发送EBS失败，错误："+data.getString(MSG), 0, null);
        }
    }
}
