package saaf.common.fmw.pon.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SrmPonBidEntity_HI_RO implements Serializable {

    //查询未创建报价的标的物(阶梯)
    public static final String QUERY_AUCTION_ITEMS_LADDER_LIST_SQL =
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
					"  pai.unit_of_measure AS unitOfMeasureNum,\n" +
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
                    "  pai.specification AS specification,\n" +
					"  sbc.category_name AS categoryName,\n" +
					"  pag.group_name AS groupName,\n" +
					"  sbi.item_code AS itemNumber,\n" +
                    "  sbi.item_name itemName,\n" +
					"  sbrl.file_Name AS fileName,\n" +
					"  (CASE WHEN slv.meaning IS NOT NULL THEN slv.meaning ELSE pai.unit_of_measure END) unitOfMeasure,\n" +
					"  slv2.meaning AS taxRateName,\n" +
					"  sbrl.access_Path AS accessPath,\n" +
					"  pail.item_ladder_id AS itemLadderId,\n" +
					"  pail.ladder_quantity ladderQuantity,\n" +
					"  (SELECT\n" +
					"    MIN(pbip1.no_tax_price)\n" +
					"  FROM\n" +
					"    srm_pon_bid_item_prices pbip1\n" +
					"  WHERE pbip1.auction_header_id = pai.auction_header_id\n" +
					"    AND pbip1.auction_line_id = pai.auction_line_id\n" +
					"    AND pbip1.item_ladder_id = pail.item_ladder_id) roundMinPrice\n" +
					"FROM\n" +
					"  srm_pon_auction_items pai\n" +
					"  LEFT JOIN srm_base_categories sbc\n" +
					"    ON sbc.category_id = pai.category_id\n" +
					"  LEFT JOIN srm_pon_auction_groups pag\n" +
					"    ON pag.group_id = pai.group_id\n" +
					"  LEFT JOIN srm_base_items_b sbi\n" +
					"    ON sbi.item_id = pai.item_id\n" +
					"  LEFT JOIN saaf_base_result_file sbrl\n" +
					"    ON sbrl.file_id = pai.file_id\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_code = pai.unit_of_measure\n" +
					"    AND slv.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_code = pai.tax_rate_code\n" +
					"    AND slv2.lookup_type = 'PON_TAX_LIST'\n" +
					"  LEFT JOIN srm_pon_auction_item_ladders pail\n" +
					"    ON pail.auction_header_id = pai.auction_header_id\n" +
					"    AND pail.auction_line_id = pai.auction_line_id\n" +
					"WHERE 1 = 1";

	//查询报价行（阶梯）
    public static final String QUERY_AUCTION_BID_LINE_LADDER_SQL =
					"SELECT\n" +
					"  pbip.bid_line_id bidLineId,\n" +
					"  pbip.bid_header_id bidHeaderId,\n" +
					"  pbip.auction_header_id auctionHeaderId,\n" +
					"  pbip.auction_line_id auctionLineId,\n" +
					"  pail.item_ladder_id itemLadderId,\n" +
					"  pbip.item_id itemId,\n" +
					"  pbip.item_description itemDescription,\n" +
					"  pbip.promised_quantity promisedQuantity,\n" +
					"  pbip.MATERIALS_PRICE materialsPrice,\n" +
					"  pbip.ARTIFICIAL_PRICE artificialPrice,\n" +
					"  pbip.no_tax_price noTaxPrice,\n" +
					"  pbip.bargain bargain,\n" +
					"  pbip.rank rank,\n" +
                    "  pbip.rank rank,\n" +
                    "  pbip.rank rank,\n" +
                    "  pbip.specification specification,\n" +
					"  pbip.promised_start_date promisedStartDate,\n" +
					"  pbip.promised_end_date promisedEndDate,\n" +
					"  pbip.award_quantity awardQuantity,\n" +
					"  pbip.award_status awardStatus,\n" +
					"  pbip.award_date awardDate,\n" +
					"  pbip.bid_line_file_id bidLineFileId,\n" +
					"  rf.access_Path filePath,\n" +
					"  rf.file_Name fileName,\n" +
					"  sbc.category_name categoryName,\n" +
					"  pai.auction_line_id auctionLineId,\n" +
					"  pai.auction_header_id auctionHeaderId,\n" +
					"  pai.line_number lineNumber,\n" +
					"  pai.disp_line_number dispLineNumber,\n" +
					"  pai.line_type lineType,\n" +
					"  pai.parent_line_id parentLineId,\n" +
					"  pai.group_id groupId,\n" +
					"  pai.item_id itemId,\n" +
					"  sbi.item_code itemNumber,\n" +
                    "  sbi.item_name itemName,\n" +
					"  pai.item_description itemDescription,\n" +
					"  pai.unit_of_measure unitOfMeasureNum,\n" +
					"  (CASE WHEN slv1.meaning IS NOT NULL THEN slv1.meaning ELSE pai.unit_of_measure END) unitOfMeasure,\n" +
					"  pai.category_id categoryId,\n" +
					"  pai.quantity quantity,\n" +
					"  pai.tax_rate_code taxRateCode,\n" +
					"  slv.meaning taxRateName,\n" +
					"  pai.start_date startDate,\n" +
					"  pai.end_date endDate,\n" +
					"  pai.award_status awardStatus,\n" +
					"  pai.awarded_quantity awardedQuantity,\n" +
					"  pai.notes notes,\n" +
					"  pag.group_name groupName,\n" +
					"  pail.ladder_quantity ladderQuantity,\n" +
					"  (SELECT\n" +
					"    MIN(pbip1.no_tax_price)\n" +
					"  FROM\n" +
					"    srm_pon_bid_item_prices pbip1\n" +
					"  WHERE pbip1.auction_header_id = pbip.auction_header_id\n" +
					"    AND pbip1.auction_line_id = pbip.auction_line_id\n" +
					"    AND pbip1.item_ladder_id = pbip.item_ladder_id) roundMinPrice,\n" +
                    "  h.tax_rate_code headerTaxRateCode,\n" +
                    "  slv.meaning headerTaxRateName,\n" +
                    " (pbip.artificial_price*Pai.Quantity) totalArtiPrice\n" +
                    ",(pbip.materials_price+pbip.artificial_price) materArtiPrice\n" +
                    ",(pbip.materials_price+pbip.artificial_price)*Pai.Quantity maTotal \n" +
					"FROM\n" +
					"  srm_pon_bid_item_prices pbip\n" +
                    "  LEFT JOIN srm_pon_bid_headers h\n" +
                    "    ON h.bid_header_id = pbip.bid_header_id\n" +
                    "  LEFT JOIN saaf_lookup_values slv2\n" +
                    "    ON slv2.lookup_code = h.tax_rate_code\n" +
                    "    AND slv2.lookup_type = 'PON_TAX_LIST'\n" +
					"  LEFT JOIN saaf_base_result_file rf\n" +
					"    ON rf.file_id = pbip.bid_line_file_id\n" +
					"  LEFT JOIN srm_pon_auction_item_ladders pail\n" +
					"    ON pail.auction_line_id = pbip.auction_line_id\n" +
					"    AND pail.item_ladder_id = pbip.item_ladder_id,\n" +
					"  srm_pon_auction_items pai\n" +
					"  LEFT JOIN saaf_lookup_values slv1\n" +
					"    ON slv1.lookup_code = pai.unit_of_measure\n" +
					"    AND slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"  LEFT JOIN srm_pon_auction_groups pag\n" +
					"    ON pag.group_id = pai.group_id\n" +
					"  LEFT JOIN srm_base_categories sbc\n" +
					"    ON sbc.category_id = pai.category_id\n" +
					"  LEFT JOIN srm_base_items_b sbi\n" +
					"    ON sbi.item_id = pai.item_id\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_code = pai.tax_rate_code\n" +
					"    AND slv.lookup_type = 'PON_TAX_LIST'\n" +
					"WHERE pbip.auction_line_id = pai.auction_line_id\n";

	//查询招标的物料(阶梯)
    public static final String QUERY_AUCTION_ITEM_LADDER_SQL =
					"SELECT spai.auction_line_id   auctionLineId\n" +
					"      ,spai.auction_header_id auctionHeaderId\n" +
					"      ,spai.line_number       lineNumber\n" +
					"      ,spai.disp_line_number  dispLineNumber\n" +
					"      ,spai.line_type         lineType\n" +
					"      ,spai.parent_line_id    parentLineId\n" +
					"      ,spai.group_id          groupId\n" +
					"      ,spai.item_id           itemId\n" +
					"      ,sbi.item_code          itemNumber\n" +
					"      ,spai.item_description  itemDescription\n" +
					"      ,spai.unit_of_measure   unitOfMeasureNum\n" +
					"      ,slv1.meaning           unitOfMeasure\n" +
					"      ,spai.category_id       categoryId\n" +
					"      ,spai.quantity          quantity\n" +
					"      ,spai.tax_rate_code     taxRateCode\n" +
					"      ,slv.meaning            taxRateName\n" +
					"      ,spai.start_date        startDate\n" +
					"      ,spai.end_date          endDate\n" +
					"      ,spai.award_status      awardStatus\n" +
					"      ,spai.awarded_quantity  awardedQuantity\n" +
					"      ,spai.file_id           fileId\n" +
					"      ,spai.notes             notes\n" +
                    "      ,spai.specification     specification\n" +
					"      ,sbc.category_name      categoryName\n" +
					"      ,spag.group_name        groupName\n" +
					"      ,spah.last_round        lastRound\n" +
					"      ,spah.auction_type      auctionType\n" +
					"      ,spail.item_ladder_id   itemLadderId\n" +
					"      ,rf.access_path         filePath\n" +
					"      ,rf.file_name           fileName\n" +
					"      ,spail.ladder_quantity  ladderQuantity\n" +
					"FROM   srm_pon_auction_headers spah\n" +
					"      ,srm_pon_auction_items   spai\n" +
					"LEFT   JOIN saaf_lookup_values slv1\n" +
					"ON     slv1.lookup_code = spai.unit_of_measure\n" +
					"AND    slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"LEFT   JOIN saaf_base_result_file rf\n" +
					"ON     rf.file_id = spai.file_id\n" +
					"LEFT   JOIN srm_base_categories sbc\n" +
					"ON     sbc.category_id = spai.category_id\n" +
					"LEFT   JOIN srm_pon_auction_groups spag\n" +
					"ON     spag.group_id = spai.group_id\n" +
					"LEFT   JOIN srm_base_items_b sbi\n" +
					"ON     sbi.item_id = spai.item_id\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_code = spai.tax_rate_code\n" +
					"AND    slv.lookup_type = 'PON_TAX_LIST'\n" +
					"LEFT   JOIN srm_pon_auction_item_ladders spail\n" +
					"ON     spail.auction_line_id = spai.auction_line_id\n" +
					"WHERE  spah.auction_header_id = spai.auction_header_id\n";

	//查询待报价的招标列表，发布状态且未报过价
    public static final String QUERY_UNBID_SQL =
					"SELECT *\n" +
					"FROM   (SELECT pah.auction_title auctionTitle\n" +
					"              ,pah.auction_header_id auctionHeaderId\n" +
					"              ,pah.auction_number auctionNumber\n" +
					"              ,pah.org_id orgId\n" +
					"              ,pah.auction_type auctionType\n" +
					"              ,pah.bid_start_date bidStartDate\n" +
					"              ,pah.bid_end_date bidEndDate\n" +
					"              ,pah.auction_status auctionStatusNo\n" +
					"              ,pah.inviting_bid_way invitingBidWay\n" +
					"              ,slv3.meaning auctionStatus\n" +
					"              ,pah.currency_code currencyCode\n" +
					"              ,pah.number_price_decimals numberPriceDecimals\n" +
					"              ,pah.show_current_round_min_price showCurrentRoundMinPrice\n" +
					"              ,pah.show_current_round_ranking showCurrentRoundRanking\n" +
					"              ,pah.multiple_bid_flag multipleBidFlag\n" +
					"              ,pah.max_bid_frequency maxBidFrequency\n" +
					"              ,pah.bid_bond_account_number bidBondAccountNumber\n" +
					"              ,pah.bid_bond_bank_name bidBondBankName\n" +
					"              ,pah.bid_bond bidBond\n" +
					"              ,pah.bid_bond_term bidBondTerm\n" +
					"              ,pah.note_to_supplier noteToSupplier\n" +
					"              ,pah.to_supplier_file_id toSupplierFileId\n" +
					"              ,si.inst_name instName\n" +
					"              ,slv.meaning currencyCodeName\n" +
					"              ,slv2.meaning auctionTypeName\n" +
					"              ,rf1.access_path toSupplierFilePath\n" +
					"              ,rf1.file_name toSupplierFileName\n" +
					"              ,(SELECT t.bargain_flag\n" +
					"                FROM   srm_pon_bid_headers t\n" +
					"                WHERE  t.auction_header_id = pah.auction_header_id\n" +
					"                AND    t.bid_status = 'ACT'\n" +
					"                AND    t.supplier_id = spas.supplier_id) bargainFlag\n" +
					"              ,spaj.user_id userId\n" +
					"              ,spaj.user_duty userDuty\n" +
					"              ,se.employee_name employeeName\n" +
					"              ,se.mobile_phone mobilePhone\n" +
					"              ,spas.supplier_id supplierId\n" +
					"              ,(SELECT s.supplier_id || 'S'\n" +
					"                FROM   srm_pon_auction_suppliers s\n" +
					"                WHERE  s.supplier_id = :varSupplierId\n" +
					"                AND    s.auction_header_id = pah.auction_header_id) overtFlag\n" +
					"        FROM   srm_pon_auction_headers pah\n" +
					"        LEFT   JOIN srm_pon_auction_juries spaj\n" +
					"        LEFT   JOIN saaf_employees se\n" +
					"        ON     spaj.employee_id = se.employee_id ON\n" +
					"         spaj.auction_header_id = pah.auction_header_id\n" +
					"        AND    spaj.user_duty = 3\n" +
					"        LEFT   JOIN saaf_lookup_values slv3\n" +
					"        ON     slv3.lookup_type = 'PON_AUCTION_STATUS'\n" +
					"        AND    slv3.lookup_code = pah.auction_status\n" +
					"        LEFT   JOIN saaf_base_result_file rf1\n" +
					"        ON     rf1.file_id = pah.to_supplier_file_id\n" +
					"        LEFT   JOIN saaf_institution si\n" +
					"        ON     si.inst_id = pah.org_id\n" +
					"        LEFT   JOIN saaf_lookup_values slv\n" +
					"        ON     slv.lookup_type = 'BANK_CURRENCY'\n" +
					"        AND    slv.lookup_code = pah.currency_code\n" +
					"        LEFT   JOIN saaf_lookup_values slv2\n" +
					"        ON     slv2.lookup_type = 'PON_AUCTION_TYPE'\n" +
					"        AND    slv2.lookup_code = pah.auction_type\n" +
					"        LEFT   JOIN srm_pon_auction_suppliers spas\n" +
					"        ON     pah.auction_header_id = spas.auction_header_id\n" +
					"        AND    spas.supplier_id = :supplierId\n" +
					"        WHERE  pah.auction_type = 'INQUIRY'\n" +
					"        UNION ALL\n" +
					"        SELECT pah.auction_title auctionTitle\n" +
					"              ,pah.auction_header_id auctionHeaderId\n" +
					"              ,pah.auction_number auctionNumber\n" +
					"              ,pah.org_id orgId\n" +
					"              ,pah.auction_type auctionType\n" +
					"              ,pah.bid_start_date bidStartDate\n" +
					"              ,pah.bid_end_date bidEndDate\n" +
					"              ,pah.auction_status auctionStatusNo\n" +
					"              ,pah.inviting_bid_way invitingBidWay\n" +
					"              ,slv3.meaning auctionStatus\n" +
					"              ,pah.currency_code currencyCode\n" +
					"              ,pah.number_price_decimals numberPriceDecimals\n" +
					"              ,pah.show_current_round_min_price showCurrentRoundMinPrice\n" +
					"              ,pah.show_current_round_ranking showCurrentRoundRanking\n" +
					"              ,pah.multiple_bid_flag multipleBidFlag\n" +
					"              ,pah.max_bid_frequency maxBidFrequency\n" +
					"              ,pah.bid_bond_account_number bidBondAccountNumber\n" +
					"              ,pah.bid_bond_bank_name bidBondBankName\n" +
					"              ,pah.bid_bond bidBond\n" +
					"              ,pah.bid_bond_term bidBondTerm\n" +
					"              ,pah.note_to_supplier noteToSupplier\n" +
					"              ,pah.to_supplier_file_id toSupplierFileId\n" +
					"              ,si.inst_name instName\n" +
					"              ,slv.meaning currencyCodeName\n" +
					"              ,slv2.meaning auctionTypeName\n" +
					"              ,rf1.access_path toSupplierFilePath\n" +
					"              ,rf1.file_name toSupplierFileName\n" +
					"              ,(SELECT t.bargain_flag\n" +
					"                FROM   srm_pon_bid_headers t\n" +
					"                WHERE  t.auction_header_id = pah.auction_header_id\n" +
					"                AND    t.bid_status = 'ACT'\n" +
					"                AND    t.supplier_id = spas.supplier_id) bargainFlag\n" +
					"              ,spaj.user_id userId\n" +
					"              ,spaj.user_duty userDuty\n" +
					"              ,se.employee_name employeeName\n" +
					"              ,se.mobile_phone mobilePhone\n" +
					"              ,spas.supplier_id supplierId\n" +
					"              ,(SELECT s.supplier_id || 'S'\n" +
					"                FROM   srm_pon_auction_suppliers s\n" +
					"                WHERE  s.supplier_id = :varSupplierId\n" +
					"                AND    s.inviting_bid_way = '2'\n" +
					"                AND    s.auction_header_id = pah.auction_header_id) overtFlag\n" +
					"        FROM   srm_pon_auction_headers pah\n" +
					"        LEFT   JOIN srm_pon_auction_juries spaj\n" +
					"        LEFT   JOIN saaf_employees se\n" +
					"        ON     spaj.employee_id = se.employee_id ON\n" +
					"         spaj.auction_header_id = pah.auction_header_id\n" +
					"        AND    spaj.user_duty = 5\n" +
					"        LEFT   JOIN saaf_lookup_values slv3\n" +
					"        ON     slv3.lookup_type = 'PON_AUCTION_STATUS'\n" +
					"        AND    slv3.lookup_code = pah.auction_status\n" +
					"        LEFT   JOIN saaf_base_result_file rf1\n" +
					"        ON     rf1.file_id = pah.to_supplier_file_id\n" +
					"        LEFT   JOIN saaf_institution si\n" +
					"        ON     si.inst_id = pah.org_id\n" +
					"        LEFT   JOIN saaf_lookup_values slv\n" +
					"        ON     slv.lookup_type = 'BANK_CURRENCY'\n" +
					"        AND    slv.lookup_code = pah.currency_code\n" +
					"        LEFT   JOIN saaf_lookup_values slv2\n" +
					"        ON     slv2.lookup_type = 'PON_AUCTION_TYPE'\n" +
					"        AND    slv2.lookup_code = pah.auction_type\n" +
					"        LEFT   JOIN srm_pon_auction_suppliers spas\n" +
					"        ON     pah.auction_header_id = spas.auction_header_id\n" +
					"        AND    spas.supplier_id = :supplierId\n" +
					"        WHERE  pah.auction_type = 'TENDER') ah\n" +
					"WHERE  1 = 1\n" +
					"AND    (ah.bidEndDate > SYSDATE OR ah.bargainFlag = '2')\n";

	//查询招标的物料
    public static final String QUERY_AUCTION_ITEM_SQL =
					"SELECT spai.auction_line_id   auctionLineId\n" +
					"      ,spai.auction_header_id auctionHeaderId\n" +
					"      ,spai.line_number       lineNumber\n" +
					"      ,spai.disp_line_number  dispLineNumber\n" +
					"      ,spai.line_type         lineType\n" +
					"      ,spai.parent_line_id    parentLineId\n" +
					"      ,spai.group_id          groupId\n" +
					"      ,spai.item_id           itemId\n" +
					"      ,sbi.item_code          itemNumber\n" +
					"      ,spai.item_description  itemDescription\n" +
					"      ,spai.unit_of_measure   unitOfMeasurenum\n" +
					"      ,slv1.meaning           unitOfMeasure\n" +
					"      ,spai.category_id       categoryId\n" +
					"      ,spai.quantity          quantity\n" +
					"      ,spai.tax_rate_code     taxRateCode\n" +
					"      ,slv.meaning            taxRateName\n" +
					"      ,spai.start_date        startDate\n" +
					"      ,spai.end_date          endDate\n" +
					"      ,spai.award_status      awardStatus\n" +
					"      ,spai.awarded_quantity  awardedQuantity\n" +
					"      ,spai.file_id           fileId\n" +
					"      ,spai.notes             notes\n" +
                    "      ,spai.specification             specification\n" +
					"      ,sbc.category_name      categoryName\n" +
					"      ,spag.group_name        groupName\n" +
					"      ,spah.last_round        lastRound\n" +
					"      ,rf.access_path         filePath\n" +
					"      ,rf.file_name           fileName\n" +
					"      ,spah.auction_type      auctionType\n" +
					"FROM   srm_pon_auction_headers spah\n" +
					"      ,srm_pon_auction_items   spai\n" +
					"LEFT   JOIN saaf_lookup_values slv1\n" +
					"ON     slv1.lookup_code = spai.unit_of_measure\n" +
					"AND    slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"LEFT   JOIN saaf_base_result_file rf\n" +
					"ON     rf.file_id = spai.file_id\n" +
					"LEFT   JOIN srm_base_categories sbc\n" +
					"ON     sbc.category_id = spai.category_id\n" +
					"LEFT   JOIN srm_pon_auction_groups spag\n" +
					"ON     spag.group_id = spai.group_id\n" +
					"LEFT   JOIN srm_base_items_b sbi\n" +
					"ON     sbi.item_id = spai.item_id\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_code = spai.tax_rate_code\n" +
					"AND    slv.lookup_type = 'PON_TAX_LIST'\n" +
					"WHERE  spah.auction_header_id = spai.auction_header_id\n";

	//查询监控报价的物料
    public static final String QUERY_PRICE_ITEM_SQL =
					"SELECT spai.auction_line_id   auctionLineId\n" +
					"      ,spai.auction_header_id auctionHeaderId\n" +
					"      ,spai.line_number       lineNumber\n" +
					"      ,spai.disp_line_number  dispLineNumber\n" +
					"      ,spai.line_type         lineType\n" +
					"      ,spai.parent_line_id    parentLineId\n" +
					"      ,spai.group_id          groupId\n" +
					"      ,spai.item_id           itemId\n" +
					"      ,sbi.item_code          itemNumber\n" +
					"      ,spai.item_description  itemDescription\n" +
					"      ,spai.unit_of_measure   unitOfMeasureNum\n" +
					"      ,slv1.meaning           unitOfMeasure\n" +
					"      ,spai.category_id       categoryId\n" +
					"      ,spai.quantity          quantity\n" +
					"      ,spai.tax_rate_code     taxRateCode\n" +
					"      ,slv.meaning            taxRateName\n" +
					"      ,spai.start_date        startDate\n" +
					"      ,spai.end_date          endDate\n" +
					"      ,spai.award_status      awardStatus\n" +
					"      ,spai.awarded_quantity  awardedQuantity\n" +
					"      ,spai.file_id           fileId\n" +
					"      ,spai.notes             notes\n" +
					"      ,sbc.category_name      categoryName\n" +
					"      ,spag.group_name        groupName\n" +
					"      ,spah.last_round        lastRound\n" +
					"      ,rf.access_path         filePath\n" +
					"      ,rf.file_name           fileName\n" +
					"      ,spah.auction_type      auctionType\n" +
					"      ,pail.ladder_quantity   ladderQuantity\n" +
					"      ,pail.item_ladder_id    itemLadderId\n" +
					"      ,spah.subsection_flag   subsectionFlag\n" +
					"FROM   srm_pon_auction_headers spah\n" +
					"      ,srm_pon_auction_items   spai\n" +
					"LEFT   JOIN srm_pon_auction_item_ladders pail\n" +
					"ON     pail.auction_header_id = spai.auction_header_id\n" +
					"AND    pail.auction_line_id = spai.auction_line_id\n" +
					"LEFT   JOIN saaf_lookup_values slv1\n" +
					"ON     slv1.lookup_code = spai.unit_of_measure\n" +
					"AND    slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"LEFT   JOIN saaf_base_result_file rf\n" +
					"ON     rf.file_id = spai.file_id\n" +
					"LEFT   JOIN srm_base_categories sbc\n" +
					"ON     sbc.category_id = spai.category_id\n" +
					"LEFT   JOIN srm_pon_auction_groups spag\n" +
					"ON     spag.group_id = spai.group_id\n" +
					"LEFT   JOIN srm_base_items_b sbi\n" +
					"ON     sbi.item_id = spai.item_id\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_code = spai.tax_rate_code\n" +
					"AND    slv.lookup_type = 'PON_TAX_LIST'\n" +
					"WHERE  spah.auction_header_id = spai.auction_header_id\n";

	//查询报价头
    public static final String QUERY_AUCTION_BID_SQL =
					"SELECT *\n" +
					"FROM   (SELECT h.bid_header_id bidHeaderId\n" +
					"              ,h.auction_header_id auctionHeaderId\n" +
					"              ,h.bid_number bidNumber\n" +
					"              ,h.bid_status bidStatus\n" +
					"              ,h.payment_condition paymentCondition\n" +
					"              ,h.note_to_auction_owner noteToAuctionOwner\n" +
					"              ,h.to_owner_file_id toOwnerFileId\n" +
					"              ,h.bargain_flag bargainFlag\n" +
                    "              ,h.bargain_reason bargainReason\n" +
					"              ,spah.auction_status auctionStatus\n" +
					"              ,spah.auction_title auctionTitle\n" +
					"              ,spah.auction_type auctionType\n" +
					"              ,spah.bid_start_date bidStartDate\n" +
					"              ,spah.bid_end_date bidEndDate\n" +
					"              ,spah.auction_number auctionNumber\n" +
					"              ,spah.payment_condition_update_flag paymentConditionUpdateFlag\n" +
                    "              ,spah.item_type itemType\n" +
					"              ,spah.ekp_number ekpNumber\n" +
					"              ,rf.access_path toOwnerFilePath\n" +
					"              ,rf.file_name toOwnerFileName\n" +
					"              ,slv.meaning auctionTypeName\n" +
					"              ,slv1.meaning bidStatusName\n" +
					"              ,spah.subsection_flag subsectionFlag\n" +
					"              ,sppt.payment_term_name paymentTermName\n" +
					"              ,spaj.user_id userId\n" +
					"              ,spaj.user_duty userDuty\n" +
					"              ,se.employee_name employeeName\n" +
					"              ,se.mobile_phone mobilePhone\n" +
					"              ,h.version_num versionNum\n" +
					"              ,h.supplier_id supplierId\n" +
                    "              ,spsi.supplier_name supplierName\n" +
                    "              ,h.tax_rate_code taxRateCode\n" +
                    "              ,slv2.meaning taxRateName\n" +
                    "              ,h.tran_manage_fees tranManageFees\n" +
                    "              ,h.measures_fees measuresFees\n" +
                    "              ,h.tran_manage_percen tranManagePercen\n" +
                    "              ,h.measures_percen measuresPercen\n" +
                    "              ,h.project_costs projectCosts\n" +
                    "              ,h.engineering_tax engineeringTax\n" +
                    "              ,h.total_project_cost totalProjectCost\n" +
                    "              ,slv2.tag tag\n" +
					"        FROM   srm_pon_bid_headers h\n" +
					"        LEFT   JOIN saaf_base_result_file rf\n" +
					"        ON     rf.file_id = h.to_owner_file_id\n" +
                    "        LEFT   JOIN srm_pos_supplier_info spsi\n" +
                    "        ON     spsi.supplier_id=h.supplier_id\n" +
					"        LEFT   JOIN saaf_lookup_values slv1\n" +
					"        ON     slv1.lookup_type = 'PON_OFFER_STATUS'\n" +
					"        AND    slv1.lookup_code = h.bid_status\n" +
                    "        LEFT   JOIN saaf_lookup_values slv2\n" +
                    "        ON     slv2.lookup_type = 'PON_TAX_LIST'\n" +
                    "        AND    slv2.lookup_code = h.tax_rate_code\n" +
					"        LEFT   JOIN srm_pon_payment_terms sppt\n" +
					"        ON     sppt.payment_term_code = h.payment_condition,\n" +
					"         srm_pon_auction_headers spah\n" +
					"        LEFT   JOIN srm_pon_auction_juries spaj\n" +
					"        LEFT   JOIN saaf_employees se\n" +
					"        ON     spaj.employee_id = se.employee_id ON\n" +
					"         spaj.auction_header_id = spah.auction_header_id\n" +
					"        AND    spaj.user_duty = 5\n" +
					"        LEFT   JOIN saaf_lookup_values slv\n" +
					"        ON     slv.lookup_type = 'PON_AUCTION_TYPE'\n" +
					"        AND    slv.lookup_code = spah.auction_type\n" +
					"        WHERE  spah.auction_header_id = h.auction_header_id\n" +
					"        AND    spah.auction_type = 'TENDER'\n" +
					"        UNION ALL\n" +
					"        SELECT h.bid_header_id bidHeaderId\n" +
					"              ,h.auction_header_id auctionHeaderId\n" +
					"              ,h.bid_number bidNumber\n" +
					"              ,h.bid_status bidStatus\n" +
					"              ,h.payment_condition paymentCondition\n" +
					"              ,h.note_to_auction_owner noteToAuctionOwner\n" +
					"              ,h.to_owner_file_id toOwnerFileId\n" +
					"              ,h.bargain_flag bargainFlag\n" +
                    "              ,h.bargain_reason bargainReason\n" +
					"              ,spah.auction_status auctionStatus\n" +
					"              ,spah.auction_title auctionTitle\n" +
					"              ,spah.auction_type auctionType\n" +
					"              ,spah.bid_start_date bidStartDate\n" +
					"              ,spah.bid_end_date bidEndDate\n" +
					"              ,spah.auction_number auctionNumber\n" +
					"              ,spah.payment_condition_update_flag paymentConditionUpdateFlag\n" +
                    "              ,spah.item_type itemType\n" +
                    "              ,spah.ekp_number ekpNumber\n" +
					"              ,rf.access_path toOwnerFilePath\n" +
					"              ,rf.file_name toOwnerFileName\n" +
					"              ,slv.meaning auctionTypeName\n" +
					"              ,slv1.meaning bidStatusName\n" +
					"              ,spah.subsection_flag subsectionFlag\n" +
					"              ,sppt.payment_term_name paymentTermName\n" +
					"              ,spaj.user_id userId\n" +
					"              ,spaj.user_duty userDuty\n" +
					"              ,se.employee_name employeeName\n" +
					"              ,se.mobile_phone mobilePhone\n" +
					"              ,h.version_num versionNum\n" +
					"              ,h.supplier_id supplierId\n" +
                    "              ,spsi.supplier_name supplierName\n" +
                    "              ,h.tax_rate_code taxRateCode\n" +
                    "              ,slv2.meaning taxRateName\n" +
                    "              ,h.tran_manage_fees tranManageFees\n" +
                    "              ,h.measures_fees measuresFees\n" +
                    "              ,h.tran_manage_percen tranManagePercen\n" +
                    "              ,h.measures_percen measuresPercen\n" +
                    "              ,h.project_costs projectCosts\n" +
                    "              ,h.engineering_tax engineeringTax\n" +
                    "              ,h.total_project_cost totalProjectCost\n" +
                    "              ,slv2.tag tag\n" +
					"        FROM   srm_pon_bid_headers h\n" +
					"        LEFT   JOIN saaf_base_result_file rf\n" +
					"        ON     rf.file_id = h.to_owner_file_id\n" +
                    "        LEFT   JOIN srm_pos_supplier_info spsi\n" +
                    "        ON     spsi.supplier_id=h.supplier_id\n" +
					"        LEFT   JOIN saaf_lookup_values slv1\n" +
					"        ON     slv1.lookup_type = 'PON_OFFER_STATUS'\n" +
					"        AND    slv1.lookup_code = h.bid_status\n" +
                    "        LEFT   JOIN saaf_lookup_values slv2\n" +
                    "        ON     slv2.lookup_type = 'PON_TAX_LIST'\n" +
                    "        AND    slv2.lookup_code = h.tax_rate_code\n" +
					"        LEFT   JOIN srm_pon_payment_terms sppt\n" +
					"        ON     sppt.payment_term_code = h.payment_condition,\n" +
					"         srm_pon_auction_headers spah\n" +
					"        LEFT   JOIN srm_pon_auction_juries spaj\n" +
					"        LEFT   JOIN saaf_employees se\n" +
					"        ON     spaj.employee_id = se.employee_id ON\n" +
					"         spaj.auction_header_id = spah.auction_header_id\n" +
					"        AND    spaj.user_duty = 3\n" +
					"        LEFT   JOIN saaf_lookup_values slv\n" +
					"        ON     slv.lookup_type = 'PON_AUCTION_TYPE'\n" +
					"        AND    slv.lookup_code = spah.auction_type\n" +
					"        WHERE  spah.auction_header_id = h.auction_header_id\n" +
					"        AND    spah.auction_type = 'INQUIRY') bh\n" +
					"WHERE  1 = 1\n";

	//查询报价行
    public static final String QUERY_AUCTION_BID_LINE_SQL =
					"SELECT\n" +
					"  pbip.bid_line_id bidLineId,\n" +
					"  pbip.bid_header_id bidHeaderId,\n" +
					"  pbip.auction_header_id auctionHeaderId,\n" +
					"  pbip.auction_line_id auctionLineId,\n" +
					"  pbip.item_ladder_id itemLadderId,\n" +
					"  pbip.item_id itemId,\n" +
					"  pbip.item_description itemDescription,\n" +
					"  pbip.promised_quantity promisedQuantity,\n" +
					"  pbip.tax_price taxPrice,\n" +
					"  pbip.tax_rate taxRate,\n" +
					"  pbip.no_tax_price noTaxPrice,\n" +
					"  pbip.bargain bargain,\n" +
					"  pbip.rank rank,\n" +
                    "  pbip.specification specification,\n" +
					"  pbip.promised_start_date promisedStartDate,\n" +
					"  pbip.promised_end_date promisedEndDate,\n" +
					"  pbip.award_quantity awardQuantity,\n" +
					"  pbip.award_status awardStatus,\n" +
					"  pbip.award_date awardDate,\n" +
					"  pbip.bid_line_file_id bidLineFileId,\n" +
					"  pbip.materials_price materialsPrice,\n" +
					"  pbip.artificial_price artificialPrice,\n" +
					"  rf.access_Path filePath,\n" +
					"  rf.file_Name fileName,\n" +
					"  sbc.category_name categoryName,\n" +
                    "  sbc.FULL_CATEGORY_CODE fullCategoryCode,\n" +
                    "  sbc.FULL_CATEGORY_NAME fullCategoryName,\n" +
					"  pai.auction_line_id auctionLineId,\n" +
					"  pai.auction_header_id auctionHeaderId,\n" +
					"  pai.line_number lineNumber,\n" +
					"  pai.disp_line_number dispLineNumber,\n" +
					"  pai.line_type lineType,\n" +
					"  pai.parent_line_id parentLineId,\n" +
					"  pai.group_id groupId,\n" +
					"  pai.item_id itemId,\n" +
					"  sbi.item_code itemNumber,\n" +
                    "  sbi.item_name itemName,\n" +
					"  pai.item_description itemDescription,\n" +
					"  pai.unit_of_measure unitOfMeasureNum,\n" +
					"  (CASE WHEN slv1.meaning IS NOT NULL THEN slv1.meaning ELSE pai.unit_of_measure END) unitOfMeasure,\n" +
					"  pai.category_id categoryId,\n" +
					"  pai.quantity quantity,\n" +
					"  pai.tax_rate_code taxRateCode,\n" +
					"  slv.meaning taxRateName,\n" +
					"  pai.start_date startDate,\n" +
					"  pai.end_date endDate,\n" +
					"  pai.award_status awardStatus,\n" +
					"  pai.awarded_quantity awardedQuantity,\n" +
					"  pai.notes notes,\n" +
					"  pag.group_name groupName,\n" +
					"  (SELECT\n" +
					"    MIN(pbip1.no_tax_price)\n" +
					"  FROM\n" +
					"    srm_pon_bid_item_prices pbip1\n" +
					"  WHERE pbip1.auction_header_id = pbip.auction_header_id\n" +
					"    AND pbip1.auction_line_id = pbip.auction_line_id) roundMinPrice,\n" +
                    "  h.tax_rate_code headerTaxRateCode,\n" +
                    "  slv.meaning headerTaxRateName\n" +
                    ",(pbip.artificial_price*Pai.Quantity) totalArtiPrice\n" +
                    ",(pbip.materials_price+pbip.artificial_price) materArtiPrice\n" +
                    ",(pbip.materials_price+pbip.artificial_price)*Pai.Quantity maTotal\n" +
					"FROM\n" +
					"  srm_pon_bid_item_prices pbip\n" +
                    "  LEFT JOIN srm_pon_bid_headers h\n" +
                    "    ON h.bid_header_id = pbip.bid_header_id\n" +
                    "  LEFT JOIN saaf_lookup_values slv2\n" +
                    "    ON slv2.lookup_code = h.tax_rate_code\n" +
                    "    AND slv2.lookup_type = 'PON_TAX_LIST'\n" +
					"  LEFT JOIN saaf_base_result_file rf\n" +
					"    ON rf.file_id = pbip.bid_line_file_id,\n" +
					"  srm_pon_auction_items pai\n" +
					"  LEFT JOIN saaf_lookup_values slv1\n" +
					"    ON slv1.lookup_code = pai.unit_of_measure\n" +
					"    AND slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"  LEFT JOIN srm_pon_auction_groups pag\n" +
					"    ON pag.group_id = pai.group_id\n" +
					"  LEFT JOIN srm_base_categories sbc\n" +
					"    ON sbc.category_id = pai.category_id\n" +
					"  LEFT JOIN srm_base_items_b sbi\n" +
					"    ON sbi.item_id = pai.item_id\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_code = pai.tax_rate_code\n" +
					"    AND slv.lookup_type = 'PON_TAX_LIST'\n" +
					"WHERE pbip.auction_line_id = pai.auction_line_id\n";

	//查询未创建报价的标的物
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
					"  pai.unit_of_measure AS unitOfMeasureNum,\n" +
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
                    "  pai.specification AS specification,\n" +
					"  sbc.category_name AS categoryName,\n" +
					"  pag.group_name AS groupName,\n" +
					"  sbi.item_code AS itemNumber,\n" +
                    "  sbi.item_name itemName,\n" +
					"  sbrl.file_Name AS fileName,\n" +
					"  (CASE WHEN slv.meaning IS NOT NULL THEN slv.meaning ELSE pai.unit_of_measure END) unitOfMeasure,\n" +
					"  slv2.meaning AS taxRateName,\n" +
					"  sbrl.access_Path AS filePath,\n" +
					"  (SELECT\n" +
					"    MIN(pbip1.no_tax_price)\n" +
					"  FROM\n" +
					"    srm_pon_bid_item_prices pbip1\n" +
					"  WHERE pbip1.auction_header_id = pai.auction_header_id\n" +
					"    AND pbip1.auction_line_id = pai.auction_line_id) roundMinPrice\n" +
					"FROM\n" +
					"  srm_pon_auction_items pai\n" +
					"  LEFT JOIN srm_base_categories sbc\n" +
					"    ON sbc.category_id = pai.category_id\n" +
					"  LEFT JOIN srm_pon_auction_groups pag\n" +
					"    ON pag.group_id = pai.group_id\n" +
					"  LEFT JOIN srm_base_items_b sbi\n" +
					"    ON sbi.item_id = pai.item_id\n" +
					"  LEFT JOIN saaf_base_result_file sbrl\n" +
					"    ON sbrl.file_id = pai.file_id\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_code = pai.unit_of_measure\n" +
					"    AND slv.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_code = pai.tax_rate_code\n" +
					"    AND slv2.lookup_type = 'PON_TAX_LIST'\n" +
					"WHERE 1 = 1";

	public static final String QUERY_AUCTION_SUPPLIER_LIST_SQL =
					"SELECT pas.invite_id             inviteId\n" +
					"      ,pas.supplier_name         supplierName\n" +
					"      ,pas.supplier_id           supplierId\n" +
					"      ,pas.supplier_contact_name supplierContactName\n" +
					"FROM   srm_pon_auction_suppliers pas\n" +
					"WHERE  1 = 1\n";

	//已发布的标书，查看监控报价
    public static final String QUERY_MONITOR_BID_PRICE_SQL =
					"SELECT h.bid_number            bidNumber\n" +
					"      ,h.supplier_contact_name supplierContactName\n" +
					"      ,h.bid_status            bidStatus\n" +
					"      ,h.creation_date         creationDate\n" +
					"      ,h.publish_date          publishDate\n" +
					"      ,slv.meaning             bidStatusName\n" +
					"FROM   srm_pon_bid_headers h\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_type = 'PON_BID_STATUS'\n" +
					"AND    slv.lookup_code = h.bid_status\n" +
					"WHERE  h.supplier_id = ?\n" +
					"AND    h.auction_header_id = ?\n";

	//查询供应商的第一次报价
    public static final String QUERY_FIRST_BID_SQL =
					"SELECT h.bid_header_id\n" +
					"FROM   srm_pon_bid_headers h\n" +
					"WHERE  h.publish_date IS NOT NULL\n" +
					"AND    h.bid_status <> 'DRAFT'\n" +
					"AND    h.auction_header_id = ?\n" +
					"AND    h.supplier_id = ?\n" +
					"ORDER  BY h.publish_date ASC\n";

	//获取报价的供应商
//    public static final String QUERY_BID_SUPPLIER_ITEMS_SQL = "select \n" +
//            "bip.ITEM_ID itemId,\n" +
//            "bip.auction_line_id auctionLineId,\n" +
//            "pbh.BID_HEADER_ID bidHeaderId,\n" +
//            "pbh.SUPPLIER_NAME supplierName,\n" +
//            "pai.INDICATOR_NUMBER indicatorNumber,\n" +
//            "pbh.supplier_id supplierId,\n" +
//            "bip.PRICE_ADJUSTMENT priceAdjustment\n" +
//            "from \n" +
//            "srm_pon_bid_item_prices as bip \n" +
//            "left join srm_pon_bid_headers as pbh on pbh.BID_HEADER_ID = bip.BID_HEADER_ID\n" +
//            "left join srm_pon_auction_items as pai on pai.AUCTION_LINE_ID = bip.AUCTION_LINE_ID\n" +
//            "where bip.AUCTION_HEADER_ID = ? and bip.AWARD_STATUS in('5','6')";

    public static final String QUERY_ALL_SUPPLIER_PRICE = "SELECT y.no_tax_price FROM srm_pon_bid_item_prices y WHERE 1 = 1\n";

    private String taxRateCode; //税率
    private String taxRateName; //税率名称
    private Integer auctionLineId;
    private Integer auctionHeaderId;
    private Integer lineNumber;
    private String dispLineNumber;
    private String lineType;
    private Integer parentLineId;
    private Integer groupId;
    private Integer indicatorId;
    private String indicatorNumber;
    private Integer indicatorVersion;
    private Integer itemId;
    private String itemNumber;
    private String itemDescription;
    private String unitOfMeasure;
    private Integer categoryId;
    private BigDecimal quantity;
    private BigDecimal basePrice;
    private BigDecimal priceAdjustment;
    private BigDecimal limitPrice;
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
    private String notes;
    private String categoryName;
    private String groupName;
    private Integer numberOfWin;
    private String indicatorName;
    private String awardStatus;
    private Integer bidHeaderId;
    private Integer bidLineId;
    private String bidNumber;
    private String bidStatus;
    private String auctionTitle;
    private String auctionType;
    private String auctionNumber;
    private String auctionTypeName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date bidStartDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date bidEndDate;
    private String taxCode;
    private BigDecimal taxPrice;
    private BigDecimal bestUnitPrice;
    private String bidStatusName;
    private String isLaunchFlag;
    private BigDecimal initialPrice;
    private String transportationLine;
    private BigDecimal volumeFraction;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;
    private String supplierContactName;
    private String isFollowFlag;
    private String awardStatusName;
    private String currencyCodeName;
    private String auctionStatus;
    private String noteToSupplier;
    private String toSupplierFilePath;
    private String toSupplierFileName;
    private Integer toSupplierFileId;
    private Integer toOwnerFileId;
    private Integer fileId;
    private String toOwnerFileName;
    private String toOwnerFilePath;
    @JSONField(format = "yyyy-MM-dd")
    private Date bidBondTerm;
    private BigDecimal bidBond;
    private String bidBondBankName;
    private String biddingType;
    private String bidBondAccountNumber;
    private Integer maxBidFrequency;
    private String showCurrentRoundRanking;
    private String multipleBidFlag;
    private String currencyCode;
    private String instName;
    private String shipToAddress;
    private String openBiddingStyle;
    private String employeeName;
    private String showCurrentRoundMinPrice;
    private Integer numberPriceDecimals;
    private String noteToAuctionOwner;
    private Integer rank;
    private Integer inviteId;
    private Integer supplierId;
    private String supplierName;
    private List<SrmPonBidEntity_HI_RO> lineDataList;
    private String initialPriceRequirement;
    private Integer lastRound;
    private Integer destinationId;
    private String zoneCode;
    private String province;
    private String destinationAddress;
    private BigDecimal timeForArrival;
    private String paymentCondition;

    private Integer itemLadderId; //洽谈行阶梯ID
    private BigDecimal promisedQuantity; //承诺数量
    private String taxRate; //税率
    private BigDecimal noTaxPrice; //不含税单价
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date promisedStartDate; //承诺开始日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date promisedEndDate; //承诺结束日期
    private BigDecimal awardQuantity; //中标数量
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date awardDate; //中标时间
    private Integer bidLineFileId; //附件
    private String filePath; //附件路径
    private String fileName; //附件名称
    private BigDecimal roundMinPrice;//本轮最低价格
    private BigDecimal ladderQuantity;//阶梯数量
    private String paymentTermName;//付款条件
    private String paymentConditionUpdateFlag;//是否允许更改付款条件
    private String subsectionFlag;//是否阶梯数量
    private String unitOfMeasureName;//单位
    private String unitOfMeasureNum;//单位（快码）
	private Integer userId;//人员ID
	private String userDuty;//人员职责
	private String mobilePhone;//联系电话
	private String invitingBidWay;//邀标方式
	private String overtFlag;
	private String bargainFlag;//议价状态
	private Integer versionNum;//版本
	private BigDecimal bargain;//议价前的价格
    private String bargainReason;//议价原因
	private BigDecimal materialsPrice;//材料单价
	private BigDecimal artificialPrice;//人工单价
    private String itemType;//寻源物料类型
    private String headerTaxRateCode;//报价头税率
    private String headerTaxRateName;//报价头税率
    private String specification;
    private BigDecimal tranManageFees;//运杂及管理费
    private BigDecimal measuresFees;//规费及措施费
    private BigDecimal tranManagePercen;//运杂及管理费比率
    private BigDecimal measuresPercen;//运杂及管理费比率
    private BigDecimal projectCosts;
    private BigDecimal engineeringTax;
    private BigDecimal totalProjectCost;
    private BigDecimal totalArtiPrice;//人工总价
    private BigDecimal materArtiPrice;//材料与人工合计单价
    private BigDecimal maTotal;//总价
    private String tag;
    private String ekpNumber;
    private String categoryCode;
    private String fullCategoryCode;
    private String fullCategoryName;
    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getFullCategoryCode() {
        return fullCategoryCode;
    }

    public void setFullCategoryCode(String fullCategoryCode) {
        this.fullCategoryCode = fullCategoryCode;
    }

    public String getFullCategoryName() {
        return fullCategoryName;
    }

    public void setFullCategoryName(String fullCategoryName) {
        this.fullCategoryName = fullCategoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getEkpNumber() {
		return ekpNumber;
	}

	public void setEkpNumber(String ekpNumber) {
		this.ekpNumber = ekpNumber;
	}

	public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public BigDecimal getTotalArtiPrice() {
        return totalArtiPrice;
    }

    public void setTotalArtiPrice(BigDecimal totalArtiPrice) {
        this.totalArtiPrice = totalArtiPrice;
    }

    public BigDecimal getMaterArtiPrice() {
        return materArtiPrice;
    }

    public void setMaterArtiPrice(BigDecimal materArtiPrice) {
        this.materArtiPrice = materArtiPrice;
    }

    public BigDecimal getMaTotal() {
        return maTotal;
    }

    public void setMaTotal(BigDecimal maTotal) {
        this.maTotal = maTotal;
    }

    public BigDecimal getProjectCosts() {
        return projectCosts;
    }

    public void setProjectCosts(BigDecimal projectCosts) {
        this.projectCosts = projectCosts;
    }

    public BigDecimal getEngineeringTax() {
        return engineeringTax;
    }

    public void setEngineeringTax(BigDecimal engineeringTax) {
        this.engineeringTax = engineeringTax;
    }

    public BigDecimal getTotalProjectCost() {
        return totalProjectCost;
    }

    public void setTotalProjectCost(BigDecimal totalProjectCost) {
        this.totalProjectCost = totalProjectCost;
    }

    public BigDecimal getTranManageFees() {
        return tranManageFees;
    }

    public void setTranManageFees(BigDecimal tranManageFees) {
        this.tranManageFees = tranManageFees;
    }

    public BigDecimal getMeasuresFees() {
        return measuresFees;
    }

    public void setMeasuresFees(BigDecimal measuresFees) {
        this.measuresFees = measuresFees;
    }

    public BigDecimal getTranManagePercen() {
        return tranManagePercen;
    }

    public void setTranManagePercen(BigDecimal tranManagePercen) {
        this.tranManagePercen = tranManagePercen;
    }

    public BigDecimal getMeasuresPercen() {
        return measuresPercen;
    }

    public void setMeasuresPercen(BigDecimal measuresPercen) {
        this.measuresPercen = measuresPercen;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getHeaderTaxRateCode() {
        return headerTaxRateCode;
    }

    public void setHeaderTaxRateCode(String headerTaxRateCode) {
        this.headerTaxRateCode = headerTaxRateCode;
    }

    public String getHeaderTaxRateName() {
        return headerTaxRateName;
    }

    public void setHeaderTaxRateName(String headerTaxRateName) {
        this.headerTaxRateName = headerTaxRateName;
    }

    public String getPaymentCondition() {
		return paymentCondition;
	}

	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

	public String getUnitOfMeasureNum() {
        return unitOfMeasureNum;
    }

    public void setUnitOfMeasureNum(String unitOfMeasureNum) {
        this.unitOfMeasureNum = unitOfMeasureNum;
    }

    public String getUnitOfMeasureName() {
        return unitOfMeasureName;
    }

    public void setUnitOfMeasureName(String unitOfMeasureName) {
        this.unitOfMeasureName = unitOfMeasureName;
    }

    public String getPaymentTermName() {
        return paymentTermName;
    }

    public void setPaymentTermName(String paymentTermName) {
        this.paymentTermName = paymentTermName;
    }

    public BigDecimal getLadderQuantity() {
        return ladderQuantity;
    }

    public void setLadderQuantity(BigDecimal ladderQuantity) {
        this.ladderQuantity = ladderQuantity;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getItemLadderId() {
        return itemLadderId;
    }

    public void setItemLadderId(Integer itemLadderId) {
        this.itemLadderId = itemLadderId;
    }

    public BigDecimal getPromisedQuantity() {
        return promisedQuantity;
    }

    public void setPromisedQuantity(BigDecimal promisedQuantity) {
        this.promisedQuantity = promisedQuantity;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getNoTaxPrice() {
        return noTaxPrice;
    }

    public void setNoTaxPrice(BigDecimal noTaxPrice) {
        this.noTaxPrice = noTaxPrice;
    }

    public Date getPromisedStartDate() {
        return promisedStartDate;
    }

    public void setPromisedStartDate(Date promisedStartDate) {
        this.promisedStartDate = promisedStartDate;
    }

    public Date getPromisedEndDate() {
        return promisedEndDate;
    }

    public void setPromisedEndDate(Date promisedEndDate) {
        this.promisedEndDate = promisedEndDate;
    }

    public BigDecimal getAwardQuantity() {
        return awardQuantity;
    }

    public void setAwardQuantity(BigDecimal awardQuantity) {
        this.awardQuantity = awardQuantity;
    }
    
    public Date getAwardDate() {
		return awardDate;
	}

	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
	}

	public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getBidLineFileId() {
        return bidLineFileId;
    }

    public void setBidLineFileId(Integer bidLineFileId) {
        this.bidLineFileId = bidLineFileId;
    }

    public String getTaxRateCode() {
        return taxRateCode;
    }

    public void setTaxRateCode(String taxRateCode) {
        this.taxRateCode = taxRateCode;
    }

    public String getTaxRateName() {
        return taxRateName;
    }

    public void setTaxRateName(String taxRateName) {
        this.taxRateName = taxRateName;
    }

    public BigDecimal getPriceAdjustment() {
        return priceAdjustment;
    }

    public void setPriceAdjustment(BigDecimal priceAdjustment) {
        this.priceAdjustment = priceAdjustment;
    }

    public List<SrmPonBidEntity_HI_RO> getLineDataList() {
        return lineDataList;
    }

    public void setLineDataList(List<SrmPonBidEntity_HI_RO> lineDataList) {
        this.lineDataList = lineDataList;
    }

    public Integer getAuctionLineId() {
        return auctionLineId;
    }

    public void setAuctionLineId(Integer auctionLineId) {
        this.auctionLineId = auctionLineId;
    }

    public Integer getAuctionHeaderId() {
        return auctionHeaderId;
    }

    public void setAuctionHeaderId(Integer auctionHeaderId) {
        this.auctionHeaderId = auctionHeaderId;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getDispLineNumber() {
        return dispLineNumber;
    }

    public void setDispLineNumber(String dispLineNumber) {
        this.dispLineNumber = dispLineNumber;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public Integer getParentLineId() {
        return parentLineId;
    }

    public void setParentLineId(Integer parentLineId) {
        this.parentLineId = parentLineId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(Integer indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getIndicatorNumber() {
        return indicatorNumber;
    }

    public void setIndicatorNumber(String indicatorNumber) {
        this.indicatorNumber = indicatorNumber;
    }

    public Integer getIndicatorVersion() {
        return indicatorVersion;
    }

    public void setIndicatorVersion(Integer indicatorVersion) {
        this.indicatorVersion = indicatorVersion;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getLimitPrice() {
        return limitPrice;
    }

    public void setLimitPrice(BigDecimal limitPrice) {
        this.limitPrice = limitPrice;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public Integer getNumberOfWin() {
        return numberOfWin;
    }

    public void setNumberOfWin(Integer numberOfWin) {
        this.numberOfWin = numberOfWin;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public Integer getBidHeaderId() {
        return bidHeaderId;
    }

    public void setBidHeaderId(Integer bidHeaderId) {
        this.bidHeaderId = bidHeaderId;
    }

    public String getBidNumber() {
        return bidNumber;
    }

    public void setBidNumber(String bidNumber) {
        this.bidNumber = bidNumber;
    }

    public String getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(String bidStatus) {
        this.bidStatus = bidStatus;
    }

    public String getAuctionTitle() {
        return auctionTitle;
    }

    public void setAuctionTitle(String auctionTitle) {
        this.auctionTitle = auctionTitle;
    }

    public String getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(String auctionType) {
        this.auctionType = auctionType;
    }

    public String getAuctionTypeName() {
        return auctionTypeName;
    }

    public void setAuctionTypeName(String auctionTypeName) {
        this.auctionTypeName = auctionTypeName;
    }

    public String getAuctionNumber() {
        return auctionNumber;
    }

    public void setAuctionNumber(String auctionNumber) {
        this.auctionNumber = auctionNumber;
    }

    public Date getBidStartDate() {
        return bidStartDate;
    }

    public void setBidStartDate(Date bidStartDate) {
        this.bidStartDate = bidStartDate;
    }

    public Date getBidEndDate() {
        return bidEndDate;
    }

    public void setBidEndDate(Date bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

    public String getBidStatusName() {
        return bidStatusName;
    }

    public void setBidStatusName(String bidStatusName) {
        this.bidStatusName = bidStatusName;
    }

    public Integer getBidLineId() {
        return bidLineId;
    }

    public void setBidLineId(Integer bidLineId) {
        this.bidLineId = bidLineId;
    }

    public String getCurrencyCodeName() {
        return currencyCodeName;
    }

    public void setCurrencyCodeName(String currencyCodeName) {
        this.currencyCodeName = currencyCodeName;
    }

    public String getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(String auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public String getNoteToSupplier() {
        return noteToSupplier;
    }

    public void setNoteToSupplier(String noteToSupplier) {
        this.noteToSupplier = noteToSupplier;
    }

    public String getToSupplierFilePath() {
        return toSupplierFilePath;
    }

    public void setToSupplierFilePath(String toSupplierFilePath) {
        this.toSupplierFilePath = toSupplierFilePath;
    }

    public String getToSupplierFileName() {
        return toSupplierFileName;
    }

    public void setToSupplierFileName(String toSupplierFileName) {
        this.toSupplierFileName = toSupplierFileName;
    }

    public Integer getToSupplierFileId() {
        return toSupplierFileId;
    }

    public void setToSupplierFileId(Integer toSupplierFileId) {
        this.toSupplierFileId = toSupplierFileId;
    }

    public Date getBidBondTerm() {
        return bidBondTerm;
    }

    public void setBidBondTerm(Date bidBondTerm) {
        this.bidBondTerm = bidBondTerm;
    }

    public BigDecimal getBidBond() {
        return bidBond;
    }

    public void setBidBond(BigDecimal bidBond) {
        this.bidBond = bidBond;
    }

    public String getBidBondBankName() {
        return bidBondBankName;
    }

    public void setBidBondBankName(String bidBondBankName) {
        this.bidBondBankName = bidBondBankName;
    }

    public String getBiddingType() {
        return biddingType;
    }

    public void setBiddingType(String biddingType) {
        this.biddingType = biddingType;
    }

    public String getBidBondAccountNumber() {
        return bidBondAccountNumber;
    }

    public void setBidBondAccountNumber(String bidBondAccountNumber) {
        this.bidBondAccountNumber = bidBondAccountNumber;
    }

    public Integer getMaxBidFrequency() {
        return maxBidFrequency;
    }

    public void setMaxBidFrequency(Integer maxBidFrequency) {
        this.maxBidFrequency = maxBidFrequency;
    }

    public String getShowCurrentRoundRanking() {
        return showCurrentRoundRanking;
    }

    public void setShowCurrentRoundRanking(String showCurrentRoundRanking) {
        this.showCurrentRoundRanking = showCurrentRoundRanking;
    }

    public String getMultipleBidFlag() {
        return multipleBidFlag;
    }

    public void setMultipleBidFlag(String multipleBidFlag) {
        this.multipleBidFlag = multipleBidFlag;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getShipToAddress() {
        return shipToAddress;
    }

    public void setShipToAddress(String shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    public String getOpenBiddingStyle() {
        return openBiddingStyle;
    }

    public void setOpenBiddingStyle(String openBiddingStyle) {
        this.openBiddingStyle = openBiddingStyle;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getShowCurrentRoundMinPrice() {
        return showCurrentRoundMinPrice;
    }

    public void setShowCurrentRoundMinPrice(String showCurrentRoundMinPrice) {
        this.showCurrentRoundMinPrice = showCurrentRoundMinPrice;
    }

    public Integer getNumberPriceDecimals() {
        return numberPriceDecimals;
    }

    public void setNumberPriceDecimals(Integer numberPriceDecimals) {
        this.numberPriceDecimals = numberPriceDecimals;
    }

    public String getNoteToAuctionOwner() {
        return noteToAuctionOwner;
    }

    public void setNoteToAuctionOwner(String noteToAuctionOwner) {
        this.noteToAuctionOwner = noteToAuctionOwner;
    }

    public Integer getToOwnerFileId() {
        return toOwnerFileId;
    }

    public void setToOwnerFileId(Integer toOwnerFileId) {
        this.toOwnerFileId = toOwnerFileId;
    }

    public String getToOwnerFileName() {
        return toOwnerFileName;
    }

    public void setToOwnerFileName(String toOwnerFileName) {
        this.toOwnerFileName = toOwnerFileName;
    }

    public String getToOwnerFilePath() {
        return toOwnerFilePath;
    }

    public void setToOwnerFilePath(String toOwnerFilePath) {
        this.toOwnerFilePath = toOwnerFilePath;
    }

    public String getAwardStatus() {
        return awardStatus;
    }

    public void setAwardStatus(String awardStatus) {
        this.awardStatus = awardStatus;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getSupplierContactName() {
        return supplierContactName;
    }

    public void setSupplierContactName(String supplierContactName) {
        this.supplierContactName = supplierContactName;
    }

    public Integer getInviteId() {
        return inviteId;
    }

    public void setInviteId(Integer inviteId) {
        this.inviteId = inviteId;
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

    public String getIsLaunchFlag() {
        return isLaunchFlag;
    }

    public void setIsLaunchFlag(String isLaunchFlag) {
        this.isLaunchFlag = isLaunchFlag;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public String getTransportationLine() {
        return transportationLine;
    }

    public void setTransportationLine(String transportationLine) {
        this.transportationLine = transportationLine;
    }

    public BigDecimal getVolumeFraction() {
        return volumeFraction;
    }

    public void setVolumeFraction(BigDecimal volumeFraction) {
        this.volumeFraction = volumeFraction;
    }

    public String getIsFollowFlag() {
        return isFollowFlag;
    }

    public void setIsFollowFlag(String isFollowFlag) {
        this.isFollowFlag = isFollowFlag;
    }

    public BigDecimal getBestUnitPrice() {
        return bestUnitPrice;
    }

    public void setBestUnitPrice(BigDecimal bestUnitPrice) {
        this.bestUnitPrice = bestUnitPrice;
    }

    public String getInitialPriceRequirement() {
        return initialPriceRequirement;
    }

    public void setInitialPriceRequirement(String initialPriceRequirement) {
        this.initialPriceRequirement = initialPriceRequirement;
    }

    public Integer getLastRound() {
        return lastRound;
    }

    public void setLastRound(Integer lastRound) {
        this.lastRound = lastRound;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public String getAwardStatusName() {
        return awardStatusName;
    }

    public void setAwardStatusName(String awardStatusName) {
        this.awardStatusName = awardStatusName;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public BigDecimal getTimeForArrival() {
        return timeForArrival;
    }

    public String getItemNumber() {return itemNumber;}

    public void setItemNumber(String itemNumber) {this.itemNumber = itemNumber;}

    public void setTimeForArrival(BigDecimal timeForArrival) {
        this.timeForArrival = timeForArrival;
    }

    public String getPaymentConditionUpdateFlag() {
        return paymentConditionUpdateFlag;
    }

    public void setPaymentConditionUpdateFlag(String paymentConditionUpdateFlag) { this.paymentConditionUpdateFlag = paymentConditionUpdateFlag; }

    public String getSubsectionFlag() {
        return subsectionFlag;
    }

    public void setSubsectionFlag(String subsectionFlag) {
        this.subsectionFlag = subsectionFlag;
    }

    public BigDecimal getRoundMinPrice() { return roundMinPrice; }

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserDuty() {
		return userDuty;
	}

	public void setUserDuty(String userDuty) {
		this.userDuty = userDuty;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public void setRoundMinPrice(BigDecimal roundMinPrice) { this.roundMinPrice = roundMinPrice; }

	public String getInvitingBidWay() {
		return invitingBidWay;
	}

	public void setInvitingBidWay(String invitingBidWay) {
		this.invitingBidWay = invitingBidWay;
	}

	public String getOvertFlag() {
		return overtFlag;
	}

	public void setOvertFlag(String overtFlag) {
		this.overtFlag = overtFlag;
	}

	public String getBargainFlag() {
		return bargainFlag;
	}

	public void setBargainFlag(String bargainFlag) {
		this.bargainFlag = bargainFlag;
	}

	public Integer getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
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

    public String getBargainReason() {
        return bargainReason;
    }

    public void setBargainReason(String bargainReason) {
        this.bargainReason = bargainReason;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
