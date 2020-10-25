package saaf.common.fmw.pos.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseNotificationsEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafUserEmployeesEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SaafUserInstEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseNotificationsEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafBaseResultFile;
import saaf.common.fmw.base.model.inter.ISaafUsersInst;
import saaf.common.fmw.base.model.inter.ISrmBaseNotifications;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.base.utils.ESBClientUtils;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.base.utils.enums.ESBParams;
import saaf.common.fmw.base.ws.service.EKPSyncService;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.message.email.utils.SendMailUtil;
import saaf.common.fmw.pos.model.entities.*;
import saaf.common.fmw.pos.model.entities.readonly.*;
import saaf.common.fmw.pos.model.inter.IQualificationInfo;
import saaf.common.fmw.pos.model.inter.ISrmPosJob;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierCertificate;
import saaf.common.fmw.pos.model.inter.ISupplierInfo;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;
import static saaf.common.fmw.services.CommonAbstractServices.SUCCESS_STATUS;

@Component("srmPosJobServer")
public class SrmPosJobServer implements ISrmPosJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosJobServer.class);

    @Autowired
    private BaseViewObject<SrmPosQualificationInfoEntity_HI_RO> srmPosQualificationInfoDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPosQualificationInfoEntity_HI> srmPosQualificationInfoDAO_HI;

    @Autowired
    private ViewObject<SrmPosQualificationCatesEntity_HI> srmPosQualificationCatesDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierCategoriesEntity_HI> srmPosSupplierCategoriesDAO_HI;

    @Autowired
    private ViewObject<SrmPosQualificationSitesEntity_HI> srmPosQualificationSitesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosSceneManageEntity_HI_RO> srmPosSceneManageDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPosQualificationSitesEntity_HI_RO> srmPosQualificationSitesDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPosQualificationCatesEntity_HI_RO> srmPosQualificationCatesDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPosSupplierSitesEntity_HI> srmPosSupplierSitesDAO_HI;

    @Autowired
    private ISupplierInfo iSupplierInfo;

    @Autowired
    private ISrmBaseNotifications srmBaseNotificationsServer;//系统通知

    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;  //用户DAO
    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;

    @Autowired
    private ViewObject<SrmPosSupplierContactsEntity_HI> srmPosSupplierContactsDAO_HI;

    private static SendMailUtil sendMailUtil = new SendMailUtil(true);
    @Autowired
    private BaseViewObject<SaafUserEmployeesEntity_HI_RO> saafUserEmployeesDAO_HI_RO;
    @Autowired
    private BaseViewObject<SrmBaseNotificationsEntity_HI_RO> srmBaseNotificationsDAO_HI_RO;
    @Autowired
    private ViewObject<SrmBaseNotificationsEntity_HI> srmBaseNotificationsDAO_HI;


    @Autowired
    private BaseViewObject<SrmPosSupplierCertificateEntity_HI_RO> srmPosSupplierCertificateDAO_HI_RO;
    @Autowired
    private ISaafUsersInst iSaafUsersInst; //用户分配组织关系

    @Autowired
    private IQualificationInfo iQualificationInfo; //用户分配组织关系


    public SrmPosJobServer() {
        super();
    }
    /**
     * 根据资质有效时间自动创建预警
     * @param
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-08      谢晓霞            创建
     * ==============================================================================
     */
    //@Transactional(rollbackFor = { Exception.class })
    @Override
    public JSONObject saveCreateWarning() throws Exception{
        try {
            StringBuffer querySB = new StringBuffer(SrmPosSupplierCertificateEntity_HI_RO.QUERY_INVALID_CERTIFICATE_SQL);
            //querySB.append(" AND t.end_date>=DATE_SUB(SYSDATE(),INTERVAL 3 MONTH) ");//排除3个月之前过期的证书，减少查询
            //List<SrmBaseParamsEntity_HI> paramsEntityList = srmBaseParamsDAO_HI.findByProperty("paramCode","EARLY_WARNING_TIME");//提前预警时间设置
        /*if(null != paramsEntityList && paramsEntityList.size()>0){
            SrmBaseParamsEntity_HI paramsEntity = paramsEntityList.get(0);
            if(null != paramsEntity.getParamValue1() && !"".equals(paramsEntity.getParamValue1())){
                querySB.append(" AND (SELECT get_latest_weekday(DATE_SUB(t.end_date, INTERVAL "+paramsEntity.getParamValue1()+")) from DUAL)<=SYSDATE() ");
            }else{
                querySB.append(" AND (SELECT get_latest_weekday(DATE_SUB(t.end_date, INTERVAL 30 DAY)) from DUAL)<=SYSDATE() ");
            }
        }else{
            querySB.append(" AND (SELECT get_latest_weekday(DATE_SUB(t.end_date, INTERVAL 30 DAY)) from DUAL)<=SYSDATE() ");
        }*/
        /*querySB.append(" AND (t.End_Date BETWEEN To_Date(To_Char(SYSDATE\n" +
                "                                          ,'yyyy-mm-dd')\n" +
                "                                  ,'yyyy-mm-dd') AND\n" +
                "       To_Date(To_Char(SYSDATE + 30\n" +
                "                       ,'yyyy-mm-dd')\n" +
                "               ,'yyyy-mm-dd')) ");*/
            querySB.append(" AND (t.End_Date=To_Date(To_Char(SYSDATE + 30, 'yyyy-MM-dd'), 'yyyy-MM-dd')) ");
            Map<String, Object> map = new HashMap<String, Object>();
            List<SrmPosSupplierCertificateEntity_HI_RO> certificateList = srmPosSupplierCertificateDAO_HI_RO.findList(querySB,map);
            if(null != certificateList && certificateList.size()>0){
                for(int i =0; i<certificateList.size(); i++){
                    //供应商预警信息创建
                    StringBuilder sbr = new StringBuilder("SELECT * FROM srm_base_notifications t where t.table_name='srm_pos_supplier_certificate' and t.table_id ="+certificateList.get(i).getCertificateId()+"");
                    sbr.append(" and t.receiver_id = "+certificateList.get(i).getSupplierId()+" ");
                    sbr.append(" and (To_Date(To_Char(t.creation_date, 'yyyy-MM-dd'), 'yyyy-MM-dd') =\n" +
                            "       To_Date(To_Char(SYSDATE, 'yyyy-MM-dd'), 'yyyy-MM-dd')) ");
                    List<SrmBaseNotificationsEntity_HI_RO> checkNotificationsList = srmBaseNotificationsDAO_HI_RO.findList(sbr.toString());
                    if(null == checkNotificationsList || checkNotificationsList.size()<=0){
                        SrmBaseNotificationsEntity_HI newSupplierNotifications = new SrmBaseNotificationsEntity_HI();
                        String zzNumber ="";
                        Date dateZZ = new Date();
                        String dateZZFromate = DateUtils.formatDate(dateZZ, "yyyyMMdd");
                        String zzNumberStr = "ZZ-"+dateZZFromate;
                        StringBuilder sb = new StringBuilder("SELECT MAX(t.notification_code) AS notificationCode FROM srm_base_notifications t ");
                        sb.append(" WHERE t.notification_code LIKE '"+zzNumberStr+"%'");
                        List<SrmBaseNotificationsEntity_HI_RO> notificationsList = srmBaseNotificationsDAO_HI_RO.findList(sb.toString());
                        if(null == notificationsList || notificationsList.size() == 0 || "".equals(notificationsList)){
                            zzNumber = zzNumberStr+"0001";
                        }else{
                            SrmBaseNotificationsEntity_HI_RO notification = notificationsList.get(0);
                            if(null == notification.getNotificationCode() || "".equals(notification.getNotificationCode())){
                                zzNumber = zzNumberStr+"0001";
                            }else{
                                String poNumberV = notification.getNotificationCode();
                                Integer number = Integer.parseInt(poNumberV.substring(poNumberV.length()-4));
                                number++;
                                String newPoNumber = StringUtils.leftPad(number.toString(),4,"0");
                                zzNumber = poNumberV.substring(0,poNumberV.length()-4)+newPoNumber;
                            }
                        }
                        newSupplierNotifications.setNotificationCode(zzNumber);
                        newSupplierNotifications.setNotificationStatus("1");
                        newSupplierNotifications.setNotificationType("31");
                        newSupplierNotifications.setMenuType("供应商资质到期预警");
                        newSupplierNotifications.setNotificationContent("您好！贵公司的"+certificateList.get(i).getCertificateName()+"资质还剩30天到期，请尽快提供新的资质资料，谢谢！");
                        newSupplierNotifications.setReceiverId(certificateList.get(i).getSupplierId());
                        newSupplierNotifications.setViewedFlag("N");
                        newSupplierNotifications.setTableName("srm_pos_supplier_certificate");
                        newSupplierNotifications.setTableId(certificateList.get(i).getCertificateId());
                        newSupplierNotifications.setTableIdName("credentialsId");
                        newSupplierNotifications.setOperatorUserId(-1);
                        srmBaseNotificationsDAO_HI.save(newSupplierNotifications);

                        //发送邮件-联系人
                        List<SrmPosSupplierContactsEntity_HI> contactsList = srmPosSupplierContactsDAO_HI.findByProperty("supplierId", certificateList.get(i).getSupplierId());
                        if (contactsList != null && contactsList.size() > 0) {
                            String title = "供应商资质到期预警";
                            String content = "<p>您好！</p><br/>贵公司的"+certificateList.get(i).getCertificateName()+"资质还剩30天到期，请尽快提供新的资质资料，谢谢！";
                            sendEmailToContacts(contactsList, title, content);
                        }
                        List<SaafUsersEntity_HI> usersList = saafUsersDAO_HI.findByProperty("supplierId", certificateList.get(i).getSupplierId());

                        //发送邮件-供应商管理员
                        List<SaafUserEmployeesEntity_HI_RO> userList=findUserToEmail(usersList.get(0).getUserId(),certificateList.get(i).getRegion(),certificateList.get(i).getSupplierType());
                        //发邮件
                        String title = "供应商资质到期预警";
                        String content = "<p>您好！<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp"+certificateList.get(i).getSupplierName()+"公司的"+certificateList.get(i).getCertificateName()+"资质还剩30天到期，请尽快联系供应商处理！</p>";
                        sendEmailToRes(userList, title, content);
                    }
                }
            }
            return SToolUtils.convertResultJSONObj("S", "创建预警成功", 0, null);
        }catch (Exception e){
            //手动回滚事务
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOGGER.error("未知错误:{}", e);
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"操作失败"+e.getMessage(),0,null);
        }

    }
    private void sendEmailToContacts(final List<SrmPosSupplierContactsEntity_HI> contactList, final String title, final String content) {
        ArrayList<String> emailAddressList = new ArrayList<>();
        for (SrmPosSupplierContactsEntity_HI contact : contactList) {
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

    public List<SaafUserEmployeesEntity_HI_RO> findUserToEmail(Integer userId, String region, String supplierType) throws Exception  {
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
     * 保存ekpStatus
     *
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-05-24     谢晓霞             创建
     * =======================================================================
     */
    //@Transactional(rollbackFor = { Exception.class })
    @Override
    public JSONObject saveEkpStatus() throws Exception {
        try{
            StringBuffer sql = new StringBuffer(SrmPosQualificationInfoEntity_HI_RO.QUERY_QUALIFICATION_SITE_FROMEKP);
            //sql.append(" and spqi.qualification_number='YR-20200706-0002' ");
            List<SrmPosQualificationInfoEntity_HI_RO> list = srmPosQualificationInfoDAO_HI_RO.findList(sql.toString());
            if(null != list && list.size()>0){

                ESBClientUtils esb = new ESBClientUtils();
                JSONObject baseQueryParams = new JSONObject();
                JSONArray businessData = new JSONArray();
                for (SrmPosQualificationInfoEntity_HI_RO ro : list) {
                    JSONObject businessJson = new JSONObject();
                    businessJson.put("fdId",ro.getAttribute1());
                    businessData.add(businessJson);
                }
                JSONObject result = esb.doPost(ESBParams.SystemName.PCB_SRM.getValue(), ESBParams.SystemName.SRM_SERVER.getValue(),
                        ESBParams.SystemName.EKP.getValue(), ESBParams.ServiceName.EKP_REVIEW_MAIN.getValue(),
                        null,"S",baseQueryParams,businessData);
                LOGGER.info("----------------------EKP查询返回结果: " + result);

                if(SUCCESS_STATUS.equalsIgnoreCase(result.getString("status"))) {
                    if (null != result.getJSONObject("data")) {
                        JSONObject data = result.getJSONObject("data");
                        if (null != data.getJSONObject("obj")) {
                            JSONObject obj = data.getJSONObject("obj");
                            if (null != obj.getJSONArray("rows") && obj.getJSONArray("rows").size() > 0) {
                                JSONArray rows = obj.getJSONArray("rows");
                                LOGGER.info("----------------------供应商引入EKP审批,返回结果: " + rows);
                                for (int i = 0 ; i < rows.size(); i++) {
                                    JSONObject row = rows.getJSONObject(i);
                                    List<SrmPosQualificationInfoEntity_HI> rowList = srmPosQualificationInfoDAO_HI.findByProperty("attribute1", row.getString("fdId"));
                                    if(null!=rowList && rowList.size()>0) {
                                        SrmPosQualificationInfoEntity_HI qualInfo = rowList.get(0);
                                        if(!"10".equals(row.getString("docStatus"))){
                                            JSONObject params = new JSONObject();
                                            JSONObject responseJson = new JSONObject();
                                            params.put("qualificationId",qualInfo.getQualificationId());
                                            params.put("varUserId",-999);
                                            if("30".equals(row.getString("docStatus"))){
                                                responseJson = iQualificationInfo.updateApprovalQualification(params);
                                            }else if("11".equals(row.getString("docStatus"))&&!"REJECTED".equals(qualInfo.getQualificationStatus())){
                                                responseJson = iQualificationInfo.updateRejectQualification(params);
                                            }else if("00".equals(row.getString("docStatus"))){
                                                responseJson = iQualificationInfo.updateAbandonedQualification(params);
                                            }else if("20".equals(row.getString("docStatus"))&&"REJECTED".equals(qualInfo.getQualificationStatus())){
                                                responseJson = iQualificationInfo.updateSubmittdQualification(params);
                                            }
                                            if("S".equals(responseJson.getString("status"))){
                                                qualInfo.setAttribute2(row.getString("fdNumber"));
                                                qualInfo.setOperatorUserId(-999);
                                                srmPosQualificationInfoDAO_HI.saveEntity(qualInfo);
                                            }
                                        }
                                    }
                                }
                            } else {
                                return SToolUtils.convertResultJSONObj("E", "供应商引入结果查询EKP接口返回结果rows不能为空, 返回结果: " + result, 1, null);
                                //throw new UtilsException("供应商引入结果查询EKP接口返回结果rows不能为空, 返回结果: " + result);
                            }
                        } else {
                            return SToolUtils.convertResultJSONObj("E", "供应商引入结果查询EKP接口返回结果obj为空, 返回结果: " + result, 1, null);
                            //throw new UtilsException("供应商引入结果查询EKP接口返回结果obj为空, 返回结果: " + result);
                        }
                    } else {
                        return SToolUtils.convertResultJSONObj("E", "供应商引入结果查询SRM调用EKP接口srm工具类返回结果data为空, 返回结果: " + result, 1, null);
                        //throw new UtilsException("供应商引入结果查询SRM调用EKP接口srm工具类返回结果data为空, 返回结果: " + result);
                    }
                } else {
                    return SToolUtils.convertResultJSONObj("E", "供应商引入结果查询EKP接口返回结果不为S, 返回结果: " + result, 1, null);
                    //throw new UtilsException("供应商引入结果查询EKP接口返回结果不为S, 返回结果: " + result);
                }
            }
            return SToolUtils.convertResultJSONObj("E", "供应商引入结果查询EKP接口成功" , 1, null);
        }catch (Exception e){
            //手动回滚事务
           // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOGGER.error("未知错误:{}", e);
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"操作失败"+e.getMessage(),0,null);
        }

    }

}
