package com.ldz.quartz;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class Executer {

    public void run() throws Exception {
        // 通过 schedulerFactory 获取一个调度器
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        // 创建 jobDetail 实例，绑定 Job 实现类
        // 指明 job 的名称，所在组的名称，以及绑定 job 类
        JobDetail job1 = JobBuilder.newJob(TaskJob.class)
                .withIdentity("statefulJob1", "group1")
                // 给定的键-值对添加到 JobDetail 的 JobDataMap 中
                .usingJobData(TaskJob.EXECUTION_DELAY, 10000L).build();

        // 定义调度触发规则，先立即执行一次，然后每隔 3 秒执行一次
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever())
                .build();

        // 把作业和触发器注册到任务调度中
        Date firstRunTime = sched.scheduleJob(job1, trigger);
        System.out.println(job1.getKey() + " 开始运行于：" + firstRunTime + "，重复：" + trigger.getRepeatCount() + " 次，每次间隔 "
                + trigger.getRepeatInterval() / 1000 + " 秒");

        // 任务 job1 方法中拿到的 JobDataMap 的数据是共享的
        // 这里要注意一个情况： 就是 JobDataMap 的数据共享只针对一个 job1 任务
        // 如果在下面在新增加一个任务 那么他们之间是不共享的，比如下面的 job2
        // 创建第二个 JobDetail 实例
        JobDetail job2 = JobBuilder.newJob(TaskJob.class)
                .withIdentity("statefulJob2", "group1")
                // 给定的键-值对添加到 JobDetail 的 JobDataMap 中
                .usingJobData(TaskJob.EXECUTION_DELAY, 10000L)
                .build();

        // 定义调度触发规则，先立即执行一次，然后每隔 3 秒执行一次
        trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().
                        withIntervalInSeconds(3)
                        .repeatForever()
                        // 指定失效时的策略
                        .withMisfireHandlingInstructionNowWithExistingCount())
                .build();

        // 这个 job2 与 job1 执行的 JobDataMap 不共享
        // 把作业和触发器注册到任务调度中
        firstRunTime = sched.scheduleJob(job2, trigger);
        System.out.println(job2.getKey() + " 开始运行于：" + firstRunTime + "，重复：" + trigger.getRepeatCount() + " 次，每次间隔 "
                + trigger.getRepeatInterval() / 1000 + " 秒");

        // 启动计划程序（实际上直到调度器已经启动才会开始运行）
        sched.start();

        // 等待 60 秒，使我们的 job 有机会执行
        Thread.sleep(60000);

        // 等待作业执行完成时才关闭调度器
        sched.shutdown(true);

        SchedulerMetaData metaData = sched.getMetaData();
        System.out.println("一共运行了：" + metaData.getNumberOfJobsExecuted() + " 个任务");
    }

    public static void main(String[] args) throws Exception {
        Executer example = new Executer();
        example.run();
    }
}