package saaf.common.fmw.pon.model.entities.readonly;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPonBidItemPricesEntity_HI_RO Entity Object Tue Apr 17 11:14:34 CST 2018
 * Auto Generate
 */

public class SrmPonBidItemPricesEntity_HI_RO {

	//查询供应商报价行list（不分页）——已截止（阶梯数量）
	public static final String QUERY_BIDITEMPRICESLIST_SQL =
					"SELECT\n" +
					"  pbip.bid_line_id AS bidLineId,\n" +
					"  pbip.bid_header_id AS bidHeaderId,\n" +
					"  pbip.auction_header_id AS auctionHeaderId,\n" +
					"  pbip.auction_line_id AS auctionLineId,\n" +
					"  pbip.item_ladder_id AS itemLadderId,\n" +
					"  pbip.item_id AS itemId,\n" +
					"  pbip.item_description AS itemDescription,\n" +
					"  pbip.unit_of_measure AS unitOfMeasure,\n" +
					"  pbip.promised_quantity AS promisedQuantity,\n" +
					"  pbip.tax_price AS taxPrice,\n" +
					"  pbip.tax_rate AS taxRate,\n" +
					"  pbip.no_tax_price AS noTaxPrice,\n" +
					"  pbip.bargain AS bargain,\n" +
					"  pbip.rank AS rank,\n" +
					"  pbip.promised_start_date AS promisedStartDate,\n" +
					"  pbip.promised_end_date AS promisedEndDate,\n" +
					"  pbip.award_proportion AS awardProportion,\n" +
					"  pbip.award_quantity AS awardQuantity,\n" +
					"  pbip.award_status AS awardStatus,\n" +
					"  pbip.award_date AS awardDate,\n" +
					"  pbip.bid_line_file_id AS bidLineFileId,\n" +
					"  pbip.notes AS notes,\n" +
					"  pbip.version_num AS versionNum,\n" +
					"  pbip.creation_date AS creationDate,\n" +
					"  pbip.created_by AS createdBy,\n" +
					"  pbip.last_updated_by AS lastUpdatedBy,\n" +
					"  pbip.last_update_date AS lastUpdateDate,\n" +
					"  pbip.last_update_login AS lastUpdateLogin,\n" +
					"  pbip.attribute_category AS attributeCategory,\n" +
					"  pbip.attribute1 AS attribute1,\n" +
					"  pbip.attribute2 AS attribute2,\n" +
					"  pbip.attribute3 AS attribute3,\n" +
					"  pbip.attribute4 AS attribute4,\n" +
					"  pbip.attribute5 AS attribute5,\n" +
					"  pbip.attribute6 AS attribute6,\n" +
					"  pbip.attribute7 AS attribute7,\n" +
					"  pbip.attribute8 AS attribute8,\n" +
					"  pbip.attribute9 AS attribute9,\n" +
					"  pbip.attribute10 AS attribute10,\n" +
					"  slv.meaning AS supplierStatusName,\n" +
					"  sbrf.file_Name AS bidLineFileName,\n" +
					"  sbrf.access_Path AS bidLineFileAccessPath,\n" +
					"  spbh.supplier_id AS supplierId,\n" +
					"  spbh.supplier_name AS supplierName,\n" +
					"  spsi.supplier_status AS supplierStatus,\n" +
					"  spag.group_id AS groupId,\n" +
					"  spag.group_name AS groupName,\n" +
					"  spail.ladder_quantity AS ladderQuantity,\n" +
					"  sbi.item_code AS itemCode,\n" +
					"  sbc.category_id AS categoryId,\n" +
					"  sbc.category_name AS categoryName,\n" +
					"  slv2.meaning AS taxRateName,\n" +
					"  slv3.meaning AS awardStatusName,\n" +
					"  spai.start_date AS startDate,\n" +
					"  spai.end_date AS endDate,\n" +
					"  slv4.meaning AS awardStatusBidHeader,\n" +
					"  sppt.payment_term_name AS paymentTermName,\n" +
					"  slv5.meaning AS unitOfMeasureName,\n" +
					"  spai.quantity AS quantity,\n" +
					"  spbh.payment_condition AS paymentCondition,\n" +
					"  spai.tax_rate_code AS taxRateCode,\n" +
					"  (SELECT\n" +
					"    COUNT(1)\n" +
					"  FROM\n" +
					"    srm_pon_bid_item_prices tt\n" +
					"  WHERE tt.bid_header_id = pbip.bid_header_id\n" +
					"    AND tt.auction_line_id = pbip.auction_line_id) AS numberByBidItemPrices\n" +
                   ",spbh.tran_manage_fees AS tranManageFees,\n"
                   + "spbh.tran_manage_percen AS tranManagePercen,\n"
                   + "spbh.measures_fees AS measuresFees,\n"
                   + "spbh.measures_percen AS measuresPercen,\n"
                   + "spbh.project_costs AS projectCosts,\n"
                   + "spbh.engineering_tax AS engineeringTax,\n"
                   + "spbh.total_project_cost AS totalProjectCost\n"+
					"FROM\n" +
					"  srm_pon_bid_item_prices pbip\n" +
					"  LEFT JOIN saaf_base_result_file sbrf\n" +
					"    ON sbrf.file_id = pbip.bid_line_file_id\n" +
					"  LEFT JOIN srm_pon_bid_headers spbh\n" +
					"    ON spbh.bid_header_id = pbip.bid_header_id\n" +
					"  LEFT JOIN srm_pos_supplier_info spsi\n" +
					"    ON spbh.supplier_id = spsi.supplier_id\n" +
					"  LEFT JOIN srm_pon_auction_items spai\n" +
					"    ON spai.auction_line_id = pbip.auction_line_id\n" +
					"  LEFT JOIN srm_pon_auction_groups spag\n" +
					"    ON spag.group_id = spai.group_id\n" +
					"  LEFT JOIN srm_pon_auction_item_ladders spail\n" +
					"    ON spail.item_ladder_id = pbip.item_ladder_id\n" +
					"  LEFT JOIN srm_base_items_b sbi\n" +
					"    ON sbi.item_id = pbip.item_id\n" +
					"  LEFT JOIN srm_base_categories sbc\n" +
					"    ON sbc.category_id = spai.category_id\n" +
					"  LEFT JOIN srm_pon_payment_terms sppt\n" +
					"    ON sppt.payment_term_code = spbh.payment_condition\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_type = 'POS_SUPPLIER_STATUS'\n" +
					"    AND slv.lookup_code = spsi.supplier_status\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_type = 'PON_TAX_LIST'\n" +
					"    AND slv2.lookup_code = pbip.tax_rate\n" +
					"  LEFT JOIN saaf_lookup_values slv3\n" +
					"    ON slv3.lookup_type = 'PON_AWARD_STATUS'\n" +
					"    AND slv3.lookup_code = pbip.award_status\n" +
					"  LEFT JOIN saaf_lookup_values slv4\n" +
					"    ON slv4.lookup_type = 'PON_AWARD_STATUS'\n" +
					"    AND slv4.lookup_code = spbh.award_status\n" +
					"  LEFT JOIN saaf_lookup_values slv5\n" +
					"    ON slv5.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"    AND slv5.lookup_code = pbip.unit_of_measure\n" +
					"WHERE spbh.bid_status = 'ACT'";

