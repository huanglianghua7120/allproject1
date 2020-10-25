package saaf.common.fmw.base.model.inter.server;


import com.alibaba.fastjson.JSONObject;

import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.ViewObject;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafLoginsHisEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafLoginsHis;
import saaf.common.fmw.common.model.inter.server.ShortDescMessageServer;
import saaf.common.fmw.common.utils.SaafToolUtils;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：saafLoginsHisServer.java
 * Description：日记记录
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     钟士元             创建
 * ===========================================================================
 */
@Component("saafLoginsHisServer")
public class SaafLoginsHisServer implements ISaafLoginsHis {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafLoginsHisServer.class);
    @Autowired
    private ShortDescMessageServer shortDescMessageServer;

    public SaafLoginsHisServer() {
        super();
    }
    @Autowired
    private ViewObject<SaafLoginsHisEntity_HI> saafLoginsHisDAO_HI;
    /**
     * 保存日记记录
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    @Override
    public JSONObject saveLoginHistory(JSONObject params) throws Exception {
        LOGGER.info(JSONObject.toJSONString(params));
        //int userId = params.getIntValue("UserId");
        SaafLoginsHisEntity_HI log = new SaafLoginsHisEntity_HI();
        log.setLoginIp(params.getString("userIp"));
        log.setLoginName(params.getString("UserName"));
        log.setLoginTime(new Date());
        log.setLoginType(params.getString("userType"));
        log.setSessionId(params.getString("sessionId"));
        log.setUserId(params.getInteger("UserId"));
        log.setOperatorUserId(params.getInteger("UserId"));
        saafLoginsHisDAO_HI.saveOrUpdate(log);
        return SToolUtils.convertResultJSONObj("S", shortDescMessageServer.getMessage("SAVE-SUCCESS"), 1, log);
    }
}
