package saaf.common.fmw.base.services;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafMessageLineEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafMessage;
import saaf.common.fmw.common.model.inter.server.ShortDescMessageServer;
import saaf.common.fmw.services.CommonAbstractServices;


@Component("baseSaafMessageSerices")
@Path("/baseSaafMessageSerices")
public class BaseSaafMessageSerices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSaafMessageSerices.class);

    @Autowired
    private ISaafMessage saafMessageServer;
    @Autowired
    private ShortDescMessageServer shortDescMessageServer;

    public BaseSaafMessageSerices() {
        // TODO Auto-generated constructor stub
        super();
    }

    @POST
    @Path("findSaafMessageHead")
    public String findSaafMessageHead(@FormParam(PARAMS)
        String params, @FormParam(PAGE_INDEX)
        Integer pageIndex, @FormParam(PAGE_ROWS)
        Integer pageRows) {
        LOGGER.info(params);
        Pagination resultStr = null;
        try {
            JSONObject paramJSON = this.parseObject(params);
            //            String userType = paramJSON.get("varPlatformCode").toString();
            //            //代理商查询，
            //            if (Constants.USER_TYPE_AGENT.equals(userType)) {  //代理商查询
            //                int customerId = paramJSON.getIntValue("varMemberId");
            //                paramJSON.put("customerId", customerId);
            //            } else if (Constants.USER_TYPE_AUX.equals(userType)) {
            //                paramJSON.put("instId", paramJSON.get("varInstIdData"));
            //            } else {
            //                return SaafToolUtils.getJsonResultStr("CMSBASE-ERR-006");
            //            }

            //ISaafMessage saafMessageServer = (ISaafMessage) getServerBean("saafMessageServer");

            resultStr = saafMessageServer.findSaafMessageHead(paramJSON, pageIndex, pageRows);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("消息查询异常" + e);
            return shortDescMessageServer.getJsonResultStr("QUERY-FAILURE", new Object[] { e.getMessage() });
        }
        return JSONObject.toJSONString(resultStr);
    }

    @POST
    @Path("findSaafMessageLine")
    public String findSaafMessageLine(@FormParam(PARAMS)
        String params, @FormParam(PAGE_INDEX)
        Integer pageIndex, @FormParam(PAGE_ROWS)
        Integer pageRows) {
        LOGGER.info(params);
        Pagination resultStr = null;
        try {
            JSONObject paramJSON = this.parseObject(params);
            //            String userType = paramJSON.get("varPlatformCode").toString();
            //            //代理商查询，
            //            if (Constants.USER_TYPE_AGENT.equals(userType)) {  //代理商查询
            //                int customerId = paramJSON.getIntValue("varMemberId");
            //                paramJSON.put("customerId", customerId);
            //            } else if (Constants.USER_TYPE_AUX.equals(userType)) {
            //                paramJSON.put("instId", paramJSON.get("varInstIdData"));
            //            } else {
            //                return SaafToolUtils.getJsonResultStr("CMSBASE-ERR-006");
            //            }

            //ISaafMessage saafMessageServer = (ISaafMessage) getServerBean("saafMessageServer");

            resultStr = saafMessageServer.findSaafMessageLine(paramJSON, pageIndex, pageRows);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("消息查询异常" + e);
            return shortDescMessageServer.getJsonResultStr("QUERY-FAILURE", new Object[] { e.getMessage() });
        }
        return JSONObject.toJSONString(resultStr);
    }

    @POST
    @Path("findMessageUserLOV")
    public String findMessageUserLOV(@FormParam(PARAMS)
        String params, @FormParam(PAGE_INDEX)
        Integer pageIndex, @FormParam(PAGE_ROWS)
        Integer pageRows) {
        LOGGER.info(params);
        Pagination resultStr = null;
        try {
            JSONObject paramJSON = this.parseObject(params);
            //            String userType = paramJSON.get("varPlatformCode").toString();
            //            //代理商查询，
            //            if (Constants.USER_TYPE_AGENT.equals(userType)) {  //代理商查询
            //                int customerId = paramJSON.getIntValue("varMemberId");
            //                paramJSON.put("customerId", customerId);
            //            } else if (Constants.USER_TYPE_AUX.equals(userType)) {
            //                paramJSON.put("instId", paramJSON.get("varInstIdData"));
            //            } else {
            //                return SaafToolUtils.getJsonResultStr("CMSBASE-ERR-006");
            //            }

            //ISaafMessage saafMessageServer = (ISaafMessage) getServerBean("saafMessageServer");

            resultStr = saafMessageServer.findMessageUserLOV(paramJSON, pageIndex, pageRows);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("消息查询异常" + e);
            return shortDescMessageServer.getJsonResultStr("QUERY-FAILURE", new Object[] { e.getMessage() });
        }
        return JSONObject.toJSONString(resultStr);
    }

    @POST
    @Path("findMessageGroupLOV")
    public String findMessageGroupLOV(@FormParam(PARAMS)
        String params, @FormParam(PAGE_INDEX)
        Integer pageIndex, @FormParam(PAGE_ROWS)
        Integer pageRows) {
        LOGGER.info(params);
        Pagination resultStr = null;
        try {
            JSONObject paramJSON = this.parseObject(params);
            //            String userType = paramJSON.get("varPlatformCode").toString();
            //            //代理商查询，
            //            if (Constants.USER_TYPE_AGENT.equals(userType)) {  //代理商查询
            //                int customerId = paramJSON.getIntValue("varMemberId");
            //                paramJSON.put("customerId", customerId);
            //            } else if (Constants.USER_TYPE_AUX.equals(userType)) {
            //                paramJSON.put("instId", paramJSON.get("varInstIdData"));
            //            } else {
            //                return SaafToolUtils.getJsonResultStr("CMSBASE-ERR-006");
            //            }

            //            ISaafMessage saafMessageServer = (ISaafMessage) getServerBean("saafMessageServer");

            resultStr = saafMessageServer.findMessageGroupLOV(paramJSON, pageIndex, pageRows);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("消息查询异常" + e);
            return shortDescMessageServer.getJsonResultStr("QUERY-FAILURE", new Object[] { e.getMessage() });
        }
        return JSONObject.toJSONString(resultStr);
    }

    @POST
    @Path("saveSaafMessage")
    public String saveSaafMessage(@FormParam(PARAMS)
        String params) {
        LOGGER.info(params);
        JSONObject resultStr = new JSONObject();
        try {
            JSONObject paramJSON = this.parseObject(params);
            int userId = paramJSON.getIntValue("varUserId");
            //            String userType = paramJSON.get("varPlatformCode").toString();
            //            //代理商查询，
            //            if (Constants.USER_TYPE_AGENT.equals(userType)) {  //代理商查询
            //                int customerId = paramJSON.getIntValue("varMemberId");
            //                paramJSON.put("customerId", customerId);
            //            } else if (Constants.USER_TYPE_AUX.equals(userType)) {
            //                paramJSON.put("instId", paramJSON.get("varInstIdData"));
            //            } else {
            //                return SaafToolUtils.getJsonResultStr("CMSBASE-ERR-006");
            //            }
            JSONArray paramDataList = paramJSON.getJSONArray("linesData");

            paramJSON.remove("linesData");
            System.out.println(paramDataList);
            System.out.println(paramJSON);
            //ISaafMessage saafMessageServer = (ISaafMessage) getServerBean("saafMessageServer");

            resultStr = saafMessageServer.saveSaafMessageHead(paramJSON, paramDataList, userId);

        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("消息保存异常" + e);
            return shortDescMessageServer.getJsonResultStr("SAVE-FAILURE", new Object[] { e.getMessage() });
        }
        return JSONObject.toJSONString(resultStr);
    }
    
    

    @POST
    @Path("updateSaafMessage")
    public String updateSaafMessage(@FormParam(PARAMS)
        String params) {
        LOGGER.info(params);
        JSONObject resultStr = new JSONObject();
        try {
            JSONObject paramJSON = this.parseObject(params);
            int userId = paramJSON.getIntValue("varUserId");
            //            String userType = paramJSON.get("varPlatformCode").toString();
            //            //代理商查询，
            //            if (Constants.USER_TYPE_AGENT.equals(userType)) {  //代理商查询
            //                int customerId = paramJSON.getIntValue("varMemberId");
            //                paramJSON.put("customerId", customerId);
            //            } else if (Constants.USER_TYPE_AUX.equals(userType)) {
            //                paramJSON.put("instId", paramJSON.get("varInstIdData"));
            //            } else {
            //                return SaafToolUtils.getJsonResultStr("CMSBASE-ERR-006");
            //            }
            int messageId = paramJSON.getIntValue("messageId");


            System.out.println(paramJSON);
            //            ISaafMessage saafMessageServer = (ISaafMessage) getServerBean("saafMessageServer");

            resultStr = saafMessageServer.updateSaafMessage(messageId, userId, "", "");

        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("消息保存异常" + e);
            return shortDescMessageServer.getJsonResultStr("SAVE-FAILURE", new Object[] { e.getMessage() });
        }
        return JSONObject.toJSONString(resultStr);
    }
    
    /**
     * 查询供应商LOV
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Path("findSupplierUserLov")
    @POST
    public String findSupplierUserLov(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SaafMessageLineEntity_HI_RO> supplierlov = saafMessageServer.findSupplierUserLov(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(supplierlov);
        }catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询供应商LOV失败！" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询供应商LOV失败!" + e, 0, null);
        }
    }
    
    /**
     * 推送通知
     * @param params
     * @return
     */
    @POST
    @Path("pushSaafMessage")
    public String pushSaafMessage(@FormParam("params")
        String params) {
        LOGGER.info(params);
        JSONObject resultStr = new JSONObject();
        try {
            JSONObject paramJSON = this.parseObject(params);
            int userId = paramJSON.getIntValue("varUserId");
            int messageId = paramJSON.getIntValue("messageId");
            System.out.println(paramJSON);
            resultStr = saafMessageServer.pushSaafMessage(messageId, userId, "", "");

        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("消息保存异常" + e);
            return shortDescMessageServer.getJsonResultStr("SAVE-FAILURE", new Object[] { e.getMessage() });
        }
        return JSONObject.toJSONString(resultStr);
    }
    
    /**
     * 删除消息行
     * @param params
     * @return
     */
    @Path("deleteSaafMessageLine")
    @POST
    public String deleteSaafMessageLine(@FormParam("params")
        String params) {
    	LOGGER.info("删除消息行，参数：" + params.toString());
    	try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = saafMessageServer.deleteSaafMessageLine(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        }catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("--->>删除消息行失败！参数：" + params + ",异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除消息行失败!", 0, null);
        }
    }
    
    /**
     * 保存消息
     * @param params
     * @return
     */
    @Path("saveSaafMessageHeadLine")
    @POST
    public String saveSaafMessageHeadLine(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = saafMessageServer.saveSaafMessageHeadLine(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (Exception e) {
            LOGGER.error("保存失败！" + e,e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存失败!" + e, 0, null);
        }
    }
    
    /**
     * 删除通知
     * @param params
     * @return
     */
    @Path("deleteSaafMessageHeader")
    @POST
    public String deleteSaafMessageHeader(@FormParam("params")
        String params) {
    	LOGGER.info("删除通知，参数：" + params.toString());
    	try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = saafMessageServer.deleteSaafMessageHeader(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        }catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("--->>删除通知失败！参数：" + params + ",异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除通知失败!", 0, null);
        }
    }
}
