package saaf.common.fmw.po.model.inter.server;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.SrmPoSupplyProportionLEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoSupplyProportionLEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoSupplyProportionL;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供货比例行表
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-20     hgq             modify
 * ==============================================================================
 */
@Component("srmPoSuppluProportionLServer")
public class SrmPoSuppluProportionLServer implements ISrmPoSupplyProportionL {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoSuppluProportionLServer.class);


    @Autowired
    private BaseViewObject<SrmPoSupplyProportionLEntity_HI_RO> baseViewObject;

    @Autowired
    private ViewObject<SrmPoSupplyProportionLEntity_HI> srmPoSupplyProportionLDAO_HI;
    /**
     * Description：查询供货比例行表
     * @param parameters 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    @Override
    public Pagination<SrmPoSupplyProportionLEntity_HI_RO> findSupplyProportionL(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer queryString = new StringBuffer(SrmPoSupplyProportionLEntity_HI_RO.QUERY_SUPPLY_PROPORTION_L_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(parameters, "spspl.supply_id", "supplyId", queryString, map, "=");
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" order by spspl.LAST_UPDATE_DATE desc");
        Pagination<SrmPoSupplyProportionLEntity_HI_RO> h2Entity_HI_ROs = baseViewObject.findPagination(queryString,countSql, map, 0, 0);
        return h2Entity_HI_ROs;
    }
    /**
     * Description：保存供货列表详情
     * @param params 参数
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    @Override
    public JSONObject saveSupplyProportionL(JSONObject params) throws Exception {
        SrmPoSupplyProportionLEntity_HI entity_HI = null;
        JSONArray valuesArray = params.getJSONArray("spLData");
        if(null == valuesArray || valuesArray.isEmpty()){
            SToolUtils.convertResultJSONObj(ERROR_STATUS,"暂无数据",0,null);
        }
        Integer varUserId = params.getInteger("varUserId");
        LOGGER.info("->>>" + params);
        for (int i = 0; i < valuesArray.size(); i++) {
            JSONObject valuesJson = valuesArray.getJSONObject(i);
            LOGGER.info("->>>" + valuesJson.toJSONString());
            //如果id为空则新增 否则为修改
            if (null == valuesJson.get("supplyDetailId") || "".equals(valuesJson.get("supplyDetailId").toString().trim())) {
                //新增
                if (valuesJson.getInteger("supplyId") == null) {
                    throw new UtilsException("请选择要保存的头");
                }
                entity_HI = new SrmPoSupplyProportionLEntity_HI();
                entity_HI.setOperatorUserId(varUserId);
                entity_HI.setSupplierId(valuesJson.getInteger("supplierId"));
                entity_HI.setSupplyId(valuesJson.getInteger("supplyId"));
                entity_HI.setSupplierNmuber(valuesJson.getString("supplierNmuber"));
                entity_HI.setSupplierName(valuesJson.getString("supplierName"));
                entity_HI.setProportion(valuesJson.getBigDecimal("proportion"));
                entity_HI.setEnableFlag(valuesJson.getString("enableFlag"));
                entity_HI.setCreationDate(new Date());
                entity_HI.setCreatedBy(varUserId);
                entity_HI.setLastUpdateDate(new Date());
                entity_HI.setLastUpdatedBy(varUserId);
                srmPoSupplyProportionLDAO_HI.save(entity_HI);
            } else {
                //修改
                entity_HI = new SrmPoSupplyProportionLEntity_HI();
                entity_HI.setOperatorUserId(varUserId);
                entity_HI.setSupplyDetailId(valuesJson.getInteger("supplyDetailId"));
                entity_HI.setSupplierId(valuesJson.getInteger("supplierId"));
                entity_HI.setSupplyId(valuesJson.getInteger("supplyId"));
                entity_HI.setSupplierNmuber(valuesJson.getString("supplierNmuber"));
                entity_HI.setSupplierName(valuesJson.getString("supplierName"));
                entity_HI.setProportion(valuesJson.getBigDecimal("proportion"));
                entity_HI.setEnableFlag(valuesJson.getString("enableFlag"));
                entity_HI.setCreationDate(new Date());
                entity_HI.setCreatedBy(varUserId);
                entity_HI.setLastUpdateDate(new Date());
                entity_HI.setLastUpdatedBy(varUserId);
                LOGGER.info("------>>" + entity_HI);
                srmPoSupplyProportionLDAO_HI.update(entity_HI);
            }
        }
        return SToolUtils.convertResultJSONObj("S", "保存供货比例信息成功", 1, entity_HI);
    }
}
