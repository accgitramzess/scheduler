package scheduler.framework.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import org.springframework.beans.factory.annotation.Autowired;
import scheduler.framework.core.annotation.QuartzJob;
import scheduler.framework.service.ReportService;

/**
 * Created by LilG2pac on 30.04.2018.
 */
@QuartzJob
public class ReportJob implements Job {

    @Autowired
    private ReportService reportService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap data = jobExecutionContext.getMergedJobDataMap();
        System.out.println(data.get("name"));
        reportService.save();
    }
}
