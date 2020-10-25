package saaf.common.fmw.login.model.inter;

import com.alibaba.fastjson.JSONObject;

public interface IRegister {

    public JSONObject saveRegister(JSONObject params) throws Exception;

}
