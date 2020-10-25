package saaf.common.fmw.tarkjob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierCertificate;
import saaf.common.fmw.pos.services.QualificationInfoServices;
import saaf.common.fmw.pos.services.SrmPosSupplierCertificateService;
import saaf.common.fmw.services.CommonAbstractServices;

@Component("qualificationInfoJobs")
public class QualificationInfoJobs extends CommonAbstractServices {
    @Autowired
    private QualificationInfoServices srmQualificationInfoServices;

    @Autowired
    private SrmPosSupplierCertificateService srmPosSupplierCertificateService;

    /*@Scheduled(cron = "0 0/3 * * * ?")
    public void saveEkpStatus () {
        srmQualificationInfoServices.saveEkpStatus();
    }

    @Scheduled(cron = "0 0/3 * * * ?")
    public void saveCreateWarning (){
        srmPosSupplierCertificateService.saveCreateWarning();
    }*/
}
