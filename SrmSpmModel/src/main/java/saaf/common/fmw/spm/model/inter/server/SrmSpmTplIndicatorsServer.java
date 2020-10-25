package saaf.common.fmw.spm.model.inter.server;

import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.spm.model.inter.ISrmSpmTplIndicators;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.spm.model.entities.SrmSpmTplIndicatorsEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorsEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplIndicatorOwerEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplIndicatorsEntity_HI_RO;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

@Component("srmSpmTplIndicatorsServer")
public class SrmSpmTplIndicatorsServer implements ISrmSpmTplIndicators {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmTplIndicatorsServer.class);

    @Autowired
    private ViewObject<SrmSpmTplIndicatorsEntity_HI> srmSpmTplIndicatorsDAO_HI;

    @Autowired
    private BaseViewObject<SrmSpmTplIndicatorsEntity_HI_RO> srmSpmTplIndicatorsDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmSpmIndicatorsEntity_HI_RO> srmGlStatementLinesDAO_HI_RO;

    @Autowired
    private BaseViewObject<SaafLookupValuesEntity_HI_RO> saafLookupValuesDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmSpmTplIndicatorOwerEntity_HI_RO> srmSpmTplIndicatorOwerEntityDAO_HI_RO;

    public SrmSpmTplIndicatorsServer() {
        super();
    }
    /**
     * 查询模板指标
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmSpmTplIndicatorsEntity_HI> findSrmSpmTplIndicatorsInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<SrmSpmTplIndicatorsEntity_HI> findListResult = srmSpmTplIndicatorsDAO_HI.findList("from SrmSpmTplIndicatorsEntity_HI", queryParamMap);
        return findListResult;
    }
    /**
     * 保存模板指标
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Object saveSrmSpmTplIndicatorsInfo(JSONObject queryParamJSON) {
        SrmSpmTplIndicatorsEntity_HI srmSpmTplIndicatorsEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmSpmTplIndicatorsEntity_HI.class);
        Object resultData = srmSpmTplIndicatorsDAO_HI.save(srmSpmTplIndicatorsEntity_HI);
        return resultData;
    }
    /**
     * Description：查询绩效模板评价指标
     * @param jsonParams 评价指标查询参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SrmSpmTplIndicatorsEntity_HI_RO> getInvoiceLineList(JSONObject jsonParams) {
        // TODO Auto-generated method stub
        LOGGER.info(jsonParams.toString());
        List<SrmSpmTplIndicatorsEntity_HI_RO> listRO = new ArrayList<SrmSpmTplIndicatorsEntity_HI_RO>();
        StringBuffer queryParam = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.QUERY_DIMENSION_INFO_LIST);
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        SaafToolUtils.parperParam(jsonParams, "sti.TPL_DIMENSION_ID", "tplDimensionId", queryParam, queryParamMap, "=");
        List<SrmSpmTplIndicatorsEntity_HI_RO> list = srmSpmTplIndicatorsDAO_HI_RO.findList(queryParam, queryParamMap);
/*        if (!list.isEmpty()) {
            for (SrmSpmTplIndicatorsEntity_HI_RO rowId : list) {
                SrmSpmTplIndicatorsEntity_HI_RO Hrod = new SrmSpmTplIndicatorsEntity_HI_RO();
                String postCode = "";
                String postName = "";
                Map<String, Object> queryParamMapt = new HashMap<String, Object>();
                StringBuffer queryString = new StringBuffer(SrmSpmTplIndicatorOwerEntity_HI_RO.COUNT_QUERY_FLAG);
                queryString.append(" AND ow.TPL_INDICATOR_ID = " + rowId.getTplIndicatorId() + " ");
                List<SrmSpmTplIndicatorOwerEntity_HI_RO> listOw = srmSpmTplIndicatorOwerEntityDAO_HI_RO.findList(queryString, queryParamMapt);
                if (!listOw.isEmpty()) {
                    for (int y = 0; y < listOw.size(); y++) {
                        if (y < listOw.size() - 1) {
                            postCode += listOw.get(y).getPostCode() + ",";
                            postName += listOw.get(y).getPostName() + ",";
                        } else {
                            postCode += listOw.get(y).getPostCode();
                            postName += listOw.get(y).getPostName();
                        }
                    }
                } else {
                    postCode = "0";
                }
                Hrod.setPostCode(postCode);
                Hrod.setPostName(postName);
                Hrod.setTplIndicatorId(rowId.getTplIndicatorId());
                Hrod.setTplDimensionId(rowId.getTplDimensionId());
                Hrod.setDimensionWeight(rowId.getDimensionWeight());
                Hrod.setIndicatorId(rowId.getIndicatorId());
                Hrod.setTargetValue(rowId.getTargetValue());
                Hrod.setDescription(rowId.getDescription());
                Hrod.setScoreDeductingLimit(rowId.getScoreDeductingLimit());
                Hrod.setIndicatorCode(rowId.getIndicatorCode());
                Hrod.setIndicatorName(rowId.getIndicatorName());
                Hrod.setIndicatorType(rowId.getIndicatorType());
                Hrod.setIndicatorTypeName(rowId.getIndicatorTypeName());
                Hrod.setScoreExplain(rowId.getScoreExplain());
                Hrod.setIndicatorValueType(rowId.getIndicatorValueType());
                Hrod.setScoregLimit(rowId.getScoregLimit());
                listRO.add(Hrod);
            }
        }
        return listRO;*/
        return list;
    }

    /**
     * 查询模板指标CODE
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Map<String, Object> selectIndicatorCode(JSONObject jsonParam) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer(SrmSpmIndicatorsEntity_HI_RO.QUERY_SUPPLIER_INFO_LIST);
        queryString.append(" AND sp.APPLICATION_DOMAIN='" + jsonParam.getString("tplDomain") + "' AND sp.INDICATOR_DIMENSION='" + jsonParam.getString("evaluateDimension") + "' AND sp.INDICATOR_CODE='" + jsonParam.getString("indicatorCode") + "' AND sp.INDICATOR_STATUS='ACTIVE' LIMIT 0,1 ");
        List<SrmSpmIndicatorsEntity_HI_RO> list = srmGlStatementLinesDAO_HI_RO.findList(queryString, queryParamMap);
        if (!list.isEmpty()) {
            map.put("indicator", list.get(0));
        }
        return map;
    }

    /**
     *
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SaafLookupValuesEntity_HI_RO> findInvoicetplPost(JSONObject paramJSON, Integer pageIndex, Integer pageRows) throws Exception{
        try {
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer queryParam = new StringBuffer(SaafLookupValuesEntity_HI_RO.QUERY_INVOICEIDT_SQL);
            SaafToolUtils.parperParam(paramJSON, "slv.lookup_code", "postCode", queryParam, queryParamMap, "=");
            SaafToolUtils.parperParam(paramJSON, "slv.meaning", "postName", queryParam, queryParamMap, "LIKE");
            queryParam.append(" AND slv.lookup_type = 'EMPLOYEE_POST' AND slv.enabled_flag = 'Y' ORDER BY slv.lookup_code");
            String countSql = "select count(1) from (" + queryParam + ")";
            Pagination<SaafLookupValuesEntity_HI_RO> findPagination = saafLookupValuesDAO_HI_RO.findPagination(queryParam.toString(),countSql, paramJSON, pageIndex, pageRows);
            return findPagination;
        } catch (Exception e) {
            //e.printStackTrace();
            throw new UtilsException("查询失败");
        }
    }

    /**
     *查询绩效模板的指标信息
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Map<String, Object> findTplIndicatorsList(JSONObject jsonParam) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.QUERY_TPL_INDICATORS_LIST);
        queryString.append(" AND Spt.Tpl_Id = " + jsonParam.getInteger("tplId"));
        List<SrmSpmTplIndicatorsEntity_HI_RO> list = srmSpmTplIndicatorsDAO_HI_RO.findList(queryString, queryParamMap);
        if (!list.isEmpty()) {
            map.put("list", list);
        }
        return map;
    }

}
