package saaf.common.fmw.common.model.inter.server;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import saaf.common.fmw.common.model.entities.SaafDocSequencesEntity_HI;
import saaf.common.fmw.common.model.inter.ISaafDocSequences;

import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;


@Component("saafDocSequencesServer")
public class SaafDocSequencesServer implements ISaafDocSequences {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafDocSequencesServer.class);
    @Autowired
    private ViewObject<SaafDocSequencesEntity_HI> saafDocSequencesDAO_HI;
    @Autowired
    private GenerateOnlyCodeServer generateOnlyCodeServer;
    public SaafDocSequencesServer() {
        super();
    }

//    @Resource(name = "baseDAO")
//    private IBaseDAO baseDAO;

    /**
     * 获取下一个单据号(不支持集群部署服务，需优化后使用)，注释于20190911，修改后保留历史
     * @param docType 单据类型（TableName）
     * @param pk1Value 前缀1
     * @param pk2Value 前缀2
     * @param pk3Value 前缀3
     * @param pk4Value 前缀4
     * @param pk5Value 前缀5
     * @return
     * @throws Exception
     */
//    public synchronized String updateNexDocSequences(String docType, String pk1Value, String pk2Value, String pk3Value, String pk4Value, String pk5Value,
//                                                     Integer length) throws Exception {
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        boolean flag = false;
//        Integer userId = -1;
//        Integer versionNum = 0;
//        String resultSeqNum = null;
//        // 查询单据号
//        map = findEcpDocSequences(docType, pk1Value, pk2Value, pk3Value, pk4Value, pk5Value);
//        if (null == map.get("nextSeqNumber") && null == map.get("versionNum")) {
//            // 不存在 新增
//            flag = false;
//            versionNum = 1;
//        } else {
//            // 存在修改
//            flag = true;
//            versionNum = (Integer)map.get("versionNum");
//        }
//        //保存单据规则
//        Integer result = saveEcpDocSequences(flag, docType, pk1Value, pk2Value, pk3Value, pk4Value, pk5Value, versionNum, userId);
//        if (result > 0) {
//            //组合单据号
//            String longSeqNum = appendSeqNum(length, result);
//            resultSeqNum = formatDocSequences(pk1Value, pk2Value, pk3Value, pk4Value, pk5Value, longSeqNum);
//        } else {
//            // 保存失败，继续
//            // updateNexDocSequences(docType, pk1Value, pk2Value, pk3Value, pk4Value, pk5Value, length);
//            resultSeqNum = null;
//        }
//
//        return resultSeqNum;
//    }

    /**
     * 获取下一个单据号(不支持集群部署服务，需优化后使用)
     * @param docType 单据类型（TableName）
     * @param pk1Value 前缀1
     * @param pk2Value 前缀2
     * @param pk3Value 前缀3
     * @param pk4Value 前缀4
     * @param pk5Value 前缀5
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public synchronized String updateNexDocSequences(String docType, String pk1Value, String pk2Value, String pk3Value, String pk4Value, String pk5Value,
                                                     Integer length) throws Exception {
        Integer userId = -1;
        Integer versionNum = 0;
        String resultSeqNum = null;
        String sequenceKey = docType + ":" + pk1Value;
        sequenceKey += StringUtils.isEmpty(pk2Value) ? "" : ":" + pk2Value;
        sequenceKey += StringUtils.isEmpty(pk3Value) ? "" : ":" + pk3Value;
        sequenceKey += StringUtils.isEmpty(pk4Value) ? "" : ":" + pk4Value;
        sequenceKey += StringUtils.isEmpty(pk5Value) ? "" : ":" + pk5Value;
        String sequenceValue = generateOnlyCodeServer.get(sequenceKey);
        // 查询单据号
        SaafDocSequencesEntity_HI docSequencesEntity = findDocSequencesEntity(docType, pk1Value, pk2Value, pk3Value, pk4Value, pk5Value);
        if (null == docSequencesEntity) {
            // 不存在 新增
            versionNum = ObjectUtils.isEmpty(sequenceValue) ? 1 : Integer.valueOf(sequenceValue);
        } else {
            // 存在修改
            versionNum = ObjectUtils.isEmpty(sequenceValue) ? docSequencesEntity.getVersionNum() + 1 : Integer.valueOf(sequenceValue) + 1;
        }
        //序号有效期为24小时
        generateOnlyCodeServer.set(sequenceKey, versionNum.toString(), 24*60*60*1000);
        //保存单据规则
        saveEcpDocSequences(docSequencesEntity, docType, pk1Value, pk2Value, pk3Value, pk4Value, pk5Value, versionNum, userId);
        //saveEcpDocSequences(flag, docType, pk1Value, pk2Value, pk3Value, pk4Value, pk5Value, versionNum, userId);
        Integer result = versionNum;
        if (result > 0) {
            //组合单据号
            String longSeqNum = appendSeqNum(length, result);
            resultSeqNum = formatDocSequences(pk1Value, pk2Value, pk3Value, pk4Value, pk5Value, longSeqNum);
        } else {
            // 保存失败，继续
            // updateNexDocSequences(docType, pk1Value, pk2Value, pk3Value, pk4Value, pk5Value, length);
            resultSeqNum = null;
        }
        return resultSeqNum;
    }

    /**
     * 查询单据号
     * @param docType
     * @return
     */
    public synchronized SaafDocSequencesEntity_HI findDocSequencesEntity(String docType, String pk1Value, String pk2Value, String pk3Value, String pk4Value, String pk5Value) {
        LOGGER.info("查询单据号，参数：docType==>" + docType);
        LOGGER.info("查询单据号，参数：pk1Value==>" + pk1Value);
        LOGGER.info("查询单据号，参数：pk2Value==>" + pk2Value);
        LOGGER.info("查询单据号，参数：pk3Value==>" + pk3Value);
        LOGGER.info("查询单据号，参数：pk4Value==>" + pk4Value);
        LOGGER.info("查询单据号，参数：pk5Value==>" + pk5Value);
        Map<String, Object> map = new HashMap();
        map.put("docType", docType);
        if (isStringEmpty(pk1Value)) {
            map.put("pk1Value", pk1Value);
        }
        if (isStringEmpty(pk2Value)) {
            map.put("pk2Value", pk2Value);
        }
        if (isStringEmpty(pk3Value)) {
            map.put("pk3Value", pk3Value);
        }
        if (isStringEmpty(pk4Value)) {
            map.put("pk4Value", pk4Value);
        }
        if (isStringEmpty(pk5Value)) {
            map.put("pk5Value", pk5Value);
        }
        List<SaafDocSequencesEntity_HI> seqList = saafDocSequencesDAO_HI.findByProperty(map);
        if (seqList.size() > 0) {
            return seqList.get(0);
        }
        return null;
    }

    /**
     * 保存单据号
     * @return
     */
    public synchronized void saveEcpDocSequences(SaafDocSequencesEntity_HI docSequencesEntityHi, String docType, String pk1Value,
                                                 String pk2Value, String pk3Value, String pk4Value, String pk5Value, Integer versionNum, Integer userId) throws Exception {
        try {
            SaafDocSequencesEntity_HI row = docSequencesEntityHi;
            if (row == null) {
                row = new SaafDocSequencesEntity_HI();
                row.setCreatedBy(userId);
                row.setCreationDate(new Date());
                row.setNextSeqNumber(1);
                row.setVersionNum(1);
            } else {
                row.setVersionNum(versionNum);
                row.setNextSeqNumber(versionNum);
            }
            row.setLastUpdateDate(new Date());
            row.setLastUpdateLogin(userId);
            row.setLastUpdatedBy(userId);
            row.setDocType(docType);
            row.setPk1Value(pk1Value);
            row.setPk2Value(pk2Value);
            row.setPk3Value(pk3Value);
            row.setPk4Value(pk4Value);
            row.setPk5Value(pk5Value);
            saafDocSequencesDAO_HI.saveOrUpdate(row);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询单据号
     * @param docType
     * @return
     */
    public Map<String, Object> findEcpDocSequences(String docType, String pk1Value, String pk2Value, String pk3Value, String pk4Value, String pk5Value) {

        LOGGER.info("查询单据号，参数：docType==>" + docType);
        LOGGER.info("查询单据号，参数：pk1Value==>" + pk1Value);
        LOGGER.info("查询单据号，参数：pk2Value==>" + pk2Value);
        LOGGER.info("查询单据号，参数：pk3Value==>" + pk3Value);
        LOGGER.info("查询单据号，参数：pk4Value==>" + pk4Value);
        LOGGER.info("查询单据号，参数：pk5Value==>" + pk5Value);
        Map<String, Object> resulMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("docType", docType);
        if (isStringEmpty(pk1Value)) {
            map.put("pk1Value", pk1Value);
        }
        if (isStringEmpty(pk2Value)) {
            map.put("pk2Value", pk2Value);
        }
        if (isStringEmpty(pk3Value)) {
            map.put("pk3Value", pk3Value);
        }
        if (isStringEmpty(pk4Value)) {
            map.put("pk4Value", pk4Value);
        }
        if (isStringEmpty(pk5Value)) {
            map.put("pk5Value", pk5Value);
        }

        SaafDocSequencesEntity_HI row = null;
        List<SaafDocSequencesEntity_HI> seqList = saafDocSequencesDAO_HI.findByProperty(map);
        if (seqList.size() > 0) {
            row = seqList.get(0);
            resulMap.put("nextSeqNumber", row.getNextSeqNumber());
            resulMap.put("versionNum", row.getVersionNum());
        } else {
            resulMap.put("nextSeqNumber", null);
            resulMap.put("versionNum", null);
        }

        return resulMap;
    }

    /**
     * 保存单据号
     * @return
     */
    public Integer saveEcpDocSequences(boolean updated, String docType, String pk1Value, String pk2Value, String pk3Value, String pk4Value, String pk5Value, Integer versionNum,
                                       Integer userId) throws Exception {
        try {
            SaafDocSequencesEntity_HI row = null;
            if (!updated) {
                row = new SaafDocSequencesEntity_HI();
                row.setCreatedBy(userId);
                row.setCreationDate(new Date());
                row.setNextSeqNumber(1);
                row.setVersionNum(1);
            } else {
                StringBuffer whereSql = new StringBuffer();
                whereSql.append(" where docType = :docType and versionNum = :versionNum  ");
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("docType", docType);
                map.put("versionNum", versionNum);
                if (isStringEmpty(pk1Value)) {
                    whereSql.append(" AND pk1Value =:pk1Value");
                    map.put("pk1Value", pk1Value);
                }
                if (isStringEmpty(pk2Value)) {
                    whereSql.append(" AND pk2Value =:pk2Value");
                    map.put("pk2Value", pk2Value);
                }
                if (isStringEmpty(pk3Value)) {
                    whereSql.append(" AND pk3Value =:pk3Value");
                    map.put("pk3Value", pk3Value);
                }
                if (isStringEmpty(pk4Value)) {
                    whereSql.append(" AND pk4Value =:pk4Value ");
                    map.put("pk4Value", pk4Value);
                }
                if (isStringEmpty(pk5Value)) {
                    whereSql.append(" AND pk5Value =:pk5Value");
                    map.put("pk5Value", pk5Value);
                }
                
                Pagination<SaafDocSequencesEntity_HI> docseqList = saafDocSequencesDAO_HI.findPagination("from SaafDocSequencesEntity_HI "+whereSql, map, 1, 1);
                
                if (docseqList!=null && docseqList.getData().size() > 0) {
                    row = docseqList.getData().get(0);
                    row.setNextSeqNumber(row.getNextSeqNumber() + 1);
                    row.setVersionNum(row.getVersionNum() + 1);
                } else {
                    return -1;
                }
            }
            row.setLastUpdateDate(new Date());
            row.setLastUpdateLogin(userId);
            row.setLastUpdatedBy(userId);
            row.setDocType(docType);
            row.setPk1Value(pk1Value);
            row.setPk2Value(pk2Value);
            row.setPk3Value(pk3Value);
            row.setPk4Value(pk4Value);
            row.setPk5Value(pk5Value);
            saafDocSequencesDAO_HI.saveOrUpdate(row);
            return row.getNextSeqNumber();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     *
     * @param pk1Value
     * @param pk2Value
     * @param pk3Value
     * @param pk4Value
     * @param pk5Value
     * @param longSeqNum
     * @return
     */
    public String formatDocSequences(String pk1Value, String pk2Value, String pk3Value, String pk4Value, String pk5Value, String longSeqNum) {
        StringBuffer resuleNum = new StringBuffer();
        resuleNum.append(isValueEmpty(pk1Value));
        resuleNum.append(isValueEmpty(pk2Value));
        resuleNum.append(isValueEmpty(pk3Value));
        resuleNum.append(isValueEmpty(pk4Value));
        resuleNum.append(isValueEmpty(pk5Value));
        resuleNum.append(longSeqNum);
        return resuleNum.toString();
    }

    /**
     * 补空字符串
     * @param length
     * @param seqNum
     * @return
     */
    public String appendSeqNum(Integer length, Integer seqNum) {
        String strValue = String.valueOf(seqNum);
        int strLen = strValue.length();
        while (strLen < length) {
            StringBuffer sb = new StringBuffer();
            sb.append("0").append(strValue); // 左补0
            strValue = sb.toString();
            strLen = strValue.length();
        }
        return strValue.toString();
    }

    /**
     * 空字符转换
     * @param value
     * @return
     */
    public String isValueEmpty(String value) {
        if (value == null) {
            value = "";
        }
        return value;
    }

    /**
     * 空字符验证
     * @param value
     * @return
     */
    public boolean isStringEmpty(String value) {
        if (null != value && !"".equals(value)) {
            return true;
        } else {
            return false;
        }
    }

}
