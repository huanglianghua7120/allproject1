package saaf.common.fmw.base.ws.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SaafBaseOperlogEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafBaseOperlog;
import saaf.common.fmw.base.ws.ekp.AttachmentForm;
import saaf.common.fmw.base.ws.ekp.IKmReviewWebserviceService;
import saaf.common.fmw.base.ws.ekp.KmReviewParamterForm;
import saaf.common.fmw.base.ws.process.SyncProcess;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: ZHJ
 * @date:Created in 11:52 2020/1/2
 * @modify By:
 * @description :
 */
@Component("eKPSyncService")
public class EKPSyncService {
    private Logger LOGGER = LoggerFactory.getLogger(EKPSyncService.class);
    public final static String TEN = "10";
    public final static String TWENTY = "20";
    @Autowired
    private SyncProcess syncProcess;
    @Autowired
    private ISaafBaseOperlog iSaafBaseOperlog;
    /**
     * Description: 提交EKP审批——流程启动
     * @param docStatus 文档状态，可以为草稿（"10"）或者待审（"20"）两种状态，默认为待审，可传空值
     * @param processId 流程ID
     * @param tempateId 模板ID
     * @param docSubject 模板标题
     * @param creator 创建人
     * @param keyword 关键字
     * @param formValues 流程表单
     * @param attachments 附件
     * @param lineFileArray 行附件列表
     * @param system 模块区分标识
     * @return 流程实例ID
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-01-02     zhj          创建
     * ==============================================================================
     */
    public String createFlow(String docStatus,String processId, String tempateId, String docSubject, String creator, List<String> keyword,
                             Map<String,Object> formValues, Map<String,Map<String,byte[]>> attachments, JSONArray lineFileArray,String system) throws Exception{
        //方法执行开始时间
        Long startTime = System.currentTimeMillis();
        LOGGER.info("<————EKP启动流程请求，开始时间————>：{}", DateUtil.date2Str(new Date(),"yyyy-MM-dd HH:mm:ss"));
        LOGGER.info("<————请求开始，执行EKP启动方法————>：EKPSyncService.createFlow");
        LOGGER.info("<————请求报文————>：tempateId={}, docSubject={}, creator={}, keyword={}",tempateId,docSubject,creator, JSON.toJSONString(keyword));
        //EKP返回结果
        String result = "";
        //日志
        SaafBaseOperlogEntity_HI operlog = new SaafBaseOperlogEntity_HI();
        operlog.setTitle(docSubject);
        operlog.setBusinessType(docSubject);
        operlog.setMethod("saaf.common.fmw.base.ws.service.EKPSyncService.createFlow()");
        operlog.setOperParam("processId:"+processId+";creator:"+creator+";formValues:"+formValues);
        operlog.setOperatorUserId(-1);
        try{
            KmReviewParamterForm form = new KmReviewParamterForm();
            // 文档模板id
            form.setFdTemplateId(tempateId);
            //流程ID
            if(null != processId && !"".equals(processId)){
                form.setFdId(processId);
            }
            // 文档标题
            form.setDocSubject(docSubject);
            //当前用户的员工工号
            form.setDocCreator("{\"PersonNo\": \""+creator+"\"}");
            // 文档关键字
            form.setFdKeyword(JSON.toJSONString(keyword));
            //文档状态-默认待审
            form.setDocStatus(null == docStatus || "".equals(docStatus) ? TWENTY:docStatus);
            // 流程表单
            form.setFormValues(JSON.toJSONString(formValues));
            LOGGER.info("FdKeyword:{}",form.getFdKeyword());
            LOGGER.info("FormValues:{}",form.getFormValues());
            //流程附件
            if(null != attachments && attachments.size()>0){
                List<AttachmentForm> attFormsList = new ArrayList<>();
                AttachmentForm attForm = null;
                for(String key:attachments.keySet()) {
                    for (String name : attachments.get(key).keySet()) {
                        attForm = new AttachmentForm();
                        attForm.setFdKey(key);
                        attForm.setFdFileName(name);
                        attForm.setFdAttachment(attachments.get(key).get(name));
                        attFormsList.add(attForm);
                    }
                }
                form.getAttachmentForms().addAll(attFormsList);
            }
            if(null != lineFileArray && lineFileArray.size()>0){
                //行附件列表
                form.setAttachmentValues(lineFileArray.toString());
            }
            IKmReviewWebserviceService iKmReviewWebserviceService = syncProcess.getKmReviewWebserviceServiceService().getIKmReviewWebserviceServicePort();
            if(null == processId || "".equals(processId)){
                //首次提交
                result = iKmReviewWebserviceService.addReview(form);
            }else{
                //update
                if("POS".equals(system)){
                    form.setFlowParam("{operationType: \"handler_pass\",auditNote: \"审批意见\"}");
                    result = iKmReviewWebserviceService.approveProcess(form);
                }else{
                    result = iKmReviewWebserviceService.updateReviewInfo(form);
                }
            }
            long costMs = System.currentTimeMillis() - startTime;
            LOGGER.info("<————{}请求结束，耗时：{}ms————>","EKPSyncService.createFlow", costMs);
            LOGGER.info("<————EKP启动流程请求结束，返回数据————>：{}",result);
            if(null == result || "".equals(result)){
                operlog.setStatus("FAIL");
                operlog.setErrorMsg("EKP没有返回值"+result);
            }else{
                boolean flag = this.isJSONValid(result);
                if(flag){
                    //返回json格式
                    operlog.setStatus("FAIL");
                    operlog.setErrorMsg(result);
                }else{
                    operlog.setStatus("SUCCESS");
                }
            }
            operlog.setOperRespParam(result);
            iSaafBaseOperlog.saveOperLog(operlog);
            return result;
        }catch (Exception e){
            LOGGER.error("操作失败！未知错误：{}",e);
            long costMs = System.currentTimeMillis() - startTime;
            LOGGER.info("<————EKP启动流程请求结束，耗时：{}ms————>",costMs);
            operlog.setStatus("FAIL");
            operlog.setErrorMsg(e.getMessage());
            operlog.setOperRespParam(e.toString());
            iSaafBaseOperlog.saveOperLog(operlog);
            return null;
        }
    }

    /**
     * Description:判断一个字符串是否是合法的JSON字符串，是json格式返回true，否则为false
     * @param str
     * @return
     */
    public boolean isJSONValid(String str) {
        try {
            //json对象
            JSONObject.parseObject(str);
        } catch (JSONException ex) {
            try {
                //json数组
                JSONObject.parseArray(str);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
