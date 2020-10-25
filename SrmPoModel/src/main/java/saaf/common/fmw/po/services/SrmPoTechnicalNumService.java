package saaf.common.fmw.po.services;

import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.po.model.entities.readonly.SrmPoTechnicalNumEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoTechnicalNum;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.services.CommonAbstractServices;

@Component("srmPoTechnicalNumService")
@Path("/srmPoTechnicalNumService")
public class SrmPoTechnicalNumService extends CommonAbstractServices{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoTechnicalNumService.class);
	@Autowired
	private ISrmPoTechnicalNum srmPoTechnicalNumServer;
    @Autowired
    private ISrmPoTechnicalNum iSrmPoTechnicalNum;
	public SrmPoTechnicalNumService() {
		super();
	}

    /**
     * Description：查询技改编号
     * @param params 查询参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-27     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("findSrmPoTechnicalNumInfo")
    public String findSrmPoTechnicalNumInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
                                             @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            Pagination<SrmPoTechnicalNumEntity_HI_RO> page = iSrmPoTechnicalNum.findSrmPoTechnicalNumInfo(jsonParams, pageIndex, pageRows);
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSONObject.toJSONString(page);
        }catch (UtilsException e){
            LOGGER.error(e.getMessage());
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
}
