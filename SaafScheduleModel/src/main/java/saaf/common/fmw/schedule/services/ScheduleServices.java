package saaf.common.fmw.schedule.services;


import com.alibaba.fastjson.JSONObject;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import saaf.common.fmw.schedule.model.inter.ISchedules;
import saaf.common.fmw.schedule.model.inter.server.SchedulesServer;
import saaf.common.fmw.schedule.servlets.CommonAbstractServices;
import saaf.common.fmw.schedule.utils.SaafToolUtils;


@Path("/scheduleServices")
public class ScheduleServices extends CommonAbstractServices {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleServices.class);
    


    public ScheduleServices() {
        super();
    }

    @POST
    @Path("cancelRequest")
    public String cancelRequest(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            ISchedules schedulesServer = (ISchedules)SaafToolUtils.context.getBean("schedulesServer");
            return schedulesServer.cancelRequest(jsonParam);
        } catch (Exception e) {
            logger.error("cancelRequest 失败，" + e.toString() + "，Exception：" + e);
            logger.error("未知错误:{}", e);
            return convertResultJSONObj("E", "取消失败", 0, null);
        }
    }

    @POST
    @Path("resumeRequest")
    public String resumeRequest(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            ISchedules schedulesServer = (ISchedules)SaafToolUtils.context.getBean("schedulesServer");
            return schedulesServer.resumeRequest(jsonParam);
        } catch (Exception e) {
            logger.error("resumeRequest 失败，" + e.toString() + "，Exception：" + e);
            logger.error("未知错误:{}", e);
            return convertResultJSONObj("E", "启动（resume）失败", 0, null);
        }
    }

    @POST
    @Path("deleteSchedule")
    public String deleteSchedule(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
           ISchedules schedulesServer = (ISchedules)SaafToolUtils.context.getBean("schedulesServer");
            return schedulesServer.deleteSchedule(jsonParam);
        } catch (Exception e) {
            logger.error("deleteSchedule 失败，" + e.toString() + "，Exception：" + e);
            logger.error("未知错误:{}", e);
            return convertResultJSONObj("E", "删除失败", 0, null);
        }
    }

    @POST
    @Path("findRequestLog")
    public String findRequestLog(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            ISchedules schedulesServer = (ISchedules)SaafToolUtils.context.getBean("schedulesServer");
            return schedulesServer.getRequestLog(jsonParam);
        } catch (Exception e) {
            logger.error("findRequestLog 失败，" + e.toString() + "，Exception：" + e);
            logger.error("未知错误:{}", e);
            return convertResultJSONObj("E", "查询请求日志失败", 0, null);
        }
    }

    @POST
    @Path("findRequests")
    public String findRequests(@FormParam("params") String params, @FormParam("pageIndex") @DefaultValue("1") Integer pageIndex, @FormParam("pageRows") @DefaultValue("10") Integer pageRows) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            ISchedules schedulesServer = (ISchedules)SaafToolUtils.context.getBean("schedulesServer");
            return schedulesServer.findRequests(jsonParam, pageIndex, pageRows);
        } catch (Exception e) {
            logger.error("findRequests 失败，" + e.toString() + "，Exception：" + e);
            logger.error("未知错误:{}", e);
            return convertResultJSONObj("E", "查询请求称失败", 0, null);
        }
    }

    @POST
    @Path("launchRequest")
    public String launchRequest(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            ISchedules schedulesServer = (ISchedules)SaafToolUtils.context.getBean("schedulesServer");
            return schedulesServer.launchRequest(jsonParam);
        } catch (Exception e) {
            logger.error("launchRequest 失败，" + e.toString() + "，Exception：" + e);
            logger.error("未知错误:{}", e);
            return convertResultJSONObj("E", "启动失败", 0, null);
        }
    }

    @POST
    @Path("pauseRequest")
    public String pauseRequest(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            ISchedules schedulesServer = (ISchedules)SaafToolUtils.context.getBean("schedulesServer");
            return schedulesServer.pauseRequest(jsonParam);
        } catch (Exception e) {
            logger.error("pauseRequest 失败，" + e.toString() + "，Exception：" + e);
            logger.error("未知错误:{}", e);
            return convertResultJSONObj("E", "暂停失败", 0, null);
        }
    }

    @POST
    @Path("saveRequest")
    public String saveRequest(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            //            jsonParam.put("varUserId", this.getUserSessionBean().getUserId());
//            jsonParam.put("varUserId", -9999);
           ISchedules schedulesServer = (ISchedules)SaafToolUtils.context.getBean("schedulesServer");
            return schedulesServer.saveRequest(jsonParam);
        } catch (Exception e) {
            logger.error("saveRequest 失败，" + e.toString() + "，Exception：" + e);
            logger.error("未知错误:{}", e);
            return convertResultJSONObj("E", "提交请求失败", 0, null);
        }
    }
}
