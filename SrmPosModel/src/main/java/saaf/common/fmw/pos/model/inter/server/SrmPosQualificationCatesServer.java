package saaf.common.fmw.pos.model.inter.server;

import com.yhg.hibernate.core.dao.BaseViewObject;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosQualificationCatesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosQualificationCates;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pos.model.entities.SrmPosQualificationCatesEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("srmPosQualificationCatesServer")
public class SrmPosQualificationCatesServer implements ISrmPosQualificationCates {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosQualificationCatesServer.class);

    @Autowired
    private ViewObject<SrmPosQualificationCatesEntity_HI> srmPosQualificationCatesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosQualificationCatesEntity_HI_RO> srmPosQualificationCatesDAO_HI_RO;

    public SrmPosQualificationCatesServer() {
        super();
    }

    /**
     * 根据资质审查ID和是否新增标识，查询资质审查单下面供应商品类
     *
     * @param jsonParams
     * @return
     * @throws Exception
     */
    public List<SrmPosQualificationCatesEntity_HI_RO> findQualificationCategoryByAddFlag(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPosQualificationCatesEntity_HI_RO.QUERY_CATEGORY_BY_ADDFLAG);
        SaafToolUtils.parperParam(jsonParams, "pqc.qualification_id", "qualificationId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "pqc.add_flag", "addFlag", queryString, queryParamMap, "=");
        queryString.append(" ORDER BY sbc.full_category_code ");
        List<SrmPosQualificationCatesEntity_HI_RO> rowSet = srmPosQualificationCatesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        return rowSet;
    }

    @Override
    public JSONObject deleteQualificationCategory(JSONObject jsonParams) throws Exception {
        Integer qualifCategoryId = jsonParams.getInteger("qualifCategoryId");
        srmPosQualificationCatesDAO_HI.delete(qualifCategoryId);
        return SToolUtils.convertResultJSONObj("S", "删除资质审查分类成功", 1, null);
    }

}
