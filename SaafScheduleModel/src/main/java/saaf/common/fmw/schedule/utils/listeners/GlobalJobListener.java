package saaf.common.fmw.schedule.utils.listeners;


import com.yhg.hibernate.core.dao.ViewObject;

import java.sql.Timestamp;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import saaf.common.fmw.schedule.model.entities.SaafSchedulesEntity_HI;
import saaf.common.fmw.schedule.utils.MyLogUtils;
import static saaf.common.fmw.schedule.utils.MyLogUtils.error;
import static saaf.common.fmw.schedule.utils.MyLogUtils.info;


public class GlobalJobListener implements JobListener {

    private String name = "DEFAULT_JOB_LISTENR_NAME";

    private final Logger log = LoggerFactory.getLogger(GlobalJobListener.class);

    private ViewObject saafSchedulesDAOHI;

    @Resource(name = "saafSchedulesDAO_HI")
    public void setSaafSchedulesDAOHI(ViewObject saafSchedulesDAOHI) {
        this.saafSchedulesDAOHI = saafSchedulesDAOHI;
    }

    public GlobalJobListener() {
        super();
    }

    public GlobalJobListener(String name) {
        super();
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        ViewObject vo = this.saafSchedulesDAOHI;
        SaafSchedulesEntity_HI request = null;
        List<SaafSchedulesEntity_HI> list = vo.findByProperty("quartzJobName", context.getJobDetail().getKey().getName());
        log.info("----------------------jobToBeExecuted-" + context.getJobDetail().getKey().getName() + ",listIsNull:" + (list == null) + ",list.length=" +
                 (list != null ? list.size() : 0));

        if (list != null) {
            request = list.get(0);
        } else {
            log.error("------------获取记录失败,jobName:" + context.getJobDetail().getKey().getName() + ",job-ToBeExecuted-即将执行（执行中）---------------");
        }

        Date time = new Date();
        if (request != null) {
            request.setStatusCode("OK_STATUSCODE");
            request.setPhaseCode("JOBEXECUTING_PHASECODE"); //JOBTOBEEXECUTED_PHASECODE
        }
        if (request != null && request.getActualStartDate() == null) {
            request.setActualStartDate(new Timestamp(time.getTime()));
        }
        vo.saveOrUpdate(request);
        log.info("------------job-ToBeExecuted-即将执行（执行中）---------------" + context.getJobDetail().toString());
        info(context.getJobDetail().getKey().getName(), " - 请求执行中");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        log.info("------------job-ExecutionVetoed-执行被否定（取消）---------------" + context.getJobDetail().toString());
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        int totalCount = 0;
        int errorCount = 0;
        SaafSchedulesEntity_HI request = null;
        List<SaafSchedulesEntity_HI> list = saafSchedulesDAOHI.findByProperty("quartzJobName", context.getJobDetail().getKey().getName());

        log.info("----------------------jobWasExecuted-" + context.getJobDetail().getKey().getName() + ",listIsNull:" + (list == null) + ",list.length=" +
                 (list != null ? list.size() : 0));

        if (list != null) {
            request = list.get(0);
        } else {
            log.error("------------获取记录失败,jobName:" + context.getJobDetail().getKey().getName() + ",jobWasExecuted-执行完毕---------------");
        }
        StringBuilder detailMessageOfError = new StringBuilder();
        if (jobException != null && jobException.getStackTrace().length > 0) {
            log.error("Job<group,name>:" + context.getJobDetail().getKey().toString() + "------------job-WasExecuted-error-start---------------");
            detailMessageOfError = new StringBuilder().append("Error: ").append(jobException.getMessage() == null ? "" : jobException.getMessage()).append("\r\n");
            for (StackTraceElement item : jobException.getStackTrace()) {
                detailMessageOfError.append("\t").append(item.toString()).append("\r\n");
            }
            if (jobException.getCause() != null) {
                for (StackTraceElement item : jobException.getCause().getStackTrace()) {
                    detailMessageOfError.append("Cause By: \r\n").append(item.toString()).append("\r\n");
                }
            }
            log.error(detailMessageOfError.toString());
            log.error("Job<group,name>:" + context.getJobDetail().getKey().toString() + "------------job-WasExecuted-error-end---------------");
        }
        if (request != null) {
            totalCount = request.getWasExecutedTotalCount();
            errorCount = request.getWasExecutedFailCount();
        }
        Date previousFireTime = context.getTrigger().getPreviousFireTime();
        Date nextFireTime = context.getTrigger().getNextFireTime();
        if (request != null) {
            if (previousFireTime != null) {
                request.setPreviousFireTime(new Timestamp(previousFireTime.getTime()));
            }
            if (nextFireTime != null) {
                request.setNextFireTime(new Timestamp(nextFireTime.getTime()));
            }
        }
        if (context.getTrigger().mayFireAgain()) {
            if (request != null) {
                request.setStatusCode(detailMessageOfError.length() > 0 ? "ERROR_STATUSCODE" : "OK_STATUSCODE");
                request.setPhaseCode("JOBWAITING_PHASECODE");
                request.setActualCompletionDate(null);
            }
            totalCount = totalCount + 1;
            request.setWasExecutedTotalCount(totalCount);
            info(context.getJobDetail().getKey().getName(), " - 请求 第 " + totalCount + " 次 执行完毕 ————————");
        } else {
            if (request != null) {
                request.setActualCompletionDate(new Timestamp(new Date().getTime()));
                request.setStatusCode(detailMessageOfError.length() > 0 ? "ERROR_STATUSCODE" : "OK_STATUSCODE");
                request.setPhaseCode("JOBWASEXECUTED_PHASECODE");
            }
            totalCount = totalCount + 1;
            request.setWasExecutedTotalCount(totalCount);
            info(context.getJobDetail().getKey().getName(), " - 请求 第 " + totalCount + " 次 执行完毕 ————————");
            MyLogUtils.flushLogFileConstants(context.getJobDetail().getKey().getName());
        }
        if (detailMessageOfError != null && detailMessageOfError.length() > 0) {
            errorCount = errorCount + 1;
            request.setWasExecutedFailCount(errorCount);
        }
        request.setWasExecutedSuccessCount(totalCount - errorCount);
        saafSchedulesDAOHI.saveOrUpdate(request);
        log.info("------------job-WasExecuted-执行完毕---------------" + context.getJobDetail().toString());
        if (detailMessageOfError != null && detailMessageOfError.length() > 0) {
            error(context.getJobDetail().getKey().getName(), detailMessageOfError.toString());
            log.error(detailMessageOfError.toString());
        }
    }

}
