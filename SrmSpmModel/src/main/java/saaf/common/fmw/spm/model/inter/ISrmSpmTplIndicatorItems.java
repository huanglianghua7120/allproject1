package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import java.util.List;

import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.SrmSpmTplIndicatorItemsEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplIndicatorItemsEntity_HI_RO;

public interface ISrmSpmTplIndicatorItems {

	List<SrmSpmTplIndicatorItemsEntity_HI> findSrmSpmTplIndicatorItemsInfo(JSONObject queryParamJSON);

	Object saveSrmSpmTplIndicatorItemsInfo(JSONObject queryParamJSON);

	List<SrmSpmTplIndicatorItemsEntity_HI_RO> getInvoiceItemsList(JSONObject jsonParams);

	List<SaafLookupValuesEntity_HI_RO> selectTplDimension(JSONObject jsonParam)throws Exception;



}
