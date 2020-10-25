package saaf.common.fmw.base.model.inter.server;


import com.alibaba.fastjson.JSONObject;

import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.DynamicBaseViewObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.inter.ISaafFunctionStatistics;
import saaf.common.fmw.common.utils.SaafToolUtils;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：saafFunctionRecordsServer.java
 * Description：保存用户记录
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     钟士元             创建
 * ===========================================================================
 */
@Component("saafFunctionRecordsServer")
public class SaafFunctionRecordsServer implements ISaafFunctionStatistics {

    public SaafFunctionRecordsServer() {
        super();
    }
    @Autowired
    private DynamicBaseViewObject commonDAO_HI_DY;


    private String sql = "INSERT INTO saaf_function_records (\n" +
        "user_name,\n" +
        "page_url,\n" +
        "page_name,\n" +
        "open_time,\n" +
        "browser,\n" +
        "ip,\n" +
        "CREATED_BY,\n" +
        "LAST_UPDATED_BY,\n" +
        "LAST_UPDATE_LOGIN\n" +
        ")\n" +
        "VALUES\n" +
        "(\n" +
        ":var_user_name,\n" +
        ":var_page_url,\n" +
        ":var_page_name,\n" +
        ":var_open_time,\n" +
        ":var_browser,\n" +
        ":var_ip,\n" +
        ":var_userId,\n" +
        ":var_userId,\n" +
        ":var_userId\n" +
        ");\n" +
        "\n";


    /**
     * 保存用户记录
     *
     * @param parameters
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public JSONObject save(JSONObject parameters) throws Exception {
        int userId = parameters.getInteger("varUserId");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("var_user_name", parameters.get("varUserName"));
        map.put("var_page_url", parameters.get("var_page_url"));
        map.put("var_page_name", parameters.get("var_page_name"));
        map.put("var_open_time", new Date());
        map.put("var_browser", parameters.get("var_browser"));
        map.put("var_ip", parameters.get("var_ip"));
        map.put("var_userId", userId);
        commonDAO_HI_DY.executeUpdate(sql, map);
        return SToolUtils.convertResultJSONObj("S", "保存用户记录成功!", 0, null);
    }
}
