package saaf.common.fmw.pon.model.entities.readonly;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPonAuctionItemsEntity_HI_RO Entity Object Tue Apr 17 11:14:24 CST 2018
 * Auto Generate
 */

public class SrmPonAuctionItemsEntity_HI_RO implements Cloneable {

	@Override
	public Object clone() {
		SrmPonAuctionItemsEntity_HI_RO saiRO = null;
		try {
			saiRO = (SrmPonAuctionItemsEntity_HI_RO) super.clone();
		} catch (CloneNotSupportedException e) {
			//e.printStackTrace();
		}
		return saiRO;
	}

	//招标、询价的已截止（查询阶梯数量的筛选标的物）
	public static final String QUERY_AUCTION_ITEMS_LIST_SQLV =
					"SELECT\n" +
					"  pai.auction_line_id AS auctionLineId,\n" +
					"  pai.line_number AS lineNumber,\n" +
					"  pai.disp_line_number AS dispLineNumber,\n" +
					"  pai.auction_header_id AS auctionHeaderId,\n" +
					"  pai.line_type AS lineType,\n" +
					"  pai.parent_line_id AS parentLineId,\n" +
					"  pai.group_id AS groupId,\n" +
					"  pai.item_id AS itemId,\n" +
					"  pai.item_description AS itemDescription,\n" +
					"  pai.unit_of_measure AS unitOfMeasure,\n" +
					"  pai.category_id AS categoryId,\n" +
					"  pai.quantity AS quantity,\n" +
					"  pai.tax_rate_code AS taxRateCode,\n" +
					"  pai.start_date AS startDate,\n" +
					"  pai.end_date AS endDate,\n" +
					"  pai.award_status AS awardStatus,\n" +
					"  pai.awarded_quantity AS awardedQuantity,\n" +
					"  pai.file_id AS fileId,\n" +
					"  pai.notes AS notes,\n" +
					"  pai.version_num AS versionNum,\n" +
					"  pai.creation_date AS creationDate,\n" +
					"  pai.created_by AS createdBy,\n" +
					"  pai.last_updated_by AS lastUpdatedBy,\n" +
					"  pai.last_update_date AS lastUpdateDate,\n" +
					"  pai.last_update_login AS lastUpdateLogin,\n" +
					"  pai.attribute_category AS attributeCategory,\n" +
					"  pai.attribute1 AS attribute1,\n" +
					"  pai.attribute2 AS attribute2,\n" +
					"  pai.attribute3 AS attribute3,\n" +
					"  pai.attribute4 AS attribute4,\n" +
					"  pai.attribute5 AS attribute5,\n" +
					"  pai.attribute6 AS attribute6,\n" +
					"  pai.attribute7 AS attribute7,\n" +
					"  pai.attribute8 AS attribute8,\n" +
					"  pai.attribute9 AS attribute9,\n" +
					"  pai.attribute10 AS attribute10,\n" +
					"  spail.item_ladder_id AS itemLadderId,\n" +
					"  spail.ladder_quantity AS ladderQuantity,\n" +
					"  sbc.category_name AS categoryName,\n" +
					"  spag.group_name AS groupName,\n" +
					"  sbi.item_code AS itemCode,\n" +
					"  sbrl.file_Name AS fileName,\n" +
					"  slv.meaning AS unitOfMeasureName,\n" +
					"  sbrl.access_Path AS accessPath\n" +
					"FROM\n" +
					"  srm_pon_auction_items pai\n" +
					"  LEFT JOIN srm_base_categories sbc\n" +
					"    ON sbc.category_id = pai.category_id\n" +
					"  LEFT JOIN srm_pon_auction_groups spag\n" +
					"    ON spag.group_id = pai.group_id\n" +
					"  LEFT JOIN srm_base_items_b sbi\n" +
					"    ON sbi.item_id = pai.item_id\n" +
					"  LEFT JOIN srm_pon_auction_item_ladders spail\n" +
					"    ON spail.auction_line_id = pai.auction_line_id\n" +
					"  LEFT JOIN saaf_base_result_file sbrl\n" +
					"    ON sbrl.file_id = pai.file_id\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"    AND slv.lookup_code = pai.unit_of_measure\n" +
					"WHERE pai.auction_header_id = ?";

