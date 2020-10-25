package saaf.common.fmw.pon.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonPaymentTermNodesEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonPaymentTermNodes;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonPaymentTermNodesService.java
 * Description：寻源--付款节点信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonPaymentTermNodesService")
@Path("/srmPonPaymentTermNodesService")
public class SrmPonPaymentTermNodesService extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonPaymentTermNodesService.class);
    @Autowired
    private ISrmPonPaymentTermNodes iSrmPonPaymentTermNodes;

    public SrmPonPaymentTermNodesService() {
        super();
    }

    /**
     * Description：付款节点查询（不分页）
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("findSrmPonPaymentTermNodesInfo")
    @Produces("application/json")
    public String findSrmPonPaymentTermNodesInfo(@FormParam(PARAMS) String params) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            List<SrmPonPaymentTermNodesEntity_HI_RO> list = iSrmPonPaymentTermNodes.findSrmPonPaymentTermNodesInfo(paramJSON);
//            JSONObject jsonParams = new JSONObject();
//            jsonParams.put(DATA, list);
//            jsonParams.put(STATUS, SUCCESS_STATUS);
//            jsonParams.put("count", list.size());
//            jsonParams.put("curIndex", 1);
//            jsonParams.put("pagesCount", 1);
//            jsonParams.put("pageSize", list.size());
//            jsonParams.put(MSG, "查询成功！");
//            return JSON.toJSONString(list);
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("S", "查询成功!", 0, list));
        } catch (Exception e) {
            LOGGER.error("查询失败！", e.getMessage());
            return convertResultJSONObj(ERROR_STATUS, "查询失败!", 0, null);
        }
    }

    /**
     * Description：删除付款节点——根据主键Id（单条数据）
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("deleteTermCodeId")
    @Produces("application/json")
    public String deleteTermCodeId(@FormParam(PARAMS) String params) {
        LOGGER.info("参数：" + params.toString());
        if (StringUtils.isBlank(params)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可删除！", 0, null);
        }
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = iSrmPonPaymentTermNodes.deleteTermCodeId(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.get(DATA));
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("删除失败！参数：" + params + ",异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "删除失败!", 0, null);
        }
    }
}
