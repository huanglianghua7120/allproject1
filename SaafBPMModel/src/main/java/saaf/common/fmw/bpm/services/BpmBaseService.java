package saaf.common.fmw.bpm.services;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.ActHiProcinstEntity_HI;
import saaf.common.fmw.bpm.model.inter.ISaafActInstanceBusiness;
import saaf.common.fmw.bpm.model.inter.server.ActHiProcinstServer;
import saaf.common.fmw.bpm.utils.WorkflowConstant;
import saaf.common.fmw.services.CommonAbstractServices;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.bpm.activiti.custom.bean.ActivitiTaskPayloadBean;
import com.yhg.bpm.activiti.custom.core.ActivitiCoreImpl;
import com.yhg.bpm.activiti.custom.core.IActivitiCore;


@Component("bpmBaseService")
@Path("/bpmservice")
public class BpmBaseService extends CommonAbstractServices{
    private static final Logger LOGGER = LoggerFactory.getLogger(BpmBaseService.class);
    @Autowired
    private IActivitiCore activitiCore;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private ISaafActInstanceBusiness saafActInstanceBusinessServer;
    @Autowired
    private ActHiProcinstServer actHiProcinstServer;
       
    public BpmBaseService() {
        super();
    }
    
    @POST
    @Path("deploymentProcess")
    public String deploymentProcess(@FormParam("processName")
        String processName, @FormParam("descriptor")
        String descriptor) {
        String executeResult = null;
        Deployment deployment = activitiCore.deploymentProcessDefinition(processName, descriptor);
        if (null != deployment) {
            executeResult = "success";
        } else {
            executeResult = "fail";
        }
        LOGGER.info(JSONObject.toJSONString(deployment));
        return executeResult;
    }
    
    /**
     * 创建流程任务，不启动流程<p>
     * 创建任务时，根据流程定义Id，取得任务节点formKey，跳转到页面由用户填写数据，用户提交后再启动流程
     * @param processDefinitionId 流程定义Id
     * @param params 流程定义参数
     * @return
     */
    @POST
    @Path("createInitProcess")
    public String createInitProcess(@FormParam("processDefinitionId")String processDefinitionId,
    		@FormParam("params")String params){
    	JSONObject paramsJSON = JSONObject.parseObject(params);
    	  
    	UserTask task = this.getFirstUserTask(processDefinitionId);
    	String formKey = task.getFormKey();
    	
    	JSONObject retJSON = new JSONObject();
    	retJSON.put("formKey", formKey);
    	retJSON.put("processDefinitionId", processDefinitionId);
    	retJSON.put("processDefinitionKey", paramsJSON.getString("key_"));
    	return convertResultJSONObj("S", "", 1, retJSON);
    }
    
    /**
     * 获取流程定义的第一个用户任务节点
     * @param processDefinitionId 流程定义Id
     * @return
     */
    private UserTask getFirstUserTask(String processDefinitionId){
    	BpmnModel model = repositoryService.getBpmnModel(processDefinitionId);
    	if(model != null) {  
            Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();  
            String targetRef = "";//记录启动节点流向的任务节点Id
            for(FlowElement e : flowElements) {
            	
            	if(e instanceof StartEvent){
            		StartEvent startEvent = (StartEvent)e;
            		List<SequenceFlow> outgoingFlows = startEvent.getOutgoingFlows();
            		SequenceFlow flow = outgoingFlows.get(0);
            		targetRef = flow.getTargetRef();
            		continue;
            	}
            	 
                if(e instanceof UserTask){
                    UserTask task = (UserTask)e;
                    if(task.getId().equals(targetRef)){
	                    //formKey = task.getFormKey();
                    	return task;
	                    //break;
                    }
                }
            }
    	}
    	return null;
    }
    
    /** 
     * 获取流程的下一个节点 且要经过规则引擎判断后的节点 
     * @param taskId 
     * @return 
     */  
    private List<FlowElement> getNextNode(Task task) {  
        if(task==null) {  
            return null;  
        }  
        List<FlowElement> list = new ArrayList<FlowElement>();  
          
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();  
          
        //当前活动节点  
        String activitiId = processInstance.getActivityId();  
          
        //pmmnModel 遍历节点需要它  
        BpmnModel bpmnModel =  repositoryService.getBpmnModel(task.getProcessDefinitionId());  
  
        List<Process> processList = bpmnModel.getProcesses();
        //循环多个物理流程  
        for(Process process:processList) {  
            //返回该流程的所有任务，事件  
            Collection<FlowElement> cColl = process.getFlowElements();  
            //遍历节点  
            for(FlowElement f :cColl) {  
                //如果改节点是当前节点 者 输出该节点的下一个节点  
                if(f.getId().equals(activitiId)) {  
                    List<SequenceFlow>  sequenceFlowList = new ArrayList<SequenceFlow>();  
                    //通过反射来判断是哪种类型  
                    if(f instanceof org.activiti.bpmn.model.StartEvent) {  
                        //开始事件的输出路由  
                        sequenceFlowList    = ((org.activiti.bpmn.model.StartEvent) f).getOutgoingFlows();  
                    }else if(f instanceof org.activiti.bpmn.model.UserTask) {  
                        sequenceFlowList    = ((org.activiti.bpmn.model.UserTask) f).getOutgoingFlows();  
                        for(SequenceFlow sf :sequenceFlowList)  {  
                            String targetRef = sf.getTargetRef();  
                            FlowElement ref = process.getFlowElement(targetRef);  
                        //  nextActivitiIdList.add(ref.getId());  
                            list.add(ref);  
                        }  
                          
                    }else if(f instanceof org.activiti.bpmn.model.SequenceFlow) {  
                          
                    }else if(f instanceof org.activiti.bpmn.model.EndEvent) {  
                        sequenceFlowList    = ((org.activiti.bpmn.model.EndEvent) f).getOutgoingFlows();  
                    }  
                    break;  
                }   
                      
            }  
              
        }     
        return list;  
    }  
    