	//查询供应商报价行list（不分页）——已截止（没有阶梯数量）
	public static final String  QUERY_BIDITEMPRICESLISTV_SQL =
					"SELECT\n" +
					"  pbip.bid_line_id AS bidLineId,\n" +
					"  pbip.bid_header_id AS bidHeaderId,\n" +
					"  pbip.auction_header_id AS auctionHeaderId,\n" +
					"  pbip.auction_line_id AS auctionLineId,\n" +
					"  pbip.item_ladder_id AS itemLadderId,\n" +
					"  pbip.item_id AS itemId,\n" +
					"  pbip.item_description AS itemDescription,\n" +
					"  pbip.unit_of_measure AS unitOfMeasure,\n" +
					"  pbip.promised_quantity AS promisedQuantity,\n" +
					"  pbip.tax_price AS taxPrice,\n" +
					"  pbip.tax_rate AS taxRate,\n" +
					"  pbip.no_tax_price AS noTaxPrice,\n" +
					"  pbip.bargain AS bargain,\n" +
					"  pbip.rank AS rank,\n" +
					"  pbip.promised_start_date AS promisedStartDate,\n" +
					"  pbip.promised_end_date AS promisedEndDate,\n" +
					"  pbip.award_proportion AS awardProportion,\n" +
					"  pbip.award_quantity AS awardQuantity,\n" +
					"  pbip.award_status AS awardStatus,\n" +
					"  pbip.award_date AS awardDate,\n" +
					"  pbip.bid_line_file_id AS bidLineFileId,\n" +
					"  pbip.notes AS notes,\n" +
					"  pbip.version_num AS versionNum,\n" +
					"  pbip.creation_date AS creationDate,\n" +
					"  pbip.created_by AS createdBy,\n" +
					"  pbip.last_updated_by AS lastUpdatedBy,\n" +
					"  pbip.last_update_date AS lastUpdateDate,\n" +
					"  pbip.last_update_login AS lastUpdateLogin,\n" +
					"  pbip.attribute_category AS attributeCategory,\n" +
					"  pbip.attribute1 AS attribute1,\n" +
					"  pbip.attribute2 AS attribute2,\n" +
					"  pbip.attribute3 AS attribute3,\n" +
					"  pbip.attribute4 AS attribute4,\n" +
					"  pbip.attribute5 AS attribute5,\n" +
					"  pbip.attribute6 AS attribute6,\n" +
					"  pbip.attribute7 AS attribute7,\n" +
					"  pbip.attribute8 AS attribute8,\n" +
					"  pbip.attribute9 AS attribute9,\n" +
					"  pbip.attribute10 AS attribute10,\n" +
					"  slv.meaning AS supplierStatusName,\n" +
					"  sbrf.file_Name AS bidLineFileName,\n" +
					"  sbrf.access_Path AS bidLineFileAccessPath,\n" +
					"  spbh.supplier_id AS supplierId,\n" +
					"  spbh.supplier_name AS supplierName,\n" +
					"  spsi.supplier_status AS supplierStatus,\n" +
					"  spag.group_id AS groupId,\n" +
					"  spag.group_name AS groupName,\n" +
					"  sbi.item_code AS itemCode,\n" +
					"  sbc.category_id AS categoryId,\n" +
					"  sbc.category_name AS categoryName,\n" +
					"  slv2.meaning AS taxRateName,\n" +
					"  slv3.meaning AS awardStatusName,\n" +
					"  spai.start_date AS startDate,\n" +
					"  spai.end_date AS endDate,\n" +
					"  slv4.meaning AS awardStatusBidHeader,\n" +
					"  sppt.payment_term_name AS paymentTermName,\n" +
					"  slv5.meaning AS unitOfMeasureName,\n" +
					"  spai.quantity AS quantity,\n" +
					"  spbh.payment_condition AS paymentCondition,\n" +
					"  spai.tax_rate_code AS taxRateCode,\n" +
					"  (SELECT\n" +
					"    COUNT(*)\n" +
					"  FROM\n" +
					"    srm_pon_bid_item_prices tt\n" +
					"  WHERE tt.bid_header_id = pbip.bid_header_id\n" +
					"    AND tt.auction_line_id = pbip.auction_line_id) AS numberByBidItemPrices\n" +
                    ",spbh.tran_manage_fees AS tranManageFees,\n"
                     + "spbh.tran_manage_percen AS tranManagePercen,\n"
                     + "spbh.measures_fees AS measuresFees,\n"
                     + "spbh.measures_percen AS measuresPercen,\n"
                     + "spbh.project_costs AS projectCosts,\n"
                     + "spbh.engineering_tax AS engineeringTax,\n"
                     + "spbh.total_project_cost AS totalProjectCost\n"+
					"FROM\n" +
					"  srm_pon_bid_item_prices pbip\n" +
					"  LEFT JOIN saaf_base_result_file sbrf\n" +
					"    ON sbrf.file_id = pbip.bid_line_file_id\n" +
					"  LEFT JOIN srm_pon_bid_headers spbh\n" +
					"    ON spbh.bid_header_id = pbip.bid_header_id\n" +
					"  LEFT JOIN srm_pos_supplier_info spsi\n" +
					"    ON spbh.supplier_id = spsi.supplier_id\n" +
					"  LEFT JOIN srm_pon_auction_items spai\n" +
					"    ON spai.auction_line_id = pbip.auction_line_id\n" +
					"  LEFT JOIN srm_pon_auction_groups spag\n" +
					"    ON spag.group_id = spai.group_id\n" +
					"  LEFT JOIN srm_base_items_b sbi\n" +
					"    ON sbi.item_id = pbip.item_id\n" +
					"  LEFT JOIN srm_base_categories sbc\n" +
					"    ON sbc.category_id = spai.category_id\n" +
					"  LEFT JOIN srm_pon_payment_terms sppt\n" +
					"    ON sppt.payment_term_code = spbh.payment_condition\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_type = 'POS_SUPPLIER_STATUS'\n" +
					"    AND slv.lookup_code = spsi.supplier_status\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_type = 'PON_TAX_LIST'\n" +
					"    AND slv2.lookup_code = pbip.tax_rate\n" +
					"  LEFT JOIN saaf_lookup_values slv3\n" +
					"    ON slv3.lookup_type = 'PON_AWARD_STATUS'\n" +
					"    AND slv3.lookup_code = pbip.award_status\n" +
					"  LEFT JOIN saaf_lookup_values slv4\n" +
					"    ON slv4.lookup_type = 'PON_AWARD_STATUS'\n" +
					"    AND slv4.lookup_code = spbh.award_status\n" +
					"  LEFT JOIN saaf_lookup_values slv5\n" +
					"    ON slv5.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"    AND slv5.lookup_code = pbip.unit_of_measure\n" +
					"WHERE spbh.bid_status = 'ACT'";

