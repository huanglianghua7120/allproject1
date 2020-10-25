package saaf.common.fmw.common.model.inter.server;


import com.yhg.redis.framework.WSSecurityPolicy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.exception.NotTokenException;


/**
 * Created by Admin on 2017/7/12.
 */
@Component("generateTokenServer")
public class GenerateTokenServer {
    @Autowired
    private WSSecurityPolicy wsSecurityPolicy;

    /**
     * 生成token
     * @param sesisonId
     * @return
     */
    //    public String generateStaticToken(String sesisonId) {
    //        //WSSecurityPolicy wsSecurityPolicy = (WSSecurityPolicy) SaafToolUtils.context.getBean("wsSecurityPolicy");
    //        String byKey = wsSecurityPolicy.getTokenByKey(sesisonId);
    //        if (null != byKey && !"".equals(byKey)) {
    //            return byKey;
    //        }
    //        return wsSecurityPolicy.generateStaticToken(sesisonId, 0);
    //    }

    public String generateStaticToken(String sesisonId, String jsonStr) {
        //WSSecurityPolicy wsSecurityPolicy = (WSSecurityPolicy) SaafToolUtils.context.getBean("wsSecurityPolicy");
        String byKey = wsSecurityPolicy.generateToken(sesisonId, jsonStr, "memberId", 120 * 60);
        if (null != byKey && !"".equals(byKey)) {
            return byKey;
        }
        return wsSecurityPolicy.generateStaticToken(sesisonId, 0);
    }

    /**
     * 验证Token是否失效
     * @param tokenCode
     * @throws NotTokenException
     */
    public boolean checkToken(String sessionId, String tokenCode) {
        if (null != tokenCode && !"".equals(tokenCode)) {
            //WSSecurityPolicy wsSecurityPolicy = (WSSecurityPolicy) SaafToolUtils.context.getBean("wsSecurityPolicy");
            return wsSecurityPolicy.isValidToken(sessionId, tokenCode);
        }
        return false;
    }


}
