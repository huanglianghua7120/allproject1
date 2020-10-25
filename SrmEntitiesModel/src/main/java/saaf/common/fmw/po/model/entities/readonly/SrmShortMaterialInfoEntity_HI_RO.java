package saaf.common.fmw.po.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmShortMaterialInfoEntity_HI_RO implements Serializable{
	private static final long serialVersionUID = 598162915151178014L;

	public static String QUERY_SQL= " SELECT a.CREATION_DATE as checkdate, "
		                          + " a.inst_id,  "
		                          + " a.inst_name moorg, "
		                          + " '' AS docno, "
		                          + " a.itemcode, "
		                          + " a.itemid, "
		                          + " a.itemName, "
		                          + " a.category_Code categoryCode , "
		                          + " a.category_Name categoryName, "   
		                       //   + " date_format(a.mostartdate,'%Y-%m-%d') mostartdate, "
		                          + " a.calcdate, "
		                       //   + " round(sum(a.ACTQTY),5) actqty,"
		                      //    + " round(sum(a.ISSUEDQTY),5) issuedqty, "
		                          + " round(a.TOTAL_ACTQTY,5) actqty, "
		                          + " round(a.TOTAL_ISSUEDQTY,5) issuedqty , "
		                          + " round(Ifnull(a.TOTAL_ACTQTY,0) - Ifnull(a.TOTAL_ISSUEDQTY,0) ,5) orderNoSendQty , "
		                          + " a.invqty, "
		                          + " a.midqty, "
		                          
		                          + " case when round(a.PurchaseBatchQty) = 0 "
		                          + " then Sum(if(a.SHORTQTY - a.SHORTQTY*2<0,0,a.SHORTQTY - a.SHORTQTY*2 )) "
		                          + " else ceil(Sum(if(a.SHORTQTY - a.SHORTQTY*2<0,0,a.SHORTQTY - a.SHORTQTY*2 ))) end qty, "    // 超量的都按照0显示	                          
		                          + " '' doctype , "
		                          + " a.isholdrelease, "
		                          + " a.docstate, "
		                          + " a.seibancode , "
		                          + " a.unit"
		                          + " FROM SRM_SHORT_MATERIAL_INV_INFO  a where 1=1 " ;
	
	public static String WARNING_QUERY_SQL= " select t.CREATION_DATE AS checkDate , "
			                                     + " t.inst_id, "
			                                     + " t.moorg  , "
			                                     + " t.itemcode , "
			                                     + " t.itemId, "
			                                     + " t.itemName, "
			                                     + " t.calcDate, "
			                                     + " t.seibancode , "
			                                     + " t.unit, "
			                                     + " t.INVQTY, "
			                                     + " t.ITEMFLAG , "
			                                     + " t.GJFL , "
			                                     + " t.GJFLN, "
			                                     + " t.BLFL, "
			                                     + " t.WhMan , "
			                                     + " t.WhManName , "
			                                     + " t.OPERATORCODE, "
			                                     + " t.categoryCode, "
			                                     + " t.categoryName , "
			                                     + " t.itemProp , "
			                                     + " t.itemPropDesc , "
			                                     + " t.mrpcode, "
			                                     + " sum(t.orderNoSendQty) orderNoSendQty, "
			                                     + " t.warmid  "
			                                     + " from("
			                                     + "     SELECT distinct a.CREATION_DATE , "
			                                     + "                     a.inst_id,  "
			                                     + "                     a.inst_name moorg  , "
			                                     + "                     a.itemcode , "
			                                     + "                     a.itemId, "
			                                     + "                     a.itemName, "
			                                     + "                     a.calcDate, "
			                                     + "                     a.seibancode , "
			                                     + "                     a.unit, "
			                                     + "                     a.INVQTY, "
			                                     + "                     a.ITEMFLAG , "
			                                     + "                     a.GJFL , "
			                                     + "                     a.GJFLN, "
			                                     + "                     a.BLFL, "
			                                     + "                     a.WhMan , "
			                                     + "                     a.WhManName , "
			                                     + "                     a.OPERATORCODE, "
			                                     + "                     a.CATEGORY_CODE categoryCode, "
			                                     + "                     a.CATEGORY_NAME categoryName , "
			                                     + "                     a.ITEM_PROP itemProp ,  "
			                                     + "                     a.ITEM_PROP_DESC itemPropDesc , "
			                                     + "                     a.mrpcode, "
			                                     + "                     round(v.totalACTQTY - v.totalISSUEDQTY ,5) orderNoSendQty, "
			                                     + "                     CONCAT(a.inst_id,a.itemcode,IFNULL(a.seibancode,'')) warmid  "
			                                     + "       FROM srm_only_short_material_info a join( "
			                                     + "                            select b.inst_id,  "
			                                     + "                                   b.itemcode, "
			                                     + "                                   b.docstate, "
			                                     + "                                   b.calcDate, "
			                                     + "                                   sum(b.ACTQTY) totalACTQTY,  "
			                                     + "                                   sum(b.ISSUEDQTY) totalISSUEDQTY "
			                                     + "                              from SRM_ONLY_SHORT_MATERIAL_INFO b "
			                                     + "                             where b.creation_Date = Curdate() "
			                                     + "                         group by  b.inst_id, "
			                                     + "                                   b.itemcode, "
			                                     + "                                   b.docstate, "
			                                     + "                                   b.calcDate ) v "
			                                     + "       on a.inst_id = v.inst_id "
			                                     + "      and a.calcDate = v.calcDate "
			                                     + "      and a.itemcode = v.itemcode "
			                                     + "      and a.docstate = v.docstate  "
			                                     + " where a.CREATION_DATE = CURDATE()  "
			                                     + "   and a.calcdate <= (CURDATE()+ INTERVAL+(7)day)  "
			                                     + " )t  group by  t.CREATION_DATE, "
			                                     + "               t.inst_id, "
			                                     + "               t.moorg  , "
			                                     + "               t.itemcode , "
			                                     + "               t.itemId, "
			                                     + "               t.itemName, "
			                                     + "               t.calcDate, "
			                                     + "               t.seibancode , "
			                                     + "               t.unit, "
			                                     + "               t.INVQTY, "
			                                     + "               t.ITEMFLAG , "
			                                     + "               t.GJFL , "
			                                     + "               t.GJFLN, "
			                                     + "               t.BLFL, "
			                                     + "               t.WhMan , "
			                                     + "               t.WhManName ,"
			                                     + "               t.OPERATORCODE, "
			                                     + "               t.categoryCode, "
			                                     + "               t.categoryName , "
			                                     + "               t.itemProp , "
			                                     + "               t.itemPropDesc , "
			                                     + "               t.mrpcode, "
			                                     + "               t.warmid  "
			                                     + " order by t.inst_id, "
			                                     + "          t.itemcode, "
			                                     + "          t.calcdate ";
	
	/*
	public static String WARNING_QUERY_SQL= " select t.CREATION_DATE AS checkDate ,"
			                                     + " t.inst_id, "
			                                     + " t.moorg  , "
			                                     + " t.itemcode , "
			                                     + " t.itemId, "
			                                     + " t.itemName, "
			                                     + " t.calcDate, "
			                                     + " t.seibancode , "
			                                     + " t.unit, "
			                                     + " t.INVQTY, "
			                                     + " t.ITEMFLAG , "
			                                     + " t.GJFL , "
			                                     + " t.GJFLN, "
			                                     + " t.BLFL, "
			                                     + " t.WhMan , "
			                                     + " t.WhManName , "
			                                     + " t.OPERATORCODE, "
			                                     + " t.categoryCode, "
			                                     + " t.categoryName , "
			                                     + " t.itemProp , "
			                                     + " t.itemPropDesc , "
			                                     + " t.mrpcode, "
			                                     + " sum(t.orderNoSendQty) orderNoSendQty, "
			                                     + " t.warmid "
			                                     + " from("
			                                    + " SELECT a.CREATION_DATE , "
		                                    	+ " a.inst_id, "
		                                    	+ " a.inst_name moorg  , "
		                                    	+ " a.itemcode , "
		                                    	+ " b.ITEM_ID itemId, "
		                                    	+ " b.ITEM_NAME itemName, "
		                                    	+ " a.calcDate, "
		                                    	+ " a.seibancode , "
		                                    	+ " b.UNIT_OF_MEASURE_NAME unit, "
		                                    	+ " a.INVQTY, "
		                                    	+ " a.ITEMFLAG , "
		                                    	+ " a.GJFL ,"
		                                    	+ " a.GJFLN, "
		                                    	+ " a.BLFL, "
		                                    	+ " a.WhMan , "
		                                    	+ " a.WhManName , "
		                                    	+ " a.OPERATORCODE, "
		                                    	+ " b.CATEGORY_CODE categoryCode, "
		                                    	+ " bc.CATEGORY_NAME categoryName , "
		                                    	+ " b.ITEM_PROP itemProp , "
		                                    	+ " b.ITEM_PROP_DESC itemPropDesc , "
		                                    	+ " a.mrpcode, "
//		                                    	+ " round(sum(a.ActQty - a.issuedqty ),5) orderNoSendQty, "
		                                    	+ " round(a.TOTAL_ACTQTY - a.TOTAL_ISSUEDQTY ,5) orderNoSendQty, "
		                                    	+ " CONCAT(a.inst_id,a.itemcode,IFNULL(a.seibancode,'')) warmid "
		                                    	+ " FROM SRM_SHORT_MATERIAL_INV_INFO a "
		                                    	+ " LEFT JOIN srm_base_items b ON  a.itemcode = b.item_code "
		                                    	+ " LEFT JOIN srm_base_categories bc on b.category_code= bc.category_code "
		                                    	+ " where a.CREATION_DATE = CURDATE()  "
		                                    	+ "   and a.calcdate <= (CURDATE()+ INTERVAL+(7)day)  "
		                                    	+ "   and b.ITEM_PROP in(4,9) "
		                                    	+ " GROUP BY a.CREATION_DATE, "
		                                    	+ "          a.inst_id, "
		                                    	+ "          a.inst_name, "
		                                    	+ "          a.itemcode, "
		                                    	+ "          b.ITEM_ID , "
		                                    	+ "          b.ITEM_NAME ,   "
		                                    	+ "          a.calcdate,"
		                                    	+ "          a.seibancode ,"
		                                    	+ "          b.UNIT_OF_MEASURE_NAME, "
		                                    	+ "          a.INVQTY ,  "
		                                    	+ "          a.ITEMFLAG ,"
		                                    	+ "          a.GJFL , "
		                                    	+ "          a.GJFLN, "
		                                    	+ "          a.BLFL, "
		                                    	+ "          a.WhMan , "
		                                    	+ "          a.WhManName, "
		                                    	+ "          a.OPERATORCODE, "
		                                    	+ "          b.CATEGORY_CODE, "
		                                    	+ "          bc.CATEGORY_NAME, "
		                                    	+ "          b.ITEM_PROP, "
		                                    	+ "          b.ITEM_PROP_DESC, "
		                                    	+ "          a.mrpcode,a.TOTAL_ACTQTY - a.TOTAL_ISSUEDQTY, "
		                                    	+ "          warmid "
		                                    	+ " )t  group by  t.CREATION_DATE, "
		                                    	              + " t.inst_id, "
		                                    	              + " t.moorg  , "
		                                    	              + " t.itemcode , "
		                                    	              + " t.itemId, "
		                                    	              + " t.itemName, "
		                                    	              + " t.calcDate, "
		                                    	              + " t.seibancode ,"
		                                    	              + " t.unit, "
		                                    	              + " t.INVQTY, "
		                                    	              + " t.ITEMFLAG , "
		                                    	              + " t.GJFL , "
		                                    	              + " t.GJFLN, "
		                                    	              + " t.BLFL, "
		                                    	              + " t.WhMan , "
		                                    	              + " t.WhManName , "
		                                    	              + " t.OPERATORCODE, "
		                                    	              + " t.categoryCode, "
		                                    	              + " t.categoryName , "
		                                    	              + " t.itemProp , "
		                                    	              + " t.itemPropDesc ,"
		                                    	              + " t.mrpcode, "
		                                    	              + " t.warmid "
		                                    	+ " order by t.inst_id,  "
		                                    	+ "          t.itemcode, "
		                                    	+ "          t.calcdate ";
		                          */
	
	public static String ONLY_SHORT_MATERIAL_QTY = " SELECT a.checkdate, "
			                                            + " a.inst_id, "
			                                            + " a.inst_name moorg, "
			                                            + " a.docno,  "
			                                            + " a.itemcode, a.itemname,  "
			                                            + " date_format(a.mostartdate,'%Y-%m-%d') mostartdate,  "
			                                            + " a.calcdate,  "
			                                            + " a.actqty, "
			                                            + " round(a.issuedqty,5) issuedqty,   "
			                                            + " round(a.ActQty - a.issuedqty ,5) orderNoSendQty,       "
			                                            + " round(a.invqty,5) invqty ,  "
			                                            + " round(if((a.SHORTQTY - a.SHORTQTY*2)<0,0,(a.SHORTQTY - a.SHORTQTY*2)),5) qty, " 
			                                            + " (SELECT meaning from saaf_lookup_values WHERE lookup_type = 'PO_ORDER_TYPE' AND lookup_code = a.doctype ) doctype ,  "
			                                            + " a.seibancode , "
			                                            + " a.demandcode  , "
			                                            + " a.mrpcode , "
			                                            + " a.itemflag , "
			                                            + " a.deliverldtime ,"
			                                            + " a.unit , "
			                                            + " a.category_Code categoryCode ,  "
			                                			+ " a.category_Name categoryName, "
			                                			+ " a.operatorcode "
			                                            + " FROM SRM_ONLY_SHORT_MATERIAL_INFO a "
			                                            + " where 1=1 ";
	
	

	public static String TO_DELIVERY_NOTICE_ORDER_SQL= " SELECT a.CREATION_DATE checkdate, "
            + " a.inst_id,  "
            + " a.inst_name moorg, "
            + " '' AS docno, "
            + " a.itemcode, "
            + " a.itemid, "
            + " a.calcdate, "
            + " case when a.PurchaseBatchQty = 0 "
            + " then Sum(if(a.SHORTQTY - a.SHORTQTY*2<0,0,a.SHORTQTY - a.SHORTQTY*2 )) "
            + " else ceil(Sum(if(a.SHORTQTY - a.SHORTQTY*2<0,0,a.SHORTQTY - a.SHORTQTY*2 ))) end qty, "
            + " '' doctype , "
            + " a.seibancode, "
            + " a.category_code categoryCode, "
            + " a.employee_id employeeId ,"
            + " a.mrpcode  "
            + " FROM SRM_SHORT_MATERIAL_INV_INFO a "
            + " where  a.creation_Date = Curdate() and a.RTNGOODSTYPE ='2' "
            + " GROUP BY a.CREATION_DATE,"
            + "          a.inst_id, "
            + "          a.inst_name, "
            + "          a.itemcode, "
            + "          a.itemid,"
            + "          a.calcdate,"
            + "          a.seibancode "
            + " having 1=1 ";

	
	public static String QUERY_SHORT_MATERIAL_WARM = " SELECT a.id,  "
			+ " a.inst_name moOrg, "
			+ " a.itemcode, "
			+ " a.itemname, "
			+ " a.seibancode, "
			+ " a.unit, "
			+ " a.itemflag, "
			+ " a.gjfl, "
			+ " a.gjfln, "
			+ " a.blfl, "
			+ " a.whmanname, "
			+ " a.employee employeename,  "
			+ " a.total_3days_qty, "
			+ " a.total_3days_differ_qty,  "
			+ " a.urgent, "
			+ " a.t0_qty, "
			+ " a.t0_inv_qty, "
			+ " a.t0_differ_qty, "
			+ " a.t1_qty, "
			+ " a.t1_inv_qty, "
			+ " a.t1_differ_qty, "
			+ " a.t2_qty, "
			+ " a.t2_inv_qty, "
			+ " a.t2_differ_qty, "
			+ " a.t3_qty,"
			+ " a.t3_inv_qty,"
			+ " a.t3_differ_qty, "
			+ " a.t4_qty, "
			+ " a.t4_inv_qty, "
			+ " a.t4_differ_qty, "
			+ " a.t5_qty,  "
			+ " a.t5_inv_qty,  "
			+ " a.t5_differ_qty,  "
			+ " a.t6_qty,  "
			+ " a.t6_inv_qty, "
			+ " a.t6_differ_qty, "
			+ " a.t7_qty, "
			+ " a.t7_inv_qty, "
			+ " a.t7_differ_qty, "
			+ " a.creation_date checkDate , "
			+ " a.category_Code categoryCode ,  "
			+ " a.category_Name categoryName , "
			+ " a.ITEM_PROP itemProp , "
			+ " a.ITEM_PROP_DESC itemPropDesc, "
			+ " a.mrpcode "
			+ " FROM  srm_short_material_warm a "
			+ " where 1=1 ";
	
	public static String QUERY_U9_READY_MATERIAL = " SELECT a.docno, "
			                                             + " (SELECT meaning "
			                                             + " FROM saaf_lookup_values "
			                                             + " WHERE  lookup_type = 'PO_ORDER_TYPE' "
			                                             + " AND lookup_code = a.doctype)           docType, "
			                                             + " CASE  "
			                                             + " WHEN a.docstate = '0' THEN '开立' "
			                                             + " WHEN a.docstate = '1' THEN '已核准' "
			                                             + " WHEN a.docstate = '2' THEN '开工' "
			                                             + " ELSE '' "
			                                             + " end  status, "
			                                             + " IF(a.isholdrelease = 0, '非挂起', '挂起') isHoldRelease, "
			                                             + " c.inst_name AS moOrg, "
			                                             + " a.doclineno, "
			                                             + " a.itemcode, "
			                                             + " a.itemname, "
			                                             + " a.ActQty, "
			                                             + " round(a.issuedqty,5) issuedqty, "
			                                             + " round(a.ActQty - a.issuedqty ,5) orderNoSendQty  ,"
			                                             + " a.actualreqdate, "
			                                             + " a.seibancode, "
			                                             + " a.deliverldtime, "
			                                             + " a.itemformattributename, "
			                                             + " a.isinheritbommasterno, "
			                                             + " a.purchasebatchqty, "
			                                             + " d.employee_name employeename , "
			                                             + " a.rtngoodstypename  ,"
			                                             + " a.CREATION_DATE checkdate ,"
			                                             + " b.UNIT_OF_MEASURE_NAME unit "
			                                             + " FROM  srm_short_material_info a "
			                                             + " left join srm_base_items b on a.itemcode=b.item_code "
			                                             + " left join saaf_employees d on a.operatorcode =d.employee_number "
			                                             + " LEFT JOIN saaf_institution c  "
			                                             + " ON a.moorg = c.inst_code "
			                                             + " where 1=1 ";
	
	public static String QUERY_AND_ONLY_MATERIAL_INV = " SELECT c.inst_id , "
			                                                + " c.inst_name  AS moOrg, "
			                                                + " a.DocNo , "
			                                                + " a.itemcode , "
			                                                + " b.item_id itemid, "
			                                                + " b.ITEM_NAME itemname, "
			                                                + " Date_format(a.mostartdate, '%Y-%m-%d') AS MoStartDate, "
			                                                + " ( CASE WHEN  "
			                                                + " a.actualreqdate  + INTERVAL -(a.deliverldtime) day  < Curdate()  "
			                                                + " THEN Curdate() "
			                                                + " ELSE a.actualreqdate  + INTERVAL -(a.deliverldtime) day end ) AS calcDate, "
			                                                + " a.actqty, "
			                                                + " a.issuedqty, "
			                                                + " (a.actqty - a.issuedqty ) needQty ,"
			                                                + " Ifnull((select sum(d.qty) "
			                                                + "  from SRM_SHORT_MATERIAL_INV d "
			                                                + " where d.material_Code = a.ITEMCODE "
			                                                + " and d.invorg = a.MOORG "
			                                                + " and d.considerdelivplan = '1' "
			                                                + " and d.CREATION_DATE >= CURDATE()+ INTERVAL-(0)day ) ,0 ) invqty,"
			                                                + " a.docType, "
			                                                + " a.isholdrelease, "
			                                                + " a.deliverldtime, "
			                                                + " a.docstate, "
			                                                + " a.PurchaseBatchQty , "
			                                                + " a.demandcode, "
			                                                + " a.mrpcode, "
			                                                + " a.itemflag , "
			                                                + " d.employee_id employeeId ,d.employee_name employeename , "
			                                                + " a.whName, "
			                                                + " a.ItemFormAttributeName,"
			                                                + " a.RTNGoodsTypeName ,"
			                                                + " a.RTNGoodsType , "
			                                                + " a.ItemFormAttribute, "
			                                                + " b.UNIT_OF_MEASURE_NAME unit, a.GJFL, a.GJFLN , "
			                                                + " a.BLFL , a.WhMan , "
			                                                + " a.WhManName , "
			                                                + " b.CATEGORY_CODE categoryCode, "
		                                    	            + " bc.CATEGORY_NAME categoryName, "
		                                    	            + " b.item_prop itemProp , "
		                                    	            + " b.ITEM_PROP_DESC itemPropDesc, "
		                                    	            + " a.productqty, "
		                                    	            + " a.notcompleteqty, "
		                                    	            + " a.moitemcode, "
		                                    	            + " a.moitemname, "
		                                    	            + " a.productdept "
			                                                + " FROM srm_short_material_info a "
			                                                + " LEFT JOIN srm_base_items b ON  a.itemcode = b.ITEM_CODE "
			                                                + " LEFT JOIN saaf_institution c ON a.moorg = c.inst_code  "
			                                                + " left join saaf_employees d on a.operatorCode = d.employee_number "
			                                                + " LEFT JOIN srm_base_categories bc on b.category_code= bc.category_code "
			                                                + " WHERE  ( a.seibancode = '' OR Isnull(a.seibancode)  ) "
			                                                + " and a.creation_Date = Curdate() and b.item_prop in(4,9) "
			                                                + " order by c.inst_id ,a.itemcode, calcDate ,a.mostartdate ";  
	
	public static String QUERY_AND_CALC_MATERIAL_INV = " select a.inst_id , "
			                                                + " a.inst_name  AS moOrg, "
			                                                + " a.DocNo ,  "
			                                                + " a.itemcode, "
			                                                + " a.itemid, "
			                                                + " a.itemname, "
			                                                + " Date_format(a.mostartdate, '%Y-%m-%d') AS MoStartDate,"
			                                                + " a.calcDate, "
			                                                + " a.ACTQTY, "
			                                                + " a.ISSUEDQTY, "
			                                                + " a.invqty,  "
			                                                + " (a.SHORTQTY - a.SHORTQTY*2) SHORTQTY , "
			                                                
			                                                /*
			                                                + "  (SELECT Ifnull(Sum(e.QTY),0) - Ifnull(Sum(e.delivery_qty),0)   "
			                                                + " FROM  srm_po_notice_qty_tmp e  "
			                                                + " WHERE e.inst_id = a.inst_id  "
			                                                + "   AND e.item_id =  a.itemid "
			                                                + "   AND Ifnull(e.SPECIAL_USE_NUM,0) = Ifnull(a.SEIBANCODE,0) )  midqty, " 
			                                                */
			                                                
			                                                + " case when a.mrpcode = 'M01' "
			                                                + " THEN "
			                                                + " (SELECT Ifnull(Sum(e.QTY),0) - Ifnull(Sum(e.delivery_qty),0) "
			                                                + " FROM srm_po_notice_qty_tmp e "
			                                                + " WHERE e.inst_id = 90 "
			                                                + "   AND e.delivery_site_id = a.inst_id "
			                                                + "   AND e.item_id =  a.itemid "
			                                                + "   AND Ifnull(e.SPECIAL_USE_NUM,0) = Ifnull(a.SEIBANCODE,0) ) "
			                                                + " when a.mrpcode = 'M02' "
			                                                + " THEN "
			                                                + " (SELECT Ifnull(Sum(e.QTY),0) - Ifnull(Sum(e.delivery_qty),0) "
			                                                + " FROM srm_po_notice_qty_tmp e "
			                                                + " WHERE e.inst_id = 91 "
			                                                + "   AND e.delivery_site_id = a.inst_id "
			                                                + "   AND e.item_id =  a.itemid "
			                                                + "   AND Ifnull(e.SPECIAL_USE_NUM,0) = Ifnull(a.SEIBANCODE,0) ) "
			                                                + " else "
			                                                + " (SELECT Ifnull(Sum(e.QTY),0) - Ifnull(Sum(e.delivery_qty),0) "
			                                                + " FROM srm_po_notice_qty_tmp e "
			                                                + " WHERE e.inst_id = a.inst_id "
			                                                + " AND e.item_id =  a.itemid "
			                                                + " AND Ifnull(e.SPECIAL_USE_NUM,0) = Ifnull(a.SEIBANCODE,0) ) end  midqty,"
			                                                
			                                                + " a.docType, "
			                                                + " a.isholdrelease, "
			                                                + " a.docstate, "
			                                                + " a.PurchaseBatchQty , "
			                                                + " a.demandcode, "
			                                                + " a.mrpcode, "
			                                                + " a.itemflag , "
			                                                + " a.employee_id employeeId, "
			                                                + " a.OPERATORCODE,  "
			                                                + " a.deliverldtime , "
			                                                + " a.unit, "
			                                                + " a.GJFL, "
			                                                + " a.GJFLN , "
			                                                + " a.BLFL , "
			                                                + " a.WhMan , "
			                                                + " a.WhManName , "
			                                                + " a.category_Code categoryCode, "
			                                                + " a.category_Name categoryName, "
			                                                + " v.totalActqty, "
			                                                + " v.totalIssuedqty, "
			                                                + " a.RTNGoodsType, "
			                                                + " a.productqty, "
		                                    	            + " a.notcompleteqty, "
		                                    	            + " a.moitemcode, "
		                                    	            + " a.moitemname, "
		                                    	            + " a.productdept "
			                                                + " from SRM_ONLY_SHORT_MATERIAL_INFO a join("
			                                                + " select b.inst_id, "
			                                                + "        b.itemcode,b.docstate, b.calcDate, "
			                                                + "        sum(b.ACTQTY) totalACTQTY, "
			                                                + "        sum(b.ISSUEDQTY) totalISSUEDQTY "
			                                                + " from SRM_ONLY_SHORT_MATERIAL_INFO b where b.creation_Date = Curdate() "
			                                                + " group by  b.inst_id,b.itemcode, b.docstate,b.calcDate ) v "
			                                                + " on a.inst_id = v.inst_id and a.calcDate = v.calcDate "
			                                                + " and a.itemcode = v.itemcode and a.docstate = v.docstate "
			                                                + " where a.SHORTQTY<0 and a.creation_Date = Curdate() " 
			                                                + " order by a.inst_id ,a.itemcode, a.calcDate ASC ,a.SHORTQTY desc ";
	
	
	public static String QUERY_SHORT_MATERIAL_ORDERS = " SELECT DISTINCT a.docno, "
			                                         + "         (SELECT v.meaning "
			                                         + "            FROM saaf_lookup_values v "
			                                         + "           WHERE v.lookup_type = 'PO_ORDER_TYPE' "
			                                         + "             AND v.lookup_code = a.doctype) doctype, "
			                                         + "        a.demandcode, "
			                                         + "        Ifnull(a.productqty,0) productqty, "
			                                         + "        Ifnull(a.notcompleteqty,0) notcompleteqty , "
			                                         + "        date_format(a.mostartdate,'%Y-%m-%d') mostartdate , "
			                                         + "        a.moitemcode, "
			                                         + "        a.moitemname, "
			                                         + "        a.productdept, "
			                                         + "        CASE WHEN a.docstate = '0' THEN '开立' "
			                                         + "             WHEN a.docstate = '1' THEN '已核准' "
			                                         + "             WHEN a.docstate = '2' THEN '开工' "
			                                         + "        ELSE ''  END status "
			                                         + " FROM srm_short_material_inv_info a "
			                                         + " WHERE  1 = 1  ";
	
	public static String QUERY_NOTICE_ORDERS = " select a.po_notice_code poNoticeCode, "
			                                        + " a.demand_date demandDate,  "
			                                        + " e.supplier_name supplierName, "
			                                        + " e.supplier_number supplierNumber, "
			                                        + " c.ITEM_CODE itemcode, "
			                                        + " c.ITEM_NAME itemname, "
			                                        + " a.quantity qty , "
			                                        + " IFNULL(a.delivery_qty,0) deliveryqty, "
			                                        + " (IFNULL(a.quantity,0) - IFNULL(a.delivery_qty,0)) orderNoSendQty , "
			                                        + " IFNULL(get_notice_qty_f (  a.po_notice_id, 'CREATE_ACT_DELIVERY'   ),0) deliveryOrderQty, "
			                                        + " a.SPECIAL_USE_NUM seibancode, "
			                                        + " f.employee_name employeename, "
			                                        + " (select v.meaning from saaf_lookup_values v "
			                                        + " where v.lookup_type='PO_NOTICE_STATUS' and v.lookup_code = a.status) status, "
			                                        + " b.inst_name moOrg, "
			                                        + " d.inst_name deliveryInst, "
			                                        + " case when a.PO_STARVING_ID is null then '手工生成' else '系统建议' end way "
			                                        + " from srm_po_notice a join saaf_institution b on a.inst_id = b.inst_id "
			                                        + " join srm_base_items c on a.item_id = c.ITEM_ID "
			                                        + " left join saaf_institution d on a.delivery_site_id = d.inst_id "
			                                        + " left join srm_pos_supplier_info e on a.supplier_id = e.supplier_id "
			                                        + " left join saaf_employees f on a.employee_num =f.employee_number "
			                                        + " where a.status in( 'CONFIRMED','CREATED','REJECTED') ";
	
	public static String QUERY_DELIVERY_ORDER = " SELECT dh.delivery_number deliveryNumber, "
			                                         + " si.supplier_name supplierName , "
			                                         + " si.supplier_number supplierNumber ,"
			                                         + " dh.CREATION_DATE checkDate , "
			                                         + " it.inst_name moOrg , "
			                                         + " item.ITEM_CODE itemcode , "
			                                         + " item.ITEM_NAME itemname , "
			                                         + " sum(l.delivery_order_qty) qty , "
			                                         + " l.SPECIAL_USE_NUM seibancode, "
			                                         + " item.UNIT_OF_MEASURE_NAME unit "
			                                         + " FROM srm_po_delivery_headers dh "
			                                         + " join srm_po_delivery_lines l on dh.delivery_header_id = l.delivery_header_id "
			                                         + " LEFT JOIN saaf_institution it on dh.inst_id = it.inst_id "
			                                         + " LEFT JOIN srm_pos_supplier_info si on dh.supplier_Id = si.supplier_id "
			                                         + " left join srm_base_items item on l.item_id = item.ITEM_ID "
			                                         + " where dh.status='CREATED'  "; 
	
	public static String QUERY_SUPER_QTY = "SELECT a.creation_date, "
			+ " a.inst_id, "
			+ " a.inst_name moorg, "
			+ " a.deliveryinstid, "
			+ " a.deliveryinst, "
			+ " a.itemcode, "
			+ " a.itemname, "
			+ " a.mrpcode, "
			+ " a.qty, "
			+ " a.midqty, "
			+ " a.midqty1 , "
			+ " a.operatorcode "
			+ " FROM srm_short_material_superqty a "
			+ " WHERE 1=1 and a.midqty1 >0 ";
		

	@JSONField(format = "yyyy-MM-dd")
    private Date checkDate;
	private String moOrg;
	private String DocNo;
	private String itemcode;
	private Integer itemid;
	private String itemname;
	private String MoStartDate;
	@JSONField(format = "yyyy-MM-dd")
    private Date calcDate;
	private BigDecimal qty;
	private BigDecimal invqty;
	private BigDecimal needQty;
	private String docType;
	private String seibancode;
	
	private String status;
	private String IsHoldRelease;
	private String doclineno;	
	private BigDecimal bomreqqty;
	private BigDecimal issuedqty;
	private BigDecimal orderNoSendQty;
	private BigDecimal actqty;
	private BigDecimal shortqty;   //缺料
	private BigDecimal midqty;   // 在途总数量
	private BigDecimal midqty1;
	@JSONField(format = "yyyy-MM-dd")
	private Date actualreqdate;
	private Integer deliverldtime;
	private Integer itemformattribute;
	private Integer isinheritbommasterno;
	private BigDecimal  purchasebatchqty;
	private String operatorcode;
	private Integer rtngoodstype ;
	private String inst_id;
	private String deliveryinstid;
	private Integer docstate;
	
	@JSONField(format = "yyyy-MM-dd")
	private Date demandDate;	
	private String demandcode;
	private String mrpcode;
	private String itemflag;
	private Integer employeeId;
	private String categoryCode;
	private String categoryName;
	private String whname;
    private String itemformattributename;
    private String rtngoodstypename;
    private String unit;
    private String employeename;
    private String warmid;
    
    private String gjfl;
    private String gjfln;
    private String blfl;
    private String whman;
    private String whmanname;
    
    private String urgent;
    private BigDecimal total_3days_qty;
    private BigDecimal total_3days_differ_qty;
    private BigDecimal t0_qty;
    private BigDecimal t0_inv_qty;
    private BigDecimal t0_differ_qty;
    private BigDecimal t1_qty;
    private BigDecimal t1_inv_qty;
    private BigDecimal t1_differ_qty;
    private BigDecimal t2_qty;
    private BigDecimal t2_inv_qty;
    private BigDecimal t2_differ_qty;
    private BigDecimal t3_qty;
    private BigDecimal t3_inv_qty;
    private BigDecimal t3_differ_qty;
    private BigDecimal t4_qty;
    private BigDecimal t4_inv_qty;
    private BigDecimal t4_differ_qty;
    private BigDecimal t5_qty;
    private BigDecimal t5_inv_qty;
    private BigDecimal t5_differ_qty;
    private BigDecimal t6_qty;
    private BigDecimal t6_inv_qty;
    private BigDecimal t6_differ_qty;
    private BigDecimal t7_qty;
    private BigDecimal t7_inv_qty;
    private BigDecimal t7_differ_qty;
 
    private String deliveryNumber;
	private String supplierName;
	private String supplierNumber;
	private String itemProp;
	private String itemPropDesc;
	private String poNoticeCode;

	private BigDecimal deliveryOrderQty;
	private String deliveryInst;
	private String way;
	private BigDecimal deliveryqty;
	private BigDecimal totalActqty;
	private BigDecimal totalIssuedqty;
	
	private BigDecimal productqty;
	private BigDecimal notcompleteqty;
	private String moitemcode;
	private String moitemname;
	private String productdept;

	
	public String getDeliveryinstid() {
		return deliveryinstid;
	}
	public void setDeliveryinstid(String deliveryinstid) {
		this.deliveryinstid = deliveryinstid;
	}
	public BigDecimal getMidqty1() {
		return midqty1;
	}
	public void setMidqty1(BigDecimal midqty1) {
		this.midqty1 = midqty1;
	}
	public BigDecimal getProductqty() {
		return productqty;
	}
	public void setProductqty(BigDecimal productqty) {
		this.productqty = productqty;
	}
	public BigDecimal getNotcompleteqty() {
		return notcompleteqty;
	}
	public void setNotcompleteqty(BigDecimal notcompleteqty) {
		this.notcompleteqty = notcompleteqty;
	}
	public String getMoitemcode() {
		return moitemcode;
	}
	public void setMoitemcode(String moitemcode) {
		this.moitemcode = moitemcode;
	}
	public String getMoitemname() {
		return moitemname;
	}
	public void setMoitemname(String moitemname) {
		this.moitemname = moitemname;
	}
	public String getProductdept() {
		return productdept;
	}
	public void setProductdept(String productdept) {
		this.productdept = productdept;
	}
	public Date getDemandDate() {
		return demandDate;
	}
	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}
	public BigDecimal getTotalActqty() {
		return totalActqty;
	}
	public void setTotalActqty(BigDecimal totalActqty) {
		this.totalActqty = totalActqty;
	}
	public BigDecimal getTotalIssuedqty() {
		return totalIssuedqty;
	}
	public void setTotalIssuedqty(BigDecimal totalIssuedqty) {
		this.totalIssuedqty = totalIssuedqty;
	}
	public BigDecimal getDeliveryqty() {
		return deliveryqty;
	}
	public void setDeliveryqty(BigDecimal deliveryqty) {
		this.deliveryqty = deliveryqty;
	}
	public BigDecimal getDeliveryOrderQty() {
		return deliveryOrderQty;
	}
	public void setDeliveryOrderQty(BigDecimal deliveryOrderQty) {
		this.deliveryOrderQty = deliveryOrderQty;
	}
	public String getDeliveryInst() {
		return deliveryInst;
	}
	public void setDeliveryInst(String deliveryInst) {
		this.deliveryInst = deliveryInst;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public String getPoNoticeCode() {
		return poNoticeCode;
	}
	public void setPoNoticeCode(String poNoticeCode) {
		this.poNoticeCode = poNoticeCode;
	}
	public String getItemProp() {
		return itemProp;
	}
	public void setItemProp(String itemProp) {
		this.itemProp = itemProp;
	}
	public String getItemPropDesc() {
		return itemPropDesc;
	}
	public void setItemPropDesc(String itemPropDesc) {
		this.itemPropDesc = itemPropDesc;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDeliveryNumber() {
		return deliveryNumber;
	}
	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
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
	public String getEmployeename() {
		return employeename;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getUrgent() {
		return urgent;
	}
	public void setUrgent(String urgent) {
		this.urgent = urgent;
	}
	public BigDecimal getTotal_3days_qty() {
		return total_3days_qty;
	}
	public void setTotal_3days_qty(BigDecimal total_3days_qty) {
		this.total_3days_qty = total_3days_qty;
	}
	public BigDecimal getTotal_3days_differ_qty() {
		return total_3days_differ_qty;
	}
	public void setTotal_3days_differ_qty(BigDecimal total_3days_differ_qty) {
		this.total_3days_differ_qty = total_3days_differ_qty;
	}
	public BigDecimal getT0_qty() {
		return t0_qty;
	}
	public void setT0_qty(BigDecimal t0_qty) {
		this.t0_qty = t0_qty;
	}
	public BigDecimal getT0_inv_qty() {
		return t0_inv_qty;
	}
	public void setT0_inv_qty(BigDecimal t0_inv_qty) {
		this.t0_inv_qty = t0_inv_qty;
	}
	public BigDecimal getT0_differ_qty() {
		return t0_differ_qty;
	}
	public void setT0_differ_qty(BigDecimal t0_differ_qty) {
		this.t0_differ_qty = t0_differ_qty;
	}
	public BigDecimal getT1_qty() {
		return t1_qty;
	}
	public void setT1_qty(BigDecimal t1_qty) {
		this.t1_qty = t1_qty;
	}
	public BigDecimal getT1_inv_qty() {
		return t1_inv_qty;
	}
	public void setT1_inv_qty(BigDecimal t1_inv_qty) {
		this.t1_inv_qty = t1_inv_qty;
	}
	public BigDecimal getT1_differ_qty() {
		return t1_differ_qty;
	}
	public void setT1_differ_qty(BigDecimal t1_differ_qty) {
		this.t1_differ_qty = t1_differ_qty;
	}
	public BigDecimal getT2_qty() {
		return t2_qty;
	}
	public void setT2_qty(BigDecimal t2_qty) {
		this.t2_qty = t2_qty;
	}
	public BigDecimal getT2_inv_qty() {
		return t2_inv_qty;
	}
	public void setT2_inv_qty(BigDecimal t2_inv_qty) {
		this.t2_inv_qty = t2_inv_qty;
	}
	public BigDecimal getT2_differ_qty() {
		return t2_differ_qty;
	}
	public void setT2_differ_qty(BigDecimal t2_differ_qty) {
		this.t2_differ_qty = t2_differ_qty;
	}
	public BigDecimal getT3_qty() {
		return t3_qty;
	}
	public void setT3_qty(BigDecimal t3_qty) {
		this.t3_qty = t3_qty;
	}
	public BigDecimal getT3_inv_qty() {
		return t3_inv_qty;
	}
	public void setT3_inv_qty(BigDecimal t3_inv_qty) {
		this.t3_inv_qty = t3_inv_qty;
	}
	public BigDecimal getT3_differ_qty() {
		return t3_differ_qty;
	}
	public void setT3_differ_qty(BigDecimal t3_differ_qty) {
		this.t3_differ_qty = t3_differ_qty;
	}
	public BigDecimal getT4_qty() {
		return t4_qty;
	}
	public void setT4_qty(BigDecimal t4_qty) {
		this.t4_qty = t4_qty;
	}
	public BigDecimal getT4_inv_qty() {
		return t4_inv_qty;
	}
	public void setT4_inv_qty(BigDecimal t4_inv_qty) {
		this.t4_inv_qty = t4_inv_qty;
	}
	public BigDecimal getT4_differ_qty() {
		return t4_differ_qty;
	}
	public void setT4_differ_qty(BigDecimal t4_differ_qty) {
		this.t4_differ_qty = t4_differ_qty;
	}
	public BigDecimal getT5_qty() {
		return t5_qty;
	}
	public void setT5_qty(BigDecimal t5_qty) {
		this.t5_qty = t5_qty;
	}
	public BigDecimal getT5_inv_qty() {
		return t5_inv_qty;
	}
	public void setT5_inv_qty(BigDecimal t5_inv_qty) {
		this.t5_inv_qty = t5_inv_qty;
	}
	public BigDecimal getT5_differ_qty() {
		return t5_differ_qty;
	}
	public void setT5_differ_qty(BigDecimal t5_differ_qty) {
		this.t5_differ_qty = t5_differ_qty;
	}
	public BigDecimal getT6_qty() {
		return t6_qty;
	}
	public void setT6_qty(BigDecimal t6_qty) {
		this.t6_qty = t6_qty;
	}
	public BigDecimal getT6_inv_qty() {
		return t6_inv_qty;
	}
	public void setT6_inv_qty(BigDecimal t6_inv_qty) {
		this.t6_inv_qty = t6_inv_qty;
	}
	public BigDecimal getT6_differ_qty() {
		return t6_differ_qty;
	}
	public void setT6_differ_qty(BigDecimal t6_differ_qty) {
		this.t6_differ_qty = t6_differ_qty;
	}
	public BigDecimal getT7_qty() {
		return t7_qty;
	}
	public void setT7_qty(BigDecimal t7_qty) {
		this.t7_qty = t7_qty;
	}
	public BigDecimal getT7_inv_qty() {
		return t7_inv_qty;
	}
	public void setT7_inv_qty(BigDecimal t7_inv_qty) {
		this.t7_inv_qty = t7_inv_qty;
	}
	public BigDecimal getT7_differ_qty() {
		return t7_differ_qty;
	}
	public void setT7_differ_qty(BigDecimal t7_differ_qty) {
		this.t7_differ_qty = t7_differ_qty;
	}
	public String getGjfl() {
		return gjfl;
	}
	public void setGjfl(String gjfl) {
		this.gjfl = gjfl;
	}
	public String getGjfln() {
		return gjfln;
	}
	public void setGjfln(String gjfln) {
		this.gjfln = gjfln;
	}
	public String getBlfl() {
		return blfl;
	}
	public void setBlfl(String blfl) {
		this.blfl = blfl;
	}
	public String getWhman() {
		return whman;
	}
	public void setWhman(String whman) {
		this.whman = whman;
	}
	public String getWhmanname() {
		return whmanname;
	}
	public void setWhmanname(String whmanname) {
		this.whmanname = whmanname;
	}
	public String getWarmid() {
		return warmid;
	}
	public void setWarmid(String warmid) {
		this.warmid = warmid;
	}
	public BigDecimal getMidqty() {
		return midqty;
	}
	public void setMidqty(BigDecimal midqty) {
		this.midqty = midqty;
	}
	public String getEmployeeName() {
		return employeename;
	}
	public void setEmployeeName(String employeename) {
		this.employeename = employeename;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getWhname() {
		return whname;
	}
	public void setWhname(String whname) {
		this.whname = whname;
	}
	public String getItemformattributename() {
		return itemformattributename;
	}
	public void setItemformattributename(String itemformattributename) {
		this.itemformattributename = itemformattributename;
	}
	public String getRtngoodstypename() {
		return rtngoodstypename;
	}
	public void setRtngoodstypename(String rtngoodstypename) {
		this.rtngoodstypename = rtngoodstypename;
	}
	public BigDecimal getOrderNoSendQty() {
		return orderNoSendQty;
	}
	public void setOrderNoSendQty(BigDecimal orderNoSendQty) {
		this.orderNoSendQty = orderNoSendQty;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getDemandcode() {
		return demandcode;
	}
	public void setDemandcode(String demandcode) {
		this.demandcode = demandcode;
	}
	public String getMrpcode() {
		return mrpcode;
	}
	public void setMrpcode(String mrpcode) {
		this.mrpcode = mrpcode;
	}
	public String getItemflag() {
		return itemflag;
	}
	public void setItemflag(String itemflag) {
		this.itemflag = itemflag;
	}
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public BigDecimal getShortqty() {
		return shortqty;
	}
	public void setShortqty(BigDecimal shortqty) {
		this.shortqty = shortqty;
	}
	public Integer getDocstate() {
		return docstate;
	}
	public void setDocstate(Integer docstate) {
		this.docstate = docstate;
	}
	public BigDecimal getNeedQty() {
		return needQty;
	}
	public void setNeedQty(BigDecimal needQty) {
		this.needQty = needQty;
	}
	public BigDecimal getInvqty() {
		return invqty;
	}
	public void setInvqty(BigDecimal invqty) {
		this.invqty = invqty;
	}
	public String getInst_id() {
		return inst_id;
	}
	public void setInst_id(String inst_id) {
		this.inst_id = inst_id;
	}
	public BigDecimal getActqty() {
		return actqty;
	}
	public void setActqty(BigDecimal actqty) {
		this.actqty = actqty;
	}
	public String getIsHoldRelease() {
		return IsHoldRelease;
	}
	public void setIsHoldRelease(String isHoldRelease) {
		IsHoldRelease = isHoldRelease;
	}
	public Integer getDeliverldtime() {
		return deliverldtime;
	}
	public void setDeliverldtime(Integer deliverldtime) {
		this.deliverldtime = deliverldtime;
	}
	public Integer getItemformattribute() {
		return itemformattribute;
	}
	public void setItemformattribute(Integer itemformattribute) {
		this.itemformattribute = itemformattribute;
	}
	public Integer getIsinheritbommasterno() {
		return isinheritbommasterno;
	}
	public void setIsinheritbommasterno(Integer isinheritbommasterno) {
		this.isinheritbommasterno = isinheritbommasterno;
	}
	public BigDecimal getPurchasebatchqty() {
		return purchasebatchqty;
	}
	public void setPurchasebatchqty(BigDecimal purchasebatchqty) {
		this.purchasebatchqty = purchasebatchqty;
	}
	public Integer getRtngoodstype() {
		return rtngoodstype;
	}
	public void setRtngoodstype(Integer rtngoodstype) {
		this.rtngoodstype = rtngoodstype;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDoclineno() {
		return doclineno;
	}
	public void setDoclineno(String doclineno) {
		this.doclineno = doclineno;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public BigDecimal getBomreqqty() {
		return bomreqqty;
	}
	public void setBomreqqty(BigDecimal bomreqqty) {
		this.bomreqqty = bomreqqty;
	}
	public BigDecimal getIssuedqty() {
		return issuedqty;
	}
	public void setIssuedqty(BigDecimal issuedqty) {
		this.issuedqty = issuedqty;
	}
	public Date getActualreqdate() {
		return actualreqdate;
	}
	public void setActualreqdate(Date actualreqdate) {
		this.actualreqdate = actualreqdate;
	}
	public String getOperatorcode() {
		return operatorcode;
	}
	public void setOperatorcode(String operatorcode) {
		this.operatorcode = operatorcode;
	}
	public String getMoStartDate() {
		return MoStartDate;
	}
	public void setMoStartDate(String moStartDate) {
		MoStartDate = moStartDate;
	}
	public String getSeibancode() {
		return seibancode;
	}
	public void setSeibancode(String seibancode) {
		this.seibancode = seibancode;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getMoOrg() {
		return moOrg;
	}
	public void setMoOrg(String moOrg) {
		this.moOrg = moOrg;
	}
	public String getDocNo() {
		return DocNo;
	}
	public void setDocNo(String docNo) {
		DocNo = docNo;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public Date getCalcDate() {
		return calcDate;
	}
	public void setCalcDate(Date calcDate) {
		this.calcDate = calcDate;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	
	

}
