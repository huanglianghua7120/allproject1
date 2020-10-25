package saaf.common.fmw.rule.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.rule.constant.Constant;
import saaf.common.fmw.rule.model.entities.RuleDimEntity_HI;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


/**
 * Created by Admin on 2017/6/28.
 */
@Component("constantServices")
@Path("/constantServices")
public class ConstantServices extends CommonAbstractServices{
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleBusinessLineService.class);

    /**
     * 业务线匹配类型
     * @return
     */
    @POST
    @Path("getBusinessLineMapptype")
    @Produces("application/json")
    public String getBusinessLineMapptype() {
        try {
            return convertResultJSONObj("S", "success", 0, Constant.BUSINESSLINE_MAPPTYPE).toString();
        } catch (Exception e) {
            LOGGER.error("", e);
            return convertResultJSONObj("E",e.getMessage(),0,null).toString();
        }
    }


    /**
     * 业务线匹配类型
     * @return
     */
    @POST
    @Path("getruleViewType")
    @Produces("application/json")
    public String getruleViewType() {
        try {
            return convertResultJSONObj("S", "success", 0, Constant.RULEVIEW_MAPPTYPE).toString();
        } catch (Exception e) {
            LOGGER.error("", e);
            return convertResultJSONObj("E",e.getMessage(),0,null).toString();
        }

    }

    /**
     *  维度的值来源
     * @return
     */
    @POST
    @Path("getDimValueFrom")
    @Produces("application/json")
    public String getDimValueFrom() {
        try {
            return convertResultJSONObj("S", "success", 0, Constant.DIMVALUEFROM_MAPPTYPE).toString();
        } catch (Exception e) {
            LOGGER.error("", e);
            return convertResultJSONObj("E",e.getMessage(),0,null).toString();
        }

    }


    @POST
    @Path("getOpreateType")
    @Produces("application/json")
    public String getOpreateType() {
        RuleDimEntity_HI temp = new RuleDimEntity_HI();
        return convertResultJSONObj("S", "success", 0, temp.getOperatorBeans()).toString();
    }

    /**
     *  数据类型
     * @return
     */
    @POST
    @Path("getDimDataType")
    @Produces("application/json")
    public String getDimDataType() {
        try {
            return convertResultJSONObj("S", "success", 0, Constant.DIMDATA_MAPPTYPE).toString();
        } catch (Exception e) {
            LOGGER.error("", e);
            return convertResultJSONObj("E",e.getMessage(),0,null).toString();
        }

    }


    /**
     * @return
     */
    @POST
    @Path("getTargetType")
    @Produces("application/json")
    public String getTargetType() {
        try {
            return convertResultJSONObj("S", "success", 0, Constant.TARGET_MAPPTYPE).toString();
        } catch (Exception e) {
            LOGGER.error("", e);
            return convertResultJSONObj("E",e.getMessage(),0,null).toString();
        }
    }
}
