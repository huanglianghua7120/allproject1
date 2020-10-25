/*================================================================================================================================================+
|   Copyright (c) 2018   SIE.                                                                                                                     |
+=================================================================================================================================================+
|  HISTORY                                                                                                                                        |
|                 Date                    Ver.                 Author                       Content                                               |
|               2018-03-17                1.0                 JinShaoJun                    Creation                                              |
+================================================================================================================================================*/
package saaf.common.fmw.pon.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.pon.model.entities.SrmPonDestinationsEntity_HI;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.pon.model.inter.ISrmPonDestinations;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonDestinationsService.java
 * Description：寻源--目的地信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonDestinationsService")
@Path("/srmPonDestinationsService")
public class SrmPonDestinationsService extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonDestinationsService.class);
    @Autowired
    private ISrmPonDestinations srmPonDestinationsServer;

    /**
     * Description：查询目的地列表
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @Path("findDestinationInfo")
    @POST
    public String findDestinationInfo(@FormParam(PARAMS)
                                              String params, @FormParam(PAGE_INDEX)
                                              Integer pageIndex, @FormParam(PAGE_ROWS)
                                              Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info("------jsonParam-----" + jsonParam.toString());
            Pagination<SrmPonDestinationsEntity_HI> infoList = srmPonDestinationsServer.findDestinationInfo(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询列表失败!" + e, 0, null);
        }
    }

    /**
     * Description：保存目的地信息
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("saveDestinationInfo")
    public String saveDestinationInfo(@FormParam(PARAMS) String params) {
        LOGGER.info(params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject json = srmPonDestinationsServer.saveDestinationInfo(jsonParam);
            return JSON.toJSONString(json);
        }catch(RuntimeException e)
        {
            //e.printStackTrace();
            return CommonAbstractServices.convertResultJSONObj("E", "操作失败：数据已有变动，请刷新页面重试。" , 0, null);
        }
        catch (Exception e) {
            //e.printStackTrace();
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "保存失败!" + e, 0, null));
        }
    }

    /**
     * Description：EXCEL导入目的地信息
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("saveDestinationByImport")
    public String saveDestinationByImport(@FormParam(PARAMS) String params) {
        LOGGER.info(params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject json = srmPonDestinationsServer.saveDestinationByImport(jsonParam);
            return JSON.toJSONString(json);
        }catch(RuntimeException e)
        {
            //e.printStackTrace();
            return CommonAbstractServices.convertResultJSONObj("E", "操作失败：数据已有变动，请刷新页面重试。" , 0, null);
        }
        catch (Exception e) {
            //e.printStackTrace();
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "导入失败!" + e, 0, null));
        }
    }

    /**
     * Description：删除目的地信息
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("deleteDestinationInfo")
    public String deleteDestinationInfo(@FormParam(PARAMS) String params) {
        LOGGER.info(params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject json = srmPonDestinationsServer.deleteDestinationInfo(jsonParam);
            return JSON.toJSONString(json);
        } catch (Exception e) {
            //e.printStackTrace();
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "删除失败!" + e, 0, null));
        }
    }
}
