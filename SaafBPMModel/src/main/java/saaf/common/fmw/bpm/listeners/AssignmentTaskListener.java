package saaf.common.fmw.bpm.listeners;


import com.yhg.bpm.activiti.custom.bean.ActivitiTaskPayloadBean;
import com.yhg.bpm.activiti.custom.core.ActivitiCoreImpl;

import net.sf.json.JSONObject;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.SaafActInstanceBusinessEntity_HI;
import saaf.common.fmw.bpm.model.inter.ISaafActInstanceBusiness;
import saaf.common.fmw.common.utils.SaafToolUtils;

public class AssignmentTaskListener implements TaskListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AssignmentTaskListener.class);
    @Autowired
    private ISaafActInstanceBusiness saafActInstanceBusinessServer;// = SaafToolUtils.context.getBean("saafActInstanceBusinessServer", ISaafActInstanceBusiness.class);
    
    public AssignmentTaskListener() {
        super();
    }

    /**用来指定任务的办理人*/
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("com.base.activiti.custom.api.ActivitiTaskHandlerListener-->notify====>" + delegateTask.getProcessInstanceId() + " " + delegateTask.getName() + "  " + delegateTask.getId() + "  " + delegateTask.getEventName() + "  " + delegateTask.getFormKey());
        SaafActInstanceBusinessEntity_HI saafActInstanceBusinessEntity_HI = new SaafActInstanceBusinessEntity_HI();
        saafActInstanceBusinessEntity_HI.setSaibBusinessId_("gavinBusinessId_01");
        saafActInstanceBusinessEntity_HI.setSaibInstanceId_(delegateTask.getProcessInstanceId());
        saafActInstanceBusinessEntity_HI.setSaibTaskId_(delegateTask.getId());
        saafActInstanceBusinessServer.saveSaafActInstanceBusinessInfo(com.alibaba.fastjson.JSONObject.parseObject(com.alibaba.fastjson.JSONObject.toJSONString(saafActInstanceBusinessEntity_HI)));
        ActivitiTaskPayloadBean payloadBean = (ActivitiTaskPayloadBean)delegateTask.getVariable(ActivitiCoreImpl.VARIABLE_NAME_PAYLOAD_BEAN);
        System.out.println("com.base.activiti.custom.api.ActivitiTaskHandlerListener-->notify====>[payloadBean:" + JSONObject.fromObject(payloadBean) + "]");
    }
}
