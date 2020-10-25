package saaf.common.fmw.intf.model.inter.server;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SrmBaseCategoryDivisionEntity_HI_RO;
import saaf.common.fmw.intf.model.entities.SrmMessageHeadEntity_HI;
import saaf.common.fmw.intf.model.entities.SrmMessageLineEntity_HI;
import saaf.common.fmw.intf.model.entities.SrmMessagePushEntity_HI;
import saaf.common.fmw.intf.model.inter.ISrmSpmRequestDatas;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmCalculateResultEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmIndicatorScoreEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceSchemeEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmRequestDatasEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmRequestNoticeEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmSupplierScoreEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmTplCategoriesEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmTplCalculateManualEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmTplCalculateTempEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmTplCalculateTempItemEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmManualScoreEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmRequestDatasEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmRequestNoticeEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmSupplierExceptionEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplCategoriesEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplDimensionEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplIndicatorItemsEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplIndicatorOwerEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplIndicatorsEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmTplCalculateManualEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmTplCalculateTempEntity_HI_RO;

import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("srmSpmRequestDatasServer")
public class SrmSpmRequestDatasServer implements ISrmSpmRequestDatas{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmRequestDatasServer.class);
	
	@Autowired
	private ViewObject<SrmSpmRequestDatasEntity_HI> srmSpmRequestDatasDAO_HI;
	@Autowired
	private BaseViewObject<SrmSpmRequestDatasEntity_HI_RO> srmSpmRequestDatasDAO_HI_RO;
	@Autowired
	private ViewObject<SrmSpmPerformanceSchemeEntity_HI>srmSpmPerformanceSchemeDAO_HI;
	@Autowired
	private BaseViewObject<SrmSpmSupplierExceptionEntity_HI_RO>srmSpmSupplierExceptionDAO_HI;
	@Autowired
	private  BaseViewObject<SrmSpmTplCategoriesEntity_HI_RO> tplCategoriesEntityDAO_HI_RO;
	@Autowired
	private ViewObject<SrmSpmTplCategoriesEntity_HI> spmTplCategoriesEntityDAO_HI;
	@Autowired
	private BaseViewObject<SrmSpmTplIndicatorsEntity_HI_RO> IndicatorsEntityDAO_HI_RO;
	@Autowired
	private BaseViewObject<SrmSpmTplDimensionEntity_HI_RO>tplDimensionEntityDAO_HI_RO;
	@Autowired
	private BaseViewObject<SrmSpmManualScoreEntity_HI_RO>spmManualScoreEntityDAO_HI_RO;
	@Autowired
	private BaseViewObject<SrmSpmTplIndicatorItemsEntity_HI_RO>indicatorItemsEntityDAO_HI_RO;
	@Autowired
	private ViewObject<SrmTplCalculateManualEntity_HI> calculateManualEntityDAO_HI;
	@Autowired
	private ViewObject<SrmSpmSupplierScoreEntity_HI> spmSupplierScoreEntityDAO_HI;
	@Autowired
	private ViewObject<SrmSpmIndicatorScoreEntity_HI> spmIndicatorScoreEntityDAO_HI;
	@Autowired
	private BaseViewObject<SrmTplCalculateManualEntity_HI_RO>calculateManualEntityDAO_HI_RO;
	@Autowired
	private ViewObject<SrmSpmRequestNoticeEntity_HI>requestNoticeEntityDAO_HI;
	@Autowired
	private BaseViewObject<SrmSpmRequestNoticeEntity_HI_RO>requestNoticeEntityDAO_HI_RO;
	@Autowired
	private BaseViewObject<SrmSpmTplIndicatorOwerEntity_HI_RO>spmTplIndicatorOwerEntityDAO_HI_RO;
	@Autowired
	private ViewObject<SrmPosSupplierInfoEntity_HI>SupplierInfoEntityDAO_HI;
	@Autowired
	private ViewObject<SrmMessageHeadEntity_HI> srmMessageHeadDAO_HI;
	@Autowired
	private ViewObject<SrmMessageLineEntity_HI> srmMessageLineDAO_HI;
	@Autowired
	private ViewObject<SrmMessagePushEntity_HI> srmMessagePushDAO_HI;
	@Autowired
	private BaseViewObject<SrmBaseCategoryDivisionEntity_HI_RO>srmBaseCategoryDivisionEntityDAO_HI_RO;
	@Autowired
	private ViewObject<SrmTplCalculateTempEntity_HI> srmTplCalculateTempDAO_HI;
	@Autowired
	private ViewObject<SrmTplCalculateTempItemEntity_HI> srmTplCalculateTempItemEntityDAO_HI;
	@Autowired
	private ViewObject<SrmSpmCalculateResultEntity_HI> srmSpmCalculateResultEntityDAO_HI;
	@Autowired
	private BaseViewObject<SrmTplCalculateTempEntity_HI_RO>srmTplCalculateTempDAO_HI_RO;

	
	public SrmSpmRequestDatasServer() {
		super();
	}

	/**
	 * 指标数据收集
	 * @param jsonParams
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject  updateCalculateScheme(JSONObject jsonParams) throws Exception {
		LOGGER.info("params:-{}", JSON.toJSONString(jsonParams));
		try {
			Integer schemeId=jsonParams.getInteger("schemeId");
			if(schemeId!=null) {
				SrmSpmPerformanceSchemeEntity_HI schemeEntity_HI =srmSpmPerformanceSchemeDAO_HI.getById(schemeId);
				if(schemeEntity_HI!=null) {
					//srmSpmRequestDatasDAO_HI.executeSqlUpdate(" delete from srm_spm_request_datas WHERE scheme_id="+schemeId+" ");
					List<SrmSpmRequestDatasEntity_HI> srmSpmRequest =srmSpmRequestDatasDAO_HI.findByProperty("schemeId", schemeId);
					if(!srmSpmRequest.isEmpty()) {//删除绩效数据收集表的数据
						srmSpmRequestDatasDAO_HI.delete(srmSpmRequest);
						srmSpmRequestDatasDAO_HI.fluch();
					}
					
				    //requestNoticeEntityDAO_HI.executeSqlUpdate(" DELETE FROM srm_spm_request_notice WHERE attribute1='"+schemeEntity_HI.getSchemeId()+"'");
					List<SrmSpmRequestNoticeEntity_HI>	requestNotice =requestNoticeEntityDAO_HI.findByProperty("schemeId", schemeId);
					if(!requestNotice.isEmpty()) {//删除绩效请求计算通知表的数据
						requestNoticeEntityDAO_HI.delete(requestNotice);
						requestNoticeEntityDAO_HI.fluch();
					}
					
					Map<String, Object> queryParamMap = new HashMap<String, Object>();
				    StringBuffer queryString = new StringBuffer(SrmSpmRequestDatasEntity_HI_RO.QUERY_SUPPLIER_INFO_LIST);
				    queryParamMap.put("evaluateIntervalFrom", getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
				    queryParamMap.put("evaluateIntervalTo",getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
				    queryParamMap.put("tplId", schemeEntity_HI.getTplId());
					queryParamMap.put("orgId", schemeEntity_HI.getOrgId());
					//获取绩效供应商例外表的绩效例外对象为供应商的数据
				    List<SrmSpmSupplierExceptionEntity_HI_RO> exceplist =getExceptionEntity(schemeEntity_HI.getOrgId(),"SUPPLIER");
				    if(!exceplist.isEmpty()) {
				    	queryString.append(" and NOT EXISTS (SELECT *  FROM srm_spm_supplier_exception excep   WHERE excep.ORG_ID = "+schemeEntity_HI.getOrgId()+"   AND excep.EXCEPTION_OBJECT = 'SUPPLIER'  AND excep.exception_type='CALCULATION' AND excep.SUPPLIER_ID = tb.supplier_Id and excep.EXCEPTION_STATUS='ACTIVE')");
				    }
					//获取绩效供应商例外表的绩效例外对象为品类的数据
				    List<SrmSpmSupplierExceptionEntity_HI_RO> exceplistY =getExceptionEntity(schemeEntity_HI.getOrgId(),"CATEGORY");
				    if(!exceplistY.isEmpty()) {
				    	queryString.append(" and NOT EXISTS  (SELECT *  FROM  srm_spm_supplier_exception excep, srm_base_categories catey  WHERE excep.category_id = catey.category_id  AND excep.org_id ="+schemeEntity_HI.getOrgId()+"   AND excep.exception_object = 'CATEGORY' AND excep.exception_type='CALCULATION' AND catey.CATEGORY_Id=tb.CATEGORY_Id and excep.EXCEPTION_STATUS='ACTIVE')");
				    }
				    List<SrmSpmRequestDatasEntity_HI_RO> list =srmSpmRequestDatasDAO_HI_RO.findList(queryString.toString(), queryParamMap);
				    if(!list.isEmpty()) {
				    	for(SrmSpmRequestDatasEntity_HI_RO datas :list) {
				    		SrmSpmRequestDatasEntity_HI  datasEntity_HI =new SrmSpmRequestDatasEntity_HI();
				    		datasEntity_HI.setSchemeId(schemeEntity_HI.getSchemeId());
				    		datasEntity_HI.setOrgId(schemeEntity_HI.getOrgId());
				    		datasEntity_HI.setTplId(schemeEntity_HI.getTplId());
				    		datasEntity_HI.setEvaluateIntervalFrom(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
				    		datasEntity_HI.setEvaluateIntervalTo(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
				    		datasEntity_HI.setVendorId(datas.getVendorId());
				    		datasEntity_HI.setPoHeaderId(datas.getPoHeaderId());
				    		datasEntity_HI.setPoLineId(datas.getPoLineId());
				    		datasEntity_HI.setDeliveryHeaderId(datas.getDeliveryHeaderId());
				    		datasEntity_HI.setDeliveryLineId(datas.getDeliveryLineId());
				    		datasEntity_HI.setDetailId(datas.getDetailId());
				    		datasEntity_HI.setItemId(datas.getItemId());
				    		datasEntity_HI.setCategoryId(datas.getCategoryId());
				    		datasEntity_HI.setSegment1(datas.getDeliveryType());
				    		datasEntity_HI.setSegment2(datas.getPoNumber());
				    		datasEntity_HI.setLineNumber(datas.getLineNumber());
				    		datasEntity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
				    		srmSpmRequestDatasDAO_HI.saveOrUpdate(datasEntity_HI);
				    		srmSpmRequestDatasDAO_HI.fluch();
				    	}
				    	List<List<Integer>> listUserId = updateRequestNotice(schemeEntity_HI);
				    	Set<Integer> set = new  HashSet<Integer>(); 
				    	if(!listUserId.isEmpty()) {
				    		for(List<Integer> listT :listUserId) {
				    			if(!listT.isEmpty()) {
				    				for(Integer userId :listT){
				    					set.add(userId);
				    				}
				    			}
				    		}
				    	}
				    	//绩效模板指标负责人表userid
				    	if(!set.isEmpty()) {
				    		Map<String, Object> queryParamMapt = new HashMap<String, Object>();
				    		StringBuffer queryStringt = new StringBuffer(SrmSpmTplCategoriesEntity_HI_RO.QUERY_CATEGORIES_INFO_LIST);
				    		queryStringt.append(" and sc.TPL_ID="+schemeEntity_HI.getTplId()+" ");
				    		List<SrmSpmTplCategoriesEntity_HI_RO> cateEntityList =tplCategoriesEntityDAO_HI_RO.findList(queryStringt.toString(), queryParamMapt);
				    		//模板类别相关数据
				    		if(!cateEntityList.isEmpty()) {
				    			for(SrmSpmTplCategoriesEntity_HI_RO cateId :cateEntityList) {
				    				Map<String, Object> queryParamMapD = new HashMap<String, Object>();
				    				//绩效请求计算通知表
						    		StringBuffer queryStringD = new StringBuffer(SrmSpmRequestNoticeEntity_HI_RO.QUERY_MANUAL_INFO_LIST);
						    		queryStringD.append("  and spt.`SCHEME_ID`="+schemeId+" AND spt.`CATEGORY_ID`="+cateId.getCategoryId()+" ");
				    				List<SrmSpmRequestNoticeEntity_HI_RO> listDatast =requestNoticeEntityDAO_HI_RO.findList(queryStringD.toString(), queryParamMapD);
									//绩效请求计算通知表有数据，则发送信息给相应的负责人
				    				if(!listDatast.isEmpty()) {
				    					String []tt =schemeEntity_HI.getEvaluateIntervalFrom().split("-");
					    				Integer num =Integer.parseInt(tt[1]);
					    				String message="绩效方案"+schemeEntity_HI.getSchemeNumber()+",“"+cateId.getCategoryName()+"("+num+"月)份绩效”，绩效评价区间"+schemeEntity_HI.getEvaluateIntervalFrom()+"~"+schemeEntity_HI.getEvaluateIntervalTo()+"。还有以下指标需要您在“指标数据手工录入界面”补录。请在两天内完成，以免影响绩效结果。若已处理，请忽略本条通知。";
					    				String userName="";
					    				SrmMessageHeadEntity_HI headEntity_HI  =new SrmMessageHeadEntity_HI();
					    			    headEntity_HI.setMessageCode(schemeEntity_HI.getSchemeNumber());
					    			    headEntity_HI.setMessageType("NEED");
					    			    headEntity_HI.setMessageUrl("#/home/requestNoticeResults/"+schemeId+"/"+cateId.getCategoryId());
					    			    headEntity_HI.setMessageTex(message);
					    			    headEntity_HI.setNoticeType("APPOINT");
					    			    headEntity_HI.setMessageDesc("指标数据待录入，请查阅并处理");
					    			    headEntity_HI.setOperatorUserId(-2);
					    			    headEntity_HI.setAttribute1(String.valueOf(cateId.getCategoryId()));
					    			    headEntity_HI.setAttribute2(String.valueOf(schemeId));
					    			    srmMessageHeadDAO_HI.saveOrUpdate(headEntity_HI);
					    			    srmMessageHeadDAO_HI.fluch();
					    			    SrmMessageLineEntity_HI lineEntity_HI  =new SrmMessageLineEntity_HI();
					    			    lineEntity_HI.setMessageId(headEntity_HI.getMessageId());
					    			    lineEntity_HI.setType("USER");
					    			    lineEntity_HI.setUseridOrGroup(","+set.toString());
					    			    lineEntity_HI.setUserCode(userName);
					    			    lineEntity_HI.setOperatorUserId(-2);
					    			    srmMessageLineDAO_HI.saveOrUpdate(lineEntity_HI);
					    			    srmMessageLineDAO_HI.fluch();
					    			    for(Integer userIdt :set) {
					    			    	Map<String, Object> queryParamMapDv = new HashMap<String, Object>();
								    		StringBuffer queryStringDv = new StringBuffer(SrmBaseCategoryDivisionEntity_HI_RO.QUERY_CATEGORY_DIVISION_INFO);
								    		queryStringDv.append(" AND su.`USER_ID`="+userIdt+" AND cated.`CATEGORY_ID`="+cateId.getCategoryId()+" AND DIVISION_STATUS='ACTIVE' ");
					    			    	List<SrmBaseCategoryDivisionEntity_HI_RO> listd =srmBaseCategoryDivisionEntityDAO_HI_RO.findList(queryStringDv.toString(), queryParamMapDv);
					    			    	if(!listd.isEmpty()) {
					    			    		SrmMessagePushEntity_HI pushEntity_HI =new SrmMessagePushEntity_HI();
							   				      pushEntity_HI.setMessageId(headEntity_HI.getMessageId());
							   				      pushEntity_HI.setMessageCode(schemeEntity_HI.getSchemeNumber());
							   				      pushEntity_HI.setState("UNREADED");
							   				      pushEntity_HI.setUserId(userIdt);
							   				      pushEntity_HI.setMessageDesc("指标数据待录入，请查阅并处理");
							   				      pushEntity_HI.setMessageUrl("#/home/requestNoticeResults/"+schemeId+"/"+cateId.getCategoryId());
							   				      pushEntity_HI.setMessageType("NEED");
							   				      pushEntity_HI.setStartDateActive(new Date());
							   				      pushEntity_HI.setOperatorUserId(-2);
							   				      srmMessagePushDAO_HI.saveOrUpdate(pushEntity_HI);
							   				   srmMessagePushDAO_HI.fluch();
					    			    	}/*else {
					    			    		SrmMessagePushEntity_HI pushEntity_HI =new SrmMessagePushEntity_HI();
							   				      pushEntity_HI.setMessageId(headEntity_HI.getMessageId());
							   				      pushEntity_HI.setMessageCode(schemeEntity_HI.getSchemeNumber());
							   				      pushEntity_HI.setState("UNREADED");
							   				      pushEntity_HI.setUserId(userIdt);
							   				      pushEntity_HI.setMessageDesc("指标数据待录入，请查阅并处理");
							   				      pushEntity_HI.setMessageUrl("#/home/requestNoticeResults/"+schemeId+"/"+cateId.getCategoryId());
							   				      pushEntity_HI.setMessageType("NEED");
							   				      pushEntity_HI.setStartDateActive(new Date());
							   				      pushEntity_HI.setOperatorUserId(-2);
							   				      srmMessagePushDAO_HI.saveOrUpdate(pushEntity_HI);
					    			    	 }  */
						    			    }
				    				}
				    			}
				    		}
				    	}
				    	schemeEntity_HI.setStatus("COLLECTED");
				    	schemeEntity_HI.setOperatorUserId(-2);
				    	srmSpmPerformanceSchemeDAO_HI.saveOrUpdate(schemeEntity_HI);
				    	srmSpmPerformanceSchemeDAO_HI.fluch();
				    	return SToolUtils.convertResultJSONObj("S", "数据收集完成", 1, null);
				    }else {
				    	schemeEntity_HI.setStatus("COLLECTED");
				    	schemeEntity_HI.setOperatorUserId(-2);
				    	srmSpmPerformanceSchemeDAO_HI.saveOrUpdate(schemeEntity_HI);
				    	srmSpmPerformanceSchemeDAO_HI.fluch();
				    	return SToolUtils.convertResultJSONObj("S", "无数据可计算", 1, null);
				    }
				}else {
					return SToolUtils.convertResultJSONObj("E", "传入参数无效请检查", 1, null);
				}
			}else {
				return SToolUtils.convertResultJSONObj("E", "参数无效无法计算", 1, null);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("数据收集失败" + e);
			return SToolUtils.convertResultJSONObj("E", "数据收集失败"+ e.getMessage(), 1, null);

		}
	}


	/**
	 * 绩效请求计算通知表
	 * @param schemeEntity_HI
	 * @return
	 * @throws Exception
	 */
	private List<List<Integer>> updateRequestNotice(SrmSpmPerformanceSchemeEntity_HI schemeEntity_HI) throws Exception {
		List<List<Integer>> listY =new ArrayList<List<Integer>>();
		Map<String, Object> queryParamMapt = new HashMap<String, Object>();
		StringBuffer queryStringt = new StringBuffer(SrmSpmTplCategoriesEntity_HI_RO.QUERY_CATEGORIES_INFO_LIST);
		queryStringt.append(" and sc.TPL_ID="+schemeEntity_HI.getTplId()+" ");
		//获取绩效模板类别表数据
		List<SrmSpmTplCategoriesEntity_HI_RO> cateEntityList =tplCategoriesEntityDAO_HI_RO.findList(queryStringt.toString(), queryParamMapt);
		if(!cateEntityList.isEmpty()) {
			for(SrmSpmTplCategoriesEntity_HI_RO rowId :cateEntityList) {
				Map<String, Object> queryParamMap = new HashMap<String, Object>();
				StringBuffer queryString = new StringBuffer(SrmSpmTplDimensionEntity_HI_RO.QUERY_DIMENSION_INFO_LIST);
				queryString.append(" and sd.TPL_ID="+schemeEntity_HI.getTplId()+" ");
				//获取绩效模板维度表数据
				List<SrmSpmTplDimensionEntity_HI_RO> dimensionList =tplDimensionEntityDAO_HI_RO.findList(queryString.toString(), queryParamMap);
				if(!dimensionList.isEmpty()) {
					for(SrmSpmTplDimensionEntity_HI_RO dimensId :dimensionList) {
						Map<String, Object> queryParamMapY = new HashMap<String, Object>();
						//模板指标
						StringBuffer queryStringY = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.QUERY_DIMENSION_INFO_LIST);
						queryStringY.append(" and sti.TPL_DIMENSION_ID="+dimensId.getTplDimensionId()+" ");
						List<SrmSpmTplIndicatorsEntity_HI_RO> indicatList =IndicatorsEntityDAO_HI_RO.findList(queryStringY.toString(), queryParamMapY);
						if(!indicatList.isEmpty()) {
							for(SrmSpmTplIndicatorsEntity_HI_RO indorId :indicatList) {
								String indicatorName ="";
								if(indorId.getIndicatorCode().contains("-")) {
									String[] tt=indorId.getIndicatorCode().split("-");
									indicatorName=tt[0];
								}else {
									indicatorName=indorId.getIndicatorCode();
								}
								if("DELIVERY_RATE".equals(indicatorName)) {
									System.out.println(indicatorName);
								}else if("INSPECT_QUALIFICATION_RATE".equals(indicatorName)) {
									System.out.println(indicatorName);
								}else if("PRICE_RANK".equals(indicatorName) ) {
									System.out.println(indicatorName);
								}else {
									List<Integer> listNt=saveRequestNotice(schemeEntity_HI.getSchemeNumber(),schemeEntity_HI.getSchemeId(),schemeEntity_HI.getEvaluateIntervalFrom(),schemeEntity_HI.getEvaluateIntervalTo(),rowId.getCategoryId(),rowId.getCategoryCode(),rowId.getCategoryName(),dimensId.getEvaluateDimension(),dimensId.getEvaluateDimensionName(),indorId.getIndicatorId(),indorId.getIndicatorCode(),indorId.getIndicatorName(),indorId.getTplIndicatorId());
									listY.add(listNt);
								}
							}
						}
					}
				}
			}
		}
		return listY;
	}


	/**
	 * 绩效请求计算通知表
	 * @param schemeNumber
	 * @param schemeId
	 * @param evaluateIntervalFrom
	 * @param evaluateIntervalTo
	 * @param categoryId
	 * @param categoryCode
	 * @param categoryName
	 * @param evaluateDimension
	 * @param evaluateDimensionName
	 * @param indicatorId
	 * @param indicatorCode
	 * @param indicatorName
	 * @param tplIndicatorId
	 * @return
	 * @throws Exception
	 */
	private List<Integer> saveRequestNotice(String schemeNumber,Integer schemeId, String evaluateIntervalFrom, String evaluateIntervalTo,Integer categoryId, String categoryCode, String categoryName, String evaluateDimension,String evaluateDimensionName, Integer indicatorId, String indicatorCode, String indicatorName,Integer tplIndicatorId) throws Exception {
		  List<Integer> listY =new ArrayList<Integer>();
		
	      List<SrmSpmRequestNoticeEntity_HI> listHI =new ArrayList<SrmSpmRequestNoticeEntity_HI>();
	      SrmSpmPerformanceSchemeEntity_HI schemeEntity_HI =srmSpmPerformanceSchemeDAO_HI.getById(schemeId);
	      Map<String, Object> queryParamMap = new HashMap<String, Object>();
		  StringBuffer queryString = new StringBuffer(SrmSpmRequestDatasEntity_HI_RO.QUERY_COUNT_INFO_LIST);
		  queryParamMap.put("evaluateIntervalFrom", getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
		  queryParamMap.put("evaluateIntervalTo",getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
		  queryParamMap.put("tplId", schemeEntity_HI.getTplId());
		  queryParamMap.put("orgId", schemeEntity_HI.getOrgId());
		  queryParamMap.put("categoryId",categoryId);
		  queryParamMap.put("schemeId", schemeId);
		  //统计srm_spm_request_datas 供应商，类别id
		  List<SrmSpmRequestDatasEntity_HI_RO> list =srmSpmRequestDatasDAO_HI_RO.findList(queryString.toString(), queryParamMap);
		  if(!list.isEmpty()) {
			  for(SrmSpmRequestDatasEntity_HI_RO datas :list) {
					Map<String, Object> queryParamMapTP = new HashMap<String, Object>();
	    			StringBuffer queryStringTP = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.QUERY_DIMENSION_INFO_LIST);
	    			queryStringTP.append(" and sti.`TPL_INDICATOR_ID`="+tplIndicatorId+" and  sp.`INDICATOR_CODE` NOT IN('DELIVERY_RATE','INSPECT_QUALIFICATION_RATE','PRICE_RANK')");
				  	//通过tplIndicatorId获取模板指标相关数据
	    			List<SrmSpmTplIndicatorsEntity_HI_RO> listTp =IndicatorsEntityDAO_HI_RO.findList(queryStringTP.toString(), queryParamMapTP);
				  	if(!listTp.isEmpty()) {
				  		Map<String, Object> queryParamMapT = new HashMap<String, Object>();
		    			StringBuffer queryStringT = new StringBuffer(SrmSpmManualScoreEntity_HI_RO.QUERY_NUM);
		    			queryStringT.append(" and sce.ORG_ID="+schemeEntity_HI.getOrgId()+"  and sce.SUPPLIER_ID= "+datas.getVendorId()+"  and sce.CATEGORY_ID="+categoryId+" and sce.INDICATOR_ID="+indicatorId+" and sce.EVALUATE_DIMENSION='"+evaluateDimension+"'");
		    			//求手工录入评分表的得分平均值
		    			List<SrmSpmManualScoreEntity_HI_RO> scoreHI =spmManualScoreEntityDAO_HI_RO.findList(queryStringT.toString(), queryParamMapT);
		    			if(scoreHI.isEmpty()) {
		    				Map<String, Object> queryParamMapTN = new HashMap<String, Object>();
			    			StringBuffer queryStringTN = new StringBuffer(SrmSpmRequestNoticeEntity_HI_RO.QUERY_MANUAL_INFO_LIST);
			    			queryStringTN.append(" and spt.indicator_id="+indicatorId+" and spt.scheme_id="+schemeId+" and spt.category_id="+categoryId+" and spt.supplier_id="+datas.getVendorId()+" ");
		    				//根据条件获取绩效请求计算通知表
			    			List<SrmSpmRequestNoticeEntity_HI_RO> listNotice =requestNoticeEntityDAO_HI_RO.findList(queryStringTN.toString(), queryParamMapTN);
		    				if(listNotice.isEmpty()) {
		    				 SrmPosSupplierInfoEntity_HI  supplierInfoEntity =SupplierInfoEntityDAO_HI.getById(datas.getVendorId());
		   	   				  SrmSpmRequestNoticeEntity_HI NoticeEntity =new SrmSpmRequestNoticeEntity_HI();
		   	   				  NoticeEntity.setCategoryId(categoryId);
		   	   				  NoticeEntity.setCategoryName(categoryName);
		   	   				  NoticeEntity.setCategoryCode(categoryCode);
		   	   				  NoticeEntity.setDimensionName(evaluateDimensionName);
		   	   				  NoticeEntity.setEvaluateIntervalFrom(evaluateIntervalFrom);
		   	   				  NoticeEntity.setEvaluateIntervalTo(evaluateIntervalTo);
		   	   				  NoticeEntity.setIndicatorName(indicatorName);
		   	   				  NoticeEntity.setIndicatorId(indicatorId);
		   	   				  NoticeEntity.setSchemeId(schemeId);
		   	   				  NoticeEntity.setSupplierId(datas.getVendorId());
		   	   				  NoticeEntity.setSupplierNumber(supplierInfoEntity.getSupplierNumber());
		   	   				  NoticeEntity.setSupplierName(supplierInfoEntity.getSupplierName());
		   	   				  NoticeEntity.setUserId(schemeEntity_HI.getCreatedBy());
		   	   				  NoticeEntity.setOperatorUserId(schemeEntity_HI.getCreatedBy());
		   	   				  NoticeEntity.setSourceId(categoryId);
		   	   				  NoticeEntity.setAttribute1(String.valueOf(schemeId));
		   	   				  NoticeEntity.setAttribute2(String.valueOf(categoryId));
		   	   				  listHI.add(NoticeEntity);
		    				}
		    			}
				  	}
			  }
			  
		  }
		  
		if(!listHI.isEmpty()) {
			Map<String, Object> queryParamMapY = new HashMap<String, Object>();
			//查询绩效模板指标负责人表
			StringBuffer queryStringY = new StringBuffer(SrmSpmTplIndicatorOwerEntity_HI_RO.COUNT_MESSAGE_FLAG);
			queryStringY.append(" and ower.TPL_INDICATOR_ID="+tplIndicatorId+" ");
			List<SrmSpmTplIndicatorOwerEntity_HI_RO> owerList =spmTplIndicatorOwerEntityDAO_HI_RO.findList(queryStringY.toString(), queryParamMapY);
			if(!owerList.isEmpty()) {
				for(SrmSpmTplIndicatorOwerEntity_HI_RO rowId :owerList) {
					listY.add(rowId.getUserId());
				}
			}
			requestNoticeEntityDAO_HI.save(listHI);
			requestNoticeEntityDAO_HI.fluch();
		}
		
		return listY;
          
	}
	private List<SrmSpmSupplierExceptionEntity_HI_RO> getExceptionEntity(Integer OrgId,String Stuts){
		Map<String, Object> queryParamMapt = new HashMap<String, Object>();
	    StringBuffer queryStringt = new StringBuffer(SrmSpmSupplierExceptionEntity_HI_RO.QUERY_EXCEPTION_INFO_LIST);
	    queryStringt.append(" and se.org_id="+OrgId+"  AND se.EXCEPTION_OBJECT='"+Stuts+"' AND se.exception_type='CALCULATION'  and se.EXCEPTION_STATUS='ACTIVE'");
		List<SrmSpmSupplierExceptionEntity_HI_RO> excepList =srmSpmSupplierExceptionDAO_HI.findList(queryStringt.toString(), queryParamMapt);
		return 	excepList;
	}
	
	
	private String getMonth(String date, String type) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (!"".equals(date) && !"".equals(type)) {
			Date datt = df.parse(date+"-01");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(datt);
			calendar.add(Calendar.MONTH, 0);
			Date theDate = calendar.getTime();
			if ("First".equals(type)) {
				GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
				gcLast.setTime(theDate);
				gcLast.set(Calendar.DAY_OF_MONTH, 1);
				String day_first = df.format(gcLast.getTime());
				return day_first;
			} else if ("Last".equals(type)) {
				calendar.add(Calendar.MONTH, 1); // 加一个月
				calendar.set(Calendar.DATE, 1); // 设置为该月第一天
				calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
				String day_last = df.format(calendar.getTime());
				return day_last;
			}
		}
		return null;
	}



	/**
	 * 综合绩效计算
	 * @param jsonParams
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject updateCalculateMultiple(JSONObject jsonParams) throws Exception {
		LOGGER.info("params:-{}", JSON.toJSONString(jsonParams));
		try {
			Integer schemeId=jsonParams.getInteger("schemeId");
			if(schemeId!=null) {
				SrmSpmPerformanceSchemeEntity_HI schemeEntity_HI =srmSpmPerformanceSchemeDAO_HI.getById(schemeId);
				if(schemeEntity_HI!=null) {
						//删除绩效数据计算中间表数据
						List<SrmTplCalculateManualEntity_HI> calculateM =calculateManualEntityDAO_HI.findByProperty("schemeId", schemeId);
						if(!calculateM.isEmpty()) {
							calculateManualEntityDAO_HI.delete(calculateM);
							calculateManualEntityDAO_HI.fluch();
						}
						//删除供应商分类得分表数据
						List<SrmSpmSupplierScoreEntity_HI> spmSupplierScore =spmSupplierScoreEntityDAO_HI.findByProperty("schemeId", schemeId);
						if(!spmSupplierScore.isEmpty()) {
							for(SrmSpmSupplierScoreEntity_HI hi :spmSupplierScore) {
								//删除供应商指标得分表数据
								List<SrmSpmIndicatorScoreEntity_HI> spmIndicatorScore =spmIndicatorScoreEntityDAO_HI.findByProperty("supplierScoreId", hi.getSupplierScoreId());
								if(!spmIndicatorScore.isEmpty()) {
									spmIndicatorScoreEntityDAO_HI.delete(spmIndicatorScore);
									spmIndicatorScoreEntityDAO_HI.fluch();	
								}
							}
							spmSupplierScoreEntityDAO_HI.delete(spmSupplierScore);
							spmSupplierScoreEntityDAO_HI.fluch();
						}

						
					Map<String, Object> queryParamMapt = new HashMap<String, Object>();
					StringBuffer queryStringt = new StringBuffer(SrmSpmTplCategoriesEntity_HI_RO.QUERY_CATEGORIES_INFO_LIST);
					queryStringt.append(" and sc.TPL_ID="+schemeEntity_HI.getTplId()+" ");
					//查询模板类别表的数据
					List<SrmSpmTplCategoriesEntity_HI_RO> cateEntityList =tplCategoriesEntityDAO_HI_RO.findList(queryStringt.toString(), queryParamMapt);
					if(!cateEntityList.isEmpty()) {
						for(SrmSpmTplCategoriesEntity_HI_RO rowId :cateEntityList) {
								Map<String, Object> queryParamMap = new HashMap<String, Object>();
								StringBuffer queryString = new StringBuffer(SrmSpmTplDimensionEntity_HI_RO.QUERY_DIMENSION_INFO_LIST);
								queryString.append(" and sd.TPL_ID="+schemeEntity_HI.getTplId()+" ");
								//模板维度数据
								List<SrmSpmTplDimensionEntity_HI_RO> dimensionList =tplDimensionEntityDAO_HI_RO.findList(queryString.toString(), queryParamMap);
								if(!dimensionList.isEmpty()) {
										for(SrmSpmTplDimensionEntity_HI_RO dimensId :dimensionList) {
											Map<String, Object> queryParamMapY = new HashMap<String, Object>();
											//模板指标相关的数据
											StringBuffer queryStringY = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.QUERY_DIMENSION_INFO_LIST);
											queryStringY.append(" and sti.TPL_DIMENSION_ID="+dimensId.getTplDimensionId()+" ");
											List<SrmSpmTplIndicatorsEntity_HI_RO> indicatList =IndicatorsEntityDAO_HI_RO.findList(queryStringY.toString(), queryParamMapY);
											if(!indicatList.isEmpty()) {
												for(SrmSpmTplIndicatorsEntity_HI_RO indorId :indicatList) {
													String indicatorName ="";
													if(indorId.getIndicatorCode().contains("-")) {
														String[] tt=indorId.getIndicatorCode().split("-");
														indicatorName=tt[0];
													}else {
														indicatorName=indorId.getIndicatorCode();
													}
													if("INSPECT_QUALIFICATION_RATE".equals(indicatorName)) {//进货批次检验合格率-A类件,进货批次检验合格率-B类件
														updateCalculateDelivery(schemeId,schemeEntity_HI.getTplId(),rowId.getTplCategoryId(),rowId.getCategoryId(),dimensId.getTplDimensionId(),indorId.getTplIndicatorId(),indorId.getIndicatorId(),indorId.getDimensionWeight(),dimensId.getEvaluateDimension());
													}else if("PRICE_RANK".equals(indicatorName)) {//供应商价格排名
														updateCalculateRanking(schemeId,schemeEntity_HI.getTplId(),rowId.getTplCategoryId(),rowId.getCategoryId(),dimensId.getTplDimensionId(),indorId.getTplIndicatorId(),indorId.getIndicatorId(),indorId.getDimensionWeight(),dimensId.getEvaluateDimension());
													}else if("DELIVERY_RATE".equals(indicatorName)) {//送货通知/采购订单交货达成率
														updateCalculateRate(schemeId,schemeEntity_HI.getTplId(),rowId.getTplCategoryId(),rowId.getCategoryId(),dimensId.getTplDimensionId(),indorId.getTplIndicatorId(),indorId.getIndicatorId(),indorId.getTargetValue(),indorId.getDimensionWeight(),dimensId.getEvaluateDimension());
													}else {//其他
														updateCalculateManual(schemeId,schemeEntity_HI.getTplId(),rowId.getTplCategoryId(),dimensId.getTplDimensionId(),indorId.getTplIndicatorId(),indorId.getIndicatorId(),dimensId.getEvaluateDimension(),indorId.getIndicatorCode(),indorId.getDimensionWeight(),indorId.getScoreDeductingLimit(),dimensId.getEvaluateDimension());
													}
												}
											}else {
												break;
											}
										}
										
								}else {
									break;
								}
							}
					}else {
						return SToolUtils.convertResultJSONObj("E", "此模板没有选择物料品类，无法计算！", 1, null);
					}
					
					updateCalculatAggregate(schemeEntity_HI);
					
					schemeEntity_HI.setStatus("CALCULATED");
					schemeEntity_HI.setOperatorUserId(-2);
					srmSpmPerformanceSchemeDAO_HI.saveOrUpdate(schemeEntity_HI);
					srmSpmPerformanceSchemeDAO_HI.fluch();
					List<SrmTplCalculateTempEntity_HI>srmTplCalculateTemp =srmTplCalculateTempDAO_HI.findByProperty("schemeId", schemeId);
					if(!srmTplCalculateTemp.isEmpty()) {
						srmTplCalculateTempDAO_HI.delete(srmTplCalculateTemp);
						srmTplCalculateTempDAO_HI.fluch();
					}
					
				    //srmTplCalculateTempItemEntityDAO_HI.executeSqlUpdate("DELETE FROM srm_tpl_calculate_temp_Item WHERE scheme_id="+schemeId+"");
					List<SrmTplCalculateTempItemEntity_HI>srmTplCalculateTempItem =srmTplCalculateTempItemEntityDAO_HI.findByProperty("schemeId", schemeId);
					if(!srmTplCalculateTempItem.isEmpty()) {
						srmTplCalculateTempItemEntityDAO_HI.delete(srmTplCalculateTempItem);
						srmTplCalculateTempItemEntityDAO_HI.fluch();
					}
					List<SrmSpmCalculateResultEntity_HI>srmSpmCalculateResult =srmSpmCalculateResultEntityDAO_HI.findByProperty("schemeId", schemeId);
					if(!srmSpmCalculateResult.isEmpty()) {
						srmSpmCalculateResultEntityDAO_HI.delete(srmSpmCalculateResult);
						srmSpmCalculateResultEntityDAO_HI.fluch();
					}
					return SToolUtils.convertResultJSONObj("S", "绩效计算成功！", 1, null);
				}else {
					return SToolUtils.convertResultJSONObj("E", "参数无效无法计算", 1, null);
				}
			}else {
				return SToolUtils.convertResultJSONObj("E", "参数为空，无法计算", 1, null);
			}
		}catch (Exception e) {
			LOGGER.error("数据收集失败" + e);
			return SToolUtils.convertResultJSONObj("E", "数据收集失败"+ e.getMessage(), 1, null);

		}
	}


	/**
	 * 指标名称为 进货批次检验合格率-A类件,进货批次检验合格率-B类件
	 * @param schemeId
	 * @param tplId
	 * @param tplCategoryId
	 * @param categoryId
	 * @param dimensionId
	 * @param tplIndicatorId
	 * @param indicatorIdt
	 * @param dimensionWeight
	 * @param evaluateDimension
	 * @throws ParseException
	 */
	private void updateCalculateDelivery(Integer schemeId, Integer tplId, Integer tplCategoryId,Integer categoryId, Integer dimensionId,Integer tplIndicatorId,Integer indicatorIdt,BigDecimal dimensionWeight,String evaluateDimension) throws ParseException {
		SrmSpmPerformanceSchemeEntity_HI schemeEntity_HI =srmSpmPerformanceSchemeDAO_HI.getById(schemeId);
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
	    StringBuffer queryString = new StringBuffer(SrmSpmRequestDatasEntity_HI_RO.QUERY_COUNT_INFO_LIST);
	    queryParamMap.put("evaluateIntervalFrom", getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
	    queryParamMap.put("evaluateIntervalTo",getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
	    queryParamMap.put("tplId", schemeEntity_HI.getTplId());
	    queryParamMap.put("orgId", schemeEntity_HI.getOrgId());
	    queryParamMap.put("categoryId", categoryId);
	    queryParamMap.put("schemeId", schemeId);
	    List<SrmSpmRequestDatasEntity_HI_RO> list =srmSpmRequestDatasDAO_HI_RO.findList(queryString.toString(), queryParamMap);
	    if(!list.isEmpty()) {
	    	for(SrmSpmRequestDatasEntity_HI_RO datasHI :list) {
	    		Map<String, Object> queryParamMapY = new HashMap<String, Object>();
	    		queryParamMapY.put("supplierId", datasHI.getVendorId());
	    		//queryParamMapY.put("orgId", schemeEntity_HI.getOrgId());
	    		//queryParamMapY.put("tplId", schemeEntity_HI.getTplId());
	    		//queryParamMapY.put("schemeId", schemeId);
	    		queryParamMapY.put("categoryId", categoryId);
	    		queryParamMapY.put("evaluateIntervalFrom", getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
	    		queryParamMapY.put("evaluateIntervalTo", getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
	    		StringBuffer queryStringY = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.QUERY_DATAS_INFO_LIST);
	    		StringBuffer queryStringN = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.QUERY_REJECT_INFO_LIST);
	    		StringBuffer queryStringT = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.QUERY_CONCESSION_INFO_LIST);
	    		List<SrmSpmTplIndicatorsEntity_HI_RO> indicatorsEntity =IndicatorsEntityDAO_HI_RO.findList(queryStringY.toString(), queryParamMapY);
	    		double numY=0.00;
	    		double numN=0.00;
	    		double numT=0.00;
	    		double numR =0.00;
	    		double numRust =0.00;
	    		if(!indicatorsEntity.isEmpty()) {
	    			if(indicatorsEntity.get(0).getNumt()!=null) {
	    				numY =indicatorsEntity.get(0).getNumt().doubleValue();
	    			}
	    			
	    		}
	    		List<SrmSpmTplIndicatorsEntity_HI_RO> indicatorsEntityN =IndicatorsEntityDAO_HI_RO.findList(queryStringN.toString(), queryParamMapY);
	    		if(!indicatorsEntityN.isEmpty()) {
	    			if(indicatorsEntityN.get(0).getNumt()!=null) {
	    				numN=indicatorsEntityN.get(0).getNumt().doubleValue();
	    			}
	    			
	    		}
	    		List<SrmSpmTplIndicatorsEntity_HI_RO> indicatorsEntityT =IndicatorsEntityDAO_HI_RO.findList(queryStringT.toString(), queryParamMapY);
	    		if(!indicatorsEntityT.isEmpty()) {
	    			if(indicatorsEntityT.get(0).getNumt()!=null) {
	    				numT=indicatorsEntityT.get(0).getNumt().doubleValue();
	    			}
	    			
	    		}
	    		
	    		if(numY==0.00) {
	    			numR =1.00;
	    		}else {
	    			numR =1-numT/numY;
	    		}
	    		System.out.println("*************"+numR);
	    		Map<String, Object> queryParamMapI = new HashMap<String, Object>();
	    		StringBuffer queryStringI = new StringBuffer(SrmSpmTplIndicatorItemsEntity_HI_RO.QUERY_DIMENSION_INFO_LIST);
	    		queryStringI.append(" and spit.TPL_INDICATOR_ID="+tplIndicatorId+" ");
	    		List<SrmSpmTplIndicatorItemsEntity_HI_RO> itemsEntity_HI_RO =indicatorItemsEntityDAO_HI_RO.findList(queryStringI.toString(), queryParamMapI);
	    		if(!itemsEntity_HI_RO.isEmpty()) {
	    			for(SrmSpmTplIndicatorItemsEntity_HI_RO items :itemsEntity_HI_RO) {
	    				if(Double.parseDouble(items.getPk1Value())<= numR && numR<= Double.parseDouble(items.getPk2Value())) {
	    					numRust=items.getScore().doubleValue();
	    					break;
	    				}else {
	    					numRust=0.00;
	    				}
	    			}
	    		}
	    		
	    		SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
				Entity_HI.setSchemeId(schemeId);
				Entity_HI.setScore(BigDecimal.valueOf(numRust));
				Entity_HI.setSupplierId(datasHI.getVendorId());
				Entity_HI.setIndicatorId(indicatorIdt);
				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
				Entity_HI.setCategoryId(categoryId);
				Entity_HI.setTplCategoryId(tplCategoryId);
				Entity_HI.setTplId(tplId);
				Entity_HI.setDimensionId(dimensionId);
				Entity_HI.setAttribute1("D");
				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
				Entity_HI.setAttribute4(evaluateDimension);
				Entity_HI.setDimensionWeight(dimensionWeight);
				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
				calculateManualEntityDAO_HI.fluch();
				
	    	}
	    }
		
	}


	/**
	 * 指标名称为 供应商价格排名
	 * @param schemeId
	 * @param tplId
	 * @param tplCategoryId
	 * @param categoryId
	 * @param dimensionId
	 * @param tplIndicatorId
	 * @param indicatorIdt
	 * @param dimensionWeight
	 * @param evaluateDimension
	 * @throws ParseException
	 */
	private void updateCalculateRanking(Integer schemeId, Integer tplId, Integer tplCategoryId,Integer categoryId, Integer dimensionId,Integer tplIndicatorId,Integer indicatorIdt,BigDecimal dimensionWeight,String evaluateDimension) throws ParseException {
		SrmSpmPerformanceSchemeEntity_HI schemeEntity_HI =srmSpmPerformanceSchemeDAO_HI.getById(schemeId);
		Map<String, Object> queryParamMapH = new HashMap<String, Object>();
	    StringBuffer queryStringH = new StringBuffer(SrmSpmRequestDatasEntity_HI_RO.QUERY_COUNT_ITEM_INFO11);
	    queryParamMapH.put("categoryId", categoryId);
	    queryParamMapH.put("schemeId", schemeId);
	    queryParamMapH.put("tplId", tplId);
	    queryParamMapH.put("evaluateIntervalFrom", getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
	    queryParamMapH.put("evaluateIntervalTo", getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
	    List<SrmSpmRequestDatasEntity_HI_RO> listH =srmSpmRequestDatasDAO_HI_RO.findList(queryStringH.toString(), queryParamMapH);
	    if(!listH.isEmpty()) {
	    	for(SrmSpmRequestDatasEntity_HI_RO datasHIT:listH) {
	    		SrmTplCalculateTempItemEntity_HI temp =new SrmTplCalculateTempItemEntity_HI();
	    		temp.setSchemeId(schemeId);
	    		temp.setItemId(datasHIT.getItemId());
	    		temp.setScore(datasHIT.getScore());
	    		temp.setCategoryId(categoryId);
	    		temp.setOperatorUserId(-2);
	    		srmTplCalculateTempItemEntityDAO_HI.saveOrUpdate(temp);
	    		srmTplCalculateTempItemEntityDAO_HI.fluch();
	    	}
	    }
	    
	    Map<String, Object> queryParamMapHT = new HashMap<String, Object>();
	    StringBuffer queryStringHT = new StringBuffer(SrmSpmRequestDatasEntity_HI_RO.QUERY_COUNT_SUP_INFO11);
	    queryParamMapHT.put("categoryId", categoryId);
	    queryParamMapHT.put("schemeId", schemeId);
	    queryParamMapHT.put("tplId", tplId);
	    queryParamMapHT.put("evaluateIntervalFrom", getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
	    queryParamMapHT.put("evaluateIntervalTo", getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
	    List<SrmSpmRequestDatasEntity_HI_RO> listHT =srmSpmRequestDatasDAO_HI_RO.findList(queryStringHT.toString(), queryParamMapHT);
	    if(!listHT.isEmpty()) {
	    	for(SrmSpmRequestDatasEntity_HI_RO datasHR :listHT) {
	    		SrmTplCalculateTempEntity_HI temp =new SrmTplCalculateTempEntity_HI();
	    		temp.setCategoryId(categoryId);
	    		temp.setSchemeId(schemeId);
	    		temp.setScore(datasHR.getScore());
	    		temp.setItemId(datasHR.getItemId());
	    		temp.setSupplierId(datasHR.getVendorId());
	    		temp.setOperatorUserId(-2);
	    		srmTplCalculateTempDAO_HI.saveOrUpdate(temp);
	    		srmTplCalculateTempDAO_HI.fluch();
	    	}
	    }
	    
	    if(!listH.isEmpty()) {
	    	for(SrmSpmRequestDatasEntity_HI_RO datasHIT:listH) {
	    		 Map<String, Object> queryParamMapHTY = new HashMap<String, Object>();
	    		 StringBuffer queryStringHTY = new StringBuffer(SrmTplCalculateTempEntity_HI_RO.QUERY_COUNT_SUP_INFO);
	    		 queryParamMapHTY.put("categoryId", categoryId);
	    		 queryParamMapHTY.put("schemeId", schemeId);
	    		 List<SrmTplCalculateTempEntity_HI_RO> listRo =srmTplCalculateTempDAO_HI_RO.findList(queryStringHTY, queryParamMapHTY);
	    		 if(!listRo.isEmpty()) {
	    			 for(SrmTplCalculateTempEntity_HI_RO tempRo :listRo) {
	    				 Map<String, Object> queryParamMapT = new HashMap<String, Object>();
	    	    		 StringBuffer queryStringT = new StringBuffer(SrmTplCalculateTempEntity_HI_RO.QUERY_COUNT_SUP_INFO1);
	    	    		 queryParamMapT.put("categoryId", categoryId);
	    	    		 queryParamMapT.put("schemeId", schemeId);
	    	    		 queryParamMapT.put("itemsId", datasHIT.getItemId());
	    	    		 queryParamMapT.put("supplierId", tempRo.getSupplierId());
	    	    		 List<SrmTplCalculateTempEntity_HI_RO> ctemp =srmTplCalculateTempDAO_HI_RO.findList(queryStringT, queryParamMapT);
	    	    		 if(!ctemp.isEmpty()) {
	    	    			 SrmSpmCalculateResultEntity_HI result =new SrmSpmCalculateResultEntity_HI();
    	    				 result.setSchemeId(schemeId);
    	    				 result.setCategoryId(categoryId);
    	    				 result.setItemId(datasHIT.getItemId());
    	    				 result.setSupplierId(tempRo.getSupplierId());
    	    				 result.setScore(ctemp.get(0).getScore());
    	    				 Map<String, Object> queryParamMapTY = new HashMap<String, Object>();
		    	    		 StringBuffer queryStringTY = new StringBuffer(SrmTplCalculateTempEntity_HI_RO.QUERY_COUNT_SUP_INFO3);
		    	    		 queryParamMapTY.put("categoryId", categoryId);
		    	    		 queryParamMapTY.put("schemeId", schemeId);
		    	    		 queryParamMapTY.put("itemsId", datasHIT.getItemId());
		    	    		 List<SrmTplCalculateTempEntity_HI_RO> ctempt =srmTplCalculateTempDAO_HI_RO.findList(queryStringTY, queryParamMapTY);
    	    				 if(!ctempt.isEmpty()) {
    	    					 result.setAuntscore(ctempt.get(0).getScore());
    	    				 }else {
    	    					 result.setAuntscore(ctemp.get(0).getScore());
    	    				 }
    	    				 result.setOperatorUserId(-2);
    	    				 srmSpmCalculateResultEntityDAO_HI.saveOrUpdate(result);
    	    				 srmSpmCalculateResultEntityDAO_HI.fluch();
	    	    		 }else {
	    	    			 Map<String, Object> queryParamMapTY = new HashMap<String, Object>();
		    	    		 StringBuffer queryStringTY = new StringBuffer(SrmTplCalculateTempEntity_HI_RO.QUERY_COUNT_SUP_INFO2);
		    	    		 queryParamMapTY.put("categoryId", categoryId);
		    	    		 queryParamMapTY.put("schemeId", schemeId);
		    	    		 queryParamMapTY.put("itemsId", datasHIT.getItemId());
		    	    		 List<SrmTplCalculateTempEntity_HI_RO> ctempt =srmTplCalculateTempDAO_HI_RO.findList(queryStringTY, queryParamMapTY);
		    	    		 if(!ctempt.isEmpty()) {
		    	    			 SrmSpmCalculateResultEntity_HI result =new SrmSpmCalculateResultEntity_HI();
	    	    				 result.setSchemeId(schemeId);
	    	    				 result.setCategoryId(categoryId);
	    	    				 result.setItemId(datasHIT.getItemId());
	    	    				 result.setSupplierId(tempRo.getSupplierId());
	    	    				 result.setScore(ctempt.get(0).getScore());
	    	    				 
	    	    				 Map<String, Object> queryParamMapU = new HashMap<String, Object>();
	    	    				 StringBuffer queryStringU = new StringBuffer(SrmTplCalculateTempEntity_HI_RO.QUERY_COUNT_SUP_INFO3);
			    	    		 queryParamMapU.put("categoryId", categoryId);
			    	    		 queryParamMapU.put("schemeId", schemeId);
			    	    		 queryParamMapU.put("itemsId", datasHIT.getItemId());
			    	    		 List<SrmTplCalculateTempEntity_HI_RO> ctemptU =srmTplCalculateTempDAO_HI_RO.findList(queryStringU, queryParamMapU);
			    	    		 if(!ctemptU.isEmpty()) {
			    	    			 result.setAuntscore(ctemptU.get(0).getScore());
			    	    		 }else {
			    	    			 result.setScore(ctempt.get(0).getScore()); 
			    	    		 }
	    	    				 result.setOperatorUserId(-2);
	    	    				 srmSpmCalculateResultEntityDAO_HI.saveOrUpdate(result);
	    	    				 srmSpmCalculateResultEntityDAO_HI.fluch();
		    	    		 }
	    	    			 
	    	    		 }
	    			 }
	    		 }
	    	}
	    }
	    
	Map<String, Object> queryParamMapHU = new HashMap<String, Object>();
    StringBuffer queryStringHU = new StringBuffer(SrmTplCalculateTempEntity_HI_RO.QUERY_COUNT_SUP_INFO4);
    queryParamMapHU.put("schemeId", schemeId);
    queryParamMapHU.put("categoryId", categoryId);
    List<SrmTplCalculateTempEntity_HI_RO> listTY =srmTplCalculateTempDAO_HI_RO.findList(queryStringHU.toString(), queryParamMapHU);
    if(!listTY.isEmpty()) {
    	for(SrmTplCalculateTempEntity_HI_RO hiRo :listTY) {
    		Map<String, Object> queryParamMapHUT = new HashMap<String, Object>();
    	    StringBuffer queryStringHUT = new StringBuffer(SrmTplCalculateTempEntity_HI_RO.QUERY_COUNT_SUP_INFO5);
    	    queryParamMapHUT.put("schemeId", schemeId);
    	    queryParamMapHUT.put("categoryId", categoryId);
    	    List<SrmTplCalculateTempEntity_HI_RO> listTYT =srmTplCalculateTempDAO_HI_RO.findList(queryStringHUT.toString(), queryParamMapHUT);
    	    if(!listTYT.isEmpty()) {
    	    	SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
				Entity_HI.setSchemeId(schemeId);
				double numRust=0.00;
				if(listTYT.get(0).getScore().doubleValue()==0.00) {
					  numRust=0.00;
				}else {
					  numRust=hiRo.getScore().doubleValue()/listTYT.get(0).getScore().doubleValue()*100;
				}
								
				Entity_HI.setScore(BigDecimal.valueOf(numRust).setScale(2,BigDecimal.ROUND_HALF_UP));
				Entity_HI.setSupplierId(hiRo.getSupplierId());
				Entity_HI.setIndicatorId(indicatorIdt);
				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
				Entity_HI.setCategoryId(categoryId);
				Entity_HI.setTplCategoryId(tplCategoryId);
				Entity_HI.setTplId(tplId);
				Entity_HI.setDimensionId(dimensionId);
				Entity_HI.setDimensionWeight(dimensionWeight);
				Entity_HI.setAttribute1("K");
				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
				Entity_HI.setAttribute4(evaluateDimension);
				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
				calculateManualEntityDAO_HI.fluch();
    	    }
    	}
    }
		
	}


	/**
	 * 指标名称为 送货通知/采购订单交货达成率
	 * @param schemeId
	 * @param tplId
	 * @param tplCategoryId
	 * @param categoryId
	 * @param dimensionId
	 * @param tplIndicatorId
	 * @param indicatorIdt
	 * @param targetValue
	 * @param dimensionWeight
	 * @param evaluateDimension
	 * @throws ParseException
	 */
	private void updateCalculateRate(Integer schemeId, Integer tplId, Integer tplCategoryId,Integer categoryId, Integer dimensionId,Integer tplIndicatorId,Integer indicatorIdt,BigDecimal targetValue,BigDecimal dimensionWeight,String evaluateDimension) throws ParseException {
		SrmSpmPerformanceSchemeEntity_HI schemeEntity_HI =srmSpmPerformanceSchemeDAO_HI.getById(schemeId);
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		//统计绩效数据收集表数据
	    StringBuffer queryString = new StringBuffer(SrmSpmRequestDatasEntity_HI_RO.QUERY_COUNT_INFO_LIST);
	    queryParamMap.put("evaluateIntervalFrom", getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
	    queryParamMap.put("evaluateIntervalTo",getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
	    queryParamMap.put("tplId", schemeEntity_HI.getTplId());
	    queryParamMap.put("orgId", schemeEntity_HI.getOrgId());
	    queryParamMap.put("categoryId", categoryId);
	    queryParamMap.put("schemeId", schemeId);
	    List<SrmSpmRequestDatasEntity_HI_RO> list =srmSpmRequestDatasDAO_HI_RO.findList(queryString.toString(), queryParamMap);
	    if(!list.isEmpty()) {
	    	for(SrmSpmRequestDatasEntity_HI_RO datasHI :list) {
	    		Map<String, Object> queryParamMapY = new HashMap<String, Object>();
	    		queryParamMapY.put("supplierId", datasHI.getVendorId());
	    		//queryParamMapY.put("orgId", schemeEntity_HI.getOrgId());
	    		queryParamMapY.put("tplId", schemeEntity_HI.getTplId());
	    		queryParamMapY.put("schemeId", schemeId);
	    		queryParamMapY.put("categoryId", categoryId);
	    		queryParamMapY.put("evaluateIntervalFrom", getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
	    		queryParamMapY.put("evaluateIntervalTo",getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));

	    		StringBuffer queryStringY = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.QUERY_RATECOUNT_INFO_LIST);
	    		StringBuffer queryStringN = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.QUERY_RATE_INFO_LIST);
	    		
	    		//StringBuffer queryStringS = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.QUERY_RATECOUNT_INFO_LIST11);
	    		
	    		//StringBuffer queryStringH = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.QUERY_RATE_INFO_LIST11);
	    		
	    		List<SrmSpmTplIndicatorsEntity_HI_RO> indicatorsEntity =IndicatorsEntityDAO_HI_RO.findList(queryStringY.toString(), queryParamMapY);
	    		double numY=0.00;
	    		double numN=0.00;
	    		double numS=0.00;
	    		double numH=0.00;
	    		double numT=0.00;
	    		double numRust =0.00;
	    		if(!indicatorsEntity.isEmpty()) {
	    			if(indicatorsEntity.get(0).getNumt()!=null) {
	    				numY =indicatorsEntity.get(0).getNumt().doubleValue();
	    			}
	    		}
	    		
	    		/*List<SrmSpmTplIndicatorsEntity_HI_RO> indicatorsEntityS =IndicatorsEntityDAO_HI_RO.findList(queryStringS.toString(), queryParamMapY);
	    		if(!indicatorsEntityS.isEmpty()) {
	    			if(indicatorsEntityS.get(0).getNumt()!=null) {
	    				numS =indicatorsEntityS.get(0).getNumt().doubleValue();
	    			}
	    		}*/
	    		
	    		List<SrmSpmTplIndicatorsEntity_HI_RO> indicatorsEntityN =IndicatorsEntityDAO_HI_RO.findList(queryStringN.toString(), queryParamMapY);
	    		if(!indicatorsEntityN.isEmpty()) {
	    			if(indicatorsEntityN.get(0).getNumt()!=null) {
	    				numN =indicatorsEntityN.get(0).getNumt().doubleValue();
	    			}
	    		}
	    		
	    		/*List<SrmSpmTplIndicatorsEntity_HI_RO> indicatorsEntityH =IndicatorsEntityDAO_HI_RO.findList(queryStringH.toString(), queryParamMapY);
	    		if(!indicatorsEntityH.isEmpty()) {
	    			if(indicatorsEntityH.get(0).getNumt()!=null) {
	    				numH=indicatorsEntityH.get(0).getNumt().doubleValue();
	    			}
	    		}*/
	    		
	    		if(numN==0.00) {
	    			numT=0.00;
	    		}else {
	    			//numT=(numY+numS)/(numN+numH);
					numT=numY/numN;
	    		}
	    		//numRust =(1+(numT-targetValue.doubleValue())/(targetValue.doubleValue()*0.3))*100;
	    		numRust =(numT-targetValue.doubleValue())/(1-targetValue.doubleValue())*100;
	    		if(numRust<0.00) {
	    			numRust=0.00;
	    		}else if(numRust>100.00){
	    			numRust=100.00;
	    		}else {
	    			BigDecimal b =new BigDecimal(numRust);
	    			numRust =b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	    		}
	    		SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
				Entity_HI.setSchemeId(schemeId);
				Entity_HI.setScore(BigDecimal.valueOf(numRust));
				Entity_HI.setSupplierId(datasHI.getVendorId());
				Entity_HI.setIndicatorId(indicatorIdt);
				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
				Entity_HI.setCategoryId(categoryId);
				Entity_HI.setTplCategoryId(tplCategoryId);
				Entity_HI.setTplId(tplId);
				Entity_HI.setDimensionId(dimensionId);
				Entity_HI.setDimensionWeight(dimensionWeight);
				Entity_HI.setAttribute1("R");
				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
				Entity_HI.setAttribute4(evaluateDimension);
				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
				calculateManualEntityDAO_HI.fluch();
	    	}
	    }
	}


	/**
	 * 指标名称为其他
	 * @param schemeId
	 * @param tplId
	 * @param tplCategoryId
	 * @param dimensionId
	 * @param tplIndicatorId
	 * @param indicatorIdt
	 * @param dimensionName
	 * @param indicatorCode
	 * @param dimensionWeight
	 * @param tagetValue
	 * @param evaluateDimension
	 * @throws ParseException
	 */
	private void updateCalculateManual(Integer schemeId, Integer tplId, Integer tplCategoryId, Integer dimensionId,Integer tplIndicatorId,Integer indicatorIdt, String dimensionName,String indicatorCode,BigDecimal dimensionWeight,BigDecimal tagetValue,String evaluateDimension) throws ParseException {
		SrmSpmTplCategoriesEntity_HI cateEntity_HI =spmTplCategoriesEntityDAO_HI.getById(tplCategoryId);
		SrmSpmPerformanceSchemeEntity_HI schemeEntity_HI =srmSpmPerformanceSchemeDAO_HI.getById(schemeId);
		if("MONTH".equals(schemeEntity_HI.getEvaluatePeriod())) {//评价周期-月度
			Map<String, Object> queryParamMap = new HashMap<String, Object>();
		    StringBuffer queryString = new StringBuffer(SrmSpmRequestDatasEntity_HI_RO.QUERY_COUNT_INFO_LIST);
		    queryParamMap.put("evaluateIntervalFrom", getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
		    queryParamMap.put("evaluateIntervalTo",getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
		    queryParamMap.put("tplId", schemeEntity_HI.getTplId());
		    queryParamMap.put("orgId", schemeEntity_HI.getOrgId());
		    queryParamMap.put("categoryId", cateEntity_HI.getCategoryId());
		    queryParamMap.put("schemeId", schemeId);
		    List<SrmSpmRequestDatasEntity_HI_RO> list =srmSpmRequestDatasDAO_HI_RO.findList(queryString.toString(), queryParamMap);
		    if(!list.isEmpty()) {
		    	for(SrmSpmRequestDatasEntity_HI_RO datasHI :list) {
		    		Map<String, Object> queryParamMapYT = new HashMap<String, Object>();
		    		 StringBuffer queryStringYT = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.QUERY_DIMENSION_INFO_LIST);
		    		 queryStringYT.append(" and sti.`TPL_INDICATOR_ID`="+tplIndicatorId+" ");
		    		List<SrmSpmTplIndicatorsEntity_HI_RO> indicatorId =IndicatorsEntityDAO_HI_RO.findList(queryStringYT.toString(), queryParamMapYT);
		    		if(!indicatorId.isEmpty()) {
			    		if("MANUAL-ADD".equals(indicatorId.get(0).getIndicatorType())) {//手工录入-加分项
			    			Map<String, Object> queryParamMapY = new HashMap<String, Object>();
			    			StringBuffer queryStringY = new StringBuffer(SrmSpmManualScoreEntity_HI_RO.QUERY_ADD_INFO);
			    			queryParamMapY.put("orgId", schemeEntity_HI.getOrgId());
			    			queryParamMapY.put("supplierId", datasHI.getVendorId());
			    			queryParamMapY.put("dimensionName", dimensionName);
			    			queryParamMapY.put("categoryId", cateEntity_HI.getCategoryId());
			    			queryParamMapY.put("indicatorId", indicatorIdt);
			    			queryParamMapY.put("evaluateIntervalFrom", getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
			    			queryParamMapY.put("evaluateIntervalTo",getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
			    			List<SrmSpmManualScoreEntity_HI_RO> scoreHI =spmManualScoreEntityDAO_HI_RO.findList(queryStringY.toString(), queryParamMapY);
			    			double num=0.00;
			    			if(!scoreHI.isEmpty()) {
			    				if(scoreHI.get(0).getScore()!=null) {
			    					if(tagetValue!=null) {
			    						if(tagetValue.doubleValue()< scoreHI.get(0).getScore().doubleValue()) {
			    							num=tagetValue.doubleValue();
			    						}else {
			    							num =scoreHI.get(0).getScore().doubleValue();
			    						}
			    						double n=100.00;
			    						dimensionWeight=BigDecimal.valueOf(n);
			    					}else {
			    						if(scoreHI.get(0).getScore().doubleValue()>100.00) {
					    					num=100.00;
					    				}else {
					    					num=scoreHI.get(0).getScore().doubleValue();
					    				}
			    					}
			    					
				    				SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
				    				Entity_HI.setSchemeId(schemeId);
				    				Entity_HI.setScore(BigDecimal.valueOf(num));
				    				Entity_HI.setSupplierId(datasHI.getVendorId());
				    				Entity_HI.setDimensionWeight(dimensionWeight);
				    				Entity_HI.setIndicatorId(indicatorIdt);
				    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
				    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
				    				Entity_HI.setTplCategoryId(tplCategoryId);
				    				Entity_HI.setTplId(tplId);
				    				Entity_HI.setDimensionId(dimensionId);
				    				Entity_HI.setAttribute1("M");
				    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
				    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
				    				Entity_HI.setAttribute4(evaluateDimension);
				    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
				    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
				    				calculateManualEntityDAO_HI.fluch();
			    				}else {
			    					double numt=0.0;
									BigDecimal numY=BigDecimal.valueOf(numt);
				    				SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
				    				Entity_HI.setSchemeId(schemeId);
				    				if(tagetValue!=null) {
				    					Entity_HI.setScore(numY);
				    					double n=100.00;
			    						dimensionWeight=BigDecimal.valueOf(n);
				    				}else {
				    					Entity_HI.setScore(numY);
				    				}
				    				Entity_HI.setSupplierId(datasHI.getVendorId());
				    				Entity_HI.setDimensionWeight(dimensionWeight);
				    				Entity_HI.setIndicatorId(indicatorIdt);
				    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
				    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
				    				Entity_HI.setTplCategoryId(tplCategoryId);
				    				Entity_HI.setTplId(tplId);
				    				Entity_HI.setDimensionId(dimensionId);
				    				Entity_HI.setAttribute1("M");
				    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
				    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
				    				Entity_HI.setAttribute4(evaluateDimension);
				    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
				    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
				    				calculateManualEntityDAO_HI.fluch();
			    				}
			    				
			    			}else {
			    				double numt=0.0;
								BigDecimal numY=BigDecimal.valueOf(numt);
			    				SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
			    				Entity_HI.setSchemeId(schemeId);
			    				if(tagetValue!=null) {
			    					Entity_HI.setScore(numY);
			    					double n=100.00;
		    						dimensionWeight=BigDecimal.valueOf(n);
			    				}else {
			    					Entity_HI.setScore(numY);
			    				}
			    				Entity_HI.setSupplierId(datasHI.getVendorId());
			    				Entity_HI.setDimensionWeight(dimensionWeight);
			    				Entity_HI.setIndicatorId(indicatorIdt);
			    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
			    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
			    				Entity_HI.setTplCategoryId(tplCategoryId);
			    				Entity_HI.setTplId(tplId);
			    				Entity_HI.setDimensionId(dimensionId);
			    				Entity_HI.setAttribute1("M");
			    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
			    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
			    				Entity_HI.setAttribute4(evaluateDimension);
			    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
			    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
			    				calculateManualEntityDAO_HI.fluch();
			    			}
			    		}else if("MANUAL-DEDUCT".equals(indicatorId.get(0).getIndicatorType())) {//手工录入-扣分项
			    			Map<String, Object> queryParamMapY = new HashMap<String, Object>();
			    			StringBuffer queryStringY = new StringBuffer(SrmSpmManualScoreEntity_HI_RO.QUERY_DEDUCT_INFO);
			    			queryParamMapY.put("orgId", schemeEntity_HI.getOrgId());
			    			queryParamMapY.put("supplierId", datasHI.getVendorId());
			    			queryParamMapY.put("dimensionName", dimensionName);
			    			queryParamMapY.put("categoryId", cateEntity_HI.getCategoryId());
			    			queryParamMapY.put("indicatorId", indicatorIdt);
			    			queryParamMapY.put("evaluateIntervalFrom",getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
			    			queryParamMapY.put("evaluateIntervalTo",getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
			    			List<SrmSpmManualScoreEntity_HI_RO> scoreHI =spmManualScoreEntityDAO_HI_RO.findList(queryStringY.toString(), queryParamMapY);
			    			double num=0.00;
			    			if(!scoreHI.isEmpty()) {
			    				Map<String, Object> queryParamMapYD = new HashMap<String, Object>();
				    			StringBuffer queryStringYD = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.QUERY_DIMENSION_INFO_LIST);
				    			queryStringYD.append("  AND sti.`TPL_DIMENSION_ID`="+dimensionId+"");
			    				List<SrmSpmTplIndicatorsEntity_HI_RO> listYD =IndicatorsEntityDAO_HI_RO.findList(queryStringYD.toString(), queryParamMapYD);
			    				if(listYD.size()==1 && scoreHI.get(0).getScore()!=null) {
			    					if(tagetValue.doubleValue() < scoreHI.get(0).getScore().doubleValue()) {
		    							num=100-tagetValue.doubleValue();
		    						}else {
		    							num=100-scoreHI.get(0).getScore().doubleValue();
		    						}
		    						double n=100.00;
		    						dimensionWeight=BigDecimal.valueOf(n);
		    						SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
				    				Entity_HI.setSchemeId(schemeId);
				    				Entity_HI.setScore(BigDecimal.valueOf(num));
				    				Entity_HI.setSupplierId(datasHI.getVendorId());
				    				Entity_HI.setDimensionWeight(dimensionWeight);
				    				Entity_HI.setIndicatorId(indicatorIdt);
				    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
				    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
				    				Entity_HI.setTplCategoryId(tplCategoryId);
				    				Entity_HI.setTplId(tplId);
				    				Entity_HI.setDimensionId(dimensionId);
				    				Entity_HI.setAttribute1("TY");
				    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
				    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
				    				Entity_HI.setAttribute4(evaluateDimension);
				    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
				    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
				    				calculateManualEntityDAO_HI.fluch();
			    				}else{
			    					if(scoreHI.get(0).getScore()!=null) {
				    					if(tagetValue!=null) {
				    						if(tagetValue.doubleValue() < scoreHI.get(0).getScore().doubleValue()) {
				    							num=-tagetValue.doubleValue();
				    						}else {
				    							num=-scoreHI.get(0).getScore().doubleValue();
				    						}
				    						double n=100.00;
				    						dimensionWeight=BigDecimal.valueOf(n);
				    					}else {
				    						if(100.00-scoreHI.get(0).getScore().doubleValue()<0.00) {
						    					num=0.00;
						    				}else {
						    					num=100.00-scoreHI.get(0).getScore().doubleValue();
						    				}
				    					}

					    				SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
					    				Entity_HI.setSchemeId(schemeId);
					    				Entity_HI.setScore(BigDecimal.valueOf(num));
					    				Entity_HI.setSupplierId(datasHI.getVendorId());
					    				Entity_HI.setDimensionWeight(dimensionWeight);
					    				Entity_HI.setIndicatorId(indicatorIdt);
					    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
					    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
					    				Entity_HI.setTplCategoryId(tplCategoryId);
					    				Entity_HI.setTplId(tplId);
					    				Entity_HI.setDimensionId(dimensionId);
					    				Entity_HI.setAttribute1("M");
					    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
					    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
					    				Entity_HI.setAttribute4(evaluateDimension);
					    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
					    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
					    				calculateManualEntityDAO_HI.fluch();
				    				}else {
				    					double numt=100.0;
				    					double numtY=0.0;
										BigDecimal numY=BigDecimal.valueOf(numt);
										BigDecimal numYt=BigDecimal.valueOf(numtY);
					    				SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
					    				Entity_HI.setSchemeId(schemeId);
					    				if(tagetValue!=null) {
					    					Entity_HI.setScore(numYt);
					    					double n=100.00;
				    						dimensionWeight=BigDecimal.valueOf(n);
					    				}else {
					    					Entity_HI.setScore(numY);
					    				}
					    				Entity_HI.setSupplierId(datasHI.getVendorId());
					    				Entity_HI.setDimensionWeight(dimensionWeight);
					    				Entity_HI.setIndicatorId(indicatorIdt);
					    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
					    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
					    				Entity_HI.setTplCategoryId(tplCategoryId);
					    				Entity_HI.setTplId(tplId);
					    				Entity_HI.setDimensionId(dimensionId);
					    				Entity_HI.setAttribute1("M");
					    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
					    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
					    				Entity_HI.setAttribute4(evaluateDimension);
					    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
					    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
					    				calculateManualEntityDAO_HI.fluch();
				    				}
			    				}
			    			}else {
			    				double numt=100.0;
			    				double numtY=0.0;
								BigDecimal numY=BigDecimal.valueOf(numt);
								BigDecimal numYt=BigDecimal.valueOf(numtY);
			    				SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
			    				Entity_HI.setSchemeId(schemeId);
			    				if(tagetValue!=null) {
			    					Entity_HI.setScore(numYt);
			    					double n=100.00;
		    						dimensionWeight=BigDecimal.valueOf(n);
			    				}else {
			    					Entity_HI.setScore(numY);
			    				}
			    				Entity_HI.setSupplierId(datasHI.getVendorId());
			    				Entity_HI.setDimensionWeight(dimensionWeight);
			    				Entity_HI.setIndicatorId(indicatorIdt);
			    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
			    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
			    				Entity_HI.setTplCategoryId(tplCategoryId);
			    				Entity_HI.setTplId(tplId);
			    				Entity_HI.setDimensionId(dimensionId);
			    				Entity_HI.setAttribute1("M");
			    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
			    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
			    				Entity_HI.setAttribute4(evaluateDimension);
			    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
			    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
			    				calculateManualEntityDAO_HI.fluch();
			    			}
			    		}else if("MANUAL-SCORE".equals(indicatorId.get(0).getIndicatorType())) {//手工录入-得分项
			    			Map<String, Object> queryParamMapY = new HashMap<String, Object>();
			    			StringBuffer queryStringY = new StringBuffer(SrmSpmManualScoreEntity_HI_RO.QUERY_AVG_INFO);
			    			queryParamMapY.put("orgId", schemeEntity_HI.getOrgId());
			    			queryParamMapY.put("supplierId", datasHI.getVendorId());
			    			queryParamMapY.put("dimensionName", dimensionName);
			    			queryParamMapY.put("categoryId", cateEntity_HI.getCategoryId());
			    			queryParamMapY.put("indicatorId", indicatorIdt);
			    			queryParamMapY.put("evaluateIntervalFrom", getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
			    			queryParamMapY.put("evaluateIntervalTo",getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
			    			List<SrmSpmManualScoreEntity_HI_RO> scoreHI =spmManualScoreEntityDAO_HI_RO.findList(queryStringY.toString(), queryParamMapY);
			    			if(!scoreHI.isEmpty()) {
			    				double num=0.00;
			    				if(scoreHI.get(0).getScore()!=null) {
			    					if(tagetValue!=null) {
				    					if(tagetValue.doubleValue()<scoreHI.get(0).getScore().doubleValue()) {
				    						num=tagetValue.doubleValue();
				    					}else {
				    						num=scoreHI.get(0).getScore().doubleValue();
				    					}
				    					double n=100.00;
			    						dimensionWeight=BigDecimal.valueOf(n);
				    				}else {
				    					num=scoreHI.get(0).getScore().doubleValue();
				    				}
			    				}else {
			    					num=0.00;
			    				}
			    				
			    				SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
			    				Entity_HI.setSchemeId(schemeId);
			    				Entity_HI.setScore(BigDecimal.valueOf(num));
			    				Entity_HI.setSupplierId(datasHI.getVendorId());
			    				Entity_HI.setDimensionWeight(dimensionWeight);
			    				Entity_HI.setIndicatorId(indicatorIdt);
			    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
			    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
			    				Entity_HI.setTplCategoryId(tplCategoryId);
			    				Entity_HI.setTplId(tplId);
			    				Entity_HI.setDimensionId(dimensionId);
			    				Entity_HI.setAttribute1("M");
			    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
			    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
			    				Entity_HI.setAttribute4(evaluateDimension);
			    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
			    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
			    				calculateManualEntityDAO_HI.fluch();
			    			}else {
			    				SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
			    				Entity_HI.setSchemeId(schemeId);
			    				double numt=0.0;
								BigDecimal num=BigDecimal.valueOf(numt);
								if(tagetValue!=null) {
			    					Entity_HI.setScore(num);
			    					double n=100.00;
		    						dimensionWeight=BigDecimal.valueOf(n);
			    				}else {
			    					Entity_HI.setScore(num);
			    				}
			    				Entity_HI.setSupplierId(datasHI.getVendorId());
			    				Entity_HI.setDimensionWeight(dimensionWeight);
			    				Entity_HI.setIndicatorId(indicatorIdt);
			    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
			    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
			    				Entity_HI.setTplCategoryId(tplCategoryId);
			    				Entity_HI.setTplId(tplId);
			    				Entity_HI.setDimensionId(dimensionId);
			    				Entity_HI.setAttribute1("M");
			    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
			    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
			    				Entity_HI.setAttribute4(evaluateDimension);
			    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
			    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
			    				calculateManualEntityDAO_HI.fluch();
			    			}
			    		}
		    		}

		    	}
		    }
		}else {////评价周期-季度、年度
			Map<String, Object> queryParamMap = new HashMap<String, Object>();
		    StringBuffer queryString = new StringBuffer(SrmSpmRequestDatasEntity_HI_RO.QUERY_COUNT_INFO_LIST);
		    queryParamMap.put("evaluateIntervalFrom", getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
		    queryParamMap.put("evaluateIntervalTo",getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
		    queryParamMap.put("tplId", schemeEntity_HI.getTplId());
		    queryParamMap.put("orgId", schemeEntity_HI.getOrgId());
		    queryParamMap.put("categoryId", cateEntity_HI.getCategoryId());
		    queryParamMap.put("schemeId", schemeId);
		    List<SrmSpmRequestDatasEntity_HI_RO> list =srmSpmRequestDatasDAO_HI_RO.findList(queryString.toString(), queryParamMap);
		    if(!list.isEmpty()) {
		    	for(SrmSpmRequestDatasEntity_HI_RO datasHI :list) {
		    		Map<String, Object> queryParamMapYT = new HashMap<String, Object>();
		    		 StringBuffer queryStringYT = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.QUERY_DIMENSION_INFO_LIST);
		    		 queryStringYT.append(" and sti.`TPL_INDICATOR_ID`="+tplIndicatorId+" ");
		    		List<SrmSpmTplIndicatorsEntity_HI_RO> indicatorId =IndicatorsEntityDAO_HI_RO.findList(queryStringYT.toString(), queryParamMapYT);
		    		if(!indicatorId.isEmpty()) {
			    		if("MANUAL-ADD".equals(indicatorId.get(0).getIndicatorType())) {//手工录入-加分项
			    			Map<String, Object> queryParamMapY = new HashMap<String, Object>();
			    			StringBuffer queryStringY = new StringBuffer(SrmSpmManualScoreEntity_HI_RO.QUERY_ADD_INFO11);
			    			queryParamMapY.put("orgId", schemeEntity_HI.getOrgId());
			    			queryParamMapY.put("supplierId", datasHI.getVendorId());
			    			queryParamMapY.put("dimensionName", dimensionName);
			    			queryParamMapY.put("categoryId", cateEntity_HI.getCategoryId());
			    			queryParamMapY.put("indicatorId", indicatorIdt);
			    			queryParamMapY.put("evaluateIntervalFrom", getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
			    			queryParamMapY.put("evaluateIntervalTo",getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
			    			List<SrmSpmManualScoreEntity_HI_RO> scoreHI =spmManualScoreEntityDAO_HI_RO.findList(queryStringY.toString(), queryParamMapY);
			    			double num=0.00;
			    			if(!scoreHI.isEmpty()) {
			    				if(scoreHI.get(0).getScore()!=null) {
			    					if(tagetValue!=null) {
			    						if(tagetValue.doubleValue()< scoreHI.get(0).getScore().doubleValue()) {
			    							num=tagetValue.doubleValue();
			    						}else {
			    							num =scoreHI.get(0).getScore().doubleValue();
			    						}
			    						double n=100.00;
			    						dimensionWeight=BigDecimal.valueOf(n);
			    					}else {
			    						if(scoreHI.get(0).getScore().doubleValue()>100.00) {
					    					num=100.00;
					    				}else {
					    					num=scoreHI.get(0).getScore().doubleValue();
					    				}
			    					}
			    					
				    				SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
				    				Entity_HI.setSchemeId(schemeId);
				    				Entity_HI.setScore(BigDecimal.valueOf(num));
				    				Entity_HI.setSupplierId(datasHI.getVendorId());
				    				Entity_HI.setDimensionWeight(dimensionWeight);
				    				Entity_HI.setIndicatorId(indicatorIdt);
				    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
				    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
				    				Entity_HI.setTplCategoryId(tplCategoryId);
				    				Entity_HI.setTplId(tplId);
				    				Entity_HI.setDimensionId(dimensionId);
				    				Entity_HI.setAttribute1("M");
				    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
				    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
				    				Entity_HI.setAttribute4(evaluateDimension);
				    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
				    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
				    				calculateManualEntityDAO_HI.fluch();
			    				}else {
			    					double numt=0.0;
									BigDecimal numY=BigDecimal.valueOf(numt);
				    				SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
				    				Entity_HI.setSchemeId(schemeId);
				    				if(tagetValue!=null) {
				    					Entity_HI.setScore(numY);
				    					double n=100.00;
			    						dimensionWeight=BigDecimal.valueOf(n);
				    				}else {
				    					Entity_HI.setScore(numY);
				    				}
				    				Entity_HI.setSupplierId(datasHI.getVendorId());
				    				Entity_HI.setDimensionWeight(dimensionWeight);
				    				Entity_HI.setIndicatorId(indicatorIdt);
				    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
				    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
				    				Entity_HI.setTplCategoryId(tplCategoryId);
				    				Entity_HI.setTplId(tplId);
				    				Entity_HI.setDimensionId(dimensionId);
				    				Entity_HI.setAttribute1("M");
				    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
				    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
				    				Entity_HI.setAttribute4(evaluateDimension);
				    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
				    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
				    				calculateManualEntityDAO_HI.fluch();
			    				}
			    				
			    			}else {
			    				double numt=0.0;
								BigDecimal numY=BigDecimal.valueOf(numt);
			    				SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
			    				Entity_HI.setSchemeId(schemeId);
			    				if(tagetValue!=null) {
			    					Entity_HI.setScore(numY);
			    					double n=100.00;
		    						dimensionWeight=BigDecimal.valueOf(n);
			    				}else {
			    					Entity_HI.setScore(numY);
			    				}
			    				Entity_HI.setSupplierId(datasHI.getVendorId());
			    				Entity_HI.setDimensionWeight(dimensionWeight);
			    				Entity_HI.setIndicatorId(indicatorIdt);
			    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
			    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
			    				Entity_HI.setTplCategoryId(tplCategoryId);
			    				Entity_HI.setTplId(tplId);
			    				Entity_HI.setDimensionId(dimensionId);
			    				Entity_HI.setAttribute1("M");
			    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
			    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
			    				Entity_HI.setAttribute4(evaluateDimension);
			    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
			    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
			    				calculateManualEntityDAO_HI.fluch();
			    			}
			    		}else if("MANUAL-DEDUCT".equals(indicatorId.get(0).getIndicatorType())) {//手工录入-扣分项
			    			Map<String, Object> queryParamMapY = new HashMap<String, Object>();
			    			StringBuffer queryStringY = new StringBuffer(SrmSpmManualScoreEntity_HI_RO.QUERY_DEDUCT_INFO11);
			    			queryParamMapY.put("orgId", schemeEntity_HI.getOrgId());
			    			queryParamMapY.put("supplierId", datasHI.getVendorId());
			    			queryParamMapY.put("dimensionName", dimensionName);
			    			queryParamMapY.put("categoryId", cateEntity_HI.getCategoryId());
			    			queryParamMapY.put("indicatorId", indicatorIdt);
			    			queryParamMapY.put("evaluateIntervalFrom",getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
			    			queryParamMapY.put("evaluateIntervalTo",getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
			    			List<SrmSpmManualScoreEntity_HI_RO> scoreHI =spmManualScoreEntityDAO_HI_RO.findList(queryStringY.toString(), queryParamMapY);
			    			double num=0.00;
			    			if(!scoreHI.isEmpty()) {
			    				Map<String, Object> queryParamMapYD = new HashMap<String, Object>();
				    			StringBuffer queryStringYD = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.QUERY_DIMENSION_INFO_LIST);
				    			queryStringYD.append("  AND sti.`TPL_DIMENSION_ID`="+dimensionId+"");
			    				List<SrmSpmTplIndicatorsEntity_HI_RO> listYD =IndicatorsEntityDAO_HI_RO.findList(queryStringYD.toString(), queryParamMapYD);
			    				if(listYD.size()==1 && scoreHI.get(0).getScore()!=null) {
			    					if(tagetValue.doubleValue() < scoreHI.get(0).getScore().doubleValue()) {
		    							num=100-tagetValue.doubleValue();
		    						}else {
		    							num=100-scoreHI.get(0).getScore().doubleValue();
		    						}
		    						double n=100.00;
		    						dimensionWeight=BigDecimal.valueOf(n);
		    						SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
				    				Entity_HI.setSchemeId(schemeId);
				    				Entity_HI.setScore(BigDecimal.valueOf(num));
				    				Entity_HI.setSupplierId(datasHI.getVendorId());
				    				Entity_HI.setDimensionWeight(dimensionWeight);
				    				Entity_HI.setIndicatorId(indicatorIdt);
				    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
				    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
				    				Entity_HI.setTplCategoryId(tplCategoryId);
				    				Entity_HI.setTplId(tplId);
				    				Entity_HI.setDimensionId(dimensionId);
				    				Entity_HI.setAttribute1("TY");
				    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
				    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
				    				Entity_HI.setAttribute4(evaluateDimension);
				    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
				    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
				    				calculateManualEntityDAO_HI.fluch();
			    				}else{
			    					if(scoreHI.get(0).getScore()!=null) {
				    					if(tagetValue!=null) {
				    						if(tagetValue.doubleValue() < scoreHI.get(0).getScore().doubleValue()) {
				    							num=-tagetValue.doubleValue();
				    						}else {
				    							num=-scoreHI.get(0).getScore().doubleValue();
				    						}
				    						double n=100.00;
				    						dimensionWeight=BigDecimal.valueOf(n);
				    					}else {
				    						if(100.00-scoreHI.get(0).getScore().doubleValue()<0.00) {
						    					num=0.00;
						    				}else {
						    					num=100.00-scoreHI.get(0).getScore().doubleValue();
						    				}
				    					}

					    				SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
					    				Entity_HI.setSchemeId(schemeId);
					    				Entity_HI.setScore(BigDecimal.valueOf(num));
					    				Entity_HI.setSupplierId(datasHI.getVendorId());
					    				Entity_HI.setDimensionWeight(dimensionWeight);
					    				Entity_HI.setIndicatorId(indicatorIdt);
					    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
					    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
					    				Entity_HI.setTplCategoryId(tplCategoryId);
					    				Entity_HI.setTplId(tplId);
					    				Entity_HI.setDimensionId(dimensionId);
					    				Entity_HI.setAttribute1("M");
					    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
					    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
					    				Entity_HI.setAttribute4(evaluateDimension);
					    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
					    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
					    				calculateManualEntityDAO_HI.fluch();
				    				}else {
				    					double numt=100.0;
				    					double numtY=0.0;
										BigDecimal numY=BigDecimal.valueOf(numt);
										BigDecimal numYt=BigDecimal.valueOf(numtY);
					    				SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
					    				Entity_HI.setSchemeId(schemeId);
					    				if(tagetValue!=null) {
					    					Entity_HI.setScore(numYt);
					    					double n=100.00;
				    						dimensionWeight=BigDecimal.valueOf(n);
					    				}else {
					    					Entity_HI.setScore(numY);
					    				}
					    				Entity_HI.setSupplierId(datasHI.getVendorId());
					    				Entity_HI.setDimensionWeight(dimensionWeight);
					    				Entity_HI.setIndicatorId(indicatorIdt);
					    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
					    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
					    				Entity_HI.setTplCategoryId(tplCategoryId);
					    				Entity_HI.setTplId(tplId);
					    				Entity_HI.setDimensionId(dimensionId);
					    				Entity_HI.setAttribute1("M");
					    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
					    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
					    				Entity_HI.setAttribute4(evaluateDimension);
					    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
					    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
					    				calculateManualEntityDAO_HI.fluch();
				    				}
			    				}
			    			}else {
			    				double numt=100.0;
			    				double numtY=0.0;
								BigDecimal numY=BigDecimal.valueOf(numt);
								BigDecimal numYt=BigDecimal.valueOf(numtY);
			    				SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
			    				Entity_HI.setSchemeId(schemeId);
			    				if(tagetValue!=null) {
			    					Entity_HI.setScore(numYt);
			    					double n=100.00;
		    						dimensionWeight=BigDecimal.valueOf(n);
			    				}else {
			    					Entity_HI.setScore(numY);
			    				}
			    				Entity_HI.setSupplierId(datasHI.getVendorId());
			    				Entity_HI.setDimensionWeight(dimensionWeight);
			    				Entity_HI.setIndicatorId(indicatorIdt);
			    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
			    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
			    				Entity_HI.setTplCategoryId(tplCategoryId);
			    				Entity_HI.setTplId(tplId);
			    				Entity_HI.setDimensionId(dimensionId);
			    				Entity_HI.setAttribute1("M");
			    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
			    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
			    				Entity_HI.setAttribute4(evaluateDimension);
			    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
			    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
			    				calculateManualEntityDAO_HI.fluch();
			    			}
			    		}else if("MANUAL-SCORE".equals(indicatorId.get(0).getIndicatorType())) {//手工录入-得分项
			    			Map<String, Object> queryParamMapY = new HashMap<String, Object>();
			    			StringBuffer queryStringY = new StringBuffer(SrmSpmManualScoreEntity_HI_RO.QUERY_AVG_INFO11);
			    			queryParamMapY.put("orgId", schemeEntity_HI.getOrgId());
			    			queryParamMapY.put("supplierId", datasHI.getVendorId());
			    			queryParamMapY.put("dimensionName", dimensionName);
			    			queryParamMapY.put("categoryId", cateEntity_HI.getCategoryId());
			    			queryParamMapY.put("indicatorId", indicatorIdt);
			    			queryParamMapY.put("evaluateIntervalFrom", getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
			    			queryParamMapY.put("evaluateIntervalTo",getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
			    			List<SrmSpmManualScoreEntity_HI_RO> scoreHI =spmManualScoreEntityDAO_HI_RO.findList(queryStringY.toString(), queryParamMapY);
			    			if(!scoreHI.isEmpty()) {
			    				double num=0.00;
			    				if(scoreHI.get(0).getScore()!=null) {
			    					if(tagetValue!=null) {
				    					if(tagetValue.doubleValue()<scoreHI.get(0).getScore().doubleValue()) {
				    						num=tagetValue.doubleValue();
				    					}else {
				    						num=scoreHI.get(0).getScore().doubleValue();
				    					}
				    					double n=100.00;
			    						dimensionWeight=BigDecimal.valueOf(n);
				    				}else {
				    					num=scoreHI.get(0).getScore().doubleValue();
				    				}
			    				}else {
			    					num=0.00;
			    				}
			    				
			    				SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
			    				Entity_HI.setSchemeId(schemeId);
			    				Entity_HI.setScore(BigDecimal.valueOf(num));
			    				Entity_HI.setSupplierId(datasHI.getVendorId());
			    				Entity_HI.setDimensionWeight(dimensionWeight);
			    				Entity_HI.setIndicatorId(indicatorIdt);
			    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
			    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
			    				Entity_HI.setTplCategoryId(tplCategoryId);
			    				Entity_HI.setTplId(tplId);
			    				Entity_HI.setDimensionId(dimensionId);
			    				Entity_HI.setAttribute1("M");
			    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
			    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
			    				Entity_HI.setAttribute4(evaluateDimension);
			    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
			    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
			    				calculateManualEntityDAO_HI.fluch();
			    			}else {
			    				SrmTplCalculateManualEntity_HI Entity_HI =new SrmTplCalculateManualEntity_HI();
			    				Entity_HI.setSchemeId(schemeId);
			    				double numt=0.0;
								BigDecimal num=BigDecimal.valueOf(numt);
								if(tagetValue!=null) {
			    					Entity_HI.setScore(num);
			    					double n=100.00;
		    						dimensionWeight=BigDecimal.valueOf(n);
			    				}else {
			    					Entity_HI.setScore(num);
			    				}
			    				Entity_HI.setSupplierId(datasHI.getVendorId());
			    				Entity_HI.setDimensionWeight(dimensionWeight);
			    				Entity_HI.setIndicatorId(indicatorIdt);
			    				Entity_HI.setOrgId(schemeEntity_HI.getOrgId());
			    				Entity_HI.setCategoryId(cateEntity_HI.getCategoryId());
			    				Entity_HI.setTplCategoryId(tplCategoryId);
			    				Entity_HI.setTplId(tplId);
			    				Entity_HI.setDimensionId(dimensionId);
			    				Entity_HI.setAttribute1("M");
			    				Entity_HI.setAttribute2(getMonth(schemeEntity_HI.getEvaluateIntervalFrom(),"First"));
			    				Entity_HI.setAttribute3(getMonth(schemeEntity_HI.getEvaluateIntervalTo(),"Last"));
			    				Entity_HI.setAttribute4(evaluateDimension);
			    				Entity_HI.setOperatorUserId(schemeEntity_HI.getCreatedBy());
			    				calculateManualEntityDAO_HI.saveOrUpdate(Entity_HI);
			    				calculateManualEntityDAO_HI.fluch();
			    			}
			    		}
		    		}

		    	}
		    }
		}
		
	}
	
	
	private void updateCalculatAggregate(SrmSpmPerformanceSchemeEntity_HI schemeEntity_HI) {
		Map<String, Object> queryParamMapt = new HashMap<String, Object>();
		StringBuffer queryStringt = new StringBuffer(SrmSpmTplCategoriesEntity_HI_RO.QUERY_CATEGORIES_INFO_LIST);
		queryStringt.append(" and sc.TPL_ID="+schemeEntity_HI.getTplId()+" ");
		List<SrmSpmTplCategoriesEntity_HI_RO> cateEntityList =tplCategoriesEntityDAO_HI_RO.findList(queryStringt.toString(), queryParamMapt);
		if(!cateEntityList.isEmpty()) {
			for(SrmSpmTplCategoriesEntity_HI_RO rowId :cateEntityList) {
				Map<String, Object> queryParamMap = new HashMap<String, Object>();
				StringBuffer queryString = new StringBuffer(SrmSpmTplDimensionEntity_HI_RO.QUERY_DIMENSION_INFO_LIST);
				queryString.append(" and sd.TPL_ID="+schemeEntity_HI.getTplId()+" ");
				List<SrmSpmTplDimensionEntity_HI_RO> dimensionList =tplDimensionEntityDAO_HI_RO.findList(queryString.toString(), queryParamMap);
				if(!dimensionList.isEmpty()) {
					for(SrmSpmTplDimensionEntity_HI_RO dimensId :dimensionList) {
						Map<String, Object> queryParamMapY = new HashMap<String, Object>();
						StringBuffer queryStringY = new StringBuffer(SrmTplCalculateManualEntity_HI_RO.QUERY_DIMENSION_INFO_LIST);
						queryParamMapY.put("schemeId", schemeEntity_HI.getSchemeId());
						queryParamMapY.put("categoryId", rowId.getCategoryId());
						queryParamMapY.put("orgId", schemeEntity_HI.getOrgId());
						queryParamMapY.put("dimensionId", dimensId.getTplDimensionId());
						List<SrmTplCalculateManualEntity_HI_RO> Calculist =calculateManualEntityDAO_HI_RO.findList(queryStringY.toString(), queryParamMapY);
						if(!Calculist.isEmpty()) {
							int i=0;
							for(SrmTplCalculateManualEntity_HI_RO rowIdt : Calculist) {
								i=i+1;
								SrmSpmSupplierScoreEntity_HI Entity_HI =new SrmSpmSupplierScoreEntity_HI();
								Entity_HI.setScoreType("DIMENSION");
								Entity_HI.setSchemeId(schemeEntity_HI.getSchemeId());
								Entity_HI.setVendorId(rowIdt.getSupplierId());
								Entity_HI.setTplCategoryId(rowId.getTplCategoryId());
								Entity_HI.setCategoryId(rowId.getCategoryId());
								Entity_HI.setTplDimensionId( dimensId.getTplDimensionId());
								Entity_HI.setEvaluateDimension(dimensId.getEvaluateDimension());
								if(rowIdt.getNumt().doubleValue()<0) {
									double numy=0.00;
									Entity_HI.setScore(BigDecimal.valueOf(numy));
								}else {
									Entity_HI.setScore(rowIdt.getNumt());
								}
								
								Entity_HI.setRank(i);
								Entity_HI.setOperatorUserId(-2);
								spmSupplierScoreEntityDAO_HI.saveOrUpdate(Entity_HI);
								spmSupplierScoreEntityDAO_HI.fluch();
								Map<String, Object> queryParamMapT = new HashMap<String, Object>();
								StringBuffer queryStringT = new StringBuffer(SrmTplCalculateManualEntity_HI_RO.QUERY_INDICATOR_INFO);
								queryParamMapT.put("schemeId", schemeEntity_HI.getSchemeId());
								queryParamMapT.put("categoryId", rowId.getCategoryId());
								queryParamMapT.put("orgId", schemeEntity_HI.getOrgId());
								queryParamMapT.put("dimensionId", dimensId.getTplDimensionId());
								queryParamMapT.put("supplierId", rowIdt.getSupplierId());
								List<SrmTplCalculateManualEntity_HI_RO> CalculistT =calculateManualEntityDAO_HI_RO.findList(queryStringT.toString(), queryParamMapT);
								if(!CalculistT.isEmpty()) {
									for(SrmTplCalculateManualEntity_HI_RO rom : CalculistT) {
										SrmSpmIndicatorScoreEntity_HI scoreEntity_HI =new SrmSpmIndicatorScoreEntity_HI();
										scoreEntity_HI.setSupplierScoreId(Entity_HI.getSupplierScoreId());
										scoreEntity_HI.setIndicatorId(rom.getIndicatorId());
										scoreEntity_HI.setScore(rom.getNumt());
										scoreEntity_HI.setOperatorUserId(-2);
										spmIndicatorScoreEntityDAO_HI.saveOrUpdate(scoreEntity_HI);
										spmIndicatorScoreEntityDAO_HI.fluch();
									}
								}
							}
						}
						
						
					}
				}
				
				Map<String, Object> queryParamMapTY = new HashMap<String, Object>();
				StringBuffer queryStringTY = new StringBuffer(SrmTplCalculateManualEntity_HI_RO.QUERY_CATE_INFO);
				queryParamMapTY.put("schemeId", schemeEntity_HI.getSchemeId());
				queryParamMapTY.put("categoryId", rowId.getCategoryId());
				queryParamMapTY.put("orgId", schemeEntity_HI.getOrgId());
				List<SrmTplCalculateManualEntity_HI_RO> CalculistTY =calculateManualEntityDAO_HI_RO.findList(queryStringTY.toString(), queryParamMapTY);
				if(!CalculistTY.isEmpty()) {
					int t=0;
					for(SrmTplCalculateManualEntity_HI_RO HiRo :CalculistTY) {
						t=t+1;
						SrmSpmSupplierScoreEntity_HI Entity_HI =new SrmSpmSupplierScoreEntity_HI();
						Entity_HI.setScoreType("CATEGORY");
						Entity_HI.setSchemeId(schemeEntity_HI.getSchemeId());
						Entity_HI.setCategoryId(rowId.getCategoryId());
						Entity_HI.setVendorId(HiRo.getSupplierId());
						Entity_HI.setTplCategoryId(rowId.getTplCategoryId());
						Entity_HI.setScore(HiRo.getNumt());
						Entity_HI.setRank(t);
						Entity_HI.setOperatorUserId(-2);
						spmSupplierScoreEntityDAO_HI.saveOrUpdate(Entity_HI);
						spmSupplierScoreEntityDAO_HI.fluch();
					}
				}
			}
		}
		
	}


}
