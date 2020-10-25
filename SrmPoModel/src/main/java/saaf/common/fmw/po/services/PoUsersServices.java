package saaf.common.fmw.po.services;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.po.model.inter.IPoUsers;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Project Name：PoUsersServices
 * Company Name：SIE
 * Program Name：
 * Description：采购用户列表
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("poUsersServices")
@Path("/poUsersServices")
public class PoUsersServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(PoUsersServices.class);
    @Autowired
    private IPoUsers poUsersServer; 

    public PoUsersServices() {
        super();
    }




    /**
     * Description：查询用户列表
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
    @Path("findSaafUsersList")
    @POST
    public String findSaafUsersList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            System.out.println("测试2");
            LOGGER.info("------jsonParam-----" + jsonParam.toString());
            Pagination usersList = poUsersServer.findSaafUsersList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(usersList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

}

