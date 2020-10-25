package saaf.common.fmw.base.ws.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.PropertiesLoader;
import saaf.common.fmw.base.ws.ekp.IKmReviewWebserviceServiceService;

/**
 * @author: ZHJ
 * @date:Created in 11:21 2020/1/2
 * @modify By:
 * @description :控制SyncProcess生成单例
 */
@Component("syncProcess")
public class SyncProcess {
    private Logger LOGGER = LoggerFactory.getLogger(SyncProcess.class);
    public static PropertiesLoader PRO = new PropertiesLoader("/wsConfig.properties");
    //EKP的启动流程方法
    //注释掉，因为webservice不提供外网地址，不立即初始化
    //private IKmReviewWebserviceServiceService iKmReviewWebserviceServiceService = getKmReviewWebserviceServiceService();
    private IKmReviewWebserviceServiceService iKmReviewWebserviceServiceService = null;
    /**
     * Description：控制EKP启动流程的单例
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-01-02     zhj          创建
     * ==============================================================================
     */
    public IKmReviewWebserviceServiceService getKmReviewWebserviceServiceService(){
        try {
            if(null == iKmReviewWebserviceServiceService){
                return new IKmReviewWebserviceServiceService();
            }else{
                return iKmReviewWebserviceServiceService;
            }
        }catch (Exception e){
            LOGGER.error("未知错误:{}",e);
        }
        return null;
    }
}
