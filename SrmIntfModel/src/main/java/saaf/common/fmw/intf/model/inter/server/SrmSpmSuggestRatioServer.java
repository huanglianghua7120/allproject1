/*================================================================================================================================================+
|   Copyright (c) 2018   SIE.                                                                                                                     |
+=================================================================================================================================================+
|  HISTORY                                                                                                                                        |
|                 Date                    Ver.                 Author                       Content                                               |
|               2018-04-03                1.0                 Jinshaojun                    Creation                                              |
+================================================================================================================================================*/
package saaf.common.fmw.intf.model.inter.server;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.intf.model.inter.ISrmSpmSuggestRatio;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceSchemeEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmSupplierScoreEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmRatioMappingsEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmSupplierScoreEntity_HI_RO;
import saaf.common.fmw.utils.SrmUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("spmSuggestRatioServer")
public class SrmSpmSuggestRatioServer implements ISrmSpmSuggestRatio {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmSuggestRatioServer.class);

    @Autowired
    private ViewObject<SrmSpmPerformanceSchemeEntity_HI> srmSpmPerformanceSchemeDAO_HI;

    @Autowired
    private ViewObject<SrmSpmSupplierScoreEntity_HI> srmSpmSupplierScoreDAO_HI;

    @Autowired
    private BaseViewObject<SrmSpmSupplierScoreEntity_HI_RO> srmSpmSupplierScoreDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmSpmRatioMappingsEntity_HI_RO> srmSpmRatioMappingsDAO_HI_RO;

    @Autowired
    private SrmSequencesUtil srmSequencesUtil;

    /**
     * 绩效结果应用供货比例建议
     *
     * @param schemeId 绩效方案ID
     * @return
     * @throws Exception
     */
    @Override
    public String saveSuggestRatio(Integer schemeId) throws Exception {
        LOGGER.info("saveSuggestRatio--schemeId:" + schemeId);
        if (schemeId == null) {
            return "缺少必要参数";
        }

        SrmSpmPerformanceSchemeEntity_HI info = srmSpmPerformanceSchemeDAO_HI.getById(schemeId);
        if (info == null) {
            return "未找到对应数据或已经存在执行中的请求";
        }

        String schemeNumber = info.getSchemeNumber();

        //获取供货比例，如果存在供货比例但是未包含在本次绩效计算结果中的供应商则写入绩效供应商分类得分表
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.clear();
        map1.put("scheme_id", schemeId);
        List<SrmSpmSupplierScoreEntity_HI_RO> otherList = srmSpmSupplierScoreDAO_HI_RO.findList(SrmSpmSupplierScoreEntity_HI_RO.QUERY_OUT_SUPPLIER_LIST, map1);
        List<SrmSpmSupplierScoreEntity_HI> newList = new ArrayList<SrmSpmSupplierScoreEntity_HI>();
        SrmSpmSupplierScoreEntity_HI newRow;
        for (SrmSpmSupplierScoreEntity_HI_RO row : otherList) {
            if(!SrmUtils.isNvl(row.getSupplierScoreId()))
            {
                newRow = srmSpmSupplierScoreDAO_HI.getById(row.getSupplierScoreId());
            }
            else
            {
                newRow = new SrmSpmSupplierScoreEntity_HI();

                newRow.setSchemeId(row.getSchemeId());
                newRow.setCategoryId(row.getCategoryId());
                newRow.setScoreType(row.getScoreType());
                newRow.setVendorId(row.getVendorId());
                newRow.setAdviseSupplyRatio(new BigDecimal(0));
                newRow.setNewSupplyRatio(new BigDecimal(0));
            }
            newRow.setCurrSupplyRatio(row.getCurrSupplyRatio());
            newRow.setPublishFlag(row.getPublishFlag());
            newRow.setOperatorUserId(-1);
            newList.add(newRow);
        }
        if (newList.size() > 0) {
            srmSpmSupplierScoreDAO_HI.save(newList);
            srmSpmSupplierScoreDAO_HI.fluch();
        }


        String queryString2 = "SELECT ssrm.mapping_id\n" +
                "      ,ssrm.org_id\n" +
                "      ,ssrm.category_id\n" +
                "      ,ssrm.segment1\n" +
                "      ,ssrm.segment2\n" +
                "      ,ssrm.segment3\n" +
                "      ,ssrm.segment4\n" +
                "      ,ssrm.vendor_count\n" +
                "      ,ssrm.supply_ratio_1 supply_ratio1\n" +
                "      ,ssrm.supply_ratio_2 supply_ratio2\n" +
                "      ,ssrm.supply_ratio_3 supply_ratio3\n" +
                "      ,ssrm.supply_ratio_4 supply_ratio4\n" +
                "      ,ssrm.supply_ratio_5 supply_ratio5\n" +
                "      ,ssrm.supply_ratio_6 supply_ratio6\n" +
                "      ,ssrm.supply_ratio_7 supply_ratio7\n" +
                "      ,ssrm.supply_ratio_8 supply_ratio8\n" +
                "      ,ssrm.supply_ratio_9 supply_ratio9\n" +
                "      ,ssrm.supply_ratio_10 supply_ratio10\n" +
                "      ,ssrm.supply_ratio_11 supply_ratio11\n" +
                "      ,ssrm.supply_ratio_12 supply_ratio12\n" +
                "      ,ssrm.supply_ratio_13 supply_ratio13\n" +
                "      ,ssrm.supply_ratio_14 supply_ratio14\n" +
                "      ,ssrm.supply_ratio_15 supply_ratio15\n" +
                "      ,ssrm.supply_ratio_16 supply_ratio16\n" +
                "      ,ssrm.supply_ratio_17 supply_ratio17\n" +
                "      ,ssrm.supply_ratio_18 supply_ratio18\n" +
                "      ,ssrm.supply_ratio_19 supply_ratio19\n" +
                "      ,ssrm.supply_ratio_20 supply_ratio20\n" +
                "      ,ssrm.description\n" +
                "      ,ssrm.version_num\n" +
                "      ,ssrm.creation_date\n" +
                "      ,ssrm.created_by\n" +
                "      ,ssrm.last_updated_by\n" +
                "      ,ssrm.last_update_date\n" +
                "      ,ssrm.last_update_login\n" +
                "      ,ssrm.attribute_category\n" +
                "      ,ssrm.attribute1\n" +
                "      ,ssrm.attribute2\n" +
                "      ,ssrm.attribute3\n" +
                "      ,ssrm.attribute4\n" +
                "      ,ssrm.attribute5\n" +
                "      ,ssrm.attribute6\n" +
                "      ,ssrm.attribute7\n" +
                "      ,ssrm.attribute8\n" +
                "      ,ssrm.attribute9\n" +
                "      ,ssrm.attribute10\n" +
                "      ,ssrm.attribute11\n" +
                "      ,ssrm.attribute12\n" +
                "      ,ssrm.attribute13\n" +
                "      ,ssrm.attribute14\n" +
                "      ,ssrm.attribute15\n" +
                "  FROM srm_spm_ratio_mappings ssrm\n" +
                " WHERE ssrm.vendor_count =\n" +
                "       (SELECT COUNT(1)\n" +
                "          FROM srm_spm_performance_scheme ssps\n" +
                "              ,srm_spm_supplier_score     ssss\n" +
                "         WHERE ssss.score_type = 'CATEGORY'\n" +
                "           AND ssss.scheme_id = ssps.scheme_id\n" +
                "           AND ssps.org_id = ssrm.org_id\n" +
                "           AND ssss.category_id = ssrm.category_id\n" +
                "           AND ssss.scheme_id = :scheme_id)\n";

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.clear();
        map2.put("scheme_id", schemeId);

        List<SrmSpmRatioMappingsEntity_HI_RO> ratioList = srmSpmRatioMappingsDAO_HI_RO.findList(queryString2, map2);
        if (ratioList == null || ratioList.size() == 0) {
        	 info.setRatioRequestStatus("EXECUTED");
             info.setOperatorUserId(-1);
             srmSpmPerformanceSchemeDAO_HI.save(info);
            return "供货比例计算成功，没有填写建议比例无法计算供货比例建议！";
        }else {
        	Map<Integer, Object> ratioMap = new HashMap<>();
            for (SrmSpmRatioMappingsEntity_HI_RO row : ratioList) {
                ratioMap.put(row.getCategoryId(), row);
            }
            String docNumber = "";
            docNumber = srmSequencesUtil.getDocSequences("srm_spm_supplier_score".toUpperCase(), schemeNumber,"-", 3);

            StringBuffer queryString3 = new StringBuffer("from SrmSpmSupplierScoreEntity_HI h where h.schemeId = :schemeId and h.scoreType in ( 'CATEGORY','OTHER') ");

            Map<String, Object> map3 = new HashMap<String, Object>();
            map3.clear();
            map3.put("schemeId", schemeId);
            List<SrmSpmSupplierScoreEntity_HI> supplierList = srmSpmSupplierScoreDAO_HI.findList(queryString3, map3);
            SrmSpmSupplierScoreEntity_HI supplierRow;
            SrmSpmRatioMappingsEntity_HI_RO ratioRow;
            for (int i = 0; i < supplierList.size(); i++) {
                supplierRow = supplierList.get(i);
                if (supplierRow != null && ratioMap != null && ratioMap.containsKey(supplierRow.getCategoryId()) && ratioMap.get(supplierRow.getCategoryId()) != null) {
                    ratioRow = (SrmSpmRatioMappingsEntity_HI_RO) ratioMap.get(supplierRow.getCategoryId());
                    if (supplierRow.getRank() != null) {
                        switch (supplierRow.getRank()) {//根据排名获取对应的建议供货比例
                            case 1:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio1());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio1());
                                break;
                            case 2:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio2());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio2());
                                break;
                            case 3:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio3());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio3());
                                break;
                            case 4:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio4());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio4());
                                break;
                            case 5:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio5());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio5());
                                break;
                            case 6:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio6());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio6());
                                break;
                            case 7:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio7());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio7());
                                break;
                            case 8:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio8());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio8());
                                break;
                            case 9:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio9());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio9());
                                break;
                            case 10:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio10());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio10());
                                break;
                            case 11:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio11());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio11());
                                break;
                            case 12:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio12());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio12());
                                break;
                            case 13:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio13());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio13());
                                break;
                            case 14:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio14());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio14());
                                break;
                            case 15:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio15());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio15());
                                break;
                            case 16:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio16());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio16());
                                break;
                            case 17:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio17());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio17());
                                break;
                            case 18:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio18());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio18());
                                break;
                            case 19:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio19());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio19());
                                break;
                            case 20:
                                supplierRow.setAdviseSupplyRatio(ratioRow.getSupplyRatio20());
                                supplierRow.setNewSupplyRatio(ratioRow.getSupplyRatio20());
                                break;
                            default:
                                supplierRow.setAdviseSupplyRatio(null);
                                supplierRow.setNewSupplyRatio(null);
                        }
                    } else {
                        supplierRow.setAdviseSupplyRatio(new BigDecimal(0));
                        supplierRow.setNewSupplyRatio(new BigDecimal(0));
                    }
                    supplierRow.setOperatorUserId(-1);
                    supplierRow.setLastUpdatedBy(-1);
                    supplierRow.setLastUpdateDate(new Date());
                    supplierRow.setSourceSchemeNumber(docNumber);
                    supplierList.set(i, supplierRow);
                }

            }
            if (supplierList.size() > 0) {
                srmSpmSupplierScoreDAO_HI.save(supplierList);
//                srmSpmSupplierScoreDAO_HI.fluch();
            }
            
            info.setRatioRequestStatus("EXECUTED");
            info.setOperatorUserId(-1);
            srmSpmPerformanceSchemeDAO_HI.save(info);

            return "应用供货比例建议成功！";
        }
    }
}
