package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONObject;
import java.util.List;

import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.po.model.entities.SrmPoRecruitmentHeaderEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitmentHeaderEntity_HI_RO;

public interface ISrmPoRecruitmentHeader {

	/**
	 * Description：招聘头
	 * @param params 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	List<SrmPoRecruitmentHeaderEntity_HI_RO> findRecruitmentHeaderInfo(JSONObject params) throws Exception;
	/**
	 * Description：招聘查询页
	 * @param params 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	Pagination<SrmPoRecruitmentHeaderEntity_HI_RO> findRecruitmentInfo(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;


	/**
	 * Description：查找招聘版本
	 * @param jsonParams 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	public List<SrmPoRecruitmentHeaderEntity_HI_RO> findEditionNum(JSONObject jsonParams) throws Exception;


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
	JSONObject updateRecruitmentHeaderStatus(JSONObject jsonParams) throws Exception;

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
	JSONObject saveOrSubmitRecruitmentInfo(JSONObject jsonParams)throws Exception;

	/**
	 * Description：删除招聘需求
	 * @param jsonParams 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	public JSONObject deleteRecruitment(JSONObject jsonParams) throws Exception;

}