	//会签的模态框的中标记录——已截止（查询）
	public static final String QUERY_BIDITEMPRICELIST_SQL = "SELECT\n"
			+ "t.bid_line_id AS bidLineId,\n"
			+ "t.bid_header_id AS bidHeaderId,\n"
			+ "t.auction_header_id AS auctionHeaderId,\n"
			+ "t.auction_line_id AS auctionLineId,\n"
			+ "t.item_ladder_id AS itemLadderId,\n"
			+ "t.item_id AS itemId,\n"
			+ "t.item_description AS itemDescription,\n"
			+ "t.unit_of_measure AS unitOfMeasure,\n"
			+ "t.promised_quantity AS promisedQuantity,\n"
			+ "t.tax_price AS taxPrice,\n"
			+ "t.tax_rate AS taxRate,\n"
			+ "t.no_tax_price AS noTaxPrice,\n"
			+ "t.rank AS rank,\n"
			+ "t.promised_start_date AS promisedStartDate,\t\n"
			+ "t.promised_end_date AS promisedEndDate,\n"
			+ "t.award_proportion AS awardProportion,\n"
			+ "t.award_quantity AS awardQuantity,\n"
			+ "t.award_status AS awardStatus,\n"
			+ "t.award_date AS awardDate,\n"
			+ "t.bid_line_file_id AS bidLineFileId,\n"
			+ "t.notes AS notes,\n"
			+ "t.version_num AS versionNum,\n"
			+ "t.creation_date AS creationDate,\n"
			+ "t.created_by AS createdBy,\n"
			+ "t.last_updated_by AS lastUpdatedBy,\n"
			+ "t.last_update_date AS lastUpdateDate,\n"
			+ "t.last_update_login AS lastUpdateLogin,\n"
			+ "t.attribute_category AS attributeCategory,\n"
			+ "t.attribute1 AS attribute1,\n"
			+ "t.attribute2 AS attribute2,\n"
			+ "t.attribute3 AS attribute3,\n"
			+ "t.attribute4 AS attribute4,\n"
			+ "t.attribute5 AS attribute5,\n"
			+ "t.attribute6 AS attribute6,\t\n"
			+ "t.attribute7 AS attribute7,\n"
			+ "t.attribute8 AS attribute8,\n"
			+ "t.attribute9 AS attribute9,\n"
			+ "t.attribute10 AS attribute10,\n"
			+ "spbh.supplier_id AS supplierId,\n"
			+ "spbh.supplier_name AS supplierName,\n"
			+ "spail.ladder_quantity AS ladderQuantity,\n"
			+ "spai.quantity AS quantity,\n"
			+ "sbi.item_code AS itemCode,\n"
			+ "sbc.category_id AS categoryId,\n"
			+ "sbc.category_name AS categoryName,\n"
			+ "slv2.meaning AS taxRateName,\n"
			+ "slv3.meaning AS awardStatusName,\n"
			+ "slv4.meaning AS awardStatusBidHeader,\n"
			+ "sppt.payment_term_name AS paymentTermName,\n"
			+ "slv5.meaning AS unitOfMeasureName\n"
			+ "FROM srm_pon_bid_item_prices t\n"
			+ "LEFT JOIN srm_pon_bid_headers spbh ON spbh.bid_header_id = t.bid_header_id\n"
			+ "LEFT JOIN srm_pon_auction_item_ladders spail ON spail.item_ladder_id = t.item_ladder_id\n"
			+ "LEFT JOIN srm_base_items sbi ON sbi.item_id = t.item_id\n"
			+ "LEFT JOIN srm_pon_auction_items spai ON spai.auction_line_id = t.auction_line_id\n"
			+ "LEFT JOIN srm_base_categories sbc ON sbc.category_id = spai.category_id\n"
			+ "LEFT JOIN srm_pon_payment_terms sppt ON sppt.payment_term_code = spbh.payment_condition\n"
			+ "LEFT JOIN saaf_lookup_values slv2 ON slv2.lookup_type='PON_TAX_LIST' AND slv2.lookup_code=t.tax_rate\n"
			+ "LEFT JOIN saaf_lookup_values slv3 ON slv3.lookup_type='PON_AWARD_STATUS' AND slv3.lookup_code=t.award_status\n"
			+ "LEFT JOIN saaf_lookup_values slv4 ON slv4.lookup_type='PON_AWARD_STATUS' AND slv4.lookup_code=spbh.award_status\n"
			+ "LEFT JOIN saaf_lookup_values slv5 ON slv5.lookup_type='BASE_ITEMS_UNIT' AND slv5.lookup_code=t.unit_of_measure\n"
			+ "WHERE 1=1 ";

