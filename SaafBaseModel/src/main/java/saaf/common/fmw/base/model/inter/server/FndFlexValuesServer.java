package saaf.common.fmw.base.model.inter.server;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.FndFlexValuesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.FndFlexValuesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.IFndFlexValues;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：FndFlexValuesServer.java
 * Description：用来处理值集
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
@Component("fndFlexValuesServer")
public class FndFlexValuesServer implements IFndFlexValues {
    private static final Logger LOGGER = LoggerFactory.getLogger(FndFlexValuesServer.class);
    @Autowired
    private ViewObject<FndFlexValuesEntity_HI> fndFlexValuesDAO_HI;
    @Autowired
    private BaseViewObject<FndFlexValuesEntity_HI_RO> fndFlexValuesDAO_HI_RO;

    public FndFlexValuesServer() {
        super();
    }

    //	@Resource(name = "baseDAO")
    //	private IBaseDAO baseDAO;
    /**
     * 获取活动信息
     *
     * @param
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public String findFndFlexValuesInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<FndFlexValuesEntity_HI> findListResult = fndFlexValuesDAO_HI.findList("from FndFlexValuesEntity_HI", queryParamMap);
        String resultData = JSON.toJSONString(findListResult);
        JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
        return resultStr.toString();
    }

    /**
     * 保存活动信息
     *
     * @param
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public String saveFndFlexValuesInfo(JSONObject queryParamJSON) {
        FndFlexValuesEntity_HI fndFlexValuesEntity_HI = JSON.parseObject(queryParamJSON.toString(), FndFlexValuesEntity_HI.class);
        Object resultData = fndFlexValuesDAO_HI.save(fndFlexValuesEntity_HI);
        JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
        return resultStr.toString();
    }

    /**
     * 查询值集行集合
     * @param queryParamMap
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public List findFndFlexValues(Map queryParamMap) {
        StringBuffer queryString = new StringBuffer();
        queryString.append(FndFlexValuesEntity_HI_RO.QUERY_SQL_M);
        queryString.append(" and t.FLEX_VALUE_SET_ID = :flexValueSetId");
        List<FndFlexValuesEntity_HI_RO> findListResult = fndFlexValuesDAO_HI_RO.findList(queryString, queryParamMap);
        return findListResult;
    }

    /**
     * 查询值集行表ById
     *
     * @param flexValueId
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public FndFlexValuesEntity_HI findFlexValueById(Integer flexValueId) throws Exception {
        try {
            return (FndFlexValuesEntity_HI)fndFlexValuesDAO_HI.findByProperty("flexValueId", flexValueId).get(0);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 删除值集行表值
     *
     * @param params
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public JSONObject deleteFlexValues(JSONObject params) throws Exception {
        try {
            FndFlexValuesEntity_HI flexValuesRow = null;
            Integer flexValueSetId = null;
            if (params.get("flexValueSetId") != null && !"".equals(params.getString("flexValueSetId"))) {
                flexValueSetId = params.getInteger("flexValueSetId");
                flexValuesRow = (FndFlexValuesEntity_HI)fndFlexValuesDAO_HI.findByProperty("flexValueSetId", flexValueSetId).get(0);
                fndFlexValuesDAO_HI.delete(flexValuesRow);
            }
            return SToolUtils.convertResultJSONObj("S", "", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
