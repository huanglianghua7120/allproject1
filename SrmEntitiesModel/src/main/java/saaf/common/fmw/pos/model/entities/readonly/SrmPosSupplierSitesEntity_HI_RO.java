package saaf.common.fmw.pos.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * SrmPosSupplierSitesEntity_HI_RO Entity Object Fri Mar 09 15:32:48 CST 2018
 * Auto Generate
 */

public class SrmPosSupplierSitesEntity_HI_RO {
	// 查询供应商退出地点
	public static String QUERY_QUIT_SITES = "SELECT DISTINCT b.supplier_site_id AS supplierSiteId,\r\n" + 
			"(CASE WHEN BB.supplier_quit_site_id \r\n" + 
			"IS NULL THEN 'N' ELSE 'Y' END) AS siteId,\r\n" + 
			"b.supplier_id AS supplierId,\r\n" +
			"b.supplier_address_id AS supplierAddressId,\r\n" + 
			"b.site_name AS siteName,\r\n" + 
			"b.org_id AS orgId,\r\n" + 
			"b.site_status AS siteStatus,\r\n" + 
			"b.purchase_flag AS purchaseFlag,\r\n" + 
			"b.payment_flag AS paymentFlag,\r\n" + 
			"b.froze_flag AS frozeFlag,\r\n" + 
			"b.unfroze_date AS unfrozeDate,\r\n" + 
			"b.qualified_date AS qualifiedDate,\r\n" + 
			"b.invalid_date AS invalidDate,\r\n" + 
			"b.temporary_site_flag AS temporarySiteFlag,\r\n" + 
			"b.version_num AS versionNum,\r\n" + 
			"b.creation_date AS creationDate,\r\n" + 
			"b.created_by AS createdBy,\r\n" + 
			"b.last_updated_by AS lastUpdatedBy,\r\n" + 
			"b.last_update_date AS lastUpdateDate,\r\n" + 
			"b.last_update_login AS lastUpdateLogin,\r\n" + 
			"c.inst_name AS instName,\r\n" + 
			"d.meaning AS siteStatusStr,\r\n" + 
			"e.meaning AS temporarySiteFlagName,\r\n" + 
			"(CASE WHEN BB.selected_flag = 'Y' THEN 'Y' ELSE 'N' END) AS selectedFlag\r\n" +
			"FROM srm_pos_supplier_sites b \r\n" +
			"LEFT JOIN saaf_lookup_values e ON e.lookup_type = 'YSE_NO' and b.temporary_site_flag = e.lookup_code\r\n" + 
			"LEFT JOIN (SELECT A.supplier_site_id,A.supplier_quit_site_id,A.selected_flag FROM \r\n" +
			"srm_pos_supplier_quit_sites A GROUP BY A.supplier_site_id,A.supplier_quit_site_id,A.selected_flag) BB \r\n" + 
			"ON BB.supplier_site_id = b.supplier_site_id,\r\n" +
			"saaf_institution c,\r\n" + 
			"saaf_lookup_values d\r\n" + 
			"WHERE b.org_id = c.inst_id \r\n" + 
			"and d.lookup_type='POS_SUPPLIER_SITE_STATUS' \r\n" + 
			"and b.site_status = d.lookup_code";

	// 查询供应商的认证证书
	public static String QUERY_SUPPLIER_SITES =
					"SELECT a.*\n" +
					"      ,b.Inst_Name       instName\n" +
					"      ,Slv1.Meaning      siteStatusStr\n" +
					"      ,Ad.Country        country\n" +
					"      ,Ad.Detail_Address detailAddress\n" +
					"      ,Ad.Province       province\n" +
					"      ,Slv.Meaning       countryName\n" +
                    "      ,a.invalid_date    invalidDate\n" +
					"  FROM Srm_Pos_Supplier_Sites a\n" +
					"  LEFT JOIN Saaf_Institution b\n" +
					"    ON b.Inst_Id = a.Org_Id\n" +
					"  LEFT JOIN Srm_Pos_Supplier_Addresses Ad\n" +
					"    ON Ad.Supplier_Address_Id = a.Supplier_Address_Id\n" +
					"  LEFT JOIN Saaf_Lookup_Values Slv\n" +
					"    ON Slv.Lookup_Type = 'BASE_COUNTRY'\n" +
					"   AND Slv.Lookup_Code = Ad.Country\n" +
					"  LEFT JOIN Saaf_Lookup_Values Slv1\n" +
					"    ON Slv1.Lookup_Type = 'POS_SUPPLIER_SITE_STATUS'\n" +
					"   AND a.Site_Status = Slv1.Lookup_Code\n" +
					" WHERE 1 = 1\n";

