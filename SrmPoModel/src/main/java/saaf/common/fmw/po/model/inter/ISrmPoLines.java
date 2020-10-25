package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.po.model.entities.readonly.SrmPoLinesEntity_HI_RO;

import java.util.List;
/**
 * Project Name：ISrmPoLines
 * Company Name：SIE
 * Program Name：
 * Description：采购订单行
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
public interface ISrmPoLines {
    /**
     * Description：目录采购查询list（分页）
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
    public Pagination<SrmPoLinesEntity_HI_RO> findSrmPoLinesInfoList(JSONObject jsonParams, Integer pageIndex, Integer pageRows);


    /**
     * Description：根据categoryId获取采购分类的父节点及其子节点的categoryIdList
     * @param categoryId 品类ID
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public String findCategoryIdList(Integer categoryId);

    /**
     * Description：订单确认（确认APPROVED）——内部，所有订单行的反馈状态改为反馈审核通过——系统代办通知
     * @param jsonParams 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject updatePoLineStatus(JSONObject jsonParams);


    /**
     * Description：订单反馈（确认CONFIRMED）——供应商，订单行的反馈状态改为已确认CONFIRMED，反馈内容改为确认CONFIRM——系统代办通知
     * @param jsonParams 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject updatePoLineFeedBackStatus(JSONObject jsonParams);

    /**
     * Description：拆分订单行
     * @param jsonParams 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject saveSplitPoLine(JSONObject jsonParams) throws Exception;


    /**
     * Description：取消订单行
     * @param jsonParams 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject saveCancelPoLine(JSONObject jsonParams) throws Exception;


    /**
     * Description：结算订单行
     * @param jsonParams 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject saveSettlePoLine(JSONObject jsonParams) throws Exception;


    /**
     * Description：简单查询PO行
     * @param jsonParams 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public List<SrmPoLinesEntity_HI_RO> findPoLineSimpleList(JSONObject jsonParams);
}
