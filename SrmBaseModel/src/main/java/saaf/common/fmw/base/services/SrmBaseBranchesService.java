package saaf.common.fmw.base.services;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseBranchesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseBranches;
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
 * Description：分行
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmBaseBranchesService")
@Path("/srmBaseBranchesService")
public class SrmBaseBranchesService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseBranchesService.class);
	@Autowired
	private ISrmBaseBranches srmBaseBranchesServer;
	public SrmBaseBranchesService() {
		super();
	}

	/**
	 * 分行查询
	 * @param params
	 * @param curIndex
	 * @param pageSize
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findSrmBaseBranchesInfo")
	//	//srmBaseBranchesService/findSrmBaseBranchesInfo
	public String findSrmBaseBranchesInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) @DefaultValue("1")
        Integer curIndex, @FormParam(PAGE_ROWS) @DefaultValue("10") Integer pageSize) {
		try{
			JSONObject paramJSON = this.parseObject(params);
			Pagination<SrmBaseBranchesEntity_HI_RO> list = srmBaseBranchesServer.findSrmBaseBranchesInfoList(paramJSON,curIndex,pageSize);
			return JSONObject.toJSONString(list);
		}catch (Exception e){
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"未知错误",0,null);
		}
	}
}