    /**
     * 启动流程，根据流程定义Key启动流程，即启动最新版本的流程定义实例
     * @param processDefinitionKey 流程定义Key
     * @param params 参数
     * @return
     */
    @POST
    @Path("startProcess")
    public String startProcess(@FormParam("processDefinitionId")String processDefinitionId,
    		@FormParam("processDefinitionKey")String processDefinitionKey,
    		@FormParam("params")String params){
    	
    	JSONObject retJSON = new JSONObject();
		try {
			JSONObject variables = JSON.parseObject(params);
			String currentUserId = getSessionUserId().toString();
			variables.put(WorkflowConstant.START_USER_ID, currentUserId);//设置启动用户
			variables.put(WorkflowConstant.CURRENT_USER_ID, currentUserId);//设置当前用户
			//variables.put("assignmentTaskListener", assignmentTaskListener);
			
			UserTask task = this.getFirstUserTask(processDefinitionId);
			ActivitiTaskPayloadBean payloadBean = new ActivitiTaskPayloadBean();
			payloadBean.setCurrentUser(currentUserId);
			payloadBean.setNextTaskHander(task.getAssignee());
			payloadBean.setSubmitUser(currentUserId);
			payloadBean.setCurrentAssignee(currentUserId);
			payloadBean.setOutcome(task.getId());
			variables.put(ActivitiCoreImpl.VARIABLE_NAME_PAYLOAD_BEAN, payloadBean);
			processEngine.getIdentityService().setAuthenticatedUserId(currentUserId);//设置认证用户
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey,variables);
			
			if(processInstance!=null){
				/***********start****************/
				/**
				 * 由于启动流程时，起草人提交后直接通过第一个任务节点前往下一个用户任务
				 * 所以流程的第一个节点必须是起草人提交任务，第二个任务后才是流程审批操作
				 */
				Task firstTask = null;
				List<Task> taskLists = taskService.createTaskQuery().taskCandidateOrAssigned(currentUserId).processInstanceId(processInstance.getId()).orderByTaskCreateTime().asc().list();
				if(taskLists!=null && !taskLists.isEmpty()){
					firstTask = taskLists.get(0);//获取第一个任务
					//Authentication.setAuthenticatedUserId(currentUserId);
					//taskService.addComment(firstTask.getId(), processInstance.getId(), "提交流程");
					//taskService.setAssignee(firstTask.getId(), currentUserId);//设置当前用户为用户所有者
					//variables.put("startUserId", currentUserId);
					taskService.complete(firstTask.getId(),variables);
				}
				/*************end***************/
				
				retJSON.put(STATUS, "success");
				retJSON.put(MSG, "流程启动完成");
				retJSON.put("processInstanceId", processInstance.getId());
				retJSON.put("BusinessKey", processInstance.getBusinessKey());
				retJSON.put("name", processInstance.getName());
				retJSON.put("processDefinitionKey", processInstance.getProcessDefinitionKey());
			}else{
				throw new Exception("流程启动失败，返回流程实例为null");
			}
		} catch (Exception e) {
			LOGGER.error("启动流程失败",e);
			retJSON.put(STATUS, "fail");
			retJSON.put(MSG, e.getMessage());
		}
    	
