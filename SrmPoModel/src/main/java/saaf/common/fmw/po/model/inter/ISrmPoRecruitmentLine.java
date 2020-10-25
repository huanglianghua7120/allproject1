package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import saaf.common.fmw.po.model.entities.SrmPoRecruitmentLineEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitmentHeaderEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitmentLineEntity_HI_RO;

public interface ISrmPoRecruitmentLine {
    /**
     * Description：招聘行
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public List<SrmPoRecruitmentLineEntity_HI_RO> findRecruitmentLineInfo(JSONObject jsonParams) throws Exception;


    /**
     * Description：删除招聘行
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject deleteRecruitmentLines(JSONObject params) throws Exception;
}