	// 查询供应商地点
//	public static String QUERY_SUPPLIER_SITES_LIST1 = "SELECT DISTINCT \r\n" + "a.supplier_site_id supplierSiteId,\r\n"
//			+ "a.supplier_id supplierId,\r\n" + "a.site_name siteName,\r\n" + "a.site_status siteStatus,\r\n"
//			+ "a.temporary_site_flag temporarySiteFlag,\r\n" + "c.inst_name instName,\r\n"
//			//+ "d.frozen_site_id frozenSiteId,\r\n"
//			+ "d.selected_flag selectedFlag,\r\n"
//			+ "(case d.froze_flag WHEN 'Y' THEN '是' WHEN 'N' THEN '否' END) frozeFlag,\r\n"
//			+ "d.unfroze_date unfrozeDate,\r\n"
//			+ "(case a.site_status WHEN 'DISABLED' THEN '失效' WHEN 'EFFECTIVE' THEN '合格' WHEN 'INTRODUCING' THEN '合格申请中' WHEN 'NEW' THEN '' END)  meaning,\r\n"
//			+ "(CASE a.temporary_site_flag\r\n" + "WHEN 'Y' THEN '是'\r\n" + "WHEN 'N' THEN '否' END) tempSiteStr,\r\n"
//			+ "d.action_purchase_flag actionPurchaseFlag,\r\n"
//			+ "(case d.action_purchase_flag WHEN 'Y' THEN '否' WHEN 'N' THEN '是' END) purchaseFlagStr,\r\n" + // 可采购：Y在冻结中表示‘是’已冻结，对应不能采购‘否’
//			"d.action_payment_flag  actionPaymentFlag,\r\n"
//			+ "(case d.action_payment_flag WHEN 'Y' THEN '否' WHEN 'N' THEN '是' END) paymentFlagStr\r\n" + // 可付款：Y在冻结中表示‘是’已冻结，对应不能采购‘否’
//			"FROM srm_pos_supplier_sites a\r\n"
//			//+ "LEFT JOIN saaf_lookup_values b ON a.site_status = b.lookup_code\r\n"
//			+ "LEFT JOIN saaf_institution c ON a.org_id = c.inst_id\r\n"
//			+ "LEFT JOIN srm_pos_frozen_sites d ON d.supplier_site_id = a.supplier_site_id\r\n" + "where 1=1 \r\n";

	// 查询供应商地点编辑界面
	public static String QUERY_SUPPLIER_SITES_LIST_EDIT =
					"SELECT a.supplier_site_id supplierSiteId\n" +
					"      ,a.supplier_id supplierId\n" +
					"      ,a.site_name siteName\n" +
					"      ,a.site_status siteStatus\n" +
					"      ,a.temporary_site_flag temporarySiteFlag\n" +
					"      ,c.inst_name instName\n" +
					"      ,d.frozen_site_id frozenSiteId\n" +
					"      ,d.selected_flag selectedFlag\n" +
					"      ,d.froze_flag frozeFlag\n" +
					"      ,(CASE d.froze_flag\n" +
					"         WHEN 'Y' THEN\n" +
					"          '是'\n" +
					"         WHEN 'N' THEN\n" +
					"          '否'\n" +
					"       END) frozeFlagStr\n" +
					"      ,d.unfroze_date unfrozeDate\n" +
					"      ,(CASE a.site_status\n" +
					"         WHEN 'DISABLED' THEN\n" +
					"          '失效'\n" +
					"         WHEN 'EFFECTIVE' THEN\n" +
					"          '合格'\n" +
					"         WHEN 'INTRODUCING' THEN\n" +
					"          '合格申请中'\n" +
					"         WHEN 'NEW' THEN\n" +
					"          ''\n" +
					"       END) meaning\n" +
					"      ,(CASE a.temporary_site_flag\n" +
					"         WHEN 'Y' THEN\n" +
					"          '是'\n" +
					"         WHEN 'N' THEN\n" +
					"          '否'\n" +
					"       END) tempSiteStr\n" +
					"      ,d.action_purchase_flag actionPurchaseFlag\n" +
					"      ,(CASE d.action_purchase_flag\n" +
					"         WHEN 'Y' THEN\n" +
					"          '否'\n" +
					"         WHEN 'N' THEN\n" +
					"          '是'\n" +
					"       END) purchaseFlagStr\n" +
					"      ,d.action_payment_flag actionPaymentFlag\n" +
					"      ,(CASE d.action_payment_flag\n" +
					"         WHEN 'Y' THEN\n" +
					"          '否'\n" +
					"         WHEN 'N' THEN\n" +
					"          '是'\n" +
					"       END) paymentFlagStr\n" +
					"      ,a.payment_flag actionPaymentFlagInit\n" +
					"      ,a.purchase_flag actionPurchaseFlagInit\n" +
					"FROM   srm_pos_frozen_sites   d\n" +
					"      ,srm_pos_supplier_sites a\n" +
					"LEFT   JOIN saaf_institution c\n" +
					"ON     a.org_id = c.inst_id\n" +
					"WHERE  d.supplier_site_id = a.supplier_site_id\n";

