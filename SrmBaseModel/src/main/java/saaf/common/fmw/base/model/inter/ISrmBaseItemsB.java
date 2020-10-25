package saaf.common.fmw.base.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.base.model.entities.SrmBaseItemsBEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseItemsBEntity_HI_RO;
import saaf.common.fmw.base.model.dao.SrmBaseItemsBDAO_HI;
import saaf.common.fmw.base.model.dao.readonly.SrmBaseItemsBDAO_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseItemsB;

import java.util.List;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：ISrmBaseItemsB.java
 * Description：物料基表的Server层接口类
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019-05-29     秦晓钊          创建
 * ==============================================================================
 */
public interface ISrmBaseItemsB {

    /**
     * Description：保存物料，自动生成的方法
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * itemId  物料ID  NUMBER  Y
     * itemCode  物料编码  VARCHAR2  Y
     * itemName  物料名称  VARCHAR2  Y
     * itemDescription  物料说明  VARCHAR2  N
     * globalFlag  全局标识  VARCHAR2  N
     * enabledAsl  启用ASL  VARCHAR2  N
     * uomCode  计量单位  VARCHAR2  N
     * itemStatus  物料状态  VARCHAR2  N
     * purchasingFlag  可采购标识  VARCHAR2  N
     * agentId  采购员ID  NUMBER  N
     * categoryId  采购分类ID  NUMBER  Y
     * purchaseCycle  采购周期  NUMBER  N
     * purchasingLeadTime  采购提前期  NUMBER  N
     * minPacking  最小包装量  NUMBER  N
     * benchmarkPrice  基准价  NUMBER  N
     * invalidDate  失效时间  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * imageId  图片ID  NUMBER  N
     * itemCode  物料编码  VARCHAR2  Y
     * itemName  物料名称  VARCHAR2  Y
     * itemDescription  物料说明  VARCHAR2  N
     * globalFlag  全局标识(Y/N)  VARCHAR2  N
     * enabledAsl  启用ASL  VARCHAR2  N
     * uomCode  计量单位  VARCHAR2  N
     * itemStatus  物料状态  VARCHAR2  N
     * purchasingFlag  可采购标识  VARCHAR2  N
     * agentId  采购员ID  NUMBER  N
     * categoryId  采购分类ID  NUMBER  Y
     * purchaseCycle  采购周期  NUMBER  N
     * purchasingLeadTime  采购提前期  NUMBER  N
     * minPacking  最小包装量  NUMBER  N
     * benchmarkPrice  基准价  NUMBER  N
     * invalidDate  失效时间  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * imageId  图片ID  NUMBER  N
     * cost  成本  NUMBER  N
     * specification  规格型号  VARCHAR2  N
     * region  组织区域  VARCHAR2  N
     * itemId  物料ID  NUMBER  Y
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    public JSONObject saveSrmBaseItemsB(JSONObject parameters) throws Exception;

    /**
     * Description：查询列表，自动生成的方法
     *
     * @param parameters 查询参数，JSON格式数据
     * @param pageIndex  页码
     * @param pageRows   每页显示的行数
     * @return 物料列表
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    public Pagination<SrmBaseItemsBEntity_HI_RO> findSrmBaseItemsBList(JSONObject parameters, Integer pageIndex, Integer pageRows);

    /**
     * Description：查询指定ID的物料，自动生成的方法
     *
     * @param parameters 查询参数，JSON格式数据
     * @return 物料对象
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    public SrmBaseItemsBEntity_HI_RO findSrmBaseItemsBById(JSONObject parameters) throws Exception;


    /**
     * Description：根据指定的业务实体ID，查询物料
     *
     * @param parameters 查询参数，JSON格式数据
     * @param pageIndex  页码
     * @param pageRows   每页显示的行数
     * @return 物料列表
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    public Pagination findItemsBByOrgId(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：失效物料基表
     *
     * @param jsonParams 物料的JSON格式数据
     * @return 对象
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-03-04     吴嘉利          创建
     * ==============================================================================
     */
    JSONObject updateInvalidBaseItemB(JSONObject jsonParams) throws Exception;


    /**
     * Description：删除物料
     *
     * @param jsonParams 物料的JSON格式数据
     * @return 对象
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-03     SIE 谢晓霞          创建
     * ==============================================================================
     */
    public JSONObject deleteBaseItemsB(JSONObject jsonParams) throws Exception;


    /**
     * Description：新增物料主数据导入
     * @param jsonParams 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-07     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject saveImportMaterialMaster(JSONObject jsonParams)throws Exception;

    /**
     * Description：招标询价转订单/合同生成物料主数据
     * @param jsonParams 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-07     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject createPonMaterialMaster(JSONObject jsonParams)throws Exception;

}
