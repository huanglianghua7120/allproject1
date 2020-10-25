package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import saaf.common.fmw.po.model.entities.SrmPoRecruitArchivesLEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitArchivesLEntity_HI_RO;

public interface ISrmPoRecruitArchivesL {

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
	public List<SrmPoRecruitArchivesLEntity_HI_RO> findRecruitmentArchivesLine(JSONObject jsonParams) throws Exception;
}
