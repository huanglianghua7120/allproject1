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
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.intf.model.inter.ISrmShortMaterialConfig;
import saaf.common.fmw.po.model.entities.readonly.SrmShortMaterialConfigEntity_HI_RO;
import saaf.common.fmw.services.CommonAbstractServices;

@Path("/srmShortMaterialConfigServices")
@Component
public class SrmShortMaterialConfigServices extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmShortMaterialInvServices.class);

	@Autowired
	private ISrmShortMaterialConfig srmShortMaterialConfigServer;

	/**
	 * 缺料查询条件配置
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 */
	@Path("findQueryConfig")
	@POST
	public String findQueryConfig(@FormParam("params") String params, @FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageRows") Integer pageRows) {
		try {
			JSONObject jsonParam = JSON.parseObject(params);
			LOGGER.info("------jsonParam------" + jsonParam.toString());
			Pagination<SrmShortMaterialConfigEntity_HI_RO> infoList = srmShortMaterialConfigServer
					.findQueryConfig(jsonParam, pageIndex, pageRows);
			return JSON.toJSONString(infoList);
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("缺料查询条件配置查询异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "缺料查询条件配置查询失败!" + e, 0, null);
		}
	}
	
	
    @Path("saveConfig")
    @POST
    public JSONObject saveConfig(@FormParam("params")
        String params) {
    	LOGGER.info("保存信息:"+params);
        try {
            String  flag = srmShortMaterialConfigServer.saveConfig(this.parseObject(params));
            
            if("S".equals(flag)){
            	return SToolUtils.convertResultJSONObj("S", "修改成功" ,0, null);
            }
            

        }catch (Exception e) {
            LOGGER.error("保存失败！" + e,e);
            return SToolUtils.convertResultJSONObj("E", "修改成功:"+e.getMessage() ,0, null);
        }
        
        return SToolUtils.convertResultJSONObj("E", "修改失败" ,0, null);
    }

}
