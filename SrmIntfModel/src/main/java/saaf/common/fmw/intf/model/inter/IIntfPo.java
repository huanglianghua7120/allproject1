package saaf.common.fmw.intf.model.inter;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import saaf.common.fmw.po.model.entities.readonly.SrmPoLinesEntity_HI_RO;

public interface IIntfPo {
	
	public JSONObject pushPoOrder(String poHeaderId, Integer userId) throws Exception;
	
	public JSONObject updateAndClosePoLine(String poHeaderId, Integer userId) throws Exception;
	
	public void updatePoOrder(String polineIds, Integer userId) throws Exception;
	
	public JSONObject updateSinglePoOrder(String polineIds, Integer userId) throws Exception;
	
	public  List<SrmPoLinesEntity_HI_RO> pushBatchPoOrder() throws Exception;

}