	// 招标，询价的拟定，使用的查询方法
	public static final String QUERY_AUCTION_ITEMS_LIST_SQL =
					"SELECT\n" +
					"  pai.auction_line_id AS auctionLineId,\n" +
					"  pai.line_number AS lineNumber,\n" +
					"  pai.disp_line_number AS dispLineNumber,\n" +
					"  pai.auction_header_id AS auctionHeaderId,\n" +
					"  pai.line_type AS lineType,\n" +
					"  pai.parent_line_id AS parentLineId,\n" +
					"  pai.group_id AS groupId,\n" +
					"  pai.item_id AS itemId,\n" +
					"  pai.item_description AS itemDescription,\n" +
					"  pai.unit_of_measure AS unitOfMeasure,\n" +
					"  pai.category_id AS categoryId,\n" +
					"  pai.quantity AS quantity,\n" +
					"  pai.tax_rate_code AS taxRateCode,\n" +
					"  pai.start_date AS startDate,\n" +
					"  pai.end_date AS endDate,\n" +
					"  pai.award_status AS awardStatus,\n" +
					"  pai.awarded_quantity AS awardedQuantity,\n" +
					"  pai.file_id AS fileId,\n" +
					"  pai.notes AS notes,\n" +
					"  pai.version_num AS versionNum,\n" +
					"  pai.creation_date AS creationDate,\n" +
					"  pai.created_by AS createdBy,\n" +
					"  pai.last_updated_by AS lastUpdatedBy,\n" +
					"  pai.last_update_date AS lastUpdateDate,\n" +
					"  pai.last_update_login AS lastUpdateLogin,\n" +
					"  pai.attribute_category AS attributeCategory,\n" +
					"  pai.attribute1 AS attribute1,\n" +
					"  pai.attribute2 AS attribute2,\n" +
					"  pai.attribute3 AS attribute3,\n" +
					"  pai.attribute4 AS attribute4,\n" +
					"  pai.attribute5 AS attribute5,\n" +
					"  pai.attribute6 AS attribute6,\n" +
					"  pai.attribute7 AS attribute7,\n" +
					"  pai.attribute8 AS attribute8,\n" +
					"  pai.attribute9 AS attribute9,\n" +
					"  pai.attribute10 AS attribute10,\n" +
					"  sbc.full_category_name AS categoryName,\n" +
					"  spag.group_name AS groupName,\n" +
					"  sbi.item_code AS itemCode,\n" +
					"  (CASE WHEN pai.cost IS NOT NULL THEN pai.cost ELSE sbi.cost END) AS costBudget,\n" +
					"  sbrl.file_Name AS fileName,\n" +
					"  (CASE WHEN slv.meaning IS NOT NULL THEN slv.meaning ELSE pai.unit_of_measure END) AS unitOfMeasureName,\n" +
					"  sbrl.access_Path AS accessPath,\n" +
                    "  pai.specification AS specification\n" +
					"FROM\n" +
					"  srm_pon_auction_items pai\n" +
					"  LEFT JOIN srm_base_categories sbc\n" +
					"    ON sbc.category_id = pai.category_id\n" +
					"  LEFT JOIN srm_pon_auction_groups spag\n" +
					"    ON spag.group_id = pai.group_id\n" +
					"  LEFT JOIN srm_base_items_b sbi\n" +
					"    ON sbi.item_id = pai.item_id\n" +
					"  LEFT JOIN saaf_base_result_file sbrl\n" +
					"    ON sbrl.file_id = pai.file_id\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_code = pai.unit_of_measure\n" +
					"    AND slv.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"WHERE pai.auction_header_id = ?\n";

