package saaf.common.fmw.base.model.inter.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.model.entities.SaafMessageHeadEntity_HI;
import saaf.common.fmw.base.model.entities.SaafMessageLineEntity_HI;
import saaf.common.fmw.base.model.entities.SaafMessagePushEntity_HI;
import saaf.common.fmw.base.model.entities.SaafUserGroupEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafMessageHeadEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SaafMessageLineEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafMessage;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.model.inter.server.ShortDescMessageServer;
import saaf.common.fmw.common.utils.SaafToolUtils;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：saafMessageServer.java
 * Description：消息
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
@Component("saafMessageServer")
public class SaafMessageServer implements ISaafMessage {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SaafMessageServer.class);
	// @Autowired
	// private ISaafMessagePush saafMessagePushServer; //=
	// (ISaafMessagePush)SaafToolUtils.context.getBean("saafMessagePushServer");
	@Autowired
	private ShortDescMessageServer shortDescMessageServer;

	public SaafMessageServer() {
		super();
	}

	@Autowired
	private SaafSequencesUtil saafSequencesUtil;

	@Autowired
	private BaseViewObject saafMessageHeadDAO_HI_RO;
	@Autowired
	private BaseViewObject saafMessageLineDAO_HI_RO;
	@Autowired
	private ViewObject<SaafMessageHeadEntity_HI> saafMessageHeadDAO_HI;
	@Autowired
	private ViewObject<SaafMessageLineEntity_HI> saafMessageLineDAO_HI;
	@Autowired
	private ViewObject<SaafMessagePushEntity_HI> saafMessagePushDAO_HI;
	@Autowired
	private ViewObject<SaafUserGroupEntity_HI> saafUserGroupDAO_HI;

	/**
	 * 查询消息头
	 * 
	 * @param queryParamJSON
	 * @param pagIndex
	 * @param pagRow
	 * @return
	 * @throws Exception
	 * Update History
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     HLH            创建
	 * ===========================================================================
	 */
	public Pagination findSaafMessageHead(JSONObject queryParamJSON,
			Integer pagIndex, Integer pagRow) throws Exception {
		StringBuffer query = new StringBuffer();
		query.append(SaafMessageHeadEntity_HI_RO.QUERY_SQL);
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		SaafToolUtils.parperParam(queryParamJSON, "smh.message_id",
				"messageId", query, queryParamMap, "=");
		SaafToolUtils.parperParam(queryParamJSON, "smh.message_code",
				"messageCode", query, queryParamMap, "=");
		SaafToolUtils.parperParam(queryParamJSON, "smh.status",
				"messageStatus", query, queryParamMap, "like");
		SaafToolUtils.parperParam(queryParamJSON, "su.user_full_name",
				"userFullName", query, queryParamMap, "like");
		SaafToolUtils.parperParam(queryParamJSON, "smh.message_desc",
				"messageDesc", query, queryParamMap, "like");
		SaafToolUtils.parperParam(queryParamJSON, "smh.notice_type",
				"noticeType", query, queryParamMap, "=");
		SaafToolUtils.parperParam(queryParamJSON, "DATE_FORMAT(smh.start_date_active,'%Y-%m-%d') ",
				"creationDateTo", query, queryParamMap, ">=");
		SaafToolUtils.parperParam(queryParamJSON, "DATE_FORMAT(smh.start_date_active,'%Y-%m-%d') ",
				"creationDateFrom", query, queryParamMap, "<=");
		/*
		 * Object value; Object key; for (Entry<String, Object> entry :
		 * queryParamJSON.entrySet()) { value = entry.getValue(); key =
		 * entry.getKey(); if("messageId".equals(key)&&null!=value){
		 * query.append(" and smh.message_id ="+value); }else
		 * if("messageCode".equals(key)&&null!=value){
		 * query.append(" and smh.message_code like '%"+value+"'%"); }else
		 * if("messageNumber".equals(key)&&null!=value){
		 * $scope.virSearch(res.data);
		 * query.append(" and smh.message_code ='"+value+"'"); }else
		 * if("messageDesc".equals(key)&&null!=value){
		 * query.append(" and smh.message_desc like '%"+value+"%'"); }else
		 * if("message_type".equals(key)&&null!=value){
		 * query.append(" and smh.message_type ="+value+""); } }
		 */

		// 分组
		query.append(" GROUP BY smh.message_id ");
		String countSql = "select count(1) from (" + query + ")";
		// 排序
		query.append(" ORDER BY smh.last_update_date desc  ");
		Pagination findListResult = saafMessageHeadDAO_HI_RO.findPagination(
				query.toString(),countSql, queryParamMap, pagIndex, pagRow);

		return findListResult;
	}

	/**
	 * 查询消息行
	 * 
	 * @param queryParamJSON
	 * @param pagIndex
	 * @param pagRow
	 * @return
	 * @throws Exception
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     HLH            创建
	 * ===========================================================================
	 */
	public Pagination findSaafMessageLine(JSONObject queryParamJSON,
			Integer pagIndex, Integer pagRow) throws Exception {
		StringBuffer query = new StringBuffer();
		query.append(SaafMessageLineEntity_HI_RO.QUERY_SUPPLIER_SQL);
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		SaafToolUtils.parperParam(queryParamJSON, "sml.message_id",
				"messageId", query, queryParamMap, "=");
		String countSql = "select count(1) from (" + query + ")";
		query.append(" ORDER BY sml.last_update_date desc  ");
		Pagination findListResult = saafMessageLineDAO_HI_RO.findPagination(
				query.toString(),countSql, queryParamMap, pagIndex, pagRow);
		return findListResult;
	}

	/**
	 * 查询用户
	 * 
	 * @param queryParamJSON
	 * @param pagIndex
	 * @param pagRow
	 * @return
	 * @throws Exception
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     HLH            创建
	 * ===========================================================================
	 */
	public Pagination findMessageUserLOV(JSONObject queryParamJSON,
			Integer pagIndex, Integer pagRow) throws Exception {
		StringBuffer query = new StringBuffer();
		query.append(SaafMessageLineEntity_HI_RO.QUERY_USER_LOV);
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		SaafToolUtils.parperParam(queryParamJSON, "su.user_name", "userCode",
				query, queryParamMap, "like");
		SaafToolUtils.parperParam(queryParamJSON, "su.user_full_name",
				"userName", query, queryParamMap, "like");
		queryParamMap.put("messagelineid", queryParamJSON.get("messageLineId"));
		String countSql = "select count(1) from (" + query + ")";
		query.append(" ORDER BY checkFlag DESC , su.last_update_date desc  ");
		Pagination findListResult = saafMessageLineDAO_HI_RO.findPagination(
				query.toString(),countSql, queryParamMap, pagIndex, pagRow);
		return findListResult;
	}

	/**
	 * 查询用户组
	 * 
	 * @param queryParamJSON
	 * @param pagIndex
	 * @param pagRow
	 * @return
	 * @throws Exception
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     HLH            创建
	 * ===========================================================================
	 */
	public Pagination findMessageGroupLOV(JSONObject queryParamJSON,
			Integer pagIndex, Integer pagRow) throws Exception {
		StringBuffer query = new StringBuffer();
		query.append(SaafMessageLineEntity_HI_RO.QUERY_GROUP_LOV);
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		SaafToolUtils.parperParam(queryParamJSON, "sug.group_code", "userCode",
				query, queryParamMap, "like");
		SaafToolUtils.parperParam(queryParamJSON, "sug.group_desc", "userName",
				query, queryParamMap, "like");
		queryParamMap.put("messageLineId", queryParamJSON.get("messageLineId"));
		String countSql = "select count(1) from (" + query + ")";
		query.append(" ORDER BY checkFlag DESC , sug.last_update_date desc  ");

		Pagination findListResult = saafMessageLineDAO_HI_RO.findPagination(
				query.toString(),countSql, queryParamMap, pagIndex, pagRow);
		return findListResult;
	}

	/**
	 * 保存消息头
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     HLH            创建
	 * ===========================================================================
	 */
	public JSONObject saveSaafMessageHead(JSONObject paramData,
			JSONArray paramDataList, int userId) throws Exception {
		// JSONObject data = new JSONObject();
		/*
		 * SaafMessageHeadEntity_HI row =
		 * JSONObject.parseObject(paramData.toString(),
		 * SaafMessageHeadEntity_HI.class); if (null == row.getMessageId()) {
		 * row.setCreationDate(new Date()); row.setCreatedBy(userId);
		 * row.setLastUpdateDate(new Date()); row.setLastUpdatedBy(userId);
		 * row.setLastUpdateLogin(userId);
		 * row.setMessageCode("");//SaafToolUtils
		 * .getBillNumber("SAAF_MESSAGE_HEAD"));
		 * row.setMessageUrl("#/home/show_message_List/" +
		 * row.getMessageCode());
		 * row.setCategoryCode1(paramData.getString("bigCategoryCode"));
		 * row.setCategoryCode2(paramData.getString("middleCategoryCode"));
		 * row.setCategoryCode3(paramData.getString("smallCategoryCode"));
		 * saafMessageHeadDAO_HI.save(row); data.put("messageId",
		 * row.getMessageId());
		 * 
		 * } else {
		 * 
		 * SaafMessageHeadEntity_HI entity =
		 * saafMessageHeadDAO_HI.getById(row.getMessageId()); JSONObject
		 * tempJson = (JSONObject)JSON.toJSON(entity);
		 * tempJson.putAll(paramData); row =
		 * JSONObject.parseObject(tempJson.toString(),
		 * SaafMessageHeadEntity_HI.class);
		 * //tempJson.toJavaObject(SaafMessageHeadEntity_HI.class);
		 * BeanUtils.copyProperties(row, entity); entity.setLastUpdateDate(new
		 * Date()); entity.setLastUpdatedBy(userId);
		 * entity.setLastUpdateLogin(userId);
		 * row.setCategoryCode1(paramData.getString("bigCategoryCode"));
		 * row.setCategoryCode2(paramData.getString("middleCategoryCode"));
		 * row.setCategoryCode3(paramData.getString("smallCategoryCode"));
		 * saafMessageHeadDAO_HI.saveOrUpdate(entity); data.put("messageId",
		 * entity.getMessageId()); // if ("RECALL".equals(entity.getStatus())) {
		 * // boolean resultStr =
		 * saafMessagePushServer.setPushState(entity.getMessageId(), userId,
		 * "RECALL"); // } }
		 */
		SaafMessageHeadEntity_HI headerRow = null;
		Integer operatorUserId = paramData.getInteger("varUserId");
		// 保存消息判断
		if (null == paramData.get("messageId")
				|| "".equals(paramData.get("messageId"))) {
			headerRow = new SaafMessageHeadEntity_HI();
			Date date = new Date();
			String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
			String messageCode = saafSequencesUtil.getDocSequences(
					"AAF_MESSAGE_HEAD", "", dateFromate, 3);
			headerRow.setMessageCode(messageCode);
			headerRow.setMessageUrl("#/home/show_message_List/"
					+ headerRow.getMessageCode());
			headerRow.setMessageType(paramData.getString("messageType"));
			headerRow.setStatus(paramData.getString("status"));
		} else {
			// 判断存在就是修改
			headerRow = saafMessageHeadDAO_HI.findByProperty("messageId",
					paramData.getInteger("messageId")).get(0);
		}
		headerRow.setOperatorUserId(operatorUserId);
		headerRow.setCategoryCode1(paramData.getString("bigCategoryCode"));
		headerRow.setCategoryCode2(paramData.getString("middleCategoryCode"));
		headerRow.setCategoryCode3(paramData.getString("smallCategoryCode"));
		headerRow.setNoticeType(paramData.getString("noticeType"));
		if (!"".equals(paramData.getString("startDateActive"))) {
			headerRow.setStartDateActive(paramData.getDate("startDateActive"));
		}
		headerRow.setMessageDesc(paramData.getString("messageDesc"));
		headerRow.setMessageTex(paramData.getString("messageTex"));
		headerRow.setMessageFileId(paramData.getInteger("messageFileId"));
		saafMessageHeadDAO_HI.saveOrUpdate(headerRow);

		List<SaafMessageLineEntity_HI> lineList = new ArrayList<SaafMessageLineEntity_HI>();
		JSONObject linesData = paramData.getJSONObject("linesData");
		JSONArray valuesArray = linesData.getJSONArray("data");
		for (int i = 0; i < valuesArray.size(); i++) {

			JSONObject valuesJson = valuesArray.getJSONObject(i);

			SaafMessageLineEntity_HI lineRow = null;
			// 保存消息行判断
			if (valuesJson.getInteger("messageLineId") == null) {
				lineRow = new SaafMessageLineEntity_HI();
				lineRow.setMessageId(headerRow.getMessageId());
			} else {
				// 判断存在就是修改
				lineRow = saafMessageLineDAO_HI.getById(valuesJson
						.getInteger("messageLineId"));
			}

			lineRow.setDescription(valuesJson.getString("description"));
			lineRow.setSupplierId(valuesJson.getInteger("supplierId"));
			lineRow.setOperatorUserId(operatorUserId);
			lineList.add(lineRow);
		}
		saafMessageLineDAO_HI.saveOrUpdateAll(lineList);
		return SToolUtils.convertResultJSONObj("S", "保存通知成功", 1, null);
	}

	/**
	 * 保存消息行
	 * 
	 * @param paramDataList
	 * @param userId
	 * @return
	 * @throws Exception
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     HLH            创建
	 * ===========================================================================
	 */
	public JSONObject saveSaafMessageLine(JSONArray paramDataList, int userId,
			Integer messageId) throws Exception {
		for (int i = 0; i < paramDataList.size(); i++) {
			JSONObject paramData = paramDataList.getJSONObject(i);
			SaafMessageLineEntity_HI row = JSONObject.parseObject(
					paramData.toString(), SaafMessageLineEntity_HI.class); // paramData.toJavaObject(SaafMessageLineEntity_HI.class);
			if (null == row.getMessageLineId()) {
				row.setOperatorUserId(userId);
				row.setMessageId(messageId);
				row.setSupplierId(paramData.getInteger("supplierId"));
				saafMessageLineDAO_HI.save(row);
			} else {
				SaafMessageLineEntity_HI entity = saafMessageLineDAO_HI
						.getById(row.getMessageLineId());
				JSONObject tempJson = (JSONObject) JSON.toJSON(entity);
				tempJson.putAll(paramData);
				row = JSONObject.parseObject(tempJson.toString(),
						SaafMessageLineEntity_HI.class); // tempJson.toJavaObject(SaafMessageLineEntity_HI.class);
				BeanUtils.copyProperties(row, entity);
				entity.setOperatorUserId(userId);
				row.setSupplierId(paramData.getInteger("supplierId"));
				saafMessageLineDAO_HI.saveOrUpdate(entity);
			}
		}

		return SToolUtils.convertResultJSONObj("S",
				shortDescMessageServer.getMessage("SAVE-SUCCESS"), 1, null);
	}

	/**
	 * 消息推送
	 * 
	 * @param messageId
	 * @param crateuserId
	 * @param sourceType
	 * @param sourceId
	 * @return
	 * @throws Exception
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     HLH            创建
	 * ===========================================================================
	 */
	public JSONObject updateSaafMessage(Integer messageId, int crateuserId,
			String sourceType, String sourceId) throws Exception {
		SaafMessageHeadEntity_HI row = saafMessageHeadDAO_HI.getById(messageId);
		row.setStatus("COMPLETE");
		saafMessageHeadDAO_HI.saveOrUpdate(row);
		List<SaafMessageLineEntity_HI> lineList = saafMessageLineDAO_HI
				.findByProperty("messageId", messageId);
		if (lineList.size() <= 0) {
			new Exception("没有可以接受通知/代办用户！");
		}
		// 删除，已经存在的消息推送
		Map<String, Object> map = new HashMap<>();
		map.put("messageId", row.getMessageId());
		List<SaafMessagePushEntity_HI> pushList = saafMessagePushDAO_HI
				.findByProperty(map);
		saafMessagePushDAO_HI.delete(pushList);
		List<String> userIdList;

		for (SaafMessageLineEntity_HI lineEntity : lineList) {
			if ("USER".equals(lineEntity.getType())) {
				String userIdStr = lineEntity.getUseridOrGroup();
				userIdStr = userIdStr.substring(1, userIdStr.length() - 1);
				String[] userIdStrs = userIdStr.split(",");
				userIdList = Arrays.asList(userIdStrs);
				for (int j = 0; j < userIdList.size(); j++) {
					int userId = Integer.parseInt(userIdList.get(j));
					saveSaafMessagePush(row, userId, crateuserId, sourceType,
							sourceId);
				}
			} else {
				String groupIdStr = lineEntity.getUseridOrGroup();
				groupIdStr = groupIdStr.substring(1, groupIdStr.length() - 1);
				List<SaafUserGroupEntity_HI> userGroupList = saafUserGroupDAO_HI
						.findList(" from SaafUserGroupEntity_HI sug where sug.userGroupId in ( "
								+ groupIdStr + " )");
				StringBuffer sql = new StringBuffer();
				for (int i = 0; i < userGroupList.size(); i++) {
					SaafUserGroupEntity_HI userGroupEntity = userGroupList
							.get(i);
					sql.append("( " + userGroupEntity.getGroupSql() + " )");
					if (i != userGroupList.size() - 1) {
						sql.append(" UNION  ");
					}
				}
				List<SaafUserGroupEntity_HI> userList = saafUserGroupDAO_HI
						.findList(sql);
				for (SaafUserGroupEntity_HI userEntity : userList) {
					saveSaafMessagePush(row, userEntity.getUserGroupId(),
							crateuserId, sourceType, sourceId);
				}
			}
		}
		return SToolUtils.convertResultJSONObj("S",
				shortDescMessageServer.getMessage("SAVE-SUCCESS"), 1, null);
	}

	public JSONObject saveSaafMessagePush(SaafMessageHeadEntity_HI row,
			int userId, int createUserId, String sourceType, String sourceId)
			throws Exception {

		SaafMessagePushEntity_HI entity = new SaafMessagePushEntity_HI();
		entity.setMessageId(row.getMessageId());
		entity.setMessageCode(row.getMessageCode());
		entity.setUserId(userId);
		entity.setState("UNREADED");
		entity.setMessageDesc(row.getMessageDesc());
		entity.setMessageType(row.getMessageType());
		entity.setMessageUrl(row.getMessageUrl());
		if ("NEED".equals(row.getMessageType())) {
			entity.setSourceType(sourceType);
			entity.setSourceId(sourceId);
		}
		entity.setStartDateActive(new Date());
		entity.setOperatorUserId(createUserId);
		saafMessagePushDAO_HI.save(entity);
		return SToolUtils.convertResultJSONObj("S",
				shortDescMessageServer.getMessage("SAVE-SUCCESS"), 1, null);
	}

	/**
	 * 用于调度，删除代办/通知数据
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     HLH            创建
	 * ===========================================================================
	 */
	@Override
	public JSONObject deleteSaafMessagePush(JSONObject params) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from SaafMessagePushEntity_HI where 1=1 "). //
				append(" and ((state='READED' and messageType='NOTICE') "). // 通知已读
				append(" 	or (state='DONE' and  messageType='NEED'))"); // 待办已处理
		sb.append(" and lastUpdateDate<:lastUpdateDate");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lastUpdateDate", params.getDate("lastUpdateDate"));
		List<SaafMessagePushEntity_HI> list = saafMessagePushDAO_HI.findList(
				sb, map);
		LOGGER.info("将删除的数据list------------->" + JSON.toJSONString(list));
		saafMessagePushDAO_HI.deleteAll(list);
		return SToolUtils.convertResultJSONObj("S", "删除成功", list.size(), null);
	}

	/**
	 * 查询冻结、合格、引入中状态下的供应商
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     HLH            创建
	 * ===========================================================================
	 */
	@Override
	public Pagination findSupplierUserLov(JSONObject parameters,
			Integer pageIndex, Integer pageRows) throws Exception {
		System.out.println("==========" + parameters);
		try {
			Map<String, Object> queryParamMap = new HashMap<String, Object>();
			StringBuffer sb = new StringBuffer();
			sb.append(SaafMessageLineEntity_HI_RO.QUERY_SUPPLIER_LOV);
			SaafToolUtils.parperParam(parameters, "si.supplier_id",
					"supplierId", sb, queryParamMap, "=");
			SaafToolUtils.parperParam(parameters, "si.supplier_name",
					"supplierName", sb, queryParamMap, "like");
			SaafToolUtils.parperParam(parameters, "si.supplier_number",
					"supplierNumber", sb, queryParamMap, "like");
			String countSql = "select count(1) from (" + sb + ")";
			Pagination<SaafMessageLineEntity_HI_RO> rowSet = saafMessageLineDAO_HI_RO
					.findPagination(sb.toString(),countSql, queryParamMap, pageIndex,
							pageRows);
			return rowSet;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 推送通知
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     HLH            创建
	 * ===========================================================================
	 */
	@Override
	public JSONObject pushSaafMessage(Integer messageId, int crateuserId,
			String sourceType, String sourceId) throws Exception {
		SaafMessageHeadEntity_HI row = saafMessageHeadDAO_HI.getById(messageId);
		row.setStatus("COMPLETE");
		saafMessageHeadDAO_HI.saveOrUpdate(row);
		List<SaafMessageLineEntity_HI> lineList = saafMessageLineDAO_HI
				.findByProperty("messageId", messageId);

		List<SaafMessageHeadEntity_HI> headerList = saafMessageHeadDAO_HI
				.findByProperty("messageId", messageId);
		if (headerList.size() <= 0) {
			new Exception("没有可以接受通知/代办用户！");
		}
		// 删除，已经存在的消息推送
		Map<String, Object> map = new HashMap<>();
		map.put("messageId", row.getMessageId());
		List<SaafMessagePushEntity_HI> pushList = saafMessagePushDAO_HI
				.findByProperty(map);
		saafMessagePushDAO_HI.delete(pushList);
		List<String> strIdList;
		StringBuffer query = new StringBuffer();
		Map<String, Object> queryParamMap = new HashMap<String, Object>();

		if ("OVERALL".equals(row.getNoticeType())) {
			query.append(SaafMessageHeadEntity_HI_RO.QUERY_OVERALL_SQL);

			List<SaafMessageHeadEntity_HI_RO> userIdList = saafMessageHeadDAO_HI_RO
					.findList(query);
			for (SaafMessageHeadEntity_HI_RO userEntity : userIdList) {
				saveSaafMessagePush(row, userEntity.getUserId(), crateuserId,
						sourceType, sourceId);
			}
		} else if ("APPOINT".equals(row.getNoticeType())) {
			query.append(SaafMessageHeadEntity_HI_RO.QUERY_APPOINT_SQL);
			query.append( "  and l.message_id = "+row.getMessageId() );
			List<SaafMessageHeadEntity_HI_RO> userIdList = saafMessageHeadDAO_HI_RO
					.findList(query);
			for (SaafMessageHeadEntity_HI_RO userEntity : userIdList) {
				saveSaafMessagePush(row, userEntity.getUserId(), crateuserId,
						sourceType, sourceId);
			}
		} else if ("CATEGORY".equals(row.getNoticeType())) {
			query.append(SaafMessageHeadEntity_HI_RO.QUERY_CATEGORY_SQL);
			queryParamMap.put("varMessageId", messageId);
			List<SaafMessageHeadEntity_HI_RO> userIdList = saafMessageHeadDAO_HI_RO
					.findList(query, queryParamMap);
			for (SaafMessageHeadEntity_HI_RO userEntity : userIdList) {
				saveSaafMessagePush(row, userEntity.getUserId(), crateuserId,
						sourceType, sourceId);
			}

		}
		return SToolUtils.convertResultJSONObj("S",
				shortDescMessageServer.getMessage("SAVE-SUCCESS"), 1, null);
	}

	/**
	 * 删除消息行
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     HLH            创建
	 * ===========================================================================
	 */
	@Override
	public JSONObject deleteSaafMessageLine(JSONObject params) throws Exception {
		LOGGER.info("删除消息行，参数：" + params.toString());
		try {
			SaafMessageLineEntity_HI lineRow = null;
			List lineList = saafMessageLineDAO_HI.findByProperty(
					"messageLineId", params.getInteger("messageLineId"));
			if (lineList != null && lineList.size() > 0) {
				lineRow = (SaafMessageLineEntity_HI) lineList.get(0);
				saafMessageLineDAO_HI.delete(lineRow);
			} else {
				return SToolUtils.convertResultJSONObj("E",
						"删除消息行失败，" + params.getString("messageLineId") + "不存在",
						0, null);
			}
			return SToolUtils.convertResultJSONObj("S", "删除消息行成功", 1, null);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 保存消息
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     HLH            创建
	 * ===========================================================================
	 */
	@Override
	public JSONObject saveSaafMessageHeadLine(JSONObject params)
			throws Exception {
		SaafMessageHeadEntity_HI headerRow = null;
		Integer operatorUserId = params.getInteger("varUserId");
		// 保存消息判断
		if (null == params.get("messageId")
				|| "".equals(params.get("messageId"))) {
			headerRow = new SaafMessageHeadEntity_HI();
			Date date = new Date();
			String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
			String messageCode = saafSequencesUtil.getDocSequences(
					"AAF_MESSAGE_HEAD", "", dateFromate, 3);
			headerRow.setMessageCode(messageCode);
			headerRow.setMessageUrl("#/home/show_message_List/"
					+ headerRow.getMessageCode());
			headerRow.setMessageType(params.getString("messageType"));
			headerRow.setStatus(params.getString("status"));
		} else {
			// 判断存在就是修改
			headerRow = saafMessageHeadDAO_HI.findByProperty("messageId",
					params.getInteger("messageId")).get(0);
		}
		headerRow.setOperatorUserId(operatorUserId);
		headerRow.setCategoryCode1(params.getString("bigCategoryCode"));
		headerRow.setCategoryCode2(params.getString("middleCategoryCode"));
		headerRow.setCategoryCode3(params.getString("smallCategoryCode"));
		headerRow.setNoticeType(params.getString("noticeType"));
		if (!"".equals(params.getString("startDateActive"))) {
			headerRow.setStartDateActive(params.getDate("startDateActive"));
		}
		headerRow.setMessageDesc(params.getString("messageDesc"));
		headerRow.setMessageTex(params.getString("messageTex"));
		headerRow.setMessageFileId(params.getInteger("messageFileId"));
		saafMessageHeadDAO_HI.saveOrUpdate(headerRow);

		List<SaafMessageLineEntity_HI> lineList = new ArrayList<SaafMessageLineEntity_HI>();
		JSONArray valuesArray = params.getJSONArray("linesData");
		// JSONArray valuesArray = linesData.getJSONArray("data");
		if (null != valuesArray) {
			for (int i = 0; i < valuesArray.size(); i++) {

				JSONObject valuesJson = valuesArray.getJSONObject(i);

				SaafMessageLineEntity_HI lineRow = null;
				// 保存消息行判断
				if (valuesJson.getInteger("messageLineId") == null) {
					lineRow = new SaafMessageLineEntity_HI();
					lineRow.setMessageId(headerRow.getMessageId());
				} else {
					// 判断存在就是修改
					lineRow = saafMessageLineDAO_HI.getById(valuesJson
							.getInteger("messageLineId"));
				}

				lineRow.setDescription(valuesJson.getString("description"));
				lineRow.setSupplierId(valuesJson.getInteger("supplierId"));
				lineRow.setOperatorUserId(operatorUserId);
				lineList.add(lineRow);
			}
			saafMessageLineDAO_HI.saveOrUpdateAll(lineList);
		}

		JSONObject result = SToolUtils.convertResultJSONObj("S", "保存消息成功", 1,
				headerRow);
		result.put("qualificationId", headerRow.getMessageId());
		return result;
	}

	/**
	 * 删除通知头信息
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     HLH            创建
	 * ===========================================================================
	 */
	@Override
	public JSONObject deleteSaafMessageHeader(JSONObject params)
			throws Exception {
		LOGGER.info("删除消息行，参数：" + params.toString());
		try {
			SaafMessageHeadEntity_HI headerRow = null;
			List headerList = saafMessageHeadDAO_HI.findByProperty("messageId",
					params.getInteger("messageId"));

			if (headerList != null && headerList.size() > 0) {
				headerRow = (SaafMessageHeadEntity_HI) headerList.get(0);
				saafMessageHeadDAO_HI.delete(headerRow);
				if (headerRow.getNoticeType().equals("APPOINT")) {
					List lineList = saafMessageLineDAO_HI.findByProperty(
							"messageId", params.getInteger("messageId"));
					if (lineList == null && lineList.size() < 0) {
						saafMessageHeadDAO_HI.delete(headerRow);
					} else if (lineList != null && lineList.size() > 0) {
						saafMessageHeadDAO_HI.delete(headerRow);
						saafMessageLineDAO_HI.delete(lineList);
					}
				}
			} else {
				return SToolUtils.convertResultJSONObj("E",
						"删除通知失败，" + params.getString("messageId") + "不存在", 0,
						null);
			}
			return SToolUtils.convertResultJSONObj("S", "删除通知成功", 1, null);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
