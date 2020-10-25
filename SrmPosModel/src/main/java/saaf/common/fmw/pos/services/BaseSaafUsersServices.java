package saaf.common.fmw.pos.services;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.pos.model.inter.ISaafUsers;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.services.CommonAbstractServices;


/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：baseSaafUsersServices
 * Description：用来处理用户维护
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmUsersServices")
@Path("/srmUsersServlet")
public class BaseSaafUsersServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSaafUsersServices.class);
    @Autowired
    private ISaafUsers srmUsersServer; 

    public BaseSaafUsersServices() {
        super();
    }


    
    @Path("findStr")
    @POST
    public String getStrings(@FormParam("params")
        String params) {
        return "success!";
    }


    /**   
     * 查询用户列表
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSaafUsersList")
    @POST
    public String findSaafUsersList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination usersList = srmUsersServer.findSaafUsersList(jsonParam, pageIndex, pageRows);
            LOGGER.info("=========="+JSON.toJSONString(usersList));
            return JSON.toJSONString(usersList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //LOGGER.error("未知错误:{}", e);
            LOGGER.error("查询用户列表异常：" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询用户列表失败!" + e, 0, null);
        }
    }
    
    
    
}
