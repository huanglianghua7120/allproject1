package saaf.common.fmw.common.model.inter.server;


import com.yhg.base.utils.SToolUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.common.model.inter.ISaafDocSequences;


@Component("saafSequencesUtil")
public class SaafSequencesUtil {
    private final Logger LOGGER = LoggerFactory.getLogger(SaafSequencesUtil.class);
    @Autowired
    private GenerateOnlyCodeServer generateOnlyCodeServer;
    @Autowired
    private ISaafDocSequences saafDocSequencesServer;

    public SaafSequencesUtil() {
        super();
    }

    /**
     * 获取规则序列号
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
     * 基于redis获取规则序列号 (未测试)
     * @param docType 序列号名称
     * @param pk1Value 前缀1
     * @param length 流水号位数
     * @return
     */
    public String getDocSequencesByRedis(String docType, String pk1Value, Integer length) throws Exception {
        String actorSeq = null;
        try {
            //            GenerateOnlyCodeServer redisSequenceIdServer = (GenerateOnlyCodeServer)SaafToolUtils.context.getBean("generateOnlyCodeServer");
            actorSeq = generateOnlyCodeServer.getSequenceId(docType); //"(saaf_user_employee_number_seq");
            //初始化 从 100+ 开始
            //actorSeq = "" + (100 + Integer.parseInt(actorSeq.toString()));

            //限制 length 位流水号
            if (actorSeq.length() < length) {
                for (int i = actorSeq.length() + 1; i <= length; i++) {
                    actorSeq = "0" + actorSeq;
                }
            }
            return SToolUtils.object2String(pk1Value + actorSeq);
        } catch (Exception e) {
            LOGGER.error("从redis 获取唯一编码 失败!");
            throw new Exception(e);
        }

    }

    /**
     * 基于数据库获取规则序列号
     * @param docType 表名
     * @param pk1Value 前缀1
     * @param pk2Value
     * @param pk3Value
     * @param pk4Value
     * @param pk5Value
     * @param length 流水号位数
     * @return
     * @throws Exception
     */
    public String getDocSequencesByTable(String docType, String pk1Value, String pk2Value, String pk3Value, String pk4Value, String pk5Value, Integer length) throws Exception {
        //        ISaafDocSequences saafDocSequencesServer = (ISaafDocSequences)SaafToolUtils.context.getBean("saafDocSequencesServer");
        String seqNumber = saafDocSequencesServer.updateNexDocSequences(docType.toUpperCase(), pk1Value, pk2Value, pk3Value, pk4Value, pk5Value, length);
        return seqNumber;
    }
}
