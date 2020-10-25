package saaf.common.fmw.bpm.utils;

/**
 * 定义一些工作流常量
 * 
 * @author Rocky
 *
 */
public interface WorkflowConstant {
	/**
	 * 流程启动者，提交人
	 */
	String START_USER_ID = "startUserId";
	/**
	 * 当前登录用户
	 */
	String CURRENT_USER_ID = "currentUserId";
	/**
	 * 任务Id
	 */
	String TASK_ID = "taskId";
	/**
	 * 流程实例Id
	 */
	String PROCESS_INSTANCE_ID = "processInstanceId";
	/**
	 * 提交
	 */
	String SUBMIT = "submit";
	/**
	 * 撤回
	 */
	String CALL_BACK = "callBack";
	/**
	 * 自由跳转
	 */
	String JUMP = "jump";
	/**
	 * 驳回
	 */
	String REJECT = "reject";
}