	public static final String QUERY_SUPPLIER_BID_PRICE_SQL ="select l.item_id,\n" +
            "       l.auction_line_id,\n" +
			"       l.tax_price,\n" +
            "       l.no_tax_price,\n" +
			"       l.bargain,\n" +
            "       l.bargain_tax,\n" +
			"       slv1.meaning as tax_rate,\n" +
			"       l.rank,\n" +
			"       l.award_proportion,\n" +
            "       l.award_quantity\n" +
			"  from srm_pon_bid_item_prices l\n" +
			"  left join saaf_lookup_values slv1\n" +
			"    on slv1.lookup_type = 'PON_TAX_LIST'\n" +
			"   and slv1.lookup_code = l.tax_rate, srm_pon_bid_headers h\n" +
			" where 1=1\n" +
			"   and h.bid_header_id = l.bid_header_id\n" +
			"	and l.auction_header_id = :auctionHeaderId\n" +
            "	and l.auction_line_id = :auctionLineId\n" +
			"   and h.supplier_id = :supplierId\n" +
			"   and h.bid_status = 'ACT'\n";

	public static final String QUERY_BID_ITEMS_SQL = "select t.auction_header_id,\n" +
			"       t.auction_line_id,\n" +
			"       t.item_id,\n" +
			"       t.item_description,\n" +
			"       t.unit_of_measure,\n" +
			"       t.category_id,\n" +
			"       t.quantity,\n" +
			"       t.web_reference_price,\n" +
			"       t.discount_price,\n" +
			"       t.tax_rate_code,\n" +
			"       t.start_date,\n" +
			"       t.end_date,\n" +
			"       t.award_status,\n" +
			"       t.specification,\n" +
			"       sbib.item_code,\n" +
			"       sbc.full_category_code categoryCode,\n" +
			"       sbc.full_category_name categoryName,\n" +
			"      (CASE WHEN t.cost IS NOT NULL THEN t.cost ELSE sbib.cost END) cost,\n" +
			"       (CASE WHEN slv1.meaning IS NOT NULL THEN slv1.meaning ELSE t.unit_of_measure END) unitOfMeasureName,\n" +
			"       decode(spah.item_type\n" +
			"             ,'LOGISTICS'\n" +
			"             ,ppl.tax_price\n" +
			"             ,ppl.non_tax_price) low_price,\n" +
			"      decode(spah.item_type\n" +
			"             ,'LOGISTICS'\n" +
			"             ,pp.tax_price\n" +
			"             ,pp.non_tax_price) latest_price,\n" +
			"  t.specification AS specification,\n" +
			"  t.market_inquiry AS marketInquiry\n" +
			"  from srm_pon_auction_items t\n" +
			"  left join srm_base_items_b sbib\n" +
			"   on t.item_id = sbib.item_id\n" +
			"  left join srm_base_categories sbc\n" +
			"    on t.category_id = sbc.category_id\n" +
			"  left join saaf_lookup_values slv1\n" +
			"    on slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
			"   and slv1.lookup_code = t.unit_of_measure\n" +
			"   left join srm_pon_auction_headers spah\n" +
			"    on spah.auction_header_id = t.auction_header_id\n" +
			"  left join (select min(sppl.tax_price) tax_price\n" +
			"                   ,min(sppl.non_tax_price) non_tax_price\n" +
			"                   ,sppl.item_id\n" +
			"                   ,sppl.org_id\n" +
			"               from srm_pon_price_library sppl\n" +
			"              group by sppl.item_id\n" +
			"                      ,sppl.org_id) ppl\n" +
			"    on ppl.item_id = t.item_id\n" +
			"   and ppl.org_id = spah.org_id\n" +
			"  left join (select min(sppl.tax_price) tax_price\n" +
			"                   ,min(sppl.non_tax_price) non_tax_price\n" +
			"                   ,sppl.item_id\n" +
			"                   ,sppl.org_id\n" +
			"               from srm_pon_price_library sppl\n" +
			"              where sppl.price_library_version in\n" +
			"                    (select max(l.price_library_version) price_library_version\n" +
			"                       from srm_pon_price_library l\n" +
			"                      where l.item_id = sppl.item_id\n" +
			"                        and l.org_id = sppl.org_id)\n" +
			"              group by sppl.item_id\n" +
			"                      ,sppl.org_id) pp\n" +
			"    on pp.item_id = t.item_id\n" +
			"   and pp.org_id = spah.org_id\n" +
			" where 1 = 1 ";

