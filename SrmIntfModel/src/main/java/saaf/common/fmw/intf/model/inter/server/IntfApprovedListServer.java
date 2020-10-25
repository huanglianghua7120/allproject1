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
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.intf.bean.ApprovedListInfoBean;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;
import saaf.common.fmw.intf.model.entities.readonly.SrmApprovedListEntity_HI_RO;
import saaf.common.fmw.intf.model.inter.IIntfApprovedList;
import saaf.common.fmw.intf.util.U9Client;
import saaf.common.fmw.intf.util.U9IntfUtils;
import saaf.common.fmw.po.model.entities.SrmPoApprovedListEntity_HI;

@Component("intfApprovedListServer")
public class IntfApprovedListServer implements IIntfApprovedList{
	private static final Logger LOGGER = LoggerFactory.getLogger(IntfApprovedListServer.class);
	
	@Autowired
	private ViewObject<SrmIntfLogsEntity_HI> srmIntfLogsDAO_HI;
	@Autowired
	private BaseViewObject<SrmApprovedListEntity_HI_RO> srmApprovedListDAO_HI_RO;
	@Autowired
	private ViewObject<SrmPoApprovedListEntity_HI> srmPoApprovedListDAO_HI;
	
	
	public Integer getListId (String supplierCode ,String itemCode,List<SrmApprovedListEntity_HI_RO> list){
		try{
		for (SrmApprovedListEntity_HI_RO row :list){
			if (supplierCode.equals(  row.getSupplierCode())&&itemCode.equals(row.getItemCode())){
				
				return row.getListId();
			}
			
		}}catch(Exception e){
	}
		return null;
	}
	
	
	/**
	 * 批量传U9
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public JSONObject pushApprovedLists( Integer userId) throws Exception{
		SrmIntfLogsEntity_HI row = new SrmIntfLogsEntity_HI();
		try{
			 
			StringBuffer sb=new StringBuffer();
			sb.append(SrmApprovedListEntity_HI_RO.SRM_PO_APPROVED_LIST);
			sb.append(" and ifnull(t.pass_u9_flag,'N') ='N' ");
			sb.append("  Limit   0,2   ");
			List<SrmApprovedListEntity_HI_RO> list=srmApprovedListDAO_HI_RO.findList(sb.toString());
			if(list.size()<=0){
				return SToolUtils.convertResultJSONObj("F", "没有需要传U9的数据！", 0, null);
			}
			
			
			List<ApprovedListInfoBean> lists = new ArrayList<ApprovedListInfoBean>();
			for (int i =0 ;i<list.size();i++){
			SrmApprovedListEntity_HI_RO en=list.get(i);
			ApprovedListInfoBean su=new ApprovedListInfoBean();
			
//			private String OperateType;//操作方式 0 新增 2 修改
			su.setOperateType("0");
			su.setRowNo(en.getRowNo().toString());
			su.setItemCode(en.getItemCode());
			su.setSupplierCode(en.getSupplierCode());
			su.setFromDate(en.getFromDate());
			su.setToDate(en.getToDate());
			su.setState(en.getStatus());
			su.setDaySupplyAmount(en.getDayCapacity());
			lists.add(su);
			}
			Date currentDate = new Date();
			Map <String ,Object >   map=new HashMap();
			
			map.put("JsonNO", "SRM" + currentDate.getTime());
			map.put("U9MessageNO", "");
			map.put("supplierSourceList", lists);

			String jsonStr = JSONObject.toJSONString(map);
			
			System.out.println("jsonStr:" + jsonStr);
			String transCode = U9IntfUtils.APPROVED_LISTS_TRANS_CODE;

			row.setIntfStatus("W");
			row.setDataDirection("IN");
			row.setInData(jsonStr);
			row.setIntfType(transCode);
			row.setOperatorUserId(userId);
			srmIntfLogsDAO_HI.saveOrUpdate(row);
			Map<String, String> retInfo = U9Client.invokeService(jsonStr, transCode);
			row.setIntfStatus(retInfo.get("status"));
			row.setOutData(retInfo.get("returnMsg"));
			srmIntfLogsDAO_HI.saveOrUpdate(row);
			String JsonStr = null;
			LOGGER.info("returnMsg:" + retInfo.get("returnMsg"));
			LOGGER.info("JsonStr:" + JsonStr);
			JSONObject retJson = JSON.parseObject(retInfo.get("returnMsg"));
			if ("500".equals(retJson.getString("RetCode"))) {
				return SToolUtils.convertResultJSONObj("E", "导入U9失败：" + retJson.getString("RetMsg"), 0, retJson);
			}

			String retJsonStr = retJson.getString("JsonStr");
			JSONArray valuesArray = JSONObject.parseArray(jsonStr);
			for (int i = 0; i < valuesArray.size(); i++) {
				JSONObject lineJson = valuesArray.getJSONObject(i);
				String supplierCode = lineJson.getString("SupplierCode");
				String itemCode = lineJson.getString("ItemCode");
				Integer listId = this.getListId(supplierCode, itemCode, list);
				
				if (listId == null) {
					if (lineJson.getInteger("RetCode") == 200) {
						SrmPoApprovedListEntity_HI listRow = srmPoApprovedListDAO_HI.findByProperty("list_Id", listId)
								.get(0);
						listRow.setOperatorUserId(userId);
						listRow.setPassU9Flag("S");
						listRow.setAttribute1(null);
						srmPoApprovedListDAO_HI.save(listRow);
					} else {
						SrmPoApprovedListEntity_HI listRow = srmPoApprovedListDAO_HI.findByProperty("list_Id", listId)
								.get(0);
						listRow.setOperatorUserId(userId);
						listRow.setPassU9Flag("S");
						listRow.setAttribute1(lineJson.getString("RetMsg"));
						srmPoApprovedListDAO_HI.save(listRow);
					}
				}

			}
			
			
			return null;
		}catch (Exception e) {
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
			return SToolUtils.convertResultJSONObj("E",   e.getMessage(), 0, null);	
		}
		
	}
    
	/**
	 * 批量传U9
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public JSONObject pushLoopApprovedList(Integer userId) throws Exception {
 		try {

			StringBuffer sb = new StringBuffer();
			sb.append(SrmApprovedListEntity_HI_RO.SRM_PO_APPROVED_LIST);
			sb.append(" and ifnull(t.pass_u9_flag,'N') ='N' ");
//			sb.append("  Limit   0,2   ");
			List<SrmApprovedListEntity_HI_RO> list = srmApprovedListDAO_HI_RO.findList(sb.toString());
			if (list.size() <= 0) {
				return SToolUtils.convertResultJSONObj("F", "没有需要传U9的数据！", 0, null);
			}
			for (int i = 0; i < list.size(); i++) {
				this.pushApprovedList(list.get(i).getListId(), userId);
			}
			Thread.sleep(10000);
			return null;
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			//e.printStackTrace();
			
			return SToolUtils.convertResultJSONObj("E", e.getMessage(), 0, null);
		}

	}
	
	
	
	
	public JSONObject pushApprovedList(Integer listId, Integer userId) throws Exception{
		SrmIntfLogsEntity_HI row = new SrmIntfLogsEntity_HI();
		try{
			Map map=new HashMap();
			map.put("listId", listId);
			StringBuffer sb=new StringBuffer();
			sb.append(SrmApprovedListEntity_HI_RO.SRM_PO_APPROVED_LIST);
			sb.append(" and t.list_id=:listId ");
			List<SrmApprovedListEntity_HI_RO> list=srmApprovedListDAO_HI_RO.findList(sb.toString(), map);
			if(list.size()<=0){
				return SToolUtils.convertResultJSONObj("E", "没找到货源表信息！", 0, null);
			}
			SrmPoApprovedListEntity_HI listRow = srmPoApprovedListDAO_HI.findByProperty("list_Id",listId).get(0);

			SrmApprovedListEntity_HI_RO en=list.get(0);
			ApprovedListInfoBean su=new ApprovedListInfoBean();
			
			String operateType = "0"; //新增
			
			if("U".equals( listRow.getPassU9Flag())){
				operateType="2"; //修改
				
			}
			
			
//			private String OperateType;//操作方式 0 新增 2 修改
			Date currentDate = new Date();
			su.setU9MessageNO("");
			su.setJsonNO("SRM" + currentDate.getTime());
			su.setOperateType(operateType);
			su.setRowNo(en.getRowNo().toString());
			su.setItemCode(en.getItemCode());
			su.setSupplierCode(en.getSupplierCode());
			su.setFromDate(en.getFromDate());
			su.setToDate(en.getToDate());
			su.setState(en.getStatus());

			String jsonStr = JSONObject.toJSONString(su);
			System.out.println("jsonStr:" + jsonStr);
			String transCode = U9IntfUtils.APPROVED_LIST_TRANS_CODE;

			row.setIntfStatus("W");
			row.setDataDirection("IN");
			row.setInData(jsonStr);
			row.setIntfType(transCode);
			row.setOperatorUserId(userId);
			row.setAttribute1(listId.toString());
			srmIntfLogsDAO_HI.saveOrUpdate(row);
			Map<String, String> retInfo = U9Client.invokeService(jsonStr, transCode);
			row.setIntfStatus(retInfo.get("status"));
			row.setOutData(retInfo.get("returnMsg"));
			srmIntfLogsDAO_HI.saveOrUpdate(row);
			
			if ("E".equals(retInfo.get("status"))){
				listRow.setOperatorUserId(userId);
				listRow.setAttribute1(retInfo.get("returnMsg"));
				srmPoApprovedListDAO_HI.save(listRow);
				return SToolUtils.convertResultJSONObj("E",  "导入U9失败：" +retInfo.get("returnMsg"), 0, null);
			}
			
			JSONObject retJson = JSON.parseObject(retInfo.get("returnMsg"));

			if (!"200".equals(retJson.getString("RetCode"))) {
				listRow.setOperatorUserId(userId);
				listRow.setAttribute1(retJson.getString("RetMsg"));
				srmPoApprovedListDAO_HI.save(listRow);
				row.setIntfStatus("E");
				srmIntfLogsDAO_HI.saveOrUpdate(row);
				return SToolUtils.convertResultJSONObj("E",  "导入U9失败：" + retJson.getString("RetMsg"), 0, retJson);
			}	
			listRow.setOperatorUserId(userId);
			listRow.setPassU9Flag("S");
			listRow.setAttribute1(retJson.getString("RetMsg"));
			
			
			
			
			srmPoApprovedListDAO_HI.save(listRow);
			
			row.setIntfStatus("S");
			srmIntfLogsDAO_HI.saveOrUpdate(row);
			String JsonStr = null;
			LOGGER.info("returnMsg:" + retInfo.get("returnMsg"));
			LOGGER.info("JsonStr:" + JsonStr);
			return null;
		}catch (Exception e) {
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
			return SToolUtils.convertResultJSONObj("E",   e.getMessage(), 0, null);	
		}
		
	}

}