	//20180426ly
//	public static String QUERY_SRM_POS_SUPPLIER_SITES_LIST ="\tSELECT a.payment_flag actionPaymentFlag,a.purchase_flag actionPurchaseFlag,a.froze_flag frozeFlag,d.selected_flag selectedFlag,a.supplier_site_id supplierSiteId,\r\n"
//            +"\ta.supplier_id supplierId,a.site_name siteName,a.site_status siteStatus,\r\n"
//            +"\ta.temporary_site_flag temporarySiteFlag,c.inst_name instName,a.unfroze_date unfrozeDate,\r\n"
//            +"\t(case a.site_status WHEN 'DISABLED' THEN '失效' WHEN 'EFFECTIVE' THEN '合格' WHEN 'INTRODUCING' THEN '合格申请中' WHEN 'NEW' THEN '' END)  meaning,\r\n"
//            +"\t(CASE a.temporary_site_flag WHEN 'Y' THEN '是' WHEN 'N' THEN '否' END) tempSiteStr,\r\n"
//            +"\t(case a.purchase_flag WHEN 'Y' THEN '是' WHEN 'N' THEN '否' END) purchaseFlagStr,\r\n"
//            +"\t(case a.payment_flag WHEN 'Y' THEN '是' WHEN 'N' THEN '否' END) paymentFlagStr \r\n"
//            +"\tFROM srm_pos_supplier_sites a LEFT JOIN saaf_institution c ON a.org_id = c.inst_id\r\n "
//            + "\tLEFT JOIN srm_pos_frozen_sites d ON a.supplier_site_id = d.supplier_site_id where 1=1 \r\n";

	public static String QUERY_SUPPLIER_SITES_LIST =
					"SELECT a.supplier_site_id supplierSiteId\n" +
					"      ,a.froze_flag frozeFlag\n" +
					"      ,a.payment_flag paymentFlag\n" +
					"      ,a.purchase_flag purchaseFlag\n" +
					"      ,a.supplier_id supplierId\n" +
					"      ,a.site_name siteName\n" +
					"      ,a.site_status siteStatus\n" +
					"      ,a.temporary_site_flag temporarySiteFlag\n" +
					"      ,c.inst_name instName\n" +
					"      ,a.unfroze_date unfrozeDate\n" +
					"      ,(CASE a.site_status\n" +
					"         WHEN 'DISABLED' THEN\n" +
					"          '失效'\n" +
					"         WHEN 'EFFECTIVE' THEN\n" +
					"          '合格'\n" +
					"         WHEN 'INTRODUCING' THEN\n" +
					"          '合格申请中'\n" +
					"         WHEN 'NEW' THEN\n" +
					"          ''\n" +
					"       END) meaning\n" +
					"      ,(CASE a.temporary_site_flag\n" +
					"         WHEN 'Y' THEN\n" +
					"          '是'\n" +
					"         WHEN 'N' THEN\n" +
					"          '否'\n" +
					"       END) tempSiteStr\n" +
					"      ,(CASE a.purchase_flag\n" +
					"         WHEN 'Y' THEN\n" +
					"          '是'\n" +
					"         WHEN 'N' THEN\n" +
					"          '否'\n" +
					"       END) purchaseFlagStr\n" +
					"      ,(CASE a.froze_flag\n" +
					"         WHEN 'Y' THEN\n" +
					"          '是'\n" +
					"         WHEN 'N' THEN\n" +
					"          '否'\n" +
					"       END) frozeFlagStr\n" +
					"      ,(CASE a.payment_flag\n" +
					"         WHEN 'Y' THEN\n" +
					"          '是'\n" +
					"         WHEN 'N' THEN\n" +
					"          '否'\n" +
					"       END) paymentFlagStr\n" +
					"      ,a.payment_flag actionPaymentFlagInit\n" +
					"      ,a.purchase_flag actionPurchaseFlagInit\n" +
					"FROM   srm_pos_supplier_sites a\n" +
					"LEFT   JOIN saaf_institution c\n" +
					"ON     a.org_id = c.inst_id\n" +
					"WHERE  1 = 1\n";

