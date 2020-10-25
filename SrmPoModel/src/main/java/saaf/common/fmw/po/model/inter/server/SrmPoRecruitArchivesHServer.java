package saaf.common.fmw.po.model.inter.server;

import com.yhg.hibernate.core.dao.BaseViewObject;
import org.springframework.beans.BeanUtils;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.SrmPoRecruitArchivesLEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoRecruitmentHeaderEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoRecruitmentLineEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitArchivesHEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoRecruitArchivesH;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.po.model.entities.SrmPoRecruitArchivesHEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("srmPoRecruitArchivesHServer")
public class SrmPoRecruitArchivesHServer implements ISrmPoRecruitArchivesH{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRecruitArchivesHServer.class);
	@Autowired
	private ViewObject<SrmPoRecruitArchivesHEntity_HI> srmPoRecruitArchivesHDAO_HI;
	@Autowired
	private BaseViewObject<SrmPoRecruitArchivesHEntity_HI_RO> srmPoRecruitArchivesHDAO_HI_RO;

	@Autowired
	private ViewObject<SrmPoRecruitmentHeaderEntity_HI> srmPoRecruitmentHeaderDAO_HI;

	@Autowired
	private ViewObject<SrmPoRecruitmentLineEntity_HI> srmPoRecruitmentLineDAO_HI;

	@Autowired
	private ViewObject<SrmPoRecruitArchivesLEntity_HI> srmPoRecruitArchivesLDAO_HI;

	public SrmPoRecruitArchivesHServer() {
		super();
	}

	/**
	 * Description：查询招聘历史头
	 * @param jsonParams 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@Override
	public List<SrmPoRecruitArchivesHEntity_HI_RO> findRecruitmentArchivesHeader(JSONObject jsonParams) throws Exception {
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		StringBuffer queryParamSql = new StringBuffer(SrmPoRecruitArchivesHEntity_HI_RO.QUERY_ARCHIVES_H_SQL);
		SaafToolUtils.parperParam(jsonParams, "sprah.recruitment_header_id", "recruitmentHeaderId", queryParamSql, queryParamMap, "=");
		SaafToolUtils.parperParam(jsonParams, "sprah.edition_num", "editionNum", queryParamSql, queryParamMap, "=");
		queryParamSql.append(" ORDER BY sprah.last_update_date desc");
		return srmPoRecruitArchivesHDAO_HI_RO.findList(queryParamSql, queryParamMap);
	}


	/**
	 * Description：保存招聘历史
	 * @param jsonParams 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	public void saveEditionNum(JSONObject jsonParams){
		Integer userId = jsonParams.getInteger("varUserId");
		Integer recruitmentHeaderId = jsonParams.getInteger("recruitmentHeaderId");
		SrmPoRecruitmentHeaderEntity_HI recruitmentHeader = srmPoRecruitmentHeaderDAO_HI.getById(recruitmentHeaderId);
		//保存招聘头信息
		SrmPoRecruitArchivesHEntity_HI copyHeader = new SrmPoRecruitArchivesHEntity_HI();
		BeanUtils.copyProperties(recruitmentHeader, copyHeader);
		copyHeader.setOperatorUserId(userId);
		srmPoRecruitArchivesHDAO_HI.save(copyHeader);
		//保存招聘行信息
		List<SrmPoRecruitmentLineEntity_HI> recruitmentLineList = srmPoRecruitmentLineDAO_HI.findByProperty("recruitmentHeaderId", recruitmentHeaderId);
		if (null != recruitmentLineList && recruitmentLineList.size() > 0) {
			for (SrmPoRecruitmentLineEntity_HI recruitmentLine : recruitmentLineList) {
				SrmPoRecruitArchivesLEntity_HI copyLine = new SrmPoRecruitArchivesLEntity_HI();
				BeanUtils.copyProperties(recruitmentLine, copyLine);
				copyLine.setOperatorUserId(userId);
				copyLine.setArchivesHeaderId(copyHeader.getArchivesHeaderId());
				srmPoRecruitArchivesLDAO_HI.save(copyLine);
			}
		}
	}

}
