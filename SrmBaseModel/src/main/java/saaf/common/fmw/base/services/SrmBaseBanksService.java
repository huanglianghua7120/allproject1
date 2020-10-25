package saaf.common.fmw.base.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseBanksEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseBanks;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：银行
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmBaseBanksService")
@Path("/srmBaseBanksService")
public class SrmBaseBanksService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseBanksService.class);
	@Autowired
	private ISrmBaseBanks srmBaseBanksServer;
	public SrmBaseBanksService() {
		super();
	}

	/**
	 *银行查询
	 * @param params
	 * @param curIndex
	 * @param pageSize
	 * @return
	 * Update History
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findSrmBaseBanksInfo")
	//	//srmBaseBanksService/findSrmBaseBanksInfo
	public String findSrmBaseBanksInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) @DefaultValue("1")
        Integer curIndex, @FormParam(PAGE_ROWS) @DefaultValue("10") Integer pageSize) {
		LOGGER.info(params);
		try{
			JSONObject paramJSON = this.parseObject(params);
			Pagination<SrmBaseBanksEntity_HI_RO> list=srmBaseBanksServer.findSrmBaseBanksInfoList(paramJSON,curIndex,pageSize);
			return JSON.toJSONString(list);
		}catch (Exception e){
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"未知错误",0,null);
		}
	}
}
