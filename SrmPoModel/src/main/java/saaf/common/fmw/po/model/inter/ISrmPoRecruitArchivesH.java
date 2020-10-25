package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import saaf.common.fmw.po.model.entities.SrmPoRecruitArchivesHEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitArchivesHEntity_HI_RO;

public interface ISrmPoRecruitArchivesH {

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
	public List<SrmPoRecruitArchivesHEntity_HI_RO> findRecruitmentArchivesHeader(JSONObject jsonParams) throws Exception;

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
	public void saveEditionNum(JSONObject jsonParams);

}