	public static String QUERY_FROZEN_SITES_LIST =
					"SELECT a.supplier_site_id supplierSiteId\n" +
					"      ,a.frozen_site_id frozenSiteId\n" +
					"      ,a.selected_flag selectedFlag\n" +
					"      ,a.site_status siteStatus\n" +
					"      ,d.temporary_site_flag temporarySiteFlag\n" +
					"      ,d.supplier_id supplierId\n" +
					"      ,d.site_name siteName\n" +
					"      ,c.inst_name instName\n" +
					"      ,(CASE a.froze_flag\n" +
					"         WHEN 'Y' THEN\n" +
					"          '是'\n" +
					"         WHEN 'N' THEN\n" +
					"          '否'\n" +
					"       END) frozeFlag\n" +
					"      ,a.unfroze_date unfrozeDate\n" +
					"      ,(CASE d.site_status\n" +
					"         WHEN 'DISABLED' THEN\n" +
					"          '失效'\n" +
					"         WHEN 'EFFECTIVE' THEN\n" +
					"          '合格'\n" +
					"         WHEN 'INTRODUCING' THEN\n" +
					"          '合格申请中'\n" +
					"         WHEN 'NEW' THEN\n" +
					"          ''\n" +
					"       END) meaning\n" +
					"      ,(CASE d.temporary_site_flag\n" +
					"         WHEN 'Y' THEN\n" +
					"          '是'\n" +
					"         WHEN 'N' THEN\n" +
					"          '否'\n" +
					"       END) tempSiteStr\n" +
					"      ,a.action_purchase_flag actionPurchaseFlag\n" +
					"      ,(CASE a.action_purchase_flag\n" +
					"         WHEN 'Y' THEN\n" +
					"          '否'\n" +
					"         WHEN 'N' THEN\n" +
					"          '是'\n" +
					"       END) purchaseFlagStr\n" +
					"      ,a.action_payment_flag actionPaymentFlag\n" +
					"      ,(CASE a.action_payment_flag\n" +
					"         WHEN 'Y' THEN\n" +
					"          '否'\n" +
					"         WHEN 'N' THEN\n" +
					"          '是'\n" +
					"       END) paymentFlagStr\n" +
					"FROM   srm_pos_frozen_sites a\n" +
					"LEFT   JOIN srm_pos_supplier_sites d\n" +
					"ON     d.supplier_site_id = a.supplier_site_id\n" +
					"LEFT   JOIN saaf_institution c\n" +
					"ON     d.org_id = c.inst_id\n" +
					"WHERE  1 = 1\n";