	public static final String QUERY_AUCTION_ITEMS_SUPPLISER_LIST_SQL =
					"SELECT\n" +
					"  pai.auction_line_id AS auctionLineId,\n" +
					"  pai.line_number AS lineNumber,\n" +
					"  pai.disp_line_number AS dispLineNumber,\n" +
					"  pai.auction_header_id AS auctionHeaderId,\n" +
					"  pai.line_type AS lineType,\n" +
					"  pai.parent_line_id AS parentLineId,\n" +
					"  pai.group_id AS groupId,\n" +
					"  pai.item_id AS itemId,\n" +
					"  pai.item_description AS itemDescription,\n" +
					"  pai.unit_of_measure AS unitOfMeasure,\n" +
					"  pai.category_id AS categoryId,\n" +
					"  pai.quantity AS quantity,\n" +
					"  pai.tax_rate_code AS taxRateCode,\n" +
					"  pai.start_date AS startDate,\n" +
					"  pai.end_date AS endDate,\n" +
					"  pai.award_status AS awardStatus,\n" +
					"  pai.awarded_quantity AS awardedQuantity,\n" +
					"  pai.file_id AS fileId,\n" +
					"  pai.notes AS notes,\n" +
					"  IF(pai.end_date < NOW(), 'Y', 'N') AS isShowPrice,\n" +
					"  pai.version_num AS versionNum,\n" +
					"  pai.creation_date AS creationDate,\n" +
					"  pai.created_by AS createdBy,\n" +
					"  pai.last_updated_by AS lastUpdatedBy,\n" +
					"  pai.last_update_date AS lastUpdateDate,\n" +
					"  pai.last_update_login AS lastUpdateLogin,\n" +
					"  pai.attribute_category AS attributeCategory,\n" +
					"  pai.attribute1 AS attribute1,\n" +
					"  pai.attribute2 AS attribute2,\n" +
					"  pai.attribute3 AS attribute3,\n" +
					"  pai.attribute4 AS attribute4,\n" +
					"  pai.attribute5 AS attribute5,\n" +
					"  pai.attribute6 AS attribute6,\n" +
					"  pai.attribute7 AS attribute7,\n" +
					"  pai.attribute8 AS attribute8,\n" +
					"  pai.attribute9 AS attribute9,\n" +
					"  pai.attribute10 AS attribute10,\n" +
					"  sbc.category_name AS categoryName,\n" +
					"  spag.group_name AS groupName,\n" +
					"  sbi.item_code AS itemCode,\n" +
					"  sbrl.file_Name AS fileName,\n" +
					"  sbrl.access_Path AS accessPath,\n" +
					"  bip.no_tax_price AS noTaxPrice,\n" +
					"  bhe.supplier_name AS supplierName,\n" +
					"  psi.supplier_status AS supplierStatus,\n" +
					"  bip.bid_line_id AS bidLineId,\n" +
					"  slv1.meaning AS supplierStatusName,\n" +
					"  pah.payment_condition AS paymentCondition,\n" +
					"  bhe.payment_condition AS paymentConditionOfBid,\n" +
					"  slv2.meaning AS paymentConditionName,\n" +
					"  slv3.meaning AS taxRateName,\n" +
					"  slv4.meaning AS awardStatusName,\n" +
					"  (CASE WHEN slv5.meaning IS NOT NULL THEN slv5.meaning ELSE pai.unit_of_measure END) AS unitOfMeasureName,\n" +
					"  bip.promised_quantity AS promisedQuantity,\n" +
					"  bip.award_quantity AS awardQuantity,\n" +
					"  bip.award_proportion AS awardProportion,\n" +
					"  bhe.supplier_id AS supplierId,\n" +
                    "  pai.specification AS specification\n" +
					"FROM\n" +
					"  srm_pon_auction_items pai\n" +
					"  LEFT JOIN srm_pon_bid_headers bhe\n" +
					"    ON bhe.auction_header_id = pai.auction_header_id\n" +
					"    AND bhe.bid_status = 'SUBMITTED'\n" +
					"  LEFT JOIN srm_pon_bid_item_prices bip\n" +
					"    ON bip.item_id = pai.item_id\n" +
					"    AND bip.auction_header_id = pai.auction_header_id\n" +
					"  LEFT JOIN srm_base_categories sbc\n" +
					"    ON sbc.category_id = pai.category_id\n" +
					"  LEFT JOIN srm_pon_auction_groups spag\n" +
					"    ON spag.group_id = pai.group_id\n" +
					"  LEFT JOIN srm_base_items sbi\n" +
					"    ON sbi.item_id = pai.item_id\n" +
					"  LEFT JOIN saaf_base_result_file sbrl\n" +
					"    ON sbrl.file_id = pai.file_id\n" +
					"  LEFT JOIN srm_pon_auction_headers pah\n" +
					"    ON pai.auction_header_id = pah.auction_header_id\n" +
					"  LEFT JOIN srm_pos_supplier_info psi\n" +
					"    ON psi.supplier_id = bhe.supplier_id\n" +
					"  LEFT JOIN saaf_lookup_values slv1\n" +
					"    ON slv1.lookup_type = 'POS_SUPPLIER_INFO_STATUS'\n" +
					"    AND slv1.lookup_code = psi.supplier_status\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_type = 'PON_PAY_TYPE'\n" +
					"    AND slv2.lookup_code = pah.payment_condition\n" +
					"  LEFT JOIN saaf_lookup_values slv3\n" +
					"    ON slv3.lookup_type = 'PON_TAX_LIST'\n" +
					"    AND slv3.lookup_code = pai.tax_rate_code\n" +
					"  LEFT JOIN saaf_lookup_values slv4\n" +
					"    ON slv4.lookup_type = 'PON_AWARD_STATUS'\n" +
					"    AND slv4.lookup_code = bhe.award_status\n" +
					"  LEFT JOIN saaf_lookup_values slv5\n" +
					"    ON slv5.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"    AND slv5.lookup_code = pai.unit_of_measure\n" +
					"WHERE pai.auction_header_id = ?";

