package saaf.common.fmw.pon.model.entities.readonly;

import java.math.BigInteger;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
 * SrmPonAuctionHeadersEntity_HI_RO Entity Object
 * Tue Apr 17 11:14:21 CST 2018  Auto Generate
 */

public class SrmPonAuctionHeadersEntity_HI_RO {

	//获取最大的洽谈轮次编号的后面倒数第一个-之后的编号
	public static final String QUERY_NEWROUNDAUCTIONNUMBER =
					"SELECT to_number(substr(t.auction_round_number,\n" +
					"                  instr(t.auction_round_number, '-', -1) + 1)) auctionRoundNumberBigInteger\n" +
					"FROM   srm_pon_auction_headers t\n" +
					"WHERE  1 = 1\n";

	//查询草稿箱
	 public static final String QUERY_DRAFTS_SQL =
			 		"SELECT ah.auction_header_id       auctionHeaderId\n" +
					 "      ,ah.auction_number          auctionNumber\n" +
					 "      ,ah.auction_title           auctionTitle\n" +
					 "      ,ah.auction_status          auctionStatus\n" +
					 "      ,ah.publish_approval_status publishApprovalStatus\n" +
					 "      ,slv.meaning                typeMean\n" +
					 "      ,ah.auction_type            auctionType\n" +
					 "      ,ah.rounds                  rounds\n" +
					 "      ,ah.auction_round_number    auctionRoundNumber\n" +
					 "      ,ah.bid_start_date          bidStartDate\n" +
					 "      ,ah.bid_end_date            bidEndDate\n" +
					 "FROM   srm_pon_auction_headers ah\n" +
					 "LEFT   JOIN saaf_lookup_values slv\n" +
					 "ON     slv.lookup_type = 'PON_AUCTION_TYPE'\n" +
					 "AND    slv.lookup_code = ah.auction_type\n" +
					 "WHERE  ah.auction_status = 'DRAFT'\n";

	//查询已发布、 已完成、 已截至的招标信息
	  public static final String QUERY_OTHER_STATUS_SQL =
					"SELECT ah.auction_header_id auctionHeaderId\n" +
					"      ,ah.auction_number auctionNumber\n" +
					"      ,ah.auction_title auctionTitle\n" +
					"      ,ah.auction_status auctionStatus\n" +
					"      ,slv.meaning statusMean\n" +
					"      ,ah.rounds rounds\n" +
					"      ,ah.auction_type auctionType\n" +
					"      ,slv2.meaning typeMean\n" +
					"      ,ah.bid_start_date bidStartDate\n" +
					"      ,ah.bid_end_date bidEndDate\n" +
					"      ,ah.auction_round_number auctionRoundNumber\n" +
					"      ,ah.publish_date publishDate\n" +
					"      ,ah.inviting_bid_way invitingBidWay\n" +
					"      ,slv3.meaning invitingBidWayDesc\n" +
					"      ,(SELECT (CASE\n" +
					"                 WHEN COUNT(t.jury_id) > 0 THEN\n" +
					"                  'Y'\n" +
					"                 ELSE\n" +
					"                  'N'\n" +
					"               END) copyFlag\n" +
					"        FROM   (SELECT spaj.jury_id jury_id\n" +
					"                      ,spah.auction_header_id\n" +
					"                FROM   srm_pon_auction_juries  spaj\n" +
					"                      ,srm_pon_auction_headers spah\n" +
					"                WHERE  spaj.auction_header_id = spah.auction_header_id\n" +
					"                AND    spaj.user_id = :varUserId\n" +
					"                AND    spah.auction_type = 'TENDER'\n" +
					"                AND    spaj.user_duty IN (1, 5)\n" +
					"                UNION ALL\n" +
					"                SELECT spaj.jury_id jury_id\n" +
					"                      ,spah.auction_header_id\n" +
					"                FROM   srm_pon_auction_juries  spaj\n" +
					"                      ,srm_pon_auction_headers spah\n" +
					"                WHERE  spaj.auction_header_id = spah.auction_header_id\n" +
					"                AND    spaj.user_id = :varUserId\n" +
					"                AND    spah.auction_type = 'INQUIRY'\n" +
					"                AND    spaj.user_duty IN (1, 3)) t\n" +
					"        WHERE  t.auction_header_id = ah.auction_header_id) copyFlag\n" +
					"      ,(SELECT emp.employee_name\n" +
					"        FROM   srm_pon_auction_juries t\n" +
					"              ,saaf_employees         emp\n" +
					"        WHERE  t.auction_header_id = ah.auction_header_id\n" +
					"        AND    t.employee_id = emp.employee_id\n" +
					"        AND    t.user_duty = 5) juryName\n" +
					"      ,(SELECT COUNT(1)\n" +
					"        FROM   srm_pon_bid_headers pdh\n" +
					"        WHERE  pdh.bid_status = 'ACT'\n" +
					"        AND    pdh.auction_header_id = ah.auction_header_id) actBidCount\n" +
					"FROM   srm_pon_auction_headers ah\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_code = ah.auction_status\n" +
					"AND    slv.lookup_type = 'PON_AUCTION_STATUS'\n" +
					"LEFT   JOIN saaf_lookup_values slv2\n" +
					"ON     slv2.lookup_code = ah.auction_type\n" +
					"AND    slv2.lookup_type = 'PON_AUCTION_TYPE'\n" +
					"LEFT   JOIN saaf_lookup_values slv3\n" +
					"ON     slv3.lookup_code = ah.inviting_bid_way\n" +
					"AND    slv3.lookup_type = 'PON_AUCTION_TENDER_TYPE'\n" +
					"WHERE  1 = 1\n";

