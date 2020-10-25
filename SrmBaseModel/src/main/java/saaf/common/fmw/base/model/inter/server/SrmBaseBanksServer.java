package saaf.common.fmw.base.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SrmBaseBanksEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseBanksEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseBanks;
import saaf.common.fmw.common.utils.SaafToolUtils;
import com.yhg.hibernate.core.paging.Pagination;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：银行
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmBaseBanksServer")
public class SrmBaseBanksServer implements ISrmBaseBanks {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseBanksServer.class);
    @Autowired
    private ViewObject<SrmBaseBanksEntity_HI> srmBaseBanksDAO_HI;
    @Autowired
    private BaseViewObject<SrmBaseBanksEntity_HI_RO> srmBaseBanksDAO_HI_RO;

    public SrmBaseBanksServer() {
        super();
    }

    /**
     * 银行查询
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmBaseBanksEntity_HI_RO> findSrmBaseBanksInfoList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmBaseBanksEntity_HI_RO.QUERY_BANK);
        SaafToolUtils.parperParam(jsonParams, "t.bank_code", "bankCode", sb, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "t.bank_name", "bankName", sb, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "t.country", "country", sb, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "t.bank_id", "bankId", sb, queryParamMap, "like");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmBaseBanksEntity_HI_RO> result = srmBaseBanksDAO_HI_RO
                .findPagination(sb.toString(), countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：银行信息定时任务接口list
     *
     * @param businessData ==============================================================================
     *                     Version    Date           Updated By     Description
     *                     -------    -----------    -----------    ---------------
     *                     V1.0       2019-11-08     zhj             创建
     *                     ==============================================================================
     */
    @Override
    public Integer updateSrmBaseBankListJob(JSONArray businessData) {
        Integer count = 0;
        if (null != businessData && businessData.size() > 0) {
            for (int i = 0; i < businessData.size(); i++) {
                SrmBaseBanksEntity_HI row = new SrmBaseBanksEntity_HI();
                List<SrmBaseBanksEntity_HI> list = new ArrayList<>();
                JSONObject obj = businessData.getJSONObject(i);
                try {
                    String bankId = obj.getString("BANK_ID");
                    String bankName = obj.getString("BANK_NAME");
                    String bankCode = obj.getString("BANK_CODE");
                    String country = obj.getString("COUNTRY_CODE");
                    String countryName = obj.getString("COUNTRY");
                    Date startDate = null;
                    Date endDate = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (!ObjectUtils.isEmpty(obj.getString("START_DATE"))) {
                        startDate = sdf.parse(obj.getString("START_DATE"));
                    }
                    if (!ObjectUtils.isEmpty(obj.getString("END_DATE"))) {
                        endDate = sdf.parse(obj.getString("END_DATE"));
                    }
                    if (null == bankId || "".equals(bankId) || null == bankName || "".equals(bankName)) {
                        continue;
                    }
                    list = srmBaseBanksDAO_HI.findByProperty("sourceId", bankId);
                    if (null != list && list.size() > 0) {
                        //update
                        row = list.get(0);
                    } else {
                        //insert
                        row = new SrmBaseBanksEntity_HI();
                        //采用EBS过来的主键ID
                        row.setBankId(Integer.parseInt(bankId));
                        row.setSourceId(bankId);
                    }
                    row.setSourceCode("EBS");
                    row.setBankName(bankName);
                    row.setBankCode(bankCode);
                    row.setCountry(country);
                    row.setCountryName(countryName);
                    row.setStartDate(startDate);
                    row.setEndDate(endDate);
                    row.setOperatorUserId(-1);
                    srmBaseBanksDAO_HI.saveOrUpdate(row);
                    srmBaseBanksDAO_HI.fluch();
                    count++;
                } catch (Exception e) {
                    LOGGER.error("--------------------------->保存银行信息操作失败！参数：{}", obj.toJSONString() + "，异常：{}", e.getMessage());
                    continue;
                }
            }
        }
        return count;
    }
}
