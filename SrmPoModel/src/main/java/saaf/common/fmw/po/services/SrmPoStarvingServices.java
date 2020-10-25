package saaf.common.fmw.po.services;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.po.model.entities.readonly.SrmPoStarvingEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoStarving;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：缺料
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-20     hgq             modify
 * ==============================================================================
 */
@Component("srmPoStarvingServices")
@Path("/srmPoStarvingServlet")
public class SrmPoStarvingServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoStarvingServices.class);

    public SrmPoStarvingServices(){super();}


    @Autowired
    private ISrmPoStarving srmPoStarving;

    /**
     * Description：查询缺料
     * @param params 参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    @Path("findStarvingList")
    @POST
    public String findStarvingList(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            List<SrmPoStarvingEntity_HI_RO> list = srmPoStarving.findStarvingList(jsonParam);
            return JSON.toJSONString(list);
        } catch (Exception e) {
        	LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：查询缺料明细
     * @param params 参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    @Path("findStarvingInfoList")
    @POST
    public String findStarvingInfoList(@FormParam("params") String params) {
        try {
        	JSONObject jsonParam = this.parseObject(params);
            List<SrmPoStarvingEntity_HI_RO> list = srmPoStarving.findStarvingInfoPoList(jsonParam);
            return JSON.toJSONString(list);
        } catch (Exception e) {
        	LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
    /**
     * Description：创建缺料信息
     * @param params 参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    @Path("saveNoticeLines")
    @POST
    public String saveNoticeLines(@FormParam("params") String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPoStarving.saveNoticeLines(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
}
