package saaf.common.fmw.pos.model.inter.server;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafResponsibilitysEntity_HI;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafUserEmployeesEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SaafUserInstEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafUsersInst;
import saaf.common.fmw.base.model.inter.ISrmBaseNotifications;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.base.utils.StringUtils;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.message.email.utils.SendMailUtil;
import saaf.common.fmw.pos.model.entities.*;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosChangeCredentialsEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosChangeInfoEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierInfoEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISupplierInfo;
import saaf.common.fmw.pos.model.inter.ISupplierInfoUpdate;
import saaf.common.fmw.utils.SrmUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static saaf.common.fmw.services.CommonAbstractServices.MSG;
import static saaf.common.fmw.services.CommonAbstractServices.STATUS;
import static saaf.common.fmw.services.CommonAbstractServices.SUCCESS_STATUS;

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
@Component("supplierInfoUpdateServer")
public class SupplierInfoUpdateServer implements ISupplierInfoUpdate {

    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierInfoUpdateServer.class);

    private static final String EXECUTE_SUCCESS = "S";

    private static final String EXECUTE_FAIL = "F";

    @Autowired
    private BaseViewObject<SrmPosChangeInfoEntity_HI_RO> srmPosChangeInfoDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPosChangeCredentialsEntity_HI_RO> srmPosChangeCredentialsDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPosChangeInfoEntity_HI> srmPosChangeInfoDAO_HI;

    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersEntity_HI;

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
    private ViewObject<SrmPosSupplierAddressesEntity_HI> srmPosSupplierAddressesDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeBaseEntity_HI> srmPosChangeBaseDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeCredentialsEntity_HI> srmPosChangeCredentialsDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeCategoriesEntity_HI> srmPosChangeCategoriesDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeCertificateAfEntity_HI> srmPosChangeCertificateAfDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeCertificateBfEntity_HI> srmPosChangeCertificateBfDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeContactAfEntity_HI> srmPosChangeContactAfDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeContactBfEntity_HI> srmPosChangeContactBfDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeAddressAfEntity_HI> srmPosChangeAddressAfDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeAddressBfEntity_HI> srmPosChangeAddressBfDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeBankAfEntity_HI> srmPosChangeBankAfDAO_HI;

    @Autowired
    private ViewObject<SrmPosChangeBankBfEntity_HI> srmPosChangeBankBfDAO_HI;

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private ISrmBaseNotifications iSrmBaseNotifications;//系统通知

    @Autowired
    private ViewObject<SaafResponsibilitysEntity_HI> saafResponsibilitysDAO_HI;//职责

    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;  //用户DAO

    @Autowired
    private ISupplierInfo iSupplierInfo;//供应商server

    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值
    @Autowired
    private ISaafUsersInst iSaafUsersInst; //用户分配组织关系
    @Autowired
    private BaseViewObject<SaafUserEmployeesEntity_HI_RO> saafUserEmployeesDAO_HI_RO;

    private static SendMailUtil sendMailUtil = new SendMailUtil(true);

    /**
     * 查询变更头信息
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findSupplierInfoUpdate(JSONObject jsonParams, Integer pageIndex,
                                                                           Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer(SrmPosChangeInfoEntity_HI_RO.QUERY_CHANGE_INFO_LIST);
        //StringBuffer countString = new StringBuffer(SrmPosChangeInfoEntity_HI_RO.COUNT_CHANGE_INFO_LIST);
        StringBuffer queryParam = new StringBuffer();
        String platformCode = jsonParams.getString("varPlatformCode");

        SaafToolUtils.parperParam(jsonParams, "pci.change_id", "changeId", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "pci.supplier_id", "supplier_id", queryParam, queryParamMap, "=");//如果是供应商查询
        SaafToolUtils.parperParam(jsonParams, "pci.supplier_id", "supplierId", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "pci.change_number", "changeNumber", queryParam, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_name", "supplierName", queryParam, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "date_format(pci.creation_date,'%Y-%m-%d')", "creationDateFrom", queryParam, queryParamMap, ">=");
        SaafToolUtils.parperParam(jsonParams, "date_format(pci.creation_date,'%Y-%m-%d')", "creationDateTo", queryParam, queryParamMap, "<=");
        SaafToolUtils.parperParam(jsonParams, "pci.change_status", "changeStatus", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "su.user_full_name", "createdByName", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "pcc.license_num_af", "licenseNumAf", queryParam, queryParamMap, "like");
        queryParam.append(queryParam);
        if ("SAAF".equals(platformCode)) {   //如果是内部平台，筛选掉外部平台拟定的数据
            queryParam.append(" AND (pci.source_type = 'SAAF' OR pci.change_status != 'NEW')");
        }
        //countString.append(queryParam);

       if("Y".equals(jsonParams.getString("isPo"))){
           queryString.append(" AND pci.Supplier_Id IN\n" +
                   " (SELECT Psi.Supplier_Id\n" +
                   "          FROM Srm_Pos_Supplier_Info Psi\n" +
                   "         WHERE Psi.Supplier_Status IN ('APPROVED'\n" +
                   "                                      ,'EFFETIVE'\n" +
                   "                                      ,'INTRODUCING'\n" +
                   "                                      ,'QUIT')\n" +
                   "           AND Psi.Supplier_Type IN (" + getSupplierType(jsonParams.getInteger("varUserId")) +"))");
       }
        queryParam.append(" ORDER BY pci.creation_date DESC ");
        queryString.append(queryParam);
        String countSql = "select count(1) from (" + queryString + ")";
        //会造成分页错误
        //Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(queryString, countString, queryParamMap, pageIndex, pageRows);
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO
                .findPagination(queryString,countSql, queryParamMap, pageIndex, pageRows);
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
        String supplierType= String.valueOf(categoryCode.stream().distinct().collect(Collectors.joining("','")));
        supplierType="'"+supplierType+"'";
        return supplierType;
    }

    /**
     * 查询变更头信息-门户
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findDoorSupplierInfoUpdate(JSONObject jsonParams, Integer pageIndex,
                                                                               Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer(SrmPosChangeInfoEntity_HI_RO.QUERY_CHANGE_INFO_LIST);
        //StringBuffer countString = new StringBuffer(SrmPosChangeInfoEntity_HI_RO.COUNT_CHANGE_INFO_LIST);
        StringBuffer queryParam = new StringBuffer();

        int supplierId = jsonParams.getIntValue("varSupplierId");
        queryParamMap.put("supplierId", supplierId);
        SaafToolUtils.parperParam(jsonParams, "pci.change_id", "changeId", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "pci.change_number", "changeNumber", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_name", "supplierName", queryParam, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "to_char(pci.creation_date,'yyyy-mm-dd hh24:mi:ss')", "creationDateFrom", queryParam, queryParamMap, ">=");
        SaafToolUtils.parperParam(jsonParams, "to_char(pci.creation_date,'yyyy-mm-dd hh24:mi:ss')", "creationDateTo", queryParam, queryParamMap, "<=");
        SaafToolUtils.parperParam(jsonParams, "pci.change_status", "changeStatus", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "su.user_full_name", "createdByName", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "pcc.license_num_af", "licenseNumAf", queryParam, queryParamMap, "=");
        queryParam.append(" AND pci.supplier_id = :supplierId\n");

        //countString.append(queryParam);
        queryParam.append(" ORDER BY pci.creation_date DESC ");
        queryString.append(queryParam);
        String countSql = "select count(1) from (" + queryString + ")";
        //分页出现问题
        //Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(queryString, countString, queryParamMap, pageIndex, pageRows);
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(queryString,countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }
    /**
     * 查询变更合格证书
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findBaseInfoUpdate(JSONObject jsonParams, Integer pageIndex,
                                                                       Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_CHANGE_BASE_INFO);
        SaafToolUtils.parperParam(jsonParams, "spcb.change_id", "changeId", sb, queryParamMap, "=");
        System.out.println("----------------" + sb.toString());
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(), countSql,queryParamMap, pageIndex, pageRows);
        System.out.println("----------------");
        return result;
    }
    /**
     * 查询变更品类
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeCategory(JSONObject jsonParams, Integer pageIndex,
                                                                       Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_CHANGE_CATEGORY);
        SaafToolUtils.parperParam(jsonParams, "spsc.change_id", "changeId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }
    /**
     * 查询变更合格证书
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeCertificate(JSONObject jsonParams, Integer pageIndex,
                                                                          Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_CHANGE_CERTIFICATE);
        SaafToolUtils.parperParam(jsonParams, "spsc.change_id", "changeId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }
    /**
     * 查询变更证书前
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeCertificateBf(JSONObject jsonParams, Integer pageIndex,
                                                                            Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_CHANGE_CERTIFICATE_BF);
        SaafToolUtils.parperParam(jsonParams, "spsc.change_id", "changeId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(), countSql,queryParamMap, pageIndex, pageRows);
        return result;
    }
    /**
     * 查询变更证书后
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeCertificateAf(JSONObject jsonParams, Integer pageIndex,
                                                                            Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_CHANGE_CERTIFICATE_AF);
        SaafToolUtils.parperParam(jsonParams, "spsc.change_id", "changeId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }
    /**
     * 查询变更银行前
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findOldBankInfo(JSONObject jsonParams, Integer pageIndex,
                                                                    Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_SUPPLIER_BANK);
        SaafToolUtils.parperParam(jsonParams, "spsb.supplier_id", "supplier_id", sb, queryParamMap, "=");//如果是供应商查询
        SaafToolUtils.parperParam(jsonParams, "spsb.supplier_id", "supplierId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }
    /**
     * 查询变更联银行前
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeBankBfInfo(JSONObject jsonParams, Integer pageIndex,
                                                                         Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_CHANGE_BANK_BF);
        SaafToolUtils.parperParam(jsonParams, "spsb.change_id", "changeId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }
    /**
     * 查询变更联银行后
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeBankAfInfo(JSONObject jsonParams, Integer pageIndex,
                                                                         Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_CHANGE_BANK_AF);
        SaafToolUtils.parperParam(jsonParams, "spsb.change_id", "changeId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }
    /**
     * 查询变更联银行
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeBankInfo(JSONObject jsonParams, Integer pageIndex,
                                                                       Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_CHANGE_BANK);
        SaafToolUtils.parperParam(jsonParams, "spsb.change_id", "changeId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }
    /**
     * 查询变更联系方式
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeContacts(JSONObject jsonParams, Integer pageIndex,
                                                                       Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_CHANGE_CONTACTS);
        SaafToolUtils.parperParam(jsonParams, "spsc.change_id", "changeId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }
    /**
     * 查询变更联系方式前
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeContactsBf(JSONObject jsonParams, Integer pageIndex,
                                                                         Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_CHANGE_CONTACTS_BF);
        SaafToolUtils.parperParam(jsonParams, "spsc.change_id", "changeId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * 查询变更联系方式后
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeContactsAf(JSONObject jsonParams, Integer pageIndex,
                                                                         Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_CHANGE_CONTACTS_AF);
        SaafToolUtils.parperParam(jsonParams, "spsc.change_id", "changeId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }
    /**
     * 查询地址前
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findAddressesBf(JSONObject jsonParams, Integer pageIndex,
                                                                    Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_CHANGE_ADDRESS_BF);
        SaafToolUtils.parperParam(jsonParams, "spsa.change_id", "changeId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }
    /**
     * 查询地址后
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findAddressesAf(JSONObject jsonParams, Integer pageIndex,
                                                                    Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_CHANGE_ADDRESS_AF);
        SaafToolUtils.parperParam(jsonParams, "spsa.change_id", "changeId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
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

    public JSONObject saveChangeInfo(JSONObject jsonParams) throws Exception {
        JSONObject json = new JSONObject();
        Integer supplierId = jsonParams.getInteger("supplierId");
        if (supplierId == null || supplierId.intValue() <= 0) {
            return SToolUtils.convertResultJSONObj("E", "参数缺失！", 1, json);
        }
        //校验变更后的供应商名称是否已存在
        List<SrmPosChangeInfoEntity_HI_RO> supplierInfoList = checkSupplierName(supplierId, jsonParams.getString("supplierNameAfter").trim());
        if (supplierInfoList!=null&&supplierInfoList.size() > 0) {
            return SToolUtils.convertResultJSONObj("E", "变更后的供应商名称，在档案管理或变更单据中已存在！", 1, json);
        }

        //校验资质信息营业执照号是否已存在
        List<SrmPosChangeCredentialsEntity_HI_RO> changeCredentialsList = checkLicenseNum(supplierId, jsonParams.getString("licenseNumAfter").trim());
        if (changeCredentialsList!=null&&changeCredentialsList.size() > 0) {
            return SToolUtils.convertResultJSONObj("E", "变更后的营业执照号，在档案管理或变更单据中已存在！", 1, json);
        }

        String msg = "";
        if ("submit".equals(jsonParams.getString("action"))) {
            msg = "提交成功！";
        } else if ("save".equals(jsonParams.getString("action"))) {
            msg = "保存成功！";
        }
        int userId = jsonParams.getInteger("varUserId");
        SrmPosChangeInfoEntity_HI row = null;
        if (null == jsonParams.getInteger("changeId")) {
            row = new SrmPosChangeInfoEntity_HI();
            row.setChangeStatus("NEW");
            row.setChangeNumber(saafSequencesUtil.getDocSequences("srm_pos_change_info".toUpperCase(), "ZLBG-" + DateUtil.date2Str(new Date(), "yyyyMMdd"), 4));
            row.setSupplierId(jsonParams.getInteger("supplierId"));
            row.setSourceType(jsonParams.getString("varPlatformCode"));
        } else {
            row = srmPosChangeInfoDAO_HI.getById(jsonParams.getInteger("changeId"));
        }
        row.setChangeReason(jsonParams.getString("changeReason"));
        row.setApprovalUserId(jsonParams.getInteger("approvalUserId"));
        row.setApprovalDate(jsonParams.getDate("approvalDate"));
        row.setApprovalOpinion(jsonParams.getString("approvalOpinion"));
        //row.setDescription(jsonParams.getString("description"));
        row.setFileId(jsonParams.getInteger("fileId"));
        if ("submit".equals(jsonParams.getString("action"))) {
            row.setChangeStatus("APPROVING");
        }
        row.setOperatorUserId(userId);
        srmPosChangeInfoDAO_HI.saveEntity(row);
        //保存变更基础信息
        SrmPosChangeBaseEntity_HI base = null;
        if (null == jsonParams.getInteger("changeBaseId")) {
            base = new SrmPosChangeBaseEntity_HI();
            base.setChangeId(row.getChangeId());
            base.setAcctCheckStaffBf(jsonParams.getString("acctCheckStaff"));
            base.setAcctCheckTypeBf(jsonParams.getString("acctCheckType"));
            base.setCompanyDescriptionBf(jsonParams.getString("companyDescription"));
            base.setCompanyFaxBf(jsonParams.getString("companyFax"));
            base.setCompanyPhoneBf(jsonParams.getString("companyPhone"));
            base.setFinClassifyBf(jsonParams.getString("finClassify"));
            base.setPaymentFlagBf(jsonParams.getString("paymentFlag"));
            base.setPurchaseFlagBf(jsonParams.getString("purchaseFlag"));
            base.setSrmDeliveryBf(jsonParams.getString("srmDelivery"));
            base.setSettleAcctTypeBf(jsonParams.getString("settleAcctType"));
            base.setPosAcctConditionBf(jsonParams.getString("posAcctCondition"));
            base.setPosTaxBf(jsonParams.getString("posTax"));
            base.setStaffNumBf(jsonParams.getInteger("staffNum"));
            base.setSupplierClassifyBf(jsonParams.getString("supplierClassify"));
            base.setSupplierFileIdBf(jsonParams.getInteger("supplierFileId"));
            base.setSupplierIndustryBf(jsonParams.getString("supplierIndustry"));
            base.setSupplierNameBf(jsonParams.getString("supplierName"));
            base.setSupplierShortNameBf(jsonParams.getString("supplierShortName"));
            base.setSupplierTypeBf(jsonParams.getString("supplierType"));
            base.setAbleCheckOrderFlagBf(jsonParams.getString("ableCheckOrderFlag"));
            base.setAddressBf(jsonParams.getString("address"));
            base.setCompanyRegisteredAddressBf(jsonParams.getString("companyRegisteredAddress"));
            base.setRegisteredCapitalBf(jsonParams.getString("registeredCapital"));
            base.setIndLegalPersonFlagBf(jsonParams.getString("indLegalPersonFlag"));
            base.setValueAddedTaxInvFlagBf(jsonParams.getString("valueAddedTaxInvoiceFlag"));
            base.setValueAddedTaxInvDescBf(jsonParams.getString("valueAddedTaxInvoiceDesc"));
            base.setAssociatedCompanyBf(jsonParams.getString("associatedCompany"));
            base.setRegionBf(jsonParams.getString("region"));
        } else {
            base = srmPosChangeBaseDAO_HI.getById(jsonParams.getInteger("changeBaseId"));
        }
        base.setAcctCheckStaffAf(jsonParams.getString("acctCheckStaffAfter"));
        base.setAcctCheckTypeAf(jsonParams.getString("acctCheckTypeAfter"));
        base.setCompanyDescription(jsonParams.getString("companyDescriptionAfter"));
        base.setCompanyFaxAf(jsonParams.getString("companyFaxAfter"));
        base.setCompanyPhoneAf(jsonParams.getString("companyPhoneAfter"));
        base.setFinClassifyAf(jsonParams.getString("finClassifyAfter"));
        base.setPaymentFlagAf(jsonParams.getString("paymentFlagAfter"));
        base.setPurchaseFlagAf(jsonParams.getString("purchaseFlagAfter"));
        base.setSettleAcctTypeAf(jsonParams.getString("settleAcctTypeAfter"));
        base.setPosTaxAf(jsonParams.getString("posTaxAfter"));
        base.setPosAcctConditionAf(jsonParams.getString("posAcctConditionAfter"));
        base.setStaffNumAf(jsonParams.getInteger("staffNumAfter"));
        base.setSupplierClassifyAf(jsonParams.getString("supplierClassifyAfter"));
        base.setSupplierFileIdAf(jsonParams.getInteger("supplierFileIdAfter"));
        base.setSupplierIndustryAf(jsonParams.getString("supplierIndustryAfter"));
        base.setSupplierNameAf(jsonParams.getString("supplierNameAfter"));
        base.setSupplierShortNameAf(jsonParams.getString("supplierShortNameAfter"));
        base.setSupplierTypeAf(jsonParams.getString("supplierTypeAfter"));
        base.setSrmDeliveryAf(jsonParams.getString("srmDeliveryAfter"));
        base.setAbleCheckOrderFlagAf(jsonParams.getString("ableCheckOrderFlagAfter"));
        base.setAddressAf(jsonParams.getString("addressAfter"));
        base.setCompanyRegisteredAddressAf(jsonParams.getString("companyRegisteredAddressAfter"));
        base.setRegisteredCapitalAf(jsonParams.getString("registeredCapitalAfter"));
        base.setIndLegalPersonFlagAf(jsonParams.getString("indLegalPersonFlagAfter"));
        base.setValueAddedTaxInvFlagAf(jsonParams.getString("valueAddedTaxInvoiceFlagAfter"));
        base.setValueAddedTaxInvDescAf(jsonParams.getString("valueAddedTaxInvoiceDescAfter"));
        base.setAssociatedCompanyAf(jsonParams.getString("associatedCompanyAfter"));
        base.setRegionAf(jsonParams.getString("regionAfter"));
        base.setOperatorUserId(userId);
        srmPosChangeBaseDAO_HI.save(base);

        //保存变更资质信息
        SrmPosChangeCredentialsEntity_HI credential = null;
        if (null == jsonParams.getInteger("changgeCredentialsId")) {
            credential = new SrmPosChangeCredentialsEntity_HI();
            credential.setChangeId(row.getChangeId());
            credential.setLongLicenseIndateBf(jsonParams.getString("longLicenseIndate"));
            if ("N".equals(jsonParams.getString("longLicenseIndate"))) {
                credential.setLicenseIndateBf(jsonParams.getDate("licenseIndate"));
            }
            credential.setLicenseNumBf(jsonParams.getString("licenseNum"));
            credential.setLicenseFileIdBf(jsonParams.getInteger("licenseFileId"));
            credential.setBankPermitNumberBf(jsonParams.getString("bankPermitNumber"));
            credential.setBankPermitFileIdBf(jsonParams.getInteger("bankPermitFileId"));
            credential.setMuchInOneBf(jsonParams.getString("muchInOne"));
            if ("N".equals(jsonParams.getString("muchInOne"))) {
                credential.setTissueCodeBf(jsonParams.getString("tissueCode"));
                credential.setTissueIndateBf(jsonParams.getDate("tissueIndate"));
                credential.setTissueFileIdBf(jsonParams.getInteger("tissueFileId"));
                credential.setTaxCodeBf(jsonParams.getString("taxCode"));
                credential.setTaxFileIdBf(jsonParams.getInteger("taxFileId"));
            }
            credential.setBusinessScopeBf(jsonParams.getString("businessScope"));
            credential.setCorporateIdentityBf(jsonParams.getString("corporateIdentity"));
            credential.setRepresentativeNameBf(jsonParams.getString("representativeName"));
            credential.setOthersFileIdBf(jsonParams.getInteger("othersFileId"));
        } else {
            credential = srmPosChangeCredentialsDAO_HI.getById(jsonParams.getInteger("changgeCredentialsId"));
        }
        credential.setLongLicenseIndateAf(jsonParams.getString("longLicenseIndateAfter"));
        if ("N".equals(jsonParams.getString("longLicenseIndateAfter"))) {
            credential.setLicenseIndateAf(jsonParams.getDate("licenseIndateAfter"));
        }
        credential.setLicenseNumAf(jsonParams.getString("licenseNumAfter"));
        credential.setLicenseFileIdAf(jsonParams.getInteger("licenseFileIdAfter"));
        credential.setBankPermitNumberAf(jsonParams.getString("bankPermitNumberAfter"));
        credential.setBankPermitFileIdAf(jsonParams.getInteger("bankPermitFileIdAfter"));
        credential.setMuchInOneAf(jsonParams.getString("muchInOneAfter"));
        if ("N".equals(jsonParams.getString("muchInOneAfter"))) {
            credential.setTissueCodeAf(jsonParams.getString("tissueCodeAfter"));
            credential.setTissueIndateAf(jsonParams.getDate("tissueIndateAfter"));
            credential.setTissueFileIdAf(jsonParams.getInteger("tissueFileIdAfter"));
            credential.setTaxCodeAf(jsonParams.getString("taxCodeAfter"));
            credential.setTaxFileIdAf(jsonParams.getInteger("taxFileIdAfter"));
        }
        credential.setBusinessScopeAf(jsonParams.getString("businessScopeAfter"));
        credential.setCorporateIdentityAf(jsonParams.getString("corporateIdentityAfter"));
        credential.setRepresentativeNameAf(jsonParams.getString("representativeNameAfter"));
        credential.setOperatorUserId(userId);
        credential.setOthersFileIdAf(jsonParams.getInteger("othersFileIdAfter"));
        credential.setEstablishmentDateAf(jsonParams.getDate("establishmentDateAfter"));
        srmPosChangeCredentialsDAO_HI.save(credential);
        //保存产品与服务（变更后）
        if (null != jsonParams.getJSONArray("lineDataList1")) {
            saveChangeCategory(jsonParams.getJSONArray("lineDataList1"), userId, row.getChangeId(), row.getSupplierId());
        }
        //保存认证与证书（变更后）
        if (null != jsonParams.getJSONArray("lineDataList2")) {
            saveChangeCertificate(jsonParams.getJSONArray("lineDataList2"), userId, row.getChangeId());
        }
        //保存银行信息（变更后）
        if (null != jsonParams.getJSONArray("lineDataList3")) {
            saveChangeBankInfo(jsonParams.getJSONArray("lineDataList3"), userId, row.getChangeId());
        }
        //保存联系人目录（变更后）
        if (null != jsonParams.getJSONArray("lineDataList4")) {
            saveChangeContacts(jsonParams.getJSONArray("lineDataList4"), userId, row.getChangeId(), row.getSupplierId());
        }
        //保存地址簿（变更后）
        if (null != jsonParams.getJSONArray("lineDataList5")) {
            saveAddresses(jsonParams.getJSONArray("lineDataList5"), userId, row.getChangeId(), row.getSupplierId());
        }
        //保存认证与证书（变更前）
        if (null != jsonParams.getJSONArray("lineDataList22")) {
            saveChangeCertificateBf(jsonParams.getJSONArray("lineDataList22"), userId, row.getChangeId());
        }
        //保存银行信息（变更前）
        if (null != jsonParams.getJSONArray("lineDataList33")) {
            saveChangeBankInfoBf(jsonParams.getJSONArray("lineDataList33"), userId, row.getChangeId());
        }
        //保存联系人目录（变更前）
        if (null != jsonParams.getJSONArray("lineDataList44")) {
            saveChangeContactsBf(jsonParams.getJSONArray("lineDataList44"), userId, row.getChangeId(), row.getSupplierId());
        }
        //保存地址簿（变更前）
        if (null != jsonParams.getJSONArray("lineDataList55")) {
            saveAddressesBf(jsonParams.getJSONArray("lineDataList55"), userId, row.getChangeId(), row.getSupplierId());
        }
        //提交
        if ("submit".equals(jsonParams.getString("action"))) {
            //发送邮件
            SendMailUtil sendMailUtil = new SendMailUtil(true);
            JSONArray paramDataList = jsonParams.getJSONArray("lineDataList4");
            JSONObject paramData = null;
            String[] emailAddress = new String[paramDataList.size()];
            for (int i = 0; i < paramDataList.size(); i++) {
                paramData = paramDataList.getJSONObject(i);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if (paramData.getString("failureDate") == null || dateFormat.parse(paramData.getString("failureDate")).after(new Date())) {
                    emailAddress[i] = paramData.getString("emailAddressAf");
                }

            }
            sendMailUtil.doSendHtmlEmail("提交资料变更成功", "<p>您好！</p><br/>" + "<p>您的资料变更单据已提交成功：" + row.getChangeNumber(), emailAddress);


             List<SaafUserEmployeesEntity_HI_RO> userList=findUserToEmail(userId,jsonParams.getString("regionAfter"),jsonParams.getString("supplierTypeAfter"));
            //发邮件
            String title = "供应商档案变更通知";
            String content = "<p>您好！<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp供应商档案变更：" + row.getChangeNumber() + "，需要您这边审批！</p>";
            sendEmailToRes(userList, title, content);

            //插入系统通知
            if(null != userList && userList.size()>0){
                for(int i=0;i<userList.size();i++){
                    SaafUserEmployeesEntity_HI_RO usersEntity = userList.get(i);
                    iSrmBaseNotifications.insertSrmBaseNotifications("公司档案维护", "您好！供应商档案变更：" + row.getChangeNumber() + "，需要您这边审批！"
                            , userList.get(i).getUserId(), "srm_pos_supplier_info", supplierId, "supplierId", "home.supplierInfoForSelf", userId);
                }
            }
           /* //插入系统通知，通知开发专员
            List<SaafResponsibilitysEntity_HI> responsibilityList = saafResponsibilitysDAO_HI.findByProperty("responsibilityName", "开发专员");
            if (null != responsibilityList && responsibilityList.size() > 0) {
                SaafResponsibilitysEntity_HI resp = responsibilityList.get(0);
                iSrmBaseNotifications.insertSrmBaseNotifications("供应商档案变更", "您好，供应商档案变更：" + row.getChangeNumber() + "，需要您这边审批！"
                        , resp.getResponsibilityId(), "srm_pos_change_info", row.getChangeId(), "changeId", "home.supplierInfoUpdateDetail", userId);
            }*/
        }
        json.put("changeId", row.getChangeId());
        return SToolUtils.convertResultJSONObj("S", msg, 1, json);
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
    /**
     * 保存供应商的产品与服务变更
     *
     * @param dataList
     * @param userId
     * @param changeId
     * @param supplierId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveChangeCategory(JSONArray dataList, int userId, int changeId, int supplierId) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        for (int i = 0; i < dataList.size(); i++) {
            paramData = dataList.getJSONObject(i);
            SrmPosChangeCategoriesEntity_HI row = null;
            if (null == paramData.getInteger("changeCategoryId")) {
                row = new SrmPosChangeCategoriesEntity_HI();
                row.setChangeId(changeId);
//                row.setCategoryId(paramData.getIntValue("CategoryId"));
                row.setSupplierId(supplierId);
                row.setStatus(paramData.getString("status"));
                if (null != SrmUtils.getString(paramData.getString("invalidDate"))) {
                    row.setInvalidDate(paramData.getDate("invalidDate"));
                }
                row.setCreationDate(new Date());
                row.setCreatedBy(userId);
            } else {
                row = srmPosChangeCategoriesDAO_HI.getById(paramData.getInteger("changeCategoryId"));
            }

            row.setSupplierCategoryId(paramData.getInteger("supplierCategoryId"));
            row.setCategoryId(paramData.getInteger("categoryId"));
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            srmPosChangeCategoriesDAO_HI.saveEntity(row);
        }
        return resultjson;
    }

    /**
     * 保存供应商的产品与服务
     *
     * @param changeId
     * @param userId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    private String saveOrUpdateSupplierCategory(int changeId, int userId) {
        List<SrmPosChangeCategoriesEntity_HI> changeCategorieslist = srmPosChangeCategoriesDAO_HI.findByProperty("changeId", changeId);
        if (changeCategorieslist.size() > 0) {
            SrmPosChangeCategoriesEntity_HI changeCategoriesEntity_HI = null;
            List<SrmPosSupplierCategoriesEntity_HI> supplierCategoriesList = new ArrayList<>();
            SrmPosSupplierCategoriesEntity_HI supplierCategoriesEntity_HI = null;
            try {
                for (int i = 0; i < changeCategorieslist.size(); i++) {
                    changeCategoriesEntity_HI = changeCategorieslist.get(i);
                    if (changeCategoriesEntity_HI.getSupplierCategoryId() == null) {
                        supplierCategoriesEntity_HI = new SrmPosSupplierCategoriesEntity_HI();
                        supplierCategoriesEntity_HI.setSupplierId(changeCategoriesEntity_HI.getSupplierId());
                        supplierCategoriesEntity_HI.setCategoryId(changeCategoriesEntity_HI.getCategoryId());
                        supplierCategoriesEntity_HI.setManagementBrand(changeCategoriesEntity_HI.getManagementBrand());
                        supplierCategoriesEntity_HI.setSupplyCycle(changeCategoriesEntity_HI.getSupplyCycle());
                        supplierCategoriesEntity_HI.setStatus(changeCategoriesEntity_HI.getStatus());
                        supplierCategoriesEntity_HI.setVersionNum(1);
                        supplierCategoriesEntity_HI.setOperatorUserId(userId);
                        supplierCategoriesList.add(supplierCategoriesEntity_HI);
                    }
                }
                srmPosSupplierCategoriesDAO_HI.saveOrUpdateAll(supplierCategoriesList);
                srmPosSupplierCategoriesDAO_HI.fluch();
            } catch (Exception e) {
                LOGGER.error("更新供应商的产品和服务失败，异常信息：" + e.getMessage());
                return EXECUTE_FAIL;
            }
        }
        return EXECUTE_SUCCESS;
    }

    /**
     * 保存供应商的认证证书(变更前)
     *
     * @param paramDataList
     * @param userId
     * @param changeId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveChangeCertificateBf(JSONArray paramDataList, int userId, int changeId) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        for (int a = 0; a < paramDataList.size(); a++) {
            paramData = paramDataList.getJSONObject(a);
            SrmPosChangeCertificateBfEntity_HI row = null;
            if (null == paramData.getInteger("changeCertificateBfId")) {
                row = new SrmPosChangeCertificateBfEntity_HI();
                row.setChangeId(changeId);
                row.setCreationDate(new Date());
                row.setCreatedBy(userId);
            } else {
                row = srmPosChangeCertificateBfDAO_HI.getById(paramData.getInteger("changeCertificateBfId"));
            }
            row.setCertificateId(paramData.getInteger("certificateId"));
            row.setCertificateName(paramData.getString("certificateName"));
            row.setCertificateNumber(paramData.getString("certificateNumber"));
            row.setCertificateDescription(paramData.getString("certificateDescription"));
            row.setFileId(paramData.getInteger("fileId"));
            row.setEndDate(paramData.getDate("endDate"));
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            srmPosChangeCertificateBfDAO_HI.saveEntity(row);
        }
        return resultjson;
    }

    /**
     * 保存供应商的认证证书(变更后)
     *
     * @param paramDataList
     * @param userId
     * @param changeId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveChangeCertificate(JSONArray paramDataList, int userId, int changeId) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        for (int a = 0; a < paramDataList.size(); a++) {
            paramData = paramDataList.getJSONObject(a);
            SrmPosChangeCertificateAfEntity_HI row = null;
            if (null == paramData.getInteger("changeCertificateAfId")) {
                row = new SrmPosChangeCertificateAfEntity_HI();
                row.setChangeId(changeId);
                row.setCreationDate(new Date());
                row.setCreatedBy(userId);
            } else {
                row = srmPosChangeCertificateAfDAO_HI.getById(paramData.getInteger("changeCertificateAfId"));
            }
            row.setCertificateId(paramData.getInteger("certificateIdAf"));
            row.setCertificateName(paramData.getString("certificateNameAf"));
            row.setCertificateNumber(paramData.getString("certificateNumberAf"));
            row.setCertificateDescription(paramData.getString("certificateDescriptionAf"));
            row.setFileId(paramData.getInteger("fileId"));
            row.setEndDate(paramData.getDate("endDateAf"));
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            srmPosChangeCertificateAfDAO_HI.saveEntity(row);
        }
        return resultjson;
    }

    /**
     * 保存供应商的认证证书（主表）
     *
     * @param changeId
     * @param userId
     * @param supplierId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    private String saveOrUpdateSupplierCertificate(int changeId, int userId, int supplierId) {
        List<SrmPosChangeCertificateAfEntity_HI> changeCertificateList = srmPosChangeCertificateAfDAO_HI.findByProperty("changeId", changeId);
        if (changeCertificateList.size() > 0) {
            SrmPosChangeCertificateAfEntity_HI changeCertificateAfEntity_HI = null;
            List<SrmPosSupplierCertificateEntity_HI> supplierCertificateList = new ArrayList<>();
            SrmPosSupplierCertificateEntity_HI supplierCertificateEntity_HI = null;
            try {
                for (int i = 0; i < changeCertificateList.size(); i++) {
                    changeCertificateAfEntity_HI = changeCertificateList.get(i);
                    if (changeCertificateAfEntity_HI.getCertificateId() == null) {
                        supplierCertificateEntity_HI = new SrmPosSupplierCertificateEntity_HI();
                        supplierCertificateEntity_HI.setVersionNum(1);
                    } else {
                        supplierCertificateEntity_HI = srmPosSupplierCertificateDAO_HI.getById(changeCertificateAfEntity_HI.getCertificateId());
                    }
                    supplierCertificateEntity_HI.setSupplierId(supplierId);
                    supplierCertificateEntity_HI.setCertificateName(changeCertificateAfEntity_HI.getCertificateName());
                    supplierCertificateEntity_HI.setCertificateNumber(changeCertificateAfEntity_HI.getCertificateNumber());
                    supplierCertificateEntity_HI.setCertificateDescription(changeCertificateAfEntity_HI.getCertificateDescription());
                    supplierCertificateEntity_HI.setStartDate(changeCertificateAfEntity_HI.getStartDate());
                    supplierCertificateEntity_HI.setEndDate(changeCertificateAfEntity_HI.getEndDate());
                    supplierCertificateEntity_HI.setFileId(changeCertificateAfEntity_HI.getFileId());
                    supplierCertificateEntity_HI.setOperatorUserId(userId);
                    supplierCertificateList.add(supplierCertificateEntity_HI);
                }
                srmPosSupplierCertificateDAO_HI.saveOrUpdateAll(supplierCertificateList);
                srmPosSupplierCertificateDAO_HI.fluch();

                //删除变更后余下的行
                List<Integer> certificateIdList = supplierCertificateList.stream().map(SrmPosSupplierCertificateEntity_HI::getCertificateId).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList()).stream().filter(Integer->Integer!=null).collect(Collectors.toList());
                List<SrmPosSupplierCertificateEntity_HI> supplierCertificate=srmPosSupplierCertificateDAO_HI.findByProperty("supplierId",supplierId);
                if(!ObjectUtils.isEmpty(supplierCertificate)&&!ObjectUtils.isEmpty(certificateIdList)){
                    for(SrmPosSupplierCertificateEntity_HI ro:supplierCertificate){
                        if(!certificateIdList.contains(ro.getCertificateId())){
                            srmPosSupplierCertificateDAO_HI.delete(ro);
                            srmPosSupplierCertificateDAO_HI.fluch();
                        }
                    }
                }



            } catch (Exception e) {
                LOGGER.error("更新供应商的认证证书失败，异常信息：" + e.getMessage());
                return EXECUTE_FAIL;
            }
        }
        return EXECUTE_SUCCESS;
    }

    /**
     * 删除供应商的认证证书(变更后)
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject deleteChangeCertificateAf(JSONObject params) throws Exception {
        try {
            if (null != params.getInteger("changeCertificateAfId")) {
                SrmPosChangeCertificateAfEntity_HI row = srmPosChangeCertificateAfDAO_HI.getById(params.getInteger("changeCertificateAfId"));

                if (null != row) {
                    srmPosChangeCertificateAfDAO_HI.delete(row);
                } else {
                    throw new Exception("该证书行在系统中不存在！");
                }
            }
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询供应商旧信息
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findSupplierInfo(JSONObject jsonParams, Integer pageIndex,
                                                                     Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        StringBuffer count = new StringBuffer();
        StringBuffer queryParam = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_SUPPLIER_INFO_LIST);
        count.append(SrmPosSupplierInfoEntity_HI_RO.QUERY_COUNT);
        SaafToolUtils.parperParam(jsonParams, "spsi.supplier_id", "supplier_id", queryParam, queryParamMap, "=");//如果是供应商查询
        SaafToolUtils.parperParam(jsonParams, "spsi.supplier_id", "supplierId", queryParam, queryParamMap, "=");
        sb.append(queryParam);
        count.append(queryParam);
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(), count, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * 查询供应商旧信息-门户
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findDoorSupplierInfo(JSONObject jsonParams, Integer pageIndex,
                                                                         Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        StringBuffer count = new StringBuffer();
        StringBuffer queryParam = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_SUPPLIER_INFO_LIST);
        count.append(SrmPosSupplierInfoEntity_HI_RO.QUERY_COUNT);
        int userId = jsonParams.getInteger("varUserId");
        SaafUsersEntity_HI saafUsers = saafUsersEntity_HI.getById(userId);
        if (null != saafUsers) {
            sb.append(" AND spci.supplier_id = " + saafUsers.getSupplierId());
        }
        SaafToolUtils.parperParam(jsonParams, "spsi.supplier_id", "supplier_id", queryParam, queryParamMap, "=");//如果是供应商查询
        SaafToolUtils.parperParam(jsonParams, "spsi.supplier_id", "supplierId", queryParam, queryParamMap, "=");
        sb.append(queryParam);
        count.append(queryParam);
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(), count, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * 验证供应商名称是否已存在
     *
     * @param supplierNameAfter
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmPosChangeInfoEntity_HI_RO> checkSupplierName(Integer supplierId, String supplierNameAfter) {
        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(supplierNameAfter)) {
            return null;
        }
        String queryString =
                "SELECT\n" +
                        "  psi.supplier_id AS supplierId\n" +
                        "FROM\n" +
                        "  srm_pos_supplier_info psi\n" +
                        "WHERE psi.supplier_name = '" + supplierNameAfter + "'\n" +
                        "  AND psi.supplier_id <> " + supplierId + "\n" +
                        "UNION ALL\n" +
                        "SELECT\n" +
                        "  pci.supplier_id AS supplierId\n" +
                        "FROM\n" +
                        "  srm_pos_change_info pci,\n" +
                        "  srm_pos_change_base pcb\n" +
                        "WHERE pci.change_id = pcb.change_id\n" +
                        "  AND pcb.supplier_name_af = '" + supplierNameAfter + "'\n" +
                        "  AND pci.supplier_id <> " + supplierId;
        List<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findList(queryString);
        return result;
    }
    /**
     *
     * 检查行编码
     * @param supplierId
     * @param licenseNumAfter
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmPosChangeCredentialsEntity_HI_RO> checkLicenseNum(Integer supplierId, String licenseNumAfter) {
        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(licenseNumAfter)) {
            return null;
        }
        String queryString =
                "SELECT\n" +
                        "  psi.supplier_id AS supplierId\n" +
                        "FROM\n" +
                        "  srm_pos_supplier_info psi,\n" +
                        "  srm_pos_supplier_credentials psc\n" +
                        "WHERE psi.supplier_id = psc.supplier_id\n" +
                        "  AND psc.license_num = '" + licenseNumAfter + "'\n" +
                        "  AND psi.supplier_id <> " + supplierId + "\n" +
                        "UNION ALL\n" +
                        "SELECT\n" +
                        "  pci.supplier_id AS supplierId\n" +
                        "FROM\n" +
                        "  srm_pos_change_info pci,\n" +
                        "  srm_pos_change_credentials pcc\n" +
                        "WHERE pci.change_id = pcc.change_id\n" +
                        "  AND pcc.license_num_af = '" + licenseNumAfter + "'\n" +
                        "  AND pci.supplier_id <> " + supplierId;
        List<SrmPosChangeCredentialsEntity_HI_RO> result = srmPosChangeCredentialsDAO_HI_RO.findList(queryString);
        return result;
    }

    /**
     * 保存供应商的银行信息(变更前)
     *
     * @param paramDataList
     * @param userId
     * @param changeId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveChangeBankInfoBf(JSONArray paramDataList, int userId, int changeId) {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        for (int b = 0; b < paramDataList.size(); b++) {
            paramData = paramDataList.getJSONObject(b);
            SrmPosChangeBankBfEntity_HI row = null;
            if (null == paramData.getInteger("changeBankBfId")) {
                row = new SrmPosChangeBankBfEntity_HI();
                row.setChangeId(changeId);
                row.setCreationDate(new Date());
                row.setCreatedBy(userId);
            } else {
                row = srmPosChangeBankBfDAO_HI.getById(paramData.getInteger("changeBankBfId"));
            }
            row.setBankAccountId(paramData.getInteger("bankAccountIdBf"));
            row.setBankAccountNumber(paramData.getString("bankAccountNumber"));
            row.setBankUserName(paramData.getString("bankUserName"));
            row.setBankId(paramData.getInteger("bankId"));
            row.setBranchId(paramData.getInteger("branchId"));
            row.setBankName(paramData.getString("bankName"));
            row.setBranchName(paramData.getString("branchName"));
            row.setBranchNumber(paramData.getString("branchNumber"));
            row.setBankCurrency(paramData.getString("bankCurrency"));
            if (null != SrmUtils.getString(paramData.getString("endDate"))) {
                row.setEndDate(paramData.getDate("endDate"));
            }
            if (!"".equals(paramData.getString("invalidDate"))) {
                row.setInvalidDate(paramData.getDate("invalidDate"));
            }
            row.setSwiftCode(paramData.getString("swiftCode"));
            row.setIbanCode(paramData.getString("ibanCode"));
            row.setBankLink(paramData.getString("bankLink"));
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            srmPosChangeBankBfDAO_HI.saveEntity(row);
        }
        return resultjson;
    }

    /**
     * 保存供应商的银行信息(变更后)
     *
     * @param paramDataList
     * @param userId
     * @param changeId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveChangeBankInfo(JSONArray paramDataList, int userId, int changeId) {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        for (int b = 0; b < paramDataList.size(); b++) {
            paramData = paramDataList.getJSONObject(b);
            SrmPosChangeBankAfEntity_HI row = null;
            if (null == paramData.getInteger("changeBankAfId")) {
                row = new SrmPosChangeBankAfEntity_HI();
                row.setChangeId(changeId);
                row.setCreationDate(new Date());
                row.setCreatedBy(userId);
            } else {
                row = srmPosChangeBankAfDAO_HI.getById(paramData.getInteger("changeBankAfId"));
            }
            row.setBankAccountId(paramData.getInteger("bankAccountIdAf"));
            row.setBankAccountNumber(paramData.getString("bankAccountNumberAf"));
            row.setBankUserName(paramData.getString("bankUserNameAf"));
            if (!"".equals(paramData.getString("bankIdAf"))) {
                row.setBankId(paramData.getIntValue("bankIdAf"));
            }
            if (!"".equals(paramData.getString("branchIdAf"))) {
                row.setBranchId(paramData.getIntValue("branchIdAf"));
            }
            row.setBankName(paramData.getString("bankNameAf"));
            row.setBranchName(paramData.getString("branchNameAf"));
            row.setBranchNumber(paramData.getString("branchNumberAf"));
            row.setBankCurrency(paramData.getString("bankCurrencyAf"));
            if (null != SrmUtils.getString(paramData.getString("endDateAf"))) {
                row.setEndDate(paramData.getDate("endDateAf"));
            }
            if (!"".equals(paramData.getString("invalidDateAf"))) {
                row.setInvalidDate(paramData.getDate("invalidDateAf"));
            }
            row.setSwiftCode(paramData.getString("swiftCodeAf"));
            row.setIbanCode(paramData.getString("ibanCodeAf"));
            row.setBankLink(paramData.getString("bankLinkAf"));
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            srmPosChangeBankAfDAO_HI.saveEntity(row);
        }
        return resultjson;
    }

    /**
     * 变更审批后，回写供应商的银行信息
     *
     * @param changeId
     * @param userId
     * @param supplierId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    private String saveOrUpdateSupplierBankInfo(int changeId, int userId, int supplierId) throws Exception {
        List<SrmPosChangeBankAfEntity_HI> changeBankList = srmPosChangeBankAfDAO_HI.findByProperty("changeId", changeId);
        if (changeBankList.size() > 0) {
            SrmPosChangeBankAfEntity_HI changeBankAfEntity_HI = null;
            SrmPosSupplierBankEntity_HI supplierBankEntity_HI = null;
            List<SrmPosSupplierBankEntity_HI> supplierBankList = new ArrayList<>();
            try {
                for (int i = 0; i < changeBankList.size(); i++) {
                    changeBankAfEntity_HI = changeBankList.get(i);
                    if (changeBankAfEntity_HI.getBankAccountId() == null) {
                        supplierBankEntity_HI = new SrmPosSupplierBankEntity_HI();
                        supplierBankEntity_HI.setVersionNum(1);
                    } else {
                        supplierBankEntity_HI = srmPosSupplierBankDAO_HI.getById(changeBankAfEntity_HI.getBankAccountId());
                    }
                    supplierBankEntity_HI.setSupplierId(supplierId);
                    supplierBankEntity_HI.setSupplierSiteId(changeBankAfEntity_HI.getSupplierSiteId());
                    supplierBankEntity_HI.setAssignmentType(changeBankAfEntity_HI.getAssignmentType());
                    supplierBankEntity_HI.setBankAccountNumber(changeBankAfEntity_HI.getBankAccountNumber());
                    supplierBankEntity_HI.setBankUserName(changeBankAfEntity_HI.getBankUserName());
                    supplierBankEntity_HI.setBankId(changeBankAfEntity_HI.getBankId());
                    supplierBankEntity_HI.setBankName(changeBankAfEntity_HI.getBankName());
                    supplierBankEntity_HI.setBranchId(changeBankAfEntity_HI.getBranchId());
                    supplierBankEntity_HI.setBranchName(changeBankAfEntity_HI.getBranchName());
                    supplierBankEntity_HI.setBranchNumber(changeBankAfEntity_HI.getBranchNumber());
                    supplierBankEntity_HI.setBankCurrency(changeBankAfEntity_HI.getBankCurrency());
                    supplierBankEntity_HI.setSwiftCode(changeBankAfEntity_HI.getSwiftCode());
                    supplierBankEntity_HI.setIbanCode(changeBankAfEntity_HI.getIbanCode());
                    supplierBankEntity_HI.setBankLink(changeBankAfEntity_HI.getBankLink());
                    supplierBankEntity_HI.setStartDate(changeBankAfEntity_HI.getStartDate());
                    supplierBankEntity_HI.setEndDate(changeBankAfEntity_HI.getEndDate());
                    supplierBankEntity_HI.setInvalidDate(changeBankAfEntity_HI.getInvalidDate());
                    supplierBankEntity_HI.setOperatorUserId(userId);
                    supplierBankList.add(supplierBankEntity_HI);
                }
                srmPosSupplierBankDAO_HI.saveOrUpdateAll(supplierBankList);
                srmPosSupplierBankDAO_HI.fluch();

                //删除变更后余下的行
                List<Integer> bankAccountIdList = supplierBankList.stream().map(SrmPosSupplierBankEntity_HI::getBankAccountId).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList()).stream().filter(Integer->Integer!=null).collect(Collectors.toList());
                List<SrmPosSupplierBankEntity_HI> supplierBank=srmPosSupplierBankDAO_HI.findByProperty("supplierId",supplierId);
                if(!ObjectUtils.isEmpty(supplierBank)&&!ObjectUtils.isEmpty(bankAccountIdList)){
                    for(SrmPosSupplierBankEntity_HI ro:supplierBank){
                        if(!bankAccountIdList.contains(ro.getBankAccountId())){
                            srmPosSupplierBankDAO_HI.delete(ro);
                            srmPosSupplierBankDAO_HI.fluch();
                        }
                    }
                }
            } catch (Exception e) {
                LOGGER.error("更新供应商银行信息失败，异常信息：" + e.getMessage());
                return EXECUTE_FAIL;
            }
        }
        return EXECUTE_SUCCESS;
    }

    /**
     * 删除供应商的银行信息(变更后)
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject deleteChangeBankAf(JSONObject params) throws Exception {
        try {
            if (null != params.getInteger("changeBankAfId")) {
                SrmPosChangeBankAfEntity_HI row = srmPosChangeBankAfDAO_HI.getById(params.getInteger("changeBankAfId"));

                if (null != row) {
                    srmPosChangeBankAfDAO_HI.delete(row);
                } else {
                    throw new Exception("该银行信息行在系统中不存在！");
                }
            }
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 保存供应商的联系人(变更前)
     *
     * @param paramDataList
     * @param userId
     * @param changeId
     * @param supplierId
     * @returnf
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveChangeContactsBf(JSONArray paramDataList, int userId, int changeId, int supplierId) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        for (int c = 0; c < paramDataList.size(); c++) {
            paramData = paramDataList.getJSONObject(c);
            SrmPosChangeContactBfEntity_HI row = null;
            if (null == paramData.getInteger("changeContactBfId")) {
                row = new SrmPosChangeContactBfEntity_HI();
                row.setChangeId(changeId);
                //row.setSupplierId(supplierId);
                row.setCreationDate(new Date());
                row.setCreatedBy(userId);
            } else {
                row = (SrmPosChangeContactBfEntity_HI) srmPosChangeContactBfDAO_HI.getById(paramData.getInteger("changeContactBfId"));
            }
            row.setSupplierContactId(paramData.getInteger("supplierContactId"));
            row.setContactName(paramData.getString("contactName"));
            row.setDepartmentName(paramData.getString("departmentName"));
            row.setPositionName(paramData.getString("positionName"));
            row.setMobilePhone(paramData.getString("mobilePhone"));
            row.setEmailAddress(paramData.getString("emailAddress"));
            row.setFixedPhone(paramData.getString("fixedPhone"));
            row.setFaxPhone(paramData.getString("faxPhone"));
            row.setFailureDate(paramData.getDate("failureDate"));
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            srmPosChangeContactBfDAO_HI.saveEntity(row);
        }
        return resultjson;
    }

    /**
     * 保存供应商的联系人(变更后)
     *
     * @param paramDataList
     * @param userId
     * @param changeId
     * @param supplierId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveChangeContacts(JSONArray paramDataList, int userId, int changeId, int supplierId) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        for (int c = 0; c < paramDataList.size(); c++) {
            paramData = paramDataList.getJSONObject(c);
            SrmPosChangeContactAfEntity_HI row = null;
            if (null == paramData.getInteger("changeContactAfId")) {
                row = new SrmPosChangeContactAfEntity_HI();
                row.setChangeId(changeId);
                row.setCreationDate(new Date());
                row.setCreatedBy(userId);
            } else {
                row = srmPosChangeContactAfDAO_HI.getById(paramData.getInteger("changeContactAfId"));
            }
            row.setSupplierContactId(paramData.getInteger("supplierContactIdAf"));
            row.setContactName(paramData.getString("contactNameAf"));
            row.setDepartmentName(paramData.getString("departmentNameAf"));
            row.setPositionName(paramData.getString("positionNameAf"));
            row.setMobilePhone(paramData.getString("mobilePhoneAf"));
            row.setEmailAddress(paramData.getString("emailAddressAf"));
            row.setFixedPhone(paramData.getString("fixedPhoneAf"));
            row.setFaxPhone(paramData.getString("faxPhoneAf"));
            row.setFailureDate(paramData.getDate("failureDateAf"));
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            srmPosChangeContactAfDAO_HI.saveEntity(row);
        }
        return resultjson;
    }

    /**
     * 审批通过后，回写供应商的联系人信息
     *
     * @param changeId
     * @param userId
     * @param supplierId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    private String saveOrUpdateSupplierContacts(int changeId, int userId, int supplierId) {
        List<SrmPosChangeContactAfEntity_HI> changeContactList = srmPosChangeContactAfDAO_HI.findByProperty("changeId", changeId);
        if (changeContactList != null && changeContactList.size() > 0) {
            SrmPosChangeContactAfEntity_HI changeContactAfEntity_HI = null;
            SrmPosSupplierContactsEntity_HI supplierContactsEntity_HI = null;
            List<SrmPosSupplierContactsEntity_HI> supplierContactsList = new ArrayList();
            try {
                for (int i = 0; i < changeContactList.size(); i++) {
                    changeContactAfEntity_HI = changeContactList.get(i);
                    if (changeContactAfEntity_HI.getSupplierContactId() == null) {
                        supplierContactsEntity_HI = new SrmPosSupplierContactsEntity_HI();
                        supplierContactsEntity_HI.setVersionNum(1);
                    } else {
                        supplierContactsEntity_HI = srmPosSupplierContactsDAO_HI.getById(changeContactAfEntity_HI.getSupplierContactId());
                    }
                    supplierContactsEntity_HI.setSupplierId(supplierId);
                    supplierContactsEntity_HI.setContactName(changeContactAfEntity_HI.getContactName());
                    supplierContactsEntity_HI.setDepartmentName(changeContactAfEntity_HI.getDepartmentName());
                    supplierContactsEntity_HI.setPositionName(changeContactAfEntity_HI.getPositionName());
                    supplierContactsEntity_HI.setMobilePhone(changeContactAfEntity_HI.getMobilePhone());
                    supplierContactsEntity_HI.setFixedPhone(changeContactAfEntity_HI.getFixedPhone());
                    supplierContactsEntity_HI.setFaxPhone(changeContactAfEntity_HI.getFaxPhone());
                    supplierContactsEntity_HI.setEmailAddress(changeContactAfEntity_HI.getEmailAddress());
                    supplierContactsEntity_HI.setDepartmentName(changeContactAfEntity_HI.getDepartmentName());
                    supplierContactsEntity_HI.setPositionName(changeContactAfEntity_HI.getPositionName());
                    supplierContactsEntity_HI.setBirthDate(changeContactAfEntity_HI.getBirthDate());
                    supplierContactsEntity_HI.setFailureDate(changeContactAfEntity_HI.getFailureDate());
                    supplierContactsEntity_HI.setOperatorUserId(userId);
                    supplierContactsList.add(supplierContactsEntity_HI);
                }
                srmPosSupplierContactsDAO_HI.saveOrUpdateAll(supplierContactsList);
                srmPosSupplierContactsDAO_HI.fluch();

                //删除变更后余下的行
                List<Integer> contactIdList = supplierContactsList.stream().map(SrmPosSupplierContactsEntity_HI::getSupplierContactId).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList()).stream().filter(Integer->Integer!=null).collect(Collectors.toList());
                List<SrmPosSupplierContactsEntity_HI> supplierContacts=srmPosSupplierContactsDAO_HI.findByProperty("supplierId",supplierId);
                if(!ObjectUtils.isEmpty(supplierContacts)&&!ObjectUtils.isEmpty(contactIdList)){
                    for(SrmPosSupplierContactsEntity_HI ro:supplierContacts){
                        if(!contactIdList.contains(ro.getSupplierContactId())){
                            srmPosSupplierContactsDAO_HI.delete(ro);
                            srmPosSupplierContactsDAO_HI.fluch();
                        }
                    }
                }


            } catch (Exception e) {
                LOGGER.error("更新供应商联系人失败，异常信息：" + e.getMessage());
                return EXECUTE_FAIL;
            }
        }
        return EXECUTE_SUCCESS;
    }

    /**
     * 保存供应商的地址簿(变更前)
     *
     * @param paramDataList
     * @param userId
     * @param changeId
     * @param supplierId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveAddressesBf(JSONArray paramDataList, int userId, int changeId, int supplierId) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        for (int d = 0; d < paramDataList.size(); d++) {
            paramData = paramDataList.getJSONObject(d);
            SrmPosChangeAddressBfEntity_HI row = null;
            if (null == paramData.getInteger("changeAddressBfId")) {
                row = new SrmPosChangeAddressBfEntity_HI();
                row.setChangeId(changeId);
                row.setCreationDate(new Date());
                row.setCreatedBy(userId);
            } else {
                row = srmPosChangeAddressBfDAO_HI.getById(paramData.getInteger("changeAddressBfId"));
            }
            row.setSupplierAddressId(paramData.getInteger("supplierAddressId"));
            row.setAddressName(paramData.getString("addressName"));
            row.setCity(paramData.getString("city"));
            row.setCountry(paramData.getString("country"));
            row.setCounty(paramData.getString("county"));
            row.setDetailAddress(paramData.getString("detailAddress"));
            row.setProvince(paramData.getString("province"));
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            srmPosChangeAddressBfDAO_HI.saveEntity(row);
        }
        return resultjson;
    }

    /**
     * 保存供应商的地址簿(变更后)
     *
     * @param paramDataList
     * @param userId
     * @param changeId
     * @param supplierId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveAddresses(JSONArray paramDataList, int userId, int changeId, int supplierId) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        for (int d = 0; d < paramDataList.size(); d++) {
            paramData = paramDataList.getJSONObject(d);
            SrmPosChangeAddressAfEntity_HI row = null;
            if (null == paramData.getInteger("changeAddressAfId")) {
                row = new SrmPosChangeAddressAfEntity_HI();
                row.setChangeId(changeId);
                row.setCreationDate(new Date());
                row.setCreatedBy(userId);
            } else {
                row = srmPosChangeAddressAfDAO_HI.getById(paramData.getInteger("changeAddressAfId"));
            }
            row.setSupplierAddressId(paramData.getInteger("supplierAddressIdAf"));
            row.setAddressName(paramData.getString("addressNameAf"));
            row.setCity(paramData.getString("cityAf"));
            row.setCountry(paramData.getString("countryAf"));
            row.setCounty(paramData.getString("countyAf"));
            row.setDetailAddress(paramData.getString("detailAddressAf"));
            row.setProvince(paramData.getString("provinceAf"));
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            srmPosChangeAddressAfDAO_HI.saveEntity(row);
        }
        return resultjson;
    }

    /**
     * 审批通过后，回写供应商的地址簿信息
     *
     * @param changeId
     * @param userId
     * @param supplierId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    private String saveOrUpdateSupplierAddresses(int changeId, int userId, int supplierId) {
        List<SrmPosChangeAddressAfEntity_HI> changeAddressList = srmPosChangeAddressAfDAO_HI.findByProperty("changeId", changeId);
        if (changeAddressList.size() > 0) {
            SrmPosChangeAddressAfEntity_HI changeAddressAfEntity_HI = null;
            SrmPosSupplierAddressesEntity_HI supplierAddressesEntity_HI = null;
            List<SrmPosSupplierAddressesEntity_HI> supplierAddressesList = new ArrayList();
            try {
                for (int i = 0; i < changeAddressList.size(); i++) {
                    changeAddressAfEntity_HI = changeAddressList.get(i);
                    if (changeAddressAfEntity_HI.getSupplierAddressId() == null) {
                        supplierAddressesEntity_HI = new SrmPosSupplierAddressesEntity_HI();
                        supplierAddressesEntity_HI.setVersionNum(1);
                    } else {
                        supplierAddressesEntity_HI = srmPosSupplierAddressesDAO_HI.getById(changeAddressAfEntity_HI.getSupplierAddressId());
                    }
                    supplierAddressesEntity_HI.setSupplierId(supplierId);
                    supplierAddressesEntity_HI.setAddressName(changeAddressAfEntity_HI.getAddressName());
                    supplierAddressesEntity_HI.setCountry(changeAddressAfEntity_HI.getCountry());
                    supplierAddressesEntity_HI.setProvince(changeAddressAfEntity_HI.getProvince());
                    supplierAddressesEntity_HI.setCity(changeAddressAfEntity_HI.getCity());
                    supplierAddressesEntity_HI.setCounty(changeAddressAfEntity_HI.getCounty());
                    supplierAddressesEntity_HI.setDetailAddress(changeAddressAfEntity_HI.getDetailAddress());
                    supplierAddressesEntity_HI.setInvalidDate(changeAddressAfEntity_HI.getInvalidDate());
                    supplierAddressesEntity_HI.setOperatorUserId(userId);
                    supplierAddressesList.add(supplierAddressesEntity_HI);
                }
                srmPosSupplierAddressesDAO_HI.saveOrUpdateAll(supplierAddressesList);
                srmPosSupplierAddressesDAO_HI.fluch();

                //删除变更后余下的行
                List<Integer> addressIdList = supplierAddressesList.stream().map(SrmPosSupplierAddressesEntity_HI::getSupplierAddressId).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList()).stream().filter(Integer->Integer!=null).collect(Collectors.toList());
                List<SrmPosSupplierAddressesEntity_HI> supplierAddresses=srmPosSupplierAddressesDAO_HI.findByProperty("supplierId",supplierId);
                if(!ObjectUtils.isEmpty(supplierAddresses)&&!ObjectUtils.isEmpty(addressIdList)){
                    for(SrmPosSupplierAddressesEntity_HI ro:supplierAddresses){
                        if(!addressIdList.contains(ro.getSupplierAddressId())){
                            srmPosSupplierAddressesDAO_HI.delete(ro);
                            srmPosSupplierAddressesDAO_HI.fluch();
                        }
                    }
                }

            } catch (Exception e) {
                LOGGER.error("更新供应商地址信息失败，异常信息：" + e.getMessage());
                return EXECUTE_FAIL;
            }
        }
        return EXECUTE_SUCCESS;
    }

    /**
     * 删除供应商的地址簿(变更后)
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject deleteAddressesAf(JSONObject params) throws Exception {
        try {
            if (null != params.getInteger("changeAddressAfId")) {
                SrmPosChangeAddressAfEntity_HI row = srmPosChangeAddressAfDAO_HI.getById(params.getInteger("changeAddressAfId"));

                if (null != row) {
                    srmPosChangeAddressAfDAO_HI.delete(row);
                } else {
                    throw new Exception("该地址簿行在系统中不存在！");
                }
            }
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 删除变更单据
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteChangeInfo(JSONObject params) throws Exception {
        Integer changeId = params.getInteger("changeId");
        if (changeId != null) {
            SrmPosChangeInfoEntity_HI row = srmPosChangeInfoDAO_HI.getById(changeId);
            //拟定状态的单据才可以删除
            if (row != null && "NEW".equals(row.getChangeStatus())) {
                try {
                    //删除变更的基础信息
                    List<SrmPosChangeBaseEntity_HI> list1 = srmPosChangeBaseDAO_HI.findByProperty("changeId", changeId);
                    if (list1.size() > 0) {
                        srmPosChangeBaseDAO_HI.delete(list1);
                    }
                    //删除变更的资质信息
                    List<SrmPosChangeCredentialsEntity_HI> list2 = srmPosChangeCredentialsDAO_HI.findByProperty("changeId", changeId);
                    if (list2.size() > 0) {
                        srmPosChangeCredentialsDAO_HI.delete(list2);
                    }
                    //删除变更的产品和服务信息
                    List<SrmPosChangeCategoriesEntity_HI> list3 = srmPosChangeCategoriesDAO_HI.findByProperty("changeId", changeId);
                    if (list3.size() > 0) {
                        srmPosChangeCategoriesDAO_HI.delete(list3);
                    }
                    //删除变更的认证和证书信息
                    List<SrmPosChangeCertificateBfEntity_HI> list4 = srmPosChangeCertificateBfDAO_HI.findByProperty("changeId", changeId);
                    if (list4.size() > 0) {
                        srmPosChangeCertificateBfDAO_HI.delete(list4);
                    }
                    List<SrmPosChangeCertificateAfEntity_HI> list5 = srmPosChangeCertificateAfDAO_HI.findByProperty("changeId", changeId);
                    if (list5.size() > 0) {
                        srmPosChangeCertificateAfDAO_HI.delete(list5);
                    }
                    //删除变更的银行信息
                    List<SrmPosChangeBankBfEntity_HI> list6 = srmPosChangeBankBfDAO_HI.findByProperty("changeId", changeId);
                    if (list6.size() > 0) {
                        srmPosChangeBankBfDAO_HI.delete(list6);
                    }
                    List<SrmPosChangeBankAfEntity_HI> list7 = srmPosChangeBankAfDAO_HI.findByProperty("changeId", changeId);
                    if (list7.size() > 0) {
                        srmPosChangeBankAfDAO_HI.delete(list7);
                    }
                    //删除变更的联系人信息
                    List<SrmPosChangeContactBfEntity_HI> list8 = srmPosChangeContactBfDAO_HI.findByProperty("changeId", changeId);
                    if (list8.size() > 0) {
                        srmPosChangeContactBfDAO_HI.delete(list8);
                    }
                    List<SrmPosChangeContactAfEntity_HI> list9 = srmPosChangeContactAfDAO_HI.findByProperty("changeId", changeId);
                    if (list9.size() > 0) {
                        srmPosChangeContactAfDAO_HI.delete(list9);
                    }
                    //删除变更的地址簿信息
                    List<SrmPosChangeAddressBfEntity_HI> list10 = srmPosChangeAddressBfDAO_HI.findByProperty("changeId", changeId);
                    if (list10.size() > 0) {
                        srmPosChangeAddressBfDAO_HI.delete(list10);
                    }
                    List<SrmPosChangeAddressAfEntity_HI> list11 = srmPosChangeAddressAfDAO_HI.findByProperty("changeId", changeId);
                    if (list11.size() > 0) {
                        srmPosChangeAddressAfDAO_HI.delete(list11);
                    }
                    //删除变更单据头信息
                    srmPosChangeInfoDAO_HI.delete(row);
                    return SToolUtils.convertResultJSONObj("S", "删除成功！", 1, null);
                } catch (Exception e) {
                    throw new Exception(e);
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "单据不存在或非拟定状态的单据不允许删除！", 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "参数为空，删除失败！", 0, null);
        }
    }

    /**
     * 删除产品与服务行
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject deleteChangeCategory(JSONObject params) throws Exception {
        try {
            if (null != params.getInteger("changeCategoryId")) {
                SrmPosChangeCategoriesEntity_HI row = srmPosChangeCategoriesDAO_HI.getById(params.getInteger("changeCategoryId"));
                if (null != row) {
                    if (!"NEW".equals(row.getStatus())) {
                        throw new Exception("非新增状态的产品不能删除！");
                    }
                    srmPosChangeCategoriesDAO_HI.delete(row);
                } else {
                    throw new Exception("该产品行在系统中不存在！");
                }
            }
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：保存/提交供应商变更信息
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
    public JSONObject deleteChangeContact(JSONObject params) throws Exception {
        try {
            if (null != params.getInteger("changeContactAfId")) {
                SrmPosChangeContactAfEntity_HI row = srmPosChangeContactAfDAO_HI.getById(params.getInteger("changeContactAfId"));
                if (null != row) {
                    srmPosChangeContactAfDAO_HI.delete(row);
                } else {
                    throw new Exception("联系人在系统中不存在！");
                }
            }
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 审批档案变更
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Transactional(readOnly = false)
    public JSONObject approveUpdate(JSONObject params, int userId) throws Exception {
        try {
            Integer changeId = params.getInteger("changeId");
            String supplierEbsNumber="";
            if (changeId != null) {
                SrmPosChangeInfoEntity_HI row = srmPosChangeInfoDAO_HI.getById(changeId);
                if (row != null) {
                    Integer supplierId = params.getInteger("supplierId");
                    try {
                        //回写供应商主信息
                        SrmPosSupplierInfoEntity_HI supplierInfo = srmPosSupplierInfoDAO_HI.getById(supplierId);
                        supplierInfo.setAcctCheckStaff(params.getString("acctCheckStaffAfter"));
                        supplierInfo.setAcctCheckType(params.getString("acctCheckTypeAfter"));
                        supplierInfo.setCompanyDescription(params.getString("companyDescriptionAfter"));
                        supplierInfo.setCompanyFax(params.getString("companyFaxAfter"));
                        supplierInfo.setCompanyPhone(params.getString("companyPhoneAfter"));
                        supplierInfo.setFinClassify(params.getString("finClassifyAfter"));
                        supplierInfo.setPaymentFlag(params.getString("paymentFlagAfter"));
                        supplierInfo.setPurchaseFlag(params.getString("purchaseFlagAfter"));
                        supplierInfo.setSettleAcctType(params.getString("settleAcctTypeAfter"));
                        supplierInfo.setPosTax(params.getString("posTaxAfter"));
                        supplierInfo.setPosAcctCondition(params.getString("posAcctConditionAfter"));
                        supplierInfo.setStaffNum(params.getInteger("staffNumAfter"));
                        supplierInfo.setSupplierClassify(params.getString("supplierClassifyAfter"));
                        supplierInfo.setSupplierFileId(params.getInteger("supplierFileIdAfter"));
                        supplierInfo.setSupplierIndustry(params.getString("supplierIndustryAfter"));
                        supplierInfo.setSupplierName(params.getString("supplierNameAfter"));
                        supplierInfo.setSupplierShortName(params.getString("supplierShortNameAfter"));
                        supplierInfo.setSupplierType(params.getString("supplierTypeAfter"));
                        supplierInfo.setSrmDelivery(params.getString("srmDeliveryAfter"));
                        supplierInfo.setAbleCheckOrderFlag(params.getString("ableCheckOrderFlagAfter"));
                        supplierInfo.setAddress(params.getString("addressAfter"));
                        supplierInfo.setCompanyRegisteredAddress(params.getString("companyRegisteredAddressAfter"));
                        supplierInfo.setRegisteredCapital(params.getString("registeredCapitalAfter"));
                        supplierInfo.setIndependentLegalPersonFlag(params.getString("indLegalPersonFlagAfter"));
                        supplierInfo.setValueAddedTaxInvoiceFlag(params.getString("valueAddedTaxInvoiceFlagAfter"));
                        supplierInfo.setValueAddedTaxInvoiceDesc(params.getString("valueAddedTaxInvoiceDescAfter"));
                        supplierInfo.setAssociatedCompany(params.getString("associatedCompanyAfter"));
                        supplierInfo.setRegion(params.getString("regionAfter"));
                        supplierInfo.setOperatorUserId(userId);
                        srmPosSupplierInfoDAO_HI.update(supplierInfo);
                        supplierEbsNumber=StringUtils.isEmpty(supplierInfo.getSupplierEbsNumber())?"":supplierInfo.getSupplierEbsNumber();
                        //供应商资质信息
                        List<SrmPosSupplierCredentialsEntity_HI> credentialsList = srmPosSupplierCredentialsDAO_HI.findByProperty("supplierId", supplierId);
                        if (credentialsList.size() > 0) {
                            for (SrmPosSupplierCredentialsEntity_HI credentials : credentialsList) {
                                credentials.setLongLicenseIndate(params.getString("longLicenseIndateAfter"));
                                if ("N".equals(params.getString("longLicenseIndateAfter"))) {
                                    credentials.setLicenseIndate(params.getDate("licenseIndateAfter"));
                                }
                                credentials.setLicenseNum(params.getString("licenseNumAfter"));
                                credentials.setLicenseFileId(params.getInteger("licenseFileIdAfter"));
                                credentials.setBankPermitNumber(params.getString("bankPermitNumberAfter"));
                                credentials.setBankPermitFileId(params.getInteger("bankPermitFileIdAfter"));
                                credentials.setMuchInOne(params.getString("muchInOneAfter"));
                                if ("N".equals(params.getString("muchInOneAfter"))) {
                                    credentials.setTissueCode(params.getString("tissueCodeAfter"));
                                    credentials.setTissueIndate(params.getDate("tissueIndateAfter"));
                                    credentials.setTissueFileId(params.getInteger("tissueFileIdAfter"));
                                    credentials.setTaxCode(params.getString("taxCodeAfter"));
                                    credentials.setTaxFileId(params.getInteger("taxFileIdAfter"));
                                }
                                credentials.setBusinessScope(params.getString("businessScopeAfter"));
                                credentials.setCorporateIdentity(params.getString("corporateIdentityAfter"));
                                credentials.setRepresentativeName(params.getString("representativeNameAfter"));
                                credentials.setOperatorUserId(userId);
                                credentials.setOthersFileId(params.getInteger("othersFileIdAfter"));
                                credentials.setEstablishmentDate(params.getDate("establishmentDateAfter"));
                                srmPosSupplierCredentialsDAO_HI.update(credentials);
                            }
                        }
                    } catch (Exception e) {
                        LOGGER.error("审批失败，异常信息：" + e.getMessage());
                        return SToolUtils.convertResultJSONObj("E", "审批失败，更新供应商主信息时出现异常！", 1, null);
                    }

                    String flag = null;
                    //供应商产品与服务
                    if (changeId != null && changeId > 0) {
                        flag = saveOrUpdateSupplierCategory(changeId, userId);
                        if (!EXECUTE_SUCCESS.equals(flag)) {
                            return SToolUtils.convertResultJSONObj("E", "审批失败，更新供应商产品和服务时出现异常！", 1, null);
                        }
                    }
                    //供应商认证与证书
                    if (changeId != null && changeId > 0) {
                        flag = saveOrUpdateSupplierCertificate(changeId, userId, supplierId);
                        if (!EXECUTE_SUCCESS.equals(flag)) {
                            return SToolUtils.convertResultJSONObj("E", "审批失败，更新供应商认证与证书时出现异常！", 1, null);
                        }
                    }
                    //供应商银行信息
                    if (changeId != null && changeId > 0) {
                        flag = saveOrUpdateSupplierBankInfo(changeId, userId, supplierId);
                        if (!EXECUTE_SUCCESS.equals(flag)) {
                            return SToolUtils.convertResultJSONObj("E", "审批失败，更新供应商银行信息时出现异常！", 1, null);
                        }
                    }
                    //供应商联系人
                    if (changeId != null && changeId > 0) {
                        flag = saveOrUpdateSupplierContacts(changeId, userId, supplierId);
                        if (!EXECUTE_SUCCESS.equals(flag)) {
                            return SToolUtils.convertResultJSONObj("E", "审批失败，更新供应商联系人时出现异常！", 1, null);
                        }
                    }
                    //供应商地址薄
                    if (changeId != null && changeId > 0) {
                        flag = saveOrUpdateSupplierAddresses(changeId, userId, supplierId);
                        if (!EXECUTE_SUCCESS.equals(flag)) {
                            return SToolUtils.convertResultJSONObj("E", "审批失败，更新供应商地址薄时出现异常！", 1, null);
                        }
                    }

                    //更新变更单的状态
                    row.setChangeStatus("APPROVED");
                    row.setApprovalDate(new Date());
                    row.setApprovalOpinion(params.getString("approvalOpinion"));
                    row.setApprovalUserId(userId);
                    row.setOperatorUserId(userId);
                    srmPosChangeInfoDAO_HI.update(row);

                    //传送供应商数据到ERP
                    if(!StringUtils.isEmpty(supplierEbsNumber)){
                        JSONObject sendJson = new JSONObject();
                        sendJson.put("supplierId", supplierId);
                        sendJson.put("varUserId", userId);
                        JSONObject json = iSupplierInfo.updateSrmPosSupplierInfoSendToEBS(sendJson);
                        if(null == json || null == json.getString(STATUS) || !SUCCESS_STATUS.equals(json.getString(STATUS))){
                            throw new UtilsException(json.getString(MSG));
                        }
                    }


                    List<SrmPosChangeContactAfEntity_HI> paramDataList = srmPosChangeContactAfDAO_HI.findByProperty("changeId", changeId);
                  /*  SendMailUtil sendMailUtil = new SendMailUtil(true);
                    String[] emailAddress = new String[paramDataList.size()];
                    for (int i = 0; i < paramDataList.size(); i++) {
                        emailAddress[i] = paramDataList.get(i).getEmailAddress();
                    }
                    sendMailUtil.doSendHtmlEmail("审批资料变更成功", "<p>您好！</p><br/>" + "<p>您有新的审批资料变更单据：" + row.getChangeNumber(), emailAddress);
*/
                    String title = "审批资料变更成功";
                    String content = "<p>您好！</p><br/>" +"贵公司的档案变更流程已审批通过，单据号：" + row.getChangeNumber();
                    sendEmailToContacts(paramDataList, title, content);

                    //插入通知
                    List<SaafUsersEntity_HI> usersList = saafUsersDAO_HI.findByProperty("supplierId", row.getSupplierId());
                    if (null != usersList && usersList.size() > 0) {
                        SaafUsersEntity_HI usersEntity = usersList.get(0);
                        iSrmBaseNotifications.insertSrmBaseNotifications("供应商档案变更", "您好！贵公司的资料已经变更成功，请查看确认，如果有疑问可及时跟对应采购负责人联系！"
                                , usersEntity.getUserId(), "srm_pos_change_info", row.getChangeId(), "changeId", "home.supplierInfoUpdateDetail", userId);
                    }
                } else {
                    return SToolUtils.convertResultJSONObj("E", "审批失败，单据在系统中不存在！", 1, null);
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "审批失败，单据在系统中不存在！", 1, null);
            }
            return SToolUtils.convertResultJSONObj("S", "审批成功！", 1, null);
        } catch (Exception e) {
            LOGGER.error("审批失败，异常信息：" + e.getMessage());
            return SToolUtils.convertResultJSONObj("E", "审批失败！"+ e.getMessage(), 1, null);
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

    @Override
    public JSONObject saveRejectUpdate(JSONObject params) throws Exception {
        try {
            int userId = params.getInteger("varUserId");
            if (null != params.getInteger("changeId")) {
                SrmPosChangeInfoEntity_HI row = (SrmPosChangeInfoEntity_HI) srmPosChangeInfoDAO_HI.getById(params.getInteger("changeId"));
                if (null != row) {
                    if ("APPROVING".equals(row.getChangeStatus())) {
                        row.setChangeStatus("REJECT");
                        row.setApprovalOpinion(params.getString("approvalOpinion"));
                        row.setOperatorUserId(userId);
                        srmPosChangeInfoDAO_HI.save(row);
                        //
                        List<SrmPosChangeContactAfEntity_HI> paramDataList = srmPosChangeContactAfDAO_HI.findByProperty("changeId", params.getInteger("changeId"));
                        String title = "档案变更驳回";
                        String content = "<p>您好！</p><br/>" + "<p>您所提交的供应商档案变更，单号：" + row.getChangeNumber() + "；审批被驳回，请查看处理，谢谢！";
                        sendEmailToContacts(paramDataList, title, content);
                        //插入通知
                        iSrmBaseNotifications.insertSrmBaseNotifications("供应商档案变更", "您好：\n" +
                                        " 你所提交的供应商档案变更，单号：" + row.getChangeNumber() + "；审批被驳回，请查看处理，谢谢！"
                                , row.getCreatedBy(), "srm_pos_change_info", row.getChangeId(), "changeId", "home.supplierInfoUpdateDetail", userId);
                    } else {
                        throw new Exception("非审批中状态不能驳回！");
                    }
                } else {
                    throw new Exception("单据在系统中不存在");
                }
            } else {
                throw new Exception("单据在系统中不存在");
            }
            return SToolUtils.convertResultJSONObj("S", "驳回成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询供应商的产品和服务
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findUpdateSupplierCertificate(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosChangeInfoEntity_HI_RO.QUERY_SUPPLIER_CERTIFICATE);
        SaafToolUtils.parperParam(jsonParams, "spsc.supplier_id", "supplier_id", sb, queryParamMap, "=");//如果是供应商查询
        SaafToolUtils.parperParam(jsonParams, "spsc.supplier_id", "supplierId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spsc.certificate_id", "certificateId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosChangeInfoEntity_HI_RO> result = srmPosChangeInfoDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    private void sendEmailToContacts(final List<SrmPosChangeContactAfEntity_HI> contactList, final String title, final String content) {
        ArrayList<String> emailAddressList = new ArrayList<>();
        for (SrmPosChangeContactAfEntity_HI contact : contactList) {
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



}
