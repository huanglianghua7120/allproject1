package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.inter.ISaafProfiles;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmComprehensivePerformanceResultsEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmComprehensivePerformanceResults;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.util.List;


/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：综合绩效结果查询Service
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     hgq             创建
 * ===========================================================================
 */

@Path("/srmSpmComprehensivePerformanceResultsService")
@Component
public class SrmSpmComprehensivePerformanceResultsService extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmComprehensivePerformanceResultsService.class);

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    public SrmSpmComprehensivePerformanceResultsService() {
        super();
    }

    @Autowired
    private ISrmSpmComprehensivePerformanceResults srmSpmComprehensivePerformanceResultsServer;

    @Autowired
    private ISaafProfiles baseSaafProfilesServer;



    /**
     * 查询评价维度数据
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
    @Path("findEvaluateDimensionData")
    @POST
    public String findEvaluateDimensionData(@FormParam(PARAMS)
        String params, @FormParam(PAGE_INDEX)
        Integer pageIndex, @FormParam(PAGE_ROWS)
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            String jsonStr = srmSpmComprehensivePerformanceResultsServer.
                    findEvaluateDimensionData(jsonParam, pageIndex, pageRows);
            return jsonStr;
        }catch (UtilsException e){
            LOGGER.error("查询评价维度数据异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询评价维度数据异常!" + e.getMessage(), 0, null);
        }catch (Exception e) {
            LOGGER.error("查询评价维度数据异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询评价维度数据异常!" + e.getMessage(), 0, null);
        }
    }



    /**
     * 查询综合绩效信息数据
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
    @Path("findComprehensivePerformanceData")
    @POST
    public String findComprehensivePerformanceData(@FormParam(PARAMS)
        String params, @FormParam(PAGE_INDEX)
        Integer pageIndex, @FormParam(PAGE_ROWS)
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> list = srmSpmComprehensivePerformanceResultsServer.
                    findComprehensivePerformanceData(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(list);
        }catch (UtilsException e){
            LOGGER.error("查询综合绩效信息数据异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询综合绩效信息数据异常!" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询综合绩效信息数据异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询综合绩效信息数据异常!" + e, 0, null);
        }
    }



    /**
     * 综合绩效结果详情头
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
    @Path("findComprehensivePerformanceResultsHeader")
    @POST
    public String findComprehensivePerformanceResultsHeader(@FormParam(PARAMS)
       String params, @FormParam(PAGE_INDEX)
       Integer pageIndex, @FormParam(PAGE_ROWS)
       Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> list = srmSpmComprehensivePerformanceResultsServer.
                    findComprehensivePerformanceResultsHeader(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(list);
        }catch (UtilsException e){
            LOGGER.error("综合绩效结果详情头异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "综合绩效结果详情头异常!" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("综合绩效结果详情头异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "综合绩效结果详情头异常!" + e, 0, null);
        }
    }


    /**
     * 导出综合绩效结果查询数据
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
    @Path("findExportComprehensivePerformanceResults")
    @POST
    public String findExportComprehensivePerformanceResults(@FormParam(PARAMS)
       String params, @FormParam(PAGE_INDEX)
       Integer pageIndex, @FormParam(PAGE_ROWS)
       Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            if ("EX".equals(jsonParam.getString("varPlatformCode"))) { //是供应商查询
                //查询配置文件值，控制供应商是否能够查看到其他供应商的指标数据
                String flag = baseSaafProfilesServer.findProfileValueByProNum("SUPPLIER_REVIEW");
                if("N".equals(flag)){ //否
                    jsonParam.put("supplierId", jsonParam.getInteger("varSupplierId"));
                }
            }
            List<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> list = srmSpmComprehensivePerformanceResultsServer.
                    findExportComprehensivePerformanceResults(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功", list.size(), list);
        } catch (UtilsException e){
            LOGGER.error("查询异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询异常!" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询异常!" + e, 0, null);
        }
    }



    /**
     * 综合绩效结果列表查询
     * @param params 传查询所需的查询条件
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findComprehensivePerformanceResults")
    @POST
    public String findComprehensivePerformanceResults(@FormParam(PARAMS)
        String params, @FormParam(PAGE_INDEX)
        Integer pageIndex, @FormParam(PAGE_ROWS)
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            if ("EX".equals(jsonParam.getString("varPlatformCode"))) { //是供应商查询
                //查询配置文件值，控制供应商是否能够查看到其他供应商的指标数据
                String flag = baseSaafProfilesServer.findProfileValueByProNum("SUPPLIER_REVIEW");
                if("N".equals(flag)){ //否
                    jsonParam.put("supplierId", jsonParam.getInteger("varSupplierId"));
                }
            }
            Pagination<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> infoList = srmSpmComprehensivePerformanceResultsServer.
                    findComprehensivePerformanceResults(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        }catch (UtilsException e){
            LOGGER.error("综合绩效结果列表异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "综合绩效结果列表异常:" + e.getMessage(), 0, null);
        }catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("综合绩效结果列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "综合绩效结果列表失败!" + e, 0, null);
        }
    }


}
