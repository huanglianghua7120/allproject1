package saaf.common.fmw.pos.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSceneManageEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISceneManage;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

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
@Path("/srmPosSceneMangeServices")
@Component
public class SrmPosSceneMangeServices extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSceneMangeServices.class);

    @Autowired
    private ISceneManage srmPosSceneMangeServer;

    public SrmPosSceneMangeServices() {
        super();
    }

    /**
     * 查询场景列表
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSceneManageList")
    @POST
    public String findSceneManageList(@FormParam("params") String params, @FormParam("pageIndex")
            Integer pageIndex, @FormParam("pageRows") Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            System.out.println("==========================================>>>>>>");
            LOGGER.info("------jsonParam------" + jsonParam.toString());
            Pagination<SrmPosSceneManageEntity_HI_RO> infoList = srmPosSceneMangeServer.findSceneManageList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e, 0, null);
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

    @Path("saveSceneManage")
    @POST
    public String saveSceneManage(@FormParam("params")
                                          String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPosSceneMangeServer.saveSceneManage(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (Exception e) {
            LOGGER.error("保存失败！" + e, e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "保存失败!" + e, 0, null);
        }
    }

    /**
     * 删除场景信息
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("deleteSceneManage")
    @POST
    public String deleteSceneManage(@FormParam("params")
                                            String params) {
        LOGGER.info("删除参数：" + params.toString());
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPosSceneMangeServer.deleteSceneManage(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败!", 0, null);
        }
    }
}
