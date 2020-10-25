package saaf.common.fmw.intf.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.intf.bean.PoInfoBean;
import saaf.common.fmw.intf.bean.PoLineInfoBean;
import saaf.common.fmw.intf.bean.PoReturnLinesDetal;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;
import saaf.common.fmw.intf.model.inter.IIntfPo;
import saaf.common.fmw.intf.util.U9Client;
import saaf.common.fmw.intf.util.U9IntfUtils;
import saaf.common.fmw.po.model.entities.SrmPoHeadersEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoLinesEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoHeadersEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoLinesEntity_HI_RO;

import java.util.*;

@Component("intfPoServer")
public class IntfPoServer implements IIntfPo {
	private static final Logger LOGGER = LoggerFactory.getLogger(IntfPoServer.class);

	@Autowired
	private ViewObject<SrmIntfLogsEntity_HI> srmIntfLogsDAO_HI;

	@Autowired
	private BaseViewObject<SrmPoHeadersEntity_HI_RO> srmPoHeadersDAO_HI_RO;
	@Autowired
	private BaseViewObject<SrmPoLinesEntity_HI_RO> srmPoLinesDAO_HI_RO;

	@Autowired
	private ViewObject<SrmPoLinesEntity_HI> srmPoLinesDAO_HI;

	@Autowired
	private ViewObject<SrmPoHeadersEntity_HI> srmPoHeadersDAO_HI;

	@Override
	public List<SrmPoLinesEntity_HI_RO> pushBatchPoOrder() throws Exception {
		StringBuffer sql = new StringBuffer(" select distinct h.po_header_id from srm_po_headers h "
				+ " join srm_po_lines l on h.po_header_id=l.po_header_id "
				+ " where h.status='APPROVED' and (h.pass_u9_flag ='N' or h.pass_u9_flag is null ) and l.u9_plan_line_id is null "
				+ " union " + " select distinct po_header_id from srm_po_lines where ATTRIBUTE1 ='U' "
				+ " AND u9_plan_line_id IS NOT NULL AND status in( 'APPROVED','SHORTAGE_CLOSED') ");
		List<SrmPoLinesEntity_HI_RO> poLineRow = srmPoLinesDAO_HI_RO.findList(sql);

		return poLineRow;

	}

