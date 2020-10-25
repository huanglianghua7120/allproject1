package saaf.common.fmw.pos.model.inter.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pon.model.entities.readonly.SrmPosFrozenCategoriesEntity_HI_RO;
import saaf.common.fmw.pos.model.dao.readonly.SrmPosFrozenCategoriesDAO_HI_RO;
import saaf.common.fmw.pos.model.entities.SrmPosFrozenCategoriesEntity_HI;
import saaf.common.fmw.pos.model.inter.ISrmPosFrozenCategories;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商冻结、恢复
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     hgq             创建
 * ===========================================================================
 */
@Component("srmPosFrozenCategoriesServer")
public class SrmPosFrozenCategoriesServer implements ISrmPosFrozenCategories {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosFrozenCategoriesServer.class);

    @Autowired
    private ViewObject<SrmPosFrozenCategoriesEntity_HI> srmPosFrozenCategoriesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosFrozenCategoriesEntity_HI_RO> srmPosFrozenCategoriesDAO_HI_RO;

    public SrmPosFrozenCategoriesServer() {
        super();
    }

    /**
     * 查询冻结品类信息
     * @param 　jsonParams
     * @return
     * @throws Exception
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     hgq             创建
     * ===========================================================================
     */
    @Override
    public List<SrmPosFrozenCategoriesEntity_HI_RO> findSrmPosFrozenCategoriesByCon(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmPosFrozenCategoriesEntity_HI_RO.QUERY_FROZEN_CATEGORIE_SQL);
            SaafToolUtils.parperParam(jsonParams, "b.supplier_id", "supplierId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "a.category_id", "categoryId", queryString, queryParamMap, "=");
            List<SrmPosFrozenCategoriesEntity_HI_RO> rowSet = srmPosFrozenCategoriesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
