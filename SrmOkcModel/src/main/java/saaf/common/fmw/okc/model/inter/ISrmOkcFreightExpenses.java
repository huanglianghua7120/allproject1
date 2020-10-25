package saaf.common.fmw.okc.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.okc.model.entities.SrmOkcFreightExpensesEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcFreightExpensesEntity_HI_RO;
import saaf.common.fmw.okc.model.dao.SrmOkcFreightExpensesDAO_HI;
import saaf.common.fmw.okc.model.dao.readonly.SrmOkcFreightExpensesDAO_HI_RO;
import saaf.common.fmw.okc.model.inter.ISrmOkcFreightExpenses;

import java.util.Map;

public interface ISrmOkcFreightExpenses {


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
    public String saveSrmOkcFreightExpenses(JSONObject parameters) throws Exception;

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
    public Pagination findSrmOkcFreightExpensesList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

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
    public SrmOkcFreightExpensesEntity_HI_RO findSrmOkcFreightExpensesById(JSONObject parameters) throws Exception;

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
    public SrmOkcFreightExpensesEntity_HI_RO findFreightPrice(Map<String, Object> queryParamMap);

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
    public void deleteFreightExpense(Integer freightExpenseId) throws Exception;
}
