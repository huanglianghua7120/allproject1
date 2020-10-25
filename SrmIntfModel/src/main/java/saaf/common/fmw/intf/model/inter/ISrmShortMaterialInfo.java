package saaf.common.fmw.intf.model.inter;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.po.model.entities.readonly.SrmShortMaterialInfoEntity_HI_RO;

public interface ISrmShortMaterialInfo {
	public void saveShortMaterialInfo() throws Exception;
	
	public boolean saveU9Data();
	
	Pagination<SrmShortMaterialInfoEntity_HI_RO> findShortMaterielList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
	
	Pagination<SrmShortMaterialInfoEntity_HI_RO> findU9ReadyMaterielList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
	
	Pagination<SrmShortMaterialInfoEntity_HI_RO> findOnlyShortMaterielList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
	
	Pagination<SrmShortMaterialInfoEntity_HI_RO> findShortMaterialWarm(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
	
	List findOrders(JSONObject jsonParams) throws Exception;
	
	List findDeliveryOrder(JSONObject jsonParams) throws Exception;
	
	List findNoticeOrder(JSONObject jsonParams) throws Exception;
	
	Pagination<SrmShortMaterialInfoEntity_HI_RO>  findNoticeOrderSuperQty(JSONObject jsonParams, Integer pageIndex, Integer pageRows)  throws Exception;

    
}
