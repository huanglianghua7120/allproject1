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
import saaf.common.fmw.po.model.entities.SrmPoTechnicalNumEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;
import saaf.common.fmw.po.model.entities.readonly.SrmPoSubprojectNumEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoTechnicalNumEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoTechnicalNum;
import saaf.common.fmw.common.utils.SaafToolUtils;

@Component("srmPoTechnicalNumServer")
public class SrmPoTechnicalNumServer implements ISrmPoTechnicalNum{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoTechnicalNumServer.class);

	@Autowired
	private ViewObject<SrmPoTechnicalNumEntity_HI> srmPoTechnicalNumDAO_HI;

    @Autowired
    private BaseViewObject<SrmPoTechnicalNumEntity_HI_RO> SrmPoTechnicalNumDAO_HI_RO;

	public SrmPoTechnicalNumServer() {
		super();
	}

    /**
     * Description：查询技改编号
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
    public Pagination<SrmPoTechnicalNumEntity_HI_RO> findSrmPoTechnicalNumInfo(JSONObject jsonParams, Integer pageIndex,
                                                                               Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap();
        try {
            StringBuffer queryString = new StringBuffer(SrmPoTechnicalNumEntity_HI_RO.QUERY_TECHNICAL_NUM_SQL);
            SaafToolUtils.parperParam(jsonParams, "Sptn.Technical_Code", "technicalCode", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "Sptn.Technical_Name", "technicalName", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "Sptn.enable", "enable", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "Sptn.Technical_Id", "technicalId", queryString, queryParamMap, "=");
            String countSql = "select count(1) from (" + queryString + ")";
            Pagination<SrmPoTechnicalNumEntity_HI_RO> result = SrmPoTechnicalNumDAO_HI_RO.findPagination(queryString.toString(),countSql, queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new UtilsException(e);
        }
    }
}
