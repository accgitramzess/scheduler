package com.genericscheduler.differentimpl.config;

import java.util.List;
import java.util.Set;

import com.genericscheduler.differentimpl.source.scheduler.GenericSchedulerService;
import com.genericscheduler.scheduler.GenericScheduler;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.genericscheduler.differentimpl.source.event.scheduler.SchedulerSourceEventPublisher;

@Configuration
@RequiredArgsConstructor
@EnableScheduling
//@EnableConfigurationProperties(value = GenericSchedulerProperties.class)
public class MyGenericSchedulerConfiguration implements SchedulingConfigurer {

   // @Autowired
    //private final GenericSchedulerProperties genericSchedulerProperties;

    //@Autowired
    //private final FlowsFactoryImpl flowsFactory;

    @Autowired
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private final GenericSchedulerService genericSchedulerService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        /*List<GenericSchedulerProperties.Cron> crons = genericSchedulerProperties.getCrons();
        crons.forEach(cron -> {
            CronTask cronTask = new CronTask(
                    new SchedulerSourceEventPublisher(cron, applicationEventPublisher),
                    cron.getCron()
            );
            taskRegistrar.addCronTask(cronTask);
        });*/
        /*Set<ScheduledTask> scheduledTasks = taskRegistrar.getScheduledTasks();
        List<CronTask> cronTaskList = taskRegistrar.getCronTaskList();
        //System.out.println("scheduler!!!");
        genericSchedulerService.getCronTaskByCronExpression("0 * * * * *");
        try {
            //genericSchedulerService.removeCronTask(ScheduledAnnotationBeanPostProcessor.class, "scheduledTasks");
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }
}
