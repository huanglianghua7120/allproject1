package saaf.common.fmw.po.services;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.po.model.entities.readonly.SrmPoHeaderArchivesEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoHeaderArchives;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Project Name：SrmPoHeaderArchivesServices
 * Company Name：SIE
 * Program Name：
 * Description：采购订单历史
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoHeaderArchivesServices")
@Path("/srmPoHeaderArchivesServices")
public class SrmPoHeaderArchivesServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoHeaderArchivesServices.class);
    private static final String PARAMS = "params";
    private static final String PAGE_INDEX = "pageIndex";
    private static final String PAGE_ROWS = "pageRows";
    @Autowired
    private ISrmPoHeaderArchives srmPoHeaderArchivesServer;

    public SrmPoHeaderArchivesServices() {
        super();
    }

    /**
     * Description：查询采购订单历史头
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("findPoHeaderArchivesBySupplier")
    public String findPoHeaderArchivesBySupplier(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            if (null == jsonParams.getInteger("poHeaderId") || null == jsonParams.getInteger("poVersions")) {
                LOGGER.error("查询失败");
                return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
            }
            List<SrmPoHeaderArchivesEntity_HI_RO> result = srmPoHeaderArchivesServer.findPoHeaderArchivesBySupplier(jsonParams);
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功!", 1, result);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：查询采购订单历史行
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("findPoLineArchivesBySupplier")
    public String findPoLineArchivesBySupplier(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            if (null == jsonParams.getInteger("poHeaderId") || null == jsonParams.getInteger("poVersions")) {
                LOGGER.error("查询失败");
                return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
            }
            List<SrmPoHeaderArchivesEntity_HI_RO> result = srmPoHeaderArchivesServer.findPoLineArchivesBySupplier(jsonParams);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功!", 1, result);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

}
