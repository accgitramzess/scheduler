package com.genericscheduler.differentimpl.config.source.scheduler;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.genericscheduler.differentimpl.config.MyGenericSchedulerConfiguration;

/*@Configuration
@ConditionalOnProperty(value="source-impl.scheduler.enabled", havingValue = "true", matchIfMissing = true)
@EnableScheduling
@Import(MyGenericSchedulerConfiguration.class)*/
public class SchedulerSourceConfig {


}
