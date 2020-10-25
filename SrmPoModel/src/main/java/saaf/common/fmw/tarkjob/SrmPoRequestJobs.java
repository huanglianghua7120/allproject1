package saaf.common.fmw.tarkjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import saaf.common.fmw.po.services.PurchaseOrderServices;
import saaf.common.fmw.services.CommonAbstractServices;

/**
 * @author: ZWJ
 * @date:Created in 10:21 2020/3/19
 * @modify By:
 * @description :PO模块的定时任务
 */
@Component("srmPoRequestJobs")
public class SrmPoRequestJobs extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRequestJobs.class);

    public SrmPoRequestJobs(){
        super();
    }

    @Autowired
    private PurchaseOrderServices purchaseOrderServices;

    /*@Scheduled(cron = "0 0 0 1/1 * ?")
    public void savePoTechnicalNum (){
        purchaseOrderServices.savePoTechnicalNum();
    }

    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void savePoSubprojectNum (){
        purchaseOrderServices.savePoSubprojectNum();
    }*/
}
