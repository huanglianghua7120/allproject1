package saaf.common.fmw.pon.model.inter.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionSuppliersEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonBidHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonBidItemPricesEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionSuppliersEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionSuppliers;
import saaf.common.fmw.utils.SrmUtils;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonAuctionSuppliersServer.java
 * Description：寻源--寻源邀请供应商信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonAuctionSuppliersServer")
public class SrmPonAuctionSuppliersServer implements ISrmPonAuctionSuppliers {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonAuctionSuppliersServer.class);

    @Autowired
    private ViewObject<SrmPonAuctionSuppliersEntity_HI> srmPonAuctionSuppliersDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonAuctionSuppliersEntity_HI_RO> srmPonAuctionSuppliersDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPonAuctionHeadersEntity_HI> srmPonAuctionHeadersDAO_HI;

    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值

    /**
     * Description：删除洽谈邀请供应商——根据主键ID（单条数据）
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteSrmPonAuctionSupplierById(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());
        Integer inviteId = jsonParams.getInteger("inviteId");
        if (!(inviteId == null || "".equals(inviteId))) {
            SrmPonAuctionSuppliersEntity_HI row = srmPonAuctionSuppliersDAO_HI.getById(jsonParams.getInteger("inviteId"));
            if (null != row) {
                srmPonAuctionSuppliersDAO_HI.delete(row.getInviteId());
                return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "未知错误", 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * Description：删除洽谈邀请供应商——根据主键ID（单条数据）
     * @param jsonParams 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return Pagination<SrmPonAuctionSuppliersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPonAuctionSuppliersEntity_HI_RO> findSrmPonAuctionSuppliersLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> map = new HashMap();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPonAuctionSuppliersEntity_HI_RO.QUERY_SELECT_PONSUPPLIERSQL);
        //采购类别查询
        String categoryName = jsonParams.getString("categoryName");
        if (categoryName != null && !"".equals(categoryName)) {
            //验证字符串是否含有SQL关键字及字符，有则返回NULL
            if (SrmUtils.isContainSQL(categoryName)) {
                return null;
            }
            sb.append("AND    EXISTS (SELECT 1\n" +
                      "        FROM   srm_pos_supplier_categories psc\n" +
                      "              ,srm_base_categories         sbc\n" +
                      "        WHERE  psc.category_id = sbc.category_id\n" +
                      "        AND    psc.supplier_id = psi.supplier_id\n" +
                      "        AND    nvl(psc.status, 'EFFECTIVE') <> 'DISABLED'\n" +
                      "        AND    sbc.full_category_name LIKE '%" + categoryName + "%')\n");
        }
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_id", "supplierId", sb, map, "=");// 供应商Id查询
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_name", "supplierName", sb, map, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_number", "supplierNumber", sb, map, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_status", "supplierStatus", sb, map, "=");
        if ("SUBMITTED".equals(jsonParams.getString("supplierStatus"))) {
            sb.append(" AND psi.source_code = 'REGISTER'\n");
        }

        sb.append(" AND psi.Supplier_Type IN (" + getSupplierType(jsonParams.getInteger("varUserId")) +")");
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY psi.creation_date DESC,spsc.creation_date desc "); // 排序
        Pagination<SrmPonAuctionSuppliersEntity_HI_RO> reesult = srmPonAuctionSuppliersDAO_HI_RO.findPagination(sb.toString(),countSql, map, pageIndex, pageRows);
        if("Y".equals(jsonParams.getString("isAuctionDrafts"))){
            for(int i=0;i<reesult.getData().size();i++){
                for(int j=reesult.getData().size()-1;j>i;j--){
                    if(reesult.getData().get(j).getSupplierId().compareTo(reesult.getData().get(i).getSupplierId())==0&&!reesult.getData().get(j).getSupplierContactCreationDate().after(reesult.getData().get(i).getSupplierContactCreationDate())){
                        reesult.getData().remove(j);
                    }
                }
            }
        }
        return reesult;
    }

    /**
     * Description：获取操作者品类权限
     * @param userId 操作者ID
     * @return String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-05-14         xiexiaoxia      创建
     * =======================================================================
     */
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
     * Description：询价的洽谈邀请供应商——选择供应商弹出框查询（分页）
     * @param jsonParams 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return Pagination<SrmPonAuctionSuppliersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPonAuctionSuppliersEntity_HI_RO> findSrmPonAuctionSuppliersBiddingLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPonAuctionSuppliersEntity_HI_RO.QUERY_SELECT_PONSUPPLIERBIDDIN_SQL);
        //采购类别查询
        String categoryName = jsonParams.getString("categoryName");
        if (categoryName != null && !"".equals(categoryName)) { // 采购类别查询
            //验证字符串是否含有SQL关键字及字符，有则返回NULL
            if (SrmUtils.isContainSQL(categoryName)) {
                return null;
            }
            sb.append("AND    EXISTS (SELECT 1\n" +
                    "        FROM   srm_pos_supplier_categories psc\n" +
                    "              ,srm_base_categories         sbc\n" +
                    "        WHERE  psc.category_id = sbc.category_id\n" +
                    "        AND    psc.supplier_id = psi.supplier_id\n" +
                    "        AND    nvl(psc.status, 'EFFECTIVE') <> 'DISABLED'\n" +
                    "        AND    sbc.full_category_name LIKE '%" + categoryName + "%')\n");
        }
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_id", "supplierId", sb, map, "=");// 供应商Id查询
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_name", "supplierName", sb, map, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_number", "supplierNumber", sb, map, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "psi.supplier_status", "supplierStatus", sb, map, "=");


        sb.append(" AND psi.Supplier_Type IN (" + getSupplierType(jsonParams.getInteger("varUserId")) +")");
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY psi.creation_date DESC,spsc.creation_date desc  "); // 排序
        Pagination<SrmPonAuctionSuppliersEntity_HI_RO> reesult = srmPonAuctionSuppliersDAO_HI_RO.findPagination(sb.toString(),countSql, map, pageIndex, pageRows);
        if("Y".equals(jsonParams.getString("isAuctionDrafts"))){
            for(int i=0;i<reesult.getData().size();i++){
                for(int j=reesult.getData().size()-1;j>i;j--){
                    if(reesult.getData().get(j).getSupplierId().compareTo(reesult.getData().get(i).getSupplierId())==0&&!reesult.getData().get(j).getSupplierContactCreationDate().after(reesult.getData().get(i).getSupplierContactCreationDate())){
                        reesult.getData().remove(j);
                    }
                }
            }
        }
        return reesult;
    }

    /**
     * Description：查询被邀请的供应商（不分页）
     * @param params 参数
     * @return Pagination<SrmPonAuctionSuppliersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<SrmPonAuctionSuppliersEntity_HI_RO> getAuctionSupplierList(JSONObject params) {
        LOGGER.info("params:" + params.toString());
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer querySql = new StringBuffer(SrmPonAuctionSuppliersEntity_HI_RO.QUERY_AUCTION_SUPPLIER_LIST_SQL);
        //邀标方式
        SaafToolUtils.parperParam(params, "t.inviting_bid_way", "invitingBidWay", querySql, queryParamMap, "=");
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        if (auctionHeaderId == null || "".equals(auctionHeaderId)) {
            return null;
        }
        List<SrmPonAuctionSuppliersEntity_HI_RO> result = srmPonAuctionSuppliersDAO_HI_RO.findList(querySql, new Object[]{auctionHeaderId});
        if (null != result && result.size() > 0) {
            for (SrmPonAuctionSuppliersEntity_HI_RO k : result) {
                if (null == k.getAwardedStatus() || "".equals(k.getAwardedStatus())) {
                    k.setAwardedStatusName("未中标");
                }
            }
        }
        return result;
    }

    /**
     * Description：查询供应商是否有对应保证金
     * @param params 参数
     * @return String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public String getIsAuctionSupplier(JSONObject params) {
        LOGGER.info("params:" + params.toString());
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer querySql = new StringBuffer(SrmPonAuctionSuppliersEntity_HI_RO.QUERY_AUCTION_SUPPLIERS_SQL);
        Integer supplierId = params.getInteger("varSupplierId");
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        if (supplierId != null && null != auctionHeaderId) {
//            params.put("supplierId", supplierId);
//            SaafToolUtils.parperParam(params, "t.auction_header_id", "auctionHeaderId", querySql, queryParamMap, "=");
//            SaafToolUtils.parperParam(params, "t.supplier_id", "supplierId", querySql, queryParamMap, "=");
            queryParamMap.put("supplierId", supplierId);
            queryParamMap.put("auctionHeaderId", auctionHeaderId);
            List<SrmPonAuctionSuppliersEntity_HI_RO> result = srmPonAuctionSuppliersDAO_HI_RO.findList(querySql, queryParamMap);
            if (0 == result.get(0).getCount()) {
                return "N";
            } else {
                return "Y";
            }
        }
        return "N";
    }

    /**
     * Description：保存拟标——洽谈邀请供应商（招标）
     * @param ponAuctionSuppliersList 邀请供应商信息
     * @param userId 操作者ID
     * @param auctionHeaderId 寻源单据ID
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public void savePonAuctionSuppliersList(List<SrmPonAuctionSuppliersEntity_HI> ponAuctionSuppliersList,
                                            Integer userId, Integer auctionHeaderId) {
        if (null != ponAuctionSuppliersList && ponAuctionSuppliersList.size() > 0) {
            for (SrmPonAuctionSuppliersEntity_HI k : ponAuctionSuppliersList) {
                k.setAuctionHeaderId(auctionHeaderId);
                k.setOperatorUserId(userId);
                k.setInvitingBidWay("1");
            }
            srmPonAuctionSuppliersDAO_HI.saveOrUpdateAll(ponAuctionSuppliersList);
        } else {
            //删除原来可能添加了的邀请供应商
            HashMap<String, Object> queryMap = new HashMap<>();
            queryMap.put("auctionHeaderId", auctionHeaderId);
            List<SrmPonAuctionSuppliersEntity_HI> suppliersEntityList = srmPonAuctionSuppliersDAO_HI.findByProperty(queryMap);
            if (suppliersEntityList.size() > 0) {
                srmPonAuctionSuppliersDAO_HI.delete(suppliersEntityList);
            }
        }
    }

    /**
     * Description：保存保证金和标书费用
     * @param params 参数
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveBondPayStatus(JSONObject params) throws Exception {
        Integer userId = params.getInteger("varUserId");
        try {
            SrmPonAuctionHeadersEntity_HI auctionHeader = srmPonAuctionHeadersDAO_HI.getById(params.getInteger("auctionHeaderId"));
            if (null != auctionHeader) {
                if ("PUBLISHED".equals(auctionHeader.getAuctionStatus())) {
                    JSONArray array = params.getJSONArray("lineData");
                    for (int i = 0; i < array.size(); i++) {
                        JSONObject json = new JSONObject();
                        json = array.getJSONObject(i);
                        SrmPonAuctionSuppliersEntity_HI row = srmPonAuctionSuppliersDAO_HI.getById(json.getInteger("inviteId"));
                        if (null != row) {
                            row.setBidBondPayStatus(json.getString("bidBondPayStatus"));
                            row.setTenderCostPayStatus(json.getString("tenderCostPayStatus"));
                            row.setOperatorUserId(userId);
                            srmPonAuctionSuppliersDAO_HI.save(row);
                        }
                    }
                }
            }
            return SToolUtils.convertResultJSONObj("S", "保存供应商的保证金缴纳状态成功", 1, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
            throw new Exception("保存供应商的保证金缴纳状态失败");
        }
    }

    /**
     * Description：招标的洽谈邀请供应商保证金查询
     * @param jsonParams 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return Pagination<SrmPonAuctionSuppliersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPonAuctionSuppliersEntity_HI_RO> findSrmPonAuctionSuppliersBondList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> map = new HashMap();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPonAuctionSuppliersEntity_HI_RO.QUERY_AUCTION_SUPPLIERS_BOND_LIST_SQL);

        SaafToolUtils.parperParam(jsonParams, "spah.org_id", "orgId", sb, map, "=");
        SaafToolUtils.parperParam(jsonParams, "spas.supplier_id", "supplierId", sb, map, "=");
        SaafToolUtils.parperParam(jsonParams, "spah.auction_number", "auctionNumber", sb, map, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "spah.auction_title", "auctionTitle", sb, map, "LIKE");

        String returnFlag = jsonParams.getString("returnFlag");
        if ("Y".equals(returnFlag)){
            sb.append(" AND Spas.Return_Flag = 'Y'");
        }else if ("N".equals(returnFlag)){
            sb.append(" AND Spas.Return_Flag IS NULL");
        }else {

        }

        String bidBondPayDateFrom = jsonParams.getString("bidBondPayDateFrom");
        if (bidBondPayDateFrom != null && !"".equals(bidBondPayDateFrom.trim())) {
            sb.append("  AND trunc(Spas.Bid_Bond_Pay_Date) >= to_date(:bidBondPayDateFrom, 'yyyy-mm-dd')\n");
            map.put("bidBondPayDateFrom", bidBondPayDateFrom);
        }
        String bidBondPayDateTo = jsonParams.getString("bidBondPayDateTo");
        if (bidBondPayDateTo != null && !"".equals(bidBondPayDateTo.trim())) {
            sb.append("  AND trunc(Spas.Bid_Bond_Pay_Date) <= to_date(:bidBondPayDateTo, 'yyyy-mm-dd')\n");
            map.put("bidBondPayDateTo", bidBondPayDateTo);
        }
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY spah.creation_date DESC "); // 排序
        Pagination<SrmPonAuctionSuppliersEntity_HI_RO> result = srmPonAuctionSuppliersDAO_HI_RO.findPagination(sb.toString(),countSql, map, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：招标的供应商中标情况查询
     * @param jsonParams 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return Pagination<SrmPonAuctionSuppliersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPonAuctionSuppliersEntity_HI_RO> findSrmPonAuctionAnalysisList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> map = new HashMap();
        StringBuffer queryString = new StringBuffer(SrmPonAuctionSuppliersEntity_HI_RO.QUERY_AUCTION_ANALYSIS_LIST_SQL);
        StringBuffer queryString2 = new StringBuffer(SrmPonAuctionSuppliersEntity_HI_RO.QUERY_AUCTION_ANALYSIS_LIST_SQL2);

        //SaafToolUtils.parperParam(jsonParams, "spah.org_id", "orgId", sb, map, "=");
        SaafToolUtils.parperParam(jsonParams, "Pas.supplier_id", "supplierId", queryString, map, "=");

        String creationDateFrom = jsonParams.getString("creationDateFrom");
        if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
            queryString.append("  AND trunc(Pah.Creation_Date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
            queryString2.append("  AND trunc(Pah2.Creation_Date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
            map.put("creationDateFrom", creationDateFrom);
        }
        String creationDateTo = jsonParams.getString("creationDateTo");
        if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
            queryString.append("  AND trunc(Pah.Creation_Date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
            queryString2.append("  AND trunc(Pah2.Creation_Date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
            map.put("creationDateTo", creationDateTo);
        }
       // queryString.replace(queryString.indexOf("AWARDCOUNT"), queryString.indexOf("AWARDCOUNT") + "AWARDCOUNT".length(), queryString2.toString());

        //queryString.append(" ORDER BY Pah.creation_date DESC "); // 排序
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" GROUP BY Si1.Inst_Name\n" +
                "         ,Pas.Supplier_Name\n" +
                "         ,Pah.Org_Id\n" +
                "         ,Pas.Supplier_Id ");
        System.out.println(queryString.toString());
        Pagination<SrmPonAuctionSuppliersEntity_HI_RO> result = srmPonAuctionSuppliersDAO_HI_RO.findPagination(queryString.toString(),countSql, map, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：保存保证金退还信息
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveBondReturn(JSONObject params) throws Exception {
        Integer userId = params.getInteger("varUserId");
        Integer inviteId = params.getInteger("inviteId");
        if (null == inviteId){
            return SToolUtils.convertResultJSONObj("E", "缺少inviteId", 0, null);
        }
        try {
            SrmPonAuctionSuppliersEntity_HI row = srmPonAuctionSuppliersDAO_HI.getById(inviteId);
            if (null != row) {
                row.setReturnFlag("Y");
                row.setBidBondReturn(params.getBigDecimal("bidBondReturn"));
                row.setBidBondReturnDate(params.getDate("bidBondReturnDate"));
                row.setOperatorUserId(userId);
                srmPonAuctionSuppliersDAO_HI.save(row);
            }
            return SToolUtils.convertResultJSONObj("S", "保存供应商的保证金退还信息成功", 1, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
            throw new Exception("保存供应商的保证金退还信息失败");
        }
    }

}
