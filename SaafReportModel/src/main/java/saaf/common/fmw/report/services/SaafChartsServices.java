package saaf.common.fmw.report.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.model.inter.server.ShortDescMessageServer;
import saaf.common.fmw.report.model.inter.ISaafCharts;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Component
@Path("/chartsServices")
public class SaafChartsServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafChartsServices.class);
    @Autowired
    private ISaafCharts saafChartsServer;
    @Autowired
    private ShortDescMessageServer shortDescMessageServer;

    @POST
    @Path("findList")
    public String findList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject parameters = this.parseObject(params);
            return saafChartsServer.findSqlList(parameters, pageIndex, pageRows);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询动态报表配置头异常!" + e);
            return shortDescMessageServer.getJsonResultStr("QUERY-FAILURE", new Object[] { e.getMessage() });
        }
    }

    @POST
    @Path("save")
    public String save(@FormParam("params")
        String params) {
        LOGGER.info(params);
        if (!"Y".equals(getIsAdmin())) {
            LOGGER.info("无权限用户尝试配置图表！！！");
            return shortDescMessageServer.getJsonResultStr("SAVE-FAILURE", new Object[] { "您没有权限" });
        }
        try {
            JSONObject parameters = this.parseObject(params);
            JSONObject resultStr = saafChartsServer.save(parameters);
            return JSON.toJSONString(resultStr);
        } catch (Exception e) {
            final Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                LOGGER.error("保存失败：图表编码，违反唯一索引" + e);
                return shortDescMessageServer.getJsonResultStr("UNIQUE-CONSTRAINT", new Object[] { "图表编码" });
            }
            LOGGER.error("保存异常!" + e);
            return shortDescMessageServer.getJsonResultStr("SAVE-FAILURE", new Object[] { e.getMessage() });
        }
    }

    @POST
    @Path("delete")
    public String delete(@FormParam("params")
        String params) {
        LOGGER.info(params);
        if (!"Y".equals(getIsAdmin())) {
            LOGGER.info("无权限用户尝试删除图表！！！");
            return shortDescMessageServer.getJsonResultStr("SAVE-FAILURE", new Object[] { "您没有权限" });
        }
        try {
            JSONObject parameters = this.parseObject(params);
            JSONObject resultStr = saafChartsServer.delete(parameters);
            return resultStr.toJSONString();
        } catch (Exception e) {
            LOGGER.error("删除异常!" + e);
            return shortDescMessageServer.getJsonResultStr("DELETE-FAILURE", new Object[] { e.getMessage() });
        }
    }


}
