package saaf.common.fmw.spm.model.inter;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorItemsEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceTplEntity_HI_RO;

public interface ISrmSpmPerformanceTpl {

    /**
     * Description：查询绩效模板
     * @param paramJSON 绩效模板查询参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	Pagination<SrmSpmPerformanceTplEntity_HI_RO> queryPerformanceTplDetail(JSONObject paramJSON, Integer pageIndex,Integer pageRows);
    /**
     * Description：删除绩效模板
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	JSONObject deletePerformanceTpl(JSONObject jsonParams)throws Exception;
    /**
     * Description：查询绩效模板
     * @param tplId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	Map<String, Object> finderformancetpl(Integer tplId)throws Exception;

    /**
     * Description：保存绩效模板
     * @param jsonParams 绩效模板数据
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	JSONObject saveformancetpl(JSONObject jsonParams)throws Exception;
    /**
     * Description：删除绩效模板行
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	JSONObject deleteTplLine(JSONObject jsonParams)throws Exception;
    /**
     * Description：更新状态
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	JSONObject updateApproveformancetpl(JSONObject jsonParams)throws Exception;
    /**
     * Description：更新状态
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	JSONObject updateRejecteformancetpl(JSONObject jsonParams)throws Exception;
    /**
     * Description：绩效模板导出
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	List<SrmSpmPerformanceTplEntity_HI_RO> performanceTplListExport(JSONObject paramJSON)throws Exception;
    /**
     * Description：保存
     * @param tplId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	Map<String, Object> saveCopyPerformanceTpl(Integer tplId)throws Exception;
    /**
     * Description：查询绩效模板
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	Pagination<SrmSpmIndicatorItemsEntity_HI_RO> queryItemTplList(JSONObject paramJSON, Integer pageIndex,Integer pageRows);
    /**
     * Description：绩效模板计数
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	boolean countformancetpl(JSONObject jsonParams)throws Exception;
    /**
     * Description：
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	boolean countCateScheme(JSONObject jsonParams)throws Exception;


	
}
