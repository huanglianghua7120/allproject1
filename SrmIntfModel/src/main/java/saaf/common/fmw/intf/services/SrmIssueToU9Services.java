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

import saaf.common.fmw.intf.bean.U9ResultBean;
import saaf.common.fmw.intf.model.inter.IIntfPlanAnalyze;
import saaf.common.fmw.intf.model.inter.ISrmIssueToU9;
import saaf.common.fmw.services.CommonAbstractServices;

/*===========================================================+
|   Copyright (c) 2012 赛意信息科技有限公司                                          |
+===========================================================+
|  HISTORY                                                                        |
| ============ ====== ============  ===========================                   |
|  Date                     Ver.        liuwenjun                Content          |
| ============ ====== ============  ===========================                   |
|  Aug 20, 2016            1.0          创建                      Creation        |
|  Desc:测试
+===========================================================*/
@Component("SrmIssueToU9Services")
@Path("/SrmIssueToU9Services")
public class SrmIssueToU9Services {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmIssueToU9Services.class);
	@Autowired
	private ISrmIssueToU9 srmIssueToU9Server;
	
	@Autowired
	private IIntfPlanAnalyze IntfPlanAnalyzeServer;

	/**
	 * 保存供货列表
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 */
	@Path("saveData")
	@POST
	public String saveData(@FormParam("params") String params) {
		try {
			LOGGER.info("params:" + params);
			JSONObject jsonParam = JSON.parseObject(params);
			U9ResultBean r = srmIssueToU9Server.saveLog(jsonParam);
			try {
				// 五金需求接口
				if ("S0001".equals(r.getIntfType())) {
					String msg = srmIssueToU9Server.savePlanData(r);
					if ("S".equals(msg)) {
						
						IntfPlanAnalyzeServer.saveAndHandPlanData("3", r.getLogId(), null, "N", -1);
						r.setMsg("保存信息成功!");
										
					} else {
						r.setMsg("保存失败:" + msg);

						srmIssueToU9Server.updateLog(r);
						return CommonAbstractServices.convertResultJSONObj("E", "保存失败:" + msg, 1, null);
					}
				}
				// 收货审核接口 退货审核接口
				else {
					srmIssueToU9Server.saveDeliery(r);
				}

				srmIssueToU9Server.updateLog(r);

			} catch (Exception e) {
				//e.printStackTrace();
				r.setStatus("E");
				r.setMsg("SRM系统同步数据失败：" + e.getMessage());
				srmIssueToU9Server.updateLog(r);
				return CommonAbstractServices.convertResultJSONObj("E", r.getMsg(), 1, null);
			}

			return CommonAbstractServices.convertResultJSONObj("S", "保存信息成功!", 1, null);

		} catch (Exception e) {
			LOGGER.error("保存供信息异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "SRM系统同步数据异：!" + e.getMessage(), 0, null);
		}
	}

}
