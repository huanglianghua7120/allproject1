package saaf.common.fmw.bpm.listeners;


import com.yhg.bpm.activiti.custom.bean.ActivitiTaskPayloadBean;
import com.yhg.bpm.activiti.custom.core.ActivitiCoreImpl;

import net.sf.json.JSONObject;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AllTaskListener implements TaskListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AllTaskListener.class);

    public AllTaskListener() {
        super();
    }

    /**用来指定任务的办理人*/
    @Override
    public void notify(DelegateTask delegateTask) {
        LOGGER.info("com.base.activiti.custom.api.ActivitiTaskHandlerListener-->notify====>" + delegateTask.getTenantId() + " " + delegateTask.getName() + "  " +
                    delegateTask.getId() + "  " + delegateTask.getEventName() + "  " + delegateTask.getFormKey());
        ActivitiTaskPayloadBean payloadBean = (ActivitiTaskPayloadBean)delegateTask.getVariable(ActivitiCoreImpl.VARIABLE_NAME_PAYLOAD_BEAN);
        LOGGER.info("com.base.activiti.custom.api.ActivitiTaskHandlerListener-->notify====>[payloadBean:" + JSONObject.fromObject(payloadBean) + "]");
        LOGGER.info("com.base.activiti.custom.api.ActivitiTaskHandlerListener-->notify====>" + delegateTask.getTenantId() + " " + delegateTask.getName() + "  " +
                    delegateTask.getId() + "  " + delegateTask.getEventName() + "  " + delegateTask.getFormKey());
        LOGGER.info("com.base.activiti.custom.api.ActivitiTaskHandlerListener-->notify====>[payloadBean:" + JSONObject.fromObject(payloadBean) + "]");
    }
}