	// 根据地址ID，查询有效的供应商地点
	public static String QUERY_SITES_BY_STATUS =
					"SELECT pss.supplier_site_id supplierSiteId\n" +
					"      ,pss.supplier_id supplierId\n" +
					"      ,pss.supplier_address_id supplierAddressId\n" +
					"      ,pss.site_name siteName\n" +
					"      ,pss.org_id orgId\n" +
					"      ,pss.site_status siteStatus\n" +
					"      ,pss.payment_flag paymentFlag\n" +
					"      ,(CASE pss.payment_flag WHEN 'Y' THEN '是' ELSE '否' END) paymentFlagStr\n" +
					"      ,pss.purchase_flag purchaseFlag\n" +
					"      ,(CASE pss.purchase_flag WHEN 'Y' THEN '是' ELSE '否' END) purchaseFlagStr\n" +
					"      ,pss.temporary_site_flag temporarySiteFlag\n" +
					"      ,(CASE pss.temporary_site_flag WHEN 'Y' THEN '是' ELSE '否' END) temporarySiteFlagName\n" +
					"      ,pss.froze_flag frozeFlag\n" +
					"      ,(CASE pss.froze_flag WHEN 'Y' THEN '是' ELSE '否' END) frozeFlagStr\n" +
					"      ,pss.unfroze_date unfrozeDate\n" +
					"      ,pss.qualified_date qualifiedDate\n" +
					"      ,pss.invalid_date invalidDate\n" +
					"      ,org.inst_name instName\n" +
					"      ,slv.meaning siteStatusStr\n" +
					"  FROM srm_pos_supplier_sites pss\n" +
					"      ,saaf_institution       org\n" +
					"      ,saaf_lookup_values     slv\n" +
					" WHERE pss.org_id = org.inst_id\n" +
					"   AND org.inst_type = 'ORG'\n" +
					"   AND pss.site_status = slv.lookup_code\n" +
					"   AND slv.lookup_type = 'POS_SUPPLIER_SITE_STATUS'\n" +
					"   AND pss.site_status = 'EFFECTIVE'\n" +
					"   AND nvl(pss.temporary_site_flag, 'N') = 'N'\n" +
					"   AND nvl(pss.invalid_date, SYSDATE + 1) >= SYSDATE\n"+
					"   AND pss.purchase_flag = 'Y'\n";

	public static String QUERY_SITES_BY_NEW =
					"SELECT pst.supplier_site_id supplierSiteId\n" +
					"      ,pst.supplier_id supplierId\n" +
					"      ,pst.supplier_address_id supplierAddressId\n" +
					"      ,pst.site_name siteName\n" +
					"      ,pst.org_id orgId\n" +
					"      ,pst.site_status siteStatus\n" +
					"      ,pst.payment_flag paymentFlag\n" +
					"      ,(CASE pst.payment_flag WHEN 'Y' THEN '是' ELSE '否' END) paymentFlagStr\n" +
					"      ,pst.purchase_flag purchaseFlag\n" +
					"      ,(CASE pst.purchase_flag WHEN 'Y' THEN '是' ELSE '否' END) purchaseFlagStr\n" +
					"      ,pst.temporary_site_flag temporarySiteFlag\n" +
					"      ,(CASE pst.temporary_site_flag WHEN 'Y' THEN '是' ELSE '否' END) temporarySiteFlagName\n" +
					"      ,pst.froze_flag frozeFlag\n" +
					"      ,(CASE pst.froze_flag WHEN 'Y' THEN '是' ELSE '否' END) frozeFlagStr\n" +
					"      ,pst.unfroze_date unfrozeDate\n" +
					"      ,pst.qualified_date qualifiedDate\n" +
					"      ,pst.invalid_date invalidDate\n" +
					"      ,pst.inst_name instName\n" +
					"      ,pst.site_status_str siteStatusStr\n" +
					"  FROM (SELECT pss.supplier_site_id\n" +
					"              ,pss.supplier_id\n" +
					"              ,pss.supplier_address_id\n" +
					"              ,pss.site_name\n" +
					"              ,pss.org_id\n" +
					"              ,pss.site_status\n" +
					"              ,pss.payment_flag\n" +
					"              ,pss.purchase_flag\n" +
					"              ,pss.temporary_site_flag\n" +
					"              ,pss.froze_flag\n" +
					"              ,pss.unfroze_date\n" +
					"              ,pss.qualified_date\n" +
					"              ,pss.invalid_date\n" +
					"              ,org.inst_name\n" +
					"              ,slv.meaning site_status_str\n" +
					"          FROM srm_pos_supplier_sites pss\n" +
					"              ,saaf_institution       org\n" +
					"              ,saaf_lookup_values     slv\n" +
					"         WHERE pss.org_id = org.inst_id\n" +
					"           AND org.inst_type = 'ORG'\n" +
					"           AND pss.site_status = slv.lookup_code\n" +
					"           AND slv.lookup_type = 'POS_SUPPLIER_SITE_STATUS'\n" +
					"           AND (pss.site_status IN ('QUIT', 'DISABLED') OR\n" +
					"               nvl(pss.temporary_site_flag, 'N') = 'Y')\n" +
					"        UNION ALL\n" +
					"        SELECT NULL\n" +
					"              ,psa.supplier_id\n" +
					"              ,psa.supplier_address_id\n" +
					"              ,psa.address_name        site_name\n" +
					"              ,si.inst_id              org_id\n" +
					"              ,NULL                    site_status\n" +
					"              ,NULL                    payment_flag\n" +
					"              ,NULL                    purchase_flag\n" +
					"              ,NULL                    temporary_site_flag\n" +
					"              ,NULL                    froze_flag\n" +
					"              ,NULL                    unfroze_date\n" +
					"              ,NULL                    qualified_date\n" +
					"              ,NULL                    invalid_date\n" +
					"              ,si.inst_name\n" +
					"              ,NULL                    site_status_str\n" +
					"          FROM srm_pos_supplier_addresses psa\n" +
					"              ,saaf_institution           si\n" +
					"         WHERE si.inst_type = 'ORG'\n" +
					"           AND nvl(si.start_date, SYSDATE - 1) <= SYSDATE\n" +
					"           AND nvl(si.end_date, SYSDATE + 1) >= SYSDATE\n" +
					"           AND NOT EXISTS\n" +
					"         (SELECT 1\n" +
					"                  FROM srm_pos_supplier_sites pss\n" +
					"                 WHERE pss.supplier_address_id = psa.supplier_address_id\n" +
					"                   AND pss.org_id = si.inst_id)) pst\n" +
					" WHERE 1 = 1\n";