	public static final String QUERY_BID_ITEMS_PRICES_SQL = "select t.bid_line_id,\n" +
			"       t.bid_header_id,\n" +
			"       t.auction_header_id,\n" +
			"       t.auction_line_id,\n" +
			"       t.item_ladder_id,\n" +
			"       t.item_id,\n" +
			"       t.award_proportion\n" +
			"  from srm_pon_bid_item_prices t\n" +
			" where 1=1 ";

    public static final String QUERY_BID_ITEMS_SQL_ALL="SELECT Sbib.Item_Code\n" +
            "      ,t.Item_Description\n" +
            "      ,Sbc.Category_Name\n" +
            "      ,Slv12.Meaning unit_Of_Measure_Name\n" +
            "      ,t.Quantity\n" +
            "      ,Sbib.Cost\n" +
            "      ,Decode(Spah.Item_Type\n" +
            "             ,'LOGISTICS'\n" +
            "             ,Ppl.Tax_Price\n" +
            "             ,Ppl.Non_Tax_Price) Low_Price\n" +
            "      ,Decode(Spah.Item_Type\n" +
            "             ,'LOGISTICS'\n" +
            "             ,Pp.Tax_Price\n" +
            "             ,Pp.Non_Tax_Price) Latest_Price\n" +
            "      ,t.Web_Reference_Price\n" +
            "      ,t.Discount_Price\n" +
            "       \n" +
            "      ,Spsi.Supplier_Name\n" +
            "      ,l.Item_Id\n" +
            "      ,l.Auction_Line_Id\n" +
            "      ,l.Tax_Price\n" +
            "      ,l.No_Tax_Price\n" +
            "      ,l.Bargain\n" +
            "      ,l.Bargain_Tax\n" +
            "      ,Slv1.Meaning       AS Tax_Rate\n" +
            "      ,l.Rank\n" +
            "      ,l.Award_Proportion\n" +
            "      ,l.bid_line_id\n" +
            "      ,l.bid_header_id\n" +
            "      ,l.specification\n" +
            "  FROM Srm_Pon_Bid_Item_Prices l\n" +
            "  LEFT JOIN Saaf_Lookup_Values Slv1\n" +
            "    ON Slv1.Lookup_Type = 'PON_TAX_LIST'\n" +
            "   AND Slv1.Lookup_Code = l.Tax_Rate, Srm_Pon_Bid_Headers h\n" +
            "  LEFT JOIN Srm_Pos_Supplier_Info Spsi\n" +
            "    ON Spsi.Supplier_Id = h.Supplier_Id, Srm_Pon_Auction_Items t\n" +
            "  LEFT JOIN Srm_Base_Items_b Sbib\n" +
            "  LEFT JOIN Srm_Base_Categories Sbc\n" +
            "    ON Sbib.Category_Id = Sbc.Category_Id ON t.Item_Id = Sbib.Item_Id\n" +
            "  LEFT JOIN Saaf_Lookup_Values Slv12\n" +
            "    ON Slv12.Lookup_Type = 'BASE_ITEMS_UNIT'\n" +
            "   AND Slv12.Lookup_Code = Sbib.Uom_Code\n" +
            "  LEFT JOIN Srm_Pon_Auction_Headers Spah\n" +
            "    ON Spah.Auction_Header_Id = t.Auction_Header_Id\n" +
            "  LEFT JOIN (SELECT MIN(Sppl.Tax_Price) Tax_Price\n" +
            "                   ,MIN(Sppl.Non_Tax_Price) Non_Tax_Price\n" +
            "                   ,Sppl.Item_Id\n" +
            "                   ,Sppl.Org_Id\n" +
            "               FROM Srm_Pon_Price_Library Sppl\n" +
            "              GROUP BY Sppl.Item_Id\n" +
            "                      ,Sppl.Org_Id) Ppl\n" +
            "    ON Ppl.Item_Id = t.Item_Id\n" +
            "   AND Ppl.Org_Id = Spah.Org_Id\n" +
            "  LEFT JOIN (SELECT MIN(Sppl.Tax_Price) Tax_Price\n" +
            "                   ,MIN(Sppl.Non_Tax_Price) Non_Tax_Price\n" +
            "                   ,Sppl.Item_Id\n" +
            "                   ,Sppl.Org_Id\n" +
            "               FROM Srm_Pon_Price_Library Sppl\n" +
            "              WHERE Sppl.Price_Library_Version IN\n" +
            "                    (SELECT MAX(l.Price_Library_Version) Price_Library_Version\n" +
            "                       FROM Srm_Pon_Price_Library l\n" +
            "                      WHERE l.Item_Id = Sppl.Item_Id\n" +
            "                        AND l.Org_Id = Sppl.Org_Id)\n" +
            "              GROUP BY Sppl.Item_Id\n" +
            "                      ,Sppl.Org_Id) Pp\n" +
            "    ON Pp.Item_Id = t.Item_Id\n" +
            "   AND Pp.Org_Id = Spah.Org_Id\n" +
            " WHERE h.Auction_Header_Id = t.Auction_Header_Id\n" +
            "   AND h.Bid_Header_Id = l.Bid_Header_Id\n" +
            "   AND t.Auction_Line_Id = l.Auction_Line_Id\n" +
            "   AND h.Bid_Status = 'ACT'";

