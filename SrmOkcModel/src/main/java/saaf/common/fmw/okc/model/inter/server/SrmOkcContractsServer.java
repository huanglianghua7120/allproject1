package saaf.common.fmw.okc.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import com.yhg.fastdfs.core.bean.FastDFSFileEntity;
import com.yhg.fastdfs.core.bean.ResutlFileEntity;
import com.yhg.fastdfs.core.utils.FileManagerUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafBaseResultFileEntity_HI;
import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafBaseResultFileEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafBaseResultFile;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.base.utils.ESBClientUtils;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.base.utils.enums.ESBParams;
import saaf.common.fmw.base.ws.service.EKPSyncService;
import saaf.common.fmw.common.model.dao.CommonDAO_HI_DY;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.okc.model.entities.*;
import saaf.common.fmw.okc.model.entities.readonly.*;
import saaf.common.fmw.okc.model.inter.ISrmOkcContracts;
import saaf.common.fmw.okc.model.inter.ISrmOkcFreightExpenses;
import saaf.common.fmw.po.model.entities.SrmPoHeaderArchivesEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoHeadersEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoLineArchivesEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoLinesEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoHeadersEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonBidHeadersEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierBankEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierContactsEntity_HI_RO;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static saaf.common.fmw.services.CommonAbstractServices.SUCCESS_STATUS;

@Component
public class SrmOkcContractsServer implements ISrmOkcContracts {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmOkcContractsServer.class);

    @Autowired
    private ViewObject<SrmOkcContractPartiesEntity_HI> srmOkcContractPartiesDAO_HI;

    @Autowired
    private BaseViewObject<SrmOkcContractPartiesEntity_HI_RO> srmOkcContractPartiesDAO_HI_RO;

    @Autowired
    private ViewObject<SrmOkcContractItemsEntity_HI> srmOkcContractItemsDAO_HI;

    @Autowired
    private BaseViewObject<SrmOkcContractItemsEntity_HI_RO> srmOkcContractItemsDAO_HI_RO;

    @Autowired
    private ViewObject<SrmOkcContractTextsEntity_HI> srmOkcContractTextsDAO_HI;

    @Autowired
    private BaseViewObject<SrmOkcContractTextsEntity_HI_RO> srmOkcContractTextsDAO_HI_RO;

    @Autowired
    private ViewObject<SrmOkcContractFreightsEntity_HI> srmOkcContractFreightsDAO_HI;

    @Autowired
    private BaseViewObject<SrmOkcContractFreightsEntity_HI_RO> srmOkcContractFreightsDAO_HI_RO;

    @Autowired
    private ViewObject<SrmOkcContractAttachmentsEntity_HI> srmOkcContractAttachmentsDAO_HI;

    @Autowired
    private BaseViewObject<SrmOkcContractAttachmentsEntity_HI_RO> srmOkcContractAttachmentsDAO_HI_RO;

    @Autowired
    private ViewObject<SrmOkcContractPaymentsEntity_HI> srmOkcContractPaymentsDAO_HI;

    @Autowired
    private BaseViewObject<SrmOkcContractPaymentsEntity_HI_RO> srmOkcContractPaymentsDAO_HI_RO;

    @Autowired
    private ViewObject<SrmOkcContractsEntity_HI> srmOkcContractsDAO_HI;

    @Autowired
    private BaseViewObject<SrmOkcContractsEntity_HI_RO> srmOkcContractsDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPosSupplierContactsEntity_HI_RO> srmPosSupplierContactsDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPosSupplierBankEntity_HI_RO> srmPosSupplierBankDAO_HI_RO;

    private static final String configFilePath = "/fdfs_client.properties";

    @Autowired
    private ISaafBaseResultFile iSaafBaseResultFile;//附件文件

    @Autowired
    private BaseViewObject<SaafBaseResultFileEntity_HI_RO> saafBaseResultFileDAO_HI_RO;

    @Autowired
    @Qualifier("commonDAO_HI_DY")
    private CommonDAO_HI_DY commonDAOHiDy;

    @Autowired
    private BaseViewObject<SrmPoHeadersEntity_HI_RO> SrmPoHeadersDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPoHeadersEntity_HI> srmPoHeadersDAO_HI;

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private ISrmOkcFreightExpenses iSrmOkcFreightExpenses;

    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;
    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值

    @Autowired
    private ViewObject<SrmPonBidHeadersEntity_HI> srmPonBidHeadersDAO_HI;
    @Autowired
    private ViewObject<SrmPonAuctionHeadersEntity_HI> srmPonAuctionHeadersDAO_HI;

    @Autowired
    private ViewObject<SaafEmployeesEntity_HI> saafEmployeesDAO_HI;

    @Autowired
    private EKPSyncService ekpSyncService;

    @Autowired
    private ViewObject<SrmPoHeaderArchivesEntity_HI> srmPoHeaderArchivesDAO_HI;

    @Autowired
    private ViewObject<SrmPoLineArchivesEntity_HI> srmPoLineArchivesDAO_HI;
    @Autowired
    private ViewObject<SrmPoLinesEntity_HI> srmPoLinesDAO_HI;

    /**
     * 保存合同交易方
     *
     * @param parameters
     * @return
     */
