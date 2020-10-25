package saaf.common.fmw.common.model.inter.server;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("generateCodeServer")
public class GenerateCodeServer {

    private static final String MEMBERCODE_SEQ = "mcseq";
    private static final String ACTORCODE_SEQ = "acseq";
    private static final String CHANNELCODE_J_SEQ = "ccjseq";
    private static final String CHANNELCODE_D_SEQ = "ccdseq";

    public static final String MEMBERCODE_P = "MEMBER"; //会员编号
    public static final String ACTORCODE_P = "ACTOR"; //演员编号
    public static final String CHANNELCODE_J = "J"; //金逸自由渠道
    public static final String CHANNELCODE_D = "D"; //第三方支付渠道
    @Autowired
    private GenerateOnlyCodeServer generateOnlyCodeServer;

    public String generateCode(String financialCode, String seqType, int codeLength) {
        StringBuffer codeStrBuffer = new StringBuffer();
        //        GenerateOnlyCodeServer redisSequenceIdServer = (GenerateOnlyCodeServer)SaafToolUtils.context.getBean("generateOnlyCodeServer");
        String seqCode = generateOnlyCodeServer.getSequenceId(seqType);
        System.out.println("seqCode:" + seqCode);
        codeStrBuffer.append(financialCode);
        int seqCodeLength = seqCode.length();
        for (int i = 0; i < (codeLength - seqCodeLength); i++) {
            seqCode = "0" + seqCode;
        }
        codeStrBuffer.append(seqCode);
        return codeStrBuffer.toString();
    }

    //会员编码

    public String generagerMemberCode(String financialCode, int codeLenth) {
        return generateCode(financialCode,  MEMBERCODE_SEQ, codeLenth);
    }

    //演员编码

    public String generagerActorCode(String financialCode, int codeLenth) {
        return generateCode(financialCode,  ACTORCODE_SEQ, codeLenth);
    }

    //渠道编码 (自有渠道以J开头 J0001) (第三方渠道以D开头 D0001)

    public String generagerChannelCode(String financialCode, int codeLenth) {
        if ("J".equals(financialCode))
            return generateCode(financialCode,  CHANNELCODE_J_SEQ, codeLenth);
        return generateCode(financialCode,  CHANNELCODE_D_SEQ, codeLenth);
    }



}
