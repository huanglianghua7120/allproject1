package saaf.common.fmw.schedule.model.inter;

import com.alibaba.fastjson.JSONObject;


public interface ISchedules {
    String saveRequest(JSONObject parameters);

    String deleteSchedule(JSONObject parameters);

    String findRequests(JSONObject parameters, Integer pageIndex, Integer pageRows);

    String getRequestLog(JSONObject parameters);

    String cancelRequest(JSONObject parameters);

    String launchRequest(JSONObject parameters);

    String pauseRequest(JSONObject parameters);

    String resumeRequest(JSONObject parameters);
}
