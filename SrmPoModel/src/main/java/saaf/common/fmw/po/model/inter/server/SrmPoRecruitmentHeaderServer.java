package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.apache.http.client.utils.DateUtils;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.utils.StringUtils;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.message.email.utils.SendMailUtil;
import saaf.common.fmw.po.model.entities.SrmPoRecruitmentLineEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitmentHeaderEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitmentLineEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoRecruitArchivesH;
import saaf.common.fmw.po.model.inter.ISrmPoRecruitmentHeader;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.SrmPoRecruitmentHeaderEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;
import saaf.common.fmw.po.model.inter.ISrmPoRecruitmentLine;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;

@Component("srmPoRecruitmentHeaderServer")
public class SrmPoRecruitmentHeaderServer implements ISrmPoRecruitmentHeader{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRecruitmentHeaderServer.class);
	@Autowired
	private ViewObject<SrmPoRecruitmentHeaderEntity_HI> srmPoRecruitmentHeaderDAO_HI;
	@Autowired
	private BaseViewObject<SrmPoRecruitmentHeaderEntity_HI_RO> srmPoRecruitmentHeaderDAO_HI_RO;


	@Autowired
	private ViewObject<SrmPoRecruitmentLineEntity_HI> srmPoRecruitmentLineDAO_HI;
	@Autowired
	private BaseViewObject<SrmPoRecruitmentLineEntity_HI_RO> srmPoRecruitmentLineDAO_HI_RO;

	@Autowired
	private ISrmPoRecruitmentLine iSrmPoRecruitmentLine;

	@Autowired
	private ISrmPoRecruitArchivesH iSrmPoRecruitArchivesH;

	@Autowired
	private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;

    @Autowired
    private ViewObject<SaafEmployeesEntity_HI> saafEmployeesDAO_HI;

	@Autowired
	private SaafSequencesUtil saafSequencesUtil;

    private static SendMailUtil sendMailUtil = new SendMailUtil(true);

	public SrmPoRecruitmentHeaderServer() {
		super();
	}

	/**
	 * Description：供应商查询采购订单头
	 *
	 * @param jsonParams 查询条件参数
	 * @return =======================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@Override
	public List<SrmPoRecruitmentHeaderEntity_HI_RO> findRecruitmentHeaderInfo(JSONObject jsonParams) throws Exception {
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		StringBuffer queryParamSql = new StringBuffer(SrmPoRecruitmentHeaderEntity_HI_RO.QUERY_RECRUITMENT_HEADER);
		queryParamMap.put("recruitmentHeaderId", jsonParams.getString("recruitmentHeaderId"));
		return srmPoRecruitmentHeaderDAO_HI_RO.findList(queryParamSql, queryParamMap);
	}
	/**
	 * Description：招聘查询页
	 * @param jsonParams 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@Override
	public Pagination<SrmPoRecruitmentHeaderEntity_HI_RO> findRecruitmentInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
		Integer userId=jsonParams.getInteger("varUserId");
		Integer employeeId=jsonParams.getInteger("varEmployeeId");
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		StringBuffer queryParamSql = new StringBuffer(SrmPoRecruitmentHeaderEntity_HI_RO.QUERY_RECRUITMENT_INFO);
		SaafToolUtils.parperParam(jsonParams, "sprh.recruitment_code", "recruitmentCode", queryParamSql, queryParamMap, "LIKE");
		SaafToolUtils.parperParam(jsonParams, "sprh.org_id", "orgId", queryParamSql, queryParamMap, "=");
		SaafToolUtils.parperParam(jsonParams, "sprh.status", "status", queryParamSql, queryParamMap, "=");
		SaafToolUtils.parperParam(jsonParams, "sprh.supplier_id", "supplierId", queryParamSql, queryParamMap, "=");
		SaafToolUtils.parperParam(jsonParams, "sprh.employee_id", "employeeId", queryParamSql, queryParamMap, "=");
		SaafToolUtils.parperParam(jsonParams, "sprh.pos_id", "posId", queryParamSql, queryParamMap, "=");
		SaafToolUtils.parperParam(jsonParams, "sprl.category_id", "categoryId", queryParamSql, queryParamMap, "=");
		SaafToolUtils.parperParam(jsonParams, "to_char(sprh.creation_date,'yyyy-MM-dd')", "creationDateFrom", queryParamSql, queryParamMap, ">=");
		SaafToolUtils.parperParam(jsonParams, "to_char(sprh.creation_date,'yyyy-MM-dd')", "creationDateTo", queryParamSql, queryParamMap, "<=");
		SaafToolUtils.parperParam(jsonParams, "to_char(sprl.demand_date,'yyyy-MM-dd')", "demandDateFrom", queryParamSql, queryParamMap, ">=");
		SaafToolUtils.parperParam(jsonParams, "to_char(sprl.demand_date,'yyyy-MM-dd')", "demandDateTo", queryParamSql, queryParamMap, "<=");
		//权限控制
		//供应商只能看自己的单据
		Integer supplierId = jsonParams.getInteger("varSupplierId");
		if ("EX".equals(jsonParams.getString("varPlatformCode"))) {
			queryParamSql.append(" AND sprh.status in ('APPROVED','CANCELED') and sprh.supplier_id=" + supplierId);
		}

		if ("SAAF".equals(jsonParams.getString("varPlatformCode"))) {
			queryParamSql.append(" AND (sprh.created_by ="+userId+" or sprh.employee_id ="+employeeId+")");
		}
		String countSql = "select count(1) from (" + queryParamSql + ")";
		queryParamSql.append(" order by sprh.recruitment_header_id desc, sprl.last_update_date desc,sprh.last_update_date desc");
		return srmPoRecruitmentHeaderDAO_HI_RO.findPagination(queryParamSql.toString(), countSql, queryParamMap, pageIndex, pageRows);
	}

	/**
	 * Description：查找招聘版本
	 *
	 * @param jsonParams 查询条件参数
	 * @return =======================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@Override
	public List<SrmPoRecruitmentHeaderEntity_HI_RO> findEditionNum(JSONObject jsonParams) throws Exception {
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		StringBuffer queryParamSql = new StringBuffer(SrmPoRecruitmentHeaderEntity_HI_RO.QUERY_EDITION_NUM_SQL);
		queryParamMap.put("recruitmentHeaderId", jsonParams.getInteger("recruitmentHeaderId"));
		return srmPoRecruitmentHeaderDAO_HI_RO.findList(queryParamSql, queryParamMap);
	}



	/**
	 * Description：招聘 取消,变更
	 * @param jsonParams 条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@Override
	public JSONObject updateRecruitmentHeaderStatus(JSONObject jsonParams) throws Exception {
		String operatorType = jsonParams.getString("operatorType");
		Integer recruitmentHeaderId = jsonParams.getInteger("recruitmentHeaderId");
		Integer operatorUserId = jsonParams.getInteger("operatorUserId");
		String headerStatus = null;
		String tipMsg = null;
		SrmPoRecruitmentHeaderEntity_HI header = srmPoRecruitmentHeaderDAO_HI.getById(recruitmentHeaderId);

		if (header == null) {
			return SToolUtils.convertResultJSONObj("E", tipMsg + "失败,找不到招聘需求数据!", 0, null);
		}

		if ("REJECT".equals(operatorType)) {// 拒绝
			headerStatus = "REJECTED";
			tipMsg = "拒绝";

			List<SrmPoRecruitmentLineEntity_HI> lineList = srmPoRecruitmentLineDAO_HI.findByProperty("recruitmentHeaderId", recruitmentHeaderId);
			List<SrmPoRecruitmentLineEntity_HI> newLineList = new ArrayList<>();
			if (lineList != null && lineList.size() != 0) {
				for (SrmPoRecruitmentLineEntity_HI line : lineList) {
					if ("OPEN".equals(line.getLineStatus())) {
						//将行状态改成拟定
						line.setLineStatus("DRAFT");
						line.setOperatorUserId(operatorUserId);
						srmPoRecruitmentLineDAO_HI.update(line);
					}
				}
			}


		} else if ("APPROVE".equals(operatorType)) {// 审批
			headerStatus = "APPROVED";
			tipMsg = "审批";

			SrmPosSupplierInfoEntity_HI supplier = srmPosSupplierInfoDAO_HI.getById(header.getSupplierId());
			//判断供应商是否存在或黑名单
			if (null == supplier || (null != supplier.getBlacklistFlag() && supplier.getBlacklistFlag().equals("Y"))) {
				return SToolUtils.convertResultJSONObj("E", tipMsg + "失败,供应商不存在或是黑名单!", 0, null);
			}

			header.setApprovedDate(new Date());
			header.setStatus(headerStatus);
		} else if ("CANCEL".equals(operatorType)) {// 取消
			headerStatus = "CANCELED";
			tipMsg = "取消";
			List<SrmPoRecruitmentLineEntity_HI> lineList = srmPoRecruitmentLineDAO_HI.findByProperty("recruitmentHeaderId", recruitmentHeaderId);
			List<SrmPoRecruitmentLineEntity_HI> newLineList = new ArrayList<>();
			if (lineList != null && lineList.size() != 0) {
				for (SrmPoRecruitmentLineEntity_HI line : lineList) {
					if ("RECEIVED".equals(line.getLineStatus())) {
						return SToolUtils.convertResultJSONObj("E", tipMsg + "失败，招聘需求："+line.getRecruitmentDescription()+"行已接收，不可取消整个招聘需求单据！", 0, null);
					} else {
						line.setLineStatus("CANCELED");
						line.setOperatorUserId(operatorUserId);
						newLineList.add(line);
					}
				}
				srmPoRecruitmentLineDAO_HI.updateAll(newLineList);
				header.setStatus(headerStatus);
			}
		} else if ("CHANGE".equals(operatorType)) {// 变更
			headerStatus = "UPDATED";
			tipMsg = "变更";
			//查询是否有状态为 打开 的行
			JSONObject params = new JSONObject();
			params.put("recruitmentHeaderId", recruitmentHeaderId);
			params.put("lineStatus", "OPEN");
			List<SrmPoRecruitmentLineEntity_HI_RO> list = iSrmPoRecruitmentLine.findRecruitmentLineInfo(params);
			if (null == list || list.size() == 0) {
				return SToolUtils.convertResultJSONObj("E",
						tipMsg + "失败!不存在状态为打开的招聘行。", 0, null);
			}

			//保存历史
			iSrmPoRecruitArchivesH.saveEditionNum(jsonParams);

			//新增版本
			header.setEditionNum(header.getEditionNum()+1);

			List<SrmPoRecruitmentLineEntity_HI> lineList = srmPoRecruitmentLineDAO_HI.findByProperty("recruitmentHeaderId", recruitmentHeaderId);
			if (lineList != null && lineList.size() != 0) {
				for (SrmPoRecruitmentLineEntity_HI line : lineList) {
					if ("OPEN".equals(line.getLineStatus())) {
						//将行状态改成拟定
						line.setLineStatus("DRAFT");

						line.setOriginalDemandNum(line.getDemandNum());
						line.setOriginalDemandDate(line.getDemandDate());
						line.setOperatorUserId(operatorUserId);
						srmPoRecruitmentLineDAO_HI.update(line);
					}
				}
			}
		}
		header.setStatus(headerStatus);
		header.setOperatorUserId(operatorUserId);
		srmPoRecruitmentHeaderDAO_HI.update(header);
		return SToolUtils.convertResultJSONObj("S", tipMsg + "成功!", 1, null);

	}
	/**
	 * Description：招聘 保存 发布
	 * @param jsonParams 条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@Override
	public JSONObject saveOrSubmitRecruitmentInfo(JSONObject jsonParams) throws Exception {
		Integer recruitmentHeaderId = jsonParams.getInteger("recruitmentHeaderId");
		Integer operatorUserId = jsonParams.getInteger("operatorUserId");
		String operatorType = jsonParams.getString("operatorType");
		BigDecimal zero = new BigDecimal("0");
		SrmPoRecruitmentHeaderEntity_HI header = new SrmPoRecruitmentHeaderEntity_HI();
		String tipMsg = null;
		String headerStatus = null;
		Date date = new Date();
		String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
		Integer supplierId = jsonParams.getInteger("supplierId");
		if (null == supplierId) {
			return SToolUtils.convertResultJSONObj(ERROR_STATUS, "供应商必填", 0, null);
		}
		if (null == jsonParams.getInteger("orgId")) {
			return SToolUtils.convertResultJSONObj(ERROR_STATUS, "业务实体必填", 0, null);
		}
		if (null == jsonParams.getInteger("posId")) {
			return SToolUtils.convertResultJSONObj(ERROR_STATUS, "需求部门必填", 0, null);
		}
		if (null == jsonParams.getInteger("employeeId")) {
			return SToolUtils.convertResultJSONObj(ERROR_STATUS, "招聘员必填", 0, null);
		}
		SrmPosSupplierInfoEntity_HI supplier = srmPosSupplierInfoDAO_HI.getById(supplierId);
		/**
		 * 不同操作，状态不同
		 */
		if ("SAVE".equals(operatorType)) {
			headerStatus = jsonParams.getString("status");
			tipMsg = "保存";
		} else if ("SUBMIT".equals(operatorType)) {
			headerStatus = "SUBMITTED";
			tipMsg = "提交";
		}
		//判断供应商是否存在或黑名单
		if (null == supplier || (null != supplier.getBlacklistFlag() && supplier.getBlacklistFlag().equals("Y"))) {
			return SToolUtils.convertResultJSONObj("E", tipMsg + "失败,供应商不存在或是黑名单!", 0, null);
		}

		// 如果为空是新增，不为空是更新
		if (null == recruitmentHeaderId) {
			header = new SrmPoRecruitmentHeaderEntity_HI();
			// 自动生成招聘单号
			String recruitmentCode = saafSequencesUtil.getDocSequences("srm_po_recruitment_header", "ZP-", dateFromate, 3);

			header.setRecruitmentCode(recruitmentCode);
			header.setEditionNum(0);
		} else {
			header = srmPoRecruitmentHeaderDAO_HI.getById(recruitmentHeaderId);
		}
		header.setOrgId(jsonParams.getInteger("orgId"));
		header.setStatus(headerStatus);
		header.setSupplierId(jsonParams.getInteger("supplierId"));
		header.setRecruitmentName(jsonParams.getString("recruitmentName"));
		header.setEmployeeId(jsonParams.getInteger("employeeId"));
		header.setPosId(jsonParams.getInteger("posId"));
		header.setEkpContractNum(jsonParams.getString("ekpContractNum"));
		header.setContractName(jsonParams.getString("contractName"));
		header.setContractNum(jsonParams.getString("contractNum"));
		header.setRmk(jsonParams.getString("rmk"));
		header.setOperatorUserId(operatorUserId);
		srmPoRecruitmentHeaderDAO_HI.saveOrUpdate(header);
		srmPoRecruitmentHeaderDAO_HI.fluch();

		JSONArray lineArray = jsonParams.getJSONArray("lineData");
		if (null != lineArray && lineArray.size() != 0) {
			List<SrmPoRecruitmentLineEntity_HI> lineList = new ArrayList<SrmPoRecruitmentLineEntity_HI>();
			for (int i = 0; i < lineArray.size(); i++) {
				JSONObject jsonObj = lineArray.getJSONObject(i);
				SrmPoRecruitmentLineEntity_HI line = new SrmPoRecruitmentLineEntity_HI();
				Integer recruitmentLineId = jsonObj.getInteger("recruitmentLineId");
				if (null == recruitmentLineId) {
					line = new SrmPoRecruitmentLineEntity_HI();
					line.setRecruitmentHeaderId(header.getRecruitmentHeaderId());
					if ("SUBMITTED".equals(headerStatus)) {
						line.setLineStatus("OPEN");
					} else {
						line.setLineStatus("DRAFT");
					}
				} else {
					line = srmPoRecruitmentLineDAO_HI.getById(recruitmentLineId);
					if ("SUBMITTED".equals(headerStatus) && "DRAFT".equals(line.getLineStatus())) {
						line.setLineStatus("OPEN");
					} else {
						line.setLineStatus(jsonObj.getString("lineStatus"));
					}
				}
				line.setRecruitmentDescription(jsonObj.getString("recruitmentDescription"));
				line.setDemandNum(jsonObj.getInteger("demandNum"));
				line.setActualInterviewsNum(jsonObj.getInteger("actualInterviewsNum"));
				line.setRegistrationNum(jsonObj.getInteger("registrationNum"));
				line.setCategoryId(jsonObj.getInteger("categoryId"));
				line.setRmk(jsonObj.getString("lineRmk"));
				//判断是否接收
				if ("RECEIVE".equals(operatorType) && ((!"0".equals(line.getActualInterviewsNum())&&!ObjectUtils.isEmpty(line.getActualInterviewsNum())) || (!"0".equals(line.getRegistrationNum())&&!ObjectUtils.isEmpty(line.getRegistrationNum())))) {
					line.setLineStatus("RECEIVED");
				}

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if (!ObjectUtils.isEmpty(jsonObj.getString("demandDate"))) {
					Date demandDate = null;
					demandDate = sdf.parse(jsonObj.getString("demandDate"));
					line.setDemandDate(demandDate);
				}
				if(!ObjectUtils.isEmpty(jsonObj.getString("startDate"))){
					line.setStartDate(sdf.parse(jsonObj.getString("startDate")));
				}
				if(!ObjectUtils.isEmpty(jsonObj.getString("endDate"))){
					line.setEndDate(sdf.parse(jsonObj.getString("endDate")));
				}

				line.setOperatorUserId(operatorUserId);
				lineList.add(line);
			}
			srmPoRecruitmentLineDAO_HI.saveOrUpdateAll(lineList);
			srmPoRecruitmentLineDAO_HI.fluch();

		}

		if ("RECEIVE".equals(operatorType)) {
			updateRecruitmentStatus(header, operatorUserId);
		}

        //提交，发送邮件
        if("SUBMITTED".equals(header.getStatus())){
            //查询创建人上级岗位
            List<SaafEmployeesEntity_HI> se=saafEmployeesDAO_HI.findByProperty("userId",header.getCreatedBy());
            if(!ObjectUtils.isEmpty(se)){
                if(StringUtils.isEmpty(se.get(0).getParentsQuartersCode())){
                    throw new UtilsException("未存在上级岗位审批人！");
                }else{
                    List<SaafEmployeesEntity_HI> userList=saafEmployeesDAO_HI.findByProperty("quartersCode",se.get(0).getParentsQuartersCode());
                    if(!ObjectUtils.isEmpty(userList)){
                        String content = "<p>您好！</p><br/>" + "您有新的招聘需求单据: " + header.getRecruitmentCode() + "需要审批！";
                        for (int c = 0; c < userList.size(); c++) {
                            String emailAddress = userList.get(c).getEmail();
                            //发送邮件
                            sendMailUtil.doSendHtmlEmail("招聘需求审批通知", content, new String[]{emailAddress});
                        }
                    }
                }

            }
        }

		return SToolUtils.convertResultJSONObj("S", tipMsg + "成功!", 1, header);
	}


	/**
	 * Description：检查招聘头状态
	 *
	 * @param header 招聘头数据
	 * @param userId
	 * @return =======================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	public void updateRecruitmentStatus(SrmPoRecruitmentHeaderEntity_HI header, Integer userId) {
		int rowCount = 0;
		int cancelledCount = 0;
		int receivedCount = 0;
		int settledCount = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("recruitmentHeaderId", header.getRecruitmentHeaderId());
		//所有行
		List<SrmPoRecruitmentLineEntity_HI> lines = srmPoRecruitmentLineDAO_HI.findByProperty(map);
		if (null != lines) {
			rowCount = lines.size();
		}
		//检查是否所有行都已取消
		map.put("lineStatus", "CANCELED");
		List<SrmPoRecruitmentLineEntity_HI> cancelledLines = srmPoRecruitmentLineDAO_HI.findByProperty(map);
		if (null != cancelledLines) {
			cancelledCount = cancelledLines.size();
		}
		if (rowCount == cancelledCount) {
			header.setStatus("CANCELED");
			header.setOperatorUserId(userId);
			srmPoRecruitmentHeaderDAO_HI.update(header);
			srmPoRecruitmentHeaderDAO_HI.fluch();
			return;
		}
		//检查是否已接收完毕
		map.put("lineStatus", "RECEIVED");
		List<SrmPoRecruitmentLineEntity_HI> receivedLines = srmPoRecruitmentLineDAO_HI.findByProperty(map);
		if (null != receivedLines) {
			receivedCount = receivedLines.size();
		}
		if (receivedCount > 0 && rowCount == cancelledCount + receivedCount) {
			header.setStatus("RECEIVED");
			header.setOperatorUserId(userId);
			srmPoRecruitmentHeaderDAO_HI.update(header);
			srmPoRecruitmentHeaderDAO_HI.fluch();
			return;
		}
	}


	/**
	 * Description：删除招聘单
	 *
	 * @param jsonParams 查询条件参数
	 * @return =======================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	public JSONObject deleteRecruitment(JSONObject jsonParams) throws Exception {
		if (null != jsonParams.getInteger("recruitmentHeaderId")) {
			SrmPoRecruitmentHeaderEntity_HI recruitmentHeader = srmPoRecruitmentHeaderDAO_HI.getById(jsonParams.getInteger("recruitmentHeaderId"));
			if ("DRAFT".equals(recruitmentHeader.getStatus()) || "REJECTED".equals(recruitmentHeader.getStatus())) {
				if (recruitmentHeader != null) {
					List<SrmPoRecruitmentLineEntity_HI> list = srmPoRecruitmentLineDAO_HI.findByProperty("recruitmentHeaderId",
							jsonParams.getInteger("recruitmentHeaderId"));
					if (list != null && list.size() > 0) {
						srmPoRecruitmentLineDAO_HI.delete(list);
					}
					srmPoRecruitmentHeaderDAO_HI.delete(recruitmentHeader);
				}
			} else {
				return SToolUtils.convertResultJSONObj("E", "删除招聘需求单据失败,只能删除拟定和驳回的单据!", 0, null);
			}
		} else {
			return SToolUtils.convertResultJSONObj("E", "删除招聘需求单据失败，" + jsonParams.getInteger("recruitmentHeaderId") + "不存在", 0, null);
		}
		return SToolUtils.convertResultJSONObj("S", "删除招聘需求单据成功", 1, null);
	}


}
