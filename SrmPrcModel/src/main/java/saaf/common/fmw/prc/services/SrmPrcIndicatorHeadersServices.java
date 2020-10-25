package saaf.common.fmw.prc.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.prc.model.entities.readonly.SrmPrcIndicatorHeadersEntity_HI_RO;
import saaf.common.fmw.prc.model.inter.IPrcIndicatorHeaders;
import saaf.common.fmw.services.CommonAbstractServices;

@Path("/srmPrcIndicatorHeadersServices")
@Component
public class SrmPrcIndicatorHeadersServices extends CommonAbstractServices{
	
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPrcIndicatorHeadersServices.class);
	
	@Autowired
	private IPrcIndicatorHeaders srmPrcIndicatorHeadersServer;
	public SrmPrcIndicatorHeadersServices(){
		super();
	}
	
	/**   
     * 查询材质指标列表
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Path("findIndicatorHeadersList")
    @POST
    public String findIndicatorHeadersList(@FormParam("params") String params, @FormParam("pageIndex")
            Integer pageIndex, @FormParam("pageRows") Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmPrcIndicatorHeadersEntity_HI_RO> indicatorList = srmPrcIndicatorHeadersServer.findIndicatorHeadersList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(indicatorList);
        }catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询材质指标异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询查询材质指标失败!" + e.getMessage(), 0, null);
        }
    }
    
    /**
     * 删除材质指标
     * @param params
     * @return
     */
    @Path("deleteIndicatorHeaders")
    @POST
    public String deleteIndicatorHeaders(@FormParam("params")
        String params) {
    	try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPrcIndicatorHeadersServer.deleteIndicatorHeaders(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        }catch (Exception e) {
            ////e.printStackTrace();
            LOGGER.error("--->>删除材质指标参数：" + params + ",异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除材质指标失败!"+e.getMessage(), 0, null);
        }
    }
    
    /**
     * 保存／确认材质指标
     * @param params
     * @return
     */
    @Path("saveIndicatorHeaders")
    @POST
    public String saveIndicatorHeaders(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPrcIndicatorHeadersServer.saveIndicatorHeaders(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (Exception e) {
            LOGGER.error("保存材质指标失败！" + e,e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存材质指标失败!" + e.getMessage(), 0, null);
        }
    }
    
    /**
     * 材质指标失效
     * @param params
     * @return
     */
    @Path("updateIndicatorInvalid")
    @POST
    public String updateIndicatorInvalid(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPrcIndicatorHeadersServer.updateIndicatorInvalid(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (Exception e) {
            LOGGER.error("失效失败！" + e,e);
            return CommonAbstractServices.convertResultJSONObj("E", "失效失败!" + e.getMessage(), 0, null);
        }
    }

    /**
     * 查询材质指标相关供应商列表
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Path("findIndicatorSupplierList")
    @POST
    public String findIndicatorSupplierList(@FormParam("params") String params, @FormParam("pageIndex")
            Integer pageIndex, @FormParam("pageRows") Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmPrcIndicatorHeadersEntity_HI_RO> indicatorList = srmPrcIndicatorHeadersServer.findIndicatorSupplierList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(indicatorList);
        }catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询材质指标相关供应商列表：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询材质指标相关供应商列表!" + e.getMessage(), 0, null);
        }
    }

    /**
     * 根据指标查询关联的物料和对应的长宽高
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Path("findIndicatorToItem")
    @POST
    public String findIndicatorToItem(@FormParam("params") String params, @FormParam("pageIndex")
            Integer pageIndex, @FormParam("pageRows") Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmPrcIndicatorHeadersEntity_HI_RO> indicatorList = srmPrcIndicatorHeadersServer.findIndicatorToItem(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(indicatorList);
        }catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("根据指标查询关联的物料和对应的长宽高：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "根据指标查询关联的物料和对应的长宽高!" + e.getMessage(), 0, null);
        }
    }

    /**
     * 保存调价
     * @param params
     * @return
     */
    @Path("saveAdjustPrice")
    @POST
    public String saveAdjustPrice(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPrcIndicatorHeadersServer.saveAdjustPrice(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (Exception e) {
            LOGGER.error("保存价格失败！" + e,e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存价格失败!" + e.getMessage(), 0, null);
        }
    }
}
