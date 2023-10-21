package com.genericscheduler.differentimpl.source.scheduler;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

import com.genericscheduler.scheduler.GenericScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class GenericSchedulerService extends ScheduledAnnotationBeanPostProcessor {

    //private final Map<Object, Set<ScheduledTask>> scheduledTasksMap;

    @Autowired
    private ApplicationContext applicationContext;

    private GenericSchedulerService() {

    }

    public Optional<ScheduledTask> getCronTaskByCronExpression(String cronExpression) {
        Set<ScheduledTask> scheduledTasks = getScheduledTasks();
        for (ScheduledTask scheduledTask : scheduledTasks) {
            if (scheduledTask.getTask() instanceof CronTask) {
                CronTask cronTask = (CronTask) scheduledTask.getTask();
                if (cronTask.getExpression().equals(cronExpression)) {
                    scheduledTask.cancel();

                    return Optional.of(scheduledTask);
                }
            }
        }

        return Optional.empty();
    }

    public void removeCronTask(Class<?> class_, String fieldName) throws IllegalAccessException, NoSuchFieldException {
        Field field = ReflectionUtils.findField(class_, fieldName);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        Map<Object, Set<ScheduledTask>> scheduledTasksMap = (Map<Object, Set<ScheduledTask>>) field.get(this);
        System.out.println(scheduledTasksMap);
        GenericScheduler bean = applicationContext.getBean(GenericScheduler.class);
        Optional<ScheduledTask> cronTaskByCronExpression = this.getCronTaskByCronExpression("0 * * * * *");
        Set<ScheduledTask> scheduledTasks = scheduledTasksMap.get(bean);
        if (cronTaskByCronExpression.isPresent()) {
            ScheduledTask scheduledTask = cronTaskByCronExpression.get();
            scheduledTasks.remove(scheduledTask);
        }
        scheduledTasksMap = (Map<Object, Set<ScheduledTask>>) field.get(this);
        System.out.println(scheduledTasksMap);

        Field registrar = ReflectionUtils.findField(class_, "registrar");
        if (!registrar.isAccessible()) {
            registrar.setAccessible(true);
        }

        ScheduledTaskRegistrar scheduledTaskRegistrar = (ScheduledTaskRegistrar) registrar.get(this);
        //List<CronTask> cronTasks = scheduledTaskRegistrar.getCronTaskList();
        //System.out.println("cronTasks before " + cronTasks);
        Field fieldOfScheduledTasksFrom_ScheduledTaskRegistrar = ReflectionUtils.findField(ScheduledTaskRegistrar.class, "scheduledTasks");
        if (!fieldOfScheduledTasksFrom_ScheduledTaskRegistrar.isAccessible()) {
            fieldOfScheduledTasksFrom_ScheduledTaskRegistrar.setAccessible(true);
        }
        Set<ScheduledTask> scheduledTasksFromScheduledTaskRegistrar = (Set<ScheduledTask>) fieldOfScheduledTasksFrom_ScheduledTaskRegistrar.get(scheduledTaskRegistrar);
        System.out.println("scheduledTasksFromScheduledTaskRegistrar before " + scheduledTasksFromScheduledTaskRegistrar);
        scheduledTasksFromScheduledTaskRegistrar.forEach(task -> {
            System.out.println("Cancel task " + task);
            task.cancel();
        });
        System.out.println("scheduledTasksFromScheduledTaskRegistrar before " + scheduledTasksFromScheduledTaskRegistrar);
    }
}
