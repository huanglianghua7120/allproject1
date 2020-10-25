package saaf.common.fmw.bpm.listeners;

import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectMessageTask implements TaskListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectMessageTask.class);

    @SuppressWarnings("unchecked")
    public void notify(DelegateTask delegateTask) {
        LOGGER.info("i am CollectMessageTask.");
        LOGGER.info("out : " + (Map<String, Object>)delegateTask.getVariables().get("out"));
        LOGGER.info("all : " + delegateTask.getVariables());
    }
}