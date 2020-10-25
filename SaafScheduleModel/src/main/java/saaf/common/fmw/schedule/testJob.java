package saaf.common.fmw.schedule;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import saaf.common.fmw.schedule.utils.ScheduleUtil;
 
public class testJob {
	private static final Logger logger = LoggerFactory.getLogger(testJob.class);
	public void saveJobInfo(JobExecutionContext parameters) throws Exception {
	
		System.out.println("paramete**************rs:"+parameters);
		String testParam =  ScheduleUtil.getParamsJsonStr(parameters);
		
		Object testParam2 = ScheduleUtil.getArguments(parameters, "testParam");
		
		
		
		Object	testParam3 = ScheduleUtil.getParamDateStr(parameters,"testParam");


		System.out.println(testParam2+"testJob testParam***"+testParam3+"***********"+testParam);
	
	}
	
	
}