	public static final String QUERY_AUCTION_ITEMS_SUPPLISER_SQL = " SELECT\n" +
			" t.auction_line_id AS auctionLineId,\n" +
			" bip.bid_header_id AS bidHeaderId,\n" +
			" bip.bid_line_id AS bidLineId,\n" +
			" t.line_number AS lineNumber,\n" +
			" t.disp_line_number AS dispLineNumber,\n" +
			" t.auction_header_id AS auctionHeaderId,\n" +
			" t.line_type AS lineType,\n" +
			" t.parent_line_id AS parentLineId,\n" +
			" t.group_id AS groupId,\n" +
			" t.item_id AS itemId,\n" +
			" t.item_description AS itemDescription,\n" +
			" t.unit_of_measure AS unitOfMeasure,\n" +
			" t.category_id AS categoryId,\n" +
			" t.quantity AS quantity,\n" +
			" t.tax_rate_code AS taxRateCode,\n" +
			" t.start_date AS startDate,\n" +
			" t.end_date AS endDate,\n" +
			" t.award_status AS awardStatus,\n" +
			" t.awarded_quantity AS awardedQuantity,\n" +
			" t.file_id AS fileId,\tt.notes AS notes,\n" +
			" if(t.end_date < now(),'Y','N') AS isShowPrice,\n" +
			" t.version_num AS versionNum,\n" +
			" t.creation_date AS creationDate,\n" +
			" t.created_by AS createdBy,\n" +
			" t.last_updated_by AS lastUpdatedBy,\n" +
			" t.last_update_date AS lastUpdateDate,\n" +
			" t.last_update_login AS lastUpdateLogin,\n" +
			" t.attribute_category AS attributeCategory,\n" +
			" sbc.category_name AS categoryName,\n" +
			" spag.group_name AS groupName,\n" +
			" sbi.item_code AS itemCode,\n" +
			" sbrl.file_Name AS fileName,\n" +
			" sbrl.access_Path AS accessPath,\n" +
			" bip.no_tax_price AS noTaxPrice,\n" +
			" bip.tax_price AS taxPrice,\n" +
			" bhe.supplier_name AS supplierName,\n" +
			" psi.supplier_status AS supplierStatus,\n" +
			" bip.bid_line_id AS bidLineId,\n" +
			" slv1.meaning AS supplierStatusName,\n" +
			" pah.payment_condition AS paymentCondition,\n" +
			" bhe.payment_condition AS paymentConditionOfBid,\n" +
			" ppt.payment_term_name AS paymentConditionName,\n" +
			" slv3.meaning AS taxRateName,\n" +
			" slv4.meaning AS awardStatusName,\n" +
			" slv5.meaning AS unitOfMeasureName,\n" +
			" bip.promised_quantity AS promisedQuantity,\n" +
			" bip.award_quantity AS awardQuantity,\n" +
			" bip.award_proportion AS awardProportion,\n" +
			" bhe.supplier_id AS supplierId,\n" +
			" bip.award_status AS bipAwardStatus\n" +
			" FROM\n" +
			" srm_pon_auction_items t\n" +
			" LEFT JOIN srm_pon_bid_headers bhe \n" +
			" ON bhe.auction_header_id = t.auction_header_id and bhe.bid_status='ACT'\n" +
			" LEFT JOIN srm_pon_bid_item_prices bip\n" +
			" ON bip.item_id = t.item_id and bip.auction_header_id = t.auction_header_id and bip.bid_header_id = bhe.bid_header_id\n" +
			" LEFT JOIN srm_base_categories sbc\n" +
			" ON sbc.category_id = t.category_id\n" +
			" LEFT JOIN srm_pon_auction_groups spag\n" +
			" ON spag.group_id = t.group_id\n" +
			" LEFT JOIN srm_base_items sbi\n" +
			" ON sbi.item_id = t.item_id AND EXISTS\n" +
			"    (SELECT      1    FROM      saaf_institution si,      srm_pon_auction_headers t2    WHERE si.inst_id = sbi.organization_id       AND si.inst_type = 'ORGANIZATION'" +
			//"       AND si.parent_inst_id = t2.org_id " +
			"       AND si.inst_id = t2.receive_to_organization_id " +
			"      AND t2.auction_header_id = t.auction_header_id)\n" +
			" LEFT JOIN saaf_base_result_file sbrl\n" +
			" ON sbrl.file_id = t.file_id\n" +
			" LEFT JOIN srm_pon_auction_headers pah\n" +
			" ON t.auction_header_id = pah.auction_header_id\n" +
			" LEFT JOIN srm_pos_supplier_info psi\n" +
			" ON psi.supplier_id = bhe.supplier_id\n" +
			" LEFT JOIN saaf_lookup_values slv1\n" +
			" ON slv1.lookup_type = 'POS_SUPPLIER_STATUS' and slv1.lookup_code= psi.supplier_status\n" +
			" LEFT JOIN srm_pon_payment_terms ppt\n" +
			" ON ppt.payment_term_code= pah.payment_condition\n" +
			" LEFT JOIN saaf_lookup_values slv3\n" +
			" ON slv3.lookup_type = 'PON_TAX_LIST' and slv3.lookup_code = t.tax_rate_code\n" +
			" LEFT JOIN saaf_lookup_values slv4\n" +
			" ON slv4.lookup_type = 'PON_AWARD_STATUS' and slv4.lookup_code= t.award_status\n" +
			" LEFT JOIN saaf_lookup_values slv5\n" +
			" ON slv5.lookup_type = 'BASE_ITEMS_UNIT' AND slv5.lookup_code = t.unit_of_measure" +
			" WHERE 1=1 \r\n";