    public static final String QUERY_BID_SUPPLIER_SQL="SELECT SUM(t.Tax_Price) Tax_Price\n" +
            "      ,SUM(t.No_Tax_Price) No_Tax_Price\n" +
            "      ,SUM(t.Bargain_Tax) Bargain_Tax\n" +
            "      ,SUM(t.Bargain) Bargain\n" +
            "      ,t.Supplier_Id\n" +
            "      ,t.Supplier_Name\n" +
            "      ,t.Supplier_Ebs_Number\n" +
            "      ,t.Tax_Rate_Name\n" +
            "      ,t.Award_Status\n" +
            "      ,t.Auction_Header_Id\n" +
            "  FROM (SELECT (SUM(Itp.Tax_Price) * Spai.Quantity) Tax_Price\n" +
            "              ,SUM(Itp.No_Tax_Price) * Spai.Quantity No_Tax_Price\n" +
            "              ,SUM(Itp.Bargain_Tax) * Spai.Quantity Bargain_Tax\n" +
            "              ,SUM(Itp.Bargain) * Spai.Quantity Bargain\n" +
            "              ,Spbh.Supplier_Id\n" +
            "              ,Spbh.Supplier_Name\n" +
            "              ,Spsi.Supplier_Ebs_Number\n" +
            "              ,Slv.Meaning Tax_Rate_Name\n" +
            "              ,Decode(Spbh.Award_Status\n" +
            "                     ,'4'\n" +
            "                     ,'是'\n" +
            "                     ,'否') Award_Status\n" +
            "              ,Itp.Auction_Header_Id\n" +
            "          FROM Srm_Pon_Bid_Item_Prices Itp\n" +
            "          LEFT JOIN Srm_Pon_Bid_Headers Spbh\n" +
            "            ON Itp.Bid_Header_Id = Spbh.Bid_Header_Id\n" +
            "          LEFT JOIN Srm_Pon_Auction_Items Spai\n" +
            "            ON Spai.Auction_Line_Id = Itp.Auction_Line_Id\n" +
            "          LEFT JOIN Srm_Pos_Supplier_Info Spsi\n" +
            "            ON Spsi.Supplier_Id = Spbh.Supplier_Id\n" +
            "          LEFT JOIN Saaf_Lookup_Values Slv\n" +
            "            ON Slv.Lookup_Type = 'PON_TAX_LIST'\n" +
            "           AND Slv.Lookup_Code = Spbh.Tax_Rate_Code\n" +
            "         WHERE Spbh.Bid_Status = 'ACT'\n" +
            "         GROUP BY Spbh.Supplier_Id\n" +
            "                 ,Itp.Auction_Line_Id\n" +
            "                 ,Spai.Quantity\n" +
            "                 ,Spbh.Supplier_Id\n" +
            "                 ,Spbh.Supplier_Name\n" +
            "                 ,Spsi.Supplier_Ebs_Number\n" +
            "                 ,Spbh.Tax_Rate_Code\n" +
            "                 ,Itp.Auction_Header_Id\n" +
            "                 ,Slv.Meaning\n" +
            "                 ,Spbh.Award_Status) t\n" +
            " WHERE 1 = 1\n" +
            "   AND t.Auction_Header_Id = :auctionheaderid\n" +
            " GROUP BY t.Supplier_Id\n" +
            "         ,t.Supplier_Id\n" +
            "         ,t.Supplier_Name\n" +
            "         ,t.Supplier_Ebs_Number\n" +
            "         ,t.Tax_Rate_Name\n" +
            "         ,t.Award_Status\n" +
            "         ,t.Auction_Header_Id\n";

