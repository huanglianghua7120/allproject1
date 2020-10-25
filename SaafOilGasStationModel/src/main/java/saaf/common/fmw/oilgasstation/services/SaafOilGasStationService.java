package saaf.common.fmw.oilgasstation.services;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.oilgasstation.model.entities.readonly.SaafOilGasStationEntity_HI_RO;
import saaf.common.fmw.oilgasstation.model.inter.ISaafOilGasStation;
import saaf.common.fmw.services.CommonAbstractServices;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

@Component("saafOilGasStationService")
@Path("/saafOilGasStationService")
public class SaafOilGasStationService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SaafOilGasStationService.class);
	@Autowired
	private ISaafOilGasStation saafOilGasStationServer;
	
	public SaafOilGasStationService() {
		super();
	}

	/**
	 * 
	 * @param params
	 * @param curIndex
	 * @param pageSize
	 * @return
	 */
	@POST
	@Path("findSaafOilGasStationInfo")
//	@Consumes("application/json")
//	@Produces("application/json")
	public String findSaafOilGasStationInfo(@FormParam(PARAMS) String params,
			@FormParam(PAGE_INDEX) @DefaultValue("1") Integer curIndex,
			@FormParam(PAGE_ROWS) @DefaultValue("10") Integer pageSize) {
		LOGGER.info(params);
		JSONObject paramJSON = JSON.parseObject(params);
		Pagination<SaafOilGasStationEntity_HI_RO> paginationList = saafOilGasStationServer.findSaafOilGasStationInfo(paramJSON,curIndex,pageSize);
		return JSON.toJSONString(paginationList);
	}
	
	@POST
	@Path("saveSaafOilGasStationInfos")
	public String saveSaafOilGasStationInfos(@FormParam(PARAMS) String params){
		
		try {
			//LOGGER.info(params);
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = saafOilGasStationServer.saveSaafOilGasStationInfos(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.get(DATA));
        } catch (Exception e) {
            LOGGER.error("保存加油站信息失败",e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存加油站信息失败!", 0, null);
        }
	}
}
