package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.FormParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceTplEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplIndicatorsEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmTplIndicators;

@Component("srmSpmTplIndicatorsService")
@Path("/srmSpmTplIndicatorsService")
public class SrmSpmTplIndicatorsService extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmTplIndicatorsService.class);
	private static final String PARAMS = "params";
	private static final String PAGE_INDEX = "pageIndex";
	private static final String PAGE_ROWS = "pageRows";
	@Autowired
	private ISrmSpmTplIndicators srmSpmTplIndicatorsServer;

	public SrmSpmTplIndicatorsService() {
		super();
	}

    /**
     * Description：查询绩效模板评价指标
     * @param params 评价指标查询参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("getInvoiceLineList")
	public String getInvoiceLineList(@FormParam(PARAMS) String params) {
		try {
			LOGGER.info(params);
			JSONObject jsonParams = this.parseObject(params);
			List<SrmSpmTplIndicatorsEntity_HI_RO> list = srmSpmTplIndicatorsServer.getInvoiceLineList(jsonParams);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功", list.size(), list);
		}catch (UtilsException e){
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}
    /**
     * 查询模板指标CODE
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@POST
	@Path("selectIndicatorCode")
	public String selectIndicatorCode(@FormParam(PARAMS) String params) {
		try {
			LOGGER.info(params);
			JSONObject jsonParam = this.parseObject(params);
			Map<String, Object> frozenInfo = srmSpmTplIndicatorsServer.selectIndicatorCode(jsonParam);
			jsonParam.put("frozenInfo", frozenInfo);
			jsonParam.put("status", "S");
			jsonParam.put("msg", "查询成功");
			return jsonParam.toJSONString();
		}catch (UtilsException e){
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}
    /**
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@POST
	@Path("findInvoicetplPost")
	public String findInvoicetplPost(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,@FormParam(PAGE_ROWS) Integer pageRows) {
		try {
			JSONObject paramJSON = JSON.parseObject(params);
			Pagination<SaafLookupValuesEntity_HI_RO> page =srmSpmTplIndicatorsServer.findInvoicetplPost(paramJSON,pageIndex, pageRows);
			return JSON.toJSONString(page);
		}catch (UtilsException e){
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}

    /**
     *查询绩效模板的指标信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@POST
	@Path("findTplIndicatorsList")
	public String findTplIndicatorsList(@FormParam(PARAMS) String params) {
		try {
			LOGGER.info(params);
			JSONObject jsonParam = this.parseObject(params);
			Map<String, Object> tplIndicatorsList = srmSpmTplIndicatorsServer.findTplIndicatorsList(jsonParam);
			jsonParam.put("data", tplIndicatorsList);
			jsonParam.put("status", "S");
			jsonParam.put("msg", "查询成功");
			return jsonParam.toJSONString();
		} catch (UtilsException e){
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}
}
