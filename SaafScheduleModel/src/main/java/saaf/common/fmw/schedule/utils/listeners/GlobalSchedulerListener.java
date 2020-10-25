package saaf.common.fmw.schedule.utils.listeners;


import com.yhg.hibernate.core.dao.ViewObject;

import java.sql.Timestamp;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import saaf.common.fmw.schedule.model.entities.SaafSchedulesEntity_HI;
import saaf.common.fmw.schedule.utils.MyLogUtils;
import static saaf.common.fmw.schedule.utils.MyLogUtils.info;


public class GlobalSchedulerListener implements SchedulerListener {

    private final Logger log = LoggerFactory.getLogger(GlobalSchedulerListener.class);

    private ViewObject saafSchedulesDAOHI;

    @Resource(name = "saafSchedulesDAO_HI")
    public void setSaafSchedulesDAOHI(ViewObject saafSchedulesDAOHI) {
        this.saafSchedulesDAOHI = saafSchedulesDAOHI;
    }

    public GlobalSchedulerListener() {
        super();
    }

    @Override
    public void jobScheduled(Trigger trigger) {
        log.info("------------job-Scheduled-Job被部署到调度器---------------" + trigger.getJobKey().toString());
        info(trigger.getJobKey().getName(), " - 请求被部署到调度器");
    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        SaafSchedulesEntity_HI request = null;
        List<SaafSchedulesEntity_HI> list = saafSchedulesDAOHI.findByProperty("quartzJobName", triggerKey.getName());
        log.info("----------------------jobUnscheduled-" + triggerKey.getName() + ",listIsNull:" + (list == null) + ",list.length=" + (list != null ? list.size() : 0));
        if (list != null && list.size() > 0) {
            request = list.get(0);
        } else {
            log.error("------------获取记录失败,jobName:" + triggerKey.getName() + "job-Unscheduled-Job从调度器卸载---------------");
        }
        if (request != null) {
            request.setStatusCode("OK_STATUSCODE");
            request.setPhaseCode("JOBUNSCHEDULED_PHASECODE");
        }
        log.info("------------job-Unscheduled-Job从调度器卸载---------------" + triggerKey.toString());
        info(triggerKey.getName(), " - 请求被卸载（取消执行）");
        if (request != null) {
            request.setActualCompletionDate(new Timestamp(new Date().getTime()));
        }
        saafSchedulesDAOHI.saveOrUpdate(request);
        MyLogUtils.flushLogFileConstants(triggerKey.getName());
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        log.info("------------trigger-Finalized-触发器完成（Finalized）---------------" + trigger.getKey() + " , " + trigger.getJobKey().toString());
    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        log.info("------------trigger-Paused-触发器被暂停---------------" + triggerKey.toString());
    }

