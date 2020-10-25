package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.FormParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplIndicatorItemsEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmTplIndicatorItems;

@Component("srmSpmTplIndicatorItemsService")
@Path("/srmSpmTplIndicatorItemsService")
public class SrmSpmTplIndicatorItemsService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmTplIndicatorItemsService.class);
	@Autowired
	private ISrmSpmTplIndicatorItems srmSpmTplIndicatorItemsServer;
	public SrmSpmTplIndicatorItemsService() {
		super();
	}

	@POST
	@Path("getInvoiceItemsList")
	public String getInvoiceItemsList(@FormParam(PARAMS) String params) {
		try {
			LOGGER.info(params);
			JSONObject jsonParams = this.parseObject(params);
			List<SrmSpmTplIndicatorItemsEntity_HI_RO> list = srmSpmTplIndicatorItemsServer.getInvoiceItemsList(jsonParams);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功", list.size(), list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}
	
	
	@POST
	@Path("selectTplDimension")
	public String selectTplDimension(@FormParam("params")String params) throws Exception{
		LOGGER.info(params);
		JSONObject jsonParam = JSON.parseObject(params);
		List<SaafLookupValuesEntity_HI_RO> list=srmSpmTplIndicatorItemsServer.selectTplDimension(jsonParam);
		jsonParam.put("LookupValues", list);
	       jsonParam.put("status", "S");
	       jsonParam.put("msg", "查询成功");
		return jsonParam.toJSONString();
	}
	
	
}
