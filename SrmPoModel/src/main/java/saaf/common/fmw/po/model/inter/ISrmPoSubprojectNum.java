package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import saaf.common.fmw.po.model.entities.SrmPoSubprojectNumEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoShoppingCartsEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoSubprojectNumEntity_HI_RO;

public interface ISrmPoSubprojectNum {

    /**
     * Description：查询子项目编号
     * @param jsonParams 查询参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-27     SIE 谢晓霞       创建
     * =======================================================================
     */
    Pagination<SrmPoSubprojectNumEntity_HI_RO> findSrmPoSubprojectNumInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;


}
