package saaf.common.fmw.base.model.inter;


import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.model.entities.SaafInstitutionEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafInstitutionEntity_HI_RO;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafInstitution.java
 * Description：组织维护接口
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface ISaafInstitution {

    /**
     * LOV:srm查询业务实体Lov
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination<SaafInstitutionEntity_HI_RO> findInstitutionListLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * LOV:srm查询组织
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination<SaafInstitutionEntity_HI_RO> queryInstitution(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;


    /**
     * LOV:查询机构名称
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination<SaafInstitutionEntity_HI_RO> findInstNameLov(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 条件查询组织机构
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination<SaafInstitutionEntity_HI_RO> findInstitution(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * Description：查询组织机构列表
     *
     * @param parameters 物料的JSON格式数据
     * @return 对象
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-03     SIE 谢晓霞          创建
     * ==============================================================================
     */
    List<SaafInstitutionEntity_HI_RO> findInstitutionList(JSONObject parameters) throws Exception;

    /**
     * 查询机构名称Tree(用户组织关系维护)
     *
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    List<SaafInstitutionEntity_HI_RO> findInstNameTree(JSONObject parameters) throws Exception;

    /**
     * 查询组织架构ROW
     *
     * @param map
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    SaafInstitutionEntity_HI findInstitutionLine(Map<String, Object> map) throws Exception;

    /**
     * 保存组织
     *
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    JSONObject saveInstitution(JSONObject parameters) throws Exception;

    /**
     * 删除组织机构
     *
     * @param instId
     * @return
     * @throws Exception
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    JSONObject deleteInstitution(Integer instId) throws Exception;

    /**
     * 根据用户ID查询组织机构列表
     *
     * @param jsonParams
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination<SaafInstitutionEntity_HI_RO> findSaafInstitutionByUserId(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * srm根据物料查询组织
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination<SaafInstitutionEntity_HI_RO> queryItemInstitution(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询当前登录用户的组织表的业务实体/库存组织的权限范围（分页）
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public Pagination<SaafInstitutionEntity_HI_RO> findInstitutionListByUserId(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

    /**
     * 查询指定业务实体下的库存组织
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public Pagination<SaafInstitutionEntity_HI_RO> queryInstitutionByOrg(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询有效的部门信息
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination<SaafInstitutionEntity_HI_RO> findInstitutionByDept(JSONObject parameters, Integer pageIndex, Integer pageRows);

}
