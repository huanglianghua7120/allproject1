package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.pon.model.entities.SrmPonDestinationsEntity_HI;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonDestinations.java
 * Description：寻源--目的地信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonDestinations {

    /**
     * Description：查询目的地列表
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return Pagination<SrmPonDestinationsEntity_HI>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    Pagination<SrmPonDestinationsEntity_HI> findDestinationInfo(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：保存目的地信息
     * @param params
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject saveDestinationInfo(JSONObject params) throws Exception;

    /**
     * Description：EXCEL导入目的地信息
     * @param params
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject saveDestinationByImport(JSONObject params) throws Exception;

    /**
     * Description：删除目的地信息
     * @param params
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject deleteDestinationInfo(JSONObject params) throws Exception;
}
