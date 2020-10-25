package saaf.common.fmw.schedule.services;


import com.alibaba.fastjson.JSONObject;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import saaf.common.fmw.schedule.model.inter.IJobParameters;
import saaf.common.fmw.schedule.model.inter.server.JobParametersServer;
import saaf.common.fmw.schedule.servlets.CommonAbstractServices;
import saaf.common.fmw.schedule.utils.SaafToolUtils;


@Path("/jobParameterServices")
public class JobParameterServices extends CommonAbstractServices {

    private static final Logger logger = LoggerFactory.getLogger(JobParameterServices.class);

    public JobParameterServices() {
        super();
    }

    @POST
    @Path("deleteJobParameter")
    public String deleteJobParameter(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            IJobParameters jobParameterServer = (IJobParameters)SaafToolUtils.context.getBean("jobParametersServer");
            return jobParameterServer.deleteJobParameter(jsonParam);
        } catch (Exception e) {
            logger.error("deleteJobParameter 失败，" + e.toString() + "，Exception：" + e);
            logger.error("未知错误:{}", e);
            return convertResultJSONObj("E", "删除Job参数失败", 0, null);
        }
    }

    @POST
    @Path("findJobParameters")
    public String findJobParameters(@FormParam("params")
        String params, @FormParam("pageIndex") @DefaultValue("1")
        Integer pageIndex, @FormParam("pageRows") @DefaultValue("10")
        Integer pageRows) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            IJobParameters jobParameterServer = (IJobParameters)SaafToolUtils.context.getBean("jobParametersServer");
            return jobParameterServer.findJobParameters(jsonParam, pageIndex, pageRows);
        } catch (Exception e) {
            logger.error("findJobParameters 失败，" + e.toString() + "，Exception：" + e);
            logger.error("未知错误:{}", e);
            return convertResultJSONObj("E", "查询Job参数失败", 0, null);
        }
    }

    @POST
    @Path("saveJobParameter")
    public String saveJobParameter(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            //            jsonParam.put("varUserId", this.getUserSessionBean().getUserId());
//            jsonParam.put("varUserId", -9999);
           IJobParameters jobParameterServer = (IJobParameters)SaafToolUtils.context.getBean("jobParametersServer");
            return jobParameterServer.saveParameterInfo(jsonParam);
        } catch (Exception e) {
            logger.error("saveJobParameter 失败，" + e.toString() + "，Exception：" + e);
            logger.error("未知错误:{}", e);
            return convertResultJSONObj("E", "保存Job参数失败", 0, null);
        }
    }

    @POST
    @Path("updateJobParameter")
    public String updateJobParameter(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            //            jsonParam.put("varUserId", this.getUserSessionBean().getUserId());
//            jsonParam.put("varUserId", -9999);
           IJobParameters jobParameterServer = (IJobParameters)SaafToolUtils.context.getBean("jobParametersServer");
            return jobParameterServer.updateJobParameter(jsonParam);
        } catch (Exception e) {
            logger.error("updateJobParameter 失败，" + e.toString() + "，Exception：" + e);
            logger.error("未知错误:{}", e);
            return convertResultJSONObj("E", "更新Job参数失败", 0, null);
        }
    }
}

