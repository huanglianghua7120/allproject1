package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceLines;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Map;

@Component("srmSpmPerformanceLinesService")
@Path("/srmSpmPerformanceLinesService")
public class SrmSpmPerformanceLinesService extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmPerformanceLinesService.class);
	private static final String PARAMS = "params";
	private static final String PAGE_INDEX = "pageIndex";
	private static final String PAGE_ROWS = "pageRows";

	@Autowired
	private ISrmSpmPerformanceLines srmSpmPerformanceLinesServer;

	public SrmSpmPerformanceLinesService() {
		super();
	}


    /**
     * 查询绩效行信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Path("findPerformanceLineById")
	@POST
	public String findPerformanceLineById(@FormParam("params")String params) {
		try {
			JSONObject jsonParam = JSONObject.parseObject(params);
			Integer performanceId = jsonParam.getInteger("performanceId");
			if(performanceId != null) {
				Map<String,Object> performanceLinesInfo = srmSpmPerformanceLinesServer.findPerformanceLinesById(performanceId);
				jsonParam.put("data", performanceLinesInfo);
				jsonParam.put("status", "S");
				jsonParam.put("msg", "查询成功");
				return jsonParam.toJSONString();
			}else {
				return convertResultJSONObj("E", "请检查performanceId参数", 0, null);
			}
		}catch (Exception e) {
			LOGGER.error("查询失败！", e);
			return convertResultJSONObj("E", "查询失败!" + e, 0, null);
		}
	}

    /**
     * 保存绩效行信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Path("savePerformanceLine")
	@POST
	public String savePerformanceLine(@FormParam("params")String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmSpmPerformanceLinesServer.savePerformanceLine(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"),jsondata.getInteger("count"), jsondata.get("data"));
		} catch (NotLoginException e) {
			return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
		}catch (UtilsException e){
            LOGGER.error("保存绩效信息失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "保存绩效信息失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("--->>保存绩效信息失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "保存绩效信息失败!", 0, null);
		}
	}

}
