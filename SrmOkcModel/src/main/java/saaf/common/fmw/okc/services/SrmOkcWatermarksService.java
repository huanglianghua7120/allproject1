package saaf.common.fmw.okc.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.okc.model.entities.SrmOkcWatermarksEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcWatermarksEntity_HI_RO;
import saaf.common.fmw.okc.model.inter.ISrmOkcWatermarks;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：SrmOkcWatermarksService.java
 * Description：水印控制类
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date     @Author(Updated By)     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019/6/5      欧阳岛          创建
 * <p>
 * ==============================================================================
 */
@Component("srmOkcWatermarksService")
@Path("/srmOkcWatermarksService")
public class SrmOkcWatermarksService extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmOkcWatermarksService.class);
    @Autowired
    private ISrmOkcWatermarks srmOkcWatermarksServer;

    public SrmOkcWatermarksService() {
        super();
    }

    /**
     * Description：分页查找水印列表
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/5      欧阳岛          创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmOkcWatermarksList")
    public String findSrmOkcWatermarksList(@FormParam(PARAMS)
                                                   String params, @FormParam(PAGE_INDEX)
                                                   Integer pageIndex, @FormParam(PAGE_ROWS)
                                                   Integer pageRows) {
        try {
            JSONObject paramJSON = JSON.parseObject(params);
            Pagination<SrmOkcWatermarksEntity_HI_RO> infoList = srmOkcWatermarksServer.findSrmOkcWatermarksList(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("查询水印列表异常：", e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询水印列表失败!" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：保存水印
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/5      欧阳岛          创建
     * ==============================================================================
     */
    @Path("saveSrmOkcWatermarks")
    @POST
    public String saveSrmOkcWatermarks(@FormParam("params")
                                               String params) {
        LOGGER.debug("保存水印信息，参数：{}", params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmOkcWatermarksServer.saveSrmOkcWatermarks(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (UtilsException e) {
            LOGGER.error("保存水印失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "保存水印失败：" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("保存水印失败！", e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存水印失败!" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：删除水印
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/5      欧阳岛          创建
     * ==============================================================================
     */
    @Path("deleteSrmOkcWatermarks")
    @POST
    public String deleteSrmOkcWatermarks(@FormParam("params")
                                                 String params) {
        LOGGER.debug("删除水印信息，参数：{}", params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmOkcWatermarksServer.deleteSrmOkcWatermarks(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (UtilsException e) {
            LOGGER.error("删除水印失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "删除水印失败：" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("删除水印失败！", e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除水印失败!" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：获取水印列表
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/13      欧阳岛          创建
     * ==============================================================================
     */
    @POST
    @Path("findOkcWatermarks")
    public String findOkcWatermarks(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
                                    @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            Pagination<SrmOkcWatermarksEntity_HI> pag = srmOkcWatermarksServer.findEmployeeLov(jsonParams, pageIndex, pageRows);
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSON.toJSONString(pag);
        }catch (UtilsException e) {
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败：" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询失败" + e, e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e.getMessage(), 0, null);
        }
    }

}