    	return retJSON.toJSONString();
    }
    
    @POST
    @Path("completeProcess")
    public String completeProcess(@FormParam("taskId")String taskId,
    		@FormParam("params")String params,@FormParam("comment")String comment){
    	JSONObject retJSON = new JSONObject();
		try {
			JSONObject variables = JSON.parseObject(params);
			variables.put(WorkflowConstant.CURRENT_USER_ID, getSessionUserId().toString());//设置当前用户
			
			Task currentTask = activitiCore.findTaskByTaskId(taskId);
			variables.put(ActivitiCoreImpl.VARIABLE_NAME_PAYLOAD_BEAN, createPayloadBean(currentTask));
			ActHiProcinstEntity_HI actHiProcinst = actHiProcinstServer.findActHiProcinstEntityById(currentTask.getProcessInstanceId());
			variables.put(WorkflowConstant.START_USER_ID, actHiProcinst.getStartUserId_());
			//查询当前任务
			Authentication.setAuthenticatedUserId(String.valueOf(getSessionUserId()));
			taskService.addComment(taskId, currentTask.getProcessInstanceId(), comment);
	        taskService.complete(taskId, variables);
	        
	        //activitiCore.completeMyPersonalTask(taskId, variables, comment);
			retJSON.put(STATUS, "success");
			retJSON.put(MSG, "任务处理完成");
		}catch (Exception e) {
			LOGGER.error("任务处理失败",e);
			retJSON.put(STATUS, "fail");
			retJSON.put(MSG, e.getMessage());
		}
    	
    	return retJSON.toJSONString();
    }

    /**
     * 根据当前任务Id创建PayloadBean
     * @param taskId
     * @return
     */
	private ActivitiTaskPayloadBean createPayloadBean(Task task) {
		//查询当前任务
		//Task task = activitiCore.findTaskByTaskId(taskId);
		//查询历史任务
		List<HistoricTaskInstance> hisTaskInsts = historyService.createHistoricTaskInstanceQuery().processInstanceId(task.getProcessInstanceId()).orderByTaskCreateTime().asc().list();
		HistoricTaskInstance lastTaskInst = null;
		if(hisTaskInsts!=null && !hisTaskInsts.isEmpty()){
			lastTaskInst = hisTaskInsts.get(hisTaskInsts.size()-1);
		}
		//查询下一个任务
		//TaskDefinition nextTaskDef = activitiCore.findNextTaskDefinition(task.getProcessInstanceId());
		String netxtTaskHandler = "",outcome = "";
		List<FlowElement> flowElements = getNextNode(task);
		if(flowElements!=null && !flowElements.isEmpty()){
			for(FlowElement e : flowElements){
				if(e instanceof UserTask){
					netxtTaskHandler = ((UserTask) e).getAssignee();
					outcome = e.getId();
					break;
				}
			}
		}
		//查询流程实例
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		
		ActivitiTaskPayloadBean payloadBean = new ActivitiTaskPayloadBean();
		String currentUserId = getUserSessionBean().getUserId().toString();
		payloadBean.setCurrentUser(currentUserId);
		payloadBean.setSubmitUser(currentUserId);
		payloadBean.setNextTaskHander(netxtTaskHandler);//下一个任务操作人
		payloadBean.setBusinessKeyValue(processInstance.getBusinessKey());
		payloadBean.setCurrentAssignee(currentUserId);//当前操作人
		payloadBean.setCurrentTaskDefId(task.getTaskDefinitionKey());
		payloadBean.setCurrentTaskName(task.getName());
		if(lastTaskInst != null){
			payloadBean.setLastAssignee(lastTaskInst.getAssignee());
			payloadBean.setLastTaskDefId(lastTaskInst.getTaskDefinitionKey());
			payloadBean.setLastTaskDefName(lastTaskInst.getName());
			payloadBean.setLastUser(lastTaskInst.getOwner()==null?lastTaskInst.getAssignee():lastTaskInst.getOwner());
		}
		payloadBean.setOutcome(outcome);//目标流向
		return payloadBean;
	}
    
    
    @GET
    @Path("graphHistoryProcessInstance")
    public void graphHistoryProcessInstance(@QueryParam("processInstanceId")String processInstanceId) throws Exception{
    	HistoricProcessInstance processInstance =  historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    	//获取流程图
    	BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
    	ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
    	Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
    	
    	ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
    	ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());
    	List<HistoricActivityInstance> highLightedActivitList =  historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
    	//高亮环节id集合
    	List<String> highLightedActivitis = new ArrayList<String>();
    	//高亮线路id集合
    	List<String> highLightedFlows = getHighLightedFlows(definitionEntity,highLightedActivitList);
    	for(HistoricActivityInstance tempActivity : highLightedActivitList){
    	    String activityId = tempActivity.getActivityId();
    	    highLightedActivitis.add(activityId);
    	}
    	InputStream is = null;
    	try {
			is = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis,highLightedFlows,"宋体","宋体","宋体",null,1.0);
			//is = diagramGenerator.generatePngDiagram(bpmnModel);
			response.setContentType("image/png");
			byte[] bytes = new byte[1024];
			int len;
			while ((len = is.read(bytes)) != -1) {
				response.getOutputStream().write(bytes, 0, len);
			}
    	}catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally{
			if(is != null){
				is.close();
			}
		}
    }
    
    /**
     * 获取需要高亮的线
     * @param processDefinitionEntity
     * @param historicActivityInstances
     * @return
     */
    private List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances) {
        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i)
                            .getActivityId());// 得到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
            ActivityImpl sameActivityImpl1 = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i + 1)
                            .getActivityId());
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances
                        .get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances
                        .get(j + 1);// 后续第二个节点
                if (activityImpl1.getStartTime().equals(
                        activityImpl2.getStartTime())) {
                    // 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // 有不相同跳出循环
                    break;
                }
            }
            List<PvmTransition> pvmTransitions = activityImpl
                    .getOutgoingTransitions();// 取出节点的所有出去的线
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
                        .getDestination();
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }
}
