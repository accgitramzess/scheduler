package scheduler.framework.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import scheduler.framework.core.annotation.QuartzJob;

/**
 * Created by LilG2pac on 05.05.2018.
 */
@QuartzJob
public class TestJob implements Job {

    public final static String JOB_KEY = "testJob";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap data = jobExecutionContext.getMergedJobDataMap();
        System.out.println("Test job");
    }
}
