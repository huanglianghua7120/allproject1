package saaf.common.fmw.intf.model.inter.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yhg.hibernate.core.dao.ViewObject;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;
import saaf.common.fmw.intf.model.entities.readonly.SrmIntfLogsEntity_HI_RO;
import saaf.common.fmw.intf.model.inter.ISrmIntfLogs;

@Component("srmIntfLogsServer")
public class SrmIntfLogsServer implements ISrmIntfLogs {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmIntfLogsServer.class);

	// private ViewObject srmIntfLogsDAO_HI;
	// @Autowired
	// private ViewObject srmIntfLogsDAO_HI;
	@Autowired
	private BaseViewObject<SrmIntfLogsEntity_HI_RO> SrmIntfLogsDAO_HI_RO;
	@Autowired
	private ViewObject<SrmIntfLogsEntity_HI> srmIntfLogsDAO_HI;

	public String saveData(String logId, String intfType, String inData, String outData) throws Exception {
		
	List<SrmIntfLogsEntity_HI> list = 	srmIntfLogsDAO_HI.findByProperty("intfStatus","TEST");
	    System.out.println(list.size());
		
		SrmIntfLogsEntity_HI row = new SrmIntfLogsEntity_HI();
		row.setIntfStatus("TEST");
		row.setIntfType(intfType);
		row.setInData(inData);
		row.setOperatorUserId(Integer.parseInt("8")); 

		System.out.println("test22222");
		System.out.println(srmIntfLogsDAO_HI);
		srmIntfLogsDAO_HI.save(row);
		return "S";

	}
	
	public void saveOrUpdateDate(SrmIntfLogsEntity_HI row) throws Exception{
		srmIntfLogsDAO_HI.saveOrUpdate (row);

	}

	/**
	 * 查询日志
	 *
	 * @param jsonParams
	 * @param pageIndex
	 * @param pageRows
	 */
	public Pagination<SrmIntfLogsEntity_HI_RO> findLogList(JSONObject jsonParams, Integer pageIndex,
														   Integer pageRows) throws Exception {
		LOGGER.info(JSONObject.toJSONString(jsonParams));
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		try {
			StringBuffer queryString = new StringBuffer();
            StringBuffer appendString = new StringBuffer();
            StringBuffer countString = new StringBuffer();
			queryString.append(SrmIntfLogsEntity_HI_RO.QUERY_LOG_INFO);
			countString.append(SrmIntfLogsEntity_HI_RO.QUERY_LOG_COUNT);
			SaafToolUtils.parperParam(jsonParams, "sil.intf_type", "intfType", appendString, queryParamMap, "=");
			SaafToolUtils.parperParam(jsonParams, "sil.intf_status", "intfStatus", appendString, queryParamMap, "=");
//			SaafToolUtils.parperParam(jsonParams, "date_format(sil.creation_date,'%Y-%m-%d')", "createDate", appendString, queryParamMap, "=");
			String createDate = jsonParams.getString("createDate");
			if (createDate != null && !"".equals(createDate.trim())) {
				appendString.append(" AND trunc(sil.creation_date) = to_date(:createDate, 'yyyy-mm-dd')");
				queryParamMap.put("createDate", createDate);
			}
			queryString.append(appendString);
			queryString.append(" ORDER BY sil.creation_date DESC");
			countString.append(appendString);
			Pagination<SrmIntfLogsEntity_HI_RO> result = SrmIntfLogsDAO_HI_RO.findPagination(queryString.toString(),countString, queryParamMap, pageIndex, pageRows);
			return result;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List findLogData(JSONObject jsonParams) throws Exception {
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		try {
			StringBuffer queryString = new StringBuffer();
			queryString.append(SrmIntfLogsEntity_HI_RO.QUERY_DATA_INFO);
			SaafToolUtils.parperParam(jsonParams, "sil.log_id", "logId", queryString, queryParamMap, "=");
			List<SrmIntfLogsEntity_HI_RO> result = SrmIntfLogsDAO_HI_RO.findList(queryString.toString(), queryParamMap);
			return result;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}


	// @Resource(name = "srmIntfLogsDAO_HI")
	// public void setSrmIntfLogsDAO_HI(ViewObject srmIntfLogsDAO_HI) {
	// this.srmIntfLogsDAO_HI =
	// srmIntfLogsDAO_HI;
	// }

}