	public static final String QUERY_AUCTION_ITEMS_BID_SQL =
					"SELECT DISTINCT\n" +
					"  pbh.supplier_id supplierId\n" +
					"  , pbip.tax_rate taxRateCode\n" +
					"  , pbh.bid_header_id bidHeaderId\n" +
					"  , pbh.supplier_site_id supplierSiteId\n" +
					"  , pbh.supplier_contact_id supplierContactId\n" +
					"FROM\n" +
					"  srm_pon_bid_headers pbh\n" +
					"  , srm_pon_bid_item_prices pbip\n" +
					"WHERE pbh.bid_header_id = pbip.bid_header_id\n" +
					"  AND pbip.award_status = '4'\n" +
					"  AND pbh.auction_header_id = :auctionHeaderId";

	public static final String QUERY_AUCTION_ITEMS_BID_LINE_SQL =
					"SELECT\n" +
					"  pbh.bid_header_id\n" +
					"  , pbh.auction_header_id\n" +
					"  , pbh.bid_number\n" +
					"  , pbh.bid_status\n" +
					"  , pbh.supplier_id\n" +
					"  , pbh.supplier_name\n" +
					"  , pbh.supplier_site_id\n" +
					"  , pbh.supplier_contact_id\n" +
					"  , pbh.supplier_contact_name\n" +
					"  , pbh.supplier_contact_phone\n" +
					"  , pbh.supplier_contact_email\n" +
					"  , pbh.payment_condition\n" +
					"  , pbh.award_status\n" +
					"  , pbh.award_date\n" +
					"  , pbh.publish_date\n" +
					"  , pbh.cancelled_date\n" +
					"  , pbh.currency_code\n" +
					"  , pbh.note_to_auction_owner\n" +
					"  , pbh.original_bid_header_id\n" +
					"  , pbh.po_header_id\n" +
					"  , pbh.version_num\n" +
					"  , pbh.creation_date\n" +
					"  , pbh.created_by\n" +
					"  , pbh.last_updated_by\n" +
					"  , pbh.last_update_date\n" +
					"  , pbh.last_update_login\n" +
					"  , pbip.bid_line_id\n" +
					"  , pbip.auction_line_id\n" +
					"  , pbip.item_ladder_id\n" +
					"  , pbip.item_id\n" +
					"  , pbip.item_description\n" +
					"  , pbip.unit_of_measure\n" +
					"  , pbip.promised_quantity\n" +
					"  , pbip.tax_price\n" +
					"  , pbip.tax_rate as taxRateCode\n" +
					"  , pbip.no_tax_price\n" +
					"  , pbip.rank\n" +
					"  , pbip.promised_start_date\n" +
					"  , pbip.promised_end_date\n" +
					"  , pbip.award_proportion\n" +
					"  , pbip.award_quantity\n" +
					"  , pbip.bid_line_file_id\n" +
					"  , pbip.notes\n" +
					"  , sbi.category_id\n" +
					"  , pai.quantity\n" +
					"  , pai.start_date\n" +
					"  , pai.end_date\n" +
					"FROM\n" +
					"  srm_pon_bid_headers pbh\n" +
					"  , srm_pon_bid_item_prices pbip\n" +
					"  , srm_pon_auction_items pai\n" +
					"  , srm_base_items_b sbi\n" +
					"WHERE pbh.bid_header_id = pbip.bid_header_id\n" +
					"  AND pbip.auction_line_id = pai.auction_line_id\n" +
					"  AND pbip.item_id = sbi.item_id\n" +
					"  AND pbip.award_status = '4'\n" +
					"  AND pbip.tax_rate = :taxRateCode\n" +
					"  AND pbh.bid_header_id = :bidHeaderId";

