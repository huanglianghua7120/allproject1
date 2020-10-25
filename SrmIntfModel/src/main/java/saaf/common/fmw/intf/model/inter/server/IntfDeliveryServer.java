package saaf.common.fmw.intf.model.inter.server;

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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.intf.bean.DeliveryInfoBean;
import saaf.common.fmw.intf.bean.DeliveryRevLinesInfoBean;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;
import saaf.common.fmw.intf.model.entities.readonly.SrmDeliveryEntity_HI_RO;
import saaf.common.fmw.intf.model.inter.IIntfDelivery;
import saaf.common.fmw.intf.util.U9Client;
import saaf.common.fmw.intf.util.U9IntfUtils;
import saaf.common.fmw.po.model.entities.SrmPoDeliveryHeadersEntity_HI;
import saaf.common.fmw.utils.SrmUtils;

@Component("intfDeliveryServer")
public class IntfDeliveryServer implements IIntfDelivery {
	private static final Logger LOGGER = LoggerFactory.getLogger(IntfDeliveryServer.class);

	@Autowired
	private ViewObject<SrmIntfLogsEntity_HI> srmIntfLogsDAO_HI;
	@Autowired
	private BaseViewObject<SrmDeliveryEntity_HI_RO> srmDeliveryDAO_HI_RO;
	@Autowired
	private ViewObject<SrmPoDeliveryHeadersEntity_HI> srmPoDeliveryHeadersDAO_HI;
	
	@Autowired
	private ViewObject<SaafUsersEntity_HI> SaafUsersDAO_HI;

