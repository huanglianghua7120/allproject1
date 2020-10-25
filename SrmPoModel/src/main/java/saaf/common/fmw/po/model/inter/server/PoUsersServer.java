package saaf.common.fmw.po.model.inter.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.base.adf.common.utils.SToolUtils;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.pos.model.entities.readonly.SaafUserEmployees2Entity_HI_RO;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.model.inter.server.ShortDescMessageServer;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.inter.IPoUsers;
import saaf.common.fmw.utils.SHA1Util;

/**
 * Project Name：PoUsersServer
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
@Component("poUsersServer")
public class PoUsersServer implements IPoUsers {

    private static final Logger LOGGER = LoggerFactory.getLogger(PoUsersServer.class);

    @Autowired
    private BaseViewObject<SaafUserEmployees2Entity_HI_RO> saafUserEmployeesDAO_HI_RO;

    public PoUsersServer() {
        super();
    }

    /**
     * Description：查询用户列表
     * @param parameters 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public Pagination findSaafUsersList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer queryString = new StringBuffer(SaafUserEmployees2Entity_HI_RO.QUERY_USERLIST_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(parameters, "su.User_Name", "varInputUserName", queryString, map, "like");
        SaafToolUtils.parperParam(parameters, "su.User_Full_Name", "varUserFullName", queryString, map, "like");
        SaafToolUtils.parperParam(parameters, "su.sex", "varUserSex", queryString, map, "like");
        //平台
        SaafToolUtils.parperParam(parameters, "su.platform_code", "var_equal_platformCode", queryString, map, "=");
        //新增查询过滤条件
        SaafToolUtils.parperParam(parameters, "se.EMPLOYEE_NUMBER", "employeeNumber", queryString, map, "like");
        SaafToolUtils.parperParam(parameters, "se.EMPLOYEE_NAME", "employeeName", queryString, map, "like");
        SaafToolUtils.parperParam(parameters, "se.MOBILE_PHONE", "mobilePhone", queryString, map, "like");
        //排序
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" ORDER BY su.last_update_date DESC");
        Pagination<SaafUserEmployees2Entity_HI_RO> userRowSet = saafUserEmployeesDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
        return userRowSet;
    }

    public String getValuessss() {
        return "***********";
    }

}


