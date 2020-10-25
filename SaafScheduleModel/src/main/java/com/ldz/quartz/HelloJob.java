package com.ldz.quartz;
 
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.impl.JobDetailImpl;
@DisallowConcurrentExecution
//或者继承
public class HelloJob implements Job/*implements StatefulJob*/
{  
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		
		System.out.println("start:"+new Date());
		 try{
             Thread.currentThread().sleep(6000);
          }catch(InterruptedException ie){
			 ie.printStackTrace();
          }
		//System.out.println("hello quartz:");
		System.out.println("end:"+new Date());
 
	}
}