	// 查询供应商地点，属于现场考察
	public static String QUERY_SITES_OF_REVIEW =
					"SELECT\n" +
					"  t1.supplier_site_id AS supplierSiteId,\n" +
					"  t1.supplier_address_id AS supplierAddressId,\n" +
					"  t1.supplier_id AS supplierId,\n" +
					"  t1.site_name AS siteName,\n" +
					"  t1.org_id AS orgId,\n" +
					"  t2.inst_name AS instName,\n" +
					"  t1.site_status AS siteStatus,\n" +
					"  t3.meaning AS siteStatusStr,\n" +
					"  nvl(t1.temporary_site_flag, 'N') AS temporarySiteFlag,\n" +
					"  t4.meaning AS temporarySiteFlagName,\n" +
					"  site.locale_review_site_id AS localeReviewSiteId,\n" +
					"  decode(site.selected_flag, 'Y', 'Y', 'N') AS selectedFlag,\n" +
					"  site.selected_flag AS selectedFlag2\n" +
					"FROM\n" +
					"  srm_pos_qualification_sites qs,\n" +
					"  srm_pos_supplier_sites t1\n" +
					"  LEFT JOIN saaf_institution t2\n" +
					"    ON t1.org_id = t2.inst_id\n" +
					"  LEFT JOIN srm_pos_locale_review_sites site\n" +
					"    ON t1.supplier_site_id = site.supplier_site_id\n" +
					"    AND site.locale_review_id = :localeReviewId\n" +
					"  LEFT JOIN saaf_lookup_values t3\n" +
					"    ON t1.site_status = t3.lookup_code\n" +
					"    AND t3.lookup_type = 'POS_SUPPLIER_SITE_STATUS'\n" +
					"  LEFT JOIN saaf_lookup_values t4\n" +
					"    ON t4.lookup_code = nvl(t1.temporary_site_flag, 'N')\n" +
					"    AND t4.lookup_type = 'YSE_NO'\n" +
					"WHERE 1 = 1\n" +
					"  AND qs.supplier_site_id = t1.supplier_site_id\n" +
					"  AND qs.add_flag = 'Y'\n";

	//查询供应商地点-送货通知
	public static String QUERY_SITES_OF_NOTICE =
					"SELECT a.supplier_site_id    supplierSiteId\n" +
					"      ,a.supplier_id         supplierId\n" +
					"      ,a.supplier_address_id supplierAddressId\n" +
					"      ,a.site_name           siteName\n" +
					"      ,a.purchase_flag       purchaseFlag\n" +
					"      ,a.invalid_date        invalidDate\n" +
					"      ,a.org_id              orgId\n" +
					"FROM   srm_pos_supplier_sites a\n" +
					"WHERE  a.purchase_flag = 'Y'\n" +
					"AND    a.invalid_date IS NULL\n";

