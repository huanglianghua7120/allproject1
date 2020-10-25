package saaf.common.fmw.intf.services;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.intf.model.inter.ISrmShortMaterialInfo;
import saaf.common.fmw.po.model.entities.readonly.SrmShortMaterialInfoEntity_HI_RO;
import saaf.common.fmw.services.CommonAbstractServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/srmShortMaterialInfoServices")
@Component
public class SrmShortMaterialInfoServices extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmShortMaterialInfoServices.class);
	
	@Autowired
	private ISrmShortMaterialInfo srmShortMaterialInfoServer;

	/**   
     * 缺料查询
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */	
    @Path("findShortMaterielList")
    @POST
    public String findShortMaterielList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info("------jsonParam------" + jsonParam.toString());
            Pagination<SrmShortMaterialInfoEntity_HI_RO> infoList = srmShortMaterialInfoServer.findShortMaterielList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        }catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("缺料查询异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "缺料查询失败!" + e, 0, null);
        }
    }
    
    /**   
     * 工单缺料明细表
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */	
    @Path("findOnlyShortMaterielList")
    @POST
    public String findOnlyShortMaterielList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info("------jsonParam------" + jsonParam.toString());
            Pagination<SrmShortMaterialInfoEntity_HI_RO> infoList = srmShortMaterialInfoServer.findOnlyShortMaterielList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        }catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("工单齐套检查异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "工单齐套检查失败!" + e, 0, null);
        }
    }
    
    
	/**   
     * 缺料数据源头：U9备料表查询
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */	
    @Path("findU9ReadyMaterielList")
    @POST
    public String findU9ReadyMaterielList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info("------jsonParam------" + jsonParam.toString());
            Pagination<SrmShortMaterialInfoEntity_HI_RO> infoList = srmShortMaterialInfoServer.findU9ReadyMaterielList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        }catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("备料表查询异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "备料表查询失败!" + e, 0, null);
        }
    }
    
    
    /**   
     * 缺料预警
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */	
    @Path("findShortMaterialWarm")
    @POST
    public String findShortMaterialWarm(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info("------jsonParam------" + jsonParam.toString());
            Pagination<SrmShortMaterialInfoEntity_HI_RO> infoList = srmShortMaterialInfoServer.findShortMaterialWarm(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        }catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("缺料预警查询失败：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "缺料预警查询失败!" + e, 0, null);
        }
    }
    
    
    /**
     * 根据缺料预警查到汇总的订单号
     * @param params
     * @return
     */
    @POST
    @Path("findOrders")
    public String findOrders(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            List list  = srmShortMaterialInfoServer.findOrders(jsonParams);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功！", list.size(), list);
        } catch (Exception e) {
            LOGGER.error("查询失败" + e,e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e.getMessage(), 0, null);
        }
    }
	
    /**
     * 根据缺料预警查最早的送货单
     * @param params
     * @return
     */
    @POST
    @Path("findDeliveryOrder")
    public String findDeliveryOrder(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            List list  = srmShortMaterialInfoServer.findDeliveryOrder(jsonParams);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功！", list.size(), list);
        } catch (Exception e) {
            LOGGER.error("查询失败" + e,e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e.getMessage(), 0, null);
        }
    }
    
    /**
     * 根据缺料预警查最早的送货通知单
     * @param params
     * @return
     */
    @POST
    @Path("findNoticeOrder")
    public String findNoticeOrder(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            List list  = srmShortMaterialInfoServer.findNoticeOrder(jsonParams);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功！", list.size(), list);
        } catch (Exception e) {
            LOGGER.error("查询失败" + e,e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e.getMessage(), 0, null);
        }
    }
    
    /**
     * 送货通知超量查询
     * @param params
     * @return
     */
    @POST
    @Path("findNoticeOrderSuperQty")
    public String findNoticeOrderSuperQty(@FormParam("params")
    String params, @FormParam("pageIndex")
    Integer pageIndex, @FormParam("pageRows")
    Integer pageRows) {
        try {
        	JSONObject jsonParam = this.parseObject(params);
            LOGGER.info("------jsonParam------" + jsonParam.toString());
            Pagination<SrmShortMaterialInfoEntity_HI_RO> infoList = srmShortMaterialInfoServer.findNoticeOrderSuperQty(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("查询失败" + e,e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e.getMessage(), 0, null);
        }
    }
	

}
