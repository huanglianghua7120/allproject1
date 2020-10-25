package saaf.common.fmw.pos.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseNotificationsEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafUserEmployeesEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SaafUserInstEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseNotificationsEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafUsersInst;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.message.email.utils.SendMailUtil;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierCertificateEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierContactsEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierCertificateEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierCertificate;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component("srmPosSupplierCertificateServer")
public class SrmPosSupplierCertificateServer implements ISrmPosSupplierCertificate {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierCertificateServer.class);

    @Autowired
    private ViewObject<SrmPosSupplierCertificateEntity_HI> srmPosSupplierCertificateDAO_HI;
    @Autowired
    private BaseViewObject<SrmBaseNotificationsEntity_HI_RO> srmBaseNotificationsDAO_HI_RO;
    @Autowired
    private ViewObject<SrmBaseNotificationsEntity_HI> srmBaseNotificationsDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierContactsEntity_HI> srmPosSupplierContactsDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosSupplierCertificateEntity_HI_RO> srmPosSupplierCertificateDAO_HI_RO;

    private static SendMailUtil sendMailUtil = new SendMailUtil(true);

    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;  //用户DAO
    @Autowired
    private BaseViewObject<SaafUserEmployeesEntity_HI_RO> saafUserEmployeesDAO_HI_RO;
    @Autowired
    private ISaafUsersInst iSaafUsersInst; //用户分配组织关系
    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;

   /* @Value("#{config.warning_day}")
    private Integer warningDay;//预警天数*/

    public SrmPosSupplierCertificateServer() {
        super();
    }

    @Override
    public JSONObject deleteCertificate(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());
        Integer certificateId = jsonParams.getInteger("certificateId");
        if (!(certificateId == null || "".equals(certificateId))) {
            SrmPosSupplierCertificateEntity_HI row = srmPosSupplierCertificateDAO_HI.getById(certificateId);
            if (null != row) {
                srmPosSupplierCertificateDAO_HI.delete(row.getCertificateId());
                return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除失败，无此记录！参数为：" + certificateId, 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "删除失败，参数" + certificateId, 0, null);
        }
    }

    /**
     * 保存供应商的认证与证书（档案自助维护/内部创建供应商）
     *
     * @param certificateList
     * @param userId
     * @param supplierId
     * @return
     */
    @Override
    public boolean saveSrmPosSupplierCertificateInfo(List<SrmPosSupplierCertificateEntity_HI> certificateList, Integer userId, Integer supplierId) {
        boolean flag = false;
        List<SrmPosSupplierCertificateEntity_HI> newCertificateList = new ArrayList<>();
        if (null != certificateList && certificateList.size() > 0) {
            for (SrmPosSupplierCertificateEntity_HI k : certificateList) {
                SrmPosSupplierCertificateEntity_HI findRow = srmPosSupplierCertificateDAO_HI.getById(k.getCertificateId());
                if (null != findRow) {
                    findRow.setCertificateName(k.getCertificateName());
                    findRow.setCertificateNumber(k.getCertificateNumber());
                    findRow.setCertificateDescription(k.getCertificateDescription());
                    findRow.setStartDate(k.getStartDate());
                    findRow.setEndDate(k.getEndDate());
                    findRow.setFileId(k.getFileId());
                    findRow.setSupplierId(supplierId);
                    findRow.setOperatorUserId(userId);
                    newCertificateList.add(findRow);
                } else {
                    k.setSupplierId(supplierId);
                    k.setOperatorUserId(userId);
                    newCertificateList.add(k);
                }
            }
            srmPosSupplierCertificateDAO_HI.saveOrUpdateAll(newCertificateList);
            flag = true;
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 保存供应商的认证与证书——供应商接口（数据输入）
     *
     * @param supplierCertificateInfoList
     * @param userId
     * @param supplierId
     */
    @Override
    public void saveSupplierCertificateInfoListExternal(List<SrmPosSupplierCertificateEntity_HI> supplierCertificateInfoList, Integer userId, Integer supplierId) {
        List<SrmPosSupplierCertificateEntity_HI> newCertificateList = new ArrayList<>();
        if (null != supplierCertificateInfoList && supplierCertificateInfoList.size() > 0) {
            for (SrmPosSupplierCertificateEntity_HI k : supplierCertificateInfoList) {
                SrmPosSupplierCertificateEntity_HI findRow = null;
                JSONObject jsonParams = new JSONObject();
                jsonParams.put("sourceCode", k.getSourceCode());
                jsonParams.put("sourceId", k.getSourceId());
                jsonParams.put("supplierId", supplierId);
                Pagination<SrmPosSupplierCertificateEntity_HI_RO> listPagination = findSupplierCertificate(jsonParams, 1, 1000);
                List<SrmPosSupplierCertificateEntity_HI_RO> listRO = listPagination.getData();
                if (null != listRO && listRO.size() > 0) {
                    SrmPosSupplierCertificateEntity_HI_RO findRO = listRO.get(0);
                    findRow = srmPosSupplierCertificateDAO_HI.getById(findRO.getCertificateId());
                }
                if (null != findRow) {
                    findRow.setCertificateName(k.getCertificateName());
                    findRow.setCertificateNumber(k.getCertificateNumber());
                    findRow.setCertificateDescription(k.getCertificateDescription());
                    findRow.setStartDate(k.getStartDate());
                    findRow.setEndDate(k.getEndDate());
                    findRow.setFileId(k.getFileId());
                    findRow.setSupplierId(supplierId);
                    findRow.setOperatorUserId(userId);
                    newCertificateList.add(findRow);
                } else {
                    k.setSupplierId(supplierId);
                    k.setOperatorUserId(userId);
                    newCertificateList.add(k);
                }
            }
            srmPosSupplierCertificateDAO_HI.saveOrUpdateAll(newCertificateList);
        }
    }

    /**
     * 校验供应商的认证与证书必填项——供应商接口（数据输入）
     *
     * @param supplierCertificateInfoList
     * @return
     */
    @Override
    public String judgeSpaceSupplierCertificate(List<SrmPosSupplierCertificateEntity_HI> supplierCertificateInfoList) {
        String result = "";
        if (null == supplierCertificateInfoList || supplierCertificateInfoList.size() <= 0) {
            return result;
        }
        Integer index = 0;
        HashSet<Object> valid = new HashSet<>();
        for (SrmPosSupplierCertificateEntity_HI k : supplierCertificateInfoList) {
            index++;
            if (null == k.getSourceId() || "".equals(k.getSourceId())) {
                result += "请填写认证与证书" + "第" + index + "行的数据来源Id——sourceId";
                return result;
            }
            if (null == k.getSourceCode() || "".equals(k.getSourceCode())) {
                result += "请填写认证与证书" + "第" + index + "行的数据来源类型Code——sourceCode";
                return result;
            }
            if (null == k.getCertificateName() || "".equals(k.getCertificateName())) {
                result += "请填写认证与证书" + "第" + index + "行的必填项——证书名称";
                return result;
            }
            if (null == k.getCertificateNumber() || "".equals(k.getCertificateNumber())) {
                result += "请填写认证与证书" + "第" + index + "行的必填项——证书编号";
                return result;
            }
            if (null == k.getEndDate() || "".equals(k.getEndDate())) {
                result += "请填写认证与证书" + "第" + index + "行的必填项——失效时间";
                return result;
            }
            valid.add(k.getCertificateName() + k.getCertificateNumber() + k.getEndDate());
        }
        boolean flag = supplierCertificateInfoList.size() != valid.size() ? true : false;
        if (flag) {
            result += "认证与证书的证书名称、证书编号、失效时间，三者不可都相同，请删除重复的行！";
            return result;
        }
        return result;
    }

    /**
     * 查询供应商的认证与证书
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SrmPosSupplierCertificateEntity_HI_RO> findSupplierCertificate(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT t.*,sbrf.file_Name AS fileName,sbrf.access_Path AS filePath FROM srm_pos_supplier_certificate t " +
                "LEFT JOIN saaf_base_result_file sbrf ON sbrf.file_id=t.file_id " +
                "WHERE 1=1 ");
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplier_id", sb, queryParamMap, "=");//如果是供应商查询
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplierId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.certificate_id", "certificateId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.source_code", "sourceCode", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.source_id", "sourceId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosSupplierCertificateEntity_HI_RO> result =
                srmPosSupplierCertificateDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * 资质到期查询
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SrmPosSupplierCertificateEntity_HI_RO> findSupplierCertificateDue(JSONObject jsonParams,
                                                                                        Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap();
        //StringBuffer sql = new StringBuffer(MessageFormat.format(SrmPosSupplierCertificateEntity_HI_RO.CERTIFICATE_DUE_SQL, warningDay));
        StringBuffer sql = new StringBuffer(SrmPosSupplierCertificateEntity_HI_RO.CERTIFICATE_DUE_SQL);
        sql.append(" AND (st.supplierStatus = 'EFFETIVE' OR st.supplierStatus = 'APPROVED' OR st.supplierStatus = 'INTRODUCING' OR st.supplierStatus = 'QUIT') \r\n");
        SaafToolUtils.parperParam(jsonParams, "st.certificateName", "certificateName", sql, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "st.supplierName", "supplierName", sql, queryParamMap, "LIKE");
        //算出当前时间to失效时间之间的间隔天数，之后在拼查询条件
        SaafToolUtils.parperParam(jsonParams, "round(to_number(st.endDate - SYSDATE))", "surplusDays", sql, queryParamMap, "<");
        //格式化Sting串中的{}占位符
        //String queryCondition = MessageFormat.format("IF ((DATEDIFF(st.endDate, CURDATE()) < {0} ), \"Y\", \"N\" )", warningDay);
        //算出当前时间to失效时间之间的间隔天数，之后在拼查询条件
        //SaafToolUtils.parperParam(jsonParams, queryCondition, "earlyWarning", sql, queryParamMap, "=");
        //sql.append(" ORDER BY st.supplierName ");
        //System.out.println(sql);
        String countSql = "select count(1) from (" + sql + ")";
        Pagination<SrmPosSupplierCertificateEntity_HI_RO> result =
                srmPosSupplierCertificateDAO_HI_RO.findPagination(sql.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }
    @Override
    public String saveCreateWarning() throws Exception{
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
        return "预警创建成功！";
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
}
