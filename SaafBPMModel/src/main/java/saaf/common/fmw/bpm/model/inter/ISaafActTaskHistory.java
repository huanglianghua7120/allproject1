package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;

public interface ISaafActTaskHistory {
    public String findActTaskHistory(JSONObject queryParamJSON, Integer pageIndex, Integer pageRows);
    
}
