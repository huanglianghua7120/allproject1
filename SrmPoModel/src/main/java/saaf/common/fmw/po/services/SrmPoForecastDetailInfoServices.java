package saaf.common.fmw.po.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.po.model.entities.readonly.SrmPoForecastDetailInfoEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoForecastDetailInfo;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Project Name：SrmPoForecastDetailInfoServices
 * Company Name：SIE
 * Program Name：
 * Description：预测信息供应商明细
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoForecastDetailInfoServices")
@Path("/srmForecastDetailInfoServlet")
public class SrmPoForecastDetailInfoServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoForecastDetailInfoServices.class);

    public SrmPoForecastDetailInfoServices() {
        super();
    }


    @Autowired
    private ISrmPoForecastDetailInfo srmPoForecastDetailInfo;



    /**
     * Description：查询采购预测明细
     * @param params 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("findForecastDetailInfoList")
    @POST
    public String findForecastDetailInfoList(@FormParam("params")
    String params, @FormParam("pageIndex")
    Integer pageIndex, @FormParam("pageRows")
    Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
             Pagination<SrmPoForecastDetailInfoEntity_HI_RO> usersList = srmPoForecastDetailInfo.findForecastDetailInfoList(jsonParam,0,0);
            return JSON.toJSONString(usersList);
        }   catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
}