	public static final String QUERY_AUCTION_HEADER =
					"SELECT ah.auction_header_id auctionHeaderId\n" +
					"      ,ah.auction_number auctionNumber\n" +
					"      ,ah.auction_title auctionTitle\n" +
					"      ,ah.auction_status auctionStatus\n" +
					"      ,slv.meaning statusMean\n" +
					"      ,ah.rounds rounds\n" +
					"      ,ah.auction_type auctionType\n" +
					"      ,slv2.meaning typeMean\n" +
					"      ,ah.bid_start_date bidStartDate\n" +
					"      ,ah.bid_end_date bidEndDate\n" +
					"      ,ah.auction_round_number auctionRoundNumber\n" +
					"      ,ah.publish_date publishDate1\n" +
					"      ,ah.inviting_bid_way invitingBidWay\n" +
					"      ,slv3.meaning invitingBidWayDesc\n" +
					"      ,(SELECT emp.employee_name\n" +
					"        FROM   srm_pon_auction_juries t\n" +
					"              ,saaf_employees         emp\n" +
					"        WHERE  t.auction_header_id = ah. auction_header_id\n" +
					"        AND    t.employee_id = emp.employee_id\n" +
					"        AND    t. user_duty = 5) juryName\n" +
					"      ,(SELECT COUNT(1)\n" +
					"        FROM   srm_pon_bid_headers pdh\n" +
					"        WHERE  pdh.bid_status = 'SUBMITTED'\n" +
					"        AND    pdh.auction_header_id = ah.auction_header_id) actBidCount\n" +
					"FROM   srm_pon_auction_headers ah\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_type = 'PON_AUCTION_STATUS'\n" +
					"AND    slv.lookup_code = ah.auction_status\n" +
					"LEFT   JOIN saaf_lookup_values slv2\n" +
					"ON     slv2.lookup_type = 'PON_AUCTION_TYPE'\n" +
					"AND    slv2.lookup_code = ah.auction_type\n" +
					"LEFT   JOIN saaf_lookup_values slv3\n" +
					"ON     slv3.lookup_type = 'PON_AUCTION_TENDER_TYPE'\n" +
					"AND    slv3.lookup_code = ah. inviting_bid_way\n" +
					"WHERE  1 = 1\n";

	public static final String QUERY_BID_SUPPLIER_SQL =
					"SELECT bip.bid_line_id bidLineId\n" +
					"      ,pbh.supplier_name supplierName\n" +
					"      ,pbh.supplier_id supplierId\n" +
					"      ,pbh.bid_header_id bidHeaderId\n" +
					"      ,lv.meaning supplierStatus\n" +
					"      ,bip.tax_price taxPrice\n" +
					"      ,bip.award_proportion awardProportion\n" +
					"      ,bip.award_status awardStatus\n" +
					"      ,bip.version_num versionNum\n" +
					"      ,bip.auction_line_id auctionLineId\n" +
					"      ,(nvl(bip.tax_price, 0) * nvl(pai.quantity, 0)) sumPrice\n" +
					"FROM   srm_pon_bid_item_prices bip\n" +
					"LEFT   JOIN srm_pon_bid_headers pbh\n" +
					"ON     pbh.bid_header_id = bip.bid_header_id\n" +
					"LEFT   JOIN srm_pon_auction_items pai\n" +
					"ON     pai.auction_line_id = bip.auction_line_id\n" +
					"LEFT   JOIN srm_pos_supplier_info psi\n" +
					"ON     psi.supplier_id = pbh.supplier_id\n" +
					"LEFT   JOIN saaf_lookup_values lv\n" +
					"ON     lv.lookup_type = 'POS_SUPPLIER_INFO_STATUS'\n" +
					"AND    lv.lookup_code = psi.supplier_status\n" +
					"WHERE  pbh.bid_status = 'SUBMITTED'\n";

	public static final String QUERY_AUCTION_ROUND_NUMBERS =
					"SELECT t.auction_round_number AS auctionRoundNumber\n" +
					"      ,t.auction_header_id    AS auctionHeaderId\n" +
					"FROM   srm_pon_auction_headers t\n" +
					"WHERE  1 = 1\n" +
					"AND    t.auction_number = ?\n";

