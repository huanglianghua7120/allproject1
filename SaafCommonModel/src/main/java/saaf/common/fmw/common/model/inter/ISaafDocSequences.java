package saaf.common.fmw.common.model.inter;

import java.util.Map;

public interface ISaafDocSequences {

    /**
     * 获取下一个单据号
     * @param docType 单据类型（TableName）
     * @param pk1Value 前缀1
     * @param pk2Value 前缀2
     * @param pk3Value 前缀3
     * @param pk4Value 前缀4
     * @param pk5Value 前缀5
     * @return
     * @throws Exception
     */
    String updateNexDocSequences(String docType, String pk1Value, String pk2Value, String pk3Value, String pk4Value, String pk5Value, Integer length) throws Exception;


    /**
     * 查询单据号
     * @param docType
     * @return
     */
    public Map<String, Object> findEcpDocSequences(String docType, String pk1Value, String pk2Value, String pk3Value, String pk4Value, String pk5Value);

    /**
     * 保存单据号
     * @return
     */
    public Integer saveEcpDocSequences(boolean updated, String docType, String pk1Value, String pk2Value, String pk3Value, String pk4Value, String pk5Value, Integer versionNum,
                                       Integer userId) throws Exception;

}