	public static final String QUERY_ITEMS_BID_LINE_SQL = "SELECT\n" +
			"\tspai.auction_line_id auctionLineId,\n" +
			"\tspai.auction_header_id auctionHeaderId,\n" +
			"\tspai.line_number lineNumber,\n" +
			"\tspai.disp_line_number dispLineNumber,\n" +
			"\tspai.line_type lineType,\n" +
			"\tspai.parent_line_id parentLineId,\n" +
			"\tspai.group_id groupId,\n" +
			"\tspai.item_id itemId,\n" +
			"\tsbi.item_code itemNumber,\n" +
			"\tspai.item_description itemDescription,\n" +
			"\tspai.unit_of_measure unitOfMeasureNum,\n" +
			"\tslv1.meaning unitOfMeasure,\n" +
			"\tspai.category_id categoryId,\n" +
			"\tspai.quantity quantity,\n" +
			"\tspai.tax_rate_code taxRateCode,\n" +
			"\tslv.meaning taxRateName,\n" +
			"\tspai.start_date startDate,\n" +
			"\tspai.end_date endDate,\n" +
			"\tspai.award_status awardStatus,\n" +
			"\tspai.awarded_quantity awardedQuantity,\n" +
			"\tspai.file_id fileId,\n" +
			"\tspai.notes notes,\n" +
			"\tsbc.category_name categoryName,\n" +
			"\tspag.group_name groupName,\n" +
			"\tspah.last_round lastRound,\n" +
			"\trf.access_Path filePath,\n" +
			"\trf.file_Name fileName,\n" +
			"\tspah.auction_type auctionType,\n" +
			"\tpail.ladder_quantity ladderQuantity,\n" +
			"\tpail.item_ladder_id itemLadderId,\n" +
			"\tspah.subsection_flag subsectionFlag\n" +
			"FROM\n" +
			"\tsrm_pon_auction_headers spah,\n" +
			"\tsrm_pon_auction_items spai\n" +
			"LEFT JOIN srm_pon_auction_item_ladders pail ON pail.auction_header_id = spai.auction_header_id\n" +
			"and pail.auction_line_id = spai.auction_line_id\n" +
			"LEFT JOIN saaf_lookup_values slv1 ON slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
			"and slv1.lookup_code = spai.unit_of_measure\n" +
			"left join saaf_base_result_file rf on rf.file_id = spai.file_id\n" +
			"LEFT JOIN srm_base_categories sbc ON sbc.category_id = spai.category_id\n" +
			"LEFT JOIN srm_pon_auction_groups spag ON spag.group_id = spai.group_id\n" +
			"LEFT JOIN srm_base_items sbi ON sbi.item_id = spai.item_id\n" +
			"AND EXISTS (\n" +
			"\tSELECT\n" +
			"\t\t1\n" +
			"\tFROM\n" +
			"\t\tsaaf_institution si,\n" +
			"\t\tsrm_pon_auction_headers t2\n" +
			"\tWHERE\n" +
			"\t\tsi.inst_id = sbi.organization_id\n" +
			"\tAND si.inst_type = 'ORGANIZATION'\n" +
			//"\tAND si.parent_inst_id = t2.org_id\n" +
			"\tAND si.inst_id = t2.receive_to_organization_id\n" +
			"\tAND t2.auction_header_id = spai.auction_header_id\n" +
			")\n" +
			"left join saaf_lookup_values slv on slv.lookup_type = 'PON_TAX_LIST'\n" +
			"and slv.lookup_code = spai.tax_rate_code\n" +
			"WHERE\n" +
			"\tspah.auction_header_id = spai.auction_header_id";

	public static final String QUERY_AUCTION_ITEMS_LINE_SQL = "select t.auction_line_id from srm_pon_auction_items t LEFT JOIN srm_base_items_b b ON b.item_id=t.item_id where 1=1 ";

	public static final String QUERY_AUCTION_ITEMS_BID_LINE_FOR_PRICE_LIBRARY_SQL =
			"SELECT\n" +
					"  pbh.bid_header_id\n" +
					"  , pbh.auction_header_id\n" +
					"  , pbh.bid_number\n" +
					"  , pbh.bid_status\n" +
					"  , pbh.supplier_id\n" +
					"  , pbh.supplier_name\n" +
					"  , pbh.supplier_site_id\n" +
					"  , pbh.supplier_contact_id\n" +
					"  , pbh.supplier_contact_name\n" +
					"  , pbh.supplier_contact_phone\n" +
					"  , pbh.supplier_contact_email\n" +
					"  , pbh.payment_condition\n" +
					"  , pbh.award_status\n" +
					"  , pbh.award_date\n" +
					"  , pbh.publish_date\n" +
					"  , pbh.cancelled_date\n" +
					"  , pbh.currency_code\n" +
					"  , pbh.note_to_auction_owner\n" +
					"  , pbh.original_bid_header_id\n" +
					"  , pbh.po_header_id\n" +
					"  , pbh.version_num\n" +
					"  , pbh.creation_date\n" +
					"  , pbh.created_by\n" +
					"  , pbh.last_updated_by\n" +
					"  , pbh.last_update_date\n" +
					"  , pbh.last_update_login\n" +
					"  , pbip.bid_line_id\n" +
					"  , pbip.auction_line_id\n" +
					"  , pbip.item_ladder_id\n" +
					"  , pbip.item_id\n" +
					"  , pbip.item_description\n" +
					"  , pbip.unit_of_measure\n" +
					"  , pbip.promised_quantity\n" +
					"  , pbip.tax_price\n" +
					"  , pbip.tax_rate as taxRateCode\n" +
					"  , pbip.no_tax_price\n" +
                    "  , pbip.materials_price\n" +
                    "  , pbip.artificial_price\n" +
					"  , pbip.rank\n" +
					"  , pbip.promised_start_date\n" +
					"  , pbip.promised_end_date\n" +
					"  , pbip.award_proportion\n" +
					"  , pbip.award_quantity\n" +
					"  , pbip.bid_line_file_id\n" +
					"  , pbip.notes\n" +
					"  , sbi.category_id\n" +
					"  , pai.quantity\n" +
					"  , pai.start_date\n" +
					"  , pai.end_date\n" +
                    "  , pah.item_Type\n" +
					"FROM\n" +
					"  srm_pon_bid_headers pbh\n" +
					"  , srm_pon_bid_item_prices pbip\n" +
					"  , srm_pon_auction_items pai\n" +
					"  , srm_base_items_b sbi\n" +
                    "  , srm_pon_auction_headers pah\n" +
					"WHERE pbh.bid_header_id = pbip.bid_header_id\n" +
					"  AND pbip.auction_line_id = pai.auction_line_id\n" +
					"  AND pbip.item_id = sbi.item_id\n" +
                    "  AND pah.auction_header_id=pbh.auction_header_id\n" +
					"  AND pbip.award_status = '4'";

