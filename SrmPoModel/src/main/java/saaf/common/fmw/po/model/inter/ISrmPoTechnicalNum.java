package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import saaf.common.fmw.po.model.entities.SrmPoTechnicalNumEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoTechnicalNumEntity_HI_RO;

public interface ISrmPoTechnicalNum {

    /**
     * Description：查询技改编号
     * @param jsonParams 查询参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-27     SIE 谢晓霞       创建
     * =======================================================================
     */
    Pagination<SrmPoTechnicalNumEntity_HI_RO> findSrmPoTechnicalNumInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;


}
