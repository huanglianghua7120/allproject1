package saaf.common.fmw.spm.model.inter.server;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.spm.model.entities.SrmSpmSupplierScoreEntity_HI;
import saaf.common.fmw.spm.model.inter.ISrmSpmSupplierScore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("srmSpmSupplierScoreServer")
public class SrmSpmSupplierScoreServer implements ISrmSpmSupplierScore {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmSupplierScoreServer.class);

    public SrmSpmSupplierScoreServer() {
        super();
    }

    @Autowired
    private ViewObject<SrmSpmSupplierScoreEntity_HI> srmSpmSupplierScoreDAO_HI;



    /**
     * 更新供应商得分表数据
     *
     * @param paramJSON
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateSupplierScore(JSONObject paramJSON) throws Exception {
        JSONArray jsonArray = paramJSON.getJSONArray("data");
        Integer varUserId = paramJSON.getInteger("varUserId");
        if (jsonArray.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无更新的数据", 0, null);
        }
        SrmSpmSupplierScoreEntity_HI entity = null;
        List<SrmSpmSupplierScoreEntity_HI> list = new ArrayList<SrmSpmSupplierScoreEntity_HI>();
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            entity = srmSpmSupplierScoreDAO_HI.getById(obj.getInteger("supplierScoreId"));
            entity.setNewSupplyRatio(obj.getBigDecimal("newSupplyRatio"));
            entity.setDescription(obj.getString("description"));
            entity.setStatus("UNCONFIRMED");
            entity.setOperatorUserId(varUserId);
            if (entity != null) {
                list.add(entity);
            }
        }
        if (list.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无更新的数据", 0, null);
        }
        try {
            srmSpmSupplierScoreDAO_HI.update(list);
            return SToolUtils.convertResultJSONObj("S", "更新成功", list.size(), null);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new UtilsException("更新失败");
        }
    }


}
