package saaf.common.fmw.prc.model.inter;

import saaf.common.fmw.prc.model.entities.readonly.SrmPrcIndicatorHeadersEntity_HI_RO;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IPrcIndicatorHeaders {
	
	/**
	 * 查询材质指标列表
	 * @param parameters
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
    Pagination<SrmPrcIndicatorHeadersEntity_HI_RO> findIndicatorHeadersList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;
    
    
    /**
     * 删除材质指标
     * @param params
     * @return
     */
    JSONObject deleteIndicatorHeaders(JSONObject params) throws Exception;
    
    /**
     * 保存／确认材质指标
     * @param params
     * @return
     */
    JSONObject saveIndicatorHeaders(JSONObject params) throws Exception;
    
    /**
     * 材质指标失效
     * @param params
     * @return
     */
    JSONObject updateIndicatorInvalid(JSONObject params) throws Exception;

	/**
	 * 查询材质指标相关供应商列表
	 * @param parameters
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	Pagination<SrmPrcIndicatorHeadersEntity_HI_RO> findIndicatorSupplierList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

	/**
	 * 根据指标查询关联的物料和对应的长宽高
	 * @param parameters
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	Pagination<SrmPrcIndicatorHeadersEntity_HI_RO> findIndicatorToItem(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

	/**
	 * 保存调价
	 * @param params
	 * @return
	 */
	JSONObject saveAdjustPrice(JSONObject params) throws Exception;
	
	//导出查询
	List<SrmPrcIndicatorHeadersEntity_HI_RO> findExpHeader( String indicatorNumber,
			  String indicatorStatus,  String creationDateS,
			 String creationDateE) throws Exception;

	//导出查询
	List<SrmPrcIndicatorHeadersEntity_HI_RO> findSupplier( String indicatorNumber,
			  String indicatorStatus,  String creationDateS,
			 String creationDateE) throws Exception;

	//导出查询
	List<SrmPrcIndicatorHeadersEntity_HI_RO> findSupplierPrice(Integer indicatorHeaderId) throws Exception;
	
}
