package com.ldz.quartz;
import org.quartz.*;

import java.util.Date;

/*@PersistJobDataAfterExecution
@DisallowConcurrentExecution*/
public class TaskJob implements Job {

    public static final String NUM_EXECUTIONS = "NumExecutions";
    public static final String EXECUTION_DELAY = "ExecutionDelay";

    /**
     * 静态变量可以保持工作状态，但无法达到预期效果
     */
    private static int _staticCounter = 0;

    /**
     * Quartz 每次执行作业时都会重新实例化，非静态变量无法保持工作状态
     */
    private int _counter = 0;

    /**
     * 需要一个公共的空构造方法，以便 scheduler 随时实例化 job
     */
    public TaskJob() {
    }

    /**
     * 该方法实现需要执行的任务
     */
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.err.println("---> " + context.getJobDetail().getKey() + " 运行中[" + new Date() + "]");

        JobDataMap map = context.getJobDetail().getJobDataMap();

        int executeCount = 0;
        if (map.containsKey(NUM_EXECUTIONS)) {
            executeCount = map.getInt(NUM_EXECUTIONS);
        }

        // 增量计数并将其存储回 JobDataMap，这样可以适当保持工作状态
        executeCount++;
        map.put(NUM_EXECUTIONS, executeCount);

        // 只要有任务执行都会递增，无法达到预期效果
        _staticCounter++;

        // 本地变量递增加，但实际上无法保持工作状态
        _counter++;

        long delay = 5000L;
        if (map.containsKey(EXECUTION_DELAY)) {
            delay = map.getLong(EXECUTION_DELAY);
        }

        try {
            // 模拟一个耗时的 job
            Thread.sleep(delay);
        } catch (InterruptedException e) {
        }

        System.err.println(context.getJobDetail().getKey() + " 的静态变量 _staticCounter 为：" + _staticCounter + "，非静态变量 scheduler 为：" + _counter);
        System.err.println(context.getJobDetail().getKey() + " 完成了（" + executeCount + "）次 <---");
    }
}