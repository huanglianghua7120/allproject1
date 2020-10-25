package saaf.common.fmw.intf.services;

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

import saaf.common.fmw.intf.model.inter.ISrmShortMaterialInv;
import saaf.common.fmw.po.model.entities.readonly.SrmShortMaterialInvEntity_HI_RO;
import saaf.common.fmw.services.CommonAbstractServices;

@Path("/srmShortMaterialInvServices")
@Component
public class SrmShortMaterialInvServices extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmShortMaterialInvServices.class);

	@Autowired
	private ISrmShortMaterialInv srmShortMaterialInvServer;

	/**
	 * U9库存查询
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 */
	@Path("findU9MaterielInv")
	@POST
	public String findU9MaterielInv(@FormParam("params") String params, @FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageRows") Integer pageRows) {
		try {
			JSONObject jsonParam = JSON.parseObject(params);
			LOGGER.info("------jsonParam------" + jsonParam.toString());
			Pagination<SrmShortMaterialInvEntity_HI_RO> infoList = srmShortMaterialInvServer
					.findU9MaterielInv(jsonParam, pageIndex, pageRows);
			return JSON.toJSONString(infoList);
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("U9库存查询异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "U9库存查询失败!" + e, 0, null);
		}
	}

}
