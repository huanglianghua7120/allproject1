package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafProfiles;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorCalculationResultsEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmIndicatorCalculationResults;

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
 * Description：绩效指标计算结果查询类
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     hgq             创建
 * ===========================================================================
 */

@Path("/srmSpmIndicatorCalculationResultsService")
@Component
public class SrmSpmIndicatorCalculationResultsService extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmIndicatorCalculationResultsService.class);

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    public SrmSpmIndicatorCalculationResultsService() {
        super();
    }

    @Autowired
    private ISrmSpmIndicatorCalculationResults srmSpmIndicatorCalculationResultsServer;

    @Autowired
    private ISaafProfiles baseSaafProfilesServer;



    /**
     * 导出绩效指标计算结果数据
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
    @Path("findExportIndicatorCalculationResults")
    @POST
    public String findExportIndicatorCalculationResults(@FormParam(PARAMS)
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
            List<SrmSpmIndicatorCalculationResultsEntity_HI_RO> list = srmSpmIndicatorCalculationResultsServer.findExportIndicatorCalculationResults(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功", list.size(), list);
        }catch (UtilsException e){
            LOGGER.error("查询异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询异常:" + e.getMessage(), 0, null);
        }  catch (Exception e) {
            LOGGER.error("查询异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询异常!" + e, 0, null);
        }
    }




    /**
     * 绩效指标计算结果列表查询
     *
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
    @Path("findIndicatorCalculationResults")
    @POST
    public String findIndicatorCalculationResults(@FormParam(PARAMS)
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
            Pagination<SrmSpmIndicatorCalculationResultsEntity_HI_RO> infoList = srmSpmIndicatorCalculationResultsServer.findIndicatorCalculationResults(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        }catch (UtilsException e){
            LOGGER.error("绩效指标计算结果列表异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "绩效指标计算结果列表异常:" + e.getMessage(), 0, null);
        }catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("绩效指标计算结果列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "绩效指标计算结果列表失败!" + e, 0, null);
        }
    }



    /**
     *根据评价维度控制评价指标
     *
     * @param params
     * @return
     * @throws Exception
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("selectChangeEvaluateDimension")
    public String selectChangeEvaluateDimension(@FormParam(PARAMS)String params) throws Exception{
        LOGGER.info(params);
        JSONObject jsonParam = JSON.parseObject(params);
        List<SaafLookupValuesEntity_HI_RO> list=srmSpmIndicatorCalculationResultsServer.selectChangeEvaluateDimension(jsonParam);
        jsonParam.put("LookupValues", list);
        jsonParam.put("status", "S");
        jsonParam.put("msg", "查询成功");
        return jsonParam.toJSONString();
    }

}
