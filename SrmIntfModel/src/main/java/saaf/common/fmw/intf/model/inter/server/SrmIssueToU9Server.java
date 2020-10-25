package saaf.common.fmw.intf.model.inter.server;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.intf.bean.U9ResultBean;
import saaf.common.fmw.intf.model.entities.SrmIntfDeliveryEntity_HI;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;
import saaf.common.fmw.intf.model.entities.SrmIntfPlanDemandEntity_HI;
import saaf.common.fmw.intf.model.entities.readonly.SrmDeliveryBackEntity_HI_RO;
import saaf.common.fmw.intf.model.entities.readonly.SrmPlanAnalyzeEntity_HI_RO;
import saaf.common.fmw.intf.model.inter.IIntfUtils;
import saaf.common.fmw.intf.model.inter.ISrmIntfLogs;
import saaf.common.fmw.intf.model.inter.ISrmIssueToU9;
import saaf.common.fmw.po.model.entities.SrmPoDeliveryDetailsEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoDeliveryHeadersEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoDeliveryLinesEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoHeadersEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoLinesEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoNoticeEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoNoticeLineEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoNoticeLineEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;
import saaf.common.fmw.utils.SrmUtils;

@Component("srmIssueToU9Server")
public class SrmIssueToU9Server implements ISrmIssueToU9 {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmIssueToU9Server.class);

	// private ViewObject srmIntfLogsDAO_HI;
	@Autowired
	private IIntfUtils intfUtilsServer;
	@Autowired
	private ViewObject<SrmIntfLogsEntity_HI> srmIntfLogsDAO_HI;
	@Autowired
	private ViewObject<SrmIntfPlanDemandEntity_HI> srmIntfPlanDemandDAO_HI;
	@Autowired
	private ViewObject<SrmIntfDeliveryEntity_HI> srmIntfDeliveryDAO_HI;
	@Autowired
	private BaseViewObject<SrmDeliveryBackEntity_HI_RO> srmDeliveryBackDAO_HI_RO;

	@Autowired
	private ViewObject<SrmPoDeliveryHeadersEntity_HI> srmPoDeliveryHeadersDAO_HI;
	@Autowired
	private ViewObject<SrmPoDeliveryDetailsEntity_HI> srmPoDeliveryDetailsDAO_HI;
	@Autowired
	private ViewObject<SrmPoDeliveryLinesEntity_HI> srmPoDeliveryLinesDAO_HI;
	@Autowired
	private ViewObject<SrmPoHeadersEntity_HI> srmPoHeadersDAO_HI;
	@Autowired
	private ViewObject<SrmPoLinesEntity_HI> srmPoLinesDAO_HI;
	@Autowired
	private ViewObject<SrmPoNoticeEntity_HI> srmPoNoticeDAO_HI;
	@Autowired
	private ViewObject<SrmPoNoticeLineEntity_HI> srmPoNoticeLineDAO_HI;
	 
	@Autowired
	private BaseViewObject<SrmPlanAnalyzeEntity_HI_RO> srmPlanAnalyzeDAO_HI_RO;
   //保存日志
	public U9ResultBean saveLog(JSONObject jsonParam) throws Exception {
		U9ResultBean result = new U9ResultBean();
		Integer userId = 1;
		String intfType = jsonParam.getString("intfType");
		SrmIntfLogsEntity_HI row = new SrmIntfLogsEntity_HI();
		row.setIntfType(intfType);
		row.setInData(jsonParam.toString());
		row.setIntfStatus("N");
		row.setOperatorUserId(userId);
		srmIntfLogsDAO_HI.save(row);
		String data = jsonParam.getString("data");
		srmIntfLogsDAO_HI.save(row);

		result.setAtrr1(jsonParam.getString("u9HeaderId"));
		result.setBatchId(null);
		result.setLogId(row.getLogId());
		result.setUserId(userId);
		result.setIntfType(intfType);
		result.setData(data);
		result.setStatus("S");
		return result;

	}

	public boolean updateLog(U9ResultBean param) throws Exception {
		SrmIntfLogsEntity_HI row = srmIntfLogsDAO_HI.getById(param.getLogId());
		row.setIntfStatus(param.getStatus());
		row.setOutData(param.getMsg());
		row.setOperatorUserId(param.getUserId());
		srmIntfLogsDAO_HI.update(row);
		return true;
	}

	/**
	 * 保存五金需求计划数据入接口表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String savePlanData(U9ResultBean param) throws Exception {
		String jsonStr = param.getData();
		Integer logId = param.getLogId();
		String batchId = param.getBatchId();
		Integer userId = param.getUserId();
		String u9HeaderId = param.getAtrr1();
		String sql = "  select COUNT(1) dataCount from srm_intf_plan_demand t where T.HANDLE_STATUS = 'S' AND  t.U9_HEADER_ID ='" + u9HeaderId
				+ "'";
		String sql2 = "  select COUNT(1) dataCount from srm_po_plan_demands t where   t.U9_HEADER_ID ='" + u9HeaderId
				+ "'";

		List<SrmPlanAnalyzeEntity_HI_RO> list = srmPlanAnalyzeDAO_HI_RO.findList(sql);

		if (list.get(0).getDataCount() > 0) {
			return "11111111111";
		} else {
			list = srmPlanAnalyzeDAO_HI_RO.findList(sql2);
			if (list.get(0).getDataCount() > 0) {
				return "11111111111";
			}
		}

		JSONArray valuesArray = JSONObject.parseArray(jsonStr);

		for (int i = 0; i < valuesArray.size(); i++) {
			JSONObject lineJson = valuesArray.getJSONObject(i);
			SrmIntfPlanDemandEntity_HI row = new SrmIntfPlanDemandEntity_HI();
			row.setHandleStatus("N");
			row.setPlanDate(lineJson.getDate("PLAN_DATE"));
			row.setPlanType("3");
			row.setInstCode(lineJson.getString("ORG_CODE"));
			row.setItemCode(lineJson.getString("ITEM_CODE"));
			row.setNeedQuantity(lineJson.getBigDecimal("NEED_QUANTITY"));
			row.setNeedByDate(lineJson.getDate("NEED_BY_DATE"));
			row.setDemandClassify(lineJson.getString("BATCH_NUM"));
			row.setSupplierNumber(lineJson.getString("SUPPLIER_NUMBER"));
			row.setU9HeaderId(u9HeaderId);
			row.setBatchId(batchId);
			row.setLogId(logId);
			row.setOperatorUserId(userId);
			srmIntfPlanDemandDAO_HI.saveOrUpdate(row);
		}
		return "S";

	}

	/**
	 * 处理接收数据
	 * 
	 * @throws Exception
	 */
	public void saveDeliery(U9ResultBean param) throws Exception {
		String jsonStr = param.getData();
		Integer logId = param.getLogId();
		String batchId = param.getBatchId();
		Integer userId = param.getUserId();
		String intfType = param.getIntfType();
		JSONArray valuesArray = JSONObject.parseArray(jsonStr);

		if (valuesArray.size() < 1) {
			throw new Exception("回传数组数据不能为空！");
		}
		String docNo = "";
		String deliverNumber = "";
		for (int i = 0; i < valuesArray.size(); i++) {
			JSONObject lineJson = valuesArray.getJSONObject(i);
			SrmIntfDeliveryEntity_HI row = new SrmIntfDeliveryEntity_HI();
			row.setHandleStatus("N");

			deliverNumber = lineJson.getString("DELIVERY_NUMBER");
			docNo = lineJson.getString("DOCNO");

			row.setDeliveryNumber(lineJson.getString("DELIVERY_NUMBER"));
			row.setDeliveryLineNumber(lineJson.getString("DELIVERY_LINE_NUMBER"));
			row.setItemCode(lineJson.getString("ITEM_NUMBER"));
			row.setQuantity(lineJson.getBigDecimal("RECEIVE_QUANTITY"));
			row.setPoNumber(lineJson.getString("PO_NUMBER"));
			row.setPoLineNumber(lineJson.getBigDecimal("PO_LINE_NUMBER"));
			row.setDocno(lineJson.getString("DOCNO"));

			if (lineJson.getBigDecimal("PO_LINE_NUMBER") == null) {
				throw new Exception("订单行号不能为空");
			}

			if (lineJson.getString("PO_NUMBER") == null) {
				throw new Exception("订单编号不能为空");
			}

			if ("S0002".equals(intfType)) {
				// if (deliverNumber == null && "".equals(deliverNumber.trim()))
				// {
				// throw new Exception("送货单号不能为空");
				// }
				row.setDocType("REC");
			} else if ("S0003".equals(intfType)) {
				row.setDocType("REJ");
			}
			row.setBatchId(batchId);
			row.setLogId(logId);
			row.setOperatorUserId(userId);
			srmIntfDeliveryDAO_HI.saveOrUpdate(row);

		}
		srmIntfDeliveryDAO_HI.fluch();

		param.setAtrr1(docNo);
		String flag = this.isExistsSame(logId, docNo, valuesArray.size());

		if ("Y".equals(flag)) {
			param.setMsg("存在相同收退货数据 ,保存信息成功!!");
			return;
		}

		if ("S0002".equals(intfType)) {
			this.updatePoDeliver(logId, batchId, userId);
		} else if ("S0003".equals(intfType)) {

			this.updatePo(logId, batchId, userId);
		}

		param.setMsg("保存信息成功!");

	}

	public String isExistsSame(Integer logId, String docNo, Integer size) {
		StringBuffer sql = new StringBuffer();
		sql.append(SrmDeliveryBackEntity_HI_RO.QUERY_DELIVERY_INFO);
		sql.append(" and  HANDLE_STATUS ='S' ");
		sql.append("  AND DOCNO =  '" + docNo + "'");
		sql.append("  AND log_id !=   " + logId);
		List<SrmDeliveryBackEntity_HI_RO> list = srmDeliveryBackDAO_HI_RO.findList(sql);

		if (list.size() != size) {
			return "N";
		}

		if (list.size() > 0) {
			Integer logId2 = list.get(0).getLogId();
			StringBuffer sql2 = new StringBuffer();
			sql2.append(SrmDeliveryBackEntity_HI_RO.QUERY_SAME_DELIVERY);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("varLogId1", logId);
			param.put("varLogId2", logId2);
			List<SrmDeliveryBackEntity_HI_RO> list2 = srmDeliveryBackDAO_HI_RO.findList(sql2, param);
			if (list2.size() == size) {
				return "Y";
			}
		}

		return "N";

	}

	public List<SrmDeliveryBackEntity_HI_RO> updateAllInfo() throws Exception {
		StringBuffer sql = new StringBuffer(
				"  SELECT s.LOG_ID logId,S.DOC_TYPE docType from ( SELECT DISTINCT LOG_ID,DOC_TYPE FROM srm_intf_delivery t WHERE t.handle_status = 'U' ) s "
						+ "  order by  s.LOG_ID" + "     LIMIT 0,100  ");

		List<SrmDeliveryBackEntity_HI_RO> list = srmDeliveryBackDAO_HI_RO.findList(sql);

		return list;
	}

	/**
	 * 送货接收回写数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updatePoDeliver(Integer logId, String batchId, Integer userId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(SrmDeliveryBackEntity_HI_RO.QUERY_DELIVERY_INFO);
		sql.append(" and   t.log_id =  " + logId);
		sql.append("  order by t.DELIVERY_NUMBER,t.docno  ");
		List<SrmDeliveryBackEntity_HI_RO> list = srmDeliveryBackDAO_HI_RO.findList(sql);
		String deliveryNumber = "";
		String docNo = "";

		SrmIntfDeliveryEntity_HI intfRow = null;
		SrmPoDeliveryHeadersEntity_HI deliverHeadRow = null;
		SrmPoDeliveryLinesEntity_HI deliverLineRow = null;
		SrmPoHeadersEntity_HI poHeadRow = null;
		SrmPoLinesEntity_HI poLineRow = null;
		String type = null;
		SrmPoNoticeEntity_HI noticeRow = null;
		SrmPoNoticeLineEntity_HI noticeLineRow = null;
		for (SrmDeliveryBackEntity_HI_RO row : list) {

			intfRow = srmIntfDeliveryDAO_HI.getById(row.getIntfId());

			try {
				poHeadRow = srmPoHeadersDAO_HI.findByProperty("po_number", intfRow.getPoNumber()).get(0);
			} catch (Exception e) {
				throw new Exception("srm采购订单[" + intfRow.getPoNumber() + "]不存在");
			}

			try {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("po_header_id", poHeadRow.getPoHeaderId());
				param.put("line_number", intfRow.getPoLineNumber());
				poLineRow = srmPoLinesDAO_HI.findByProperty(param).get(0);
			} catch (Exception e) {
				throw new Exception("srm订单[" + intfRow.getPoNumber() + "]没有行号为" + intfRow.getPoLineNumber() + "的行数据！");
			}

			SrmPosSupplierInfoEntity_HI suppierRow = intfUtilsServer.findSupplierById(poHeadRow.getSupplierId());

			if ("Y".equals(suppierRow.getSrmDelivery()) && SrmUtils.isNvl(row.getDeliveryNumber())) {
				throw new Exception("供应商是SRM平台收货，srm送货单号必需存在");
			}

			if (SrmUtils.isNvl(row.getDeliveryNumber()) && SrmUtils.isNvl(docNo)) {
				docNo = row.getDocNo();
				StringBuffer sql2 = new StringBuffer();
				sql2.append(SrmDeliveryBackEntity_HI_RO.QUERY_DELIVERY_INFO);
				sql2.append(" and  HANDLE_STATUS ='S' ");
				sql2.append("  AND DOCNO =  '" + docNo + "'");
				sql2.append("  AND log_id !=   " + logId);
				List<SrmDeliveryBackEntity_HI_RO> list2 = srmDeliveryBackDAO_HI_RO.findList(sql2);

				if (list2.size() > 0) {
					throw new Exception("U9收货单[" + intfRow.getDocno() + "]已经传过SRM");
				}
			}

			if (!SrmUtils.isNvl(row.getDeliveryNumber())) {

				if (!deliveryNumber.equals(row.getDeliveryNumber())) {
					deliveryNumber = row.getDeliveryNumber();
					try {
						deliverHeadRow = srmPoDeliveryHeadersDAO_HI.findByProperty("delivery_number", deliveryNumber)
								.get(0);
						type = deliverHeadRow.getAttribute1();
					} catch (Exception e) {
						throw new Exception("srm没有送货单单号为[" + deliveryNumber + "]订单！");
					}

					if ("CLOSED".equals(deliverHeadRow.getStatus())) {
						throw new Exception("srm送货单单号为[" + deliveryNumber + "]订单已经做过接收,不能再次接收！");
					}

					if ("CANCEL".equals(deliverHeadRow.getStatus())) {
						throw new Exception("srm送货单单号为[" + deliveryNumber + "]订单已经取消,不能接收！");
					}
				}

				// 采购订单生成送货单接收
				if ("1".equals(type)) {
					try {
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("delivery_header_id", deliverHeadRow.getDeliveryHeaderId());
						param.put("po_line_id", poLineRow.getPoLineId());
						deliverLineRow = srmPoDeliveryLinesDAO_HI.findByProperty(param).get(0);
					} catch (Exception e) {
						throw new Exception("srm收送货单没有对应采购订单为[" + intfRow.getPoNumber() + "] 行号为"
								+ intfRow.getPoLineNumber() + "的行！");
					}

					deliverLineRow
							.setDeliveryQty(addBigDecimal(deliverLineRow.getDeliveryQty(), intfRow.getQuantity()));

					/*if (deliverLineRow.getDeliveryQty().compareTo(deliverLineRow.getDeliveryOrderQty()) > 0) {
						throw new Exception("srm收送货单对应采购订单为[" + intfRow.getPoNumber() + "] 行号为"
								+ intfRow.getPoLineNumber() + "的行数量不够！");
					}*/

					deliverLineRow.setOperatorUserId(userId);
					srmPoDeliveryLinesDAO_HI.update(deliverLineRow);
				}
				// 送货通知生成送货单接收
				else {
					StringBuffer detailSql = new StringBuffer();
					detailSql.append(SrmDeliveryBackEntity_HI_RO.QUTERY_DELI_DETAIL);
					detailSql.append("  and t.delivery_header_id =" + deliverHeadRow.getDeliveryHeaderId());
					detailSql.append("  and t.po_line_id =" + poLineRow.getPoLineId());
					detailSql.append("  order by t.demand_date ");

					BigDecimal quantity = intfRow.getQuantity();
					BigDecimal canDeliveryQty = null;
					List<SrmDeliveryBackEntity_HI_RO> detailList = srmDeliveryBackDAO_HI_RO.findList(detailSql);
					for (SrmDeliveryBackEntity_HI_RO row2 : detailList) {
						SrmPoDeliveryDetailsEntity_HI deatilRow = srmPoDeliveryDetailsDAO_HI
								.findByProperty("detail_id", row2.getDetailId()).get(0);
						canDeliveryQty = this.subtractBigDecimal(deatilRow.getQuantity(), deatilRow.getDeliveryQty());
						if (quantity.compareTo(canDeliveryQty) >= 0) {
							quantity = quantity.subtract(canDeliveryQty);
						} else if (quantity.compareTo(new BigDecimal(0)) == 0) {
							break;
						} else if (quantity.compareTo(canDeliveryQty) < 0) {
							canDeliveryQty = quantity;
							quantity = new BigDecimal(0);
						}
						deatilRow.setDeliveryQty(this.addBigDecimal(deatilRow.getDeliveryQty(), canDeliveryQty));
						if (deatilRow.getDeliveryQty().compareTo(deatilRow.getQuantity()) > 0) {
							throw new Exception("srm收送货单明细 行数量不够！");
						}

						deatilRow.setOperatorUserId(deatilRow.getLastUpdatedBy());
						srmPoDeliveryDetailsDAO_HI.update(deatilRow);
						// 回写送货单行
						deliverLineRow = srmPoDeliveryLinesDAO_HI
								.findByProperty("delivery_line_id", deatilRow.getDeliveryLineId()).get(0);
						deliverLineRow
								.setDeliveryQty(this.addBigDecimal(deliverLineRow.getDeliveryQty(), canDeliveryQty));

						/*if (deliverLineRow.getDeliveryQty().compareTo(deliverLineRow.getDeliveryOrderQty()) > 0) {
							throw new Exception("srm收送货单行 行数量不够！");
						}*/

						deliverLineRow.setOperatorUserId(deliverLineRow.getLastUpdatedBy());
						srmPoDeliveryLinesDAO_HI.save(deliverLineRow);

						// 回写送货通知
						noticeLineRow = srmPoNoticeLineDAO_HI.findByProperty("line_id", deatilRow.getPoNoticeLineId())
								.get(0);

						noticeRow = srmPoNoticeDAO_HI.findByProperty("po_notice_id", deatilRow.getPoNoticeId()).get(0);

						//noticeLineRow.setDeliveryQty(this.addBigDecimal(noticeLineRow.getDeliveryQty(), canDeliveryQty));

						// if
						// (noticeLineRow.getDeliveryQty().compareTo(noticeLineRow.getMatchQuantity())>0){
						// throw new Exception("srm收送货单对应送货通知为[" +
						// noticeRow.getPoNoticeCode() + "] 匹配"
						// + "行数量不够！");
						// }

						noticeLineRow.setOperatorUserId(noticeLineRow.getLastUpdatedBy());
						srmPoNoticeLineDAO_HI.update(noticeLineRow);

						noticeRow.setDeliveryQty(addBigDecimal(noticeRow.getDeliveryQty(), canDeliveryQty));

						// if
						// (noticeRow.getDeliveryQty().compareTo(noticeRow.getQuantity())>0){
						// throw new Exception("srm收送货单对应送货通知为[" +
						// noticeRow.getPoNoticeCode() + "] 的数量不够！");
						// }

						noticeRow.setOperatorUserId(noticeRow.getLastUpdatedBy());
						srmPoNoticeDAO_HI.update(noticeRow);
						srmPoDeliveryDetailsDAO_HI.fluch();
					}

					if (quantity.compareTo(new BigDecimal(0)) > 0) {
						throw new Exception("srm收送货单对应采购订单为[" + intfRow.getPoNumber() + "] 行号为"
								+ intfRow.getPoLineNumber() + "的行数量不够！");
					}

				}
				//deliverHeadRow.setReceiveFlag("Y");
				deliverHeadRow.setStatus("CLOSED");
				deliverHeadRow.setOperatorUserId(deliverHeadRow.getLastUpdatedBy());
				srmPoDeliveryHeadersDAO_HI.update(deliverHeadRow);
				srmPoDeliveryLinesDAO_HI.fluch();
			}

			poLineRow.setOperatorUserId(poLineRow.getLastUpdatedBy());

			srmPoLinesDAO_HI.update(poLineRow);
			intfRow.setOperatorUserId(userId);
			intfRow.setHandleStatus("S");
			srmIntfDeliveryDAO_HI.update(intfRow);

		}

		return null;
	}

	/**
	 * 退货回写数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updatePo(Integer logId, String batchId, Integer userId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(SrmDeliveryBackEntity_HI_RO.QUERY_DELIVERY_INFO);
		sql.append(" and   t.log_id =  " + logId);
		sql.append("  order by t.docno,t.DELIVERY_NUMBER ");
		List<SrmDeliveryBackEntity_HI_RO> list = srmDeliveryBackDAO_HI_RO.findList(sql);
		String docNo = "";

		SrmIntfDeliveryEntity_HI intfRow = null;
		SrmPoHeadersEntity_HI poHeadRow = null;
		SrmPoLinesEntity_HI poLineRow = null;
		for (SrmDeliveryBackEntity_HI_RO row : list) {

			intfRow = srmIntfDeliveryDAO_HI.getById(row.getIntfId());

			if ("".equals(docNo) || docNo == null || !docNo.equals(intfRow.getDocno())) {

				docNo = intfRow.getDocno();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("docno", docNo);
				map.put("Handle_Status", "S");

				if (srmIntfDeliveryDAO_HI.findByProperty(map).size() > 0) {
					throw new Exception("U9退货单[" + intfRow.getDocno() + "]已经做过退货，不能再次退货。");
				}
			}

			try {
				poHeadRow = srmPoHeadersDAO_HI.findByProperty("po_number", intfRow.getPoNumber()).get(0);
			} catch (Exception e) {
				throw new Exception("srm采购订单[" + intfRow.getPoNumber() + "]不存在");
			}
			try {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("po_header_id", poHeadRow.getPoHeaderId());
				param.put("line_number", intfRow.getPoLineNumber());
				poLineRow = srmPoLinesDAO_HI.findByProperty(param).get(0);
			} catch (Exception e) {
				throw new Exception("srm订单[" + intfRow.getPoNumber() + "]没有行号为" + intfRow.getPoLineNumber() + "行！");
			}

			

			if ("SHORTAGE_CLOSED".equals(poLineRow.getStatus()) || "NATURAL_CLOSED".equals(poLineRow.getStatus())) {
				poLineRow.setStatus("APPROVED");
			}
			poLineRow.setOperatorUserId(poLineRow.getLastUpdatedBy());
			
			srmPoLinesDAO_HI.saveOrUpdate(poLineRow);

			intfRow.setOperatorUserId(userId);
			intfRow.setHandleStatus("S");
			srmIntfDeliveryDAO_HI.update(intfRow);

		}

		return null;
	}

	public BigDecimal addBigDecimal(BigDecimal s1, BigDecimal s2) {

		if (s2 == null) {
			s2 = new BigDecimal(0);
		}

		if (s1 == null) {
			return s2;
		}

		return s1.add(s2);

	}

	public BigDecimal subtractBigDecimal(BigDecimal s1, BigDecimal s2) {
		if (s2 == null) {
			s2 = new BigDecimal(0);
		}

		if (s1 == null) {
			s1 = new BigDecimal(0);
		}

		return s1.subtract(s2);

	}

}
