package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yhg.base.utils.SToolUtils;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.po.model.dao.readonly.SrmPoSubprojectNumDAO_HI_RO;
import saaf.common.fmw.po.model.entities.SrmPoSubprojectNumEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;
import saaf.common.fmw.po.model.entities.readonly.SrmPoSubprojectNumEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoSubprojectNum;
import saaf.common.fmw.common.utils.SaafToolUtils;

@Component("srmPoSubprojectNumServer")
public class SrmPoSubprojectNumServer implements ISrmPoSubprojectNum{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoSubprojectNumServer.class);

	@Autowired
	private ViewObject<SrmPoSubprojectNumEntity_HI> srmPoSubprojectNumDAO_HI;

    @Autowired
    private BaseViewObject<SrmPoSubprojectNumEntity_HI_RO> SrmPoSubprojectNumDAO_HI_RO;

	public SrmPoSubprojectNumServer() {
		super();
	}

    /**
     * Description：查询子项目编号
     * @param jsonParams 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-27     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoSubprojectNumEntity_HI_RO> findSrmPoSubprojectNumInfo(JSONObject jsonParams, Integer pageIndex,
                                                                                 Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap();
        try {
            StringBuffer queryString = new StringBuffer(SrmPoSubprojectNumEntity_HI_RO.QUERY_SUBPROJECT_NUM_SQL);
            SaafToolUtils.parperParam(jsonParams, "Spsn.subproject_code", "subprojectCode", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "Spsn.subproject_name", "subprojectName", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "Spsn.enable", "enable", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "Spsn.technical_id", "technicalId", queryString, queryParamMap, "=");
            String countSql = "select count(1) from (" + queryString + ")";
            Pagination<SrmPoSubprojectNumEntity_HI_RO> result = SrmPoSubprojectNumDAO_HI_RO.findPagination(queryString.toString(),countSql, queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new UtilsException(e);
        }
    }


}
