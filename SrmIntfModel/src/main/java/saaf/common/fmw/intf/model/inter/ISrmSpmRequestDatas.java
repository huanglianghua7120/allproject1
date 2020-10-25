package saaf.common.fmw.intf.model.inter;

import com.alibaba.fastjson.JSONObject;

public interface ISrmSpmRequestDatas {

	//指标数据收集
	JSONObject  updateCalculateScheme(JSONObject jsonParams)throws Exception;
	//综合绩效计算
	JSONObject updateCalculateMultiple(JSONObject jsonParams)throws Exception;
}
