package scheduler.framework.core;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by LilG2pac on 30.04.2018.
 */
@Component
public class QuartzScheduler {

    private SchedulerFactoryBean schedulerFactoryBean;
    private DefaultQuartzJobFactory quartzJobFactory;

    @Autowired
    public QuartzScheduler(SchedulerFactoryBean schedulerFactoryBean,
                           DefaultQuartzJobFactory quartzJobFactory) {
        this.schedulerFactoryBean = schedulerFactoryBean;
        this.quartzJobFactory = quartzJobFactory;
    }

    @PostConstruct
    public void init() throws SchedulerException {
        scheduleJobs();
    }

    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        List<QuartzJobData> quartzJobData = quartzJobFactory.getQuartzJobs();
        for (QuartzJobData model : quartzJobData) {
            scheduler.scheduleJob(model.getJobDetail(), model.getTrigger());
        }
        scheduler.start();
    }
}
