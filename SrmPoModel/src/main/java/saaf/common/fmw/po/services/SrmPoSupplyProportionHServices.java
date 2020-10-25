package saaf.common.fmw.po.services;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.po.model.entities.readonly.SrmPoSupplyProportionHEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoSupplyProportionH;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供货比例头表
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-20     hgq             modify
 * ==============================================================================
 */
@Component("srmPoSupplyProportionHServices")
@Path("/srmSupplyProportionHServlet")
public class SrmPoSupplyProportionHServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoSupplyProportionHServices.class);

    public SrmPoSupplyProportionHServices() {
        super();
    }

    @Autowired
    private ISrmPoSupplyProportionH srmPoSuppluProportionHServer;

    /**
     * Description：查询供货列表
     *
     * @param params 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    @Path("findSupplyProportionHList")
    @POST
    public String findSupplyProportion(@FormParam("params")
                                               String params, @FormParam("pageIndex")
                                               Integer pageIndex, @FormParam("pageRows")
                                               Integer pageRows) {
        try {

            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmPoSupplyProportionHEntity_HI_RO> usersList = srmPoSuppluProportionHServer.findSupplyProportion(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(usersList);
        }  catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：导出供货比例
     *
     * @param params 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    @Path("findSupplyExport")
    @POST
    public String findSupplyExport(@FormParam("params")
                                          String params, @FormParam("pageIndex")
                                          Integer pageIndex, @FormParam("pageRows")
                                          Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            List<SrmPoSupplyProportionHEntity_HI_RO> supplyList = srmPoSuppluProportionHServer.findSupplyExport(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功", supplyList.size(), supplyList);
        }  catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：保存供货列表
     *
     * @param params 参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    @Path("saveSupplyProportionH")
    @POST
    public String saveSupplyProportion(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject json = srmPoSuppluProportionHServer.saveSupplyProportionH(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "保存供信息成功!", 1, json.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：批量导入供货比例
     * @param params 参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    @POST
    @Path("batchImportSupply")
    public String batchImportSupply(@FormParam(PARAMS) String params){
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject object = srmPoSuppluProportionHServer.saveList(jsonParams);
            return JSONObject.toJSONString(object);
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

}
