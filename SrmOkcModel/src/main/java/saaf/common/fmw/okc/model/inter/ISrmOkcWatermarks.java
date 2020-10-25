package saaf.common.fmw.okc.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.okc.model.entities.SrmOkcWatermarksEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcWatermarksEntity_HI_RO;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：ISrmOkcWatermarks.java
 * Description：水印服务接口
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date     @Author(Updated By)     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019/6/5      欧阳岛          创建
 * <p>
 * ==============================================================================
 */
public interface ISrmOkcWatermarks {

    /**
     * Description：分页查找水印列表
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/5      欧阳岛          创建
     * ==============================================================================
     */
    Pagination<SrmOkcWatermarksEntity_HI_RO> findSrmOkcWatermarksList(
            JSONObject parameters, Integer pageIndex, Integer pageRows);

    /**
     * Description：保存水印
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/5      欧阳岛          创建
     * ==============================================================================
     */
    JSONObject saveSrmOkcWatermarks(JSONObject queryParamJSON);

    /**
     * Description：删除水印
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/5      欧阳岛          创建
     * ==============================================================================
     */
    JSONObject deleteSrmOkcWatermarks(JSONObject params) throws Exception;

    /**
     * Description：获取水印
     * @param
     * @return
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/13      欧阳岛          创建
     * ==============================================================================
     */
    SrmOkcWatermarksEntity_HI findSrmOkcWatermarksEntity_HI(Integer watermarkId);

    /**
     * Description：获取水印列表
     * @param
     * @return
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/13      欧阳岛          创建
     * ==============================================================================
     */
    Pagination<SrmOkcWatermarksEntity_HI> findEmployeeLov(JSONObject jsonParams, Integer pageIndex,
                                                          Integer pageRows) throws Exception;

}
