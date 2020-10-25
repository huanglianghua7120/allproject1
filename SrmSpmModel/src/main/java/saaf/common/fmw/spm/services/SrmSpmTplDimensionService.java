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

import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplDimensionEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmTplDimension;

@Component("srmSpmTplDimensionService")
@Path("/srmSpmTplDimensionService")
public class SrmSpmTplDimensionService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmTplDimensionService.class);
	@Autowired
	private ISrmSpmTplDimension srmSpmTplDimensionServer;
	public SrmSpmTplDimensionService() {
		super();
	}


	@POST
	@Path("getDimensionLineList")
	public String getDimensionLineList(@FormParam(PARAMS) String params) {
		try {
			LOGGER.info(params);
			JSONObject jsonParams = this.parseObject(params);
			List<SrmSpmTplDimensionEntity_HI_RO> list = srmSpmTplDimensionServer.getDimensionLineList(jsonParams);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功", list.size(), list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
		
	}
}
