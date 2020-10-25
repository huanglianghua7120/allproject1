package saaf.common.fmw.base.services;


import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.exception.NotLoginException;

import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.services.CommonAbstractServices;

import java.util.List;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：SrmBaseUserCategoriesService.java
 * Description：用户采购分类权限控制的Service层
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019-06-04     秦晓钊          创建
 * ==============================================================================
 */
@Component("srmBaseUserCategoriesService")
@Path("/srmBaseUserCategoriesService")
public class SrmBaseUserCategoriesService extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseUserCategoriesService.class);

    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategoriesServer;

    /**
     * Description：保存用户的采购分类，自动生成的方法
     *
     * @param params 参数
     * @return 字符串
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-06-04     秦晓钊          创建
     * ==============================================================================
     */
    @Path("saveUserCategories")
    @POST
    public String saveUserCategories(@FormParam("params")
                                                    String params) {
        LOGGER.info(" saveSrmBaseUserCategories params:" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            Object obj = srmBaseUserCategoriesServer.saveUserCategories(jsonParam);
            if ("S".equals(obj.toString())) {
                return CommonAbstractServices.convertResultJSONObj("S", "保存成功！", 1, null);
            } else {
                return CommonAbstractServices.convertResultJSONObj("E","保存失败！", 0, null);
            }
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("保存用户的采购分类信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E","保存失败！", 0, null);
        }
    }

    /**
     * Description：查询指定用户的采购分类列表，自动生成的方法
     *
     * @param params 参数
     * @return 字符串
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-06-04     秦晓钊          创建
     * ==============================================================================
     */
    @Path("findUserCategories")
    @POST
    public String findUserCategories(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            System.out.println("userId:" + jsonParam.get("userId"));
            List<SrmBaseUserCategoriesEntity_HI_RO> obj = null;
            //查询用户ID
            if (jsonParam.get("userId") != null && !"".equals(jsonParam.get("userId"))) {
                obj = srmBaseUserCategoriesServer.findUserCategories(jsonParam);
            }
            return CommonAbstractServices.convertResultJSONObj("S", "查询用户的采购分类信息成功！", 1, obj);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询用户的采购分类信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询用户的采购分类信息失败！" + e, 0, null);
        }
    }

}
