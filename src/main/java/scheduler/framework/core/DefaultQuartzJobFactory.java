package scheduler.framework.core;

import org.quartz.*;
import org.springframework.stereotype.Component;
import scheduler.framework.job.ReportJob;

import java.util.ArrayList;
import java.util.List;

import static org.quartz.CronScheduleBuilder.cronSchedule;

/**
 * Created by LilG2pac on 09.05.2018.
 */
@Component
public class DefaultQuartzJobFactory {

    public List<QuartzJobData> getQuartzJobs() {

        List<QuartzJobData> quartzJobsData = new ArrayList<>();

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name", "report-job");

        JobDetail jobDetail = JobBuilder.newJob(ReportJob.class)
                .setJobData(jobDataMap)
                .withDescription("Amazon reports job analyzer")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withSchedule(cronSchedule("0/5 * * * * ?"))
                .build();

        QuartzJobData quartzJobData = new QuartzJobData(jobDetail, trigger);

        quartzJobsData.add(quartzJobData);

        return quartzJobsData;
    }
}
