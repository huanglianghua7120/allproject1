package saaf.common.fmw.po.model.inter.server;

import com.yhg.hibernate.core.dao.BaseViewObject;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitArchivesLEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoRecruitArchivesL;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.po.model.entities.SrmPoRecruitArchivesLEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("srmPoRecruitArchivesLServer")
public class SrmPoRecruitArchivesLServer implements ISrmPoRecruitArchivesL{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRecruitArchivesLServer.class);
	@Autowired
	private ViewObject<SrmPoRecruitArchivesLEntity_HI> srmPoRecruitArchivesLDAO_HI;

	@Autowired
	private BaseViewObject<SrmPoRecruitArchivesLEntity_HI_RO> srmPoRecruitArchivesLDAO_HI_RO;

	public SrmPoRecruitArchivesLServer() {
		super();
	}

	/**
	 * Description：查询招聘历史行
	 * @param jsonParams 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@Override
	public List<SrmPoRecruitArchivesLEntity_HI_RO> findRecruitmentArchivesLine(JSONObject jsonParams) throws Exception {
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		StringBuffer queryParamSql = new StringBuffer(SrmPoRecruitArchivesLEntity_HI_RO.QUERY_ARCHIVES_LINE_SQL);
		SaafToolUtils.parperParam(jsonParams, "sprah.recruitment_header_id", "recruitmentHeaderId", queryParamSql, queryParamMap, "=");
		SaafToolUtils.parperParam(jsonParams, "sprah.edition_num", "editionNum", queryParamSql, queryParamMap, "=");
		SaafToolUtils.parperParam(jsonParams, "sprah.archives_header_id", "archivesHeaderId", queryParamSql, queryParamMap, "=");
		queryParamSql.append(" ORDER BY spral.last_update_date desc");
		return srmPoRecruitArchivesLDAO_HI_RO.findList(queryParamSql, queryParamMap);
	}
}