	private Integer auctionHeaderId; //洽谈ID
	private String auctionNumber; //洽谈编号
	private String auctionTitle; //洽谈标题
	private Integer orgId; //洽谈主体单位，关联表：SAAF_INSTITUTION
	private String auctionType; //洽谈类型，快码：PON_AUCTION_TYPE
	private String contractType; //合同类型（招标结果），快码：PON_CONTRACT_TYPE
	private String auctionStatus; //洽谈状态，快码：PON_AUCTION_STATUS
	private Integer buyerId; //采购员，关联表：SAAF_EMPLOYEES 的 EMPLOYEE_ID
	private String invitingBidWay; //邀标方式
	private Integer receiveToOrganizationId; //收货组织ID
	private String receiveToAddress; //收货/收单地址
	private String paymentCondition; //付款条件
	private String paymentConditionUpdateFlag; //允许供应商修改付款条件（Y/N）
	private String subsectionFlag; //分段价格（Y/N）
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date bidStartDate; //报价开始时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date bidEndDate; //报价截止时间
	private String currencyCode; //币种，关联表：SAAF_LOOKUP_VALUES（BANK_CURRENCY）
	private Integer numberPriceDecimals; //报价的小数位
	private String allowUpdateTaxRate; //允许修改税率（Y/N）
	private String showCurrentRoundMinPrice; //向供应商展示本轮最低价格（Y/N）
	private String showCurrentRoundRanking; //向供应商展示本轮排名（Y/N）
	private String allItemBidFlag; //供应商必须回应所有行（Y/N）
	private String multipleBidFlag; //允许多次报价（Y/N）
	private Integer maxBidFrequency; //多次报价次数
	private BigDecimal minDecreasingRange; //最小降幅
	private BigDecimal bidBond; //保证金金额
	@JSONField(format = "yyyy-MM-dd")
	private Date bidBondTerm; //保证金缴纳期限
	private String bidBondAccountNumber; //保证金缴纳账户
	private String bidBondBankName; //保证金缴纳账户开户行
	private BigDecimal tenderCost; //标书费用
	private String publishApprovalStatus; //发布审批状态
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date publishApprovalDate; //发布批准时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date publishDate; //发布时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date closeBiddingDate; //截标时间
	private String noteToSupplier; //给供应商的附注
	private String noteToJury; //给评标小组的附注
	private Integer toSupplierFileId; //给供应商的附件ID
	private Integer toJuryFileId; //给评标小组的附件ID
	private String allowScoreFlag;//是否可评分标识（Y/N）
	private Integer rounds; //第几轮
	private Integer firstRound; //第一轮ID
	private Integer lastRound; //上一轮ID
	private String auctionRoundNumber; //洽谈轮次编号
	private String openBiddingFlag; //是否开标（Y/N）
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date openBiddingDate; //开标时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date judgeCompleteDate; //评标完成时间
	private String awardStatus; //决标状态
	private String awardApprovalStatus; //决标审批状态
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date awardCompleteDate; //决标完成时间
	private String awardComments; //决标说明
	private String poCreateFlag; //是否已创建采购订单（Y/N）
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date poCreateDate; //采购订单创建时间
	private Integer versionNum;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;
	private Integer createdBy;
	private Integer lastUpdatedBy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;
	private Integer lastUpdateLogin;
	private Integer count;
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

	private String auctionCode;
	private Integer actBidCount;
	private Integer templateId;
	private String statusMean;
	private String typeMean;
	private String templateCode;
	private String templateName;
	private String invitingBidWayDesc;
	private String juryName;
	private String copyFlag;
	private String onlyChartFlag;
	@JSONField(format = "yyyy-MM-dd")
	private Date publishDate1;
    private BigDecimal percent;
    private String okcCreateFlag; //是否已创建合同（Y/N）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date okcCreateDate; //合同创建时间
    private Integer bidHeaderId;


    
	
