package saaf.common.fmw.base.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SaafUserAccessOrgsEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseUserCategoriesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.common.utils.SaafToolUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：SrmBaseUserCategoriesServer.java
 * Description：用户采购分类权限控制的Server层接口实现类
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019-06-04     秦晓钊          创建
 * ==============================================================================
 */
@Component("srmBaseUserCategoriesServer")
public class SrmBaseUserCategoriesServer implements ISrmBaseUserCategories {

    @Autowired
    private ViewObject<SrmBaseUserCategoriesEntity_HI> srmBaseUserCategoriesDAO_HI;

    @Autowired
    private BaseViewObject<SrmBaseUserCategoriesEntity_HI_RO> srmBaseUserCategoriesDAO_HI_RO;

    /**
     * Description：保存用户的采购分类，自动生成的方法
     *
     * @param parameters 参数
     * @return 字符串
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-06-04     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public Object saveUserCategories(JSONObject parameters) throws Exception {
        SrmBaseUserCategoriesEntity_HI srmBaseUserCategoriesEntity_HI = null;
        int operatorUserId = parameters.getInteger("userId");
        JSONArray jsonObjectArray = JSON.parseArray(parameters.getString("userCategoriesData"));
        JSONObject jsonObject = null;
        List<SrmBaseUserCategoriesEntity_HI> objectList = new ArrayList<>();
        //先删除指定用户的所有采购分类，然后再重新新增
        deleteUserCategories(operatorUserId);
        try {
            for (int i = 0; i < jsonObjectArray.size(); i++) {
                jsonObject = jsonObjectArray.getJSONObject(i);
                srmBaseUserCategoriesEntity_HI = new SrmBaseUserCategoriesEntity_HI();
                srmBaseUserCategoriesEntity_HI.setUserId(jsonObject.getInteger("userId"));
                srmBaseUserCategoriesEntity_HI.setCategoryId(jsonObject.getInteger("categoryId"));
                srmBaseUserCategoriesEntity_HI.setEnabledFlag("Y");
                srmBaseUserCategoriesEntity_HI.setVersionNum(0);
                srmBaseUserCategoriesEntity_HI.setOperatorUserId(operatorUserId);
                objectList.add(srmBaseUserCategoriesEntity_HI);
            }
            srmBaseUserCategoriesDAO_HI.save(objectList);
            return "S";
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：查询用户的采购分类列表，自动生成的方法
     *
     * @param parameters 参数
     * @return 字符串
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-06-04     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public List<SrmBaseUserCategoriesEntity_HI_RO> findUserCategories(JSONObject parameters) {
        StringBuffer queryString = new StringBuffer(SrmBaseUserCategoriesEntity_HI_RO.SRM_BASE_USER_CATEGORIES_QUERY_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(parameters, "sbuc.user_id", "userId", queryString, map, "=");
        SaafToolUtils.parperParam(parameters, "sbc.full_category_code", "categoryCode", queryString, map, "=");
        List<SrmBaseUserCategoriesEntity_HI_RO> list = srmBaseUserCategoriesDAO_HI_RO.findList(queryString, map);
        return list;
    }


    /**
     * Description：查询用户的采购分类列表，自动生成的方法
     *
     * @param userId 用户ID
     * @param categoryId 采购分类ID
     * @return 指定采购分类ID的，用户采购分类列表
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-06-10     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public List<SrmBaseUserCategoriesEntity_HI_RO> findUserCategories(int userId, int categoryId) {
        StringBuffer queryString = new StringBuffer(SrmBaseUserCategoriesEntity_HI_RO.SRM_BASE_USER_CATEGORIES_QUERY_SQL);
        queryString.append(" AND sbuc.user_id = :userId");
        queryString.append(" AND sbuc.category_id = :categoryId");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("categoryId", categoryId);
        List<SrmBaseUserCategoriesEntity_HI_RO> list =  srmBaseUserCategoriesDAO_HI_RO.findList(queryString, map);
        return list;
    }

    /**
     * Description：删除指定用户的所有采购分类
     *
     * @param userId 用户ID
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-06-10     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public void deleteUserCategories(int userId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        try {
            List<SrmBaseUserCategoriesEntity_HI> list = srmBaseUserCategoriesDAO_HI.findList(" from SrmBaseUserCategoriesEntity_HI where userId = :userId ", map);
            srmBaseUserCategoriesDAO_HI.deleteAll(list);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：查询用户的采购分类id串
     *
     * @param userId 参数
     * @return 字符串
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-03-09     吴嘉利          创建
     * ==============================================================================
     */
    @Override
    public String findUserCategoriesIdStr(Integer userId) {
        StringBuffer queryString = new StringBuffer(SrmBaseUserCategoriesEntity_HI_RO.QUERY_USER_CATEGORIES_SQL);
        String categoriesIdStr = null;
        if(null != userId) {
            queryString.append(" AND Sbuc.user_id = " + userId);
            List<SrmBaseUserCategoriesEntity_HI_RO> list = srmBaseUserCategoriesDAO_HI_RO.findList(queryString);
            if (null != list && list.size() > 0) {
                categoriesIdStr = list.get(0).getCatagoryIdStr();
            }
        }
        return categoriesIdStr;
    }
}