    @Override
    public void triggersPaused(String triggerGroup) {
        log.info("------------trigger-Paused-触发器组（group）被暂停---------------" + triggerGroup);
    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {
        log.info("------------trigger-Resumed-触发器继续执行（重启）---------------" + triggerKey.toString());
    }

    @Override
    public void triggersResumed(String triggerGroup) {
        log.info("------------trigger-Paused-触发器组（group）继续执行（重启）---------------" + triggerGroup);
    }

    @Override
    public void jobAdded(JobDetail jobDetail) {
        log.info("------------job-Added-Job被加载---------------" + jobDetail.getKey().toString());
        info(jobDetail.getKey().getName(), " - 请求被加载");
    }

    @Override
    public void jobDeleted(JobKey jobKey) {
        log.info("------------job-Deleted-Job被删除---------------" + jobKey.toString());
        info(jobKey.getName(), " - 请求被删除");
    }

    @Override
    public void jobPaused(JobKey jobKey) {
        ViewObject vo = this.saafSchedulesDAOHI;
        SaafSchedulesEntity_HI request = null;
        List<SaafSchedulesEntity_HI> list = vo.findByProperty("quartzJobName", jobKey.getName());
        log.info("----------------------jobPaused-" + jobKey.getName() + ",listIsNull:" + (list == null) + ",list.length=" + (list != null ? list.size() : 0));
        if (list != null) {
            request = list.get(0);
        } else {
            log.error("------------获取记录失败,jobName:" + jobKey.getName() + ",job-Paused-Job被暂停执行---------------");
        }
        if (request != null) {
            request.setStatusCode("OK_STATUSCODE");
            request.setPhaseCode("JOBPAUSED_PHASECODE");
        }
        vo.saveOrUpdate(request);
        log.info("------------job-Paused-Job被暂停执行---------------" + jobKey.toString());
        info(jobKey.getName(), " - 请求被暂停");
        MyLogUtils.flushLogFileConstants(jobKey.getName());
    }

    @Override
    public void jobsPaused(String jobGroup) {
        log.info("------------jobGroup-Paused-Job组（group）被暂停执行---------------" + jobGroup);
    }

    @Override
    public void jobResumed(JobKey jobKey) {
        SaafSchedulesEntity_HI request = null;
        List<SaafSchedulesEntity_HI> list = saafSchedulesDAOHI.findByProperty("quartzJobName", jobKey.getName());
        log.info("----------------------jobResumed-" + jobKey.getName() + ",listIsNull:" + (list == null) + ",list.length=" + (list != null ? list.size() : 0));
        if (list != null) {
            request = list.get(0);
        } else {
            log.error("------------获取记录失败,jobName:" + jobKey.getName() + ",job-Resumed-Job被继续执行---------------");
        }
        if (request != null) {
            request.setStatusCode("OK_STATUSCODE");
            request.setPhaseCode("JOBWAITING_PHASECODE");
        }
        saafSchedulesDAOHI.saveOrUpdate(request);
        log.info("------------job-Resumed-Job被继续执行---------------" + jobKey.toString());
        info(jobKey.getName(), " - 请求被恢复继续执行");
    }

    @Override
    public void jobsResumed(String jobGroup) {
        log.info("------------jobGroup-Resumed-Job组（group）被继续执行---------------" + jobGroup);
    }

    @Override
    public void schedulerError(String msg, SchedulerException cause) {
        log.error("------------scheduler-Error-调度器内部执行错误---------------");
        StringBuilder detailMessageOfError = new StringBuilder();
        if (cause != null && cause.getStackTrace().length > 0) {
            log.error("------------schedulerError-start---------------");
            detailMessageOfError = new StringBuilder().append("Error: ").append(cause.getMessage() == null ? "" : cause.getMessage()).append("\r\n");
            for (StackTraceElement item : cause.getStackTrace()) {
                detailMessageOfError.append("\t").append(item.toString()).append("\r\n");
            }
            if (cause.getCause() != null) {
                for (StackTraceElement item : cause.getCause().getStackTrace()) {
                    detailMessageOfError.append("Cause By: \r\n").append(item.toString()).append("\r\n");
                }
            }
            log.error(detailMessageOfError.toString());
            log.error("------------schedulerError-end---------------");
        }
    }

    @Override
    public void schedulerInStandbyMode() {
        log.info("------------scheduler-InStandbyMode-调度器处于 standby 模式---------------");
    }

    @Override
    public void schedulerStarted() {
        log.info("------------scheduler-Started-调度器已启动---------------");
    }

    @Override
    public void schedulerStarting() {
        log.info("------------scheduler-Starting-调度器启动中（ing）---------------");
    }

    @Override
    public void schedulerShutdown() {
        log.info("------------scheduler-Shutdown-调度器已停止运行---------------");
    }

    @Override
    public void schedulerShuttingdown() {
        log.info("------------scheduler-Shuttingdown-调度器正在停止（ing）---------------");
    }

    @Override
    public void schedulingDataCleared() {
        log.info("------------schedulingDataCleared-所有数据已被删除（!）---------------");
    }

}
