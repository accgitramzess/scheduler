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
    private QuartzJobContextFactory quartzJobContextFactory;

    @Autowired
    public QuartzScheduler(SchedulerFactoryBean schedulerFactoryBean,
                           QuartzJobContextFactory quartzJobContextFactory) {
        this.schedulerFactoryBean = schedulerFactoryBean;
        this.quartzJobContextFactory = quartzJobContextFactory;
    }

    @PostConstruct
    public void init() throws SchedulerException {
        scheduleJobs();
    }

    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        List<QuartzJobContext> quartzJobContexts = quartzJobContextFactory.getJobsContext();
        for (QuartzJobContext model : quartzJobContexts) {
            scheduler.scheduleJob(model.getJobDetail(), model.getTrigger());
        }
        scheduler.start();
    }
}
