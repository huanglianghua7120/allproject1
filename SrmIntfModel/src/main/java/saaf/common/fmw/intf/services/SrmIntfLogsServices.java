package saaf.common.fmw.intf.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.intf.model.entities.readonly.SrmIntfLogsEntity_HI_RO;
import saaf.common.fmw.intf.model.inter.ISrmIntfLogs;
import saaf.common.fmw.services.CommonAbstractServices;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;


@Path("/srmIntfLogsServices")
@Component
public class SrmIntfLogsServices extends CommonAbstractServices{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmIntfLogsServices.class);
	@Autowired
	private ISrmIntfLogs srmIntfLogsServer;

	/**
	 * 查询日志
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 */
	@POST
	@Path("findLogList")
	public String findLogList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			Pagination<SrmIntfLogsEntity_HI_RO> pag = srmIntfLogsServer.findLogList(jsonParams, pageIndex, pageRows);
			LOGGER.info("-->>参数：" + params + "查询成功！");
			return JSON.toJSONString(pag);
		} catch (Exception e) {
			LOGGER.error("查询失败" + e,e);
			return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e, 0, null);
		}
	}

	/**
	 * 查询报文
	 * @param params
	 * @return
	 */
	@POST
	@Path("findLogData")
	public String findLogData(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			List list  = srmIntfLogsServer.findLogData(jsonParams);
			LOGGER.info("-->>参数：" + params + "查询成功！");
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功！", list.size(), list);
		} catch (Exception e) {

			LOGGER.error("查询失败" + e,e);
			return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e, 0, null);
		}
	}

}
