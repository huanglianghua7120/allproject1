package saaf.common.fmw.schedule.model.inter;

import com.alibaba.fastjson.JSONObject;

public interface IJobParameters {
    String saveParameter(JSONObject parameters);
    String saveParameterInfo(JSONObject parameters);

    String deleteJobParameter(JSONObject parameters);

    String updateJobParameter(JSONObject parameters);

    String findJobParameters(JSONObject parameters, Integer pageIndex, Integer pageRows);
}
