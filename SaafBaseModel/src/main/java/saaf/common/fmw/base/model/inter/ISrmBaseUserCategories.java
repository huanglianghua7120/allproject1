package saaf.common.fmw.base.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.base.model.entities.SrmBaseUserCategoriesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.dao.SrmBaseUserCategoriesDAO_HI;
import saaf.common.fmw.base.model.dao.readonly.SrmBaseUserCategoriesDAO_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;

import java.util.List;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：ISrmBaseUserCategories.java
 * Description：用户采购分类权限控制的Server层接口类
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019-06-04     秦晓钊          创建
 * ==============================================================================
 */
public interface ISrmBaseUserCategories {

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
    Object saveUserCategories(JSONObject parameters) throws Exception;

    /**
     * Description：查询用户的采购分类列表，自动生成的方法
     *
     * @param parameters 参数
     * @return 用户的采购分类列表
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-06-04     秦晓钊          创建
     * ==============================================================================
     */
    List<SrmBaseUserCategoriesEntity_HI_RO> findUserCategories(JSONObject parameters);

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
    List<SrmBaseUserCategoriesEntity_HI_RO> findUserCategories(int userId, int categoryId);

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
    void deleteUserCategories(int userId) throws Exception;

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
    public String findUserCategoriesIdStr(Integer userId);
}
