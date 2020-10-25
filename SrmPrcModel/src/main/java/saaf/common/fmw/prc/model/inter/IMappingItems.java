package saaf.common.fmw.prc.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.prc.model.entities.readonly.SrmPrcMappingItmesEntity_HI_RO;
import saaf.common.fmw.prc.model.entities.readonly.SrmPrcMappingSuppliersEntity_HI_RO;

import java.util.List;

public interface IMappingItems {

    /**
     * 查询指标关联物料 （可分页）
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    Pagination<SrmPrcMappingItmesEntity_HI_RO> findMappingItemsPage(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 删除指标关联物料
     *
     * @param params
     * @return
     */
    List<SrmPrcMappingSuppliersEntity_HI_RO> findByMappingItemId(JSONObject params) throws Exception;

    /**
     * 删除指标关联物料
     *
     * @param params
     * @return
     */
    JSONObject deleteMappingItems(JSONObject params) throws Exception;

    /**
     * 状态变更（确认）
     *
     * @param params
     * @return
     */
    JSONObject updateMappingHeaderStatus(JSONObject params) throws Exception;

    /**
     * 查询指标关联物料lov
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     */
    Pagination<SrmPrcMappingItmesEntity_HI_RO> findMappingItemsLov(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 保存关联物料
     *
     * @param params
     * @return
     * @throws Exception
     */
    JSONObject saveItems(JSONObject params,String status) throws Exception;/**
     * 保存关联物料
     *
     * @param params
     * @return
     * @throws Exception
     */
    JSONObject saveItemsList(JSONObject params) throws Exception;

    /**
     * 查询详情
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     */
    Pagination<SrmPrcMappingItmesEntity_HI_RO> findByMappingHeadreId(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询头信息
     *
     * @param params
     * @return
     * @throws Exception
     */
    SrmPrcMappingItmesEntity_HI_RO findByHeadreId(JSONObject params) throws Exception;

    /**
     *  查询物料编码是否存在
     * @param params
     * @return flase/true
     * @throws Exception
     */
    boolean findByNumbereaderId(JSONObject params) throws Exception;

    /**
     * 查询指标编码是否已有生效行
     * @param parmas
     * @return
     * @throws Exception
     */
    boolean findbyIndicatorNumber(JSONObject parmas) throws Exception;
}
