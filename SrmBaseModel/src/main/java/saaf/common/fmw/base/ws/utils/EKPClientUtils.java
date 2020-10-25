package saaf.common.fmw.base.ws.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saaf.common.fmw.base.ws.ekp.IKmReviewWebserviceService;
import saaf.common.fmw.base.ws.ekp.IKmReviewWebserviceServiceService;
import saaf.common.fmw.base.ws.ekp.KmReviewParamterForm;

public class EKPClientUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(EKPClientUtils.class);

    /**
     * 创建EKP单据
     * @param paramterForm
     * @return
     * @throws Exception
     */
    public String addReview (KmReviewParamterForm paramterForm) throws Exception {

        IKmReviewWebserviceServiceService webService= new IKmReviewWebserviceServiceService();
        IKmReviewWebserviceService service = webService.getIKmReviewWebserviceServicePort();

        return service.addReview(paramterForm);

    }

    /**
     * 修改EKP单据
     * @param paramterForm
     * @return
     * @throws Exception
     */
    public String updateReviewInfo (KmReviewParamterForm paramterForm) throws Exception {

        IKmReviewWebserviceServiceService webService= new IKmReviewWebserviceServiceService();
        IKmReviewWebserviceService service = webService.getIKmReviewWebserviceServicePort();

        return service.updateReviewInfo(paramterForm);

    }


}
