package scheduler.framework.core;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import scheduler.framework.configuration.JobProperties;
import scheduler.framework.configuration.QuartzProperties;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.quartz.CronScheduleBuilder.cronSchedule;

/**
 * Created by LilG2pac on 30.04.2018.
 */
@Component
public class QuartzJobContextFactory {

    private QuartzProperties quartzProperties;

    private QuartzJobDetector quartzJobDetector = new QuartzJobDetector();

    @Autowired
    public QuartzJobContextFactory(QuartzProperties quartzProperties) {
        this.quartzProperties = quartzProperties;
    }

    public List<QuartzJobContext> getJobsContext() {

        Map<String, Class<? extends Job>> jobs = quartzJobDetector.detect("scheduler.framework.job");

        Map<String, JobProperties> jobsProperties = quartzProperties.getJobsProperties();

        return jobs.keySet()
                .stream()
                .map(jobKey -> generateJobContext(jobs.get(jobKey), jobsProperties.get(jobKey)))
                .collect(Collectors.toList());
    }

    private QuartzJobContext generateJobContext(Class<? extends Job> jobClass, JobProperties job) {

        JobDetail jobDetail = createJobDetail(jobClass, job);
        Trigger trigger = createTrigger(job, jobDetail);
        QuartzJobContext quartzJobContext = new QuartzJobContext(jobDetail, trigger);

        return quartzJobContext;
    }

    private JobDetail createJobDetail(Class<? extends Job> jobClass, JobProperties job) {

        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .setJobData(createDataMap(job))
                .withDescription(job.getProperties().get("description"))
                .withIdentity(job.getProperties().get("description"), job.getProperties().get("description"))
                .build();

        return jobDetail;
    }

    private JobDataMap createDataMap(JobProperties job) {

        JobDataMap jobDataMap = new JobDataMap(job.getProperties());

        return jobDataMap;
    }

    private Trigger createTrigger(JobProperties job, JobDetail jobDetail) {

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withSchedule(cronSchedule(job.getProperties().get("cron-expression")))
                .build();

        return trigger;
    }
}