	private Integer auctionLineId; // 洽谈行ID
	private Integer auctionHeaderId; // 洽谈ID
	private Integer lineNumber; // 行号
	private String dispLineNumber; // 显示行号
	private String lineType; // 行类型，LINE：行，GROUP：组，GROUP_LINE：组的行
	private Integer parentLineId; // 父级行ID，备用字段
	private Integer groupId; // 组别ID
	private Integer itemId; // 物料ID，关联表：SRM_BASE_ITEMS
	private String itemDescription; // 物料说明
	private String unitOfMeasure; // 计量单位
	private Integer categoryId; // 分类ID，关联表：SRM_BASE_CATEGORIES
	private BigDecimal quantity; // 数量
	private String taxRateCode; // 税率
	@JSONField(format = "yyyy-MM-dd")
	private Date startDate; // 开始日期
	@JSONField(format = "yyyy-MM-dd")
	private Date endDate; // 结束日期
	private String awardStatus; // 决标状态
	private BigDecimal awardedQuantity; // 决标数量
	private Integer fileId; // 附件ID
	private String notes; // 备注
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
	private BigDecimal webReferencePrice;//网站参考价
	private BigDecimal discountPrice;//折后价
	private Integer operatorUserId;

	// 别名
	private String categoryName;// 采购分类
	private String groupName; // 组别名称
	private String itemCode; // 物料编码
	private Integer itemLaddersNumber;// 阶梯数量list的记录条数
	private String laddersFlag;//是否复查
	private String fileName; // 附件名称
	private String accessPath; // 附件下载路径
	private BigDecimal noTaxPrice;// 不含税单价
	private BigDecimal taxPrice;//含税单价
	private String supplierName;// 供应商名称
	private String supplierStatus;// 供应商状态
	private String supplierStatusName;// 供应商状态（快码）
	private String paymentCondition;// 付款条件
	private String paymentConditionName;// 付款条件（快码）
	private String taxRateName;// 税率（快码）
	private String awardStatusName;// 中标状态（快码）
	private BigDecimal promisedQuantity;// 承诺数量
	private String isShowPrice;// 报价时间截止后价格不进行显示 Y N
	private Integer supplierId;
	private Integer bidLineId;// 供应商报价行ID
	private String unitOfMeasureName;// 计量单位的快码别名
	private BigDecimal awardQuantity; // 中标数量
	private BigDecimal awardProportion; // 中标份额
	private BigDecimal ladderQuantity; //阶梯数量
	private Integer itemLadderId; // 洽谈行阶梯ID
	private Integer bidHeaderId;//报价头ID
	private Integer supplierSiteId;//报价头供应商地点ID
	private Integer supplierContactId;
	private BigDecimal costBudget;//成本预算
    private String itemType;
    private BigDecimal materialsPrice;//材料单价
    private BigDecimal artificialPrice;//人工单价
    private BigDecimal cost;

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    private String specification;



    public BigDecimal getMaterialsPrice() {
        return materialsPrice;
    }

    public void setMaterialsPrice(BigDecimal materialsPrice) {
        this.materialsPrice = materialsPrice;
    }

    public BigDecimal getArtificialPrice() {
        return artificialPrice;
    }

