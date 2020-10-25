package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import saaf.common.fmw.spm.model.entities.SrmSpmTplCategoriesEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplCategoriesEntity_HI_RO;

public interface ISrmSpmTplCategories {

	List<SrmSpmTplCategoriesEntity_HI> findSrmSpmTplCategoriesInfo(JSONObject queryParamJSON);

	Object saveSrmSpmTplCategoriesInfo(JSONObject queryParamJSON);

	List<SrmSpmTplCategoriesEntity_HI_RO> getperformanceTplItemList(JSONObject jsonParams);

	List<SrmSpmTplCategoriesEntity_HI_RO> categoryCodeExport(JSONObject jsonParams);

	JSONObject savecategoryImport(JSONObject jsonParam)throws Exception;

}