	public JSONObject pushDelivery(Integer deliveryHeaderId, Integer userId) throws Exception {
		SrmIntfLogsEntity_HI row = new SrmIntfLogsEntity_HI();
		try {
			Map map = new HashMap();
			map.put("deliveryHeaderId", deliveryHeaderId);
			StringBuffer sb = new StringBuffer();
			sb.append(SrmDeliveryEntity_HI_RO.SRM_PO_DELIVERY_HEADERS);
			sb.append(" and t.delivery_header_id=:deliveryHeaderId ");
			String receiveName ="";
			try{
			SaafUsersEntity_HI userRow = 	SaafUsersDAO_HI.getById(userId);
			receiveName = userRow.getUserFullName();
			}catch(Exception e){
				receiveName="";
			}
			
			List<SrmDeliveryEntity_HI_RO> headList = srmDeliveryDAO_HI_RO.findList(sb.toString(), map);
			if (headList.size() <= 0) {
				return SToolUtils.convertResultJSONObj("E", "推送u9失败:没找到订单信息！", 0, null);
			}

			SrmPoDeliveryHeadersEntity_HI deliverRow = srmPoDeliveryHeadersDAO_HI
					.findByProperty("delivery_header_id", deliveryHeaderId).get(0);

			StringBuffer lineSql = new StringBuffer();
			
			String validate = this.validateDelivery(deliverRow.getDeliveryNumber(), deliveryHeaderId, deliverRow.getAttribute1());
			if (!"S".equals(validate)){
				return SToolUtils.convertResultJSONObj("E",validate , 0, null);
			}
			

			if ("1".equals(deliverRow.getAttribute1())) {
				lineSql.append(SrmDeliveryEntity_HI_RO.SRM_PO_DELIVERY_LINES);
			} else {
				lineSql.append(SrmDeliveryEntity_HI_RO.SRM_PO_DELIVERY_LINES2);
			}

			List<SrmDeliveryEntity_HI_RO> lineList = srmDeliveryDAO_HI_RO.findList(lineSql.toString(), map);

			if (lineList.size() <= 0) {
				return SToolUtils.convertResultJSONObj("E", "没找到订单信息！", 0, null);
			}

			JSONArray array = new JSONArray();
			for (int i = 0; i < lineList.size(); i++) {
				SrmDeliveryEntity_HI_RO srmLine = lineList.get(i);
				DeliveryRevLinesInfoBean line = new DeliveryRevLinesInfoBean();
				Integer lineNum = i * 10 + 10;
				line.setDocLineNo(lineNum.toString());
				line.setItemCode(srmLine.getItemCode());
				line.setPoLineNo(srmLine.getLineNumber());
				line.setPOQty(srmLine.getDeliveryOrderQty().toString());
				line.setSeibanCode(srmLine.getSpecialUseNum());
				if (SrmUtils.isNvl(srmLine.getPlanLineId())) {
					// return SToolUtils.convertResultJSONObj("E",
					// deliverRow.getDeliveryNumber() + "导入U9失败：对应的采购订单【"
					// + srmLine.getPoNumber() + "】行【" + srmLine.getLineNumber()
					// + "】无计划行id", 0, null);
					line.setSrcDoc("");
				} else {
					line.setSrcDoc(SrmUtils.getString(srmLine.getPlanLineId()));
				}
				line.setPoNo(srmLine.getPoNumber());
				array.add(line);
			}

			List<DeliveryRevLinesInfoBean> list = JSONObject.parseArray(
					JSONObject.toJSONString(array, SerializerFeature.WriteClassName), DeliveryRevLinesInfoBean.class);

			DeliveryInfoBean di = new DeliveryInfoBean();
			SrmDeliveryEntity_HI_RO srmHead = headList.get(0);

			Date currentDate = new Date();
			di.setJsonNO("SRM" + U9IntfUtils.DELIVERY_TRANS_CODE + srmHead.getDeliveryNumber());
			di.setDocumentType("RCVA");
			di.setDocNo(srmHead.getDeliveryNumber());
			di.setCurrency("RMB");
			String businessDate = null;
			try {
				businessDate = SToolUtils.date2String(srmHead.getDeliveryDate(), "yyyy-MM-dd");
			} catch (Exception e) {
				businessDate = "";
			}
			di.setBusinessDate(businessDate);
			di.setSupplierCode(srmHead.getSupplierNumber());
			di.setBRANCH_CODE(srmHead.getInstCode());
			di.setReceiveName(receiveName);
			di.setRCVLineS(list);

			String jsonStr = JSONObject.toJSONString(di);
			System.out.println("jsonStr:" + jsonStr);
			String transCode = U9IntfUtils.DELIVERY_TRANS_CODE;

			row.setIntfStatus("W");
			row.setDataDirection("IN");
			row.setInData(jsonStr);
			row.setIntfType(transCode);
			row.setOperatorUserId(userId);
			srmIntfLogsDAO_HI.saveOrUpdate(row);
			Map<String, String> retInfo = U9Client.invokeService(jsonStr, transCode);
			row.setIntfStatus(retInfo.get("status"));
			row.setOutData(retInfo.get("returnMsg"));
			row.setOperatorUserId(userId);
			srmIntfLogsDAO_HI.saveOrUpdate(row);
			if ("E".equals(retInfo.get("status"))) {
				return SToolUtils.convertResultJSONObj("E", di.getDocNo() + "导入U9失败：" + retInfo.get("returnMsg"), 0,
						null);
			}

			String JsonStr = null;
			LOGGER.info("returnMsg:" + retInfo.get("returnMsg"));
			LOGGER.info("JsonStr:" + JsonStr);
			JSONObject retJson = JSON.parseObject(retInfo.get("returnMsg"));
			if (!"200".equals(retJson.getString("RetCode"))) {

				row.setIntfStatus("E");
				row.setErrorMsg(di.getDocNo() + "导入U9失败：" + retJson.getString("RetMsg"));
				row.setOperatorUserId(userId);
				srmIntfLogsDAO_HI.saveOrUpdate(row);

				return SToolUtils.convertResultJSONObj("E", di.getDocNo() + "导入U9失败：" + retJson.getString("RetMsg"), 0,
						retJson);
			}

			String u9DocNumber = null;
			try {
				JSONObject ob = JSON.parseArray(retJson.getString("RCVLst")).getJSONObject(0);
				u9DocNumber = ob.getString("DocNo");
			} catch (Exception e) {
				row.setIntfStatus("E");
				row.setErrorMsg(di.getDocNo() + "导入U9失败："+"解析返回信息失败！" + retJson.getString("RetMsg"));
				row.setOperatorUserId(userId);
				srmIntfLogsDAO_HI.saveOrUpdate(row);

				return SToolUtils.convertResultJSONObj("E", di.getDocNo() + "导入U9失败：" +"解析返回信息失败！"+ retJson.getString("RetMsg"), 0,
						retJson);
			}

			row.setIntfStatus("S");
			row.setOutData(retInfo.get("returnMsg"));
			row.setOperatorUserId(userId);
			srmIntfLogsDAO_HI.saveOrUpdate(row);

			//deliverRow.setU9DocNumber(u9DocNumber);
			//deliverRow.setReceiveFlag("Y");
			//deliverRow.setIsPassU9("Y");
			//deliverRow.setReceiveBy(userId);
			deliverRow.setOperatorUserId(userId);
			srmPoDeliveryHeadersDAO_HI.saveOrUpdate(deliverRow);

			return SToolUtils.convertResultJSONObj("S", di.getDocNo() + "导入U9成功", 0, retJson);

		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			//e.printStackTrace();
			row.setOperatorUserId(userId);
			row.setIntfStatus("E");
			row.setErrorMsg(e.getMessage());
			try {
				srmIntfLogsDAO_HI.saveOrUpdate(row);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return SToolUtils.convertResultJSONObj("E", e.getMessage(), 0, null);
		}
	}

	public String validateDelivery(String deliveryNumber, Integer deliveryHeaderId, String deliveryType)
			throws Exception {

		Map map = new HashMap();
		map.put("deliveryHeaderId", deliveryHeaderId);
		StringBuffer retMsg = new StringBuffer();
		if ("1".equals(deliveryType)) {
			StringBuffer sb = new StringBuffer();

			sb.append(SrmDeliveryEntity_HI_RO.QUERY_VALIDATE1);
			List<SrmDeliveryEntity_HI_RO> list = srmDeliveryDAO_HI_RO.findList(sb.toString(), map);
			if (list.size() < 1) {
				return "S";
			}
			int i = 0;
			for (SrmDeliveryEntity_HI_RO row : list) {
				i = i + 1;
				retMsg.append("送货单对应采购订单" + row.getPoNumber() + "[" + row.getLineNumber() + "]已经超过采购单行的剩余数量！\n");

				if (i > 5) {
					break;
				}
			}

			return deliveryNumber + "验证失败:" + retMsg;

		} else {

			StringBuffer sb = new StringBuffer();
			sb.append(SrmDeliveryEntity_HI_RO.QUERY_VALIDATE2);
			List<SrmDeliveryEntity_HI_RO> list = srmDeliveryDAO_HI_RO.findList(sb.toString(), map);
			int i = 0;
			if (list.size() > 0) {
				for (SrmDeliveryEntity_HI_RO row : list) {
					i = i + 1;
					retMsg.append("送货单对应送货通知" + row.getPoNoticeCode() + "已经超过送货通知的剩余数量！\n");

					if (i > 5) {
						break;
					}
				}
				return deliveryNumber + "验证失败:" + retMsg;
			}

			StringBuffer sb2 = new StringBuffer();
			sb2.append(SrmDeliveryEntity_HI_RO.QUERY_VALIDATE21);
			List<SrmDeliveryEntity_HI_RO> list2 = srmDeliveryDAO_HI_RO.findList(sb2.toString(), map);

			i = 0;
			if (list2.size() > 0) {
				for (SrmDeliveryEntity_HI_RO row2 : list2) {
					i = i + 1;
					retMsg.append("送货单对应送货通知" + row2.getPoNoticeCode() + "匹配的采购订单" + row2.getPoNumber() + "["
							+ row2.getLineNumber() + "]已经超过采购订单的剩余数量！\n");

					if (i > 5) {
						break;
					}
				}
				return deliveryNumber + "验证失败:" + retMsg;
			}

		}

		return "S";

	}
}