    public void setArtificialPrice(BigDecimal artificialPrice) {
        this.artificialPrice = artificialPrice;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getSupplierSiteId() {
		return supplierSiteId;
	}

	public void setSupplierSiteId(Integer supplierSiteId) {
		this.supplierSiteId = supplierSiteId;
	}

	public Integer getItemLadderId() {
		return itemLadderId;
	}

	public void setItemLadderId(Integer itemLadderId) {
		this.itemLadderId = itemLadderId;
	}

	public BigDecimal getLadderQuantity() {
		return ladderQuantity;
	}

	public void setLadderQuantity(BigDecimal ladderQuantity) {
		this.ladderQuantity = ladderQuantity;
	}

	public String getUnitOfMeasureName() {
		return unitOfMeasureName;
	}

	public void setUnitOfMeasureName(String unitOfMeasureName) {
		this.unitOfMeasureName = unitOfMeasureName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAccessPath() {
		return accessPath;
	}

	public void setAccessPath(String accessPath) {
		this.accessPath = accessPath;
	}

	public Integer getItemLaddersNumber() {
		return itemLaddersNumber;
	}

	public void setItemLaddersNumber(Integer itemLaddersNumber) {
		this.itemLaddersNumber = itemLaddersNumber;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setAuctionLineId(Integer auctionLineId) {
		this.auctionLineId = auctionLineId;
	}

	public Integer getAuctionLineId() {
		return auctionLineId;
	}

	public void setAuctionHeaderId(Integer auctionHeaderId) {
		this.auctionHeaderId = auctionHeaderId;
	}

	public Integer getAuctionHeaderId() {
		return auctionHeaderId;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setDispLineNumber(String dispLineNumber) {
		this.dispLineNumber = dispLineNumber;
	}

	public String getDispLineNumber() {
		return dispLineNumber;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public String getLineType() {
		return lineType;
	}

	public void setParentLineId(Integer parentLineId) {
		this.parentLineId = parentLineId;
	}

	public Integer getParentLineId() {
		return parentLineId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setTaxRateCode(String taxRateCode) {
		this.taxRateCode = taxRateCode;
	}

	public String getTaxRateCode() {
		return taxRateCode;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setAwardStatus(String awardStatus) {
		this.awardStatus = awardStatus;
	}

	public String getAwardStatus() {
		return awardStatus;
	}

	public void setAwardedQuantity(BigDecimal awardedQuantity) {
		this.awardedQuantity = awardedQuantity;
	}

	public BigDecimal getAwardedQuantity() {
		return awardedQuantity;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
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

	public BigDecimal getNoTaxPrice() {
		return noTaxPrice;
	}

	public void setNoTaxPrice(BigDecimal noTaxPrice) {
		this.noTaxPrice = noTaxPrice;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierStatus() {
		return supplierStatus;
	}

	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}

	public String getSupplierStatusName() {
		return supplierStatusName;
	}

	public void setSupplierStatusName(String supplierStatusName) {
		this.supplierStatusName = supplierStatusName;
	}

	public String getPaymentCondition() {
		return paymentCondition;
	}

	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

	public String getPaymentConditionName() {
		return paymentConditionName;
	}

	public void setPaymentConditionName(String paymentConditionName) {
		this.paymentConditionName = paymentConditionName;
	}

	public String getTaxRateName() {
		return taxRateName;
	}

	public void setTaxRateName(String taxRateName) {
		this.taxRateName = taxRateName;
	}

	public String getAwardStatusName() {
		return awardStatusName;
	}

	public void setAwardStatusName(String awardStatusName) {
		this.awardStatusName = awardStatusName;
	}

	public BigDecimal getPromisedQuantity() {
		return promisedQuantity;
	}

	public void setPromisedQuantity(BigDecimal promisedQuantity) {
		this.promisedQuantity = promisedQuantity;
	}

	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public String getIsShowPrice() {
		return isShowPrice;
	}

	public void setIsShowPrice(String isShowPrice) {
		this.isShowPrice = isShowPrice;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public BigDecimal getAwardQuantity() {
		return awardQuantity;
	}

	public void setAwardQuantity(BigDecimal awardQuantity) {
		this.awardQuantity = awardQuantity;
	}

	public BigDecimal getAwardProportion() {
		return awardProportion;
	}

	public void setAwardProportion(BigDecimal awardProportion) {
		this.awardProportion = awardProportion;
	}

	public Integer getBidLineId() {
		return bidLineId;
	}

	public void setBidLineId(Integer bidLineId) {
		this.bidLineId = bidLineId;
	}

	public Integer getBidHeaderId() {
		return bidHeaderId;
	}

	public String getLaddersFlag() {
		return laddersFlag;
	}

	public void setLaddersFlag(String laddersFlag) {
		this.laddersFlag = laddersFlag;
	}

	public void setBidHeaderId(Integer bidHeaderId) {
		this.bidHeaderId = bidHeaderId;
	}

	public Integer getSupplierContactId() {
		return supplierContactId;
	}

	public void setSupplierContactId(Integer supplierContactId) {
		this.supplierContactId = supplierContactId;
	}

	public BigDecimal getCostBudget() {
		return costBudget;
	}

	public void setCostBudget(BigDecimal costBudget) {
		this.costBudget = costBudget;
	}

	public BigDecimal getWebReferencePrice() {
		return webReferencePrice;
	}

	public void setWebReferencePrice(BigDecimal webReferencePrice) {
		this.webReferencePrice = webReferencePrice;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
}
