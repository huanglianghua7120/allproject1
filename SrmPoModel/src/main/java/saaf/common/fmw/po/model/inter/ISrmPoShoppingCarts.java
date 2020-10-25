package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.po.model.entities.SrmPoShoppingCartsEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoShoppingCartsEntity_HI_RO;

import java.util.List;
/**
 * Project Name：ISrmPoShoppingCarts
 * Company Name：SIE
 * Program Name：
 * Description：购物车
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
public interface ISrmPoShoppingCarts {
    /**
     * Description：批量删除购物车
     * @param jsonParams 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject deleteShoppingCartByBatch(JSONObject jsonParams)throws Exception;

    /**
     * Description：购物车查询list（分页）
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
    public Pagination<SrmPoShoppingCartsEntity_HI_RO> findSrmPoShoppingCartsInfoList(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

    /**
     * Description：保存购物车list
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * shoppingCartId  购物车ID  NUMBER  Y
     * orgId  业务实体ID  NUMBER  Y
     * agreementLineId  框架协议行ID，关联表：srm_po_lines  NUMBER  N
     * demandQty  需求数量  NUMBER  N
     * demandDate  需求时间  DATE  N
     * receiveOrganizationId  接收组织ID  NUMBER  N
     * createdPoFlag  创建采购订单标识（Y/N）  VARCHAR2  N
     * poLineId  采购订单行ID，关联表：srm_po_lines  NUMBER  N
     * shoppingCartId  购物车ID  NUMBER  Y
     * orgId  业务实体ID  NUMBER  Y
     * agreementLineId  框架协议行ID，关联表：srm_po_lines  NUMBER  N
     * demandQty  需求数量  NUMBER  N
     * demandDate  需求时间  DATE  N
     * receiveOrganizationId  接收组织ID  NUMBER  N
     * createdPoFlag  创建采购订单标识（Y/N）  VARCHAR2  N
     * poLineId  采购订单行ID，关联表：srm_po_lines  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    public JSONObject saveShoppingCartsList(JSONObject jsonParams)throws Exception;


    /**
     * Description：加入购物车的保存（单条数据）
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * shoppingCartId  购物车ID  NUMBER  Y
     * orgId  业务实体ID  NUMBER  Y
     * agreementLineId  框架协议行ID，关联表：srm_po_lines  NUMBER  N
     * demandQty  需求数量  NUMBER  N
     * demandDate  需求时间  DATE  N
     * receiveOrganizationId  接收组织ID  NUMBER  N
     * createdPoFlag  创建采购订单标识（Y/N）  VARCHAR2  N
     * poLineId  采购订单行ID，关联表：srm_po_lines  NUMBER  N
     * shoppingCartId  购物车ID  NUMBER  Y
     * orgId  业务实体ID  NUMBER  Y
     * agreementLineId  框架协议行ID，关联表：srm_po_lines  NUMBER  N
     * demandQty  需求数量  NUMBER  N
     * demandDate  需求时间  DATE  N
     * receiveOrganizationId  接收组织ID  NUMBER  N
     * createdPoFlag  创建采购订单标识（Y/N）  VARCHAR2  N
     * poLineId  采购订单行ID，关联表：srm_po_lines  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    public JSONObject saveShoppingCart(JSONObject jsonParams)throws Exception;

    /**
     * Description：分组已确定创建订单的组别——购物车的创建订单 【业务实体+供应商+币种+税率+付款方式+回货方式】相同的产生同一个订单
     * @param poShoppingCartsList 购物车参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public List<List<SrmPoShoppingCartsEntity_HI>> getShoppingCartsGrouping(List<SrmPoShoppingCartsEntity_HI> poShoppingCartsList);


    /**
     * Description：根据poShoppingCartsList创建订单、订单行以及更新购物车的订单标识——购物车的创建订单
     * @param poShoppingCartsList 购物车参数
     * @param userId
     * @param employeeId 员工ID
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public Integer savePoOrderAndShoppingCartAll(Integer userId,Integer employeeId,List<SrmPoShoppingCartsEntity_HI> poShoppingCartsList) throws Exception;
}
