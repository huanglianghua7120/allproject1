package saaf.common.fmw.tarkjob;

import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.annotation.Log;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionHeadersEntity_HI;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionHeaders;
import saaf.common.fmw.pon.services.SrmPonBidItemPricesService;
import saaf.common.fmw.services.CommonAbstractServices;

/**
 * @author: ZWJ
 * @date:Created in 10:21 2020/3/19
 * @modify By:
 * @description :PON模块的定时任务
 */
@Component("srmPonRequestJobs")
public class SrmPonRequestJobs extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonRequestJobs.class);
    @Autowired
    private ISrmPonAuctionHeaders iSrmPonAuctionHeaders;
    @Autowired
    private ViewObject<SrmPonAuctionHeadersEntity_HI> srmPonAuctionHeadersDAO_HI;
    @Autowired
    private SrmPonBidItemPricesService srmPonBidItemPricesService;
    public SrmPonRequestJobs(){
        super();
    }
    /**
     * Description：寻源自动截标定时任务,每五分钟运行一次
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020/3/19      zwj             创建
     * ==============================================================================
     **/
   /* @Scheduled(cron = "0 0/3 * * * ?")
    @Log(title = "寻源自动截标定时任务,每五分钟运行一次",businessType = "每三分钟定时修改单据状态")
    public JSONObject updatePonAuctionHeaderJob()throws Exception{
        Integer count = iSrmPonAuctionHeaders.updatePonAuctionHeaderJob();
        if(null == count || count<1){
            LOGGER.info("没有即将到期的标书，暂无数据");
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "没有即将到期的标书", 0, count);
        }
        LOGGER.info("标书状态更新成功，更新数量："+count);
        return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "标书状态更新成功",0, count);
    }


    @Scheduled(cron = "0 0/3 * * * ?")
    public void saveEkpStatus () {
        srmPonBidItemPricesService.saveEkpStatus();
    }*/

}
