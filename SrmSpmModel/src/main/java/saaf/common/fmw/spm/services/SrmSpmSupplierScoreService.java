package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.inter.ISrmSpmSupplierScore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/srmSpmSupplierScoreService")
@Component
public class SrmSpmSupplierScoreService extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmSupplierScoreService.class);

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    public SrmSpmSupplierScoreService() {
        super();
    }

    @Autowired
    private ISrmSpmSupplierScore srmSpmSupplierScoreServer;


    /**
     * 更新供应商得分数据
     *
     * @param params（ids）
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("updateSupplierScore")
    public String updateSupplierScore(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(srmSpmSupplierScoreServer.updateSupplierScore(jsonParams));
        } catch (UtilsException e){
            LOGGER.error("更新失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "更新失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
            LOGGER.error("更新失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "更新失败：" + e.getMessage(), 0, null);
        }
    }

}
