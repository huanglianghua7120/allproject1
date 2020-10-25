package saaf.common.fmw.base.model.inter.server;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.FndFlexValidationTablesEntity_HI;
import saaf.common.fmw.base.model.inter.IFndFlexValidationTables;
import saaf.common.fmw.common.TestToolUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.ViewObject;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：FndFlexValidationTablesServer.java
 * Description：用来处理值集
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
@Component("fndFlexValidationTablesServer")
public class FndFlexValidationTablesServer implements IFndFlexValidationTables {
    @Autowired
    private ViewObject<FndFlexValidationTablesEntity_HI> fndFlexValidationTablesDAO_HI;

    public FndFlexValidationTablesServer() {
        super();
    }
    /**
     * 查询值集信息
     *
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public String findFndFlexValidationTablesInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<FndFlexValidationTablesEntity_HI> findListResult = fndFlexValidationTablesDAO_HI.findList("from FndFlexValidationTablesEntity_HI", queryParamMap);
        String resultData = JSON.toJSONString(findListResult);
        JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
        return resultStr.toString();
    }
    /**
     * 保持值集
     *
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public String saveFndFlexValidationTablesInfo(JSONObject queryParamJSON) {
        FndFlexValidationTablesEntity_HI fndFlexValidationTablesEntity_HI = JSON.parseObject(queryParamJSON.toString(), FndFlexValidationTablesEntity_HI.class);
        Object resultData = fndFlexValidationTablesDAO_HI.save(fndFlexValidationTablesEntity_HI);
        JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
        return resultStr.toString();
    }

    /**
     * 查询值集行集合
     * @param queryParamMap
     * @return
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     HLH             创建
     * ===========================================================================
     */
    public List findFndFlexValidation(Map queryParamMap) {
        List<FndFlexValidationTablesEntity_HI> findListResult = fndFlexValidationTablesDAO_HI.findByProperty(queryParamMap);
        return findListResult;
    }


    public static void main(String[] args) {
        FndFlexValidationTablesServer fndFlexValidationTablesServer = (FndFlexValidationTablesServer)TestToolUtils.context.getBean("fndFlexValidationTablesServer");
        JSONObject paramJSON = new JSONObject();
        //paramJSON.put("ordercode", 1);
        //paramJSON.put("ordercode", 1);
        //paramJSON.put("tid", 1);
        String resultStr = fndFlexValidationTablesServer.findFndFlexValidationTablesInfo(paramJSON);
        System.out.println(resultStr);
    }
}
