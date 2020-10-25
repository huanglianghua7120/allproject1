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
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplCategoriesEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmTplCategories;

@Component("srmSpmTplCategoriesService")
@Path("/srmSpmTplCategoriesService")
public class SrmSpmTplCategoriesService extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmTplCategoriesService.class);
	@Autowired
	private ISrmSpmTplCategories srmSpmTplCategoriesServer;

	public SrmSpmTplCategoriesService() {
		super();
	}

	@POST
	@Path("getperformanceTplItemList")
	public String getperformanceTplItemList(@FormParam(PARAMS) String params) {
		try {
			LOGGER.info(params);
			JSONObject jsonParams = this.parseObject(params);
			List<SrmSpmTplCategoriesEntity_HI_RO> list = srmSpmTplCategoriesServer
					.getperformanceTplItemList(jsonParams);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功", list.size(), list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}

	@POST
	@Path("categoryCodeExport")
	public String categoryCodeExport(@FormParam(PARAMS) String params) {
		try {
			LOGGER.info(params);
			JSONObject jsonParams = this.parseObject(params);
			List<SrmSpmTplCategoriesEntity_HI_RO> list = srmSpmTplCategoriesServer.categoryCodeExport(jsonParams);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功", list.size(), list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}

	@POST
	@Path("savecategoryImport")
	public String savecategoryImport(@FormParam("params") String params) {
		try {
			JSONObject jsonParam = parseObject(params);
			JSONObject json = this.srmSpmTplCategoriesServer.savecategoryImport(jsonParam);
			return JSON.toJSONString(json);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：数据已有变动，请刷新页面重试。", 0L, null);
		} catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "导入失败!" + e, 0L, null));
		}
	}
}