	//查询头
	public static final String QUERY_AUCTION_HEADHER_INFO_SQL =
					"SELECT pah.auction_header_id AS auctionHeaderId\n" +
					"      ,pah.auction_number AS auctionNumber\n" +
					"      ,pah.template_id AS templateId\n" +
					"      ,pah.template_code AS templateCode\n" +
					"      ,pah.template_name AS templateName\n" +
					"      ,pah.auction_title AS auctionTitle\n" +
					"      ,pah.org_id AS orgId\n" +
                    "      ,pah.organization_id AS organizationId\n" +
					"      ,pah.auction_type AS auctionType\n" +
					"      ,pah.contract_type AS contractType\n" +
					"      ,pah.auction_status AS auctionStatus\n" +
					"      ,slv3.meaning AS auctionStatusName\n" +
					"      ,pah.buyer_id AS buyerId\n" +
					"      ,pah.inviting_bid_way AS invitingBidWay\n" +
					"      ,pah.receive_to_organization_id AS receiveToOrganizationId\n" +
					"      ,pah.receive_to_address AS receiveToAddress\n" +
					"      ,sppt.payment_term_name AS paymentTermName\n" +
                    "      ,sppt.payment_term_id AS paymentTermId\n" +
					"      ,pah.payment_condition AS paymentCondition\n" +
					"      ,pah.payment_condition_update_flag AS paymentConditionUpdateFlag\n" +
					"      ,pah.subsection_flag AS subsectionFlag\n" +
					"      ,pah.bid_start_date AS bidStartDate\n" +
					"      ,pah.bid_end_date AS bidEndDate\n" +
					"      ,pah.currency_code AS currencyCode\n" +
					"      ,pah.number_price_decimals AS numberPriceDecimals\n" +
					"      ,pah.allow_update_tax_rate AS allowUpdateTaxRate\n" +
					"      ,pah.show_current_round_min_price AS showCurrentRoundMinPrice\n" +
					"      ,pah.show_current_round_ranking AS showCurrentRoundRanking\n" +
					"      ,pah.all_item_bid_flag AS allItemBidFlag\n" +
					"      ,pah.multiple_bid_flag AS multipleBidFlag\n" +
					"      ,pah.max_bid_frequency AS maxBidFrequency\n" +
					"      ,pah.min_decreasing_range AS minDecreasingRange\n" +
					"      ,pah.bid_bond AS bidBond\n" +
					"      ,pah.bid_bond_term AS bidBondTerm\n" +
					"      ,pah.bid_bond_account_number AS bidBondAccountNumber\n" +
					"      ,pah.bid_bond_bank_name AS bidBondBankName\n" +
					"      ,pah.tender_cost AS tenderCost\n" +
					"      ,pah.publish_approval_status AS publishApprovalStatus\n" +
					"      ,pah.publish_approval_date AS publishApprovalDate\n" +
					"      ,pah.publish_date AS publishDate\n" +
					"      ,pah.close_bidding_date AS closeBiddingDate\n" +
					"      ,pah.note_to_supplier AS noteToSupplier\n" +
					"      ,pah.note_to_jury AS noteToJury\n" +
					"      ,pah.to_supplier_file_id AS toSupplierFileId\n" +
					"      ,pah.to_jury_file_id AS toJuryFileId\n" +
					"      ,pah.allow_score_flag AS allowScoreFlag\n" +
					"      ,pah.rounds AS rounds\n" +
					"      ,pah.first_round AS firstRound\n" +
					"      ,pah.last_round AS lastRound\n" +
					"      ,pah.auction_round_number AS auctionRoundNumber\n" +
					"      ,pah.open_bidding_flag AS openBiddingFlag\n" +
					"      ,pah.open_bidding_date AS openBiddingDate\n" +
					"      ,pah.judge_complete_date AS judgeCompleteDate\n" +
					"      ,pah.award_status AS awardStatus\n" +
					"      ,pah.award_approval_status AS awardApprovalStatus\n" +
					"      ,pah.award_complete_date AS awardCompleteDate\n" +
					"      ,pah.award_comments AS awardComments\n" +
					"      ,pah.po_create_flag AS poCreateFlag\n" +
					"      ,pah.po_create_date AS poCreateDate\n" +
                    "      ,pah.okc_create_flag AS okcCreateFlag\n" +
                    "      ,pah.okc_create_date AS okcCreateDate\n" +
					"      ,pah.version_num AS versionNum\n" +
					"      ,pah.creation_date AS creationDate\n" +
					"      ,pah.created_by AS createdBy\n" +
					"      ,pah.last_updated_by AS lastUpdatedBy\n" +
					"      ,pah.last_update_date AS lastUpdateDate\n" +
					"      ,pah.last_update_login AS lastUpdateLogin\n" +
					"      ,pah.attribute_category AS attributeCategory\n" +
					"      ,pah.attribute1 AS attribute1\n" +
					"      ,pah.attribute2 AS attribute2\n" +
					"      ,pah.attribute3 AS attribute3\n" +
					"      ,pah.attribute4 AS attribute4\n" +
					"      ,pah.attribute5 AS attribute5\n" +
					"      ,pah.attribute6 AS attribute6\n" +
					"      ,pah.attribute7 AS attribute7\n" +
					"      ,pah.attribute8 AS attribute8\n" +
					"      ,pah.attribute9 AS attribute9\n" +
					"      ,pah.attribute10 AS attribute10\n" +
					"      ,pah.item_type AS itemType\n" +
					"      ,pah.ekp_number AS ekpNumber\n" +
                    "      ,pah.percent AS percent\n" +
					"      ,rf1.access_path AS supplierAccessPath\n" +
					"      ,rf1.file_name AS supplierFileName\n" +
					"      ,rf2.access_path AS juryAccessPath\n" +
					"      ,rf2.file_name AS juryFileName\n" +
					"      ,si.inst_name AS instName\n" +
					"      ,si1.inst_name AS receiveToOrganizationName\n" +
                    "      ,si2.inst_name AS organizationName\n" +
					"      ,slv.meaning AS currencyCodeName\n" +
					"      ,emp1.employee_name AS employeeName\n" +
					"      ,slv4.meaning AS publishApprovalStatusName\n" +
					"      ,slv5.meaning AS awardStatusName\n" +
					"      ,slv6.meaning AS awardApprovalStatusName\n" +
					"      ,slv7.meaning AS contractTypeName\n" +
					"      ,slv8.meaning AS invitingBidWayName\n" +
                    /*"      ,pah.tax_rate_code AS taxRateCode\n" +
                    "      ,slv9.meaning AS taxRateName\n" +*/
					"      ,(SELECT (CASE\n" +
					"                 WHEN COUNT(s.jury_id) > 0 THEN\n" +
					"                  'N'\n" +
					"                 ELSE\n" +
					"                  'Y'\n" +
					"               END) copyflag\n" +
					"        FROM   (SELECT spaj.jury_id jury_id\n" +
					"                      ,spah.auction_header_id\n" +
					"                FROM   srm_pon_auction_juries  spaj\n" +
					"                      ,srm_pon_auction_headers spah\n" +
					"                WHERE  spaj.auction_header_id = spah.auction_header_id\n" +
					"                AND    spaj.user_id = :varUserId\n" +
					"                AND    spah.auction_type = 'TENDER'\n" +
					"                AND    spaj.user_duty = 5\n" +
					"                UNION ALL\n" +
					"                SELECT spaj.jury_id jury_id\n" +
					"                      ,spah.auction_header_id\n" +
					"                FROM   srm_pon_auction_juries  spaj\n" +
					"                      ,srm_pon_auction_headers spah\n" +
					"                WHERE  spaj.auction_header_id = spah.auction_header_id\n" +
					"                AND    spaj.user_id = :varUserId\n" +
					"                AND    spah.auction_type = 'INQUIRY'\n" +
					"                AND    spaj.user_duty = 3) s\n" +
					"        WHERE  s.auction_header_id = pah.auction_header_id) onlyChartFlag\n" +
					"FROM   srm_pon_auction_headers pah\n" +
					"LEFT   JOIN saaf_employees emp1\n" +
					"ON     emp1.employee_id = pah.buyer_id\n" +
					"LEFT   JOIN saaf_lookup_values slv3\n" +
					"ON     slv3.lookup_code = pah.auction_status\n" +
					"AND    slv3.lookup_type = 'PON_AUCTION_STATUS'\n" +
					"LEFT   JOIN srm_pon_payment_terms sppt\n" +
					"ON     sppt.payment_term_code = pah.payment_condition\n" +
					"LEFT   JOIN saaf_base_result_file rf1\n" +
					"ON     rf1.file_id = pah.to_supplier_file_id\n" +
					"LEFT   JOIN saaf_base_result_file rf2\n" +
					"ON     rf2.file_id = pah.to_jury_file_id\n" +
					"LEFT   JOIN saaf_institution si\n" +
					"ON     si.inst_id = pah.org_id\n" +
					"LEFT   JOIN saaf_institution si1\n" +
					"ON     si1.inst_id = pah.receive_to_organization_id\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_code = pah.currency_code\n" +
					"AND    slv.lookup_type = 'BANK_CURRENCY'\n" +
					"LEFT   JOIN saaf_lookup_values slv4\n" +
					"ON     slv4.lookup_code = pah.publish_approval_status\n" +
					"AND    slv4.lookup_type = 'PON_APPROVAL_STATUS'\n" +
					"LEFT   JOIN saaf_lookup_values slv5\n" +
					"ON     slv5.lookup_code = pah.award_status\n" +
					"AND    slv5.lookup_type = 'PON_AWARD_STATUS'\n" +
					"LEFT   JOIN saaf_lookup_values slv6\n" +
					"ON     slv6.lookup_code = pah.award_approval_status\n" +
					"AND    slv6.lookup_type = 'PON_APPROVAL_STATUS'\n" +
					"LEFT   JOIN saaf_lookup_values slv7\n" +
					"ON     slv7.lookup_code = pah.contract_type\n" +
					"AND    slv7.lookup_type = 'PON_AUCTION_RESULT'\n" +
					"LEFT   JOIN saaf_lookup_values slv8\n" +
					"ON     slv8.lookup_code = pah.inviting_bid_way\n" +
					"AND    slv8.lookup_type = 'PON_AUCTION_TENDER_TYPE'\n" +
                     "LEFT   JOIN saaf_institution si2\n" +
                     "ON     si2.inst_id = pah.organization_id\n" +
                    /*"LEFT   JOIN saaf_lookup_values slv9\n" +
                    "ON     slv9.lookup_code = pah.tax_rate_code\n" +
                    "AND    slv9.lookup_type = 'PON_TAX_LIST'\n" +*/
					"WHERE  pah.auction_header_id = :auctionHeaderId\n";

