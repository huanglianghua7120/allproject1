package saaf.common.fmw.okc.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.okc.model.entities.SrmOkcFreightExpensesEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcFreightExpensesEntity_HI_RO;
import saaf.common.fmw.okc.model.dao.SrmOkcFreightExpensesDAO_HI;
import saaf.common.fmw.okc.model.dao.readonly.SrmOkcFreightExpensesDAO_HI_RO;
import saaf.common.fmw.okc.model.inter.ISrmOkcFreightExpenses;
import saaf.common.fmw.okc.model.inter.server.SrmOkcFreightExpensesServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SrmOkcFreightExpensesServer implements ISrmOkcFreightExpenses {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmOkcFreightExpensesServer.class);

    @Autowired
    private ViewObject<SrmOkcFreightExpensesEntity_HI> srmOkcFreightExpensesDAO_HI;

    @Autowired
    private BaseViewObject<SrmOkcFreightExpensesEntity_HI_RO> srmOkcFreightExpensesDAO_HI_RO;


    /**
     * Description：保存运价单信息
     * @param parameters
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    public String saveSrmOkcFreightExpenses(JSONObject parameters) throws Exception {
        LOGGER.info("参数：" + parameters.toString());
        try {
            SrmOkcFreightExpensesEntity_HI srmOkcFreightExpensesEntity_HI = JSON.parseObject(parameters.toString(), SrmOkcFreightExpensesEntity_HI.class);
            Map<String, Object> map = new HashMap<>();
            map.put("carrierId", srmOkcFreightExpensesEntity_HI.getCarrierId());
            map.put("supplierId", srmOkcFreightExpensesEntity_HI.getSupplierId());
            map.put("supplierSiteId", srmOkcFreightExpensesEntity_HI.getSupplierSiteId());
            map.put("organizationId", srmOkcFreightExpensesEntity_HI.getOrganizationId());
            map.put("transportMethod", srmOkcFreightExpensesEntity_HI.getTransportMethod());
            map.put("uomCode", srmOkcFreightExpensesEntity_HI.getUomCode());
            boolean flag = validateDuplication(srmOkcFreightExpensesEntity_HI.getFreightExpenseId(), map);
            if (flag) {
                return "D";
            }
            if (srmOkcFreightExpensesEntity_HI.getFreightExpenseId() != null && srmOkcFreightExpensesEntity_HI.getFreightExpenseId() > 0) {
                srmOkcFreightExpensesDAO_HI.update(srmOkcFreightExpensesEntity_HI);
            } else {
                srmOkcFreightExpensesEntity_HI.setVersionNum(1);
                srmOkcFreightExpensesDAO_HI.save(srmOkcFreightExpensesEntity_HI);
            }
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("保存运价单信息异常："+e.getMessage());
        }
        return "S";
    }

    /**
     * Description：验证运价单是否重复
     * @param freightExpenseId
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    private boolean validateDuplication(Integer freightExpenseId, Map<String, Object> map) {
        StringBuffer queryString = new StringBuffer(SrmOkcFreightExpensesEntity_HI_RO.QUERY_FREIGHT_PRICE_SQL);
        List<SrmOkcFreightExpensesEntity_HI_RO> freightExpensesList = srmOkcFreightExpensesDAO_HI_RO.findList(queryString, map);
        if (freightExpenseId == null && freightExpensesList.size() > 0) {
            return true;
        }
        if (freightExpenseId != null && freightExpensesList.size() > 1) {
            return true;
        }
        return false;
    }

    /**
     * Description：查询运价单
     * @param parameters
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    public Pagination findSrmOkcFreightExpensesList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info("参数：" + parameters.toString());
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcFreightExpensesEntity_HI_RO.SRM_OKC_FREIGHT_EXPENSES_QUERY_SQL);
            Map<String, Object> map = new HashMap();
            SaafToolUtils.parperParam(parameters, "ofe.carrier_id", "carrierId", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "ofe.supplier_id", "supplierId", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "ofe.transport_method", "transportMethod", queryString, map, "=");
			SaafToolUtils.parperParam(parameters, "ofe.organization_id", "organizationId", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "ofe.start_date", "startDate", queryString, map, ">");
            SaafToolUtils.parperParam(parameters, "ofe.end_date", "endDate", queryString, map, "<");
            String startDate = parameters.getString("startDate");
            if (startDate != null && !"".equals(startDate.trim())) {
                queryString.append(" AND ofe.start_date >= to_date(:startDate, 'yyyy-mm-dd')\n");
                map.put("startDate", startDate);
            }
            String endDate = parameters.getString("endDate");
            if (endDate != null && !"".equals(endDate.trim())) {
                queryString.append(" AND (ofe.start_date IS NULL OR ofe.start_date <= to_date(:endDate, 'yyyy-mm-dd'))\n");
                map.put("endDate", endDate);
            }
            String countSql = "select count(1) from (" + queryString + ")";
            Pagination<SrmOkcFreightExpensesEntity_HI_RO> resultSet = srmOkcFreightExpensesDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return resultSet;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("保存运价单信息异常："+e.getMessage());
        }
    }

    /**
     * Description：根据ID查询运价单
     * @param parameters
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    public SrmOkcFreightExpensesEntity_HI_RO findSrmOkcFreightExpensesById(JSONObject parameters) throws Exception {
        LOGGER.info("参数：" + parameters.toString());
        try {
            StringBuffer queryString = new StringBuffer(SrmOkcFreightExpensesEntity_HI_RO.SRM_OKC_FREIGHT_EXPENSES_QUERY_SQL);
            queryString.append(" AND su.user_id = :userId");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", parameters.getInteger("userId"));
            SrmOkcFreightExpensesEntity_HI_RO srmOkcFreightExpensesEntity_HI_RO = srmOkcFreightExpensesDAO_HI_RO.findList(queryString, map).get(0);
            return srmOkcFreightExpensesEntity_HI_RO;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("保存运价单信息异常："+e.getMessage());
        }
    }

    /**
     * Description：查询运费单价
     * @param queryParamMap
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    public SrmOkcFreightExpensesEntity_HI_RO findFreightPrice(Map<String, Object> queryParamMap) {
        StringBuffer queryString = new StringBuffer(SrmOkcFreightExpensesEntity_HI_RO.QUERY_FREIGHT_PRICE_SQL);
        List<SrmOkcFreightExpensesEntity_HI_RO> freightExpensesList = srmOkcFreightExpensesDAO_HI_RO.findList(queryString, queryParamMap);
        if (freightExpensesList.size() > 0) {
            return freightExpensesList.get(0);
        }
        return null;
    }

    /**
     * Description：删除指定ID的运价单
     * @param freightExpenseId
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    public void deleteFreightExpense(Integer freightExpenseId) throws Exception {
        SrmOkcFreightExpensesEntity_HI freightExpense = srmOkcFreightExpensesDAO_HI.getById(freightExpenseId);
        if (freightExpense != null) {
            srmOkcFreightExpensesDAO_HI.delete(freightExpense);
        }
    }
}