	// 创建
	public JSONObject createOrder(SrmPoHeadersEntity_HI header, Integer userId) {
		SrmPoHeadersEntity_HI_RO poHeaderRo = null;
		List<SrmPoLinesEntity_HI_RO> poLinesRo = null;
		PoInfoBean headerJson = new PoInfoBean();
		SrmIntfLogsEntity_HI log = new SrmIntfLogsEntity_HI();

		StringBuffer sql = new StringBuffer(SrmPoHeadersEntity_HI_RO.GET_PO_HEADER_SQL);
		if (null != header.getPoHeaderId()) {
			sql.append("  and a.po_header_id = '" + header.getPoHeaderId() + "'");
		}

		try {
			poHeaderRo = srmPoHeadersDAO_HI_RO.findList(sql).get(0);
			if (null != poHeaderRo) {
				headerJson.setU9MessageNO("");
				headerJson.setDocNo(poHeaderRo.getPoNumber());
				headerJson.setDocType(poHeaderRo.getPoDocType());
				headerJson.setBusinessDate(convertStr(poHeaderRo.getCreationDate()));
				headerJson.setFCur(poHeaderRo.getCurrencyCode());
				headerJson.setOperator(poHeaderRo.getEmployeeNumber());
				headerJson.setStatus(poHeaderRo.getStatus());
				headerJson.setOperateType(String.valueOf(0)); // 操作类型 0新增
				headerJson.setSupplierCode(poHeaderRo.getSupplierNumber());
				headerJson.setPriceIncludeTax(1);

				sql.setLength(0);
				sql.append(SrmPoLinesEntity_HI_RO.GET_PO_LINE_SQL);
				sql.append(" and a.po_header_id = '" + header.getPoHeaderId() + "'");
				poLinesRo = srmPoLinesDAO_HI_RO.findList(sql);

				PoLineInfoBean lineJson = null;
				for (SrmPoLinesEntity_HI_RO obj : poLinesRo) {
					lineJson = new PoLineInfoBean();
					lineJson.setItemCode(obj.getItemCode());
					lineJson.setReqQtyTU(convertStr(obj.getDemandQty()));
					lineJson.setTradeUOMCode(obj.getUnitOfMeasure()); // 物料单位
					lineJson.setDocLineNo(convertStr(obj.getLineNumber()));
					lineJson.setReqDate(convertStr(obj.getDemandDate()));
					lineJson.setHandBillCauseBg("7");
					lineJson.setHandBillCauseSm("701");
					lineJson.setBranchOrgCode(obj.getInstCode()); // 分厂编码
					lineJson.setLineStatus("");
					lineJson.setIsHand("1");
					lineJson.setMemo(obj.getDescription() );

					headerJson.getPOLineS().add(lineJson);

				}

				String jsonStr = JSONObject.toJSONString(headerJson);
				String transCode = U9IntfUtils.PO_ORDER_CODE;
				log.setDataDirection("IN");
				log.setInData(jsonStr);
				log.setIntfType(transCode);
				log.setOperatorUserId(userId);

				Map<String, Object> param = null;
				try {

					Map<String, String> retInfo = U9Client.invokeService(jsonStr, transCode, headerJson.getOrgCode());
					log.setOutData(retInfo.get("returnMsg"));
					log.setIntfStatus("S");

					LOGGER.info("returnMsg:" + retInfo.get("returnMsg"));
					srmIntfLogsDAO_HI.saveOrUpdate(log);
					String RetCode = convertStr(JSON.parseObject(retInfo.get("returnMsg")).get("RetCode"));

					if ("200".equals(RetCode)
							&& JSON.parseObject(retInfo.get("returnMsg")).get("JsonStr").toString().length() > 0) {
						List<PoReturnLinesDetal> msgs = JSON.parseArray(
								JSON.parseObject(retInfo.get("returnMsg")).get("JsonStr").toString(),
								PoReturnLinesDetal.class);

						SrmPoLinesEntity_HI updatePoLine = null;

						for (PoReturnLinesDetal obj : msgs) {
							param = new HashMap<String, Object>();
							param.put("po_header_id", header.getPoHeaderId());
							param.put("line_number", obj.getPOLineNo());
							updatePoLine = srmPoLinesDAO_HI.findByProperty(param).get(0);

							if (null != updatePoLine) {
								updatePoLine.setOperatorUserId(userId);
								updatePoLine.setLastUpdateDate(new Date());

								if ("SHORTAGE_CLOSED".equals(updatePoLine.getStatus())) {
									updatePoLine.setAttribute1("U");
								} else {
									updatePoLine.setAttribute1("Y");
								}

								srmPoLinesDAO_HI.saveOrUpdate(updatePoLine);

							}
						}

						header.setAttribute1("0");
						header.setOperatorUserId(userId);
						header.setLastUpdateDate(new Date());

						if ("200".equals(this.commitPoOrder(headerJson.getDocNo(), "2", transCode, log))) { // U9提交
							log.setIntfStatus("S");
							srmIntfLogsDAO_HI.saveOrUpdate(log);
							header.setAttribute1("1");

							if ("200".equals(this.commitPoOrder(headerJson.getDocNo(), "3", transCode, log))) {// U9审核
								log.setIntfStatus("S");
								header.setAttribute1("2");
							} else {
								log.setIntfStatus("E");
							}

							srmIntfLogsDAO_HI.saveOrUpdate(log);
						} else {
							log.setIntfStatus("E");
							srmIntfLogsDAO_HI.saveOrUpdate(log);
						}

						srmPoHeadersDAO_HI.saveOrUpdate(header);
					} else {
						log.setIntfStatus("E");
						srmIntfLogsDAO_HI.saveOrUpdate(log);
						return SToolUtils.convertResultJSONObj("E", "采购订单创建推送U9失败:" + retInfo.get("returnMsg"), 0,
								null);

					}

				} catch (Exception e) {
					LOGGER.info(e.getMessage());
					//e.printStackTrace();
					log.setIntfStatus("E");
					log.setErrorMsg(e.getMessage());
					try {
						srmIntfLogsDAO_HI.saveOrUpdate(log);
						srmIntfLogsDAO_HI.saveOrUpdate(log);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					return SToolUtils.convertResultJSONObj("E", "采购订单创建推送U9失败!", 0, null);

				}

			}

		} catch (Exception e) {
			return SToolUtils.convertResultJSONObj("E", "没找到采购订单id为" + header.getPoHeaderId(), 0, null);
		}

		return SToolUtils.convertResultJSONObj("S", "采购订单推送U9成功!", 0, null);

	}

	@Override
	public JSONObject pushPoOrder(String poHeaderId, Integer userId) {
		SrmPoHeadersEntity_HI singleHeader = srmPoHeadersDAO_HI.findByProperty("po_header_id", poHeaderId).get(0);

		// 创建
		if ("APPROVED".equals(singleHeader.getStatus())
				// && ("N".equals(singleHeader.getPassU9Flag()) ||
				// SrmUtils.isNvl(singleHeader.getPassU9Flag()) )
				&& (null == singleHeader.getAttribute1() || !"2".equals(singleHeader.getAttribute1()))) {

			List<SrmPoHeadersEntity_HI_RO> li = null;
			StringBuffer sql = new StringBuffer(" select distinct l.status from srm_po_headers h,  srm_po_lines l where  h.po_header_id = l.po_header_id  and l.status='APPROVED' ");
			sql.append(" and h.po_header_id = '").append(poHeaderId).append("' ");
			li = srmPoHeadersDAO_HI_RO.findList(sql);

			JSONObject createOrder = null;
			if ( li==null ||li.size()<1  ) {
				return SToolUtils.convertResultJSONObj("E", "采购订单明细行没有状态为审批通过的行，且未传过U9，因此无需再传!", 0, null);
			} else {
				createOrder = this.createOrder(singleHeader, userId);
			}

			// String stringee =
			// JSON.parseObject(convertStr(createOrder.get("msg"))).get("RetMsg").toString();

			if ("E".equals(convertStr(createOrder.get("status")))) {
				return SToolUtils.convertResultJSONObj("E", "采购订单推送失败!", 0, null);
			}

		} else if ("APPROVED".equals(singleHeader.getStatus()) // 修改，和 关闭
				&& "2".equals(singleHeader.getAttribute1())) {
			List<SrmPoLinesEntity_HI> lines = srmPoLinesDAO_HI.findByProperty("po_header_id", poHeaderId);
			StringBuffer closePos = new StringBuffer();
			StringBuffer updatePos = new StringBuffer();
			for (SrmPoLinesEntity_HI l : lines) {
				if ("U".equals(l.getAttribute1()) && "SHORTAGE_CLOSED".equals(l.getStatus())) {
					closePos.append(l.getPoLineId()).append(",");
				}

				if ("U".equals(l.getAttribute1()) && !"SHORTAGE_CLOSED".equals(l.getStatus())) {
					updatePos.append(l.getPoLineId()).append(",");
				}
			}

			if (closePos.length() > 0) {
				this.updateAndClosePoLine(closePos.substring(0, closePos.length() - 1), userId);
			}
			if (updatePos.length() > 0) {
				this.updateSinglePoOrder(updatePos.substring(0, updatePos.length() - 1), userId);
			}

		} else {
			return SToolUtils.convertResultJSONObj("E", "采购订单非已核准状态，不能推U9!", 0, null);
		}

		return SToolUtils.convertResultJSONObj("S", "采购订单推送U9成功!", 0, null);

	}

	// @Override
	// public JSONObject pushPoOrder(String poHeaderId, Integer userId) {
	// SrmPoHeadersEntity_HI_RO poHeaderRow = null;
	// List<SrmPoLinesEntity_HI_RO> poLineRow = null;
	// PoInfoBean header = new PoInfoBean();
	//
	// SrmPoHeadersEntity_HI singleHeader =
	// srmPoHeadersDAO_HI.findByProperty("po_header_id", poHeaderId).get(0);
	//
	// if ("APPROVED".equals(singleHeader.getStatus())
	// && ("N".equals(singleHeader.getPassU9Flag()) ||
	// SrmUtils.isNvl(singleHeader.getPassU9Flag()))) {
	//
	// StringBuffer sql = new
	// StringBuffer(SrmPoHeadersEntity_HI_RO.GET_PO_HEADER_SQL);
	// if (poHeaderId != null && poHeaderId.trim().length() > 0) {
	// sql.append(" and a.po_header_id = '" + poHeaderId + "'");
	//
	// }
	//
	// try {
	// poHeaderRow = srmPoHeadersDAO_HI_RO.findList(sql).get(0);
	// if (null != poHeaderRow) {
	// header.setU9MessageNO("");
	// header.setDocNo(poHeaderRow.getPoNumber());
	// header.setDocType(poHeaderRow.getPoDocType());
	// header.setBusinessDate(convertStr(poHeaderRow.getDemandDate()));
	// header.setFCur(poHeaderRow.getCurrencyCode());
	// header.setOperator(poHeaderRow.getEmployeeNumber());
	// header.setStatus(poHeaderRow.getStatus());
	// header.setOperateType(String.valueOf(0)); // 操作类型 0新增
	// header.setSupplierCode(poHeaderRow.getSupplierNumber());
	// header.setPriceIncludeTax(1);
	// header.setOrgCode(convertCode(poHeaderRow.getSupplyInstId()));
	//
	// sql.setLength(0);
	// sql.append(SrmPoLinesEntity_HI_RO.GET_PO_LINE_SQL);
	// sql.append(" and a.po_header_id = '" + poHeaderId + "'");
	// poLineRow = srmPoLinesDAO_HI_RO.findList(sql);
	//
	// PoLineInfoBean line = null;
	// for (SrmPoLinesEntity_HI_RO obj : poLineRow) {
	// line = new PoLineInfoBean();
	// line.setItemCode(obj.getItemCode());
	// line.setReqQtyTU(convertStr(obj.getDemandQty()));
	// line.setTradeUOMCode(obj.getUnitOfMeasure()); // 物料单位
	// line.setDocLineNo(convertStr(obj.getLineNumber()));
	// line.setReqDate(convertStr(obj.getDemandDate()));
	// line.setHandBillCauseBg("7");
	// line.setHandBillCauseSm("701");
	// line.setBranchOrgCode(obj.getInstCode()); // 分厂编码
	// line.setDemandCode(obj.getDemandClassify());
	// line.setSeiBanCode(obj.getSpecialUseNum());
	// line.setLineStatus("");
	// line.setIsHand("1");
	// line.setPrice(obj.getPrice());
	//
	// header.getPOLineS().add(line);
	//
	// }
	//
	// SrmIntfLogsEntity_HI row = new SrmIntfLogsEntity_HI();
	// try {
	// String jsonStr = JSONObject.toJSONString(header);
	// // System.out.println("jsonStr:" + jsonStr);
	// String transCode = U9IntfUtils.PO_ORDER_CODE;
	// row.setDataDirection("IN");
	// row.setInData(jsonStr);
	// row.setIntfType(transCode);
	// row.setOperatorUserId(userId);
	// row.setDocId(poHeaderRow.getPoHeaderId());
	// row.setDocNumber(poHeaderRow.getPoNumber());
	//
	// int flag = -1;
	// SrmPoHeadersEntity_HI poH = new SrmPoHeadersEntity_HI();
	// Map<String, Object> param = null;
	//
	// if (null == poHeaderRow.getAttribute1()) {
	// Map<String, String> retInfo = U9Client.invokeService(jsonStr, transCode,
	// header.getOrgCode());
	// row.setOutData(retInfo.get("returnMsg"));
	// LOGGER.info("returnMsg:" + retInfo.get("returnMsg"));
	//
	// if
	// (JSON.parseObject(retInfo.get("returnMsg")).get("JsonStr").toString().length()
	// > 0) {
	// List<PoReturnLinesDetal> msgs = JSON.parseArray(
	// JSON.parseObject(retInfo.get("returnMsg")).get("JsonStr").toString(),
	// PoReturnLinesDetal.class);
	//
	// SrmPoLinesEntity_HI poline = null;
	// List<SrmPoLinesEntity_HI> lists = new ArrayList<SrmPoLinesEntity_HI>();
	//
	// for (PoReturnLinesDetal obj : msgs) {
	// param = new HashMap<String, Object>();
	// param.put("po_header_id", poHeaderId);
	// param.put("line_number", obj.getPOLineNo());
	// poline = srmPoLinesDAO_HI.findByProperty(param).get(0);
	//
	// if (null != poline) {
	// poH.setAttribute1("0");
	// poline.setU9PlanLineId(obj.getID());
	// poline.setOperatorUserId(userId);
	// poline.setLastUpdateDate(new Date());
	//
	// if ("SHORTAGE_CLOSED".equals(poline.getStatus())) {
	// poline.setAttribute1("U");
	// } else {
	// poline.setAttribute1("Y");
	// }
	//
	// // srmPoLinesDAO_HI.saveOrUpdate(poline);
	// lists.add(poline);
	//
	//
	// }
	// }
	//
	// if ("200".equals(this.commitPoOrder(header.getDocNo(), "2", transCode)))
	// { // U9提交
	// flag = 0;
	// if ("200".equals(this.commitPoOrder(header.getDocNo(), "3", transCode)))
	// {// U9审核
	// flag = 1;
	// row.setIntfStatus("S");
	// }
	// }
	//
	// if (flag >= 0) {
	// param = new HashMap<String, Object>();
	// param.put("po_header_id", poHeaderId);
	//
	// srmPoLinesDAO_HI.saveOrUpdateAll(lists);
	// }
	//
	// } else {
	// row.setIntfStatus("E");
	// return SToolUtils.convertResultJSONObj("E", "采购订单创建推送U9失败:" +
	// retInfo.get("returnMsg"),
	// 0, null);
	// }
	//
	// } else if ("0".equals(poHeaderRow.getAttribute1())) {
	// if ("200".equals(this.commitPoOrder(header.getDocNo(), "2", transCode)))
	// { // U9提交
	// flag = 0;
	// if ("200".equals(this.commitPoOrder(header.getDocNo(), "3", transCode)))
	// {// U9审核
	// flag = 1;
	// row.setIntfStatus("S");
	// } else {
	// row.setIntfStatus("E");
	// return SToolUtils.convertResultJSONObj("E", "U9审核采购订单失败", 0, null);
	// }
	// } else {
	// row.setIntfStatus("E");
	// return SToolUtils.convertResultJSONObj("E", "U9提交采购订单失败", 0, null);
	// }
	//
	// } else if ("1".equals(poHeaderRow.getAttribute1())) {
	// if ("200".equals(this.commitPoOrder(header.getDocNo(), "3", transCode)))
	// {// U9审核
	// flag = 1;
	// row.setIntfStatus("S");
	// } else {
	// row.setIntfStatus("E");
	// return SToolUtils.convertResultJSONObj("E", "U9审核采购订单失败", 0, null);
	// }
	// }
	//
	// poH = srmPoHeadersDAO_HI.findByProperty(param).get(0);
	// poH.setPassU9Flag("Y");
	//
	// if (flag == 0) {
	// poH.setAttribute1("1");
	// } else if (flag == 1) {
	// poH.setAttribute1("2");
	// }
	//
	// poH.setOperatorUserId(userId);
	// poH.setLastUpdateDate(new Date());
	// srmPoHeadersDAO_HI.saveOrUpdate(poH);
	//
	// srmIntfLogsDAO_HI.saveOrUpdate(row);
	//
	// } catch (Exception e) {
	// LOGGER.info(e.getMessage());
	// //e.printStackTrace();
	// row.setIntfStatus("E");
	// row.setErrorMsg(e.getMessage());
	// row.setDocId(poHeaderRow.getPoHeaderId());
	// row.setDocNumber(poHeaderRow.getPoNumber());
	// try {
	// srmIntfLogsDAO_HI.saveOrUpdate(row);
	// } catch (Exception e1) {
	// e1.printStackTrace();
	// }
	//
	// return SToolUtils.convertResultJSONObj("E", "采购订单创建推送U9失败!", 0, null);
	//
	// }
	// }
	//
	// } catch (Exception e) {
	// SToolUtils.convertResultJSONObj("E", "没找到采购订单id为" + poHeaderId, 0, null);
	// }
	//
	// } else if ("APPROVED".equals(singleHeader.getStatus()) &&
	// "Y".equals(singleHeader.getPassU9Flag())) {
	// List<SrmPoLinesEntity_HI> lines =
	// srmPoLinesDAO_HI.findByProperty("po_header_id", poHeaderId);
	// StringBuffer closePos = new StringBuffer();
	// StringBuffer updatePos = new StringBuffer();
	// for (SrmPoLinesEntity_HI l : lines) {
	// if ("U".equals(l.getAttribute1()) &&
	// "SHORTAGE_CLOSED".equals(l.getStatus())) {
	// closePos.append(l.getPoLineId()).append(",");
	// }
	//
	// if ("U".equals(l.getAttribute1()) && null != l.getU9PlanLineId()
	// && !"SHORTAGE_CLOSED".equals(l.getStatus())) {
	// updatePos.append(l.getPoLineId()).append(",");
	// }
	// }
	//
	// if (closePos.length() > 0) {
	// this.updateAndClosePoLine(closePos.substring(0, closePos.length() - 1),
	// userId);
	// }
	// if (updatePos.length() > 0) {
	// this.updateSinglePoOrder(updatePos.substring(0, updatePos.length() - 1),
	// userId);
	// }
	//
	// } else {
	// return SToolUtils.convertResultJSONObj("E", "采购订单非已核准状态，不能推U9!", 0,
	// null);
	// }
	//
	// return SToolUtils.convertResultJSONObj("S", "采购订单推送U9成功!", 0, null);
	//
	// }

	// 提交与审批
	public String commitPoOrder(String poCode, String OperateType, String transCode, SrmIntfLogsEntity_HI log)
			throws Exception {
		String jsonStr = "{\"DocNo\":\"" + poCode + "\",\"OperateType\":\"" + OperateType + "\"}";
		Map<String, String> retInfo = U9Client.invokeService(jsonStr, transCode);

		log.setOutData(retInfo.get("returnMsg"));

		return convertStr(JSON.parseObject(retInfo.get("returnMsg")).get("RetCode"));

	}

	@Override
	public JSONObject updateAndClosePoLine(String polineIds, Integer userId) {
		String sql = " select b.po_number poNumber,a.line_number lineNumber,a.po_line_id poLineId  "
				+ " from srm_po_lines a join srm_po_headers b on a.po_header_id = b.po_header_id "
				+ " where a.status = 'SHORTAGE_CLOSED'  and  a.po_line_id in (" + polineIds + ") ";

		List<SrmPoLinesEntity_HI_RO> poLineRow = null;
		SrmIntfLogsEntity_HI row = new SrmIntfLogsEntity_HI();
		List<Integer> lineIds = new ArrayList<Integer>();
		PoInfoBean header = new PoInfoBean();
		PoLineInfoBean line = null;
		String po = null, jsonStr = null;
		String transCode = U9IntfUtils.PO_CLOSED_ORDER_CODE;
		Map<String, String> retInfo = null;

		try {
			poLineRow = srmPoLinesDAO_HI_RO.findList(new StringBuffer(sql));

			for (SrmPoLinesEntity_HI_RO obj : poLineRow) {
				po = obj.getPoNumber();
				line = new PoLineInfoBean();
				line.setPoNo(obj.getPoNumber());
				line.setDocLineNo(convertStr(obj.getLineNumber()));
				header.getPOLineS().add(line);
				lineIds.add(obj.getPoLineId());
			}

			if (header.getPOLineS().size() > 0) {
				jsonStr = JSONObject.toJSONString(header);
				retInfo = U9Client.invokeService(jsonStr, transCode);

				if ("S".equalsIgnoreCase(retInfo.get("returnMsg"))) {
					Map<String, Object> param = new HashMap<String, Object>();
					SrmPoLinesEntity_HI poL = new SrmPoLinesEntity_HI();

					for (int i = 0; i < lineIds.size(); i++) {
						param.put("po_line_id", lineIds.get(i));
						poL = srmPoLinesDAO_HI.findByProperty(param).get(0);
						poL.setAttribute1("Y");
						// poL.setLastUpdatedBy(userId);
						poL.setLastUpdateDate(new Date());
						// poL.setLastUpdateLogin(userId);
						poL.setOperatorUserId(userId);
						srmPoLinesDAO_HI.saveOrUpdate(poL);

						row.setIntfStatus("S");
						row.setDataDirection("IN");
						row.setInData(jsonStr);
						row.setIntfType(transCode);
						row.setOperatorUserId(userId);
						row.setOutData(retInfo.get("returnMsg"));
						srmIntfLogsDAO_HI.saveOrUpdate(row);
					}

				} else {
					for (int i = 0; i < lineIds.size(); i++) {
						row.setIntfStatus("E");
						row.setDataDirection("IN");
						row.setInData(jsonStr);
						row.setIntfType(transCode);
						row.setOperatorUserId(userId);
						row.setOutData(retInfo.get("returnMsg"));
						srmIntfLogsDAO_HI.saveOrUpdate(row);
					}
					return SToolUtils.convertResultJSONObj("E", "采购订单关闭失败，不能推U9!", 0, null);
				}

			}

		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			for (Integer lineid : lineIds) {
				row.setIntfStatus("E");
				row.setDataDirection("IN");
				row.setInData(jsonStr);
				row.setIntfType(transCode);
				row.setOperatorUserId(userId);
				row.setOutData(retInfo.get("returnMsg"));
				srmIntfLogsDAO_HI.saveOrUpdate(row);
			}
			return SToolUtils.convertResultJSONObj("E", "采购订单关闭失败，不能推U9!", 0, null);
		}
		return null;

	}

	@Override
	public JSONObject updateSinglePoOrder(String polineIds, Integer userId) {
		SrmIntfLogsEntity_HI row = new SrmIntfLogsEntity_HI();
		PoInfoBean header = null;
		String transCode = U9IntfUtils.PO_ORDER_CODE;
		String jsonStr = null;

		StringBuffer sql = new StringBuffer(SrmPoHeadersEntity_HI_RO.GET_PO_CHANGE_SQL);
		if (null != polineIds && polineIds.length() > 0) {
			sql.append(" and l.po_line_id  in (" + polineIds + ") order by h.po_number ");
		}

		List<SrmPoHeadersEntity_HI_RO> lines = null;
		Map<String, String> retInfo = null;
		try {
			lines = srmPoHeadersDAO_HI_RO.findList(sql);
			PoLineInfoBean line = null;

			for (int i = 0; i < lines.size(); i++) {
				if (i == 0) {
					header = new PoInfoBean();
					header.setDocNo(lines.get(i).getPoNumber());
					header.setOperateType(String.valueOf(1));
					header.setOperator(lines.get(i).getEmployeeNumber());
				}

				line = new PoLineInfoBean();
				line.setReqQtyTU(convertStr(lines.get(i).getDemandQty()));
				line.setDocLineNo(convertStr(lines.get(i).getLineNumber()));

				header.getPOLineS().add(line);
			}

			jsonStr = JSONObject.toJSONString(header);
			System.out.println("jsonStr:" + jsonStr);
			retInfo = U9Client.invokeService(jsonStr, transCode);
			LOGGER.info("returnMsg:" + retInfo.get("returnMsg"));

			if ("S".equalsIgnoreCase(retInfo.get("returnMsg"))) {
				Map<String, Object> param = null;
				for (SrmPoHeadersEntity_HI_RO o : lines) {
					row.setIntfStatus("S");
					row.setDataDirection("IN");
					row.setInData(jsonStr);
					row.setIntfType(transCode);
					row.setOperatorUserId(userId);
					row.setOutData(retInfo.get("returnMsg"));
					srmIntfLogsDAO_HI.saveOrUpdate(row);

					param = new HashMap<String, Object>();
					SrmPoLinesEntity_HI poL = new SrmPoLinesEntity_HI();

					param.put("po_line_id", o.getPoLineId());
					poL = srmPoLinesDAO_HI.findByProperty(param).get(0);
					poL.setAttribute1("Y");
					// poL.setLastUpdatedBy(userId);
					poL.setLastUpdateDate(new Date());
					// poL.setLastUpdateLogin(userId);
					poL.setOperatorUserId(userId);
					srmPoLinesDAO_HI.saveOrUpdate(poL);

				}

			} else {
				for (SrmPoHeadersEntity_HI_RO o : lines) {
					row.setIntfStatus("E");
					row.setDataDirection("IN");
					row.setInData(jsonStr);
					row.setIntfType(transCode);
					row.setOperatorUserId(userId);
					row.setOutData(retInfo.get("returnMsg"));
					srmIntfLogsDAO_HI.saveOrUpdate(row);

				}
				return SToolUtils.convertResultJSONObj("E", "采购订单更新失败，不能推U9!", 0, null);
			}

		} catch (Exception e) {
			for (SrmPoHeadersEntity_HI_RO o : lines) {
				row.setIntfStatus("E");
				row.setDataDirection("IN");
				row.setInData(jsonStr);
				row.setIntfType(transCode);
				row.setOperatorUserId(userId);
				row.setOutData(retInfo.get("returnMsg"));
				srmIntfLogsDAO_HI.saveOrUpdate(row);

			}
			return SToolUtils.convertResultJSONObj("E", "采购订单更新失败，不能推U9!", 0, null);

		}
		return null;

	}

	@Override
	public void updatePoOrder(String polineIds, Integer userId) {
		SrmIntfLogsEntity_HI row = new SrmIntfLogsEntity_HI();
		PoInfoBean header = null;

		StringBuffer sql = new StringBuffer(SrmPoHeadersEntity_HI_RO.GET_PO_CHANGE_SQL);
		if (null != polineIds && polineIds.length() > 0) {
			sql.append(" and l.po_line_id  in (" + polineIds + ") order by h.po_number ");
		}

		try {
			List<SrmPoHeadersEntity_HI_RO> lines = srmPoHeadersDAO_HI_RO.findList(sql);
			List<String> nums = new ArrayList<String>();
			List<String> jsonStrs = new ArrayList<String>();
			PoLineInfoBean line = null;

			for (SrmPoHeadersEntity_HI_RO entity : lines) {
				line = new PoLineInfoBean();

				if (!nums.contains(entity.getPoNumber())) {
					if (null != header) {
						jsonStrs.add(JSONObject.toJSONString(header));
					}

					header = new PoInfoBean();
					header.setDocNo(entity.getPoNumber());
					header.setOperateType(String.valueOf(1));
					header.setOperator(entity.getEmployeeNumber());
					nums.add(entity.getPoNumber());
				}

				line.setReqQtyTU(convertStr(entity.getDemandQty()));
				line.setDocLineNo(convertStr(entity.getLineNumber()));

				header.getPOLineS().add(line);

			}

			String transCode = U9IntfUtils.PO_ORDER_CODE;
			Map<String, String> retInfo = null;
			for (String jsonStr : jsonStrs) {
				System.out.println("jsonStr:" + jsonStr);

				Thread.sleep(1000);
				retInfo = U9Client.invokeService(jsonStr, transCode);
				LOGGER.info("returnMsg:" + retInfo.get("returnMsg"));

				if ("S".equalsIgnoreCase(retInfo.get("returnMsg"))) {
					row.setIntfStatus("S");
					row.setDataDirection("IN");
					row.setInData(jsonStr);
					row.setIntfType(transCode);
					row.setOperatorUserId(userId);
					row.setOutData(retInfo.get("returnMsg"));
					// row.setDocNumber("PO关闭行:" + lineIds.toString());
					srmIntfLogsDAO_HI.saveOrUpdate(row);

					Map<String, Object> param = new HashMap<String, Object>();
					SrmPoLinesEntity_HI poL = new SrmPoLinesEntity_HI();

					for (int i = 0; i < lines.size(); i++) {
						param.put("po_line_id", lines.get(i).getPoLineId());
						poL = srmPoLinesDAO_HI.findByProperty(param).get(0);
						poL.setAttribute1("Y");
						poL.setLastUpdatedBy(userId);
						poL.setLastUpdateDate(new Date());
						poL.setLastUpdateLogin(userId);
						poL.setOperatorUserId(userId);
						srmPoLinesDAO_HI.saveOrUpdate(poL);
					}

				}

			}

		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			//e.printStackTrace();
			row.setIntfStatus("E");
			row.setErrorMsg(e.getMessage());
			try {
				srmIntfLogsDAO_HI.saveOrUpdate(row);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	public String convertStr(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	public boolean isStr(Object str) {
		if (null != str && String.valueOf(str).trim().length() > 0)
			return true;
		return false;
	}

	public String convertCode(String instid) {
		if ("85".equals(instid))
			return "Homa";
		else if ("90".equals(instid))
			return "301";
		else if ("91".equals(instid))
			return "302";

		return "";

	}

}
