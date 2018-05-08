package scheduler.framework.configuration;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import scheduler.framework.core.QuartzScheduler;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by LilG2pac on 30.04.2018.
 */
//@Configuration
//@EnableConfigurationProperties(QuartzProperties.class)
public class SchedulerConfig {

    private QuartzProperties quartzProperties;

    @Autowired
    public SchedulerConfig(QuartzProperties quartzProperties) {
        this.quartzProperties = quartzProperties;
    }

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {

        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);

        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) throws IOException {

        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setAutoStartup(true);
        factory.setJobFactory(jobFactory);

        Properties properties = new Properties();
        properties.setProperty("org.quartz.scheduler.instanceName", quartzProperties.getScheduler().getInstanceName());
        properties.setProperty("org.quartz.scheduler.instanceId", quartzProperties.getScheduler().getInstanceId());
        properties.setProperty("org.quartz.threadPool.threadCount", quartzProperties.getThreadPool().getThreadCount());

        factory.setQuartzProperties(properties);

        return factory;
    }
}