	public static final String QUERY_USER_AUTHORIZTION_SQL =
			 		"SELECT paj.user_duty userDuty\n" +
					 "FROM   srm_pon_auction_juries paj\n" +
					 "WHERE  paj.user_id = ?\n" +
					 "AND    paj.auction_header_id = ?\n" +
					 "ORDER  BY paj.user_duty DESC\n";

    public static final String QUERY_INST_REGION="SELECT Siv.Inst_Id        AS Instid\n" +
            "      ,Siv.Inst_Code      AS Instcode\n" +
            "      ,Siv.Inst_Region\n" +
            "  FROM Saaf_Institution Siv\n" +
            " WHERE (Siv.Inst_Type = 'ORG' OR Siv.Inst_Type = 'ORGANIZATION')\n" +
            "   AND Siv.Platform_Code LIKE '%SAAF%' ";

    public static final String QUERY_AUCTIONINFO_FROMEKP="SELECT Spah.Auction_Header_Id\n" +
            "      ,Spah.Auction_Number\n" +
            "      ,Spah.Auction_Title\n" +
            "      ,spah.award_approval_status\n" +
            "      ,Spah.Attribute1\n" +
            "      ,Spah.ekp_number\n" +
            "      ,Spah.item_type\n" +
            "      ,Spah.org_id\n" +
            "  FROM Srm_Pon_Auction_Headers Spah\n" +
            "  WHERE spah.award_approval_status IN ('APPROVING','REJECT')\n"+
            "  and spah.item_Type IN ('INFORMATION_TECHNOLOGY','ENGINEERING')\n";;



    public static  final  String QUERY_EKP_NUMBER="SELECT Spah.Auction_Number\n" +
            "      ,Spah.Auction_Status\n" +
            "      ,Spah.auction_Header_Id\n" +
            "  FROM Srm_Pon_Auction_Headers Spah\n" +
            " WHERE Spah.Auction_Header_Id NOT IN\n" +
            "       (SELECT Pah.Last_Round AS Auction_Header_Id\n" +
            "          FROM Srm_Pon_Auction_Headers Pah\n" +
            "         WHERE Pah.Auction_Number = Spah.Auction_Number and Pah.Last_Round is not null) \n"+
            " and spah.award_approval_status IN ('APPROVING','REJECT') ";