	private Integer bidLineId; // 供应商报价行ID
	private Integer bidHeaderId; // 供应商报价头ID
	private Integer auctionHeaderId; // 洽谈头ID
	private Integer auctionLineId; // 洽谈行ID
	private Integer itemLadderId; // 洽谈行阶梯ID
	private Integer itemId; // 物料ID
	private String itemDescription; // 物料说明
	private String unitOfMeasure; // 计量单位
	private BigDecimal promisedQuantity; // 承诺数量
	private BigDecimal taxPrice; // 含税单价
	private String taxRate; // 税率
	private BigDecimal noTaxPrice; // 不含税单价
	private Integer rank; // 排名
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date promisedStartDate; // 承诺开始日期
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date promisedEndDate; // 承诺结束日期
	private BigDecimal awardProportion;// 中标份额
	private BigDecimal awardQuantity; // 中标数量
	private String awardStatus; // 中标状态
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date awardDate; // 中标时间
	private Integer bidLineFileId; // 附件
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
	private Integer operatorUserId;
	//别名
	private String bidLineFileName;//附件文件名称
	private String bidLineFileAccessPath;//附件下载路径
	private Integer supplierId;//供应商Id
	private String supplierName;//供应商名称
	private String supplierStatus;//供应商的状态（pos模块）
	private String supplierStatusName;//供应商的状态别名
	private Integer groupId; //组别ID
	private String groupName; //组别名称
	private BigDecimal ladderQuantity; //阶梯数量
	private String itemCode; // 物料编码
	private String categoryName;// 采购分类
	private Integer categoryId;// 采购分类Id
	private String taxRateName;// 税率（快码）
	private String awardStatusName;// 中标状态（快码）
	@JSONField(format = "yyyy-MM-dd")
	private Date startDate; // 开始日期——合同有效期从
	@JSONField(format = "yyyy-MM-dd")
	private Date endDate; // 结束日期——合同有效期
	private String paymentTermName; //付款条件名称
	private String paymentCondition; //付款条件
	private String awardStatusBidHeader; // 供应商报价头表对的中标状态
	private String unitOfMeasureName;// 计量单位的快码别名
	private BigDecimal quantity; // 数量——标的物
	private String taxRateCode; //税率——标的物
	private Integer numberByBidItemPrices;//记录同一个供应商报价头表下的同一个标的物Id的供应商报价行的list数量
	private BigDecimal bargain; //不含税议价
    private BigDecimal bargainTax; //含税议价
	private BigDecimal materialsPrice;//材料单价
	private BigDecimal artificialPrice;//人工单价
	private BigDecimal webReferencePrice;//网站参考价
	private BigDecimal discountPrice;//折后价
    private BigDecimal cost;//成本预算
    private BigDecimal lowPrice;
    private BigDecimal latestPrice;
    private String supplierEbsNumber;
    private String specification;
    private BigDecimal marketInquiry;

    public BigDecimal getMarketInquiry() {
        return marketInquiry;
    }

