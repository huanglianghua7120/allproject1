package saaf.common.fmw.po.model.inter;

 
import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import java.util.Map;



/**
 * Project Name：IPoUsers
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
public interface IPoUsers {


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
    Pagination findSaafUsersList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    String getValuessss();

}

