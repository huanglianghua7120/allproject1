package saaf.common.fmw.bpm.listeners;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.springframework.stereotype.Component;

/**
 * 全局的流程监听事件
 * @author Rocky
 *
 */
@Component("globalActivitiEventListener")
public class GlobalActivitiEventListener implements ActivitiEventListener {

	@Override
	public void onEvent(ActivitiEvent event) {
		
		switch(event.getType()){
			case TASK_CREATED:
				System.err.println("=====================================");
				System.out.println("创建任务事件");
				System.err.println("=====================================");
				break;
			case TASK_ASSIGNED:
				//任务设置处理人后同时向处理人发送待办通知
				System.err.println("=====================================");
				System.out.println("设置流程处理人事件");
				System.err.println("=====================================");
				break;
			case TASK_COMPLETED:
				System.err.println("=====================================");
				System.out.println("任务完成事件");
				System.err.println("=====================================");
				break;
			default:
				
		}
	}

	@Override
	public boolean isFailOnException() {
		return false;
	}

}
