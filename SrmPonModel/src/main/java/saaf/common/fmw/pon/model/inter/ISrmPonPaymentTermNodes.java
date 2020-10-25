package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pon.model.entities.SrmPonPaymentTermNodesEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonPaymentTermNodesEntity_HI_RO;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonPaymentTermNodes.java
 * Description：寻源--付款节点信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonPaymentTermNodes {

    /**
     * Description：付款节点查询（不分页）
     * @param jsonParams
     * @return List<SrmPonPaymentTermNodesEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public List<SrmPonPaymentTermNodesEntity_HI_RO> findSrmPonPaymentTermNodesInfo(JSONObject jsonParams);

    /**
     * Description：删除付款节点——根据主键Id（单条数据）
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject deleteTermCodeId(JSONObject jsonParams);

    /**
     * Description：保存付款节点List（需要传入付款条件的paymentTermCode）
     * @param paymentTermNodesList
     * @param userId
     * @param paymentTermCode
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public void savePaymentTermNodesList(List<SrmPonPaymentTermNodesEntity_HI> paymentTermNodesList,Integer userId,String paymentTermCode);

    /**
     * Description：根据付款节点获取付款条件名称
     * @param paymentTermNodesList
     * @return String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public String getPaymentTermName(List<SrmPonPaymentTermNodesEntity_HI> paymentTermNodesList);
}
