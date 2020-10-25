package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;

import saaf.common.fmw.common.model.inter.ISessionBean;

public interface ISaafActHiTaskUrl extends ISessionBean {
    public String findActTaskHistoryUrl(JSONObject queryParamJSON, Integer pageIndex, Integer pageRows);
}
