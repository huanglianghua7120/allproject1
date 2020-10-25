package saaf.common.fmw.pos.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.SrmPosSceneManageEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSceneManageEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISceneManage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Component("srmPosSceneMangeServer")
public class SrmPosSceneMangeServer implements ISceneManage {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSceneMangeServer.class);

    @Autowired
    private BaseViewObject<SrmPosSceneManageEntity_HI_RO> srmPosSceneManageDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPosSceneManageEntity_HI> srmPosSceneManageDAO_HI;

    /**
     * 查询场景信息
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     zhj             创建
     * ===========================================================================
     */
    @Override
    public Pagination<SrmPosSceneManageEntity_HI_RO> findSceneManageList(
            JSONObject params, Integer pageIndex, Integer pageRows)
            throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmPosSceneManageEntity_HI_RO.QUERY_SCENE_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询过滤条件
            SaafToolUtils.parperParam(params, "sm.scene_manage_id", "sceneManageId", queryString, map, "=");
            SaafToolUtils.parperParam(params, "sm.scene_type", "sceneType", queryString, map, "=");
            // 排序
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" ORDER BY sm.creation_date DESC");
            Pagination<SrmPosSceneManageEntity_HI_RO> sceneManageList = srmPosSceneManageDAO_HI_RO
                    .findPagination(queryString,countSql,map, pageIndex, pageRows);
            return sceneManageList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

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

    @Override
    public JSONObject saveSceneManage(JSONObject params) throws Exception {
        LOGGER.info("保存参数：" + params.toString());
        SrmPosSceneManageEntity_HI sceneRow = null;
        boolean flag = false;
        try {
            Integer operatorUserId = params.getInteger("varUserId");

            // 保存参数判断
            if (null == params.getInteger("sceneManageId")) {
                flag = findExistSceneType(params.getString("sceneType"), params.get("sceneManageId"));
                if (flag) {
                    return SToolUtils.convertResultJSONObj("E", "场景类型已存在，保存失败!", 0, null);
                }
                sceneRow = new SrmPosSceneManageEntity_HI();
            } else {
                // 判断存在就是修改
                sceneRow = srmPosSceneManageDAO_HI.findByProperty("sceneManageId", params.getInteger("sceneManageId")).get(0);
            }
            sceneRow.setSceneType(params.getString("sceneType"));
            sceneRow.setSceneDescription(params.getString("sceneDescription"));
            sceneRow.setQualifiedCensorFlag("Y");
            sceneRow.setLocaleReviewFlag(params.getString("localeReviewFlag"));
            sceneRow.setSampleTrialsFlag(params.getString("sampleTrialsFlag"));
            sceneRow.setOperatorUserId(operatorUserId);
            srmPosSceneManageDAO_HI.saveOrUpdate(sceneRow);
            return SToolUtils.convertResultJSONObj("S", "保存成功", 1, sceneRow);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 删除场景信息
     *
     * @param params
     * @return
     * @throws Exception
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     zhj             创建
     * ===========================================================================
     */
    @Override
    public JSONObject deleteSceneManage(JSONObject params) throws Exception {
        try {
            SrmPosSceneManageEntity_HI sceneManageRow = null;
            List<SrmPosSceneManageEntity_HI> sceneManageList = srmPosSceneManageDAO_HI.findByProperty("sceneManageId", params.getInteger("sceneManageId"));
            if (sceneManageList != null && sceneManageList.size() > 0) {
                sceneManageRow = sceneManageList.get(0);
                srmPosSceneManageDAO_HI.delete(sceneManageRow);
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除失败，" + params.getString("sceneManageId") + "不存在", 0,
                        null);
            }
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 验证场景类型是否重复
     *
     * @param sceneManageId
     * @param sceneType
     * @return
     * @throws Exception
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     zhj             创建
     * ===========================================================================
     */
    public boolean findExistSceneType(String sceneType, Object sceneManageId) {
        StringBuffer queryString = new StringBuffer(SrmPosSceneManageEntity_HI_RO.COMMON_QUERY_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sceneType", sceneType);
        queryString.append(" AND psm.scene_type = :sceneType");
        String countSql = "select count(1) from (" + queryString + ")";
        Pagination<SrmPosSceneManageEntity_HI_RO> sceneManageList = srmPosSceneManageDAO_HI_RO
                .findPagination(queryString,countSql, map, 1, 10);
        if (sceneManageList.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
