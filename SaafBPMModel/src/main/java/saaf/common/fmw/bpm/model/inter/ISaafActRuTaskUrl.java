package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;

import saaf.common.fmw.common.model.inter.ISessionBean;

public interface ISaafActRuTaskUrl extends ISessionBean {
    public String findRunTaskURLInfo(JSONObject queryParamJSON, Integer pageIndex, Integer pageRows);
}
