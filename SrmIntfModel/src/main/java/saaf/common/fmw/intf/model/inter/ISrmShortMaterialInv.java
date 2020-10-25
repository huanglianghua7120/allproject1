package saaf.common.fmw.intf.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.po.model.entities.readonly.SrmShortMaterialInvEntity_HI_RO;

public interface ISrmShortMaterialInv {

	Pagination<SrmShortMaterialInvEntity_HI_RO> findU9MaterielInv(JSONObject jsonParams, Integer pageIndex,
			Integer pageRows) throws Exception;

}
