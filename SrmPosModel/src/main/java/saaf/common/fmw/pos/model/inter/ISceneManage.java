package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSceneManageEntity_HI_RO;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：场景管理
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
public interface ISceneManage {

    /**
     * 查询场景信息
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmPosSceneManageEntity_HI_RO> findSceneManageList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：保存场景信息
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * sysadminAuditFlag  体系审核(Y/N)  VARCHAR2  N
     * batchTrialsFlag  批量试验(Y/N)  VARCHAR2  N
     * protocolSignFlag  协议签订(Y/N)  VARCHAR2  N
     * sceneManageId  场景管理ID  NUMBER  Y
     * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  Y
     * sceneDescription  场景说明  VARCHAR2  N
     * qualifiedCensorFlag  资质审查(Y/N)  VARCHAR2  N
     * localeReviewFlag  现场考察(Y/N)  VARCHAR2  N
     * sampleTrialsFlag  样品试验(Y/N)  VARCHAR2  N
     * sceneSuitable  适用对象  VARCHAR2  N
     * introduceLocationFlag  引入地点、型号(Y/N)  VARCHAR2  N
     * sceneManageId  场景管理ID  NUMBER  Y
     * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  Y
     * sceneDescription  场景说明  VARCHAR2  N
     * qualifiedCensorFlag  资质审查(Y/N)  VARCHAR2  N
     * localeReviewFlag  现场考察(Y/N)  VARCHAR2  N
     * sampleTrialsFlag  样品试验(Y/N)  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    JSONObject saveSceneManage(JSONObject params) throws Exception;

    /**
     * 删除场景信息
     * @param params
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject deleteSceneManage(JSONObject params) throws Exception;
}
