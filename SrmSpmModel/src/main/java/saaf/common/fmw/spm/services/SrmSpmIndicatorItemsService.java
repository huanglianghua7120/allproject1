package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
import saaf.common.fmw.spm.model.inter.ISrmSpmIndicatorItems;

@Component("srmSpmIndicatorItemsService")
@Path("/srmSpmIndicatorItemsService")
public class SrmSpmIndicatorItemsService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmIndicatorItemsService.class);
	@Autowired
	private ISrmSpmIndicatorItems srmSpmIndicatorItemsServer;
	public SrmSpmIndicatorItemsService() {
		super();
	}

	/**
	 * 
	 * @param params
	 * @param curIndex
	 * @param pageSize
	 * @return
	 * @throws Exception 
	 */
	@POST
	@Path("selectDimension")
	public String selectDimension(@FormParam("params")String params) throws Exception{
		LOGGER.info(params);
		JSONObject jsonParam = JSON.parseObject(params);
		List<SaafLookupValuesEntity_HI_RO> list=srmSpmIndicatorItemsServer.selectDimension(jsonParam);
		jsonParam.put("LookupValues", list);
	       jsonParam.put("status", "S");
	       jsonParam.put("msg", "查询成功");
		return jsonParam.toJSONString();
	}
}
