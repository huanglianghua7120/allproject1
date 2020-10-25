package saaf.common.fmw.okc.services;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.okc.model.inter.ISrmOkcSimpContracts;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：SrmOkcSimpContractsService.java
 * Description：简版合同查询控制类
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date     @Author(Updated By)     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019/6/18      欧阳岛          创建
 * <p>
 * ==============================================================================
 */
@Component("srmOkcSimpContractsService")
@Path("/srmOkcSimpContractsService")
public class SrmOkcSimpContractsService extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmOkcSimpContractsService.class);

    @Autowired
    private ISrmOkcSimpContracts srmOkcSimpContractsServer;

    /**
     * Description：查询供应商已签订合同列表
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/18      欧阳岛          创建
     * ==============================================================================
     */
    @Path("findSrmOkcSimpContractsList")
    @POST
    public String findSrmOkcSimpContractsList(@FormParam("params")
                                                      String params, @FormParam("pageIndex")
                                                      Integer pageIndex, @FormParam("pageRows")
                                                      Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info("------jsonParam-----" + jsonParam.toString());
            Pagination list = srmOkcSimpContractsServer.findSrmOkcSimpContractsList(jsonParam, this.getSupplierId(), pageIndex, pageRows);
            return JSON.toJSONString(list);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (UtilsException e) {
            LOGGER.error("查询简版合同列表异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询简版合同列表异常：" + e.getMessage(), 0, null);
        }catch (Exception e) {
            LOGGER.error("查询简版合同列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询简版合同列表失败!" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：查询供应商“发起供方确认”及已签订合同列表
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/19      欧阳岛          创建
     * ==============================================================================
     */
    @Path("findSrmOkcEnhanceContractsList")
    @POST
    public String findSrmOkcEnhanceContractsList(@FormParam("params")
                                                         String params, @FormParam("pageIndex")
                                                         Integer pageIndex, @FormParam("pageRows")
                                                         Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info("------jsonParam-----" + jsonParam.toString());
            Pagination list = srmOkcSimpContractsServer.findSrmOkcEnhanceContractsList(jsonParam, this.getSupplierId(), pageIndex, pageRows);
            return JSON.toJSONString(list);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e) {
            LOGGER.error("查询加强版合同列表异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询加强版合同列表异常：" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询加强版合同列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询加强版合同列表失败!" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：确认合同
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/24      欧阳岛          创建
     * ==============================================================================
     */
    @Path("doConfirmContract")
    @POST
    public String doConfirmContract(@FormParam("contractId") Integer contractId) {
        try {
            String flag = srmOkcSimpContractsServer.doConfirmContract(contractId, this.getSupplierId(), this.getSessionUserId());
            if ("S".equals(flag)) {
                return CommonAbstractServices.convertResultJSONObj("S", "确认合同成功！", 0, null);
            } else {
                return CommonAbstractServices.convertResultJSONObj("E", flag, 0, null);
            }
        } catch (Exception e) {
            LOGGER.error("确认合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "确认合同失败！", 0, null);
        }
    }

    /**
     * Description：拒绝合同
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/24      欧阳岛          创建
     * ==============================================================================
     */
    @Path("doRejectContract")
    @POST
    public String doRejectContract(@FormParam("contractId") Integer contractId) {
        try {
            String flag = srmOkcSimpContractsServer.doRejectContract(contractId, this.getSupplierId(), this.getSessionUserId());
            if ("S".equals(flag)) {
                return CommonAbstractServices.convertResultJSONObj("S", "拒绝合同成功！", 0, null);
            } else {
                return CommonAbstractServices.convertResultJSONObj("E", flag, 0, null);
            }
        } catch (Exception e) {
            LOGGER.error("拒绝合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "拒绝合同失败！", 0, null);
        }
    }

}
