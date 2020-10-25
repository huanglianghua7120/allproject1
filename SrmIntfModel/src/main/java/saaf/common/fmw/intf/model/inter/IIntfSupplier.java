package saaf.common.fmw.intf.model.inter;

import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;

import java.util.Map;

public interface IIntfSupplier {

	public JSONObject pushSupplier(String supplierNumber, Integer userId) throws Exception;


	public JSONObject pushSupplier(String supplierNumber,Integer supplierId, Integer userId) throws Exception ;
}
