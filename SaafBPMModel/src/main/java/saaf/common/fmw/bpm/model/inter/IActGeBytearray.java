package saaf.common.fmw.bpm.model.inter;

import saaf.common.fmw.bpm.model.entities.ActGeBytearrayEntity_HI;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActGeBytearray{

	String findActGeBytearrayInfo(JSONObject queryParamJSON);

	String saveActGeBytearrayInfo(JSONObject queryParamJSON);

	ActGeBytearrayEntity_HI getActGeBytearrayEntity(String name,String deploymentId);
}
