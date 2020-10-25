package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.SrmPoForecastLinesEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoForecastLinesEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoForecastLines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;

/**
 * Project Name：SrmPoForecastLinesServer
 * Company Name：SIE
 * Program Name：
 * Description：采购预测行
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoForecastLinesServer")
public class SrmPoForecastLinesServer implements ISrmPoForecastLines {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoForecastLinesServer.class);

    @Autowired
    private ViewObject<SrmPoForecastLinesEntity_HI> srmPoForecastLinesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPoForecastLinesEntity_HI_RO> srmPoForecastLinesDAO_HI_RO;

    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;

    public SrmPoForecastLinesServer() {
        super();
    }

    /**
     * Description：采购预测行删除
     * @param jsonParam 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteForecastLine(JSONObject jsonParam) throws Exception {
        if (jsonParam.getInteger("forecastLineId") != null && jsonParam.getInteger("forecastLineId") > 0) {
            SrmPoForecastLinesEntity_HI row = srmPoForecastLinesDAO_HI.getById(jsonParam.getInteger("forecastLineId"));
            if(null == row){
                return SToolUtils.convertResultJSONObj(ERROR_STATUS,"查无此记录，已被删除",0,null);
            }
            srmPoForecastLinesDAO_HI.delete(jsonParam.getInteger("forecastLineId"));
        }else{
            SToolUtils.convertResultJSONObj(ERROR_STATUS,"参数为空",0,null);
        }
        return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
    }

    /**
     * Description：采购预测物料查询
     * @param jsonParams 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoForecastLinesEntity_HI_RO> findPoForecastItemLov(JSONObject jsonParams, Integer pageIndex,
                                                                            Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer queryString = new StringBuffer(SrmPoForecastLinesEntity_HI_RO.QUERY_ITEM_LOV_SQL);
        StringBuffer appendString = new StringBuffer();
        //StringBuffer countString = new StringBuffer(SrmPoForecastLinesEntity_HI_RO.COUNT_ITEM_LOV_SQL);
        SaafToolUtils.parperParam(jsonParams, "sbi.item_id", "itemId", appendString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_code", "itemCode", appendString, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_status", "itemStatus", appendString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_name", "itemName", appendString, queryParamMap, "LIKE"); //供应商查询
        SaafToolUtils.parperParam(jsonParams, "sbc.full_category_name", "fullCategoryName", appendString, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "sbc.full_category_code", "fullCategoryCode", appendString, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "sbi.organization_id", "organizationId", appendString, queryParamMap, "=");
        //采购分类过滤
        if ("Y".equals(jsonParams.getString("categoryLimitFlag"))){
                /*String categoriesIdStr = srmBaseUserCategories.findUserCategoriesIdStr(jsonParams.getInteger("varUserId"));
                if (null != categoriesIdStr && !"".equals(categoriesIdStr)){
                    appendString.append(" AND sbi.category_id in (" + categoriesIdStr + ")");
                }*/
                /*appendString.append(" and sbi.Organization_Id IN\n" +
                        "       (SELECT Sai.Inst_Id\n" +
                        "          FROM Saaf_Institution Sai\n" +
                        "         WHERE Sai.Parent_Inst_Id = " +jsonParams.getInteger("parentInstId")+
                        "           AND Sai.Inst_Type = 'ORGANIZATION') "+
                        "        AND sbi.Category_Id IN (" +getCategoryId(jsonParams.getInteger("varUserId"))+")");*/
            appendString.append(" AND sbi.Category_Id IN (" +getCategoryId(jsonParams.getInteger("varUserId"))+")");
        }



        queryString.append(appendString);
        String countSql = "select count(1) from (" + queryString + ")";
        //countString.append(appendString);
        //Pagination<SrmPoForecastLinesEntity_HI_RO> result = srmPoForecastLinesDAO_HI_RO.findPagination(queryString, countString, queryParamMap, pageIndex, pageRows);
        Pagination<SrmPoForecastLinesEntity_HI_RO> result = srmPoForecastLinesDAO_HI_RO.findPagination(queryString,countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：获取品类ID
     * @param userId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private String getCategoryId(Integer userId){
        JSONObject json=new JSONObject();
        json.put("userId",userId);
        List<SrmBaseUserCategoriesEntity_HI_RO> userCategoriesList=srmBaseUserCategories.findUserCategories(json);
        List  categoryIds=new ArrayList();
        for(SrmBaseUserCategoriesEntity_HI_RO ro : userCategoriesList){
            categoryIds.add(ro.getCategoryId().toString());
        }
        String categoryId= String.valueOf(categoryIds.stream().distinct().collect(Collectors.joining(",")));
        return categoryId;
    }

    /**
     * Description：采购预测物料查询
     * @param jsonParams 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoForecastLinesEntity_HI_RO> findPoForecastItemLovForLine(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer queryString = new StringBuffer(SrmPoForecastLinesEntity_HI_RO.QUERY_ITEM_LOV_SQL);
        StringBuffer appendString = new StringBuffer();
        //StringBuffer countString = new StringBuffer(SrmPoForecastLinesEntity_HI_RO.COUNT_ITEM_LOV_SQL);
        SaafToolUtils.parperParam(jsonParams, "sbi.item_id", "itemId", appendString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_code", "itemCode", appendString, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_status", "itemStatus", appendString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_name", "itemName", appendString, queryParamMap, "LIKE"); //供应商查询
        SaafToolUtils.parperParam(jsonParams, "sbc.full_category_name", "fullCategoryName", appendString, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "sbc.full_category_code", "fullCategoryCode", appendString, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "sbi.organization_id", "organizationId", appendString, queryParamMap, "=");
        appendString.append("\t AND sbc.leaf_flag = 'Y' \t\n");
        //采购分类过滤
        if ("Y".equals(jsonParams.getString("categoryLimitFlag"))){
                /*String categoriesIdStr = srmBaseUserCategories.findUserCategoriesIdStr(jsonParams.getInteger("varUserId"));
                if (null != categoriesIdStr && !"".equals(categoriesIdStr)){
                    appendString.append(" AND sbi.category_id in (" + categoriesIdStr + ")");
                }*/
            appendString.append(" AND sbi.Category_Id IN (" +getCategoryId(jsonParams.getInteger("varUserId"))+")");
        }
        queryString.append(appendString);
        //countString.append(appendString);
        String countSql = "select count(1) from (" + queryString + ")";
        Pagination<SrmPoForecastLinesEntity_HI_RO> result = srmPoForecastLinesDAO_HI_RO.findPagination(queryString,countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

}
