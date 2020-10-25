package saaf.common.fmw.intf.model.inter.server;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.intf.model.inter.ISrmShortMaterialInfo;
import saaf.common.fmw.intf.util.U9IntfUtils;
import saaf.common.fmw.po.model.entities.SaafDbConfigEntity_HI;
import saaf.common.fmw.po.model.entities.SrmOnlyShortMaterialInfoEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoStarvingEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoStarvingHisEntity_HI;
import saaf.common.fmw.po.model.entities.SrmShortMaterialConfigEntity_HI;
import saaf.common.fmw.po.model.entities.SrmShortMaterialInfoEntity_HI;
import saaf.common.fmw.po.model.entities.SrmShortMaterialInvEntity_HI;
import saaf.common.fmw.po.model.entities.SrmShortMaterialInvInfoEntity_HI;
import saaf.common.fmw.po.model.entities.SrmShortMaterialWarmEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoHeadersEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmShortMaterialInfoEntity_HI_RO;

@Component("srmShortMaterialInfoServer")
public class SrmShortMaterialInfoServer implements ISrmShortMaterialInfo {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmShortMaterialInfoServer.class);

	PreparedStatement statement = null;
	ResultSet res = null;

	@Autowired
	private ViewObject<SrmShortMaterialInfoEntity_HI> srmShortMaterialInfoDAO_HI;

	@Autowired
	private ViewObject<SrmShortMaterialInvInfoEntity_HI> srmShortMaterialInvInfoDAO_HI;

	@Autowired
	private ViewObject<SrmOnlyShortMaterialInfoEntity_HI> srmOnlyShortMaterialInfoDAO_HI;

	@Autowired
	private ViewObject<SrmShortMaterialInvEntity_HI> srmShortMaterialInvDAO_HI;

	@Autowired
	private BaseViewObject<SrmShortMaterialInfoEntity_HI_RO> srmShortMaterialInfoDAO_HI_RO;

	@Autowired
	private ViewObject<SaafDbConfigEntity_HI> saafDbConfigDAO_HI;

	@Autowired
	private ViewObject<SrmPoStarvingEntity_HI> srmPoStarvingDAO_HI;

	@Autowired
	private ViewObject<SrmPoStarvingHisEntity_HI> srmPoStarvingHisDAO_HI;

	@Autowired
	private ViewObject<SrmShortMaterialConfigEntity_HI> srmShortMaterialConfigDAO_HI;
	
	@Autowired
	private ViewObject<SrmShortMaterialWarmEntity_HI> srmShortMaterialWarmDAO_HI;

	@Override
	public boolean saveU9Data() {
		Connection con = null;
		
		String jsonStr = "{\"PLAN_DATE\":\"\",\"PLAN_TYPE\":\"\",\"Limit\":\"2\"}";
		JSONObject parseObject = JSON.parseObject(U9IntfUtils.invokeService(jsonStr, U9IntfUtils.STARVING_TRANS_CODE));
		if (null != parseObject && null != parseObject.get("RetCode") && "200".equals(parseObject.get("RetCode"))) {
			try {
				con = this.getU9Db();
				if(this.saveShortMaterialInv(con)){
					return this.saveU9ShortMaterial(con);
				}

			} catch (Exception e) {
				//e.printStackTrace();
			}

		}
		
		return false;
	}
	
	public boolean saveU9ShortMaterial(Connection con){
		try {
			statement = con.prepareStatement(this.getQueryConfig());
			res = statement.executeQuery();

			SrmShortMaterialInfoEntity_HI row = null;
			List<SrmShortMaterialInfoEntity_HI> rows = new ArrayList<SrmShortMaterialInfoEntity_HI>();
			while (res.next()) {
				row = new SrmShortMaterialInfoEntity_HI();
				row.setDocno(res.getString("docNo"));
				row.setDoctype(res.getString("doctype"));
				row.setDocstate(res.getInt("docstate"));
				row.setIsholdrelease(res.getInt("isholdrelease"));
				row.setMoorg(res.getString("moorg"));
				row.setDoclineno(res.getInt("doclineno"));
				row.setItemcode(res.getString("itemcode"));
				row.setItemname(res.getString("itemname"));
				row.setBomreqqty(res.getBigDecimal("bomreqqty"));
				row.setIssuedqty(res.getBigDecimal("issuedqty"));
				row.setActualreqdate(res.getDate("actualreqdate"));
				row.setDeliverldtime(res.getInt("deliverldtime"));
				row.setItemformattribute(res.getInt("itemformattribute"));
				row.setIsinheritbommasterno(res.getInt("isinheritbommasterno"));
				if (res.getInt("isinheritbommasterno") == 0) {
					row.setSeibancode("");
				} else {
					row.setSeibancode(res.getString("seibancode"));
				}

				row.setPurchasebatchqty(res.getBigDecimal("purchasebatchqty"));
				row.setOperatorcode(res.getString("operatorcode"));
				row.setCreationDate(new Date());
				row.setMostartdate(res.getDate("mostartdate"));
				row.setRtngoodstype(res.getInt("RTNGoodsType"));
				row.setActqty(res.getBigDecimal("actqty"));
				row.setDemandcode(res.getString("demandcode"));
				row.setMrpcode(res.getString("mrpcode"));
				row.setItemflag(res.getString("itemflag"));
				row.setWhName(res.getString("whName"));
				row.setItemFormAttributeName(res.getString("itemFormAttributeName"));
				row.setRTNGoodsTypeName(res.getString("rTNGoodsTypeName"));
				row.setGjfl(res.getString("gjfl"));
				row.setGjfln(res.getString("gjfln"));
				row.setBlfl(res.getString("blfl"));
				row.setWhman(res.getString("whman"));
				row.setWhmanname(res.getString("whmanname"));
				row.setProductqty(res.getBigDecimal("productqty"));
				row.setNotcompleteqty(res.getBigDecimal("notcompleteqty"));
				row.setMoitemcode(res.getString("moitemcode"));
				row.setMoitemname(res.getString("moitemname"));
				row.setProductdept(res.getString("productdept"));
						
				
				rows.add(row);

			}

			if (rows.size() > 0) {
				srmShortMaterialInfoDAO_HI.save(rows);
				return true;

			}

		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			//e.printStackTrace();
		} finally {
			try {
				if (res != null)
					res.close();
				if (statement != null)
					statement.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return false;
		
	}

	@Override
	public void saveShortMaterialInfo() throws Exception {
		if (this.saveOnlyShortMaterialInv())
			this.saveCalcMaterialInv();

	}

	public void saveCalcMaterialInv() {
		SrmShortMaterialInvInfoEntity_HI row = null;
		List<SrmShortMaterialInvInfoEntity_HI> rows = new ArrayList<SrmShortMaterialInvInfoEntity_HI>();
		List<SrmShortMaterialInfoEntity_HI_RO> lists = null;

		try {
			lists = srmShortMaterialInfoDAO_HI_RO
					.findList(SrmShortMaterialInfoEntity_HI_RO.QUERY_AND_CALC_MATERIAL_INV);

			int flag = -1;
			List<String> items = new ArrayList<String>();
			BigDecimal midqty = null;

			for (SrmShortMaterialInfoEntity_HI_RO entity : lists) {

				if (items.contains(entity.getItemcode())) {
					if (flag > 0) {
						entity.setShortqty(entity.getShortqty().multiply(new BigDecimal(-1)));
					} else {
						if (midqty.subtract(entity.getShortqty()).compareTo(BigDecimal.ZERO) == -1) {
							flag = 1;
						}
						entity.setShortqty(midqty.subtract(entity.getShortqty()));
						midqty = entity.getShortqty();
					}

				} else {
					if (items.size() > 0) {
						flag = -1;
					}

					items.clear();
					items.add(entity.getItemcode());
					if (entity.getMidqty().subtract(entity.getShortqty()).compareTo(BigDecimal.ZERO) == -1) {
						flag = 1;
					} else {
						flag = -1;
					}

					entity.setShortqty(entity.getMidqty().subtract(entity.getShortqty())); // <0为缺料
					midqty = entity.getShortqty();

				}

				row = new SrmShortMaterialInvInfoEntity_HI();
				row.setInstId(entity.getInst_id());
				row.setInstName(entity.getMoOrg());
				row.setDocno(entity.getDocNo());
				row.setItemcode(entity.getItemcode());
				row.setItemId(entity.getItemid());
				row.setItemname(entity.getItemname());			
				row.setMostartdate(new SimpleDateFormat("yyyy-MM-dd").parse(entity.getMoStartDate()));
				row.setCalcdate(entity.getCalcDate());
				row.setActqty(entity.getActqty());
				row.setIssuedqty(entity.getIssuedqty());

				row.setInvqty(entity.getInvqty());
				row.setMidqty(entity.getMidqty());
				row.setActqty(entity.getActqty());
				row.setIssuedqty(entity.getIssuedqty());

				row.setDoctype(entity.getDocType());
				row.setSeibancode(entity.getSeibancode());
				row.setIsholdrelease(
						Integer.valueOf(entity.getIsHoldRelease() == null ? "1" : entity.getIsHoldRelease()));
				row.setDocstate(entity.getDocstate());
				row.setPurchasebatchqty(entity.getPurchasebatchqty());
				row.setLastUpdatedBy(-1);
				row.setLastUpdateDate(new Date());
				row.setCreationDate(new Date());
				row.setShortqty(entity.getShortqty());
				row.setEmployeeId(entity.getEmployeeId());
				row.setWhName(entity.getWhname());
				row.setItemFormAttributeName(entity.getItemformattributename());
				row.setRTNGoodsTypeName(entity.getRtngoodstypename());
				row.setOperatorUserId(-1);
				row.setMrpcode(entity.getMrpcode());
				row.setDeliverldtime(entity.getDeliverldtime());
				row.setItemflag(entity.getItemflag());
				row.setUnit(entity.getUnit());

				row.setGjfl(entity.getGjfl());
				row.setGjfln(entity.getGjfln());
				row.setBlfl(entity.getBlfl());
				row.setWhName(entity.getWhman());
				row.setWhmanname(entity.getWhmanname());
				row.setOperatorcode(entity.getOperatorcode());
				row.setCategoryCode(entity.getCategoryCode());
				row.setCategoryName(entity.getCategoryName());
				row.setDemandcode(entity.getDemandcode());
				row.setTotalActqty(entity.getTotalActqty());
				row.setTotalIssuedqty(entity.getTotalIssuedqty());
				row.setRtngoodstype(entity.getRtngoodstype());				
				row.setProductqty(entity.getProductqty());
				row.setNotcompleteqty(entity.getNotcompleteqty());
				row.setMoitemcode(entity.getMoitemcode());
				row.setMoitemname(entity.getMoitemname());
				row.setProductdept(entity.getProductdept());
			

				rows.add(row);

			}

			if (rows.size() > 0) {
				srmShortMaterialInvInfoDAO_HI.save(rows);
				this.savePoStarving();
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public boolean saveOnlyShortMaterialInv() {
		SrmOnlyShortMaterialInfoEntity_HI row = null;
		List<SrmOnlyShortMaterialInfoEntity_HI> rows = new ArrayList<SrmOnlyShortMaterialInfoEntity_HI>();
		List<SrmShortMaterialInfoEntity_HI_RO> lists = null;

		try {
			lists = srmShortMaterialInfoDAO_HI_RO
					.findList(SrmShortMaterialInfoEntity_HI_RO.QUERY_AND_ONLY_MATERIAL_INV);

			int flag = -1;
			List<String> items = new ArrayList<String>();
			BigDecimal invqty = null;

			for (SrmShortMaterialInfoEntity_HI_RO entity : lists) {

				if (items.contains(entity.getItemcode())) {
					if (flag > 0) {
						entity.setShortqty(entity.getNeedQty().multiply(new BigDecimal(-1)));
					} else {
						if (invqty.subtract(entity.getNeedQty()).compareTo(BigDecimal.ZERO) == -1) {
							flag = 1;
						}
						entity.setShortqty(invqty.subtract(entity.getNeedQty()));
						invqty = entity.getShortqty();
					}

				} else {
					if (items.size() > 0) {
						flag = -1;
					}

					items.clear();
					items.add(entity.getItemcode());
					if (entity.getInvqty().subtract(entity.getNeedQty()).compareTo(BigDecimal.ZERO) == -1) {
						flag = 1;
					} else {
						flag = -1;
					}

					entity.setShortqty(entity.getInvqty().subtract(entity.getNeedQty()));
					invqty = entity.getShortqty();

				}

				row = new SrmOnlyShortMaterialInfoEntity_HI();
				row.setCheckdate(new Date());
				row.setInstId(entity.getInst_id());
				row.setInstName(entity.getMoOrg());
				row.setDocno(entity.getDocNo());
				row.setItemcode(entity.getItemcode());
				row.setItemId(entity.getItemid());
				row.setItemname(entity.getItemname());				
				row.setMostartdate(new SimpleDateFormat("yyyy-MM-dd").parse(entity.getMoStartDate()));
				row.setCalcdate(entity.getCalcDate());
				row.setActqty(entity.getActqty());
				row.setIssuedqty(entity.getIssuedqty());
				row.setInvqty(entity.getInvqty());
				row.setDoctype(entity.getDocType());
				row.setSeibancode(entity.getSeibancode());				
				row.setIsholdrelease(
						Integer.valueOf(entity.getIsHoldRelease() == null ? "1" : entity.getIsHoldRelease()));
				row.setDocstate(entity.getDocstate());
				row.setPurchasebatchqty(entity.getPurchasebatchqty());
				row.setShortqty(entity.getShortqty());
				row.setDemandcode(entity.getDemandcode());
				row.setMrpcode(entity.getMrpcode());
				row.setItemflag(entity.getItemflag());
				row.setRtngoodstype(entity.getRtngoodstype());
				row.setEmployeeId(entity.getEmployeeId());
				row.setLastUpdatedBy(-1);
				row.setLastUpdateLogin(-1);
				row.setLastUpdateDate(new Date());
				row.setCreationDate(new Date());
				row.setDeliverldtime(entity.getDeliverldtime());
				row.setOperatorUserId(-1);
				row.setWhName(entity.getWhname());
				row.setItemFormAttributeName(entity.getItemformattributename());
				row.setRTNGoodsTypeName(entity.getRtngoodstypename());				
				row.setUnit(entity.getUnit());
				row.setGjfl(entity.getGjfl());
				row.setGjfln(entity.getGjfln());
				row.setBlfl(entity.getBlfl());
				row.setWhName(entity.getWhman());
				row.setWhmanname(entity.getWhmanname());
				row.setOperatorcode(entity.getEmployeeName());
				row.setCategoryCode(entity.getCategoryCode());
				row.setCategoryName(entity.getCategoryName() );
				row.setItemProp(entity.getItemProp());
				row.setItemPropDesc(entity.getItemPropDesc());
				row.setProductqty(entity.getProductqty());
				row.setNotcompleteqty(entity.getNotcompleteqty());
				row.setMoitemcode(entity.getMoitemcode());
				row.setMoitemname(entity.getMoitemname());
				row.setProductdept(entity.getProductdept());
	

				rows.add(row);

			}

			if (rows.size() > 0) {
				srmOnlyShortMaterialInfoDAO_HI.save(rows);
				return true;

			}

		} catch (Exception e) {
			e.getMessage();
		}
		return false;

	}

	public void savePoStarving() {
		SrmPoStarvingEntity_HI row = null;
		List<SrmShortMaterialInfoEntity_HI_RO> lists = null;

		try {
			lists = srmShortMaterialInfoDAO_HI_RO
					.findList(SrmShortMaterialInfoEntity_HI_RO.TO_DELIVERY_NOTICE_ORDER_SQL);
			int i = 0;
			for (SrmShortMaterialInfoEntity_HI_RO entity : lists) {
				row = new SrmPoStarvingEntity_HI();

				if (entity.getQty().compareTo(BigDecimal.ZERO) > 0) {
					row.setPoStarvingId(++i);
					row.setShortCheckDate(entity.getCheckDate());
					row.setWipEntityNumber(entity.getDocNo());
					row.setInstId(Integer.valueOf(entity.getInst_id() == null ? "-1" : entity.getInst_id()));
					row.setItemId(entity.getItemid());
					row.setCreatedBy(1);
					row.setLastUpdatedBy(1);
					row.setCreationDate(new Date());
					row.setLastUpdateDate(new Date());
					row.setNeedQuantity(entity.getQty());
					row.setNeedByDate(entity.getCalcDate());
					row.setItemCode(entity.getItemcode());
					row.setEmployeeId(entity.getEmployeeId());
					row.setCategoryCode(entity.getCategoryCode());

					if (null != entity.getMrpcode() && "M01".equals(entity.getMrpcode())) {
						row.setInstId(90);
					} else if (null != entity.getMrpcode() && "M02".equals(entity.getMrpcode())) {
						row.setInstId(91);
					}
					row.setDeliverySiteId(Integer.valueOf(entity.getInst_id()) );

					srmPoStarvingDAO_HI.save(row);
				}

			}

			if (i > 0) {
				// 存放10天记录
				this.savePoStarvingHis();
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}
	
	public void saveSuperQty(){
		srmShortMaterialInfoDAO_HI.executeSqlUpdate(" truncate table srm_short_material_superqty ");
		srmShortMaterialInfoDAO_HI.executeSqlUpdate(
				" insert into srm_short_material_superqty "
			  + " SELECT a.CREATION_DATE, "
			  + "        a.inst_id, "
			  + "        a.inst_name ,  "
			  + "        case when a.mrpcode = 'M01' then 90 "
			  + "             when a.mrpcode = 'M02' then 91 "
			  + "             else a.inst_id end deliveryInstId, "
			  + "        case when a.mrpcode = 'M01' then '管道厂' "
			  + "             when a.mrpcode = 'M02' then '注塑厂' "
			  + "             else a.inst_name end deliveryInst, "
			  + "        a.itemcode,  "
			  + "        a.itemname,  "
			  + "        a.mrpcode, "
			  + "        sum(if(Ifnull(a.SHORTQTY - a.SHORTQTY*2,0)<0,0,Ifnull(a.SHORTQTY - a.SHORTQTY*2,0) )) qty, "
			  + "        case when a.mrpcode = 'M01'  THEN  "
			  + "                  (SELECT Ifnull(Sum(e.QTY),0) - Ifnull(Sum(e.delivery_qty),0) "
			  + "                     FROM srm_po_notice_qty_tmp e  "
			  + "                    WHERE e.inst_id = 90  "
			  + "                      AND e.delivery_site_id = deliveryInstId "
			  + "                      AND e.item_id =  a.itemid "
			  + "                      AND Ifnull(e.SPECIAL_USE_NUM,0) = Ifnull(a.SEIBANCODE,0) )  "
			  + "             when a.mrpcode = 'M02'  THEN  "
			  + "                  (SELECT Ifnull(Sum(e.QTY),0) - Ifnull(Sum(e.delivery_qty),0) "
			  + "                     FROM srm_po_notice_qty_tmp e  "
			  + "                    WHERE e.inst_id = 91 "
			  + "                      AND e.delivery_site_id = deliveryInstId "
			  + "                      AND e.item_id =  a.ITEMID "
			  + "                      AND Ifnull(e.SPECIAL_USE_NUM,0) = Ifnull(a.SEIBANCODE,0) ) "
			  + "            else  (SELECT Ifnull(Sum(e.QTY),0) - Ifnull(Sum(e.delivery_qty),0) "
			  + "                      FROM srm_po_notice_qty_tmp e "
			  + "                     WHERE e.inst_id = a.inst_id "
			  + "                       AND e.item_id =  a.itemid "
			  + "                       AND Ifnull(e.SPECIAL_USE_NUM,0) = Ifnull(a.SEIBANCODE,0) ) end  midqty, "
			  + "       case when a.mrpcode = 'M01'  THEN "
			  + "                  (SELECT Ifnull(Sum(e.QTY),0) - Ifnull(Sum(e.delivery_qty),0) "
			  + "                     FROM srm_po_notice_qty_tmp e "
			  + "                    WHERE e.inst_id = 90 "
			  + "                      AND e.delivery_site_id = deliveryInstId "
			  + "                      AND e.item_id =  a.itemid "
			  + "                      AND Ifnull(e.SPECIAL_USE_NUM,0) = Ifnull(a.SEIBANCODE,0) ) "
			  + "                        - sum(if(Ifnull(a.SHORTQTY - a.SHORTQTY*2,0)<0,0,Ifnull(a.SHORTQTY - a.SHORTQTY*2,0) )) "
			  + "            when a.mrpcode = 'M02'  THEN "
			  + "                  (SELECT Ifnull(Sum(e.QTY),0) - Ifnull(Sum(e.delivery_qty),0) "
			  + "                     FROM srm_po_notice_qty_tmp e "
			  + "                    WHERE e.inst_id = 91  "
			  + "                      AND e.delivery_site_id = deliveryInstId "
			  + "                      AND e.item_id =  a.ITEMID "
			  + "                      AND Ifnull(e.SPECIAL_USE_NUM,0) = Ifnull(a.SEIBANCODE,0) ) "
			  + "                        - sum(if(Ifnull(a.SHORTQTY - a.SHORTQTY*2,0)<0,0,Ifnull(a.SHORTQTY - a.SHORTQTY*2,0) )) "
			  + "            else (SELECT Ifnull(Sum(e.QTY),0) - Ifnull(Sum(e.delivery_qty),0)  "
			  + "                    FROM srm_po_notice_qty_tmp e "
			  + "                   WHERE e.inst_id = a.inst_id "
			  + "                     AND e.item_id =  a.itemid "
			  + "                     AND Ifnull(e.SPECIAL_USE_NUM,0) = Ifnull(a.SEIBANCODE,0) ) "
			  + "                       - sum(if(Ifnull(a.SHORTQTY - a.SHORTQTY*2,0)<0,0,Ifnull(a.SHORTQTY - a.SHORTQTY*2,0) )) end  midqty1, "
			  + "         a.operatorcode "
			  + " FROM SRM_ONLY_SHORT_MATERIAL_INFO a  "
			  + " where a.CREATION_DATE = CURDATE() "
			  + " group by a.CREATION_DATE, "
			  + "          a.inst_id, "
			  + "          a.inst_name, "
			  + "          a.itemcode, "
			  + "          a.operatorcode,"
			  + "          a.itemname, "
			  + "          a.mrpcode, "
			  + "          deliveryInstId, "
			  + "          deliveryInst ");
		
	}

	public void savePoStarvingHis() {
		SrmPoStarvingHisEntity_HI row = null;
		StringBuffer sb = new StringBuffer(
				" select datediff(Curdate(),min(CREATION_DATE))  qty from SRM_PO_STARVING_HIS ");
		try {
			SrmShortMaterialInfoEntity_HI_RO obj = srmShortMaterialInfoDAO_HI_RO.findList(sb).get(0);
			if (null != obj && null != obj.getQty() && obj.getQty().compareTo(new BigDecimal(10)) == 1) {
				srmPoStarvingDAO_HI.executeSqlUpdate("delete from  SRM_PO_STARVING_HIS ");
			}

			List<SrmPoStarvingEntity_HI> lists = srmPoStarvingDAO_HI.findByProperty(new HashMap<String, Object>());

			int i = 0;
			for (SrmPoStarvingEntity_HI entity : lists) {
				row = new SrmPoStarvingHisEntity_HI();
				row.setHisId(++i);
				row.setPoStarvingId(entity.getPoStarvingId());
				row.setShortCheckDate(entity.getShortCheckDate());
				row.setWipEntityNumber(entity.getWipEntityNumber());
				row.setInstId(entity.getInstId());
				row.setItemId(entity.getItemId());
				row.setCreatedBy(1);
				row.setLastUpdatedBy(1);
				row.setCreationDate(new Date());
				row.setLastUpdateDate(new Date());
				row.setOperatorUserId(1);
				row.setNeedQuantity(entity.getNeedQuantity());
				row.setNeedByDate(entity.getNeedByDate());
				row.setEmployeeId(entity.getEmployeeId());
				row.setCategoryCode(entity.getCategoryCode());
				row.setDeliverySiteId(entity.getDeliverySiteId());

				srmPoStarvingHisDAO_HI.save(row);

			}
			
			this.savePreWarning();

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public boolean savePreWarning() {
		List<SrmShortMaterialInfoEntity_HI_RO> lists = null;
		
		List<String> ids = new ArrayList<String>();
		List<SrmShortMaterialWarmEntity_HI> rows = new ArrayList<SrmShortMaterialWarmEntity_HI>();
		
		try {
			SrmShortMaterialWarmEntity_HI row = null;
			
			lists = srmShortMaterialInfoDAO_HI_RO.findList(SrmShortMaterialInfoEntity_HI_RO.WARNING_QUERY_SQL);
			long day = 0;
			int flag = -1 , num = -1;
			
			int size = lists.size();  
			for(int i = 0; i<size;i++ ){
				day = ( lists.get(i).getCalcDate().getTime() - lists.get(i).getCheckDate().getTime() ) /(24*60*60*1000);
				if( ids.contains(lists.get(i).getWarmid())){
					//最后一次
					if( i == size -1){
						row.setT1InvQty(row.getT0InvQty().subtract(row.getT0Qty()) );
						row.setT2InvQty(row.getT1InvQty().subtract(row.getT1Qty()) );
						row.setT3InvQty(row.getT2InvQty().subtract(row.getT2Qty()) );
						row.setT4InvQty(row.getT3InvQty().subtract(row.getT3Qty()) );
						row.setT5InvQty(row.getT4InvQty().subtract(row.getT4Qty()) );
						row.setT6InvQty(row.getT5InvQty().subtract(row.getT5Qty()) );
						row.setT7InvQty(row.getT6InvQty().subtract(row.getT6Qty()) );
						
						row.setT0DifferQty(row.getT0InvQty().subtract(row.getT0Qty()));
						row.setT1DifferQty(row.getT1InvQty().subtract(row.getT1Qty()));
						row.setT2DifferQty(row.getT2InvQty().subtract(row.getT2Qty()));
						row.setT3DifferQty(row.getT3InvQty().subtract(row.getT3Qty()));
						row.setT4DifferQty(row.getT4InvQty().subtract(row.getT4Qty()));
						row.setT5DifferQty(row.getT5InvQty().subtract(row.getT5Qty()));
						row.setT6DifferQty(row.getT6InvQty().subtract(row.getT6Qty()));
						row.setT7DifferQty(row.getT7InvQty().subtract(row.getT7Qty()));		
						
						row.setTotal3daysQty(row.getT0Qty().add(row.getT1Qty()).add(row.getT2Qty()));
						row.setTotal3daysDifferQty(row.getT2DifferQty());
						
						row.setOperatorUserId(1);	
						
						if(row.getT0DifferQty().compareTo(BigDecimal.ZERO)== -1 ){
							row.setUrgent("特急");
						}else if(row.getT1DifferQty().compareTo(BigDecimal.ZERO)== -1 ){
							row.setUrgent("紧急");
						}else if(row.getT2DifferQty().compareTo(BigDecimal.ZERO)== -1 ){
							row.setUrgent("急");
						}
						
						num = -1;
						rows.add(row);
					}
				}else{
					if(flag > 0 && null != row ){
						row.setT1InvQty(row.getT0InvQty().subtract(row.getT0Qty()) );
						row.setT2InvQty(row.getT1InvQty().subtract(row.getT1Qty()) );
						row.setT3InvQty(row.getT2InvQty().subtract(row.getT2Qty()) );
						row.setT4InvQty(row.getT3InvQty().subtract(row.getT3Qty()) );
						row.setT5InvQty(row.getT4InvQty().subtract(row.getT4Qty()) );
						row.setT6InvQty(row.getT5InvQty().subtract(row.getT5Qty()) );
						row.setT7InvQty(row.getT6InvQty().subtract(row.getT6Qty()) );
						
						row.setT0DifferQty(row.getT0InvQty().subtract(row.getT0Qty()));
						row.setT1DifferQty(row.getT1InvQty().subtract(row.getT1Qty()));
						row.setT2DifferQty(row.getT2InvQty().subtract(row.getT2Qty()));
						row.setT3DifferQty(row.getT3InvQty().subtract(row.getT3Qty()));
						row.setT4DifferQty(row.getT4InvQty().subtract(row.getT4Qty()));
						row.setT5DifferQty(row.getT5InvQty().subtract(row.getT5Qty()));
						row.setT6DifferQty(row.getT6InvQty().subtract(row.getT6Qty()));
						row.setT7DifferQty(row.getT7InvQty().subtract(row.getT7Qty()));		
						
						row.setTotal3daysQty(row.getT0Qty().add(row.getT1Qty()).add(row.getT2Qty()));
						row.setTotal3daysDifferQty(row.getT2DifferQty());
						
						row.setOperatorUserId(1);
						
						if(row.getT0DifferQty().compareTo(BigDecimal.ZERO)== -1 ){
							row.setUrgent("特急");
						}else if(row.getT1DifferQty().compareTo(BigDecimal.ZERO)== -1 ){
							row.setUrgent("紧急");
						}else if(row.getT2DifferQty().compareTo(BigDecimal.ZERO)== -1 ){
							row.setUrgent("急");
						}
						
						num = -1;
						rows.add(row);
					}
					
					row = new SrmShortMaterialWarmEntity_HI();
					row.setT0InvQty(lists.get(i).getInvqty());
					row.setT0Qty(new BigDecimal(0));	
					row.setT1Qty(new BigDecimal(0));
					row.setT2Qty(new BigDecimal(0));
					row.setT3Qty(new BigDecimal(0));
					row.setT4Qty(new BigDecimal(0));
					row.setT5Qty(new BigDecimal(0));
					row.setT6Qty(new BigDecimal(0));
					row.setT7Qty(new BigDecimal(0));
					
					ids.add(lists.get(i).getWarmid());
					
					if(-1 ==flag)flag = 1;
					
				}
				
				if(null != row){
					if( 0 == day){
						row.setT0Qty(lists.get(i).getOrderNoSendQty());			
					}else if( 1 == day ){
						row.setT1Qty(lists.get(i).getOrderNoSendQty());
					}else if( 2 == day ){
						row.setT2Qty(lists.get(i).getOrderNoSendQty());						
					}else if( 3 == day ){
						row.setT3Qty(lists.get(i).getOrderNoSendQty());						
					}else if( 4 == day ){
						row.setT4Qty(lists.get(i).getOrderNoSendQty());						
					}else if( 5 == day){
						row.setT5Qty(lists.get(i).getOrderNoSendQty());						
					}else if( 6 == day ){
						row.setT6Qty(lists.get(i).getOrderNoSendQty());					
					}else if( 7 == day ){
						row.setT7Qty(lists.get(i).getOrderNoSendQty());						
					}
					
				}
				
				if(-1 == num){
					row.setInstId(lists.get(i).getInst_id());
					row.setInstName(lists.get(i).getMoOrg());
					row.setItemcode(lists.get(i).getItemcode());
					row.setItemname(lists.get(i).getItemname());
					row.setSeibancode(lists.get(i).getSeibancode());
					row.setUnit(lists.get(i).getUnit());
					row.setItemflag(lists.get(i).getItemflag());
					row.setGjfl(lists.get(i).getGjfl());
					row.setGjfln(lists.get(i).getGjfln());
					row.setBlfl(lists.get(i).getBlfl());
					row.setWhman(lists.get(i).getWhman());
					row.setWhmanname(lists.get(i).getWhmanname());
					row.setEmployee(lists.get(i).getOperatorcode());
					row.setCategoryCode(lists.get(i).getCategoryCode() );
					row.setCategoryName(lists.get(i).getCategoryName() );
					row.setItemProp(lists.get(i).getItemProp());
					row.setItemPropDesc(lists.get(i).getItemPropDesc());
					row.setMrpcode(lists.get(i).getMrpcode() );
					num = 1;
	
				}
			
			}
			
			if(rows.size()>0){
				srmShortMaterialWarmDAO_HI.save(rows);
			}
			

		} catch (Exception e) {
			e.getMessage();
		}
		return false;

	}

	public boolean saveShortMaterialInv(Connection con) {
		cleanTable();
		try {
			statement = con.prepareStatement("select * from View_SRM_Short_Material_INV ");
			res = statement.executeQuery();

			SrmShortMaterialInvEntity_HI row = null;
			List<SrmShortMaterialInvEntity_HI> rows = new ArrayList<SrmShortMaterialInvEntity_HI>();
			while (res.next()) {
				row = new SrmShortMaterialInvEntity_HI();
				row.setMaterialId(res.getString("MATERIAL_ID"));
				row.setMaterialCode(res.getString("MATERIAL_CODE"));
				row.setInvCode(res.getString("INV_CODE"));
				row.setInvId(res.getString("INV_ID"));
				row.setQty(res.getBigDecimal("qty"));
				row.setUseAbleQty(res.getBigDecimal("USE_ABLE_QTY"));
				row.setInvorg(res.getString("invorg"));
				row.setConsiderdelivplan(res.getString("considerdelivplan"));
				row.setCreationDate(new Date());
				row.setSeibanno(res.getString("seibanno"));
				row.setOperatorUserId(-1);
				row.setWhname(res.getString("whname"));
				row.setEffective(res.getString("effective"));
				
				rows.add(row);

			}

			if (rows.size() > 0) {
				srmShortMaterialInvDAO_HI.save(rows);
				return true;
			}

		} catch (SQLException e) {
			LOGGER.info(e.getMessage());
			//e.printStackTrace();
		}

		return false;
	}

	@Override
	public Pagination<SrmShortMaterialInfoEntity_HI_RO> findShortMaterielList(JSONObject jsonParams, Integer pageIndex,
			Integer pageRows) throws Exception {
		try {
			StringBuffer queryString = new StringBuffer(SrmShortMaterialInfoEntity_HI_RO.QUERY_SQL);
			Map<String, Object> map = new HashMap<String, Object>();

			SaafToolUtils.parperParam(jsonParams, "a.inst_id", "moorg", queryString, map, "=");
			SaafToolUtils.parperParam(jsonParams, "a.itemcode", "itemCode", queryString, map, "like");
			SaafToolUtils.parperParam(jsonParams, "a.category_Code", "categoryCode", queryString, map, "=");

			String version = jsonParams.getString("version");
			if (null == version || "1".equals(version)) {
				queryString.append(" and a.CREATION_DATE = Curdate()  ");
			} else if ("0".equals(version)) {
				queryString.append(" and a.CREATION_DATE = CURDATE() + INTERVAL-(1)day  ");
			}
			
			String isShortMaterials = jsonParams.getString("isShortMaterials");
			if (null != isShortMaterials && "0".equals(isShortMaterials)) {
				queryString.append(" and (a.SHORTQTY - a.SHORTQTY*2) >0  ");
			}

			queryString.append(" GROUP BY a.CREATION_DATE, " 
		                              + " a.inst_id, " 
					                  + " a.inst_name, " 
		                              + " a.itemcode, "
					                  + " a.itemid, " 
		                              + " a.calcdate, " 
					                  + " a.seibancode , " 
		                              + " a.isholdrelease, " 
					                  + " a.docstate , "
					                  + " a.unit, " 
					                  + " a.INVQTY, "
					                  + " a.midqty, "
					                  + " a.category_Code, "
					                  + " a.category_Name, "
					                  + " a.TOTAL_ACTQTY, "
					                  + " a.TOTAL_ISSUEDQTY  ");

			queryString.append(" order by moOrg ,a.itemcode, qty ");

			Pagination<SrmShortMaterialInfoEntity_HI_RO> resultList = srmShortMaterialInfoDAO_HI_RO
					.findPagination(queryString, map, pageIndex, pageRows);

			return resultList;
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new Exception(e);
		}

	}

	@Override
	public Pagination<SrmShortMaterialInfoEntity_HI_RO> findOnlyShortMaterielList(JSONObject jsonParams,
			Integer pageIndex, Integer pageRows) throws Exception {
		try {
			StringBuffer queryString = new StringBuffer(SrmShortMaterialInfoEntity_HI_RO.ONLY_SHORT_MATERIAL_QTY);
			Map<String, Object> map = new HashMap<String, Object>();

			SaafToolUtils.parperParam(jsonParams, "a.inst_id", "moorg", queryString, map, "=");
			SaafToolUtils.parperParam(jsonParams, "a.itemcode", "itemCode", queryString, map, "like");
			SaafToolUtils.parperParam(jsonParams, "a.docno", "docno", queryString, map, "like");
			SaafToolUtils.parperParam(jsonParams, "a.category_Code", "categoryCode", queryString, map, "=");			
			SaafToolUtils.parperParam(jsonParams, "a.operatorcode", "buyerName", queryString, map, "=");

			String startDate = null;
            String endDate = null;
            if (jsonParams.getString("startDate") != null && !"".equals(jsonParams.getString("startDate").trim())) {
                startDate = jsonParams.getString("startDate");
            }
            if (jsonParams.getString("endDate") != null && !"".equals(jsonParams.getString("endDate").trim())) {
                endDate = jsonParams.getString("endDate");
            }

            if (startDate != null && endDate != null) {
            	queryString.append(" and a.mostartdate between :startDate and :endDate");
                map.put("startDate", startDate);
                map.put("endDate", endDate);
            } else if (startDate != null && endDate == null) {
            	queryString.append(" and a.mostartdate >= :startDate");
                map.put("startDate", startDate);
            } else if (endDate != null && startDate == null) {
            	queryString.append(" and a.mostartdate <= :endDate");
                map.put("endDate", endDate);
            }

			String version = jsonParams.getString("version");
			if (null == version || "1".equals(version)) {
				queryString.append(" and a.CREATION_DATE = Curdate()  ");
			} else if ("0".equals(version)) {
				queryString.append(" and a.CREATION_DATE = CURDATE() + INTERVAL-(1)day  ");
			}
			
			String isShortMaterials = jsonParams.getString("isShortMaterials");
			if (null != isShortMaterials && "0".equals(isShortMaterials)) {
				queryString.append(" and a.SHORTQTY <0  ");
			}			

			queryString.append(" order by moOrg , a.itemcode , a.calcdate, a.mostartdate ");

			Pagination<SrmShortMaterialInfoEntity_HI_RO> resultList = srmShortMaterialInfoDAO_HI_RO
					.findPagination(queryString, map, pageIndex, pageRows);

			return resultList;
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	public Pagination<SrmShortMaterialInfoEntity_HI_RO> findU9ReadyMaterielList(JSONObject jsonParams,
			Integer pageIndex, Integer pageRows) throws Exception {
		try {
			StringBuffer queryString = new StringBuffer(SrmShortMaterialInfoEntity_HI_RO.QUERY_U9_READY_MATERIAL);
			Map<String, Object> map = new HashMap<String, Object>();

			SaafToolUtils.parperParam(jsonParams, "c.inst_id", "moorg", queryString, map, "=");
			SaafToolUtils.parperParam(jsonParams, "a.itemcode", "itemCode", queryString, map, "like");
			SaafToolUtils.parperParam(jsonParams, "a.docno", "docno", queryString, map, "like");

			// String creationDate = null;
			// if (jsonParams.getString("creationDate") != null
			// && !"".equals(jsonParams.getString("creationDate").trim())) {
			// creationDate = jsonParams.getString("creationDate");
			// }
			// if (null != creationDate) {
			// queryString.append(" and a.checkdate = :creationDate");
			// map.put("creationDate", creationDate);
			// }
			String version = jsonParams.getString("version");
			if (null == version || "1".equals(version)) {
				queryString.append(" and a.CREATION_DATE = Curdate()  ");
			} else if ("0".equals(version)) {
				queryString.append(" and a.CREATION_DATE = CURDATE() + INTERVAL-(1)day  ");
			}

			queryString.append(" order by c.inst_id, a.docno,  orderNoSendQty desc ");

			Pagination<SrmShortMaterialInfoEntity_HI_RO> resultList = srmShortMaterialInfoDAO_HI_RO
					.findPagination(queryString, map, pageIndex, pageRows);

			return resultList;
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new Exception(e);
		}
	}
	
	@Override
	public Pagination<SrmShortMaterialInfoEntity_HI_RO> findShortMaterialWarm(JSONObject jsonParams, Integer pageIndex,
			Integer pageRows) throws Exception {
		try {
			StringBuffer queryString = new StringBuffer(SrmShortMaterialInfoEntity_HI_RO.QUERY_SHORT_MATERIAL_WARM);
			Map<String, Object> map = new HashMap<String, Object>();

			SaafToolUtils.parperParam(jsonParams, "a.inst_id", "moorg", queryString, map, "=");
			SaafToolUtils.parperParam(jsonParams, "a.itemcode", "itemCode", queryString, map, "like");
			SaafToolUtils.parperParam(jsonParams, "a.urgent", "urgent", queryString, map, "=");
			SaafToolUtils.parperParam(jsonParams, "a.employee", "buyerName", queryString, map, "=");

			String version = jsonParams.getString("version");
			if (null == version || "1".equals(version)) {
				queryString.append(" and a.CREATION_DATE = Curdate()  ");
			} else if ("0".equals(version)) {
				queryString.append(" and a.CREATION_DATE = CURDATE() + INTERVAL-(1)day  ");
			}

			queryString.append(" order by a.inst_id, a.itemcode ");

			Pagination<SrmShortMaterialInfoEntity_HI_RO> resultList = srmShortMaterialInfoDAO_HI_RO
					.findPagination(queryString, map, pageIndex, pageRows);

			return resultList;
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new Exception(e);
		}
	}
	
	@Override
	public List findOrders(JSONObject jsonParams) throws Exception {
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		try {
			StringBuffer queryString = new StringBuffer();
			queryString.append(SrmShortMaterialInfoEntity_HI_RO.QUERY_SHORT_MATERIAL_ORDERS);
			SaafToolUtils.parperParam(jsonParams, "a.INST_NAME", "moOrg", queryString, queryParamMap, "=");
			SaafToolUtils.parperParam(jsonParams, "a.ITEMCODE", "itemcode", queryString, queryParamMap, "=");
			SaafToolUtils.parperParam(jsonParams, "a.SEIBANCODE", "seibancode", queryString, queryParamMap, "=");

			String checkDate = null;
			if (jsonParams.getString("checkDate") != null
					&& !"".equals(jsonParams.getString("checkDate").trim())) {
				checkDate = jsonParams.getString("checkDate");
			}
			if (null != checkDate) {				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String format2 = format.format(new Date());
				if(checkDate.equals(format2)){
					queryString.append(" and a.CREATION_DATE = CURDATE() ");
				}else{
					queryString.append(" and a.CREATION_DATE = CURDATE() + INTERVAL-(1)day ");
				}
			}

			List<SrmShortMaterialInfoEntity_HI_RO> findList = srmShortMaterialInfoDAO_HI_RO
					.findList(queryString.toString(), queryParamMap);

			return findList;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	@Override
	public List findDeliveryOrder(JSONObject jsonParams) throws Exception {
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmShortMaterialInfoEntity_HI_RO.QUERY_DELIVERY_ORDER);
            SaafToolUtils.parperParam(jsonParams, "it.inst_name", "moOrg", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "item.ITEM_CODE", "itemcode", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "l.SPECIAL_USE_NUM", "seibancode", queryString, queryParamMap, "=");
            
            queryString.append("  group by dh.delivery_number, "
            		                   + " si.supplier_name, "
            		                   + " si.supplier_number,  "
            		                   + " dh.CREATION_DATE , "
            		                   + " it.inst_name , "
            		                   + " item.ITEM_CODE, "
            		                   + " item.ITEM_NAME , "
            		                   + " item.UNIT_OF_MEASURE_NAME , "
            		                   + " l.SPECIAL_USE_NUM  ");          
            queryString.append(" order by dh.CREATION_DATE ");

            List<SrmShortMaterialInfoEntity_HI_RO> findList = srmShortMaterialInfoDAO_HI_RO.findList(queryString.toString(), queryParamMap);
            
            return findList;
        } catch (Exception e) {
            throw new Exception(e);
        }
	}
	
	@Override
	public List findNoticeOrder(JSONObject jsonParams) throws Exception {
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmShortMaterialInfoEntity_HI_RO.QUERY_NOTICE_ORDERS);
            
            int flag = -1;
            if (jsonParams.containsKey("mrpcode") && !"".equals(jsonParams.get("mrpcode").toString())&&jsonParams.getString("mrpcode")!=null) {
            	Object attributeValue = jsonParams.get("mrpcode");
            	String attributeValueStr = SToolUtils.object2String(attributeValue);
            	if("M01".equals(attributeValueStr)){
            		queryString.append(" and a.inst_id = 90 ");
            		SaafToolUtils.parperParam(jsonParams, "d.inst_name", "moOrg", queryString, queryParamMap, "=");
            		++flag;
            	}else if("M02".equals(attributeValueStr)){
            		queryString.append(" and a.inst_id = 91 ");
            		SaafToolUtils.parperParam(jsonParams, "d.inst_name", "moOrg", queryString, queryParamMap, "=");
            		++flag ;
            	}
            }
            
            if(-1 == flag){
            	SaafToolUtils.parperParam(jsonParams, "b.inst_name", "moOrg", queryString, queryParamMap, "=");
            }          
            
            SaafToolUtils.parperParam(jsonParams, "c.ITEM_CODE", "itemcode", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "a.SPECIAL_USE_NUM", "seibancode", queryString, queryParamMap, "="); 
            
            return srmShortMaterialInfoDAO_HI_RO.findList(queryString.toString(), queryParamMap);

        } catch (Exception e) {
            throw new Exception(e);
        }
	}
	
	@Override
	public Pagination<SrmShortMaterialInfoEntity_HI_RO>  findNoticeOrderSuperQty(JSONObject jsonParams, Integer pageIndex,
			Integer pageRows) throws Exception {
		try {
			StringBuffer queryString = new StringBuffer(SrmShortMaterialInfoEntity_HI_RO.QUERY_SUPER_QTY);
			Map<String, Object> map = new HashMap<String, Object>();

			SaafToolUtils.parperParam(jsonParams, "a.inst_id", "moorg", queryString, map, "=");
			SaafToolUtils.parperParam(jsonParams, "a.itemcode", "itemCode", queryString, map, "like");
			SaafToolUtils.parperParam(jsonParams, "a.operatorcode", "buyerName", queryString, map, "=");

			queryString.append(" order by a.inst_id, a.itemcode ");

			Pagination<SrmShortMaterialInfoEntity_HI_RO> resultList = srmShortMaterialInfoDAO_HI_RO
					.findPagination(queryString, map, pageIndex, pageRows);

			return resultList;
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new Exception(e);
		}
	}

	public void cleanTable() {
		StringBuffer sb = new StringBuffer(
				" select datediff(max(CREATION_DATE),min(CREATION_DATE))  qty from SRM_SHORT_MATERIAL_INFO ");
		SrmShortMaterialInfoEntity_HI_RO obj = srmShortMaterialInfoDAO_HI_RO.findList(sb).get(0);

		// 保留两天数据
		if (null != obj && null != obj.getQty() && obj.getQty().compareTo(new BigDecimal(1)) == 0) {
			srmShortMaterialInfoDAO_HI.executeSqlUpdate(" truncate table SRM_ONLY_SHORT_MATERIAL_INFO_TMP ");
			srmShortMaterialInfoDAO_HI.executeSqlUpdate(" truncate table SRM_SHORT_MATERIAL_INV_INFO_TMP ");
			srmShortMaterialInfoDAO_HI.executeSqlUpdate(" truncate table SRM_SHORT_MATERIAL_INFO_TMP ");
			srmShortMaterialInfoDAO_HI.executeSqlUpdate(" truncate table srm_short_material_warm_tmp ");
			srmShortMaterialInvDAO_HI.executeSqlUpdate(" delete from SRM_Short_Material_inv where  CREATION_DATE <= CURDATE() + INTERVAL-(2)day ");

			srmShortMaterialInfoDAO_HI.executeSqlUpdate(
					" insert into SRM_ONLY_SHORT_MATERIAL_INFO_TMP select * from SRM_ONLY_SHORT_MATERIAL_INFO where creation_Date = CURDATE() + INTERVAL-(1)day ");
			srmShortMaterialInfoDAO_HI.executeSqlUpdate(" truncate table SRM_ONLY_SHORT_MATERIAL_INFO ");
			srmShortMaterialInfoDAO_HI.executeSqlUpdate(
					" insert into SRM_ONLY_SHORT_MATERIAL_INFO select * from SRM_ONLY_SHORT_MATERIAL_INFO_TMP  ");

			srmShortMaterialInfoDAO_HI.executeSqlUpdate(
					" insert into SRM_SHORT_MATERIAL_INV_INFO_TMP select * from SRM_SHORT_MATERIAL_INV_INFO where creation_Date = CURDATE() + INTERVAL-(1)day  ");
			srmShortMaterialInfoDAO_HI.executeSqlUpdate(" truncate table SRM_SHORT_MATERIAL_INV_INFO ");
			srmShortMaterialInfoDAO_HI.executeSqlUpdate(
					" insert into SRM_SHORT_MATERIAL_INV_INFO select * from SRM_SHORT_MATERIAL_INV_INFO_TMP  ");

			srmShortMaterialInfoDAO_HI.executeSqlUpdate(
					" insert into SRM_SHORT_MATERIAL_INFO_TMP select * from SRM_SHORT_MATERIAL_INFO where creation_Date = CURDATE() + INTERVAL-(1)day  ");
			srmShortMaterialInfoDAO_HI.executeSqlUpdate(" truncate table SRM_SHORT_MATERIAL_INFO ");
			srmShortMaterialInfoDAO_HI.executeSqlUpdate(
					" insert into SRM_SHORT_MATERIAL_INFO select * from SRM_SHORT_MATERIAL_INFO_TMP  ");
			
			srmShortMaterialInfoDAO_HI.executeSqlUpdate(
					" insert into srm_short_material_warm_tmp select * from srm_short_material_warm where creation_Date = CURDATE() + INTERVAL-(1)day  ");
			srmShortMaterialInfoDAO_HI.executeSqlUpdate(" truncate table srm_short_material_warm ");
			srmShortMaterialInfoDAO_HI.executeSqlUpdate(
					" insert into srm_short_material_warm select * from srm_short_material_warm_tmp  ");

		}
		
		srmShortMaterialInfoDAO_HI.executeSqlUpdate(" truncate table srm_po_notice_qty_tmp1 ");
		srmShortMaterialInfoDAO_HI.executeSqlUpdate(" insert into srm_po_notice_qty_tmp1 "
				+ " SELECT  e.inst_id, e.delivery_site_id, e.item_id, Ifnull(e.delivery_qty,0), Ifnull(e.quantity,0) ,e.po_notice_code,e.SPECIAL_USE_NUM   " 
				+ " FROM  srm_po_notice e  "
				+ " WHERE e.status in( 'CONFIRMED','CREATED','REJECTED') " );
		
		srmShortMaterialInfoDAO_HI.executeSqlUpdate(" truncate table srm_po_notice_qty_tmp ");
		srmShortMaterialInfoDAO_HI.executeSqlUpdate(" insert into srm_po_notice_qty_tmp "
				+ " SELECT e.inst_id,e.delivery_site_id, e.item_id,e.SPECIAL_USE_NUM,Sum(e.delivery_qty),Sum(e.qty)   "
				+ " FROM  srm_po_notice_qty_tmp1 e  "
				+ " group by e.inst_id, e.delivery_site_id, e.item_id ,e.SPECIAL_USE_NUM ");

		srmPoStarvingDAO_HI.executeSqlUpdate("delete from  SRM_PO_STARVING ");

	}

	public String getQueryConfig() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("IS_VALID", "1");
		List<SrmShortMaterialConfigEntity_HI> rows = srmShortMaterialConfigDAO_HI.findByProperty(param);

		List<Object> value = new ArrayList<Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		int flag = -1;
		for (int i = 0; i < rows.size(); i++) {
			// System.out.println(rows.get(i).getId() + ", " +
			// rows.get(i).getAttrValue());
			if (!value.contains(rows.get(i).getAttrUn())) {
				if (flag == 1) {
					map.put(rows.get(i - 1).getAttrUn(), sb.substring(0, sb.length() - 1));
					sb.delete(0, sb.length());
				}
				flag = 1;
				value.add(rows.get(i).getAttrUn());

			}
			sb.append("'").append(rows.get(i).getAttrValue()).append("',");

			if (i == rows.size() - 1) {
				map.put(rows.get(i).getAttrUn(), sb.substring(0, sb.length() - 1));
			}
		}

		sb.delete(0, sb.length());
		sb.append(" select * from SRM_Short_Material_Info where 1=1  ");
		for (Map.Entry<String, Object> m : map.entrySet()) {
			sb.append(" and ").append(m.getKey()).append(" in (").append(m.getValue()).append(" ) ");

		}

		return sb.toString();

	}

	public Connection getU9Db() {
		Connection con = null;
		String cfn = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sys", "U9");

		try {
			SaafDbConfigEntity_HI DbEntity = saafDbConfigDAO_HI.findByProperty(param).get(0);
			String url = "jdbc:sqlserver://" + DbEntity.getIp() + ";DatabaseName=" + DbEntity.getDbname();

			Class.forName(cfn);
			con = DriverManager.getConnection(url, DbEntity.getLoginuser(), DbEntity.getPwd());

		} catch (ClassNotFoundException e) {
			LOGGER.info(e.getMessage());
			//e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.info(e.getMessage());
			//e.printStackTrace();
		}
		return con;

	}


}
