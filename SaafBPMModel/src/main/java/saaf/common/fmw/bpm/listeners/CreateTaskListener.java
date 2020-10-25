package saaf.common.fmw.bpm.listeners;


import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import saaf.common.fmw.bpm.model.entities.SaafActInstanceBusinessEntity_HI;
import saaf.common.fmw.bpm.model.inter.ISaafActInstanceBusiness;
import saaf.common.fmw.bpm.utils.SaafToolUtils;

import com.alibaba.fastjson.JSONObject;
import com.yhg.bpm.activiti.custom.bean.ActivitiTaskPayloadBean;
import com.yhg.bpm.activiti.custom.core.ActivitiCoreImpl;

public class CreateTaskListener implements TaskListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitiCoreImpl.class);
    private static final String EMAIL_SPLIT = ",";
    
    public CreateTaskListener() {
        super();
    }

    /**用来指定任务的办理人*/
    @Override
    public void notify(DelegateTask delegateTask) {
        LOGGER.info("com.base.activiti.custom.api.ActivitiTaskHandlerListener-->notify====>" + delegateTask.getTenantId() + " " + delegateTask.getName() + "  " +
                    delegateTask.getId() + "  " + delegateTask.getEventName() + "  " + delegateTask.getFormKey());
        ActivitiTaskPayloadBean payloadBean = (ActivitiTaskPayloadBean)delegateTask.getVariable(ActivitiCoreImpl.VARIABLE_NAME_PAYLOAD_BEAN);
        LOGGER.info("com.base.activiti.custom.api.ActivitiTaskHandlerListener-->notify====>[payloadBean:" + JSONObject.toJSONString(payloadBean) + "]");
        
        ISaafActInstanceBusiness saafActInstanceBusinessServer = (ISaafActInstanceBusiness)SaafToolUtils.context.getBean("saafActInstanceBusinessServer");
        SaafActInstanceBusinessEntity_HI entity = new SaafActInstanceBusinessEntity_HI();
		entity.setSaibInstanceId_(delegateTask.getProcessInstanceId());
		entity.setSaibTaskId_(delegateTask.getId());
		entity.setSaibTaskTitle_(delegateTask.getEventName());
		entity.setSaibBusinessId_((String)delegateTask.getVariable("businessId"));
		entity.setOperatorUserId(Integer.parseInt(payloadBean.getCurrentUser()));
		saafActInstanceBusinessServer.saveSaafActInstanceBusinessInfo(JSONObject.parseObject(JSONObject.toJSON(entity).toString()));
        
		//delegateTask.setAssignee(payloadBean.getNextTaskHander());
		
		
        //        if(null != payloadBean){
        //            EmailInfoBean emailInfoBean = payloadBean.getEmailInfoBean();
        //            if(null != emailInfoBean){
        //                //发送邮件给指定的用户
        //                String mailSendMs = emailInfoBean.getMailSendMs();
        //                String mailSendCs = emailInfoBean.getMailSendCS();
        //                String mailSendTo = emailInfoBean.getMailSendTo();
        //                String attarchmentFilePath = emailInfoBean.getAttarchmentFilePath();
        //                List<String> toList = new ArrayList<String>();
        //                toList.add("yuanhaigang@chinasie.com");
        //                if(null != mailSendTo && !"".equals(mailSendTo)){
        //                    String[] mailSendTo_ = mailSendTo.split(EMAIL_SPLIT);
        //                    for(String sendTo : mailSendTo_){
        //                        toList.add(sendTo);
        //                    }
        //                }
        //                List<String> csList = new ArrayList<String>();
        //                csList.add("819038939@qq.com");
        //                if(null != mailSendTo && !"".equals(mailSendTo)){
        //                    String[] mailSendCs_ = mailSendCs.split(EMAIL_SPLIT);
        //                    for(String sendCs : mailSendCs_){
        //                        csList.add(sendCs);
        //                    }
        //                }
        //                List<String> msList = new ArrayList<String>();
        //                if(null != mailSendMs && !"".equals(mailSendMs)){
        //                    String[] mailSendMs_ = mailSendMs.split(EMAIL_SPLIT);
        //                    for(String sendMs : mailSendMs_){
        //                        msList.add(sendMs);
        //                    }
        //                }
        //                //TODO 获取附件
        //                List<String> attachmentList = new ArrayList<String>();
        //                String subject = "流程实例待办--" + delegateTask.getName();
        //
        //                //TODO 从配置中获取配置好的邮件模板
        //                String content = "<h4>" + delegateTask.getAssignee() + "你好：</h4></br>&#9请你处理<h2>" + delegateTask.getName() + "</h2>的待办。任务Id是<h2>" + delegateTask.getId() + "</h2>";
        //                MailSenderInfoBean mailSenderInfo = new MailSenderInfoBean();
        //                mailSenderInfo.setValidate(true);
        //                mailSenderInfo.setToList(toList);
        //                mailSenderInfo.setCsList(csList);
        //                mailSenderInfo.setMsList(msList);
        //                mailSenderInfo.setArrArchiveList(attachmentList);
        //                mailSenderInfo.setSubject(subject);
        //                mailSenderInfo.setContent(content);
        //
        //                //在这里如果发送邮件的功能不能使用，20s之后程序会自动放弃这个模块
        //                long timeOutSeconds = 20000; //将方法的执行超时时间设置为20秒
        ////                try {
        //                    //EmailSender emailSender = new EmailSender();
        //                    //emailSender.sendEmail(mailSenderInfo);
        //                    MethodTimeOutUtil mTimeOutUtil = new MethodTimeOutUtil(new CreateTaskListener(), "sendEmail", new Class[]{MailSenderInfoBean.class}, new Object[]{mailSenderInfo}, timeOutSeconds);
        //                    mTimeOutUtil.executeMethodTime();
        ////                } catch (EmailException e) {
        ////                    LOGGER.error(e.getMessage(), e);
        ////                }
        //            }
        //            //指定个人任务的办理人，也可以指定组任务的办理人
        //            String nextTaskHandler = payloadBean.getNextTaskHander();
        //            if(null == nextTaskHandler || "".equals(nextTaskHandler.trim())){
        //                //TODO 通过payload的数据去数据库里面判断目前需要执行那个服务来获取一个处理人
        //                //如果系统没有执行下一个处理人
        //                //TODO 1.根据当前的业务数据从之前配置好的地方获取
        //                //TODO 2.如果还是获取不到直接从配置的默认处理人中获取
        //                //如果还是不能获取到直接给一个默认的处理人
        //                nextTaskHandler = "Gavin";
        //            }
        //            delegateTask.addCandidateUser(nextTaskHandler);
        //            delegateTask.addCandidateUser(nextTaskHandler);
        //            delegateTask.setAssignee(nextTaskHandler);
        //        }

        //TODO 将当前的任务发送到统一待办平台
    }
}
