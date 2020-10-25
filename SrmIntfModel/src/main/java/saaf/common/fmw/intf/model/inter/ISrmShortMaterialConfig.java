package saaf.common.fmw.intf.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.po.model.entities.readonly.SrmShortMaterialConfigEntity_HI_RO;

public interface ISrmShortMaterialConfig {

	Pagination<SrmShortMaterialConfigEntity_HI_RO> findQueryConfig(JSONObject jsonParams, Integer pageIndex,
			Integer pageRows) throws Exception;
	
	String saveConfig(JSONObject params) throws Exception;

}
