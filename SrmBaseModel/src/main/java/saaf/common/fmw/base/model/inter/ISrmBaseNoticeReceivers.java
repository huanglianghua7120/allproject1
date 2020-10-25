package saaf.common.fmw.base.model.inter;

import java.util.List;

import saaf.common.fmw.base.model.entities.SrmBaseNoticeReceiversEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseNoticesEntity_HI_RO;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：公告
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmBaseNoticeReceivers {
	
   /**
    * 查询公告接收者(组织)
    * @param params
    * @param pageIndex
    * @param pageRows
    * @return
    * @throws Exception
    * Update History
    * ==============================================================================
    *  Version    Date           Updated By     Description
    *  -------    -----------    -----------    ---------------
    *  V1.0       2020-04-15     hgq             创建
    * ==============================================================================
    */
    Pagination<SrmBaseNoticesEntity_HI_RO> findInstReceiversList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;
    
    /**
     * 查询公告接收者(员工)
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmBaseNoticesEntity_HI_RO> findEmployeeReceiversList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;
     
    /**
     * 查询公告接收者(供应商)
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmBaseNoticesEntity_HI_RO> findSupplierReceiversList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;
    
   /**
    * 保存接收者(组织)
    * @param instList
    * @param userId
    * @param noticeId
    * @throws Exception
    * Update History
    * ==============================================================================
    *  Version    Date           Updated By     Description
    *  -------    -----------    -----------    ---------------
    *  V1.0       2020-04-15     hgq             创建
    * ==============================================================================
    */
    public void saveInstList(List<SrmBaseNoticeReceiversEntity_HI> instList,Integer userId,Integer noticeId) throws Exception;
    
    /**
     * 保存接收者(员工)
     * @param employeeList
     * @param userId
     * @param noticeId
     * @throws Exception
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
     public void saveEmployeeList(List<SrmBaseNoticeReceiversEntity_HI> employeeList,Integer userId,Integer noticeId) throws Exception;
     
     /**
      * 保存接收者(供应商)
      * @param supplierList
      * @param userId
      * @param noticeId
      * @throws Exception
      * Update History
      * ==============================================================================
      *  Version    Date           Updated By     Description
      *  -------    -----------    -----------    ---------------
      *  V1.0       2020-04-15     hgq             创建
      * ==============================================================================
      */
     public void saveSupplierList(List<SrmBaseNoticeReceiversEntity_HI> supplierList,Integer userId,Integer noticeId) throws Exception;
    
    /**
	 * 删除公告接收者
	 * @param params
	 * @return
	 * @throws Exception
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject deleteNoticeReceivers(JSONObject params) throws Exception;
   
}
