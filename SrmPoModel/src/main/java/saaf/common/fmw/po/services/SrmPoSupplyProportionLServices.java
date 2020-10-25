package saaf.common.fmw.po.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.po.model.entities.readonly.SrmPoSupplyProportionLEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoSupplyProportionL;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供货比例行表
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-20     hgq             modify
 * ==============================================================================
 */
@Component("srmPoSupplyProportionLServices")
@Path("/srmSupplyProportionLServlet")
public class SrmPoSupplyProportionLServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoSupplyProportionLServices.class);

    public SrmPoSupplyProportionLServices() {
        super();
    }


    @Autowired
    private ISrmPoSupplyProportionL srmPoSupplyProportionL;


    /**   
     * Description：查询供货比例行表
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
    @Path("findSupplyProportionLList")
    @POST
    public String findSupplyProportionL(@FormParam("params")
    String params, @FormParam("pageIndex")
    Integer pageIndex, @FormParam("pageRows")
    Integer pageRows) {
        try {
             JSONObject jsonParam = this.parseObject(params);
             Pagination<SrmPoSupplyProportionLEntity_HI_RO> usersList = srmPoSupplyProportionL.findSupplyProportionL(jsonParam,pageIndex,pageRows);
            return JSON.toJSONString(usersList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
    
    /**   
     * Description：保存供货列表详情
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    @Path("saveSupplyProportionL")
    @POST
    public String saveSupplyProportion(@FormParam("params")String params) {
        try {
             JSONObject jsonParam = this.parseObject(params);
             JSONObject json =srmPoSupplyProportionL.saveSupplyProportionL(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "保存供货列表详情成功!", 1, json.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,e.getMessage(),0,null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
}