	public static String QUERY_SUPPLIER_PLAN_SITES =
					"SELECT ips.supplier_site_id   AS supplierSiteId\n" +
					"      ,t1.supplier_address_id AS supplierAddressId\n" +
					"      ,t1.supplier_id         AS supplierId\n" +
					"      ,t1.site_name           AS siteName\n" +
					"      ,t1.org_id              AS orgId\n" +
					"      ,t2.inst_name           AS instName\n" +
					"      ,t1.site_status         AS siteStatus\n" +
					"      ,t3.meaning             AS siteStatusStr\n" +
					"      ,t1.temporary_site_flag AS temporarySiteFlag\n" +
					"      ,t4.meaning             AS temporarySiteFlagName\n" +
					"FROM   srm_pos_investigation_plan_sites ips\n" +
					"LEFT   JOIN srm_pos_supplier_sites t1\n" +
					"ON     ips.supplier_site_id = t1.supplier_site_id\n" +
					"LEFT   JOIN saaf_institution t2\n" +
					"ON     t1.org_id = t2.inst_id\n" +
					"LEFT   JOIN saaf_lookup_values t3\n" +
					"ON     t1.site_status = t3.lookup_code\n" +
					"AND    t3.lookup_type = 'POS_SUPPLIER_SITE_STATUS'\n" +
					"LEFT   JOIN saaf_lookup_values t4\n" +
					"ON     t1.temporary_site_flag = t4.lookup_code\n" +
					"AND    t4.lookup_type = 'YSE_NO'\n" +
					"WHERE  1 = 1\n";

	private Integer supplierSiteId; //供应商地点ID
	private Integer supplierId; //供应商ID
	private Integer supplierAddressId; //供应商地址ID
	private String siteName; //地点名称
	private Integer orgId; //业务实体ID(关联saaf_institution)
	private String siteStatus; //地点状态(POS_SUPPLIER_SITE_STATUS)
	private String purchaseFlag; //可采购(Y/N)
	private String paymentFlag; //可付款(Y/N)
	private String frozeFlag; //已冻结(Y/N)
	@JSONField(format = "yyyy-MM-dd")
	private Date unfrozeDate; //解冻时间
	@JSONField(format = "yyyy-MM-dd")
	private Date qualifiedDate; //合格时间
	@JSONField(format = "yyyy-MM-dd")
	private Date invalidDate; //失效时间
	private String temporarySiteFlag; //临时地点标识
	private String sourceCode; //数据来源
	private String sourceId; //数据来源ID
	private String requestId;//报文请求ID
	private Integer versionNum;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;
	private Integer createdBy;
	private Integer lastUpdatedBy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;
	private Integer lastUpdateLogin;
	private String attributeCategory;
	private String attribute1;
	private String attribute2;
	private String attribute3;
	private String attribute4;
	private String attribute5;
	private String attribute6;
	private String attribute7;
	private String attribute8;
	private String attribute9;
	private String attribute10;
	private Integer operatorUserId;
	//别名
	private Integer frozenId;
	private String siteId;// 供应商退出地点表地点ID
	private String purchaseFlagStr;
	private String paymentFlagStr;
	private String siteStatusStr;// 地点状态
	private String instName;// 业务实体名称
	private Integer frozenSiteId; // 供应商地点ID
	private Integer localeReviewSiteId;// 现场考察地点ID
	private String selectedFlag;
	private String meaning;
	private String tempSiteStr;
	private String actionPurchaseFlag;
	private String actionPaymentFlag;
	private String temporarySiteFlagName;
	private String frozeFlagStr;
	private String actionPurchaseFlagInit;
	private String actionPaymentFlagInit;

	private String country;
	private String detailAddress;
	private String province;
	private String countryName;


	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getSiteStatusStr() {
		return siteStatusStr;
	}

	public void setSiteStatusStr(String siteStatusStr) {
		this.siteStatusStr = siteStatusStr;
	}