    public void setMarketInquiry(BigDecimal marketInquiry) {
        this.marketInquiry = marketInquiry;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getSupplierEbsNumber() {
        return supplierEbsNumber;
    }

    public void setSupplierEbsNumber(String supplierEbsNumber) {
        this.supplierEbsNumber = supplierEbsNumber;
    }

    public BigDecimal getBargainTax() {
        return bargainTax;
    }

    public void setBargainTax(BigDecimal bargainTax) {
        this.bargainTax = bargainTax;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(BigDecimal latestPrice) {
        this.latestPrice = latestPrice;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getNumberByBidItemPrices() {
		return numberByBidItemPrices;
	}

	public void setNumberByBidItemPrices(Integer numberByBidItemPrices) {
		this.numberByBidItemPrices = numberByBidItemPrices;
	}

	public String getTaxRateCode() {
		return taxRateCode;
	}

	public void setTaxRateCode(String taxRateCode) {
		this.taxRateCode = taxRateCode;
	}

	public String getPaymentCondition() {
		return paymentCondition;
	}

	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getUnitOfMeasureName() {
		return unitOfMeasureName;
	}

	public void setUnitOfMeasureName(String unitOfMeasureName) {
		this.unitOfMeasureName = unitOfMeasureName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getAwardStatusBidHeader() {
		return awardStatusBidHeader;
	}

	public void setAwardStatusBidHeader(String awardStatusBidHeader) {
		this.awardStatusBidHeader = awardStatusBidHeader;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
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

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public BigDecimal getLadderQuantity() {
		return ladderQuantity;
	}

	public void setLadderQuantity(BigDecimal ladderQuantity) {
		this.ladderQuantity = ladderQuantity;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPaymentTermName() {
		return paymentTermName;
	}

	public void setPaymentTermName(String paymentTermName) {
		this.paymentTermName = paymentTermName;
	}

	public String getBidLineFileName() {
		return bidLineFileName;
	}

	public void setBidLineFileName(String bidLineFileName) {
		this.bidLineFileName = bidLineFileName;
	}

	public String getBidLineFileAccessPath() {
		return bidLineFileAccessPath;
	}

	public void setBidLineFileAccessPath(String bidLineFileAccessPath) {
		this.bidLineFileAccessPath = bidLineFileAccessPath;
	}

	public void setBidLineId(Integer bidLineId) {
		this.bidLineId = bidLineId;
	}

	public Integer getBidLineId() {
		return bidLineId;
	}

	public void setBidHeaderId(Integer bidHeaderId) {
		this.bidHeaderId = bidHeaderId;
	}

	public Integer getBidHeaderId() {
		return bidHeaderId;
	}

	public void setAuctionHeaderId(Integer auctionHeaderId) {
		this.auctionHeaderId = auctionHeaderId;
	}

	public Integer getAuctionHeaderId() {
		return auctionHeaderId;
	}

	public void setAuctionLineId(Integer auctionLineId) {
		this.auctionLineId = auctionLineId;
	}

	public Integer getAuctionLineId() {
		return auctionLineId;
	}

	public void setItemLadderId(Integer itemLadderId) {
		this.itemLadderId = itemLadderId;
	}

	public Integer getItemLadderId() {
		return itemLadderId;
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

	public void setPromisedQuantity(BigDecimal promisedQuantity) {
		this.promisedQuantity = promisedQuantity;
	}

	public BigDecimal getPromisedQuantity() {
		return promisedQuantity;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setNoTaxPrice(BigDecimal noTaxPrice) {
		this.noTaxPrice = noTaxPrice;
	}

	public BigDecimal getNoTaxPrice() {
		return noTaxPrice;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getRank() {
		return rank;
	}

	public void setPromisedStartDate(Date promisedStartDate) {
		this.promisedStartDate = promisedStartDate;
	}

	public Date getPromisedStartDate() {
		return promisedStartDate;
	}

	public void setPromisedEndDate(Date promisedEndDate) {
		this.promisedEndDate = promisedEndDate;
	}

	public Date getPromisedEndDate() {
		return promisedEndDate;
	}

	public void setAwardQuantity(BigDecimal awardQuantity) {
		this.awardQuantity = awardQuantity;
	}

	public BigDecimal getAwardQuantity() {
		return awardQuantity;
	}

	public void setAwardStatus(String awardStatus) {
		this.awardStatus = awardStatus;
	}

	public String getAwardStatus() {
		return awardStatus;
	}

	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
	}

	public Date getAwardDate() {
		return awardDate;
	}

	public void setBidLineFileId(Integer bidLineFileId) {
		this.bidLineFileId = bidLineFileId;
	}

	public Integer getBidLineFileId() {
		return bidLineFileId;
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

	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public BigDecimal getAwardProportion() {
		return awardProportion;
	}

	public void setAwardProportion(BigDecimal awardProportion) {
		this.awardProportion = awardProportion;
	}

	public BigDecimal getBargain() {
		return bargain;
	}

	public void setBargain(BigDecimal bargain) {
		this.bargain = bargain;
	}

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
