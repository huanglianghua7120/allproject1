package saaf.common.fmw.okc.services;


import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.exception.NotLoginException;

import saaf.common.fmw.okc.model.entities.readonly.SrmOkcFreightExpensesEntity_HI_RO;
import saaf.common.fmw.okc.model.inter.ISrmOkcFreightExpenses;
import saaf.common.fmw.services.CommonAbstractServices;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：SrmOkcFreightExpensesService.java
 * Description：运输维护控制类
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date           @Author(Updated By)     Description
 * -------    -----------    -----------             ---------------
 * V1.0       2019/6/4       陈庆超                   创建
 * ==============================================================================
 */
@Component("srmOkcFreightExpensesService")
@Path("/srmOkcFreightExpensesService")
public class SrmOkcFreightExpensesService extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmOkcFreightExpensesService.class);

    @Autowired
    private ISrmOkcFreightExpenses srmOkcFreightExpensesServer;

    /**
     * 保存运价单信息
     *
     * @param params
     * @return
     */
    @Path("saveSrmOkcFreightExpenses")
    @POST
    public String saveSrmOkcFreightExpenses(@FormParam("params") String params) {
        LOGGER.info("saveSrmOkcFreightExpenses params:" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONArray jsonArray = jsonParam.getJSONArray("data");
            String flag = null;
            int s = jsonArray.size();
            for (int i = 0; i < s; i++) {
                JSONObject j = (JSONObject) jsonArray.get(i);
                j.put("operatorUserId", jsonParam.getInteger("operatorUserId"));
                flag = srmOkcFreightExpensesServer.saveSrmOkcFreightExpenses(j);
                if ("D".equals(flag)) {
                    return CommonAbstractServices.convertResultJSONObj("E", "承运商：" + j.getString("carrierName") + "行存在重复！", 0, null);
                }
            }
            return CommonAbstractServices.convertResultJSONObj("S", "保存成功！", 1, jsonParam);
        } catch (NotLoginException e) {
            LOGGER.error("保存运价单信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "保存运价单信息失败！", 0, null);
        }catch (UtilsException e) {
            LOGGER.error("保存运价单信息异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "保存运价单信息异常：" + e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("保存运价单信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存运价单信息失败！"+ e.getMessage(), 0, null);
        }
    }

    /**
     * 查询运价单列表
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Path("findSrmOkcFreightExpensesList")
    @POST
    public String findSrmOkcFreightExpensesList(@FormParam("params")
                                                        String params, @FormParam("pageIndex")
                                                        Integer pageIndex, @FormParam("pageRows")
                                                        Integer pageRows) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination list = srmOkcFreightExpensesServer.findSrmOkcFreightExpensesList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(list);
        } catch (NotLoginException e) {
            LOGGER.error("查询运价单列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "查询运价单列表失败！", 0, null);
        } catch (UtilsException e) {
            LOGGER.error("查询运价单列表异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询运价单列表异常：" + e.getMessage(), 0, null);
        }catch (Exception e) {
            LOGGER.error("查询运价单列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询运价单列表失败！"+ e.getMessage(), 0, null);
        }
    }

    /**
     * 查询运价单
     *
     * @param params
     * @return
     */
    @Path("findSrmOkcFreightExpenses")
    @POST
    public String findSrmOkcFreightExpenses(@FormParam("params") String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            //查询用户账号&员工信息
            if (null != jsonParam.get("userId") && !"".equals(jsonParam.get("userId"))) {
                SrmOkcFreightExpensesEntity_HI_RO obj = srmOkcFreightExpensesServer.findSrmOkcFreightExpensesById(jsonParam);
                return CommonAbstractServices.convertResultJSONObj("S", "查询运价单信息成功!", 1, obj);
            } else {
                return CommonAbstractServices.convertResultJSONObj("E", "查询运价单信息参数为空!", 0, null);
            }
        } catch (NotLoginException e) {
            LOGGER.error("查询运价单信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "查询运价单信息失败！", 0, null);
        }catch (UtilsException e) {
            LOGGER.error("查询运价单信息异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询运价单信息异常：" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询运价单信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询运价单信息失败！"+ e.getMessage(), 0, null);
        }
    }

    /**
     * 删除运价单
     *
     * @param params
     * @return
     */
    @Path("deleteFreightExpense")
    @POST
    public String deleteFreightExpense(@FormParam("params") String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            Integer freightExpenseId = jsonParam.getInteger("freightExpenseId");
            if (freightExpenseId != null) {
                srmOkcFreightExpensesServer.deleteFreightExpense(freightExpenseId);
            }
            return CommonAbstractServices.convertResultJSONObj("S", "删除运价单成功！", 0, null);
        } catch (UtilsException e) {
            LOGGER.error("删除运价单异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "删除运价单异常：" + e.getMessage(), 0, null);
        }catch (Exception e) {
            LOGGER.error("删除运价单异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除运价单失败！"+ e.getMessage(), 0, null);
        }
    }
}