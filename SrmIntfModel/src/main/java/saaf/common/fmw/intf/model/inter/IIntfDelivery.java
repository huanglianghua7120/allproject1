package saaf.common.fmw.intf.model.inter;

import com.alibaba.fastjson.JSONObject;

public interface IIntfDelivery {
	
	public JSONObject pushDelivery(Integer deliveryHeaderId, Integer userId) throws Exception;

}
