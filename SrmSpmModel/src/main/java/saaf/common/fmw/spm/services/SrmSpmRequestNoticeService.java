package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import java.util.Map;

import javax.ws.rs.FormParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmRequestNoticeEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmRequestNotice;

@Component("srmSpmRequestNoticeService")
@Path("/srmSpmRequestNoticeService")
public class SrmSpmRequestNoticeService extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmRequestNoticeService.class);
	private static final String PARAMS = "params";
	private static final String PAGE_INDEX = "pageIndex";
	private static final String PAGE_ROWS = "pageRows";
	@Autowired
	private ISrmSpmRequestNotice srmSpmRequestNoticeServer;

	public SrmSpmRequestNoticeService() {
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
	@Path("RequestNoticeList")
	public String RequestNoticeList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,@FormParam(PAGE_ROWS) Integer pageRows) {
		
		LOGGER.info(params);
		try {
			JSONObject paramJSON = JSON.parseObject(params);
			Pagination<SrmSpmRequestNoticeEntity_HI_RO> page =srmSpmRequestNoticeServer.RequestNoticeList(paramJSON,pageIndex, pageRows);
			return JSON.toJSONString(page);
		}catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}
	
	@Path("getNoticeResults")
    @POST
    public String getNoticeResults(@FormParam("params")String params) {
		try {
			JSONObject jsonParam = JSONObject.parseObject(params);
			Map<String,Object> frozenInfo =srmSpmRequestNoticeServer.getNoticeResults(jsonParam);
		    jsonParam.put("frozenInfo", frozenInfo);
		    jsonParam.put("status", "S");
		    jsonParam.put("msg", "查询成功");
		    return jsonParam.toJSONString();
		}catch (Exception e) {
            LOGGER.error("查询失败！", e);
            return convertResultJSONObj("E", "查询失败!" + e, 0, null);
        }
	}
}