//    public Object saveSrmOkcContractParties(JSONObject parameters) throws Exception {
//        try {
//            SrmOkcContractPartiesEntity_HI srmOkcContractPartiesEntity_HI = JSON.parseObject(parameters.toString(), SrmOkcContractPartiesEntity_HI.class);
//            Object resultData = srmOkcContractPartiesDAO_HI.save(srmOkcContractPartiesEntity_HI);
//            return resultData;
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//    }

    /**
     * 查询合同的交易方
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmOkcContractPartiesEntity_HI_RO> findSrmOkcContractPartiesList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcContractPartiesEntity_HI_RO.SRM_OKC_CONTRACT_PARTIES_QUERY_SQL);
            Map<String, Object> map = new HashMap<>();
            map.put("contractId", parameters.get("contractId"));
            List<SrmOkcContractPartiesEntity_HI_RO> resultSet = srmOkcContractPartiesDAO_HI_RO.findList(queryString, map);
            return resultSet;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同的交易方异常：" + e.getMessage());
        }
    }

    /**
     * 查询合同交易方联系人
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosSupplierContactsEntity_HI_RO> findSrmOkcContractPerson(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT * FROM srm_pos_supplier_contacts t WHERE t.supplier_id = :supplierId ");
            queryParamMap.put("supplierId", parameters.getInteger("supplierId"));
            String countSql = "select count(1) from (" + sb + ")";
            Pagination<SrmPosSupplierContactsEntity_HI_RO> c = srmPosSupplierContactsDAO_HI_RO.findPagination(sb, countSql, queryParamMap, pageIndex, pageRows);
            return c;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询供应商合同列表异常：" + e.getMessage());
        }
    }

    /**
     * 查询合同银行
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosSupplierBankEntity_HI_RO> findSrmOkcContractBank(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            queryParamMap.put("supplierId", parameters.getInteger("supplierId"));
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT * FROM srm_pos_supplier_bank t WHERE t.supplier_id = :supplierId ");
            String countSql = "select count(1) from (" + sb + ")";
            Pagination<SrmPosSupplierBankEntity_HI_RO> a = srmPosSupplierBankDAO_HI_RO.findPagination(sb.toString(), countSql, queryParamMap, pageIndex, pageRows);
            return a;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同银行异常：" + e.getMessage());
        }
    }

    /**
     * Description：合同查询供应商地点
     *
     * @param parameters 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public Pagination<JSONObject> findSrmOkcContractSite(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        String sql =
                "SELECT\n" +
                        "  pss.supplier_site_id AS supplierSiteId,\n" +
                        "  pss.site_name AS siteName,\n" +
                        "  psa.detail_address AS detailAddress,\n" +
                        "  sfi.inst_name AS orgName\n" +
                        "FROM\n" +
                        "  srm_pos_supplier_sites pss,\n" +
                        "  srm_pos_supplier_addresses psa,\n" +
                        "  saaf_institution sfi\n" +
                        "WHERE pss.supplier_address_id = psa.supplier_address_id\n" +
                        "  AND pss.org_id = sfi.inst_id\n" +
                        "  AND pss.site_status = 'EFFECTIVE'\n" +
                        "  AND pss.supplier_id = :supplierId";
        try {
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            queryParamMap.put("supplierId", parameters.getInteger("supplierId"));
            StringBuffer sb = new StringBuffer();
            sb.append(sql);
            String countSql = "select count(1) from (" + sb + ")";
            Pagination<JSONObject> a = commonDAOHiDy.findPagination(sb.toString(), countSql, queryParamMap, pageIndex, pageRows);
            return a;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("合同查询供应商地点异常：" + e.getMessage());
        }
    }

    /**
     * 根据ID查询合同交易方
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SrmOkcContractPartiesEntity_HI_RO findSrmOkcContractPartiesById(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcContractPartiesEntity_HI_RO.SRM_OKC_CONTRACT_PARTIES_QUERY_SQL);
            queryString.append(" AND su.user_id = :userId");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", parameters.getInteger("userId"));
            SrmOkcContractPartiesEntity_HI_RO srmOkcContractPartiesEntity_HI_RO = srmOkcContractPartiesDAO_HI_RO.findList(queryString, map).get(0);
            return srmOkcContractPartiesEntity_HI_RO;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同交易方异常：" + e.getMessage());
        }
    }

    /**
     * 保存合同的标的物
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Object saveSrmOkcContractItems(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            SrmOkcContractItemsEntity_HI srmOkcContractItemsEntity_HI = JSON.parseObject(parameters.toString(), SrmOkcContractItemsEntity_HI.class);
            Object resultData = srmOkcContractItemsDAO_HI.save(srmOkcContractItemsEntity_HI);
            return resultData;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("保存合同的标的物异常：" + e.getMessage());
        }
    }

    /**
     * Description：查询合同标的物列表
     *
     * @param parameters 查询条件参数
     * @param pageIndex  页码
     * @param pageRows   页行数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public List<SrmOkcContractItemsEntity_HI_RO> findSrmOkcContractItemsList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcContractItemsEntity_HI_RO.SRM_OKC_CONTRACT_ITEMS_QUERY_SQL);
            Map<String, Object> map = new HashMap<>();
            map.put("contractId", parameters.get("contractId"));
            // map.put("varUserId", parameters.getInteger("varUserId"));
            List<SrmOkcContractItemsEntity_HI_RO> resultSet = srmOkcContractItemsDAO_HI_RO.findList(queryString, map);
            return resultSet;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同标的物列表异常：" + e.getMessage());
        }
    }


    /**
     * 查询合同的标的物
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SrmOkcContractItemsEntity_HI_RO findSrmOkcContractItemsById(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcContractItemsEntity_HI_RO.SRM_OKC_CONTRACT_ITEMS_QUERY_SQL);
            queryString.append(" AND su.user_id = :userId");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", parameters.getInteger("userId"));
            SrmOkcContractItemsEntity_HI_RO srmOkcContractItemsEntity_HI_RO = srmOkcContractItemsDAO_HI_RO.findList(queryString, map).get(0);
            return srmOkcContractItemsEntity_HI_RO;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同的标的物异常：" + e.getMessage());
        }
    }

    /**
     * 删除合同的标的物
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public void deleteSrmOkcContractItem(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            SrmOkcContractItemsEntity_HI r = (SrmOkcContractItemsEntity_HI) srmOkcContractItemsDAO_HI.getById(parameters.getInteger("contractItemId"));
            if (r != null) {
                srmOkcContractItemsDAO_HI.delete(r);
            }
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("删除合同的标的物异常：" + e.getMessage());
        }
    }

    /**
     * 删除运输方式
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public void deleteSrmOkcContractFreightExpense(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            SrmOkcContractFreightsEntity_HI r = (SrmOkcContractFreightsEntity_HI) srmOkcContractFreightsDAO_HI.getById(parameters.getInteger("contractFreightId"));
            if (r != null) {
                srmOkcContractFreightsDAO_HI.delete(r);
            }
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("删除运价单异常：" + e.getMessage());
        }
    }

    /**
     * 删除合同付款阶段
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public void deleteSrmOkcContractPayment(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            SrmOkcContractPaymentsEntity_HI r = (SrmOkcContractPaymentsEntity_HI) srmOkcContractPaymentsDAO_HI.getById(parameters.getInteger("paymentStageId"));
            if (r != null) {
                srmOkcContractPaymentsDAO_HI.delete(r);
            }
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("删除合同付款方式异常：" + e.getMessage());
        }
    }

    /**
     * 保存合同文本
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Object saveSrmOkcContractTexts(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            SrmOkcContractTextsEntity_HI srmOkcContractTextsEntity_HI = JSON.parseObject(parameters.toString(), SrmOkcContractTextsEntity_HI.class);
            srmOkcContractTextsEntity_HI.setVersionNum(new Integer(1));
            srmOkcContractTextsEntity_HI.setOperatorUserId(parameters.getInteger("varUserId"));
            srmOkcContractTextsEntity_HI.setTemplateModifyFlag("Y");
            Object resultData = srmOkcContractTextsDAO_HI.save(srmOkcContractTextsEntity_HI);
            return resultData;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("保存合同文本异常：" + e.getMessage());
        }
    }

    /**
     * 保存合同文本
     *
     * @param text
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Object saveSrmOkcContractTexts(SrmOkcContractTextsEntity_HI text) throws Exception {
        try {
            srmOkcContractTextsDAO_HI.saveOrUpdate(text);
            return text.getTextFileId();
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("保存合同文本异常：" + e.getMessage());
        }
    }

    /**
     * 查询合同文本
     *
     * @param parameters
     * @param pageIndex  页码
     * @param pageRows   页行数
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmOkcContractTextsEntity_HI_RO> findSrmOkcContractTextsList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcContractTextsEntity_HI_RO.SRM_OKC_CONTRACT_TEXTS_QUERY_SQL);
            Map<String, Object> map = new HashMap<>();
            map.put("contractId", parameters.get("contractId"));
            List<SrmOkcContractTextsEntity_HI_RO> resultSet = srmOkcContractTextsDAO_HI_RO.findList(queryString, map);
            return resultSet;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同文本异常：" + e.getMessage());
        }
    }

    /**
     * 根据ID查询合同文本
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SrmOkcContractTextsEntity_HI_RO findSrmOkcContractTextsById(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcContractTextsEntity_HI_RO.SRM_OKC_CONTRACT_TEXTS_QUERY_SQL);
            queryString.append(" AND su.user_id = :userId");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", parameters.getInteger("userId"));
            SrmOkcContractTextsEntity_HI_RO srmOkcContractTextsEntity_HI_RO = srmOkcContractTextsDAO_HI_RO.findList(queryString, map).get(0);
            return srmOkcContractTextsEntity_HI_RO;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同文本异常：" + e.getMessage());
        }
    }

    /**
     * 保存合同运货单
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Object saveSrmOkcContractFreights(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            SrmOkcContractFreightsEntity_HI srmOkcContractFreightsEntity_HI = JSON.parseObject(parameters.toString(), SrmOkcContractFreightsEntity_HI.class);
            Object resultData = srmOkcContractFreightsDAO_HI.save(srmOkcContractFreightsEntity_HI);
            return resultData;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同运货单异常：" + e.getMessage());
        }
    }

    /**
     * 查询运输方式列表
     *
     * @param parameters
     * @param pageIndex  页码
     * @param pageRows   页行数
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmOkcContractFreightsEntity_HI_RO> findSrmOkcContractFreightsList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcContractFreightsEntity_HI_RO.SRM_OKC_CONTRACT_FREIGHTS_QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("contractId", parameters.get("contractId"));
            List<SrmOkcContractFreightsEntity_HI_RO> resultSet = srmOkcContractFreightsDAO_HI_RO.findList(queryString, map);
            return resultSet;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同运货单异常：" + e.getMessage());
        }
    }

    /**
     * 根据ID查询合同运货单
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SrmOkcContractFreightsEntity_HI_RO findSrmOkcContractFreightsById(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcContractFreightsEntity_HI_RO.SRM_OKC_CONTRACT_FREIGHTS_QUERY_SQL);
            queryString.append(" AND su.user_id = :userId");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", parameters.getInteger("userId"));
            SrmOkcContractFreightsEntity_HI_RO srmOkcContractFreightsEntity_HI_RO = srmOkcContractFreightsDAO_HI_RO.findList(queryString, map).get(0);
            return srmOkcContractFreightsEntity_HI_RO;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同运货单异常：" + e.getMessage());
        }
    }


    /**
     * 保存合同附件
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Object saveSrmOkcContractAttachments(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            SrmOkcContractAttachmentsEntity_HI srmOkcContractAttachmentsEntity_HI = JSON.parseObject(parameters.toString(), SrmOkcContractAttachmentsEntity_HI.class);
            srmOkcContractAttachmentsEntity_HI.setVersionNum(new Integer(1));
            srmOkcContractAttachmentsEntity_HI.setOperatorUserId(parameters.getInteger("varUserId"));
            srmOkcContractAttachmentsDAO_HI.saveOrUpdate(srmOkcContractAttachmentsEntity_HI);
            return srmOkcContractAttachmentsEntity_HI;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("保存合同附件异常：" + e.getMessage());
        }
    }

    /**
     * 查询合同附件
     *
     * @param parameters
     * @param pageIndex  页码
     * @param pageRows   页行数
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmOkcContractAttachmentsEntity_HI_RO> findSrmOkcContractAttachmentsList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcContractAttachmentsEntity_HI_RO.SRM_OKC_CONTRACT_ATTACHMENTS_QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("contractId", parameters.get("contractId"));
            List<SrmOkcContractAttachmentsEntity_HI_RO> resultSet = srmOkcContractAttachmentsDAO_HI_RO.findList(queryString, map);
            return resultSet;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同附件异常：" + e.getMessage());
        }
    }

    /**
     * 根据ID查询合同附件
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SrmOkcContractAttachmentsEntity_HI_RO findSrmOkcContractAttachmentsById(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcContractAttachmentsEntity_HI_RO.SRM_OKC_CONTRACT_ATTACHMENTS_QUERY_SQL);
            queryString.append(" AND su.user_id = :userId");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", parameters.getInteger("userId"));
            SrmOkcContractAttachmentsEntity_HI_RO srmOkcContractAttachmentsEntity_HI_RO = srmOkcContractAttachmentsDAO_HI_RO.findList(queryString, map).get(0);
            return srmOkcContractAttachmentsEntity_HI_RO;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同附件异常：" + e.getMessage());
        }
    }

    /**
     * 保存合同付款方式
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Object saveSrmOkcContractPayments(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            SrmOkcContractPaymentsEntity_HI srmOkcContractPaymentsEntity_HI = JSON.parseObject(parameters.toString(), SrmOkcContractPaymentsEntity_HI.class);
            Object resultData = srmOkcContractPaymentsDAO_HI.save(srmOkcContractPaymentsEntity_HI);
            return resultData;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("保存合同付款方式异常：" + e.getMessage());
        }
    }

    /**
     * 查询该合同的付款阶段信息
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmOkcContractPaymentsEntity_HI_RO> findSrmOkcContractPaymentsList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcContractPaymentsEntity_HI_RO.SRM_OKC_CONTRACT_PAYMENTS_QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            int contractId = -1;
            if (parameters.get("contractId") != null && !"".equals(parameters.get("contractId"))) {
                contractId = parameters.getInteger("contractId");
            }
            map.put("contractId", contractId);
            List<SrmOkcContractPaymentsEntity_HI_RO> resultSet = srmOkcContractPaymentsDAO_HI_RO.findList(queryString, map);
            return resultSet;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同的付款阶段信息异常：" + e.getMessage());
        }
    }

    /**
     * 根据合同ID，查询该合同的付款阶段信息
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SrmOkcContractPaymentsEntity_HI_RO findSrmOkcContractPaymentsById(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcContractPaymentsEntity_HI_RO.SRM_OKC_CONTRACT_PAYMENTS_QUERY_SQL);
            queryString.append(" AND su.user_id = :userId");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", parameters.getInteger("userId"));
            SrmOkcContractPaymentsEntity_HI_RO srmOkcContractPaymentsEntity_HI_RO = srmOkcContractPaymentsDAO_HI_RO.findList(queryString, map).get(0);
            return srmOkcContractPaymentsEntity_HI_RO;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同的付款阶段信息异常：" + e.getMessage());
        }
    }

    /**
     * 查询合同采购订单
     *
     * @param jsonParams
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmPoHeadersEntity_HI_RO> findSrmOkcContractPoList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.debug("参数:==========>>>{}", jsonParams);
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmPoHeadersEntity_HI_RO.CONTRACT_PO_REL);
            queryParamMap.put("contractId", jsonParams.get("contractId"));
            List<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findList(queryString.toString(),
                    queryParamMap);
            return result;
        } catch (Exception e) {
            //e.printStackTrace();
            //throw new Exception(e);
            throw new UtilsException("查询合同采购订单异常：" + e.getMessage());
        }
    }

    /**
     * 更新合同状态
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public void updateSrmOkcContractStatus(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        SrmOkcContractsEntity_HI srmOkcContractsEntity_HI = srmOkcContractsDAO_HI.getById(parameters.getInteger("contractId"));
        String contractApprovalStatus = parameters.getString("contractApprovalStatus");
        String contractStatus = parameters.getString("contractStatus");
        srmOkcContractsEntity_HI.setContractStatus(contractStatus);
        srmOkcContractsEntity_HI.setContractApprovalStatus(contractApprovalStatus);
        Integer operaterId = parameters.getInteger("operatorUserId") != null ? parameters.getInteger("operatorUserId") : -1;
        srmOkcContractsEntity_HI.setOperatorUserId(operaterId);
        srmOkcContractsDAO_HI.update(srmOkcContractsEntity_HI);
    }

    /**
     * Description：保存合同
     *
     * @param parameters 参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public Object saveSrmOkcContracts(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        Integer bidHeaderId = parameters.getInteger("bidHeaderId");
        Integer userId = parameters.getInteger("varUserId");
        try {
            SrmOkcContractsEntity_HI srmOkcContractsEntity_HI = JSON.parseObject(parameters.toString(), SrmOkcContractsEntity_HI.class);
            boolean newFlag = srmOkcContractsEntity_HI.getContractId() == null ? true : false;
            if (newFlag) {
                String seq = saafSequencesUtil.getDocSequences("srm_contract_headers".toUpperCase(),
                        "HTBH-" + DateUtil.date2Str(new Date(), "yyyyMMdd") + "-", 4);
                srmOkcContractsEntity_HI.setContractCode(seq);
                srmOkcContractsEntity_HI.setVersionNum(0);

            }

            /*注释于 20190730，存在逻辑Bug
            if (newFlag) {
                Integer fileId = null;
                if (srmOkcContractsEntity_HI.getTemplateId() != null) {
                    SaafBaseResultFileEntity_HI_RO saafBaseResultFileEntity_hi_ro = findTemplateFileById(srmOkcContractsEntity_HI.getTemplateId());
                    if (saafBaseResultFileEntity_hi_ro != null) {
                        fileId = saafBaseResultFileEntity_hi_ro.getFileId();
                    }
                }

                if (fileId == null) {
                    WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
                    MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();

                    mdp.addParagraphOfText("合同名称:@@contactName@@");
                    mdp.addParagraphOfText("合同编号:@@contactCode@@");
                    mdp.addParagraphOfText("甲方:@@partyA@@");
                    mdp.addParagraphOfText("乙方:@@partyB@@");
                    mdp.addParagraphOfText("丙方:@@partyC@@");
                    mdp.addParagraphOfText("付款条件:@@payTerm@@");
                    mdp.addParagraphOfText("合同金额:@@totalAmount@@");

                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    Docx4J.save(wordMLPackage, outStream, Docx4J.FLAG_SAVE_ZIP_FILE);
                    FileManagerUtils fileManager = FileManagerUtils.getInstance(configFilePath);
                    FastDFSFileEntity DFSFileEntity = new FastDFSFileEntity(srmOkcContractsEntity_HI.getContractName(), outStream.toByteArray(), "docx");
                    ResutlFileEntity resutlFileEntity = fileManager.uploadFile2FastDFS(DFSFileEntity);
                    SaafBaseResultFileEntity_HI row = iSaafBaseResultFile.saveResult(resutlFileEntity, srmOkcContractsEntity_HI.getOperatorUserId());
                    fileId = row.getFileId();
                }

                //存放编辑中的合同
                srmOkcContractsEntity_HI.setAttribute16(fileId);
            } else {
                HashMap<String, Object> searchParam = new HashMap<>();
                searchParam.put("contractId", srmOkcContractsEntity_HI.getContractId());
                List<SrmOkcContractsEntity_HI_RO> oldSrmOkcContractsEntitys = srmOkcContractsDAO_HI_RO.findList("select * from srm_okc_contracts where contract_id = :contractId", searchParam);
                if (oldSrmOkcContractsEntitys != null && oldSrmOkcContractsEntitys.size() == 1) {

                    SrmOkcContractsEntity_HI_RO oldSrmOkcContractsEntity_HI = oldSrmOkcContractsEntitys.get(0);
                    srmOkcContractsEntity_HI.setAttribute16(oldSrmOkcContractsEntity_HI.getAttribute16());

                    if (srmOkcContractsEntity_HI.getTemplateId() != null && oldSrmOkcContractsEntity_HI.getTemplateId() != null) {
                        if (srmOkcContractsEntity_HI.getTemplateId().intValue() != oldSrmOkcContractsEntity_HI.getTemplateId().intValue()) {
                            SaafBaseResultFileEntity_HI_RO saafBaseResultFileEntity_hi_ro = findTemplateFileById(srmOkcContractsEntity_HI.getTemplateId());
                            if (saafBaseResultFileEntity_hi_ro != null) {
                                srmOkcContractsEntity_HI.setAttribute16(saafBaseResultFileEntity_hi_ro.getFileId());
                            }
                        }
                    }
                }
            }*/

            //合同模板的处理，如果没有选择合同模板，那么自动生成一个
            Integer fileId = null;
            if (srmOkcContractsEntity_HI.getTemplateId() != null) {
                SaafBaseResultFileEntity_HI_RO saafBaseResultFileEntity_hi_ro = findTemplateFileById(srmOkcContractsEntity_HI.getTemplateId());
                if (saafBaseResultFileEntity_hi_ro != null) {
                    fileId = saafBaseResultFileEntity_hi_ro.getFileId();
                }
            } else {
                HashMap<String, Object> searchParam = new HashMap<>();
                StringBuffer sql = new StringBuffer("select * from srm_okc_contracts where 1=1 and contract_id = :contractId ");
                if (null != srmOkcContractsEntity_HI.getContractId()) {
                    searchParam.put("contractId", srmOkcContractsEntity_HI.getContractId());
                } else {
                    searchParam.put("contractId", "");
                }
                //searchParam.put("contractId", srmOkcContractsEntity_HI.getContractId());
                List<SrmOkcContractsEntity_HI_RO> oldSrmOkcContractsEntitys = srmOkcContractsDAO_HI_RO.findList(sql.toString(), searchParam);
                if (oldSrmOkcContractsEntitys != null && oldSrmOkcContractsEntitys.size() == 1) {
                    SrmOkcContractsEntity_HI_RO oldSrmOkcContractsEntity_HI = oldSrmOkcContractsEntitys.get(0);
                    if (oldSrmOkcContractsEntity_HI.getAttribute16() != null && oldSrmOkcContractsEntity_HI.getAttribute16() > 0) {
                        fileId = oldSrmOkcContractsEntity_HI.getAttribute16();
                    } else {
                        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
                        MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();

                        mdp.addParagraphOfText("合同名称:@@contactName@@");
                        mdp.addParagraphOfText("合同编号:@@contactCode@@");
                        mdp.addParagraphOfText("甲方:@@partyA@@");
                        mdp.addParagraphOfText("乙方:@@partyB@@");
                        mdp.addParagraphOfText("丙方:@@partyC@@");
                        mdp.addParagraphOfText("付款条件:@@payTerm@@");
                        mdp.addParagraphOfText("合同金额:@@totalAmount@@");

                        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                        Docx4J.save(wordMLPackage, outStream, Docx4J.FLAG_SAVE_ZIP_FILE);
                        FileManagerUtils fileManager = FileManagerUtils.getInstance(configFilePath);
                        FastDFSFileEntity DFSFileEntity = new FastDFSFileEntity(srmOkcContractsEntity_HI.getContractName(), outStream.toByteArray(), "docx");
                        ResutlFileEntity resutlFileEntity = fileManager.uploadFile2FastDFS(DFSFileEntity);
                        SaafBaseResultFileEntity_HI row = iSaafBaseResultFile.saveResult(resutlFileEntity, srmOkcContractsEntity_HI.getOperatorUserId());
                        fileId = row.getFileId();
                    }
                }
            }
            srmOkcContractsEntity_HI.setAttribute16(fileId);
            srmOkcContractsDAO_HI.saveOrUpdate(srmOkcContractsEntity_HI);
            srmOkcContractsDAO_HI.fluch();
            //交易方
            JSONArray t = parameters.getJSONArray("srmOkcContractsPartiesTable");
            if (t != null) {
                int s = t.size();
                for (int i = 0; i < s; i++) {
                    JSONObject o = t.getJSONObject(i);
                    SrmOkcContractPartiesEntity_HI srmOkcContractPartiesEntity_hi = JSON.parseObject(o.toJSONString(), SrmOkcContractPartiesEntity_HI.class);
                    srmOkcContractPartiesEntity_hi.setContractId(srmOkcContractsEntity_HI.getContractId());
                    srmOkcContractPartiesEntity_hi.setOperatorUserId(srmOkcContractsEntity_HI.getOperatorUserId());
                    if (srmOkcContractPartiesEntity_hi.getContractPartyId() == null) {
                        srmOkcContractPartiesEntity_hi.setVersionNum(1);
                    }
                    srmOkcContractPartiesDAO_HI.saveOrUpdate(srmOkcContractPartiesEntity_hi);
                }
            }

            //标的物
            t = parameters.getJSONArray("srmOkcContractsItemsTable");
            if (t != null) {
                int s = t.size();
                BigDecimal taxPrice = null;
                for (int i = 0; i < s; i++) {
                    JSONObject o = t.getJSONObject(i);
                    SrmOkcContractItemsEntity_HI srmOkcContractItemsEntity_hi = JSON.parseObject(o.toJSONString(), SrmOkcContractItemsEntity_HI.class);
                    if (srmOkcContractItemsEntity_hi.getTaxPrice() == null && srmOkcContractItemsEntity_hi.getTaxRate() != null && srmOkcContractItemsEntity_hi.getNonTaxPrice() != null) {
                        taxPrice = srmOkcContractItemsEntity_hi.getTaxRate().multiply(srmOkcContractItemsEntity_hi.getNonTaxPrice());
                        srmOkcContractItemsEntity_hi.setTaxPrice(taxPrice.setScale(6, BigDecimal.ROUND_HALF_UP));
                    }
                    srmOkcContractItemsEntity_hi.setContractId(srmOkcContractsEntity_HI.getContractId());
                    srmOkcContractItemsEntity_hi.setOperatorUserId(srmOkcContractsEntity_HI.getOperatorUserId());
                    if (srmOkcContractItemsEntity_hi.getContractItemId() == null) {
                        srmOkcContractItemsEntity_hi.setVersionNum(1);
                    }
                    srmOkcContractItemsDAO_HI.saveOrUpdate(srmOkcContractItemsEntity_hi);
                }
            }

            //运输方式
            t = parameters.getJSONArray("srmOkcFreightExpensesTable");
            if (t != null) {
                int s = t.size();
                Map<String, Object> queryParamMap = new HashMap<String, Object>();
                SrmOkcFreightExpensesEntity_HI_RO freightExpensesEntity_HI_RO = null;
                for (int i = 0; i < s; i++) {
                    JSONObject o = t.getJSONObject(i);
                    SrmOkcContractFreightsEntity_HI srmOkcContractFreightsEntity_HI = JSON.parseObject(o.toJSONString(), SrmOkcContractFreightsEntity_HI.class);
                    srmOkcContractFreightsEntity_HI.setSupplierId(srmOkcContractsEntity_HI.getPartyBId());
                    srmOkcContractFreightsEntity_HI.setContractId(srmOkcContractsEntity_HI.getContractId());
                    srmOkcContractFreightsEntity_HI.setOperatorUserId(srmOkcContractsEntity_HI.getOperatorUserId());
                    if (srmOkcContractFreightsEntity_HI.getContractFreightId() == null) {
                        srmOkcContractFreightsEntity_HI.setVersionNum(1);
                    }
                    queryParamMap.put("carrierId", srmOkcContractFreightsEntity_HI.getCarrierId());
                    queryParamMap.put("supplierId", srmOkcContractFreightsEntity_HI.getSupplierId());
                    queryParamMap.put("supplierSiteId", srmOkcContractFreightsEntity_HI.getSupplierSiteId());
                    queryParamMap.put("organizationId", srmOkcContractFreightsEntity_HI.getOrganizationId());
                    queryParamMap.put("transportMethod", srmOkcContractFreightsEntity_HI.getTransportMethod());
                    queryParamMap.put("uomCode", srmOkcContractFreightsEntity_HI.getUomCode());
                    freightExpensesEntity_HI_RO = iSrmOkcFreightExpenses.findFreightPrice(queryParamMap);
                    if (freightExpensesEntity_HI_RO != null) {
                        srmOkcContractFreightsEntity_HI.setFreightPrice(freightExpensesEntity_HI_RO.getFreightPrice());
                    }
                    srmOkcContractFreightsDAO_HI.saveOrUpdate(srmOkcContractFreightsEntity_HI);
                }
            }

            //付款阶段
            t = parameters.getJSONArray("srmOkcContractPaymentsTable");
            if (t != null) {
                int s = t.size();
                for (int i = 0; i < s; i++) {
                    JSONObject o = t.getJSONObject(i);
                    SrmOkcContractPaymentsEntity_HI srmOkcContractPaymentsEntity_hi = JSON.parseObject(o.toJSONString(), SrmOkcContractPaymentsEntity_HI.class);
                    srmOkcContractPaymentsEntity_hi.setContractId(srmOkcContractsEntity_HI.getContractId());
                    srmOkcContractPaymentsEntity_hi.setOperatorUserId(srmOkcContractsEntity_HI.getOperatorUserId());
                    if (srmOkcContractPaymentsEntity_hi.getPaymentStageId() == null) {
                        srmOkcContractPaymentsEntity_hi.setVersionNum(1);
                    }
                    srmOkcContractPaymentsDAO_HI.saveOrUpdate(srmOkcContractPaymentsEntity_hi);
                }
            }


            //招标询价转合同，回写招标询价转合同标识
            if (!ObjectUtils.isEmpty(bidHeaderId)) {
                SrmPonBidHeadersEntity_HI bidHeader = srmPonBidHeadersDAO_HI.getById(bidHeaderId);
                bidHeader.setOkcCreateFlag("Y");
                bidHeader.setOkcCreateDate(new Date());
                bidHeader.setOperatorUserId(userId);
                srmPonBidHeadersDAO_HI.saveOrUpdate(bidHeader);
                Integer auctionHeaderId = bidHeader.getAuctionHeaderId();
                List<SrmPonBidHeadersEntity_HI> bidHeaderList = srmPonBidHeadersDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
                Boolean flag = true;
                if (!ObjectUtils.isEmpty(bidHeaderList)) {
                    for (int n = 0; n < bidHeaderList.size(); n++) {
                        SrmPonBidHeadersEntity_HI bid = bidHeaderList.get(n);
                        if (!bidHeader.getBidHeaderId().equals(bid.getBidHeaderId()) && "4".equals(bid.getAwardStatus()) && !"Y".equals(bid.getOkcCreateFlag()) && "ACT".equals(bid.getBidStatus())) {
                            flag = false;
                        }
                    }
                }
                if (flag) {
                    SrmPonAuctionHeadersEntity_HI auctionHeader = srmPonAuctionHeadersDAO_HI.getById(auctionHeaderId);
                    auctionHeader.setOkcCreateDate(new Date());
                    auctionHeader.setOkcCreateFlag("Y");
                    auctionHeader.setOperatorUserId(userId);
                    srmPonAuctionHeadersDAO_HI.saveOrUpdate(auctionHeader);
                }
            }

            //提交发送EKP审批
            if ("W1".equals(srmOkcContractsEntity_HI.getContractType()) && "2".equals(srmOkcContractsEntity_HI.getContractApprovalStatus())) {
                //获取EKP流程编号
                JSONArray jsonArray = findEkpId(srmOkcContractsEntity_HI.getEkpNumber());
                if (!ObjectUtils.isEmpty(jsonArray)) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    if ("W1".equals(srmOkcContractsEntity_HI.getContractType())) {
                        //查询业务实体区域
                        StringBuffer instRegionString = new StringBuffer(SrmOkcContractsEntity_HI_RO.QUERY_INST_REGION);
                        instRegionString.append(" and  siv.Inst_Id=" + srmOkcContractsEntity_HI.getPartyAId());
                        List<SrmOkcContractsEntity_HI_RO> instRegionList = srmOkcContractsDAO_HI_RO.findList(instRegionString.toString());
                        if (null == instRegionList || instRegionList.isEmpty()) {
                            throw new RuntimeException("无此甲方组织");
                        }
                        String instRegion = instRegionList.get(0).getInstRegion();

                        Map<String, Object> mapV = new HashMap<>();
                        mapV.put("tag", "10");
                        if ("SHENZHEN".equals(instRegion)) { //深圳
                            mapV.put("lookupType", "SZ_CONTRACT_EKP_NODE");
                        } /*else if ("NANTONG".equals(instRegion)) { //南通
                        mapV.put("lookupType", "BIDDING_INQUIRY_EKP_NODE_NT");
                    } else if ("WUXI".equals(instRegion)) {//无锡
                        mapV.put("lookupType", "BIDDING_INQUIRY_EKP_NODE_WX");
                    }*/
                        List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
                        String node = "";
                        if (null != lookupValueList && lookupValueList.size() > 0) {
                            node = lookupValueList.get(0).getLookupCode();
                        } else {
                            throw new UtilsException("请在快码中配置合同EKP流程接入节点！");
                        }
                        if (!ObjectUtils.isEmpty(jsonObject.getString("fdFactNodeId")) && !ObjectUtils.isEmpty(jsonObject.getString("fdStatus")) && node.equals(jsonObject.getString("fdFactNodeId")) && "20".equals(jsonObject.getString("fdStatus"))) {
                            srmOkcContractsEntity_HI.setAttribute1(jsonObject.getString("fdId"));
                            srmOkcContractsEntity_HI.setOperatorUserId(userId);
                            srmOkcContractsDAO_HI.saveEntity(srmOkcContractsEntity_HI);
                            srmOkcContractsDAO_HI.fluch();
                            //更新EKP流程
                            JSONObject data = saveOkcContractsToEkp(srmOkcContractsEntity_HI.getContractId(), userId, srmOkcContractsEntity_HI.getContractType());
                            if (!"S".equals(data.getString("status"))) {
                                throw new UtilsException(data.getString("msg"));
                            }
                        } else {
                            throw new UtilsException("当前EKP流程节点非" + node + "或审批状态非审批中！");
                        }
                    }

                }
            }


            return srmOkcContractsEntity_HI;
        } catch (Exception e) {
            //e.printStackTrace();
            //throw new Exception(e);
            throw new UtilsException("保存合同异常：" + e.getMessage());
        }
    }

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
    @Override
    public JSONObject saveOkcContractsToEkp(Integer contractId, Integer userId, String contractType) throws Exception {
        JSONObject obj = new JSONObject();
        if (null == contractId || "".equals(contractId)) {
            throw new UtilsException("传输EKP所需参数为空！");
        }
        SrmOkcContractsEntity_HI header = srmOkcContractsDAO_HI.getById(contractId);
        if (null == header || "".equals(header)) {
            throw new UtilsException("无此记录，已被删除！");
        }

        String employeeNumber = "";
        List<SaafEmployeesEntity_HI> employeeList = saafEmployeesDAO_HI.findByProperty("userId", userId);
        if (null == employeeList || employeeList.isEmpty()) {
            throw new UtilsException("该用户没有对应员工信息！");
        }
        employeeNumber = employeeList.get(0).getEmployeeNumber();
        if (null == employeeNumber || "".equals(employeeNumber)) {
            throw new UtilsException("查无此员工工号！");
        }
        //查询EKP编号
        StringBuffer ekpSql = new StringBuffer(SrmOkcContractsEntity_HI_RO.QUERY_EKP_NUMBER);
        ekpSql.append(" and soc.Ekp_Number= '" + header.getEkpNumber() + "' ");
        ekpSql.append(" and soc.contract_id != " + header.getContractId() + " ");
        List<SrmOkcContractsEntity_HI_RO> ekp = srmOkcContractsDAO_HI_RO.findList(ekpSql.toString());

        //查询快码值对应的流程模板Id
        String tempateId = "";
        //String itemType = params.getString("contractType");
        Map<String, Object> slvMap = new HashMap<>();
        slvMap.put("lookupType", "SER_EKF_FD_DATA");
        List<SaafLookupValuesEntity_HI> slvList = saafLookupValuesDAO_HI.findByProperty(slvMap);
        if (null == slvList || slvList.size() < 1) {
            throw new UtilsException("没有对应的流程模板Id！");
        }
        //查询业务实体区域
        StringBuffer instRegionString = new StringBuffer(SrmOkcContractsEntity_HI_RO.QUERY_INST_REGION);
        instRegionString.append(" and  siv.Inst_Id=" + header.getPartyAId());
        List<SrmOkcContractsEntity_HI_RO> instRegionList = srmOkcContractsDAO_HI_RO.findList(instRegionString.toString());
        if (null == instRegionList || instRegionList.isEmpty()) {
            throw new UtilsException("无此甲方组织");
        }
        String instRegion = instRegionList.get(0).getInstRegion();

        //工程/IT
        for (int i = 0; i < slvList.size(); i++) {
            //区域
            if ("W1".equals(contractType)) {
                if ("SHENZHEN".equals(instRegion) && "SER_SZ_SPO_PROJECT".equals(slvList.get(i).getLookupCode())) { //深圳
                    tempateId = slvList.get(i).getMeaning();
                    break;
                } else if ("NANTONG".equals(instRegion) && "SER_NT_SPO_PROJECT".equals(slvList.get(i).getLookupCode())) { //南通
                    tempateId = slvList.get(i).getMeaning();
                    break;
                } else if ("WUXI".equals(instRegion) && "SER_WX_SPO_PROJECT".equals(slvList.get(i).getLookupCode())) {//无锡
                    tempateId = slvList.get(i).getMeaning();
                    break;
                }
            }

        }

        Map<String, Object> map = new HashMap<>();
        // 文件附件列表
        Map<String, Map<String, byte[]>> fileMap = new HashMap<>();
        //行附件列表
        JSONArray lineFileArray = new JSONArray();
        //头层的附件

        List<String> contractCodeList = new ArrayList<>();//合同编号
        List<String> contractNameList = new ArrayList<>();//合同名称
        List<String> partyBNameList = new ArrayList<>();//签订供应商
        List<String> totalAmountList = new ArrayList<>();//合同金额
        List<String> commentsList = new ArrayList<>();//备注
        //传Header
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("contractId", header.getContractId());
        SrmOkcContractsEntity_HI_RO headerInfo = findSrmOkcContractsById(jsonParams);
        if (!ObjectUtils.isEmpty(headerInfo)) {
            contractCodeList.add(headerInfo.getContractCode());
            contractNameList.add(headerInfo.getContractName());
            partyBNameList.add(headerInfo.getPartyBName());
            totalAmountList.add(String.valueOf(headerInfo.getTotalAmount()));
            commentsList.add(headerInfo.getComments());
        }

        //传已驳回状态
        if (!ObjectUtils.isEmpty(ekp)) {
            for (SrmOkcContractsEntity_HI_RO ekpRo : ekp) {
                jsonParams.clear();
                jsonParams.put("contractId", header.getContractId());
                SrmOkcContractsEntity_HI_RO headerRejectInfo = findSrmOkcContractsById(jsonParams);
                if (!ObjectUtils.isEmpty(headerRejectInfo)) {
                    contractCodeList.add(headerRejectInfo.getContractCode());
                    contractNameList.add(headerRejectInfo.getContractName());
                    partyBNameList.add(headerRejectInfo.getPartyBName());
                    totalAmountList.add(String.valueOf(headerRejectInfo.getTotalAmount()));
                    commentsList.add(headerRejectInfo.getComments());
                }

            }
        }
        //查询快码
        Map<String, Object> mapV = new HashMap<>();
        mapV.put("lookupType", "SZ_CONTRACT_EKP_FIELDS");
        List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
        if (!ObjectUtils.isEmpty(lookupValueList)) {
            String lineNumber = "";
            for (int i = 0; i < lookupValueList.size(); i++) {
                SaafLookupValuesEntity_HI interfaceFields = lookupValueList.get(i);
                if ("lineNumber".equals(interfaceFields.getTag())) {
                    lineNumber = interfaceFields.getLookupCode();
                }
            }

            if (!ObjectUtils.isEmpty(contractCodeList)) {
                if (!StringUtils.isEmpty(lineNumber)) {
                    for (int i = 0; i < lookupValueList.size(); i++) {
                        SaafLookupValuesEntity_HI interfaceFields = lookupValueList.get(i);
                        if ("contractCode".equals(interfaceFields.getTag())) {
                            map.put(lineNumber + "." + interfaceFields.getLookupCode(), contractCodeList);
                        }
                        if ("contractName".equals(interfaceFields.getTag())) {
                            map.put(lineNumber + "." + interfaceFields.getLookupCode(), contractNameList);
                        }
                        if ("partyBName".equals(interfaceFields.getTag())) {
                            map.put(lineNumber + "." + interfaceFields.getLookupCode(), partyBNameList);
                        }
                        if ("totalAmount".equals(interfaceFields.getTag())) {
                            map.put(lineNumber + "." + interfaceFields.getLookupCode(), totalAmountList);
                        }
                        if ("comments".equals(interfaceFields.getTag())) {
                            map.put(lineNumber + "." + interfaceFields.getLookupCode(), commentsList);
                        }
                    }
                } else {
                    throw new UtilsException("请维护合同EKP接口序号字段快码！");
                }
            }
            obj.put("ekpData", ekp);
        } else {
            throw new UtilsException("查询合同EKP接口字段快码值失败！");
        }
        //文档关键字
        List<String> descList = new ArrayList<String>(4);
        descList.add("【SRM】");
        descList.add("合同审批");
        descList.add(header.getContractName());
        descList.add(header.getContractCode());

        //调用EKP的启动流程
        String processId = ekpSyncService.createFlow("20", header.getAttribute1(), tempateId, "【SRM】合同审批" + header.getContractName() + header.getContractCode(), employeeNumber, descList, map, fileMap, lineFileArray, "PON");
        if (null == processId || "".equals(processId)) {
            throw new UtilsException("EKP没有返回值" + processId);
        }
        Boolean flag = ekpSyncService.isJSONValid(processId);
        if (flag) {
            throw new UtilsException("EKP返回值错误" + processId);
        }

        //回写状态
        if (!ObjectUtils.isEmpty(ekp)) {
            for (SrmOkcContractsEntity_HI_RO ro : ekp) {
                if (!header.getContractId().equals(ro.getContractId())) {
                    SrmOkcContractsEntity_HI roHeader = srmOkcContractsDAO_HI.getById(ro.getContractId());
                    roHeader.setAttribute1(processId);
                    roHeader.setContractStatus("2");
                    roHeader.setContractApprovalStatus("2");
                    roHeader.setOperatorUserId(userId);
                    srmOkcContractsDAO_HI.saveEntity(roHeader);
                    srmOkcContractsDAO_HI.fluch();
                }
            }
        }

        return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "操作成功", 0, obj);
    }

    /**
     * Description：通过EKP编号查询EKPID
     *
     * @return JSONArray
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-05-29         xiexiaoxia      创建
     * =======================================================================
     */
    private JSONArray findEkpId(String ekpNumber) throws Exception {
        JSONArray rows = null;
        String isSuccess = null;
        ESBClientUtils esb = new ESBClientUtils();
        JSONObject baseQueryParams = new JSONObject();
        JSONArray businessData = new JSONArray();
        JSONObject businessJson = new JSONObject();
        businessJson.put("fdNumber", ekpNumber);
        businessData.add(businessJson);
        JSONObject result = esb.doPost(ESBParams.SystemName.PCB_SRM.getValue(), ESBParams.SystemName.SRM_SERVER.getValue(),
                ESBParams.SystemName.EKP.getValue(), ESBParams.ServiceName.EKP_REVIEW_NODE.getValue(),
                null, "S", baseQueryParams, businessData);
        LOGGER.info("----------------------EKP查询返回结果: " + result);
        if (SUCCESS_STATUS.equalsIgnoreCase(result.getString("status"))) {
            if (null != result.getJSONObject("data")) {
                JSONObject data = result.getJSONObject("data");
                if (null != data.getJSONObject("obj")) {
                    JSONObject obj = data.getJSONObject("obj");
                    if (null != obj.getJSONArray("rows") && obj.getJSONArray("rows").size() > 0) {
                        rows = obj.getJSONArray("rows");
                        LOGGER.info("----------------------查询EKP流程ID,返回结果: " + rows);
                        return rows;
                    } else {
                        throw new UtilsException("查询EKP流程ID返回结果rows不能为空, 返回结果: " + obj);
                    }
                } else {
                    throw new UtilsException("查询EKP流程ID返回结果obj为空, 返回结果: " + data);
                }
            } else {
                throw new UtilsException("查询EKP流程ID,查询SRM调用EKP接口srm工具类返回结果data为空, 返回结果: " + result);
            }
        } else {
            throw new UtilsException("查询EKP流程ID查询EKP接口返回结果不为S, 返回结果: " + result);
        }
    }

    /**
     * 保存变更合同
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Object saveRenewSrmOkcContracts(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {

            SrmOkcContractsEntity_HI srmOkcContractsEntity_HI = JSON.parseObject(parameters.toString(), SrmOkcContractsEntity_HI.class);
            SrmOkcContractsEntity_HI oldSrmOkcContractsEntity_HI = srmOkcContractsDAO_HI.getById(parameters.getInteger("contractId"));
            Map<String, Object> map = new HashMap<>();
            map.put("contractCode", oldSrmOkcContractsEntity_HI.getContractCode());
            map.put("versionNumber", new Integer(oldSrmOkcContractsEntity_HI.getVersionNumber() + 1));
            List<SrmOkcContractsEntity_HI> contracts = srmOkcContractsDAO_HI.findByProperty(map);
            if (contracts != null && contracts.size() >= 1) {
                throw (new Exception("当前状态不能变更"));
            }
            if (!("4".equals(oldSrmOkcContractsEntity_HI.getContractApprovalStatus()) && "3".equals(oldSrmOkcContractsEntity_HI.getContractStatus()))) {
                throw (new Exception("当前状态不能变更"));
            }
            srmOkcContractsEntity_HI.setContractId(null);
            srmOkcContractsEntity_HI.setCreatedBy(null);
            srmOkcContractsEntity_HI.setCreationDate(null);
            srmOkcContractsEntity_HI.setContractCode(oldSrmOkcContractsEntity_HI.getContractCode());
            srmOkcContractsEntity_HI.setVersionNumber(oldSrmOkcContractsEntity_HI.getVersionNumber() + 1);
            srmOkcContractsEntity_HI.setContractApprovalStatus("1");
            srmOkcContractsEntity_HI.setContractStatus("4");

            //srmOkcContractsEntity_HI.setOperatorUserId(oldSrmOkcContractsEntity_HI.getCreatedBy());
            oldSrmOkcContractsEntity_HI.setOperatorUserId(srmOkcContractsEntity_HI.getOperatorUserId());
            srmOkcContractsEntity_HI.setContractStatus("4");
            srmOkcContractsEntity_HI.setVersionNum(0);

            srmOkcContractsDAO_HI.update(oldSrmOkcContractsEntity_HI);
            srmOkcContractsDAO_HI.save(srmOkcContractsEntity_HI);

            JSONArray t = parameters.getJSONArray("srmOkcContractsPartiesTable");

            if (t != null) {
                int s = t.size();
                for (int i = 0; i < s; i++) {
                    JSONObject o = t.getJSONObject(i);
                    SrmOkcContractPartiesEntity_HI srmOkcContractPartiesEntity_hi = JSON.parseObject(o.toJSONString(), SrmOkcContractPartiesEntity_HI.class);
                    srmOkcContractPartiesEntity_hi.setContractId(srmOkcContractsEntity_HI.getContractId());
                    srmOkcContractPartiesEntity_hi.setOperatorUserId(srmOkcContractsEntity_HI.getOperatorUserId());
                    srmOkcContractPartiesEntity_hi.setContractPartyId(null);
                    srmOkcContractPartiesEntity_hi.setVersionNum(0);
                    srmOkcContractPartiesDAO_HI.saveOrUpdate(srmOkcContractPartiesEntity_hi);
                }
            }

            t = parameters.getJSONArray("srmOkcContractsItemsTable");
            if (t != null) {
                int s = t.size();
                for (int i = 0; i < s; i++) {
                    JSONObject o = t.getJSONObject(i);
                    SrmOkcContractItemsEntity_HI srmOkcContractItemsEntity_hi = JSON.parseObject(o.toJSONString(), SrmOkcContractItemsEntity_HI.class);
                    srmOkcContractItemsEntity_hi.setContractId(srmOkcContractsEntity_HI.getContractId());
                    srmOkcContractItemsEntity_hi.setOperatorUserId(srmOkcContractsEntity_HI.getOperatorUserId());
                    srmOkcContractItemsEntity_hi.setContractItemId(null);
                    srmOkcContractItemsEntity_hi.setVersionNum(1);
                    srmOkcContractItemsDAO_HI.saveOrUpdate(srmOkcContractItemsEntity_hi);
                }
            }
            t = parameters.getJSONArray("srmOkcFreightExpensesTable");
            if (t != null) {
                int s = t.size();
                for (int i = 0; i < s; i++) {
                    JSONObject o = t.getJSONObject(i);
                    SrmOkcContractFreightsEntity_HI srmOkcContractFreightsEntity_HI = JSON.parseObject(o.toJSONString(), SrmOkcContractFreightsEntity_HI.class);
                    srmOkcContractFreightsEntity_HI.setContractId(srmOkcContractsEntity_HI.getContractId());
                    srmOkcContractFreightsEntity_HI.setOperatorUserId(srmOkcContractsEntity_HI.getOperatorUserId());
                    srmOkcContractFreightsEntity_HI.setContractFreightId(null);
                    srmOkcContractFreightsEntity_HI.setVersionNum(0);
                    srmOkcContractFreightsDAO_HI.saveOrUpdate(srmOkcContractFreightsEntity_HI);
                }
            }

            t = parameters.getJSONArray("srmOkcContractPaymentsTable");
            if (t != null) {
                int s = t.size();
                for (int i = 0; i < s; i++) {
                    JSONObject o = t.getJSONObject(i);
                    SrmOkcContractPaymentsEntity_HI srmOkcContractPaymentsEntity_hi = JSON.parseObject(o.toJSONString(), SrmOkcContractPaymentsEntity_HI.class);
                    srmOkcContractPaymentsEntity_hi.setContractId(srmOkcContractsEntity_HI.getContractId());
                    srmOkcContractPaymentsEntity_hi.setOperatorUserId(srmOkcContractsEntity_HI.getOperatorUserId());
                    srmOkcContractPaymentsEntity_hi.setPaymentStageId(null);
                    srmOkcContractPaymentsEntity_hi.setVersionNum(0);
                    srmOkcContractPaymentsDAO_HI.saveOrUpdate(srmOkcContractPaymentsEntity_hi);
                }
            }

            //若存在已转订单，采购订单亦同步复刻一版本
            List<SrmPoHeadersEntity_HI> poHeaderList=srmPoHeadersDAO_HI.findByProperty("contractId",oldSrmOkcContractsEntity_HI.getContractId());
            if(!ObjectUtils.isEmpty(poHeaderList)){
                for(int i=0;i<poHeaderList.size();i++){
                    SrmPoHeadersEntity_HI poHeader=poHeaderList.get(0);
                    List<SrmPoLinesEntity_HI> poLineList = srmPoLinesDAO_HI.findByProperty("poHeaderId", poHeader.getPoHeaderId());
                    //保存采购订单头归档信息
                    SrmPoHeaderArchivesEntity_HI copyHeader = new SrmPoHeaderArchivesEntity_HI();
                    BeanUtils.copyProperties(poHeader, copyHeader);
                    copyHeader.setOperatorUserId(srmOkcContractsEntity_HI.getOperatorUserId());
                    srmPoHeaderArchivesDAO_HI.save(copyHeader);
                    srmPoHeaderArchivesDAO_HI.fluch();
                    //保存采购订单行归档信息
                    if (null != poLineList && poLineList.size() > 0) {
                        for (SrmPoLinesEntity_HI poLine : poLineList) {
                            SrmPoLineArchivesEntity_HI copyLine = new SrmPoLineArchivesEntity_HI();
                            BeanUtils.copyProperties(poLine, copyLine);
                            copyLine.setOperatorUserId(srmOkcContractsEntity_HI.getOperatorUserId());
                            copyLine.setPoArchiveId(copyHeader.getPoArchiveId());
                            srmPoLineArchivesDAO_HI.save(copyLine);
                            srmPoHeaderArchivesDAO_HI.fluch();
                        }
                    }


                    //更新采购订单头合同ID
                    poHeader.setContractId(srmOkcContractsEntity_HI.getContractId());
                    poHeader.setPoVersions(new BigDecimal("1").add(poHeader.getPoVersions()));
                    poHeader.setOperatorUserId(srmOkcContractsEntity_HI.getOperatorUserId());
                    //查询变更后行
                    List<SrmOkcContractItemsEntity_HI> okcItemList=srmOkcContractItemsDAO_HI.findByProperty("contractId",srmOkcContractsEntity_HI.getContractId());
                    if (!ObjectUtils.isEmpty(poLineList)&&!ObjectUtils.isEmpty(okcItemList)) {
                        for(int n=0;n<poLineList.size();n++){
                            SrmPoLinesEntity_HI poLine=poLineList.get(n);
                            for(int m=0;m<okcItemList.size();m++){
                                SrmOkcContractItemsEntity_HI okcItem=okcItemList.get(m);
                                if(poHeader.getOrganizationId().equals(okcItem.getOrganizationId())&&poLine.getItemId().equals(okcItem.getItemId())){
                                    poLine.setContractId(okcItem.getContractId());
                                    poLine.setContractItemId(okcItem.getContractItemId());
                                    poLine.setOperatorUserId(srmOkcContractsEntity_HI.getOperatorUserId());
                                    srmPoLinesDAO_HI.saveOrUpdate(poLine);
                                    srmPoLinesDAO_HI.fluch();
                                }
                            }
                        }
                    }
                }
            }

            return srmOkcContractsEntity_HI;
        } catch (Exception e) {
            //e.printStackTrace();
            //throw new Exception(e);
            throw new UtilsException("保存变更合同异常：" + e.getMessage());
        }
    }

    /**
     * 查询合同预警
     *
     * @param parameters
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination findSrmOkcContractsWarnList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcContractsEntity_HI_RO.SRM_OKC_CONTRACTS_WARN_QUERY_SQL);
            Map<String, Object> map = new HashMap();
            map.put("varUserId", parameters.getInteger("varUserId"));
            SaafToolUtils.parperParam(parameters, "t.contract_code", "contractCode", queryString, map, "like");
            SaafToolUtils.parperParam(parameters, "t.contract_name", "contractName", queryString, map, "like");
            SaafToolUtils.parperParam(parameters, "t.version_num", "versionNum", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.main_contract_id", "mainContractId", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.party_a_id", "partyAId", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.party_b_id", "partyBId", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.template_id", "templateId", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.contract_type", "contractType", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.created_mode", "createdMode", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.contract_status", "contractStatus", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.contract_approval_status", "contractApprovalStatus", queryString, map, "=");
            String searchStartDate = parameters.getString("searchStartDate");
            if (searchStartDate != null && !"".equals(searchStartDate.trim())) {
                queryString.append(" AND t.party_a_sign_date >= to_date(:searchStartDate, 'yyyy-mm-dd')\n");
                map.put("searchStartDate", searchStartDate);
            }
            String searchEndDate = parameters.getString("searchEndDate");
            if (searchEndDate != null && !"".equals(searchEndDate.trim())) {
                queryString.append(" AND t.party_a_sign_date <= to_date(:searchEndDate, 'yyyy-mm-dd')\n");
                map.put("searchEndDate", searchEndDate);
            }
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" ORDER BY t.contract_id desc");
            Pagination<SrmOkcContractsEntity_HI_RO> resultSet = srmOkcContractsDAO_HI_RO.findPagination(queryString, countSql, map, pageIndex, pageRows);
            return resultSet;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同预警异常：" + e.getMessage());
        }
    }

    /**
     * Description：查询合同列表
     *
     * @param parameters 查询条件参数
     * @param pageIndex  页码
     * @param pageRows   页行数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public Pagination findSrmOkcContractsList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcContractsEntity_HI_RO.SRM_OKC_CONTRACTS_QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("varUserId", parameters.getIntValue("varUserId"));

            SaafToolUtils.parperParam(parameters, "t.contract_code", "contractCode", queryString, map, "like");
            SaafToolUtils.parperParam(parameters, "t.contract_name", "contractName", queryString, map, "like");
            SaafToolUtils.parperParam(parameters, "t.version_num", "versionNum", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.main_contract_id", "mainContractId", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.party_a_id", "partyAId", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.party_b_id", "partyBId", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.template_id", "templateId", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.contract_type", "contractType", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.created_mode", "createdMode", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.contract_status", "contractStatus", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.contract_approval_status", "contractApprovalStatus", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "u.user_full_name", "createdByName", queryString, map, "like");
            SaafToolUtils.parperParam(parameters, "t.version_number", "versionNumber", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "t.source_code", "sourceCode", queryString, map, "like");
            String searchStartDate = parameters.getString("searchStartDate");
            if (searchStartDate != null && !"".equals(searchStartDate.trim())) {
                queryString.append(" AND t.party_a_sign_date >= to_date(:searchStartDate, 'yyyy-MM-dd')\n");
                map.put("searchStartDate", searchStartDate);
            }
            String searchEndDate = parameters.getString("searchEndDate");
            if (searchEndDate != null && !"".equals(searchEndDate.trim())) {
                queryString.append(" AND t.party_a_sign_date <= to_date(:searchEndDate, 'yyyy-MM-dd')\n");
                map.put("searchEndDate", searchEndDate);
            }

            queryString.append(" and t.party_a_id in (SELECT distinct (sua.inst_id) Organization_Id\n" +
                    "                               FROM saaf_user_access_orgs sua,\n" +
                    "                                    saaf_institution      si,\n" +
                    "                                    saaf_users            su\n" +
                    "                              WHERE sua.user_id = su.user_id\n" +
                    "                                AND sua.inst_id = si.inst_id\n" +
                    "                                and sua.platform_code = 'SAAF'\n" +
                    "                                and si.inst_type='ORG'\n" +
                    "                                and sua.user_id = " + parameters.getInteger("varUserId") + ") " +
                    "      AND t.contract_type IN  (" + getContractType(parameters.getInteger("varUserId")) + ")");

            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" ORDER BY t.contract_id desc");
            Pagination<SrmOkcContractsEntity_HI_RO> resultSet = srmOkcContractsDAO_HI_RO.findPagination(queryString, countSql, map, pageIndex, pageRows);
            return resultSet;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同异常：" + e.getMessage());
        }
    }


    /**
     * 获取合同类型
     *
     * @param userId
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    private String getContractType(Integer userId) {
        JSONObject json = new JSONObject();
        json.put("userId", userId);
        List<SrmBaseUserCategoriesEntity_HI_RO> userCategoriesList = srmBaseUserCategories.findUserCategories(json);
        Map map = new HashMap();
        map.put("lookupType", "CON_CON_TYPE");
        map.put("enabledFlag", "Y");
        List<SaafLookupValuesEntity_HI> lookupValues = saafLookupValuesDAO_HI.findByProperty(map);
        List categoryCode = new ArrayList();
        for (SaafLookupValuesEntity_HI vo : lookupValues) {
            for (SrmBaseUserCategoriesEntity_HI_RO ro : userCategoriesList) {
                if (ro.getCategoryCode().equals(vo.getLookupCode())) {
                    categoryCode.add(ro.getCategoryCode());
                }
            }
        }
        String templateType = String.valueOf(categoryCode.stream().distinct().collect(Collectors.joining("','")));
        templateType = "'" + templateType + "'";
        return templateType;
    }

    /**
     * Description：根据ID查询合同
     *
     * @param parameters 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public SrmOkcContractsEntity_HI_RO findSrmOkcContractsById(JSONObject parameters) throws Exception {
        LOGGER.debug("参数:==========>>>{}", parameters);
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcContractsEntity_HI_RO.SRM_OKC_CONTRACTS_BY_ID);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "t.contract_id", "contractId", queryString, map, "=");
            SrmOkcContractsEntity_HI_RO srmOkcContractsEntity_HI_RO = srmOkcContractsDAO_HI_RO.findList(queryString, map).get(0);
            return srmOkcContractsEntity_HI_RO;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询合同异常：" + e.getMessage());
        }
    }

    /**
     * 更新在线编辑文档
     *
     * @param userId
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public boolean updateEditFile(Integer contractId, Integer fileId, Integer userId) throws Exception {
        try {
            SrmOkcContractsEntity_HI srmOkcContractsEntity_hi = srmOkcContractsDAO_HI.getById(contractId);
            srmOkcContractsEntity_hi.setAttribute16(fileId);
            srmOkcContractsEntity_hi.setOperatorUserId(userId);
            srmOkcContractsDAO_HI.update(srmOkcContractsEntity_hi);

        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("更新在线编辑文档异常：" + e.getMessage());
        }
        return true;
    }

    /**
     * 根据ID加载合同
     *
     * @param contractId
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SrmOkcContractsEntity_HI loadSrmOkcContractsById(Integer contractId) throws Exception {
        try {
            return srmOkcContractsDAO_HI.getById(contractId);
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("加载合同异常：" + e.getMessage());
        }
    }

    /**
     * 根据ID保存合同
     *
     * @param contract
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public void updateSrmOkcContractsById(SrmOkcContractsEntity_HI contract) throws Exception {
        try {
            srmOkcContractsDAO_HI.update(contract);
        } catch (Exception e) {
            throw new UtilsException("保存合同异常：" + e.getMessage());
        }
    }

    /**
     * 更新合同文本
     *
     * @param fileId
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public void updateTextFile(Integer id, Integer fileId) {
        SrmOkcContractTextsEntity_HI text = srmOkcContractTextsDAO_HI.getById(id);
        text.setTextFileId(fileId);
        text.setOperatorUserId(text.getLastUpdatedBy());
        text.setOperatorUserId(text.getLastUpdatedBy());
        srmOkcContractTextsDAO_HI.update(text);
    }

    /**
     * 根据ID查询合同模板
     *
     * @param id
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SaafBaseResultFileEntity_HI_RO findTemplateFileById(Integer id) {
        String sql = "select f.* from saaf_base_result_file f INNER JOIN  srm_okc_contract_templates t on t.template_file_id = f.file_id where t.template_id = :templateId";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("templateId", id);
        List<SaafBaseResultFileEntity_HI_RO> result = saafBaseResultFileDAO_HI_RO.findList(sql, param);
        if (result != null && result.size() != 0) {
            return result.get(0);
        }
        return null;
    }

    /**
     * 查询合同的补充合同列表
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination findSubContractsList(JSONObject parameters, Integer pageIndex, Integer pageRows) {
        LOGGER.debug("参数:==========>>>{}", parameters);
        StringBuffer queryString = new StringBuffer(SrmOkcContractsEntity_HI_RO.SUB_CONTRACT_QUERY_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mainContractId", parameters.getIntValue("mainContractId"));
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" ORDER BY soc.contract_id desc");
        Pagination<SrmOkcContractsEntity_HI_RO> resultSet = srmOkcContractsDAO_HI_RO.findPagination(queryString, countSql, map, pageIndex, pageRows);
        return resultSet;
    }

    /**
     * 删除合同附件
     *
     * @param params
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteSrmOkcContractAttachments(JSONObject params) throws Exception {
        LOGGER.info("删除合同附件，参数：" + params.toString());
        Integer contractAttachmentId = params.getInteger("contractAttachmentId");
        try {
            if (contractAttachmentId == null) {
                return SToolUtils.convertResultJSONObj("E", "删除失败，" + params.getString("contractAttachmentId") + "不存在", 0, null);
            }
            SrmOkcContractAttachmentsEntity_HI srmOkcContractAttachmentsEntity_HI = srmOkcContractAttachmentsDAO_HI.getById(contractAttachmentId);
            //删除文件
            iSaafBaseResultFile.deleteFile(srmOkcContractAttachmentsEntity_HI.getAttachmentFileId());
            //删除合同附件
            srmOkcContractAttachmentsDAO_HI.delete(srmOkcContractAttachmentsEntity_HI);
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("删除合同附件异常：" + e.getMessage());
        }
    }


}