	public void setSupplierSiteId(Integer supplierSiteId) {
		this.supplierSiteId = supplierSiteId;
	}

	public Integer getSupplierSiteId() {
		return supplierSiteId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierAddressId(Integer supplierAddressId) {
		this.supplierAddressId = supplierAddressId;
	}

	public Integer getSupplierAddressId() {
		return supplierAddressId;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setSiteStatus(String siteStatus) {
		this.siteStatus = siteStatus;
	}

	public String getSiteStatus() {
		return siteStatus;
	}

	public void setPurchaseFlag(String purchaseFlag) {
		this.purchaseFlag = purchaseFlag;
	}

	public String getPurchaseFlag() {
		return purchaseFlag;
	}

	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}

	public String getPaymentFlag() {
		return paymentFlag;
	}

	public void setFrozeFlag(String frozeFlag) {
		this.frozeFlag = frozeFlag;
	}

	public String getFrozeFlag() {
		return frozeFlag;
	}

	public void setUnfrozeDate(Date unfrozeDate) {
		this.unfrozeDate = unfrozeDate;
	}

	public Date getUnfrozeDate() {
		return unfrozeDate;
	}

	public void setQualifiedDate(Date qualifiedDate) {
		this.qualifiedDate = qualifiedDate;
	}

	public Date getQualifiedDate() {
		return qualifiedDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	public Integer getVersionNum() {
		return versionNum;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	public String getAttribute10() {
		return attribute10;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getTempSiteStr() {
		return tempSiteStr;
	}

	public void setTempSiteStr(String tempSiteStr) {
		this.tempSiteStr = tempSiteStr;
	}

	public String getActionPurchaseFlag() {
		return actionPurchaseFlag;
	}

	public void setActionPurchaseFlag(String actionPurchaseFlag) {
		this.actionPurchaseFlag = actionPurchaseFlag;
	}

	public String getActionPaymentFlag() {
		return actionPaymentFlag;
	}

	public void setActionPaymentFlag(String actionPaymentFlag) {
		this.actionPaymentFlag = actionPaymentFlag;
	}

	public Integer getFrozenSiteId() {
		return frozenSiteId;
	}

	public void setFrozenSiteId(Integer frozenSiteId) {
		this.frozenSiteId = frozenSiteId;
	}

	public String getSelectedFlag() {
		return selectedFlag;
	}

	public void setSelectedFlag(String selectedFlag) {
		this.selectedFlag = selectedFlag;
	}

	public String getTemporarySiteFlag() {
		return temporarySiteFlag;
	}

	public void setTemporarySiteFlag(String temporarySiteFlag) {
		this.temporarySiteFlag = temporarySiteFlag;
	}

	public String getSiteId() {
		return siteId;
	}

	public String getTemporarySiteFlagName() {
		return temporarySiteFlagName;
	}

	public void setTemporarySiteFlagName(String temporarySiteFlagName) {
		this.temporarySiteFlagName = temporarySiteFlagName;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getPurchaseFlagStr() {
		return purchaseFlagStr;
	}

	public void setPurchaseFlagStr(String purchaseFlagStr) {
		this.purchaseFlagStr = purchaseFlagStr;
	}

	public String getPaymentFlagStr() {
		return paymentFlagStr;
	}

	public void setPaymentFlagStr(String paymentFlagStr) {
		this.paymentFlagStr = paymentFlagStr;
	}

	public String getFrozeFlagStr() {
		return frozeFlagStr;
	}

	public void setFrozeFlagStr(String frozeFlagStr) {
		this.frozeFlagStr = frozeFlagStr;
	}

	public Integer getLocaleReviewSiteId() {
		return localeReviewSiteId;
	}

	public void setLocaleReviewSiteId(Integer localeReviewSiteId) {
		this.localeReviewSiteId = localeReviewSiteId;
	}

	public String getActionPurchaseFlagInit() {
		return actionPurchaseFlagInit;
	}

	public void setActionPurchaseFlagInit(String actionPurchaseFlagInit) {
		this.actionPurchaseFlagInit = actionPurchaseFlagInit;
	}

	public String getActionPaymentFlagInit() {
		return actionPaymentFlagInit;
	}

	public void setActionPaymentFlagInit(String actionPaymentFlagInit) {
		this.actionPaymentFlagInit = actionPaymentFlagInit;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
