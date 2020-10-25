package saaf.common.fmw.schedule.services;


import com.alibaba.fastjson.JSONObject;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import saaf.common.fmw.schedule.model.inter.IJobs;
import saaf.common.fmw.schedule.utils.SaafToolUtils;
import saaf.common.fmw.services.CommonAbstractServices;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：并发程序
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             modify
 * ==============================================================================
 */
@Path("/jobServices")
public class JobServices extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobServices.class);

    public JobServices() {
        super();
    }
    /**
     * Description：查询并发程序
     * @param params 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             modify
     * ==============================================================================
     */
    @POST
    @Path("findJobs")
    public String findJobs(@FormParam("params")
        String params, @FormParam("pageIndex") @DefaultValue("1")
        Integer pageIndex, @FormParam("pageRows") @DefaultValue("10")
        Integer pageRows) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            IJobs jobsServer = (IJobs)SaafToolUtils.context.getBean("jobsServer");
            return jobsServer.findJobs(jsonParam, pageIndex, pageRows);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
    /**
     * Description：删除并发程序
     * @param params 参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             modify
     * ==============================================================================
     */
    @POST
    @Path("deleteJob")
    public String deleteJob(@FormParam("params")
    String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            IJobs jobsServer = (IJobs)SaafToolUtils.context.getBean("jobsServer");
            return jobsServer.deleteJob(jsonParam);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
    /**
     * Description：保存并发程序
     * @param params 参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             modify
     * ==============================================================================
     */
    @POST
    @Path("saveJob")
    public String saveJob(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            //            jsonParam.put("varUserId", this.getUserSessionBean().getUserId());
            IJobs jobsServer = (IJobs)SaafToolUtils.context.getBean("jobsServer");
            return jobsServer.saveJobInfo(jsonParam);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
    /**
     * Description：修改并发程序
     * @param params 参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             modify
     * ==============================================================================
     */
    @POST
    @Path("updateJob")
    public String updateJob(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            IJobs jobsServer = (IJobs)SaafToolUtils.context.getBean("jobsServer");
            return jobsServer.updateJob(jsonParam);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
}