	private String supplierAccessPath;
	private String supplierFileName;
	private String juryAccessPath;
	private String juryFileName;
	private String instName;
	private String currencyCodeName;
	private String employeeName;
	private String receiveToOrganizationName;//收货组织别名
	private String auctionStatusName; //洽谈状态别名
	private String publishApprovalStatusName;//洽谈发布审批状态别名
	private String awardStatusName; //决标状态别名
	private String awardApprovalStatusName;//决标审批状态别名
	private String contractTypeName;//招标结果别名
	private String invitingBidWayName;//邀标方式别名
	private String paymentTermName; //付款条件名称
	private String itemType;//询价物料类型
    private String itemTypeName;//询价物料类型
	private String ekpNumber;//EKP编号
    private String taxRateCode;
    private String taxRateName;
    private String itemCode;
	
	private String userDuty;
	private String instCode;
    private String instRegion;
    private Integer paymentTermId;
    private BigDecimal totalAmount;
    private Integer supplierId;
    private String supplierName;
    private String supplierNumber;
    private Integer organizationId;
    private String organizationName;

    public Integer getBidHeaderId() {
        return bidHeaderId;
    }

    public void setBidHeaderId(Integer bidHeaderId) {
        this.bidHeaderId = bidHeaderId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
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

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getPaymentTermId() {
        return paymentTermId;
    }

    public void setPaymentTermId(Integer paymentTermId) {
        this.paymentTermId = paymentTermId;
    }

    public String getInstRegion() {
        return instRegion;
    }

    public void setInstRegion(String instRegion) {
        this.instRegion = instRegion;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
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

    public String getPaymentTermName() {
		return paymentTermName;
	}

	public void setPaymentTermName(String paymentTermName) {
		this.paymentTermName = paymentTermName;
	}

	public String getPublishApprovalStatusName() {
		return publishApprovalStatusName;
	}

	public void setPublishApprovalStatusName(String publishApprovalStatusName) {
		this.publishApprovalStatusName = publishApprovalStatusName;
	}

	public String getAwardStatusName() {
		return awardStatusName;
	}

	public void setAwardStatusName(String awardStatusName) {
		this.awardStatusName = awardStatusName;
	}

	public String getAwardApprovalStatusName() {
		return awardApprovalStatusName;
	}

	public void setAwardApprovalStatusName(String awardApprovalStatusName) {
		this.awardApprovalStatusName = awardApprovalStatusName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAuctionStatusName() {
		return auctionStatusName;
	}

	public void setAuctionStatusName(String auctionStatusName) {
		this.auctionStatusName = auctionStatusName;
	}

	public String getReceiveToOrganizationName() {
		return receiveToOrganizationName;
	}

	public void setReceiveToOrganizationName(String receiveToOrganizationName) {
		this.receiveToOrganizationName = receiveToOrganizationName;
	}

	public String getSupplierAccessPath() {
		return supplierAccessPath;
	}

	public void setSupplierAccessPath(String supplierAccessPath) {
		this.supplierAccessPath = supplierAccessPath;
	}

	public String getSupplierFileName() {
		return supplierFileName;
	}

	public void setSupplierFileName(String supplierFileName) {
		this.supplierFileName = supplierFileName;
	}

	public String getJuryAccessPath() {
		return juryAccessPath;
	}

	public void setJuryAccessPath(String juryAccessPath) {
		this.juryAccessPath = juryAccessPath;
	}

	public String getJuryFileName() {
		return juryFileName;
	}

	public void setJuryFileName(String juryFileName) {
		this.juryFileName = juryFileName;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getCurrencyCodeName() {
		return currencyCodeName;
	}

	public void setCurrencyCodeName(String currencyCodeName) {
		this.currencyCodeName = currencyCodeName;
	}

    private BigInteger auctionRoundNumberBigInteger;//洽谈轮次编号的BigInteger类型

    public BigInteger getAuctionRoundNumberBigInteger() {
        return auctionRoundNumberBigInteger;
    }

    public void setAuctionRoundNumberBigInteger(BigInteger auctionRoundNumberBigInteger) {
        this.auctionRoundNumberBigInteger = auctionRoundNumberBigInteger;
    }

    public void setAuctionHeaderId(Integer auctionHeaderId) {
		this.auctionHeaderId = auctionHeaderId;
	}


	public Integer getAuctionHeaderId() {
		return auctionHeaderId;
	}

	public void setAuctionNumber(String auctionNumber) {
		this.auctionNumber = auctionNumber;
	}


	public String getAuctionNumber() {
		return auctionNumber;
	}

	public void setAuctionTitle(String auctionTitle) {
		this.auctionTitle = auctionTitle;
	}


	public String getAuctionTitle() {
		return auctionTitle;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public Integer getOrgId() {
		return orgId;
	}

	public void setAuctionType(String auctionType) {
		this.auctionType = auctionType;
	}


	public String getAuctionType() {
		return auctionType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}


	public String getContractType() {
		return contractType;
	}

	public void setAuctionStatus(String auctionStatus) {
		this.auctionStatus = auctionStatus;
	}


	public String getAuctionStatus() {
		return auctionStatus;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}


	public Integer getBuyerId() {
		return buyerId;
	}

	public void setInvitingBidWay(String invitingBidWay) {
		this.invitingBidWay = invitingBidWay;
	}


	public String getInvitingBidWay() {
		return invitingBidWay;
	}

	public void setReceiveToOrganizationId(Integer receiveToOrganizationId) {
		this.receiveToOrganizationId = receiveToOrganizationId;
	}


	public Integer getReceiveToOrganizationId() {
		return receiveToOrganizationId;
	}

	public void setReceiveToAddress(String receiveToAddress) {
		this.receiveToAddress = receiveToAddress;
	}


	public String getReceiveToAddress() {
		return receiveToAddress;
	}

	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}


	public String getPaymentCondition() {
		return paymentCondition;
	}

	public void setPaymentConditionUpdateFlag(String paymentConditionUpdateFlag) {
		this.paymentConditionUpdateFlag = paymentConditionUpdateFlag;
	}


	public String getPaymentConditionUpdateFlag() {
		return paymentConditionUpdateFlag;
	}

	public void setSubsectionFlag(String subsectionFlag) {
		this.subsectionFlag = subsectionFlag;
	}


	public String getSubsectionFlag() {
		return subsectionFlag;
	}

	public void setBidStartDate(Date bidStartDate) {
		this.bidStartDate = bidStartDate;
	}


	public Date getBidStartDate() {
		return bidStartDate;
	}

	public void setBidEndDate(Date bidEndDate) {
		this.bidEndDate = bidEndDate;
	}


	public Date getBidEndDate() {
		return bidEndDate;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setNumberPriceDecimals(Integer numberPriceDecimals) {
		this.numberPriceDecimals = numberPriceDecimals;
	}


	public Integer getNumberPriceDecimals() {
		return numberPriceDecimals;
	}

	public void setAllowUpdateTaxRate(String allowUpdateTaxRate) {
		this.allowUpdateTaxRate = allowUpdateTaxRate;
	}


	public String getAllowUpdateTaxRate() {
		return allowUpdateTaxRate;
	}

	public void setShowCurrentRoundMinPrice(String showCurrentRoundMinPrice) {
		this.showCurrentRoundMinPrice = showCurrentRoundMinPrice;
	}


	public String getShowCurrentRoundMinPrice() {
		return showCurrentRoundMinPrice;
	}

	public void setShowCurrentRoundRanking(String showCurrentRoundRanking) {
		this.showCurrentRoundRanking = showCurrentRoundRanking;
	}


	public String getShowCurrentRoundRanking() {
		return showCurrentRoundRanking;
	}

	public void setAllItemBidFlag(String allItemBidFlag) {
		this.allItemBidFlag = allItemBidFlag;
	}


	public String getAllItemBidFlag() {
		return allItemBidFlag;
	}

	public void setMultipleBidFlag(String multipleBidFlag) {
		this.multipleBidFlag = multipleBidFlag;
	}


	public String getMultipleBidFlag() {
		return multipleBidFlag;
	}

	public void setMaxBidFrequency(Integer maxBidFrequency) {
		this.maxBidFrequency = maxBidFrequency;
	}


	public Integer getMaxBidFrequency() {
		return maxBidFrequency;
	}

	public void setMinDecreasingRange(BigDecimal minDecreasingRange) {
		this.minDecreasingRange = minDecreasingRange;
	}


	public BigDecimal getMinDecreasingRange() {
		return minDecreasingRange;
	}

	public void setBidBond(BigDecimal bidBond) {
		this.bidBond = bidBond;
	}


	public BigDecimal getBidBond() {
		return bidBond;
	}

	public void setBidBondTerm(Date bidBondTerm) {
		this.bidBondTerm = bidBondTerm;
	}


	public Date getBidBondTerm() {
		return bidBondTerm;
	}

	public void setBidBondAccountNumber(String bidBondAccountNumber) {
		this.bidBondAccountNumber = bidBondAccountNumber;
	}


	public String getBidBondAccountNumber() {
		return bidBondAccountNumber;
	}

	public void setBidBondBankName(String bidBondBankName) {
		this.bidBondBankName = bidBondBankName;
	}


	public String getBidBondBankName() {
		return bidBondBankName;
	}

	public void setTenderCost(BigDecimal tenderCost) {
		this.tenderCost = tenderCost;
	}


	public BigDecimal getTenderCost() {
		return tenderCost;
	}

	public void setPublishApprovalStatus(String publishApprovalStatus) {
		this.publishApprovalStatus = publishApprovalStatus;
	}


	public String getPublishApprovalStatus() {
		return publishApprovalStatus;
	}

	public void setPublishApprovalDate(Date publishApprovalDate) {
		this.publishApprovalDate = publishApprovalDate;
	}


	public Date getPublishApprovalDate() {
		return publishApprovalDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}


	public Date getPublishDate() {
		return publishDate;
	}

	public void setCloseBiddingDate(Date closeBiddingDate) {
		this.closeBiddingDate = closeBiddingDate;
	}


	public Date getCloseBiddingDate() {
		return closeBiddingDate;
	}

	public void setNoteToSupplier(String noteToSupplier) {
		this.noteToSupplier = noteToSupplier;
	}


	public String getNoteToSupplier() {
		return noteToSupplier;
	}

	public void setNoteToJury(String noteToJury) {
		this.noteToJury = noteToJury;
	}


	public String getNoteToJury() {
		return noteToJury;
	}

	public void setToSupplierFileId(Integer toSupplierFileId) {
		this.toSupplierFileId = toSupplierFileId;
	}


	public Integer getToSupplierFileId() {
		return toSupplierFileId;
	}

	public void setToJuryFileId(Integer toJuryFileId) {
		this.toJuryFileId = toJuryFileId;
	}


	public Integer getToJuryFileId() {
		return toJuryFileId;
	}

	public void setRounds(Integer rounds) {
		this.rounds = rounds;
	}

	public String getAllowScoreFlag() {
		return allowScoreFlag;
	}

	public void setAllowScoreFlag(String allowScoreFlag) {
		this.allowScoreFlag = allowScoreFlag;
	}

	public Integer getRounds() {
		return rounds;
	}

	public void setFirstRound(Integer firstRound) {
		this.firstRound = firstRound;
	}


	public Integer getFirstRound() {
		return firstRound;
	}

	public void setLastRound(Integer lastRound) {
		this.lastRound = lastRound;
	}


	public Integer getLastRound() {
		return lastRound;
	}

	public void setAuctionRoundNumber(String auctionRoundNumber) {
		this.auctionRoundNumber = auctionRoundNumber;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getAuctionRoundNumber() {
		return auctionRoundNumber;
	}

	public void setOpenBiddingFlag(String openBiddingFlag) {
		this.openBiddingFlag = openBiddingFlag;
	}


	public String getOpenBiddingFlag() {
		return openBiddingFlag;
	}

	public void setOpenBiddingDate(Date openBiddingDate) {
		this.openBiddingDate = openBiddingDate;
	}


	public Date getOpenBiddingDate() {
		return openBiddingDate;
	}

	public void setJudgeCompleteDate(Date judgeCompleteDate) {
		this.judgeCompleteDate = judgeCompleteDate;
	}


	public Date getJudgeCompleteDate() {
		return judgeCompleteDate;
	}

	public void setAwardStatus(String awardStatus) {
		this.awardStatus = awardStatus;
	}


	public String getAwardStatus() {
		return awardStatus;
	}

	public void setAwardApprovalStatus(String awardApprovalStatus) {
		this.awardApprovalStatus = awardApprovalStatus;
	}


	public String getAwardApprovalStatus() {
		return awardApprovalStatus;
	}

	public void setAwardCompleteDate(Date awardCompleteDate) {
		this.awardCompleteDate = awardCompleteDate;
	}


	public Date getAwardCompleteDate() {
		return awardCompleteDate;
	}

	public void setAwardComments(String awardComments) {
		this.awardComments = awardComments;
	}


	public String getAwardComments() {
		return awardComments;
	}

	public void setPoCreateFlag(String poCreateFlag) {
		this.poCreateFlag = poCreateFlag;
	}


	public String getPoCreateFlag() {
		return poCreateFlag;
	}

	public void setPoCreateDate(Date poCreateDate) {
		this.poCreateDate = poCreateDate;
	}


	public Date getPoCreateDate() {
		return poCreateDate;
	}


    public String getOkcCreateFlag() {
        return okcCreateFlag;
    }

    public void setOkcCreateFlag(String okcCreateFlag) {
        this.okcCreateFlag = okcCreateFlag;
    }

    public Date getOkcCreateDate() {
        return okcCreateDate;
    }

    public void setOkcCreateDate(Date okcCreateDate) {
        this.okcCreateDate = okcCreateDate;
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

	public String getAuctionCode() { return auctionCode; }

	public void setAuctionCode(String auctionCode) { this.auctionCode = auctionCode; }

	public Integer getActBidCount() { return actBidCount; }

	public void setActBidCount(Integer actBidCount) { this.actBidCount = actBidCount; }

	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public String getStatusMean() {
		return statusMean;
	}

	public void setStatusMean(String statusMean) {
		this.statusMean = statusMean;
	}

	public String getTypeMean() {
		return typeMean;
	}

	public void setTypeMean(String typeMean) {
		this.typeMean = typeMean;
	}

	public String getContractTypeName() {
		return contractTypeName;
	}

	public void setContractTypeName(String contractTypeName) {
		this.contractTypeName = contractTypeName;
	}

	public String getInvitingBidWayName() {
		return invitingBidWayName;
	}

	public void setInvitingBidWayName(String invitingBidWayName) {
		this.invitingBidWayName = invitingBidWayName;
	}

	public String getUserDuty() {
		return userDuty;
	}

	public void setUserDuty(String userDuty) {
		this.userDuty = userDuty;
	}

	public String getInvitingBidWayDesc() {
		return invitingBidWayDesc;
	}

	public void setInvitingBidWayDesc(String invitingBidWayDesc) {
		this.invitingBidWayDesc = invitingBidWayDesc;
	}

	public String getJuryName() {
		return juryName;
	}

	public void setJuryName(String juryName) {
		this.juryName = juryName;
	}

	public Date getPublishDate1() {
		return publishDate1;
	}

	public void setPublishDate1(Date publishDate1) {
		this.publishDate1 = publishDate1;
	}

	public String getCopyFlag() { return copyFlag; }

	public void setCopyFlag(String copyFlag) { this.copyFlag = copyFlag; }

    public String getOnlyChartFlag() { return onlyChartFlag; }

    public void setOnlyChartFlag(String onlyChartFlag) { this.onlyChartFlag = onlyChartFlag; }

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getEkpNumber() {
		return ekpNumber;
	}

	public void setEkpNumber(String ekpNumber) {
		this.ekpNumber = ekpNumber;
	}
}
