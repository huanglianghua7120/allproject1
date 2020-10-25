package saaf.common.fmw.base.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.inter.IESBClient;
import saaf.common.fmw.base.utils.ESBClientUtils;
import saaf.common.fmw.base.utils.annotation.Log;
import saaf.common.fmw.base.utils.enums.ESBParams;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.pon.model.entities.SrmPonPaymentTermNodesEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonPaymentTermsEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static saaf.common.fmw.services.CommonAbstractServices.*;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：ESB总线接口
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019-11-12     zhj             创建
 * ==============================================================================
 */
@Component("esbClientServer")
public class ESBClientServer implements IESBClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ESBClientServer.class);
    private static final String ACTION = "action";
    private static final String SUCCESS = "success";
    //需求描述
    private static final String DEMAND = "DEMAND";
    //主题
    private static final String THEME = "THEME";
    private static final String QUIT = "QUIT";
    private static final String FREEZE_CODE = "FREEZE";
    private static final String NORMAL = "NORMAL";
    private static final String CREATE = "CREATE";
    private static final String UPDATE = "UPDATE";
    private static final String EFFECTIVE_CODE = "EFFECTIVE";
    private static final String Y = "Y";
    private static final String N = "N";
    private static final String EFFECTIVE = "有效";
    private static final String INVALID = "失效";
    private static final String FREEZE = "冻结";
    private static final String FREEZE_RECOVERY = "冻结恢复";
    //EBS的字段分隔符
    private static final String EBS_SEPARATOR_FLAG = "$#$";
    //EBS的行分隔符，多个时用“*$*”隔开
    private static final String EBS_SEPARATOR_LINE_FLAG = "*$*";
    private static final String DRAFT = "DRAFT";
    private static final String FINISH = "FINISH";
    private ESBClientUtils esbClientUtils = new ESBClientUtils();
    @Autowired
    private BaseViewObject<SrmPosSupplierQuitEntity_HI_RO> srmPosSupplierQuitDAO_HI_RO;
    @Autowired
    private ViewObject<SrmPonPaymentTermsEntity_HI> srmPonPaymentTermsDAO_HI;
    @Autowired
    private ViewObject<SrmPonPaymentTermNodesEntity_HI> srmPonPaymentTermNodesDAO_HI;//付款节点
    /*@Autowired
    private ViewObject<SrmPosGoodsCodeHeaderEntity_HI> srmPosGoodsCodeHeaderDAO_HI;
    @Autowired
    private ViewObject<SrmPosGoodsCodeLineEntity_HI> srmPosGoodsCodeLineDAO_HI;*/
    @Autowired
    private ViewObject<SaafEmployeesEntity_HI> saafEmployeesDAO_HI;
    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;
    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    /**
     * Description：根据EKP认证流程单号获取EKP数据
     *
     * @param jsonParams
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-11-12     zhj             创建
     * ==============================================================================
     */
    //@Log(title = "EKP认证流程单号接口", businessType = "根据EKP认证流程单号获取EKP数据")
    @Override
    public JSONObject findEkpNumberInfoByEkpNumber(JSONObject jsonParams) throws Exception {
        //EKP认证流程单号
        String fdNumber = jsonParams.getString("fdNumber");
        if (null == fdNumber || "".equals(fdNumber)) {
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "EKP认证流程单号不可为空", 0, null);
        }
        String action = jsonParams.getString(ACTION);
        if (null == action || "".equals(action)) {
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "参数为空", 0, null);
        }
        JSONArray businessData = new JSONArray();
        JSONObject value = new JSONObject();
        value.put("fdNumber", fdNumber);
        businessData.add(value);
        JSONObject result = null;
        JSONObject baseQueryParams = new JSONObject();
        if (THEME.equals(action)) {
            //主题
            result = esbClientUtils.doPost(ESBParams.SystemName.PCB_SRM.getValue(), ESBParams.SystemName.PCB_SRM.getValue(), ESBParams.SystemName.EKP.getValue(), ESBParams.ServiceName.EKP_REVIEW_MAIN.getValue(), "", "S", baseQueryParams, businessData);
        } else if (DEMAND.equals(action)) {
            //需求描述
            result = esbClientUtils.doPost(ESBParams.SystemName.PCB_SRM.getValue(), ESBParams.SystemName.PCB_SRM.getValue(), ESBParams.SystemName.EKP.getValue(), ESBParams.ServiceName.EKP_MTERIAL_CRTIFICATION.getValue(), "", "S", baseQueryParams, businessData);
        } else {
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "暂无操作", 0, null);
        }
        LOGGER.info("返回参数：{}", result.toJSONString());
        if (null == result || null == result.getString(STATUS) || "".equals(result.getString(STATUS)) || !SUCCESS_STATUS.equals(result.getString(STATUS))) {
            LOGGER.error("接口请求失败，错误：{}", result.getString(MSG));
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, result.getString(MSG), 0, result);
        }
        JSONObject data = result.getJSONObject(DATA);
        if (null != data && null != data.getBoolean(SUCCESS) && data.getBoolean(SUCCESS)) {
            JSONObject obj = data.getJSONObject("obj");
            if (null == obj || "".equals(obj)) {
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, fdNumber + "查无记录", 0, result);
            }
            JSONArray rows = obj.getJSONArray("rows");
            if (null == rows || rows.isEmpty()) {
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, fdNumber + "查无记录", 0, result);
            }
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "查询成功", 0, rows.getJSONObject(0));
        } else {
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, fdNumber + "查无记录", 0, result);
        }
    }

    /**
     * Description：将供应商档案信息（供应商基础信息、地点、银行、联系人）推送到EBS
     *
     * @param supplierId         标识供应商supplierId，用于日志查询供应商
     * @param actionCode         创建和修改业务为NORMAL，冻结和冻结恢复为FREEZE
     * @param freezeRecoveryFlag 是否冻结恢复(Y/N)，Y为冻结恢复
     * @param supplierInfo
     * @param siteList
     * @param contactsList
     * @param bankList
     * @return
     * @throws Exception
     */
    //@Log(title = "供应商档案信息接口", businessType = "将供应商档案信息（供应商基础信息、地点、银行、联系人）推送到EBS")
    @Override
    public JSONObject findSupplierInfoSendToEBS(Integer supplierId, String actionCode, String freezeRecoveryFlag, SrmPosSupplierInfoEntity_HI_RO supplierInfo, List<SrmPosSupplierSitesEntity_HI_RO> siteList, List<SrmPosSupplierContactsEntity_HI_RO> contactsList, List<SrmPosSupplierBankEntity_HI_RO> bankList) throws Exception {
        JSONObject result = null;
        if (null != supplierInfo && !"".equals(supplierInfo) && null != siteList && siteList.size() > 0) {
            //供应商状态
            String supplierStatusFlag = INVALID;
            if (null != supplierInfo.getSupplierStatus() && QUIT.equals(supplierInfo.getSupplierStatus())) {
                //退出
                supplierStatusFlag = INVALID;
            } else {
                supplierStatusFlag = EFFECTIVE;
            }
            //银行信息
            String bankInfo = "" + EBS_SEPARATOR_FLAG;
            //联系人信息
            String contactPersonInfo = "" + EBS_SEPARATOR_FLAG;
            if (null != bankList && bankList.size() > 0) {
                //银行信息
                SrmPosSupplierBankEntity_HI_RO row = null;
                for (int i = 0; i < bankList.size(); i++) {
                    row = bankList.get(i);
                    if (i != 0) {
                        //从第二行算起就添加行分隔符
                        bankInfo += EBS_SEPARATOR_LINE_FLAG;
                    }
                    //状态
                    bankInfo += supplierStatusFlag + EBS_SEPARATOR_FLAG;
                    //银行名称
                    if (null == row.getBankName() || "".equals(row.getBankName())) {
                        bankInfo += "" + EBS_SEPARATOR_FLAG;
                    } else {
                        bankInfo += row.getBankName() + EBS_SEPARATOR_FLAG;
                    }
                    //分行名称
                    if (null == row.getBranchName() || "".equals(row.getBranchName())) {
                        bankInfo += "" + EBS_SEPARATOR_FLAG;
                    } else {
                        bankInfo += row.getBranchName() + EBS_SEPARATOR_FLAG;
                    }
                    //账户名称
                    if (null == row.getBankUserName() || "".equals(row.getBankUserName())) {
                        bankInfo += "" + EBS_SEPARATOR_FLAG;
                    } else {
                        bankInfo += row.getBankUserName() + EBS_SEPARATOR_FLAG;
                    }
                    //账户
                    if (null == row.getBankAccountNumber() || "".equals(row.getBankAccountNumber())) {
                        bankInfo += "" + EBS_SEPARATOR_FLAG;
                    } else {
                        bankInfo += row.getBankAccountNumber() + EBS_SEPARATOR_FLAG;
                    }
                    //IBAN
                    if (null == row.getIbanCode() || "".equals(row.getIbanCode())) {
                        bankInfo += "" + EBS_SEPARATOR_FLAG;
                    } else {
                        bankInfo += row.getIbanCode() + EBS_SEPARATOR_FLAG;
                    }
                    //替换账户名
                    bankInfo += "" + EBS_SEPARATOR_FLAG;
                    //分行编码
                    if (null == row.getSwiftCode() || "".equals(row.getSwiftCode())) {
                        bankInfo += "" + EBS_SEPARATOR_FLAG;
                    } else {
                        bankInfo += row.getSwiftCode() + EBS_SEPARATOR_FLAG;
                    }
                }
            }
            //联系人
            if (null != contactsList && contactsList.size() > 0) {
                SrmPosSupplierContactsEntity_HI_RO row = null;
                for (int i = 0; i < contactsList.size(); i++) {
                    row = contactsList.get(i);
                    if (i != 0) {
                        //从第二行算起就添加行分隔符
                        contactPersonInfo += EBS_SEPARATOR_LINE_FLAG;
                    }
                    //状态
                    contactPersonInfo += supplierStatusFlag + EBS_SEPARATOR_FLAG;
                    //名称
                    if (null == row.getContactName() || "".equals(row.getContactName())) {
                        contactPersonInfo += "" + EBS_SEPARATOR_FLAG;
                    } else {
                        contactPersonInfo += row.getContactName() + EBS_SEPARATOR_FLAG;
                    }
                    //中间名
                    contactPersonInfo += "" + EBS_SEPARATOR_FLAG;
                    //姓氏
                    contactPersonInfo += "" + EBS_SEPARATOR_FLAG;
                    //别名
                    contactPersonInfo += "" + EBS_SEPARATOR_FLAG;
                    //职称
                    if (null == row.getPositionNameStr() || "".equals(row.getPositionNameStr())) {
                        contactPersonInfo += "" + EBS_SEPARATOR_FLAG;
                    } else {
                        contactPersonInfo += row.getPositionNameStr() + EBS_SEPARATOR_FLAG;
                    }
                    //部门
                    contactPersonInfo += "" + EBS_SEPARATOR_FLAG;
                    //电话区号
                    contactPersonInfo += "" + EBS_SEPARATOR_FLAG;
                    //移动电话
                    if (null == row.getMobilePhone() || "".equals(row.getMobilePhone())) {
                        contactPersonInfo += "" + EBS_SEPARATOR_FLAG;
                    } else {
                        contactPersonInfo += row.getMobilePhone() + EBS_SEPARATOR_FLAG;
                    }
                    //备用电话区号
                    contactPersonInfo += "" + EBS_SEPARATOR_FLAG;
                    //固定电话
                    if (null == row.getFixedPhone() || "".equals(row.getFixedPhone())) {
                        contactPersonInfo += "" + EBS_SEPARATOR_FLAG;
                    } else {
                        contactPersonInfo += row.getFixedPhone() + EBS_SEPARATOR_FLAG;
                    }
                    //传真区号
                    contactPersonInfo += "" + EBS_SEPARATOR_FLAG;
                    //传真号码
                    if (null == row.getFaxPhone() || "".equals(row.getFaxPhone())) {
                        contactPersonInfo += "" + EBS_SEPARATOR_FLAG;
                    } else {
                        contactPersonInfo += row.getFaxPhone() + EBS_SEPARATOR_FLAG;
                    }
                    //URL
                    contactPersonInfo += "" + EBS_SEPARATOR_FLAG;
                    //电子邮箱
                    if (null == row.getEmailAddress() || "".equals(row.getEmailAddress())) {
                        contactPersonInfo += "" + EBS_SEPARATOR_FLAG;
                    } else {
                        contactPersonInfo += row.getEmailAddress() + EBS_SEPARATOR_FLAG;
                    }
                    //主键ID
                    contactPersonInfo += row.getSupplierContactId() + EBS_SEPARATOR_FLAG;
                }
            }
            JSONObject baseQueryParams = new JSONObject();
            JSONArray businessData = new JSONArray();
            //地点
            JSONObject obj = null;
            Date endDate = null;
            if (null != supplierInfo.getSupplierStatus() && QUIT.equals(supplierInfo.getSupplierStatus())) {
                //供应商状态判断是否有退出时间
                StringBuffer sb = new StringBuffer("select t.* from srm_pos_supplier_quit t where 1=1 ");
                sb.append(" and t.document_status='APPROVED' and t.supplier_id='").append(supplierInfo.getSupplierId()).append("' ");
                sb.append(" order by t.last_update_date desc ");
                List<SrmPosSupplierQuitEntity_HI_RO> list = srmPosSupplierQuitDAO_HI_RO.findList(sb.toString());
                if (null != list && list.size() > 0) {
                    endDate = list.get(0).getLastUpdateDate();
                }
            }
            for (SrmPosSupplierSitesEntity_HI_RO k : siteList) {
                obj = new JSONObject();
                //基本操作
                obj.put("SOURCE_LINE_ID", k.getSupplierSiteId().toString());
                //operationCode 操作指令：新增=CREATE，更新=UPDATE,针对供应商而言,并非地点的更新与insert
                obj.put("OPERATION_CODE", null == supplierInfo.getSupplierEbsNumber() || "".equals(supplierInfo.getSupplierEbsNumber()) ? CREATE : UPDATE);
                obj.put("ACTION_CODE", actionCode);
                //供应商基本信息
                obj.put("VENDOR_NUM", null == supplierInfo.getSupplierEbsNumber() ? "" : supplierInfo.getSupplierEbsNumber());
                obj.put("VENDOR_NAME", null == supplierInfo.getSupplierName() ? "" : supplierInfo.getSupplierName());
                obj.put("ORG_NAME", null == k.getInstName() ? "" : k.getInstName());
                //地点名称
                obj.put("VENDOR_SITE_CODE", null == k.getSiteName() ? "" : k.getSiteName());
                obj.put("PURCHASING_SITE_FLAG", null != k.getPurchaseFlag() && Y.equals(k.getPurchaseFlag()) ? Y : N);
                obj.put("PAY_SITE_FLAG", null != k.getPaymentFlag() && Y.equals(k.getPaymentFlag()) ? Y : N);
                obj.put("RFQ_ONLY_SITE_FLAG", N);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = "";
                if (!ObjectUtils.isEmpty(k.getInvalidDate())) {
                    date = simpleDateFormat.format(k.getInvalidDate());
                    obj.put("INVALID_DATE", date);
                }

                //地点状态
                if (null != actionCode && NORMAL.equals(actionCode)) {
                    //创建和修改业务为NORMAL
                    if (null == k.getSiteStatus() || "".equals(k.getSiteStatus()) || EFFECTIVE_CODE.equals(k.getSiteStatus())) {
                        obj.put("VENDOR_SITE_STATUS", EFFECTIVE);
                    } else {
                        obj.put("VENDOR_SITE_STATUS", INVALID);
                    }
                    //联系人信息
                    obj.put("CONTACT_PERSON_INFO", contactPersonInfo);
                    //银行信息
                    obj.put("BANK_INFO", bankInfo);
                    obj.put("VENDOR_NAME_ALT", null == supplierInfo.getSupplierShortName() ? "" : supplierInfo.getSupplierShortName());
                    //obj.put("PARENT_VENDOR_NAME",supplierInfo.getParentCompany());
                    obj.put("VENDOR_TYPE", "VENDOR");
                    obj.put("VENDOR_ID", "");
                    obj.put("TAX_REFERENCE", null == supplierInfo.getLicenseNum() ? "" : supplierInfo.getLicenseNum());
                    obj.put("FAX_NUMBER", null == supplierInfo.getCompanyFax() ? "" : supplierInfo.getCompanyFax());
                    //供应商状态
                    obj.put("VENDOR_STATUS", supplierStatusFlag);
                    obj.put("END_DATE_ACTIVE", null == endDate ? "" : endDate);
                    obj.put("VENDOR_CLASS_TYPE", "");
                    //obj.put("TEMPORARY_VENDOR_FLAG",null != supplierInfo.getTemporaryFlag() && Y.equals(supplierInfo.getTemporaryFlag()) ? Y : N);
                    obj.put("TEMPORARY_VENDOR_FLAG", "N");
                    obj.put("PROVINCE", null == k.getProvince() ? "" : k.getProvince());
                    obj.put("COUNTRY", null == k.getCountryName() ? "" : k.getCountryName());
                    obj.put("ADDRESS_LINE1", null == k.getDetailAddress() ? "" : k.getDetailAddress());
                } else if (null != actionCode && FREEZE_CODE.equals(actionCode)) {
                    //冻结和冻结恢复FREEZE
                    if (null != freezeRecoveryFlag && Y.equals(freezeRecoveryFlag)) {
                        //冻结恢复
                        obj.put("VENDOR_SITE_STATUS", FREEZE_RECOVERY);
                    } else {
                        //冻结
                        obj.put("VENDOR_SITE_STATUS", FREEZE);
                    }
                    obj.put("ORG_ID", "");
                }
                businessData.add(obj);
            }
            result = esbClientUtils.doPost(ESBParams.SystemName.PCB_SRM.getValue(), ESBParams.SystemName.SRM_SERVER.getValue(), ESBParams.SystemName.EBS.getValue(), ESBParams.ServiceName.callSrmProcedure.getValue(), ESBParams.BusinessServiceName.REST_SRM_PUSHPOVENDLIST.getValue(), ERROR_STATUS, baseQueryParams, businessData);
        }
        return result;
    }

    /**
     * Description：付款条件定时任务接口list
     *
     * @param businessData
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-12-20     zhj             创建
     * ==============================================================================
     */
    @Override
    public Integer updateSrmPonPaymentTermsListJob(JSONArray businessData) {
        Integer count = 0;
        if (null != businessData && businessData.size() > 0) {
            for (int i = 0; i < businessData.size(); i++) {
                JSONObject obj = businessData.getJSONObject(i);
                SrmPonPaymentTermsEntity_HI row = new SrmPonPaymentTermsEntity_HI();
                List<SrmPonPaymentTermsEntity_HI> list = new ArrayList<>();
                SrmPonPaymentTermNodesEntity_HI line = new SrmPonPaymentTermNodesEntity_HI();
                List<SrmPonPaymentTermNodesEntity_HI> lineList = new ArrayList<>();
                Map<String, Object> map = new HashMap<>();
                try {
                    //头ID
                    String sourceHeaderId = String.valueOf(obj.getInteger("TERM_ID"));
                    //行号
                    String sourceId = String.valueOf(obj.getInteger("LINE_NUM"));
                    String paymentTermName = obj.getString("NAME");

                    Date startDate = null;
                    Date endDate = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (!ObjectUtils.isEmpty(obj.getString("START_DATE_ACTIVE"))) {
                        startDate = sdf.parse(obj.getString("START_DATE_ACTIVE"));
                    }
                    if (!ObjectUtils.isEmpty(obj.getString("END_DATE_ACTIVE"))) {
                        endDate = sdf.parse(obj.getString("END_DATE_ACTIVE"));
                    }
                    if (null == sourceHeaderId || "".equals(sourceHeaderId) || null == paymentTermName || "".equals(paymentTermName)) {
                        continue;
                    }

                    list = srmPonPaymentTermsDAO_HI.findByProperty("sourceId", sourceHeaderId);
                    if (null == list || list.isEmpty()) {
                        row = new SrmPonPaymentTermsEntity_HI();
                        //采用EBS过来的主键ID
                        row.setPaymentTermId(Integer.parseInt(sourceHeaderId));
                        row.setSourceId(sourceHeaderId);
                    } else {
                        row = list.get(0);
                    }
                    row.setPaymentTermName(paymentTermName);
                    if (null == startDate || "".equals(startDate)) {
                        row.setStartDate(startDate);
                    } else {
                        row.setStartDate(startDate);
                    }
                    row.setEndDate(endDate);
                    if (null == row.getSourceCode() || "".equals(row.getSourceCode())) {
                        row.setSourceCode("EBS");
                    }
                    if (null == row.getPaymentTermCode() || "".equals(row.getPaymentTermCode())) {
                        String paymentTermCode = saafSequencesUtil.getDocSequences("srm_pon_payment_terms".toUpperCase(), "FK", 6);
                        row.setPaymentTermCode(paymentTermCode);
                    }
                    row.setOperatorUserId(-1);
                    srmPonPaymentTermsDAO_HI.saveOrUpdate(row);
                    srmPonPaymentTermsDAO_HI.fluch();
                    //行数据
                    map.put("sourceId", sourceId);
                    map.put("sourceHeaderId", sourceHeaderId);
                    lineList = srmPonPaymentTermNodesDAO_HI.findByProperty(map);
                    if (null == lineList || lineList.isEmpty()) {
                        line = new SrmPonPaymentTermNodesEntity_HI();
                        line.setPaymentTermCode(row.getPaymentTermCode());
                    } else {
                        line = lineList.get(0);
                    }
                    line.setLineNumber(Integer.parseInt(sourceId));
                    line.setPaymentProportion(obj.getBigDecimal("DUE_PERCENT"));
                    line.setBiasDays(0);
                    line.setPaymentTermNode(paymentTermName);
                    if (null == line.getSourceCode() || "".equals(line.getSourceCode())) {
                        line.setSourceCode("EBS");
                    }
                    line.setSourceId(sourceId);
                    line.setSourceHeaderId(sourceHeaderId);
                    line.setOperatorUserId(-1);
                    srmPonPaymentTermNodesDAO_HI.saveOrUpdate(line);
                    srmPonPaymentTermNodesDAO_HI.fluch();
                    count++;
                } catch (Exception e) {
                    LOGGER.error("未知错误:{}", e);
                    LOGGER.error("--------------------------->保存付款条件操作失败！参数：{}", obj.toJSONString() + "，异常：{}", e.getMessage());
                    continue;
                }
            }
        }
        return count;
    }

    /**
     * Description：物资编码申请接口定时任务List
     *
     * @param businessData
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-12-30     zhj             创建
     * ==============================================================================
     */
    @Override
    public Integer updateSrmPosGoodsCodeHeaderListJob(JSONArray businessData) {
        Integer count = 0;
        /*if(null != businessData && businessData.size()>0){
            JSONObject obj = null;
            String fdParentId = "";
            String fdId = "";
            String fdNumber = "";
            SrmPosGoodsCodeHeaderEntity_HI header = null;
            List<SrmPosGoodsCodeHeaderEntity_HI> headerList = null;
            SrmPosGoodsCodeLineEntity_HI row = null;
            List<SrmPosGoodsCodeLineEntity_HI> rowList = null;
            //初始化创建人的userId
            Integer userId = -1;
            List<SaafUsersEntity_HI> sysUserList = saafUsersDAO_HI.findByProperty("userName","sysadmin");
            if(null != sysUserList && sysUserList.size()>0){
                userId = sysUserList.get(0).getUserId();
            }
            Integer createdBy = null;
            //查询编码审核员的userId
            List<SaafEmployeesEntity_HI> employeesList = saafEmployeesDAO_HI.findByProperty("quartersCode","CODING_AUDITOR");
            if(null != employeesList && employeesList.size()>0){
                createdBy = employeesList.get(0).getUserId();
            }
            for(int i =0;i<businessData.size();i++){
                obj = businessData.getJSONObject(i);
                fdParentId = obj.getString("fdParentId");
                fdId = obj.getString("fdId");
                fdNumber = obj.getString("fdNumber");
                if(null == fdParentId || "".equals(fdParentId) || null == fdId || "".equals(fdId) || null == fdNumber || "".equals(fdNumber)){
                    continue;
                }
                try{
                    headerList = srmPosGoodsCodeHeaderDAO_HI.findByProperty("sourceId",fdParentId);
                    if(null == headerList || headerList.isEmpty()){
                        header = new SrmPosGoodsCodeHeaderEntity_HI();
                        header.setSourceId(fdParentId);
                    }else{
                        header = headerList.get(0);
                        if(null != header.getGoodsCodeStatus() && FINISH.equals(header.getGoodsCodeStatus())){
                            //已完成的状态不update
                            continue;
                        }
                    }
                    if(null == header.getGoodsCodeNumber() || "".equals(header.getGoodsCodeNumber())){
                        String goodsCodeNumber = saafSequencesUtil.getDocSequences("srm_pos_goods_code_header".toUpperCase(),"BMSQ"+ DateUtil.date2Str(new Date(),"yyyyMMdd"),4);
                        header.setGoodsCodeNumber(goodsCodeNumber);
                    }
                    if(null == header.getGoodsCodeStatus() || "".equals(header.getGoodsCodeStatus())){
                        header.setGoodsCodeStatus(DRAFT);
                    }
                    if(null == header.getSourceCode() || "".equals(header.getSourceCode())){
                        header.setSourceCode("EKP");
                    }
                    header.setEkpNumber(obj.getString("fdNumber"));
                    //主题
                    header.setTheme(obj.getString("docSubject"));
                    //物资描述
                    header.setGoodsDescription(obj.getString("fdDescription"));
                    //起草人
                    header.setDrafterName(obj.getString("docCreator"));
                    header.setOperatorUserId(null == createdBy || "".equals(createdBy) ? userId : createdBy);
                    srmPosGoodsCodeHeaderDAO_HI.saveOrUpdate(header);
                    srmPosGoodsCodeHeaderDAO_HI.fluch();
                    //行数据
                    rowList = srmPosGoodsCodeLineDAO_HI.findByProperty("sourceId",fdId);
                    if(null == rowList || rowList.isEmpty()){
                        row = new SrmPosGoodsCodeLineEntity_HI();
                        row.setSourceId(fdId);
                        row.setSourceHeaderId(fdParentId);
                    }else{
                        row = rowList.get(0);
                    }
                    if(null == row.getSourceCode() || "".equals(row.getSourceCode())){
                        row.setSourceCode("EKP");
                    }
                    if(null == row.getFeedbackStatus() || "".equals(row.getFeedbackStatus())){
                        row.setFeedbackStatus(DRAFT);
                    }
                    row.setGoodsCodeId(header.getGoodsCodeId());
                    row.setModelCode(obj.getString("fdCclppType"));
                    row.setOperatorUserId(null == createdBy || "".equals(createdBy) ? userId : createdBy);
                    srmPosGoodsCodeLineDAO_HI.saveOrUpdate(row);
                    srmPosGoodsCodeLineDAO_HI.fluch();
                    count++;
                }catch (Exception e){
                    LOGGER.error("未知错误:{}", e)();
                    LOGGER.error("--------------------------->操作失败！参数：{}" , obj.toJSONString() + "，异常：{}" , e);
                    continue;
                }
            }
        }*/
        return count;
    }
}
