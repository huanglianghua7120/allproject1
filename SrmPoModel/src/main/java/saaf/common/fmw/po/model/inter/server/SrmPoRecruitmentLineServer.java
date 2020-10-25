package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitmentHeaderEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitmentLineEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoRecruitmentLine;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.SrmPoRecruitmentLineEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("srmPoRecruitmentLineServer")
public class SrmPoRecruitmentLineServer implements ISrmPoRecruitmentLine{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRecruitmentLineServer.class);
	@Autowired
	private ViewObject<SrmPoRecruitmentLineEntity_HI> srmPoRecruitmentLineDAO_HI;
	@Autowired
	private BaseViewObject<SrmPoRecruitmentLineEntity_HI_RO> srmPoRecruitmentLineDAO_HI_RO;

	public SrmPoRecruitmentLineServer() {
		super();
	}
	/**
	 * Description：招聘行
	 * @param jsonParams 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@Override
	public List<SrmPoRecruitmentLineEntity_HI_RO> findRecruitmentLineInfo(JSONObject jsonParams) throws Exception {
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		StringBuffer queryParamSql = new StringBuffer(SrmPoRecruitmentLineEntity_HI_RO.QUERY_RECRUITMENT_LINE);
		SaafToolUtils.parperParam(jsonParams, "sprl.recruitment_line_id", "recruitmentLineId", queryParamSql, queryParamMap, "=");
		SaafToolUtils.parperParam(jsonParams, "sprl.recruitment_header_id", "recruitmentHeaderId", queryParamSql, queryParamMap, "=");
		SaafToolUtils.parperParam(jsonParams, "sprl.line_status", "lineStatus", queryParamSql, queryParamMap, "=");
		SaafToolUtils.parperParam(jsonParams, "slv.meaning", "lineStatusName", queryParamSql, queryParamMap, "=");
		SaafToolUtils.parperParam(jsonParams, "sprl.recruitment_description", "recruitmentDescription", queryParamSql, queryParamMap, "=");
		queryParamSql.append(" order by sprl.last_update_date");
		return srmPoRecruitmentLineDAO_HI_RO.findList(queryParamSql.toString(),queryParamMap);
	}



	/**
	 * Description：删除招聘行
	 * @param params 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@Override
	public JSONObject deleteRecruitmentLines(JSONObject params) throws Exception {
		LOGGER.info("删除信息，参数：" + params.toString());
		JSONArray lineIds = params.getJSONArray("data");
		SrmPoRecruitmentLineEntity_HI line = new SrmPoRecruitmentLineEntity_HI();
		for (int i = 0, j = lineIds.size(); i < j; i++) {
			Integer poLineId = lineIds.getInteger(i);
			if (!(poLineId == null || "".equals(poLineId))) {
				line = srmPoRecruitmentLineDAO_HI.getById(poLineId);
				if (line != null && "DRAFT".equals(line.getLineStatus())) {
					srmPoRecruitmentLineDAO_HI.delete(line);
				}
			}
		}
		return SToolUtils.convertResultJSONObj("S", "删除成功", lineIds.size(), null);
	}

}
