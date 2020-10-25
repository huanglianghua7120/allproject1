package saaf.common.fmw.spm.model.inter.server;

import saaf.common.fmw.base.model.entities.SrmBaseCategoriesEntity_HI;
import saaf.common.fmw.spm.model.inter.ISrmSpmTplCategories;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;

import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.spm.model.entities.SrmSpmTplCategoriesEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplCategoriesEntity_HI_RO;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("srmSpmTplCategoriesServer")
public class SrmSpmTplCategoriesServer implements ISrmSpmTplCategories {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmTplCategoriesServer.class);
    @Autowired
    private ViewObject<SrmSpmTplCategoriesEntity_HI> srmSpmTplCategoriesDAO_HI;
    @Autowired
    private ViewObject<SrmBaseCategoriesEntity_HI> srmBaseCategoriesEntityDAO_HI;
    @Autowired
    private BaseViewObject<SrmSpmTplCategoriesEntity_HI_RO> srmSpmTplCategoriesDAO_HI_RO;

    public SrmSpmTplCategoriesServer() {
        super();
    }

    public List<SrmSpmTplCategoriesEntity_HI> findSrmSpmTplCategoriesInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<SrmSpmTplCategoriesEntity_HI> findListResult = srmSpmTplCategoriesDAO_HI.findList("from SrmSpmTplCategoriesEntity_HI", queryParamMap);
        return findListResult;
    }

    public Object saveSrmSpmTplCategoriesInfo(JSONObject queryParamJSON) {
        SrmSpmTplCategoriesEntity_HI srmSpmTplCategoriesEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmSpmTplCategoriesEntity_HI.class);
        Object resultData = srmSpmTplCategoriesDAO_HI.save(srmSpmTplCategoriesEntity_HI);
        return resultData;
    }

    @Override
    public List<SrmSpmTplCategoriesEntity_HI_RO> getperformanceTplItemList(JSONObject jsonParams) {
        LOGGER.info("" + jsonParams);
        StringBuffer queryParam = new StringBuffer(SrmSpmTplCategoriesEntity_HI_RO.QUERY_CATEGORIES_INFO_LIST);
        Map<String, Object> queryParamMap = new HashMap();
        SaafToolUtils.parperParam(jsonParams, "sc.tpl_id", "tplId", queryParam, queryParamMap, "=");
        List<SrmSpmTplCategoriesEntity_HI_RO> list = srmSpmTplCategoriesDAO_HI_RO.findList(queryParam, queryParamMap);
        return list;
    }

    @Override
    public List<SrmSpmTplCategoriesEntity_HI_RO> categoryCodeExport(JSONObject jsonParams) {
        LOGGER.info("" + jsonParams);
        StringBuffer queryParam = new StringBuffer(SrmSpmTplCategoriesEntity_HI_RO.QUERY_CATEGORIES_INFO_LIST);
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        SaafToolUtils.parperParam(jsonParams, "sc.tpl_id", "tplId", queryParam, queryParamMap, "=");
        List<SrmSpmTplCategoriesEntity_HI_RO> list = srmSpmTplCategoriesDAO_HI_RO.findList(queryParam, queryParamMap);
        return list;
    }

    @Override
    public JSONObject savecategoryImport(JSONObject jsonParam) throws Exception {
        JSONArray array = jsonParam.getJSONArray("data");
        Integer userId = jsonParam.getInteger("varUserId");
        String info = jsonParam.getString("info");
        JSONObject jsonParamt = JSON.parseObject(info);
        Integer tplId = jsonParamt.getInteger("tplId");
        if (tplId != 0) {
            List<SrmSpmTplCategoriesEntity_HI> listcate = new ArrayList<SrmSpmTplCategoriesEntity_HI>();
            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonObj = array.getJSONObject(i);
                String categoryCode = jsonObj.getString("categoryCode");
                StringBuffer queryParam = new StringBuffer(SrmSpmTplCategoriesEntity_HI_RO.QUERY_CATEGORIES_INFO_LIST);
                queryParam.append(" and sc.tpl_id=" + tplId + " and st.CATEGORY_CODE='" + categoryCode + "'");
                Map<String, Object> queryParamMap = new HashMap<String, Object>();
                List<SrmSpmTplCategoriesEntity_HI_RO> list = srmSpmTplCategoriesDAO_HI_RO.findList(queryParam, queryParamMap);
                if (list.isEmpty()) {
                    List<SrmBaseCategoriesEntity_HI> listY = srmBaseCategoriesEntityDAO_HI.findByProperty("category_code", categoryCode);
                    if (!listY.isEmpty()) {
                        SrmSpmTplCategoriesEntity_HI entityHi = new SrmSpmTplCategoriesEntity_HI();
                        entityHi.setOperatorUserId(userId);
                        entityHi.setTplId(tplId);
                        entityHi.setCategoryId(listY.get(0).getCategoryId());
                        listcate.add(entityHi);
                    }
                } else {
                    throw new ServerException("已存在同样的记录！【" + categoryCode + "】编码，已经存在");
                }
            }
            srmSpmTplCategoriesDAO_HI.save(listcate);
            return SToolUtils.convertResultJSONObj("S", "分类导入成功", 1L, null);
        } else {
            return SToolUtils.convertResultJSONObj("E", "请先保存模板主数据在导入分类", 1L, null);
        }
    }

}
