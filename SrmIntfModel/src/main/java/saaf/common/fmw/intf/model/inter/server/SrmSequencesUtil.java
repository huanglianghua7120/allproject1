package saaf.common.fmw.intf.model.inter.server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.intf.model.inter.ISrmDocSequences;


@Component("srmSequencesUtil")
public class SrmSequencesUtil {
    private final Logger LOGGER = LoggerFactory.getLogger(SrmSequencesUtil.class);
    @Autowired
    private ISrmDocSequences srmDocSequencesServer;

    public SrmSequencesUtil() {
        super();
    }

    /**
     * 获取规则序列号
     *
     * @param docType
     * @param pk1Value
     * @param length
     * @return
     * @throws Exception
     */
    public String getDocSequences(String docType, String pk1Value, Integer length) throws Exception {
        String seqNum = getDocSequencesByTable(docType, pk1Value, null, null, null, null, length);
        return seqNum;
    }

    /**
     * 获取规则序列号
     *
     * @param docType
     * @param pk1Value
     * @param length
     * @return
     * @throws Exception
     */
    public String getDocSequences(String docType, String pk1Value, String pk2Value, Integer length) throws Exception {
        String seqNum = getDocSequencesByTable(docType, pk1Value, pk2Value, null, null, null, length);
        return seqNum;
    }

    /**
     * 获取规则序列号
     *
     * @param docType
     * @param pk1Value
     * @param length
     * @return
     * @throws Exception
     */
    public String getDocSequences(String docType, String pk1Value, String pk2Value, String pk3Value, Integer length) throws Exception {
        String seqNum = getDocSequencesByTable(docType, pk1Value, pk2Value, pk3Value, null, null, length);
        return seqNum;
    }

    /**
     * 获取规则序列号
     *
     * @param docType
     * @param pk1Value
     * @param length
     * @return
     * @throws Exception
     */
    public String getDocSequences(String docType, String pk1Value, String pk2Value, String pk3Value, String pk4Value, Integer length) throws Exception {
        String seqNum = getDocSequencesByTable(docType, pk1Value, pk2Value, pk3Value, pk4Value, null, length);
        return seqNum;
    }

    /**
     * 获取规则序列号
     *
     * @param docType
     * @param pk1Value
     * @param length
     * @return
     * @throws Exception
     */
    public String getDocSequences(String docType, String pk1Value, String pk2Value, String pk3Value, String pk4Value, String pk5Value, Integer length) throws Exception {
        String seqNum = getDocSequencesByTable(docType, pk1Value, pk2Value, pk3Value, pk4Value, pk5Value, length);
        return seqNum;
    }


    /**
     * 基于数据库获取规则序列号
     *
     * @param docType  表名
     * @param pk1Value 前缀1
     * @param pk2Value
     * @param pk3Value
     * @param pk4Value
     * @param pk5Value
     * @param length   流水号位数
     * @return
     * @throws Exception
     */
    public String getDocSequencesByTable(String docType, String pk1Value, String pk2Value, String pk3Value, String pk4Value, String pk5Value, Integer length) throws Exception {
        //        ISaafDocSequences saafDocSequencesServer = (ISaafDocSequences)SaafToolUtils.context.getBean("saafDocSequencesServer");
        String seqNumber = srmDocSequencesServer.updateNexDocSequences(docType.toUpperCase(), pk1Value, pk2Value, pk3Value, pk4Value, pk5Value, length);
        return seqNumber;
    }
}
